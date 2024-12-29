// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class CivilizationBonusesSerializer implements Json.Serializer<CivilizationBonuses>
{
    public void write(final Json json, final CivilizationBonuses bonuses, final Class knownType) {
        json.writeObjectStart();
        if (bonuses.TempTurnID != 0 && bonuses.TempTurnID != 1000000) {
            json.writeValue("TempTurnID", (Object)bonuses.TempTurnID);
        }
        if (bonuses.MonthlyIncome != 0.0f) {
            json.writeValue("MonthlyIncome", (Object)bonuses.MonthlyIncome);
        }
        if (bonuses.TaxEfficiency != 0.0f) {
            json.writeValue("TaxEfficiency", (Object)bonuses.TaxEfficiency);
        }
        if (bonuses.ProvinceMaintenance != 0.0f) {
            json.writeValue("ProvinceMaintenance", (Object)bonuses.ProvinceMaintenance);
        }
        if (bonuses.BuildingsMaintenanceCost != 0.0f) {
            json.writeValue("BuildingsMaintenanceCost", (Object)bonuses.BuildingsMaintenanceCost);
        }
        if (bonuses.GrowthRate != 0.0f) {
            json.writeValue("GrowthRate", (Object)bonuses.GrowthRate);
        }
        if (bonuses.MaintenanceCost != 0.0f) {
            json.writeValue("MaintenanceCost", (Object)bonuses.MaintenanceCost);
        }
        if (bonuses.ProductionEfficiency != 0.0f) {
            json.writeValue("ProductionEfficiency", (Object)bonuses.ProductionEfficiency);
        }
        if (bonuses.IncomeProduction != 0.0f) {
            json.writeValue("IncomeProduction", (Object)bonuses.IncomeProduction);
        }
        if (bonuses.IncomeTaxation != 0.0f) {
            json.writeValue("IncomeTaxation", (Object)bonuses.IncomeTaxation);
        }
        if (bonuses.IncomeEconomy != 0.0f) {
            json.writeValue("IncomeEconomy", (Object)bonuses.IncomeEconomy);
        }
        if (bonuses.MonthlyLegacy != 0.0f) {
            json.writeValue("MonthlyLegacy", (Object)bonuses.MonthlyLegacy);
        }
        if (bonuses.MonthlyLegacy_Percentage != 0.0f) {
            json.writeValue("MonthlyLegacy_Percentage", (Object)bonuses.MonthlyLegacy_Percentage);
        }
        if (bonuses.MaxManpower != 0.0f) {
            json.writeValue("MaxManpower", (Object)bonuses.MaxManpower);
        }
        if (bonuses.MaxManpower_Percentage != 0.0f) {
            json.writeValue("MaxManpower_Percentage", (Object)bonuses.MaxManpower_Percentage);
        }
        if (bonuses.ManpowerRecoverySpeed != 0.0f) {
            json.writeValue("ManpowerRecoverySpeed", (Object)bonuses.ManpowerRecoverySpeed);
        }
        if (bonuses.ReinforcementSpeed != 0.0f) {
            json.writeValue("ReinforcementSpeed", (Object)bonuses.ReinforcementSpeed);
        }
        if (bonuses.ArmyMoraleRecovery != 0.0f) {
            json.writeValue("ArmyMoraleRecovery", (Object)bonuses.ArmyMoraleRecovery);
        }
        if (bonuses.WarScoreCost != 0.0f) {
            json.writeValue("WarScoreCost", (Object)bonuses.WarScoreCost);
        }
        if (bonuses.ArmyMaintenance != 0.0f) {
            json.writeValue("ArmyMaintenance", (Object)bonuses.ArmyMaintenance);
        }
        if (bonuses.RecruitmentTime != 0.0f) {
            json.writeValue("RecruitmentTime", (Object)bonuses.RecruitmentTime);
        }
        if (bonuses.RecruitArmyCost != 0.0f) {
            json.writeValue("RecruitArmyCost", (Object)bonuses.RecruitArmyCost);
        }
        if (bonuses.RecruitArmyFirstLineCost != 0.0f) {
            json.writeValue("RecruitArmyFirstLineCost", (Object)bonuses.RecruitArmyFirstLineCost);
        }
        if (bonuses.RecruitArmySecondLineCost != 0.0f) {
            json.writeValue("RecruitArmySecondLineCost", (Object)bonuses.RecruitArmySecondLineCost);
        }
        if (bonuses.Research != 0.0f) {
            json.writeValue("Research", (Object)bonuses.Research);
        }
        if (bonuses.ResearchPoints != 0.0f) {
            json.writeValue("ResearchPoints", (Object)bonuses.ResearchPoints);
        }
        if (bonuses.TechnologyCost != 0.0f) {
            json.writeValue("TechnologyCost", (Object)bonuses.TechnologyCost);
        }
        if (bonuses.ConstructionCost != 0.0f) {
            json.writeValue("ConstructionCost", (Object)bonuses.ConstructionCost);
        }
        if (bonuses.AdministrationBuildingsCost != 0.0f) {
            json.writeValue("AdministrationBuildingsCost", (Object)bonuses.AdministrationBuildingsCost);
        }
        if (bonuses.MilitaryBuildingsCost != 0.0f) {
            json.writeValue("MilitaryBuildingsCost", (Object)bonuses.MilitaryBuildingsCost);
        }
        if (bonuses.EconomyBuildingsCost != 0.0f) {
            json.writeValue("EconomyBuildingsCost", (Object)bonuses.EconomyBuildingsCost);
        }
        if (bonuses.WonderConstructionCost != 0.0f) {
            json.writeValue("WonderConstructionCost", (Object)bonuses.WonderConstructionCost);
        }
        if (bonuses.ConstructionTime != 0.0f) {
            json.writeValue("ConstructionTime", (Object)bonuses.ConstructionTime);
        }
        if (bonuses.BuildingSlot != 0) {
            json.writeValue("BuildingSlot", (Object)bonuses.BuildingSlot);
        }
        if (bonuses.MaxInfrastructure != 0) {
            json.writeValue("MaxInfrastructure", (Object)bonuses.MaxInfrastructure);
        }
        if (bonuses.InvestInEconomyCost != 0.0f) {
            json.writeValue("InvestInEconomyCost", (Object)bonuses.InvestInEconomyCost);
        }
        if (bonuses.IncreaseManpowerCost != 0.0f) {
            json.writeValue("IncreaseManpowerCost", (Object)bonuses.IncreaseManpowerCost);
        }
        if (bonuses.IncreaseTaxEfficiencyCost != 0.0f) {
            json.writeValue("IncreaseTaxEfficiencyCost", (Object)bonuses.IncreaseTaxEfficiencyCost);
        }
        if (bonuses.DevelopInfrastructureCost != 0.0f) {
            json.writeValue("DevelopInfrastructureCost", (Object)bonuses.DevelopInfrastructureCost);
        }
        if (bonuses.IncreaseGrowthRateCost != 0.0f) {
            json.writeValue("IncreaseGrowthRateCost", (Object)bonuses.IncreaseGrowthRateCost);
        }
        if (bonuses.GeneralAttack != 0) {
            json.writeValue("GeneralAttack", (Object)bonuses.GeneralAttack);
        }
        if (bonuses.GeneralDefense != 0) {
            json.writeValue("GeneralDefense", (Object)bonuses.GeneralDefense);
        }
        if (bonuses.UnitsAttack != 0) {
            json.writeValue("UnitsAttack", (Object)bonuses.UnitsAttack);
        }
        if (bonuses.UnitsDefense != 0) {
            json.writeValue("UnitsDefense", (Object)bonuses.UnitsDefense);
        }
        if (bonuses.MaxMorale != 0.0f) {
            json.writeValue("MaxMorale", (Object)bonuses.MaxMorale);
        }
        if (bonuses.ArmyMovementSpeed != 0.0f) {
            json.writeValue("ArmyMovementSpeed", (Object)bonuses.ArmyMovementSpeed);
        }
        if (bonuses.SiegeEffectiveness != 0.0f) {
            json.writeValue("SiegeEffectiveness", (Object)bonuses.SiegeEffectiveness);
        }
        if (bonuses.ImproveRelationsModifier != 0.0f) {
            json.writeValue("ImproveRelationsModifier", (Object)bonuses.ImproveRelationsModifier);
        }
        if (bonuses.IncomeFromVassals != 0.0f) {
            json.writeValue("IncomeFromVassals", (Object)bonuses.IncomeFromVassals);
        }
        if (bonuses.LoanInterest != 0.0f) {
            json.writeValue("LoanInterest", (Object)bonuses.LoanInterest);
        }
        if (bonuses.AggressiveExpansion != 0.0f) {
            json.writeValue("AggressiveExpansion", (Object)bonuses.AggressiveExpansion);
        }
        if (bonuses.MaxNumOfAlliances != 0) {
            json.writeValue("MaxNumOfAlliances", (Object)bonuses.MaxNumOfAlliances);
        }
        if (bonuses.RevolutionaryRisk != 0.0f) {
            json.writeValue("RevolutionaryRisk", (Object)bonuses.RevolutionaryRisk);
        }
        if (bonuses.CoreCost != 0.0f) {
            json.writeValue("CoreCost", (Object)bonuses.CoreCost);
        }
        if (bonuses.ReligionCost != 0.0f) {
            json.writeValue("ReligionCost", (Object)bonuses.ReligionCost);
        }
        if (bonuses.AdvisorCost != 0.0f) {
            json.writeValue("AdvisorCost", (Object)bonuses.AdvisorCost);
        }
        if (bonuses.GeneralCost != 0.0f) {
            json.writeValue("GeneralCost", (Object)bonuses.GeneralCost);
        }
        if (bonuses.AdvisorMaxLevel != 0) {
            json.writeValue("AdvisorMaxLevel", (Object)bonuses.AdvisorMaxLevel);
        }
        if (bonuses.AdvisorPoolSize != 0) {
            json.writeValue("AdvisorPoolSize", (Object)bonuses.AdvisorPoolSize);
        }
        if (bonuses.MaxNumberOfLoans != 0) {
            json.writeValue("MaxNumberOfLoans", (Object)bonuses.MaxNumberOfLoans);
        }
        if (bonuses.DiseaseDeathRate != 0.0f) {
            json.writeValue("DiseaseDeathRate", (Object)bonuses.DiseaseDeathRate);
        }
        if (bonuses.DiplomacyPoints != 0.0f) {
            json.writeValue("DiplomacyPoints", (Object)bonuses.DiplomacyPoints);
        }
        if (bonuses.Devastation != 0.0f) {
            json.writeValue("Devastation", (Object)bonuses.Devastation);
        }
        if (bonuses.MaximumLevelOfTheMilitaryAcademyForGenerals != 0) {
            json.writeValue("MaximumLevelOfTheMilitaryAcademyForGenerals", (Object)bonuses.MaximumLevelOfTheMilitaryAcademyForGenerals);
        }
        if (bonuses.MaximumLevelOfTheMilitaryAcademy != 0) {
            json.writeValue("MaximumLevelOfTheMilitaryAcademy", (Object)bonuses.MaximumLevelOfTheMilitaryAcademy);
        }
        if (bonuses.MaximumLevelOfTheSupremeCourt != 0) {
            json.writeValue("MaximumLevelOfTheSupremeCourt", (Object)bonuses.MaximumLevelOfTheSupremeCourt);
        }
        if (bonuses.MaximumLevelOfNuclearReactor != 0) {
            json.writeValue("MaximumLevelOfNuclearReactor", (Object)bonuses.MaximumLevelOfNuclearReactor);
        }
        if (bonuses.MaximumLevelOfCapitalCity != 0) {
            json.writeValue("MaximumLevelOfCapitalCity", (Object)bonuses.MaximumLevelOfCapitalCity);
        }
        if (bonuses.BattleWidth != 0) {
            json.writeValue("BattleWidth", (Object)bonuses.BattleWidth);
        }
        if (bonuses.Discipline != 0.0f) {
            json.writeValue("Discipline", (Object)bonuses.Discipline);
        }
        if (bonuses.ManpowerRecoveryFromADisbandedArmy != 0.0f) {
            json.writeValue("ManpowerRecoveryFromADisbandedArmy", (Object)bonuses.ManpowerRecoveryFromADisbandedArmy);
        }
        if (bonuses.MaximumAmountOfGold != 0.0f) {
            json.writeValue("MaximumAmountOfGold", (Object)bonuses.MaximumAmountOfGold);
        }
        if (bonuses.MaximumAmountOfGold_Percentage != 0.0f) {
            json.writeValue("MaximumAmountOfGold_Percentage", (Object)bonuses.MaximumAmountOfGold_Percentage);
        }
        if (bonuses.Loot != 0.0f) {
            json.writeValue("Loot", (Object)bonuses.Loot);
        }
        if (bonuses.AllCharactersLifeExpectancy != 0) {
            json.writeValue("AllCharactersLifeExpectancy", (Object)bonuses.AllCharactersLifeExpectancy);
        }
        if (bonuses.RegimentsLimit != 0) {
            json.writeValue("RegimentsLimit", (Object)bonuses.RegimentsLimit);
        }
        if (bonuses.Inflation != 0.0f) {
            json.writeValue("Inflation", (Object)bonuses.Inflation);
        }
        if (bonuses.Corruption != 0.0f) {
            json.writeValue("Corruption", (Object)bonuses.Corruption);
        }
        json.writeObjectEnd();
    }
    
    public CivilizationBonuses read(final Json json, final JsonValue jsonData, final Class type) {
        final CivilizationBonuses bonuses = new CivilizationBonuses();
        bonuses.TempTurnID = jsonData.getInt("TempTurnID", 0);
        bonuses.MonthlyIncome = jsonData.getFloat("MonthlyIncome", 0.0f);
        bonuses.TaxEfficiency = jsonData.getFloat("TaxEfficiency", 0.0f);
        bonuses.ProvinceMaintenance = jsonData.getFloat("ProvinceMaintenance", 0.0f);
        bonuses.BuildingsMaintenanceCost = jsonData.getFloat("BuildingsMaintenanceCost", 0.0f);
        bonuses.GrowthRate = jsonData.getFloat("GrowthRate", 0.0f);
        bonuses.MaintenanceCost = jsonData.getFloat("MaintenanceCost", 0.0f);
        bonuses.ProductionEfficiency = jsonData.getFloat("ProductionEfficiency", 0.0f);
        bonuses.IncomeProduction = jsonData.getFloat("IncomeProduction", 0.0f);
        bonuses.IncomeTaxation = jsonData.getFloat("IncomeTaxation", 0.0f);
        bonuses.IncomeEconomy = jsonData.getFloat("IncomeEconomy", 0.0f);
        bonuses.MonthlyLegacy = jsonData.getFloat("MonthlyLegacy", 0.0f);
        bonuses.MonthlyLegacy_Percentage = jsonData.getFloat("MonthlyLegacy_Percentage", 0.0f);
        bonuses.MaxManpower = jsonData.getFloat("MaxManpower", 0.0f);
        bonuses.MaxManpower_Percentage = jsonData.getFloat("MaxManpower_Percentage", 0.0f);
        bonuses.ManpowerRecoverySpeed = jsonData.getFloat("ManpowerRecoverySpeed", 0.0f);
        bonuses.ReinforcementSpeed = jsonData.getFloat("ReinforcementSpeed", 0.0f);
        bonuses.ArmyMoraleRecovery = jsonData.getFloat("ArmyMoraleRecovery", 0.0f);
        bonuses.WarScoreCost = jsonData.getFloat("WarScoreCost", 0.0f);
        bonuses.ArmyMaintenance = jsonData.getFloat("ArmyMaintenance", 0.0f);
        bonuses.RecruitmentTime = jsonData.getFloat("RecruitmentTime", 0.0f);
        bonuses.RecruitArmyCost = jsonData.getFloat("RecruitArmyCost", 0.0f);
        bonuses.RecruitArmyFirstLineCost = jsonData.getFloat("RecruitArmyFirstLineCost", 0.0f);
        bonuses.RecruitArmySecondLineCost = jsonData.getFloat("RecruitArmySecondLineCost", 0.0f);
        bonuses.Research = jsonData.getFloat("Research", 0.0f);
        bonuses.ResearchPoints = jsonData.getFloat("ResearchPoints", 0.0f);
        bonuses.TechnologyCost = jsonData.getFloat("TechnologyCost", 0.0f);
        bonuses.ConstructionCost = jsonData.getFloat("ConstructionCost", 0.0f);
        bonuses.AdministrationBuildingsCost = jsonData.getFloat("AdministrationBuildingsCost", 0.0f);
        bonuses.MilitaryBuildingsCost = jsonData.getFloat("MilitaryBuildingsCost", 0.0f);
        bonuses.EconomyBuildingsCost = jsonData.getFloat("EconomyBuildingsCost", 0.0f);
        bonuses.WonderConstructionCost = jsonData.getFloat("WonderConstructionCost", 0.0f);
        bonuses.ConstructionTime = jsonData.getFloat("ConstructionTime", 0.0f);
        bonuses.BuildingSlot = jsonData.getInt("BuildingSlot", 0);
        bonuses.MaxInfrastructure = jsonData.getInt("MaxInfrastructure", 0);
        bonuses.InvestInEconomyCost = jsonData.getFloat("InvestInEconomyCost", 0.0f);
        bonuses.IncreaseManpowerCost = jsonData.getFloat("IncreaseManpowerCost", 0.0f);
        bonuses.IncreaseTaxEfficiencyCost = jsonData.getFloat("IncreaseTaxEfficiencyCost", 0.0f);
        bonuses.DevelopInfrastructureCost = jsonData.getFloat("DevelopInfrastructureCost", 0.0f);
        bonuses.IncreaseGrowthRateCost = jsonData.getFloat("IncreaseGrowthRateCost", 0.0f);
        bonuses.GeneralAttack = jsonData.getInt("GeneralAttack", 0);
        bonuses.GeneralDefense = jsonData.getInt("GeneralDefense", 0);
        bonuses.UnitsAttack = jsonData.getInt("UnitsAttack", 0);
        bonuses.UnitsDefense = jsonData.getInt("UnitsDefense", 0);
        bonuses.MaxMorale = jsonData.getFloat("MaxMorale", 0.0f);
        bonuses.ArmyMovementSpeed = jsonData.getFloat("ArmyMovementSpeed", 0.0f);
        bonuses.SiegeEffectiveness = jsonData.getFloat("SiegeEffectiveness", 0.0f);
        bonuses.ImproveRelationsModifier = jsonData.getFloat("ImproveRelationsModifier", 0.0f);
        bonuses.IncomeFromVassals = jsonData.getFloat("IncomeFromVassals", 0.0f);
        bonuses.LoanInterest = jsonData.getFloat("LoanInterest", 0.0f);
        bonuses.AggressiveExpansion = jsonData.getFloat("AggressiveExpansion", 0.0f);
        bonuses.MaxNumOfAlliances = jsonData.getInt("MaxNumOfAlliances", 0);
        bonuses.RevolutionaryRisk = jsonData.getFloat("RevolutionaryRisk", 0.0f);
        bonuses.CoreCost = jsonData.getFloat("CoreCost", 0.0f);
        bonuses.ReligionCost = jsonData.getFloat("ReligionCost", 0.0f);
        bonuses.AdvisorCost = jsonData.getFloat("AdvisorCost", 0.0f);
        bonuses.GeneralCost = jsonData.getFloat("GeneralCost", 0.0f);
        bonuses.AdvisorMaxLevel = jsonData.getInt("AdvisorMaxLevel", 0);
        bonuses.AdvisorPoolSize = jsonData.getInt("AdvisorPoolSize", 0);
        bonuses.MaxNumberOfLoans = jsonData.getInt("MaxNumberOfLoans", 0);
        bonuses.DiseaseDeathRate = jsonData.getFloat("DiseaseDeathRate", 0.0f);
        bonuses.DiplomacyPoints = jsonData.getFloat("DiplomacyPoints", 0.0f);
        bonuses.Devastation = jsonData.getFloat("Devastation", 0.0f);
        bonuses.MaximumLevelOfTheMilitaryAcademyForGenerals = jsonData.getInt("MaximumLevelOfTheMilitaryAcademyForGenerals", 0);
        bonuses.MaximumLevelOfTheMilitaryAcademy = jsonData.getInt("MaximumLevelOfTheMilitaryAcademy", 0);
        bonuses.MaximumLevelOfTheSupremeCourt = jsonData.getInt("MaximumLevelOfTheSupremeCourt", 0);
        bonuses.MaximumLevelOfNuclearReactor = jsonData.getInt("MaximumLevelOfNuclearReactor", 0);
        bonuses.MaximumLevelOfCapitalCity = jsonData.getInt("MaximumLevelOfCapitalCity", 0);
        bonuses.BattleWidth = jsonData.getInt("BattleWidth", 0);
        bonuses.Discipline = jsonData.getFloat("Discipline", 0.0f);
        bonuses.ManpowerRecoveryFromADisbandedArmy = jsonData.getFloat("ManpowerRecoveryFromADisbandedArmy", 0.0f);
        bonuses.MaximumAmountOfGold = jsonData.getFloat("MaximumAmountOfGold", 0.0f);
        bonuses.MaximumAmountOfGold_Percentage = jsonData.getFloat("MaximumAmountOfGold_Percentage", 0.0f);
        bonuses.Loot = jsonData.getFloat("Loot", 0.0f);
        bonuses.AllCharactersLifeExpectancy = jsonData.getInt("AllCharactersLifeExpectancy", 0);
        bonuses.RegimentsLimit = jsonData.getInt("RegimentsLimit", 0);
        bonuses.Inflation = jsonData.getFloat("Inflation", 0.0f);
        bonuses.Corruption = jsonData.getFloat("Corruption", 0.0f);
        return bonuses;
    }
}
