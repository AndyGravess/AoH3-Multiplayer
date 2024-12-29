// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_IsCiv extends EventTrigger_Value
{
    public String value;
    
    public EventTrigger_IsCiv(final String value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).getCivTag().equals(this.value) || Game.getCiv(iCivID).realTag.equals(this.value);
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Civilization") + " == ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.lang.getCiv(this.value);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.getCiv(Game.player.iCivID).getCivName() + "]";
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
