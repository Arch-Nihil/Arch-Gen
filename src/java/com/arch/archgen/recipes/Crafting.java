package com.arch.archgen.recipes;

import com.arch.archgen.lib.G;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class Crafting {
	public static void init() {
		for (int i = 0; i < G.brickArray.size(); i ++) {
			for (int j = 0; j < G.sizeArray.length - 2; j ++) {
				GameRegistry.addRecipe(new ItemStack(G.bricksArray.get(i), 1, j), "##", "##", '#', new ItemStack(G.brickArray.get(i), 1, j));
			}
		}
		
		for (int i = 0; i < G.dustArray.size(); i ++) {
			for (int j = 1; j < G.sizeArray.length; j ++) {
				GameRegistry.addShapelessRecipe(new ItemStack(G.dustArray.get(i), 1, j), new Object[] {new ItemStack(G.dustArray.get(i), 1, j - 1), new ItemStack(G.dustArray.get(i), 1, j - 1), new ItemStack(G.dustArray.get(i), 1, j - 1)});
				GameRegistry.addRecipe(new ItemStack(G.dustArray.get(i), 3, j - 1), "#  ", "   ", "   ", '#', new ItemStack(G.dustArray.get(i), 1, j));
			}
			for (int j = 2; j < G.sizeArray.length; j ++) {
				GameRegistry.addShapelessRecipe(new ItemStack(G.dustArray.get(i), 1, j), new Object[] {new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2), new ItemStack(G.dustArray.get(i), 1, j - 2)});
				GameRegistry.addRecipe(new ItemStack(G.dustArray.get(i), 9, j - 2), " # ", "   ", "   ", '#', new ItemStack(G.dustArray.get(i), 1, j));
			}
			for (int j = 3; j < G.sizeArray.length; j ++) {
				GameRegistry.addRecipe(new ItemStack(G.dustArray.get(i), 27, j - 3), "   ", "#   ", "   ", '#', new ItemStack(G.dustArray.get(i), 1, j));
			}
		}
	}
}