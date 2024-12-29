// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_BattleEnd
{
    public static void battleEnded(final int provinceID) {
        boolean isThereAnythingToMove = false;
        for (int i = 0; i < Game.getProvince(provinceID).getArmySize(); ++i) {
            if (!Game.getProvince(provinceID).getArmy(i).inRetreat && !Game.getProvince(provinceID).getArmy(i).inBattle && !Game.getProvince(provinceID).getArmy(i).inMovement && Game.getProvince(provinceID).getArmy(i).civID > 0 && (Game.getProvince(provinceID).getArmy(i).civID != Game.player.iCivID || Game.player.allowAIMove)) {
                isThereAnythingToMove = true;
                break;
            }
        }
        if (!isThereAnythingToMove) {
            return;
        }
        Game.getProvince(provinceID).wasBattleEnded = true;
        for (int a = 0; a < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++a) {
            int inProvinceID = Game.getProvince(provinceID).getNeighboringProvinces(a);
            Game.getProvince(inProvinceID).wasBattleEnded = false;
            for (int b = 0; b < Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvincesSize(); ++b) {
                inProvinceID = Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvinces(b);
                Game.getProvince(inProvinceID).wasBattleEnded = false;
            }
        }
        for (int a = 0; a < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++a) {
            int inProvinceID = Game.getProvince(provinceID).getNeighboringProvinces(a);
            moveArmiesToBattle(provinceID, inProvinceID);
            for (int b = 0; b < Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvincesSize(); ++b) {
                inProvinceID = Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(a)).getNeighboringProvinces(b);
                moveArmiesToBattle(provinceID, inProvinceID);
            }
        }
        isThereAnythingToMove = false;
        for (int j = 0; j < Game.getProvince(provinceID).getArmySize(); ++j) {
            if (!Game.getProvince(provinceID).getArmy(j).inRetreat && !Game.getProvince(provinceID).getArmy(j).inBattle && !Game.getProvince(provinceID).getArmy(j).inMovement && Game.getProvince(provinceID).getArmy(j).civID > 0 && (Game.getProvince(provinceID).getArmy(j).civID != Game.player.iCivID || Game.player.allowAIMove)) {
                isThereAnythingToMove = true;
                break;
            }
        }
        if (!isThereAnythingToMove) {
            return;
        }
        for (int a = 0; a < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++a) {
            final int inProvinceID = Game.getProvince(provinceID).getNeighboringProvinces(a);
            moveArmiesToEnemyArmy(provinceID, inProvinceID);
        }
    }
    
    public static void moveArmiesToBattle(final int fromProvinceID, final int inProvinceID) {
        if (!Game.getProvince(inProvinceID).wasBattleEnded) {
            Game.getProvince(inProvinceID).wasBattleEnded = true;
            if (!Game.battleManager.isBattleInProvince(inProvinceID)) {
                return;
            }
            if (Game.getProvince(inProvinceID).getArmySize() > 0) {
                for (int i = 0; i < Game.getProvince(fromProvinceID).getArmySize(); ++i) {
                    if (!Game.getProvince(fromProvinceID).getArmy(i).inRetreat && !Game.getProvince(fromProvinceID).getArmy(i).inBattle && !Game.getProvince(fromProvinceID).getArmy(i).inMovement && Game.getProvince(fromProvinceID).getArmy(i).civID > 0 && (Game.getProvince(fromProvinceID).getArmy(i).civID != Game.player.iCivID || Game.player.allowAIMove)) {
                        for (int j = 0; j < Game.getProvince(inProvinceID).getArmySize(); ++j) {
                            if (DiplomacyManager.isAtWar(Game.getProvince(fromProvinceID).getArmy(i).civID, Game.getProvince(inProvinceID).getArmy(j).civID)) {
                                Game.getCiv(Game.getProvince(fromProvinceID).getArmy(i).civID).newMove(fromProvinceID, inProvinceID, Game.getProvince(fromProvinceID).getArmy(i).key, 0, false);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static void moveArmiesToEnemyArmy(final int fromProvinceID, final int inProvinceID) {
        if (Game.getProvince(inProvinceID).getArmySize() > 0) {
            for (int i = 0; i < Game.getProvince(fromProvinceID).getArmySize(); ++i) {
                if (!Game.getProvince(fromProvinceID).getArmy(i).inRetreat && !Game.getProvince(fromProvinceID).getArmy(i).inBattle && !Game.getProvince(fromProvinceID).getArmy(i).inMovement && Game.getProvince(fromProvinceID).getArmy(i).civID > 0 && (Game.getProvince(fromProvinceID).getArmy(i).civID != Game.player.iCivID || Game.player.allowAIMove)) {
                    for (int j = 0; j < Game.getProvince(inProvinceID).getArmySize(); ++j) {
                        if (DiplomacyManager.isAtWar(Game.getProvince(fromProvinceID).getArmy(i).civID, Game.getProvince(inProvinceID).getArmy(j).civID) && Game.getProvince(fromProvinceID).getArmy(i).iArmy > Game.getProvince(inProvinceID).getArmy(j).civID) {
                            Game.getCiv(Game.getProvince(fromProvinceID).getArmy(i).civID).newMove(fromProvinceID, inProvinceID, Game.getProvince(fromProvinceID).getArmy(i).key, 0, false);
                        }
                    }
                }
            }
        }
    }
}
