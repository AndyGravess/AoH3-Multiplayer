// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextBGBot extends Button
{
    public TextBGBot(final String sText, final int textPos, final int iPosX, final int iPosY, final int nHeight) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, textPos, iPosX, iPosY, 20, nHeight, true, true, false, false);
        this.setWidth(this.getTextWidth() + CFG.PADDING * 4);
    }
    
    public TextBGBot(final String sText, final int textPos, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, textPos, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        int tWMax = 0;
        while (this.iTextWidth > nWidth - CFG.PADDING * 2 && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBoxBOT(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
}
