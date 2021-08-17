package com.Amanocino.driver.driver.common;

import com.Amanocino.driver.driver.IScadaDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

/**
 * @author : zhicheng chen
 * @date : 2021/8/17
 * @time : 10:54
 */
public class ModuleClassLoader  {
    private JarFile jarFile;

    private Map<String, Object> cacheObjectMap = new HashMap<>();

    private Map<String, Class> cacheClassMap = new HashMap<>();

    private Map<String , byte[]> classBytesMap = new HashMap<>();

    private List<String> registeredBean = new ArrayList<>();

    public Object getJarFiles(String jarPath) throws MalformedURLException, FileNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
}
