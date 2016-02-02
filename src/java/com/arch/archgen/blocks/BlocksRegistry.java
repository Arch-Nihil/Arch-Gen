package com.arch.archgen.blocks;

import com.arch.archgen.blocks.bricks.IgneousBricks;
import com.arch.archgen.blocks.bricks.MineralBricks;
import com.arch.archgen.blocks.bricks.SedimentaryBricks;
import com.arch.archgen.blocks.stone.IgneousStone;
import com.arch.archgen.blocks.stone.SedimentaryStone;
import com.arch.archgen.config.Config;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlocksRegistry {
	
	public static Block granite, basalt, gabbro;
	public static Block mudstone;
	
	public static Block graniteBricks, basaltBricks, gabbroBricks;
	public static Block mudstoneBricks;
	public static Block quartzBricks, alkaliFeldsparBricks, plagioclaseBricks, hornblendeBricks;
	
	public static void init() {
		GameRegistry.registerBlock(granite = new IgneousStone("Granite", "Quartz,AlkaliFeldspar,Plagioclase,Hornblende", "9,5,5,3", 10.0F, 10.0F, 2), BasicItemBlocks.class, "Granite");
		GameRegistry.registerBlock(basalt = new IgneousStone("Basalt", "Plagioclase", "5", 10.0F, 10.0F, 2), BasicItemBlocks.class, "Basalt");
		GameRegistry.registerBlock(gabbro = new IgneousStone("Gabbro", "Plagioclase", "7", 10.0F, 10.0F, 2), BasicItemBlocks.class, "Gabbro");
		
		GameRegistry.registerBlock(mudstone = new SedimentaryStone("Mudstone", "Quartz, Orthoclase, AlkaliFeldspar", "9,5,3", 10.0F, 10.0F, 1), BasicItemBlocks.class, "Mudstone");
		
		if (Config.doGenBricks) {
			GameRegistry.registerBlock(graniteBricks = new IgneousBricks("Granite", 10.0F, 10.0F, 2), BasicItemBlocks.class, "GraniteBricks");
			GameRegistry.registerBlock(basaltBricks = new IgneousBricks("Basalt", 10.0F, 10.0F, 2), BasicItemBlocks.class, "BasaltBricks");
			GameRegistry.registerBlock(gabbroBricks = new IgneousBricks("Gabbro", 10.0F, 10.0F, 2), BasicItemBlocks.class, "GabbroBricks");
			
			GameRegistry.registerBlock(mudstoneBricks = new SedimentaryBricks("Mudstone", 10.0F, 10.0F, 2), BasicItemBlocks.class, "MudstoneBricks");
			
			GameRegistry.registerBlock(quartzBricks = new MineralBricks("Quartz", 10.0F, 10.0F, 2), BasicItemBlocks.class, "QuartzBricks");
			GameRegistry.registerBlock(alkaliFeldsparBricks = new MineralBricks("AlkaliFeldspar", 10.0F, 10.0F, 2), BasicItemBlocks.class, "AlkaliFeldsparBricks");
			GameRegistry.registerBlock(plagioclaseBricks = new MineralBricks("Plagioclase", 10.0F, 10.0F, 2), BasicItemBlocks.class, "PlagioclaseBricks");
			GameRegistry.registerBlock(hornblendeBricks = new MineralBricks("Hornblende", 10.0F, 10.0F, 2), BasicItemBlocks.class, "HornblendeBricks");
		}
	}
}