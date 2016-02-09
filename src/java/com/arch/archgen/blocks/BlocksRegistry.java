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
	
	public static Block granite, basalt, gabbro, diorite, peridotite, rhyolite;
	public static Block siltstone, limestone, sandstone, shale;
	
	public static Block graniteBricks, basaltBricks, gabbroBricks, dioriteBricks, peridotiteBricks, rhyoliteBricks;
	public static Block siltstoneBricks, limestoneBricks, sandstoneBricks, shaleBricks;
	public static Block quartzBricks, alkaliFeldsparBricks, plagioclaseBricks, hornblendeBricks, calciteBricks, olivineBricks, enstatiteBricks, diopsideBricks;
	
	public static void init() {
		GameRegistry.registerBlock(granite = new IgneousStone("Granite", "Quartz,AlkaliFeldspar,Plagioclase,Hornblende", "9,7,3,1", 13.0F, 10.0F, 2), BasicItemBlocks.class, "Granite");
		GameRegistry.registerBlock(basalt = new IgneousStone("Basalt", "Plagioclase", "5", 12.0F, 10.0F, 2), BasicItemBlocks.class, "Basalt");
		GameRegistry.registerBlock(gabbro = new IgneousStone("Gabbro", "Plagioclase", "7", 12.0F, 10.0F, 2), BasicItemBlocks.class, "Gabbro");
		GameRegistry.registerBlock(diorite = new IgneousStone("Diorite", "Plagioclase,Hornblende", "5,5", 13.0F, 10.0F, 2), BasicItemBlocks.class, "Diorite");
		GameRegistry.registerBlock(peridotite = new IgneousStone("Peridotite", "Olivine,Enstatite,Diopside", "11,5,3", 12.0F, 10.0F, 2), BasicItemBlocks.class, "Peridotite");
		GameRegistry.registerBlock(rhyolite = new IgneousStone("Rhyolite", "Quartz,AlkaliFeldspar,Plagioclase,Hornblende", "7,5,3,1", 12.0F, 10.0F, 2), BasicItemBlocks.class, "Rhyolite");
		
		GameRegistry.registerBlock(siltstone = new SedimentaryStone("Siltstone", "Quartz,Plagioclase,AlkaliFeldspar", "9,3,3", 8.0F, 8.0F, 1), BasicItemBlocks.class, "Siltstone");
		GameRegistry.registerBlock(limestone = new SedimentaryStone("Limestone", "Calcite,Quartz,Plagioclase", "11,5,3", 8.0F, 8.0F, 1), BasicItemBlocks.class, "Limestone");
		GameRegistry.registerBlock(sandstone = new SedimentaryStone("Sandstone", "Quartz,AlkaliFeldspar,Plagioclase", "11,7,5", 9.0F, 8.0F, 1), BasicItemBlocks.class, "Sandstone");
		GameRegistry.registerBlock(shale = new SedimentaryStone("Shale", "Quartz,Plagioclase,AlkaliFeldspar,Calcite", "15,5,5,3", 8.0F, 8.0F, 1), BasicItemBlocks.class, "Shale");
		
		if (Config.doGenBricks) {
			GameRegistry.registerBlock(graniteBricks = new IgneousBricks("Granite", 10.0F, 10.0F, 1), BasicItemBlocks.class, "GraniteBricks");
			GameRegistry.registerBlock(basaltBricks = new IgneousBricks("Basalt", 10.0F, 10.0F, 1), BasicItemBlocks.class, "BasaltBricks");
			GameRegistry.registerBlock(gabbroBricks = new IgneousBricks("Gabbro", 10.0F, 10.0F, 1), BasicItemBlocks.class, "GabbroBricks");
			GameRegistry.registerBlock(dioriteBricks = new IgneousBricks("Diorite", 10.0F, 10.0F, 1), BasicItemBlocks.class, "DioriteBricks");
			GameRegistry.registerBlock(peridotiteBricks = new IgneousBricks("Peridotite", 10.0F, 10.0F, 1), BasicItemBlocks.class, "PeridotiteBricks");
			GameRegistry.registerBlock(rhyoliteBricks = new IgneousBricks("Rhyolite", 10.0F, 10.0F, 1), BasicItemBlocks.class, "RhyoliteBricks");
			
			GameRegistry.registerBlock(siltstoneBricks = new SedimentaryBricks("Siltstone", 10.0F, 10.0F, 0), BasicItemBlocks.class, "SiltstoneBricks");
			GameRegistry.registerBlock(limestoneBricks = new SedimentaryBricks("Limestone", 10.0F, 10.0F, 0), BasicItemBlocks.class, "LimestoneBricks");
			GameRegistry.registerBlock(sandstoneBricks = new SedimentaryBricks("Sandstone", 10.0F, 10.0F, 0), BasicItemBlocks.class, "SandstoneBricks");
			GameRegistry.registerBlock(shaleBricks = new SedimentaryBricks("Shale", 10.0F, 10.0F, 0), BasicItemBlocks.class, "ShaleBricks");
			
			GameRegistry.registerBlock(quartzBricks = new MineralBricks("Quartz", 10.0F, 10.0F, 2), BasicItemBlocks.class, "QuartzBricks");
			GameRegistry.registerBlock(alkaliFeldsparBricks = new MineralBricks("AlkaliFeldspar", 10.0F, 10.0F, 2), BasicItemBlocks.class, "AlkaliFeldsparBricks");
			GameRegistry.registerBlock(plagioclaseBricks = new MineralBricks("Plagioclase", 10.0F, 10.0F, 2), BasicItemBlocks.class, "PlagioclaseBricks");
			GameRegistry.registerBlock(hornblendeBricks = new MineralBricks("Hornblende", 10.0F, 10.0F, 2), BasicItemBlocks.class, "HornblendeBricks");
			GameRegistry.registerBlock(calciteBricks = new MineralBricks("Calcite", 10.0F, 10.0F, 2), BasicItemBlocks.class, "CalciteBricks");
			GameRegistry.registerBlock(olivineBricks = new MineralBricks("Olivine", 10.0F, 10.0F, 2), BasicItemBlocks.class, "OlivineBricks");
			GameRegistry.registerBlock(enstatiteBricks = new MineralBricks("Enstatite", 10.0F, 10.0F, 2), BasicItemBlocks.class, "EnstatiteBricks");
			GameRegistry.registerBlock(diopsideBricks = new MineralBricks("Diopside", 10.0F, 10.0F, 2), BasicItemBlocks.class, "DiopsideBricks");
		}
	}
}