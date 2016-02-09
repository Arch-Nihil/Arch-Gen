package com.arch.archgen.blocks.soil;

import net.minecraft.util.IIcon;

import com.arch.archgen.blocks.BasicBlocks;
import com.arch.archgen.lib.G;
import com.arch.archgen.tabs.Tabs;

public class BasicSoil extends BasicBlocks {
	protected static final String type = "Soil";
	
	public BasicSoil(String name, String sType, String tName, String rDrop, String rWeight, float hard, float res) {
		super(G.mForType(type), Tabs.tabSoil, type, sType, name + type, tName + name, G.tForType(type), hard, res, 0);
		
        G.soilArray.add(this);
	}

	@Override
	public IIcon getIcon(int s, int m) {
	    return iA[m];
	}
}
