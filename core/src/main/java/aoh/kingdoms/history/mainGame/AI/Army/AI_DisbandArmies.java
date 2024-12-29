// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.List;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import java.util.Collection;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_DisbandArmies
{
    public static void updateDisbandArmies() {
        for (int i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_DISBAND_ARMIES; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DISBAND_ARMIES) {
            if (!DesktopLauncher.player.contains(i)) {
                if (Game.getCiv(i).getNumOfProvinces() <= 0) {
                    continue;
                }
                try {
                    updateDisbandArmies(i);
                    continue;
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DISBAND_ARMIES;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    try {
                        updateDisbandArmies(i);
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DISBAND_ARMIES;
            }
        }
    }
    
    public static void updateDisbandArmies(final int civID) {
        final Civilization civ = Game.getCiv(civID);
        if (!civ.diplomacy.isAtWar() && civ.aiCivDiplomacy.w.isEmpty()) {
            if (civ.fTotalIncomePerMonth * Game.aiValuesArmies.MILITARY_SPENDINGS_PERC_OF_MAX_INCOME_DISBAND < civ.fArmyMaintenance) {
                float maintenanceCostToDisband = civ.fArmyMaintenance - civ.fTotalIncomePerMonth * Game.aiValuesArmies.MILITARY_SPENDINGS_PERC_OF_MAX_INCOME_DISBAND;
                civ.aiCivCreateNewArmy.cNA.clear();
                try {
                    civ.cancelRecruitArmy_All();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                for (int i = civ.iArmyPositionSize - 1; i >= 0; --i) {
                    final ArmyDivision armyDivision = Game.getProvince(civ.getArmyPosition(i)).getArmy(civ.getArmyPositionKey(i));
                    if (armyDivision != null) {
                        final ArrayList<ArmyRegiment> possibleToDisband = new ArrayList<ArmyRegiment>();
                        possibleToDisband.addAll(armyDivision.lArmyRegiment);
                        final ArrayList<ArmyRegiment> toDisband = new ArrayList<ArmyRegiment>();
                        while (!possibleToDisband.isEmpty()) {
                            final int disbandID = Game.oR.nextInt(possibleToDisband.size());
                            toDisband.add(possibleToDisband.get(disbandID));
                            maintenanceCostToDisband -= ArmyManager.lArmy.get(possibleToDisband.get(disbandID).uID).get(possibleToDisband.get(disbandID).aID).MaintenanceCost;
                            possibleToDisband.remove(disbandID);
                            if (maintenanceCostToDisband >= 0.0f) {
                                continue;
                            }
                            break;
                        }
                        Game.getProvince(civ.getArmyPosition(i)).disbandRegiment(armyDivision.key, toDisband);
                        possibleToDisband.clear();
                        if (maintenanceCostToDisband < 0.0f) {
                            break;
                        }
                    }
                }
            }
            if (civ.getBalance() < Game.aiValuesArmies.DISBAND_ARMIES_IF_BALANCE_BELOW) {
                final float balance = Game.aiValuesArmies.DISBAND_ARMIES_IF_BALANCE_BELOW;
                if (civ.getMilitaryLevel() != 1) {
                    civ.updateMilitaryLevel(1);
                }
                if (civ.getBalance() >= balance) {
                    return;
                }
                civ.aiCivCreateNewArmy.cNA.clear();
                try {
                    civ.cancelRecruitArmy_All();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                float currentBalance = civ.getBalance();
                for (int j = civ.iArmyPositionSize - 1; j >= 0; --j) {
                    final ArmyDivision armyDivision2 = Game.getProvince(civ.getArmyPosition(j)).getArmy(civ.getArmyPositionKey(j));
                    if (armyDivision2 != null) {
                        final ArrayList<ArmyRegiment> possibleToDisband2 = new ArrayList<ArmyRegiment>();
                        possibleToDisband2.addAll(armyDivision2.lArmyRegiment);
                        final ArrayList<ArmyRegiment> toDisband2 = new ArrayList<ArmyRegiment>();
                        while (!possibleToDisband2.isEmpty()) {
                            final int disbandID2 = Game.oR.nextInt(possibleToDisband2.size());
                            toDisband2.add(possibleToDisband2.get(disbandID2));
                            currentBalance += ArmyManager.lArmy.get(possibleToDisband2.get(disbandID2).uID).get(possibleToDisband2.get(disbandID2).aID).MaintenanceCost;
                            possibleToDisband2.remove(disbandID2);
                            if (currentBalance <= balance) {
                                continue;
                            }
                            Game.getProvince(civ.getArmyPosition(j)).disbandRegiment(armyDivision2.key, toDisband2);
                            possibleToDisband2.clear();
                            return;
                        }
                        Game.getProvince(civ.getArmyPosition(j)).disbandRegiment(armyDivision2.key, toDisband2);
                        possibleToDisband2.clear();
                        if (currentBalance > balance) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
