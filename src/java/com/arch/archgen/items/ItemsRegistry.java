package com.arch.archgen.items;

import com.arch.archgen.config.Config;
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
	public static Item quartzDust, alkaliFeldsparDust, plagioclaseDust, hornblendeDust;
	
	public static Item graniteChunk;
	public static Item quartzChunk, alkaliFeldsparChunk, plagioclaseChunk, hornblendeChunk;
	
	public static Item graniteBrick;
	public static Item quartzBrick, alkaliFeldsparBrick, plagioclaseBrick, hornblendeBrick;
	
	public static void init() {
		GameRegistry.registerItem(graniteDust = new IgneousDust("Granite"), "GraniteDust");
		GameRegistry.registerItem(quartzDust = new MineralDust("Quartz"), "QuartzDust");
		GameRegistry.registerItem(alkaliFeldsparDust = new MineralDust("AlkaliFeldspar"), "AlkaliFeldsparDust");
		GameRegistry.registerItem(plagioclaseDust = new MineralDust("Plagioclase"), "PlagioclaseDust");
		GameRegistry.registerItem(hornblendeDust = new MineralDust("Hornblende"), "HornblendeDust");
		
		GameRegistry.registerItem(graniteChunk = new IgneousChunk("Granite"), "GraniteChunk");
		GameRegistry.registerItem(quartzChunk = new MineralChunk("Quartz"), "QuartzChunk");
		GameRegistry.registerItem(alkaliFeldsparChunk = new MineralChunk("AlkaliFeldspar"), "AlkaliFeldsparChunk");
		GameRegistry.registerItem(plagioclaseChunk = new MineralChunk("Plagioclase"), "PlagioclaseChunk");
		GameRegistry.registerItem(hornblendeChunk = new MineralChunk("Hornblende"), "HornblendeChunk");
		
		if (Config.doGenBricks) {
			GameRegistry.registerItem(graniteBrick = new IgneousBrick("Granite"), "GraniteBrick");
			GameRegistry.registerItem(quartzBrick = new MineralBrick("Quartz"), "QuartzBrick");
			GameRegistry.registerItem(alkaliFeldsparBrick = new MineralBrick("AlkaliFeldspar"), "AlkaliFeldsparBrick");
			GameRegistry.registerItem(plagioclaseBrick = new MineralBrick("Plagioclase"), "PlagioclaseBrick");
			GameRegistry.registerItem(hornblendeBrick = new MineralBrick("Hornblende"), "HornblendeBrick");
		}
	}
}
