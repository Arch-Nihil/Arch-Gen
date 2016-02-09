package com.arch.archgen.items.brick;

import com.arch.archgen.lib.G;

public class MineralBrick extends BasicBrick {
	protected static final String subtype = "Mineral";
	protected static String texName = G.texName + type + subtype;
	
	public MineralBrick(String name) {
		super(name, subtype, texName);
	}
}
