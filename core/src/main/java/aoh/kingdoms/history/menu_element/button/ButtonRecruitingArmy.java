// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.map.army.ArmyManager;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyRecruit;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonRecruitingArmy extends Button
{
    public int unitTypeID;
    public int armyID;
    public int iCivID;
    
    public ButtonRecruitingArmy(final int iPosX, final int iPosY, final int unitTypeID, final int armyID, final int iCivID) {
        this.unitTypeID = unitTypeID;
        this.armyID = armyID;
        this.iCivID = iCivID;
        this.init(null, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        try {
            final int tRecruitArmyID = Game.getCiv(this.iCivID).getRecruitArmyInProvinceID(InGame_ProvinceInfo.iProvinceID);
            float fProgress = 0.0f;
            if (tRecruitArmyID >= 0) {
                this.unitTypeID = Game.getCiv(this.iCivID).lArmyRecruit.get(tRecruitArmyID).get(0).unitID;
                this.armyID = Game.getCiv(this.iCivID).lArmyRecruit.get(tRecruitArmyID).get(0).armyID;
                fProgress = Game.getCiv(this.iCivID).lArmyRecruit.get(tRecruitArmyID).get(0).timeLeft / (float)ArmyManager.lArmy.get(Game.getCiv(this.iCivID).lArmyRecruit.get(tRecruitArmyID).get(0).unitID).get(Game.getCiv(this.iCivID).lArmyRecruit.get(tRecruitArmyID).get(0).armyID).RecruitmentTime;
            }
            else {
                Game.addSimpleTask(new Game.SimpleTask("rebuildProvinceInfo") {
                    @Override
                    public void update() {
                        if (Game.menuManager.getVisibleInGame_ProvinceInfo()) {
                            Game.menuManager.rebuildInGame_ProvinceInfo(false);
                        }
                    }
                });
            }
            ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingsFrameSmall).getWidth(), ImageManager.getImage(Images.buildingsFrameSmall).getHeight());
            ImageManager.getImage(Images.buildingsFrameSmall).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
            oSB.setColor(new Color(ProvinceDraw.progressBarBG));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getHeight() / 2 + iTranslateY);
            oSB.setColor(new Color(ProvinceDraw.progressBar));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getHeight() / 2 + iTranslateY, (int)(ImageManager.getImage(Images.progressBarFrameMask).getWidth() * (1.0f - fProgress)), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.progressBarFrame).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrame).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() + iTranslateY);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.buildingsFrameSmall).getHeight();
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.buildingsFrameSmall).getWidth();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        final int tRecruitArmyID = Game.getCiv(this.iCivID).getRecruitArmyInProvinceID(InGame_ProvinceInfo.iProvinceID);
        if (this.iCivID == Game.player.iCivID) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToCancel"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TheArmyWillBeDeployedToThisLocation") + ": "));
        nData.add(new MenuElement_HoverElement_Type_Text(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + Game.getCiv(this.iCivID).lArmyRecruit.get(tRecruitArmyID).get(0).timeLeft), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements) {
            @Override
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }
            
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        };
    }
    
    @Override
    public void actionElement() {
        if (this.iCivID == Game.player.iCivID) {
            Game.getCiv(Game.player.iCivID).cancelRecruitArmy(InGame_ProvinceInfo.iProvinceID);
        }
    }
    
    @Override
    public int getSFX() {
        return (this.iCivID == Game.player.iCivID) ? SoundsManager.SOUND_RECRUIT_CANCEL : super.getSFX();
    }
}
