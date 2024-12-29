// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_Infrastructure_All extends EventOutcome
{
    public int value;
    
    public EventOutcome_Province_Infrastructure_All(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).setInfrastructure(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getInfrastructure() + this.value);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Infrastructure") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0) ? "+" : "") + CFG.getPrecision2((float)this.value, 100);
    }
    
    @Override
    public String getStringRight2(final int bonus_duration) {
        return Game.lang.get("AllProvinces");
    }
    
    @Override
    public int getImage() {
        return Images.infrastructure;
    }
}
