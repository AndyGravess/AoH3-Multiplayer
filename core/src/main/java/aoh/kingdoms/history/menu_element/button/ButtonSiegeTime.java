// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.SiegeManager;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceSiege;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonSiegeTime extends Button
{
    public int lastValue;
    public String sText2;
    public int iTextWidth2;
    
    public ButtonSiegeTime(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.lastValue = -89594215;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.sText2 = Game.lang.get("MaximumSiegeTime") + ": ";
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
        this.getTextToDraw();
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() / 2 - (this.getTextWidth() + this.iTextWidth2) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (this.getTextWidth() + this.iTextWidth2) / 2 + this.iTextWidth2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, Colors.getColorTopStats3(isActive, this.getIsHovered()));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    @Override
    public String getTextToDraw() {
        if (Game.battleManager.isBattleInProvince(InGame_ProvinceSiege.iProvinceID)) {
            if (this.lastValue != -999888) {
                this.setText(Game.lang.get("ArmyIsFightingInABattle"));
                this.lastValue = -999888;
            }
        }
        else if (SiegeManager.getSiegeDaysLeft(InGame_ProvinceSiege.iProvinceID) != this.lastValue) {
            this.lastValue = SiegeManager.getSiegeDaysLeft(InGame_ProvinceSiege.iProvinceID);
            this.setText("" + Game.lang.get("DaysX", Math.min(99999, this.lastValue)));
        }
        return super.getTextToDraw();
    }
}
