package com.arch.archgen.world;

import java.util.Random;

import com.arch.archgen.blocks.BlocksRegistry;
import com.arch.archgen.config.Config;
import com.arch.archgen.lib.G;
import com.google.common.primitives.Ints;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class Worldgen implements IWorldGenerator {
	int[] topBlock;
	int maxHeight;
	BiomeGenBase b;
	boolean isTopWater, answer, oceanicCheck, sandyCheck, swampyCheck;
	
	public static void init() {
		GameRegistry.registerWorldGenerator(new Worldgen(), 0);
	}
	
	// --- Getting top block for the X, Z coordinate.
	public void getTopBlock(World w, int chunkX, int chunkZ, BiomeGenBase b) {
		int topBlockAverage;
		topBlock = new int[256];
		
		if (oceanicCheck == false) {
			topBlockAverage = (w.getTopSolidOrLiquidBlock(chunkX * 16 + 1, chunkZ * 16 + 1) + w.getTopSolidOrLiquidBlock(chunkX * 16 + 14, chunkZ * 16 + 1) + w.getTopSolidOrLiquidBlock(chunkX * 16 + 1, chunkZ * 16 + 14) + w.getTopSolidOrLiquidBlock(chunkX * 16 + 14, chunkZ * 16 + 14)) / 4;
			for (int i = 0; i < 16; i ++) {
				for (int k = 0; k < 16; k ++) {
					topBlock[i * 16 + k] = topBlockAverage;
				}
			}
		}
		else {
			for (int k = 0; k < 256; k ++) {
				topBlock[k] = 64;
			}
		}
		
		for (int i = 0; i < 16; i ++) {
			for (int k = 0; k < 16; k ++) {
				if (oceanicCheck == false) {
					checkAbove(w, chunkX, chunkZ, i, k);
					checkAboveSmall(w, chunkX, chunkZ, i, k);
				}
						
				while (checkIfSolid(w.getBlock(chunkX * 16 + i, topBlock[i * 16 + k], chunkZ * 16 + k)) == false)
					topBlock[i * 16 + k] --;
				topBlock[i * 16 + k] ++;
			}
		}
	}

	// --- Check for solid block avove in increments of one.
	private boolean checkAboveSmall(World w, int chunkX, int chunkZ, int i, int k) {
		answer = false;
		for (int m = Config.magicNumberTwo; m > 0; m --) {
			if (checkIfSolid(w.getBlock(chunkX * 16 + i, topBlock[i * 16 + k] + m, chunkZ * 16 + k))) {
				topBlock[i * 16 + k] = topBlock[i * 16 + k] + m;
				answer = true;
				if (m != Config.magicNumberTwo)
					m = 0;
			}
			else if ((k % 5 == 0 && i % 5 == 0) && isTopWater == false) {
				if (Block.isEqualTo(w.getBlock(chunkX * 16 + i, topBlock[i * 16 + k], chunkZ * 16 + k), Blocks.water))
					isTopWater = true;
			}
		}
		return answer;
	}
	
	// --- Check for solid block avove in variable increments of multiples.
	private boolean checkAbove(World w, int chunkX, int chunkZ, int i, int k) {
		answer = false;
		for (int m = Config.magicNumberOne; m > 1; m --) {
			for (int n = Config.magicNumberOne; n > 3; n --) {
				if (Math.pow(m, n) < (256 - topBlock[i * 16 + k]) && checkIfSolid(w.getBlock(chunkX * 16 + i, (int) (topBlock[i * 16 + k] + Math.pow(m, n)), chunkZ * 16 + k))) {
					topBlock[i * 16 + k] = Math.max((int) (topBlock[i * 16 + k] + Math.pow(m, n)), topBlock[i * 16 + k]);
					answer = true;
					n = 2;
				}
			}
		}
		return answer;
	}

	// --- Check to see if current block is solid.
	private boolean checkIfSolid(Block b) {
		answer = false;
		for (int n = 0; n < G.solidBArray.length && answer == false; n ++) {
			if (Block.isEqualTo(G.solidBArray[n], b))
				answer = true;
		}
		return answer;
	}

	@Override
	public void generate(Random r, int chunkX, int chunkZ, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (w.provider.dimensionId) {
		case 0:
			b = w.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);
			
			for (int a = 0; a < G.maxBArraySize && sandyCheck == false && oceanicCheck == false && swampyCheck == false; a ++) {
				if (a < G.sandyBArray.length) {
					if (b.biomeName.equals(G.sandyBArray[a]))
						sandyCheck = true;
					if (a < G.oceanicBArray.length) {
						if (b.biomeName.equals(G.oceanicBArray[a]))
							oceanicCheck = true;
						if (a < G.swampyBArray.length) {
							if (b.biomeName.equals(G.swampyBArray[a]))
								swampyCheck = true;
						}
					}
				}
			}
			
			getTopBlock(w, chunkX, chunkZ, b);
			
			maxHeight = Ints.max(topBlock);
			
			this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "absolute", 1, 20, 0, 0, 100);
			this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "absolute", 1, 20, 0, 0, 100);
			this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 1, 24, 0, 0, 100);
			
			if (oceanicCheck) {
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "relative", 20, 0, -44, -8, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "relative", 44, 0, -44, 1, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "relative", 1, 20, -44, 1, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 24, 64, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 1, 12, -63, -18, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 2, 13, -62, -19, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 3, 14, -61, -20, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 4, 15, -60, -21, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 33, 0, -5, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 32, 0, -6, -4, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 31, 0, -7, -5, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 30, 0, -8, -6, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.basalt), w, r, chunkX, chunkZ, "relative", 12, 64, -51, 0, 100);
				this.runCloudGen(new WorldgenSubstitute(BlocksRegistry.peridotite, BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 8, 0, 0, 48, 12, 12, 2, 45, 32, 10);
			}
			else if (b.biomeName.equals("Beach")) {
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "absolute", 20, 55, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "absolute", 1, 63, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "absolute", 20, 63, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "relative", 64, 0, -16, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "relative", 64, 0, -16, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "relative", 56, 0, -24, -8, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 24, 112, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 1, 18, -74, -48, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 2, 19, -75, -49, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 3, 20, -76, -50, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 4, 21, -77, -51, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.limestone), w, r, chunkX, chunkZ, "relative", 34, 68, -16, -1, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.limestone), w, r, chunkX, chunkZ, "relative", 33, 0, -17, -10, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.limestone), w, r, chunkX, chunkZ, "relative", 32, 0, -18, -11, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.limestone), w, r, chunkX, chunkZ, "relative", 31, 0, -19, -12, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.limestone), w, r, chunkX, chunkZ, "absolute", 68, 69, 0, 0, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.limestone), w, r, chunkX, chunkZ, "absolute", 69, 70, 0, 0, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.limestone), w, r, chunkX, chunkZ, "absolute", 70, 71, 0, 0, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 29, 0, -21, -16, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 28, 0, -22, -18, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 27, 0, -23, -19, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 26, 0, -24, -20, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "absolute", 68, 192, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "absolute", 18, 63, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "relative", 63, 96, -32, 0, 100);
				this.runCloudGen(new WorldgenSubstitute(BlocksRegistry.peridotite, BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 10, 0, 0, 56, 14, 14, 4, 52, 36, 7);
			}
			else {
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "absolute", 20, 55, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "absolute", 1, 63, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "absolute", 20, 63, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "relative", 63, 0, -191, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "relative", 63, 0, -191, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "relative", 55, 0, -191, -8, 100);
				if (sandyCheck) {
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 44, 0, -76, 0, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 43, 0, -77, -20, 75);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 42, 0, -78, -21, 50);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sandstone), w, r, chunkX, chunkZ, "relative", 41, 0, -79, -22, 25);
				}
				else if (swampyCheck) {
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 51, 0, -10, -1, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 50, 0, -11, -9, 75);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 49, 0, -12, -10, 50);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 48, 0, -13, -11, 25);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 59, 0, -1, 0, 100);
				}
				else if (isTopWater) {
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 51, 0, -11, 0, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 50, 0, -12, -6, 75);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 49, 0, -13, -7, 50);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 48, 0, -14, -8, 25);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 53, 0, -15, -12, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 52, 0, -16, -14, 75);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 51, 0, -17, -15, 50);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 50, 0, -18, -16, 25);
				}
				else {
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 53, 0, -9, 0, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 52, 0, -10, -6, 75);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 51, 0, -11, -7, 50);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 50, 0, -12, -8, 25);
				}
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 1, 24, -254, -48, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 2, 25, -253, -49, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 3, 26, -252, -50, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "relative", 4, 27, -251, -51, 25);
				if (maxHeight >= 96) {
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 23, 81, -153, -68, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 23, 82, -152, -67, 75);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 24, 83, -151, -66, 50);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 24, 84, -150, -65, 25);
					if (maxHeight >= 144) {
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 136, 0, -15, -1, 100);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 135, 0, -16, -2, 75);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 134, 0, -17, -3, 50);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 133, 0, -18, -4, 25);
						if (maxHeight >= 192) {
							this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 64, 255, 0, 0, 100);
							this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "absolute", 64, 255, 0, 0, 100);
						}
						else {
							this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 64, 192, 0, 0, 100);
							this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "absolute", 64, 192, 0, 0, 100);
						}
					}
					else
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 64, 144, 0, 0, 100);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "absolute", 64, 144, 0, 0, 100);
				}
				else {
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 64, 96, 0, 0, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "absolute", 64, 96, 0, 0, 100);
				}
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 24, 64, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "absolute", 24, 64, 0, 0, 100);
				this.runCloudGen(new WorldgenSubstitute(BlocksRegistry.peridotite, BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 12, 0, 0, 64, 16, 16, 6, 60, 40, 4);
			}
			
			isTopWater = false;
			oceanicCheck = false;
			sandyCheck = false;
			swampyCheck = false;
			break;
		case -1:
			break;
		case 1:
			break;
		}
	}

	private void runSubstituteGen(WorldGenerator gen, World w, Random rand, int chunkX, int chunkZ, String relativity, int minY, int maxY, int minRelativeY, int maxRelativeY, int chance) {
		for (int i = 0; i < 16; i ++) {
		    for (int k = 0; k < 16; k ++) {
		    	int min, max;
		    	if (relativity == "relative") {
			    	if (maxY != 0)
			    		max = Math.min(maxY, topBlock[i * 16 + k] + maxRelativeY);
			    	else
			    		max = topBlock[i * 16 + k] + maxRelativeY;
			    	if (minY != 0)
			    		min = Math.max(minY - 1, topBlock[i * 16 + k] + minRelativeY - 1);
			    	else
			    		min = topBlock[i * 16 + k] + minRelativeY - 1;
		    	}
		    	else {
		    		max = maxY;
		    		min = minY;
		    	}
	    		for (int j = min; j < max; j ++) {
			   		if (w.rand.nextInt(100) < chance)
			   			gen.generate(w, rand, chunkX * 16 + i, j, chunkZ * 16 + k);
	    		}
	        }
	   	}
	}
	
	private void runCloudGen(WorldGenerator gen, World w, Random rand, int chunkX, int chunkZ, String relativity, int minY, int maxY, int minRelativeY, int maxRelativeY, int cSize, int cSizeY, int cSizeVar, int cSizeVarY, int innerC, int cDensity, int chance) {
		if (w.rand.nextInt(1000) < chance) {
			int height;
			if (relativity == "relative")
				height = w.rand.nextInt((topBlock[136] + maxRelativeY) - (topBlock[136] + minRelativeY));
			else
				height = w.rand.nextInt(maxY - minY);
			int cSizeZ, cSizeX;
			if (w.rand.nextInt(2) == 1)
				cSizeX = cSize + w.rand.nextInt(cSizeVar);
			else
				cSizeX = cSize - w.rand.nextInt(cSizeVar);
			if (w.rand.nextInt(2) == 1)
				cSizeZ = cSize + w.rand.nextInt(cSizeVar);
			else
				cSizeZ = cSize - w.rand.nextInt(cSizeVar);
			if (w.rand.nextInt(2) == 1)
				cSizeY = cSizeY + w.rand.nextInt(cSizeVarY);
			else
				cSizeY = cSizeY - w.rand.nextInt(cSizeVarY);
			if (cDensity >= Math.min(cSizeX / 2, cSizeZ / 2))
				cDensity = Math.min(cSizeX / 2 - 1, cSizeZ / 2 - 1);
			for (int i = 0 - cSizeX / 2; i < cSizeX / 2; i ++) {
				int x = chunkX * 16 + i;
				for (int k = 0 - cSizeZ / 2; k < cSizeZ / 2; k ++) {
			    	int z = chunkZ * 16 + k;
			    	for (int j = 0 - cSizeY / 2; j < cSizeY / 2; j ++) {
				        int y = minY + height + j;
				        if (Math.abs(i) <= innerC * (cSizeX / Math.max(cSizeZ, cSizeX)) && Math.abs(k) <= innerC * (cSizeZ / Math.max(cSizeZ, cSizeX)) && Math.abs(j) <= innerC * (cSizeY / Math.max(cSizeZ, cSizeX)))
				        	gen.generate(w, rand, x, y, z);
				        else if (Math.abs(i) <= cDensity * (cSizeX / Math.max(cSizeZ, cSizeX)) + w.rand.nextInt(cSizeX / 2 - (cDensity * (cSizeX / Math.max(cSizeZ, cSizeX)))) && Math.abs(k) <= cDensity * (cSizeZ / Math.max(cSizeZ, cSizeX)) + w.rand.nextInt(cSizeZ / 2 - (cDensity * (cSizeZ / Math.max(cSizeZ, cSizeX)))) && Math.abs(j) <= cDensity * (cSizeY / Math.max(cSizeZ, cSizeX)) + w.rand.nextInt(cSizeY / 2 - (cDensity * (cSizeY / Math.max(cSizeZ, cSizeX))))) {
				        	gen.generate(w, rand, x, y, z);
				        }
				   	}
			    }
	    	}
		}
	}
}