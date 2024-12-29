// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;

public class ArmyRegiment
{
    public String key;
    public int uID;
    public int aID;
    public int num;
    public float mo;
    
    public ArmyRegiment() {
        this.uID = 0;
        this.aID = 0;
        this.num = 0;
        this.mo = 1.0f;
    }
    
    public ArmyRegiment(final int nUnitTypeID, final int nArmyID) {
        this.uID = 0;
        this.aID = 0;
        this.num = 0;
        this.mo = 1.0f;
        this.uID = nUnitTypeID;
        this.aID = nArmyID;
        this.num = Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
        this.key = CFG.extraRandomTag();
    }
    
    public ArmyRegiment(final int nUnitTypeID, final int nArmyID, final String key, final float mo, final int num) {
        this.uID = 0;
        this.aID = 0;
        this.num = 0;
        this.mo = 1.0f;
        this.uID = nUnitTypeID;
        this.aID = nArmyID;
        this.num = num;
        this.key = key;
        this.mo = mo;
    }
    
    public ArmyRegiment(final SaveGameManager.Save_Provinces_ArmyRegiment armyRegiment) {
        this.uID = 0;
        this.aID = 0;
        this.num = 0;
        this.mo = 1.0f;
        this.key = armyRegiment.k;
        this.uID = armyRegiment.u;
        this.aID = armyRegiment.a;
        this.num = armyRegiment.n;
        this.mo = armyRegiment.m;
    }
    
    @Override
    public String toString() {
        return "ArmyRegiment{key='" + this.key + '\'' + ", uID=" + this.uID + ", aID=" + this.aID + ", num=" + this.num + ", mo=" + this.mo + '}';
    }
}
