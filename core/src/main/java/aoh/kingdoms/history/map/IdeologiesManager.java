// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Bonuses_Right_Color;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class IdeologiesManager
{
    private List<Ideology> lIdeologies;
    private int iIdeologiesSize;
    public List<Image> ideologiesImages;
    public int maxWidth;
    public int maxHeight;
    
    public IdeologiesManager() {
        this.lIdeologies = null;
        this.iIdeologiesSize = 0;
        this.ideologiesImages = new ArrayList<Image>();
        this.maxWidth = 0;
        this.maxHeight = 0;
    }
    
    public final void loadIdeologies() {
        if (this.lIdeologies != null) {
            this.lIdeologies.clear();
        }
        this.lIdeologies = new ArrayList<Ideology>();
        try {
            final FileHandle fileList = FileManager.loadFile("game/Governments.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigIdeologiesData.class, "Government", (Class)Ideology.class);
            ConfigIdeologiesData data = new ConfigIdeologiesData();
            data = (ConfigIdeologiesData)json.fromJson((Class)ConfigIdeologiesData.class, fileContent);
            for (final Object e : data.Government) {
                final Ideology tempIdeology = (Ideology)e;
                tempIdeology.Name = Game.lang.get(tempIdeology.Name);
                tempIdeology.RulerTitle = Game.lang.get(tempIdeology.RulerTitle);
                tempIdeology.Extra_Tag = ((tempIdeology.Extra_Tag.length() > 0) ? ("_" + tempIdeology.Extra_Tag) : "");
                for (int i2 = tempIdeology.AI_BUILD_SCORE.length - 1; i2 >= 0; --i2) {
                    final Ideology \u0131deology = tempIdeology;
                    \u0131deology.AI_BUILD_SCORE_TOTAL += tempIdeology.AI_BUILD_SCORE[i2];
                }
                this.lIdeologies.add(tempIdeology);
            }
            this.iIdeologiesSize = this.lIdeologies.size();
            for (int i3 = 0; i3 < this.iIdeologiesSize; ++i3) {
                this.lIdeologies.get(i3).Color[0] /= 255.0f;
                this.lIdeologies.get(i3).Color[1] /= 255.0f;
                this.lIdeologies.get(i3).Color[2] /= 255.0f;
            }
        }
        catch (final GdxRuntimeException ex2) {}
        for (int j = 0; j < this.iIdeologiesSize; ++j) {
            try {
                if (FileManager.loadFile("gfx/government/" + CFG.getRescouresPath_Short() + "gov" + this.lIdeologies.get(j).Extra_Tag + ".png").exists()) {
                    this.ideologiesImages.add(new Image(ImageManager.loadTexture("gfx/government/" + CFG.getRescouresPath_Short() + "gov" + this.lIdeologies.get(j).Extra_Tag + ".png")));
                }
                else {
                    this.ideologiesImages.add(new Image(ImageManager.loadTexture("gfx/government/" + CFG.getRescouresPath_Short_H() + "gov" + this.lIdeologies.get(j).Extra_Tag + ".png")));
                }
            }
            catch (final GdxRuntimeException ex) {
                this.ideologiesImages.add(new Image(ImageManager.loadTexture("gfx/government/" + CFG.getRescouresPath_Short() + "gov.png")));
            }
        }
        for (int j = 0; j < this.iIdeologiesSize; ++j) {
            if (this.ideologiesImages.get(j).getWidth() > this.maxWidth) {
                this.maxWidth = this.ideologiesImages.get(j).getWidth();
            }
            if (this.ideologiesImages.get(j).getHeight() > this.maxHeight) {
                this.maxHeight = this.ideologiesImages.get(j).getHeight();
            }
        }
    }
    
    public final String getRealTag(final String sIn) {
        if (sIn.contains("_")) {
            return sIn.substring(0, sIn.indexOf(95));
        }
        return sIn;
    }
    
    public final int getIdeologyID(final String nCivTag) {
        if (nCivTag.lastIndexOf(95) > 0) {
            final String trueTag = nCivTag.substring(0, nCivTag.lastIndexOf(95) + 2);
            for (int i = 0; i < this.iIdeologiesSize; ++i) {
                try {
                    if (trueTag.charAt(trueTag.length() - 1) == this.lIdeologies.get(i).Extra_Tag.charAt(1) || trueTag.charAt(trueTag.indexOf(95) + 1) == this.lIdeologies.get(i).Extra_Tag.charAt(1)) {
                        return i;
                    }
                }
                catch (final StringIndexOutOfBoundsException ex) {}
            }
        }
        return 0;
    }
    
    protected boolean canBeAdded(final int nCivID, final int nIdeologyID) {
        final String tTag = Game.getCiv(nCivID).realTag + this.lIdeologies.get(nIdeologyID).Extra_Tag;
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getCivTag().equals(tTag)) {
                return false;
            }
        }
        return true;
    }
    
    protected List<Boolean> canChangeToIdeology(final int nCivID) {
        final ArrayList<Boolean> out = new ArrayList<Boolean>();
        for (int i = 0; i < this.getIdeologiesSize(); ++i) {
            if (i == Game.getCiv(nCivID).getIdeologyID()) {
                out.add(false);
            }
            else if (this.lIdeologies.get(i).REQUIRED_TECHNOLOGY >= 0 && !Game.getCiv(nCivID).getTechResearched(this.lIdeologies.get(i).REQUIRED_TECHNOLOGY)) {
                out.add(false);
            }
            else if (!this.canBeAdded(nCivID, i)) {
                out.add(false);
            }
            else if (this.lIdeologies.get(i).REVOLUTIONISTS) {
                out.add(false);
            }
            else {
                out.add(true);
            }
        }
        return out;
    }
    
    public final int getIdeologiesSize() {
        return this.iIdeologiesSize;
    }
    
    public final Ideology getIdeology(final int i) {
        return this.lIdeologies.get(i);
    }
    
    public final void updateCivBonuses(final int iCivID, final int ideologyID, final int mod, final boolean initMode) {
        final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
        civBonuses.MonthlyIncome += this.getIdeology(ideologyID).MonthlyIncome * mod;
        final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
        civBonuses2.TaxEfficiency += this.getIdeology(ideologyID).TaxEfficiency * mod;
        final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
        civBonuses3.ProductionEfficiency += this.getIdeology(ideologyID).ProductionEfficiency * mod;
        final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
        civBonuses4.ProvinceMaintenance += this.getIdeology(ideologyID).ProvinceMaintenance * mod;
        if (this.getIdeology(ideologyID).MonthlyLegacy != 0.0f) {
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
        civBonuses5.MonthlyLegacy += this.getIdeology(ideologyID).MonthlyLegacy * mod;
        if (this.getIdeology(ideologyID).MaxManpower != 0.0f) {
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
        civBonuses6.MaxManpower += this.getIdeology(ideologyID).MaxManpower * mod;
        if (this.getIdeology(ideologyID).ArmyMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
            civBonuses7.ArmyMaintenance += this.getIdeology(ideologyID).ArmyMaintenance * mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }
        final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
        civBonuses8.RecruitmentTime += this.getIdeology(ideologyID).RecruitmentTime * mod;
        final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
        civBonuses9.RecruitArmyCost += this.getIdeology(ideologyID).RecruitArmyCost * mod;
        final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
        civBonuses10.ConstructionCost += this.getIdeology(ideologyID).ConstructionCost * mod;
        final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
        civBonuses11.AdministrationBuildingsCost += this.getIdeology(ideologyID).AdministrationBuildingsCost * mod;
        final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
        civBonuses12.EconomyBuildingsCost += this.getIdeology(ideologyID).EconomyBuildingsCost * mod;
        final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
        civBonuses13.MilitaryBuildingsCost += this.getIdeology(ideologyID).MilitaryBuildingsCost * mod;
        final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
        civBonuses14.ConstructionTime += this.getIdeology(ideologyID).ConstructionTime * mod;
        final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
        civBonuses15.InvestInEconomyCost += this.getIdeology(ideologyID).InvestInEconomyCost * mod;
        final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
        civBonuses16.IncreaseTaxEfficiencyCost += this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost * mod;
        final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
        civBonuses17.DevelopInfrastructureCost += this.getIdeology(ideologyID).DevelopInfrastructureCost * mod;
        final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
        civBonuses18.IncreaseManpowerCost += this.getIdeology(ideologyID).IncreaseManpowerCost * mod;
        final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
        civBonuses19.GeneralAttack += this.getIdeology(ideologyID).GeneralAttack * mod;
        final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
        civBonuses20.GeneralDefense += this.getIdeology(ideologyID).GeneralDefense * mod;
        final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
        civBonuses21.UnitsAttack += this.getIdeology(ideologyID).UnitsAttack * mod;
        final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
        civBonuses22.UnitsDefense += this.getIdeology(ideologyID).UnitsDefense * mod;
        final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
        civBonuses23.MaxNumberOfLoans += this.getIdeology(ideologyID).MaxNumberOfLoans * mod;
        final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
        civBonuses24.BuildingSlot += this.getIdeology(ideologyID).BuildingSlot * mod;
        final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
        civBonuses25.AdvisorCost += this.getIdeology(ideologyID).AdvisorCost * mod;
        final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
        civBonuses26.GeneralCost += this.getIdeology(ideologyID).GeneralCost * mod;
        final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
        civBonuses27.ReligionCost += this.getIdeology(ideologyID).ReligionCost * mod;
        final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
        civBonuses28.CoreCost += this.getIdeology(ideologyID).CoreCost * mod;
        if (!initMode) {
            Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
        }
    }
    
    public final List<MenuElement> getMenuElements(final int ideologyID, final int iX, int iY, final int iW, final int iH) {
        final ArrayList<ButtonStatsRectIMG_Bonuses_Right_Color> mElementsToSort = new ArrayList<ButtonStatsRectIMG_Bonuses_Right_Color>();
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        if (this.getIdeology(ideologyID).MonthlyIncome != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncome") + "", ((this.getIdeology(ideologyID).MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyIncome, 100), Images.gold, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).MonthlyIncome == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).MonthlyIncome < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).TaxEfficiency != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TaxEfficiency") + "", ((this.getIdeology(ideologyID).TaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).TaxEfficiency, 100) + "%", Images.tax, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).TaxEfficiency == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).TaxEfficiency < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).ProvinceMaintenance != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProvinceMaintenance") + "", ((this.getIdeology(ideologyID).ProvinceMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProvinceMaintenance, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).ProvinceMaintenance == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).ProvinceMaintenance > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).ProductionEfficiency != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProductionEfficiency") + "", ((this.getIdeology(ideologyID).ProductionEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProductionEfficiency, 100) + "%", Images.goods, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).ProductionEfficiency == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).ProductionEfficiency < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).MonthlyLegacy != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy") + "", ((this.getIdeology(ideologyID).MonthlyLegacy > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyLegacy, 100), Images.legacy, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).MonthlyLegacy == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).MonthlyLegacy < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).MaxManpower != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower") + "", ((this.getIdeology(ideologyID).MaxManpower > 0.0f) ? "+" : "") + (int)this.getIdeology(ideologyID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).MaxManpower == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).MaxManpower < 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).ArmyMaintenance != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMaintenance") + "", ((this.getIdeology(ideologyID).ArmyMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).ArmyMaintenance == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).ArmyMaintenance > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).RecruitmentTime != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RecruitmentTime") + "", ((this.getIdeology(ideologyID).RecruitmentTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).RecruitmentTime == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).RecruitmentTime > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).ConstructionCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionCost") + "", ((this.getIdeology(ideologyID).ConstructionCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).ConstructionCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).ConstructionCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).AdministrationBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdministrationBuildingsCost") + "", ((this.getIdeology(ideologyID).AdministrationBuildingsCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).AdministrationBuildingsCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).AdministrationBuildingsCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).EconomyBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("EconomyBuildingsCost") + "", ((this.getIdeology(ideologyID).EconomyBuildingsCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).EconomyBuildingsCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).EconomyBuildingsCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).MilitaryBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MilitaryBuildingsCost") + "", ((this.getIdeology(ideologyID).MilitaryBuildingsCost > 0.0f) ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).MilitaryBuildingsCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).MilitaryBuildingsCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).ConstructionTime != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionTime") + "", ((this.getIdeology(ideologyID).ConstructionTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionTime * 100.0f, 100) + "%", Images.buildTime, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).ConstructionTime == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).ConstructionTime > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).BuildingSlot != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingSlot") + "", ((this.getIdeology(ideologyID).BuildingSlot > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).BuildingSlot, 100), Images.build, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).BuildingSlot == 0) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).BuildingSlot < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).InvestInEconomyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).InvestInEconomyCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).InvestInEconomyCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).DevelopInfrastructureCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).DevelopInfrastructureCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).DevelopInfrastructureCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).IncreaseManpowerCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).IncreaseManpowerCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).IncreaseManpowerCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).RecruitArmyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitArmyCost, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).RecruitArmyCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).RecruitArmyCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).GeneralAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsAttack") + "", ((this.getIdeology(ideologyID).GeneralAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).GeneralAttack == 0) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).GeneralAttack < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).GeneralDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsDefense") + "", ((this.getIdeology(ideologyID).GeneralDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).GeneralDefense == 0) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).GeneralDefense < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).UnitsAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsAttack") + "", ((this.getIdeology(ideologyID).UnitsAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).UnitsAttack == 0) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).UnitsAttack < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).UnitsDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsDefense") + "", ((this.getIdeology(ideologyID).UnitsDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).UnitsDefense == 0) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).UnitsDefense < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).AdvisorCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorCost") + "", ((this.getIdeology(ideologyID).AdvisorCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).AdvisorCost * 100.0f, 100) + "%", Images.council, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).AdvisorCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).AdvisorCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).GeneralCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralCost") + "", ((this.getIdeology(ideologyID).GeneralCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).GeneralCost * 100.0f, 100) + "%", Images.general, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).GeneralCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).GeneralCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).MaxNumberOfLoans != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumNumberOfLoans") + "", ((this.getIdeology(ideologyID).MaxNumberOfLoans > 0) ? "+" : "") + this.getIdeology(ideologyID).MaxNumberOfLoans, Images.loan, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).MaxNumberOfLoans == 0) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).MaxNumberOfLoans < 0) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).CoreCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("CoreConstruction") + "", ((this.getIdeology(ideologyID).CoreCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).CoreCost, 100) + "%", Images.core, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).CoreCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).CoreCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }
        if (this.getIdeology(ideologyID).ReligionCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReligionConversionCost") + "", ((this.getIdeology(ideologyID).ReligionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ReligionCost, 100) + "%", Images.religion, iX, 0, iW, iH, maxIconW, (this.getIdeology(ideologyID).ReligionCost == 0.0f) ? Colors.HOVER_NEUTRAL : ((this.getIdeology(ideologyID).ReligionCost > 0.0f) ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
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
    
    public MenuElement_Hover getHoverIdeology(final int ideologyID, final boolean showChangeIdeology, final boolean inChangeIdeology) {
        final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (inChangeIdeology) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChangeTypeOfGovernmentTo") + ": ", CFG.FONT_BOLD));
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(this.lIdeologies.get(ideologyID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (this.lIdeologies.get(ideologyID).REQUIRED_TECHNOLOGY >= 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(TechnologyTree.lTechnology.get(this.lIdeologies.get(ideologyID).REQUIRED_TECHNOLOGY).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), CFG.FONT_BOLD_SMALL, (Game.getCiv(Game.player.iCivID).fGold > GameValues.government.CHANGE_GOVERNMENT_COST) ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), CFG.FONT_BOLD_SMALL, (Game.getCiv(Game.player.iCivID).fLegacy > GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY) ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Government") + ": ", CFG.FONT_BOLD));
            nData.add(new MenuElement_HoverElement_Type_TextTitle(this.lIdeologies.get(ideologyID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).MonthlyIncome != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", ((this.getIdeology(ideologyID).MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyIncome, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).MonthlyIncome > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).MonthlyLegacy != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", ((this.getIdeology(ideologyID).MonthlyLegacy > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyLegacy, 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).MonthlyLegacy > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).TaxEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", ((this.getIdeology(ideologyID).TaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).TaxEfficiency, 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).TaxEfficiency > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).ProductionEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", ((this.getIdeology(ideologyID).ProductionEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProductionEfficiency, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).ProductionEfficiency > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).ProvinceMaintenance != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", ((this.getIdeology(ideologyID).ProvinceMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProvinceMaintenance, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).ProvinceMaintenance < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).InvestInEconomyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", ((this.getIdeology(ideologyID).InvestInEconomyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).InvestInEconomyCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", ((this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).DevelopInfrastructureCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", ((this.getIdeology(ideologyID).DevelopInfrastructureCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).DevelopInfrastructureCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).IncreaseManpowerCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", ((this.getIdeology(ideologyID).IncreaseManpowerCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).IncreaseManpowerCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).ConstructionCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", ((this.getIdeology(ideologyID).ConstructionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).ConstructionCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).AdministrationBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdministrationBuildingsCost") + ": ", ((this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0f > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).EconomyBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EconomyBuildingsCost") + ": ", ((this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0f > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).MilitaryBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryBuildingsCost") + ": ", ((this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0f > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0f < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).ConstructionTime != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionTime") + ": ", ((this.getIdeology(ideologyID).ConstructionTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionTime * 100.0f, 100) + "%", Images.buildTime, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).ConstructionTime < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).BuildingSlot != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlot") + ": ", ((this.getIdeology(ideologyID).BuildingSlot > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).BuildingSlot, 100), Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).BuildingSlot > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).MaxManpower != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((this.getIdeology(ideologyID).MaxManpower > 0.0f) ? "+" : "") + (int)this.getIdeology(ideologyID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).MaxManpower > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).ArmyMaintenance != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", ((this.getIdeology(ideologyID).ArmyMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).ArmyMaintenance < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).RecruitmentTime != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", ((this.getIdeology(ideologyID).RecruitmentTime > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).RecruitmentTime < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).RecruitArmyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyRecruitmentCost") + ": ", ((this.getIdeology(ideologyID).RecruitArmyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitArmyCost, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).RecruitArmyCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", ((this.getIdeology(ideologyID).GeneralAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).GeneralAttack > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", ((this.getIdeology(ideologyID).GeneralDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).GeneralDefense > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((this.getIdeology(ideologyID).UnitsAttack > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).UnitsAttack > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((this.getIdeology(ideologyID).UnitsDefense > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).UnitsDefense > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).AdvisorCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorCost") + ": ", ((this.getIdeology(ideologyID).AdvisorCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).AdvisorCost * 100.0f, 100) + "%", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).AdvisorCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).GeneralCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", ((this.getIdeology(ideologyID).GeneralCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).GeneralCost * 100.0f, 100) + "%", Images.general, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).GeneralCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).MaxNumberOfLoans != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumNumberOfLoans") + ": ", ((this.getIdeology(ideologyID).MaxNumberOfLoans > 0) ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).MaxNumberOfLoans, 1), Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).MaxNumberOfLoans > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).CoreCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CoreConstruction") + ": ", ((this.getIdeology(ideologyID).CoreCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).CoreCost, 100) + "%", Images.core, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).CoreCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.getIdeology(ideologyID).ReligionCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReligionConversionCost") + ": ", ((this.getIdeology(ideologyID).ReligionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ReligionCost, 100) + "%", Images.religion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (this.getIdeology(ideologyID).ReligionCost < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (showChangeIdeology) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChangeTypeOfGovernment"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), CFG.FONT_BOLD_SMALL, (Game.getCiv(Game.player.iCivID).fGold > GameValues.government.CHANGE_GOVERNMENT_COST) ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), CFG.FONT_BOLD_SMALL, (Game.getCiv(Game.player.iCivID).fLegacy > GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY) ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        return new MenuElement_Hover(nElements);
    }
    
    public final boolean changeGovernmentType(final int iCivID, final int toIdeologyID, final boolean free) {
        if (Game.getCiv(iCivID).getIdeologyID() == toIdeologyID) {
            return false;
        }
        if (!free && Game.getCiv(iCivID).fGold < GameValues.government.CHANGE_GOVERNMENT_COST) {
            return false;
        }
        if (!free && Game.getCiv(iCivID).fLegacy < GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY) {
            return false;
        }
        if (!free && Game.ideologiesManager.getIdeology(toIdeologyID).REQUIRED_TECHNOLOGY >= 0 && !Game.getCiv(iCivID).getTechResearched(Game.ideologiesManager.getIdeology(toIdeologyID).REQUIRED_TECHNOLOGY)) {
            return false;
        }
        DesktopLauncher.SMS("ChangeIdeology: " + iCivID + " " + toIdeologyID);
        Game.addSimpleTask(new Game.SimpleTask("changeGovernment" + Game.getCiv(iCivID).getCivName(), iCivID, toIdeologyID) {
            @Override
            public void update() {
                IdeologiesManager.this.updateCivBonuses(this.id, Game.getCiv(this.id).getIdeologyID(), -1, true);
                Game.updateCivilizationIdeology_InGame(this.id, Game.ideologiesManager.getRealTag(Game.getCiv(this.id).getCivTag()) + Game.ideologiesManager.getIdeology(this.id2).Extra_Tag);
                IdeologiesManager.this.updateCivBonuses(this.id, Game.getCiv(this.id).getIdeologyID(), 1, false);
                RulersManager.loadRuler(this.id, Game.getCiv(this.id).getCivTag(), false);
                final Civilization civ = Game.getCiv(this.id);
                civ.fGold -= GameValues.government.CHANGE_GOVERNMENT_COST;
                final Civilization civ2 = Game.getCiv(this.id);
                civ2.fLegacy -= GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY;
                Game.getCiv(this.id).updateTotalIncomePerMonth();
            }
        });
        return true;
    }
    
    public final boolean changeGovernmentType_Client(final int iCivID, final int toIdeologyID, final boolean free) {
        DesktopLauncher.SMS("ChangeIdeology: " + iCivID + " " + toIdeologyID);
        Game.addSimpleTask(new Game.SimpleTask("changeGovernment" + Game.getCiv(iCivID).getCivName(), iCivID, toIdeologyID) {
            @Override
            public void update() {
                IdeologiesManager.this.updateCivBonuses(this.id, Game.getCiv(this.id).getIdeologyID(), -1, true);
                Game.updateCivilizationIdeology_InGame(this.id, Game.ideologiesManager.getRealTag(Game.getCiv(this.id).getCivTag()) + Game.ideologiesManager.getIdeology(this.id2).Extra_Tag);
                IdeologiesManager.this.updateCivBonuses(this.id, Game.getCiv(this.id).getIdeologyID(), 1, false);
                RulersManager.loadRuler(this.id, Game.getCiv(this.id).getCivTag(), false);
                final Civilization civ = Game.getCiv(this.id);
                civ.fGold -= GameValues.government.CHANGE_GOVERNMENT_COST;
                final Civilization civ2 = Game.getCiv(this.id);
                civ2.fLegacy -= GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY;
                Game.getCiv(this.id).updateTotalIncomePerMonth();
            }
        });
        return true;
    }
    
    public static class ConfigIdeologiesData
    {
        protected String Age_of_History;
        protected ArrayList Government;
    }
    
    public static class Ideology
    {
        public String Name;
        public String Extra_Tag;
        public int GOV_GROUP_ID;
        public String RulerTitle;
        public boolean RulerRoman;
        public boolean KingsImages;
        public float[] Color;
        public int REQUIRED_TECHNOLOGY;
        public boolean REVOLUTIONISTS;
        public boolean MUST_BE_CHANGED;
        public boolean TRIBAL;
        public boolean CITY_STATE;
        public float STARTING_ARMY;
        public int AI_PEACE_ORDER_CHANCE;
        public int AI_PEACE_ORDER_CHANCE2;
        public int[] AI_PEACE_ORDER;
        public int[] AI_PEACE_ORDER2;
        public int[] AI_BUILD_SCORE;
        public int AI_BUILD_SCORE_TOTAL;
        public int AI_BUILD_INVEST_IN_ECONOMY;
        public int AI_BUILD_INCREASE_TAX_EFFICIENCY;
        public int AI_BUILD_INCREASE_MANPOWER;
        public int AI_BUILD_INCREASE_GROWTH_RATE;
        public int AI_BUILD_DEVELOP_INFRASTRUCTURE;
        public int AI_BUILD_BUILDING;
        public int AI_EXTRA_AGGRESSIVENESS;
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
        
        public Ideology() {
            this.RulerTitle = "";
            this.KingsImages = true;
            this.REVOLUTIONISTS = false;
            this.MUST_BE_CHANGED = false;
            this.TRIBAL = false;
            this.CITY_STATE = false;
            this.AI_PEACE_ORDER_CHANCE = 50;
            this.AI_PEACE_ORDER_CHANCE2 = 50;
            this.AI_PEACE_ORDER = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
            this.AI_PEACE_ORDER2 = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
            this.AI_BUILD_SCORE = new int[] { 70, 50, 40, 60, 40, 65, 60, 30, 40, 55, 50, 45, 30, 50, 40 };
            this.AI_BUILD_SCORE_TOTAL = 0;
            this.AI_BUILD_INVEST_IN_ECONOMY = 25;
            this.AI_BUILD_INCREASE_TAX_EFFICIENCY = 40;
            this.AI_BUILD_INCREASE_MANPOWER = 50;
            this.AI_BUILD_INCREASE_GROWTH_RATE = 60;
            this.AI_BUILD_DEVELOP_INFRASTRUCTURE = 67;
            this.AI_BUILD_BUILDING = 100;
            this.AI_EXTRA_AGGRESSIVENESS = 0;
        }
    }
}
