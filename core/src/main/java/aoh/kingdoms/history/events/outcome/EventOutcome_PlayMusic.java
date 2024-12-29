// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_PlayMusic extends EventOutcome
{
    public String fileName;
    
    public EventOutcome_PlayMusic(final String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.soundsManager.loadNextMusic(this.fileName);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Audio") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return this.fileName;
    }
    
    @Override
    public String getStringRight2(final int bonus_duration) {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.settings;
    }
}
