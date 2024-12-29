// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class LegacyManager
{
    public static List<String> legacyGroups;
    public static int iLegacyGroupsSize;
    public static List<Image> legacyImages;
    public static List<Legacy> legacies;
    public static int iLegaciesSize;
    public static int minLegacyCost;
    public static List<Integer> numOfLegaciesInGroup;
    
    public static final void updateCivBonuses(final int i, final int level, final int iCivID) {
        updateCivBonuses(i, level, iCivID, false);
    }
    
    public static final void updateCivBonuses(final int i, final int level, final int iCivID, final boolean load) {
        if (LegacyManager.legacies.get(i).TaxEfficiency != null) {
            final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
            civBonuses.TaxEfficiency += LegacyManager.legacies.get(i).TaxEfficiency[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
                civBonuses2.TaxEfficiency -= LegacyManager.legacies.get(i).TaxEfficiency[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).ProvinceMaintenance != null) {
            final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
            civBonuses3.ProvinceMaintenance += LegacyManager.legacies.get(i).ProvinceMaintenance[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
                civBonuses4.ProvinceMaintenance -= LegacyManager.legacies.get(i).ProvinceMaintenance[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).BuildingsMaintenanceCost != null) {
            final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
            civBonuses5.BuildingsMaintenanceCost += LegacyManager.legacies.get(i).BuildingsMaintenanceCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
                civBonuses6.BuildingsMaintenanceCost -= LegacyManager.legacies.get(i).BuildingsMaintenanceCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).ConstructionTime != null) {
            final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
            civBonuses7.ConstructionTime += LegacyManager.legacies.get(i).ConstructionTime[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
                civBonuses8.ConstructionTime -= LegacyManager.legacies.get(i).ConstructionTime[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).ConstructionCost != null) {
            final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
            civBonuses9.ConstructionCost += LegacyManager.legacies.get(i).ConstructionCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
                civBonuses10.ConstructionCost -= LegacyManager.legacies.get(i).ConstructionCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).AdministrationBuildingsCost != null) {
            final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
            civBonuses11.AdministrationBuildingsCost += LegacyManager.legacies.get(i).AdministrationBuildingsCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
                civBonuses12.AdministrationBuildingsCost -= LegacyManager.legacies.get(i).AdministrationBuildingsCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MilitaryBuildingsCost != null) {
            final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
            civBonuses13.MilitaryBuildingsCost += LegacyManager.legacies.get(i).MilitaryBuildingsCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
                civBonuses14.MilitaryBuildingsCost -= LegacyManager.legacies.get(i).MilitaryBuildingsCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).EconomyBuildingsCost != null) {
            final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
            civBonuses15.EconomyBuildingsCost += LegacyManager.legacies.get(i).EconomyBuildingsCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
                civBonuses16.EconomyBuildingsCost -= LegacyManager.legacies.get(i).EconomyBuildingsCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).WonderConstructionCost != null) {
            final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
            civBonuses17.WonderConstructionCost += LegacyManager.legacies.get(i).WonderConstructionCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
                civBonuses18.WonderConstructionCost -= LegacyManager.legacies.get(i).WonderConstructionCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaxManpower != null) {
            final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
            civBonuses19.MaxManpower += LegacyManager.legacies.get(i).MaxManpower[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
                civBonuses20.MaxManpower -= LegacyManager.legacies.get(i).MaxManpower[level - 1];
            }
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (LegacyManager.legacies.get(i).ManpowerRecoverySpeed != null) {
            final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
            civBonuses21.ManpowerRecoverySpeed += LegacyManager.legacies.get(i).ManpowerRecoverySpeed[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
                civBonuses22.ManpowerRecoverySpeed -= LegacyManager.legacies.get(i).ManpowerRecoverySpeed[level - 1];
            }
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (LegacyManager.legacies.get(i).Research != null) {
            final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
            civBonuses23.Research += LegacyManager.legacies.get(i).Research[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
                civBonuses24.Research -= LegacyManager.legacies.get(i).Research[level - 1];
            }
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LegacyManager.legacies.get(i).ResearchPoints != null) {
            final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
            civBonuses25.ResearchPoints += LegacyManager.legacies.get(i).ResearchPoints[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
                civBonuses26.ResearchPoints -= LegacyManager.legacies.get(i).ResearchPoints[level - 1];
            }
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
        }
        if (LegacyManager.legacies.get(i).Devastation != null) {
            final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
            civBonuses27.Devastation += LegacyManager.legacies.get(i).Devastation[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
                civBonuses28.Devastation -= LegacyManager.legacies.get(i).Devastation[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).BuildingSlot != null) {
            final CivilizationBonuses civBonuses29 = Game.getCiv(iCivID).civBonuses;
            civBonuses29.BuildingSlot += LegacyManager.legacies.get(i).BuildingSlot[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses30 = Game.getCiv(iCivID).civBonuses;
                civBonuses30.BuildingSlot -= LegacyManager.legacies.get(i).BuildingSlot[level - 1];
            }
            Game.getCiv(iCivID).updateBuildingLimit();
        }
        if (LegacyManager.legacies.get(i).MaxInfrastructure != null) {
            final CivilizationBonuses civBonuses31 = Game.getCiv(iCivID).civBonuses;
            civBonuses31.MaxInfrastructure += LegacyManager.legacies.get(i).MaxInfrastructure[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses32 = Game.getCiv(iCivID).civBonuses;
                civBonuses32.MaxInfrastructure -= LegacyManager.legacies.get(i).MaxInfrastructure[level - 1];
            }
            Game.getCiv(iCivID).updateInfrastructureMax();
        }
        if (LegacyManager.legacies.get(i).GrowthRate != null) {
            final CivilizationBonuses civBonuses33 = Game.getCiv(iCivID).civBonuses;
            civBonuses33.GrowthRate += LegacyManager.legacies.get(i).GrowthRate[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses34 = Game.getCiv(iCivID).civBonuses;
                civBonuses34.GrowthRate -= LegacyManager.legacies.get(i).GrowthRate[level - 1];
            }
            Game.getCiv(iCivID).updateResearchPerMonth();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LegacyManager.legacies.get(i).IncomeProduction != null) {
            final CivilizationBonuses civBonuses35 = Game.getCiv(iCivID).civBonuses;
            civBonuses35.IncomeProduction += LegacyManager.legacies.get(i).IncomeProduction[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses36 = Game.getCiv(iCivID).civBonuses;
                civBonuses36.IncomeProduction -= LegacyManager.legacies.get(i).IncomeProduction[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).ProductionEfficiency != null) {
            final CivilizationBonuses civBonuses37 = Game.getCiv(iCivID).civBonuses;
            civBonuses37.ProductionEfficiency += LegacyManager.legacies.get(i).ProductionEfficiency[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses38 = Game.getCiv(iCivID).civBonuses;
                civBonuses38.ProductionEfficiency -= LegacyManager.legacies.get(i).ProductionEfficiency[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).InvestInEconomyCost != null) {
            final CivilizationBonuses civBonuses39 = Game.getCiv(iCivID).civBonuses;
            civBonuses39.InvestInEconomyCost += LegacyManager.legacies.get(i).InvestInEconomyCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses40 = Game.getCiv(iCivID).civBonuses;
                civBonuses40.InvestInEconomyCost -= LegacyManager.legacies.get(i).InvestInEconomyCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).IncreaseTaxEfficiencyCost != null) {
            final CivilizationBonuses civBonuses41 = Game.getCiv(iCivID).civBonuses;
            civBonuses41.IncreaseTaxEfficiencyCost += LegacyManager.legacies.get(i).IncreaseTaxEfficiencyCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses42 = Game.getCiv(iCivID).civBonuses;
                civBonuses42.IncreaseTaxEfficiencyCost -= LegacyManager.legacies.get(i).IncreaseTaxEfficiencyCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).IncreaseGrowthRateCost != null) {
            final CivilizationBonuses civBonuses43 = Game.getCiv(iCivID).civBonuses;
            civBonuses43.IncreaseGrowthRateCost += LegacyManager.legacies.get(i).IncreaseGrowthRateCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses44 = Game.getCiv(iCivID).civBonuses;
                civBonuses44.IncreaseGrowthRateCost -= LegacyManager.legacies.get(i).IncreaseGrowthRateCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).DevelopInfrastructureCost != null) {
            final CivilizationBonuses civBonuses45 = Game.getCiv(iCivID).civBonuses;
            civBonuses45.DevelopInfrastructureCost += LegacyManager.legacies.get(i).DevelopInfrastructureCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses46 = Game.getCiv(iCivID).civBonuses;
                civBonuses46.DevelopInfrastructureCost -= LegacyManager.legacies.get(i).DevelopInfrastructureCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).GeneralAttack != null) {
            final CivilizationBonuses civBonuses47 = Game.getCiv(iCivID).civBonuses;
            civBonuses47.GeneralAttack += LegacyManager.legacies.get(i).GeneralAttack[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses48 = Game.getCiv(iCivID).civBonuses;
                civBonuses48.GeneralAttack -= LegacyManager.legacies.get(i).GeneralAttack[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).GeneralDefense != null) {
            final CivilizationBonuses civBonuses49 = Game.getCiv(iCivID).civBonuses;
            civBonuses49.GeneralDefense += LegacyManager.legacies.get(i).GeneralDefense[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses50 = Game.getCiv(iCivID).civBonuses;
                civBonuses50.GeneralDefense -= LegacyManager.legacies.get(i).GeneralDefense[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).UnitsAttack != null) {
            final CivilizationBonuses civBonuses51 = Game.getCiv(iCivID).civBonuses;
            civBonuses51.UnitsAttack += LegacyManager.legacies.get(i).UnitsAttack[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses52 = Game.getCiv(iCivID).civBonuses;
                civBonuses52.UnitsAttack -= LegacyManager.legacies.get(i).UnitsAttack[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).UnitsDefense != null) {
            final CivilizationBonuses civBonuses53 = Game.getCiv(iCivID).civBonuses;
            civBonuses53.UnitsDefense += LegacyManager.legacies.get(i).UnitsDefense[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses54 = Game.getCiv(iCivID).civBonuses;
                civBonuses54.UnitsDefense -= LegacyManager.legacies.get(i).UnitsDefense[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaxMorale != null) {
            final CivilizationBonuses civBonuses55 = Game.getCiv(iCivID).civBonuses;
            civBonuses55.MaxMorale += LegacyManager.legacies.get(i).MaxMorale[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses56 = Game.getCiv(iCivID).civBonuses;
                civBonuses56.MaxMorale -= LegacyManager.legacies.get(i).MaxMorale[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).ArmyMovementSpeed != null) {
            final CivilizationBonuses civBonuses57 = Game.getCiv(iCivID).civBonuses;
            civBonuses57.ArmyMovementSpeed += LegacyManager.legacies.get(i).ArmyMovementSpeed[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses58 = Game.getCiv(iCivID).civBonuses;
                civBonuses58.ArmyMovementSpeed -= LegacyManager.legacies.get(i).ArmyMovementSpeed[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).SiegeEffectiveness != null) {
            final CivilizationBonuses civBonuses59 = Game.getCiv(iCivID).civBonuses;
            civBonuses59.SiegeEffectiveness += LegacyManager.legacies.get(i).SiegeEffectiveness[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses60 = Game.getCiv(iCivID).civBonuses;
                civBonuses60.SiegeEffectiveness -= LegacyManager.legacies.get(i).SiegeEffectiveness[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).ImproveRelationsModifier != null) {
            final CivilizationBonuses civBonuses61 = Game.getCiv(iCivID).civBonuses;
            civBonuses61.ImproveRelationsModifier += LegacyManager.legacies.get(i).ImproveRelationsModifier[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses62 = Game.getCiv(iCivID).civBonuses;
                civBonuses62.ImproveRelationsModifier -= LegacyManager.legacies.get(i).ImproveRelationsModifier[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).IncomeFromVassals != null) {
            final CivilizationBonuses civBonuses63 = Game.getCiv(iCivID).civBonuses;
            civBonuses63.IncomeFromVassals += LegacyManager.legacies.get(i).IncomeFromVassals[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses64 = Game.getCiv(iCivID).civBonuses;
                civBonuses64.IncomeFromVassals -= LegacyManager.legacies.get(i).IncomeFromVassals[level - 1];
            }
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (LegacyManager.legacies.get(i).DiplomacyPoints != null) {
            final CivilizationBonuses civBonuses65 = Game.getCiv(iCivID).civBonuses;
            civBonuses65.DiplomacyPoints += LegacyManager.legacies.get(i).DiplomacyPoints[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses66 = Game.getCiv(iCivID).civBonuses;
                civBonuses66.DiplomacyPoints -= LegacyManager.legacies.get(i).DiplomacyPoints[level - 1];
            }
            Game.getCiv(iCivID).updateDiplomacyPerMonth();
        }
        if (LegacyManager.legacies.get(i).LoanInterest != null) {
            final CivilizationBonuses civBonuses67 = Game.getCiv(iCivID).civBonuses;
            civBonuses67.LoanInterest += LegacyManager.legacies.get(i).LoanInterest[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses68 = Game.getCiv(iCivID).civBonuses;
                civBonuses68.LoanInterest -= LegacyManager.legacies.get(i).LoanInterest[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaxNumberOfLoans != null) {
            final CivilizationBonuses civBonuses69 = Game.getCiv(iCivID).civBonuses;
            civBonuses69.MaxNumberOfLoans += LegacyManager.legacies.get(i).MaxNumberOfLoans[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses70 = Game.getCiv(iCivID).civBonuses;
                civBonuses70.MaxNumberOfLoans -= LegacyManager.legacies.get(i).MaxNumberOfLoans[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
            final CivilizationBonuses civBonuses71 = Game.getCiv(iCivID).civBonuses;
            civBonuses71.MaximumLevelOfTheMilitaryAcademyForGenerals += LegacyManager.legacies.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses72 = Game.getCiv(iCivID).civBonuses;
                civBonuses72.MaximumLevelOfTheMilitaryAcademyForGenerals -= LegacyManager.legacies.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaximumLevelOfTheMilitaryAcademy != null) {
            final CivilizationBonuses civBonuses73 = Game.getCiv(iCivID).civBonuses;
            civBonuses73.MaximumLevelOfTheMilitaryAcademy += LegacyManager.legacies.get(i).MaximumLevelOfTheMilitaryAcademy[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses74 = Game.getCiv(iCivID).civBonuses;
                civBonuses74.MaximumLevelOfTheMilitaryAcademy -= LegacyManager.legacies.get(i).MaximumLevelOfTheMilitaryAcademy[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaximumLevelOfTheSupremeCourt != null) {
            final CivilizationBonuses civBonuses75 = Game.getCiv(iCivID).civBonuses;
            civBonuses75.MaximumLevelOfTheSupremeCourt += LegacyManager.legacies.get(i).MaximumLevelOfTheSupremeCourt[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses76 = Game.getCiv(iCivID).civBonuses;
                civBonuses76.MaximumLevelOfTheSupremeCourt -= LegacyManager.legacies.get(i).MaximumLevelOfTheSupremeCourt[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaximumLevelOfCapitalCity != null) {
            final CivilizationBonuses civBonuses77 = Game.getCiv(iCivID).civBonuses;
            civBonuses77.MaximumLevelOfCapitalCity += LegacyManager.legacies.get(i).MaximumLevelOfCapitalCity[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses78 = Game.getCiv(iCivID).civBonuses;
                civBonuses78.MaximumLevelOfCapitalCity -= LegacyManager.legacies.get(i).MaximumLevelOfCapitalCity[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).RecruitmentTime != null) {
            final CivilizationBonuses civBonuses79 = Game.getCiv(iCivID).civBonuses;
            civBonuses79.RecruitmentTime += LegacyManager.legacies.get(i).RecruitmentTime[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses80 = Game.getCiv(iCivID).civBonuses;
                civBonuses80.RecruitmentTime -= LegacyManager.legacies.get(i).RecruitmentTime[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaxNumOfAlliances != null) {
            final CivilizationBonuses civBonuses81 = Game.getCiv(iCivID).civBonuses;
            civBonuses81.MaxNumOfAlliances += LegacyManager.legacies.get(i).MaxNumOfAlliances[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses82 = Game.getCiv(iCivID).civBonuses;
                civBonuses82.MaxNumOfAlliances -= LegacyManager.legacies.get(i).MaxNumOfAlliances[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).AdvisorMaxLevel != null) {
            final CivilizationBonuses civBonuses83 = Game.getCiv(iCivID).civBonuses;
            civBonuses83.AdvisorMaxLevel += LegacyManager.legacies.get(i).AdvisorMaxLevel[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses84 = Game.getCiv(iCivID).civBonuses;
                civBonuses84.AdvisorMaxLevel -= LegacyManager.legacies.get(i).AdvisorMaxLevel[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).AdvisorPoolSize != null) {
            final CivilizationBonuses civBonuses85 = Game.getCiv(iCivID).civBonuses;
            civBonuses85.AdvisorPoolSize += LegacyManager.legacies.get(i).AdvisorPoolSize[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses86 = Game.getCiv(iCivID).civBonuses;
                civBonuses86.AdvisorPoolSize -= LegacyManager.legacies.get(i).AdvisorPoolSize[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).AdvisorCost != null) {
            final CivilizationBonuses civBonuses87 = Game.getCiv(iCivID).civBonuses;
            civBonuses87.AdvisorCost += LegacyManager.legacies.get(i).AdvisorCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses88 = Game.getCiv(iCivID).civBonuses;
                civBonuses88.AdvisorCost -= LegacyManager.legacies.get(i).AdvisorCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).GeneralCost != null) {
            final CivilizationBonuses civBonuses89 = Game.getCiv(iCivID).civBonuses;
            civBonuses89.GeneralCost += LegacyManager.legacies.get(i).GeneralCost[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses90 = Game.getCiv(iCivID).civBonuses;
                civBonuses90.GeneralCost -= LegacyManager.legacies.get(i).GeneralCost[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).AggressiveExpansion != null) {
            final CivilizationBonuses civBonuses91 = Game.getCiv(iCivID).civBonuses;
            civBonuses91.AggressiveExpansion += LegacyManager.legacies.get(i).AggressiveExpansion[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses92 = Game.getCiv(iCivID).civBonuses;
                civBonuses92.AggressiveExpansion -= LegacyManager.legacies.get(i).AggressiveExpansion[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).DiseaseDeathRate != null) {
            final CivilizationBonuses civBonuses93 = Game.getCiv(iCivID).civBonuses;
            civBonuses93.DiseaseDeathRate += LegacyManager.legacies.get(i).DiseaseDeathRate[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses94 = Game.getCiv(iCivID).civBonuses;
                civBonuses94.DiseaseDeathRate -= LegacyManager.legacies.get(i).DiseaseDeathRate[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).Discipline != null) {
            final CivilizationBonuses civBonuses95 = Game.getCiv(iCivID).civBonuses;
            civBonuses95.Discipline += LegacyManager.legacies.get(i).Discipline[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses96 = Game.getCiv(iCivID).civBonuses;
                civBonuses96.Discipline -= LegacyManager.legacies.get(i).Discipline[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).MaximumAmountOfGold != null) {
            final CivilizationBonuses civBonuses97 = Game.getCiv(iCivID).civBonuses;
            civBonuses97.MaximumAmountOfGold += LegacyManager.legacies.get(i).MaximumAmountOfGold[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses98 = Game.getCiv(iCivID).civBonuses;
                civBonuses98.MaximumAmountOfGold -= LegacyManager.legacies.get(i).MaximumAmountOfGold[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).Loot != null) {
            final CivilizationBonuses civBonuses99 = Game.getCiv(iCivID).civBonuses;
            civBonuses99.Loot += LegacyManager.legacies.get(i).Loot[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses100 = Game.getCiv(iCivID).civBonuses;
                civBonuses100.Loot -= LegacyManager.legacies.get(i).Loot[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).ManpowerRecoveryFromADisbandedArmy != null) {
            final CivilizationBonuses civBonuses101 = Game.getCiv(iCivID).civBonuses;
            civBonuses101.ManpowerRecoveryFromADisbandedArmy += LegacyManager.legacies.get(i).ManpowerRecoveryFromADisbandedArmy[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses102 = Game.getCiv(iCivID).civBonuses;
                civBonuses102.ManpowerRecoveryFromADisbandedArmy -= LegacyManager.legacies.get(i).ManpowerRecoveryFromADisbandedArmy[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).BattleWidth != null) {
            final CivilizationBonuses civBonuses103 = Game.getCiv(iCivID).civBonuses;
            civBonuses103.BattleWidth += LegacyManager.legacies.get(i).BattleWidth[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses104 = Game.getCiv(iCivID).civBonuses;
                civBonuses104.BattleWidth -= LegacyManager.legacies.get(i).BattleWidth[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).RegimentsLimit != null) {
            final CivilizationBonuses civBonuses105 = Game.getCiv(iCivID).civBonuses;
            civBonuses105.RegimentsLimit += LegacyManager.legacies.get(i).RegimentsLimit[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses106 = Game.getCiv(iCivID).civBonuses;
                civBonuses106.RegimentsLimit -= LegacyManager.legacies.get(i).RegimentsLimit[level - 1];
            }
        }
        if (LegacyManager.legacies.get(i).AllCharactersLifeExpectancy != null) {
            final CivilizationBonuses civBonuses107 = Game.getCiv(iCivID).civBonuses;
            civBonuses107.AllCharactersLifeExpectancy += LegacyManager.legacies.get(i).AllCharactersLifeExpectancy[level];
            if (level > 0 && !load) {
                final CivilizationBonuses civBonuses108 = Game.getCiv(iCivID).civBonuses;
                civBonuses108.AllCharactersLifeExpectancy -= LegacyManager.legacies.get(i).AllCharactersLifeExpectancy[level - 1];
            }
        }
        Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
    }
    
    public static final void loadLegacies() {
        final FileHandle tempFileT = FileManager.loadFile("game/legacies/LegaciesGroups.txt");
        final String[] tGroups = tempFileT.readString().split(";");
        for (int i = 0; i < tGroups.length; ++i) {
            LegacyManager.legacyGroups.add(Game.lang.get(tGroups[i]));
        }
        LegacyManager.iLegacyGroupsSize = LegacyManager.legacyGroups.size();
        try {
            final FileHandle fileList = FileManager.loadFile("game/legacies/Legacies.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigLegacyData.class, "Legacy", (Class)Legacy.class);
            final ConfigLegacyData data = (ConfigLegacyData)json.fromJson((Class)ConfigLegacyData.class, fileContent);
            for (final Object e : data.Legacy) {
                LegacyManager.legacies.add((Legacy)e);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        LegacyManager.iLegaciesSize = LegacyManager.legacies.size();
        for (int i = 0; i < LegacyManager.iLegacyGroupsSize; ++i) {
            LegacyManager.numOfLegaciesInGroup.add(0);
        }
        for (int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
            LegacyManager.numOfLegaciesInGroup.set(LegacyManager.legacies.get(i).GroupID, LegacyManager.numOfLegaciesInGroup.get(LegacyManager.legacies.get(i).GroupID) + 1);
        }
        for (int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
            for (int j = LegacyManager.legacies.get(i).CostLegacy.length - 1; j >= 0; --j) {
                if (LegacyManager.legacies.get(i).CostLegacy[j] < LegacyManager.minLegacyCost) {
                    LegacyManager.minLegacyCost = LegacyManager.legacies.get(i).CostLegacy[j];
                }
            }
        }
        loadLegacyImages();
    }
    
    public static final void loadLegacyImages() {
        final FileHandle tempFileT = FileManager.loadFile("game/legacies/legaciesImages/numOfImages.txt");
        for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
            if (FileManager.loadFile("game/legacies/legaciesImages/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                LegacyManager.legacyImages.add(new Image(ImageManager.loadTexture_RGB888("game/legacies/legaciesImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
            else {
                LegacyManager.legacyImages.add(new Image(ImageManager.loadTexture_RGB888("game/legacies/legaciesImages/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
        }
    }
    
    static {
        LegacyManager.legacyGroups = new ArrayList<String>();
        LegacyManager.iLegacyGroupsSize = 0;
        LegacyManager.legacyImages = new ArrayList<Image>();
        LegacyManager.legacies = new ArrayList<Legacy>();
        LegacyManager.iLegaciesSize = 0;
        LegacyManager.minLegacyCost = 999666;
        LegacyManager.numOfLegaciesInGroup = new ArrayList<Integer>();
    }
    
    public static class ConfigLegacyData
    {
        public String Age_of_History;
        public ArrayList Legacy;
    }
    
    public static class Legacy
    {
        public String Name;
        public int ImageID;
        public int GroupID;
        public int[] CostLegacy;
        public int[] AI;
        public float[] ConstructionCost;
        public float[] AdministrationBuildingsCost;
        public float[] MilitaryBuildingsCost;
        public float[] EconomyBuildingsCost;
        public float[] WonderConstructionCost;
        public float[] ConstructionTime;
        public float[] TaxEfficiency;
        public float[] ProvinceMaintenance;
        public float[] BuildingsMaintenanceCost;
        public float[] ManpowerRecoverySpeed;
        public int[] MaxManpower;
        public float[] Research;
        public float[] ResearchPoints;
        public int[] BuildingSlot;
        public int[] MaxInfrastructure;
        public float[] Devastation;
        public float[] GrowthRate;
        public float[] IncomeProduction;
        public float[] ProductionEfficiency;
        public float[] InvestInEconomyCost;
        public float[] IncreaseTaxEfficiencyCost;
        public float[] IncreaseGrowthRateCost;
        public float[] DevelopInfrastructureCost;
        public int[] GeneralAttack;
        public int[] GeneralDefense;
        public int[] UnitsAttack;
        public int[] UnitsDefense;
        public float[] MaxMorale;
        public float[] ArmyMovementSpeed;
        public float[] SiegeEffectiveness;
        public float[] ImproveRelationsModifier;
        public float[] IncomeFromVassals;
        public float[] LoanInterest;
        public float[] DiplomacyPoints;
        public float[] RecruitmentTime;
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
        public float[] Loot;
        public int[] AllCharactersLifeExpectancy;
        public int[] RegimentsLimit;
    }
}
