// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import java.util.List;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_DeclareWar extends EventOutcome
{
    public String value;
    
    public EventOutcome_DeclareWar(final String value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int tID = Game.getCivID(this.value);
            if (tID > 0 && iCivID != tID && Game.getCiv(tID).getNumOfProvinces() > 0) {
                DiplomacyManager.declareWar(iCivID, tID, true, new ArrayList<Integer>());
                if (iCivID == Game.player.iCivID) {
                    InGame_Info.iCivID = iCivID;
                    InGame_Info.iCivID2 = tID;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(tID).getCivName());
                    InGame_Info.imgID = Images.infoWar;
                    Game.soundsManager.loadNextMusicWar();
                    Game.menuManager.rebuildInGame_Wars();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("DeclareWarOn") + ": ";
    }
    
    @Override
    public String getStringRight() {
        final int tID = Game.getCivID(this.value);
        if (tID > 0) {
            return Game.getCiv(tID).getCivName();
        }
        return Game.lang.get("None");
    }
    
    @Override
    public int getImage() {
        return Images.war;
    }
}
