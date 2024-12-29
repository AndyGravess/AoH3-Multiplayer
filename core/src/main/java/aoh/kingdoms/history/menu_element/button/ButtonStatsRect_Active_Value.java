// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.CFG;

public class ButtonStatsRect_Active_Value extends ButtonStatsRect_Active
{
    public int id;
    
    public ButtonStatsRect_Active_Value(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int id) {
        super(sText, CFG.PADDING * 2, iPosX, iPosY, nWidth, nHeight, CFG.FONT_REGULAR_SMALL);
        this.id = id;
    }
    
    public ButtonStatsRect_Active_Value(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int id, final int iTextPos) {
        super(sText, iTextPos, iPosX, iPosY, nWidth, nHeight, CFG.FONT_REGULAR_SMALL);
        this.id = id;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
}
