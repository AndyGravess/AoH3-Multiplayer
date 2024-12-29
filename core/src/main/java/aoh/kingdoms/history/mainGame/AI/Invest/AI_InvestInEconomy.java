// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Invest;

import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import java.util.List;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Loan;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_InvestInEconomy
{
    public static final float SCORE_CANT_INVEST = 0.0f;
    
    public static final void investInEconomy(final int iCivID, final float minGold) {
        if (Game.getCiv(iCivID).fLegacy < GameValues.economy.INVEST_COST_LEGACY * 4.0f) {
            return;
        }
        final int rand = Game.oR.nextInt(100);
        if (rand < Game.aiValuesBuild.INVEST_IN_ECONOMY_SCORE_RANDOM || Game.getCiv(iCivID).getNumOfProvinces() < Game.aiValues.INVEST_RANDOM_IF_PROVINCES_BELOW) {
            for (int i = Math.min(Game.aiValues.INVEST_LIMIT_PER_TURN_RANDOM_LIMIT, 1 + Game.getCiv(iCivID).getNumOfProvinces() / 2); i >= 0; --i) {
                final int randomProvince = Game.aiManager.getRandomProvince(iCivID);
                if (!Game.getProvince(randomProvince).addInvestInProvince()) {
                    return;
                }
                if (minGold >= Game.getCiv(iCivID).fGold) {
                    return;
                }
            }
            return;
        }
        if (rand < Game.aiValuesBuild.INVEST_IN_ECONOMY_SCORE_GROWTH_RATE) {
            buildScore_InvestInEconomy_GrowthRate(iCivID);
        }
        else if (rand < Game.aiValuesBuild.INVEST_IN_ECONOMY_SCORE_DISTANCE) {
            buildScore_InvestInEconomy_Distance(iCivID);
        }
        else if (rand < Game.aiValuesBuild.INVEST_IN_ECONOMY_SCORE_PAYOFF) {
            buildScore_InvestInEconomy_PayOff(iCivID);
        }
        else {
            buildScore_InvestInEconomy(iCivID);
        }
        AI_Loan.takeLoan_Chance(iCivID, Game.aiValues.TAKE_LOAN_CHANCE_INVEST_IN_ECONOMY);
        final List<Integer> tInvested = new ArrayList<Integer>();
        for (int j = Game.aiValues.INVEST_LIMIT_PER_TURN; j > 0; --j) {
            int investInProvinceID = -1;
            int checkInvestID;
            for (checkInvestID = 0; checkInvestID < Game.getCiv(iCivID).getNumOfProvinces(); ++checkInvestID) {
                if (!tInvested.contains(Game.getCiv(iCivID).getProvinceID(checkInvestID)) && Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore > 0.0f) {
                    investInProvinceID = checkInvestID;
                    break;
                }
            }
            if (investInProvinceID < 0) {
                tInvested.clear();
                if (Game.oR.nextInt(100) < Game.aiValuesBuild.INVEST_IN_ECONOMY_MAX_VALUE_INCREASE_GROWTH_RATE_CHANCE) {
                    AI_GrowthRate.increaseGrowthRate(iCivID, minGold);
                }
                else {
                    AI_IncreaseManpower.increaseManpower(iCivID, minGold);
                }
                return;
            }
            while (checkInvestID < Game.getCiv(iCivID).getNumOfProvinces()) {
                if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore > 0.0f && Game.getProvince(Game.getCiv(iCivID).getProvinceID(investInProvinceID)).aiInvestScore < Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore && !tInvested.contains(Game.getCiv(iCivID).getProvinceID(checkInvestID))) {
                    investInProvinceID = checkInvestID;
                }
                ++checkInvestID;
            }
            for (int a = 1 + Game.oR.nextInt(Game.aiValuesBuild.INVEST_IN_ECONOMY_RANDOM); a >= 0; --a) {
                if (!Game.getProvince(Game.getCiv(iCivID).getProvinceID(investInProvinceID)).addInvestInProvince() && (Game.getCiv(iCivID).fGold < Game.getInvestCost(Game.getCiv(iCivID).getProvinceID(investInProvinceID)) || Game.getCiv(iCivID).fLegacy < Game.getInvestCost_Legacy(Game.getCiv(iCivID).getProvinceID(investInProvinceID)))) {
                    tInvested.clear();
                    return;
                }
            }
            if (minGold >= Game.getCiv(iCivID).fGold) {
                tInvested.clear();
                return;
            }
            tInvested.add(Game.getCiv(iCivID).getProvinceID(investInProvinceID));
            if (tInvested.size() == Game.getCiv(iCivID).getNumOfProvinces()) {
                tInvested.clear();
                return;
            }
        }
    }
    
    public static float buildScore_CoreReligion(final int provinceID) {
        return 1.0f + (Game.getProvince(provinceID).haveACore ? Game.aiValues.SCORE_INVEST_NON_CORE : 0.0f) + ((Game.getProvince(provinceID).getReligion() == Game.getCiv(Game.getProvince(provinceID).getReligion()).getReligionID()) ? 0.0f : Game.aiValues.SCORE_INVEST_DIFFERENT_RELIGION);
    }
    
    public static final void buildScore_InvestInEconomy(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_InvestInEconomy_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_InvestInEconomy_ProvinceID(final int provinceID) {
        if (Game.canInvestInEconomy(provinceID)) {
            Game.getProvince(provinceID).aiInvestScore = 0.0f;
        }
        else {
            Game.getProvince(provinceID).aiInvestScore = Game.aiValues.SCORE_BASE + Game.getProvince(provinceID).getGrowthRate() / 10.0f + (((Game.getProvince(provinceID).getResourceID() >= 0 && Game.getCiv(Game.getProvince(provinceID).getCivID()).getTechResearched(ResourcesManager.lResources.get(Game.getProvince(provinceID).getResourceID()).RequiredTechID)) ? (Game.aiValues.SCORE_INVEST_ECONOMY_RESOURCE_MIN + Game.aiValues.SCORE_INVEST_ECONOMY_RESOURCE_PRICE * ResourcesManager.getPrice(Game.getProvince(provinceID).getResourceID())) : 0.0f) + Game.aiValues.SCORE_INVEST_ECONOMY_PER_INFRASTRUCTURE * Game.getProvince(provinceID).getInfrastructure()) / Game.getProvince(provinceID).getEconomyWithBonuses();
            final Province province = Game.getProvince(provinceID);
            province.aiInvestScore *= buildScore_CoreReligion(provinceID);
        }
    }
    
    public static final void buildScore_InvestInEconomy_Distance(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_InvestInEconomy_Distance_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_InvestInEconomy_Distance_ProvinceID(final int provinceID) {
        if (Game.canInvestInEconomy(provinceID)) {
            Game.getProvince(provinceID).aiInvestScore = 0.0f;
        }
        else {
            Game.getProvince(provinceID).aiInvestScore = Game.aiValues.SCORE_BASE + Game.aiValues.SCORE_INVEST_ECONOMY_DISTANCE * (Game.aiValues.SCORE_INVEST_ECONOMY_DISTANCE_W_GR * (Game.getProvince(provinceID).getGrowthRateWithBonuses() / Game.getProvince(provinceID).getEconomyWithBonuses()) * (Game.aiValues.SCORE_INVEST_ECONOMY_DISTANCE_W_DIS * (1.0f - Game.getProvince(provinceID).aiDistanceToCapital)));
            final Province province = Game.getProvince(provinceID);
            province.aiInvestScore *= buildScore_CoreReligion(provinceID);
        }
    }
    
    public static final void buildScore_InvestInEconomy_GrowthRate(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_InvestInEconomy_GrowthRate_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_InvestInEconomy_GrowthRate_ProvinceID(final int provinceID) {
        if (Game.canInvestInEconomy(provinceID)) {
            Game.getProvince(provinceID).aiInvestScore = 0.0f;
        }
        else {
            Game.getProvince(provinceID).aiInvestScore = Game.aiValues.SCORE_BASE + Game.aiValues.SCORE_INVEST_ECONOMY_GROWTH_RATE * (Game.getProvince(provinceID).getGrowthRateWithBonuses() / Game.getProvince(provinceID).getEconomyWithBonuses());
            final Province province = Game.getProvince(provinceID);
            province.aiInvestScore *= buildScore_CoreReligion(provinceID);
        }
    }
    
    public static final void buildScore_InvestInEconomy_PayOff(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_InvestInEconomy_PayOff_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_InvestInEconomy_PayOff_ProvinceID(final int provinceID) {
        if (Game.canInvestInEconomy(provinceID)) {
            Game.getProvince(provinceID).aiInvestScore = 0.0f;
        }
        else {
            final float tIncomeProduction = (ResourcesManager.getMonthlyIncome(provinceID, Game.getProvince(provinceID).getResourceID(), ResourcesManager.getProductionEfficiency(provinceID) + ResourcesManager.getProductionEfficiency_FromEconomy(Game.getInvestEconomyGrowth(provinceID))) - ResourcesManager.getMonthlyIncome(provinceID, Game.getProvince(provinceID).getResourceID(), ResourcesManager.getProductionEfficiency(provinceID))) / 100.0f;
            Game.getProvince(provinceID).aiInvestScore = 100000.0f - Game.getInvestCost(provinceID) / (Game.getIncomeFromEconomy_Invest(provinceID) + tIncomeProduction);
            final Province province = Game.getProvince(provinceID);
            province.aiInvestScore *= buildScore_CoreReligion(provinceID);
        }
    }
}
