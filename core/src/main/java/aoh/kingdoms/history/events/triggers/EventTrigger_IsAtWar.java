// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_IsAtWar extends EventTrigger_Value
{
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).diplomacy.isAtWar();
    }
    
    @Override
    public String getText() {
        return Game.lang.get("IaAtWar");
    }
    
    @Override
    public String getText2() {
        return "";
    }
    
    @Override
    public String getText3() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.war;
    }
}
