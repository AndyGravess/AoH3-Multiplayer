// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.province.ProvinceInvest;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_StaticBG_ID_Infrastructure extends Text_StaticBG
{
    public int id;
    public int lastValue;
    public int lastValue2;
    
    public Text_StaticBG_ID_Infrastructure(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.lastValue = -997654;
        this.lastValue2 = -997654;
        this.id = id;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        if (Game.getProvince(this.id).iProvinceDevelopInfrastructureSize > 0) {
            oSB.setColor(new Color(ProvinceDraw.progressBar.r, ProvinceDraw.progressBar.g, ProvinceDraw.progressBar.b, 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(new Color(ProvinceDraw.progressBar));
            Renderer.drawBoxProgress(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, (int)(this.getWidth() * (1.0f - Game.getProvince(this.id).provinceDevelopInfrastructureDaysLeft.get(0).daysLeft / (float)Game.getProvince(this.id).provinceDevelopInfrastructureDaysLeft.get(0).investTime)), this.getHeight(), this.getWidth());
            oSB.setColor(Color.WHITE);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.2f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getProvince(this.id).getInfrastructure() || this.lastValue2 != Game.getProvince(this.id).iInfrastructureMax) {
            this.setText("" + Game.getProvince(this.id).getInfrastructure() + " / " + Game.getProvince(this.id).iInfrastructureMax);
            this.lastValue = Game.getProvince(this.id).getInfrastructure();
            this.lastValue2 = Game.getProvince(this.id).iInfrastructureMax;
        }
        return this.sText;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (Game.getProvince(this.id).getInfrastructure() + Game.getProvince(this.id).iProvinceDevelopInfrastructureSize != Game.getProvince(this.id).iInfrastructureMax) {
            return super.getColor(isActive);
        }
        if (isActive) {
            return Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
        }
        return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
    }
}
