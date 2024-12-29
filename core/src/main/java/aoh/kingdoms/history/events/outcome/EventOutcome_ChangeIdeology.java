// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_ChangeIdeology extends EventOutcome
{
    public int value;
    
    public EventOutcome_ChangeIdeology(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (this.value >= 0 && this.value < Game.ideologiesManager.getIdeologiesSize() && Game.ideologiesManager.changeGovernmentType(iCivID, this.value, true)) {
                final Civilization civ = Game.getCiv(iCivID);
                civ.fGold += GameValues.government.CHANGE_GOVERNMENT_COST;
                final Civilization civ2 = Game.getCiv(iCivID);
                civ2.fLegacy += GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY;
                if (iCivID == Game.player.iCivID) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("Government"), Game.ideologiesManager.getIdeology(this.value).Name);
                    InGame_Info.imgID = Images.infoCrown;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("ChangeIdeology") + ": ";
    }
    
    @Override
    public String getStringRight() {
        if (this.value >= 0 && this.value < Game.ideologiesManager.getIdeologiesSize()) {
            return Game.ideologiesManager.getIdeology(this.value).Name;
        }
        return Game.lang.get("None");
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
