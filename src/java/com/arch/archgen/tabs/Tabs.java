package com.arch.archgen.tabs;

import com.arch.archgen.blocks.BlocksRegistry;
import com.arch.archgen.items.ItemsRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Tabs {
	public static final CreativeTabs tabStone = new CreativeTabs("StoneTab") {
	    @Override public Item getTabIconItem() {
	        return Item.getItemFromBlock(BlocksRegistry.granite);
	    }
	};
	public static final CreativeTabs tabBricks = new CreativeTabs("BricksTab") {
	    @Override public Item getTabIconItem() {
	        return Item.getItemFromBlock(BlocksRegistry.graniteBricks);
	    }
	};
	public static final CreativeTabs tabDust = new CreativeTabs("DustTab") {
	    @Override public Item getTabIconItem() {
	        return ItemsRegistry.graniteDust;
	    }
	};
	public static final CreativeTabs tabChunk = new CreativeTabs("ChunkTab") {
	    @Override public Item getTabIconItem() {
	        return ItemsRegistry.graniteChunk;
	    }
	};
	public static final CreativeTabs tabBrick = new CreativeTabs("BrickTab") {
	    @Override public Item getTabIconItem() {
	        return ItemsRegistry.graniteBrick;
	    }
	};
}