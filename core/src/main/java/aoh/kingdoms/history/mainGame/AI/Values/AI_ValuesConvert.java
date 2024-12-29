// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Values;

public class AI_ValuesConvert
{
    public float BUILD_SCORE_MIN;
    public float SCORE_DISTANCE_FROM_CAPITAL;
    public float SCORE_PER_GROWTH_RATE;
    public float SCORE_PER_ECONOMY;
    
    public AI_ValuesConvert() {
        this.BUILD_SCORE_MIN = 10000.0f;
        this.SCORE_DISTANCE_FROM_CAPITAL = -250.0f;
        this.SCORE_PER_GROWTH_RATE = 0.25f;
        this.SCORE_PER_ECONOMY = 0.25f;
    }
}
