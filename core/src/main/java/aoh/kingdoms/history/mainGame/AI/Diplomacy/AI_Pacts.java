// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Pacts
{
    public static final void update() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_PACTS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_PACTS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    update(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_PACTS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    update(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DIPLOMACY_PACTS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void update(final int civID) {
        if (Game.getCiv(civID).diplomacy.isAtWar()) {
            return;
        }
        if (Game.getCiv(civID).diplomacy.defensivePact.size() + Game.getCiv(civID).diplomacy.nonAggressionPact.size() >= Game.aiValuesDiplomacy.AI_SIGN_PACT_DEFENSIVE_NON_AGGRESSION_LIMIT) {
            return;
        }
        if (Game.getCiv(civID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DEFENSIVE_PACT_COST) {
            return;
        }
        if (Game.getCiv(civID).civNeighbors.civsSize > 0) {
            final Civilization civ = Game.getCiv(civID);
            final List<Integer> possibleCivs = new ArrayList<Integer>();
            for (int i = 0; i < civ.civNeighbors.civsSize; ++i) {
                if (canSignPact(civID, civ.civNeighbors.civs.get(i).civID)) {
                    possibleCivs.add(civ.civNeighbors.civs.get(i).civID);
                }
            }
            if (!possibleCivs.isEmpty()) {
                int bestID = 0;
                for (int j = possibleCivs.size() - 1; j > 0; --j) {
                    if (civ.diplomacy.getRelation(possibleCivs.get(bestID)) < civ.diplomacy.getRelation(possibleCivs.get(j))) {
                        bestID = j;
                    }
                }
                if (Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_SIGN_PACT_DEFENSIVE_CHANCE) {
                    if (DiplomacyManager.getDefensivePact_Score(civID, possibleCivs.get(bestID)) >= 0) {
                        DiplomacyManager.offerDefensivePact(civID, possibleCivs.get(bestID));
                    }
                }
                else if (DiplomacyManager.getNonAggression_Score(civID, possibleCivs.get(bestID)) >= 0) {
                    DiplomacyManager.offerNonAggressionPact(civID, possibleCivs.get(bestID));
                }
            }
        }
    }
    
    public static boolean canSignPact(final int civID, final int withCivID) {
        return Game.getCiv(civID).diplomacy.getRelation(withCivID) >= Game.aiValuesDiplomacy.AI_SIGN_PACT_MIN_RELATIONS && !DiplomacyManager.isAlly_AllianceCheck(civID, withCivID) && !Game.getCiv(civID).diplomacy.isRival(withCivID) && !Game.getCiv(withCivID).diplomacy.isRival(civID) && !Game.getCiv(civID).diplomacy.isDamagingRelations(withCivID) && !Game.getCiv(civID).diplomacy.haveDefensivePact(withCivID) && !Game.getCiv(civID).diplomacy.haveNonAggressionPact(withCivID) && !Game.getCiv(civID).aiCivDiplomacy.isPreparingForWarWithCivID(withCivID) && !Game.getCiv(withCivID).aiCivDiplomacy.isPreparingForWarWithCivID(civID) && !Game.getCiv(civID).aiCivDiplomacy.isPreparingForAllianceWithCivID(withCivID) && !Game.getCiv(withCivID).aiCivDiplomacy.isPreparingForAllianceWithCivID(civID);
    }
}
