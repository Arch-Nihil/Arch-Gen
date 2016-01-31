package com.arch.archgen.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static boolean doGenBricks;
	
	public static void init(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		config.load();
		
		doGenBricks = (config.get(Configuration.CATEGORY_GENERAL, "GenerateBricks", true)).getBoolean();
		
		config.save();
	}
	
}
