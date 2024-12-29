// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class Message_CurrentSituation extends Button
{
    public int lastValue;
    
    public Message_CurrentSituation(final int iPosX, final int iPosY) {
        this.lastValue = -997654;
        this.init("", CFG.FONT_REGULAR, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.currentSituationBG).getWidth(), ImageManager.getImage(Images.currentSituationBG).getHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(Images.currentSituationBG).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
            oSB.setShader(Renderer.shaderAlpha);
            Images.gradientXY.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.currentSituationBG).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.iTextWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.TEXT_CURRENT_SITUATION_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.TEXT_CURRENT_SITUATION_HOVER;
        }
        return Colors.TEXT_CURRENT_SITUATION;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.player.currSituation.currentSituationNum) {
            this.setText("" + Game.player.currSituation.currentSituationNum);
            this.lastValue = Game.player.currSituation.currentSituationNum;
        }
        return super.getTextToDraw();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CurrentSituation"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Messages") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.player.currSituation.currentSituationNum, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.player.currSituation.currentSituationNum > 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.player.currSituation.noActiveResearch) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoActiveResearch"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.maxAmountOfGold) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAmountOfGold") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fGold, 1) + " / " + CFG.getPrecision2((float)Game.getMaxAmountOfGold(Game.player.iCivID), 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.missionCanBeUnlocked) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Missions") + ": " + Game.lang.get("Available"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.missions, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.chooseRivals) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ChooseYourRivals"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rivals, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.newLawAvailable) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Law") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("Unlocked"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.law, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.availableAdvantage) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvantagePoints") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).getAdvantagePoints(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.advantages, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.highInflation) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("HighInflation") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getInflation() * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.inflation, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.nonCoreProvinces) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.core, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.differentReligionProvinces) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.availableCivilizationLegacy) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AvailableCivilizationLegacy"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.noAdvisor > 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.promoteAdvisor > 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PromoteAdvisor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.lackOfGeneral) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LackOfGeneral"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.upgradeCapitalCity) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeCapitalCity"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.upgradeSupremeCourt) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeSupremeCourt"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.stability, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.militaryAcademyCanBeUpgraded) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeMilitaryAcademy"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.militaryAcademyForGeneralsCanBeUpgraded) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeMilitaryAcademyForGenerals"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.upgradeNuclearReactor) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeNuclearReactor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.wonderCanBeBuilt) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AWonderCanBeBuilt"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.mapModesWonders, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
