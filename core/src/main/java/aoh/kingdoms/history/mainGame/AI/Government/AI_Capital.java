// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Government;

import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Capital
{
    public static void checkCapital(final int civID) {
        if (Game.getCiv(civID).getCapitalProvinceID() < 0 || Game.getProvince(Game.getCiv(civID).getCapitalProvinceID()).getCivID() != civID) {
            Game.getCiv(civID).moveCapital_ToLargestProvince();
        }
    }
}
