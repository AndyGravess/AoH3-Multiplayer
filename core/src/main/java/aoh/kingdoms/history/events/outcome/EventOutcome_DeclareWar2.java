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

public class EventOutcome_DeclareWar2 extends EventOutcome
{
    public String byCivTAG;
    public String onCivTag;
    
    public EventOutcome_DeclareWar2(final String byCivTAG, final String onCivTag) {
        this.byCivTAG = byCivTAG;
        this.onCivTag = onCivTag;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int byCivID = Game.getCivID(this.byCivTAG);
            final int onCivID = Game.getCivID(this.onCivTag);
            if (byCivID > 0 && onCivID > 0 && byCivID != onCivID && Game.getCiv(byCivID).getNumOfProvinces() > 0 && Game.getCiv(onCivID).getNumOfProvinces() > 0) {
                DiplomacyManager.declareWar(byCivID, onCivID, true, new ArrayList<Integer>());
                if (onCivID == Game.player.iCivID) {
                    InGame_Info.iCivID = onCivID;
                    InGame_Info.iCivID2 = byCivID;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(byCivID).getCivName() + " - " + Game.getCiv(onCivID).getCivName());
                    InGame_Info.imgID = Images.infoWar;
                    Game.soundsManager.loadNextMusicWar();
                    Game.menuManager.rebuildInGame_Wars();
                }
                else if (byCivID == Game.player.iCivID) {
                    InGame_Info.iCivID = byCivID;
                    InGame_Info.iCivID2 = onCivID;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(byCivID).getCivName() + " - " + Game.getCiv(onCivID).getCivName());
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
        final int tID = Game.getCivID(this.byCivTAG);
        if (tID > 0) {
            return Game.getCiv(tID).getCivName() + ": " + Game.lang.get("DeclareWarOn") + ": ";
        }
        return Game.lang.get("DeclareWarOn") + ": ";
    }
    
    @Override
    public String getStringRight() {
        final int tID = Game.getCivID(this.onCivTag);
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
