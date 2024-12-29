// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.battles;

import aoc.kingdoms.lukasz.map.army.ArmyRegiment;

public class BattleRegiment
{
    public ArmyRegiment aR;
    public int c;
    public String aK;
    public int rn;
    public int ca;
    public int re;
    public int cL;
    public int rL;
    public int d;
    public byte f;
    public byte l;
    
    public BattleRegiment() {
        this.cL = 0;
        this.rL = 0;
        this.d = 0;
        this.f = 0;
        this.l = 0;
    }
    
    public BattleRegiment(final int iCivID, final ArmyRegiment armyRegiment, final String sArmyDivisionKey) {
        this.cL = 0;
        this.rL = 0;
        this.d = 0;
        this.f = 0;
        this.l = 0;
        this.c = iCivID;
        this.aR = armyRegiment;
        this.aK = sArmyDivisionKey;
    }
}
