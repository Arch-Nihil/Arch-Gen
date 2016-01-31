package com.arch.archgen.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {
	
	public static boolean doGenBricks;
	
	public static void init(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		config.load();
		
		Property genBricks = config.get(Configuration.CATEGORY_GENERAL, "GenerateBricks", true);
		genBricks.comment = "Should the mod generate bricks based on the many stones and minerals available ?";
		doGenBricks = genBricks.getBoolean();
		
		config.save();
	}
	
}
