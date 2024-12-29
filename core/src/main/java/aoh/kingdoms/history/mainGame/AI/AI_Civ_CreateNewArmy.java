// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_CreateNewArmy_Task;
import java.util.List;

public class AI_Civ_CreateNewArmy
{
    public List<AI_CreateNewArmy_Task> cNA;
    
    public AI_Civ_CreateNewArmy() {
        this.cNA = new ArrayList<AI_CreateNewArmy_Task>();
    }
    
    public final void runCreateNewArmy_Task(final int civID) {
        for (int i = 0; i < this.cNA.size(); ++i) {
            if (this.runCreateNewArmy_Task(civID, i)) {
                --i;
            }
            else if (this.cNA.get(i).t < Game_Calendar.TURN_ID) {
                this.cNA.remove(i--);
            }
        }
    }
    
    public final void runCreateNewArmy_Expired(final int civID) {
        for (int i = 0; i < this.cNA.size(); ++i) {
            if (this.cNA.get(i).t < Game_Calendar.TURN_ID) {
                this.cNA.remove(i--);
            }
        }
    }
    
    public final boolean runCreateNewArmy_Task(final int civID, final int id) {
        if (this.cNA.get(id).recruitArmy(civID)) {
            this.cNA.remove(id);
            return true;
        }
        return false;
    }
    
    public int createNewArmy_RegimentsLeft() {
        int out = 0;
        for (int i = this.cNA.size() - 1; i >= 0; --i) {
            out += this.cNA.get(i).rL;
        }
        return out;
    }
    
    public int getCreateNewArmy_RegimentsInQueue(final String armyKey) {
        int out = 0;
        for (int i = this.cNA.size() - 1; i >= 0; --i) {
            if (this.cNA.get(i).key.equals(armyKey)) {
                out += this.cNA.get(i).rL;
            }
        }
        return out;
    }
}
