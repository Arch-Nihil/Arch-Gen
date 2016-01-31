package com.arch.archgen.items.dust;

import com.arch.archgen.lib.G;

public class SedimentaryDust extends BasicDust {
	protected static final String subtype = "Sedimentary";
	protected static String texName = G.texName + type + subtype;
	
	public SedimentaryDust(String name) {
		super(name, texName);
	}
}
