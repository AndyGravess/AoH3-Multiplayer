// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class MenuElement_HoverElement_Type_Button_TextBonus implements MenuElement_HoverElement_Type
{
    public String sText;
    public int iTextWidth;
    public int iTextHeight;
    public String sText2;
    public int iTextWidth2;
    public int iTextHeight2;
    public Color oColor;
    public Color oColorBonus;
    public int fontID;
    public int fontID2;
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public int maxIconWidth;
    
    public MenuElement_HoverElement_Type_Button_TextBonus(final String sText, final String sText2, final int imageID, final int fontID, final Color nColorBonus) {
        this.fontID = 0;
        this.fontID2 = 0;
        this.init(sText, sText2, imageID, fontID, CFG.FONT_BOLD_SMALL, Colors.BUTTON_TEXT_BRIGHT, nColorBonus);
    }
    
    public MenuElement_HoverElement_Type_Button_TextBonus(final String sText, final String sText2, final int imageID, final int fontID, final int fontID2, final Color nColorBonus) {
        this.fontID = 0;
        this.fontID2 = 0;
        this.init(sText, sText2, imageID, fontID, fontID2, Colors.BUTTON_TEXT_BRIGHT, nColorBonus);
    }
    
    public MenuElement_HoverElement_Type_Button_TextBonus(final String sText, final String sText2, final int imageID, final int fontID, final int fontID2, final Color nColorLeft, final Color nColorBonus) {
        this.fontID = 0;
        this.fontID2 = 0;
        this.init(sText, sText2, imageID, fontID, fontID2, nColorLeft, nColorBonus);
    }
    
    public final void init(final String sText, final String sText2, final int imageID, final int nFontID, final int fontID2, final Color oColor, final Color nColorBonus) {
        this.sText = sText;
        this.sText2 = sText2;
        this.imageID = imageID;
        this.oColor = oColor;
        this.oColorBonus = nColorBonus;
        this.fontID = nFontID;
        this.fontID2 = fontID2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        this.iTextHeight = (int)Renderer.glyphLayout.height;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(fontID2), sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
        this.iTextHeight2 = (int)Renderer.glyphLayout.height;
        this.maxIconWidth = ImageManager.getImage(Images.gold).getWidth();
        final float iconScale = this.getImageScale(imageID) * 1.1f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, int nPosY, final float nAlpha, final int iMaxWidth) {
        nPosY += this.getPaddingY();
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f * nAlpha));
        Renderer.drawBox(oSB, Images.statsRectBG, nPosX, nPosY, iMaxWidth, this.getHeight2(), 1.0f * nAlpha);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.35f * nAlpha));
        Renderer.drawBox(oSB, Images.statsRectBG, nPosX, nPosY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight2(), 1.0f * nAlpha);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f * nAlpha));
        Images.gradientFull.draw(oSB, nPosX, nPosY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight2());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f * nAlpha));
        Images.gradientXY.draw(oSB, nPosX, nPosY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight2());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, nPosX, nPosY + this.getHeight2() - 1, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, nPosX, nPosY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f * nAlpha));
        Images.gradientFull.draw(oSB, nPosX, nPosY + this.getHeight2() - 2, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, nPosX, nPosY + 1, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ImageManager.getImage(this.imageID).draw(oSB, nPosX + (this.maxIconWidth + CFG.PADDING * 2) / 2 - this.iconWidth / 2, nPosY + (this.getHeight2() - this.iconHeight) / 2, this.iconWidth, this.iconHeight);
        Renderer.drawText(oSB, this.fontID, this.sText, nPosX + this.maxIconWidth + CFG.PADDING * 4, nPosY + (this.getHeight2() - this.iTextHeight) / 2, new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
        Renderer.drawText(oSB, this.fontID2, this.sText2, nPosX + this.maxIconWidth + this.iTextWidth + CFG.PADDING * 4, nPosY + (this.getHeight2() - this.iTextHeight) / 2, new Color(this.oColorBonus.r, this.oColorBonus.g, this.oColorBonus.b, nAlpha));
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public int getWidth() {
        return this.iTextWidth + this.iTextWidth2 + this.maxIconWidth + CFG.PADDING * 6;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
    }
    
    public int getPaddingY() {
        return CFG.PADDING;
    }
    
    public int getHeight2() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
}
