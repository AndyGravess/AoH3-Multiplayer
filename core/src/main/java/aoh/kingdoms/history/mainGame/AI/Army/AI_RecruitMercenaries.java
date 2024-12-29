// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.MercenariesManager;
import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_RecruitMercenaries
{
    public static void recruitMercenaries(final int civID) {
        if (Game.getCiv(civID).fGold < ArmyManager.averageArmyCost * (GameValues.army.MERCENARIES_COST_EXTRA_PER_REGIMENT + ((Game.getCiv(civID).iRegiments > Game.getCiv(civID).iRegimentsLimit) ? GameValues.army.REGIMENTS_LIMIT_RECRUIT_COST_OVER : 0.0f)) * Game.aiValuesArmies.RECRUIT_MERCENARIES_MIN_REGIMENTS) {
            return;
        }
        int enemyArmy = 0;
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            final Province province = Game.getProvince(Game.getCiv(civID).getProvinceID(i));
            for (int j = 0; j < province.getArmySize(); ++j) {
                if (DiplomacyManager.isAtWar(civID, province.getArmy(j).civID)) {
                    enemyArmy += province.getArmy(j).iArmyRegimentSize;
                }
            }
        }
        enemyArmy *= (int)Game.aiValuesArmies.RECRUIT_MERCENARIES_ENEMY_ARMIES_MODIFIER;
        if (enemyArmy > Game.getCiv(civID).iRegiments * Game.aiValuesArmies.RECRUIT_MERCENARIES_OWN_ARMIES_MODIFIER) {
            enemyArmy -= (int)(Game.getCiv(civID).iRegiments * Game.aiValuesArmies.RECRUIT_MERCENARIES_OWN_ARMIES_MODIFIER);
        }
        recruitMercenaries(civID, Math.max(enemyArmy, 0));
    }
    
    public static void recruitMercenaries(final int civID, int limit) {
        if (limit < 1) {
            return;
        }
        final Civilization civ = Game.getCiv(civID);
        if (civ.fGold > ArmyManager.averageArmyCost * (GameValues.army.MERCENARIES_COST_EXTRA_PER_REGIMENT + ((civ.iRegiments > civ.iRegimentsLimit) ? GameValues.army.REGIMENTS_LIMIT_RECRUIT_COST_OVER : 0.0f)) * Game.aiValuesArmies.RECRUIT_MERCENARIES_MIN_REGIMENTS) {
            final List<Integer> possibleProvinces = new ArrayList<Integer>();
            for (int a = 0; a < civ.getNumOfProvinces(); ++a) {
                if (!Game.getProvince(civ.getProvinceID(a)).isOccupied()) {
                    possibleProvinces.add(civ.getProvinceID(a));
                }
            }
            if (!possibleProvinces.isEmpty()) {
                for (int i = 0; i < Game.aiValuesArmies.RECRUIT_MERCENARIES_ACTION_LIMIT && !possibleProvinces.isEmpty(); ++i) {
                    final int bestProvinceID = Game.oR.nextInt(possibleProvinces.size());
                    final int recruited = recruitMercenariesArmy(civID, possibleProvinces.get(bestProvinceID));
                    if (recruited == 0) {
                        return;
                    }
                    limit -= recruited;
                    if (limit >= 0) {
                        return;
                    }
                    if (possibleProvinces.size() > Game.aiValuesArmies.RECRUIT_MERCENARIES_ACTION_LIMIT) {
                        possibleProvinces.remove(bestProvinceID);
                    }
                }
            }
        }
    }
    
    private static int recruitMercenariesArmy(final int civID, final int provinceID) {
        final List<MercenariesManager.MercenaryArmy> mercenaryArmies = MercenariesManager.getMercenaryArmies(civID);
        for (int i = mercenaryArmies.size() - 1; i >= 0; --i) {
            if (Game.getCiv(civID).fGold > mercenaryArmies.get(i).iCost) {
                MercenariesManager.recruitMercenaries(civID, provinceID, mercenaryArmies.get(i));
                return mercenaryArmies.get(i).iUnitID.size();
            }
        }
        return 0;
    }
}
