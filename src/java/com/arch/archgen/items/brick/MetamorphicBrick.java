package com.arch.archgen.items.brick;

import com.arch.archgen.lib.G;

public class MetamorphicBrick extends BasicBrick {
	protected static final String subtype = "Metamorphic";
	protected static String texName = G.texName + type + subtype;
	
	public MetamorphicBrick(String name) {
		super(name, subtype, texName);
	}
}
