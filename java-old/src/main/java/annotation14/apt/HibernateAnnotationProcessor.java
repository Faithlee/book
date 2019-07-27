package annotation14.apt;

import annotation14.annotation.Id;
import annotation14.annotation.Persistent;
import annotation14.annotation.Property;

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

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"Persistent", "Id", "Property"})
public class HibernateAnnotationProcessor extends AbstractProcessor
{

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        PrintStream ps = null;
        try {
            for (Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)) {
                Name className = t.getSimpleName();
                Persistent per = t.getAnnotation(Persistent.class);

                // 创建文件输出流
                ps = new PrintStream(new FileOutputStream(className + ".hbm.xml"));
                ps.println("<?xml version=\"1.0\"?>");
                ps.println("<?DOCTYPE hibernate-mapping PUBLIC");
                ps.println("    \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
                ps.println("    \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\"");
                ps.println("<hibernate-mapping>");
                ps.println("    <class name=\"" + t + "\" table=\"" + per.table() + "\">");
                for (Element f : t.getEnclosedElements()) {
                    // 只处理成员变量上的annotation
                    if (f.getKind() == ElementKind.FIELD) {
                        Id id = f.getAnnotation(Id.class);
                        if (id != null) {
                            ps.println("        <id name=\"" + f.getSimpleName() + "\" column=\"" + id.column() + "\" type=\"" + id.type() + "\">");
                            ps.println("        <generator class=\"" + id.generator() + "\"/>");
                            ps.println("        </id>");
                        }
                        Property p = f.getAnnotation(Property.class);
                        if (p != null) {
                            ps.println("        <property name=\"" + f.getSimpleName() + "\" column=\"" + p.column() + "\" type=\"" + p.type() + "\"/>");
                        }
                    }
                }

                ps.println("    </class>");
                ps.println("</hibernate-mapping>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }
}
