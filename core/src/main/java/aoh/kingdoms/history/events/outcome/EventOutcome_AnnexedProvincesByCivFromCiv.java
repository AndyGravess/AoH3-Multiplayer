// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.List;

public class EventOutcome_AnnexedProvincesByCivFromCiv extends EventOutcome
{
    public String sFromCivTAG;
    public String byCivTAG;
    public List<Integer> lProvinces;
    
    public EventOutcome_AnnexedProvincesByCivFromCiv(final String byCivTAG, final String sFromCivTAG, final List<Integer> nProvinces) {
        this.byCivTAG = byCivTAG;
        this.sFromCivTAG = sFromCivTAG;
        this.lProvinces = nProvinces;
        for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
            if (this.lProvinces.get(i) < 0 || this.lProvinces.get(i) >= Game.getProvincesSize()) {
                this.lProvinces.remove(i);
            }
        }
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int annexByCivID = Game.getCivID(this.byCivTAG);
            final int annexFromCivID = Game.getCivID(this.sFromCivTAG);
            if (annexByCivID > 0 && annexFromCivID > 0) {
                for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
                    if (Game.getProvince(this.lProvinces.get(i)).getCivID() == annexFromCivID) {
                        Game.getProvince(this.lProvinces.get(i)).setCivID(annexByCivID);
                        Game.getProvince(this.lProvinces.get(i)).addCore(annexByCivID);
                    }
                }
                for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
                    if (Game.getProvince(this.lProvinces.get(i)).getCivID() == annexByCivID) {
                        for (int j = Game.getProvince(this.lProvinces.get(i)).getArmySize() - 1; j >= 0; --j) {
                            if (!DiplomacyManager.isAlly(annexByCivID, Game.getProvince(this.lProvinces.get(i)).getArmy(j).civID)) {
                                final ArmyDivision tArmy = Game.getProvince(this.lProvinces.get(i)).getArmy(j);
                                Game.getProvince(this.lProvinces.get(i)).removeArmy(j);
                                if (tArmy.civID > 0 && Game.getCiv(tArmy.civID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(tArmy.civID).getCapitalProvinceID()).getCivID() == tArmy.civID) {
                                    Game.getProvince(Game.getCiv(tArmy.civID).getCapitalProvinceID()).addArmy(tArmy);
                                }
                            }
                        }
                    }
                }
                Game.gameThread.addCivUpdateTotalIncomePerMonth(annexByCivID);
                Game.gameThread.addCivUpdateTotalIncomePerMonth(annexFromCivID);
                if (annexByCivID == Game.player.iCivID) {
                    InGame_Info.iCivID = annexByCivID;
                    InGame_Info.iCivID2 = annexFromCivID;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("AcceptedOurUltimatum"), Game.getCiv(annexByCivID).getCivName() + " - " + Game.getCiv(annexFromCivID).getCivName());
                    InGame_Info.imgID = Images.infoDiplomacy;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.getCiv(this.byCivTAG) + ", " + Game.lang.get("Annexation") + ": ";
    }
    
    @Override
    public String getStringRight() {
        String out = "";
        final int annexCivID = Game.getCivID(this.sFromCivTAG);
        for (int i = 0; i < this.lProvinces.size(); ++i) {
            if (this.lProvinces.get(i) >= 0 && this.lProvinces.get(i) < Game.getProvincesSize() && Game.getProvince(this.lProvinces.get(i)).getCivID() == annexCivID) {
                out = out + Game.getProvince(this.lProvinces.get(i)).getProvinceName() + ((i == this.lProvinces.size() - 1) ? "" : ", ");
            }
        }
        if (out.length() > 0) {
            return out;
        }
        return Game.lang.get("None");
    }
    
    @Override
    public int getImage() {
        return Images.provinces;
    }
}
