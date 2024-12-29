// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonStatsRectIMG_Active_Click extends ButtonStatsRectIMG_Active
{
    public int lastValue;
    
    public ButtonStatsRectIMG_Active_Click(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth + CFG.PADDING, id, CFG.FONT_REGULAR_SMALL);
        this.lastValue = -997654;
    }
    
    public ButtonStatsRectIMG_Active_Click(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final long scale) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth + CFG.PADDING, id, CFG.FONT_REGULAR_SMALL);
        this.lastValue = -997654;
        this.iconWidth = ImageManager.getImage(imageID).getWidth();
        this.iconHeight = ImageManager.getImage(imageID).getHeight();
    }
    
    public ButtonStatsRectIMG_Active_Click(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final boolean bShort) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth + CFG.PADDING, id, CFG.FONT_REGULAR_SMALL);
        this.lastValue = -997654;
        int tWMax = 0;
        while (this.iTextWidth >= this.getWidth() - (CFG.PADDING * 2 + maxIconWidth) && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    public ButtonStatsRectIMG_Active_Click(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final int fontID) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth + CFG.PADDING, id, fontID);
        this.lastValue = -997654;
    }
    
    public ButtonStatsRectIMG_Active_Click(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final float fIconScale) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth + CFG.PADDING, id, CFG.FONT_REGULAR_SMALL);
        this.lastValue = -997654;
        this.iconWidth = ImageManager.getImage(imageID).getWidth();
        this.iconHeight = ImageManager.getImage(imageID).getHeight();
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
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && ButtonStatsRectIMG_Active_Click.animationState >= 0) {
            if (ButtonStatsRectIMG_Active_Click.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Active_Click.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Active_Click.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonStatsRectIMG_Active_Click.animationState;
                    ButtonStatsRectIMG_Active_Click.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Active_Click.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Active_Click.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonStatsRectIMG_Active_Click.animationState = 0;
                    ButtonStatsRectIMG_Active_Click.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.getImageID()).draw(oSB, this.getPosX() + (this.maxIconWidth + CFG.PADDING * 2) / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.iconHeight) / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + (this.maxIconWidth + CFG.PADDING * 2) + (this.getWidth() - (CFG.PADDING * 2 + this.maxIconWidth)) / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public int getImageID() {
        return this.imageID;
    }
    
    public void setImageID(final int imageID) {
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
    }
}
