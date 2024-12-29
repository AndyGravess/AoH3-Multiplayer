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

public class EventOutcome_ChangeIdeologyCiv extends EventOutcome
{
    public int value;
    public String civA;
    
    public EventOutcome_ChangeIdeologyCiv(final int value, final String civA) {
        this.value = value;
        this.civA = civA;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (this.value >= 0 && this.value < Game.ideologiesManager.getIdeologiesSize()) {
                final int idA = Game.getCivID(this.civA);
                if (idA > 0 && Game.ideologiesManager.changeGovernmentType(idA, this.value, true)) {
                    final Civilization civ = Game.getCiv(idA);
                    civ.fGold += GameValues.government.CHANGE_GOVERNMENT_COST;
                    final Civilization civ2 = Game.getCiv(idA);
                    civ2.fLegacy += GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY;
                    if (idA == Game.player.iCivID) {
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = 0;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("Government"), Game.ideologiesManager.getIdeology(this.value).Name);
                        InGame_Info.imgID = Images.infoCrown;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        final int idA = Game.getCivID(this.civA);
        if (idA > 0) {
            return Game.getCiv(idA).getCivName() + ", " + Game.lang.get("ChangeIdeology") + ": ";
        }
        return " -- ";
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
