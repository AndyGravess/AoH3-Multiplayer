// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonGame_Init extends ButtonGame
{
    public static long INIT_TIME;
    public static final float INIT_ANIMATION_TIME = 750.0f;
    
    public ButtonGame_Init(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        super(sText, iPosX, iPosY, nWidth, nHeight);
        ButtonGame_Init.INIT_TIME = CFG.currentTimeMillis;
    }
    
    public ButtonGame_Init(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
        ButtonGame_Init.INIT_TIME = CFG.currentTimeMillis;
    }
    
    public ButtonGame_Init(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final boolean isClickable) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable);
        ButtonGame_Init.INIT_TIME = CFG.currentTimeMillis;
    }
    
    public ButtonGame_Init(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean checkBox) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable, checkBox);
        ButtonGame_Init.INIT_TIME = CFG.currentTimeMillis;
    }
    
    @Override
    public void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        final float fAlpha = 1.0f - (CFG.currentTimeMillis - ButtonGame_Init.INIT_TIME) / 750.0f;
        if (fAlpha > 0.0f) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, fAlpha));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 + iTranslateY, this.getWidth(), this.getHeight() - 4, false, true);
            Images.gradientFull.draw(oSB, this.getPosX() + (int)(this.getWidth() * (1.0f - fAlpha)) / 2 + iTranslateX, this.getPosY() + 2 + iTranslateY, (int)(this.getWidth() * fAlpha), this.getHeight() - 4);
            oSB.setColor(Color.WHITE);
        }
    }
    
    static {
        ButtonGame_Init.INIT_TIME = 0L;
    }
}
