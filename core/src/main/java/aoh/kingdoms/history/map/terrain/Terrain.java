// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.terrain;

public class Terrain
{
    public String Name;
    public String[] ImageFile;
    public float[] Color;
    public float MovementSpeed;
    public int Defense;
    public int PopulationGrowth;
    public float BuildCost;
    public float IncreaseGrowthRateCost;
    public int BasePopulation;
    public int BaseEconomy;
    public int BattleOver;
    public int Ambience;
    
    public Terrain() {
        this.IncreaseGrowthRateCost = 1.0f;
    }
}
