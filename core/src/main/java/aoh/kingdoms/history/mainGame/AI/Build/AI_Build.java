// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Build;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.jakowski.AI.Invest.AI_IncreaseTaxEfficiency;
import aoc.kingdoms.lukasz.jakowski.AI.Invest.AI_InvestInEconomy;
import aoc.kingdoms.lukasz.jakowski.AI.Colonization.AI_ColonizationTribal;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.List;

public class AI_Build
{
    public static int minCostOfBuilding;
    public static float averageCostOfBuilding;
    public static float averageCostOfBuilding_Capital;
    public static List<Building> research;
    public static List<Building> gold;
    public static List<Building> legacy;
    public static List<Building> manpower;
    public static List<Building> defensive;
    public static List<Building> recruitArmyCost;
    public static List<Building> taxEfficiency;
    public static List<Building> growthRate;
    public static List<Building> economy;
    public static List<Building> productionEfficiency;
    public static List<Building> provinceMaintenance;
    public static List<Building> constructionCost;
    public static List<Building> rest;
    public static List<Building> capitalBuildings;
    public static List<Building> resourceBuildings;
    
    public static final void build(final int civID) {
        try {
            if (Game.getCiv(civID).getNumOfProvinces() > 0 && Game.getCiv(civID).fGold > AI_Build.averageCostOfBuilding) {
                for (int i = 0; i < Game.aiValuesBuild.CHOOSE_BUILD_TYPE_LIMIT; ++i) {
                    if (!build(civID, chooseBuildingType(civID))) {
                        break;
                    }
                    if (Game.getCiv(civID).fGold < AI_Build.averageCostOfBuilding) {
                        break;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final int getLimitOfBuildings(final int typeID) {
        return Game.aiValuesBuild.BUILD_LIMIT[typeID];
    }
    
    public static final boolean build(final int civID, final int typeID) {
        switch (typeID) {
            case 0: {
                return AI_BuildResearch.buildResearchBuilding(civID, getLimitOfBuildings(typeID));
            }
            case 1: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.gold);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildGold.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 2: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.legacy);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildLegacy.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 3: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.taxEfficiency);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildTaxEfficiency.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 4: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.provinceMaintenance);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildProvinceMaintenance.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 5: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.manpower);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildManpower.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 6: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.defensive);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildDefensive.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 7: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.recruitArmyCost);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildRecruitArmyCost.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 8: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.growthRate);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildGrowthRate.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 9: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.economy);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildEconomy.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 10: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.productionEfficiency);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildProductionEfficiency.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 11: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.constructionCost);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildConstructionCost.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 12: {
                final List<Building> tList = getUnlockedBuildings(civID, AI_Build.rest);
                if (tList.isEmpty()) {
                    return build_NoBuildings(civID, typeID);
                }
                return AI_BuildRest.buildBuilding(civID, getLimitOfBuildings(typeID), tList);
            }
            case 13: {
                return AI_BuildCapitalBuilding.buildBuilding(civID, getLimitOfBuildings(typeID));
            }
            case 14: {
                return AI_BuildResource.buildBuilding(civID, getLimitOfBuildings(typeID));
            }
            case 15: {
                if ((GameValues.colonization.AI_TRIBAL_CAN_COLONIZE_WITHOUT_LAWS && Game_Calendar.TURN_ID >= GameValues.colonization.AI_TRIBAL_CAN_COLONIZE_WITHOUT_LAWS_MIN_TURN_ID) || Game.getCiv(civID).canColonize) {
                    return AI_ColonizationTribal.colonize(civID);
                }
                return build(civID, Game.oR.nextInt(15));
            }
            default: {
                return false;
            }
        }
    }
    
    public static final boolean build_NoBuildings(final int civID, final int typeID) {
        final int rand = Game.oR.nextInt(100);
        if (rand >= Game.aiValuesBuild.BUILD_NO_BUILDINGS_NEXT_TYPE) {
            if (rand < Game.aiValuesBuild.BUILD_NO_BUILDINGS_INVEST_ECONOMY) {
                AI_InvestInEconomy.investInEconomy(civID, (float)Game.aiValuesBuild.INVEST_IN_ECONOMY_MIN_LEFT_GOLD);
            }
            else if (rand < Game.aiValuesBuild.BUILD_NO_BUILDINGS_TAX_EFFICIENCY) {
                AI_IncreaseTaxEfficiency.increaseTaxEfficiency(civID, (float)Game.aiValuesBuild.INCREASE_TAX_EFFICIENCY_MIN_LEFT_GOLD);
            }
            else if (rand < Game.aiValuesBuild.BUILD_NO_BUILDINGS_PRODUCTION_BUILDING) {
                return AI_BuildResource.buildBuilding(civID, getLimitOfBuildings(typeID));
            }
        }
        return true;
    }
    
    public static int chooseBuildingType(final int civID) {
        final int randomValue = Game.oR.nextInt(Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_SCORE_TOTAL);
        int score = 0;
        for (int i = 0; i < Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_SCORE.length; ++i) {
            score += Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_SCORE[i];
            if (randomValue < score) {
                return i;
            }
        }
        return 0;
    }
    
    public static final void buildProvince_AIBuildScore_Default(final int civID, final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        if (province.getBuildingsLimit_FreeSlots() <= 0) {
            province.aiBuildScore = -999999.0f;
        }
        else {
            province.aiBuildScore = Game.aiValuesBuild.BUILD_SCORE_MIN;
            if (Game.getCiv(civID).getCapitalProvinceID() >= 0 && province.getContinent() == Game.getProvince(Game.getCiv(civID).getCapitalProvinceID()).getContinent()) {
                final Province province2 = province;
                province2.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_SAME_CONTINENT_AS_CAPITAL;
            }
            if (Game.terrainManager.terrains.get(province.getTerrainID()).BuildCost != 1.0) {
                final Province province3 = province;
                province3.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_TERRAIN_CONSTRUCTION_COST * (Game.terrainManager.terrains.get(province.getTerrainID()).BuildCost - 1.0f);
            }
            final Province province4 = province;
            province4.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_CONSTRUCTION_COST * (province.provBonuses.ConstructionCost + province.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_CONSTRUCTION_COST_PER_LVL);
            final Province province5 = province;
            province5.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_PER_GROWTH_RATE * province.getGrowthRateWithBonuses();
            final Province province6 = province;
            province6.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_PER_INFRASTRUCTURE * province.getInfrastructure();
            province.aiBuildScore *= 1.0f - Game.aiValuesBuild.BUILD_SCORE_CONSTRUCTED_BUILDINGS_MODIFIER * (province.iBuildingsSize / (float)province.iBuildingsLimit);
            if (!province.haveACore) {
                final Province province7 = province;
                province7.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_NON_CORE;
            }
            if (province.getReligion() != Game.getCiv(civID).getReligionID()) {
                final Province province8 = province;
                province8.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_DIFFERENT_RELIGION;
            }
        }
    }
    
    public static final void buildProvince_AIBuildScore_ProvinceMaintenance(final int nProvinceID) {
        if (Game.getProvince(nProvinceID).provBonuses.ProvinceMaintenance < 0.0f) {
            final Province province = Game.getProvince(nProvinceID);
            province.aiBuildScore += Math.abs(Game.aiValuesBuild.BUILD_SCORE_PER_PROVINCE_MAINTENANCE_REDUCTION * Game.getProvince(nProvinceID).provBonuses.ProvinceMaintenance);
        }
    }
    
    public static final void buildProvince_AIBuildScore_DistanceToCapital(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_DISTANCE_FROM_CAPITAL * Game.getProvince(nProvinceID).aiDistanceToCapital;
    }
    
    public static final void buildProvince_AIBuildScore_TaxEfficiency(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_PER_TAX_EFFICIENCY * Game.getProvince(nProvinceID).getTaxEfficiencyWithBonuses();
    }
    
    public static final void buildProvince_AIBuildScore_Economy(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_PER_ECONOMY * Game.getProvince(nProvinceID).getEconomyWithBonuses();
    }
    
    public static final void buildProvince_AIBuildScore_Manpower(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_PER_MANPOWER * Game.getProvince(nProvinceID).getManpower();
    }
    
    public static final void buildProvince_AIBuildScore_ResourcePrice(final int nProvinceID) {
        if (Game.getProvince(nProvinceID).getResourceID() >= 0) {
            final Province province = Game.getProvince(nProvinceID);
            province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_PER_PRICE_OF_RESOURCE * ResourcesManager.getPrice(Game.getProvince(nProvinceID).getResourceID());
        }
    }
    
    public static final void buildProvince_AIBuildScore_GrowthRateResearch(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_RESEARCH_PER_GROWTH_RATE * Game.getProvince(nProvinceID).getGrowthRateWithBonuses();
    }
    
    public static final void buildProvince_AIBuildScore_GrowthRateManpower(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_MANPOWER_PER_GROWTH_RATE * Game.getProvince(nProvinceID).getGrowthRateWithBonuses();
    }
    
    public static final void buildProvince_AIBuildScore_GrowthRateTaxEfficiency(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_TAX_EFFICIENCY_PER_GROWTH_RATE * Game.getProvince(nProvinceID).getGrowthRateWithBonuses();
    }
    
    public static final void buildProvince_AIBuildScore_ProvinceValue(final int nProvinceID) {
        final Province province = Game.getProvince(nProvinceID);
        province.aiBuildScore += Game.aiValuesBuild.BUILD_SCORE_PER_PROVINCE_VALUE * Game.getProvince(nProvinceID).fProvinceValue;
    }
    
    public static int getBuildingsAIScore(final int civID, final List<Building> tList) {
        int out = 0;
        for (int i = tList.size() - 1; i >= 0; --i) {
            out += BuildingsManager.buildings.get(tList.get(i).building).AI[tList.get(i).buildingID];
        }
        return out;
    }
    
    public static int getBuildingsAIScore_BestID(final int civID, final List<Building> tList, final int aiScore) {
        final int rand = Game.oR.nextInt(aiScore);
        int i = 0;
        int currentScore = 0;
        while (i < tList.size()) {
            currentScore += BuildingsManager.buildings.get(tList.get(i).building).AI[tList.get(i).buildingID];
            if (currentScore >= rand) {
                return i;
            }
            ++i;
        }
        return 0;
    }
    
    public static boolean balance_StopBuildingConstruction(final int civID) {
        return Game.getCiv(civID).getBalance() + getBuildingsMaintenanceCost_UnderConstruction(civID) < Game.aiValuesBuild.BUILD_MIN_BALANCE;
    }
    
    public static boolean balance_StopBuildingConstruction_Research(final int civID) {
        return Game.getCiv(civID).getBalance() + getBuildingsMaintenanceCost_UnderConstruction(civID) < Game.aiValuesBuild.BUILD_RESEARCH_MIN_BALANCE;
    }
    
    public static float getBuildingsMaintenanceCost_UnderConstruction(final int civID) {
        float out = 0.0f;
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(i).iBuildingsConstructionSize > 0) {
                out += Game.getProvince(i).getBuildingsConstruction_MaintenanceCosts();
            }
        }
        return -out;
    }
    
    public static List<Building> getUnlockedBuildings(final int civID, final List<Building> tList) {
        final List<Building> out = new ArrayList<Building>();
        final Civilization civ = Game.getCiv(civID);
        for (int i = tList.size() - 1; i >= 0; --i) {
            final Building building = tList.get(i);
            if (civ.isBuildingResearched(building.building, building.buildingID) && (BuildingsManager.buildings.get(building.building).RequiredReligionID < 0 || BuildingsManager.buildings.get(building.building).RequiredReligionID == civ.getReligionID()) && (BuildingsManager.buildings.get(building.building).RequiredGovernmentID < 0 || BuildingsManager.buildings.get(building.building).RequiredGovernmentID == civ.getIdeologyID())) {
                out.add(building);
            }
        }
        return out;
    }
    
    public static final void initBuildings() {
        try {
            int numOfBuildings = 0;
            int numOfBuildingsCapital = 0;
            for (int i = BuildingsManager.buildingsResourceStartID; i < BuildingsManager.buildingsResourceSize; ++i) {
                for (int j = 0; j < BuildingsManager.buildings.get(i).Name.length; ++j) {
                    AI_Build.resourceBuildings.add(new Building(i, j));
                }
            }
            for (int i = 0; i < BuildingsManager.buildingsSize; ++i) {
                for (int j = 0; j < BuildingsManager.buildings.get(i).Name.length; ++j) {
                    boolean added = false;
                    if (BuildingsManager.buildings.get(i).GroupID == 3) {
                        ++numOfBuildingsCapital;
                        AI_Build.averageCostOfBuilding_Capital += BuildingsManager.buildings.get(i).CostGold[j];
                        AI_Build.capitalBuildings.add(new Building(i, j));
                    }
                    else {
                        if (AI_Build.minCostOfBuilding > BuildingsManager.buildings.get(i).CostGold[j]) {
                            AI_Build.minCostOfBuilding = (int)BuildingsManager.buildings.get(i).CostGold[j];
                        }
                        AI_Build.averageCostOfBuilding += BuildingsManager.buildings.get(i).CostGold[j];
                        ++numOfBuildings;
                        if (BuildingsManager.buildings.get(i).MonthlyIncome != null && BuildingsManager.buildings.get(i).MonthlyIncome[j] > 0.0f) {
                            added = true;
                            AI_Build.gold.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).TaxEfficiency != null && BuildingsManager.buildings.get(i).TaxEfficiency[j] > 0.0f) {
                            added = true;
                            AI_Build.taxEfficiency.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).LocalTaxEfficiency != null && BuildingsManager.buildings.get(i).LocalTaxEfficiency[j] > 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.taxEfficiency.size() - 1; a >= 0; --a) {
                                if (AI_Build.taxEfficiency.get(a).building == i && AI_Build.taxEfficiency.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.taxEfficiency.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).MonthlyLegacy != null && BuildingsManager.buildings.get(i).MonthlyLegacy[j] > 0.0f) {
                            added = true;
                            AI_Build.legacy.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).DefenseBonus != null && BuildingsManager.buildings.get(i).DefenseBonus[j] > 0) {
                            added = true;
                            AI_Build.defensive.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).FortLevel != null && BuildingsManager.buildings.get(i).FortLevel[j] > 0) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.defensive.size() - 1; a >= 0; --a) {
                                if (AI_Build.defensive.get(a).building == i && AI_Build.defensive.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.defensive.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).FortDefense != null && BuildingsManager.buildings.get(i).FortDefense[j] > 0) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.defensive.size() - 1; a >= 0; --a) {
                                if (AI_Build.defensive.get(a).building == i && AI_Build.defensive.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.defensive.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).MaximumManpower != null && BuildingsManager.buildings.get(i).MaximumManpower[j] > 0) {
                            added = true;
                            AI_Build.manpower.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).LocalManpower != null && BuildingsManager.buildings.get(i).LocalManpower[j] > 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.manpower.size() - 1; a >= 0; --a) {
                                if (AI_Build.manpower.get(a).building == i && AI_Build.manpower.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.manpower.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).RecruitArmyCostInProvince != null && BuildingsManager.buildings.get(i).RecruitArmyCostInProvince[j] < 0.0f) {
                            added = true;
                            AI_Build.recruitArmyCost.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).LocalGrowthRate != null && BuildingsManager.buildings.get(i).LocalGrowthRate[j] > 0.0f) {
                            added = true;
                            AI_Build.growthRate.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).ConstructionCost != null && BuildingsManager.buildings.get(i).ConstructionCost[j] < 0) {
                            added = true;
                            AI_Build.constructionCost.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).InvestInEconomyCost != null && BuildingsManager.buildings.get(i).InvestInEconomyCost[j] < 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.constructionCost.size() - 1; a >= 0; --a) {
                                if (AI_Build.constructionCost.get(a).building == i && AI_Build.constructionCost.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.constructionCost.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).IncreaseManpowerCost != null && BuildingsManager.buildings.get(i).IncreaseManpowerCost[j] < 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.constructionCost.size() - 1; a >= 0; --a) {
                                if (AI_Build.constructionCost.get(a).building == i && AI_Build.constructionCost.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.constructionCost.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).IncreaseTaxEfficiencyCost != null && BuildingsManager.buildings.get(i).IncreaseTaxEfficiencyCost[j] < 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.constructionCost.size() - 1; a >= 0; --a) {
                                if (AI_Build.constructionCost.get(a).building == i && AI_Build.constructionCost.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.constructionCost.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).DevelopInfrastructureCost != null && BuildingsManager.buildings.get(i).DevelopInfrastructureCost[j] < 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.constructionCost.size() - 1; a >= 0; --a) {
                                if (AI_Build.constructionCost.get(a).building == i && AI_Build.constructionCost.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.constructionCost.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).IncreaseGrowthRateCost != null && BuildingsManager.buildings.get(i).IncreaseGrowthRateCost[j] < 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.constructionCost.size() - 1; a >= 0; --a) {
                                if (AI_Build.constructionCost.get(a).building == i && AI_Build.constructionCost.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.constructionCost.add(new Building(i, j));
                            }
                        }
                        if (BuildingsManager.buildings.get(i).ProvinceMaintenance != null && BuildingsManager.buildings.get(i).ProvinceMaintenance[j] < 0.0f) {
                            added = true;
                            AI_Build.provinceMaintenance.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).Economy != null && BuildingsManager.buildings.get(i).Economy[j] > 0.0f) {
                            added = true;
                            AI_Build.economy.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).ResearchPoints != null && BuildingsManager.buildings.get(i).ResearchPoints[j] > 0.0f) {
                            added = true;
                            AI_Build.research.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).ProductionEfficiency != null && BuildingsManager.buildings.get(i).ProductionEfficiency[j] > 0.0f) {
                            added = true;
                            AI_Build.productionEfficiency.add(new Building(i, j));
                        }
                        if (BuildingsManager.buildings.get(i).IncomeProduction != null && BuildingsManager.buildings.get(i).IncomeProduction[j] > 0.0f) {
                            added = true;
                            boolean innerAdd = true;
                            for (int a = AI_Build.productionEfficiency.size() - 1; a >= 0; --a) {
                                if (AI_Build.productionEfficiency.get(a).building == i && AI_Build.productionEfficiency.get(a).buildingID == j) {
                                    innerAdd = false;
                                    break;
                                }
                            }
                            if (innerAdd) {
                                AI_Build.productionEfficiency.add(new Building(i, j));
                            }
                        }
                        if (!added) {
                            AI_Build.rest.add(new Building(i, j));
                        }
                    }
                }
            }
            AI_Build.averageCostOfBuilding /= numOfBuildings;
            AI_Build.averageCostOfBuilding_Capital /= numOfBuildingsCapital;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void logBuilding(final String text, final List<Building> tList) {
        for (int i = 0; i < tList.size(); ++i) {
            CFG.LOG(text + ": " + BuildingsManager.buildings.get(tList.get(i).building).Name[tList.get(i).buildingID]);
        }
    }
    
    public static final void logBuildings() {
        logBuilding("Gold", AI_Build.gold);
        logBuilding("Legacy", AI_Build.legacy);
        logBuilding("Research", AI_Build.research);
        logBuilding("Manpower", AI_Build.manpower);
        logBuilding("Defensive", AI_Build.defensive);
        logBuilding("RecruitArmyCost", AI_Build.recruitArmyCost);
        logBuilding("TaxEfficiency", AI_Build.taxEfficiency);
        logBuilding("GrowthRate", AI_Build.growthRate);
        logBuilding("Economy", AI_Build.economy);
        logBuilding("ProductionEfficiency", AI_Build.productionEfficiency);
        logBuilding("ProvinceMaintenance", AI_Build.provinceMaintenance);
        logBuilding("ConstructionCost", AI_Build.constructionCost);
        logBuilding("Rest", AI_Build.rest);
        CFG.LOG("minCostOfBuilding: " + AI_Build.minCostOfBuilding);
        CFG.LOG("averageCostOfBuilding: " + AI_Build.averageCostOfBuilding);
        CFG.LOG("averageCostOfBuilding_Capital: " + AI_Build.averageCostOfBuilding_Capital);
    }
    
    static {
        AI_Build.minCostOfBuilding = 9999999;
        AI_Build.averageCostOfBuilding = 0.0f;
        AI_Build.averageCostOfBuilding_Capital = 0.0f;
        AI_Build.research = new ArrayList<Building>();
        AI_Build.gold = new ArrayList<Building>();
        AI_Build.legacy = new ArrayList<Building>();
        AI_Build.manpower = new ArrayList<Building>();
        AI_Build.defensive = new ArrayList<Building>();
        AI_Build.recruitArmyCost = new ArrayList<Building>();
        AI_Build.taxEfficiency = new ArrayList<Building>();
        AI_Build.growthRate = new ArrayList<Building>();
        AI_Build.economy = new ArrayList<Building>();
        AI_Build.productionEfficiency = new ArrayList<Building>();
        AI_Build.provinceMaintenance = new ArrayList<Building>();
        AI_Build.constructionCost = new ArrayList<Building>();
        AI_Build.rest = new ArrayList<Building>();
        AI_Build.capitalBuildings = new ArrayList<Building>();
        AI_Build.resourceBuildings = new ArrayList<Building>();
    }
    
    public static class Building
    {
        public int building;
        public int buildingID;
        
        public Building(final int building, final int buildingID) {
            this.building = building;
            this.buildingID = buildingID;
        }
    }
}
