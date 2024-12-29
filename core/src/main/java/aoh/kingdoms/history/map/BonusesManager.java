// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.province.ProvinceBonuses;
import aoc.kingdoms.lukasz.jakowski.Game;

public class BonusesManager
{
    public static void initAndBuildProvinceBonuses() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).provBonuses = new ProvinceBonuses();
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                for (int j = 0; j < Game.getProvince(i).iBuildingsSize; ++j) {
                    updateBuildingBonuses(i, Game.getProvince(i).getBuildings(j).getBuilding(), Game.getProvince(i).getBuildings(j).getBuildingID(), 1);
                }
            }
        }
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getCapitalProvinceID() >= 0) {
                Game.getProvince(Game.getCiv(i).getCapitalProvinceID()).setIsCapital(true, true);
            }
        }
    }
    
    public static void updateBuildingBonuses(final int iProvinceID, final int building, final int buildingID, final int mod) {
        final Province province = Game.getProvince(iProvinceID);
        if (BuildingsManager.buildings.get(building).UniqueCapitalBuilding && Game.getCiv(province.getCivID()).getCapitalProvinceID() != iProvinceID) {
            return;
        }
        if (BuildingsManager.buildings.get(building).MonthlyLegacy != null) {
            final ProvinceBonuses provBonuses = province.provBonuses;
            provBonuses.MonthlyLegacy += BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] != 0.0f) {
                Game.gameThread.addCivUpdateLegacyPerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).DefenseBonus != null) {
            final ProvinceBonuses provBonuses2 = province.provBonuses;
            provBonuses2.DefenseBonus += BuildingsManager.buildings.get(building).DefenseBonus[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).FortLevel != null) {
            final ProvinceBonuses provBonuses3 = province.provBonuses;
            provBonuses3.FortLevel += BuildingsManager.buildings.get(building).FortLevel[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).FortDefense != null) {
            final ProvinceBonuses provBonuses4 = province.provBonuses;
            provBonuses4.FortDefense += BuildingsManager.buildings.get(building).FortDefense[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).MaximumManpower != null) {
            final ProvinceBonuses provBonuses5 = province.provBonuses;
            provBonuses5.MaximumManpower += BuildingsManager.buildings.get(building).MaximumManpower[buildingID] * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(province.getCivID());
        }
        if (BuildingsManager.buildings.get(building).LocalManpower != null) {
            final ProvinceBonuses provBonuses6 = province.provBonuses;
            provBonuses6.LocalManpower += (int)(BuildingsManager.buildings.get(building).LocalManpower[buildingID] * mod);
            Game.gameThreadTurns.addCivUpdateMaxManpower(province.getCivID());
        }
        if (BuildingsManager.buildings.get(building).RecruitArmyCostInProvince != null) {
            final ProvinceBonuses provBonuses7 = province.provBonuses;
            provBonuses7.RecruitArmyCostInProvince += BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).LocalGrowthRate != null) {
            final ProvinceBonuses provBonuses8 = province.provBonuses;
            provBonuses8.LocalGrowthRate += BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID] * mod;
            province.updateInfrastructureMax();
            if (province.haveResearchBuilding()) {
                Game.getCiv(province.getCivID()).updateResearchPerMonth();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).InvestInEconomyCost != null) {
            final ProvinceBonuses provBonuses9 = province.provBonuses;
            provBonuses9.InvestInEconomyCost += BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).ConstructionTimeBonus != null) {
            final ProvinceBonuses provBonuses10 = province.provBonuses;
            provBonuses10.ConstructionTimeBonus += BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).ConstructionCost != null) {
            final ProvinceBonuses provBonuses11 = province.provBonuses;
            provBonuses11.ConstructionCost += BuildingsManager.buildings.get(building).ConstructionCost[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).IncreaseGrowthRateCost != null) {
            final ProvinceBonuses provBonuses12 = province.provBonuses;
            provBonuses12.IncreaseGrowthRateCost += BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).IncreaseManpowerCost != null) {
            final ProvinceBonuses provBonuses13 = province.provBonuses;
            provBonuses13.IncreaseManpowerCost += BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost != null) {
            final ProvinceBonuses provBonuses14 = province.provBonuses;
            provBonuses14.IncreaseTaxEfficiencyCost += BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).DevelopInfrastructureCost != null) {
            final ProvinceBonuses provBonuses15 = province.provBonuses;
            provBonuses15.DevelopInfrastructureCost += BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).ProvinceMaintenance != null) {
            final ProvinceBonuses provBonuses16 = province.provBonuses;
            provBonuses16.ProvinceMaintenance += BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] != 0.0f) {
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).MaintenanceCost != null) {
            final ProvinceBonuses provBonuses17 = province.provBonuses;
            provBonuses17.MaintenanceCost += BuildingsManager.buildings.get(building).MaintenanceCost[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).MaintenanceCost[buildingID] != 0.0f) {
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).MonthlyIncome != null) {
            final ProvinceBonuses provBonuses18 = province.provBonuses;
            provBonuses18.MonthlyIncome += BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] != 0.0f) {
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).MaxInfrastructure != null) {
            final ProvinceBonuses provBonuses19 = province.provBonuses;
            provBonuses19.MaxInfrastructure += BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID] * mod;
            province.updateInfrastructureMax();
        }
        if (BuildingsManager.buildings.get(building).BuildingSlots != null) {
            final ProvinceBonuses provBonuses20 = province.provBonuses;
            provBonuses20.BuildingSlots += BuildingsManager.buildings.get(building).BuildingSlots[buildingID] * mod;
            province.updateBuildingLimit();
        }
        if (BuildingsManager.buildings.get(building).LocalTaxEfficiency != null) {
            final ProvinceBonuses provBonuses21 = province.provBonuses;
            provBonuses21.LocalTaxEfficiency += BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] != 0.0f) {
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).ArmyMovementSpeed != null) {
            final ProvinceBonuses provBonuses22 = province.provBonuses;
            provBonuses22.ArmyMovementSpeed += BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).DiseaseDeathRate != null) {
            final ProvinceBonuses provBonuses23 = province.provBonuses;
            provBonuses23.DiseaseDeathRate += BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks != null) {
            final ProvinceBonuses provBonuses24 = province.provBonuses;
            provBonuses24.CasualtiesNuclearAttacks += BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] * mod;
        }
        if (BuildingsManager.buildings.get(building).Economy != null) {
            final ProvinceBonuses provBonuses25 = province.provBonuses;
            provBonuses25.Economy += BuildingsManager.buildings.get(building).Economy[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).Economy[buildingID] != 0.0f) {
                province.updateBuildingLimit();
                province.updateInfrastructureMax();
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).ResearchPoints != null) {
            final ProvinceBonuses provBonuses26 = province.provBonuses;
            provBonuses26.ResearchPoints += BuildingsManager.buildings.get(building).ResearchPoints[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).ResearchPoints[buildingID] != 0.0f) {
                Game.gameThread.addCivUpdateResearchPerMonth(province.getCivID());
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).ProductionEfficiency != null) {
            final ProvinceBonuses provBonuses27 = province.provBonuses;
            provBonuses27.ProductionEfficiency += BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] != 0.0f) {
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).IncomeProduction != null) {
            final ProvinceBonuses provBonuses28 = province.provBonuses;
            provBonuses28.IncomeProduction += BuildingsManager.buildings.get(building).IncomeProduction[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).IncomeProduction[buildingID] != 0.0f) {
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
        if (BuildingsManager.buildings.get(building).TaxEfficiency != null) {
            final CivilizationBonuses civBonuses = Game.getCiv(province.getCivID()).civBonuses;
            civBonuses.TaxEfficiency += BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] * mod;
            if (BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] != 0.0f) {
                province.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(province.getCivID());
            }
        }
    }
}
