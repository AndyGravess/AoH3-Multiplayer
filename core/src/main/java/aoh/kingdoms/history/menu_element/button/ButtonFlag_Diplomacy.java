// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menusInGame.InGame_CivBonuses;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class ButtonFlag_Diplomacy extends Button
{
    public int iCivID;
    public boolean drawArrow;
    public boolean drawArrows;
    public boolean arrowFlipX;
    
    public ButtonFlag_Diplomacy(final int iCivID, final int iPosX, final int iPosY, final boolean isClickable) {
        this.drawArrow = false;
        this.drawArrows = false;
        this.arrowFlipX = true;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), ImageManager.getImage(Images.flagDiplomacyOver).getHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.iCivID = iCivID;
    }
    
    public ButtonFlag_Diplomacy(final int iCivID, final int iPosX, final int iPosY, final boolean isClickable, final boolean drawArrows) {
        this.drawArrow = false;
        this.drawArrows = false;
        this.arrowFlipX = true;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), ImageManager.getImage(Images.flagDiplomacyOver).getHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.iCivID = iCivID;
        this.drawArrow = drawArrows;
        this.drawArrows = drawArrows;
    }
    
    public ButtonFlag_Diplomacy(final int iCivID, final int iPosX, final int iPosY, final boolean isClickable, final boolean drawArrow, final boolean arrowFlipX) {
        this.drawArrow = false;
        this.drawArrows = false;
        this.arrowFlipX = true;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), ImageManager.getImage(Images.flagDiplomacyOver).getHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.iCivID = iCivID;
        this.drawArrow = drawArrow;
        this.arrowFlipX = !arrowFlipX;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setShader(Renderer.shaderAlpha);
        if (this.getFlagCivID() >= 0) {
            Game.getCiv(this.getFlagCivID()).getFlag().getTexture().bind(1);
        }
        else if (this.getFlagCivID() < 0) {
            ImageManager.getImage(Images.rebelsFlag).getTexture().bind(1);
        }
        else {
            ImageManager.getImage(Images.randomCivilizationFlag).getTexture().bind(1);
        }
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.flagDiplomacyMask).draw(oSB, this.getPosX() + iTranslateX + (ImageManager.getImage(Images.flagDiplomacyOver).getWidth() - ImageManager.getImage(Images.flagDiplomacyMask).getWidth()) / 2, this.getPosY() + iTranslateY + (ImageManager.getImage(Images.flagDiplomacyOver).getHeight() - ImageManager.getImage(Images.flagDiplomacyMask).getHeight()) / 2, ImageManager.getImage(Images.flagDiplomacyMask).getWidth(), ImageManager.getImage(Images.flagDiplomacyMask).getHeight());
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagDiplomacyOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.drawArrow) {
            if (this.drawArrows) {
                ImageManager.getImage(Images.flagDiplomacyArrow).draw(oSB, this.getPosX() + ImageManager.getImage(Images.flagDiplomacyOver).getWidth() - ImageManager.getImage(Images.flagDiplomacyArrow).getWidth() - CFG.PADDING / 2 + iTranslateX, this.getPosY() + CFG.PADDING / 2 + iTranslateY);
                ImageManager.getImage(Images.flagDiplomacyArrow).draw(oSB, this.getPosX() + ImageManager.getImage(Images.flagDiplomacyOver).getWidth() - ImageManager.getImage(Images.flagDiplomacyArrow).getWidth() - CFG.PADDING / 2 + iTranslateX, this.getPosY() + CFG.PADDING + ImageManager.getImage(Images.flagDiplomacyArrow).getHeight() + iTranslateY, true, false);
            }
            else {
                ImageManager.getImage(Images.flagDiplomacyArrow).draw(oSB, this.getPosX() + ImageManager.getImage(Images.flagDiplomacyOver).getWidth() - ImageManager.getImage(Images.flagDiplomacyArrow).getWidth() - CFG.PADDING / 2 + iTranslateX, this.getPosY() + CFG.PADDING / 2 + iTranslateY, this.arrowFlipX, false);
            }
        }
    }
    
    public int getFlagCivID() {
        return this.iCivID;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (this.getFlagCivID() < 0) {
            nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, 0, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Rebels"), Colors.HOVER_LEFT));
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public int getCurrent() {
        return this.iCivID;
    }
    
    @Override
    public void actionElement() {
        if (this.iCivID >= 0 && this.iCivID != InGame_Civ.iActiveCivID) {
            InGame_Civ.iRebuildToCivID = this.iCivID;
            Game.menuManager.rebuildInGame_Civ();
            InGame_Civ.lTime = 0L;
            if (Game.menuManager.getVisibleInGame_CivBonuses() && InGame_Civ.iActiveCivID != 0 && InGame_CivBonuses.iCivID != InGame_Civ.iActiveCivID) {
                InGame_CivBonuses.iCivID = InGame_Civ.iActiveCivID;
                Game.menuManager.rebuildInGame_CivBonuses();
                Game.menuManager.setVisibleInGame_CivBonuses(true);
                InGame_CivBonuses.lTime = 0L;
            }
        }
        else if (Game.getCiv(this.iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.iCivID).getCapitalProvinceID()).getCivID() == this.iCivID) {
            Game.mapCoords.centerToProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
            Game.setActiveProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
            ProvinceBorderManager.action.setProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
        }
    }
    
    @Override
    public void actionElementPPM() {
        if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() > 0 && this.getCurrent() == Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID()) {
            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
        }
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.iCivID;
        }
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.flagDiplomacyOver).getHeight();
    }
}
