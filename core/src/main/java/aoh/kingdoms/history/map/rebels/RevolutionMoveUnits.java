// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.rebels;

import aoh.kingdoms.history.mainGame.SaveLoad.SaveGameManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.SiegeManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import aoh.kingdoms.history.map.moveUnits.MoveUnits;
import java.util.List;

public class RevolutionMoveUnits
{
    public List<MoveUnits> lMoveUnits;
    public int iMoveUnitsSize;
    
    public RevolutionMoveUnits() {
        this.lMoveUnits = new ArrayList<MoveUnits>();
    }
    
    public void clearData() {
        this.lMoveUnits.clear();
        this.iMoveUnitsSize = 0;
    }
    
    public final void removeAllRebelsArmiesMovingToCiv(final int civID) {
        for (int i = this.lMoveUnits.size() - 1; i >= 0; --i) {
            if (Game.getProvince(this.lMoveUnits.get(i).getToProvinceLastID()).getCivID() == civID) {
                Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).removeArmy(this.lMoveUnits.get(i).key);
                Game.revolutionManager.removeArmyPosition(this.lMoveUnits.get(i).getFromProvinceID(), this.lMoveUnits.get(i).key);
                this.lMoveUnits.remove(i);
                this.iMoveUnitsSize = this.lMoveUnits.size();
            }
        }
    }
    
    public final void updateMoveInBattle(final String key, final boolean inBattle) {
        try {
            for (int i = 0; i < this.iMoveUnitsSize; ++i) {
                if (this.lMoveUnits.get(i).key.equals(key)) {
                    this.lMoveUnits.get(i).inBattle = inBattle;
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final boolean newMove(final int fromProvinceID, final int toProvinceID, final String key, final int extraArmyY, final boolean retreat) {
        try {
            if (fromProvinceID == toProvinceID) {
                return false;
            }
            SiegeManager.checkForSiege(fromProvinceID);
            if (Game.getProvinceData4(fromProvinceID).isUnderSiege()) {
                final int armyID = Game.getProvince(fromProvinceID).getArmyKeyID(key);
                if (armyID >= 0) {
                    Game.getProvince(fromProvinceID).getArmy(armyID).inMovement = false;
                    this.removeMove(Game.getProvince(fromProvinceID).getArmy(armyID).key);
                }
                return true;
            }
            final int armyID = Game.getProvince(fromProvinceID).getArmyKeyID(key);
            if (armyID >= 0) {
                if (Game.getProvince(fromProvinceID).getArmy(armyID).inBattle && Game.battleManager.isArmyInBattle(fromProvinceID, key)) {
                    return false;
                }
                final int civID = Game.getProvince(fromProvinceID).getArmy(armyID).civID;
                MoveUnits nMoveUnits = new MoveUnits(civID, fromProvinceID, toProvinceID, key, extraArmyY, retreat, false);
                if (nMoveUnits.iRouteSize > 2 && nMoveUnits.haveSeaProvince()) {
                    MoveUnits nMoveUnitsLand = new MoveUnits(civID, fromProvinceID, toProvinceID, key, extraArmyY, retreat, true);
                    if (nMoveUnitsLand.iRouteSize > 1 && nMoveUnitsLand.getWidthTotal() < nMoveUnits.getWidthTotal()) {
                        nMoveUnits = nMoveUnitsLand;
                        nMoveUnitsLand = null;
                    }
                }
                if (nMoveUnits.iRouteSize > 1) {
                    int i = 0;
                    while (i < this.iMoveUnitsSize) {
                        if (this.lMoveUnits.get(i).key.equals(key)) {
                            if (this.lMoveUnits.get(i).inRetreat) {
                                return false;
                            }
                            if (this.lMoveUnits.get(i).getToProvinceID() == nMoveUnits.getToProvinceID()) {
                                nMoveUnits.doneMovementProgressWidth = this.lMoveUnits.get(i).doneMovementProgressWidth;
                                nMoveUnits.movementProgressOverWidth = this.lMoveUnits.get(i).movementProgressOverWidth;
                                nMoveUnits.currentMovementProgressWidth = this.lMoveUnits.get(i).currentMovementProgressWidth;
                                nMoveUnits.lCurrentMovingTime = this.lMoveUnits.get(i).lCurrentMovingTime;
                                nMoveUnits.fCurrentMovingPercentage = this.lMoveUnits.get(i).fCurrentMovingPercentage;
                                this.lMoveUnits.remove(i);
                                this.iMoveUnitsSize = this.lMoveUnits.size();
                                break;
                            }
                            if (this.lMoveUnits.get(i).getProgressPerc() < GameValues.army.MOVE_UNITS_LOCKED_MOVE) {
                                this.lMoveUnits.remove(i);
                                this.iMoveUnitsSize = this.lMoveUnits.size();
                                break;
                            }
                            nMoveUnits = new MoveUnits(civID, this.lMoveUnits.get(i).getToProvinceID(), toProvinceID, key, this.lMoveUnits.get(i).extraArmyY, this.lMoveUnits.get(i).getFromProvinceID());
                            if (nMoveUnits.iRouteSize > 1) {
                                nMoveUnits.doneMovementProgressWidth = this.lMoveUnits.get(i).doneMovementProgressWidth;
                                nMoveUnits.movementProgressOverWidth = this.lMoveUnits.get(i).movementProgressOverWidth;
                                nMoveUnits.currentMovementProgressWidth = this.lMoveUnits.get(i).currentMovementProgressWidth;
                                nMoveUnits.lCurrentMovingTime = this.lMoveUnits.get(i).lCurrentMovingTime;
                                nMoveUnits.fCurrentMovingPercentage = this.lMoveUnits.get(i).fCurrentMovingPercentage;
                                this.lMoveUnits.set(i, nMoveUnits);
                                Game.getProvince(fromProvinceID).getArmy(armyID).inRetreat = retreat;
                                return true;
                            }
                            return false;
                        }
                        else {
                            ++i;
                        }
                    }
                    this.lMoveUnits.add(nMoveUnits);
                    this.iMoveUnitsSize = this.lMoveUnits.size();
                    Game.getProvince(fromProvinceID).getArmy(armyID).inRetreat = retreat;
                    Game.getProvince(fromProvinceID).getArmy(armyID).setInMovement(true);
                    Game.getProvince(fromProvinceID).updateArmyPosY();
                    Game.getProvince(fromProvinceID).updateIsUnderSiege();
                    return true;
                }
                return false;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final void updateMoveUnits_Load(final SaveGameManager.Save_Civ_MoveUnits civMoveUnits, final boolean nInRetreat, final boolean nInBattle) {
        for (int i = this.iMoveUnitsSize - 1; i >= 0; --i) {
            if (this.lMoveUnits.get(i).key.equals(civMoveUnits.k)) {
                this.lMoveUnits.get(i).currentMovementProgressWidth = civMoveUnits.m;
                this.lMoveUnits.get(i).inRetreat = nInRetreat;
                this.lMoveUnits.get(i).inBattle = nInBattle;
                return;
            }
        }
    }
    
    public final boolean removeMove(final String key) {
        for (int i = 0; i < this.iMoveUnitsSize; ++i) {
            if (this.lMoveUnits.get(i).key.equals(key)) {
                final int tID = Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmyKeyID(this.lMoveUnits.get(i).key);
                if (tID >= 0) {
                    Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).setInMovement(false);
                    Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftX_Scaled = 0;
                    Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftY_Scaled = 0;
                }
                this.lMoveUnits.remove(i);
                this.iMoveUnitsSize = this.lMoveUnits.size();
                return true;
            }
        }
        return false;
    }
    
    public final void removeMove(final int i) {
        final int tID = Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmyKeyID(this.lMoveUnits.get(i).key);
        if (tID >= 0) {
            Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).setInMovement(false);
            Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).inRetreat = false;
            Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftX_Scaled = 0;
            Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftY_Scaled = 0;
        }
        this.lMoveUnits.remove(i);
        this.iMoveUnitsSize = this.lMoveUnits.size();
    }
    
    public boolean isArmyAlreadyMoving(final int fromProvinceID, final int toProvinceID) {
        for (int i = 0; i < this.iMoveUnitsSize; ++i) {
            if (this.lMoveUnits.get(i).getFromProvinceID() == fromProvinceID && this.lMoveUnits.get(i).getToProvinceLastID() == toProvinceID) {
                return true;
            }
        }
        return false;
    }
}
