// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon2_Rank extends Button
{
    public int imageID;
    public String sRank;
    public int iRankW;
    public int iRankH;
    public static final float rankScale = 0.8f;
    
    public TextIcon2_Rank(final String rank, final String sText, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        this.sRank = rank;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sRank);
        this.iRankW = (int)(Renderer.glyphLayout.width * 0.8f);
        this.iRankH = (int)(Renderer.glyphLayout.height * 0.8f);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static Color getColor_gradientXY() {
        return new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.7f);
    }
    
    public static Color getColor_gradientFull() {
        return new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.45f);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, (this.getIsHovered() || isActive) ? 0.6f : 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(getColor_gradientXY());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH());
        oSB.setColor(getColor_gradientFull());
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        ImageManager.getImage(this.getImageID()).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.getImageID()).getWidth() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - ImageManager.getImage(this.getImageID()).getHeight() / 2 + iTranslateY);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.65f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getWidth() - CFG.PADDING - this.iRankW - 2 + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() - CFG.PADDING - this.iRankH - 2 + iTranslateY, this.iRankW + 4, this.iRankH + 4, 1.0f);
        oSB.setColor(Color.WHITE);
        Renderer.fontMain.get(this.fontID).getData().setScale(0.8f);
        Renderer.drawText(oSB, this.fontID, this.sRank, this.getPosX() + this.getWidth() - CFG.PADDING - this.iRankW + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() - CFG.PADDING - this.iRankH + iTranslateY, Colors.BUTTON_TEXT_BRIGHT);
        Renderer.fontMain.get(this.fontID).getData().setScale(1.0f);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public int getTextH() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
    
    public int getImageID() {
        return this.imageID;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
}
