// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import java.util.ArrayList;
import java.util.List;
import aoc.kingdoms.lukasz.map.RivalsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Rivals
{
    public static void updateAI_Rivals() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_CHOOSE_RIVALS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CHOOSE_RIVALS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    chooseRivals(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CHOOSE_RIVALS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    chooseRivals(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CHOOSE_RIVALS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void chooseRivals(final int civID) {
        try {
            if (Game.getCiv(civID).diplomacy.rivals.size() < GameValues.rivals.RIVALS_LIMIT) {
                final List<Integer> rivals = RivalsManager.buildRivals(civID, GameValues.rivals.NUM_OF_RIVALS_TO_CHOOSE_FROM);
                final List<Float> score = chooseRivals_BuildScore(civID, rivals);
                int rivalsSize = rivals.size();
                for (int a = Game.getCiv(civID).diplomacy.rivals.size(); a < GameValues.rivals.RIVALS_LIMIT && rivals.size() > 0; ++a) {
                    int bestID = 0;
                    for (int i = 1; i < rivalsSize; ++i) {
                        if (score.get(bestID) > score.get(i)) {
                            bestID = i;
                        }
                    }
                    Game.getCiv(civID).diplomacy.addRival(civID, rivals.get(bestID));
                    rivals.remove(bestID);
                    score.remove(bestID);
                    rivalsSize = rivals.size();
                }
                rivals.clear();
                score.clear();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static List<Float> chooseRivals_BuildScore(final int civID, final List<Integer> rivals) {
        final List<Float> out = new ArrayList<Float>();
        for (int i = 0, iSize = rivals.size(); i < iSize; ++i) {
            if (DiplomacyManager.isAlly(civID, rivals.get(i))) {
                out.add(999333.0f + Game.getCiv(civID).diplomacy.getRelation(rivals.get(i)));
            }
            else if (Game.getCiv(civID).diplomacy.haveDefensivePact(rivals.get(i))) {
                out.add(899999.0f + Game.getCiv(civID).diplomacy.getRelation(rivals.get(i)));
            }
            else {
                final float tScore = Math.abs(Game.getCiv(civID).iCivRankScore - Game.getCiv(rivals.get(i)).iCivRankScore);
                out.add((tScore * GameValues.rivals.RIVALS_SCORE_MIN + tScore * GameValues.rivals.RIVALS_SCORE_DISTANCE * (Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(rivals.get(i)).getCapitalProvinceID()) / Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).RIVALS_DISTANCE)) * (Math.max(0.0f, 1.0f - GameValues.rivals.AI_RIVALS_SCORE_RELATION) + GameValues.rivals.AI_RIVALS_SCORE_RELATION * calculateScore(Game.getCiv(civID).diplomacy.getRelation(rivals.get(i)))));
            }
        }
        return out;
    }
    
    public static float calculateScore(final float value) {
        return (-0.5f * value + 50.0f) / 100.0f;
    }
}
