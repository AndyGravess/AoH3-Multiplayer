// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class ArmyInvasion
{
    public String armyKey;
    public List<Integer> lProvinces;
    
    public ArmyInvasion() {
        this.lProvinces = new ArrayList<Integer>();
    }
    
    public ArmyInvasion(final int civID, final String nKey, final List<Integer> nProvinces) {
        this.lProvinces = new ArrayList<Integer>();
        this.armyKey = nKey;
        this.lProvinces = new ArrayList<Integer>(nProvinces);
        this.invasionMoveArmy(civID);
    }
    
    public final boolean invasionMoveArmy(final int civID) {
        try {
            final Game.ArmyPos armyPos = Game.findArmy_FullCheck(civID, this.armyKey);
            if (armyPos == null) {
                return false;
            }
            for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
                if (!Game.getProvince(this.lProvinces.get(i)).isOccupied() || Game.getCiv(Game.getProvince(this.lProvinces.get(i)).getCivID()).getPuppetOfCivID() != Game.player.iCivID) {
                    if (Game.getProvince(this.lProvinces.get(i)).getCivID() == civID) {
                        this.lProvinces.remove(i);
                    }
                    else if (!DiplomacyManager.isAtWar(civID, Game.getProvince(this.lProvinces.get(i)).getCivID())) {
                        this.lProvinces.remove(i);
                    }
                    else if (this.lProvinces.get(i) == armyPos.iProvinceID) {
                        this.lProvinces.remove(i);
                    }
                    else if (Game.getProvince(this.lProvinces.get(i)).isOccupied()) {
                        this.lProvinces.remove(i);
                    }
                }
            }
            if (this.lProvinces.size() == 0) {
                return false;
            }
            int bestID = 0;
            float distanceBest = Game.getDistanceFromProvinceToProvince(armyPos.iProvinceID, this.lProvinces.get(0));
            for (int j = 1, iSize = this.lProvinces.size(); j < iSize; ++j) {
                final float tempDistance = Game.getDistanceFromProvinceToProvince(armyPos.iProvinceID, this.lProvinces.get(j));
                if (distanceBest > tempDistance) {
                    bestID = j;
                    distanceBest = tempDistance;
                }
            }
            bestID = this.lProvinces.get(bestID);
            if (Game.getProvince(bestID).getFortLevel() == 0) {
                int bestScore = 0;
                final List<Integer> extraID = new ArrayList<Integer>();
                final List<Integer> extraScore = new ArrayList<Integer>();
                for (int k = 0; k < Game.getProvince(bestID).getNeighboringProvincesSize(); ++k) {
                    if (this.lProvinces.contains(Game.getProvince(bestID).getNeighboringProvinces(k))) {
                        ++bestScore;
                        extraID.add(Game.getProvince(bestID).getNeighboringProvinces(k));
                        int tExtraScore = 0;
                        for (int l = 0; l < Game.getProvince(Game.getProvince(bestID).getNeighboringProvinces(k)).getNeighboringProvincesSize(); ++l) {
                            if (this.lProvinces.contains(Game.getProvince(Game.getProvince(bestID).getNeighboringProvinces(k)).getNeighboringProvinces(l))) {
                                ++tExtraScore;
                            }
                        }
                        extraScore.add(tExtraScore);
                    }
                }
                for (int k = extraID.size() - 1; k >= 0; --k) {
                    if (extraScore.get(k) > bestScore) {
                        bestID = extraID.get(k);
                    }
                }
                extraID.clear();
                extraScore.clear();
            }
            Game.getCiv(civID).newMove(armyPos.iProvinceID, bestID, this.armyKey, 0, false);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
        return true;
    }
}
