package com.arch.archgen.recipes;

import net.minecraft.item.ItemStack;

import com.arch.archgen.lib.G;

import cpw.mods.fml.common.registry.GameRegistry;

public class Smelting {
	public static void init() {
		for (int i = 0; i < G.brickArray.size(); i ++) {
			for (int j = 0; j < G.sizeArray.length - 2; j ++) {
				GameRegistry.addSmelting(new ItemStack(G.chunkArray.get(i), 1, j + 1), new ItemStack(G.brickArray.get(i), 1, j), 0.1F * j + 0.2F);
			}
		}
		for (int i = 0; i < G.chunkArray.size(); i ++) {
			for (int j = 0; j < G.sizeArray.length; j ++) {
				GameRegistry.addSmelting(new ItemStack(G.dustArray.get(i), 1, j), new ItemStack(G.chunkArray.get(i), 1, j), 0.1F * j);
			}
		}
	}
}