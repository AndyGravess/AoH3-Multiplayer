// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

public class ButtonGame_Value extends ButtonGame
{
    public int id;
    
    public ButtonGame_Value(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
        this.id = id;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
}
