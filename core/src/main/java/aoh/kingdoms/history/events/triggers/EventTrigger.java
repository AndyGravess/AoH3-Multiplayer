// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import java.util.ArrayList;
import java.util.List;

public class EventTrigger
{
    public List<EventTrigger_Value> triggersAnd;
    public List<EventTrigger_Value> triggersAndNot;
    public List<EventTrigger_Value> triggersOr;
    public List<EventTrigger_Value> triggersOrNot;
    
    public EventTrigger() {
        this.triggersAnd = new ArrayList<EventTrigger_Value>();
        this.triggersAndNot = new ArrayList<EventTrigger_Value>();
        this.triggersOr = new ArrayList<EventTrigger_Value>();
        this.triggersOrNot = new ArrayList<EventTrigger_Value>();
    }
    
    public void addTrigger(final EventTrigger_Value trigger, final int typeID) {
        switch (typeID) {
            case 0: {
                this.triggersAnd.add(trigger);
                break;
            }
            case 1: {
                this.triggersAndNot.add(trigger);
                break;
            }
            case 2: {
                this.triggersOr.add(trigger);
                break;
            }
            case 3: {
                this.triggersOrNot.add(trigger);
                break;
            }
        }
    }
    
    public final boolean runTriggers(final int iCivID, final int iProvinceID) {
        for (int i = this.triggersOr.size() - 1; i >= 0; --i) {
            if (this.triggersOr.get(i).outCondition(iCivID, iProvinceID)) {
                return true;
            }
        }
        for (int i = this.triggersOrNot.size() - 1; i >= 0; --i) {
            if (!this.triggersOrNot.get(i).outCondition(iCivID, iProvinceID)) {
                return true;
            }
        }
        for (int i = this.triggersAnd.size() - 1; i >= 0; --i) {
            if (!this.triggersAnd.get(i).outCondition(iCivID, iProvinceID)) {
                return false;
            }
        }
        for (int i = this.triggersAndNot.size() - 1; i >= 0; --i) {
            if (this.triggersAndNot.get(i).outCondition(iCivID, iProvinceID)) {
                return false;
            }
        }
        return this.triggersAnd.size() > 0 || this.triggersAndNot.size() > 0;
    }
}
