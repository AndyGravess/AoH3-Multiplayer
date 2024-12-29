// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Loan
{
    public static boolean canTakeLoan(final int civID) {
        return Game.getCiv(civID).iLoansSize < Game.aiValues.LOANS_LIMIT;
    }
    
    public static boolean takeLoan_Chance(final int civID, final int chance) {
        return Game.oR.nextInt(100) < chance && takeLoan(civID);
    }
    
    public static boolean takeLoan(final int civID) {
        if (Game.getCiv(civID).iLoansSize >= Game.getLoanMaxNumber(civID)) {
            return false;
        }
        if (Game.getCiv(civID).fGold > Game.aiValues.TAKE_LOAN_ONLY_IF_TREASURY_BELOW) {
            return false;
        }
        Game.getCiv(civID).takeLoan();
        Game.getCiv(civID).reduceInflation();
        return true;
    }
}
