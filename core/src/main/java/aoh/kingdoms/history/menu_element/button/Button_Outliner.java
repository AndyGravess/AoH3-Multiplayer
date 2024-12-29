// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_Outliner extends Button
{
    public int iCurrent;
    
    public Button_Outliner(final String sText, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int iCurrent) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, -1, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.iCurrent = iCurrent;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, int iTranslateX, int iTranslateY, final boolean isActive) {
        iTranslateX += this.getPosX();
        iTranslateY += this.getPosY();
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.75f));
        Renderer.drawBox(oSB, Images.statsRectBG, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, iTranslateX, this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX - 1, -1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2, 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, iTranslateX, this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.85f));
        Images.gradientFull.draw(oSB, iTranslateX, this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, iTranslateX, this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, iTranslateX, this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats2(isActive, this.getIsHovered());
    }
    
    @Override
    public int getCurrent() {
        return this.iCurrent;
    }
}
