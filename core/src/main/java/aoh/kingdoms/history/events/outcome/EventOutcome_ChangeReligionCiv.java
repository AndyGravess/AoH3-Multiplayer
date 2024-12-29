// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_ChangeReligionCiv extends EventOutcome
{
    public int value;
    public String civA;
    
    public EventOutcome_ChangeReligionCiv(final int value, final String civA) {
        this.value = value;
        this.civA = civA;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (this.value >= 0 && this.value < Game.religionManager.getReligionsSize()) {
                final int idA = Game.getCivID(this.civA);
                if (idA > 0) {
                    Game.getCiv(idA).setReligionID_UpdateBonuses(this.value);
                    Game.getCiv(idA).updateProvincesIncomeAndExpenses();
                    Game.gameThread.addCivUpdateTotalIncomePerMonth(idA);
                    if (idA == Game.player.iCivID) {
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = 0;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("Religion"), Game.religionManager.getReligion(this.value).Name);
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
            return Game.getCiv(idA).getCivName() + ", " + Game.lang.get("ConvertReligion") + ": ";
        }
        return " -- ";
    }
    
    @Override
    public String getStringRight() {
        if (this.value >= 0 && this.value < Game.religionManager.getReligionsSize()) {
            return Game.religionManager.getReligion(this.value).Name;
        }
        return Game.lang.get("None");
    }
    
    @Override
    public int getImage() {
        return Images.religion;
    }
}
