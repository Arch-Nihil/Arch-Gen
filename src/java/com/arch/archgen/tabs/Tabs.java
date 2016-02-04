package com.arch.archgen.tabs;

import com.arch.archgen.blocks.BlocksRegistry;
import com.arch.archgen.config.Config;
import com.arch.archgen.items.ItemsRegistry;

import net.minecraft.item.Item;

public class Tabs {
	public static BasicTab tabStone, tabDust, tabChunk, tabBricks, tabBrick;
	
	public static void preInit() {
		tabStone = new BasicTab("StoneTab");
		tabDust = new BasicTab("DustTab");
		tabChunk = new BasicTab("ChunkTab");
		
		if (Config.doGenBricks) {
			tabBricks = new BasicTab("BricksTab");
			tabBrick = new BasicTab("BrickTab");
		}
	}
	
	public static void init() {
		tabStone.setTabIcon(Item.getItemFromBlock(BlocksRegistry.granite));
		tabDust.setTabIcon(ItemsRegistry.graniteDust);
		tabChunk.setTabIcon(ItemsRegistry.graniteChunk);
		
		if (Config.doGenBricks) {
			tabBricks.setTabIcon(Item.getItemFromBlock(BlocksRegistry.graniteBricks));
			tabBrick.setTabIcon(ItemsRegistry.graniteBrick);
		}
	}
}