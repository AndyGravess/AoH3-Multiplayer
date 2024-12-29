// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonArmy extends Button
{
    public String sTextName;
    public int iTextNameWidth;
    public int iCivID;
    public int numOfRegiments;
    public int iUnitTypeID;
    public int iArmyID;
    public int iAttackWidth;
    public int iDefenseWidth;
    public int iID;
    public int iProvinceID;
    public float fPerc;
    
    public ButtonArmy(final String nArmy, final int numOfRegiments, final int iCivID, final int iPosX, final int iPosY, final int niUnitTypeID, final int nArmyID, final int iID, final int iProvinceID) {
        this.iID = 0;
        this.init(CFG.getNumberWithSpaces(nArmy), CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
        this.iUnitTypeID = niUnitTypeID;
        this.iArmyID = nArmyID;
        this.numOfRegiments = numOfRegiments;
        this.iCivID = iCivID;
        this.iProvinceID = iProvinceID;
        this.iID = iID;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getAttack(iCivID));
        this.iAttackWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getDefense(iCivID));
        this.iDefenseWidth = (int)Renderer.glyphLayout.width;
        this.sTextName = ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).Name;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTextName);
        this.iTextNameWidth = (int)Renderer.glyphLayout.width;
        int tWMax = 0;
        while (this.iTextNameWidth > this.getWidth() && this.sTextName.length() > 5 && ++tWMax < 100) {
            this.sTextName = this.sTextName.substring(0, Math.max(1, this.sTextName.length() - 3)) + "..";
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTextName);
            this.iTextNameWidth = (int)Renderer.glyphLayout.width;
        }
    }
    
    public ButtonArmy(final String nArmy, final int numOfRegiments, final int iCivID, final int iPosX, final int iPosY, final int niUnitTypeID, final int nArmyID, final int iID, final int iProvinceID, final float fPerc) {
        this.iID = 0;
        this.init(CFG.getNumberWithSpaces(nArmy), CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
        this.iUnitTypeID = niUnitTypeID;
        this.iArmyID = nArmyID;
        this.numOfRegiments = numOfRegiments;
        this.iCivID = iCivID;
        this.iProvinceID = iProvinceID;
        this.iID = iID;
        this.fPerc = Math.min(1.0f, fPerc);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getAttack(iCivID));
        this.iAttackWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getDefense(iCivID));
        this.iDefenseWidth = (int)Renderer.glyphLayout.width;
        this.sTextName = ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).Name;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTextName);
        this.iTextNameWidth = (int)Renderer.glyphLayout.width;
        int tWMax = 0;
        while (this.iTextNameWidth > this.getWidth() && this.sTextName.length() > 5 && ++tWMax < 100) {
            this.sTextName = this.sTextName.substring(0, Math.max(1, this.sTextName.length() - 3)) + "..";
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTextName);
            this.iTextNameWidth = (int)Renderer.glyphLayout.width;
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.unitsFrameSmall).getWidth(), ImageManager.getImage(Images.unitsFrameSmall).getHeight());
        }
        ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.unitsFrameSmall).getWidth(), ImageManager.getImage(Images.unitsFrameSmall).getHeight());
        if (this.iCivID == Game.player.iCivID && (this.getIsHovered() || isActive)) {
            ImageManager.getImage(Images.unitsPlus).draw(oSB, this.getPosX() + ImageManager.getImage(Images.unitsFrameSmall).getWidth() / 2 - ImageManager.getImage(Images.unitsPlus).getWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() / 2 - ImageManager.getImage(Images.unitsPlus).getHeight() / 2 + iTranslateY);
        }
        ImageManager.getImage(Images.unitsFrameSmall).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (isActive) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.unitsFrameSmall).getWidth(), ImageManager.getImage(Images.unitsFrameSmall).getHeight(), Colors.COLOR_BOX_ACTIVE);
        }
        else if (this.getIsHovered()) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.unitsFrameSmall).getWidth(), ImageManager.getImage(Images.unitsFrameSmall).getHeight(), Colors.COLOR_BOX_HOVER);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() + iTranslateY, this.getWidth(), getStatsHeight());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() + iTranslateY, this.getWidth(), getStatsHeight(), false, true);
        if (this.fPerc >= 0.0f) {
            if (this.getIsHovered() || isActive) {
                oSB.setColor(new Color(Colors.COLOR_BOX_GOLD.r, Colors.COLOR_BOX_GOLD.g, Colors.COLOR_BOX_GOLD.b, 1.0f));
            }
            else {
                oSB.setColor(new Color(Colors.COLOR_BOX_GOLD.r, Colors.COLOR_BOX_GOLD.g, Colors.COLOR_BOX_GOLD.b, 0.8f));
            }
            Images.pix.draw(oSB, this.getPosX() + iTranslateX + 1, this.getPosY() + iTranslateY + ImageManager.getImage(Images.unitsFrameSmall).getHeight(), (int)((this.getWidth() - 2) * this.fPerc), CFG.PADDING / 2);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX + 1, this.getPosY() + iTranslateY + ImageManager.getImage(Images.unitsFrameSmall).getHeight(), this.getWidth() - 2, CFG.PADDING / 2);
        }
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() + iTranslateY, 1, getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() + iTranslateY, 1, getStatsHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() - 2 - CFG.PADDING * 2 - CFG.TEXT_HEIGHT_SMALL + iTranslateY, this.iAttackWidth + CFG.PADDING * 2, CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL);
        Images.gradientFull.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iDefenseWidth + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() - 2 - CFG.PADDING * 2 - CFG.TEXT_HEIGHT_SMALL + iTranslateY, this.iDefenseWidth + CFG.PADDING * 2, CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sTextName, this.getPosX() + this.getWidth() / 2 - this.iTextNameWidth / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() + CFG.PADDING + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() + CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getAttack(this.iCivID), this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() - 2 - CFG.TEXT_HEIGHT_SMALL - CFG.PADDING + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getDefense(this.iCivID), this.getPosX() + this.getWidth() - CFG.PADDING - this.iDefenseWidth + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameSmall).getHeight() - 2 - CFG.TEXT_HEIGHT_SMALL - CFG.PADDING + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
    }
    
    @Override
    public int getCurrent() {
        return this.iID;
    }
    
    public static int getStatsHeight() {
        return CFG.TEXT_HEIGHT_SMALL * 2 + CFG.PADDING * 3;
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.unitsFrameSmall).getHeight() + getStatsHeight();
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.unitsFrameSmall).getWidth();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(this.sTextName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Attack") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getAttack(this.iCivID), Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Defense") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getDefense(this.iCivID), Images.defense, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MovementSpeed") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).MovementSpeed, Images.movementSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AttackRange") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).AttackRange, Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SiegeAbility") + ": ", "" + CFG.getPrecision2(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).SiegeProgress, 100), Images.siege, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattlePosition") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ((ArmyManager.lUnitsTypes.get(this.iUnitTypeID).Line == 0 || ArmyManager.lUnitsTypes.get(this.iUnitTypeID).Line == 1) ? Game.lang.get("FirstLine") : ((ArmyManager.lUnitsTypes.get(this.iUnitTypeID).Line == 2) ? Game.lang.get("Support") : Game.lang.get("ThirdLine"))), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Manpower") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + this.getText(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        if (DiplomacyManager.isAlly(Game.player.iCivID, this.iCivID)) {
            nData.add(new MenuElement_HoverElement_Type_Text("/" + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * this.numOfRegiments, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        }
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (DiplomacyManager.isAlly(Game.player.iCivID, this.iCivID)) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaintenanceCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XPerMonth", "" + CFG.getPrecision2(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).MaintenanceCost * this.numOfRegiments, 100)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.iProvinceID >= 0 && this.iCivID == Game.player.iCivID) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (!ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).isSettler) {
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RecruitAndAssignToTheArmy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
                return;
            }
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("EstablishSettlement"), CFG.FONT_BOLD, (Game.getProvince(this.iProvinceID).getCivID() == 0) ? Colors.HOVER_GOLD : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceMustBeUncolonized"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ThePopulationOfTheProvinceWillBeEqualToTheNumberOfSettlers"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumNumberOfSettlers") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * GameValues.colonization.COLONIZATION_MAX_SETTLERS), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
