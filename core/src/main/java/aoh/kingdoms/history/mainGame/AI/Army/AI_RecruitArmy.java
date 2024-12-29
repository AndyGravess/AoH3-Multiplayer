// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import java.util.List;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.battles.BattleManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_RecruitArmy
{
    public static final void recruitArmy(final int civID) {
        try {
            final Civilization civ = Game.getCiv(civID);
            if (civ.fManpower >= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) {
                float limitOfRegiments = civ.iRegimentsLimit * civ.aiCiv.regimentsLimit_UseMax_Perc;
                if (Game.getCiv(civID).diplomacy.isAtWar()) {
                    limitOfRegiments *= Game.aiValuesArmies.RECRUIT_ARMY_REGIMENTS_LIMIT_AT_WAR;
                }
                else if (!Game.getCiv(civID).aiCivDiplomacy.w.isEmpty()) {
                    limitOfRegiments *= Game.aiValuesArmies.RECRUIT_ARMY_REGIMENTS_LIMIT_PREPARING_FOR_WAR;
                }
                if (civ.getArmyRegimentSize() + civ.iArmyRecruitSize_Total < limitOfRegiments) {
                    int numOfRegiments = (int)(limitOfRegiments - (civ.getArmyRegimentSize() + civ.iArmyRecruitSize_Total + civ.aiCivCreateNewArmy.createNewArmy_RegimentsLeft()));
                    numOfRegiments *= 5;
                    if (Game.getCiv(civID).iRegiments > 2 && !Game.getCiv(civID).diplomacy.isAtWar() && Game.getCiv(civID).aiCivDiplomacy.w.isEmpty()) {
                        if (civ.getMilitaryLevel() == 0) {
                            return;
                        }
                        if (!civ.unitsBest_FirstLine.isEmpty()) {
                            final float unitMaintenance = ArmyManager.lArmy.get(civ.unitsBest_FirstLine.get(0).unitID).get(civ.unitsBest_FirstLine.get(0).armyID).MaintenanceCost * Game.aiValuesArmies.RECRUIT_ARMY_UNIT_MAINTENANCE_COST_MODIFIER;
                            final float inRecruitmentMaintenance = civ.iArmyRecruitSize_Total * unitMaintenance;
                            final float balanceResearch = civ.getBalance() - inRecruitmentMaintenance - Game.getResearchCost(civID, Math.max(0.0f, TechnologyTree.getMaxResearch(civID) - civ.fResearchPerMonth)) * Game.aiValuesArmies.RECRUIT_ARMY_MIN_BALANCE_LEFT_RESEARCH_COST_MODIFIER;
                            if (balanceResearch - numOfRegiments * unitMaintenance < Game.aiValuesArmies.RECRUIT_ARMY_MIN_BALANCE_LEFT) {
                                if (balanceResearch < Game.aiValuesArmies.RECRUIT_ARMY_MIN_BALANCE_LEFT) {
                                    return;
                                }
                                numOfRegiments = (int)Math.min((float)numOfRegiments, (balanceResearch - Game.aiValuesArmies.RECRUIT_ARMY_MIN_BALANCE_LEFT) / unitMaintenance);
                            }
                            if (civ.fTotalIncomePerMonth * Game.aiValuesArmies.MILITARY_SPENDINGS_PERC_OF_MAX_INCOME < civ.fArmyMaintenance + (numOfRegiments + civ.iArmyRecruitSize_Total) * unitMaintenance) {
                                numOfRegiments = (int)Math.min((float)numOfRegiments, (civ.fTotalIncomePerMonth * Game.aiValuesArmies.MILITARY_SPENDINGS_PERC_OF_MAX_INCOME - civ.fArmyMaintenance - inRecruitmentMaintenance) / unitMaintenance);
                            }
                        }
                    }
                    if (numOfRegiments > 0) {
                        recruitArmy_ExistingArmy(civID, numOfRegiments);
                    }
                }
            }
            if (civ.diplomacy.isAtWar() && civ.fManpower < Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * Game.aiValuesArmies.RECRUIT_MERCENARIES_IF_MANPOWER_BELOW_MODIFIER) {
                AI_RecruitMercenaries.recruitMercenaries(civID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void recruitArmy_ExistingArmy(final int civID, int numOfRegiments) {
        if (numOfRegiments <= 0) {
            return;
        }
        final Civilization civ = Game.getCiv(civID);
        if (civ.iArmyPositionSize > 0) {
            final ArrayList<RecruitArmy_ExistingArmy> possibleRecruit = new ArrayList<RecruitArmy_ExistingArmy>();
            for (int i = 0; i < civ.iArmyPositionSize; ++i) {
                final Province province = Game.getProvince(civ.getArmyPosition(i));
                if (province.getCivID() == civID) {
                    for (int j = 0; j < province.getArmySize(); ++j) {
                        final ArmyDivision army = province.getArmy(j);
                        if (army.civID == civID && !army.inRetreat) {
                            if (!army.inBattle) {
                                possibleRecruit.add(new RecruitArmy_ExistingArmy(civ.getArmyPosition(i), j, army.iArmyRegimentSize));
                            }
                        }
                    }
                }
            }
            final int battleWidth = BattleManager.getBattleWidth(civID);
            final int armyFirst = (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_First);
            final int armySecond = (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_Second);
            final int armyThird = (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_Third);
            final int armyFourth = (int)Math.max(4.0f, battleWidth * 2 * civ.aiCiv.army_Fourth);
            while (numOfRegiments > 0 && possibleRecruit.size() > 0) {
                int bestID = 0;
                int bestNumOfUnitsToRecruit = 0;
                switch (possibleRecruit.get(bestID).provinceID % 4) {
                    case 0: {
                        bestNumOfUnitsToRecruit = Math.max(0, armyFirst - Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).iArmyRegimentSize);
                        break;
                    }
                    case 1: {
                        bestNumOfUnitsToRecruit = Math.max(0, armySecond - Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).iArmyRegimentSize);
                        break;
                    }
                    case 2: {
                        bestNumOfUnitsToRecruit = Math.max(0, armyThird - Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).iArmyRegimentSize);
                        break;
                    }
                    default: {
                        bestNumOfUnitsToRecruit = Math.max(0, armyFourth - Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).iArmyRegimentSize);
                        break;
                    }
                }
                for (int iSize = possibleRecruit.size(), k = 1; k < iSize; ++k) {
                    int tNumOfUnitsToRecruit = 0;
                    switch (possibleRecruit.get(k).provinceID % 4) {
                        case 0: {
                            tNumOfUnitsToRecruit = Math.max(0, armyFirst - Game.getProvince(possibleRecruit.get(k).provinceID).getArmy(possibleRecruit.get(k).armyID).iArmyRegimentSize);
                            break;
                        }
                        case 1: {
                            tNumOfUnitsToRecruit = Math.max(0, armySecond - Game.getProvince(possibleRecruit.get(k).provinceID).getArmy(possibleRecruit.get(k).armyID).iArmyRegimentSize);
                            break;
                        }
                        case 2: {
                            tNumOfUnitsToRecruit = Math.max(0, armyThird - Game.getProvince(possibleRecruit.get(k).provinceID).getArmy(possibleRecruit.get(k).armyID).iArmyRegimentSize);
                            break;
                        }
                        default: {
                            tNumOfUnitsToRecruit = Math.max(0, armyFourth - Game.getProvince(possibleRecruit.get(k).provinceID).getArmy(possibleRecruit.get(k).armyID).iArmyRegimentSize);
                            break;
                        }
                    }
                    if (tNumOfUnitsToRecruit > 0) {
                        if (tNumOfUnitsToRecruit <= bestNumOfUnitsToRecruit) {
                            if (tNumOfUnitsToRecruit != bestNumOfUnitsToRecruit) {
                                continue;
                            }
                            if (Game.oR.nextInt(100) >= 50) {
                                continue;
                            }
                        }
                        bestNumOfUnitsToRecruit = tNumOfUnitsToRecruit;
                        bestID = k;
                    }
                }
                bestNumOfUnitsToRecruit = Math.min(numOfRegiments, bestNumOfUnitsToRecruit);
                if (Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).civID == civID && (bestNumOfUnitsToRecruit = bestNumOfUnitsToRecruit - civ.getRecruitArmyInProgress(Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).key) - civ.aiCivCreateNewArmy.getCreateNewArmy_RegimentsInQueue(Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).key)) > 0) {
                    final AI_Army_Composition armyComposition = new AI_Army_Composition(civID, Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).iArmyRegimentSize + bestNumOfUnitsToRecruit);
                    AI_Army_Composition currentArmy = Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).getArmyComposition();
                    armyComposition.numFirstLine = Math.max(0, armyComposition.numFirstLine - currentArmy.numFirstLine);
                    armyComposition.numFlank = Math.max(0, armyComposition.numFlank - currentArmy.numFlank);
                    armyComposition.numSupport = Math.max(0, armyComposition.numSupport - currentArmy.numSupport);
                    armyComposition.numSiege = Math.max(0, armyComposition.numSiege - currentArmy.numSiege);
                    currentArmy = null;
                    armyComposition.updateNumOfRegiments();
                    numOfRegiments -= armyComposition.numOfRegiments;
                    final List<AI_CreateNewArmy> newArmy = getArmyToRecruit(civID, armyComposition);
                    final String sKey = Game.getProvince(possibleRecruit.get(bestID).provinceID).getArmy(possibleRecruit.get(bestID).armyID).key;
                    civ.aiCivCreateNewArmy.cNA.add(new AI_CreateNewArmy_Task(sKey, newArmy, Game_Calendar.TURN_ID + Game.aiValuesArmies.RECRUIT_ARMY_EXPIRES_DAYS));
                    civ.aiCivCreateNewArmy.runCreateNewArmy_Task(civID, civ.aiCivCreateNewArmy.cNA.size() - 1);
                }
                possibleRecruit.remove(bestID);
            }
            possibleRecruit.clear();
        }
        if (numOfRegiments > 0) {
            recruitArmy_New(civID, numOfRegiments);
        }
    }
    
    public static List<AI_CreateNewArmy> getArmyToRecruit(final int civID, final AI_Army_Composition armyComposition) {
        final ArrayList<AI_CreateNewArmy> newArmy = new ArrayList<AI_CreateNewArmy>();
        final ArrayList<AI_CreateNewArmy> tempArmy = new ArrayList<AI_CreateNewArmy>();
        final Civilization civ = Game.getCiv(civID);
        if (armyComposition.numFirstLine > 0) {
            if (civ.unitsBest_FirstLineSize > 1) {
                for (int i = 0; i < civ.unitsBest_FirstLineSize; ++i) {
                    tempArmy.add(new AI_CreateNewArmy(civ.unitsBest_FirstLine.get(i).unitID, civ.unitsBest_FirstLine.get(i).armyID, 0));
                }
                for (int i = 0; i < armyComposition.numFirstLine; ++i) {
                    final AI_CreateNewArmy a\u0131_CreateNewArmy = tempArmy.get(Game.oR.nextInt(civ.unitsBest_FirstLineSize));
                    ++a\u0131_CreateNewArmy.n;
                }
                for (int i = 0; i < civ.unitsBest_FirstLineSize; ++i) {
                    if (tempArmy.get(i).n > 0) {
                        newArmy.add(tempArmy.get(i));
                    }
                }
                tempArmy.clear();
            }
            else {
                newArmy.add(new AI_CreateNewArmy(civ.unitsBest_FirstLine.get(0).unitID, civ.unitsBest_FirstLine.get(0).armyID, armyComposition.numFirstLine));
            }
        }
        if (armyComposition.numFlank > 0) {
            if (civ.unitsBest_FlankSize > 1) {
                for (int i = 0; i < civ.unitsBest_FlankSize; ++i) {
                    tempArmy.add(new AI_CreateNewArmy(civ.unitsBest_Flank.get(i).unitID, civ.unitsBest_Flank.get(i).armyID, 0));
                }
                for (int i = 0; i < armyComposition.numFlank; ++i) {
                    final AI_CreateNewArmy a\u0131_CreateNewArmy2 = tempArmy.get(Game.oR.nextInt(civ.unitsBest_FlankSize));
                    ++a\u0131_CreateNewArmy2.n;
                }
                for (int i = 0; i < civ.unitsBest_FlankSize; ++i) {
                    if (tempArmy.get(i).n > 0) {
                        newArmy.add(tempArmy.get(i));
                    }
                }
                tempArmy.clear();
            }
            else {
                newArmy.add(new AI_CreateNewArmy(civ.unitsBest_Flank.get(0).unitID, civ.unitsBest_Flank.get(0).armyID, armyComposition.numFlank));
            }
        }
        if (armyComposition.numSupport > 0) {
            if (civ.unitsBest_SupportSize > 1) {
                for (int i = 0; i < civ.unitsBest_SupportSize; ++i) {
                    tempArmy.add(new AI_CreateNewArmy(civ.unitsBest_Support.get(i).unitID, civ.unitsBest_Support.get(i).armyID, 0));
                }
                for (int i = 0; i < armyComposition.numSupport; ++i) {
                    final AI_CreateNewArmy a\u0131_CreateNewArmy3 = tempArmy.get(Game.oR.nextInt(civ.unitsBest_SupportSize));
                    ++a\u0131_CreateNewArmy3.n;
                }
                for (int i = 0; i < civ.unitsBest_SupportSize; ++i) {
                    if (tempArmy.get(i).n > 0) {
                        newArmy.add(tempArmy.get(i));
                    }
                }
                tempArmy.clear();
            }
            else {
                newArmy.add(new AI_CreateNewArmy(civ.unitsBest_Support.get(0).unitID, civ.unitsBest_Support.get(0).armyID, armyComposition.numSupport));
            }
        }
        if (armyComposition.numSiege > 0) {
            if (civ.unitsBest_SiegeSize > 1) {
                for (int i = 0; i < civ.unitsBest_SiegeSize; ++i) {
                    tempArmy.add(new AI_CreateNewArmy(civ.unitsBest_Siege.get(i).unitID, civ.unitsBest_Siege.get(i).armyID, 0));
                }
                for (int i = 0; i < armyComposition.numSiege; ++i) {
                    final AI_CreateNewArmy a\u0131_CreateNewArmy4 = tempArmy.get(Game.oR.nextInt(civ.unitsBest_SiegeSize));
                    ++a\u0131_CreateNewArmy4.n;
                }
                for (int i = 0; i < civ.unitsBest_SiegeSize; ++i) {
                    if (tempArmy.get(i).n > 0) {
                        newArmy.add(tempArmy.get(i));
                    }
                }
                tempArmy.clear();
            }
            else {
                newArmy.add(new AI_CreateNewArmy(civ.unitsBest_Siege.get(0).unitID, civ.unitsBest_Siege.get(0).armyID, armyComposition.numSiege));
            }
        }
        return newArmy;
    }
    
    private static final void recruitArmy_New(final int civID, final int numOfRegiments) {
        buildProvincesRecruitArmyScore(civID);
        recruitArmy_New_In(civID, numOfRegiments, 0);
    }
    
    public static final void recruitArmy_New2(final int civID, final int numOfRegiments, final int PROVINCE) {
        buildProvincesRecruitArmyScore(civID);
        recruitArmy_New_In(civID, numOfRegiments, 0, PROVINCE);
    }
    
    private static final void recruitArmy_New_In(final int civID, int numOfRegiments, int limit) {
        if (numOfRegiments < Game.aiValuesArmies.RECRUIT_ARMY_MIN_NUM_OF_REGIMENTS) {
            return;
        }
        try {
            int iProvinceID = Game.getCiv(civID).getProvinceID(0);
            for (int i = 1; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(civID).getProvinceID(i)).aiRecruitArmyScore > Game.getProvince(iProvinceID).aiRecruitArmyScore) {
                    if (!Game.getProvince(Game.getCiv(civID).getProvinceID(i)).isOccupied() || Game.getProvinceData(iProvinceID).getOccupiedByCivID() <= 0) {
                        iProvinceID = Game.getCiv(civID).getProvinceID(i);
                    }
                }
            }
            if (Game.getProvince(iProvinceID).isOccupied() && Game.getProvinceData(iProvinceID).getOccupiedByCivID() > 0) {
                return;
            }
            int toRecruit = 0;
            switch (iProvinceID % 4) {
                case 0: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_First);
                    break;
                }
                case 1: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_Second);
                    break;
                }
                case 2: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_Third);
                    break;
                }
                default: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_Fourth);
                    break;
                }
            }
            toRecruit = Math.min(numOfRegiments, toRecruit);
            final AI_Army_Composition armyComposition = new AI_Army_Composition(civID, toRecruit);
            final List<AI_CreateNewArmy> newArmy = getArmyToRecruit(civID, armyComposition);
            numOfRegiments -= armyComposition.numOfRegiments;
            final String sKey = CFG.extraRandomTag();
            Game.getCiv(civID).lCreateNewArmy.put(sKey, iProvinceID);
            Game.getCiv(civID).aiCivCreateNewArmy.cNA.add(new AI_CreateNewArmy_Task(sKey, newArmy, Game_Calendar.TURN_ID + Game.aiValuesArmies.RECRUIT_ARMY_EXPIRES_DAYS));
            Game.getCiv(civID).aiCivCreateNewArmy.runCreateNewArmy_Task(civID, Game.getCiv(civID).aiCivCreateNewArmy.cNA.size() - 1);
            final Province province = Game.getProvince(iProvinceID);
            province.aiRecruitArmyScore /= 4;
            if (numOfRegiments >= Game.aiValuesArmies.RECRUIT_ARMY_MIN_NUM_OF_REGIMENTS) {
                recruitArmy_New_In(civID, numOfRegiments, ++limit);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private static final void recruitArmy_New_In(final int civID, int numOfRegiments, int limit, final int provinceID) {
        if (numOfRegiments < Game.aiValuesArmies.RECRUIT_ARMY_MIN_NUM_OF_REGIMENTS) {
            return;
        }
        try {
            int iProvinceID = Game.getCiv(civID).getProvinceID(0);
            iProvinceID = provinceID;
            if (Game.getProvince(iProvinceID).isOccupied() && Game.getProvinceData(iProvinceID).getOccupiedByCivID() > 0) {
                return;
            }
            int toRecruit = 0;
            switch (iProvinceID % 4) {
                case 0: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_First);
                    break;
                }
                case 1: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_Second);
                    break;
                }
                case 2: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_Third);
                    break;
                }
                default: {
                    toRecruit = (int)Math.max(2.0f, BattleManager.getBattleWidth(civID) * 2 * Game.getCiv(civID).aiCiv.army_Fourth);
                    break;
                }
            }
            toRecruit = Math.min(numOfRegiments, toRecruit);
            final AI_Army_Composition armyComposition = new AI_Army_Composition(civID, toRecruit);
            final List<AI_CreateNewArmy> newArmy = getArmyToRecruit(civID, armyComposition);
            numOfRegiments -= armyComposition.numOfRegiments;
            final String sKey = CFG.extraRandomTag();
            Game.getCiv(civID).lCreateNewArmy.put(sKey, iProvinceID);
            Game.getCiv(civID).aiCivCreateNewArmy.cNA.add(new AI_CreateNewArmy_Task(sKey, newArmy, Game_Calendar.TURN_ID + Game.aiValuesArmies.RECRUIT_ARMY_EXPIRES_DAYS));
            Game.getCiv(civID).aiCivCreateNewArmy.runCreateNewArmy_Task(civID, Game.getCiv(civID).aiCivCreateNewArmy.cNA.size() - 1);
            final Province province = Game.getProvince(iProvinceID);
            province.aiRecruitArmyScore /= 4;
            if (numOfRegiments >= Game.aiValuesArmies.RECRUIT_ARMY_MIN_NUM_OF_REGIMENTS) {
                recruitArmy_New_In(civID, numOfRegiments, ++limit);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private static void buildProvincesRecruitArmyScore(final int civID) {
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            final int provinceID = Game.getCiv(civID).getProvinceID(i);
            Game.getProvince(provinceID).aiRecruitArmyScore = (int)(Game.getProvince(provinceID).getGrowthRateWithBonuses() * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_GROWTH_RATE + Game.getProvince(provinceID).getEconomyWithBonuses() * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_ECONOMY + Game.getProvince(provinceID).fProvinceIncome * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_PROVINCE_INCOME + Game.getProvince(provinceID).getArmyRegimentSize_InProvince() * Game.aiValuesArmies.RECRUIT_ARMY_SCORE_PER_REGIMENT + Game.getProvince(provinceID).aiDistanceToCapital * Game.aiValuesArmies.SCORE_REORGANIZE_ARMY_DISTANCE);
        }
    }
    
    public static class RecruitArmy_ExistingArmy
    {
        public int provinceID;
        public int armyID;
        public int regimentsSize;
        
        public RecruitArmy_ExistingArmy(final int provinceID, final int armyID, final int regimentsSize) {
            this.provinceID = provinceID;
            this.armyID = armyID;
            this.regimentsSize = regimentsSize;
        }
    }
}
