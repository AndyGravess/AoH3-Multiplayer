// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Colonization;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.ColonizationManager;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.map.Continents;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.List;

public class AI_Colonization
{
    public static List<Integer> provinces;
    public static List<Integer> provincesAll;
    
    public static void initData() {
        AI_Colonization.provinces.clear();
        AI_Colonization.provincesAll.clear();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() == 0 && Game.getProvince(i).getWasteland() < 0 && !Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).accessToMainSea) {
                    AI_Colonization.provinces.add(i);
                }
                AI_Colonization.provincesAll.add(i);
            }
        }
    }
    
    public static void removeProvinceID(final int id) {
        for (int i = AI_Colonization.provinces.size() - 1; i >= 0; --i) {
            if (AI_Colonization.provinces.get(i) == id) {
                AI_Colonization.provinces.remove(i);
                return;
            }
        }
    }
    
    public static void removeProvinceID_All(final int id) {
        for (int i = AI_Colonization.provincesAll.size() - 1; i >= 0; --i) {
            if (AI_Colonization.provincesAll.get(i) == id) {
                AI_Colonization.provincesAll.remove(i);
                return;
            }
        }
    }
    
    public static void updateColonize() {
        try {
            if (!AI_Colonization.provincesAll.isEmpty()) {
                int i;
                for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_COLONIZE; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_COLONIZE) {
                    if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).canColonize && Game.getCiv(i).iCivRankPosition < Game.aiValues.COLONIZE_TOP_CIVS && !Game.getCiv(i).diplomacy.isAtWar() && Game.getCiv(i).fGold > Game.aiValues.COLONIZE_MIN_GOLD && Game.getCiv(i).fManpower > Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * Game.aiValues.COLONIZE_MIN_MANPOWER_X_REGIMENTS) {
                        colonize(i);
                    }
                }
                if (i == Game.player.iCivID) {
                    i += GameValues.gameUpdateAI.GAME_UPDATE_AI_COLONIZE;
                }
                while (i < Game.getCivsSize()) {
                    if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).canColonize && Game.getCiv(i).iCivRankPosition < Game.aiValues.COLONIZE_TOP_CIVS && !Game.getCiv(i).diplomacy.isAtWar() && Game.getCiv(i).fGold > Game.aiValues.COLONIZE_MIN_GOLD && Game.getCiv(i).fManpower > Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * Game.aiValues.COLONIZE_MIN_MANPOWER_X_REGIMENTS) {
                        colonize(i);
                    }
                    i += GameValues.gameUpdateAI.GAME_UPDATE_AI_COLONIZE;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void colonize(final int civID) {
        final List<Integer> neighProvinces = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                if (Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() == 0 && Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getWasteland() < 0) {
                    final float modifier = Game.continents.lContinents.get(Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getContinent()).prioritizeColonization ? Game.aiValues.COLONIZE_SCORE_PRIORITIZE_CONTINENT_MODIFIER : 1.0f;
                    if (!neighProvinces.contains(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j))) {
                        neighProvinces.add(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j));
                        Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).aiInvestScore = (Game.aiValues.COLONIZE_SCORE_PER_NEIGH_PROVINCE + Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).getGrowthRateWithBonuses()) * modifier;
                    }
                    else {
                        final Province province = Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j));
                        province.aiInvestScore += Game.aiValues.COLONIZE_SCORE_PER_NEIGH_PROVINCE * modifier;
                    }
                }
            }
        }
        if (neighProvinces.isEmpty()) {
            for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                for (int provinceID = Game.getCiv(civID).getProvinceID(i), k = 0; k < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++k) {
                    final int neighborProvinceID = Game.getProvince(provinceID).getNeighboringProvinces(k);
                    final float modifier2 = Game.continents.lContinents.get(Game.getProvince(neighborProvinceID).getContinent()).prioritizeColonization ? Game.aiValues.COLONIZE_SCORE_PRIORITIZE_CONTINENT_MODIFIER : 1.0f;
                    if (Game.getProvince(neighborProvinceID).getCivID() == 0 && Game.getProvince(neighborProvinceID).getWasteland() < 0) {
                        if (!neighProvinces.contains(neighborProvinceID)) {
                            neighProvinces.add(neighborProvinceID);
                            Game.getProvince(neighborProvinceID).aiInvestScore = (Game.aiValues.COLONIZE_SCORE_PER_NEIGH_PROVINCE + Game.getProvince(neighborProvinceID).getGrowthRateWithBonuses()) * modifier2;
                        }
                        else {
                            final Province province2 = Game.getProvince(neighborProvinceID);
                            province2.aiInvestScore += Game.aiValues.COLONIZE_SCORE_PER_NEIGH_PROVINCE * modifier2;
                        }
                    }
                }
            }
        }
        if (!neighProvinces.isEmpty()) {
            for (int a = 0; a < Game.aiValues.COLONIZE_PROVINCES_LIMIT && neighProvinces.size() > 0; ++a) {
                int bestID = 0;
                for (int l = neighProvinces.size() - 1; l > 0; --l) {
                    if (Game.getProvince(neighProvinces.get(bestID)).aiInvestScore < Game.getProvince(neighProvinces.get(l)).aiInvestScore) {
                        bestID = l;
                    }
                }
                if (!aiColonize(civID, neighProvinces.get(bestID))) {
                    neighProvinces.clear();
                    return;
                }
                neighProvinces.remove(bestID);
            }
            neighProvinces.clear();
            return;
        }
        if (Game.getCiv(civID).haveAccessSea && !AI_Colonization.provinces.isEmpty()) {
            for (int i = AI_Colonization.provinces.size() - 1; i > 0; --i) {
                Game.getProvince(AI_Colonization.provinces.get(i)).aiInvestScore *= Math.max(0.05f, 1.0f - Game.getDistanceFromProvinceToProvince(Game.getCiv(civID).getCapitalProvinceID(), AI_Colonization.provinces.get(i)));
            }
            for (int a = 0; a < Game.aiValues.COLONIZE_PROVINCES_LIMIT_SEA && AI_Colonization.provinces.size() > 0; ++a) {
                int bestID = 0;
                for (int l = AI_Colonization.provinces.size() - 1; l > 0; --l) {
                    if (Game.getProvince(AI_Colonization.provinces.get(bestID)).aiInvestScore < Game.getProvince(AI_Colonization.provinces.get(l)).aiInvestScore) {
                        bestID = l;
                    }
                }
                if (!aiColonize(civID, AI_Colonization.provinces.get(bestID))) {
                    return;
                }
            }
        }
    }
    
    public static boolean aiColonize(final int civID, final int provinceID) {
        if (Game.getCiv(civID).fManpower < Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) {
            return false;
        }
        if (Game.getProvince(provinceID).getCivID() == 0 && Game.getProvince(provinceID).getWasteland() < 0 && !Game.getProvince(provinceID).getSeaProvince()) {
            final int pop = (int)Math.max(Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE, Math.min(Game.getCiv(civID).fManpower, Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * (1 + Game.oR.nextInt(Game.aiValues.COLONIZE_X_REGIMENTS_RANDOM))));
            if (ColonizationManager.establishSettlement(civID, provinceID, pop)) {
                final Civilization civ = Game.getCiv(civID);
                civ.fGold -= pop / 100;
                Game.getCiv(civID).setManpower(Game.getCiv(civID).fManpower - pop);
                removeProvinceID(provinceID);
                removeProvinceID_All(provinceID);
                return Game.getCiv(civID).fManpower >= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
            }
        }
        return false;
    }
    
    static {
        AI_Colonization.provinces = new ArrayList<Integer>();
        AI_Colonization.provincesAll = new ArrayList<Integer>();
    }
}
