// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.GameValues;
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

public class AdvantagesManager
{
    public static List<String> advantagesGroups;
    public static int iAdvantagesGroupsSize;
    public static List<Image> advantagesImages;
    public static List<Advantage> advantages;
    public static int iAdvantagesSize;
    
    public static final boolean unlockAdvantage(final int iCivID, final int iAdvantageID, final int iLevel) {
        if (Game.getCiv(iCivID).getAdvantagePoints() > 0 && !Game.getCiv(iCivID).haveAdvantage(iAdvantageID, iLevel) && Game.getCiv(iCivID).canUnlockAdvantage(iAdvantageID, iLevel)) {
            Game.getCiv(iCivID).setAdvantagePoints(Math.max(0, Game.getCiv(iCivID).getAdvantagePoints() - 1));
            Game.getCiv(iCivID).addAdvantage(iAdvantageID, iLevel);
            updateCivBonuses(iAdvantageID, iLevel, iCivID);
            return true;
        }
        return false;
    }
    
    public static final boolean unlockAdvantage(final int iCivID, final int iAdvantageID) {
        final int iLevel = Game.getCiv(iCivID).getAdvantageLvl(iAdvantageID) + 1;
        if (Game.getCiv(iCivID).getAdvantagePoints() > 0 && !Game.getCiv(iCivID).haveAdvantage(iAdvantageID, iLevel) && Game.getCiv(iCivID).canUnlockAdvantage(iAdvantageID, iLevel)) {
            Game.getCiv(iCivID).setAdvantagePoints(Math.max(0, Game.getCiv(iCivID).getAdvantagePoints() - 1));
            Game.getCiv(iCivID).addAdvantage(iAdvantageID, iLevel);
            updateCivBonuses(iAdvantageID, iLevel, iCivID);
            return true;
        }
        return false;
    }
    
    public static final void updateCivBonuses(final int i, final int j, final int iCivID) {
        if (j >= AdvantagesManager.advantages.get(i).ImageID.length) {
            return;
        }
        if (AdvantagesManager.advantages.get(i).TaxEfficiency != null) {
            final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
            civBonuses.TaxEfficiency += AdvantagesManager.advantages.get(i).TaxEfficiency[j];
        }
        if (AdvantagesManager.advantages.get(i).ProvinceMaintenance != null) {
            final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
            civBonuses2.ProvinceMaintenance += AdvantagesManager.advantages.get(i).ProvinceMaintenance[j];
        }
        if (AdvantagesManager.advantages.get(i).BuildingsMaintenanceCost != null) {
            final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
            civBonuses3.BuildingsMaintenanceCost += AdvantagesManager.advantages.get(i).BuildingsMaintenanceCost[j];
        }
        if (AdvantagesManager.advantages.get(i).ConstructionTime != null) {
            final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
            civBonuses4.ConstructionTime += AdvantagesManager.advantages.get(i).ConstructionTime[j];
        }
        if (AdvantagesManager.advantages.get(i).ConstructionCost != null) {
            final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
            civBonuses5.ConstructionCost += AdvantagesManager.advantages.get(i).ConstructionCost[j];
        }
        if (AdvantagesManager.advantages.get(i).AdministrationBuildingsCost != null) {
            final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
            civBonuses6.AdministrationBuildingsCost += AdvantagesManager.advantages.get(i).AdministrationBuildingsCost[j];
        }
        if (AdvantagesManager.advantages.get(i).MilitaryBuildingsCost != null) {
            final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
            civBonuses7.MilitaryBuildingsCost += AdvantagesManager.advantages.get(i).MilitaryBuildingsCost[j];
        }
        if (AdvantagesManager.advantages.get(i).EconomyBuildingsCost != null) {
            final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
            civBonuses8.EconomyBuildingsCost += AdvantagesManager.advantages.get(i).EconomyBuildingsCost[j];
        }
        if (AdvantagesManager.advantages.get(i).WonderConstructionCost != null) {
            final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
            civBonuses9.WonderConstructionCost += AdvantagesManager.advantages.get(i).WonderConstructionCost[j];
        }
        if (AdvantagesManager.advantages.get(i).MaxManpower != null) {
            final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
            civBonuses10.MaxManpower += AdvantagesManager.advantages.get(i).MaxManpower[j];
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).ManpowerRecoverySpeed != null) {
            final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
            civBonuses11.ManpowerRecoverySpeed += AdvantagesManager.advantages.get(i).ManpowerRecoverySpeed[j];
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).ArmyMoraleRecovery != null) {
            final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
            civBonuses12.ArmyMoraleRecovery += AdvantagesManager.advantages.get(i).ArmyMoraleRecovery[j];
        }
        if (AdvantagesManager.advantages.get(i).WarScoreCost != null) {
            final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
            civBonuses13.WarScoreCost += AdvantagesManager.advantages.get(i).WarScoreCost[j];
        }
        if (AdvantagesManager.advantages.get(i).Research != null) {
            final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
            civBonuses14.Research += AdvantagesManager.advantages.get(i).Research[j];
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).ResearchPoints != null) {
            final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
            civBonuses15.ResearchPoints += AdvantagesManager.advantages.get(i).ResearchPoints[j];
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).Devastation != null) {
            final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
            civBonuses16.Devastation += AdvantagesManager.advantages.get(i).Devastation[j];
        }
        if (AdvantagesManager.advantages.get(i).BuildingSlot != null) {
            final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
            civBonuses17.BuildingSlot += AdvantagesManager.advantages.get(i).BuildingSlot[j];
            Game.getCiv(iCivID).updateBuildingLimit();
        }
        if (AdvantagesManager.advantages.get(i).MaxInfrastructure != null) {
            final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
            civBonuses18.MaxInfrastructure += AdvantagesManager.advantages.get(i).MaxInfrastructure[j];
            Game.getCiv(iCivID).updateInfrastructureMax();
        }
        if (AdvantagesManager.advantages.get(i).MonthlyIncome != null) {
            final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
            civBonuses19.MonthlyIncome += AdvantagesManager.advantages.get(i).MonthlyIncome[j];
        }
        if (AdvantagesManager.advantages.get(i).Gold != null) {
            final Civilization civ = Game.getCiv(iCivID);
            civ.fGold += AdvantagesManager.advantages.get(i).Gold[j];
        }
        if (AdvantagesManager.advantages.get(i).MonthlyLegacy != null) {
            final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
            civBonuses20.MonthlyLegacy += AdvantagesManager.advantages.get(i).MonthlyLegacy[j];
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).GrowthRate != null) {
            final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
            civBonuses21.GrowthRate += AdvantagesManager.advantages.get(i).GrowthRate[j];
            Game.getCiv(iCivID).updateResearchPerMonth();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).IncomeProduction != null) {
            final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
            civBonuses22.IncomeProduction += AdvantagesManager.advantages.get(i).IncomeProduction[j];
        }
        if (AdvantagesManager.advantages.get(i).ProductionEfficiency != null) {
            final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
            civBonuses23.ProductionEfficiency += AdvantagesManager.advantages.get(i).ProductionEfficiency[j];
        }
        if (AdvantagesManager.advantages.get(i).IncreaseManpowerCost != null) {
            final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
            civBonuses24.IncreaseManpowerCost += AdvantagesManager.advantages.get(i).IncreaseManpowerCost[j];
        }
        if (AdvantagesManager.advantages.get(i).InvestInEconomyCost != null) {
            final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
            civBonuses25.InvestInEconomyCost += AdvantagesManager.advantages.get(i).InvestInEconomyCost[j];
        }
        if (AdvantagesManager.advantages.get(i).IncreaseTaxEfficiencyCost != null) {
            final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
            civBonuses26.IncreaseTaxEfficiencyCost += AdvantagesManager.advantages.get(i).IncreaseTaxEfficiencyCost[j];
        }
        if (AdvantagesManager.advantages.get(i).IncreaseGrowthRateCost != null) {
            final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
            civBonuses27.IncreaseGrowthRateCost += AdvantagesManager.advantages.get(i).IncreaseGrowthRateCost[j];
        }
        if (AdvantagesManager.advantages.get(i).DevelopInfrastructureCost != null) {
            final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
            civBonuses28.DevelopInfrastructureCost += AdvantagesManager.advantages.get(i).DevelopInfrastructureCost[j];
        }
        if (AdvantagesManager.advantages.get(i).GeneralAttack != null) {
            final CivilizationBonuses civBonuses29 = Game.getCiv(iCivID).civBonuses;
            civBonuses29.GeneralAttack += AdvantagesManager.advantages.get(i).GeneralAttack[j];
        }
        if (AdvantagesManager.advantages.get(i).GeneralDefense != null) {
            final CivilizationBonuses civBonuses30 = Game.getCiv(iCivID).civBonuses;
            civBonuses30.GeneralDefense += AdvantagesManager.advantages.get(i).GeneralDefense[j];
        }
        if (AdvantagesManager.advantages.get(i).UnitsAttack != null) {
            final CivilizationBonuses civBonuses31 = Game.getCiv(iCivID).civBonuses;
            civBonuses31.UnitsAttack += AdvantagesManager.advantages.get(i).UnitsAttack[j];
        }
        if (AdvantagesManager.advantages.get(i).UnitsDefense != null) {
            final CivilizationBonuses civBonuses32 = Game.getCiv(iCivID).civBonuses;
            civBonuses32.UnitsDefense += AdvantagesManager.advantages.get(i).UnitsDefense[j];
        }
        if (AdvantagesManager.advantages.get(i).MaxMorale != null) {
            final CivilizationBonuses civBonuses33 = Game.getCiv(iCivID).civBonuses;
            civBonuses33.MaxMorale += AdvantagesManager.advantages.get(i).MaxMorale[j];
        }
        if (AdvantagesManager.advantages.get(i).ArmyMovementSpeed != null) {
            final CivilizationBonuses civBonuses34 = Game.getCiv(iCivID).civBonuses;
            civBonuses34.ArmyMovementSpeed += AdvantagesManager.advantages.get(i).ArmyMovementSpeed[j];
        }
        if (AdvantagesManager.advantages.get(i).SiegeEffectiveness != null) {
            final CivilizationBonuses civBonuses35 = Game.getCiv(iCivID).civBonuses;
            civBonuses35.SiegeEffectiveness += AdvantagesManager.advantages.get(i).SiegeEffectiveness[j];
        }
        if (AdvantagesManager.advantages.get(i).ImproveRelationsModifier != null) {
            final CivilizationBonuses civBonuses36 = Game.getCiv(iCivID).civBonuses;
            civBonuses36.ImproveRelationsModifier += AdvantagesManager.advantages.get(i).ImproveRelationsModifier[j];
        }
        if (AdvantagesManager.advantages.get(i).IncomeFromVassals != null) {
            final CivilizationBonuses civBonuses37 = Game.getCiv(iCivID).civBonuses;
            civBonuses37.IncomeFromVassals += AdvantagesManager.advantages.get(i).IncomeFromVassals[j];
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).DiplomacyPoints != null) {
            final CivilizationBonuses civBonuses38 = Game.getCiv(iCivID).civBonuses;
            civBonuses38.DiplomacyPoints += AdvantagesManager.advantages.get(i).DiplomacyPoints[j];
            Game.getCiv(iCivID).updateDiplomacyPerMonth();
        }
        if (AdvantagesManager.advantages.get(i).LoanInterest != null) {
            final CivilizationBonuses civBonuses39 = Game.getCiv(iCivID).civBonuses;
            civBonuses39.LoanInterest += AdvantagesManager.advantages.get(i).LoanInterest[j];
        }
        if (AdvantagesManager.advantages.get(i).MaxNumberOfLoans != null) {
            final CivilizationBonuses civBonuses40 = Game.getCiv(iCivID).civBonuses;
            civBonuses40.MaxNumberOfLoans += AdvantagesManager.advantages.get(i).MaxNumberOfLoans[j];
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
            final CivilizationBonuses civBonuses41 = Game.getCiv(iCivID).civBonuses;
            civBonuses41.MaximumLevelOfTheMilitaryAcademyForGenerals += AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j];
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademy != null) {
            final CivilizationBonuses civBonuses42 = Game.getCiv(iCivID).civBonuses;
            civBonuses42.MaximumLevelOfTheMilitaryAcademy += AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademy[j];
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheSupremeCourt != null) {
            final CivilizationBonuses civBonuses43 = Game.getCiv(iCivID).civBonuses;
            civBonuses43.MaximumLevelOfTheSupremeCourt += AdvantagesManager.advantages.get(i).MaximumLevelOfTheSupremeCourt[j];
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfCapitalCity != null) {
            final CivilizationBonuses civBonuses44 = Game.getCiv(iCivID).civBonuses;
            civBonuses44.MaximumLevelOfCapitalCity += AdvantagesManager.advantages.get(i).MaximumLevelOfCapitalCity[j];
        }
        if (AdvantagesManager.advantages.get(i).RecruitmentTime != null) {
            final CivilizationBonuses civBonuses45 = Game.getCiv(iCivID).civBonuses;
            civBonuses45.RecruitmentTime += AdvantagesManager.advantages.get(i).RecruitmentTime[j];
        }
        if (AdvantagesManager.advantages.get(i).CoreCost != null) {
            final CivilizationBonuses civBonuses46 = Game.getCiv(iCivID).civBonuses;
            civBonuses46.CoreCost += AdvantagesManager.advantages.get(i).CoreCost[j];
        }
        if (AdvantagesManager.advantages.get(i).ReligionCost != null) {
            final CivilizationBonuses civBonuses47 = Game.getCiv(iCivID).civBonuses;
            civBonuses47.ReligionCost += AdvantagesManager.advantages.get(i).ReligionCost[j];
        }
        if (AdvantagesManager.advantages.get(i).RecruitArmyCost != null) {
            final CivilizationBonuses civBonuses48 = Game.getCiv(iCivID).civBonuses;
            civBonuses48.RecruitArmyCost += AdvantagesManager.advantages.get(i).RecruitArmyCost[j];
        }
        if (AdvantagesManager.advantages.get(i).RecruitArmyFirstLineCost != null) {
            final CivilizationBonuses civBonuses49 = Game.getCiv(iCivID).civBonuses;
            civBonuses49.RecruitArmyFirstLineCost += AdvantagesManager.advantages.get(i).RecruitArmyFirstLineCost[j];
        }
        if (AdvantagesManager.advantages.get(i).RecruitArmySecondLineCost != null) {
            final CivilizationBonuses civBonuses50 = Game.getCiv(iCivID).civBonuses;
            civBonuses50.RecruitArmySecondLineCost += AdvantagesManager.advantages.get(i).RecruitArmySecondLineCost[j];
        }
        if (AdvantagesManager.advantages.get(i).ArmyMaintenance != null) {
            final CivilizationBonuses civBonuses51 = Game.getCiv(iCivID).civBonuses;
            civBonuses51.ArmyMaintenance += AdvantagesManager.advantages.get(i).ArmyMaintenance[j];
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }
        if (AdvantagesManager.advantages.get(i).MaxNumOfAlliances != null) {
            final CivilizationBonuses civBonuses52 = Game.getCiv(iCivID).civBonuses;
            civBonuses52.MaxNumOfAlliances += AdvantagesManager.advantages.get(i).MaxNumOfAlliances[j];
        }
        if (AdvantagesManager.advantages.get(i).AdvisorMaxLevel != null) {
            final CivilizationBonuses civBonuses53 = Game.getCiv(iCivID).civBonuses;
            civBonuses53.AdvisorMaxLevel += AdvantagesManager.advantages.get(i).AdvisorMaxLevel[j];
        }
        if (AdvantagesManager.advantages.get(i).AdvisorPoolSize != null) {
            final CivilizationBonuses civBonuses54 = Game.getCiv(iCivID).civBonuses;
            civBonuses54.AdvisorPoolSize += AdvantagesManager.advantages.get(i).AdvisorPoolSize[j];
        }
        if (AdvantagesManager.advantages.get(i).AdvisorCost != null) {
            final CivilizationBonuses civBonuses55 = Game.getCiv(iCivID).civBonuses;
            civBonuses55.AdvisorCost += AdvantagesManager.advantages.get(i).AdvisorCost[j];
        }
        if (AdvantagesManager.advantages.get(i).GeneralCost != null) {
            final CivilizationBonuses civBonuses56 = Game.getCiv(iCivID).civBonuses;
            civBonuses56.GeneralCost += AdvantagesManager.advantages.get(i).GeneralCost[j];
        }
        if (AdvantagesManager.advantages.get(i).AggressiveExpansion != null) {
            final CivilizationBonuses civBonuses57 = Game.getCiv(iCivID).civBonuses;
            civBonuses57.AggressiveExpansion += AdvantagesManager.advantages.get(i).AggressiveExpansion[j];
        }
        if (AdvantagesManager.advantages.get(i).DiseaseDeathRate != null) {
            final CivilizationBonuses civBonuses58 = Game.getCiv(iCivID).civBonuses;
            civBonuses58.DiseaseDeathRate += AdvantagesManager.advantages.get(i).DiseaseDeathRate[j];
        }
        if (AdvantagesManager.advantages.get(i).ManpowerRecoveryFromADisbandedArmy != null) {
            final CivilizationBonuses civBonuses59 = Game.getCiv(iCivID).civBonuses;
            civBonuses59.ManpowerRecoveryFromADisbandedArmy += AdvantagesManager.advantages.get(i).ManpowerRecoveryFromADisbandedArmy[j];
        }
        if (AdvantagesManager.advantages.get(i).BattleWidth != null) {
            final CivilizationBonuses civBonuses60 = Game.getCiv(iCivID).civBonuses;
            civBonuses60.BattleWidth += AdvantagesManager.advantages.get(i).BattleWidth[j];
        }
        if (AdvantagesManager.advantages.get(i).AllCharactersLifeExpectancy != null) {
            final CivilizationBonuses civBonuses61 = Game.getCiv(iCivID).civBonuses;
            civBonuses61.AllCharactersLifeExpectancy += AdvantagesManager.advantages.get(i).AllCharactersLifeExpectancy[j];
        }
        if (AdvantagesManager.advantages.get(i).Discipline != null) {
            final CivilizationBonuses civBonuses62 = Game.getCiv(iCivID).civBonuses;
            civBonuses62.Discipline += AdvantagesManager.advantages.get(i).Discipline[j];
        }
        if (AdvantagesManager.advantages.get(i).MaximumAmountOfGold != null) {
            final CivilizationBonuses civBonuses63 = Game.getCiv(iCivID).civBonuses;
            civBonuses63.MaximumAmountOfGold += AdvantagesManager.advantages.get(i).MaximumAmountOfGold[j];
        }
        if (AdvantagesManager.advantages.get(i).MaximumAmountOfGold_Percentage != null) {
            final CivilizationBonuses civBonuses64 = Game.getCiv(iCivID).civBonuses;
            civBonuses64.MaximumAmountOfGold_Percentage += AdvantagesManager.advantages.get(i).MaximumAmountOfGold_Percentage[j];
        }
        if (AdvantagesManager.advantages.get(i).Loot != null) {
            final CivilizationBonuses civBonuses65 = Game.getCiv(iCivID).civBonuses;
            civBonuses65.Loot += AdvantagesManager.advantages.get(i).Loot[j];
        }
        if (AdvantagesManager.advantages.get(i).RegimentsLimit != null) {
            final CivilizationBonuses civBonuses66 = Game.getCiv(iCivID).civBonuses;
            civBonuses66.RegimentsLimit += AdvantagesManager.advantages.get(i).RegimentsLimit[j];
            Game.getCiv(iCivID).updateRegimentsLimit();
        }
        Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
    }
    
    public static final void loadAdvantages() {
        final FileHandle tempFileT = FileManager.loadFile("game/advantages/AdvantagesGroups.txt");
        final String[] tGroups = tempFileT.readString().split(";");
        for (int i = 0; i < tGroups.length; ++i) {
            AdvantagesManager.advantagesGroups.add(Game.lang.get(tGroups[i]));
        }
        AdvantagesManager.iAdvantagesGroupsSize = AdvantagesManager.advantagesGroups.size();
        try {
            final FileHandle fileList = FileManager.loadFile("game/advantages/Advantages.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigAdvantageData.class, "Advantage", (Class)Advantage.class);
            final ConfigAdvantageData data = (ConfigAdvantageData)json.fromJson((Class)ConfigAdvantageData.class, fileContent);
            for (final Object e : data.Advantage) {
                AdvantagesManager.advantages.add((Advantage)e);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        AdvantagesManager.iAdvantagesSize = AdvantagesManager.advantages.size();
        loadAdvantagesImages();
    }
    
    public static final void loadAdvantagesImages() {
        final FileHandle tempFileT = FileManager.loadFile("game/advantages/advantagesImages/numOfImages.txt");
        for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
            if (FileManager.loadFile("game/advantages/advantagesImages/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                AdvantagesManager.advantagesImages.add(new Image(ImageManager.loadTexture_RGB888("game/advantages/advantagesImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
            else {
                AdvantagesManager.advantagesImages.add(new Image(ImageManager.loadTexture_RGB888("game/advantages/advantagesImages/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
        }
    }
    
    public static final void initRandomAdvantages() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            final List<Integer> addR = getRandomAdvantages();
            for (int j = 0, jSize = addR.size(); j < jSize; ++j) {
                Game.getCiv(i).addAdvantage(addR.get(j), 0);
                updateCivBonuses(addR.get(j), 0, i);
            }
        }
    }
    
    public static final void initAdvantagePoints() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).setAdvantagePoints(GameValues.advantages.ADVANTAGE_POINTS_START_GAME + ((GameValues.advantages.ADVANTAGE_POINTS_START_GAME_RANDOM > 0) ? Game.oR.nextInt(GameValues.advantages.ADVANTAGE_POINTS_START_GAME_RANDOM) : 0));
        }
    }
    
    private static final List<Integer> getRandomAdvantages() {
        final List<Integer> out = new ArrayList<Integer>();
        int iStack = 0;
        int tNum = GameValues.advantages.ADVANTAGES_RANDOM_INIT;
        if (GameValues.advantages.ADVANTAGES_RANDOM_INIT_RANDOM > 1) {
            tNum += Game.oR.nextInt(GameValues.advantages.ADVANTAGES_RANDOM_INIT_RANDOM);
        }
        while (iStack++ < 49 && out.size() < tNum) {
            final int iR = Game.oR.nextInt(AdvantagesManager.iAdvantagesSize);
            if (!out.contains(iR)) {
                out.add(iR);
            }
        }
        return out;
    }
    
    public static String getAdvantageName(final int i) {
        if (AdvantagesManager.advantages.get(i).ConstructionCost != null) {
            return Game.lang.get("ConstructionCost");
        }
        if (AdvantagesManager.advantages.get(i).AdministrationBuildingsCost != null) {
            return Game.lang.get("AdministrationBuildingsCost");
        }
        if (AdvantagesManager.advantages.get(i).MilitaryBuildingsCost != null) {
            return Game.lang.get("MilitaryBuildingsCost");
        }
        if (AdvantagesManager.advantages.get(i).EconomyBuildingsCost != null) {
            return Game.lang.get("EconomyBuildingsCost");
        }
        if (AdvantagesManager.advantages.get(i).ConstructionTime != null) {
            return Game.lang.get("ConstructionTime");
        }
        if (AdvantagesManager.advantages.get(i).WonderConstructionCost != null) {
            return Game.lang.get("WonderConstructionCost");
        }
        if (AdvantagesManager.advantages.get(i).TaxEfficiency != null) {
            return Game.lang.get("TaxEfficiency");
        }
        if (AdvantagesManager.advantages.get(i).ProvinceMaintenance != null) {
            return Game.lang.get("ProvinceMaintenance");
        }
        if (AdvantagesManager.advantages.get(i).BuildingsMaintenanceCost != null) {
            return Game.lang.get("BuildingsMaintenanceCost");
        }
        if (AdvantagesManager.advantages.get(i).ManpowerRecoverySpeed != null) {
            return Game.lang.get("ManpowerRecoverySpeed");
        }
        if (AdvantagesManager.advantages.get(i).ArmyMoraleRecovery != null) {
            return Game.lang.get("ArmyMoraleRecovery");
        }
        if (AdvantagesManager.advantages.get(i).WarScoreCost != null) {
            return Game.lang.get("WarScoreCost");
        }
        if (AdvantagesManager.advantages.get(i).ReinforcementSpeed != null) {
            return Game.lang.get("ReinforcementSpeed");
        }
        if (AdvantagesManager.advantages.get(i).MaxManpower != null) {
            return Game.lang.get("MaximumManpower");
        }
        if (AdvantagesManager.advantages.get(i).Research != null) {
            return Game.lang.get("Research");
        }
        if (AdvantagesManager.advantages.get(i).ResearchPoints != null) {
            return Game.lang.get("ResearchPerMonth");
        }
        if (AdvantagesManager.advantages.get(i).BuildingSlot != null) {
            return Game.lang.get("AdditionalBuildingsInProvince");
        }
        if (AdvantagesManager.advantages.get(i).MaxInfrastructure != null) {
            return Game.lang.get("MaximumInfrastructureLevel");
        }
        if (AdvantagesManager.advantages.get(i).Devastation != null) {
            return Game.lang.get("Devastation");
        }
        if (AdvantagesManager.advantages.get(i).GrowthRate != null) {
            return Game.lang.get("GrowthRate");
        }
        if (AdvantagesManager.advantages.get(i).MonthlyIncome != null) {
            return Game.lang.get("MonthlyIncome");
        }
        if (AdvantagesManager.advantages.get(i).Gold != null) {
            return Game.lang.get("Gold");
        }
        if (AdvantagesManager.advantages.get(i).MonthlyLegacy != null) {
            return Game.lang.get("MonthlyLegacy");
        }
        if (AdvantagesManager.advantages.get(i).IncomeProduction != null) {
            return Game.lang.get("IncomeProduction");
        }
        if (AdvantagesManager.advantages.get(i).ProductionEfficiency != null) {
            return Game.lang.get("ProductionEfficiency");
        }
        if (AdvantagesManager.advantages.get(i).InvestInEconomyCost != null) {
            return Game.lang.get("InvestInEconomyCost");
        }
        if (AdvantagesManager.advantages.get(i).IncreaseManpowerCost != null) {
            return Game.lang.get("IncreaseManpowerCost");
        }
        if (AdvantagesManager.advantages.get(i).IncreaseTaxEfficiencyCost != null) {
            return Game.lang.get("IncreaseTaxEfficiencyCost");
        }
        if (AdvantagesManager.advantages.get(i).IncreaseGrowthRateCost != null) {
            return Game.lang.get("IncreaseGrowthRateCost");
        }
        if (AdvantagesManager.advantages.get(i).DevelopInfrastructureCost != null) {
            return Game.lang.get("DevelopInfrastructureCost");
        }
        if (AdvantagesManager.advantages.get(i).GeneralAttack != null) {
            return Game.lang.get("GeneralsAttack");
        }
        if (AdvantagesManager.advantages.get(i).GeneralDefense != null) {
            return Game.lang.get("GeneralsDefense");
        }
        if (AdvantagesManager.advantages.get(i).UnitsAttack != null) {
            return Game.lang.get("UnitsAttack");
        }
        if (AdvantagesManager.advantages.get(i).UnitsDefense != null) {
            return Game.lang.get("UnitsDefense");
        }
        if (AdvantagesManager.advantages.get(i).MaxMorale != null) {
            return Game.lang.get("MaxMorale");
        }
        if (AdvantagesManager.advantages.get(i).ArmyMovementSpeed != null) {
            return Game.lang.get("ArmyMovementSpeed");
        }
        if (AdvantagesManager.advantages.get(i).SiegeEffectiveness != null) {
            return Game.lang.get("SiegeEffectiveness");
        }
        if (AdvantagesManager.advantages.get(i).ImproveRelationsModifier != null) {
            return Game.lang.get("ImproveRelationsModifier");
        }
        if (AdvantagesManager.advantages.get(i).IncomeFromVassals != null) {
            return Game.lang.get("IncomeFromVassals");
        }
        if (AdvantagesManager.advantages.get(i).LoanInterest != null) {
            return Game.lang.get("LoanInterest");
        }
        if (AdvantagesManager.advantages.get(i).DiplomacyPoints != null) {
            return Game.lang.get("DiplomacyPoints");
        }
        if (AdvantagesManager.advantages.get(i).RecruitmentTime != null) {
            return Game.lang.get("RecruitmentTime");
        }
        if (AdvantagesManager.advantages.get(i).RecruitArmyCost != null) {
            return Game.lang.get("ArmyRecruitmentCost");
        }
        if (AdvantagesManager.advantages.get(i).RecruitArmyFirstLineCost != null) {
            return Game.lang.get("FirstLineArmyRecruitmentCost");
        }
        if (AdvantagesManager.advantages.get(i).RecruitArmySecondLineCost != null) {
            return Game.lang.get("SecondLineArmyRecruitmentCost");
        }
        if (AdvantagesManager.advantages.get(i).ArmyMaintenance != null) {
            return Game.lang.get("ArmyMaintenance");
        }
        if (AdvantagesManager.advantages.get(i).CoreCost != null) {
            return Game.lang.get("CoreConstruction");
        }
        if (AdvantagesManager.advantages.get(i).ReligionCost != null) {
            return Game.lang.get("ReligionConversionCost");
        }
        if (AdvantagesManager.advantages.get(i).MaxNumOfAlliances != null) {
            return Game.lang.get("MaxNumOfAlliances");
        }
        if (AdvantagesManager.advantages.get(i).AdvisorMaxLevel != null) {
            return Game.lang.get("MaximumAdvisorSkillLevel");
        }
        if (AdvantagesManager.advantages.get(i).AdvisorPoolSize != null) {
            return Game.lang.get("AdvisorPool");
        }
        if (AdvantagesManager.advantages.get(i).MaxNumberOfLoans != null) {
            return Game.lang.get("MaximumNumberOfLoans");
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
            return Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals");
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademy != null) {
            return Game.lang.get("MaximumLevelOfTheMilitaryAcademy");
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheSupremeCourt != null) {
            return Game.lang.get("MaximumLevelOfTheSupremeCourt");
        }
        if (AdvantagesManager.advantages.get(i).MaximumLevelOfCapitalCity != null) {
            return Game.lang.get("MaximumLevelOfCapitalCity");
        }
        if (AdvantagesManager.advantages.get(i).AggressiveExpansion != null) {
            return Game.lang.get("AggressiveExpansion");
        }
        if (AdvantagesManager.advantages.get(i).DiseaseDeathRate != null) {
            return Game.lang.get("DiseasesDeathRate");
        }
        if (AdvantagesManager.advantages.get(i).ManpowerRecoveryFromADisbandedArmy != null) {
            return Game.lang.get("ManpowerRecoveryFromADisbandedArmy");
        }
        if (AdvantagesManager.advantages.get(i).AdvisorCost != null) {
            return Game.lang.get("AdvisorCost");
        }
        if (AdvantagesManager.advantages.get(i).GeneralCost != null) {
            return Game.lang.get("GeneralCost");
        }
        if (AdvantagesManager.advantages.get(i).Discipline != null) {
            return Game.lang.get("Discipline");
        }
        if (AdvantagesManager.advantages.get(i).MaximumAmountOfGold != null) {
            return Game.lang.get("MaximumAmountOfGold");
        }
        if (AdvantagesManager.advantages.get(i).MaximumAmountOfGold_Percentage != null) {
            return Game.lang.get("MaximumAmountOfGold");
        }
        if (AdvantagesManager.advantages.get(i).Loot != null) {
            return Game.lang.get("Loot");
        }
        if (AdvantagesManager.advantages.get(i).BattleWidth != null) {
            return Game.lang.get("BattleWidth");
        }
        if (AdvantagesManager.advantages.get(i).RegimentsLimit != null) {
            return Game.lang.get("RegimentsLimit");
        }
        if (AdvantagesManager.advantages.get(i).AllCharactersLifeExpectancy != null) {
            return Game.lang.get("AllCharactersLifeExpectancy");
        }
        return "--";
    }
    
    static {
        AdvantagesManager.advantagesGroups = new ArrayList<String>();
        AdvantagesManager.iAdvantagesGroupsSize = 0;
        AdvantagesManager.advantagesImages = new ArrayList<Image>();
        AdvantagesManager.advantages = new ArrayList<Advantage>();
        AdvantagesManager.iAdvantagesSize = 0;
    }
    
    public static class ConfigAdvantageData
    {
        public String Age_of_History;
        public ArrayList Advantage;
    }
    
    public static class Advantage
    {
        public int[] ImageID;
        public int GroupID;
        public int RequiredTechID;
        public int AI;
        public float[] MonthlyIncome;
        public float[] MonthlyLegacy;
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
    }
}
