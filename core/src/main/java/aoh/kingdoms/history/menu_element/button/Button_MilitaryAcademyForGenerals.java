// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.InGame_Generals;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_MilitaryAcademyForGenerals extends Button
{
    public Button_MilitaryAcademyForGenerals(final int iPosX, final int iPosY) {
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.armyGeneralAcademy).getWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.armyGeneralAcademy).getWidth(), ImageManager.getImage(Images.armyGeneralAcademy).getHeight());
        }
        ImageManager.getImage(Images.armyGeneralAcademy).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.armyGeneralAcademy).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = InGame_Generals.getHoverMilitaryAcademyForGenerals();
    }
}
