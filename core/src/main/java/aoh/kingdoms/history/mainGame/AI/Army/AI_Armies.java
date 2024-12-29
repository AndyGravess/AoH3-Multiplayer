// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.army.ArmyPosition;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Armies
{
    public static final void regroupPeace(final int civID) {
        try {
            if (!Game.getCiv(civID).diplomacy.isAtWar() && Game.getCiv(civID).getNumOfProvinces() > 0) {
                final List<ArmyPosition> toMove = new ArrayList<ArmyPosition>();
                for (int i = 0; i < Game.getCiv(civID).iArmyPositionSize; ++i) {
                    if (Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getCivID() != civID) {
                        final ArmyDivision armyDivision = Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getArmy(Game.getCiv(civID).getArmyPositionKey(i));
                        if (armyDivision != null && !armyDivision.inBattle && !armyDivision.inRetreat) {
                            toMove.add(new ArmyPosition(Game.getCiv(civID).getArmyPosition(i), Game.getCiv(civID).getArmyPositionKey(i)));
                        }
                    }
                }
                for (int i = toMove.size() - 1; i >= 0; --i) {
                    int bestID = Game.getCiv(civID).getProvinceID(0);
                    float bestDistance = Game.getDistanceFromProvinceToProvince(toMove.get(i).provinceID, Game.getCiv(civID).getProvinceID(0));
                    for (int j = 1; j < Game.getCiv(civID).getNumOfProvinces(); ++j) {
                        if (bestDistance > Game.getDistanceFromProvinceToProvince(toMove.get(i).provinceID, Game.getCiv(civID).getProvinceID(j))) {
                            bestID = Game.getCiv(civID).getProvinceID(j);
                            bestDistance = Game.getDistanceFromProvinceToProvince(toMove.get(i).provinceID, Game.getCiv(civID).getProvinceID(j));
                        }
                    }
                    Game.getCiv(civID).newMove(toMove.get(i).provinceID, bestID, toMove.get(i).key, 0, false);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
