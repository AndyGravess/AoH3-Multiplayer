// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Legacies;

import java.util.List;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.LegacyManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Legacy
{
    public static void updateCiv_Legacies() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_UNLOCK_LEGACY; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_UNLOCK_LEGACY) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    unlockLegacy(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_UNLOCK_LEGACY;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    unlockLegacy(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_UNLOCK_LEGACY;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void unlockLegacy(final int civID) {
        try {
            if (Game.getCiv(civID).fLegacy > LegacyManager.minLegacyCost) {
                final List<Integer> toUnlock = new ArrayList<Integer>();
                final List<Integer> toUnlockLevel = new ArrayList<Integer>();
                int score = 0;
                for (int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
                    final int level = Game.getCiv(civID).getLegacyLevel(i);
                    if (level < 0) {
                        if (LegacyManager.legacies.get(i).CostLegacy[0] < Game.getCiv(civID).fLegacy) {
                            toUnlock.add(i);
                            score += LegacyManager.legacies.get(i).AI[0];
                            toUnlockLevel.add(0);
                        }
                    }
                    else if (level + 1 < LegacyManager.legacies.get(i).CostLegacy.length && LegacyManager.legacies.get(i).CostLegacy[level + 1] < Game.getCiv(civID).fLegacy) {
                        toUnlock.add(i);
                        score += LegacyManager.legacies.get(i).AI[level + 1];
                        toUnlockLevel.add(level + 1);
                    }
                }
                if (toUnlock.size() > 0) {
                    int rand = 0;
                    int bestID = 0;
                    for (int a = 0; a < Game.aiValues.UNLOCK_LEGACIES; ++a) {
                        if (score > 0 && toUnlock.size() > 0) {
                            rand = Game.oR.nextInt(score);
                            bestID = 0;
                            int j = 0;
                            final int iSize = toUnlock.size();
                            int currScore = 0;
                            while (j < iSize) {
                                currScore += LegacyManager.legacies.get(toUnlock.get(j)).AI[toUnlockLevel.get(j)];
                                if (rand < currScore) {
                                    bestID = j;
                                    break;
                                }
                                ++j;
                            }
                            Game.getCiv(civID).unlockLegacy(toUnlock.get(bestID));
                            score -= toUnlockLevel.get(bestID);
                            toUnlock.remove(bestID);
                            toUnlockLevel.remove(bestID);
                        }
                    }
                }
                toUnlock.clear();
                toUnlockLevel.clear();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
