package annotation.apt;

import annotation.annotations.IdAnnotation;
import annotation.annotations.PersistentAnnotation;
import annotation.annotations.PropertyAnnotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

/**
 * 指定处理的注解
 *
 * 编译生成注解处理器
 * javac -d target/ annotation/apt/HibernateAnnotationProcessor.java
 *
 * 执行注解处理器
 * javac -d target/ -processor annotation.apt.HibernateAnnotationProcessor -processorpath target annotation/base/Pet.java
 *
 * todo 问题: 无法生成相应xml配置文件，路径问题？ -verbose详细打印也看不出来？
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"PersistentAnnotation", "IdAnnotation", "PropertyAnnotation"})
public class HibernateAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        PrintStream stream = null;
        StringBuilder sb = new StringBuilder();
        try {
            //
            for (Element element : roundEnv.getElementsAnnotatedWith(PersistentAnnotation.class)) {
                // 获取处理的类名
                Name clazzName = element.getSimpleName();
                System.out.println(clazzName);

                stream = new PrintStream(new FileOutputStream(clazzName + ".hbm.xml"));
                PersistentAnnotation persistentAnnotation = element.getAnnotation(PersistentAnnotation.class);
                sb.append("<?xml version=\"1.0\"?>\n");
                sb.append("<!DOCTYPE hibernate-mapping PUBLIC\"-//Hibernate/Hibernate Mapping DTD 3.0 //EN\" \"http://www.hibernate.org/dtd/hiebernate-mapping-3.0.tdt\">\n");
                sb.append("<hibernate-mapping>\n");
                sb.append("<class name=\"" + element + "\" table=\"" + persistentAnnotation.table() + "\">\n");
//                sb.append("")
                // 处理内部元素
                for (Element subElement : element.getEnclosedElements()) {
                    if (subElement.getKind() == ElementKind.FIELD) {
                        IdAnnotation idAnnotation = subElement.getAnnotation(IdAnnotation.class);
                        if (idAnnotation != null) {
                            sb.append("<id name=\"" + subElement.getSimpleName()+ "\" column=\"" + idAnnotation.column() + "\" type=\"" + idAnnotation.type() + "\">\n");
                            sb.append("<generator class=\"" + idAnnotation.generator() + "\">\n");
                            sb.append("</id>\n");
                        }

                        PropertyAnnotation propertyAnnotation = subElement.getAnnotation(PropertyAnnotation.class);
                        if (propertyAnnotation != null) {
                            sb.append("<property name=\"" + subElement.getSimpleName() + "\" column=\"" + propertyAnnotation.column() + "\" type=\"" + propertyAnnotation.type() + "\">\n");
                        }
                    }
                }
                sb.append("</class>\n");
                sb.append("</hibernate-mapping>\n");

                System.out.println(sb.toString());
                stream.print(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
