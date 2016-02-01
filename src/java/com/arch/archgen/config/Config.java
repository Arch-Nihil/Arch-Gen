package com.arch.archgen.config;

import java.io.File;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {
	
	public static boolean doGenBricks, doGenMachines;
	
	public static void init(FMLPreInitializationEvent e) {
		File cLocation = new File(e.getModConfigurationDirectory().toString()+"/Arch/Arch-Gen.cfg");
		
		Configuration bConfig = new Configuration(cLocation);
		
		bConfig.load();
		
		Property genBricks = bConfig.get(Configuration.CATEGORY_GENERAL, "GenerateBricks", true);
		genBricks.comment = "Should the mod generate bricks based on the many stones and minerals available ?";
		doGenBricks = genBricks.getBoolean();
		
		Property genMachines = bConfig.get(Configuration.CATEGORY_GENERAL, "GenerateMachines", true);
		genBricks.comment = "Should the mod generate its machines to process the many stones and minerals available ?";
		doGenMachines = genMachines.getBoolean();
		
		bConfig.save();
	}
}
