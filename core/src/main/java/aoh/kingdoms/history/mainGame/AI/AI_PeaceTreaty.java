// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import java.util.List;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_PeaceTreaty
{
    public static void peaceTreaty(final String warKey, final int civID, final int lostID) {
    }
    
    public static void buildProvinceScore(final int civID, final int provinceID, final int lostID) {
        final Province province = Game.getProvince(provinceID);
        province.aiPeaceScore = 0;
        int neighboringPerc = 0;
        float neighboringPerc_Total = 0.0f;
        for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
            if (Game.getProvince(province.getNeighboringProvinces(i)).getWasteland() < 0) {
                if (Game.getProvince(province.getNeighboringProvinces(i)).aiPeaceCivID == civID || Game.getProvince(province.getNeighboringProvinces(i)).aiPeaceCivID == Game.getProvince(province.getNeighboringProvinces(i)).getCivID()) {
                    ++neighboringPerc_Total;
                }
                if (Game.getProvince(province.getNeighboringProvinces(i)).aiPeaceCivID == lostID) {
                    province.aiPeaceScore += (int)GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_LOSER_SCORE;
                }
                if (Game.getProvince(province.getNeighboringProvinces(i)).aiPeaceCivID == civID) {
                    province.aiPeaceScore += (int)GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_WINNER_SCORE;
                    ++neighboringPerc;
                }
            }
        }
        if (neighboringPerc_Total > 0.0f) {
            province.aiPeaceScore += (int)(GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_WINNER_PERC * (neighboringPerc / neighboringPerc_Total));
        }
        if (province.accessToMainSea) {
            province.aiPeaceScore += (int)GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_SEA;
            boolean extraSeaPoints = false;
            for (int j = 0; j < province.getNeighboringProvincesSize(); ++j) {
                if (Game.getProvince(province.getNeighboringProvinces(j)).getWasteland() < 0 && Game.getProvince(province.getNeighboringProvinces(j)).aiPeaceCivID != province.getCivID()) {
                    extraSeaPoints = true;
                    break;
                }
            }
            if (extraSeaPoints) {
                province.aiPeaceScore += (int)GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_SEA_BEGIN;
            }
        }
        final Province province2 = province;
        province2.aiPeaceScore += (int)(GameValues.peace.AI_PEACE_DEMAND_PROVINCE_DISTANCE * Math.max(0.0f, 1.0f - Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), provinceID)));
    }
    
    public static int buildProvinceScore_Player(final List<Integer> aiPeaceCivID, final int civID, final int provinceID) {
        int out = 0;
        int neighboringPerc = 0;
        float neighboringPerc_Total = 0.0f;
        for (int i = 0; i < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++i) {
            if (Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getWasteland() < 0) {
                if (aiPeaceCivID.get(Game.getProvince(provinceID).getNeighboringProvinces(i)) == civID || aiPeaceCivID.get(Game.getProvince(provinceID).getNeighboringProvinces(i)) == Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getCivID()) {
                    ++neighboringPerc_Total;
                }
                if (aiPeaceCivID.get(Game.getProvince(provinceID).getNeighboringProvinces(i)) == civID) {
                    out += (int)GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_WINNER_SCORE;
                    ++neighboringPerc;
                }
            }
        }
        if (neighboringPerc_Total > 0.0f) {
            out += (int)(GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_WINNER_PERC * (neighboringPerc / neighboringPerc_Total));
        }
        if (Game.getProvince(provinceID).accessToMainSea) {
            out += (int)GameValues.peace.AI_PEACE_DEMAND_PROVINCE_NEIGHBORING_SEA;
        }
        return out += (int)(GameValues.peace.AI_PEACE_DEMAND_PROVINCE_DISTANCE * Math.max(0.0f, 1.0f - Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), provinceID)));
    }
}
