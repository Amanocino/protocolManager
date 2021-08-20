package driver.driver.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarFile;

/**
 * @author : zhicheng chen
 * @date : 2021/8/17
 * @time : 10:54
 * I had leave a problem to optimize the program to sington
 */
public class ModuleClassLoader  {
    private static volatile ModuleClassLoader INSTANCE;

    private JarFile jarFile;

    private Map<String, Object> cacheObjectMap = new HashMap<>();

    private Map<String, Class> cacheClassMap = new HashMap<>();

    private Map<String , byte[]> classBytesMap = new HashMap<>();

    private List<String> registeredBean = new ArrayList<>();

    private ModuleClassLoader(){

    }

    public static ModuleClassLoader getInstance(){
        if (null == INSTANCE){
            synchronized (ModuleClassLoader.class){
                if (null == INSTANCE){
                    INSTANCE = new ModuleClassLoader();
                }
            }
        }
        return INSTANCE;
    }

    public Object getJarFile(String jarPath) throws MalformedURLException, FileNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (cacheObjectMap.containsKey(jarPath) && null!=cacheObjectMap.get(jarPath)){
            return cacheObjectMap.get(jarPath);
        }
        LoadJarUtils loadJarUtils = new LoadJarUtils(new URL[]{new File(jarPath).toURL()});
        loadJarUtils.loadJar(jarPath, classBytesMap, cacheClassMap, cacheObjectMap);
        if (cacheObjectMap.containsKey(jarPath) && null!=cacheObjectMap.get(jarPath)){
            return cacheObjectMap.get(jarPath);
        }
        return null;
    }

    public Object getJarFiles(List<String> jarPaths) throws MalformedURLException, FileNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> jarPathsTmp = new ArrayList<>();
        jarPathsTmp.addAll(jarPathsTmp);
        Set<String> keys = cacheObjectMap.keySet();
        jarPathsTmp.removeAll(keys);

        if (null==jarPathsTmp || 0>=jarPathsTmp.size()){
            return null;
        }

        URL[] urls = new URL[jarPathsTmp.size()];
        for (int i=0;i<jarPathsTmp.size();i++){
            String jarPathTmp = jarPathsTmp.get(i);
            urls[i] = new File(jarPathTmp).toURI().toURL();
        }

        LoadJarUtils loadJarUtils = new LoadJarUtils(urls);
        loadJarUtils.loadJars(jarPathsTmp, classBytesMap, cacheClassMap, cacheObjectMap);

        return null;
    }
}
