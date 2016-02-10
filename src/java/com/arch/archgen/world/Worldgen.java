package com.arch.archgen.world;

import java.util.Random;

import org.apache.commons.lang3.math.NumberUtils;
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
	int maxHeight, chunkX, chunkZ;
	World w;
	BiomeGenBase b;
	boolean isTopWater, isWaterNear, answer, oceanicCheck, sandyCheck, swampyCheck;
	
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
	
	// --- Check for stuff in surrounding chunks.
	private void getChunksAround(World w, int chunkX, int chunkZ) {
		generateAllChunks(chunkX, chunkZ, 16);
		for (int i = 4; i < 13; i += 4) {
			for (int f = -1; f < 2; f += 2) {
				for (int g = -1; g < 2; g += 2) {
					if (Block.isEqualTo(w.getBlock((chunkX + f) * 16 + i, (w.getTopSolidOrLiquidBlock((chunkX + f) * 16 + i, (chunkZ + g) * 16 + i)), (chunkZ + g) * 16 + i), Blocks.water)) {
						isWaterNear = true;
						i = 16;
						f = 16;
						g = 16;
					}
				}
			}
		}
	}
	
	// --- Generate all chunks surrounding the current gen chunk in a radius as large as the current stuff generating.
	private void generateAllChunks(int chunkX, int chunkZ, int range) {
		IChunkProvider cp = w.getChunkProvider();
		for (int i = 0 - (range / 16); i < range / 16; i ++) {
			for (int j = 0 - (range / 16); j < range / 16; j ++) {
				cp.loadChunk(chunkX + i, chunkZ + j);
			}
		}
	}

	@Override
	public void generate(Random r, int chunkX, int chunkZ, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (w.provider.dimensionId) {
		case 0:
			this.chunkX = chunkX;
			this.chunkZ = chunkZ;
			this.w = w;
			
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
			
			if (Config.doRemoveBedrock == true)
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.bedrock), w, r, chunkX, chunkZ, "absolute", 1, 6, 0, 0, 100);
			this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "absolute", 1, 20, 0, 0, 100);
			this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "absolute", 1, 20, 0, 0, 100);
			this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 1, 24, 0, 0, 100);
			
			if (oceanicCheck) {
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "relative", 20, 0, -44, -8, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "relative", 44, 0, -44, 1, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "relative", 1, 20, -44, 1, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 24, 64, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 1, 12, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 2, 13, 0, 0, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 3, 14, 0, 0, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 15, 0, 0, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 33, 0, -5, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 32, 0, -6, -4, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 31, 0, -7, -5, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 30, 0, -8, -6, 25);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.basalt), w, r, chunkX, chunkZ, "relative", 12, 64, -51, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "relative", 64, 0, -32, 0, 100);
				this.runCloudGen(new WorldgenSubstitute(BlocksRegistry.peridotite, BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 8, 0, 0, 48, 16, 12, 2, 6, 16);
			}
			else if (b.biomeName.equals("Beach")) {
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "absolute", 20, 55, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "absolute", 1, 63, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "absolute", 20, 63, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.sandstone), w, r, chunkX, chunkZ, "relative", 64, 0, -16, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.gravel), w, r, chunkX, chunkZ, "relative", 64, 0, -16, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(Blocks.stone, Blocks.dirt), w, r, chunkX, chunkZ, "relative", 56, 0, -24, -8, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.sand, Blocks.sand), w, r, chunkX, chunkZ, "absolute", 24, 112, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 1, 18, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 2, 19, 0, 0, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 3, 20, 0, 0, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 21, 0, 0, 25);
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
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.granite), w, r, chunkX, chunkZ, "relative", 63, 104, -32, 0, 100);
				this.runCloudGen(new WorldgenSubstitute(BlocksRegistry.peridotite, BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 10, 0, 0, 56, 20, 14, 4, 5, 14);
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
					if (isWaterNear != true && Config.doGenExtra)
						getChunksAround(w, chunkX, chunkZ);
					if (isWaterNear) {
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 51, 0, -1, 0, 33);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 51, 0, -2, -1, 67);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 51, 0, -7, -3, 100);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 50, 0, -8, -6, 75);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 49, 0, -9, -7, 50);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.shale), w, r, chunkX, chunkZ, "relative", 48, 0, -10, -8, 25);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 56, 0, -1, 0, 100);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 53, 0, -10, -8, 100);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 52, 0, -11, -9, 75);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 51, 0, -12, -10, 50);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 50, 0, -13, -11, 25);
					}
					else {
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 53, 0, -9, 0, 100);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 52, 0, -10, -6, 75);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 51, 0, -11, -7, 50);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.siltstone), w, r, chunkX, chunkZ, "relative", 50, 0, -12, -8, 25);
					}
				}
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 1, 24, 0, 0, 100);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 2, 25, 0, 0, 75);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 3, 26, 0, 0, 50);
				this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 27, 0, 0, 25);
				maxHeight = Ints.max(topBlock);
				if (maxHeight >= 96) {
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 23, 81, -153, -68, 100);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 23, 82, -152, -67, 75);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 24, 83, -151, -66, 50);
					this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.diorite), w, r, chunkX, chunkZ, "relative", 24, 84, -150, -65, 25);
					if (maxHeight >= 144) {
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 138, 0, -15, -1, 100);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 137, 0, -16, -2, 75);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 136, 0, -17, -3, 50);
						this.runSubstituteGen(new WorldgenSubstitute(BlocksRegistry.rhyolite), w, r, chunkX, chunkZ, "relative", 135, 0, -18, -4, 25);
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
				this.runCloudGen(new WorldgenSubstitute(BlocksRegistry.peridotite, BlocksRegistry.gabbro), w, r, chunkX, chunkZ, "absolute", 4, 12, 0, 0, 64, 24, 16, 6, 4, 12);
			}

			isTopWater = false;
			isWaterNear = false;
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
	
	private void runCloudGen(WorldGenerator gen, World w, Random rand, int chunkX, int chunkZ, String relativity, int minY, int maxY, int minRelativeY, int maxRelativeY, int cSize, int cSizeY, int cSizeVar, int cSizeVarY, int cDensity, int chance) {
		if (w.rand.nextInt(1000) < chance) {
			int height;
			double ellipsoid;
			
			if (relativity == "relative")
				height = w.rand.nextInt((topBlock[136] + maxRelativeY) - (topBlock[136] + minRelativeY));
			else
				height = w.rand.nextInt(maxY - minY);
			int cSizeZ, cSizeX, cSizeYNew;
			if (w.rand.nextInt(2) == 1)
				cSizeX = cSize + w.rand.nextInt(cSizeVar);
			else
				cSizeX = cSize - w.rand.nextInt(cSizeVar);
			if (w.rand.nextInt(2) == 1)
				cSizeZ = cSize + w.rand.nextInt(cSizeVar);
			else
				cSizeZ = cSize - w.rand.nextInt(cSizeVar);
			if (w.rand.nextInt(2) == 1)
				cSizeYNew = cSizeY + w.rand.nextInt(cSizeVarY);
			else
				cSizeYNew = cSizeY - w.rand.nextInt(cSizeVarY);
			
			generateAllChunks(chunkX, chunkZ, NumberUtils.max(cSizeYNew, cSizeZ, cSizeX));
			
			for (int i = 0 - cSizeX / 2; i < cSizeX / 2; i ++) {
				int x = chunkX * 16 + i;
				for (int k = 0 - cSizeZ / 2; k < cSizeZ / 2; k ++) {
			    	int z = chunkZ * 16 + k;
			    	for (int j = 0 - cSizeYNew / 2; j < cSizeYNew / 2; j ++) {
				        int y = minY + height + j;
				        ellipsoid = (Math.pow((double) i / ((double) cSizeX / 2), 2) + Math.pow((double) k / ((double) cSizeZ / 2), 2) + Math.pow((double) j / ((double) cSizeYNew / 2), 2));
					        if (ellipsoid <= (w.rand.nextDouble() / (double) cDensity) + (1 - 1 / (double) cDensity)) {
					        	gen.generate(w, rand, x, y, z);
					        }
				   	}
			    }
	    	}
		}
	}
}