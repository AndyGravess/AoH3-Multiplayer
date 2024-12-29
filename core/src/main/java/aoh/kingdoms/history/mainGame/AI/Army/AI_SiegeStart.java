// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.map.province.Province;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_SiegeStart
{
    public static void siegeStarted_Attacker(final int provinceID) {
        try {
            if (Game.getProvince(provinceID).getFortLevel() > 0) {
                return;
            }
            if (Game.battleManager.isBattleInProvince(provinceID)) {
                return;
            }
            List<Integer> civs = new ArrayList<Integer>();
            final Province province = Game.getProvince(provinceID);
            for (int i = 0; i < province.getArmySize(); ++i) {
                if (province.getArmy(i).civID > 0 && (province.getArmy(i).civID != Game.player.iCivID || Game.player.allowAIMove) && !civs.contains(province.getArmy(i).civID)) {
                    civs.add(province.getArmy(i).civID);
                }
            }
            if (civs.isEmpty()) {
                return;
            }
            List<Integer> possibleProvinces = new ArrayList<Integer>();
            for (int j = 0; j < province.getNeighboringProvincesSize(); ++j) {
                if (Game.getProvinceData4(province.getNeighboringProvinces(j)).isUnderSiege() && province.getCivID() == Game.getProvince(province.getNeighboringProvinces(j)).getCivID()) {
                    boolean allyArmy = false;
                    for (int k = 0; k < Game.getProvince(province.getNeighboringProvinces(j)).getArmySize(); ++k) {
                        for (int l = civs.size() - 1; l >= 0; --l) {
                            if (DiplomacyManager.isAlly(civs.get(l), Game.getProvince(province.getNeighboringProvinces(j)).getArmy(k).civID)) {
                                allyArmy = true;
                                break;
                            }
                        }
                        if (allyArmy) {
                            break;
                        }
                    }
                    if (allyArmy) {
                        possibleProvinces.add(province.getNeighboringProvinces(j));
                    }
                }
            }
            boolean end = false;
            int armyY = 0;
            if (possibleProvinces.size() > 0) {
                for (int m = province.getArmySize() - 1; m >= 0; --m) {
                    if ((province.getArmy(m).civID != Game.player.iCivID || Game.player.allowAIMove) && civs.contains(province.getArmy(m).civID) && !province.getArmy(m).inRetreat && !province.getArmy(m).inBattle) {
                        final int toProvinceID = possibleProvinces.get(Game.oR.nextInt(possibleProvinces.size()));
                        if (DiplomacyManager.isAtWar(province.getArmy(m).civID, Game.getProvince(toProvinceID).getCivID())) {
                            Game.getCiv(province.getArmy(m).civID).newMove(provinceID, toProvinceID, province.getArmy(m).key, armyY++, false);
                        }
                        end = true;
                    }
                }
            }
            possibleProvinces.clear();
            possibleProvinces = null;
            if (end) {
                civs.clear();
                civs = null;
                return;
            }
            for (int m = 0; m < province.getNeighboringProvincesSize(); ++m) {
                if (!Game.battleManager.isBattleInProvince(province.getNeighboringProvinces(m)) && !Game.getProvinceData4(province.getNeighboringProvinces(m)).isUnderSiege()) {
                    for (int j2 = 0; j2 < Game.getProvince(province.getNeighboringProvinces(m)).getArmySize(); ++j2) {
                        if (Game.getProvince(province.getNeighboringProvinces(m)).getArmy(j2).civID > 0 && (Game.getProvince(province.getNeighboringProvinces(m)).getArmy(j2).civID != Game.player.iCivID || Game.player.allowAIMove) && !Game.getProvince(province.getNeighboringProvinces(m)).getArmy(j2).inRetreat) {
                            int a = civs.size() - 1;
                            while (a >= 0) {
                                if (DiplomacyManager.isAlly(civs.get(a), Game.getProvince(province.getNeighboringProvinces(m)).getArmy(j2).civID)) {
                                    if (DiplomacyManager.isAtWar(province.getCivID(), Game.getProvince(province.getNeighboringProvinces(m)).getArmy(j2).civID)) {
                                        Game.getCiv(Game.getProvince(province.getNeighboringProvinces(m)).getArmy(j2).civID).newMove(province.getNeighboringProvinces(m), provinceID, Game.getProvince(province.getNeighboringProvinces(m)).getArmy(j2).key, armyY++, false);
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    --a;
                                }
                            }
                        }
                    }
                }
            }
            civs.clear();
            civs = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void siegeStarted_Defend(final int provinceID) {
        try {
            final Province province = Game.getProvince(provinceID);
            if (province.getCivID() > 0 && (province.getCivID() != Game.player.iCivID || Game.player.allowAIMove) && !province.isOccupied() && province.isCapital && Game.getCiv(province.getCivID()).getCapitalProvinceID() == provinceID && (Game.oR.nextInt(100) < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_DEFEND_CAPITAL_RANDOM || Game.getCiv(province.getCivID()).getNumOfProvinces() < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_DEFEND_CAPITAL_IF_NUM_OF_PROVINCES_BELOW)) {
                int enemyArmies = countEnemyArmy(provinceID);
                final int civID = province.getCivID();
                if (Game.getCiv(civID).getArmyRegimentSize() * Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE >= enemyArmies * 0.95f || Game.getCiv(province.getCivID()).getNumOfProvinces() < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_DEFEND_CAPITAL_IF_NUM_OF_PROVINCES_BELOW) {
                    final List<Integer> armyPos = new ArrayList<Integer>();
                    final List<Float> armyPosDistance = new ArrayList<Float>();
                    for (int i = 0; i < Game.getCiv(civID).iArmyPositionSize; ++i) {
                        armyPos.add(Game.getCiv(civID).getArmyPosition(i));
                        armyPosDistance.add(Game.getDistanceFromProvinceToProvince(provinceID, Game.getCiv(civID).getArmyPosition(i)));
                    }
                    while (armyPos.size() > 0 && enemyArmies > 0) {
                        int bestID = 0;
                        for (int j = armyPosDistance.size() - 1; j > 0; --j) {
                            if (armyPosDistance.get(j) < armyPosDistance.get(bestID)) {
                                bestID = j;
                            }
                        }
                        for (int k = Game.getProvince(armyPos.get(bestID)).getArmySize() - 1; k >= 0; --k) {
                            if (Game.getProvince(armyPos.get(bestID)).getArmy(k).civID == civID && Game.getCiv(civID).newMove(armyPos.get(bestID), provinceID, Game.getProvince(armyPos.get(bestID)).getArmy(k).key, 0, false)) {
                                enemyArmies -= Game.getProvince(armyPos.get(bestID)).getArmy(k).iArmyRegimentSize;
                            }
                        }
                        armyPos.remove(bestID);
                        armyPosDistance.remove(bestID);
                    }
                    armyPos.clear();
                    armyPosDistance.clear();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static int countEnemyArmy(final int provinceID) {
        final Province province = Game.getProvince(provinceID);
        int enemyArmies = 0;
        for (int i = 0; i < province.getArmySize(); ++i) {
            if (DiplomacyManager.isAtWar(province.getCivID(), province.getArmy(i).civID)) {
                enemyArmies += province.getArmy(i).iArmy;
            }
        }
        for (int j = 0; j < province.getNeighboringProvincesSize(); ++j) {
            for (int k = 0; k < Game.getProvince(province.getNeighboringProvinces(j)).getArmySize(); ++k) {
                if (DiplomacyManager.isAtWar(province.getCivID(), Game.getProvince(province.getNeighboringProvinces(j)).getArmy(k).civID)) {
                    enemyArmies += Game.getProvince(province.getNeighboringProvinces(j)).getArmy(k).iArmy;
                }
            }
        }
        return enemyArmies;
    }
}
