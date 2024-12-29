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

public class ButtonStatsRectIMG_Active_Value_Red extends Button
{
    public float lastValue;
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public int maxIconWidth;
    public int id;
    
    public ButtonStatsRectIMG_Active_Value_Red(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id) {
        this.lastValue = -997654.75f;
        this.init(sText, CFG.FONT_BOLD_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        this.id = id;
        this.maxIconWidth = maxIconWidth;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    public ButtonStatsRectIMG_Active_Value_Red(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int id, final int fontID) {
        this.lastValue = -997654.75f;
        this.init(sText, fontID, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        this.id = id;
        this.maxIconWidth = maxIconWidth;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG_RED.r, Colors.COLOR_STATS_RECT_BG_RED.g, Colors.COLOR_STATS_RECT_BG_RED.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG_RED_OVER.r, Colors.COLOR_STATS_RECT_BG_RED_OVER.g, Colors.COLOR_STATS_RECT_BG_RED_OVER.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive) / 2.0f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getWidth() - (this.maxIconWidth + CFG.PADDING * 2) + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && ButtonStatsRectIMG_Active_Value_Red.animationState >= 0) {
            if (ButtonStatsRectIMG_Active_Value_Red.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonStatsRectIMG_Active_Value_Red.animationState;
                    ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonStatsRectIMG_Active_Value_Red.animationState = 0;
                    ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.95f : (isHovered ? 0.9f : 0.8f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() - (this.maxIconWidth + CFG.PADDING * 2) / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.iconHeight) / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + (this.getWidth() - (CFG.PADDING * 2 + this.maxIconWidth)) / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return Colors.HOVER_GOLD;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation = CFG.currentTimeMillis;
        ButtonStatsRectIMG_Active_Value_Red.animationState = 0;
    }
    
    static {
        ButtonStatsRectIMG_Active_Value_Red.lTimeAnimation = 0L;
        ButtonStatsRectIMG_Active_Value_Red.animationState = 0;
    }
}
