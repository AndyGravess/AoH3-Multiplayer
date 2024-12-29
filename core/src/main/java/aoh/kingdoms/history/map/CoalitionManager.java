// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.List;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class CoalitionManager
{
    public static final void updateCreateCoalition() {
        for (int i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_COALITION; i < Game.getCivsSize(); i += GameValues.gameUpdate.GAME_UPDATE_COALITION) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    updateCreateCoalition(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
    }
    
    public static final void updateCreateCoalition(final int civID) {
        if (Game.getCiv(civID).getAggressiveExpansion() > GameValues.aggressiveExpansion.START_COALITION_IF_AE_OVER) {
            if (civID == Game.player.iCivID) {
                if (Game.difficultyID >= GameValues.difficulty.NORMAL_ID) {
                    createCoalition(civID);
                }
            }
            else {
                createCoalition(civID);
            }
        }
    }
    
    public static void createCoalition(final int againstCivID) {
        final List<Integer> possibleCivs = new ArrayList<Integer>();
        final List<Point_XY> militaryAccess = new ArrayList<Point_XY>();
        final Civilization civ = Game.getCiv(againstCivID);
        int numOfRegiments = 0;
        for (int i = 0; i < civ.civNeighbors.civsSize; ++i) {
            final int neighbor = civ.civNeighbors.civs.get(i).civID;
            if (!DiplomacyManager.isAlly(againstCivID, neighbor) && !Game.getCiv(againstCivID).diplomacy.haveDefensivePact(neighbor) && !DiplomacyManager.isAtWar(againstCivID, neighbor) && !possibleCivs.contains(neighbor)) {
                possibleCivs.add(neighbor);
                numOfRegiments += Game.getCiv(neighbor).iRegiments;
            }
        }
        if (!possibleCivs.isEmpty()) {
            final List<Integer> alliesDefenders = DiplomacyManager.declareWar_AlliesDefender(againstCivID, possibleCivs.get(0));
            int regimentsB = Game.getCiv(againstCivID).iRegiments;
            try {
                for (int j = alliesDefenders.size() - 1; j >= 0; --j) {
                    regimentsB += Game.getCiv(alliesDefenders.get(j)).getArmyRegimentSize();
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            if (numOfRegiments < regimentsB * GameValues.aggressiveExpansion.COALITION_ARMY_OVER_PERC) {
                for (int j = 0; j < civ.civNeighbors.civsSize; ++j) {
                    for (int neighbor2 = civ.civNeighbors.civs.get(j).civID, k = 0; k < Game.getCiv(neighbor2).civNeighbors.civsSize; ++k) {
                        if (!DiplomacyManager.isAlly(againstCivID, Game.getCiv(neighbor2).civNeighbors.civs.get(k).civID) && !Game.getCiv(againstCivID).diplomacy.haveDefensivePact(Game.getCiv(neighbor2).civNeighbors.civs.get(k).civID) && !DiplomacyManager.isAtWar(againstCivID, Game.getCiv(neighbor2).civNeighbors.civs.get(k).civID) && againstCivID != Game.getCiv(neighbor2).civNeighbors.civs.get(k).civID && !possibleCivs.contains(Game.getCiv(neighbor2).civNeighbors.civs.get(k))) {
                            possibleCivs.add(Game.getCiv(neighbor2).civNeighbors.civs.get(k).civID);
                            numOfRegiments += Game.getCiv(Game.getCiv(neighbor2).civNeighbors.civs.get(k).civID).iRegiments;
                            militaryAccess.add(new Point_XY(Game.getCiv(neighbor2).civNeighbors.civs.get(k).civID, neighbor2));
                            if (numOfRegiments > regimentsB * GameValues.aggressiveExpansion.COALITION_ARMY_OVER_PERC) {
                                j = civ.civNeighbors.civsSize;
                                break;
                            }
                        }
                    }
                }
            }
            if (numOfRegiments > regimentsB) {
                int bestID = 0;
                if (possibleCivs.get(bestID) == Game.player.iCivID) {
                    if (possibleCivs.size() == 1) {
                        return;
                    }
                    bestID = 1;
                }
                for (int l = 1; l < possibleCivs.size(); ++l) {
                    if (Game.getCiv(possibleCivs.get(bestID)).iRegiments < Game.getCiv(possibleCivs.get(l)).iRegiments && possibleCivs.get(l) != Game.player.iCivID) {
                        bestID = l;
                    }
                }
                if (DiplomacyManager.declareWar(possibleCivs.get(bestID), againstCivID, false, possibleCivs, true)) {
                    Game.getCiv(againstCivID).setAggressiveExpansion(Game.getCiv(againstCivID).getAggressiveExpansion() * GameValues.aggressiveExpansion.COALITION_STARTED_REDUCE_AGGRESSIVE_EXPANSION);
                    for (int l = militaryAccess.size() - 1; l >= 0; --l) {
                        DiplomacyManager.addMilitaryAccess(militaryAccess.get(l).getPosX(), militaryAccess.get(l).getPosY());
                    }
                }
            }
        }
    }
}
