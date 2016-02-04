package com.arch.archgen.lib;

import java.util.ArrayList;

import com.arch.archgen.blocks.bricks.BasicBricks;
import com.arch.archgen.blocks.ore.BasicOre;
import com.arch.archgen.blocks.soil.BasicSoil;
import com.arch.archgen.blocks.stone.BasicStone;
import com.arch.archgen.items.brick.BasicBrick;
import com.arch.archgen.items.chunk.BasicChunk;
import com.arch.archgen.items.dust.BasicDust;

import net.minecraft.block.material.Material;

public class G {
	public static ArrayList<BasicBricks> bricksArray = new ArrayList<BasicBricks>();
	public static ArrayList<BasicSoil> soilArray = new ArrayList<BasicSoil>();
	public static ArrayList<BasicStone> stoneArray = new ArrayList<BasicStone>();
	public static ArrayList<BasicOre> oreArray = new ArrayList<BasicOre>();
	
	public static ArrayList<BasicBrick> brickArray = new ArrayList<BasicBrick>();
	public static ArrayList<BasicDust> dustArray = new ArrayList<BasicDust>();
	public static ArrayList<BasicChunk> chunkArray = new ArrayList<BasicChunk>();
	
	public static final String[] sizeArray = new String[]{"Tiny", "Small", "Medium", "Large", "Huge"};
	public static final String[] oceanicBArray = new String[]{"Ocean", "Deep Ocean", "FrozenOcean"};
	public static String texName = Strings.MODID + ":";
	public static final int bWeight = 81;
	
	public static int nTexForType(String type) {
		switch (type) {
			case "Stone": return 18;
			case "Bricks": case "Brick": return 3;
			default : return 5;
		}
	}
	
	public static Material mForType(String type) {
		switch (type) {
			case "Stone": return Material.rock;
			case "Bricks": return Material.rock;
			default : return Material.air;
		}
	}
	
	public static String tForType(String type) {
		switch (type) {
			case "Stone": return "pickaxe";
			case "Bricks": return "pickaxe";
			default : return "";
		}
	}

	public static int mAmountForType(String type) {
		switch (type) {
			case "Stone": case "Bricks": case "Brick": return 3;
			default : return 5;
		}
	}
}
