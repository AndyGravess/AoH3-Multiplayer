// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events;

import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger;
import java.util.List;

public class Event
{
    public String id;
    public String title;
    public String desc;
    public String mission_desc;
    public String image;
    public boolean only_once;
    public int mission_image;
    public boolean show_in_missions;
    public boolean popUp;
    public boolean run_in_background;
    public boolean possible_to_run;
    public int runCivsID;
    public List<EventOption> options;
    public List<EventTrigger> triggersAnd;
    public List<EventTrigger> triggersAndNot;
    public List<EventTrigger> triggersOr;
    public List<EventTrigger> triggersOrNot;
    
    public Event() {
        this.title = "";
        this.desc = "";
        this.mission_desc = "";
        this.image = null;
        this.only_once = true;
        this.mission_image = 0;
        this.show_in_missions = false;
        this.popUp = false;
        this.run_in_background = false;
        this.possible_to_run = true;
        this.runCivsID = 0;
        this.options = new ArrayList<EventOption>();
        this.triggersAnd = new ArrayList<EventTrigger>();
        this.triggersAndNot = new ArrayList<EventTrigger>();
        this.triggersOr = new ArrayList<EventTrigger>();
        this.triggersOrNot = new ArrayList<EventTrigger>();
    }
    
    public void addTrigger(final EventTrigger trigger, final int typeID) {
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
    
    public final boolean runTriggers(final int iCivID) {
        final int iProvinceID = 0;
        for (int i = this.triggersOr.size() - 1; i >= 0; --i) {
            try {
                if (this.triggersOr.get(i).runTriggers(iCivID, iProvinceID)) {
                    return true;
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        for (int i = this.triggersOrNot.size() - 1; i >= 0; --i) {
            try {
                if (!this.triggersOrNot.get(i).runTriggers(iCivID, iProvinceID)) {
                    return true;
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        for (int i = this.triggersAnd.size() - 1; i >= 0; --i) {
            try {
                if (!this.triggersAnd.get(i).runTriggers(iCivID, iProvinceID)) {
                    return false;
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        for (int i = this.triggersAndNot.size() - 1; i >= 0; --i) {
            try {
                if (this.triggersAndNot.get(i).runTriggers(iCivID, iProvinceID)) {
                    return false;
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        return this.triggersAnd.size() > 0 || this.triggersAndNot.size() > 0;
    }
    
    public boolean addEvent() {
        return this.triggersAnd.size() + this.triggersOr.size() + this.triggersOrNot.size() + this.triggersAndNot.size() > 0 && this.options.size() > 0;
    }
}
