// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menusInGame.InGame_Wonder;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonWonder extends Button
{
    public ButtonWonder(final int iPosX, final int iPosY) {
        this.init("", CFG.FONT_REGULAR, 0, iPosX, iPosY, InGame_Wonder.wonderIMG.getWidth(), InGame_Wonder.wonderIMG.getHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        try {
            InGame_Wonder.wonderIMG.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        catch (final Exception ex) {}
    }
}
