// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTemporaryLoad;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.map.WondersManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonWonderProvince extends Button
{
    public static long lTimeAnimation;
    public static int animationState;
    public static final int ANIMATION_T = 1000;
    public int wonderID;
    public int iProvinceID;
    private static final Color colorLine;
    
    public ButtonWonderProvince(final int iPosX, final int iPosY, final int wonderID, final int iProvinceID) {
        this.wonderID = wonderID;
        this.iProvinceID = iProvinceID;
        this.init(null, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
    }
    
    public void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
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
        if (ButtonWonderProvince.animationState >= 0) {
            if (ButtonWonderProvince.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonWonderProvince.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonWonderProvince.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonWonderProvince.animationState;
                    ButtonWonderProvince.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonWonderProvince.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonWonderProvince.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonWonderProvince.animationState = 0;
                    ButtonWonderProvince.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    public void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.wonderFrame).getHeight();
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.wonderFrame).getWidth();
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = getHoverWonder(this.iProvinceID, this.wonderID, true);
    }
    
    public static MenuElement_Hover getHoverWonder(final int iProvinceID, final int wonderID, final boolean inProvinceInfo) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Wonder") + ": ", CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(WondersManager.wonders.get(wonderID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "wondersImagesBig/" + CFG.getRescouresPath_Short() + WondersManager.wonders.get(wonderID).ImageID + ".png"));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Province") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(iProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(iProvinceID).getWonderBuilt()) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WonderConstructed") + ": ", CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (Game.getProvince(iProvinceID).wonderConstruction != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionWillBeCompleted") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + Game.getProvince(iProvinceID).wonderConstruction.daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MonthlyIncome != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyIncome") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).MonthlyIncome, 100) + "", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).LocalTaxEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LocalTaxEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).LocalTaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).LocalTaxEfficiency, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).TaxEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TaxEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).TaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).TaxEfficiency, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ProductionEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ProductionEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ProductionEfficiency, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ProvinceMaintenance != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceMaintenance") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ProvinceMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ProvinceMaintenance, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).BuildingsMaintenanceCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingsMaintenanceCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).BuildingsMaintenanceCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).BuildingsMaintenanceCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.buildings, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).Economy != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).Economy > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).Economy, 100) + "", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).GrowthRate != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).GrowthRate > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).GrowthRate, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).Research != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Research") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).Research > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).Research, 100) + "", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ResearchPoints != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ResearchPerMonth") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ResearchPoints > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ResearchPoints, 100) + "", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).TechnologyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TechnologyCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).TechnologyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).TechnologyCost, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MonthlyLegacy != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyLegacy") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MonthlyLegacy > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).MonthlyLegacy, 100) + "", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ArmyMaintenance != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyMaintenance") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ArmyMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ArmyMaintenance, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.armyMaintenance, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MaxManpower != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumManpower") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MaxManpower > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).MaxManpower, 100) + "", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_UP, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ManpowerRecoverySpeed != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManpowerRecoverySpeed") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ManpowerRecoverySpeed > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ManpowerRecoverySpeed * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).RecruitmentTime != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RecruitmentTime") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).RecruitmentTime > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).RecruitmentTime, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).RecruitArmyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyRecruitmentCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).RecruitArmyCost > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).RecruitArmyCost, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).RecruitArmyFirstLineCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("FirstLineArmyRecruitmentCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).RecruitArmyFirstLineCost > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).RecruitArmyFirstLineCost, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).RecruitArmySecondLineCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SecondLineArmyRecruitmentCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).RecruitArmySecondLineCost > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).RecruitArmySecondLineCost, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ConstructionCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ConstructionCost > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ConstructionCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).AdministrationBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdministrationBuildingsCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).AdministrationBuildingsCost > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).AdministrationBuildingsCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).EconomyBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("EconomyBuildingsCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).EconomyBuildingsCost > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).EconomyBuildingsCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MilitaryBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryBuildingsCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MilitaryBuildingsCost > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).MilitaryBuildingsCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ConstructionTimeBonus != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionTime") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ConstructionTimeBonus > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ConstructionTimeBonus * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.buildTime, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MaxInfrastructure != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumInfrastructureLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MaxInfrastructure > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).MaxInfrastructure, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.infrastructure, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).AdvisorMaxLevel != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAdvisorSkillLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).AdvisorMaxLevel > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).AdvisorMaxLevel, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).AdvisorPoolSize != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorPool") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).AdvisorPoolSize > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).AdvisorPoolSize, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.council, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).BuildingSlot != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlot") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).BuildingSlot > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).BuildingSlot, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).InvestInEconomyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InvestInEconomyCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).InvestInEconomyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).InvestInEconomyCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY_UP, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).IncreaseTaxEfficiencyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).IncreaseTaxEfficiencyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.taxUp, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).IncreaseGrowthRateCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseGrowthRateCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).IncreaseGrowthRateCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).IncreaseGrowthRateCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).DevelopInfrastructureCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DevelopInfrastructureCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).DevelopInfrastructureCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).DevelopInfrastructureCost * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.infrastructureUp, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).IncreaseManpowerCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseManpowerCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).IncreaseManpowerCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).IncreaseManpowerCost, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_UP, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GeneralsAttack") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).GeneralAttack > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).GeneralAttack, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.attack, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GeneralsDefense") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).GeneralDefense > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).GeneralDefense, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.defense, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnitsAttack") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).UnitsAttack > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).UnitsAttack, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.attack, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnitsDefense") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).UnitsDefense > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).UnitsDefense, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.defense, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MaxMorale != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaxMorale") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MaxMorale > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).MaxMorale * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.morale, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ArmyMovementSpeed != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyMovementSpeed") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ArmyMovementSpeed > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ArmyMovementSpeed, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.movementSpeed, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).SiegeEffectiveness != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SiegeEffectiveness") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).SiegeEffectiveness > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).SiegeEffectiveness * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.siege, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ImproveRelationsModifier != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ImproveRelationsModifier") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ImproveRelationsModifier > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ImproveRelationsModifier, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.relations, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).IncomeFromVassals != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncomeFromVassals") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).IncomeFromVassals > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).IncomeFromVassals * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MaxNumberOfLoans != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MaxNumberOfLoans > 0) ? "+" : "") + WondersManager.wonders.get(wonderID).MaxNumberOfLoans, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.loan, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).LoanInterest != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LoanInterest") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).LoanInterest > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).LoanInterest, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.loan, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).DiplomacyPoints != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiplomacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).DiplomacyPoints > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).DiplomacyPoints * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).AggressiveExpansion != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AggressiveExpansion") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).AggressiveExpansion > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).AggressiveExpansion, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.war, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).RevolutionaryRisk != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RevolutionaryRisk") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).RevolutionaryRisk > 0.0f) ? "-" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).RevolutionaryRisk, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.revolutionRisk, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).CoreCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CoreConstruction") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).CoreCost > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).CoreCost, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.core, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).AllCharactersLifeExpectancy != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AllCharactersLifeExpectancy") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).AllCharactersLifeExpectancy > 0) ? "+" : "") + Game.lang.get("YearsX", WondersManager.wonders.get(wonderID).AllCharactersLifeExpectancy), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.council, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).DefenseBonus != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Defense") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).DefenseBonus > 0) ? "+" : "") + CFG.getPrecision2((float)WondersManager.wonders.get(wonderID).DefenseBonus, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.defense, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).FortLevel != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DefenseLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).FortLevel > 0) ? "+" : "") + CFG.getPrecision2((float)WondersManager.wonders.get(wonderID).FortLevel, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.fort, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).FortDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceDefense") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).FortDefense > 0) ? "+" : "") + CFG.getPrecision2((float)WondersManager.wonders.get(wonderID).FortDefense, 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_FORT_DEFENSE, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).DiseaseDeathRate != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiseasesDeathRate") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).DiseaseDeathRate > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).DiseaseDeathRate * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.disease, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).MaximumAmountOfGold != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAmountOfGold") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).MaximumAmountOfGold > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).MaximumAmountOfGold * 100.0f, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).Discipline != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Discipline") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).Discipline > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).Discipline * 100.0f, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.discipline, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).RegimentsLimit != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).RegimentsLimit > 0) ? "+" : "") + CFG.getPrecision2((float)WondersManager.wonders.get(wonderID).RegimentsLimit, 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.regimentsLimit, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).BattleWidth != 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattleWidth") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).BattleWidth > 0) ? "+" : "") + CFG.getPrecision2((float)WondersManager.wonders.get(wonderID).BattleWidth, 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.battleWidth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).ManpowerRecoveryFromADisbandedArmy != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ((WondersManager.wonders.get(wonderID).ManpowerRecoveryFromADisbandedArmy > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).ManpowerRecoveryFromADisbandedArmy * 100.0f, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_DISBAND, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (WondersManager.wonders.get(wonderID).Legacy != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Legacy") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(((WondersManager.wonders.get(wonderID).Legacy > 0.0f) ? "+" : "") + CFG.getPrecision2(WondersManager.wonders.get(wonderID).Legacy, 10), CFG.FONT_BOLD_SMALL, (WondersManager.wonders.get(wonderID).Legacy > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : ((WondersManager.wonders.get(wonderID).Legacy < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.HOVER_RIGHT)));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(WondersManager.getWonderConstructionCost(iProvinceID, wonderID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionTime") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", WondersManager.wonders.get(wonderID).ConstructionTime), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.buildTime, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (WondersManager.wonders.get(wonderID).MaintenanceCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaintenanceCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XPerMonth", "" + CFG.getPrecision2(WondersManager.wonders.get(wonderID).MaintenanceCost, 100)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (inProvinceInfo) {
            return new MenuElement_Hover(nElements) {
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
        return new MenuElement_Hover(nElements);
    }
    
    public static final Color getColorLine() {
        return ButtonWonderProvince.colorLine;
    }
    
    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }
    
    static {
        ButtonWonderProvince.lTimeAnimation = 0L;
        ButtonWonderProvince.animationState = 0;
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
    }
}
