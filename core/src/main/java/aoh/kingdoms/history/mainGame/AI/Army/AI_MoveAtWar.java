// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessage;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageDemandMilitaryAccess;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.moveUnits.other.MoveUnits_AI;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData;
import java.util.Iterator;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.map.war.WarCivilization;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.map.war.War;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import java.util.List;

public class AI_MoveAtWar
{
    public static List<ArmyDivision> armies;
    public static List<ArmyDivision> armiesInMove;
    public static List<ArmyDivision> armiesSiege;
    public static List<ArmyDivision> armiesLowMoraleManpower;
    public static List<ArmyDivision> armiesToMerge;
    public static List<EnemyArmy> enemyArmies;
    public static float warScore;
    public static List<Integer> possibleProvinces;
    public static List<Integer> possibleProvincesDefend;
    
    public static void moveAtWar(final int civID) {
        try {
            AI_MoveAtWar.armies.clear();
            AI_MoveAtWar.armiesInMove.clear();
            AI_MoveAtWar.armiesLowMoraleManpower.clear();
            AI_MoveAtWar.armiesToMerge.clear();
            AI_MoveAtWar.armiesSiege.clear();
            AI_MoveAtWar.enemyArmies.clear();
            AI_MoveAtWar.possibleProvinces.clear();
            AI_MoveAtWar.possibleProvincesDefend.clear();
            AI_MoveAtWar.warScore = 1000.0f;
            final Civilization civ = Game.getCiv(civID);
            if (civ.aiUpdateID++ > 14) {
                civ.aiUpdateID = 0;
                AI_Generals.recruitGenerals(civID);
                processArmyPositions(civ);
            }
            else {
                processArmyPositions_Just(civ);
            }
            boolean buildEnemyArmy = percentageOfCivProvincesThatAreOccupied(civ) < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_ENEMY_ARMIES_IF_PERC_OF_PROVINCES_THAT_ARE_OCCUPIED;
            buildEnemyArmy = updateMinWarscore(civ, civID, buildEnemyArmy);
            if ((buildEnemyArmy || AI_MoveAtWar.warScore < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_ENEMY_ARMIES_IF_WARSCORE_BELOW || AI_MoveAtWar.warScore < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_ENEMY_ARMIES_AT_ANY_COST_IF_WARSCORE_BELOW) && (!AI_MoveAtWar.armies.isEmpty() || !AI_MoveAtWar.armiesInMove.isEmpty() || !AI_MoveAtWar.armiesSiege.isEmpty())) {
                buildEnemyArmy(civ, civID);
                if (!AI_MoveAtWar.enemyArmies.isEmpty()) {
                    moveToEnemyArmies(civID);
                }
            }
            mergeArmies(civID);
            if (!AI_MoveAtWar.armiesLowMoraleManpower.isEmpty()) {
                moveArmiesLowMoraleManpower(civID);
            }
            if (!AI_MoveAtWar.armies.isEmpty()) {
                if (civ.getWarPlayDefensiveUntilTurnID() > Game_Calendar.TURN_ID) {
                    determinePossibleProvinces_Defensive(civ, civID);
                    if (!AI_MoveAtWar.possibleProvinces.isEmpty()) {
                        moveArmiesToProvinces(civID);
                    }
                }
                else {
                    determinePossibleProvinces(civ, civID);
                    if (!AI_MoveAtWar.possibleProvinces.isEmpty()) {
                        moveArmiesToProvinces(civID);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void moveToEnemyArmies(final int civID) {
        moveToEnemyArmies_SortEnemyArmies(civID);
        final List<ArmyDivision> civArmies = moveToEnemyArmies_GetOwnArmies();
        for (int i = 0; i < AI_MoveAtWar.enemyArmies.size(); ++i) {
            final List<Float> civArmiesDistance = moveToEnemyArmies_MoveDistance(AI_MoveAtWar.enemyArmies.get(i).provinceID, civArmies);
            final List<ArmyDivision> moveArmies = new ArrayList<ArmyDivision>();
            int moveArmies_Army = 0;
            while (!civArmies.isEmpty()) {
                int bestID = 0;
                for (int a = civArmiesDistance.size() - 1; a > 0; --a) {
                    if (civArmiesDistance.get(bestID) > civArmiesDistance.get(a)) {
                        bestID = a;
                    }
                }
                moveArmies.add(civArmies.get(bestID));
                moveArmies_Army += civArmies.get(bestID).iArmy;
                civArmies.remove(bestID);
                civArmiesDistance.remove(bestID);
                if (moveArmies_Army > AI_MoveAtWar.enemyArmies.get(i).army) {
                    for (int a = 0; a < moveArmies.size(); ++a) {
                        Game.getCiv(civID).newMove(moveArmies.get(a).provinceID, AI_MoveAtWar.enemyArmies.get(i).provinceID, moveArmies.get(a).key, 0, false);
                    }
                    moveArmies.clear();
                    break;
                }
                if (!civArmies.isEmpty()) {
                    continue;
                }
                if (AI_MoveAtWar.warScore < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_ENEMY_ARMIES_AT_ANY_COST_IF_WARSCORE_BELOW) {
                    for (int a = 0; a < moveArmies.size(); ++a) {
                        Game.getCiv(civID).newMove(moveArmies.get(a).provinceID, AI_MoveAtWar.enemyArmies.get(i).provinceID, moveArmies.get(a).key, 0, false);
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public static List<Float> moveToEnemyArmies_MoveDistance(final int provinceID, final List<ArmyDivision> civArmies) {
        final List<Float> out = new ArrayList<Float>();
        for (int i = 0, iSize = civArmies.size(); i < iSize; ++i) {
            out.add(Game.getManhattanDistance(provinceID, civArmies.get(i).provinceID));
        }
        return out;
    }
    
    public static void moveToEnemyArmies_SortEnemyArmies(final int civID) {
        for (int i = AI_MoveAtWar.enemyArmies.size() - 1; i >= 0; --i) {
            AI_MoveAtWar.enemyArmies.get(i).distance = Game.getManhattanDistance(Game.getCiv(civID).getCapitalProvinceID(), AI_MoveAtWar.enemyArmies.get(i).provinceID);
        }
        Collections.sort(AI_MoveAtWar.enemyArmies, new Comparator<EnemyArmy>() {
            @Override
            public int compare(final EnemyArmy army1, final EnemyArmy army2) {
                return Float.compare(army1.distance, army2.distance);
            }
        });
    }
    
    public static List<ArmyDivision> moveToEnemyArmies_GetOwnArmies() {
        final List<ArmyDivision> civArmies = new ArrayList<ArmyDivision>();
        for (int i = AI_MoveAtWar.armies.size() - 1; i >= 0; --i) {
            civArmies.add(AI_MoveAtWar.armies.get(i));
        }
        for (int i = AI_MoveAtWar.armiesInMove.size() - 1; i >= 0; --i) {
            civArmies.add(AI_MoveAtWar.armiesInMove.get(i));
        }
        for (int i = AI_MoveAtWar.armiesSiege.size() - 1; i >= 0; --i) {
            civArmies.add(AI_MoveAtWar.armiesSiege.get(i));
        }
        return civArmies;
    }
    
    public static boolean updateMinWarscore(final Civilization civ, final int civID, boolean buildEnemyArmy) {
        try {
            for (int a = 0; a < civ.diplomacy.iWarsSize; ++a) {
                final War war = WarManager.lWars.get(civ.diplomacy.lWars.get(a));
                if (war != null) {
                    AI_MoveAtWar.warScore = Math.min(AI_MoveAtWar.warScore, (float)(-(int)war.warScore * war.getWarScore_Side(civID)));
                    if (!buildEnemyArmy) {
                        if (war.isAggressor(civID)) {
                            if (Game.getCiv(war.lAggressors.get(0).iCivID).iRegiments > Game.getCiv(war.lDefenders.get(0).iCivID).iRegiments) {
                                buildEnemyArmy = true;
                            }
                        }
                        else if (Game.getCiv(war.lDefenders.get(0).iCivID).iRegiments > Game.getCiv(war.lAggressors.get(0).iCivID).iRegiments) {
                            buildEnemyArmy = true;
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return buildEnemyArmy;
    }
    
    public static void processArmyPositions(final Civilization civ) {
        for (int i = 0; i < civ.iArmyPositionSize; ++i) {
            final ArmyDivision armyDivision = Game.getProvince(civ.getArmyPosition(i)).getArmy(civ.getArmyPositionKey(i));
            if (armyDivision != null) {
                if (!armyDivision.inBattle) {
                    if (Game.getProvinceData4(armyDivision.provinceID).isUnderSiege()) {
                        if (SiegeManager.isProvinceUnderSiege(armyDivision.provinceID)) {
                            AI_MoveAtWar.armiesSiege.add(armyDivision);
                        }
                        else {
                            Game.getProvinceData4(armyDivision.provinceID).setIsUnderSiege_Just(false);
                        }
                    }
                    else if (armyDivision.iArmyRegimentSize < Game.aiValuesArmies.AT_WAR_WAIT_IF_IN_RECRUITMENT_AND_REGIMENTS_BELOW && civ.isRecruitArmyInProgress(armyDivision.key)) {
                        AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                    }
                    else if (armyDivision.inMovement || armyDivision.inRetreat) {
                        if (!armyDivision.inRetreat) {
                            AI_MoveAtWar.armiesInMove.add(armyDivision);
                        }
                        if (!civ.isInMoveUnits_ArmyKey(armyDivision.key)) {
                            armyDivision.inMovement = false;
                            armyDivision.inRetreat = false;
                        }
                    }
                    else if (armyDivision.iArmyRegimentSize > 0) {
                        if (shouldMergeArmy(civ, armyDivision)) {
                            AI_MoveAtWar.armiesToMerge.add(armyDivision);
                        }
                        else if (armyDivision.fMorale < Game.aiValuesArmies.AT_WAR_DEFEND_IF_ARMY_MORALE_BELOW) {
                            AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                        }
                        else if (civ.fManpower > Game.aiValuesArmies.AT_WAR_DEFEND_IF_CIV_MANPOWER_OVER && armyDivision.iArmy / (float)(armyDivision.iArmyRegimentSize * Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) < Game.aiValuesArmies.AT_WAR_DEFEND_IF_ARMY_MANPOWER_BELOW) {
                            AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                        }
                        else if (armyDivision.iArmyRegimentSize < Game.aiValuesArmies.AT_WAR_DEFEND_IF_NUM_OF_REGIMENTS_BELOW) {
                            AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                        }
                        else {
                            AI_MoveAtWar.armies.add(armyDivision);
                        }
                    }
                }
            }
        }
    }
    
    public static void processArmyPositions_Just(final Civilization civ) {
        for (int i = 0; i < civ.iArmyPositionSize; ++i) {
            final ArmyDivision armyDivision = Game.getProvince(civ.getArmyPosition(i)).getArmy(civ.getArmyPositionKey(i));
            if (armyDivision != null) {
                if (!armyDivision.inBattle) {
                    if (Game.getProvinceData4(armyDivision.provinceID).isUnderSiege()) {
                        if (SiegeManager.isProvinceUnderSiege(armyDivision.provinceID)) {
                            AI_MoveAtWar.armiesSiege.add(armyDivision);
                        }
                        else {
                            Game.getProvinceData4(armyDivision.provinceID).setIsUnderSiege_Just(false);
                        }
                    }
                    else if (armyDivision.iArmyRegimentSize < Game.aiValuesArmies.AT_WAR_WAIT_IF_IN_RECRUITMENT_AND_REGIMENTS_BELOW && civ.isRecruitArmyInProgress(armyDivision.key)) {
                        AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                    }
                    else if (armyDivision.inMovement || armyDivision.inRetreat) {
                        if (!armyDivision.inRetreat) {
                            AI_MoveAtWar.armiesInMove.add(armyDivision);
                        }
                    }
                    else if (armyDivision.iArmyRegimentSize > 0) {
                        if (shouldMergeArmy(civ, armyDivision)) {
                            if (!civ.aiMerge.isArmyMerging(civ.getCivID(), civ.getArmyPositionKey(i))) {
                                AI_MoveAtWar.armiesToMerge.add(armyDivision);
                            }
                        }
                        else if (armyDivision.fMorale < Game.aiValuesArmies.AT_WAR_DEFEND_IF_ARMY_MORALE_BELOW) {
                            AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                        }
                        else if (civ.fManpower > Game.aiValuesArmies.AT_WAR_DEFEND_IF_CIV_MANPOWER_OVER && armyDivision.iArmy / (float)(armyDivision.iArmyRegimentSize * Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) < Game.aiValuesArmies.AT_WAR_DEFEND_IF_ARMY_MANPOWER_BELOW) {
                            AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                        }
                        else if (armyDivision.iArmyRegimentSize < Game.aiValuesArmies.AT_WAR_DEFEND_IF_NUM_OF_REGIMENTS_BELOW) {
                            AI_MoveAtWar.armiesLowMoraleManpower.add(armyDivision);
                        }
                        else {
                            AI_MoveAtWar.armies.add(armyDivision);
                        }
                    }
                }
            }
        }
    }
    
    public static float percentageOfCivProvincesThatAreOccupied(final Civilization civ) {
        float out = 0.0f;
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            final Province province = Game.getProvince(civ.getProvinceID(i));
            if (province.isOccupied()) {
                ++out;
            }
        }
        return out / civ.getNumOfProvinces();
    }
    
    public static void buildEnemyArmy(final Civilization civ, final int civID) {
        final List<Integer> addedCivs = new ArrayList<Integer>();
        try {
            for (int a = 0; a < civ.diplomacy.iWarsSize; ++a) {
                final War war = WarManager.lWars.get(civ.diplomacy.lWars.get(a));
                if (war != null) {
                    final List<WarCivilization> warSides = war.isAggressor(civID) ? war.lAggressors : war.lDefenders;
                    final List<WarCivilization> warSides2 = war.isAggressor(civID) ? war.lDefenders : war.lAggressors;
                    if ((Game.getCiv(warSides.get(0).iCivID).iRegiments > Game.getCiv(warSides2.get(0).iCivID).iRegiments || -(int)war.warScore * war.getWarScore_Side(civID) < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_TO_ENEMY_ARMIES_AT_ANY_COST_IF_WARSCORE_BELOW) && !addedCivs.contains(warSides.get(0).iCivID)) {
                        addedCivs.add(warSides.get(0).iCivID);
                        for (int j = 0, warSideCivID = warSides.get(0).iCivID; j < Game.getCiv(warSideCivID).getNumOfProvinces(); ++j) {
                            final Province province = Game.getProvince(Game.getCiv(warSideCivID).getProvinceID(j));
                            if (province.getArmySize() > 0) {
                                for (int k = 0; k < province.getArmySize(); ++k) {
                                    if (!province.getArmy(k).inRetreat && civID != province.getArmy(k).civID && DiplomacyManager.isAtWar(civID, province.getArmy(k).civID)) {
                                        boolean addArmy = true;
                                        for (int z = AI_MoveAtWar.enemyArmies.size() - 1; z >= 0; --z) {
                                            if (AI_MoveAtWar.enemyArmies.get(z).provinceID == province.getProvinceID()) {
                                                final EnemyArmy enemyArmy = AI_MoveAtWar.enemyArmies.get(z);
                                                enemyArmy.army += province.getArmy(k).iArmy;
                                                addArmy = false;
                                                break;
                                            }
                                        }
                                        if (addArmy) {
                                            AI_MoveAtWar.enemyArmies.add(new EnemyArmy(province.getProvinceID(), province.getArmy(k).iArmy));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (!addedCivs.contains(civID)) {
            for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
                final Province province2 = Game.getProvince(civ.getProvinceID(i));
                if (province2.getArmySize() > 0) {
                    for (int l = 0; l < province2.getArmySize(); ++l) {
                        if (!province2.getArmy(l).inRetreat && civID != province2.getArmy(l).civID && DiplomacyManager.isAtWar(civID, province2.getArmy(l).civID)) {
                            boolean addArmy2 = true;
                            for (int z2 = AI_MoveAtWar.enemyArmies.size() - 1; z2 >= 0; --z2) {
                                if (AI_MoveAtWar.enemyArmies.get(z2).provinceID == province2.getProvinceID()) {
                                    final EnemyArmy enemyArmy2 = AI_MoveAtWar.enemyArmies.get(z2);
                                    enemyArmy2.army += province2.getArmy(l).iArmy;
                                    addArmy2 = false;
                                    break;
                                }
                            }
                            if (addArmy2) {
                                AI_MoveAtWar.enemyArmies.add(new EnemyArmy(province2.getProvinceID(), province2.getArmy(l).iArmy));
                            }
                        }
                    }
                }
            }
        }
        addedCivs.clear();
    }
    
    public static boolean shouldMergeArmy(final Civilization civ, final ArmyDivision armyDivision) {
        return civ.getArmyRegimentSize() >= Game.aiValuesArmies.AT_WAR_MERGE_ALL_ARMIES_WITH_REGIMENTS_BELOW * 2 && armyDivision.iArmyRegimentSize < Game.aiValuesArmies.AT_WAR_MERGE_ALL_ARMIES_WITH_REGIMENTS_BELOW;
    }
    
    public static void mergeArmies(final int civID) {
        for (int i = AI_MoveAtWar.armiesToMerge.size() - 1; i >= 0; --i) {
            mergeArmy(civID, AI_MoveAtWar.armiesToMerge.get(i).provinceID, AI_MoveAtWar.armiesToMerge.get(i).key);
            AI_MoveAtWar.armiesToMerge.remove(i);
        }
    }
    
    public static void determinePossibleProvinces(final Civilization civ, final int civID) {
        addOccupiedProvinces(civ);
        addAlliedProvincesFromWars(civ, civID);
    }
    
    public static void determinePossibleProvinces_Defensive(final Civilization civ, final int civID) {
        addOccupiedProvinces(civ);
        addAlliedProvincesFromWars_Defensive(civ, civID);
    }
    
    public static void addOccupiedProvinces(final Civilization civ) {
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            final Province province = Game.getProvince(civ.getProvinceID(i));
            if (province.isOccupied()) {
                AI_MoveAtWar.possibleProvinces.add(civ.getProvinceID(i));
                if (Game.getProvinceData(civ.getProvinceID(i)).getOccupiedByCivID() < 0) {
                    province.aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_SCORE_OCCUPIED_BY_REBELS;
                }
                else {
                    province.aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_SCORE_OCCUPIED_BY_ENEMY_CIV;
                }
            }
            else if (Game.getProvinceData4(civ.getProvinceID(i)).isUnderSiege()) {
                boolean byRebels = false;
                for (int a = 0; a < Game.getProvince(civ.getProvinceID(i)).getArmySize(); ++a) {
                    if (Game.getProvince(civ.getProvinceID(i)).getArmy(a).civID < 0) {
                        byRebels = true;
                        break;
                    }
                }
                AI_MoveAtWar.possibleProvinces.add(civ.getProvinceID(i));
                if (byRebels) {
                    province.aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_SCORE_UNDER_SIEGE_BY_REBELS;
                }
                else {
                    province.aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_SCORE_UNDER_SIEGE_BY_ENEMY_CIV;
                }
            }
            else {
                AI_MoveAtWar.possibleProvincesDefend.add(civ.getProvinceID(i));
                province.aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_SCORE_DEFAULT;
            }
        }
    }
    
    public static void addAlliedProvincesFromWars(final Civilization civ, final int civID) {
        for (int i = 0; i < civ.diplomacy.iWarsSize; ++i) {
            final War war = WarManager.lWars.get(civ.diplomacy.lWars.get(i));
            if (war != null) {
                final float warLeaderScore = war.isWarLeader(civID) ? Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_ALLIES_WAR_LEADER : Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_ALLIES_NOT_WAR_LEADER;
                final List<WarCivilization> warSides = war.isAggressor(civID) ? war.lAggressors : war.lDefenders;
                final List<WarCivilization> warSides2 = war.isAggressor(civID) ? war.lDefenders : war.lAggressors;
                for (final WarCivilization side : warSides) {
                    if (civID != side.iCivID) {
                        for (int b = 0, bSize = Game.getCiv(side.iCivID).occupiedProvinces.size(); b < bSize; ++b) {
                            final int occupiedProvinceID = Game.getCiv(side.iCivID).occupiedProvinces.get(b);
                            final ProvinceData provinceData = Game.getProvinceData(occupiedProvinceID);
                            if (provinceData.getOccupiedByCivID() < 0) {
                                AI_MoveAtWar.possibleProvinces.add(occupiedProvinceID);
                                Game.getProvince(occupiedProvinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_ALLIES_OCCUPIED_BY_REBELS;
                            }
                            else if (DiplomacyManager.isAtWar(civID, provinceData.getOccupiedByCivID())) {
                                AI_MoveAtWar.possibleProvinces.add(occupiedProvinceID);
                                Game.getProvince(occupiedProvinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_ALLIES_OCCUPIED_BY_ENEMY_CIV;
                            }
                        }
                        for (int b = 0, bSize = Game.getCiv(side.iCivID).underSiege.size(); b < bSize; ++b) {
                            for (int underSiegeProvinceID = Game.getCiv(side.iCivID).underSiege.get(b), c = 0; c < Game.getProvince(underSiegeProvinceID).getArmySize(); ++c) {
                                if (DiplomacyManager.isAtWar(civID, Game.getProvince(underSiegeProvinceID).getArmy(c).civID)) {
                                    AI_MoveAtWar.possibleProvinces.add(underSiegeProvinceID);
                                    Game.getProvince(underSiegeProvinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_ALLIES_UNDER_SIEGE;
                                    break;
                                }
                            }
                        }
                    }
                }
                for (int j = 0, warSideCivID = warSides2.get(0).iCivID; j < Game.getCiv(warSideCivID).getNumOfProvinces(); ++j) {
                    final int provinceID = Game.getCiv(warSideCivID).getProvinceID(j);
                    if (Game.getProvince(provinceID).isOccupied()) {
                        if (Game.getProvinceData4(provinceID).isUnderSiege()) {
                            AI_MoveAtWar.possibleProvinces.add(provinceID);
                            Game.getProvince(provinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_ENEMY_WAR_LEADER_OCCUPIED_UNDER_SIEGE;
                        }
                        else {
                            AI_MoveAtWar.possibleProvincesDefend.add(provinceID);
                            Game.getProvince(provinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_ENEMY_WAR_LEADER_OCCUPIED;
                        }
                    }
                    else {
                        AI_MoveAtWar.possibleProvinces.add(provinceID);
                        Game.getProvince(provinceID).aiMoveArmyAtWarScore = warLeaderScore;
                    }
                }
                for (int a = warSides2.size() - 1; a > 0; --a) {
                    for (int warSideCivID = warSides2.get(a).iCivID, k = 0; k < Game.getCiv(warSideCivID).getNumOfProvinces(); ++k) {
                        if (!Game.getProvince(Game.getCiv(warSideCivID).getProvinceID(k)).isOccupied()) {
                            AI_MoveAtWar.possibleProvinces.add(Game.getCiv(warSideCivID).getProvinceID(k));
                            Game.getProvince(Game.getCiv(warSideCivID).getProvinceID(k)).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_ENEMY_ALLIES_PROVINCES;
                        }
                    }
                }
            }
        }
    }
    
    public static void addAlliedProvincesFromWars_Defensive(final Civilization civ, final int civID) {
        for (int i = 0; i < civ.diplomacy.iWarsSize; ++i) {
            final War war = WarManager.lWars.get(civ.diplomacy.lWars.get(i));
            if (war != null) {
                final List<WarCivilization> warSides = war.isAggressor(civID) ? war.lAggressors : war.lDefenders;
                for (final WarCivilization side : warSides) {
                    if (civID != side.iCivID) {
                        for (int b = 0, bSize = Game.getCiv(side.iCivID).occupiedProvinces.size(); b < bSize; ++b) {
                            final int occupiedProvinceID = Game.getCiv(side.iCivID).occupiedProvinces.get(b);
                            final ProvinceData provinceData = Game.getProvinceData(occupiedProvinceID);
                            if (provinceData.getOccupiedByCivID() < 0) {
                                AI_MoveAtWar.possibleProvinces.add(occupiedProvinceID);
                                Game.getProvince(occupiedProvinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_DEFEND_OCCUPIED_BY_REBELS;
                            }
                            else if (DiplomacyManager.isAtWar(civID, provinceData.getOccupiedByCivID())) {
                                AI_MoveAtWar.possibleProvinces.add(occupiedProvinceID);
                                Game.getProvince(occupiedProvinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_DEFEND_OCCUPIED_BY_ENEMY_CIV;
                            }
                        }
                        for (int b = 0, bSize = Game.getCiv(side.iCivID).underSiege.size(); b < bSize; ++b) {
                            for (int underSiegeProvinceID = Game.getCiv(side.iCivID).underSiege.get(b), c = 0; c < Game.getProvince(underSiegeProvinceID).getArmySize(); ++c) {
                                if (DiplomacyManager.isAtWar(civID, Game.getProvince(underSiegeProvinceID).getArmy(c).civID)) {
                                    AI_MoveAtWar.possibleProvinces.add(underSiegeProvinceID);
                                    Game.getProvince(underSiegeProvinceID).aiMoveArmyAtWarScore = Game.aiValuesArmies.AT_WAR_MOVE_PROVINCE_DEFEND_ENEMY_ARMY_IN_PROVINCE;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static void calculateProvinceScores() {
        for (final int provinceID : AI_MoveAtWar.possibleProvinces) {
            final Province province2;
            final Province province = province2 = Game.getProvince(provinceID);
            province2.aiMoveArmyAtWarScore *= province.fProvinceValue;
        }
    }
    
    public static void moveArmiesToProvinces(final int civID) {
        for (final ArmyDivision army : AI_MoveAtWar.armies) {
            int toProvinceID = selectBestProvince(civID, selectBestProvince(army.provinceID));
            if (army.provinceID == toProvinceID) {
                SiegeManager.checkForSiege(toProvinceID);
            }
            else if (Game.getCiv(civID).noConnectionMoveUnits.contains(army.provinceID, toProvinceID)) {
                moveArmiesToProvinces_Defend(civID, army);
            }
            else if (Game.getCiv(civID).newMove(army.provinceID, toProvinceID, army.key, 0, false)) {
                Game.getProvince(toProvinceID).aiMoveArmyAtWarScore *= Game.aiValuesArmies.AT_WAR_AFTER_MOVE_SCORE;
            }
            else {
                final MoveUnits_AI moveUnitsAI = new MoveUnits_AI(civID, army.provinceID, toProvinceID);
                if (moveUnitsAI.iRouteSize > 0) {
                    final List<Integer> accessCivs = new ArrayList<Integer>();
                    for (int i = 0; i < moveUnitsAI.iRouteSize; ++i) {
                        if (!accessCivs.contains(Game.getProvince(moveUnitsAI.lRoute.get(i)).getCivID())) {
                            accessCivs.add(Game.getProvince(moveUnitsAI.lRoute.get(i)).getCivID());
                        }
                    }
                    boolean isPlayerInList = false;
                    for (int j = accessCivs.size() - 1; j >= 0; --j) {
                        if (accessCivs.get(j) == 0 || accessCivs.get(j) == civID || DiplomacyManager.isAtWar(civID, accessCivs.get(j)) || DiplomacyManager.isAlly(civID, accessCivs.get(j))) {
                            accessCivs.remove(j);
                        }
                        else if (accessCivs.get(j) == Game.player.iCivID) {
                            accessCivs.remove(j);
                            isPlayerInList = true;
                        }
                    }
                    if (isPlayerInList) {
                        Game.player.addMessage(new PMessageDemandMilitaryAccess(civID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        final Civilization civ = Game.getCiv(civID);
                        civ.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_DEMAND_MILITARY_ACCESS_COST * Game.aiValuesDiplomacy.AI_DEMAND_MILITARY_ACCESS_COST_MODIFIER;
                    }
                    for (int j = accessCivs.size() - 1; j >= 0; --j) {
                        if (DiplomacyManager.addMilitaryAccess(civID, accessCivs.get(j))) {
                            final Civilization civ2 = Game.getCiv(civID);
                            civ2.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_DEMAND_MILITARY_ACCESS_COST * Game.aiValuesDiplomacy.AI_DEMAND_MILITARY_ACCESS_COST_MODIFIER;
                        }
                    }
                    accessCivs.clear();
                }
                Game.getCiv(civID).noConnectionMoveUnits.addProvince(army.provinceID, toProvinceID);
                boolean defend = true;
                for (int a = Math.min(Game.aiValuesArmies.AT_WAR_MOVE_ARMY_CANT_REACH_RANDOM_PROVINCES_LIMIT, AI_MoveAtWar.possibleProvinces.size()) - 1; a >= 0; --a) {
                    toProvinceID = selectBestProvince(civID, selectBestProvince_Random(army.provinceID));
                    if (army.provinceID == toProvinceID) {
                        SiegeManager.checkForSiege(toProvinceID);
                    }
                    else if (!Game.getCiv(civID).noConnectionMoveUnits.contains(army.provinceID, toProvinceID)) {
                        if (Game.getCiv(civID).newMove(army.provinceID, toProvinceID, army.key, 0, false)) {
                            Game.getProvince(toProvinceID).aiMoveArmyAtWarScore *= Game.aiValuesArmies.AT_WAR_AFTER_MOVE_SCORE;
                            defend = false;
                            break;
                        }
                    }
                }
                if (!defend) {
                    continue;
                }
                moveArmiesToProvinces_Defend(civID, army);
            }
        }
    }
    
    public static void moveArmiesToProvinces_Defend(final int civID, final ArmyDivision army) {
        if (!AI_MoveAtWar.possibleProvincesDefend.isEmpty() && Game.oR.nextInt(100) < Game.aiValuesArmies.AT_WAR_NO_CONNECTIONS_DEFEND_CHANCE) {
            final int toProvinceID = selectBestProvince_Defend(army.provinceID);
            if (army.provinceID == toProvinceID) {
                SiegeManager.checkForSiege(toProvinceID);
            }
            if (Game.getCiv(civID).newMove(army.provinceID, toProvinceID, army.key, 0, false)) {
                Game.getProvince(toProvinceID).aiMoveArmyAtWarScore *= Game.aiValuesArmies.AT_WAR_AFTER_MOVE_DEFEND_SCORE;
            }
        }
    }
    
    public static int selectBestProvince(final int civID, final int provinceID) {
        final Province province = Game.getProvince(provinceID);
        final ProvinceData provinceData = Game.getProvinceData(provinceID);
        if (province.getFortLevel() > 0 || Game.getProvinceData4(provinceID).isUnderSiege()) {
            return provinceID;
        }
        int scoreProvinceID = 0;
        int bestProvinceID = provinceID;
        int bestScore = 0;
        final boolean isOccupied = province.isOccupied();
        final int currentCivID = provinceData.getOccupiedByCivID();
        for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
            final int neighborID = province.getNeighboringProvinces(i);
            final Province neighProvince = Game.getProvince(neighborID);
            final ProvinceData neighProvinceData = Game.getProvinceData(neighborID);
            final boolean isValidNeighbor = isOccupied ? (neighProvinceData.getOccupiedByCivID() == currentCivID) : (!neighProvince.isOccupied() && neighProvince.getCivID() == province.getCivID());
            if (isValidNeighbor) {
                ++scoreProvinceID;
                int nScore = 0;
                for (int j = 0; j < neighProvince.getNeighboringProvincesSize(); ++j) {
                    final int neighOfNeighID = neighProvince.getNeighboringProvinces(j);
                    final Province neighOfNeighProvince = Game.getProvince(neighOfNeighID);
                    final boolean isValidNeighOfNeigh = isOccupied ? (Game.getProvinceData(neighOfNeighID).getOccupiedByCivID() == currentCivID) : (!neighOfNeighProvince.isOccupied() && neighOfNeighProvince.getCivID() == province.getCivID());
                    if (isValidNeighOfNeigh) {
                        ++nScore;
                    }
                }
                if (nScore > bestScore) {
                    bestScore = nScore;
                    bestProvinceID = neighborID;
                }
            }
        }
        if (scoreProvinceID >= bestScore) {
            return provinceID;
        }
        return bestProvinceID;
    }
    
    public static int selectBestProvince(final int fromProvinceID) {
        for (int i = AI_MoveAtWar.possibleProvinces.size() - 1; i >= 0; --i) {
            Game.getProvince(AI_MoveAtWar.possibleProvinces.get(i)).aiMoveArmyAtWarScore_DistanceFromArmy = Game.getProvince(AI_MoveAtWar.possibleProvinces.get(i)).aiMoveArmyAtWarScore * (1.0f - Game.getManhattanDistance_PercOfMax(fromProvinceID, AI_MoveAtWar.possibleProvinces.get(i)));
        }
        Game.getProvince(fromProvinceID).aiMoveArmyAtWarScore_DistanceFromArmy = -999999.0f;
        if (Game.oR.nextInt(100) < Game.aiValuesArmies.AT_WAR_MOVE_ARMY_CHOOSE_BEST_PROVINCE_CHANCE) {
            int bestID = 0;
            for (int j = AI_MoveAtWar.possibleProvinces.size() - 1; j > 0; --j) {
                if (Game.getProvince(AI_MoveAtWar.possibleProvinces.get(j)).aiMoveArmyAtWarScore_DistanceFromArmy > Game.getProvince(AI_MoveAtWar.possibleProvinces.get(bestID)).aiMoveArmyAtWarScore_DistanceFromArmy) {
                    bestID = j;
                }
            }
            return AI_MoveAtWar.possibleProvinces.get(bestID);
        }
        final List<Integer> bestToChooseFrom = new ArrayList<Integer>();
        for (int b = 0, bLimit = Math.min(AI_MoveAtWar.possibleProvinces.size(), Game.aiValuesArmies.AT_WAR_MOVE_ARMY_BEST_PROVINCES_LIMIT + Game.getProvince(fromProvinceID).getArmySize()); b < bLimit; ++b) {
            int bestID2 = 0;
            for (int k = AI_MoveAtWar.possibleProvinces.size() - 1; k > 0; --k) {
                if (Game.getProvince(AI_MoveAtWar.possibleProvinces.get(k)).aiMoveArmyAtWarScore_DistanceFromArmy > Game.getProvince(AI_MoveAtWar.possibleProvinces.get(bestID2)).aiMoveArmyAtWarScore_DistanceFromArmy) {
                    bestID2 = k;
                }
            }
            if (Game.getProvince(AI_MoveAtWar.possibleProvinces.get(bestID2)).aiMoveArmyAtWarScore_DistanceFromArmy == -999999.0f) {
                break;
            }
            bestToChooseFrom.add(AI_MoveAtWar.possibleProvinces.get(bestID2));
            Game.getProvince(AI_MoveAtWar.possibleProvinces.get(bestID2)).aiMoveArmyAtWarScore_DistanceFromArmy = -999999.0f;
        }
        if (bestToChooseFrom.isEmpty()) {
            return fromProvinceID;
        }
        return bestToChooseFrom.get(Game.oR.nextInt(bestToChooseFrom.size()));
    }
    
    public static int selectBestProvince_Random(final int fromProvinceID) {
        return AI_MoveAtWar.possibleProvinces.get(Game.oR.nextInt(AI_MoveAtWar.possibleProvinces.size()));
    }
    
    public static int selectBestProvince_Defend(final int fromProvinceID) {
        for (int i = AI_MoveAtWar.possibleProvincesDefend.size() - 1; i >= 0; --i) {
            final Province province = Game.getProvince(AI_MoveAtWar.possibleProvincesDefend.get(i));
            province.aiMoveArmyAtWarScore_DistanceFromArmy = province.fProvinceValue * province.aiMoveArmyAtWarScore * (1.0f - Game.getManhattanDistance_PercOfMax(fromProvinceID, AI_MoveAtWar.possibleProvincesDefend.get(i)));
        }
        Game.getProvince(fromProvinceID).aiMoveArmyAtWarScore_DistanceFromArmy = -999999.0f;
        int bestID = 0;
        for (int j = AI_MoveAtWar.possibleProvincesDefend.size() - 1; j > 0; --j) {
            if (Game.getProvince(AI_MoveAtWar.possibleProvincesDefend.get(j)).aiMoveArmyAtWarScore_DistanceFromArmy > Game.getProvince(AI_MoveAtWar.possibleProvincesDefend.get(bestID)).aiMoveArmyAtWarScore_DistanceFromArmy) {
                bestID = j;
            }
        }
        return AI_MoveAtWar.possibleProvincesDefend.get(bestID);
    }
    
    public static void moveArmiesLowMoraleManpower(final int civID) {
        try {
            for (int i = AI_MoveAtWar.armiesLowMoraleManpower.size() - 1; i >= 0; --i) {
                if (Game.getProvince(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID).getCivID() != civID) {
                    final int rand = Game.oR.nextInt(100);
                    if (rand >= Game.aiValuesArmies.AT_WAR_DEFEND_IN_ENEMY_TERRITORY_DO_NOTHING) {
                        if (rand < Game.aiValuesArmies.AT_WAR_DEFEND_IN_ENEMY_TERRITORY_MOVE_TO_CLOSEST_PROVINCE) {
                            int bestProvinceID = Game.getCiv(civID).getCapitalProvinceID();
                            float bestDistance = Game.getDistanceFromProvinceToProvince(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID, bestProvinceID);
                            for (int a = 0; a < Game.getCiv(civID).getNumOfProvinces(); ++a) {
                                final float tDistance = Game.getDistanceFromProvinceToProvince(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID, Game.getCiv(civID).getProvinceID(a));
                                if (tDistance < bestDistance) {
                                    bestProvinceID = Game.getCiv(civID).getProvinceID(a);
                                    bestDistance = tDistance;
                                }
                            }
                            Game.getCiv(civID).newMove(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID, bestProvinceID, AI_MoveAtWar.armiesLowMoraleManpower.get(i).key, 0, false);
                        }
                        else if (rand < Game.aiValuesArmies.AT_WAR_DEFEND_IN_ENEMY_TERRITORY_MOVE_TO_CAPITAL) {
                            Game.getCiv(civID).newMove(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID, Game.getCiv(civID).getCapitalProvinceID(), AI_MoveAtWar.armiesLowMoraleManpower.get(i).key, 0, false);
                        }
                        else {
                            Game.getCiv(civID).newMove(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID, Game.getCiv(civID).getProvinceID(Game.oR.nextInt(Game.getCiv(civID).getNumOfProvinces())), AI_MoveAtWar.armiesLowMoraleManpower.get(i).key, 0, false);
                        }
                    }
                }
                else {
                    final int rand = Game.oR.nextInt(100);
                    if (rand < Game.aiValuesArmies.AT_WAR_DEFEND_IN_OWN_TERRITORY_MOVE_TO_NEIGHBORING_PROVINCE) {
                        final Province province = Game.getProvince(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID);
                        final List<Integer> possibleToMove = new ArrayList<Integer>();
                        for (int a2 = 0; a2 < province.getNeighboringProvincesSize(); ++a2) {
                            if (Game.getProvince(province.getNeighboringProvinces(a2)).getCivID() == civID && !Game.getProvince(province.getNeighboringProvinces(a2)).isEnemyArmyInProvince(civID)) {
                                possibleToMove.add(province.getNeighboringProvinces(a2));
                            }
                        }
                        if (!possibleToMove.isEmpty()) {
                            Game.getCiv(civID).newMove(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID, possibleToMove.get(Game.oR.nextInt(possibleToMove.size())), AI_MoveAtWar.armiesLowMoraleManpower.get(i).key, 0, false);
                        }
                        possibleToMove.clear();
                    }
                    else if (rand < Game.aiValuesArmies.AT_WAR_DEFEND_IN_OWN_TERRITORY_MOVE_TO_CAPITAL) {
                        Game.getCiv(civID).newMove(AI_MoveAtWar.armiesLowMoraleManpower.get(i).provinceID, Game.getCiv(civID).getCapitalProvinceID(), AI_MoveAtWar.armiesLowMoraleManpower.get(i).key, 0, false);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void mergeArmy(final int civID, final int provinceID, final String armyKey) {
        try {
            if (Game.getCiv(civID).aiMerge.isArmyMerging_Just(civID, armyKey)) {
                return;
            }
            for (int i = 0; i < Game.getProvince(provinceID).getArmySize(); ++i) {
                if (Game.getProvince(provinceID).getArmy(i).civID == civID && !Game.getProvince(provinceID).getArmy(i).key.equals(armyKey)) {
                    final List<String> toMerge = new ArrayList<String>();
                    toMerge.add(Game.getProvince(provinceID).getArmy(i).key);
                    toMerge.add(armyKey);
                    Game.getProvince(provinceID).mergeUnits(toMerge);
                    return;
                }
            }
            int bestID = -1;
            float bestDistance = 999999.0f;
            for (int j = 0; j < Game.getCiv(civID).iArmyPositionSize; ++j) {
                if (Game.getCiv(civID).getArmyPosition(j) != provinceID) {
                    final float tDistance = Game.getDistanceFromProvinceToProvince(provinceID, Game.getCiv(civID).getArmyPosition(j));
                    if (tDistance < bestDistance) {
                        final ArmyDivision armyDivision = Game.getProvince(Game.getCiv(civID).getArmyPosition(j)).getArmy(Game.getCiv(civID).getArmyPositionKey(j));
                        if (armyDivision != null && !armyDivision.inMovement) {
                            bestID = j;
                            bestDistance = tDistance;
                        }
                    }
                }
            }
            if (bestID >= 0) {
                if (Game.getCiv(civID).newMove(provinceID, Game.getCiv(civID).getArmyPosition(bestID), armyKey, 0, false)) {
                    Game.getCiv(civID).aiMerge.addMerge(Game.getCiv(civID).getArmyPosition(bestID), Game.getCiv(civID).getArmyPositionKey(bestID));
                    Game.getCiv(civID).aiMerge.addMerge(Game.getCiv(civID).getArmyPosition(bestID), armyKey);
                }
            }
            else {
                Game.getCiv(civID).newMove(provinceID, Game.getCiv(civID).getCapitalProvinceID(), armyKey, 0, false);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void mergeArmy_InProvince(final int civID, final int provinceID, final String armyKey) {
        try {
            int largestArmyID = -1;
            for (int i = 0; i < Game.getProvince(provinceID).getArmySize(); ++i) {
                if (Game.getProvince(provinceID).getArmy(i).civID == civID && !Game.getProvince(provinceID).getArmy(i).key.equals(armyKey)) {
                    if (largestArmyID < 0) {
                        largestArmyID = i;
                    }
                    else if (Game.getProvince(provinceID).getArmy(i).iArmyRegimentSize > Game.getProvince(provinceID).getArmy(largestArmyID).iArmyRegimentSize) {
                        largestArmyID = i;
                    }
                }
            }
            if (largestArmyID >= 0) {
                final List<String> toMerge = new ArrayList<String>();
                toMerge.add(Game.getProvince(provinceID).getArmy(largestArmyID).key);
                toMerge.add(armyKey);
                Game.getProvince(provinceID).mergeUnits(toMerge);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        AI_MoveAtWar.armies = new ArrayList<ArmyDivision>();
        AI_MoveAtWar.armiesInMove = new ArrayList<ArmyDivision>();
        AI_MoveAtWar.armiesSiege = new ArrayList<ArmyDivision>();
        AI_MoveAtWar.armiesLowMoraleManpower = new ArrayList<ArmyDivision>();
        AI_MoveAtWar.armiesToMerge = new ArrayList<ArmyDivision>();
        AI_MoveAtWar.enemyArmies = new ArrayList<EnemyArmy>();
        AI_MoveAtWar.possibleProvinces = new ArrayList<Integer>();
        AI_MoveAtWar.possibleProvincesDefend = new ArrayList<Integer>();
    }
    
    public static class EnemyArmy
    {
        public int provinceID;
        public int army;
        public float distance;
        
        public EnemyArmy(final int provinceID, final int army) {
            this.provinceID = provinceID;
            this.army = army;
        }
    }
}
