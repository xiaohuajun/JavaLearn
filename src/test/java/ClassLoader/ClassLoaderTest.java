package ClassLoader;


import org.junit.Test;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/8 19:02
 * @description 一、类加载器:
 * 1、Bootstrap ClassLoader ：加载JAVA_HOME/jre/lib/ 的类，Java的核心类:rt.jar
 * 2、ExtClassLoader : 加载JAVA_HOME/jre/lib/ext/ 的类
 * 3、AppClassLoader : 加载开发者在项目中的自定义的类  classPath
 * 二、加载机制：双亲委派：什么是双亲？------> AppClassLoader的双亲是：ExtClassLoader、Bootstrap ClassLoader
 * 级别高低依次是：Bootstrap ClassLoader   ExtClassLoader   AppClassLoader
 * 委派顺序：，需要加载类的时候先把需要加载的类委派到 Bootstrap ClassLoader中；遍历所加载的目录下是否存在该类；若存在直接加载返回
 * 若不存在，再委派到ExtClassLoader中；遍历所加载的目录下是否存在该类；若存在直接加载返回；若不存在
 * 再委派到AppClassLoader中；遍历所加载的目录下是否存在该类；若存在直接加载返回
 */
public class ClassLoaderTest {

    @Test
    public void loadClass() throws ClassNotFoundException {
        Class<?> musicPlayerClass = Class.forName("learn.classsload.MusicPlayer");
        ClassLoader classLoader = musicPlayerClass.getClassLoader();
        System.out.println("加载器的名字----->" + classLoader.getClass().getSimpleName());
        while (classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
            //Bootstrap ClassLoader 是由jvm内部实现，这里拿不到
            System.out.println("父类加载器的名字------>" + classLoader.getClass().getSimpleName());
        }
    }

    @Test
    public void loadExtClassLoader() throws ClassNotFoundException {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader(); // AppClassLoader
        Class<?> clazz = appClassLoader.loadClass("learn.classsload.MusicPlayer");
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.printf("ClassLoader is %s", classLoader.getClass().getSimpleName());
    }


}
