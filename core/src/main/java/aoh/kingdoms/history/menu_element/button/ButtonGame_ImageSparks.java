// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menus.MainMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonGame_ImageSparks extends ButtonGame_Image
{
    public ButtonGame_ImageSparks(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final int imageID) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable, imageID);
    }
    
    public ButtonGame_ImageSparks(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final boolean isClickable, final int imageID) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable, imageID);
    }
    
    @Override
    public void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(MainMenu.sparksColors);
        MenuManager.sparksAnimation.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
    }
}
