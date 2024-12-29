// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.GeneralManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Loan;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Generals
{
    public static void recruitGenerals(final int civID) {
        final Civilization civ = Game.getCiv(civID);
        if (civ.armiesWithoutGenerals.keysSize > 0) {
            try {
                if (Game.getCiv(civID).getGeneralsNotAssignedSize() <= 0 && Game.getCiv(civID).diplomacy.isAtWar() && civ.fGold < GameValues.generals.RECRUIT_GENERAL_GOLD_COST && civ.fLegacy >= GameValues.generals.RECRUIT_GENERAL_LEGACY_COST) {
                    AI_Loan.takeLoan(civID);
                }
                if (civ.fGold >= GameValues.generals.RECRUIT_GENERAL_GOLD_COST && civ.fLegacy >= GameValues.generals.RECRUIT_GENERAL_LEGACY_COST) {
                    final List<Game.ArmyPos> armyPosList = new ArrayList<Game.ArmyPos>();
                    for (int i = 0; i < civ.armiesWithoutGenerals.keysSize; ++i) {
                        final Game.ArmyPos armyPos = Game.findArmy_FullCheck(civID, civ.armiesWithoutGenerals.keys.get(i));
                        if (armyPos == null) {
                            civ.armiesWithoutGenerals.removeArmyKey(civ.armiesWithoutGenerals.keys.get(i));
                            --i;
                        }
                        else if (Game.getProvince(armyPos.iProvinceID).getArmy(armyPos.iID).armyGeneral == null) {
                            armyPosList.add(armyPos);
                        }
                        else {
                            civ.armiesWithoutGenerals.removeArmyKey(civ.armiesWithoutGenerals.keys.get(i));
                            --i;
                        }
                    }
                    int limit = 0;
                    while (civ.fGold >= GameValues.generals.RECRUIT_GENERAL_GOLD_COST && !civ.armiesWithoutGenerals.keys.isEmpty() && civ.fLegacy >= GameValues.generals.RECRUIT_GENERAL_LEGACY_COST && armyPosList.size() > 0 && limit++ < 50) {
                        int bestID = 0;
                        for (int j = 1; j < armyPosList.size(); ++j) {
                            if (Game.getProvince(armyPosList.get(bestID).iProvinceID).getArmy(armyPosList.get(bestID).iID).iArmyRegimentSize < Game.getProvince(armyPosList.get(j).iProvinceID).getArmy(armyPosList.get(j).iID).iArmyRegimentSize) {
                                bestID = j;
                            }
                        }
                        if (Game.getCiv(civID).getGeneralsNotAssignedSize() > 0 && Game.getCiv(civID).assignGeneralToArmy(armyPosList.get(bestID).iProvinceID, armyPosList.get(bestID).iID)) {
                            continue;
                        }
                        if (!GeneralManager.recruitGeneral_AI(civID, armyPosList.get(bestID).iProvinceID, armyPosList.get(bestID).iID)) {
                            return;
                        }
                        armyPosList.remove(bestID);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
}
