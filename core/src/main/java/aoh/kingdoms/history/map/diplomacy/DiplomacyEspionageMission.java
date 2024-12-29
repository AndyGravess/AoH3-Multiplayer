// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.diplomacy;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.GameValues;

public class DiplomacyEspionageMission
{
    public int iCivID;
    public int iReportTurnID;
    public int iReportExpiresTurnID;
    
    public DiplomacyEspionageMission() {
    }
    
    public DiplomacyEspionageMission(final int iCivID, final int iReportTurnID) {
        this.iCivID = iCivID;
        this.iReportTurnID = iReportTurnID;
        this.iReportExpiresTurnID = iReportTurnID + GameValues.spy.SEND_SPY_REPORT_ACTIVE;
    }
    
    public boolean isActive() {
        return Game_Calendar.TURN_ID >= this.iReportTurnID;
    }
}
