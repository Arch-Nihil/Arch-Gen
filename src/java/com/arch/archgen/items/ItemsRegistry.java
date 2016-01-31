package com.arch.archgen.items;

import com.arch.archgen.items.brick.IgneousBrick;
import com.arch.archgen.items.brick.MineralBrick;
import com.arch.archgen.items.chunk.IgneousChunk;
import com.arch.archgen.items.chunk.MineralChunk;
import com.arch.archgen.items.dust.IgneousDust;
import com.arch.archgen.items.dust.MineralDust;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsRegistry {

	public static Item graniteDust;
	public static Item quartzDust, alkaliFeldsparDust;
	
	public static Item graniteChunk;
	public static Item quartzChunk, alkaliFeldsparChunk;
	
	public static Item graniteBrick;
	public static Item quartzBrick, alkaliFeldsparBrick;
	
	public static void init() {
		GameRegistry.registerItem(graniteDust = new IgneousDust("Granite"), "GraniteDust");
		GameRegistry.registerItem(quartzDust = new MineralDust("Quartz"), "QuartzDust");
		GameRegistry.registerItem(alkaliFeldsparDust = new MineralDust("AlkaliFeldspar"), "AlkaliFeldsparDust");
		
		GameRegistry.registerItem(graniteChunk = new IgneousChunk("Granite"), "GraniteChunk");
		GameRegistry.registerItem(quartzChunk = new MineralChunk("Quartz"), "QuartzChunk");
		GameRegistry.registerItem(alkaliFeldsparChunk = new MineralChunk("AlkaliFeldspar"), "AlkaliFeldsparChunk");
		
		GameRegistry.registerItem(graniteBrick = new IgneousBrick("Granite"), "GraniteBrick");
		GameRegistry.registerItem(quartzBrick = new MineralBrick("Quartz"), "QuartzBrick");
		GameRegistry.registerItem(alkaliFeldsparBrick = new MineralBrick("AlkaliFeldspar"), "AlkaliFeldsparBrick");
	}
}