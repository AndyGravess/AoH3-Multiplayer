// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

public class ButtonCurrentSituation_Value extends ButtonCurrentSituation
{
    public int id;
    
    public ButtonCurrentSituation_Value(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final boolean row, final int id) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth, row);
        this.id = id;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
}
