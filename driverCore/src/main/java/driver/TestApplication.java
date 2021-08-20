package driver;

import driver.driver.IScadaDriver;
import driver.driver.common.ModuleClassLoader;
import org.junit.Test;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
public class TestApplication {

	@Test
	public static void main(String[] args) {
		ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
		try {
			Object obj = moduleClassLoader.getJarFile("D:\\server\\maven\\repository\\com\\Amanocino\\modbusTcp\\1.0.1\\modbusTcp-1.0.1.jar");
			if (obj instanceof IScadaDriver){
				System.out.println("finish!!!");
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
