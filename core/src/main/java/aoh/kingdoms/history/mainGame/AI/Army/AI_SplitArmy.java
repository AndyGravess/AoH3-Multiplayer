// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.army.ArmyManager;
import java.util.List;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_SplitArmy
{
    public static String splitArmy(final AI_Army_Composition newAmyComp, final int provinceID, final String armyKey) {
        final ArmyDivision armyDivision = Game.getProvince(provinceID).getArmy(armyKey);
        if (armyDivision != null) {
            final ArmyDivision newArmy = new ArmyDivision(armyDivision.civID, provinceID, new ArrayList<ArmyRegiment>());
            if (newAmyComp.numFirstLine > 0) {
                for (int i = armyDivision.iArmyRegimentSize - 1; i >= 0; --i) {
                    if (ArmyManager.lUnitsTypes.get(armyDivision.lArmyRegiment.get(i).uID).Line == 0) {
                        newArmy.addRegiment(armyDivision.lArmyRegiment.get(i));
                        armyDivision.removeRegiment(i);
                        --newAmyComp.numFirstLine;
                        if (newAmyComp.numFirstLine <= 0) {
                            break;
                        }
                    }
                }
            }
            if (newAmyComp.numFlank > 0) {
                for (int i = armyDivision.iArmyRegimentSize - 1; i >= 0; --i) {
                    if (ArmyManager.lUnitsTypes.get(armyDivision.lArmyRegiment.get(i).uID).Line == 1) {
                        newArmy.addRegiment(armyDivision.lArmyRegiment.get(i));
                        armyDivision.removeRegiment(i);
                        --newAmyComp.numFlank;
                        if (newAmyComp.numFlank <= 0) {
                            break;
                        }
                    }
                }
            }
            if (newAmyComp.numSupport > 0) {
                for (int i = armyDivision.iArmyRegimentSize - 1; i >= 0; --i) {
                    if (ArmyManager.lUnitsTypes.get(armyDivision.lArmyRegiment.get(i).uID).Line == 2 && !ArmyManager.lArmy.get(armyDivision.lArmyRegiment.get(i).uID).get(armyDivision.lArmyRegiment.get(i).aID).SiegeUnit && !ArmyManager.lArmy.get(armyDivision.lArmyRegiment.get(i).uID).get(armyDivision.lArmyRegiment.get(i).aID).isSettler) {
                        newArmy.addRegiment(armyDivision.lArmyRegiment.get(i));
                        armyDivision.removeRegiment(i);
                        --newAmyComp.numSupport;
                        if (newAmyComp.numSupport <= 0) {
                            break;
                        }
                    }
                }
            }
            if (newAmyComp.numSiege > 0) {
                for (int i = armyDivision.iArmyRegimentSize - 1; i >= 0; --i) {
                    if (ArmyManager.lUnitsTypes.get(armyDivision.lArmyRegiment.get(i).uID).Line == 2 && ArmyManager.lArmy.get(armyDivision.lArmyRegiment.get(i).uID).get(armyDivision.lArmyRegiment.get(i).aID).SiegeUnit && !ArmyManager.lArmy.get(armyDivision.lArmyRegiment.get(i).uID).get(armyDivision.lArmyRegiment.get(i).aID).isSettler) {
                        newArmy.addRegiment(armyDivision.lArmyRegiment.get(i));
                        armyDivision.removeRegiment(i);
                        --newAmyComp.numSiege;
                        if (newAmyComp.numSiege <= 0) {
                            break;
                        }
                    }
                }
            }
            if (newArmy.iArmyRegimentSize > 0) {
                final int armyID = Game.getProvince(provinceID).getArmyKeyID(armyKey);
                if (armyID >= 0) {
                    Game.getProvince(provinceID).updateRegiment(armyID, armyDivision.lArmyRegiment);
                    return Game.getProvince(provinceID).addArmy(new ArmyDivision(armyDivision.civID, provinceID, newArmy.lArmyRegiment));
                }
            }
        }
        return null;
    }
}
