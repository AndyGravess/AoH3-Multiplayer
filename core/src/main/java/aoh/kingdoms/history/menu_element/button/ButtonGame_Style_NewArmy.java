// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

public class ButtonGame_Style_NewArmy extends ButtonGame_Style
{
    public int iArmyID;
    public int iUnitID;
    public boolean add;
    
    public ButtonGame_Style_NewArmy(final int iUnitID, final int iArmyID, final boolean add, final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final boolean isClickable) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable);
        this.iUnitID = iUnitID;
        this.iArmyID = iArmyID;
        this.add = add;
    }
    
    @Override
    public int getValue1() {
        return this.iUnitID;
    }
    
    @Override
    public int getValue2() {
        return this.iArmyID;
    }
    
    @Override
    public int getCurrent() {
        return this.add ? 1 : 0;
    }
}
