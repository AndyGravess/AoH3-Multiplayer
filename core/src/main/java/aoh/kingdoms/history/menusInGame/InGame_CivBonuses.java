// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses2;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Right_Color;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CivBonuses extends Menu
{
    public static int iCivID;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_CivBonuses() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        final int buttonH = CFG.TEXT_HEIGHT + CFG.PADDING * 6;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("CivilizationAdvantages") + ": " + Game.getCiv(InGame_CivBonuses.iCivID).iAdvantagesSize, Images.advantages, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW, 0) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_CivBonuses(false);
                Game.menuManager.rebuildInGame_Civ_UnlockedAdvantages();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_CivBonuses.iCivID).getCivName() + ": " + Game.lang.get("CivilizationAdvantages"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final List<MenuElement> mElementsToSort = new ArrayList<MenuElement>();
        if (Game.getCiv(InGame_CivBonuses.iCivID).canAccessSea) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyCanGoToSea"), "", Images.ship, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).canColonize) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ColonizationAllowed"), "", Images.population, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).canBuildNuke) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("NuclearWeapons"), "", Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyIncome != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncome") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyIncome, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TaxEfficiency != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TaxEfficiency") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TaxEfficiency, 100) + "%", Images.tax, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW) {
                @Override
                public Color getColorBonus() {
                    return (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TaxEfficiency > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                }
            });
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeTaxation != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncomeTaxation") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeTaxation > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeTaxation * 100.0f, 100) + "%", Images.tax, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeEconomy != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncomeEconomy") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeEconomy > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeEconomy * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeProduction != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncomeProduction") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeProduction > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeProduction, 100) + "%", Images.goods, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ProductionEfficiency != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProductionEfficiency") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ProductionEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ProductionEfficiency, 100) + "%", Images.goods, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ProvinceMaintenance != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ProvinceMaintenance, 100) + "%", Images.provinces, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BuildingsMaintenanceCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingsMaintenanceCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BuildingsMaintenanceCost * 100.0f, 100) + "%", Images.buildings, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaintenanceCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaintenanceCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaintenanceCost, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).getInflation_Just() > 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Inflation") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).getInflation_Just() * 100.0f, 100) + "%", Images.inflation, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GrowthRate != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GrowthRate") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GrowthRate > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GrowthRate, 100) + "%", Images.populationGrowth, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyLegacy != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyLegacy > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyLegacy, 100), Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyLegacy_Percentage != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyLegacy_Percentage > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MonthlyLegacy_Percentage * 100.0f, 100) + "%", Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxManpower != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxManpower > 0.0f) ? "+" : "") + (int)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxManpower_Percentage != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxManpower_Percentage > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxManpower_Percentage * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ManpowerRecoverySpeed != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ManpowerRecoverySpeed") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ManpowerRecoverySpeed > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ManpowerRecoverySpeed * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ReinforcementSpeed != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReinforcementSpeed") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ReinforcementSpeed > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ReinforcementSpeed * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMoraleRecovery != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMoraleRecovery") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMoraleRecovery > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMoraleRecovery * 100.0f, 100) + "%", Images.morale, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.WarScoreCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("WarScoreCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.WarScoreCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.WarScoreCost * 100.0f, 100) + "%", Images.victoryPoints, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMaintenance != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMaintenance") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMaintenance, 100) + "%", Images.armyMaintenance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitmentTime != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RecruitmentTime") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitmentTime > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyRecruitmentCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmyCost, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmyFirstLineCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("FirstLineArmyRecruitmentCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmyFirstLineCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmyFirstLineCost, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmySecondLineCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("SecondLineArmyRecruitmentCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmySecondLineCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RecruitArmySecondLineCost, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Research != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Research") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Research > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Research, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ResearchPoints != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ResearchPerMonth") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ResearchPoints > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ResearchPoints, 100), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Devastation != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Devastation") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Devastation > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Devastation * 100.0f, 100) + "%", Images.devastation, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TechnologyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TechnologyCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TechnologyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TechnologyCost, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ConstructionCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ConstructionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ConstructionCost * 100.0f, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdministrationBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdministrationBuildingsCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdministrationBuildingsCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MilitaryBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MilitaryBuildingsCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MilitaryBuildingsCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.EconomyBuildingsCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("EconomyBuildingsCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.EconomyBuildingsCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ConstructionTime != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionTime") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ConstructionTime > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ConstructionTime * 100.0f, 100) + "%", Images.buildTime, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.WonderConstructionCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("WonderConstructionCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.WonderConstructionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.WonderConstructionCost * 100.0f, 100) + "%", Images.mapModesWonders, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BuildingSlot != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingSlot") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BuildingSlot > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BuildingSlot, 100), Images.build, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxInfrastructure != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumInfrastructureLevel") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxInfrastructure > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxInfrastructure, 100), Images.infrastructure, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.InvestInEconomyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("InvestInEconomyCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.InvestInEconomyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseTaxEfficiencyCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseTaxEfficiencyCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseTaxEfficiencyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseGrowthRateCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseGrowthRateCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseGrowthRateCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DevelopInfrastructureCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DevelopInfrastructureCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DevelopInfrastructureCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseManpowerCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseManpowerCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseManpowerCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsAttack") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralAttack > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralAttack, 100), Images.attack, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsDefense") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralDefense > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralDefense, 100), Images.defense, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RegimentsLimit != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RegimentsLimit") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RegimentsLimit > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RegimentsLimit, Images.regimentsLimit, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.UnitsAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsAttack") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.UnitsAttack > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.UnitsAttack, 100), Images.attack, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.UnitsDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsDefense") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.UnitsDefense > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.UnitsDefense, 100), Images.defense, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxMorale != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaxMorale") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxMorale > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxMorale * 100.0f, 100) + "%", Images.morale, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorMaxLevel != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumAdvisorSkillLevel") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorMaxLevel > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorMaxLevel, Images.skill, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorPoolSize != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorPool") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorPoolSize > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorPoolSize, Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMovementSpeed != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMovementSpeed") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMovementSpeed > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.SiegeEffectiveness != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("SiegeEffectiveness") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.SiegeEffectiveness > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.SiegeEffectiveness * 100.0f, 100) + "%", Images.siege, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ImproveRelationsModifier != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ImproveRelationsModifier") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ImproveRelationsModifier > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ImproveRelationsModifier, 100) + "%", Images.relations, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeFromVassals != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncomeFromVassals") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeFromVassals > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.IncomeFromVassals * 100.0f, 100) + "%", Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DiplomacyPoints != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DiplomacyPoints") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DiplomacyPoints > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DiplomacyPoints * 100.0f, 100) + "%", Images.diplomacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Corruption != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Corruption") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Corruption > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Corruption * 100.0f, 100), Images.corruption, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Inflation != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Inflation") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Inflation > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Inflation * 100.0f, 100), Images.inflation, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxNumberOfLoans != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumNumberOfLoans") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxNumberOfLoans > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxNumberOfLoans, Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademyForGenerals != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademyForGenerals > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademyForGenerals, Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademy != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheMilitaryAcademy") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademy > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademy, Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheSupremeCourt != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheSupremeCourt") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheSupremeCourt > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfTheSupremeCourt, Images.stability, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfNuclearReactor != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheNuclearReactor") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfNuclearReactor > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfNuclearReactor, Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfCapitalCity != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfCapitalCity") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfCapitalCity > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumLevelOfCapitalCity, Images.capital, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Discipline != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Discipline") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Discipline > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Discipline * 100.0f, 10) + "%", Images.discipline, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumAmountOfGold != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumAmountOfGold") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumAmountOfGold > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumAmountOfGold, 10), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumAmountOfGold_Percentage != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumAmountOfGold") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumAmountOfGold_Percentage > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaximumAmountOfGold_Percentage * 100.0f, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Loot != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Loot") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Loot > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.Loot * 100.0f, 10) + "%", Images.loot, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ManpowerRecoveryFromADisbandedArmy != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ManpowerRecoveryFromADisbandedArmy > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ManpowerRecoveryFromADisbandedArmy * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_DISBAND, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BattleWidth != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BattleWidth") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BattleWidth > 0) ? "+" : "") + Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.BattleWidth, Images.battleWidth, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AllCharactersLifeExpectancy != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AllCharactersLifeExpectancy") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AllCharactersLifeExpectancy > 0) ? "+" : "") + Game.lang.get("YearsX", Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AllCharactersLifeExpectancy), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DiseaseDeathRate != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DiseasesDeathRate") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DiseaseDeathRate > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.DiseaseDeathRate * 100.0f, 100) + "%", Images.disease, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.LoanInterest != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("LoanInterest") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.LoanInterest > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.LoanInterest, 100) + "%", Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AggressiveExpansion != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AggressiveExpansion") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AggressiveExpansion > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AggressiveExpansion, 100) + "%", Images.war, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxNumOfAlliances != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaxNumOfAlliances") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxNumOfAlliances > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.MaxNumOfAlliances, 100), Images.alliance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.AdvisorCost * 100.0f, 100) + "%", Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.GeneralCost * 100.0f, 100) + "%", Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RevolutionaryRisk != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RevolutionaryRisk") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RevolutionaryRisk > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.RevolutionaryRisk, 100) + "%", Images.revolutionRisk, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.CoreCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("CoreConstruction") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.CoreCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.CoreCost, 100) + "%", Images.core, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ReligionCost != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReligionConversionCost") + "", ((Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ReligionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.ReligionCost, 100) + "%", Images.religion, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        while (mElementsToSort.size() > 0) {
            int addID = 0;
            for (int o = 1, oSize = mElementsToSort.size(); o < oSize; ++o) {
                if (CFG.compareAlphabetic_TwoString(mElementsToSort.get(addID).getText(), mElementsToSort.get(o).getText())) {
                    addID = o;
                }
            }
            menuElements.add(mElementsToSort.get(addID));
            menuElements.get(menuElements.size() - 1).setPosY(buttonY);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            mElementsToSort.remove(addID);
        }
        if (menuElements.size() == 0) {
            menuElements.add(new Text_Static(Game.lang.get("None") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT) {
                @Override
                protected Color getColor(final boolean isActive) {
                    if (isActive) {
                        return Colors.BUTTON_TEXT_ACTIVE;
                    }
                    if (this.getIsHovered()) {
                        return Colors.BUTTON_TEXT_HOVERED;
                    }
                    return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
                }
            });
        }
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        menuElements.add(new Text_Title_v2(Game.lang.get("CivilizationRank") + ": " + CivilizationRanking.getCivilizationRank_Name(Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (GameValues.civRank.CIV_RANK_MANPOWER_MAX[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("MaximumManpower") + ": ", "+" + GameValues.civRank.CIV_RANK_MANPOWER_MAX[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID], Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_ARMY_MAINTENANCE[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ArmyMaintenance") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_ARMY_MAINTENANCE[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] * 100.0f, 100) + "%", Images.armyMaintenance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("InvestInEconomyCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_BUILDING_CONSTRUCTION_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ConstructionCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_BUILDING_CONSTRUCTION_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] * 100.0f, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_ADVISOR_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("AdvisorCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_ADVISOR_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] * 100.0f, 100) + "%", Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_GENERAL_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("GeneralCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_GENERAL_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] * 100.0f, 100) + "%", Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_MONTHLY_LEGACY[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("MonthlyLegacy") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_LEGACY[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID], 100), Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ResearchPerMonth") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID], 100), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ProvinceMaintenance") + ": ", ((GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] * 100.0f, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_LOAN_INTEREST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("Interest") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_LOAN_INTEREST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID], 100) + "%", Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        if (GameValues.civRank.CIV_RANK_WAR_SCORE_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] != 0.0f) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("WarScoreCost") + ": ", ((GameValues.civRank.CIV_RANK_WAR_SCORE_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.civRank.CIV_RANK_WAR_SCORE_COST[Game.getCiv(InGame_CivBonuses.iCivID).iCivRankID] * 100.0f, 100) + "%", Images.victoryPoints, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }
        while (mElementsToSort.size() > 0) {
            int addID = 0;
            for (int o = 1, oSize = mElementsToSort.size(); o < oSize; ++o) {
                if (CFG.compareAlphabetic_TwoString(mElementsToSort.get(addID).getText(), mElementsToSort.get(o).getText())) {
                    addID = o;
                }
            }
            menuElements.add(mElementsToSort.get(addID));
            menuElements.get(menuElements.size() - 1).setPosY(buttonY);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            mElementsToSort.remove(addID);
        }
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - (CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + CFG.PADDING * 3));
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter("", Game.getCiv(InGame_CivBonuses.iCivID).getCivName(), false, false, Images.title1Red) {
            @Override
            public long getTime() {
                return InGame_CivBonuses.lTime;
            }
            
            @Override
            public int getFlagCivID() {
                return InGame_CivBonuses.iCivID;
            }
        }, CFG.GAME_WIDTH - menuWidth - CFG.PADDING * 2, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_CivBonuses.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX + CFG.BUTTON_WIDTH - (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_CivBonuses.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("CivilizationBonuses"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_CivBonuses.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
    }
    
    static {
        InGame_CivBonuses.lTime = 0L;
    }
}
