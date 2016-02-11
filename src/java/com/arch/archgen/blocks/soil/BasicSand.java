package com.arch.archgen.blocks.soil;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.arch.archgen.lib.G;
import com.arch.archgen.tabs.Tabs;

public class BasicSand extends BlockFalling {
	protected static final String type = "Soil";
	protected static final String subtype = "Sand";
	protected String[] rD, rW;
	protected ArrayList<ItemStack> allDrops = new ArrayList<ItemStack>();
	protected static int rWeight;
	protected static String texName = G.texName + type;
	protected IIcon[] iA;
	protected String tN, n;
	
	public BasicSand(String genericName, String rDrop, String rWeight, float hard, float res)  {
		super();
		this.setStepSound(soundTypeSand);
		this.setBlockName(genericName + type);
		this.setCreativeTab(Tabs.tabSoil);
        this.setHardness(hard);
        this.setResistance(res);
        this.n = genericName + type;
        this.tN = texName + genericName;
        iA = new IIcon[G.nTexForType(type)];
	}
	
	public String getName() {
		return n;
	}
	
	public String getType() {
		return type;
	}
	
	public String getSubtype() {
		return subtype;
	}
	
	@Override
	public int damageDropped(int meta) {
	    return meta;
	}
	
	@Override
	public void getSubBlocks(Item mI, CreativeTabs bTab, List l) {
	    for (int i = 0; i < G.mAmountForType(type); i ++) {
	        l.add(new ItemStack(mI, 1, i));
	    }
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
	    for (int i = 0; i < iA.length; i ++) {
	    	iA[i] = reg.registerIcon(tN + "-" + i);
	    }
	}
	@Override
	public IIcon getIcon(int s, int m) {
	    return iA[m];
	}
}
