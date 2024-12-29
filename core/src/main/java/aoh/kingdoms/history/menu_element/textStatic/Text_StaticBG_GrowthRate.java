// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.province.ProvinceInvest;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_StaticBG_GrowthRate extends Text_StaticBG
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int id;
    public float lastValue;
    
    public Text_StaticBG_GrowthRate(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.lastValue = -997654.3f;
        this.id = id;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        if (Game.getProvince(this.id).iProvinceIncreaseGrowthRateSize > 0) {
            oSB.setColor(new Color(ProvinceDraw.progressBar.r, ProvinceDraw.progressBar.g, ProvinceDraw.progressBar.b, 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(new Color(ProvinceDraw.progressBar));
            Renderer.drawBoxProgress(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, (int)(this.getWidth() * (1.0f - Game.getProvince(this.id).provinceIncreaseGrowthRateDaysLeft.get(0).daysLeft / (float)Game.getProvince(this.id).provinceIncreaseGrowthRateDaysLeft.get(0).investTime)), this.getHeight(), this.getWidth());
            oSB.setColor(Color.WHITE);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.2f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(Color.WHITE);
        }
        if (this.getClickable() && this.getIsHovered() && Text_StaticBG_GrowthRate.animationState >= 0) {
            if (Text_StaticBG_GrowthRate.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Text_StaticBG_GrowthRate.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (Text_StaticBG_GrowthRate.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++Text_StaticBG_GrowthRate.animationState;
                    Text_StaticBG_GrowthRate.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Text_StaticBG_GrowthRate.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (Text_StaticBG_GrowthRate.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    Text_StaticBG_GrowthRate.animationState = 0;
                    Text_StaticBG_GrowthRate.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getProvince(this.id).getGrowthRateWithBonuses()) {
            this.setText("" + CFG.getPrecision2(Game.getProvince(this.id).getGrowthRateWithBonuses(), 10) + "%");
            this.lastValue = Game.getProvince(this.id).getGrowthRateWithBonuses();
        }
        return this.sText;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        Text_StaticBG_GrowthRate.lTimeAnimation = CFG.currentTimeMillis;
        Text_StaticBG_GrowthRate.animationState = 0;
    }
    
    static {
        Text_StaticBG_GrowthRate.lTimeAnimation = 0L;
        Text_StaticBG_GrowthRate.animationState = 0;
    }
}
