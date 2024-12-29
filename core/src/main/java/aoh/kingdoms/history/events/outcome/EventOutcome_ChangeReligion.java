// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_ChangeReligion extends EventOutcome
{
    public int value;
    
    public EventOutcome_ChangeReligion(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (this.value >= 0 && this.value < Game.religionManager.getReligionsSize()) {
                Game.getCiv(iCivID).setReligionID_UpdateBonuses(this.value);
                Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
                if (iCivID == Game.player.iCivID) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("Religion"), Game.religionManager.getReligion(this.value).Name);
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
        return Game.lang.get("ConvertReligion") + ": ";
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
