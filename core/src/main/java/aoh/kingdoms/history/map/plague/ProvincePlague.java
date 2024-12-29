// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.plague;

public class ProvincePlague
{
    public int id;
    public int sinceTurnID;
    public int deaths;
    public float turnsLeft;
    
    public ProvincePlague() {
        this.id = 0;
        this.sinceTurnID = 0;
        this.deaths = 0;
        this.turnsLeft = 0.0f;
    }
    
    public ProvincePlague(final int iPlagueID_InGame, final int iSinceTurnID, final float iDurationTurnsLeft, final int iDeaths) {
        this.id = 0;
        this.sinceTurnID = 0;
        this.deaths = 0;
        this.turnsLeft = 0.0f;
        this.id = iPlagueID_InGame;
        this.sinceTurnID = iSinceTurnID;
        this.turnsLeft = iDurationTurnsLeft;
        this.deaths = iDeaths;
    }
}
