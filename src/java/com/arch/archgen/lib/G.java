package com.arch.archgen.lib;

import java.util.ArrayList;

import org.apache.commons.lang3.math.NumberUtils;

import com.arch.archgen.blocks.bricks.BasicBricks;
import com.arch.archgen.blocks.ore.BasicOre;
import com.arch.archgen.blocks.soil.BasicSand;
import com.arch.archgen.blocks.stone.BasicStone;
import com.arch.archgen.items.brick.BasicBrick;
import com.arch.archgen.items.chunk.BasicChunk;
import com.arch.archgen.items.dust.BasicDust;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class G {
	public static ArrayList<BasicBricks> bricksArray = new ArrayList<BasicBricks>();
	public static ArrayList<BasicSand> sandArray = new ArrayList<BasicSand>();
	public static ArrayList<BasicStone> stoneArray = new ArrayList<BasicStone>();
	public static ArrayList<BasicOre> oreArray = new ArrayList<BasicOre>();
	
	public static ArrayList<BasicBrick> brickArray = new ArrayList<BasicBrick>();
	public static ArrayList<BasicDust> dustArray = new ArrayList<BasicDust>();
	public static ArrayList<BasicChunk> chunkArray = new ArrayList<BasicChunk>();
	
	public static final Block[] solidBArray = new Block[]{Blocks.dirt, Blocks.stone, Blocks.grass, Blocks.gravel, Blocks.sand, Blocks.sandstone, Blocks.stained_hardened_clay, Blocks.hardened_clay};
	public static final Block[] minSolidBArray = new Block[]{Blocks.dirt, Blocks.stone, Blocks.gravel, Blocks.sand};
	public static final String[] oceanicBArray = new String[]{"Ocean", "Deep Ocean", "FrozenOcean"};
	public static final String[] sandyBArray = new String[]{"Desert", "Desert M", "DesertHills", "Mesa", "Mesa (Bryce)", "Mesa Plateau", "Mesa Plateau F", "Mesa Plateau M", "Mesa Plateau F M"};
	public static final String[] swampyBArray = new String[]{"Swampland", "Swampland M", "MushroomIsland"};
	
	public static final String[] sizeArray = new String[]{"Tiny", "Small", "Medium", "Large", "Huge"};
	public static final int maxBArraySize = NumberUtils.max(oceanicBArray.length, sandyBArray.length, swampyBArray.length);
	public static final int bWeight = 81;
	public static String texName = Strings.MODID + ":";
	
	public static int nTexForType(String type) {
		switch (type) {
			case "Stone": case "Soil": return 18;
			case "Bricks": case "Brick": return 3;
			default : return 5;
		}
	}
	
	public static Material mForType(String type) {
		switch (type) {
			case "Stone": case "Bricks": return Material.rock;
			case "Soil": return Material.sand;
			default : return Material.air;
		}
	}
	
	public static String tForType(String type) {
		switch (type) {
			case "Stone": case "Bricks": return "pickaxe";
			case "Soil" : return "shovel";
			default : return "";
		}
	}

	public static int mAmountForType(String type) {
		switch (type) {
			case "Stone": case "Soil": case "Bricks": case "Brick": return 3;
			default : return 5;
		}
	}
}
