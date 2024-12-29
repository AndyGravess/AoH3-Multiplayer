// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class Loan
{
    public float fLoanValue;
    public float fInterestPerMonth;
    public int iExpires_TurnID;
    
    public Loan(final float fLoanValue) {
        this.fLoanValue = fLoanValue;
        this.iExpires_TurnID = Game_Calendar.TURN_ID + Game.getLoanExpires();
        this.fInterestPerMonth = Game.getLoanMonthlyExpenses(fLoanValue);
    }
    
    public Loan(final SaveGameManager.Save_Civ_Loan nLoan) {
        this.fLoanValue = nLoan.l;
        this.iExpires_TurnID = nLoan.e;
        this.fInterestPerMonth = nLoan.n;
    }
    
    public float getLoanValueLeft() {
        return this.fLoanValue * (1.0f - Math.min(1.0f, (this.iExpires_TurnID - Game_Calendar.TURN_ID) / (float)Game.getLoanExpires()));
    }
}
