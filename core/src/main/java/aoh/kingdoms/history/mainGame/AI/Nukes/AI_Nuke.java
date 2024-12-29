// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Nukes;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Nuke
{
    public static void useNukes(final int civID) {
        if (Game.getCiv(civID).getNukes() > 0) {
            try {
                final List<Integer> possibleProvinces = new ArrayList<Integer>();
                for (int i = 0; i < Game.getCiv(civID).diplomacy.iAtWarSize; ++i) {
                    final Civilization civ = Game.getCiv(Game.getCiv(civID).diplomacy.atWar.get(i));
                    for (int j = 0; j < civ.getNumOfProvinces(); ++j) {
                        if (!Game.getProvince(civ.getProvinceID(j)).isOccupied() && Game.getProvince(civ.getProvinceID(j)).getDevastation() < GameValues.atomic.AI_NUKE_IF_DEVASTATION_BELOW) {
                            possibleProvinces.add(civ.getProvinceID(j));
                        }
                    }
                }
                while (Game.getCiv(civID).getNukes() > 0 && !possibleProvinces.isEmpty()) {
                    int bestID = 0;
                    for (int a = 0; !possibleProvinces.isEmpty() && a < Game.getProvince(possibleProvinces.get(bestID)).getArmySize(); ++a) {
                        if (Game.getProvince(possibleProvinces.get(bestID)).getArmy(a).civID == civID) {
                            possibleProvinces.remove(bestID);
                        }
                    }
                    if (possibleProvinces.isEmpty()) {
                        break;
                    }
                    for (int a = possibleProvinces.size() - 1; a >= 0; --a) {
                        if (Game.getProvince(possibleProvinces.get(bestID)).getPopulationTotal() < Game.getProvince(possibleProvinces.get(a)).getPopulationTotal()) {
                            bestID = a;
                        }
                    }
                    Game.dropAtomicBomb(civID, possibleProvinces.get(bestID));
                    possibleProvinces.remove(bestID);
                }
                possibleProvinces.clear();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
}
