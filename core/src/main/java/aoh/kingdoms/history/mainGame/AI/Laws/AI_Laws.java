// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Laws;

import aoc.kingdoms.lukasz.map.LawsManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.AI.Government.AI_Capital;

public class AI_Laws
{
    public static final void adoptNewLaws(final int civID) {
        AI_Capital.checkCapital(civID);
        if (Game.getCiv(civID).fLegacy < GameValues.laws.LAW_ADAPT_REFORM_COST_LEGACY_POINTS) {
            return;
        }
        if (Game.getCiv(civID).fGold < GameValues.laws.LAW_ADAPT_REFORM_COST_GOLD) {
            return;
        }
        for (int i = 0; i < LawsManager.iLawsSize; ++i) {
            if (Game.getCiv(civID).laws.get(i) + 1 < LawsManager.laws.get(i).RequiredTechID.length && !adoptNewLaw(civID, i)) {
                if (Game.getCiv(civID).fLegacy < GameValues.laws.LAW_ADAPT_REFORM_COST_LEGACY_POINTS) {
                    return;
                }
                if (Game.getCiv(civID).fGold < GameValues.laws.LAW_ADAPT_REFORM_COST_GOLD) {
                    return;
                }
            }
        }
    }
    
    public static boolean adoptNewLaw(final int civID, final int lawID) {
        for (int j = LawsManager.laws.get(lawID).RequiredTechID.length - 1; j > Game.getCiv(civID).laws.get(lawID); --j) {
            if (Game.getCiv(civID).getTechResearched(LawsManager.laws.get(lawID).RequiredTechID[j]) && (LawsManager.laws.get(lawID).RequiredGovernmentID == null || LawsManager.laws.get(lawID).RequiredGovernmentID[j] < 0 || LawsManager.laws.get(lawID).RequiredGovernmentID[j] == Game.getCiv(civID).getIdeologyID())) {
                return LawsManager.adoptReform(civID, lawID, j);
            }
        }
        return true;
    }
}
