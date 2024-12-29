// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.graphics.Color;

public class ButtonStatsRectIMG_BonusesColor extends ButtonStatsRectIMG_Bonuses
{
    public Color bonusColors;
    
    public ButtonStatsRectIMG_BonusesColor(final String sText, final String sText2, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final Color nColor) {
        super(sText, sText2, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth);
        this.bonusColors = nColor;
    }
    
    @Override
    public Color getColorBonus() {
        return this.bonusColors;
    }
}
