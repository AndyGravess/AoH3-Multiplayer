// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.jakowski.AI.Diplomacy.AI_PrepareForWar_Data;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import java.util.Collection;
import aoc.kingdoms.lukasz.map.battles.BattleManager;
import aoc.kingdoms.lukasz.map.army.ArmyPosition;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_ReorganizeArmies
{
    public static void reorganizeArmies_AtPeace(final int civID) {
        try {
            final Civilization civ = Game.getCiv(civID);
            civ.aiMerge.aiMergeTasks.clear();
            final List<ArmyPosition> armiesInOwnProvinces = new ArrayList<ArmyPosition>();
            final List<ArmyPosition> armiesInAnotherTerritory = new ArrayList<ArmyPosition>();
            for (int i = 0; i < civ.iArmyPositionSize; ++i) {
                final ArmyDivision armyDivision = Game.getProvince(civ.getArmyPosition(i)).getArmy(civ.getArmyPositionKey(i));
                if (armyDivision != null && armyDivision.civID == civID && !armyDivision.inBattle && !armyDivision.inRetreat) {
                    if (Game.getProvince(civ.getArmyPosition(i)).getCivID() == civID) {
                        armiesInOwnProvinces.add(new ArmyPosition(civ.getArmyPosition(i), armyDivision.key));
                    }
                    else {
                        armiesInAnotherTerritory.add(new ArmyPosition(civ.getArmyPosition(i), armyDivision.key));
                    }
                    if (armyDivision.inMovement && !civ.isInMoveUnits_ArmyKey(armyDivision.key)) {
                        armyDivision.inMovement = false;
                        armyDivision.inRetreat = false;
                    }
                }
            }
            if (civ.aiCivDiplomacy.w.isEmpty()) {
                buildProvincesScore(civID);
                final Province province = Game.getProvince(civ.getCapitalProvinceID());
                province.aiArmyScore += Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_CAPITAL;
            }
            else {
                buildProvincesScore_PrepareForWar(civID);
                final Province province2 = Game.getProvince(civ.getCapitalProvinceID());
                province2.aiArmyScore += Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_PREPARE_FOR_WAR_CAPITAL;
            }
            final int battleWidth = BattleManager.getBattleWidth(civID);
            final AI_Army_Composition armyFirst = new AI_Army_Composition(civID, (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_First));
            final AI_Army_Composition armySecond = new AI_Army_Composition(civID, (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_Second));
            final AI_Army_Composition armyThird = new AI_Army_Composition(civID, (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_Third));
            final AI_Army_Composition armyFourth = new AI_Army_Composition(civID, (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_Fourth));
            final List<Integer> civProvinces = new ArrayList<Integer>(civ.getProvinces());
            final List<Integer> sortedProvinces = new ArrayList<Integer>();
            int limitOfProvinces = (int)(Math.ceil(civ.getArmyRegimentSize() / (float)armyFirst.numOfRegiments) + 1.0);
            while (civProvinces.size() > 0 && limitOfProvinces-- > 0) {
                int bestID = 0;
                for (int j = civProvinces.size() - 1; j > 0; --j) {
                    if (Game.getProvince(civProvinces.get(j)).aiArmyScore > Game.getProvince(civProvinces.get(bestID)).aiArmyScore) {
                        bestID = j;
                    }
                }
                sortedProvinces.add(civProvinces.get(bestID));
                civProvinces.remove(bestID);
            }
            civProvinces.clear();
            int sortedID = 0;
            int limit = 100;
            while (!sortedProvinces.isEmpty() && (!armiesInOwnProvinces.isEmpty() || !armiesInAnotherTerritory.isEmpty()) && limit-- > 0) {
                final int provinceID = sortedProvinces.get(sortedID);
                String armyKey = null;
                AI_Army_Composition armyCompositionToCreate = null;
                switch (provinceID % 4) {
                    case 0: {
                        armyCompositionToCreate = new AI_Army_Composition(armyFirst);
                        break;
                    }
                    case 1: {
                        armyCompositionToCreate = new AI_Army_Composition(armySecond);
                        break;
                    }
                    case 2: {
                        armyCompositionToCreate = new AI_Army_Composition(armyThird);
                        break;
                    }
                    default: {
                        armyCompositionToCreate = new AI_Army_Composition(armyFourth);
                        break;
                    }
                }
                for (int k = armiesInOwnProvinces.size() - 1; k >= 0; --k) {
                    if (armiesInOwnProvinces.get(k).provinceID == provinceID) {
                        final ArmyDivision armyDivision2 = Game.getProvince(provinceID).getArmy(armiesInOwnProvinces.get(k).key);
                        if (armyDivision2 != null) {
                            if (armyKey == null) {
                                armyKey = armyDivision2.key;
                                Game.getCiv(civID).aiMerge.addMerge(armyDivision2.provinceID, armyDivision2.key);
                            }
                            final AI_Army_Composition currentArmyComposition = armyDivision2.getArmyComposition();
                            if (armyKey.equals(armyDivision2.key)) {
                                int tempNum = Math.min(armyCompositionToCreate.numFirstLine, currentArmyComposition.numFirstLine);
                                final AI_Army_Composition a\u0131_Army_Composition = armyCompositionToCreate;
                                a\u0131_Army_Composition.numFirstLine -= tempNum;
                                final AI_Army_Composition a\u0131_Army_Composition2 = currentArmyComposition;
                                a\u0131_Army_Composition2.numFirstLine -= tempNum;
                                tempNum = Math.min(armyCompositionToCreate.numFlank, currentArmyComposition.numFlank);
                                final AI_Army_Composition a\u0131_Army_Composition3 = armyCompositionToCreate;
                                a\u0131_Army_Composition3.numFlank -= tempNum;
                                final AI_Army_Composition a\u0131_Army_Composition4 = currentArmyComposition;
                                a\u0131_Army_Composition4.numFlank -= tempNum;
                                tempNum = Math.min(armyCompositionToCreate.numSupport, currentArmyComposition.numSupport);
                                final AI_Army_Composition a\u0131_Army_Composition5 = armyCompositionToCreate;
                                a\u0131_Army_Composition5.numSupport -= tempNum;
                                final AI_Army_Composition a\u0131_Army_Composition6 = currentArmyComposition;
                                a\u0131_Army_Composition6.numSupport -= tempNum;
                                tempNum = Math.min(armyCompositionToCreate.numSiege, currentArmyComposition.numSiege);
                                final AI_Army_Composition a\u0131_Army_Composition7 = armyCompositionToCreate;
                                a\u0131_Army_Composition7.numSiege -= tempNum;
                                final AI_Army_Composition a\u0131_Army_Composition8 = currentArmyComposition;
                                a\u0131_Army_Composition8.numSiege -= tempNum;
                                armyCompositionToCreate.updateNumOfRegiments();
                                currentArmyComposition.updateNumOfRegiments();
                                final AI_Army_Composition armyCompositionSplit = new AI_Army_Composition();
                                if (currentArmyComposition.numFirstLine > Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX) {
                                    tempNum = currentArmyComposition.numFirstLine - Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX;
                                    final AI_Army_Composition a\u0131_Army_Composition9 = currentArmyComposition;
                                    a\u0131_Army_Composition9.numFirstLine -= tempNum;
                                    armyCompositionSplit.numFirstLine = tempNum;
                                }
                                if (currentArmyComposition.numFlank > Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX) {
                                    tempNum = currentArmyComposition.numFlank - Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX;
                                    final AI_Army_Composition a\u0131_Army_Composition10 = currentArmyComposition;
                                    a\u0131_Army_Composition10.numFlank -= tempNum;
                                    armyCompositionSplit.numFlank = tempNum;
                                }
                                if (currentArmyComposition.numSupport > Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX) {
                                    tempNum = currentArmyComposition.numSupport - Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX;
                                    final AI_Army_Composition a\u0131_Army_Composition11 = currentArmyComposition;
                                    a\u0131_Army_Composition11.numSupport -= tempNum;
                                    armyCompositionSplit.numSupport = tempNum;
                                }
                                if (currentArmyComposition.numSiege > Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX) {
                                    tempNum = currentArmyComposition.numSiege - Game.aiValuesArmies.REORGANIZE_MAX_NUMBER_OF_REGIMENTS_OVER_MAX;
                                    final AI_Army_Composition a\u0131_Army_Composition12 = currentArmyComposition;
                                    a\u0131_Army_Composition12.numSiege -= tempNum;
                                    armyCompositionSplit.numSiege = tempNum;
                                }
                                currentArmyComposition.updateNumOfRegiments();
                                armyCompositionSplit.updateNumOfRegiments();
                                if (armyCompositionSplit.numOfRegiments > 0) {
                                    final int splitProvinceID = armyDivision2.provinceID;
                                    final String splitKey = AI_SplitArmy.splitArmy(armyCompositionSplit, armyDivision2.provinceID, armyDivision2.key);
                                    if (splitKey != null) {
                                        armiesInOwnProvinces.add(new ArmyPosition(splitProvinceID, splitKey));
                                    }
                                    Game.getProvince(provinceID).updateArmyPosY();
                                }
                                armiesInOwnProvinces.remove(k);
                            }
                            else {
                                final ArmyDivision armyDivision_ToCreate = Game.getProvince(provinceID).getArmy(armyKey);
                                boolean updateArmies = false;
                                if (armyDivision_ToCreate != null) {
                                    if (armyCompositionToCreate.numFirstLine > 0 && currentArmyComposition.numFirstLine > 0) {
                                        for (int a = armyDivision2.iArmyRegimentSize - 1; a >= 0; --a) {
                                            if (ArmyManager.lUnitsTypes.get(armyDivision2.lArmyRegiment.get(a).uID).Line == 0) {
                                                armyDivision_ToCreate.addRegiment(armyDivision2.lArmyRegiment.get(a));
                                                armyDivision2.removeRegiment(a);
                                                final AI_Army_Composition a\u0131_Army_Composition13 = armyCompositionToCreate;
                                                --a\u0131_Army_Composition13.numFirstLine;
                                                updateArmies = true;
                                                if (armyCompositionToCreate.numFirstLine <= 0) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (armyCompositionToCreate.numFlank > 0 && currentArmyComposition.numFlank > 0) {
                                        for (int a = armyDivision2.iArmyRegimentSize - 1; a >= 0; --a) {
                                            if (ArmyManager.lUnitsTypes.get(armyDivision2.lArmyRegiment.get(a).uID).Line == 1) {
                                                armyDivision_ToCreate.addRegiment(armyDivision2.lArmyRegiment.get(a));
                                                armyDivision2.removeRegiment(a);
                                                final AI_Army_Composition a\u0131_Army_Composition14 = armyCompositionToCreate;
                                                --a\u0131_Army_Composition14.numFlank;
                                                updateArmies = true;
                                                if (armyCompositionToCreate.numFlank <= 0) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (armyCompositionToCreate.numSupport > 0 && currentArmyComposition.numSupport > 0) {
                                        for (int a = armyDivision2.iArmyRegimentSize - 1; a >= 0; --a) {
                                            if (ArmyManager.lUnitsTypes.get(armyDivision2.lArmyRegiment.get(a).uID).Line == 2 && !ArmyManager.lArmy.get(armyDivision2.lArmyRegiment.get(a).uID).get(armyDivision2.lArmyRegiment.get(a).aID).SiegeUnit && !ArmyManager.lArmy.get(armyDivision2.lArmyRegiment.get(a).uID).get(armyDivision2.lArmyRegiment.get(a).aID).isSettler) {
                                                armyDivision_ToCreate.addRegiment(armyDivision2.lArmyRegiment.get(a));
                                                armyDivision2.removeRegiment(a);
                                                final AI_Army_Composition a\u0131_Army_Composition15 = armyCompositionToCreate;
                                                --a\u0131_Army_Composition15.numSupport;
                                                updateArmies = true;
                                                if (armyCompositionToCreate.numSupport <= 0) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (armyCompositionToCreate.numSiege > 0 && currentArmyComposition.numSiege > 0) {
                                        for (int a = armyDivision2.iArmyRegimentSize - 1; a >= 0; --a) {
                                            if (ArmyManager.lUnitsTypes.get(armyDivision2.lArmyRegiment.get(a).uID).Line == 2 && ArmyManager.lArmy.get(armyDivision2.lArmyRegiment.get(a).uID).get(armyDivision2.lArmyRegiment.get(a).aID).SiegeUnit && !ArmyManager.lArmy.get(armyDivision2.lArmyRegiment.get(a).uID).get(armyDivision2.lArmyRegiment.get(a).aID).isSettler) {
                                                armyDivision_ToCreate.addRegiment(armyDivision2.lArmyRegiment.get(a));
                                                armyDivision2.removeRegiment(a);
                                                final AI_Army_Composition a\u0131_Army_Composition16 = armyCompositionToCreate;
                                                --a\u0131_Army_Composition16.numSiege;
                                                updateArmies = true;
                                                if (armyCompositionToCreate.numSiege <= 0) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (updateArmies) {
                                        final int armyID = Game.getProvince(provinceID).getArmyKeyID(armyDivision2.key);
                                        final int armyID_ToCreate = Game.getProvince(provinceID).getArmyKeyID(armyDivision_ToCreate.key);
                                        if (armyID >= 0 && armyID_ToCreate >= 0) {
                                            if (armyDivision2.iArmyRegimentSize <= 0) {
                                                Game.getProvince(provinceID).updateRegiment(armyID_ToCreate, armyDivision_ToCreate.lArmyRegiment);
                                                Game.getProvince(provinceID).removeArmy(armyDivision2.key);
                                                armiesInOwnProvinces.remove(k);
                                            }
                                            else {
                                                Game.getProvince(provinceID).updateRegiment(armyID, armyDivision2.lArmyRegiment);
                                                Game.getProvince(provinceID).updateRegiment(armyID_ToCreate, armyDivision_ToCreate.lArmyRegiment);
                                            }
                                            Game.getProvince(provinceID).updateArmyPosY();
                                        }
                                    }
                                }
                            }
                        }
                        if (armyCompositionToCreate.numOfRegiments <= 0) {
                            break;
                        }
                    }
                }
                if (armyCompositionToCreate.numOfRegiments > 0) {
                    for (int k = armiesInAnotherTerritory.size() - 1; k >= 0; --k) {
                        final ArmyDivision armyDivision2 = Game.getProvince(armiesInAnotherTerritory.get(k).provinceID).getArmy(armiesInAnotherTerritory.get(k).key);
                        if (armyDivision2 != null) {
                            final AI_Army_Composition currentArmyComposition = armyDivision2.getArmyComposition();
                            final AI_Army_Composition armyCompositionSplit2 = new AI_Army_Composition();
                            if (armyCompositionToCreate.numFirstLine > 0 && currentArmyComposition.numFirstLine > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numFirstLine, currentArmyComposition.numFirstLine);
                                final AI_Army_Composition a\u0131_Army_Composition17 = currentArmyComposition;
                                a\u0131_Army_Composition17.numFirstLine -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition18 = armyCompositionToCreate;
                                a\u0131_Army_Composition18.numFirstLine -= tempNum2;
                                armyCompositionSplit2.numFirstLine = tempNum2;
                            }
                            if (armyCompositionToCreate.numFlank > 0 && currentArmyComposition.numFlank > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numFlank, currentArmyComposition.numFlank);
                                final AI_Army_Composition a\u0131_Army_Composition19 = currentArmyComposition;
                                a\u0131_Army_Composition19.numFlank -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition20 = armyCompositionToCreate;
                                a\u0131_Army_Composition20.numFlank -= tempNum2;
                                armyCompositionSplit2.numFlank = tempNum2;
                            }
                            if (armyCompositionToCreate.numSupport > 0 && currentArmyComposition.numSupport > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numSupport, currentArmyComposition.numSupport);
                                final AI_Army_Composition a\u0131_Army_Composition21 = currentArmyComposition;
                                a\u0131_Army_Composition21.numSupport -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition22 = armyCompositionToCreate;
                                a\u0131_Army_Composition22.numSupport -= tempNum2;
                                armyCompositionSplit2.numSupport = tempNum2;
                            }
                            if (armyCompositionToCreate.numSiege > 0 && currentArmyComposition.numSiege > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numSiege, currentArmyComposition.numSiege);
                                final AI_Army_Composition a\u0131_Army_Composition23 = currentArmyComposition;
                                a\u0131_Army_Composition23.numSiege -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition24 = armyCompositionToCreate;
                                a\u0131_Army_Composition24.numSiege -= tempNum2;
                                armyCompositionSplit2.numSiege = tempNum2;
                            }
                            currentArmyComposition.updateNumOfRegiments();
                            armyCompositionSplit2.updateNumOfRegiments();
                            armyCompositionToCreate.updateNumOfRegiments();
                            if (armyCompositionSplit2.numOfRegiments > 0) {
                                if (currentArmyComposition.numOfRegiments == 0) {
                                    if (civ.newMove(armiesInAnotherTerritory.get(k).provinceID, provinceID, armiesInAnotherTerritory.get(k).key, 0, false)) {
                                        if (armyKey == null) {
                                            armyKey = armiesInAnotherTerritory.get(k).key;
                                            civ.aiMerge.addMerge(provinceID, armyKey);
                                        }
                                        else {
                                            civ.aiMerge.addMerge(provinceID, armiesInAnotherTerritory.get(k).key);
                                        }
                                    }
                                    else {
                                        final ArmyDivision extraRegroup = Game.getProvince(armiesInAnotherTerritory.get(k).provinceID).getArmy(armiesInAnotherTerritory.get(k).key);
                                        if (extraRegroup != null) {
                                            extraRegroup.inBattle = false;
                                            extraRegroup.inRetreat = false;
                                            Game.getProvince(armiesInAnotherTerritory.get(k).provinceID).removeArmy(armiesInAnotherTerritory.get(k).key);
                                            Game.getProvince(provinceID).addArmy(extraRegroup);
                                            if (armyKey == null) {
                                                armyKey = armiesInAnotherTerritory.get(k).key;
                                                civ.aiMerge.addMerge(provinceID, armyKey);
                                            }
                                            else {
                                                civ.aiMerge.addMerge(provinceID, armiesInAnotherTerritory.get(k).key);
                                                civ.aiMerge.checkMerge(provinceID, armyKey);
                                            }
                                        }
                                    }
                                    armiesInAnotherTerritory.remove(k);
                                }
                                else {
                                    final int splitProvinceID = armyDivision2.provinceID;
                                    final String splitKey = AI_SplitArmy.splitArmy(armyCompositionSplit2, armyDivision2.provinceID, armyDivision2.key);
                                    Game.getProvince(splitProvinceID).updateArmyPosY();
                                    if (splitKey != null) {
                                        if (civ.newMove(splitProvinceID, provinceID, splitKey, 0, false)) {
                                            if (armyKey == null) {
                                                armyKey = splitKey;
                                                civ.aiMerge.addMerge(provinceID, armyKey);
                                            }
                                            else {
                                                civ.aiMerge.addMerge(provinceID, splitKey);
                                            }
                                        }
                                        else {
                                            final ArmyDivision extraRegroup2 = Game.getProvince(armiesInAnotherTerritory.get(k).provinceID).getArmy(armiesInAnotherTerritory.get(k).key);
                                            if (extraRegroup2 != null) {
                                                extraRegroup2.inBattle = false;
                                                extraRegroup2.inRetreat = false;
                                                Game.getProvince(armiesInAnotherTerritory.get(k).provinceID).removeArmy(armiesInAnotherTerritory.get(k).key);
                                                Game.getProvince(provinceID).addArmy(extraRegroup2);
                                                if (armyKey == null) {
                                                    armyKey = armiesInAnotherTerritory.get(k).key;
                                                    civ.aiMerge.addMerge(provinceID, armyKey);
                                                }
                                                else {
                                                    civ.aiMerge.addMerge(provinceID, armiesInAnotherTerritory.get(k).key);
                                                    civ.aiMerge.checkMerge(provinceID, armyKey);
                                                }
                                            }
                                        }
                                    }
                                    if (Game.getProvince(armiesInAnotherTerritory.get(k).provinceID).getArmy(armiesInAnotherTerritory.get(k).key) == null) {
                                        armiesInAnotherTerritory.remove(k);
                                    }
                                }
                            }
                        }
                        if (armyCompositionToCreate.numOfRegiments <= 0) {
                            break;
                        }
                    }
                }
                if (armyCompositionToCreate.numOfRegiments > 0) {
                    for (int k = armiesInOwnProvinces.size() - 1; k >= 0; --k) {
                        final ArmyDivision armyDivision2 = Game.getProvince(armiesInOwnProvinces.get(k).provinceID).getArmy(armiesInOwnProvinces.get(k).key);
                        if (armyDivision2 != null) {
                            final AI_Army_Composition currentArmyComposition = armyDivision2.getArmyComposition();
                            final AI_Army_Composition armyCompositionSplit2 = new AI_Army_Composition();
                            if (armyCompositionToCreate.numFirstLine > 0 && currentArmyComposition.numFirstLine > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numFirstLine, currentArmyComposition.numFirstLine);
                                final AI_Army_Composition a\u0131_Army_Composition25 = currentArmyComposition;
                                a\u0131_Army_Composition25.numFirstLine -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition26 = armyCompositionToCreate;
                                a\u0131_Army_Composition26.numFirstLine -= tempNum2;
                                armyCompositionSplit2.numFirstLine = tempNum2;
                            }
                            if (armyCompositionToCreate.numFlank > 0 && currentArmyComposition.numFlank > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numFlank, currentArmyComposition.numFlank);
                                final AI_Army_Composition a\u0131_Army_Composition27 = currentArmyComposition;
                                a\u0131_Army_Composition27.numFlank -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition28 = armyCompositionToCreate;
                                a\u0131_Army_Composition28.numFlank -= tempNum2;
                                armyCompositionSplit2.numFlank = tempNum2;
                            }
                            if (armyCompositionToCreate.numSupport > 0 && currentArmyComposition.numSupport > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numSupport, currentArmyComposition.numSupport);
                                final AI_Army_Composition a\u0131_Army_Composition29 = currentArmyComposition;
                                a\u0131_Army_Composition29.numSupport -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition30 = armyCompositionToCreate;
                                a\u0131_Army_Composition30.numSupport -= tempNum2;
                                armyCompositionSplit2.numSupport = tempNum2;
                            }
                            if (armyCompositionToCreate.numSiege > 0 && currentArmyComposition.numSiege > 0) {
                                final int tempNum2 = Math.min(armyCompositionToCreate.numSiege, currentArmyComposition.numSiege);
                                final AI_Army_Composition a\u0131_Army_Composition31 = currentArmyComposition;
                                a\u0131_Army_Composition31.numSiege -= tempNum2;
                                final AI_Army_Composition a\u0131_Army_Composition32 = armyCompositionToCreate;
                                a\u0131_Army_Composition32.numSiege -= tempNum2;
                                armyCompositionSplit2.numSiege = tempNum2;
                            }
                            currentArmyComposition.updateNumOfRegiments();
                            armyCompositionSplit2.updateNumOfRegiments();
                            armyCompositionToCreate.updateNumOfRegiments();
                            if (armyCompositionSplit2.numOfRegiments > 0) {
                                if (currentArmyComposition.numOfRegiments == 0) {
                                    if (civ.newMove(armiesInOwnProvinces.get(k).provinceID, provinceID, armiesInOwnProvinces.get(k).key, 0, false)) {
                                        if (armyKey == null) {
                                            armyKey = armiesInOwnProvinces.get(k).key;
                                            civ.aiMerge.addMerge(provinceID, armyKey);
                                        }
                                        else {
                                            civ.aiMerge.addMerge(provinceID, armiesInOwnProvinces.get(k).key);
                                        }
                                    }
                                    else {
                                        final ArmyDivision extraRegroup = Game.getProvince(armiesInOwnProvinces.get(k).provinceID).getArmy(armiesInOwnProvinces.get(k).key);
                                        if (extraRegroup != null) {
                                            extraRegroup.inBattle = false;
                                            extraRegroup.inRetreat = false;
                                            Game.getProvince(armiesInOwnProvinces.get(k).provinceID).removeArmy(armiesInOwnProvinces.get(k).key);
                                            Game.getProvince(provinceID).addArmy(extraRegroup);
                                            if (armyKey == null) {
                                                armyKey = armiesInOwnProvinces.get(k).key;
                                                civ.aiMerge.addMerge(provinceID, armyKey);
                                            }
                                            else {
                                                civ.aiMerge.addMerge(provinceID, armiesInOwnProvinces.get(k).key);
                                                civ.aiMerge.checkMerge(provinceID, armyKey);
                                            }
                                        }
                                    }
                                    armiesInOwnProvinces.remove(k);
                                }
                                else {
                                    final int splitProvinceID = armyDivision2.provinceID;
                                    final String splitKey = AI_SplitArmy.splitArmy(armyCompositionSplit2, armyDivision2.provinceID, armyDivision2.key);
                                    if (splitKey != null) {
                                        if (civ.newMove(splitProvinceID, provinceID, splitKey, 0, false)) {
                                            if (armyKey == null) {
                                                armyKey = splitKey;
                                                civ.aiMerge.addMerge(provinceID, armyKey);
                                            }
                                            else {
                                                civ.aiMerge.addMerge(provinceID, splitKey);
                                            }
                                        }
                                        else {
                                            final ArmyDivision extraRegroup2 = Game.getProvince(armiesInOwnProvinces.get(k).provinceID).getArmy(armiesInOwnProvinces.get(k).key);
                                            if (extraRegroup2 != null) {
                                                extraRegroup2.inBattle = false;
                                                extraRegroup2.inRetreat = false;
                                                Game.getProvince(armiesInOwnProvinces.get(k).provinceID).removeArmy(armiesInOwnProvinces.get(k).key);
                                                Game.getProvince(provinceID).addArmy(extraRegroup2);
                                                if (armyKey == null) {
                                                    armyKey = armiesInOwnProvinces.get(k).key;
                                                    civ.aiMerge.addMerge(provinceID, armyKey);
                                                }
                                                else {
                                                    civ.aiMerge.addMerge(provinceID, armiesInOwnProvinces.get(k).key);
                                                    civ.aiMerge.checkMerge(provinceID, armyKey);
                                                }
                                            }
                                        }
                                    }
                                    if (Game.getProvince(armiesInOwnProvinces.get(k).provinceID).getArmy(armiesInOwnProvinces.get(k).key) == null) {
                                        armiesInOwnProvinces.remove(k);
                                    }
                                }
                            }
                        }
                        if (armyCompositionToCreate.numOfRegiments <= 0) {
                            break;
                        }
                    }
                }
                if (++sortedID >= sortedProvinces.size()) {
                    sortedID = 0;
                }
            }
            try {
                for (int l = civ.iArmyPositionSize - 1; l >= 0; --l) {
                    final ArmyDivision armyDivision3 = Game.getProvince(civ.getArmyPosition(l)).getArmy(civ.getArmyPositionKey(l));
                    if (armyDivision3 != null && !armyDivision3.inMovement && !armyDivision3.inBattle && !armyDivision3.inRetreat && armyDivision3.iArmyRegimentSize < Game.aiValuesArmies.REORGANIZE_MERGE_ALL_IN_PROVINCE_WITH_REGIMENTS_BELOW) {
                        AI_MoveAtWar.mergeArmy_InProvince(civID, armyDivision3.provinceID, armyDivision3.key);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            armiesInOwnProvinces.clear();
            armiesInAnotherTerritory.clear();
            sortedProvinces.clear();
            civProvinces.clear();
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static final void buildProvincesScore(final int civID) {
        final Civilization civ = Game.getCiv(civID);
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            final Province province = Game.getProvince(civ.getProvinceID(i));
            province.aiArmyScore = province.getGrowthRateWithBonuses() * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_GROWTH_RATE + province.getEconomyWithBonuses() * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_ECONOMY + province.fProvinceIncome * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_PROVINCE_INCOME + province.aiDistanceToCapital * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_DISTANCE;
        }
    }
    
    public static final void buildProvincesScore_PrepareForWar(final int civID) {
        final Civilization civ = Game.getCiv(civID);
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            final Province province = Game.getProvince(civ.getProvinceID(i));
            province.aiArmyScore = (borderWithPreparingForWar(civ, province) ? Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_PREPARE_FOR_WAR_BORDERS_WITH_CIV : 1.0f) * (province.fProvinceIncome * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_PREPARE_FOR_WAR_PROVINCE_INCOME + province.aiDistanceToCapital * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_PREPARE_FOR_WAR_DISTANCE);
        }
    }
    
    public static boolean borderWithPreparingForWar(final Civilization civ, final Province province) {
        for (int j = 0; j < civ.aiCivDiplomacy.w.size(); ++j) {
            for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
                if (province.getCivID() == civ.aiCivDiplomacy.w.get(j).c) {
                    return true;
                }
            }
        }
        return false;
    }
}
