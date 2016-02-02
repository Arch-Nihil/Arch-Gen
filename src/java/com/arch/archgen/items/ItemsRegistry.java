package com.arch.archgen.items;

import com.arch.archgen.config.Config;
import com.arch.archgen.items.brick.IgneousBrick;
import com.arch.archgen.items.brick.MineralBrick;
import com.arch.archgen.items.brick.SedimentaryBrick;
import com.arch.archgen.items.chunk.IgneousChunk;
import com.arch.archgen.items.chunk.MineralChunk;
import com.arch.archgen.items.chunk.SedimentaryChunk;
import com.arch.archgen.items.dust.IgneousDust;
import com.arch.archgen.items.dust.MineralDust;
import com.arch.archgen.items.dust.SedimentaryDust;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsRegistry {

	public static Item graniteDust, basaltDust, gabbroDust;
	public static Item mudstoneDust;
	public static Item quartzDust, alkaliFeldsparDust, plagioclaseDust, hornblendeDust;
	
	public static Item graniteChunk, basaltChunk, gabbroChunk;
	public static Item mudstoneChunk;
	public static Item quartzChunk, alkaliFeldsparChunk, plagioclaseChunk, hornblendeChunk;
	
	public static Item graniteBrick, basaltBrick, gabbroBrick;
	public static Item mudstoneBrick;
	public static Item quartzBrick, alkaliFeldsparBrick, plagioclaseBrick, hornblendeBrick;
	
	public static void init() {
		GameRegistry.registerItem(graniteDust = new IgneousDust("Granite"), "GraniteDust");
		GameRegistry.registerItem(basaltDust = new IgneousDust("Basalt"), "BasaltDust");
		GameRegistry.registerItem(gabbroDust = new IgneousDust("Gabbro"), "GabbroDust");
		
		GameRegistry.registerItem(mudstoneDust = new SedimentaryDust("Mudstone"), "MudstoneDust");
		
		GameRegistry.registerItem(quartzDust = new MineralDust("Quartz"), "QuartzDust");
		GameRegistry.registerItem(alkaliFeldsparDust = new MineralDust("AlkaliFeldspar"), "AlkaliFeldsparDust");
		GameRegistry.registerItem(plagioclaseDust = new MineralDust("Plagioclase"), "PlagioclaseDust");
		GameRegistry.registerItem(hornblendeDust = new MineralDust("Hornblende"), "HornblendeDust");
		
		GameRegistry.registerItem(graniteChunk = new IgneousChunk("Granite"), "GraniteChunk");
		GameRegistry.registerItem(basaltChunk = new IgneousChunk("Basalt"), "BasaltChunk");
		GameRegistry.registerItem(gabbroChunk = new IgneousChunk("Gabbro"), "GabbroChunk");
		
		GameRegistry.registerItem(mudstoneChunk = new SedimentaryChunk("Mudstone"), "MudstoneChunk");
		
		GameRegistry.registerItem(quartzChunk = new MineralChunk("Quartz"), "QuartzChunk");
		GameRegistry.registerItem(alkaliFeldsparChunk = new MineralChunk("AlkaliFeldspar"), "AlkaliFeldsparChunk");
		GameRegistry.registerItem(plagioclaseChunk = new MineralChunk("Plagioclase"), "PlagioclaseChunk");
		GameRegistry.registerItem(hornblendeChunk = new MineralChunk("Hornblende"), "HornblendeChunk");
		
		if (Config.doGenBricks) {
			GameRegistry.registerItem(graniteBrick = new IgneousBrick("Granite"), "GraniteBrick");
			GameRegistry.registerItem(basaltBrick = new IgneousBrick("Basalt"), "BasaltBrick");
			GameRegistry.registerItem(gabbroBrick = new IgneousBrick("Gabbro"), "GabbroBrick");
			
			GameRegistry.registerItem(mudstoneBrick = new SedimentaryBrick("Mudstone"), "MudstoneBrick");
			
			GameRegistry.registerItem(quartzBrick = new MineralBrick("Quartz"), "QuartzBrick");
			GameRegistry.registerItem(alkaliFeldsparBrick = new MineralBrick("AlkaliFeldspar"), "AlkaliFeldsparBrick");
			GameRegistry.registerItem(plagioclaseBrick = new MineralBrick("Plagioclase"), "PlagioclaseBrick");
			GameRegistry.registerItem(hornblendeBrick = new MineralBrick("Hornblende"), "HornblendeBrick");
		}
	}
}
