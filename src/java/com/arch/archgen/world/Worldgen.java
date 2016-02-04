package com.arch.archgen.world;

import java.util.Random;

import com.arch.archgen.blocks.BlocksRegistry;
import com.arch.archgen.lib.G;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class Worldgen implements IWorldGenerator {
	int[] topBlock;
	int[] topSolidBlock;
	
	public static void init() {
		GameRegistry.registerWorldGenerator(new Worldgen(), 0);
	}
	
	@Override
	public void generate(Random r, int chunkX, int chunkZ, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (w.provider.dimensionId) {
		case 0:
			BiomeGenBase b = w.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);
			
			topBlock = new int[256];
			for (int i = 0; i < 16; i ++) {
				for (int k = 0; k < 16; k ++) {
					topBlock[i * 16 + k] = w.getTopSolidOrLiquidBlock(chunkX * 16 + i, chunkZ * 16 + k);
					if (w.getBlock(chunkX * 16 + i, topBlock[i * 16 + k], chunkZ * 16 + k).equals(Blocks.water)) {
						boolean isBSolid = false;
						for (int j = topBlock[i * 16 + k] - 1; isBSolid == false; j--) {
							if (!(w.getBlock(chunkX * 16 + i, j, chunkZ * 16 + k).equals(Blocks.water))) {
								topBlock[i * 16 + k] = j;
								isBSolid = true;
							}
						}
					}	
				}
			}
			
			for (int i = 0; i < G.oceanicBArray.length; i++) {
				if (b.biomeName.equals(G.oceanicBArray[i])) {
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 1, 12, 0, 0, 100);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 12, 13, 0, 0, 75);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 13, 14, 0, 0, 50);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 14, 15, 0, 0, 25);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 23, 0, -5, 0, 100);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 22, 0, -6, -5, 75);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 21, 0, -7, -6, 50);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 20, 0, -8, -7, 25);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.basalt), w, r, chunkX, chunkZ, "absolute", 12, 64, 0, 0, 100);
				}
			}
			
			int mustGen = 1;
			for (int i = 0; i < G.oceanicBArray.length; i++) {
				if (!b.biomeName.equals(G.oceanicBArray[i]))
					mustGen ++;
				if (mustGen == G.oceanicBArray.length)
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 1, 18, 0, 0, 100);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 18, 19, 0, 0, 75);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 19, 20, 0, 0, 50);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 20, 21, 0, 0, 25);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 53, 0, -9, 0, 100);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 52, 0, -10, -9, 75);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 51, 0, -11, -10, 50);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.mudstone), w, r, chunkX, chunkZ, "relative", 50, 0, -12, -11, 25);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 12, 61, -244, -68, 100);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 12, 62, -68, -67, 75);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 12, 63, -67, -66, 50);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 12, 64, -66, -65, 25);
					this.runGenerator(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "absolute", 18, 252, 0, 0, 100);
			}
			break;
		case -1:
			break;
		case 1:
			break;
		}
	}

	private void runGenerator(WorldGenerator generator, World w, Random rand, int chunkX, int chunkZ, String relativity, int minY, int maxY, int minRelativeY, int maxRelativeY, int chance) {
		for (int i = 0; i < 16; i ++) {
		   	int x = chunkX * 16 + i;
		    for (int k = 0; k < 16; k ++) {
		    	int z = chunkZ * 16 + k;
		    	if (relativity == "relative") {
			    	int topBlockY = topBlock[i * 16 + k];
			    	int min, max;
			    	if (maxY != 0)
			    		max = Math.min(maxY, topBlockY + maxRelativeY);
			    	else
			    		max = topBlockY + maxRelativeY;
			    	if (minY != 0)
			    		min = Math.max(minY - 1, topBlockY + minRelativeY - 1);
			    	else
			    		min = topBlockY + minRelativeY - 1;
			    	for (int j = min; j < max; j ++) {
				        int y = j;
				   		if (w.rand.nextInt(100) < chance)
				      	generator.generate(w, rand, x, y, z);
			    	}
		        }
		    	else {
		    		for (int j = minY; j < maxY; j ++) {
				        int y = j;
				   		if (w.rand.nextInt(100) < chance)
				      	generator.generate(w, rand, x, y, z);
		    		}
		    	}
		   	}
		}
	}
}