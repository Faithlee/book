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


class ClassLoaderProp {

    public static void load() throws Exception {
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("" +systemLoader);

        Enumeration<URL> urlEnumeration = systemLoader.getResources("");
        while (urlEnumeration.hasMoreElements()) {
            System.out.println(urlEnumeration.nextElement());
        }
    }
}
