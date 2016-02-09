package com.arch.archgen.items;

import java.util.List;

import com.arch.archgen.lib.G;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BasicItems extends Item {
	protected IIcon[] iA;
	protected String tN, ty, sTy, n;
	
	public BasicItems(CreativeTabs cT, String ty, String sTy, String n, String tN) {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(n);
        this.setCreativeTab(cT);
        this.tN = tN;
        this.ty = ty;
        this.sTy = sTy;
        this.n = n;
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
	public IIcon getIconFromDamage(int m) {
	    return this.iA[m];
	}
	
	@Override
	public void getSubItems(Item mI, CreativeTabs bTab, List l) {
	    for (int i = 0; i < G.mAmountForType(ty); i ++) {
	        l.add(new ItemStack(mI, 1, i));
	    }
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		String[] splitTN = tN.split("(?=[A-Z])");
		boolean isBrick = false;
		if (splitTN[1].equals("Brick")) 
			isBrick = true;
	    for (int i = 0; i < iA.length; i ++) {
	    	if (isBrick)
	    		iA[i] = reg.registerIcon(tN + G.sizeArray[i + 1]);
	    	else
	    		iA[i] = reg.registerIcon(tN + G.sizeArray[i]);
	    }
	}
	
	 @Override
	 public String getUnlocalizedName(ItemStack iStack) {
		int x = 0;
		String uN = this.getUnlocalizedName();
		 
		if (ty.equals("Brick"))
			 x = 1;
		
		String stringX = uN.substring(5) + G.sizeArray[iStack.getItemDamage() + x];
		return stringX;
	 }
}