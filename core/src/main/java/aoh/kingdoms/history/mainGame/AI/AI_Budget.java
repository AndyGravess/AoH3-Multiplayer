// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import java.util.ArrayList;
import java.util.List;

public class AI_Budget
{
    public List<AI_BudgetDefault> tx;
    public List<AI_BudgetDefault> a;
    
    public AI_Budget() {
        this.tx = new ArrayList<AI_BudgetDefault>();
        this.a = new ArrayList<AI_BudgetDefault>();
    }
    
    public final void updateBudget() {
        try {
            if (Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_BUDGET_DEFAULT == 0) {
                for (int i = this.tx.size() - 1; i >= 0; --i) {
                    if (Game_Calendar.TURN_ID > this.tx.get(i).t) {
                        Game.getCiv(this.tx.get(i).c).updateTaxationLevel(1);
                        this.tx.remove(i);
                    }
                }
                for (int i = this.a.size() - 1; i >= 0; --i) {
                    if (Game_Calendar.TURN_ID > this.a.get(i).t) {
                        if (!Game.getCiv(this.a.get(i).c).diplomacy.isAtWar()) {
                            Game.getCiv(this.a.get(i).c).updateMilitaryLevel(Math.max(1, Game.getCiv(this.a.get(i).c).getMilitaryLevel()));
                        }
                        this.a.remove(i);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_BUDGET; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUDGET) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.updateBudget(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUDGET;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.updateBudget(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUDGET;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateBudget(final int civID) {
        if (Game.oR.nextInt(100) < Game.aiValuesArmies.CHANGE_BATTLE_TACTICS_CHANCE) {
            Game.getCiv(civID).setBattleTacticsID(Game.oR.nextInt(GameValues.battleTactics.BATTLE_TACTICS.length));
        }
        if (Game.getProvince(Game.getCiv(civID).getCapitalProvinceID()).getRevulutionaryRisk() > Game.aiValues.BUDGET_DECREASE_TAXATION_IF_UNREST_IN_CAPITAL_OVER) {
            Game.getCiv(civID).updateTaxationLevel(0);
            this.tx.add(new AI_BudgetDefault(civID, Game_Calendar.TURN_ID + Game.aiValues.BUDGET_DECREASE_TAXATION_TURNS_MIN + Game.oR.nextInt(Game.aiValues.BUDGET_DECREASE_TAXATION_TURNS_RANDOM)));
        }
        else if (Game.oR.nextInt(100) < Game.aiValues.BUDGET_INCREASE_TAXATION_CHANCE && Game.getCiv(civID).civStability_LostFrom100 <= Game.aiValues.BUDGET_INCREASE_TAXATION_IF_STABILITY_BELOW && Game.getProvince(Game.getCiv(civID).getCapitalProvinceID()).getRevulutionaryRisk() < Game.aiValues.BUDGET_INCREASE_TAXATION_IF_UNREST_IN_CAPITAL_BELOW) {
            Game.getCiv(civID).updateTaxationLevel(2);
            this.tx.add(new AI_BudgetDefault(civID, Game_Calendar.TURN_ID + Game.aiValues.BUDGET_INCREASE_TAXATION_TURNS_MIN + Game.oR.nextInt(Game.aiValues.BUDGET_INCREASE_TAXATION_TURNS_RANDOM)));
        }
        if (!Game.getCiv(civID).diplomacy.isAtWar() && Game.getCiv(civID).aiCivDiplomacy.w.isEmpty() && Game.oR.nextInt(100) < Game.aiValues.BUDGET_DECREASE_MILITARY_CHANCE) {
            Game.getCiv(civID).updateMilitaryLevel(0);
            this.a.add(new AI_BudgetDefault(civID, Game_Calendar.TURN_ID + Game.aiValues.BUDGET_DECREASE_MILITARY_TURNS_MIN + Game.oR.nextInt(Game.aiValues.BUDGET_DECREASE_MILITARY_TURNS_RANDOM)));
        }
    }
    
    public final void updateMilitaryLevel_War(final int civID, final int civB, final int regimentsA, final int regimentsB) {
        if (civID == Game.player.iCivID) {
            return;
        }
        if (Game.getCiv(civB).getArmyRegimentSize() / (float)Game.getCiv(civID).getArmyRegimentSize() > GameValues.war.AI_MAX_MILITARY_LEVEL_IF_REGIMENTS_RATIO_OVER || regimentsB / (float)regimentsA > GameValues.war.AI_MAX_MILITARY_LEVEL_IF_REGIMENTS_RATIO_OVER) {
            Game.getCiv(civID).updateMilitaryLevel(GameValues.war.AI_AT_WAR_MAX_MILITARY_LEVEL);
        }
    }
}
