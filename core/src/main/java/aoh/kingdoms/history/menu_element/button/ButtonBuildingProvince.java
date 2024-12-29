// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonBuildingProvince extends Button
{
    public int building;
    public int buildingID;
    public boolean underConstruction;
    
    public ButtonBuildingProvince(final int iPosX, final int iPosY, final int building, final int buildingID, final boolean underConstruction) {
        this.init(null, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
        this.building = building;
        this.buildingID = buildingID;
        this.underConstruction = underConstruction;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        try {
            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingsFrameSmall).getWidth(), ImageManager.getImage(Images.buildingsFrameSmall).getHeight());
        }
        catch (final Exception ex) {}
        ImageManager.getImage(Images.buildingsFrameSmall).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.underConstruction) {
            float fUnderConstruction = Game.getProvince(InGame_ProvinceInfo.iProvinceID).underConstruction(this.building, this.buildingID);
            if (fUnderConstruction < 0.0f) {
                this.underConstruction = false;
                fUnderConstruction = 0.0f;
            }
            oSB.setColor(new Color(ProvinceDraw.progressBarBG));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getHeight() / 2 + iTranslateY);
            oSB.setColor(new Color(ProvinceDraw.progressBar));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2 - ImageManager.getImage(Images.progressBarFrameMask).getHeight() / 2 + iTranslateY, (int)(ImageManager.getImage(Images.progressBarFrameMask).getWidth() * (1.0f - fUnderConstruction)), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.progressBarFrame).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarFrame).getWidth() / 2 + iTranslateX, this.getPosY() - CFG.PADDING + this.getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() + iTranslateY);
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
    public void actionElement() {
        if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() == Game.player.iCivID && Game.getProvince(InGame_ProvinceInfo.iProvinceID).isUnderConstruction(this.building, this.buildingID)) {
            Game.getProvince(InGame_ProvinceInfo.iProvinceID).cancelBuildingConstruction(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID(), this.building, this.buildingID);
            Game.menuManager.rebuildInGame_ProvinceInfo(false);
        }
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = getHoverBuilding(this.building, this.buildingID, this.underConstruction, false);
    }
    
    public static MenuElement_Hover getHoverBuilding(final int building, final int buildingID, final boolean underConstruction, final boolean addBuild) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() == Game.player.iCivID && Game.getProvince(InGame_ProvinceInfo.iProvinceID).isUnderConstruction(building, buildingID)) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToCancel"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_TextTitle(BuildingsManager.buildings.get(building).Name[buildingID], CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (underConstruction) {
            final int constructionID = Game.getProvince(InGame_ProvinceInfo.iProvinceID).getConstructionID(building, buildingID);
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionWillBeCompleted") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildingsConstruction(constructionID).getConstructionTimeLeft()), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MonthlyIncome != null && BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", ((BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).MonthlyIncome[buildingID], 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).TaxEfficiency != null && BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", "" + ((BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).TaxEfficiency[buildingID], 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).LocalTaxEfficiency != null && BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LocalTaxEfficiency") + ": ", "" + ((BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID], 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MaxInfrastructure != null && BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumInfrastructureLevel") + ": ", "" + ((BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID] > 0) ? "+" : "") + BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID], Images.infrastructure, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).BuildingSlots != null && BuildingsManager.buildings.get(building).BuildingSlots[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlots") + ": ", "" + ((BuildingsManager.buildings.get(building).BuildingSlots[buildingID] > 0) ? "+" : "") + BuildingsManager.buildings.get(building).BuildingSlots[buildingID], Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncomeProduction != null && BuildingsManager.buildings.get(building).IncomeProduction[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeProduction") + ": ", "" + ((BuildingsManager.buildings.get(building).IncomeProduction[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncomeProduction[buildingID], 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ProductionEfficiency != null && BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", "" + ((BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID], 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ProvinceMaintenance != null && BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", "" + ((BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID], 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).Economy != null && BuildingsManager.buildings.get(building).Economy[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + ((BuildingsManager.buildings.get(building).Economy[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).Economy[buildingID], 100) + "", Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ConstructionCost != null && BuildingsManager.buildings.get(building).ConstructionCost[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", "" + ((BuildingsManager.buildings.get(building).ConstructionCost[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)(BuildingsManager.buildings.get(building).ConstructionCost[buildingID] * 100), 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ConstructionTimeBonus != null && BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionTime") + ": ", "" + ((BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)(BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] * 100), 100) + "%", Images.buildTime, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).LocalGrowthRate != null && BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LocalGrowthRate") + ": ", "" + ((BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID], 100) + "%", Images.populationGrowth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).InvestInEconomyCost != null && BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", "" + ((BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncreaseGrowthRateCost != null && BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseGrowthRateCost") + ": ", "" + ((BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] * 100.0f, 100) + "%", Images.populationUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).DevelopInfrastructureCost != null && BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", "" + ((BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] * 100.0f, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncreaseManpowerCost != null && BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", "" + ((BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost != null && BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", "" + ((BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] * 100.0f, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ResearchPoints != null && BuildingsManager.buildings.get(building).ResearchPoints[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", "" + ((BuildingsManager.buildings.get(building).ResearchPoints[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ResearchPoints[buildingID], 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MonthlyLegacy != null && BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", "" + ((BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID], 100) + "", Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MaximumManpower != null && BuildingsManager.buildings.get(building).MaximumManpower[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + ((BuildingsManager.buildings.get(building).MaximumManpower[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).MaximumManpower[buildingID], 1) + "", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).LocalManpower != null && BuildingsManager.buildings.get(building).LocalManpower[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LocalManpower") + ": ", "" + ((BuildingsManager.buildings.get(building).LocalManpower[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalManpower[buildingID] * 100.0f, 1) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).RecruitArmyCostInProvince != null && BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyRecruitmentCostInProvince") + ": ", "" + ((BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID], 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).DefenseBonus != null && BuildingsManager.buildings.get(building).DefenseBonus[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefenseBonus") + ": ", "" + ((BuildingsManager.buildings.get(building).DefenseBonus[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).DefenseBonus[buildingID], 100), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).FortLevel != null && BuildingsManager.buildings.get(building).FortLevel[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefenseLevel") + ": ", "" + ((BuildingsManager.buildings.get(building).FortLevel[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).FortLevel[buildingID], 100), Images.fort, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).FortDefense != null && BuildingsManager.buildings.get(building).FortDefense[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceDefense") + ": ", "" + ((BuildingsManager.buildings.get(building).FortDefense[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).FortDefense[buildingID], 1), Game_Calendar.IMG_FORT_DEFENSE, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ArmyMovementSpeed != null && BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMovementSpeed") + ": ", "" + ((BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] * 100.0f, 100) + "%", Images.movementSpeed, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks != null && BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CasualtiesFromNuclearAttacks") + ": ", "" + ((BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] * 100.0f, 100) + "%", Images.nuke, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).DiseaseDeathRate != null && BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiseasesDeathRate") + ": ", "" + ((BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] * 100.0f, 100) + "%", Images.disease, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MaintenanceCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaintenanceCost") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(BuildingsManager.buildings.get(building).MaintenanceCost[buildingID], 1000)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (addBuild) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConstructNewBuilding") + ": ", CFG.FONT_BOLD_SMALL));
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.build, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
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
}
