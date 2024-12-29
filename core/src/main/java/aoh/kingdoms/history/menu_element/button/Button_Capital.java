// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Government;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_Capital extends Button
{
    public Button_Capital(final int iPosX, final int iPosY) {
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.buildingCapital.get(Math.min(Game.getCiv(Game.player.iCivID).getCapitalLevel(), GameValues.capital.CAPITAL_IMAGES - 1))).getWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingCapital.get(Math.min(Game.getCiv(Game.player.iCivID).getCapitalLevel(), GameValues.capital.CAPITAL_IMAGES - 1))).getWidth(), ImageManager.getImage(Images.buildingCapital.get(Math.min(Game.getCiv(Game.player.iCivID).getCapitalLevel(), GameValues.capital.CAPITAL_IMAGES - 1))).getHeight());
        }
        ImageManager.getImage(Images.buildingCapital.get(Math.min(Game.getCiv(Game.player.iCivID).getCapitalLevel(), GameValues.capital.CAPITAL_IMAGES - 1))).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.buildingCapital.get(Math.min(Game.getCiv(Game.player.iCivID).getCapitalLevel(), GameValues.capital.CAPITAL_IMAGES - 1))).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = InGame_Court_Government.getHoverCapitalCity();
    }
}
