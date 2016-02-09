package com.arch.archgen.blocks.bricks;

import net.minecraft.util.IIcon;

import com.arch.archgen.blocks.BasicBlocks;
import com.arch.archgen.lib.G;
import com.arch.archgen.tabs.Tabs;

public class BasicBricks extends BasicBlocks {
	protected static final String type = "Bricks";
	
	public BasicBricks(String name, String sType, String tName, float hard, float res, int lvl) {
		super(G.mForType(type), Tabs.tabBricks, type, sType, name + type, tName + name, G.tForType(type), hard, res, lvl);
		this.setStepSound(soundTypeStone);
		
		G.bricksArray.add(this);
	}
	
	@Override
	public IIcon getIcon(int s, int m) {
	    return iA[m];
	}
}
