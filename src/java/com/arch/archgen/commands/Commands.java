package com.arch.archgen.commands;

import com.arch.archgen.config.Config;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class Commands {
	public static void init(FMLServerStartingEvent e) {
		if (Config.doStripper) {
			StripperCommand.init();
			e.registerServerCommand(new StripperCommand());
		}
	}
}
