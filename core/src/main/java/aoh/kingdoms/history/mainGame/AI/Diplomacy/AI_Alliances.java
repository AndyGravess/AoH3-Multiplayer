// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Alliances
{
    public static final void update() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_ALLIANCES; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_ALLIANCES) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    update(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_ALLIANCES;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    update(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_ALLIANCES;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void update(final int civID) {
        final Civilization civ = Game.getCiv(civID);
        if (civ.diplomacy.alliance.isEmpty()) {
            findAlly(civID);
        }
    }
    
    public static void findAlly(final int civID) {
        try {
            Game.getCiv(civID).diplomacy.updateAlliance_ConqueredProvinces(civID);
            if (Game.getCiv(civID).diplomacy.alliance.size() + Game.getCiv(civID).aiCivDiplomacy.p.size() >= DiplomacyManager.getMaxNumberOfAlliances(civID)) {
                Game.getCiv(civID).aiCivDiplomacy.clearPrepareForAlliance();
            }
            if (!Game.getCiv(civID).aiCivDiplomacy.p.isEmpty()) {
                for (int i = Game.getCiv(civID).aiCivDiplomacy.p.size() - 1; i >= 0; --i) {
                    if (!canBeAlly(civID, Game.getCiv(civID).aiCivDiplomacy.p.get(i).c)) {
                        Game.getCiv(civID).aiCivDiplomacy.p.remove(i);
                    }
                    else if (Game.getCiv(civID).aiCivDiplomacy.p.get(i).t < Game_Calendar.TURN_ID) {
                        Game.getCiv(civID).aiCivDiplomacy.p.remove(i);
                    }
                    else if (DiplomacyManager.isAlly_AllianceCheck(civID, Game.getCiv(civID).aiCivDiplomacy.p.get(i).c)) {
                        Game.getCiv(civID).aiCivDiplomacy.p.remove(i);
                    }
                    else if (!canBeAlly(civID, Game.getCiv(civID).aiCivDiplomacy.p.get(i).c)) {
                        Game.getCiv(civID).aiCivDiplomacy.p.remove(i);
                    }
                }
            }
            if (!Game.getCiv(civID).aiCivDiplomacy.p.isEmpty()) {
                if (Game.getCiv(civID).fDiplomacy >= GameValues.diplomacy.DIPLOMACY_ALLIANCE_COST) {
                    for (int i = Game.getCiv(civID).aiCivDiplomacy.p.size() - 1; i >= 0; --i) {
                        sendAllianceProposal(civID, Game.getCiv(civID).aiCivDiplomacy.p.get(i).c);
                    }
                }
                for (int i = Game.getCiv(civID).aiCivDiplomacy.p.size() - 1; i >= 0; --i) {
                    Game.getCiv(civID).diplomacy.addImproveRelations(civID, Game.getCiv(civID).aiCivDiplomacy.p.get(i).c);
                }
            }
            if (Game.getCiv(civID).diplomacy.alliance.size() + Game.getCiv(civID).aiCivDiplomacy.p.size() < DiplomacyManager.getMaxNumberOfAlliances(civID)) {
                final Civilization civ = Game.getCiv(civID);
                final List<Integer> possibleCivs = new ArrayList<Integer>();
                final boolean addThirdLine = Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_ALLIANCE_NEIGH_OF_NEIGH_OF_NEIGH_CHANCE;
                for (int j = 0; j < civ.civNeighbors.civsSize; ++j) {
                    final int neighID = civ.civNeighbors.civs.get(j).civID;
                    if (!possibleCivs.contains(neighID) && canBeAlly(civID, neighID) && neighID != civID) {
                        possibleCivs.add(neighID);
                    }
                    for (int k = 0; k < Game.getCiv(neighID).civNeighbors.civsSize; ++k) {
                        if (!possibleCivs.contains(Game.getCiv(neighID).civNeighbors.civs.get(k).civID) && canBeAlly(civID, Game.getCiv(neighID).civNeighbors.civs.get(k).civID) && Game.getCiv(neighID).civNeighbors.civs.get(k).civID != civID) {
                            possibleCivs.add(Game.getCiv(neighID).civNeighbors.civs.get(k).civID);
                        }
                        if (addThirdLine) {
                            for (int neigh2 = Game.getCiv(neighID).civNeighbors.civs.get(k).civID, l = 0; l < Game.getCiv(neigh2).civNeighbors.civsSize; ++l) {
                                if (!possibleCivs.contains(Game.getCiv(neigh2).civNeighbors.civs.get(l).civID) && canBeAlly(civID, Game.getCiv(neigh2).civNeighbors.civs.get(l).civID) && Game.getCiv(neigh2).civNeighbors.civs.get(l).civID != civID) {
                                    possibleCivs.add(Game.getCiv(neigh2).civNeighbors.civs.get(l).civID);
                                }
                            }
                        }
                    }
                }
                if (!possibleCivs.isEmpty()) {
                    final int allyID = findBestAlly(civID, possibleCivs);
                    Game.getCiv(civID).diplomacy.addImproveRelations(civID, possibleCivs.get(allyID));
                    Game.getCiv(civID).aiCivDiplomacy.addPrepareForAlliance(possibleCivs.get(allyID));
                    sendAllianceProposal(civID, possibleCivs.get(allyID));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static boolean canBeAlly(final int civID, final int allyID) {
        return !Game.getCiv(civID).diplomacy.isRival(allyID) && !Game.getCiv(allyID).diplomacy.isRival(civID) && !Game.getCiv(civID).aiCivDiplomacy.isPreparingForWarWithCivID(allyID) && !Game.getCiv(allyID).aiCivDiplomacy.isPreparingForWarWithCivID(civID) && Game.getCiv(allyID).diplomacy.alliance.size() < DiplomacyManager.getMaxNumberOfAlliances(allyID) && !Game.getCiv(allyID).diplomacy.haveAlliance(civID) && Game.getCiv(allyID).getNumOfProvinces() > 0 && !DiplomacyManager.isAtWar(civID, allyID) && (!Game.ENABLE_CALL_VASSALS || (Game.getCiv(civID).getPuppetOfCivID() != allyID && Game.getCiv(allyID).getPuppetOfCivID() != civID));
    }
    
    public static int findBestAlly(final int civID, final List<Integer> possibleCivs) {
        int bestID = 0;
        float bestScore = findAllyScore(civID, possibleCivs.get(bestID));
        float checkCivScore = 0.0f;
        for (int i = possibleCivs.size() - 1; i > 0; --i) {
            checkCivScore = findAllyScore(civID, possibleCivs.get(i));
            if (checkCivScore > bestScore) {
                bestID = i;
                bestScore = checkCivScore;
            }
        }
        return bestID;
    }
    
    public static float findAllyScore(final int civID, final int allyCivID) {
        float out = 100.0f;
        if (Game.getCiv(civID).getCapitalProvinceID() >= 0 && Game.getCiv(allyCivID).getCapitalProvinceID() >= 0) {
            out += (1.0f - Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(allyCivID).getCapitalProvinceID())) * Game.aiValuesDiplomacy.AI_ALLY_SCORE_DISTANCE;
        }
        if (Game.getCiv(civID).getReligionID() == Game.getCiv(allyCivID).getReligionID()) {
            out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_RELIGION_THE_SAME;
        }
        else if (Game.religionManager.getReligion(Game.getCiv(civID).getReligionID()).ReligionGroupID == Game.religionManager.getReligion(Game.getCiv(allyCivID).getReligionID()).ReligionGroupID) {
            out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_RELIGION_THE_SAME_GROUP;
        }
        else {
            out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_RELIGION_DIFFERENT_GROUP;
        }
        out += Game.getCiv(civID).diplomacy.getRelation(allyCivID) / 100.0f * Game.aiValuesDiplomacy.AI_ALLY_SCORE_RELATIONS;
        if (Game.getCiv(civID).getIdeologyID() == Game.getCiv(allyCivID).getIdeologyID()) {
            out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_GOVERNMENT_THE_SAME;
        }
        else if (Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).GOV_GROUP_ID == Game.ideologiesManager.getIdeology(Game.getCiv(allyCivID).getIdeologyID()).GOV_GROUP_ID) {
            out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_GOVERNMENT_THE_SAME_GROUP;
        }
        else {
            out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_GOVERNMENT_DIFFERENT_GROUP;
        }
        if (Game.aiValuesDiplomacy.AI_ALLY_SCORE_RANK_SCORE > 0.0f) {
            if (Game.getCiv(civID).iCivRankScore > Game.getCiv(allyCivID).iCivRankScore) {
                out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_RANK_SCORE * (Game.getCiv(allyCivID).iCivRankScore / Game.getCiv(civID).iCivRankScore);
            }
            else {
                out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_RANK_SCORE * (Game.getCiv(civID).iCivRankScore / Game.getCiv(allyCivID).iCivRankScore);
            }
        }
        if (Game.aiValuesDiplomacy.AI_ALLY_SCORE_HAVE_THE_SAME_RIVAL > 0.0f && Game.getCiv(civID).diplomacy.haveTheSameRival(civID, allyCivID)) {
            out += Game.aiValuesDiplomacy.AI_ALLY_SCORE_HAVE_THE_SAME_RIVAL;
        }
        return out;
    }
    
    public static boolean sendAllianceProposal(final int civID, final int allyCivID) {
        if (Game.getCiv(civID).fDiplomacy >= GameValues.diplomacy.DIPLOMACY_ALLIANCE_COST && DiplomacyManager.getAlliance_Score(civID, allyCivID) >= 0) {
            DiplomacyManager.offerAlliance(civID, allyCivID);
            return true;
        }
        return false;
    }
}
