// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.ResourcesManager;

public class ButtonResource2 extends Button
{
    public int resourceID;
    public int imgWidth;
    public int imgHeight;
    
    public ButtonResource2(final int resourceID, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.resourceID = resourceID;
        this.init(ResourcesManager.getResourceName(resourceID), CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, iWidth, iHeight, true, true, false, false);
        if (resourceID >= 0) {
            if (ImageManager.getImage(Images.population).getHeight() < ResourcesManager.resourceImages.get(resourceID).getHeight()) {
                final float fScale = ImageManager.getImage(Images.population).getHeight() / (float)ResourcesManager.resourceImages.get(resourceID).getHeight();
                this.imgWidth = (int)(ResourcesManager.resourceImages.get(resourceID).getWidth() * fScale);
                this.imgHeight = (int)(ResourcesManager.resourceImages.get(resourceID).getHeight() * fScale);
            }
            else {
                this.imgWidth = ResourcesManager.resourceImages.get(resourceID).getWidth();
                this.imgHeight = ResourcesManager.resourceImages.get(resourceID).getHeight();
            }
        }
        else if (ImageManager.getImage(Images.population).getHeight() < ImageManager.getImage(Images.resourceNone).getHeight()) {
            final float fScale = ImageManager.getImage(Images.population).getHeight() / (float)ImageManager.getImage(Images.resourceNone).getHeight();
            this.imgWidth = (int)(ImageManager.getImage(Images.resourceNone).getWidth() * fScale);
            this.imgHeight = (int)(ImageManager.getImage(Images.resourceNone).getHeight() * fScale);
        }
        else {
            this.imgWidth = ImageManager.getImage(Images.resourceNone).getWidth();
            this.imgHeight = ImageManager.getImage(Images.resourceNone).getHeight();
        }
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - CFG.PADDING && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(TextIcon2.getColor_gradientXY());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() + iTranslateY, this.getWidth(), getTextHeightBG());
        oSB.setColor(TextIcon2.getColor_gradientFull());
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.resourceID < 0) {
            ImageManager.getImage(Images.resourceNone).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.imgWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - getTextHeightBG()) / 2 - this.imgHeight / 2 + iTranslateY, this.imgWidth, this.imgHeight);
        }
        else {
            ResourcesManager.resourceImages.get(this.resourceID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.imgWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - getTextHeightBG()) / 2 - this.imgHeight / 2 + iTranslateY, this.imgWidth, this.imgHeight);
        }
        Renderer.drawText(oSB, this.fontID, this.getText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public static int getTextHeightBG() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    @Override
    public void buildElementHover() {
    }
}
