// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_BattleStart
{
    public static void battleStarted(final int battleID, final int provinceID, final int numOfProvincesToMove) {
        try {
            if (battleID < 0) {
                return;
            }
            if (Game.battleManager.getBattle(battleID).attackingArmy.iCivID < 0 || Game.battleManager.getBattle(battleID).defendingArmy.iCivID < 0) {
                return;
            }
            final String warKey = WarManager.getWarKey(Game.battleManager.getBattle(battleID).attackingArmy.iCivID, Game.battleManager.getBattle(battleID).defendingArmy.iCivID);
            if (warKey == null) {
                return;
            }
            wasBattleStartRecursively(provinceID, numOfProvincesToMove);
            battleStarted_SeaProvinces_UpdateWas(provinceID);
            Game.getProvince(provinceID).wasBattleStart = true;
            moveArmiesRecursively(warKey, provinceID, provinceID, numOfProvincesToMove);
            battleStarted_SeaProvinces(warKey, provinceID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void wasBattleStartRecursively(final int currentProvinceID, final int depth) {
        if (depth <= 0) {
            return;
        }
        final Province currentProvince = Game.getProvince(currentProvinceID);
        for (int i = 0; i < currentProvince.getNeighboringProvincesSize(); ++i) {
            final int neighborID = currentProvince.getNeighboringProvinces(i);
            Game.getProvince(neighborID).wasBattleStart = false;
            wasBattleStartRecursively(neighborID, depth - 1);
        }
    }
    
    public static void moveArmiesRecursively(final String warKey, final int originProvinceID, final int currentProvinceID, final int depth) {
        if (depth <= 0) {
            return;
        }
        final Province currentProvince = Game.getProvince(currentProvinceID);
        for (int i = 0; i < currentProvince.getNeighboringProvincesSize(); ++i) {
            final int neighborID = currentProvince.getNeighboringProvinces(i);
            moveArmies(warKey, originProvinceID, neighborID);
            moveArmiesRecursively(warKey, originProvinceID, neighborID, depth - 1);
        }
    }
    
    public static void battleStarted_2(final int battleID, final int provinceID) {
        try {
            if (battleID < 0) {
                return;
            }
            if (Game.battleManager.getBattle(battleID).attackingArmy.iCivID < 0 || Game.battleManager.getBattle(battleID).defendingArmy.iCivID < 0) {
                return;
            }
            final String warKey = WarManager.getWarKey(Game.battleManager.getBattle(battleID).attackingArmy.iCivID, Game.battleManager.getBattle(battleID).defendingArmy.iCivID);
            if (warKey == null) {
                return;
            }
            for (int a = 0; a < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++a) {
                int inProvinceID = Game.getProvince(provinceID).getNeighboringProvinces(a);
                Game.getProvince(inProvinceID).wasBattleStart = false;
                for (int b = 0; b < Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvincesSize(); ++b) {
                    inProvinceID = Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvinces(b);
                    Game.getProvince(inProvinceID).wasBattleStart = false;
                }
            }
            Game.getProvince(provinceID).wasBattleStart = true;
            for (int a = 0; a < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++a) {
                int inProvinceID = Game.getProvince(provinceID).getNeighboringProvinces(a);
                moveArmies(warKey, provinceID, inProvinceID);
                for (int b = 0; b < Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvincesSize(); ++b) {
                    inProvinceID = Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvinces(b);
                    moveArmies(warKey, provinceID, inProvinceID);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void battleStarted_1(final int battleID, final int provinceID) {
        try {
            if (battleID < 0) {
                return;
            }
            if (Game.battleManager.getBattle(battleID).attackingArmy.iCivID < 0 || Game.battleManager.getBattle(battleID).defendingArmy.iCivID < 0) {
                return;
            }
            final String warKey = WarManager.getWarKey(Game.battleManager.getBattle(battleID).attackingArmy.iCivID, Game.battleManager.getBattle(battleID).defendingArmy.iCivID);
            if (warKey == null) {
                return;
            }
            for (int a = 0; a < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++a) {
                final int inProvinceID = Game.getProvince(provinceID).getNeighboringProvinces(a);
                if (!Game.getProvinceData4(inProvinceID).isUnderSiege()) {
                    moveArmies(warKey, provinceID, inProvinceID);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void battleStarted_SeaProvinces_UpdateWas(final int provinceID) {
        for (int i = 0; i < Game.getProvince(provinceID).getNeighboringSeaProvincesSize(); ++i) {
            int inProvinceID = Game.getProvince(provinceID).getNeighboringSeaProvinces(i);
            Game.getProvince(inProvinceID).wasBattleStart = false;
            for (int j = 0; j < Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvincesSize(); ++j) {
                inProvinceID = Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvinces(j);
                Game.getProvince(inProvinceID).wasBattleStart = false;
                for (int k = 0; k < Game.getProvince(Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getNeighboringProvincesSize(); ++k) {
                    inProvinceID = Game.getProvince(Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getNeighboringProvinces(k);
                    Game.getProvince(inProvinceID).wasBattleStart = false;
                }
            }
        }
    }
    
    public static void battleStarted_SeaProvinces(final String warKey, final int provinceID) {
        for (int i = 0; i < Game.getProvince(provinceID).getNeighboringSeaProvincesSize(); ++i) {
            int inProvinceID = Game.getProvince(provinceID).getNeighboringSeaProvinces(i);
            moveArmies(warKey, provinceID, inProvinceID);
            for (int j = 0; j < Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvincesSize(); ++j) {
                inProvinceID = Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvinces(j);
                moveArmies(warKey, provinceID, inProvinceID);
                for (int k = 0; k < Game.getProvince(Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getNeighboringProvincesSize(); ++k) {
                    inProvinceID = Game.getProvince(Game.getProvince(Game.getProvince(provinceID).getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getNeighboringProvinces(k);
                    moveArmies(warKey, provinceID, inProvinceID);
                }
            }
        }
    }
    
    public static void moveArmies(final String warKey, final int battleProvinceID, final int inProvinceID) {
        if (!Game.getProvince(inProvinceID).wasBattleStart) {
            Game.getProvince(inProvinceID).wasBattleStart = true;
            for (int i = 0; i < Game.getProvince(inProvinceID).getArmySize(); ++i) {
                if (Game.getProvince(inProvinceID).getArmy(i).civID != Game.player.iCivID || Game.player.allowAIMove) {
                    if (!Game.getProvince(inProvinceID).getArmy(i).inBattle && !Game.getProvince(inProvinceID).getArmy(i).inRetreat && WarManager.lWars.get(warKey).isInThisWar(Game.getProvince(inProvinceID).getArmy(i).civID)) {
                        if (Game.getProvinceData4(inProvinceID).isUnderSiege()) {
                            if (Game.getProvince(inProvinceID).getSiegeProgress() >= Game.aiValuesArmies.BATTLE_START_DONT_MOVE_IF_SIEGE_OVER) {
                                continue;
                            }
                            if (Game.aiValuesArmies.BATTLE_START_DONT_MOVE_IF_SIEGE_IN_PROGRESS > Game.oR.nextInt(100)) {
                                continue;
                            }
                        }
                        if (Game.getProvince(inProvinceID).getArmy(i).fMorale > Game.aiValuesArmies.BATTLE_START_DONT_MOVE_IF_MORALE_BELOW) {
                            if (Game.getCiv(Game.getProvince(inProvinceID).getArmy(i).civID).fManpower <= 1000.0 || Game.getProvince(inProvinceID).getArmy(i).getPercOfTotalUnits() >= Game.aiValuesArmies.BATTLE_START_DONT_MOVE_IF_ARMY_UNITS_BELOW_PERC) {
                                if (!Game.getProvince(inProvinceID).getArmy(i).inMovement || Game.aiValuesArmies.BATTLE_START_DONT_MOVE_IF_ARMY_IS_IN_MOVEMENT <= Game.oR.nextInt(100)) {
                                    Game.getCiv(Game.getProvince(inProvinceID).getArmy(i).civID).newMove(inProvinceID, battleProvinceID, Game.getProvince(inProvinceID).getArmy(i).key, 0, false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
