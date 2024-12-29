// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.war;

public class WarCivilization
{
    public int iCivID;
    public int iCasualties;
    
    public WarCivilization() {
        this.iCivID = 0;
        this.iCasualties = 0;
    }
    
    public WarCivilization(final int nCivID) {
        this.iCivID = 0;
        this.iCasualties = 0;
        this.iCivID = nCivID;
    }
    
    public WarCivilization(final int iCivID, final int iCasualties) {
        this.iCivID = 0;
        this.iCasualties = 0;
        this.iCivID = iCivID;
        this.iCasualties = iCasualties;
    }
}
