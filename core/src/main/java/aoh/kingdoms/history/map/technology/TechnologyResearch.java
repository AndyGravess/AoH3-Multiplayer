// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.technology;

public class TechnologyResearch
{
    public int iTechID;
    public float fProgress;
    
    public TechnologyResearch(final int iTechID) {
        this.fProgress = 0.0f;
        this.iTechID = iTechID;
    }
    
    public TechnologyResearch(final int iTechID, final float fProgress) {
        this.fProgress = 0.0f;
        this.iTechID = iTechID;
        this.fProgress = fProgress;
    }
}
