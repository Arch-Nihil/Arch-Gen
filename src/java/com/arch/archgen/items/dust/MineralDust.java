package com.arch.archgen.items.dust;

import com.arch.archgen.lib.G;

public class MineralDust extends BasicDust {
	protected static final String subtype = "Mineral";
	protected static String texName = G.texName + type + subtype;
	
	public MineralDust(String name) {
		super(name, texName);
	}
}
