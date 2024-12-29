// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_AnnexCivilization extends EventOutcome
{
    public String annexCivTag;
    
    public EventOutcome_AnnexCivilization(final String annexCivTag) {
        this.annexCivTag = "";
        this.annexCivTag = annexCivTag;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        final int annexCivID = Game.getCivID(this.annexCivTag);
        if (annexCivID != iCivID && annexCivID > 0) {
            for (int i = Game.getCiv(annexCivID).getNumOfProvinces() - 1; i >= 0; --i) {
                try {
                    final int pID = Game.getCiv(annexCivID).getProvinceID(i);
                    Game.getProvince(pID).setCivID(iCivID);
                    Game.getProvince(pID).removeArmyCivID(annexCivID);
                    Game.getProvince(pID).addCore(iCivID);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Annexation") + ": ";
    }
    
    @Override
    public String getStringRight() {
        final int annexCivID = Game.getCivID(this.annexCivTag);
        if (annexCivID > 0 && Game.getCiv(annexCivID).getNumOfProvinces() > 0) {
            return Game.getCiv(annexCivID).getCivName();
        }
        return Game.lang.get("None");
    }
    
    @Override
    public int getImage() {
        return Images.provinces;
    }
}
