package com.arch.archgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.arch.archgen.lib.Strings;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Strings.MODID, name = Strings.MODNAME, version = Strings.VERSION)
public class ArchGen {
        
    @Instance
    public static ArchGen instance;
	
	public static ArchGen getInstance() {
		return instance;
	}
       
    @SidedProxy(clientSide="com.arch.archgen.ClientProxy", serverSide="com.arch.archgen.ServerProxy")
    public static CommonProxy proxy;
     
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	proxy.preInit(e);
    	getCOGFile();
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
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
