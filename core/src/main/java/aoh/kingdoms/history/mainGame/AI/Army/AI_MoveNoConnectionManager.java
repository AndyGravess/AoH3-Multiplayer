// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_MoveNoConnectionManager
{
    public static void updateNoConnections() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_UNITS_NO_CONNECTIONS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_UNITS_NO_CONNECTIONS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateNoConnections(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_UNITS_NO_CONNECTIONS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateNoConnections(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_UNITS_NO_CONNECTIONS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void updateNoConnections(final int civID) {
        Game.getCiv(civID).noConnectionMoveUnits.update();
    }
}
