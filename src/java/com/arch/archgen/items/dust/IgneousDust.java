package com.arch.archgen.items.dust;

import com.arch.archgen.lib.G;

public class IgneousDust extends BasicDust {
	protected static final String subtype = "Igneous";
	protected static String texName = G.texName + type + subtype;
	
	public IgneousDust(String name) {
		super(name, subtype, texName);
	}
}
