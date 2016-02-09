package com.arch.archgen.blocks.soil;

import net.minecraft.util.IIcon;

import com.arch.archgen.blocks.BasicBlocks;
import com.arch.archgen.lib.G;
import com.arch.archgen.tabs.Tabs;

public class BasicSoil extends BasicBlocks {
	protected static final String type = "Soil";
	protected static String texName = G.texName + type;
	
	public BasicSoil(String name, float hard, float res) {
		super(G.mForType(type), Tabs.tabSoil, type, name + type, texName + name, G.tForType(type), hard, res, 0);
		this.setStepSound(soundTypeSand);
		
        G.soilArray.add(this);
	}

	@Override
	public IIcon getIcon(int s, int m) {
	    return iA[m];
	}
}
