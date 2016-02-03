package com.arch.archgen.world;

import java.util.Random;

import com.arch.archgen.blocks.BlocksRegistry;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class Worldgen implements IWorldGenerator {

	private WorldGenerator genGabbro;
	private WorldGenerator genGabbroTransUp;
	private WorldGenerator genGabbroTransMid;
	private WorldGenerator genGabbroTransLow;
	
	public static void init() {
		GameRegistry.registerWorldGenerator(new Worldgen(), 0);
	}
	
	public Worldgen() {
		this.genGabbro = new WorldgenSubstitute(BlocksRegistry.gabbro);
		this.genGabbroTransLow = new WorldgenSubstitute(BlocksRegistry.gabbro);
		this.genGabbroTransMid = new WorldgenSubstitute(BlocksRegistry.gabbro);
		this.genGabbroTransUp = new WorldgenSubstitute(BlocksRegistry.gabbro);
	}
	
	@Override
	public void generate(Random r, int chunkX, int chunkZ, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (w.provider.dimensionId) {
		case 0:
			this.runGenerator(this.genGabbro, w, r, chunkX, chunkZ, 0, 12, 100);
			this.runGenerator(this.genGabbroTransLow, w, r, chunkX, chunkZ, 13, 14, 75);
			this.runGenerator(this.genGabbroTransMid, w, r, chunkX, chunkZ, 15, 16, 50);
			this.runGenerator(this.genGabbroTransUp, w, r, chunkX, chunkZ, 17, 18, 25);
			break;
		case -1:
			break;
		case 1:
			break;
		}
	}

	private void runGenerator(WorldGenerator generator, World w, Random rand, int chunkX, int chunkZ, int minX, int maxX, int chance) {
	    int heightD = maxX - minX + 1;
	    for (int i = 0; i < 16; i ++) {
	    	int x = chunkX * 16 + i;
	    	for (int j = 0; j < heightD; j ++) {
		        int y = minX + j;
		        for (int k = 0; k < 16; k ++) {
			        int z = chunkZ * 16 + k;
			        	if (w.rand.nextInt(100) < chance)
			        		generator.generate(w, rand, x, y, z);
		        }
	    	}
	    }
	}
}
