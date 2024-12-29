// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon_VerticalBuildings extends Button
{
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public int lastValue;
    public int lastValue2;
    public Color mainTextColor;
    
    public TextIcon_VerticalBuildings(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.lastValue = -997654;
        this.lastValue2 = -997654;
        this.mainTextColor = Colors.BUTTON_TEXT;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive) / 2.0f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.iconHeight - CFG.PADDING * 2 + iTranslateY, this.getWidth(), this.iconHeight + CFG.PADDING * 2, 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.iconHeight + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadowRotated(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + (this.getHeight() - CFG.PADDING * 2 - this.iconHeight) / 2 + this.getTextWidth() / 2 + iTranslateY, this.getColor(isActive), 90.0f);
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT_SMALL / (float)ImageManager.getImage(iImageID).getWidth();
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit || this.lastValue2 != Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots()) {
            this.setText("" + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit);
            this.lastValue = Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit;
            this.lastValue2 = Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots();
            if (this.lastValue == this.lastValue2) {
                this.mainTextColor = Colors.COLOR_TEXT_NEGATIVE;
            }
            else {
                this.mainTextColor = Colors.BUTTON_TEXT;
            }
        }
        return super.getTextToDraw();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? this.mainTextColor : Colors.BUTTON_TEXT_DISABLED;
    }
}
