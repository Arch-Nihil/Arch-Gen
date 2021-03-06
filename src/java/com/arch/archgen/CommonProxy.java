package com.arch.archgen;

import com.arch.archgen.blocks.BlocksRegistry;
import com.arch.archgen.commands.Commands;
import com.arch.archgen.config.Config;
import com.arch.archgen.items.ItemsRegistry;
import com.arch.archgen.recipes.Crafting;
import com.arch.archgen.recipes.Oredicting;
import com.arch.archgen.recipes.Smelting;
import com.arch.archgen.tabs.Tabs;
import com.arch.archgen.world.Worldgen;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    	Config.init(e);
    	Tabs.preInit();
    	ItemsRegistry.init();
    	BlocksRegistry.init();
    	Tabs.init();
    	if (Config.doGen)
    		Worldgen.init();
    }

    public void init(FMLInitializationEvent e) {
    	Oredicting.init();
    	Crafting.init();
    	Smelting.init();
    }

    public void postInit(FMLPostInitializationEvent e) {
    	
	}

	public void serverLoad(FMLServerStartingEvent e) {
		Commands.init(e);
	}
}