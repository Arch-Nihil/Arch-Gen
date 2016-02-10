package com.arch.archgen.config;

import java.io.File;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {
	
	public static boolean doGen, doGenBricks, doGenMachines, doGenExtra, doRemoveBedrock, doStripper;
	public static int magicNumberOne, magicNumberTwo;
	
	public static void init(FMLPreInitializationEvent e) {
		File cLocation = new File(e.getModConfigurationDirectory().toString()+"/Arch/Arch-Gen.cfg");
		
		Configuration bConfig = new Configuration(cLocation);
		
		bConfig.load();
		
		Property worldGen = bConfig.get(Configuration.CATEGORY_GENERAL, "WorldGen", true);
		worldGen.comment = "Should the mod generate anything at all ? Blocks will still get added.";
		doGen = worldGen.getBoolean();
		
		Property genBricks = bConfig.get(Configuration.CATEGORY_GENERAL, "GenerateBricks", true);
		genBricks.comment = "Should the mod generate bricks based on the many stones and minerals available ?";
		doGenBricks = genBricks.getBoolean();
		
		Property genMachines = bConfig.get(Configuration.CATEGORY_GENERAL, "GenerateMachines", true);
		genMachines.comment = "Should the mod generate its machines to process the many stones and minerals available ?";
		doGenMachines = genMachines.getBoolean();
		
		Property genExtra = bConfig.get(Configuration.CATEGORY_GENERAL, "GenExtraChecks", false);
		genExtra.comment = "Should the mod attempt extra checks to better detect its environment ?";
		doGenExtra = genExtra.getBoolean();
		
		Property removeBedrock = bConfig.get(Configuration.CATEGORY_GENERAL, "RemoveBedrock", true);
		removeBedrock.comment = "Should the mod remove all unecessary bedrock ?";
		doRemoveBedrock = removeBedrock.getBoolean();
		
		Property stripper = bConfig.get(Configuration.CATEGORY_GENERAL, "StripperMode", true);
		stripper.comment = "Should the mod register its world stripping functions ?";
		doStripper = stripper.getBoolean();
		
		Property numberOne = bConfig.get(Configuration.CATEGORY_GENERAL, "MagicNumberOne", "6");
		numberOne.comment = "Number affecting world-gen. Up to make detection of top block more accurate at the cost of world-gen speed.";
		magicNumberOne = numberOne.getInt();
		Property numberTwo = bConfig.get(Configuration.CATEGORY_GENERAL, "MagicNumberTwo", "9");
		magicNumberTwo = numberTwo.getInt();

		bConfig.save();
	}
}
