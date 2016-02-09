package com.arch.archgen.recipes;

import com.arch.archgen.blocks.BasicBlocks;
import com.arch.archgen.config.Config;
import com.arch.archgen.items.BasicItems;
import com.arch.archgen.lib.G;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Oredicting {
	private static BasicItems cItem;
	private static BasicBlocks cBlock;
	private static ItemStack cIStack;
	
	public static void init() {
		for (int i = 0; i < G.chunkArray.size(); i ++) {
			cItem = G.chunkArray.get(i);
			for (int n = 0; n < G.sizeArray.length; n ++) {
				cIStack = new ItemStack(G.chunkArray.get(i), 1, n);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + cItem.getName().replace("Chunk", ""), cIStack);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + cItem.getSubtype() + cItem.getName().replace("Chunk", ""), cIStack);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cItem.getName().replace("Chunk", ""), cIStack);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cItem.getSubtype() + cItem.getName().replace("Chunk", ""), cIStack);
			}
		}
		
		for (int i = 0; i < G.dustArray.size(); i ++) {
			cItem = G.dustArray.get(i);
			for (int n = 0; n < G.sizeArray.length; n ++) {
				cIStack = new ItemStack(G.dustArray.get(i), 1, n);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + cItem.getName().replace("Dust", ""), cIStack);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + cItem.getSubtype() + cItem.getName().replace("Dust", ""), cIStack);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cItem.getName().replace("Dust", ""), cIStack);
				OreDictionary.registerOre(cItem.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cItem.getSubtype() + cItem.getName().replace("Dust", ""), cIStack);
			}
		}
		
		for (int i = 0; i < G.stoneArray.size(); i ++) {
			cBlock = G.stoneArray.get(i);
			for (int n = 0; n < G.mAmountForType(cBlock.getType()); n ++) {
				cIStack = new ItemStack(G.stoneArray.get(i), 1, n);
				OreDictionary.registerOre(cBlock.getType().toLowerCase() + cBlock.getName().replace("Stone", ""), cIStack);
				OreDictionary.registerOre(cBlock.getType().toLowerCase() + cBlock.getSubtype() + cBlock.getName().replace("Stone", ""), cIStack);
				OreDictionary.registerOre("stone", cIStack);
			}
		}
		
		for (int i = 0; i < G.soilArray.size(); i ++) {
			cBlock = G.soilArray.get(i);
			for (int n = 0; n < G.mAmountForType(cBlock.getType()); n ++) {
				cIStack = new ItemStack(G.soilArray.get(i), 1, n);
				OreDictionary.registerOre(cBlock.getType().toLowerCase() + cBlock.getName().replace("Soil", ""), cIStack);
				OreDictionary.registerOre(cBlock.getType().toLowerCase() + cBlock.getSubtype() + cBlock.getName().replace("Soil", ""), cIStack);
				OreDictionary.registerOre("sand", cIStack);
			}
		}
		
		if (Config.doGenBricks) {
			for (int i = 0; i < G.brickArray.size(); i ++) {
				cItem = G.brickArray.get(i);
				for (int n = 1; n < G.sizeArray.length - 1; n ++) {
					cIStack = new ItemStack(G.brickArray.get(i), 1, n - 1);
					OreDictionary.registerOre(cItem.getType().toLowerCase() + cItem.getName().replace("Brick", ""), cIStack);
					OreDictionary.registerOre(cItem.getType().toLowerCase() + cItem.getSubtype() + cItem.getName().replace("Brick", ""), cIStack);
					OreDictionary.registerOre(cItem.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cItem.getName().replace("Brick", ""), cIStack);
					OreDictionary.registerOre(cItem.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cItem.getSubtype() + cItem.getName().replace("Brick", ""), cIStack);
					if (n == 2)
						OreDictionary.registerOre("ingotBrick", cIStack);
				}
			}
			
			for (int i = 0; i < G.bricksArray.size(); i ++) {
				cBlock = G.bricksArray.get(i);
				for (int n = 1; n < G.sizeArray.length - 1; n ++) {
					cIStack = new ItemStack(G.bricksArray.get(i), 1, n - 1);
					OreDictionary.registerOre(cBlock.getType().toLowerCase() + cBlock.getName().replace("Bricks", ""), cIStack);
					OreDictionary.registerOre(cBlock.getType().toLowerCase() + cBlock.getSubtype() + cBlock.getName().replace("Bricks", ""), cIStack);
					OreDictionary.registerOre(cBlock.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cBlock.getName().replace("Bricks", ""), cIStack);
					OreDictionary.registerOre(cBlock.getType().toLowerCase() + G.sizeArray[n].replace("Medium", "") + cBlock.getSubtype() + cBlock.getName().replace("Bricks", ""), cIStack);
				}
			}
		}
	}
}