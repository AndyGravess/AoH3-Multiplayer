// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_Infrastructure_ID extends EventOutcome
{
    public int provID;
    public int value;
    
    public EventOutcome_Province_Infrastructure_ID(final int provID, final int value) {
        this.provID = provID;
        this.value = value;
    }
    
    @Override
    public void updateProvince(final int iProvinceID) {
        try {
            Game.getProvince(this.provID).setInfrastructure(Game.getProvince(this.provID).getInfrastructure() + this.value);
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
        try {
            return Game.getProvince(this.provID).getProvinceName();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return null;
        }
    }
    
    @Override
    public int getImage() {
        return Images.infrastructure;
    }
}
