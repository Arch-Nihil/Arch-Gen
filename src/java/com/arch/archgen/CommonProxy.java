package com.arch.archgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.arch.archgen.blocks.BlocksRegistry;
import com.arch.archgen.config.Config;
import com.arch.archgen.items.ItemsRegistry;
import com.arch.archgen.recipes.Crafting;
import com.arch.archgen.recipes.Smelting;
import com.arch.archgen.tabs.Tabs;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    	Config.init(e);
    	Tabs.preInit();
    	ItemsRegistry.init();
    	BlocksRegistry.init();
    	Tabs.postInit();
    }

    public void init(FMLInitializationEvent e) {
    	Crafting.init();
    	Smelting.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

	}
    
    public void getCOGFile() {
		String file = "ArchGen.xml";
		String cPath = Loader.instance().getConfigDir() + File.separator + "CustomOreGen" + File.separator + "modules" + File.separator + "custom" + File.separator + file;
		File cFile = new File(cPath);
		cFile.getParentFile().mkdirs();
		String source = "/assets/archgen/config/" + file;
		
		try {
			OutputStream out = new FileOutputStream(cFile);
			InputStream in = this.getClass().getResourceAsStream(source);
			ByteStreams.copy(in, out);
			out.flush(); out.close(); in.close();
		}
		catch (FileNotFoundException err) {
			throw new RuntimeException("Could not find '" + cPath + "': " + err);
		}
		catch (IOException err) {
			throw new RuntimeException("Failed to export '" + source + "': " + err);
		}
	}
}