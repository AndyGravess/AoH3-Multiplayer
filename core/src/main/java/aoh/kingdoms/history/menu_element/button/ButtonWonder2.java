// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.map.WondersManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonWonder2 extends Button
{
    public int wonderID;
    public int iProvinceID;
    
    public ButtonWonder2(final int iPosX, final int iPosY, final int wonderID, final int iProvinceID) {
        this.wonderID = wonderID;
        this.iProvinceID = iProvinceID;
        this.init(null, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        WondersManager.wonderImages.get(WondersManager.wonders.get(this.wonderID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.wonderFrame).getWidth(), ImageManager.getImage(Images.wonderFrame).getHeight());
        ImageManager.getImage(Images.wonderFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setColor(Color.WHITE);
        if (Game.getProvince(this.iProvinceID).wonderConstruction != null) {
            float fUnderConstruction = Game.getProvince(this.iProvinceID).wonderConstruction.daysLeft / (float)Game.getProvince(this.iProvinceID).wonderConstruction.investTime;
            if (fUnderConstruction < 0.0f) {
                fUnderConstruction = 0.0f;
            }
            oSB.setColor(new Color(ProvinceDraw.progressBarBG));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getHeight() / 2 + iTranslateY);
            oSB.setColor(new Color(ProvinceDraw.progressBar));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getHeight() / 2 + iTranslateY, (int)(ImageManager.getImage(Images.progressBarFrameMask).getWidth() * (1.0f - fUnderConstruction)), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.progressBarFrame).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrame).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() + iTranslateY);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.wonderFrame).getHeight();
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.wonderFrame).getWidth();
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = ButtonWonderProvince.getHoverWonder(this.iProvinceID, this.wonderID, false);
    }
}
