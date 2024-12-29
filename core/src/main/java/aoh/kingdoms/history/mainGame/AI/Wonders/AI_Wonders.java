// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Wonders;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Wonders
{
    public static void buildWonders() {
        try {
            buildWonder(Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_WONDERS);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void buildWonder(final int id) {
        if (id < WondersManager.wondersSize && !Game.getProvince(WondersManager.wonders.get(id).ProvinceID).getWonderBuilt() && Game.getProvince(WondersManager.wonders.get(id).ProvinceID).getCivID() > 0 && Game.getProvince(WondersManager.wonders.get(id).ProvinceID).getCivID() != Game.player.iCivID && Game.getCiv(Game.getProvince(WondersManager.wonders.get(id).ProvinceID).getCivID()).fGold > WondersManager.wonders.get(id).CostGold && Game.oR.nextInt(100) < Game.aiValuesBuild.BUILD_WONDER_CHANCE) {
            Game.getProvince(WondersManager.wonders.get(id).ProvinceID).buildWonder();
        }
    }
}
