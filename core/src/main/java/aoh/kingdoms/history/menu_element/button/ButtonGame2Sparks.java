// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menus.MainMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonGame2Sparks extends ButtonGame2
{
    public ButtonGame2Sparks(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
    }
    
    public ButtonGame2Sparks(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final int nHeight) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable, nHeight);
    }
    
    public ButtonGame2Sparks(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean checkBox) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable, checkBox);
    }
    
    public void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(MainMenu.sparksColors);
        MenuManager.sparksAnimationHover.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
    }
}
