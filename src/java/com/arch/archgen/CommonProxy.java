package com.arch.archgen;

import com.arch.archgen.blocks.BlocksRegistry;
import com.arch.archgen.config.Config;
import com.arch.archgen.items.ItemsRegistry;
import com.arch.archgen.recipes.Crafting;
import com.arch.archgen.recipes.Smelting;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
    public void preInit(FMLPreInitializationEvent e) {
    	Config.init(e);
    	ItemsRegistry.init();
    	BlocksRegistry.init();
    }

    public void init(FMLInitializationEvent e) {
    	Crafting.init();
    	Smelting.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}