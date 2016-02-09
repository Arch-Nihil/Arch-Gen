package com.arch.archgen.items.dust;

import com.arch.archgen.lib.G;

public class MetamorphicDust extends BasicDust {
	protected static final String subtype = "Metamorphic";
	protected static String texName = G.texName + type + subtype;
	
	public MetamorphicDust(String name) {
		super(name, subtype, texName);
	}
}
