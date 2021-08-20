package driver.driver;


import driver.driver.common.ModuleClassLoader;

import java.util.List;

/**
 * @author : zhicheng chen
 * @date : 2021/8/17
 * @time : 17:12
 */
public class DriverReppository {
    public byte[] driverCompile(String jarPath, List<ScadaTag> tags) {
        ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
        try {
            Object obj = moduleClassLoader.getJarFile(jarPath);
            if (obj instanceof IScadaDriver){
                byte[] byteArray =  ((IScadaDriver) obj).encode(tags);
                return byteArray;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Object> driverAntiCompile(String jarPath, byte[] data) {
        ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
        try {
            Object obj = moduleClassLoader.getJarFile(jarPath);
            if (obj instanceof IScadaDriver){
                List<Object> objects =  ((IScadaDriver) obj).decode(data);
                return objects;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
