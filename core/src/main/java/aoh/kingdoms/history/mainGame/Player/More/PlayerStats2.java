// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class PlayerStats2
{
    public List<Integer> income;
    public List<Integer> balance;
    
    public PlayerStats2() {
        this.income = new ArrayList<Integer>();
        this.balance = new ArrayList<Integer>();
    }
    
    public void addIncome() {
        this.income.add((int)Math.ceil((Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome) * 100.0f));
    }
    
    public void addBalance() {
        this.balance.add((int)Math.ceil((Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth) * 100.0f));
    }
    
    public final void updateData() {
        this.addIncome();
        this.addBalance();
    }
}
