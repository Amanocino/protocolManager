package driver.driver.common;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author : zhicheng chen
 * @date : 2021/8/17
 * @time : 10:27
 */
public class LoadJarUtils extends URLClassLoader {

    public LoadJarUtils(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public LoadJarUtils(URL[] urls) {
        super(urls);
    }

    public LoadJarUtils(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
        //test
    }

    public void loadJar(String jarPath, Map<String , byte[]> classBytesMap, Map<String, Class> cacheClassMap, Map<String, Object> cacheObjectMap) throws FileNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        URL url = null;
        int bufferSize = 4096;
        if (!jarPath.endsWith(".jar")){
            throw new IllegalArgumentException("jarFiles is not a jar"+jarPath);
        }

        InputStream inputStream = null;
        try{
            JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
            while (jarEntryEnumeration.hasMoreElements()){
                JarEntry jarEntry = jarEntryEnumeration.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(".class")){
                    String className = name.replace(".class", "").replaceAll("/", ".");
                    inputStream = jarFile.getInputStream(jarEntry);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    byte[] buffer = new byte[bufferSize];
                    int bytesNumRead = 0;
                    while((bytesNumRead = inputStream.read(buffer)) != -1){
                        byteArrayOutputStream.write(buffer, 0, bytesNumRead);
                    }
                    byte[] classBytes = byteArrayOutputStream.toByteArray();
                    classBytesMap.put(className, classBytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try{
                    inputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        for (Map.Entry<String, byte[]> entry : classBytesMap.entrySet()){
            String key = entry.getKey();
            Class<?> classTmp = null;

            if (cacheObjectMap.containsKey(jarPath) && null!=cacheObjectMap.get(jarPath)){
                return;
            }
            try{
                classTmp = loadClass(key, cacheClassMap, jarPath);
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            IScadaDriverTag anno = classTmp.getAnnotation(IScadaDriverTag.class);
            if (anno != null) {
                //将注解中的类型值作为key，对应的类作为 value
                cacheClassMap.put(jarPath, classTmp);
//                Class<?> clazz = classTmp;
                Object obj = classTmp.getConstructor().newInstance();
                cacheObjectMap.put(jarPath, obj);
            }
//            if (null!=classTmp && classTmp.newInstance() instanceof IScadaDriver){
//
//            }

        }

        //加载指定路径的jar
//            JarFile
    }

    public void loadJars(List<String> jarPaths, Map<String , byte[]> classBytesMap, Map<String, Class> cacheClassMap, Map<String, Object> cacheObjectMap) throws FileNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        URL url = null;
        if (null!=jarPaths && 0<jarPaths.size()){
            for (String jarPath:jarPaths){
                loadJar(jarPath, classBytesMap, cacheClassMap, cacheObjectMap);
            }
        }

        //加载指定路径的jar
//            JarFile
    }

    //重写loadClass方法
    //改写loadClass方式
    public Class<?> loadClass(String name, Map<String, Class> cacheClassMap, String jarPath) throws ClassNotFoundException {
        if(findLoadedClass(name)==null || !cacheClassMap.containsKey(name) || cacheClassMap.get(name)==null){
            return super.loadClass(name);
        }else{
            return cacheClassMap.get(name);
        }

    }

}
