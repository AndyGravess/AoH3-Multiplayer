// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.CharactersManager;

public class EventOutcome_AddGeneral_CharacterAttackDefense extends EventOutcome
{
    public String sGeneral;
    public int Attack;
    public int Defense;
    
    public EventOutcome_AddGeneral_CharacterAttackDefense(final String sGeneral, final int Attack, final int Defense) {
        this.sGeneral = sGeneral;
        this.Attack = Attack;
        this.Defense = Defense;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            CharactersManager.loadGeneral(iCivID, this.sGeneral, this.Attack, this.Defense);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("RecruitGeneral") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return "" + this.sGeneral;
    }
    
    @Override
    public int getImage() {
        return Images.general;
    }
}
