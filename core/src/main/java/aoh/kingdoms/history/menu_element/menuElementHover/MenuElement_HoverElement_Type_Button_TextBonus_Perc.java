// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

public class MenuElement_HoverElement_Type_Button_TextBonus_Perc extends MenuElement_HoverElement_Type_Button_TextBonus
{
    public float perc;
    
    public MenuElement_HoverElement_Type_Button_TextBonus_Perc(final String sText, final String sText2, final int imageID, final int fontID, final Color nColorBonus, final float perc) {
        super(sText, sText2, imageID, fontID, nColorBonus);
        this.perc = Math.max(0.05f, Math.min(1.0f, perc));
    }
    
    public MenuElement_HoverElement_Type_Button_TextBonus_Perc(final String sText, final String sText2, final int imageID, final int fontID, final int fontID2, final Color nColorBonus, final float perc) {
        super(sText, sText2, imageID, fontID, fontID2, nColorBonus);
        this.perc = Math.max(0.05f, Math.min(1.0f, perc));
    }
    
    public MenuElement_HoverElement_Type_Button_TextBonus_Perc(final String sText, final String sText2, final int imageID, final int fontID, final int fontID2, final Color nColorLeft, final Color nColorBonus, final float perc) {
        super(sText, sText2, imageID, fontID, fontID2, nColorLeft, nColorBonus);
        this.perc = Math.max(0.05f, Math.min(1.0f, perc));
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
        oSB.setColor(new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, 0.25f));
        Renderer.drawBoxProgress(oSB, Images.statsRectBG, nPosX, nPosY, (int)(iMaxWidth * this.perc), this.getHeight2(), (int)(iMaxWidth * this.perc));
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ImageManager.getImage(this.imageID).draw(oSB, nPosX + (this.maxIconWidth + CFG.PADDING * 2) / 2 - this.iconWidth / 2, nPosY + (this.getHeight2() - this.iconHeight) / 2, this.iconWidth, this.iconHeight);
        Renderer.drawText(oSB, this.fontID, this.sText, nPosX + this.maxIconWidth + CFG.PADDING * 4, nPosY + (this.getHeight2() - this.iTextHeight) / 2, new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
        Renderer.drawText(oSB, this.fontID2, this.sText2, nPosX + this.maxIconWidth + this.iTextWidth + CFG.PADDING * 4, nPosY + (this.getHeight2() - this.iTextHeight) / 2, new Color(this.oColorBonus.r, this.oColorBonus.g, this.oColorBonus.b, nAlpha));
        oSB.setColor(Color.WHITE);
    }
}
