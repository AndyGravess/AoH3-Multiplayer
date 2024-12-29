// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_RelationsImprove
{
    public static void updateRelations_Improve() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateRelations_Improve(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateRelations_Improve(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void updateRelations_Improve(final int civID) {
        final Civilization civ = Game.getCiv(civID);
        if (civ.diplomacy.iImprovingRelationsSize > 0) {
            if (Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_CLEAR_IMPROVE_RELATIONS_CHANCE) {
                civ.diplomacy.clearImproveRelations_AI(civID);
            }
            if (civ.diplomacy.iImprovingRelationsSize >= Game.aiValuesDiplomacy.AI_LIMIT_IMPROVING_RELATIONS) {
                return;
            }
        }
        for (int i = 0; i < civ.diplomacy.iVassalsSize; ++i) {
            if (civ.diplomacy.getRelation(civ.diplomacy.lVassals.get(i).c) < Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_WITH_VASSAL_IF_RELATIONS_BELOW) {
                addImproveRelations(civID, civ.diplomacy.lVassals.get(i).c);
            }
        }
        final List<Integer> civs = new ArrayList<Integer>();
        if (Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_NEIGHBORS_OF_NEIGHBORS) {
            for (int j = 0; j < civ.civNeighbors.civsSize; ++j) {
                final Civilization civJ = Game.getCiv(civ.civNeighbors.civs.get(j).civID);
                for (int k = 0; k < civJ.civNeighbors.civsSize; ++k) {
                    if (Game.getCiv(civJ.civNeighbors.civs.get(k).civID).getNumOfProvinces() > 0 && !civs.contains(civJ.civNeighbors.civs.get(k)) && civJ.civNeighbors.civs.get(k).civID != civID && canBeAdded(civID, civJ.civNeighbors.civs.get(k).civID)) {
                        civs.add(civJ.civNeighbors.civs.get(k).civID);
                    }
                }
            }
            if (!civs.isEmpty()) {
                for (int a = Math.min(civs.size(), Game.aiValuesDiplomacy.AI_LIMIT_IMPROVING_RELATIONS - civ.diplomacy.iImprovingRelationsSize) - 1; a >= 0; --a) {
                    final int rand = Game.oR.nextInt(civs.size());
                    addImproveRelations(civID, civs.get(rand));
                    civs.remove(rand);
                }
                return;
            }
        }
        int type = Game.oR.nextInt(3);
        final float randDistanceScore = Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_DISTANCE_SCORE + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_DISTANCE_SCORE_RANDOM) / 100.0f;
        final float randSecondScore = Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_SECOND_SCORE + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_SECOND_SCORE_RANDOM) / 100.0f;
        switch (type) {
            case 0: {
                for (int l = 1; l < civID; ++l) {
                    buildScore(civID, l, randDistanceScore, randSecondScore);
                }
                for (int l = civID + 1; l < Game.getCivsSize(); ++l) {
                    buildScore(civID, l, randDistanceScore, randSecondScore);
                }
                break;
            }
            case 1: {
                for (int l = 1; l < civID; ++l) {
                    buildScoreManpower(civID, l, randDistanceScore, randSecondScore);
                }
                for (int l = civID + 1; l < Game.getCivsSize(); ++l) {
                    buildScoreManpower(civID, l, randDistanceScore, randSecondScore);
                }
                break;
            }
            case 2: {
                for (int l = 1; l < civID; ++l) {
                    buildScoreNumOfProvinces(civID, l, randDistanceScore, randSecondScore);
                }
                for (int l = civID + 1; l < Game.getCivsSize(); ++l) {
                    buildScoreNumOfProvinces(civID, l, randDistanceScore, randSecondScore);
                }
                break;
            }
        }
        civ.aiScore = 9.999877E7f;
        for (int a2 = (Game.aiValuesDiplomacy.AI_LIMIT_IMPROVING_RELATIONS - civ.diplomacy.iImprovingRelationsSize) * 2 - 1; a2 >= 0; --a2) {
            int bestID = 1;
            for (int m = 2; m < Game.getCivsSize(); ++m) {
                if (Game.getCiv(bestID).aiScore > Game.getCiv(m).aiScore && !civ.diplomacy.isRival(m) && !civ.diplomacy.isDamagingRelations(m) && !civ.diplomacy.isImprovingRelations(m) && civ.diplomacy.getRelation(m) < GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX) {
                    bestID = m;
                }
            }
            if (bestID == civID) {
                break;
            }
            if (civ.diplomacy.isRival(bestID) || civ.diplomacy.isDamagingRelations(bestID) || civ.diplomacy.isImprovingRelations(bestID)) {
                if (Game.getCiv(bestID).aiScore == 9.999832E7f) {
                    break;
                }
                Game.getCiv(bestID).aiScore = 9.999832E7f;
            }
            else {
                if (Game.getCiv(bestID).getNumOfProvinces() <= 0) {
                    break;
                }
                civs.add(bestID);
                Game.getCiv(bestID).aiScore = 9.999877E7f;
            }
        }
        if (Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_BEST_CIVS_RANDOMLY) {
            for (int a2 = Math.min(civs.size(), Game.aiValuesDiplomacy.AI_LIMIT_IMPROVING_RELATIONS - civ.diplomacy.iImprovingRelationsSize) - 1; a2 >= 0; --a2) {
                type = Game.oR.nextInt(civs.size());
                addImproveRelations(civID, civs.get(type));
                civs.remove(type);
            }
        }
        else {
            for (int a2 = 0; a2 < Math.min(civs.size(), Game.aiValuesDiplomacy.AI_LIMIT_IMPROVING_RELATIONS - civ.diplomacy.iImprovingRelationsSize); ++a2) {
                addImproveRelations(civID, civs.get(a2));
            }
        }
        civs.clear();
    }
    
    public static final void addImproveRelations(final int civID, final int withCivID) {
        Game.getCiv(civID).diplomacy.addImproveRelations(civID, withCivID);
        if (Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_SEND_GIFT_CHANCE) {
            DiplomacyManager.giftGold(civID, withCivID, Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_SEND_GIFT_CLICKS + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_SEND_GIFT_CLICKS_RANDOM));
        }
    }
    
    public static void buildScore(final int civID, final int civB, final float randDistanceScore, final float randSecondScore) {
        if (Game.getCiv(civB).getNumOfProvinces() > 0) {
            Game.getCiv(civB).aiScore = randDistanceScore * Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(civB).getCapitalProvinceID()) + (randSecondScore + (differentReligion(civID, civB) ? Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_DISTANCE_SCORE_EXTRA_DIFFERENT_RELIGION : 0.0f)) * (1.0f - Math.min(Game.getCiv(civID).fTotalIncomePerMonth, Game.getCiv(civB).fTotalIncomePerMonth) / Math.max(Game.getCiv(civID).fTotalIncomePerMonth, Game.getCiv(civB).fTotalIncomePerMonth));
        }
        else {
            Game.getCiv(civB).aiScore = 999876.0f;
        }
    }
    
    public static void buildScoreNumOfProvinces(final int civID, final int civB, final float randDistanceScore, final float randSecondScore) {
        if (Game.getCiv(civB).getNumOfProvinces() > 0) {
            Game.getCiv(civB).aiScore = randDistanceScore * Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(civB).getCapitalProvinceID()) + (randSecondScore + (differentReligion(civID, civB) ? Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_DISTANCE_SCORE_EXTRA_DIFFERENT_RELIGION : 0.0f)) * (1.0f - Math.min(Game.getCiv(civID).getNumOfProvinces(), Game.getCiv(civB).getNumOfProvinces()) / (float)Math.max(Game.getCiv(civID).getNumOfProvinces(), Game.getCiv(civB).getNumOfProvinces()));
        }
        else {
            Game.getCiv(civB).aiScore = 999876.0f;
        }
    }
    
    public static void buildScoreManpower(final int civID, final int civB, final float randDistanceScore, final float randSecondScore) {
        if (Game.getCiv(civB).getNumOfProvinces() > 0) {
            Game.getCiv(civB).aiScore = (float)(randDistanceScore * Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(civB).getCapitalProvinceID()) + (randSecondScore + (differentReligion(civID, civB) ? Game.aiValuesDiplomacy.AI_IMPROVE_RELATIONS_DISTANCE_SCORE_EXTRA_DIFFERENT_RELIGION : 0.0f)) * (1.0 - Math.min(Game.getCiv(civID).fManpowerMax, Game.getCiv(civB).fManpowerMax) / (float)Math.max(Game.getCiv(civID).fManpowerMax, Game.getCiv(civB).fManpowerMax)));
        }
        else {
            Game.getCiv(civB).aiScore = 999876.0f;
        }
    }
    
    public static boolean canBeAdded(final int civID, final int civB) {
        return !Game.getCiv(civID).diplomacy.isRival(civB) && !Game.getCiv(civID).diplomacy.isDamagingRelations(civB) && !Game.getCiv(civID).diplomacy.isImprovingRelations(civB) && !DiplomacyManager.isAtWar(civID, civB) && Game.getCiv(civID).diplomacy.getRelation(civB) < GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX;
    }
    
    public static boolean differentGovernment(final int civA, final int civB) {
        return Game.getCiv(civA).getIdeologyID() != Game.getCiv(civB).getIdeologyID();
    }
    
    public static boolean differentReligion(final int civA, final int civB) {
        return Game.getCiv(civA).getReligionID() != Game.getCiv(civB).getReligionID();
    }
}
