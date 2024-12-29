// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class EventOutcome_AddArmy extends EventOutcome
{
    public List<Integer> unitID;
    public List<Integer> armyID;
    
    public EventOutcome_AddArmy(final List<Integer> unitID, final List<Integer> armyID) {
        this.unitID = new ArrayList<Integer>();
        this.armyID = new ArrayList<Integer>();
        this.unitID = unitID;
        this.armyID = armyID;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (Game.getCiv(iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getCivID() == iCivID && this.armyID.size() > 0 && this.unitID.size() > 0) {
                final List<ArmyRegiment> armyRegiments = new ArrayList<ArmyRegiment>();
                for (int i = Math.min(this.armyID.size(), this.unitID.size()) - 1; i >= 0; --i) {
                    armyRegiments.add(new ArmyRegiment(this.unitID.get(i), this.armyID.get(i)));
                }
                final ArmyDivision armyDivision = new ArmyDivision(iCivID, Game.getCiv(iCivID).getCapitalProvinceID(), armyRegiments);
                Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).addArmy(armyDivision);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        if (Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getCivID() == Game.player.iCivID) {
            return Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getProvinceName() + ", " + Game.lang.get("Army") + ": ";
        }
        return Game.lang.get("Army") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return "" + CFG.getNumberWithSpaces("" + this.unitID.size() * Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE);
    }
    
    @Override
    public int getImage() {
        return Game_Calendar.IMG_MANPOWER;
    }
}
