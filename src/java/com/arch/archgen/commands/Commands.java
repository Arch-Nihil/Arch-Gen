package com.arch.archgen.commands;

import com.arch.archgen.config.Config;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class Commands {
	public static void init(FMLServerStartingEvent e) {
		if (Config.doStripper) {
			Stripper.init();
			e.registerServerCommand(new Stripper());
			e.registerServerCommand(new StripperSingle());
		}
	}
}
