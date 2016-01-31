package com.arch.archgen.items.brick;

import com.arch.archgen.lib.G;

public class SedimentaryBrick extends BasicBrick {
	protected static final String subtype = "Sedimentary";
	protected static String texName = G.texName + type + subtype;
	
	public SedimentaryBrick(String name) {
		super(name, texName);
	}
}
