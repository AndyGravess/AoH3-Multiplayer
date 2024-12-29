// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class SpaceHorizontal extends Button
{
    public SpaceHorizontal(final int nPosX, final int nPosY, final int nWidth) {
        this.init("", CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, 1, true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }
}
