package com.arch.archgen;

import com.arch.archgen.lib.Strings;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

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
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
    }
 
    @EventHandler
	public void serverLoad(FMLServerStartingEvent e)
	{
	    proxy.serverLoad(e);
	}
}
