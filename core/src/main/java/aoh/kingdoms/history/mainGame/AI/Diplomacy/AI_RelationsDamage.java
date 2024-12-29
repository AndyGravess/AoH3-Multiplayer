// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import java.util.List;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_RelationsDamage
{
    public static void updateRelations_Damage() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS_DAMAGE; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS_DAMAGE) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateRelations_Damage(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS_DAMAGE;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateRelations_Damage(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RELATIONS_DAMAGE;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void updateRelations_Damage(final int civID) {
        if (Game.getCiv(civID).diplomacy.iImprovingRelationsSize > 0 && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_CLEAR_DECREASE_RELATIONS_CHANCE) {
            Game.getCiv(civID).diplomacy.clearDamageRelations_AI(civID);
        }
        if (Game.aiValuesDiplomacy.AI_DAMAGE_RELATIONS_PRIORITIZE_TRIBAL && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_DAMAGE_RELATIONS_PRIORITIZE_TRIBAL_CHANCE && !Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).TRIBAL) {
            boolean added = false;
            for (int i = 0; i < Game.getCiv(civID).civNeighbors.civsSize; ++i) {
                if (Game.ideologiesManager.getIdeology(Game.getCiv(Game.getCiv(civID).civNeighbors.civs.get(i).civID).getIdeologyID()).TRIBAL && Game.getCiv(Game.getCiv(civID).civNeighbors.civs.get(i).civID).getNumOfProvinces() > 0 && !DiplomacyManager.isAlly(civID, Game.getCiv(civID).civNeighbors.civs.get(i).civID) && Game.getCiv(civID).getResearchedTechnologies() > Game.getCiv(Game.getCiv(civID).civNeighbors.civs.get(i).civID).getResearchedTechnologies() && Game.getCiv(civID).getNumOfProvinces() > Game.getCiv(Game.getCiv(civID).civNeighbors.civs.get(i).civID).getNumOfProvinces() && Game.getCiv(civID).diplomacy.addDamageRelations(civID, Game.getCiv(civID).civNeighbors.civs.get(i).civID)) {
                    added = true;
                }
            }
            if (added) {
                return;
            }
        }
        if (Game.getCiv(civID).diplomacy.iDamagingRelationsSize < Game.aiValuesDiplomacy.AI_LIMIT_DECREASING_RELATIONS) {
            final int type = Game.oR.nextInt(3);
            switch (type) {
                case 0: {
                    final float value = Game.getCiv(civID).fTotalIncomePerMonth * Game.aiValuesDiplomacy.AI_DAMAGE_RELATIONS_INCOME_MODIFIER;
                    for (int j = 1; j < civID; ++j) {
                        buildScore(civID, j, value);
                    }
                    for (int j = civID + 1; j < Game.getCivsSize(); ++j) {
                        buildScore(civID, j, value);
                    }
                    break;
                }
                case 1: {
                    final double value2 = Game.getCiv(civID).fManpowerMax * Game.aiValuesDiplomacy.AI_DAMAGE_RELATIONS_MANPOWER_MODIFIER;
                    for (int k = 1; k < civID; ++k) {
                        buildScoreManpower(civID, k, value2);
                    }
                    for (int k = civID + 1; k < Game.getCivsSize(); ++k) {
                        buildScoreManpower(civID, k, value2);
                    }
                    break;
                }
                case 2: {
                    final float value = Game.getCiv(civID).iRegiments * Game.aiValuesDiplomacy.AI_DAMAGE_RELATIONS_REGIMENTS_MODIFIER;
                    for (int j = 1; j < civID; ++j) {
                        buildScoreRegiments(civID, j, value);
                    }
                    for (int j = civID + 1; j < Game.getCivsSize(); ++j) {
                        buildScoreRegiments(civID, j, value);
                    }
                    break;
                }
            }
            final List<Integer> civs = new ArrayList<Integer>();
            Game.getCiv(civID).aiScore = 9.999877E7f;
            for (int j = 0; j < Game.getCiv(civID).diplomacy.iVassalsSize; ++j) {
                Game.getCiv(Game.getCiv(civID).diplomacy.lVassals.get(j).c).aiScore = 9.999997E7f;
            }
            for (int a = Game.aiValuesDiplomacy.AI_DAMAGE_RELATIONS_NUM_TO_CHOOSE_FROM - 1; a >= 0; --a) {
                int bestID = 1;
                for (int l = 2; l < Game.getCivsSize(); ++l) {
                    if (Game.getCiv(bestID).aiScore > Game.getCiv(l).aiScore && !Game.getCiv(civID).diplomacy.isDamagingRelations(l) && !Game.getCiv(civID).diplomacy.isImprovingRelations(l) && Game.getCiv(civID).diplomacy.getRelation(l) > GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX) {
                        bestID = l;
                    }
                }
                if (bestID == civID) {
                    break;
                }
                if (Game.getCiv(civID).diplomacy.isDamagingRelations(bestID) || Game.getCiv(civID).diplomacy.isImprovingRelations(bestID) || Game.getCiv(civID).diplomacy.getRelation(bestID) < GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX) {
                    if (Game.getCiv(bestID).aiScore == 9.999832E7f) {
                        break;
                    }
                    Game.getCiv(bestID).aiScore = 9.999832E7f;
                }
                else {
                    if (Game.getCiv(bestID).getNumOfProvinces() <= 0 || Game.getCiv(bestID).aiScore >= 999800.0f) {
                        break;
                    }
                    civs.add(bestID);
                    Game.getCiv(bestID).aiScore = 9.999877E7f;
                }
            }
            for (int a = Math.min(civs.size(), Game.aiValuesDiplomacy.AI_LIMIT_DECREASING_RELATIONS - Game.getCiv(civID).diplomacy.iDamagingRelationsSize) - 1; a >= 0; --a) {
                final int rand = Game.oR.nextInt(civs.size());
                Game.getCiv(civID).diplomacy.addDamageRelations(civID, civs.get(rand));
                civs.remove(rand);
            }
            civs.clear();
        }
    }
    
    public static void buildScore(final int civID, final int civB, final float value) {
        if (Game.getCiv(civB).getNumOfProvinces() > 0) {
            if (Game.getCiv(civB).fTotalIncomePerMonth < value) {
                Game.getCiv(civB).aiScore = Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(civB).getCapitalProvinceID());
            }
            else {
                Game.getCiv(civB).aiScore = 999876.0f;
            }
        }
        else {
            Game.getCiv(civB).aiScore = 999876.0f;
        }
    }
    
    public static void buildScoreManpower(final int civID, final int civB, final double value) {
        if (Game.getCiv(civB).getNumOfProvinces() > 0) {
            if (Game.getCiv(civB).fManpowerMax < value) {
                Game.getCiv(civB).aiScore = Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(civB).getCapitalProvinceID());
            }
            else {
                Game.getCiv(civB).aiScore = 999876.0f;
            }
        }
        else {
            Game.getCiv(civB).aiScore = 999876.0f;
        }
    }
    
    public static void buildScoreRegiments(final int civID, final int civB, final float value) {
        if (Game.getCiv(civB).getNumOfProvinces() > 0) {
            if (Game.getCiv(civB).iRegiments < value) {
                Game.getCiv(civB).aiScore = Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(civB).getCapitalProvinceID());
            }
            else {
                Game.getCiv(civB).aiScore = 999876.0f;
            }
        }
        else {
            Game.getCiv(civB).aiScore = 999876.0f;
        }
    }
    
    public static boolean differentGovernment(final int civA, final int civB) {
        return Game.getCiv(civA).getIdeologyID() != Game.getCiv(civB).getIdeologyID();
    }
    
    public static boolean differentReligion(final int civA, final int civB) {
        return Game.getCiv(civA).getReligionID() != Game.getCiv(civB).getReligionID();
    }
}
