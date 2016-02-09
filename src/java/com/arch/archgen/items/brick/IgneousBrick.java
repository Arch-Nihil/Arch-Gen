package com.arch.archgen.items.brick;

import com.arch.archgen.lib.G;

public class IgneousBrick extends BasicBrick {
	protected static final String subtype = "Igneous";
	protected static String texName = G.texName + type + subtype;
	
	public IgneousBrick(String name) {
		super(name, subtype, texName);
	}
}
