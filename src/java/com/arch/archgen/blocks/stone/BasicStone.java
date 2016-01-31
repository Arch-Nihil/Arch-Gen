package com.arch.archgen.blocks.stone;

import java.util.ArrayList;
import java.util.stream.IntStream;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.arch.archgen.blocks.BasicBlocks;
import com.arch.archgen.lib.G;
import com.arch.archgen.lib.Strings;
import com.arch.archgen.tabs.Tabs;

import cpw.mods.fml.common.registry.GameData;

public class BasicStone extends BasicBlocks {
	protected static final String type = "Stone";
	protected String[] rD, rW;
	protected ArrayList<ItemStack> allDrops = new ArrayList<ItemStack>();
	protected String name;
	protected static int rWeight;
	
	public BasicStone(String name, String tName, String rDrop, String rWeight, float hard, float res, int lvl) {
		super(G.mForType(type), Tabs.tabStone, type, name + type, tName + name, G.tForType(type), hard, res, lvl);
		
		G.stoneArray.add(this);
		
		rW = rWeight.split(",");
		rD = rDrop.split(",");
		this.name = name;
	}
	
	@Override
	public IIcon getIcon(int s, int m) {
	  return iA[s + m * 6];
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World w, int x, int y, int z, int meta, int fortune) {
		int[] rWInt = new int[rW.length];
		
		if (fortune > 10)
			fortune = 10;
		
		int rValue = w.rand.nextInt(4) * (fortune + 1);
		rWeight = G.bWeight + fortune * 3;
		
		for (int i = 0; i < rW.length; i ++) {
			rWInt[i] = Integer.parseInt(rW[i]);
		}
		
		int rN = w.rand.nextInt(IntStream.of(rWInt).sum() * (11 - fortune));
		int n = 0;
		
		for (int i = 0; i < rW.length; i ++) {
			if (rValue >= 9 && rWeight >= 9 && rN > n && rN < rWInt[i] + n) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + rD[i] + "Chunk"), 1, 2));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + rD[i] + "Dust"), 1, 2));
				rValue -= 9;
				rWeight -= 9;
			}
			else if (rValue >= 3 && rWeight >= 3 && rN > n && rN < rWInt[i] + n) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + rD[i] + "Chunk"), 1, 1));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + rD[i] + "Dust"), 1, 1));
				rValue -= 3;
				rWeight -= 3;
			}
			else if (rValue >= 1 && rWeight >= 1 && rN > n && rN < rWInt[i] + n) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + rD[i] + "Chunk"), 1, 0));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + rD[i] + "Dust"), 1, 0));
				rValue -= 1;
				rWeight -= 1;
			}	
		}
		
		while (rWeight > 0) {
			if (rWeight >= 81 && w.rand.nextInt(11 - fortune) < 5) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Chunk"), 1, 4));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Dust"), 1, 4));
				rWeight -= 81;
			}
			if (rWeight >= 27) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Chunk"), 1, 3));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Dust"), 1, 3));
				rWeight -= 27;
			}
			if (rWeight >= 9) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Chunk"), 1, 2));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Dust"), 1, 2));
				rWeight -= 9;
			}
			if (rWeight >= 3) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Chunk"), 1, 1));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Dust"), 1, 1));
				rWeight -= 3;
			}
			if (rWeight > 0) {
				if (w.rand.nextInt(21 - fortune) < 10)
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Chunk"), 1, 0));
				else
					allDrops.add(new ItemStack(GameData.getItemRegistry().getObject(Strings.MODID + ":" + name + "Dust"), 1, 0));
				rWeight -= 1;
			}
		}
	  return allDrops;
	}
}
