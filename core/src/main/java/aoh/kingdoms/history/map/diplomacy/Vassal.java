// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.diplomacy;

import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class Vassal
{
    public int c;
    public int tL;
    public int mL;
    public boolean cW;
    public float lD;
    
    public Vassal() {
        this.lD = 0.0f;
    }
    
    public Vassal(final int iCivID) {
        this.lD = 0.0f;
        this.c = iCivID;
        this.tL = 1;
        this.setManpower(Game.oR.nextInt(GameValues.vassal.VASSAL_MANPOWER_TO_LORD.length));
        if (this.mL == 0) {
            this.setTribute(GameValues.vassal.VASSAL_INCOME_TO_LORD.length - 1);
        }
        this.cW = GameValues.vassal.VASSAL_CAN_DECLARE_WAR_DEFAULT;
    }
    
    public final void setTribute(final int iTribute) {
        this.tL = Math.max(0, Math.min(GameValues.vassal.VASSAL_INCOME_TO_LORD.length - 1, iTribute));
        if (this.tL == 2) {
            this.mL = 0;
        }
        else if (this.tL == 1) {
            this.mL = Math.min(1, this.mL);
        }
    }
    
    public final void setManpower(final int iManpowerLevel) {
        this.mL = Math.max(0, Math.min(GameValues.vassal.VASSAL_MANPOWER_TO_LORD.length - 1, iManpowerLevel));
        if (iManpowerLevel == 2) {
            this.tL = 0;
        }
        else if (iManpowerLevel == 1) {
            this.tL = Math.min(1, this.tL);
        }
    }
    
    public void setLibertyDesire_Change(final float value) {
        this.lD = Math.min(Math.max(GameValues.vassal.LIBERTY_DESIRE_MIN, this.lD + value), GameValues.vassal.LIBERTY_DESIRE_MAX);
    }
}
