package com.Amanocino.driver;

import com.Amanocino.driver.driver.IScadaDriver;
import com.Amanocino.driver.driver.common.ModuleClassLoader;
import org.junit.Test;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
public class TestApplication {

	@Test
	public static void main(String[] args) {
		ModuleClassLoader moduleClassLoader = new ModuleClassLoader();
		try {
			Object obj = moduleClassLoader.getJarFiles("D:\\server\\maven\\repository\\com\\Amanocino\\protocolManager\\1.0-SNAPSHOT\\protocolManager-1.0-SNAPSHOT.jar");
			if (obj instanceof IScadaDriver){
				System.out.println("finish!!!");
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
