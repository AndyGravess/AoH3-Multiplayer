// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

public class AI_Civ
{
    public float army_First;
    public float army_Second;
    public float army_Third;
    public float army_Fourth;
    public float regimentsLimit_UseMax_Perc;
    public float armies_FirstLine_Perc;
    public float armies_FlankLine_Perc;
    public int armies_SiegeWeaponPerSupportUnits;
    public int armies_SiegeWeapon_Max;
    
    public AI_Civ() {
        this.army_First = 1.1f;
        this.army_Second = 0.9f;
        this.army_Third = 0.75f;
        this.army_Fourth = 0.75f;
        this.regimentsLimit_UseMax_Perc = 1.25f;
        this.armies_FirstLine_Perc = 0.8f;
        this.armies_FlankLine_Perc = 0.2f;
        this.armies_SiegeWeaponPerSupportUnits = 10;
        this.armies_SiegeWeapon_Max = 1;
        this.buildPersonality();
    }
    
    public final void buildPersonality() {
    }
}
