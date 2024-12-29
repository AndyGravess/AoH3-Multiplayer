// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon2_Value_Levels extends Button
{
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public int id;
    public int iLevel;
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    
    public TextIcon2_Value_Levels(final String sText, final int fontID, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int id, final int iLevel) {
        this.init(sText, fontID, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        this.iLevel = iLevel;
        this.id = id;
        final float iconScale = this.getScale();
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    public float getScale() {
        float iconScale = Math.min(1.0f, this.getImageScale(this.imageID));
        if (this.iLevel == 0) {
            iconScale *= 0.5f;
        }
        else if (this.iLevel == 1) {
            iconScale *= 0.75f;
        }
        return iconScale;
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
        oSB.setColor(1.0f, 1.0f, 1.0f, this.isLeveLActive() ? 1.0f : (this.getIsHovered() ? 0.3f : 0.15f));
        ImageManager.getImage(this.getImageID()).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        if (this.getClickable() && this.getIsHovered() && TextIcon2_Value_Levels.animationState >= 0) {
            if (TextIcon2_Value_Levels.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_Value_Levels.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_Value_Levels.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++TextIcon2_Value_Levels.animationState;
                    TextIcon2_Value_Levels.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_Value_Levels.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_Value_Levels.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    TextIcon2_Value_Levels.animationState = 0;
                    TextIcon2_Value_Levels.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    public boolean isLeveLActive() {
        return false;
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
    
    @Override
    public int getCurrent() {
        return this.id;
    }
    
    public final float getImageScale(final int iImageID) {
        return (this.getHeight() - CFG.PADDING * 2) / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        TextIcon2_Value_Levels.lTimeAnimation = CFG.currentTimeMillis;
        TextIcon2_Value_Levels.animationState = 0;
    }
    
    static {
        TextIcon2_Value_Levels.lTimeAnimation = 0L;
        TextIcon2_Value_Levels.animationState = 0;
    }
}
