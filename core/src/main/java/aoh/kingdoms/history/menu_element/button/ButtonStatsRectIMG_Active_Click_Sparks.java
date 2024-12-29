// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Renderer.SparksAnimation;

public class ButtonStatsRectIMG_Active_Click_Sparks extends ButtonStatsRectIMG_Active_Click
{
    public SparksAnimation sparksAnimation;
    
    public ButtonStatsRectIMG_Active_Click_Sparks(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth, id);
        this.sparksAnimation = new SparksAnimation();
    }
    
    public ButtonStatsRectIMG_Active_Click_Sparks(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final long scale) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth, id, scale);
        this.sparksAnimation = new SparksAnimation();
    }
    
    public ButtonStatsRectIMG_Active_Click_Sparks(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final boolean bShort) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth, id, bShort);
        this.sparksAnimation = new SparksAnimation();
    }
    
    public ButtonStatsRectIMG_Active_Click_Sparks(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final int fontID) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth, id, fontID);
        this.sparksAnimation = new SparksAnimation();
    }
    
    public ButtonStatsRectIMG_Active_Click_Sparks(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final float fIconScale) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth, id, fIconScale);
        this.sparksAnimation = new SparksAnimation();
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, ButtonStatsRectIMG_Active.getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight());
        oSB.setColor(MainMenu.sparksColors);
        this.sparksAnimation.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && ButtonStatsRectIMG_Active_Click_Sparks.animationState >= 0) {
            if (ButtonStatsRectIMG_Active_Click_Sparks.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Active_Click_Sparks.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Active_Click_Sparks.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonStatsRectIMG_Active_Click_Sparks.animationState;
                    ButtonStatsRectIMG_Active_Click_Sparks.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Active_Click_Sparks.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Active_Click_Sparks.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonStatsRectIMG_Active_Click_Sparks.animationState = 0;
                    ButtonStatsRectIMG_Active_Click_Sparks.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
}
