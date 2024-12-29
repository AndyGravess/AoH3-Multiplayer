// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Technology;

import java.util.List;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import java.util.ArrayList;

public class AI_SelectTechnology
{
    public static void selectTechnology(final int civID) {
        final List<Integer> possibleResearch = new ArrayList<Integer>();
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            if (Game.getCiv(civID).getAvailableToResearch(i)) {
                possibleResearch.add(i);
            }
        }
        if (possibleResearch.size() == 0) {
            return;
        }
        if (possibleResearch.size() == 1) {
            Game.getCiv(civID).setActiveTechResearch(possibleResearch.get(0));
            Game.getCiv(civID).iAlternativeTechResearch = -1;
            return;
        }
        int totalScore = 0;
        for (int j = possibleResearch.size() - 1; j >= 0; --j) {
            totalScore += TechnologyTree.lTechnology.get(possibleResearch.get(j)).AI;
        }
        if (totalScore <= 0) {
            Game.getCiv(civID).setActiveTechResearch(possibleResearch.get(0));
            Game.getCiv(civID).iAlternativeTechResearch = -1;
            return;
        }
        final int select = Game.oR.nextInt(totalScore);
        int k = 0;
        int cScore = 0;
        while (k < possibleResearch.size()) {
            if (select < cScore + TechnologyTree.lTechnology.get(possibleResearch.get(k)).AI) {
                Game.getCiv(civID).setActiveTechResearch(possibleResearch.get(k));
                Game.getCiv(civID).iAlternativeTechResearch = -1;
                return;
            }
            cScore += TechnologyTree.lTechnology.get(possibleResearch.get(k)).AI;
            ++k;
        }
        Game.getCiv(civID).setActiveTechResearch(possibleResearch.get(0));
        Game.getCiv(civID).iAlternativeTechResearch = -1;
    }
}
