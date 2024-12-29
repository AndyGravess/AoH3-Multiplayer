// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Religion;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusReligion;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Bonuses_Right_Color;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class ReligionManager
{
    private List<Religion> lReligions;
    public List<Image> religionImages;
    private int iReligionsSize;
    public int maxWidth;
    public int maxHeight;
    
    public ReligionManager() {
        this.lReligions = null;
        this.religionImages = new ArrayList<Image>();
        this.iReligionsSize = 0;
        this.maxWidth = 0;
        this.maxHeight = 0;
    }
    
    public final void loadReligions() {
        if (this.lReligions != null) {
            this.lReligions.clear();
        }
        this.lReligions = new ArrayList<Religion>();
        try {
            final FileHandle fileList = FileManager.loadFile("game/Religions.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)Religion.class);
            LoadManager.ConfigJson data = (LoadManager.ConfigJson)json.fromJson((Class)LoadManager.ConfigJson.class, fileContent);
            for (final Object e : data.Data) {
                final Religion tempData = (Religion)e;
                tempData.Name = Game.lang.get(tempData.Name);
                this.lReligions.add(tempData);
            }
            this.iReligionsSize = this.lReligions.size();
            for (int i2 = 0; i2 < this.iReligionsSize; ++i2) {
                this.lReligions.get(i2).Color[0] /= 255.0f;
                this.lReligions.get(i2).Color[1] /= 255.0f;
                this.lReligions.get(i2).Color[2] /= 255.0f;
            }
            data = null;
        }
        catch (final GdxRuntimeException ex) {
            CFG.LOG((Throwable)ex);
        }
        for (int j = 0; j < this.iReligionsSize; ++j) {
            try {
                if (FileManager.loadFile("gfx/religion/" + CFG.getRescouresPath_Short() + this.lReligions.get(j).Icon).exists()) {
                    this.religionImages.add(new Image(ImageManager.loadTexture("gfx/religion/" + CFG.getRescouresPath_Short() + this.lReligions.get(j).Icon)));
                }
                else {
                    this.religionImages.add(new Image(ImageManager.loadTexture("gfx/religion/" + CFG.getRescouresPath_Short_H() + this.lReligions.get(j).Icon)));
                }
            }
            catch (final GdxRuntimeException ex) {
                this.religionImages.add(new Image(ImageManager.loadTexture("gfx/religion/" + CFG.getRescouresPath_Short() + "notfound.png")));
            }
        }
        for (int j = 0; j < this.iReligionsSize; ++j) {
            if (this.religionImages.get(j).getWidth() > this.maxWidth) {
                this.maxWidth = this.religionImages.get(j).getWidth();
            }
            if (this.religionImages.get(j).getHeight() > this.maxHeight) {
                this.maxHeight = this.religionImages.get(j).getHeight();
            }
        }
    }
    
    public final int getReligionsSize() {
        return this.iReligionsSize;
    }
    
    public final Religion getReligion(final int i) {
        return this.lReligions.get(i);
    }
    
    public final int getReligionConversionCost(final int iProvinceID) {
        return Math.max(1, (int)((GameValues.religion.DEFAULT_CONVERSION_COST + Game.getProvince(iProvinceID).getGrowthRateWithBonuses() * GameValues.religion.CONVERSION_COST_PER_GROWTH_RATE) * (1.0f + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getInflation() + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getWarWeariness() * GameValues.warWeariness.WW_CONVERSION_COST_PER_POINT + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).civBonuses.ReligionCost / 100.0f) * ((Game.religionManager.getReligion(Game.getProvince(iProvinceID).getReligion()).ReligionGroupID != Game.religionManager.getReligion(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()).ReligionGroupID) ? GameValues.religion.CONVERSION_COST_DIFFERENT_RELIGION_GROUP : 1.0f)));
    }
    
    public final int getReligionConversionTime(final int iProvinceID) {
        return (int)(GameValues.religion.DEFAULT_CONVERSION_TIME + Math.min(GameValues.religion.DEFAULT_CONVERSION_TIME_POPULATION_MIN, Game.getProvince(iProvinceID).getPopulationTotal() / GameValues.religion.DEFAULT_CONVERSION_TIME_POPULATION) * ((Game.religionManager.getReligion(Game.getProvince(iProvinceID).getReligion()).ReligionGroupID != Game.religionManager.getReligion(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()).ReligionGroupID) ? GameValues.religion.CONVERSION_TIME_DIFFERENT_RELIGION_GROUP : 1.0f));
    }
    
    public final void updateCivBonuses(final int iCivID, final int religionID, final int mod, final boolean initMode) {
        final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
        civBonuses.MonthlyIncome += this.getReligion(religionID).MonthlyIncome * mod;
        final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
        civBonuses2.TaxEfficiency += this.getReligion(religionID).TaxEfficiency * mod;
        final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
        civBonuses3.ProductionEfficiency += this.getReligion(religionID).ProductionEfficiency * mod;
        final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
        civBonuses4.ProvinceMaintenance += this.getReligion(religionID).ProvinceMaintenance * mod;
        if (this.getReligion(religionID).MonthlyLegacy != 0.0f) {
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
        civBonuses5.MonthlyLegacy += this.getReligion(religionID).MonthlyLegacy * mod;
        if (this.getReligion(religionID).MaxManpower != 0.0f) {
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
        civBonuses6.MaxManpower += this.getReligion(religionID).MaxManpower * mod;
        if (this.getReligion(religionID).ArmyMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
            civBonuses7.ArmyMaintenance += this.getReligion(religionID).ArmyMaintenance * mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }
        final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
        civBonuses8.RecruitmentTime += this.getReligion(religionID).RecruitmentTime * mod;
        final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
        civBonuses9.RecruitArmyCost += this.getReligion(religionID).RecruitArmyCost * mod;
        final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
        civBonuses10.ConstructionCost += this.getReligion(religionID).ConstructionCost * mod;
        final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
        civBonuses11.AdministrationBuildingsCost += this.getReligion(religionID).AdministrationBuildingsCost * mod;
        final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
        civBonuses12.EconomyBuildingsCost += this.getReligion(religionID).EconomyBuildingsCost * mod;
        final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
        civBonuses13.MilitaryBuildingsCost += this.getReligion(religionID).MilitaryBuildingsCost * mod;
        final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
        civBonuses14.ConstructionTime += this.getReligion(religionID).ConstructionTime * mod;
        final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
        civBonuses15.InvestInEconomyCost += this.getReligion(religionID).InvestInEconomyCost * mod;
        final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
        civBonuses16.IncreaseTaxEfficiencyCost += this.getReligion(religionID).IncreaseTaxEfficiencyCost * mod;
        final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
        civBonuses17.DevelopInfrastructureCost += this.getReligion(religionID).DevelopInfrastructureCost * mod;
        final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
        civBonuses18.IncreaseManpowerCost += this.getReligion(religionID).IncreaseManpowerCost * mod;
        final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
        civBonuses19.GeneralAttack += this.getReligion(religionID).GeneralAttack * mod;
        final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
        civBonuses20.GeneralDefense += this.getReligion(religionID).GeneralDefense * mod;
        final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
        civBonuses21.UnitsAttack += this.getReligion(religionID).UnitsAttack * mod;
        final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
        civBonuses22.UnitsDefense += this.getReligion(religionID).UnitsDefense * mod;
        final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
        civBonuses23.MaxNumberOfLoans += this.getReligion(religionID).MaxNumberOfLoans * mod;
        final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
        civBonuses24.BuildingSlot += this.getReligion(religionID).BuildingSlot * mod;
        final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
        civBonuses25.AdvisorCost += this.getReligion(religionID).AdvisorCost * mod;
        final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
        civBonuses26.GeneralCost += this.getReligion(religionID).GeneralCost * mod;
        final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
        civBonuses27.ReligionCost += this.getReligion(religionID).ReligionCost * mod;
        final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
        civBonuses28.CoreCost += this.getReligion(religionID).CoreCost * mod;
        if (!initMode) {
            Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
        }
    }
    
    public final List<MenuElement> getMenuElements(final int religionID, final int iX, int iY, final int iW, final int iH) {
        final ArrayList<ButtonStatsRectIMG_Bonuses_Right_Color> mElementsToSort = new ArrayList<ButtonStatsRectIMG_Bonuses_Right_Color>();
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        if (this.getReligion(religionID).MonthlyIncome != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncome") + "", ((this.getReligion(religionID).MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyIncome, 100), Images.gold, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).MonthlyIncome == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).MonthlyIncome < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).TaxEfficiency != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TaxEfficiency") + "", ((this.getReligion(religionID).TaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).TaxEfficiency, 100) + "%", Images.tax, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).TaxEfficiency == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).TaxEfficiency < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).ProvinceMaintenance != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProvinceMaintenance") + "", ((this.getReligion(religionID).ProvinceMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProvinceMaintenance, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).ProvinceMaintenance == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).ProvinceMaintenance > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).ProductionEfficiency != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProductionEfficiency") + "", ((this.getReligion(religionID).ProductionEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProductionEfficiency, 100) + "%", Images.goods, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).ProductionEfficiency == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).ProductionEfficiency < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).MonthlyLegacy != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy") + "", ((this.getReligion(religionID).MonthlyLegacy > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyLegacy, 100), Images.legacy, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).MonthlyLegacy == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).MonthlyLegacy < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).MaxManpower != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower") + "", ((this.getReligion(religionID).MaxManpower > 0.0f) ? "+" : "") + (int)this.getReligion(religionID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).MaxManpower == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).MaxManpower < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).ArmyMaintenance != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMaintenance") + "", ((this.getReligion(religionID).ArmyMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).ArmyMaintenance == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).ArmyMaintenance > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).RecruitmentTime != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RecruitmentTime") + "", ((this.getReligion(religionID).RecruitmentTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).RecruitmentTime == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).RecruitmentTime > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).ConstructionCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionCost") + "", ((this.getReligion(religionID).ConstructionCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getReligion(religionID).ConstructionCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).ConstructionCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).ConstructionCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).AdministrationBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdministrationBuildingsCost") + "", ((this.getReligion(religionID).AdministrationBuildingsCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getReligion(religionID).AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).AdministrationBuildingsCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).AdministrationBuildingsCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).EconomyBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("EconomyBuildingsCost") + "", ((this.getReligion(religionID).EconomyBuildingsCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getReligion(religionID).EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).EconomyBuildingsCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).EconomyBuildingsCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).MilitaryBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MilitaryBuildingsCost") + "", ((this.getReligion(religionID).MilitaryBuildingsCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getReligion(religionID).MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).MilitaryBuildingsCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).MilitaryBuildingsCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).ConstructionTime != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionTime") + "", ((this.getReligion(religionID).ConstructionTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ConstructionTime * 100.0f, 100) + "%", Images.buildTime, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).ConstructionTime == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).ConstructionTime > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).BuildingSlot != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingSlot") + "", ((this.getReligion(religionID).BuildingSlot > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).BuildingSlot, 100), Images.build, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).BuildingSlot == 0) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).BuildingSlot < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).InvestInEconomyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(this.getReligion(religionID).InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).InvestInEconomyCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).InvestInEconomyCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).IncreaseTaxEfficiencyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(this.getReligion(religionID).IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).IncreaseTaxEfficiencyCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).IncreaseTaxEfficiencyCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).DevelopInfrastructureCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(this.getReligion(religionID).DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).DevelopInfrastructureCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).DevelopInfrastructureCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).IncreaseManpowerCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(this.getReligion(religionID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).IncreaseManpowerCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).IncreaseManpowerCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).RecruitArmyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(this.getReligion(religionID).RecruitArmyCost, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).RecruitArmyCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).RecruitArmyCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).GeneralAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsAttack") + "", ((this.getReligion(religionID).GeneralAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).GeneralAttack == 0) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).GeneralAttack < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).GeneralDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsDefense") + "", ((this.getReligion(religionID).GeneralDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).GeneralDefense == 0) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).GeneralDefense < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).UnitsAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsAttack") + "", ((this.getReligion(religionID).UnitsAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).UnitsAttack == 0) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).UnitsAttack < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).UnitsDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsDefense") + "", ((this.getReligion(religionID).UnitsDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).UnitsDefense == 0) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).UnitsDefense < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).AdvisorCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorCost") + "", ((this.getReligion(religionID).AdvisorCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).AdvisorCost * 100.0f, 100) + "%", Images.council, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).AdvisorCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).AdvisorCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).GeneralCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralCost") + "", ((this.getReligion(religionID).GeneralCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).GeneralCost * 100.0f, 100) + "%", Images.general, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).GeneralCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).GeneralCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).MaxNumberOfLoans != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumNumberOfLoans") + "", ((this.getReligion(religionID).MaxNumberOfLoans > 0) ? "+" : "") + this.getReligion(religionID).MaxNumberOfLoans, Images.loan, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).MaxNumberOfLoans == 0) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).MaxNumberOfLoans < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).CoreCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("CoreConstruction") + "", ((this.getReligion(religionID).CoreCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).CoreCost, 100) + "%", Images.core, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).CoreCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).CoreCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getReligion(religionID).ReligionCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReligionConversionCost") + "", ((this.getReligion(religionID).ReligionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ReligionCost, 100) + "%", Images.religion, iX, 0, iW, iH, maxIconW, (this.getReligion(religionID).ReligionCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getReligion(religionID).ReligionCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        final ArrayList<MenuElement> elementsOut = new ArrayList<MenuElement>();
        while (mElementsToSort.size() > 0) {
            int addID = 0;
            for (int oSize = mElementsToSort.size(), o = 1; o < oSize; ++o) {
                if (CFG.compareAlphabetic_TwoString(mElementsToSort.get(addID).getText(), mElementsToSort.get(o).getText())) {
                    addID = o;
                }
            }
            elementsOut.add(mElementsToSort.get(addID));
            elementsOut.get(elementsOut.size() - 1).setPosY(iY);
            iY += elementsOut.get(elementsOut.size() - 1).getHeight() + CFG.PADDING;
            mElementsToSort.remove(addID);
        }
        return elementsOut;
    }
    
    public MenuElement_Hover getHoverReligion(final int religionID, final int civID) {
        final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusReligion(Game.lang.get("Religion") + ": ", this.getReligion(religionID).Name, religionID, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        final int sizeBefore = nElements.size();
        if (this.getReligion(religionID).MonthlyIncome != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", ((this.getReligion(religionID).MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyIncome, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).MonthlyIncome > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).MonthlyLegacy != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", ((this.getReligion(religionID).MonthlyLegacy > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyLegacy, 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).MonthlyLegacy > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).TaxEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", ((this.getReligion(religionID).TaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).TaxEfficiency, 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).TaxEfficiency > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).ProductionEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", ((this.getReligion(religionID).ProductionEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProductionEfficiency, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).ProductionEfficiency > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).ProvinceMaintenance != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", ((this.getReligion(religionID).ProvinceMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProvinceMaintenance, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).ProvinceMaintenance < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).InvestInEconomyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", ((this.getReligion(religionID).InvestInEconomyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).InvestInEconomyCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).IncreaseTaxEfficiencyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", ((this.getReligion(religionID).IncreaseTaxEfficiencyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).IncreaseTaxEfficiencyCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).DevelopInfrastructureCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", ((this.getReligion(religionID).DevelopInfrastructureCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).DevelopInfrastructureCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).IncreaseManpowerCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", ((this.getReligion(religionID).IncreaseManpowerCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).IncreaseManpowerCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).ConstructionCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", ((this.getReligion(religionID).ConstructionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ConstructionCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).ConstructionCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).AdministrationBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdministrationBuildingsCost") + ": ", ((this.getReligion(religionID).AdministrationBuildingsCost * 100.0f > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).AdministrationBuildingsCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).EconomyBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EconomyBuildingsCost") + ": ", ((this.getReligion(religionID).EconomyBuildingsCost * 100.0f > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).EconomyBuildingsCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).MilitaryBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryBuildingsCost") + ": ", ((this.getReligion(religionID).MilitaryBuildingsCost * 100.0f > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).MilitaryBuildingsCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).ConstructionTime != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionTime") + ": ", ((this.getReligion(religionID).ConstructionTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ConstructionTime * 100.0f, 100) + "%", Images.buildTime, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).ConstructionTime < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).BuildingSlot != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlot") + ": ", ((this.getReligion(religionID).BuildingSlot > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).BuildingSlot, 100), Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).BuildingSlot > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).MaxManpower != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((this.getReligion(religionID).MaxManpower > 0.0f) ? "+" : "") + (int)this.getReligion(religionID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).MaxManpower > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).ArmyMaintenance != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", ((this.getReligion(religionID).ArmyMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).ArmyMaintenance < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).RecruitmentTime != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", ((this.getReligion(religionID).RecruitmentTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).RecruitmentTime < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).RecruitArmyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyRecruitmentCost") + ": ", ((this.getReligion(religionID).RecruitArmyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).RecruitArmyCost, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).RecruitArmyCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", ((this.getReligion(religionID).GeneralAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).GeneralAttack > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", ((this.getReligion(religionID).GeneralDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).GeneralDefense > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((this.getReligion(religionID).UnitsAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).UnitsAttack > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((this.getReligion(religionID).UnitsDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).UnitsDefense > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).AdvisorCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorCost") + ": ", ((this.getReligion(religionID).AdvisorCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).AdvisorCost * 100.0f, 100) + "%", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).AdvisorCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).GeneralCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", ((this.getReligion(religionID).GeneralCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).GeneralCost * 100.0f, 100) + "%", Images.general, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).GeneralCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).MaxNumberOfLoans != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumNumberOfLoans") + ": ", ((this.getReligion(religionID).MaxNumberOfLoans > 0) ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).MaxNumberOfLoans, 1), Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).MaxNumberOfLoans > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).CoreCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CoreConstruction") + ": ", ((this.getReligion(religionID).CoreCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).CoreCost, 100) + "%", Images.core, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).CoreCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getReligion(religionID).ReligionCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReligionConversionCost") + ": ", ((this.getReligion(religionID).ReligionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ReligionCost, 100) + "%", Images.religion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getReligion(religionID).ReligionCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (civID > 0) {
            if (sizeBefore != nElements.size()) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Population") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.getCiv(civID).getPopulationTotal()), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            try {
                final ArrayList<Integer> lReligionID = new ArrayList<Integer>();
                final ArrayList<Integer> religionPop = new ArrayList<Integer>();
                for (int i = 0; i < Game.religionManager.getReligionsSize(); ++i) {
                    lReligionID.add(i);
                    religionPop.add(0);
                }
                for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                    religionPop.set(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getReligion(), religionPop.get(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getReligion()) + Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getPopulationTotal());
                }
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                for (int i = religionPop.size() - 1; i >= 0; --i) {
                    if (religionPop.get(i) <= 0) {
                        religionPop.remove(i);
                        lReligionID.remove(i);
                    }
                }
                while (religionPop.size() > 0) {
                    int bestID = 0;
                    for (int i2 = 1; i2 < religionPop.size(); ++i2) {
                        if (religionPop.get(bestID) < religionPop.get(i2)) {
                            bestID = i2;
                        }
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(lReligionID.get(bestID)).Name + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + religionPop.get(bestID)), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
                    nData.add(new MenuElement_HoverElement_Type_Religion(lReligionID.get(bestID), CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(religionPop.get(bestID) / Game.getCiv(civID).getPopulationTotal() * 100.0f, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    lReligionID.remove(bestID);
                    religionPop.remove(bestID);
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static class Religion
    {
        public String Name;
        public String Icon;
        public int ReligionGroupID;
        public float[] Color;
        public boolean Tribal;
        public float MonthlyIncome;
        public float TaxEfficiency;
        public float ProvinceMaintenance;
        public float ProductionEfficiency;
        public float MonthlyLegacy;
        public float MaxManpower;
        public float ArmyMaintenance;
        public float RecruitmentTime;
        public float ConstructionCost;
        public float AdministrationBuildingsCost;
        public float MilitaryBuildingsCost;
        public float EconomyBuildingsCost;
        public float ConstructionTime;
        public int BuildingSlot;
        public float InvestInEconomyCost;
        public float IncreaseManpowerCost;
        public float IncreaseTaxEfficiencyCost;
        public float DevelopInfrastructureCost;
        public float RecruitArmyCost;
        public int GeneralAttack;
        public int GeneralDefense;
        public int UnitsAttack;
        public int UnitsDefense;
        public float AdvisorCost;
        public float GeneralCost;
        public float CoreCost;
        public float ReligionCost;
        public int MaxNumberOfLoans;
        
        public Religion() {
            this.Tribal = false;
        }
    }
}
