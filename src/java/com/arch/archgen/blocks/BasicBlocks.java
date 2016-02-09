package com.arch.archgen.blocks;

import java.util.List;

import com.arch.archgen.lib.G;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BasicBlocks extends Block {
	protected IIcon[] iA;
	protected String tN, ty, sTy, n;
	
	protected BasicBlocks(Material m, CreativeTabs cT, String ty, String sTy, String n, String tN, String t, float h, float r, int l) {
		super(m);
		this.setBlockName(n);
		this.setCreativeTab(cT);
        this.setHardness(h);
        this.setResistance(r);
        this.setHarvestLevel(t, l);
        this.n = n;
        this.tN = tN;
        this.ty = ty;
        this.sTy = sTy;
        iA = new IIcon[G.nTexForType(ty)];
	}
	
	public String getName() {
		return n;
	}
	
	public String getType() {
		return ty;
	}
	
	public String getSubtype() {
		return sTy;
	}
	
	@Override
	public int damageDropped(int meta) {
	    return meta;
	}
	
	@Override
	public void getSubBlocks(Item mI, CreativeTabs bTab, List l) {
	    for (int i = 0; i < G.mAmountForType(ty); i ++) {
	        l.add(new ItemStack(mI, 1, i));
	    }
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		String[] splitTN = tN.split("(?=[A-Z])");
		boolean isBricks = false;
		if (splitTN[1].equals("Bricks")) 
			isBricks = true;
	    for (int i = 0; i < iA.length; i ++) {
	    	if (isBricks)
	    		iA[i] = reg.registerIcon(tN + G.sizeArray[i + 1]);
	    	else
	    		iA[i] = reg.registerIcon(tN + "-" + i);
	    }
	}
}