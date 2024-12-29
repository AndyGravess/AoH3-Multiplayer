// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.map.province.ProvinceInvest;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon2_GrowthRate extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int imageID;
    public int iProvinceID;
    public float lastValue;
    
    public TextIcon2_GrowthRate(final int nProvinceID, final String sText, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        this.lastValue = -997654.3f;
        this.iProvinceID = nProvinceID;
        this.imageID = imageID;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
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
        if (Game.getProvince(this.iProvinceID).iProvinceIncreaseGrowthRateSize > 0) {
            oSB.setColor(new Color(ProvinceDraw.progressBar.r, ProvinceDraw.progressBar.g, ProvinceDraw.progressBar.b, 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH(), 1.0f);
        }
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
        try {
            if (Game.getProvince(this.iProvinceID).iProvinceIncreaseGrowthRateSize > 0) {
                oSB.setColor(new Color(ProvinceDraw.progressBar));
                Renderer.drawBoxProgress(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, (int)(this.getWidth() * (1.0f - Game.getProvince(this.iProvinceID).provinceIncreaseGrowthRateDaysLeft.get(0).daysLeft / (float)Game.getProvince(this.iProvinceID).provinceIncreaseGrowthRateDaysLeft.get(0).investTime)), this.getTextH(), this.getWidth());
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (this.getClickable() && this.getIsHovered() && TextIcon2_GrowthRate.animationState >= 0) {
            if (TextIcon2_GrowthRate.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_GrowthRate.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_GrowthRate.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++TextIcon2_GrowthRate.animationState;
                    TextIcon2_GrowthRate.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_GrowthRate.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_GrowthRate.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    TextIcon2_GrowthRate.animationState = 0;
                    TextIcon2_GrowthRate.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        if (this.getIsHovered() && Game.getProvince(this.iProvinceID).getCivID() == Game.player.iCivID) {
            ImageManager.getImage(Images.populationUp).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.populationUp).getWidth() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - ImageManager.getImage(Images.populationUp).getHeight() / 2 + iTranslateY);
        }
        else {
            ImageManager.getImage(this.getImageID()).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.getImageID()).getWidth() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - ImageManager.getImage(this.getImageID()).getHeight() / 2 + iTranslateY);
        }
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getTextHeight() + iTranslateY, this.getColor(isActive));
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
    public String getTextToDraw() {
        if (this.lastValue != Game.getProvince(this.iProvinceID).getGrowthRateWithBonuses()) {
            this.setText("" + CFG.getPrecision2(Game.getProvince(this.iProvinceID).getGrowthRateWithBonuses(), 1) + "%");
            this.lastValue = Game.getProvince(this.iProvinceID).getGrowthRateWithBonuses();
        }
        return super.getTextToDraw();
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        TextIcon2_GrowthRate.lTimeAnimation = CFG.currentTimeMillis;
        TextIcon2_GrowthRate.animationState = 0;
    }
    
    static {
        TextIcon2_GrowthRate.lTimeAnimation = 0L;
        TextIcon2_GrowthRate.animationState = 0;
    }
}
