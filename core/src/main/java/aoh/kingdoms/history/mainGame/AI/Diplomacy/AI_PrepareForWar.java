// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_PrepareForWar
{
    public static final void prepareForWar() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_PREPARE_FOR_WAR; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_PREPARE_FOR_WAR) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    prepareForWar(i, 0);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_PREPARE_FOR_WAR;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    prepareForWar(i, 0);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_PREPARE_FOR_WAR;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void prepareForWar(final int civID, final int extraWarChance) {
        try {
            final Civilization civ = Game.getCiv(civID);
            if (civ.fGold < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_ONLY_IF_GOLD_OVER) {
                civ.aiCivDiplomacy.clearPrepareForWar();
                return;
            }
            if (civ.civStability_LostFrom100 > Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_ONLY_IF_CIV_STABILITY_IS_BELOW_100) {
                return;
            }
            if (civ.getAggressiveExpansion() > Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_ONLY_IF_AE_IS_BELOW) {
                return;
            }
            if (civ.fManpower / civ.fManpowerMax < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_ONLY_IF_MANPOWER_PERC_OVER) {
                return;
            }
            if (civ.getPuppetOfCivID() != civID && !Game.getCiv(civ.getPuppetOfCivID()).diplomacy.getVassal_CanDeclareWar(civID)) {
                return;
            }
            if (civ.diplomacy.isAtWar()) {
                return;
            }
            if (!civ.aiCivDiplomacy.w.isEmpty()) {
                return;
            }
            if (Game.oR.nextInt(Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_RANDOM_MAX) > Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHANCE + Game.getCiv(civID).getExtraAggressiveness() + Game.ideologiesManager.getIdeology(civ.getIdeologyID()).AI_EXTRA_AGGRESSIVENESS + GameValues.civRank.CIV_RANK_AI_EXTRA_AGGRESSIVENESS[Game.getCiv(civID).iCivRankID] + Game.aiAggressivnes + extraWarChance) {
                return;
            }
            final List<Integer> possibleCivs = new ArrayList<Integer>();
            if (Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_PRIORITIZE_TRIBAL && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_PRIORITIZE_NEIGBORS_TRIBAL_CHANCE && !Game.ideologiesManager.getIdeology(civ.getIdeologyID()).TRIBAL && !Game.religionManager.getReligion(civ.getReligionID()).Tribal) {
                for (int i = 0; i < civ.civNeighbors.civsSize; ++i) {
                    if ((Game.ideologiesManager.getIdeology(Game.getCiv(civ.civNeighbors.civs.get(i).civID).getIdeologyID()).TRIBAL || Game.religionManager.getReligion(Game.getCiv(civ.civNeighbors.civs.get(i).civID).getReligionID()).Tribal) && Game.getCiv(civ.civNeighbors.civs.get(i).civID).getNumOfProvinces() > 0 && !isCivAlly(civID, civ.civNeighbors.civs.get(i).civID) && civ.getResearchedTechnologies() > Game.getCiv(civ.civNeighbors.civs.get(i).civID).getResearchedTechnologies() && civ.getNumOfProvinces() > Game.getCiv(civ.civNeighbors.civs.get(i).civID).getNumOfProvinces()) {
                        possibleCivs.add(civ.civNeighbors.civs.get(i).civID);
                    }
                }
            }
            if (possibleCivs.isEmpty() && civ.iCivRankPosition < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_TOP_RANK_CIVS && civ.haveAccessSea && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_TOP_RANK_CIVS_CHANCE) {
                final List<CivDistance> distance = new ArrayList<CivDistance>();
                for (int j = 1; j < civID; ++j) {
                    if (Game.getCiv(j).getNumOfProvinces() > 0 && (Game.ideologiesManager.getIdeology(Game.getCiv(j).getIdeologyID()).TRIBAL || Game.religionManager.getReligion(Game.getCiv(j).getReligionID()).Tribal)) {
                        distance.add(new CivDistance(j, Game.getManhattanDistance_PercOfMax(civ.getCapitalProvinceID(), Game.getCiv(j).getCapitalProvinceID()) * (Game.continents.lContinents.get(Game.getProvince(Game.getCiv(j).getCapitalProvinceID()).getContinent()).prioritizeColonization ? Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_PRIORITIZE_CONTINENT_MODIFIER : 1.0f)));
                    }
                }
                for (int j = civID + 1; j < Game.getCivsSize(); ++j) {
                    if (Game.getCiv(j).getNumOfProvinces() > 0 && (Game.ideologiesManager.getIdeology(Game.getCiv(j).getIdeologyID()).TRIBAL || Game.religionManager.getReligion(Game.getCiv(j).getReligionID()).Tribal)) {
                        distance.add(new CivDistance(j, Game.getManhattanDistance_PercOfMax(civ.getCapitalProvinceID(), Game.getCiv(j).getCapitalProvinceID()) * (Game.continents.lContinents.get(Game.getProvince(Game.getCiv(j).getCapitalProvinceID()).getContinent()).prioritizeColonization ? Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_PRIORITIZE_CONTINENT_MODIFIER : 1.0f)));
                    }
                }
                for (int j = 0; !distance.isEmpty() && j < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_LIMIT; ++j) {
                    int bestID = 0;
                    for (int k = 1, jSize = distance.size(); k < jSize; ++k) {
                        if (distance.get(bestID).distance > distance.get(k).distance) {
                            bestID = k;
                        }
                    }
                    if (!isCivAlly(civID, distance.get(bestID).civID)) {
                        possibleCivs.add(distance.get(bestID).civID);
                    }
                    else {
                        --j;
                    }
                    distance.remove(bestID);
                }
            }
            if (possibleCivs.isEmpty()) {
                boolean conquerVassal = false;
                if (civ.diplomacy.iVassalsSize > 0 && civ.diplomacy.iVassalsSize > Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).AI_CONQUER_OWN_VASSALS_IF_OVER && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_CONQUER_OWN_VASSALS_CHANCE) {
                    for (int j = 0; j < civ.civNeighbors.civsSize; ++j) {
                        if (Game.getCiv(civ.civNeighbors.civs.get(j).civID).getPuppetOfCivID() == civID && !isCivAlly(civID, civ.civNeighbors.civs.get(j).civID)) {
                            possibleCivs.add(civ.civNeighbors.civs.get(j).civID);
                            conquerVassal = true;
                        }
                    }
                }
                if (!conquerVassal && civ.civNeighbors.civsSize > 0) {
                    for (int j = 0; j < civ.civNeighbors.civsSize; ++j) {
                        if (Game.getCiv(civ.civNeighbors.civs.get(j).civID).getPuppetOfCivID() == civID) {
                            if (Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_AGAINST_OWN_VASSAL_CHANCE && !isCivAlly(civID, civ.civNeighbors.civs.get(j).civID)) {
                                possibleCivs.add(civ.civNeighbors.civs.get(j).civID);
                            }
                            else {
                                for (int l = 0; l < Game.getCiv(civ.civNeighbors.civs.get(j).civID).civNeighbors.civsSize; ++l) {
                                    if (Game.getCiv(civ.civNeighbors.civs.get(j).civID).civNeighbors.civs.get(l).civID != civID && !isCivAlly(civID, Game.getCiv(civ.civNeighbors.civs.get(j).civID).civNeighbors.civs.get(l).civID) && !possibleCivs.contains(Game.getCiv(civ.civNeighbors.civs.get(j).civID).civNeighbors.civs.get(l).civID)) {
                                        possibleCivs.add(Game.getCiv(civ.civNeighbors.civs.get(j).civID).civNeighbors.civs.get(l).civID);
                                    }
                                }
                            }
                        }
                        else if (!isCivAlly(civID, civ.civNeighbors.civs.get(j).civID) && !possibleCivs.contains(civ.civNeighbors.civs.get(j).civID)) {
                            possibleCivs.add(civ.civNeighbors.civs.get(j).civID);
                        }
                    }
                }
            }
            if (possibleCivs.isEmpty() && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CLOSEST_CIV_CHANCE) {
                final List<CivDistance> distance = new ArrayList<CivDistance>();
                for (int j = 1; j < civID; ++j) {
                    if (Game.getCiv(j).getNumOfProvinces() > 0) {
                        distance.add(new CivDistance(j, Game.getManhattanDistance_PercOfMax(civ.getCapitalProvinceID(), Game.getCiv(j).getCapitalProvinceID())));
                    }
                }
                for (int j = civID + 1; j < Game.getCivsSize(); ++j) {
                    if (Game.getCiv(j).getNumOfProvinces() > 0) {
                        distance.add(new CivDistance(j, Game.getManhattanDistance_PercOfMax(civ.getCapitalProvinceID(), Game.getCiv(j).getCapitalProvinceID())));
                    }
                }
                for (int j = 0; !distance.isEmpty() && j < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CLOSEST_CIV_CIVS_LIMIT; ++j) {
                    int bestID = 0;
                    for (int k = 1, jSize = distance.size(); k < jSize; ++k) {
                        if (distance.get(bestID).distance > distance.get(k).distance) {
                            bestID = k;
                        }
                    }
                    if (!isCivAlly(civID, distance.get(bestID).civID)) {
                        possibleCivs.add(distance.get(bestID).civID);
                    }
                    else {
                        --j;
                    }
                    distance.remove(bestID);
                }
            }
            if (!possibleCivs.isEmpty()) {
                for (int a = 0; a < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHECK_LIMIT && !possibleCivs.isEmpty(); ++a) {
                    if (civ.diplomacy.isAtWar()) {
                        break;
                    }
                    int bestID2 = 0;
                    final int rand = Game.oR.nextInt(Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_RANDOM_NUMBER);
                    if (rand < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CLOSEST_CIV_ALL_PROVINCES_CHANCE) {
                        final List<Float> distance2 = new ArrayList<Float>();
                        final int capitalProvinceID = Game.getCiv(civID).getCapitalProvinceID();
                        for (int c = 0; c < possibleCivs.size(); ++c) {
                            float provincesDistance = 0.0f;
                            final Civilization civDistance = Game.getCiv(possibleCivs.get(c));
                            for (int m = 0; m < civDistance.getNumOfProvinces(); ++m) {
                                provincesDistance += Game.getManhattanDistance(capitalProvinceID, civDistance.getProvinceID(m));
                            }
                            distance2.add(provincesDistance / civDistance.getNumOfProvinces());
                        }
                        for (int c = 1; c < possibleCivs.size(); ++c) {
                            if (distance2.get(bestID2) > distance2.get(c)) {
                                bestID2 = c;
                            }
                        }
                    }
                    else if (rand < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CLOSEST_CIV_CAPITAL_CHANCE) {
                        float distance3 = Game.getManhattanDistance_PercOfMax(civ.getCapitalProvinceID(), Game.getCiv(possibleCivs.get(bestID2)).getCapitalProvinceID());
                        for (int c2 = 1; c2 < possibleCivs.size(); ++c2) {
                            if (distance3 > Game.getManhattanDistance_PercOfMax(civ.getCapitalProvinceID(), Game.getCiv(possibleCivs.get(c2)).getCapitalProvinceID())) {
                                distance3 = Game.getManhattanDistance_PercOfMax(civ.getCapitalProvinceID(), Game.getCiv(possibleCivs.get(c2)).getCapitalProvinceID());
                                bestID2 = c2;
                            }
                        }
                    }
                    else if (rand < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CIV_PROVINCES_CHANCE) {
                        for (int c3 = 1; c3 < possibleCivs.size(); ++c3) {
                            if (Game.getCiv(possibleCivs.get(bestID2)).getNumOfProvinces() + Game.getCiv(Game.getCiv(possibleCivs.get(bestID2)).getPuppetOfCivID()).getNumOfProvinces() > Game.getCiv(Game.getCiv(possibleCivs.get(c3)).getPuppetOfCivID()).getNumOfProvinces() + Game.getCiv(possibleCivs.get(c3)).getNumOfProvinces()) {
                                bestID2 = c3;
                            }
                        }
                    }
                    else if (rand < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CIV_MANPOWER_MAX_CHANCE) {
                        for (int c3 = 1; c3 < possibleCivs.size(); ++c3) {
                            if (Game.getCiv(possibleCivs.get(bestID2)).fManpowerMax + Game.getCiv(Game.getCiv(possibleCivs.get(bestID2)).getPuppetOfCivID()).fManpowerMax > Game.getCiv(Game.getCiv(possibleCivs.get(c3)).getPuppetOfCivID()).fManpowerMax + Game.getCiv(possibleCivs.get(c3)).fManpowerMax) {
                                bestID2 = c3;
                            }
                        }
                    }
                    else if (rand < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CIV_REGIMENTS_CHANCE) {
                        for (int c3 = 1; c3 < possibleCivs.size(); ++c3) {
                            if (Game.getCiv(possibleCivs.get(bestID2)).iRegiments + Game.getCiv(Game.getCiv(possibleCivs.get(bestID2)).getPuppetOfCivID()).iRegiments > Game.getCiv(Game.getCiv(possibleCivs.get(c3)).getPuppetOfCivID()).iRegiments + Game.getCiv(possibleCivs.get(c3)).iRegiments) {
                                bestID2 = c3;
                            }
                        }
                    }
                    else {
                        bestID2 = Game.oR.nextInt(possibleCivs.size());
                    }
                    final int onCivID = possibleCivs.get(bestID2);
                    if (Game.getCiv(civID).diplomacy.haveNonAggressionPact(onCivID)) {
                        civ.diplomacy.addDamageRelations(civID, onCivID);
                        possibleCivs.remove(bestID2);
                    }
                    else if (!AI_DeclareWar.checkRegiments_ForWar(civID, onCivID)) {
                        possibleCivs.remove(bestID2);
                    }
                    else {
                        if (civ.diplomacy.getRelation(onCivID) <= GameValues.war.RELATIONS_TO_DECLARE_WAR && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_DECLARE_WAR_IMMEDIATELY_IF_POSSIBLE_CHANCE && AI_DeclareWar.declareWar(civID, onCivID)) {
                            civ.updateMilitaryLevel(Math.max(GameValues.war.AI_AT_WAR_MIN_MILITARY_LEVEL, Game.getCiv(civID).getMilitaryLevel()));
                            possibleCivs.clear();
                            return;
                        }
                        if (civ.diplomacy.getRelation(onCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR * Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_DECREASE_RELATIONS_MODIFIER) {
                            civ.diplomacy.addDamageRelations(civID, onCivID);
                        }
                        if ((civ.diplomacy.getRelation(onCivID) > Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_SEND_INSULT_IF_RELATIONS_OVER || Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_SEND_INSULT_IF_RELATIONS_OVER_RANDOM_CHANCE) && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_SEND_INSULT_CHANCE) {
                            DiplomacyManager.sendInsult(civID, onCivID);
                        }
                        if (civ.aiCivDiplomacy.addPrepareForWar(onCivID)) {
                            Game.gameThread.addAI_SimpleTask(new Game.SimpleTask("update_ReorganizeArmiesAtPeace" + civID, civID) {
                                @Override
                                public void update() {
                                    Game.aiManager.update_ReorganizeArmiesAtPeace(this.id);
                                }
                            });
                        }
                        civ.updateMilitaryLevel(Math.max(GameValues.war.AI_AT_WAR_MIN_MILITARY_LEVEL, Game.getCiv(civID).getMilitaryLevel()));
                        if (civ.aiCivDiplomacy.w.size() >= Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CIVS_LIMIT) {
                            break;
                        }
                    }
                }
            }
            possibleCivs.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static boolean isCivAlly(final int civID, final int civB) {
        return DiplomacyManager.isAlly_AllianceCheck(civID, civB) || Game.getCiv(civID).diplomacy.haveNonAggressionPact(civB) || Game.getCiv(civB).diplomacy.haveNonAggressionPact(civID) || Game.getCiv(civID).diplomacy.haveDefensivePact(civB) || Game.getCiv(civB).diplomacy.haveDefensivePact(civID);
    }
    
    public static List<Integer> getClosestCivs(final int civID, final int limit) {
        final List<CivDistance> civs = new ArrayList<CivDistance>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && !DiplomacyManager.isAlly(civID, i)) {
                civs.add(new CivDistance(i, Game.getManhattanDistance(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(i).getCapitalProvinceID())));
            }
        }
        final List<Integer> out = new ArrayList<Integer>();
        while (!civs.isEmpty() && out.size() < limit) {
            int bestID = 0;
            for (int j = civs.size() - 1; j > 0; --j) {
                if (civs.get(bestID).distance > civs.get(j).distance) {
                    bestID = j;
                }
            }
            out.add(civs.get(bestID).civID);
            civs.remove(bestID);
        }
        return out;
    }
    
    public static void buildScoreManpower(final int civID, final int civB) {
        if (Game.getCiv(civB).getNumOfProvinces() > 0) {
            Game.getCiv(civB).aiScore = (float)(Game.getCiv(civID).fManpowerMax / Game.getCiv(civB).fManpowerMax);
        }
        else {
            Game.getCiv(civB).aiScore = 999876.0f;
        }
    }
    
    public static boolean differentGovernment(final int civA, final int civB) {
        return Game.getCiv(civA).getIdeologyID() != Game.getCiv(civB).getIdeologyID();
    }
    
    public static boolean differentReligion(final int civA, final int civB) {
        return Game.getCiv(civA).getReligionID() != Game.getCiv(civB).getReligionID();
    }
    
    public static class CivDistance
    {
        public int civID;
        public float distance;
        
        public CivDistance(final int civID, final float distance) {
            this.civID = civID;
            this.distance = distance;
        }
    }
}
