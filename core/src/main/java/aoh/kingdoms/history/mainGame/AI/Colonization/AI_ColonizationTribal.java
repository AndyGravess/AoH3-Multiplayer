// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Colonization;

import java.util.List;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_ColonizationTribal
{
    public static boolean colonize(final int civID) {
        if (Game.getCiv(civID).fGold > Game.aiValues.COLONIZE_MIN_GOLD && Game.getCiv(civID).fManpower > Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * Game.aiValues.COLONIZE_MIN_MANPOWER_X_REGIMENTS_TRIBAL) {
            final List<Integer> neighProvinces = new ArrayList<Integer>();
            for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                    if (Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() == 0 && Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getWasteland() < 0 && !neighProvinces.contains(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j))) {
                        neighProvinces.add(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j));
                        Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).aiInvestScore = Game.aiValues.COLONIZE_SCORE_PER_NEIGH_PROVINCE + Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getGrowthRateWithBonuses();
                    }
                }
            }
            if (neighProvinces.isEmpty() || Game.oR.nextInt(100) < Game.aiValues.COLONIZE_OVER_SEA_CHANCE) {
                for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                    for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
                        for (int k = 0; k < Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvincesSize(); ++k) {
                            final int pID = Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k);
                            if (!Game.getProvince(pID).getSeaProvince() && Game.getProvince(pID).getCivID() == 0 && Game.getProvince(pID).getWasteland() < 0) {
                                neighProvinces.add(pID);
                                Game.getProvince(pID).aiInvestScore = Game.aiValues.COLONIZE_SCORE_PER_NEIGH_PROVINCE + Game.getProvince(pID).getGrowthRateWithBonuses();
                            }
                        }
                    }
                }
            }
            if (!neighProvinces.isEmpty()) {
                for (int a = 0; a < Game.aiValues.COLONIZE_PROVINCES_LIMIT_TRIBAL && neighProvinces.size() > 0; ++a) {
                    int bestID = 0;
                    for (int l = neighProvinces.size() - 1; l > 0; --l) {
                        if (Game.getProvince(neighProvinces.get(bestID)).aiInvestScore < Game.getProvince(neighProvinces.get(l)).aiInvestScore) {
                            bestID = l;
                        }
                    }
                    if (!AI_Colonization.aiColonize(civID, neighProvinces.get(bestID))) {
                        neighProvinces.clear();
                        return true;
                    }
                    neighProvinces.remove(bestID);
                }
                neighProvinces.clear();
                return true;
            }
            neighProvinces.clear();
        }
        return true;
    }
    
    public static boolean canColonize(final int civID) {
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                if (Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() == 0 && Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getWasteland() < 0) {
                    return true;
                }
            }
        }
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
                for (int k = 0; k < Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvincesSize(); ++k) {
                    final int pID = Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k);
                    if (!Game.getProvince(pID).getSeaProvince() && Game.getProvince(pID).getCivID() == 0 && Game.getProvince(pID).getWasteland() < 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
