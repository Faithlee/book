package classloader;

import java.net.URL;
import java.util.Enumeration;

/**
 * 类加载机制
 *
 * 1. 全盘负责
 *
 * 2. 父类委托
 *
 * 3. 缓存机制
 */
public class ClassLoaderMechanism {

    public static void main(String[] args) throws Exception {
        // 根类加载器加载核心类库
        BootstrapClassLoader.loadCoreLibrary();

        System.out.println("类加载器层次结构：");
        ClassLoaderProp.load();
    }
}

/**
 * 根类加载器: 由jvm实现，由根类实现的加载核心类库
 *
 * 扩展类加载器：负责加载jre扩展目录的jar包
 *
 * 系统类加载器：负责加载jvm启动后classpath所指定的jar包
 *
 * 用户类加载器
 */
class BootstrapClassLoader {

    public static void loadCoreLibrary() {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

        for (URL url : urls) { System.out.println(url.toExternalForm());
        }
    }
}

/**
 * 类加载器层次结构
 *
 * 类加载器以双亲委派的模式实现的，优先使用父类加载器增加了安全保障
 */
class ClassLoaderProp {

    public static void load() throws Exception {
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("" +systemLoader);

        // 获取系统类加载器的加载路径：通常由CLASSSPATH环境变量指定；
        Enumeration<URL> urlEnumeration = systemLoader.getResources("");
        while (urlEnumeration.hasMoreElements()) {
            System.out.println(urlEnumeration.nextElement());
        }

        // 获取扩展类加载器：加载
        ClassLoader extensionLoader = systemLoader.getParent();
        System.out.println("扩展类加载器: " + extensionLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));

        // 获取根类加载器，返回null，由jvm实现根类加载器
        System.out.println("扩展类加载器的parent：" + extensionLoader.getParent());
    }
}


