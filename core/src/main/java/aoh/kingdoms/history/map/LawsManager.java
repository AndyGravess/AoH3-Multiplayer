// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class LawsManager
{
    public static List<Image> lawsImages;
    public static List<Law> laws;
    public static int iLawsSize;
    
    public static final void updateCivBonuses(final int i, final int j, final int iCivID, final float mod) {
        if (j >= LawsManager.laws.get(i).ImageID.length) {
            return;
        }
        if (LawsManager.laws.get(i).TaxEfficiency != null) {
            final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
            civBonuses.TaxEfficiency += LawsManager.laws.get(i).TaxEfficiency[j] * mod;
        }
        if (LawsManager.laws.get(i).ProvinceMaintenance != null) {
            final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
            civBonuses2.ProvinceMaintenance += LawsManager.laws.get(i).ProvinceMaintenance[j] * mod;
        }
        if (LawsManager.laws.get(i).BuildingsMaintenanceCost != null) {
            final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
            civBonuses3.BuildingsMaintenanceCost += LawsManager.laws.get(i).BuildingsMaintenanceCost[j] * mod;
        }
        if (LawsManager.laws.get(i).ConstructionTime != null) {
            final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
            civBonuses4.ConstructionTime += LawsManager.laws.get(i).ConstructionTime[j] * mod;
        }
        if (LawsManager.laws.get(i).ConstructionCost != null) {
            final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
            civBonuses5.ConstructionCost += LawsManager.laws.get(i).ConstructionCost[j] * mod;
        }
        if (LawsManager.laws.get(i).AdministrationBuildingsCost != null) {
            final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
            civBonuses6.AdministrationBuildingsCost += LawsManager.laws.get(i).AdministrationBuildingsCost[j] * mod;
        }
        if (LawsManager.laws.get(i).MilitaryBuildingsCost != null) {
            final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
            civBonuses7.MilitaryBuildingsCost += LawsManager.laws.get(i).MilitaryBuildingsCost[j] * mod;
        }
        if (LawsManager.laws.get(i).EconomyBuildingsCost != null) {
            final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
            civBonuses8.EconomyBuildingsCost += LawsManager.laws.get(i).EconomyBuildingsCost[j] * mod;
        }
        if (LawsManager.laws.get(i).WonderConstructionCost != null) {
            final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
            civBonuses9.WonderConstructionCost += LawsManager.laws.get(i).WonderConstructionCost[j] * mod;
        }
        if (LawsManager.laws.get(i).MaxManpower != null) {
            final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
            civBonuses10.MaxManpower += LawsManager.laws.get(i).MaxManpower[j] * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (LawsManager.laws.get(i).MaxManpower_Percentage != null) {
            final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
            civBonuses11.MaxManpower_Percentage += LawsManager.laws.get(i).MaxManpower_Percentage[j] * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (LawsManager.laws.get(i).ManpowerRecoverySpeed != null) {
            final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
            civBonuses12.ManpowerRecoverySpeed += LawsManager.laws.get(i).ManpowerRecoverySpeed[j] * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (LawsManager.laws.get(i).ArmyMoraleRecovery != null) {
            final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
            civBonuses13.ArmyMoraleRecovery += LawsManager.laws.get(i).ArmyMoraleRecovery[j] * mod;
        }
        if (LawsManager.laws.get(i).WarScoreCost != null) {
            final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
            civBonuses14.WarScoreCost += LawsManager.laws.get(i).WarScoreCost[j] * mod;
        }
        if (LawsManager.laws.get(i).Research != null) {
            final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
            civBonuses15.Research += LawsManager.laws.get(i).Research[j] * mod;
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).ResearchPoints != null) {
            final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
            civBonuses16.ResearchPoints += LawsManager.laws.get(i).ResearchPoints[j] * mod;
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).Devastation != null) {
            final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
            civBonuses17.Devastation += LawsManager.laws.get(i).Devastation[j] * mod;
        }
        if (LawsManager.laws.get(i).BuildingSlot != null) {
            final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
            civBonuses18.BuildingSlot += (int)(LawsManager.laws.get(i).BuildingSlot[j] * mod);
            Game.getCiv(iCivID).updateBuildingLimit();
        }
        if (LawsManager.laws.get(i).MaxInfrastructure != null) {
            final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
            civBonuses19.MaxInfrastructure += (int)(LawsManager.laws.get(i).MaxInfrastructure[j] * mod);
            Game.getCiv(iCivID).updateInfrastructureMax();
        }
        if (LawsManager.laws.get(i).MonthlyIncome != null) {
            final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
            civBonuses20.MonthlyIncome += LawsManager.laws.get(i).MonthlyIncome[j] * mod;
        }
        if (LawsManager.laws.get(i).Gold != null) {
            final Civilization civ = Game.getCiv(iCivID);
            civ.fGold += LawsManager.laws.get(i).Gold[j] * mod;
        }
        if (LawsManager.laws.get(i).MonthlyLegacy != null) {
            final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
            civBonuses21.MonthlyLegacy += LawsManager.laws.get(i).MonthlyLegacy[j] * mod;
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).MonthlyLegacy_Percentage != null) {
            final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
            civBonuses22.MonthlyLegacy_Percentage += LawsManager.laws.get(i).MonthlyLegacy_Percentage[j] * mod;
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).GrowthRate != null) {
            final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
            civBonuses23.GrowthRate += LawsManager.laws.get(i).GrowthRate[j] * mod;
            Game.getCiv(iCivID).updateResearchPerMonth();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).IncomeProduction != null) {
            final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
            civBonuses24.IncomeProduction += LawsManager.laws.get(i).IncomeProduction[j] * mod;
        }
        if (LawsManager.laws.get(i).IncomeEconomy != null) {
            final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
            civBonuses25.IncomeEconomy += LawsManager.laws.get(i).IncomeEconomy[j] * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).IncomeTaxation != null) {
            final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
            civBonuses26.IncomeTaxation += LawsManager.laws.get(i).IncomeTaxation[j] * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).ProductionEfficiency != null) {
            final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
            civBonuses27.ProductionEfficiency += LawsManager.laws.get(i).ProductionEfficiency[j] * mod;
        }
        if (LawsManager.laws.get(i).IncreaseManpowerCost != null) {
            final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
            civBonuses28.IncreaseManpowerCost += LawsManager.laws.get(i).IncreaseManpowerCost[j] * mod;
        }
        if (LawsManager.laws.get(i).InvestInEconomyCost != null) {
            final CivilizationBonuses civBonuses29 = Game.getCiv(iCivID).civBonuses;
            civBonuses29.InvestInEconomyCost += LawsManager.laws.get(i).InvestInEconomyCost[j] * mod;
        }
        if (LawsManager.laws.get(i).IncreaseTaxEfficiencyCost != null) {
            final CivilizationBonuses civBonuses30 = Game.getCiv(iCivID).civBonuses;
            civBonuses30.IncreaseTaxEfficiencyCost += LawsManager.laws.get(i).IncreaseTaxEfficiencyCost[j] * mod;
        }
        if (LawsManager.laws.get(i).IncreaseGrowthRateCost != null) {
            final CivilizationBonuses civBonuses31 = Game.getCiv(iCivID).civBonuses;
            civBonuses31.IncreaseGrowthRateCost += LawsManager.laws.get(i).IncreaseGrowthRateCost[j] * mod;
        }
        if (LawsManager.laws.get(i).DevelopInfrastructureCost != null) {
            final CivilizationBonuses civBonuses32 = Game.getCiv(iCivID).civBonuses;
            civBonuses32.DevelopInfrastructureCost += LawsManager.laws.get(i).DevelopInfrastructureCost[j] * mod;
        }
        if (LawsManager.laws.get(i).GeneralAttack != null) {
            final CivilizationBonuses civBonuses33 = Game.getCiv(iCivID).civBonuses;
            civBonuses33.GeneralAttack += (int)(LawsManager.laws.get(i).GeneralAttack[j] * mod);
        }
        if (LawsManager.laws.get(i).GeneralDefense != null) {
            final CivilizationBonuses civBonuses34 = Game.getCiv(iCivID).civBonuses;
            civBonuses34.GeneralDefense += (int)(LawsManager.laws.get(i).GeneralDefense[j] * mod);
        }
        if (LawsManager.laws.get(i).UnitsAttack != null) {
            final CivilizationBonuses civBonuses35 = Game.getCiv(iCivID).civBonuses;
            civBonuses35.UnitsAttack += (int)(LawsManager.laws.get(i).UnitsAttack[j] * mod);
        }
        if (LawsManager.laws.get(i).UnitsDefense != null) {
            final CivilizationBonuses civBonuses36 = Game.getCiv(iCivID).civBonuses;
            civBonuses36.UnitsDefense += (int)(LawsManager.laws.get(i).UnitsDefense[j] * mod);
        }
        if (LawsManager.laws.get(i).MaxMorale != null) {
            final CivilizationBonuses civBonuses37 = Game.getCiv(iCivID).civBonuses;
            civBonuses37.MaxMorale += LawsManager.laws.get(i).MaxMorale[j] * mod;
        }
        if (LawsManager.laws.get(i).ArmyMovementSpeed != null) {
            final CivilizationBonuses civBonuses38 = Game.getCiv(iCivID).civBonuses;
            civBonuses38.ArmyMovementSpeed += LawsManager.laws.get(i).ArmyMovementSpeed[j] * mod;
        }
        if (LawsManager.laws.get(i).SiegeEffectiveness != null) {
            final CivilizationBonuses civBonuses39 = Game.getCiv(iCivID).civBonuses;
            civBonuses39.SiegeEffectiveness += LawsManager.laws.get(i).SiegeEffectiveness[j] * mod;
        }
        if (LawsManager.laws.get(i).ImproveRelationsModifier != null) {
            final CivilizationBonuses civBonuses40 = Game.getCiv(iCivID).civBonuses;
            civBonuses40.ImproveRelationsModifier += LawsManager.laws.get(i).ImproveRelationsModifier[j] * mod;
        }
        if (LawsManager.laws.get(i).IncomeFromVassals != null) {
            final CivilizationBonuses civBonuses41 = Game.getCiv(iCivID).civBonuses;
            civBonuses41.IncomeFromVassals += LawsManager.laws.get(i).IncomeFromVassals[j] * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LawsManager.laws.get(i).DiplomacyPoints != null) {
            final CivilizationBonuses civBonuses42 = Game.getCiv(iCivID).civBonuses;
            civBonuses42.DiplomacyPoints += LawsManager.laws.get(i).DiplomacyPoints[j] * mod;
            Game.getCiv(iCivID).updateDiplomacyPerMonth();
        }
        if (LawsManager.laws.get(i).LoanInterest != null) {
            final CivilizationBonuses civBonuses43 = Game.getCiv(iCivID).civBonuses;
            civBonuses43.LoanInterest += LawsManager.laws.get(i).LoanInterest[j] * mod;
        }
        if (LawsManager.laws.get(i).MaxNumberOfLoans != null) {
            final CivilizationBonuses civBonuses44 = Game.getCiv(iCivID).civBonuses;
            civBonuses44.MaxNumberOfLoans += (int)(LawsManager.laws.get(i).MaxNumberOfLoans[j] * mod);
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
            final CivilizationBonuses civBonuses45 = Game.getCiv(iCivID).civBonuses;
            civBonuses45.MaximumLevelOfTheMilitaryAcademyForGenerals += (int)(LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j] * mod);
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy != null) {
            final CivilizationBonuses civBonuses46 = Game.getCiv(iCivID).civBonuses;
            civBonuses46.MaximumLevelOfTheMilitaryAcademy += (int)(LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy[j] * mod);
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt != null) {
            final CivilizationBonuses civBonuses47 = Game.getCiv(iCivID).civBonuses;
            civBonuses47.MaximumLevelOfTheSupremeCourt += (int)(LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt[j] * mod);
        }
        if (LawsManager.laws.get(i).MaximumLevelOfCapitalCity != null) {
            final CivilizationBonuses civBonuses48 = Game.getCiv(iCivID).civBonuses;
            civBonuses48.MaximumLevelOfCapitalCity += (int)(LawsManager.laws.get(i).MaximumLevelOfCapitalCity[j] * mod);
        }
        if (LawsManager.laws.get(i).RecruitmentTime != null) {
            final CivilizationBonuses civBonuses49 = Game.getCiv(iCivID).civBonuses;
            civBonuses49.RecruitmentTime += LawsManager.laws.get(i).RecruitmentTime[j] * mod;
        }
        if (LawsManager.laws.get(i).CoreCost != null) {
            final CivilizationBonuses civBonuses50 = Game.getCiv(iCivID).civBonuses;
            civBonuses50.CoreCost += LawsManager.laws.get(i).CoreCost[j] * mod;
        }
        if (LawsManager.laws.get(i).ReligionCost != null) {
            final CivilizationBonuses civBonuses51 = Game.getCiv(iCivID).civBonuses;
            civBonuses51.ReligionCost += LawsManager.laws.get(i).ReligionCost[j] * mod;
        }
        if (LawsManager.laws.get(i).RecruitArmyCost != null) {
            final CivilizationBonuses civBonuses52 = Game.getCiv(iCivID).civBonuses;
            civBonuses52.RecruitArmyCost += LawsManager.laws.get(i).RecruitArmyCost[j] * mod;
        }
        if (LawsManager.laws.get(i).RecruitArmyFirstLineCost != null) {
            final CivilizationBonuses civBonuses53 = Game.getCiv(iCivID).civBonuses;
            civBonuses53.RecruitArmyFirstLineCost += LawsManager.laws.get(i).RecruitArmyFirstLineCost[j] * mod;
        }
        if (LawsManager.laws.get(i).RecruitArmySecondLineCost != null) {
            final CivilizationBonuses civBonuses54 = Game.getCiv(iCivID).civBonuses;
            civBonuses54.RecruitArmySecondLineCost += LawsManager.laws.get(i).RecruitArmySecondLineCost[j] * mod;
        }
        if (LawsManager.laws.get(i).ArmyMaintenance != null) {
            final CivilizationBonuses civBonuses55 = Game.getCiv(iCivID).civBonuses;
            civBonuses55.ArmyMaintenance += LawsManager.laws.get(i).ArmyMaintenance[j] * mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }
        if (LawsManager.laws.get(i).MaxNumOfAlliances != null) {
            final CivilizationBonuses civBonuses56 = Game.getCiv(iCivID).civBonuses;
            civBonuses56.MaxNumOfAlliances += (int)(LawsManager.laws.get(i).MaxNumOfAlliances[j] * mod);
        }
        if (LawsManager.laws.get(i).AdvisorMaxLevel != null) {
            final CivilizationBonuses civBonuses57 = Game.getCiv(iCivID).civBonuses;
            civBonuses57.AdvisorMaxLevel += (int)(LawsManager.laws.get(i).AdvisorMaxLevel[j] * mod);
        }
        if (LawsManager.laws.get(i).AdvisorPoolSize != null) {
            final CivilizationBonuses civBonuses58 = Game.getCiv(iCivID).civBonuses;
            civBonuses58.AdvisorPoolSize += (int)(LawsManager.laws.get(i).AdvisorPoolSize[j] * mod);
        }
        if (LawsManager.laws.get(i).AdvisorCost != null) {
            final CivilizationBonuses civBonuses59 = Game.getCiv(iCivID).civBonuses;
            civBonuses59.AdvisorCost += LawsManager.laws.get(i).AdvisorCost[j] * mod;
        }
        if (LawsManager.laws.get(i).GeneralCost != null) {
            final CivilizationBonuses civBonuses60 = Game.getCiv(iCivID).civBonuses;
            civBonuses60.GeneralCost += LawsManager.laws.get(i).GeneralCost[j] * mod;
        }
        if (LawsManager.laws.get(i).AggressiveExpansion != null) {
            final CivilizationBonuses civBonuses61 = Game.getCiv(iCivID).civBonuses;
            civBonuses61.AggressiveExpansion += LawsManager.laws.get(i).AggressiveExpansion[j] * mod;
        }
        if (LawsManager.laws.get(i).DiseaseDeathRate != null) {
            final CivilizationBonuses civBonuses62 = Game.getCiv(iCivID).civBonuses;
            civBonuses62.DiseaseDeathRate += LawsManager.laws.get(i).DiseaseDeathRate[j] * mod;
        }
        if (LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy != null) {
            final CivilizationBonuses civBonuses63 = Game.getCiv(iCivID).civBonuses;
            civBonuses63.ManpowerRecoveryFromADisbandedArmy += LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy[j] * mod;
        }
        if (LawsManager.laws.get(i).BattleWidth != null) {
            final CivilizationBonuses civBonuses64 = Game.getCiv(iCivID).civBonuses;
            civBonuses64.BattleWidth += (int)(LawsManager.laws.get(i).BattleWidth[j] * mod);
        }
        if (LawsManager.laws.get(i).AllCharactersLifeExpectancy != null) {
            final CivilizationBonuses civBonuses65 = Game.getCiv(iCivID).civBonuses;
            civBonuses65.AllCharactersLifeExpectancy += (int)(LawsManager.laws.get(i).AllCharactersLifeExpectancy[j] * mod);
        }
        if (LawsManager.laws.get(i).UnlocksColonization != null) {
            Game.getCiv(iCivID).canColonize = LawsManager.laws.get(i).UnlocksColonization[j];
        }
        if (LawsManager.laws.get(i).Discipline != null) {
            final CivilizationBonuses civBonuses66 = Game.getCiv(iCivID).civBonuses;
            civBonuses66.Discipline += LawsManager.laws.get(i).Discipline[j] * mod;
        }
        if (LawsManager.laws.get(i).MaximumAmountOfGold != null) {
            final CivilizationBonuses civBonuses67 = Game.getCiv(iCivID).civBonuses;
            civBonuses67.MaximumAmountOfGold += LawsManager.laws.get(i).MaximumAmountOfGold[j] * mod;
        }
        if (LawsManager.laws.get(i).MaximumAmountOfGold_Percentage != null) {
            final CivilizationBonuses civBonuses68 = Game.getCiv(iCivID).civBonuses;
            civBonuses68.MaximumAmountOfGold_Percentage += LawsManager.laws.get(i).MaximumAmountOfGold_Percentage[j] * mod;
        }
        if (LawsManager.laws.get(i).Loot != null) {
            final CivilizationBonuses civBonuses69 = Game.getCiv(iCivID).civBonuses;
            civBonuses69.Loot += LawsManager.laws.get(i).Loot[j] * mod;
        }
        if (LawsManager.laws.get(i).RegimentsLimit != null) {
            final CivilizationBonuses civBonuses70 = Game.getCiv(iCivID).civBonuses;
            civBonuses70.RegimentsLimit += (int)(LawsManager.laws.get(i).RegimentsLimit[j] * mod);
            Game.getCiv(iCivID).updateRegimentsLimit();
        }
        Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
    }
    
    public static final void loadLaws() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/laws/Laws.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigLawData.class, "Law", (Class)Law.class);
            final ConfigLawData data = (ConfigLawData)json.fromJson((Class)ConfigLawData.class, fileContent);
            for (final Object e : data.Law) {
                LawsManager.laws.add((Law)e);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        LawsManager.iLawsSize = LawsManager.laws.size();
        loadLawsImages();
    }
    
    public static final void loadLawsImages() {
        final FileHandle tempFileT = FileManager.loadFile("game/laws/lawsImages/numOfImages.txt");
        for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
            if (FileManager.loadFile("game/laws/lawsImages/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                LawsManager.lawsImages.add(new Image(ImageManager.loadTexture_RGB888("game/laws/lawsImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
            else {
                LawsManager.lawsImages.add(new Image(ImageManager.loadTexture_RGB888("game/laws/lawsImages/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
        }
    }
    
    public static MenuElement_Hover getHover(final int i, final int j) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(LawsManager.laws.get(i).Title), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.law, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get(LawsManager.laws.get(i).Law[j]), "", Images.law, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get((LawsManager.laws.get(i).LawDesc != null) ? LawsManager.laws.get(i).LawDesc[j] : (LawsManager.laws.get(i).Law[j] + ".d")), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (LawsManager.laws.get(i).RequiredTechID[j] >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + TechnologyTree.lTechnology.get(LawsManager.laws.get(i).RequiredTechID[j]).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).RequiredGovernmentID != null) {
            try {
                if (LawsManager.laws.get(i).RequiredGovernmentID[j] >= 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Government") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.ideologiesManager.getIdeology(LawsManager.laws.get(i).RequiredGovernmentID[j]).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.government, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        if (LawsManager.laws.get(i).ConstructionCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).ConstructionCost[j] * 100.0f, 10) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).AdministrationBuildingsCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdministrationBuildingsCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).AdministrationBuildingsCost[j] * 100.0f, 10) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MilitaryBuildingsCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryBuildingsCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).MilitaryBuildingsCost[j] * 100.0f, 10) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).EconomyBuildingsCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EconomyBuildingsCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).EconomyBuildingsCost[j] * 100.0f, 10) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ConstructionTime != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionTime") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).ConstructionTime[j] * 100.0f, 10) + "%", Images.buildTime, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).WonderConstructionCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("WonderConstructionCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).WonderConstructionCost[j] * 100.0f, 10) + "%", Images.mapModesWonders, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).IncomeEconomy != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncomeEconomy") + ": ", ((LawsManager.laws.get(i).IncomeEconomy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeEconomy[j] * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).IncomeTaxation != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeTaxation") + ": ", ((LawsManager.laws.get(i).IncomeTaxation[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeTaxation[j] * 100.0f, 10) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).TaxEfficiency != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", ((LawsManager.laws.get(i).TaxEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).TaxEfficiency[j], 10) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ProvinceMaintenance != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenances") + ": ", ((LawsManager.laws.get(i).ProvinceMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ProvinceMaintenance[j], 10) + "%", Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).BuildingsMaintenanceCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingsMaintenanceCost") + ": ", ((LawsManager.laws.get(i).BuildingsMaintenanceCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).BuildingsMaintenanceCost[j] * 100.0f, 10) + "%", Images.buildings, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ManpowerRecoverySpeed != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ManpowerRecoverySpeed") + ": ", ((LawsManager.laws.get(i).ManpowerRecoverySpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ManpowerRecoverySpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ArmyMoraleRecovery != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMoraleRecovery") + ": ", ((LawsManager.laws.get(i).ArmyMoraleRecovery[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ArmyMoraleRecovery[j] * 100.0f, 10) + "%", Images.morale, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).WarScoreCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("WarScoreCost") + ": ", ((LawsManager.laws.get(i).WarScoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).WarScoreCost[j] * 100.0f, 10) + "%", Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ReinforcementSpeed != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReinforcementSpeed") + ": ", ((LawsManager.laws.get(i).ReinforcementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ReinforcementSpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaxManpower != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((LawsManager.laws.get(i).MaxManpower[j] > 0) ? "+" : "") + CFG.getNumberWithSpaces("" + LawsManager.laws.get(i).MaxManpower[j]), Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaxManpower_Percentage != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((LawsManager.laws.get(i).MaxManpower_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaxManpower_Percentage[j] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).Research != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Research") + ": ", ((LawsManager.laws.get(i).Research[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Research[j], 10) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ResearchPoints != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", ((LawsManager.laws.get(i).ResearchPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ResearchPoints[j], 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).BuildingSlot != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdditionalBuildingsInProvince") + ": ", ((LawsManager.laws.get(i).BuildingSlot[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).BuildingSlot[j], 1), Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaxInfrastructure != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumInfrastructureLevel") + ": ", ((LawsManager.laws.get(i).MaxInfrastructure[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaxInfrastructure[j], 1), Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).Devastation != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Devastation") + ": ", ((LawsManager.laws.get(i).Devastation[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Devastation[j] * 100.0f, 10) + "%", Images.devastation, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).GrowthRate != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GrowthRate") + ": ", ((LawsManager.laws.get(i).GrowthRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).GrowthRate[j], 10) + "%", Images.populationGrowth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MonthlyIncome != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", ((LawsManager.laws.get(i).MonthlyIncome[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MonthlyIncome[j], 100), Images.goldPositive, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).Gold != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", ((LawsManager.laws.get(i).Gold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Gold[j], 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MonthlyLegacy != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", ((LawsManager.laws.get(i).MonthlyLegacy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MonthlyLegacy[j], 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MonthlyLegacy_Percentage != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", ((LawsManager.laws.get(i).MonthlyLegacy_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MonthlyLegacy_Percentage[j] * 100.0f, 100) + "%", Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).IncomeProduction != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeProduction") + ": ", ((LawsManager.laws.get(i).IncomeProduction[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeProduction[j], 10) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ProductionEfficiency != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", ((LawsManager.laws.get(i).ProductionEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ProductionEfficiency[j], 10) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).InvestInEconomyCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", ((LawsManager.laws.get(i).InvestInEconomyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).InvestInEconomyCost[j] * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).IncreaseManpowerCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", ((LawsManager.laws.get(i).IncreaseManpowerCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncreaseManpowerCost[j], 10) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).IncreaseTaxEfficiencyCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", ((LawsManager.laws.get(i).IncreaseTaxEfficiencyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncreaseTaxEfficiencyCost[j] * 100.0f, 10) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).IncreaseGrowthRateCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseGrowthRateCost") + ": ", ((LawsManager.laws.get(i).IncreaseGrowthRateCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncreaseGrowthRateCost[j] * 100.0f, 10) + "%", Images.populationUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).DevelopInfrastructureCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", ((LawsManager.laws.get(i).DevelopInfrastructureCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).DevelopInfrastructureCost[j] * 100.0f, 10) + "%", Images.infrastructure, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).GeneralAttack != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", ((LawsManager.laws.get(i).GeneralAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).GeneralAttack[j], 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).GeneralDefense != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", ((LawsManager.laws.get(i).GeneralDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).GeneralDefense[j], 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).UnitsAttack != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((LawsManager.laws.get(i).UnitsAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).UnitsAttack[j], 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).UnitsDefense != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((LawsManager.laws.get(i).UnitsDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).UnitsDefense[j], 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaxMorale != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaxMorale") + ": ", ((LawsManager.laws.get(i).MaxMorale[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaxMorale[j] * 100.0f, 10) + "%", Images.morale, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ArmyMovementSpeed != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMovementSpeed") + ": ", ((LawsManager.laws.get(i).ArmyMovementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ArmyMovementSpeed[j], 10) + "%", Images.movementSpeed, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).SiegeEffectiveness != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SiegeEffectiveness") + ": ", ((LawsManager.laws.get(i).SiegeEffectiveness[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).SiegeEffectiveness[j] * 100.0f, 10) + "%", Images.siege, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ImproveRelationsModifier != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ImproveRelationsModifier") + ": ", ((LawsManager.laws.get(i).ImproveRelationsModifier[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ImproveRelationsModifier[j], 10) + "%", Images.relations, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).IncomeFromVassals != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeFromVassals") + ": ", ((LawsManager.laws.get(i).IncomeFromVassals[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeFromVassals[j] * 100.0f, 10) + "%", Images.vassal, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).LoanInterest != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LoanInterest") + ": ", ((LawsManager.laws.get(i).LoanInterest[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).LoanInterest[j], 10) + "%", Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).DiplomacyPoints != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", ((LawsManager.laws.get(i).DiplomacyPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).DiplomacyPoints[j] * 100.0f, 10) + "%", Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).RecruitmentTime != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", ((LawsManager.laws.get(i).RecruitmentTime[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitmentTime[j], 10) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).RecruitArmyCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyRecruitmentCost") + ": ", ((LawsManager.laws.get(i).RecruitArmyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitArmyCost[j], 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).RecruitArmyFirstLineCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("FirstLineArmyRecruitmentCost") + ": ", ((LawsManager.laws.get(i).RecruitArmyFirstLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitArmyFirstLineCost[j], 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).RecruitArmySecondLineCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SecondLineArmyRecruitmentCost") + ": ", ((LawsManager.laws.get(i).RecruitArmySecondLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitArmySecondLineCost[j], 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ArmyMaintenance != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", ((LawsManager.laws.get(i).ArmyMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ArmyMaintenance[j], 10) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).CoreCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CoreConstruction") + ": ", ((LawsManager.laws.get(i).CoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).CoreCost[j], 10) + "%", Images.core, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ReligionCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReligionConversionCost") + ": ", ((LawsManager.laws.get(i).ReligionCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ReligionCost[j], 10) + "%", Images.religion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaxNumOfAlliances != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaxNumOfAlliances") + ": ", ((LawsManager.laws.get(i).MaxNumOfAlliances[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaxNumOfAlliances[j], 1), Images.alliance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).AdvisorMaxLevel != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumAdvisorSkillLevel") + ": ", ((LawsManager.laws.get(i).AdvisorMaxLevel[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).AdvisorMaxLevel[j], 1), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).AdvisorPoolSize != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorPool") + ": ", ((LawsManager.laws.get(i).AdvisorPoolSize[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).AdvisorPoolSize[j], 1), Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaxNumberOfLoans != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumNumberOfLoans") + ": ", ((LawsManager.laws.get(i).MaxNumberOfLoans[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaxNumberOfLoans[j], 1), Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j], 1), Images.general, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumLevelOfTheMilitaryAcademy") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy[j], 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumLevelOfTheSupremeCourt") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt[j], 1), Images.stability, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaximumLevelOfCapitalCity != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumLevelOfCapitalCity") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfCapitalCity[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfCapitalCity[j], 1), Images.capital, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).AggressiveExpansion != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AggressiveExpansion") + ": ", ((LawsManager.laws.get(i).AggressiveExpansion[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).AggressiveExpansion[j], 10) + "%", Images.aggressiveExpansion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).DiseaseDeathRate != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiseasesDeathRate") + ": ", ((LawsManager.laws.get(i).DiseaseDeathRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).DiseaseDeathRate[j] * 100.0f, 10) + "%", Images.disease, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + ": ", ((LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_DISBAND, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).AdvisorCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorCost") + ": ", ((LawsManager.laws.get(i).AdvisorCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).AdvisorCost[j] * 100.0f, 10) + "%", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).GeneralCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", ((LawsManager.laws.get(i).GeneralCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).GeneralCost[j] * 100.0f, 10) + "%", Images.general, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).Discipline != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Discipline") + ": ", ((LawsManager.laws.get(i).Discipline[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Discipline[j] * 100.0f, 10) + "%", Images.discipline, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaximumAmountOfGold != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumAmountOfGold") + ": ", ((LawsManager.laws.get(i).MaximumAmountOfGold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaximumAmountOfGold[j], 10), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).MaximumAmountOfGold_Percentage != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumAmountOfGold") + ": ", ((LawsManager.laws.get(i).MaximumAmountOfGold_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaximumAmountOfGold_Percentage[j] * 100.0f, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).Loot != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Loot") + ": ", ((LawsManager.laws.get(i).Loot[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Loot[j] * 100.0f, 10) + "%", Images.loot, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).BattleWidth != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BattleWidth") + ": ", ((LawsManager.laws.get(i).BattleWidth[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).BattleWidth[j], 1), Images.battleWidth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).RegimentsLimit != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RegimentsLimit") + ": ", ((LawsManager.laws.get(i).RegimentsLimit[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).RegimentsLimit[j], 1), Images.regimentsLimit, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).AllCharactersLifeExpectancy != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AllCharactersLifeExpectancy") + ": ", ((LawsManager.laws.get(i).AllCharactersLifeExpectancy[j] > 0) ? "+" : "") + Game.lang.get("YearsX", LawsManager.laws.get(i).AllCharactersLifeExpectancy[j]), Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LawsManager.laws.get(i).UnlocksColonization != null) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", LawsManager.laws.get(i).UnlocksColonization[j] ? Game.lang.get("ColonizationAllowed") : Game.lang.get("NoColonizationAllowed"), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2((float)GameValues.laws.LAW_ADAPT_REFORM_COST_GOLD, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2((float)GameValues.laws.LAW_ADAPT_REFORM_COST_LEGACY_POINTS, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (CFG.debugMode) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("ID: ", "" + i, Images.law, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("ID2: ", "" + j, Images.law, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static List<MenuElement> getLawBonuses(final int i, final int j, final int paddingLeft, final int menuWidth) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        final int statH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        if (LawsManager.laws.get(i).ConstructionCost != null && LawsManager.laws.get(i).ConstructionCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ConstructionCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).ConstructionCost[j] * 100.0f, 10) + "%", Images.construction, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).AdministrationBuildingsCost != null && LawsManager.laws.get(i).AdministrationBuildingsCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AdministrationBuildingsCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).AdministrationBuildingsCost[j] * 100.0f, 10) + "%", Images.construction, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MilitaryBuildingsCost != null && LawsManager.laws.get(i).MilitaryBuildingsCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MilitaryBuildingsCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).MilitaryBuildingsCost[j] * 100.0f, 10) + "%", Images.construction, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).EconomyBuildingsCost != null && LawsManager.laws.get(i).EconomyBuildingsCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("EconomyBuildingsCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).EconomyBuildingsCost[j] * 100.0f, 10) + "%", Images.construction, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ConstructionTime != null && LawsManager.laws.get(i).ConstructionTime[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ConstructionTime") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).ConstructionTime[j] * 100.0f, 10) + "%", Images.buildTime, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).WonderConstructionCost != null && LawsManager.laws.get(i).WonderConstructionCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("WonderConstructionCost") + ": ", "" + CFG.getPrecision2(LawsManager.laws.get(i).WonderConstructionCost[j] * 100.0f, 10) + "%", Images.mapModesWonders, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).IncomeEconomy != null && LawsManager.laws.get(i).IncomeEconomy[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyIncomeEconomy") + ": ", ((LawsManager.laws.get(i).IncomeEconomy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeEconomy[j] * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).IncomeTaxation != null && LawsManager.laws.get(i).IncomeTaxation[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncomeTaxation") + ": ", ((LawsManager.laws.get(i).IncomeTaxation[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeTaxation[j] * 100.0f, 10) + "%", Images.tax, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).TaxEfficiency != null && LawsManager.laws.get(i).TaxEfficiency[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("TaxEfficiency") + ": ", ((LawsManager.laws.get(i).TaxEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).TaxEfficiency[j], 10) + "%", Images.tax, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ProvinceMaintenance != null && LawsManager.laws.get(i).ProvinceMaintenance[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProvinceMaintenances") + ": ", ((LawsManager.laws.get(i).ProvinceMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ProvinceMaintenance[j], 10) + "%", Images.provinces, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).BuildingsMaintenanceCost != null && LawsManager.laws.get(i).BuildingsMaintenanceCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("BuildingsMaintenanceCost") + ": ", ((LawsManager.laws.get(i).BuildingsMaintenanceCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).BuildingsMaintenanceCost[j] * 100.0f, 10) + "%", Images.buildings, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ManpowerRecoverySpeed != null && LawsManager.laws.get(i).ManpowerRecoverySpeed[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ManpowerRecoverySpeed") + ": ", ((LawsManager.laws.get(i).ManpowerRecoverySpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ManpowerRecoverySpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ArmyMoraleRecovery != null && LawsManager.laws.get(i).ArmyMoraleRecovery[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ArmyMoraleRecovery") + ": ", ((LawsManager.laws.get(i).ArmyMoraleRecovery[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ArmyMoraleRecovery[j] * 100.0f, 10) + "%", Images.morale, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).WarScoreCost != null && LawsManager.laws.get(i).WarScoreCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("WarScoreCost") + ": ", ((LawsManager.laws.get(i).WarScoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).WarScoreCost[j] * 100.0f, 10) + "%", Images.victoryPoints, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ReinforcementSpeed != null && LawsManager.laws.get(i).ReinforcementSpeed[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ReinforcementSpeed") + ": ", ((LawsManager.laws.get(i).ReinforcementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ReinforcementSpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaxManpower != null && LawsManager.laws.get(i).MaxManpower[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumManpower") + ": ", ((LawsManager.laws.get(i).MaxManpower[j] > 0) ? "+" : "") + CFG.getNumberWithSpaces("" + LawsManager.laws.get(i).MaxManpower[j]), Game_Calendar.IMG_MANPOWER_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaxManpower_Percentage != null && LawsManager.laws.get(i).MaxManpower_Percentage[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumManpower") + ": ", ((LawsManager.laws.get(i).MaxManpower_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaxManpower_Percentage[j] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).Research != null && LawsManager.laws.get(i).Research[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Research") + ": ", ((LawsManager.laws.get(i).Research[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Research[j], 10) + "%", Game_Calendar.IMG_TECHNOLOGY, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ResearchPoints != null && LawsManager.laws.get(i).ResearchPoints[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ResearchPerMonth") + ": ", ((LawsManager.laws.get(i).ResearchPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ResearchPoints[j], 100), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).BuildingSlot != null && LawsManager.laws.get(i).BuildingSlot[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AdditionalBuildingsInProvince") + ": ", ((LawsManager.laws.get(i).BuildingSlot[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).BuildingSlot[j], 1), Images.build, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaxInfrastructure != null && LawsManager.laws.get(i).MaxInfrastructure[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumInfrastructureLevel") + ": ", ((LawsManager.laws.get(i).MaxInfrastructure[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaxInfrastructure[j], 1), Images.infrastructureUp, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).Devastation != null && LawsManager.laws.get(i).Devastation[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Devastation") + ": ", ((LawsManager.laws.get(i).Devastation[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Devastation[j] * 100.0f, 10) + "%", Images.devastation, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).GrowthRate != null && LawsManager.laws.get(i).GrowthRate[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("GrowthRate") + ": ", ((LawsManager.laws.get(i).GrowthRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).GrowthRate[j], 10) + "%", Images.populationGrowth, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MonthlyIncome != null && LawsManager.laws.get(i).MonthlyIncome[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyIncome") + ": ", ((LawsManager.laws.get(i).MonthlyIncome[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MonthlyIncome[j], 100), Images.goldPositive, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).Gold != null && LawsManager.laws.get(i).Gold[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Gold") + ": ", ((LawsManager.laws.get(i).Gold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Gold[j], 100), Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MonthlyLegacy != null && LawsManager.laws.get(i).MonthlyLegacy[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyLegacy") + ": ", ((LawsManager.laws.get(i).MonthlyLegacy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MonthlyLegacy[j], 100), Images.legacy, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MonthlyLegacy_Percentage != null && LawsManager.laws.get(i).MonthlyLegacy_Percentage[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyLegacy") + ": ", ((LawsManager.laws.get(i).MonthlyLegacy_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MonthlyLegacy_Percentage[j] * 100.0f, 100) + "%", Images.legacy, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).IncomeProduction != null && LawsManager.laws.get(i).IncomeProduction[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncomeProduction") + ": ", ((LawsManager.laws.get(i).IncomeProduction[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeProduction[j], 10) + "%", Images.goods, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ProductionEfficiency != null && LawsManager.laws.get(i).ProductionEfficiency[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProductionEfficiency") + ": ", ((LawsManager.laws.get(i).ProductionEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ProductionEfficiency[j], 10) + "%", Images.goods, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).InvestInEconomyCost != null && LawsManager.laws.get(i).InvestInEconomyCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("InvestInEconomyCost") + ": ", ((LawsManager.laws.get(i).InvestInEconomyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).InvestInEconomyCost[j] * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).IncreaseManpowerCost != null && LawsManager.laws.get(i).IncreaseManpowerCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreaseManpowerCost") + ": ", ((LawsManager.laws.get(i).IncreaseManpowerCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncreaseManpowerCost[j], 10) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).IncreaseTaxEfficiencyCost != null && LawsManager.laws.get(i).IncreaseTaxEfficiencyCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", ((LawsManager.laws.get(i).IncreaseTaxEfficiencyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncreaseTaxEfficiencyCost[j] * 100.0f, 10) + "%", Images.taxUp, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).IncreaseGrowthRateCost != null && LawsManager.laws.get(i).IncreaseGrowthRateCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreaseGrowthRateCost") + ": ", ((LawsManager.laws.get(i).IncreaseGrowthRateCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncreaseGrowthRateCost[j] * 100.0f, 10) + "%", Images.populationUp, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).DevelopInfrastructureCost != null && LawsManager.laws.get(i).DevelopInfrastructureCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DevelopInfrastructureCost") + ": ", ((LawsManager.laws.get(i).DevelopInfrastructureCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).DevelopInfrastructureCost[j] * 100.0f, 10) + "%", Images.infrastructure, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).GeneralAttack != null && LawsManager.laws.get(i).GeneralAttack[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("GeneralsAttack") + ": ", ((LawsManager.laws.get(i).GeneralAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).GeneralAttack[j], 1), Images.attack, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).GeneralDefense != null && LawsManager.laws.get(i).GeneralDefense[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("GeneralsDefense") + ": ", ((LawsManager.laws.get(i).GeneralDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).GeneralDefense[j], 1), Images.defense, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).UnitsAttack != null && LawsManager.laws.get(i).UnitsAttack[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("UnitsAttack") + ": ", ((LawsManager.laws.get(i).UnitsAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).UnitsAttack[j], 1), Images.attack, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).UnitsDefense != null && LawsManager.laws.get(i).UnitsDefense[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("UnitsDefense") + ": ", ((LawsManager.laws.get(i).UnitsDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).UnitsDefense[j], 1), Images.defense, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaxMorale != null && LawsManager.laws.get(i).MaxMorale[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaxMorale") + ": ", ((LawsManager.laws.get(i).MaxMorale[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaxMorale[j] * 100.0f, 10) + "%", Images.morale, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ArmyMovementSpeed != null && LawsManager.laws.get(i).ArmyMovementSpeed[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ArmyMovementSpeed") + ": ", ((LawsManager.laws.get(i).ArmyMovementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ArmyMovementSpeed[j], 10) + "%", Images.movementSpeed, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).SiegeEffectiveness != null && LawsManager.laws.get(i).SiegeEffectiveness[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("SiegeEffectiveness") + ": ", ((LawsManager.laws.get(i).SiegeEffectiveness[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).SiegeEffectiveness[j] * 100.0f, 10) + "%", Images.siege, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ImproveRelationsModifier != null && LawsManager.laws.get(i).ImproveRelationsModifier[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ImproveRelationsModifier") + ": ", ((LawsManager.laws.get(i).ImproveRelationsModifier[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ImproveRelationsModifier[j], 10) + "%", Images.relations, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).IncomeFromVassals != null && LawsManager.laws.get(i).IncomeFromVassals[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncomeFromVassals") + ": ", ((LawsManager.laws.get(i).IncomeFromVassals[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).IncomeFromVassals[j] * 100.0f, 10) + "%", Images.vassal, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).LoanInterest != null && LawsManager.laws.get(i).LoanInterest[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("LoanInterest") + ": ", ((LawsManager.laws.get(i).LoanInterest[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).LoanInterest[j], 10) + "%", Images.loan, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).DiplomacyPoints != null && LawsManager.laws.get(i).DiplomacyPoints[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DiplomacyPoints") + ": ", ((LawsManager.laws.get(i).DiplomacyPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).DiplomacyPoints[j] * 100.0f, 10) + "%", Images.diplomacy, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).RecruitmentTime != null && LawsManager.laws.get(i).RecruitmentTime[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("RecruitmentTime") + ": ", ((LawsManager.laws.get(i).RecruitmentTime[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitmentTime[j], 10) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).RecruitArmyCost != null && LawsManager.laws.get(i).RecruitArmyCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ArmyRecruitmentCost") + ": ", ((LawsManager.laws.get(i).RecruitArmyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitArmyCost[j], 10) + "%", Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).RecruitArmyFirstLineCost != null && LawsManager.laws.get(i).RecruitArmyFirstLineCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("FirstLineArmyRecruitmentCost") + ": ", ((LawsManager.laws.get(i).RecruitArmyFirstLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitArmyFirstLineCost[j], 10) + "%", Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).RecruitArmySecondLineCost != null && LawsManager.laws.get(i).RecruitArmySecondLineCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("SecondLineArmyRecruitmentCost") + ": ", ((LawsManager.laws.get(i).RecruitArmySecondLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).RecruitArmySecondLineCost[j], 10) + "%", Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ArmyMaintenance != null && LawsManager.laws.get(i).ArmyMaintenance[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ArmyMaintenance") + ": ", ((LawsManager.laws.get(i).ArmyMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ArmyMaintenance[j], 10) + "%", Images.armyMaintenance, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).CoreCost != null && LawsManager.laws.get(i).CoreCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("CoreConstruction") + ": ", ((LawsManager.laws.get(i).CoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).CoreCost[j], 10) + "%", Images.core, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ReligionCost != null && LawsManager.laws.get(i).ReligionCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ReligionConversionCost") + ": ", ((LawsManager.laws.get(i).ReligionCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ReligionCost[j], 10) + "%", Images.religion, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaxNumOfAlliances != null && LawsManager.laws.get(i).MaxNumOfAlliances[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaxNumOfAlliances") + ": ", ((LawsManager.laws.get(i).MaxNumOfAlliances[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaxNumOfAlliances[j], 1), Images.alliance, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).AdvisorMaxLevel != null && LawsManager.laws.get(i).AdvisorMaxLevel[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumAdvisorSkillLevel") + ": ", ((LawsManager.laws.get(i).AdvisorMaxLevel[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).AdvisorMaxLevel[j], 1), Images.skill, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).AdvisorPoolSize != null && LawsManager.laws.get(i).AdvisorPoolSize[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AdvisorPool") + ": ", ((LawsManager.laws.get(i).AdvisorPoolSize[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).AdvisorPoolSize[j], 1), Images.council, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaxNumberOfLoans != null && LawsManager.laws.get(i).MaxNumberOfLoans[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumNumberOfLoans") + ": ", ((LawsManager.laws.get(i).MaxNumberOfLoans[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaxNumberOfLoans[j], 1), Images.loan, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals != null && LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j], 1), Images.general, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy != null && LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumLevelOfTheMilitaryAcademy") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfTheMilitaryAcademy[j], 1), Game_Calendar.IMG_MANPOWER, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt != null && LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumLevelOfTheSupremeCourt") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfTheSupremeCourt[j], 1), Images.stability, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaximumLevelOfCapitalCity != null && LawsManager.laws.get(i).MaximumLevelOfCapitalCity[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumLevelOfCapitalCity") + ": ", ((LawsManager.laws.get(i).MaximumLevelOfCapitalCity[j] >= 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).MaximumLevelOfCapitalCity[j], 1), Images.capital, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).AggressiveExpansion != null && LawsManager.laws.get(i).AggressiveExpansion[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AggressiveExpansion") + ": ", ((LawsManager.laws.get(i).AggressiveExpansion[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).AggressiveExpansion[j], 10) + "%", Images.aggressiveExpansion, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).DiseaseDeathRate != null && LawsManager.laws.get(i).DiseaseDeathRate[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DiseasesDeathRate") + ": ", ((LawsManager.laws.get(i).DiseaseDeathRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).DiseaseDeathRate[j] * 100.0f, 10) + "%", Images.disease, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy != null && LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + ": ", ((LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).ManpowerRecoveryFromADisbandedArmy[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_DISBAND, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).AdvisorCost != null && LawsManager.laws.get(i).AdvisorCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AdvisorCost") + ": ", ((LawsManager.laws.get(i).AdvisorCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).AdvisorCost[j] * 100.0f, 10) + "%", Images.council, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).GeneralCost != null && LawsManager.laws.get(i).GeneralCost[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("GeneralCost") + ": ", ((LawsManager.laws.get(i).GeneralCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).GeneralCost[j] * 100.0f, 10) + "%", Images.general, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).Discipline != null && LawsManager.laws.get(i).Discipline[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Discipline") + ": ", ((LawsManager.laws.get(i).Discipline[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Discipline[j] * 100.0f, 10) + "%", Images.discipline, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaximumAmountOfGold != null && LawsManager.laws.get(i).MaximumAmountOfGold[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumAmountOfGold") + ": ", ((LawsManager.laws.get(i).MaximumAmountOfGold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaximumAmountOfGold[j], 10), Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).MaximumAmountOfGold_Percentage != null && LawsManager.laws.get(i).MaximumAmountOfGold_Percentage[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumAmountOfGold") + ": ", ((LawsManager.laws.get(i).MaximumAmountOfGold_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).MaximumAmountOfGold_Percentage[j] * 100.0f, 100) + "%", Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).Loot != null && LawsManager.laws.get(i).Loot[j] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Loot") + ": ", ((LawsManager.laws.get(i).Loot[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(LawsManager.laws.get(i).Loot[j] * 100.0f, 10) + "%", Images.loot, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).BattleWidth != null && LawsManager.laws.get(i).BattleWidth[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("BattleWidth") + ": ", ((LawsManager.laws.get(i).BattleWidth[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).BattleWidth[j], 1), Images.battleWidth, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).RegimentsLimit != null && LawsManager.laws.get(i).RegimentsLimit[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("RegimentsLimit") + ": ", ((LawsManager.laws.get(i).RegimentsLimit[j] > 0) ? "+" : "") + CFG.getPrecision2((float)LawsManager.laws.get(i).RegimentsLimit[j], 1), Images.regimentsLimit, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).AllCharactersLifeExpectancy != null && LawsManager.laws.get(i).AllCharactersLifeExpectancy[j] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AllCharactersLifeExpectancy") + ": ", ((LawsManager.laws.get(i).AllCharactersLifeExpectancy[j] > 0) ? "+" : "") + Game.lang.get("YearsX", LawsManager.laws.get(i).AllCharactersLifeExpectancy[j]), Images.council, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (LawsManager.laws.get(i).UnlocksColonization != null) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses("", LawsManager.laws.get(i).UnlocksColonization[j] ? Game.lang.get("ColonizationAllowed") : Game.lang.get("NoColonizationAllowed"), Images.population, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        return menuElements;
    }
    
    public static final boolean adoptReform(final int iCivID, final int lawID, final int lawID2) {
        if (Game.getCiv(iCivID).fLegacy < GameValues.laws.LAW_ADAPT_REFORM_COST_LEGACY_POINTS) {
            return false;
        }
        if (Game.getCiv(iCivID).fGold < GameValues.laws.LAW_ADAPT_REFORM_COST_GOLD) {
            return false;
        }
        if (Game.getCiv(iCivID).laws.get(lawID) == lawID2) {
            return false;
        }
        if (LawsManager.laws.get(lawID).RequiredTechID[lawID2] >= 0 && !Game.getCiv(iCivID).getTechResearched(LawsManager.laws.get(lawID).RequiredTechID[lawID2])) {
            return false;
        }
        if (LawsManager.laws.get(lawID).RequiredGovernmentID != null && LawsManager.laws.get(lawID).RequiredGovernmentID[lawID2] >= 0 && LawsManager.laws.get(lawID).RequiredGovernmentID[lawID2] != Game.getCiv(iCivID).getIdeologyID()) {
            return false;
        }
        final Civilization civ = Game.getCiv(iCivID);
        civ.fGold -= GameValues.laws.LAW_ADAPT_REFORM_COST_GOLD;
        final Civilization civ2 = Game.getCiv(iCivID);
        civ2.fLegacy -= GameValues.laws.LAW_ADAPT_REFORM_COST_LEGACY_POINTS;
        updateCivBonuses(lawID, Game.getCiv(iCivID).laws.get(lawID), iCivID, -1.0f);
        try {
            Game.getCiv(iCivID).adoptReform(lawID, lawID2);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        updateCivBonuses(lawID, Game.getCiv(iCivID).laws.get(lawID), iCivID, 1.0f);
        return true;
    }
    
    public static final int getAvailableLaws(final int iCivID, final int lawID) {
        int out = 0;
        for (int i = 0, iSize = LawsManager.laws.get(lawID).RequiredTechID.length; i < iSize; ++i) {
            if (LawsManager.laws.get(lawID).RequiredTechID[i] < 0 || Game.getCiv(iCivID).getTechResearched(LawsManager.laws.get(lawID).RequiredTechID[i])) {
                if (LawsManager.laws.get(lawID).RequiredGovernmentID == null) {
                    ++out;
                }
                else {
                    try {
                        if (LawsManager.laws.get(lawID).RequiredGovernmentID[i] < 0 || LawsManager.laws.get(lawID).RequiredGovernmentID[i] == Game.getCiv(iCivID).getIdeologyID()) {
                            ++out;
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        return out;
    }
    
    static {
        LawsManager.lawsImages = new ArrayList<Image>();
        LawsManager.laws = new ArrayList<Law>();
        LawsManager.iLawsSize = 0;
    }
    
    public static class ConfigLawData
    {
        public String Age_of_History;
        public ArrayList Law;
    }
    
    public static class Law
    {
        public int[] ImageID;
        public String Title;
        public String[] Law;
        public String[] LawDesc;
        public int[] RequiredTechID;
        public int[] RequiredGovernmentID;
        public float[] MonthlyIncome;
        public float[] MonthlyLegacy;
        public float[] MonthlyLegacy_Percentage;
        public float[] Gold;
        public float[] ConstructionCost;
        public float[] AdministrationBuildingsCost;
        public float[] MilitaryBuildingsCost;
        public float[] EconomyBuildingsCost;
        public float[] ConstructionTime;
        public float[] WonderConstructionCost;
        public float[] TaxEfficiency;
        public float[] ProvinceMaintenance;
        public float[] BuildingsMaintenanceCost;
        public float[] ArmyMaintenance;
        public int[] MaxManpower;
        public float[] MaxManpower_Percentage;
        public float[] ManpowerRecoverySpeed;
        public float[] ReinforcementSpeed;
        public float[] ArmyMovementSpeed;
        public float[] Research;
        public float[] ResearchPoints;
        public float[] MaxMorale;
        public float[] ArmyMoraleRecovery;
        public float[] WarScoreCost;
        public int[] BuildingSlot;
        public int[] MaxInfrastructure;
        public float[] Devastation;
        public float[] GrowthRate;
        public float[] IncomeProduction;
        public float[] ProductionEfficiency;
        public float[] IncomeTaxation;
        public float[] IncomeEconomy;
        public float[] InvestInEconomyCost;
        public float[] IncreaseManpowerCost;
        public float[] IncreaseTaxEfficiencyCost;
        public float[] IncreaseGrowthRateCost;
        public float[] DevelopInfrastructureCost;
        public int[] GeneralAttack;
        public int[] GeneralDefense;
        public int[] UnitsAttack;
        public int[] UnitsDefense;
        public float[] SiegeEffectiveness;
        public float[] ImproveRelationsModifier;
        public float[] IncomeFromVassals;
        public float[] LoanInterest;
        public float[] DiplomacyPoints;
        public float[] CoreCost;
        public float[] ReligionCost;
        public float[] RecruitmentTime;
        public float[] RecruitArmyCost;
        public float[] RecruitArmyFirstLineCost;
        public float[] RecruitArmySecondLineCost;
        public int[] MaxNumOfAlliances;
        public int[] AdvisorMaxLevel;
        public int[] AdvisorPoolSize;
        public int[] MaxNumberOfLoans;
        public int[] MaximumLevelOfTheMilitaryAcademyForGenerals;
        public int[] MaximumLevelOfTheMilitaryAcademy;
        public int[] MaximumLevelOfTheSupremeCourt;
        public int[] MaximumLevelOfCapitalCity;
        public float[] AggressiveExpansion;
        public float[] DiseaseDeathRate;
        public float[] AdvisorCost;
        public float[] GeneralCost;
        public int[] BattleWidth;
        public float[] Discipline;
        public float[] ManpowerRecoveryFromADisbandedArmy;
        public float[] MaximumAmountOfGold;
        public float[] MaximumAmountOfGold_Percentage;
        public float[] Loot;
        public int[] AllCharactersLifeExpectancy;
        public int[] RegimentsLimit;
        public boolean[] UnlocksColonization;
    }
}
