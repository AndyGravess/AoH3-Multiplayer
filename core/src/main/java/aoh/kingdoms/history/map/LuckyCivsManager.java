// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;

public class LuckyCivsManager
{
    public static void buildLuckyCivs() {
        final List<Integer> possibleCivs = new ArrayList<Integer>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && i != Game.player.iCivID) {
                possibleCivs.add(i);
            }
        }
        for (int i = Math.min(Math.min(possibleCivs.size(), GameValues.luckyCivs.LUCKY_CIVS_LIMIT), (int)(possibleCivs.size() * GameValues.luckyCivs.LUCKY_CIVS_LIMIT_PERC_ALL_CIVS)) - 1; i >= 0; --i) {
            final int rand = Game.oR.nextInt(possibleCivs.size());
            luckyCiv(possibleCivs.get(rand));
            possibleCivs.remove(rand);
        }
        possibleCivs.clear();
    }
    
    public static void luckyCiv(final int civID) {
        final CivilizationBonuses civBonus = new CivilizationBonuses();
        civBonus.MonthlyIncome = GameValues.luckyCivs.BONUS_MONTHLY_INCOME;
        civBonus.ProductionEfficiency = GameValues.luckyCivs.BONUS_PRODUCTION_EFFICIENCY;
        civBonus.MaxManpower = (float)GameValues.luckyCivs.BONUS_MAX_MANPOWER;
        civBonus.RegimentsLimit = GameValues.luckyCivs.BONUS_REGIMENTS_LIMIT;
        civBonus.UnitsAttack = GameValues.luckyCivs.BONUS_UNITS_ATTACK;
        civBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.luckyCivs.BONUS_EXPIRES;
        Game.getCiv(civID).addCivilizationBonus_Temporary(civBonus);
    }
}
