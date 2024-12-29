// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.AI.Diplomacy.AI_PrepareForWar_Data;
import java.util.List;

public class AI_CivDiplomacy
{
    public List<AI_PrepareForWar_Data> w;
    public List<AI_PrepareForWar_Data> p;
    
    public AI_CivDiplomacy() {
        this.w = new ArrayList<AI_PrepareForWar_Data>();
        this.p = new ArrayList<AI_PrepareForWar_Data>();
    }
    
    public boolean addPrepareForWar(final int onCivID) {
        for (int i = this.w.size() - 1; i >= 0; --i) {
            if (this.w.get(i).c == onCivID) {
                return false;
            }
        }
        this.w.add(new AI_PrepareForWar_Data(onCivID, Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_TURNS_MIN + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_TURNS_RANDOM)));
        return true;
    }
    
    public boolean isPreparingForWarWithCivID(final int onCivID) {
        for (int i = this.w.size() - 1; i >= 0; --i) {
            if (this.w.get(i).c == onCivID) {
                return true;
            }
        }
        return false;
    }
    
    public void clearPrepareForWar() {
        this.w.clear();
    }
    
    public boolean addPrepareForAlliance(final int onCivID) {
        for (int i = this.p.size() - 1; i >= 0; --i) {
            if (this.p.get(i).c == onCivID) {
                return false;
            }
        }
        this.p.add(new AI_PrepareForWar_Data(onCivID, Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_FIND_ALLY_EXPIRE));
        return true;
    }
    
    public void removePreparingForAllianceWithCivID(final int onCivID) {
        for (int i = this.p.size() - 1; i >= 0; --i) {
            if (this.p.get(i).c == onCivID) {
                this.p.remove(i);
                return;
            }
        }
    }
    
    public boolean isPreparingForAllianceWithCivID(final int onCivID) {
        for (int i = this.p.size() - 1; i >= 0; --i) {
            if (this.p.get(i).c == onCivID) {
                return true;
            }
        }
        return false;
    }
    
    public void clearPrepareForAlliance() {
        this.p.clear();
    }
}
