package com.arch.archgen.blocks;

import com.arch.archgen.blocks.bricks.IgneousBricks;
import com.arch.archgen.blocks.bricks.MineralBricks;
import com.arch.archgen.blocks.stone.IgneousStone;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlocksRegistry {

	public static Block granite;
	
	public static Block graniteBricks;
	public static Block quartzBricks, alkaliFeldsparBricks;
	
	public static void init() {
		GameRegistry.registerBlock(granite = new IgneousStone("Granite", "Quartz,AlkaliFeldspar", "9,5", 10.0F, 10.0F, 2), BasicItemBlocks.class, "Granite");
		
		GameRegistry.registerBlock(graniteBricks = new IgneousBricks("Granite", 10.0F, 10.0F, 2), BasicItemBlocks.class, "GraniteBricks");
		GameRegistry.registerBlock(quartzBricks = new MineralBricks("Quartz", 10.0F, 10.0F, 2), BasicItemBlocks.class, "QuartzBricks");
		GameRegistry.registerBlock(alkaliFeldsparBricks = new MineralBricks("AlkaliFeldspar", 10.0F, 10.0F, 2), BasicItemBlocks.class, "AlkaliFeldsparBricks");
	}

}