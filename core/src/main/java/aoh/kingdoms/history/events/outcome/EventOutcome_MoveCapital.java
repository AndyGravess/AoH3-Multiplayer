// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_MoveCapital extends EventOutcome
{
    public int value;
    
    public EventOutcome_MoveCapital(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (this.value >= 0 && this.value < Game.getProvincesSize() && Game.getProvince(this.value).getCivID() == iCivID) {
                Game.getCiv(iCivID).setCapitalProvinceID(this.value);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("MoveCapitalTo") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return (this.value >= 0 && this.value < Game.getProvincesSize() && Game.getProvince(this.value).getCivID() == Game.player.iCivID) ? Game.getProvince(this.value).getProvinceName() : Game.lang.get("MaintainStatusQuo");
    }
    
    @Override
    public int getImage() {
        return Images.capital;
    }
}
