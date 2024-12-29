// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.army.ArmyPosition;
import aoc.kingdoms.lukasz.map.SiegeManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_MoveAtPeace
{
    public static void moveAtPeace(final int civID) {
        try {
            if (Game.getCiv(civID).underSiegeSize > 0 || Game.getCiv(civID).occupiedProvincesSize > 0) {
                final List<ArmyDivision_TempData> possibleToMove = new ArrayList<ArmyDivision_TempData>();
                for (int j = 0; j < Game.getCiv(civID).iArmyPositionSize; ++j) {
                    if (Game.getProvince(Game.getCiv(civID).getArmyPosition(j)).isOccupied() && !Game.getProvinceData4(Game.getCiv(civID).getArmyPosition(j)).isUnderSiege()) {
                        SiegeManager.checkForSiege(Game.getCiv(civID).getArmyPosition(j));
                    }
                    if (!Game.getProvinceData4(Game.getCiv(civID).getArmyPosition(j)).isUnderSiege()) {
                        final ArmyDivision armyDivision = Game.getProvince(Game.getCiv(civID).getArmyPosition(j)).getArmy(Game.getCiv(civID).getArmyPositionKey(j));
                        if (armyDivision != null) {
                            if (armyDivision.inMovement) {
                                if (!Game.getCiv(civID).isInMoveUnits_ArmyKey(armyDivision.key)) {
                                    armyDivision.inMovement = false;
                                    armyDivision.inRetreat = false;
                                }
                            }
                            else if (!armyDivision.inBattle) {
                                possibleToMove.add(new ArmyDivision_TempData(Game.getCiv(civID).armyPosition.get(j), armyDivision.iArmyRegimentSize));
                            }
                        }
                    }
                }
                if (possibleToMove.isEmpty()) {
                    if (Game.getCiv(civID).getArmyRegimentSize() < 6) {
                        AI_RecruitMercenaries.recruitMercenaries(civID);
                    }
                    return;
                }
                final List<Integer> provinces = new ArrayList<Integer>();
                for (int i = Game.getCiv(civID).underSiegeSize - 1; i >= 0; --i) {
                    if (Game.getProvinceData4(Game.getCiv(civID).underSiege.get(i)).isUnderSiege()) {
                        provinces.add(Game.getCiv(civID).underSiege.get(i));
                    }
                    else {
                        Game.getCiv(civID).underSiege.remove(i);
                        Game.getCiv(civID).underSiegeSize = Game.getCiv(civID).underSiege.size();
                    }
                }
                while (provinces.size() > 0) {
                    final int id = Game.oR.nextInt(provinces.size());
                    int enemyArmy = 0;
                    for (int k = 0; k < Game.getProvince(provinces.get(id)).getArmySize(); ++k) {
                        if (DiplomacyManager.isAtWar(civID, Game.getProvince(provinces.get(id)).getArmy(k).civID)) {
                            enemyArmy += Game.getProvince(provinces.get(id)).getArmy(k).iArmyRegimentSize;
                        }
                    }
                    enemyArmy -= Game.getCiv(civID).armyMovingToProvince_MoveUnits(provinces.get(id));
                    if (enemyArmy > 0 && Game.getCiv(civID).getArmyRegimentSize() >= enemyArmy) {
                        for (int a = possibleToMove.size() - 1; a >= 0; --a) {
                            possibleToMove.get(a).distance = Game.getDistanceFromProvinceToProvince(provinces.get(id), possibleToMove.get(a).armyPosition.provinceID);
                        }
                        while (enemyArmy > 0 && possibleToMove.size() > 0) {
                            int bestID = 0;
                            for (int a2 = possibleToMove.size() - 1; a2 > 0; --a2) {
                                if (possibleToMove.get(bestID).distance > possibleToMove.get(a2).distance) {
                                    bestID = a2;
                                }
                            }
                            if (possibleToMove.get(bestID).distance == 100000.0f) {
                                break;
                            }
                            if (Game.getCiv(civID).newMove(possibleToMove.get(bestID).armyPosition.provinceID, provinces.get(id), possibleToMove.get(bestID).armyPosition.key, 0, false)) {
                                enemyArmy -= possibleToMove.get(bestID).iRegiments;
                                possibleToMove.remove(bestID);
                            }
                            else {
                                possibleToMove.get(bestID).distance = 100000.0f;
                            }
                        }
                    }
                    provinces.remove(id);
                }
                provinces.clear();
                if (possibleToMove.size() == 0) {
                    return;
                }
                for (int i = Game.getCiv(civID).occupiedProvincesSize - 1; i >= 0; --i) {
                    if (Game.getProvinceData(Game.getCiv(civID).occupiedProvinces.get(i)).getOccupiedByCivID() != 0) {
                        provinces.add(Game.getCiv(civID).occupiedProvinces.get(i));
                    }
                    else {
                        Game.getCiv(civID).occupiedProvinces.remove(i);
                        Game.getCiv(civID).occupiedProvincesSize = Game.getCiv(civID).occupiedProvinces.size();
                    }
                }
                while (!provinces.isEmpty()) {
                    int id = Game.oR.nextInt(provinces.size());
                    if (Game.getProvince(provinces.get(id)).getFortLevel() <= 0) {
                        int iNeigh = 0;
                        for (int l = 0; l < Game.getProvince(provinces.get(id)).getNeighboringProvincesSize(); ++l) {
                            if (Game.getProvince(Game.getProvince(provinces.get(id)).getNeighboringProvinces(l)).getCivID() == civID && Game.getProvinceData(Game.getProvince(provinces.get(id)).getNeighboringProvinces(l)).getOccupiedByCivID() != 0) {
                                ++iNeigh;
                            }
                        }
                        for (int l = 0; l < Game.getProvince(provinces.get(id)).getNeighboringProvincesSize(); ++l) {
                            int pNeigh = 0;
                            for (int m = 0; m < Game.getProvince(Game.getProvince(provinces.get(id)).getNeighboringProvinces(l)).getNeighboringProvincesSize(); ++m) {
                                if (Game.getProvince(Game.getProvince(Game.getProvince(provinces.get(id)).getNeighboringProvinces(l)).getNeighboringProvinces(m)).getCivID() == civID && Game.getProvinceData(Game.getProvince(Game.getProvince(provinces.get(id)).getNeighboringProvinces(l)).getNeighboringProvinces(m)).getOccupiedByCivID() != 0) {
                                    ++pNeigh;
                                }
                            }
                            if (iNeigh < pNeigh) {
                                for (int k2 = provinces.size() - 1; k2 >= 0; --k2) {
                                    if (provinces.get(k2) == Game.getProvince(provinces.get(id)).getNeighboringProvinces(l)) {
                                        id = k2;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    int enemyArmy = GameValues.siege.SIEGE_REGIMENTS_MIN + 1;
                    for (int k = 0; k < Game.getProvince(provinces.get(id)).getArmySize(); ++k) {
                        if (DiplomacyManager.isAtWar(civID, Game.getProvince(provinces.get(id)).getArmy(k).civID)) {
                            enemyArmy += Game.getProvince(provinces.get(id)).getArmy(k).iArmyRegimentSize;
                        }
                    }
                    enemyArmy -= Game.getCiv(civID).armyMovingToProvince_MoveUnits(provinces.get(id));
                    if (enemyArmy > 0) {
                        for (int a = possibleToMove.size() - 1; a >= 0; --a) {
                            possibleToMove.get(a).distance = Game.getDistanceFromProvinceToProvince(provinces.get(id), possibleToMove.get(a).armyPosition.provinceID);
                        }
                        while (enemyArmy > 0 && possibleToMove.size() > 0) {
                            int bestID = 0;
                            for (int a2 = possibleToMove.size() - 1; a2 > 0; --a2) {
                                if (possibleToMove.get(bestID).distance > possibleToMove.get(a2).distance) {
                                    bestID = a2;
                                }
                            }
                            if (possibleToMove.get(bestID).distance == 100000.0f) {
                                break;
                            }
                            if (Game.getCiv(civID).newMove(possibleToMove.get(bestID).armyPosition.provinceID, provinces.get(id), possibleToMove.get(bestID).armyPosition.key, 0, false)) {
                                enemyArmy -= possibleToMove.get(bestID).iRegiments;
                                possibleToMove.remove(bestID);
                            }
                            else {
                                possibleToMove.get(bestID).distance = 100000.0f;
                            }
                        }
                    }
                    provinces.remove(id);
                }
                possibleToMove.clear();
                provinces.clear();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static class ArmyDivision_TempData
    {
        public ArmyPosition armyPosition;
        public int iRegiments;
        public float distance;
        
        public ArmyDivision_TempData(final ArmyPosition armyPosition, final int iRegiments) {
            this.armyPosition = armyPosition;
            this.iRegiments = iRegiments;
        }
    }
}
