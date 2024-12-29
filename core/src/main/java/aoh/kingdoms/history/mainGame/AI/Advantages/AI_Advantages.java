// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Advantages;

import java.util.List;
import aoc.kingdoms.lukasz.map.AdvantagesManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Advantages
{
    public static final void takeAdvantages(final int civID) {
        if (Game.getCiv(civID).getAdvantagePoints() > 0) {
            for (int i = Game.getCiv(civID).getAdvantagePoints() - 1; i >= 0; --i) {
                if (!takeAdvantage(civID)) {
                    return;
                }
            }
        }
    }
    
    public static final boolean takeAdvantage(final int civID) {
        final List<Integer> possibleToUnlock = new ArrayList<Integer>();
        int totalScore = 0;
        for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
            if (Game.getCiv(civID).getTechResearched(AdvantagesManager.advantages.get(i).RequiredTechID) && !Game.getCiv(civID).haveAdvantageMaxLvl(i)) {
                possibleToUnlock.add(i);
                totalScore += AdvantagesManager.advantages.get(i).AI;
            }
        }
        if (possibleToUnlock.size() == 0) {
            return false;
        }
        if (totalScore <= 0) {
            AdvantagesManager.unlockAdvantage(civID, possibleToUnlock.get(Game.oR.nextInt(possibleToUnlock.size())));
        }
        else {
            final int select = Game.oR.nextInt(totalScore);
            int j = 0;
            int cScore = 0;
            for (int iSize = possibleToUnlock.size(); j < iSize; ++j) {
                if (select < cScore + AdvantagesManager.advantages.get(possibleToUnlock.get(j)).AI) {
                    AdvantagesManager.unlockAdvantage(civID, possibleToUnlock.get(j));
                    return true;
                }
                cScore += AdvantagesManager.advantages.get(possibleToUnlock.get(j)).AI;
            }
            AdvantagesManager.unlockAdvantage(civID, possibleToUnlock.get(0));
        }
        return true;
    }
    
    public static final int takeAdvantage_Player(final int civID) {
        final List<Integer> possibleToUnlock = new ArrayList<Integer>();
        int totalScore = 0;
        for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
            if (Game.getCiv(civID).getTechResearched(AdvantagesManager.advantages.get(i).RequiredTechID) && !Game.getCiv(civID).haveAdvantageMaxLvl(i)) {
                possibleToUnlock.add(i);
                totalScore += AdvantagesManager.advantages.get(i).AI;
            }
        }
        if (possibleToUnlock.size() == 0) {
            return -1;
        }
        if (totalScore <= 0) {
            AdvantagesManager.unlockAdvantage(civID, possibleToUnlock.get(Game.oR.nextInt(possibleToUnlock.size())));
            return -1;
        }
        final int select = Game.oR.nextInt(totalScore);
        int j = 0;
        int cScore = 0;
        for (int iSize = possibleToUnlock.size(); j < iSize; ++j) {
            if (select < cScore + AdvantagesManager.advantages.get(possibleToUnlock.get(j)).AI) {
                AdvantagesManager.unlockAdvantage(civID, possibleToUnlock.get(j));
                return possibleToUnlock.get(j);
            }
            cScore += AdvantagesManager.advantages.get(possibleToUnlock.get(j)).AI;
        }
        AdvantagesManager.unlockAdvantage(civID, possibleToUnlock.get(0));
        return possibleToUnlock.get(0);
    }
}
