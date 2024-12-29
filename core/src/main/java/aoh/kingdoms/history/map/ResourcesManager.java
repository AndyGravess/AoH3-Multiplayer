// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class ResourcesManager
{
    public static List<Resources> lResources;
    public static int iResourcesSize;
    public static List<Image> resourceImages;
    public static List<Float> priceChangePerc;
    public static List<PriceChange> priceChanges;
    public static float maxPrice;
    public static List<Boolean> uniqueGoods;
    public static List<Integer> worldResourcesProduced;
    public static List<Integer> worldResources_LargestProducer;
    public static List<Integer> worldResources_LargestProducer_Amount;
    public static int lastUpdateYear;
    
    public static float getPrice(final int iResourceID) {
        return ResourcesManager.lResources.get(iResourceID).Price * ResourcesManager.priceChangePerc.get(iResourceID);
    }
    
    public static float getPrice_Default(final int iResourceID) {
        return ResourcesManager.lResources.get(iResourceID).Price;
    }
    
    public static float getMonthlyIncome(final int iProvinceID) {
        return getMonthlyIncome(iProvinceID, Game.getProvince(iProvinceID).getResourceID());
    }
    
    public static float getMonthlyIncome(final int iProvinceID, final int iResourceID) {
        return getMonthlyIncome(iProvinceID, iResourceID, getProductionEfficiency(iProvinceID));
    }
    
    public static float getMonthlyIncome(final int iProvinceID, final int iResourceID, final float productionEfficiency) {
        if (iResourceID < 0) {
            return 0.0f;
        }
        if (ResourcesManager.lResources.get(iResourceID).RequiredTechID >= 0 && !Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getTechResearched(ResourcesManager.lResources.get(iResourceID).RequiredTechID)) {
            return 0.0f;
        }
        return getPrice(iResourceID) * productionEfficiency * Math.max(0.01f, 1.0f - Game.getProvince(iProvinceID).getDevastation() + ((Game.getProvince(iProvinceID).getReligion() == Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()) ? 0.0f : GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION) + (Game.getProvince(iProvinceID).haveACore ? 0.0f : GameValues.core.BASE_INCOME_NON_CORE) - Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() + (Game.getCiv(Game.getProvince(iProvinceID).getCivID()).civBonuses.IncomeProduction + Game.getProvince(iProvinceID).provBonuses.IncomeProduction) / 100.0f);
    }
    
    public static float getProductionEfficiency(final int iProvinceID) {
        return (GameValues.production.BASE_PRODUCTION_EFFICIENCY + getProductionEfficiency_FromEconomy(iProvinceID)) * Math.max(0.01f, 1.0f + (Game.getProvince(iProvinceID).provBonuses.ProductionEfficiency + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).civBonuses.ProductionEfficiency) / 100.0f + GameValues.infrastructure.INFRASTRUCTURE_PRODUCTION_EFFICIENCY_PER_LVL * Game.getProvince(iProvinceID).getInfrastructure());
    }
    
    public static float getProductionEfficiency_FromEconomy(final int iProvinceID) {
        return Game.getProvince(iProvinceID).getEconomyWithBonuses() * GameValues.production.PRODUCTION_EFFICIENCY_PER_ECONOMY;
    }
    
    public static float getProductionEfficiency_FromEconomy(final float fEconomy) {
        return fEconomy * GameValues.production.PRODUCTION_EFFICIENCY_PER_ECONOMY;
    }
    
    public static float getProducedGoods(final int iProvinceID) {
        if (Game.getProvince(iProvinceID).getResourceID() < 0) {
            return 0.0f;
        }
        if (ResourcesManager.lResources.get(Game.getProvince(iProvinceID).getResourceID()).RequiredTechID >= 0 && !Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getTechResearched(ResourcesManager.lResources.get(Game.getProvince(iProvinceID).getResourceID()).RequiredTechID)) {
            return 0.0f;
        }
        return Math.max(0.0f, (GameValues.production.BASE_PRODUCTION + GameValues.production.GOODS_PRODUCED_PER_ECONOMY * Game.getProvince(iProvinceID).getEconomyWithBonuses() * getProductionEfficiency(iProvinceID)) * (1.0f + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getWarWeariness() * GameValues.warWeariness.WW_GOODS_PRODUCTION_PER_POINT));
    }
    
    public static float getProducedGoods_ResourceCiv(final int civID, final int resourceID) {
        float out = 0.0f;
        final Civilization civ = Game.getCiv(civID);
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            if (Game.getProvince(civ.getProvinceID(i)).getResourceID() == resourceID) {
                out += getProducedGoods(civ.getProvinceID(i));
            }
        }
        return (float)(int)out;
    }
    
    public static int getLargestGoodsProducedByCiv(final int civID) {
        final List<Float> goodsProduced = new ArrayList<Float>();
        for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
            goodsProduced.add(0.0f);
        }
        int bestResourceID = -1;
        for (int j = 0; j < Game.getCiv(civID).getNumOfProvinces(); ++j) {
            if (Game.getProvince(Game.getCiv(civID).getProvinceID(j)).getResourceID() >= 0) {
                goodsProduced.set(Game.getProvince(Game.getCiv(civID).getProvinceID(j)).getResourceID(), goodsProduced.get(Game.getProvince(Game.getCiv(civID).getProvinceID(j)).getResourceID()) + getProducedGoods(Game.getCiv(civID).getProvinceID(j)));
                bestResourceID = Game.getProvince(Game.getCiv(civID).getProvinceID(j)).getResourceID();
            }
        }
        if (bestResourceID >= 0) {
            for (int j = 0; j < ResourcesManager.iResourcesSize; ++j) {
                if (goodsProduced.get(bestResourceID) < goodsProduced.get(j)) {
                    bestResourceID = j;
                }
            }
        }
        goodsProduced.clear();
        return bestResourceID;
    }
    
    public static final void resetPriceChangePerc() {
        for (int i = 0; i < ResourcesManager.lResources.size(); ++i) {
            ResourcesManager.priceChangePerc.set(i, 1.0f);
        }
        ResourcesManager.priceChanges.clear();
    }
    
    public static final void updatePriceChanges() {
        try {
            if (Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_CHANGE_PRICE_EXPIRED == 0) {
                for (int i = ResourcesManager.priceChanges.size() - 1; i >= 0; --i) {
                    if (ResourcesManager.priceChanges.get(i).expiresTurnID <= Game_Calendar.TURN_ID) {
                        if (updatePriceChanges_NumOfPriceChanges(ResourcesManager.priceChanges.get(i).resourceID) == 1) {
                            ResourcesManager.priceChangePerc.set(ResourcesManager.priceChanges.get(i).resourceID, 1.0f);
                        }
                        else {
                            ResourcesManager.priceChangePerc.set(ResourcesManager.priceChanges.get(i).resourceID, Math.max(0.01f, ResourcesManager.priceChangePerc.get(ResourcesManager.priceChanges.get(i).resourceID) - ResourcesManager.priceChanges.get(i).priceChange));
                        }
                        ResourcesManager.priceChanges.remove(i);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static int updatePriceChanges_NumOfPriceChanges(final int resourceID) {
        int out = 0;
        for (int i = ResourcesManager.priceChanges.size() - 1; i >= 0; --i) {
            if (ResourcesManager.priceChanges.get(i).resourceID == resourceID) {
                ++out;
            }
        }
        return out;
    }
    
    public static final void setPriceChangePerc(final int resourceID, final float newPriceChangePerc, final int expiresTurnID) {
        final float newPricePerc = Math.max(0.01f, ResourcesManager.priceChangePerc.get(resourceID) + newPriceChangePerc);
        final float changeInPrice = newPricePerc - ResourcesManager.priceChangePerc.get(resourceID);
        if (Math.abs(changeInPrice) >= 0.01f) {
            ResourcesManager.priceChangePerc.set(resourceID, newPricePerc);
            ResourcesManager.priceChanges.add(new PriceChange(resourceID, changeInPrice, expiresTurnID));
            updateIncomeOfCivsWithResource(resourceID);
        }
    }
    
    public static void updateIncomeOfCivsWithResource(final int iResourceID) {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            for (int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                if (Game.getProvince(Game.getCiv(i).getProvinceID(j)).getResourceID() == iResourceID) {
                    Game.gameThread.addCivUpdateTotalIncomePerMonth(i);
                    break;
                }
            }
        }
    }
    
    public static final void initUniqueCivsGoods() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).iUniqueResources = 0;
        }
        updateUniqueCivsGoods();
    }
    
    public static final void updateUniqueCivsGoods() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                updateUniqueCivGoods(i);
            }
        }
    }
    
    public static final void updateUniqueCivGoods(final int iCivID) {
        try {
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                ResourcesManager.uniqueGoods.set(i, false);
            }
            for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getResourceID() >= 0) {
                    ResourcesManager.uniqueGoods.set(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getResourceID(), true);
                }
            }
            Game.getCiv(iCivID).iUniqueResources = 0;
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                if (ResourcesManager.uniqueGoods.get(i)) {
                    final Civilization civ = Game.getCiv(iCivID);
                    ++civ.iUniqueResources;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void updateWorldResourcesProduced_NewYear() {
        if (Game_Calendar.currentYear != ResourcesManager.lastUpdateYear) {
            updateWorldResourcesProduced(false);
            if (Game.menuManager.getVisibleInGame_Goods() && InGame_Goods.inGoodsView) {
                Game.addSimpleTask(new Game.SimpleTask("rebuildGoods") {
                    @Override
                    public void update() {
                        if (Game.menuManager.getVisibleInGame_Goods() && InGame_Goods.inGoods) {
                            Game.menuManager.rebuildInGame_Goods(true);
                            Game.menuManager.setVisibleInGame_Goods(true);
                            InGame_Goods.lTime = 0L;
                        }
                    }
                });
            }
        }
    }
    
    private static final void updateCivBonuses(final int i, final int iCivID, final int mod) {
        if (ResourcesManager.lResources.get(i).MonthlyIncome != 0.0f) {
            final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
            civBonuses.MonthlyIncome += ResourcesManager.lResources.get(i).MonthlyIncome * mod;
        }
        if (ResourcesManager.lResources.get(i).TaxEfficiency != 0.0f) {
            final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
            civBonuses2.TaxEfficiency += ResourcesManager.lResources.get(i).TaxEfficiency * mod;
        }
        if (ResourcesManager.lResources.get(i).IncomeProduction != 0.0f) {
            final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
            civBonuses3.IncomeProduction += ResourcesManager.lResources.get(i).IncomeProduction * mod;
        }
        if (ResourcesManager.lResources.get(i).ProductionEfficiency != 0.0f) {
            final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
            civBonuses4.ProductionEfficiency += ResourcesManager.lResources.get(i).ProductionEfficiency * mod;
        }
        if (ResourcesManager.lResources.get(i).ProvinceMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
            civBonuses5.ProvinceMaintenance += ResourcesManager.lResources.get(i).ProvinceMaintenance * mod;
        }
        if (ResourcesManager.lResources.get(i).GrowthRate != 0.0f) {
            final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
            civBonuses6.GrowthRate += ResourcesManager.lResources.get(i).GrowthRate * mod;
            Game.getCiv(iCivID).updateResearchPerMonth();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (ResourcesManager.lResources.get(i).MonthlyLegacy != 0.0f) {
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
        civBonuses7.MonthlyLegacy += ResourcesManager.lResources.get(i).MonthlyLegacy * mod;
        if (ResourcesManager.lResources.get(i).MaxManpower != 0.0f) {
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (ResourcesManager.lResources.get(i).MaxManpower != 0.0f) {
            final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
            civBonuses8.MaxManpower += ResourcesManager.lResources.get(i).MaxManpower * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (ResourcesManager.lResources.get(i).ManpowerRecoverySpeed != 0.0f) {
            final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
            civBonuses9.ManpowerRecoverySpeed += ResourcesManager.lResources.get(i).ManpowerRecoverySpeed * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (ResourcesManager.lResources.get(i).ArmyMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
            civBonuses10.ArmyMaintenance += ResourcesManager.lResources.get(i).ArmyMaintenance * mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }
        if (ResourcesManager.lResources.get(i).RecruitmentTime != 0.0f) {
            final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
            civBonuses11.RecruitmentTime += ResourcesManager.lResources.get(i).RecruitmentTime * mod;
        }
        if (ResourcesManager.lResources.get(i).RecruitArmyCost != 0.0f) {
            final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
            civBonuses12.RecruitArmyCost += ResourcesManager.lResources.get(i).RecruitArmyCost * mod;
        }
        if (ResourcesManager.lResources.get(i).RecruitArmyFirstLineCost != 0.0f) {
            final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
            civBonuses13.RecruitArmyFirstLineCost += ResourcesManager.lResources.get(i).RecruitArmyFirstLineCost * mod;
        }
        if (ResourcesManager.lResources.get(i).RecruitArmySecondLineCost != 0.0f) {
            final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
            civBonuses14.RecruitArmySecondLineCost += ResourcesManager.lResources.get(i).RecruitArmySecondLineCost * mod;
        }
        if (ResourcesManager.lResources.get(i).ResearchPoints != 0.0f) {
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (ResourcesManager.lResources.get(i).ResearchPoints != 0.0f) {
            final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
            civBonuses15.ResearchPoints += ResourcesManager.lResources.get(i).ResearchPoints * mod;
        }
        if (ResourcesManager.lResources.get(i).TechnologyCost != 0.0f) {
            final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
            civBonuses16.TechnologyCost += ResourcesManager.lResources.get(i).TechnologyCost * mod;
        }
        if (ResourcesManager.lResources.get(i).ConstructionCost != 0.0f) {
            final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
            civBonuses17.ConstructionCost += ResourcesManager.lResources.get(i).ConstructionCost * mod;
        }
        if (ResourcesManager.lResources.get(i).AdministrationBuildingsCost != 0.0f) {
            final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
            civBonuses18.AdministrationBuildingsCost += ResourcesManager.lResources.get(i).AdministrationBuildingsCost * mod;
        }
        if (ResourcesManager.lResources.get(i).MilitaryBuildingsCost != 0.0f) {
            final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
            civBonuses19.MilitaryBuildingsCost += ResourcesManager.lResources.get(i).MilitaryBuildingsCost * mod;
        }
        if (ResourcesManager.lResources.get(i).EconomyBuildingsCost != 0.0f) {
            final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
            civBonuses20.EconomyBuildingsCost += ResourcesManager.lResources.get(i).EconomyBuildingsCost * mod;
        }
        if (ResourcesManager.lResources.get(i).ConstructionTime != 0.0f) {
            final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
            civBonuses21.ConstructionTime += ResourcesManager.lResources.get(i).ConstructionTime * mod;
        }
        if (ResourcesManager.lResources.get(i).InvestInEconomyCost != 0.0f) {
            final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
            civBonuses22.InvestInEconomyCost += ResourcesManager.lResources.get(i).InvestInEconomyCost * mod;
        }
        if (ResourcesManager.lResources.get(i).IncreaseManpowerCost != 0.0f) {
            final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
            civBonuses23.IncreaseManpowerCost += ResourcesManager.lResources.get(i).IncreaseManpowerCost * mod;
        }
        if (ResourcesManager.lResources.get(i).GeneralAttack != 0) {
            final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
            civBonuses24.GeneralAttack += ResourcesManager.lResources.get(i).GeneralAttack * mod;
        }
        if (ResourcesManager.lResources.get(i).GeneralDefense != 0) {
            final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
            civBonuses25.GeneralDefense += ResourcesManager.lResources.get(i).GeneralDefense * mod;
        }
        if (ResourcesManager.lResources.get(i).UnitsAttack != 0) {
            final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
            civBonuses26.UnitsAttack += ResourcesManager.lResources.get(i).UnitsAttack * mod;
        }
        if (ResourcesManager.lResources.get(i).UnitsDefense != 0) {
            final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
            civBonuses27.UnitsDefense += ResourcesManager.lResources.get(i).UnitsDefense * mod;
        }
        if (ResourcesManager.lResources.get(i).GeneralDefense != 0) {
            final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
            civBonuses28.MaxMorale += ResourcesManager.lResources.get(i).GeneralDefense * mod;
        }
        if (ResourcesManager.lResources.get(i).ArmyMovementSpeed != 0.0f) {
            final CivilizationBonuses civBonuses29 = Game.getCiv(iCivID).civBonuses;
            civBonuses29.ArmyMovementSpeed += ResourcesManager.lResources.get(i).ArmyMovementSpeed * mod;
        }
        if (ResourcesManager.lResources.get(i).SiegeEffectiveness != 0.0f) {
            final CivilizationBonuses civBonuses30 = Game.getCiv(iCivID).civBonuses;
            civBonuses30.SiegeEffectiveness += ResourcesManager.lResources.get(i).SiegeEffectiveness * mod;
        }
        if (ResourcesManager.lResources.get(i).ImproveRelationsModifier != 0.0f) {
            final CivilizationBonuses civBonuses31 = Game.getCiv(iCivID).civBonuses;
            civBonuses31.ImproveRelationsModifier += ResourcesManager.lResources.get(i).ImproveRelationsModifier * mod;
        }
        if (ResourcesManager.lResources.get(i).IncomeFromVassals != 0.0f) {
            final CivilizationBonuses civBonuses32 = Game.getCiv(iCivID).civBonuses;
            civBonuses32.IncomeFromVassals += ResourcesManager.lResources.get(i).IncomeFromVassals * mod;
        }
        if (ResourcesManager.lResources.get(i).LoanInterest != 0.0f) {
            final CivilizationBonuses civBonuses33 = Game.getCiv(iCivID).civBonuses;
            civBonuses33.LoanInterest += ResourcesManager.lResources.get(i).LoanInterest * mod;
        }
        if (ResourcesManager.lResources.get(i).AggressiveExpansion != 0.0f) {
            final CivilizationBonuses civBonuses34 = Game.getCiv(iCivID).civBonuses;
            civBonuses34.AggressiveExpansion += ResourcesManager.lResources.get(i).AggressiveExpansion * mod;
        }
        if (ResourcesManager.lResources.get(i).RevolutionaryRisk != 0.0f) {
            final CivilizationBonuses civBonuses35 = Game.getCiv(iCivID).civBonuses;
            civBonuses35.RevolutionaryRisk += ResourcesManager.lResources.get(i).RevolutionaryRisk * mod;
        }
        if (ResourcesManager.lResources.get(i).CoreCost != 0.0f) {
            final CivilizationBonuses civBonuses36 = Game.getCiv(iCivID).civBonuses;
            civBonuses36.CoreCost += ResourcesManager.lResources.get(i).CoreCost * mod;
        }
        if (ResourcesManager.lResources.get(i).BattleWidth != 0) {
            final CivilizationBonuses civBonuses37 = Game.getCiv(iCivID).civBonuses;
            civBonuses37.BattleWidth += ResourcesManager.lResources.get(i).BattleWidth * mod;
        }
        if (ResourcesManager.lResources.get(i).RegimentsLimit != 0) {
            final CivilizationBonuses civBonuses38 = Game.getCiv(iCivID).civBonuses;
            civBonuses38.RegimentsLimit += ResourcesManager.lResources.get(i).RegimentsLimit * mod;
        }
        Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
    }
    
    public static void updateWorldResourcesProduced(final boolean init) {
        try {
            final List<Boolean> playerLargest = new ArrayList<Boolean>();
            if (ResourcesManager.worldResources_LargestProducer.size() > 0) {
                for (int j = 0; j < ResourcesManager.iResourcesSize; ++j) {
                    playerLargest.add(ResourcesManager.worldResources_LargestProducer.get(j) == Game.player.iCivID);
                }
            }
            else {
                for (int j = 0; j < ResourcesManager.iResourcesSize; ++j) {
                    playerLargest.add(false);
                }
            }
            if (!init) {
                try {
                    for (int i = 0, iSize = Math.max(ResourcesManager.iResourcesSize, ResourcesManager.worldResources_LargestProducer.size()); i < iSize; ++i) {
                        if (ResourcesManager.worldResources_LargestProducer.get(i) > 0 && Game.getCiv(ResourcesManager.worldResources_LargestProducer.get(i)).getTechResearched(ResourcesManager.lResources.get(i).RequiredTechID)) {
                            updateCivBonuses(i, ResourcesManager.worldResources_LargestProducer.get(i), -1);
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                for (int i = 0; i < Game.getCivsSize(); ++i) {
                    Game.getCiv(i).resetGoodsProduced();
                    Game.getCiv(i).largestProducerNum = 0;
                }
            }
            else {
                for (int i = 0; i < Game.getCivsSize(); ++i) {
                    Game.getCiv(i).initGoodsProduced();
                    Game.getCiv(i).largestProducerNum = 0;
                }
            }
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                ResourcesManager.worldResourcesProduced.set(i, 0);
            }
            ResourcesManager.worldResources_LargestProducer.clear();
            ResourcesManager.worldResources_LargestProducer_Amount.clear();
            ResourcesManager.lastUpdateYear = Game_Calendar.currentYear;
            for (int i = 0; i < Game.getCivsSize(); ++i) {
                final List<Integer> tList = new ArrayList<Integer>();
                for (int k = 0; k < ResourcesManager.iResourcesSize; ++k) {
                    tList.add(0);
                }
            }
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).getResourceID() >= 0) {
                    final int goodsProduced = (int)(getProducedGoods(i) * 100.0f);
                    ResourcesManager.worldResourcesProduced.set(Game.getProvince(i).getResourceID(), ResourcesManager.worldResourcesProduced.get(Game.getProvince(i).getResourceID()) + goodsProduced);
                    Game.getCiv(Game.getProvince(i).getCivID()).addGoodsProduced(Game.getProvince(i).getResourceID(), goodsProduced);
                }
            }
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                ResourcesManager.worldResourcesProduced.set(i, ResourcesManager.worldResourcesProduced.get(i));
            }
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                int largestProducer = 0;
                for (int k = 1; k < Game.getCivsSize(); ++k) {
                    if (Game.getCiv(largestProducer).getGoodsProduced(i) < Game.getCiv(k).getGoodsProduced(i)) {
                        largestProducer = k;
                    }
                }
                if (Game.getCiv(largestProducer).getTechResearched(ResourcesManager.lResources.get(i).RequiredTechID)) {
                    ResourcesManager.worldResources_LargestProducer.add(largestProducer);
                    ResourcesManager.worldResources_LargestProducer_Amount.add(Game.getCiv(largestProducer).getGoodsProduced(i));
                }
                else {
                    ResourcesManager.worldResources_LargestProducer.add(0);
                    ResourcesManager.worldResources_LargestProducer_Amount.add(Game.getCiv(largestProducer).getGoodsProduced(i));
                }
            }
            if (!init) {
                for (int j = 0; j < ResourcesManager.iResourcesSize; ++j) {
                    if (playerLargest.get(j)) {
                        if (ResourcesManager.worldResources_LargestProducer.get(j) != Game.player.iCivID) {
                            Game.player.addNotification(new Notification(Notification.Notification_Type.NO_LONGER_LARGEST_PRODUCER, Game.lang.get("WeAreNoLongerTheLargestProducerOf") + ": " + ResourcesManager.lResources.get(j).Name, j, Game_Calendar.TURN_ID, Notification.Notification_BG.RED));
                        }
                    }
                    else if (ResourcesManager.worldResources_LargestProducer.get(j) == Game.player.iCivID) {
                        Game.player.addNotification(new Notification(Notification.Notification_Type.LARGEST_PRODUCER, Game.lang.get("WeAreTheLargestProducerOf") + ": " + ResourcesManager.lResources.get(j).Name, j, Game_Calendar.TURN_ID, Notification.Notification_BG.GREEN));
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        try {
            for (int l = 0, iSize2 = Math.max(ResourcesManager.iResourcesSize, ResourcesManager.worldResources_LargestProducer.size()); l < iSize2; ++l) {
                if (ResourcesManager.worldResources_LargestProducer.get(l) > 0 && Game.getCiv(ResourcesManager.worldResources_LargestProducer.get(l)).getTechResearched(ResourcesManager.lResources.get(l).RequiredTechID)) {
                    updateCivBonuses(l, ResourcesManager.worldResources_LargestProducer.get(l), 1);
                    final Civilization civ = Game.getCiv(ResourcesManager.worldResources_LargestProducer.get(l));
                    ++civ.largestProducerNum;
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static final void loadResources() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/resources/Resources.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigResourcesData.class, "Resources", (Class)Resources.class);
            ConfigResourcesData data = (ConfigResourcesData)json.fromJson((Class)ConfigResourcesData.class, fileContent);
            int id = 0;
            for (final Object e : data.Resources) {
                ResourcesManager.lResources.add((Resources)e);
                ResourcesManager.lResources.get(id).Name = Game.lang.get(ResourcesManager.lResources.get(id).Name);
                ResourcesManager.lResources.get(id).Color[0] /= 255.0f;
                ResourcesManager.lResources.get(id).Color[1] /= 255.0f;
                ResourcesManager.lResources.get(id).Color[2] /= 255.0f;
                ResourcesManager.worldResourcesProduced.add(0);
                ResourcesManager.priceChangePerc.add(1.0f);
                ResourcesManager.maxPrice = Math.max(ResourcesManager.maxPrice, ResourcesManager.lResources.get(id).Price);
                ++id;
            }
            data = null;
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        ResourcesManager.iResourcesSize = ResourcesManager.lResources.size();
        for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
            ResourcesManager.uniqueGoods.add(false);
        }
        loadResourcesImages();
        for (int r = 0; r < ResourcesManager.iResourcesSize; ++r) {
            for (int j = BuildingsManager.buildingsResourceStartID; j < BuildingsManager.buildingsResourceSize; ++j) {
                if (BuildingsManager.buildings.get(j).RequiredResource == r) {
                    ResourcesManager.lResources.get(r).uniqueBuildingID = j;
                    break;
                }
            }
        }
    }
    
    public static final void loadResourcesImages() {
        final FileHandle tempFileT = FileManager.loadFile("game/resources/resourcesImages/numOfImages.txt");
        for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
            if (FileManager.loadFile("game/resources/resourcesImages/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                ResourcesManager.resourceImages.add(new Image(ImageManager.loadTexture("game/resources/resourcesImages/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
            else {
                ResourcesManager.resourceImages.add(new Image(ImageManager.loadTexture("game/resources/resourcesImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
        }
    }
    
    public static String getResourceName(final int iID) {
        if (iID >= 0) {
            return ResourcesManager.lResources.get(iID).Name;
        }
        return Game.lang.get("None");
    }
    
    public static String getResourceGroupName(final int i) {
        switch (i) {
            case 0: {
                return Game.lang.get("Food");
            }
            case 1: {
                return Game.lang.get("Commodities");
            }
            case 2: {
                return Game.lang.get("LuxuryCommodities");
            }
            case 3: {
                return Game.lang.get("Luxury");
            }
            case 4: {
                return Game.lang.get("ProductionResources");
            }
            default: {
                return Game.lang.get("Commodities");
            }
        }
    }
    
    public static boolean hasResource(final int civID, final int resourceID) {
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getResourceID() == resourceID) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ResourcesManager.lResources = new ArrayList<Resources>();
        ResourcesManager.iResourcesSize = 0;
        ResourcesManager.resourceImages = new ArrayList<Image>();
        ResourcesManager.priceChangePerc = new ArrayList<Float>();
        ResourcesManager.priceChanges = new ArrayList<PriceChange>();
        ResourcesManager.maxPrice = 0.01f;
        ResourcesManager.uniqueGoods = new ArrayList<Boolean>();
        ResourcesManager.worldResourcesProduced = new ArrayList<Integer>();
        ResourcesManager.worldResources_LargestProducer = new ArrayList<Integer>();
        ResourcesManager.worldResources_LargestProducer_Amount = new ArrayList<Integer>();
        ResourcesManager.lastUpdateYear = 0;
    }
    
    public static class PriceChange
    {
        public int resourceID;
        public float priceChange;
        public int expiresTurnID;
        
        public PriceChange(final int resourceID, final float priceChange, final int expiresTurnID) {
            this.resourceID = resourceID;
            this.priceChange = priceChange;
            this.expiresTurnID = expiresTurnID;
        }
    }
    
    public static class ConfigResourcesData
    {
        public String Age_of_History;
        public ArrayList Resources;
    }
    
    public static class Resources
    {
        public String Name;
        public int ID;
        public int ImageID;
        public int GroupID;
        public int uniqueBuildingID;
        public float[] Color;
        public float Price;
        public int RequiredTechID;
        public float MonthlyIncome;
        public float IncomeProduction;
        public float ProductionEfficiency;
        public float TaxEfficiency;
        public float ProvinceMaintenance;
        public float GrowthRate;
        public float MonthlyLegacy;
        public float MaxManpower;
        public float ManpowerRecoverySpeed;
        public float ArmyMaintenance;
        public float RecruitmentTime;
        public float RecruitArmyCost;
        public float RecruitArmyFirstLineCost;
        public float RecruitArmySecondLineCost;
        public float ResearchPoints;
        public float TechnologyCost;
        public float ConstructionCost;
        public float AdministrationBuildingsCost;
        public float MilitaryBuildingsCost;
        public float EconomyBuildingsCost;
        public float ConstructionTime;
        public float InvestInEconomyCost;
        public float IncreaseManpowerCost;
        public int GeneralAttack;
        public int GeneralDefense;
        public int UnitsAttack;
        public int UnitsDefense;
        public float MaxMorale;
        public float ArmyMovementSpeed;
        public float SiegeEffectiveness;
        public float ImproveRelationsModifier;
        public float IncomeFromVassals;
        public float LoanInterest;
        public float AggressiveExpansion;
        public float RevolutionaryRisk;
        public float CoreCost;
        public int BattleWidth;
        public int RegimentsLimit;
        
        public Resources() {
            this.uniqueBuildingID = -1;
        }
    }
}
