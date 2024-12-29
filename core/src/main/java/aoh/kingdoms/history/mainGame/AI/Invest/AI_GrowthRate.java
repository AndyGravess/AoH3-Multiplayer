// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Invest;

import aoc.kingdoms.lukasz.map.province.Province;
import java.util.List;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_GrowthRate
{
    public static final void increaseGrowthRate(final int iCivID, final float minGold) {
        final int rand = Game.oR.nextInt(100);
        if (rand < Game.aiValuesBuild.INCREASE_GROWTH_RATE_SCORE_RANDOM) {
            for (int i = Math.min(Game.aiValues.INVEST_LIMIT_PER_TURN_RANDOM_LIMIT, 1 + Game.getCiv(iCivID).getNumOfProvinces() / 2); i >= 0; --i) {
                final int randomProvince = Game.aiManager.getRandomProvince(iCivID);
                if (!Game.getProvince(randomProvince).addIncreaseGrowthRateInProvince()) {
                    return;
                }
                if (minGold >= Game.getCiv(iCivID).fGold) {
                    return;
                }
            }
            return;
        }
        buildScore_IncreaseGrowthRate(iCivID);
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
                return;
            }
            while (checkInvestID < Game.getCiv(iCivID).getNumOfProvinces()) {
                if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore > 0.0f && Game.getProvince(Game.getCiv(iCivID).getProvinceID(investInProvinceID)).aiInvestScore < Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore && !tInvested.contains(Game.getCiv(iCivID).getProvinceID(checkInvestID))) {
                    investInProvinceID = checkInvestID;
                }
                ++checkInvestID;
            }
            for (int a = 1 + Game.oR.nextInt(Game.aiValuesBuild.INCREASE_GROWTH_RATE_RANDOM); a >= 0; --a) {
                if (!Game.getProvince(Game.getCiv(iCivID).getProvinceID(investInProvinceID)).addIncreaseGrowthRateInProvince() && Game.getCiv(iCivID).fGold < Game.getIncreaseGrowthRateCost(Game.getCiv(iCivID).getProvinceID(investInProvinceID))) {
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
    
    public static final void buildScore_IncreaseGrowthRate(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_IncreaseGrowthRate_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_IncreaseGrowthRate_ProvinceID(final int provinceID) {
        Game.getProvince(provinceID).aiInvestScore = Game.aiValues.SCORE_BASE + Game.getProvince(provinceID).getEconomyWithBonuses();
        final Province province = Game.getProvince(provinceID);
        province.aiInvestScore *= AI_InvestInEconomy.buildScore_CoreReligion(provinceID);
    }
}
