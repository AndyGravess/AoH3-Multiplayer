// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.map.civilization.CivilizationAdvisorsPool;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Right;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Ruler;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.button.ButtonAdvisorGeneral;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.map.advisors.AdvisorManager;
import aoh.kingdoms.history.menu_element.button.ButtonAdvisor;
import aoh.kingdoms.history.map.advisors.Advisor;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_AdvisorRecruit extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    private long lTime;
    public static int iActiveAdvisorTypeID;
    
    public InGame_AdvisorRecruit() {
        this.lTime = 0L;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        if (InGame_AdvisorRecruit.iActiveAdvisorTypeID < 3 || InGame_AdvisorRecruit.iActiveAdvisorTypeID == 4) {
            this.getAdvisorPool().updatePoolOfAdvisors(Game.player.iCivID);
        }
        else {
            Game.player.civAdvisorsPool_Military.updatePoolOfAdvisors(Game.player.iCivID);
        }
        for (int i = 0, iSize = this.getAdvisorPool().lAdvisors.size(); i < iSize; ++i) {
            if (InGame_AdvisorRecruit.iActiveAdvisorTypeID < 3 || InGame_AdvisorRecruit.iActiveAdvisorTypeID == 4) {
                menuElements.add(new ButtonAdvisor(buttonX, buttonY, this.getAdvisorPool().lAdvisors.get(i).sName, this.getAdvisorPool().lAdvisors.get(i).imageID, Game.player.iCivID, i, InGame_AdvisorRecruit.iActiveAdvisorTypeID, this.getAdvisorPool().lAdvisors.get(i).sIMG) {
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(Game.player.iCivID).fGold < AdvisorManager.getRecruitGoldCost(Game.player.iCivID)) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getRecruitGoldCost(Game.player.iCivID), 100), Images.gold);
                        }
                        else if (Game.getCiv(Game.player.iCivID).fLegacy < AdvisorManager.getRecruitCostLegacy(Game.player.iCivID)) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getRecruitCostLegacy(Game.player.iCivID), 100), Images.legacy);
                        }
                        else {
                            final String tName = InGame_AdvisorRecruit.this.getAdvisorPool().lAdvisors.get(this.getCurrent()).sName;
                            if (InGame_AdvisorRecruit.this.getAdvisorPool().recruitAdvisorID(Game.player.iCivID, this.getCurrent())) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                final MenuManager menuManager = Game.menuManager;
                                final AdvisorManager advisorManager = Game.advisorManager;
                                menuManager.rebuildInGame_Info(AdvisorManager.getAdvisorGroupName(InGame_AdvisorRecruit.this.getAdvisorPool().iAdvisorType), Game.lang.get("Hired") + ": " + tName);
                                InGame_Info.imgID = ((InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) ? Images.infoGeneral : Images.infoCrown);
                                Game.getCiv(Game.player.iCivID).updateResearchPerMonth();
                                Game.getCiv(Game.player.iCivID).updateLegacyPerMonth();
                                Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                            }
                            Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            if (Game.menuManager.getVisibleInGame_Court()) {
                                Game.menuManager.rebuildInGame_CourtSavePos();
                                Game.menuManager.setVisibleInGame_Court(true);
                                InGame_Court.lTime = 0L;
                            }
                            if (Game.menuManager.getVisibleInGame_CivBonuses()) {
                                Game.menuManager.rebuildInGame_CivBonuses();
                                Game.menuManager.setVisibleInGame_CivBonuses(true);
                                InGame_CivBonuses.lTime = 0L;
                            }
                        }
                    }
                });
            }
            else {
                menuElements.add(new ButtonAdvisorGeneral(buttonX, buttonY, this.getAdvisorPool().lAdvisors.get(i).sName, this.getAdvisorPool().lAdvisors.get(i).imageID, Game.player.iCivID, i, this.getAdvisorPool().lAdvisors.get(i).sIMG) {
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(Game.player.iCivID).fGold < AdvisorManager.getRecruitGoldCost(Game.player.iCivID)) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getRecruitGoldCost(Game.player.iCivID), 100), Images.gold);
                        }
                        else if (Game.getCiv(Game.player.iCivID).fLegacy < AdvisorManager.getRecruitCostLegacy(Game.player.iCivID)) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getRecruitCostLegacy(Game.player.iCivID), 100), Images.legacy);
                        }
                        else {
                            final String tName = InGame_AdvisorRecruit.this.getAdvisorPool().lAdvisors.get(this.getCurrent()).sName;
                            if (InGame_AdvisorRecruit.this.getAdvisorPool().recruitAdvisorID(Game.player.iCivID, this.getCurrent())) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                final MenuManager menuManager = Game.menuManager;
                                final AdvisorManager advisorManager = Game.advisorManager;
                                menuManager.rebuildInGame_Info(AdvisorManager.getAdvisorGroupName(InGame_AdvisorRecruit.this.getAdvisorPool().iAdvisorType), Game.lang.get("Hired") + ": " + tName);
                                InGame_Info.imgID = ((InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) ? Images.infoGeneral : Images.infoCrown);
                            }
                            Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            if (Game.menuManager.getVisibleInGame_Court()) {
                                Game.menuManager.rebuildInGame_CourtSavePos();
                                Game.menuManager.setVisibleInGame_Court(true);
                                InGame_Court.lTime = 0L;
                            }
                            if (Game.menuManager.getVisibleInGame_CivBonuses()) {
                                Game.menuManager.rebuildInGame_CivBonuses();
                                Game.menuManager.setVisibleInGame_CivBonuses(true);
                                InGame_CivBonuses.lTime = 0L;
                            }
                        }
                    }
                });
            }
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            int statsY = 0;
            final int statW = menuWidth - buttonX - paddingLeft;
            final int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
            menuElements.add(new Text_StaticBG_Ruler(this.getAdvisorPool().lAdvisors.get(i).sName, Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - this.getAdvisorPool().lAdvisors.get(i).iYearOfBirth)), buttonX, buttonY, statW, statH) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorButtonHover2(isActive, this.getIsHovered());
                }
            });
            statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            if (this.getAdvisorPool().lAdvisors.get(i).TaxEfficiency != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ProvinceMaintenance != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).GrowthRate != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).GrowthRate, 100), Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ConstructionCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ConstructionCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).AdministrationBuildingsCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).EconomyBuildingsCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).MilitaryBuildingsCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).InvestInEconomyCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).IncreaseTaxEfficiencyCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).IncreaseGrowthRateCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).DevelopInfrastructureCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ProductionEfficiency != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).Research != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).MonthlyLegacy != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).GeneralAttack != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).GeneralDefense != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ArmyMaintenance != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).RecruitArmyCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ConstructionTime != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ConstructionTime * 100.0f, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).IncreaseManpowerCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).RecruitmentTime != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).LoanInterest != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).CoreCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ReligionCost != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).IncomeProduction != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).MaxManpower != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)this.getAdvisorPool().lAdvisors.get(i).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).UnitsAttack != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).UnitsDefense != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).RegimentsLimit != 0) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)this.getAdvisorPool().lAdvisors.get(i).RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ImproveRelationsModifier != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).ArmyMovementSpeed != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (this.getAdvisorPool().lAdvisors.get(i).SiegeEffectiveness != 0.0f) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(this.getAdvisorPool().lAdvisors.get(i).SiegeEffectiveness * 100.0f, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING * 3;
            buttonX = paddingLeft;
        }
        final int iconWidth = (int)Math.ceil(ImageManager.getImage(Images.gold).getWidth() * 1.5f);
        final int costW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + AdvisorManager.getRecruitGoldCost(Game.player.iCivID), Images.gold, paddingLeft, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth));
        menuElements.add(new TextBonus(Game.lang.get("LegacyPoints") + ": ", "" + AdvisorManager.getRecruitCostLegacy(Game.player.iCivID), Images.legacy, paddingLeft + costW + CFG.PADDING, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        final String sText = "";
        final AdvisorManager advisorManager = Game.advisorManager;
        this.initMenu(new MenuTitleIMG_DoubleText(sText, AdvisorManager.getAdvisorGroupName(InGame_AdvisorRecruit.iActiveAdvisorTypeID), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_AdvisorRecruit.this.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2, menuWidth, menuHeight, menuElements, false, true);
    }
    
    public CivilizationAdvisorsPool getAdvisorPool() {
        switch (InGame_AdvisorRecruit.iActiveAdvisorTypeID) {
            case 0: {
                return Game.player.civAdvisorsPool_Administration;
            }
            case 1: {
                return Game.player.civAdvisorsPool_Economy;
            }
            case 2: {
                return Game.player.civAdvisorsPool_Technology;
            }
            default: {
                return Game.player.civAdvisorsPool_Military;
            }
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (this.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 2 / 5 + (int)(CFG.BUTTON_HEIGHT * 2 / 5 * ((CFG.currentTimeMillis - this.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("ChooseAnAdvisorToHire"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_AdvisorRecruit.iActiveAdvisorTypeID = 0;
    }
}
