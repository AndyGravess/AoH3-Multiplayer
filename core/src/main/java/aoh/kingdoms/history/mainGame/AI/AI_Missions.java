// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.events.EventOption;
import aoc.kingdoms.lukasz.jakowski.Missions.Mission;
import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Missions
{
    public static final void updateMissions() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateMissions(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateMissions(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateMissions(final int civID) {
        try {
            if (Game.getCiv(civID).iMissionsSize > 0) {
                for (int i = 0; i < Game.getCiv(civID).iMissionsSize; ++i) {
                    if (MissionTree.canRunMission_Civ(civID, i) && Game.getCiv(civID).lMissions.get(i).event.options.size() > 0) {
                        int score = 0;
                        for (int a = 0; a < Game.getCiv(civID).lMissions.get(i).event.options.size(); ++a) {
                            score += (int)Game.getCiv(civID).lMissions.get(i).event.options.get(a).ai;
                        }
                        int takeID = 0;
                        if (score > 0) {
                            score = Game.oR.nextInt(score);
                            int a2 = 0;
                            final int currentScore = 0;
                            while (a2 < Game.getCiv(civID).lMissions.get(i).event.options.size()) {
                                if (score <= currentScore + Game.getCiv(civID).lMissions.get(i).event.options.get(a2).ai) {
                                    takeID = a2;
                                    break;
                                }
                                ++a2;
                            }
                        }
                        Game.getCiv(civID).eventsDataVariables.addVariable(Game.getCiv(civID).lMissions.get(i).event.id);
                        Game.getCiv(civID).lMissions.get(i).event.options.get(takeID).executeOutcome(civID);
                    }
                }
            }
            else {
                for (int i = 0; i < MissionTree.iMissionsSize; ++i) {
                    if (MissionTree.canRunMission(civID, i) && MissionTree.lMissions.get(i).event.options.size() > 0) {
                        int score = 0;
                        for (int a = 0; a < MissionTree.lMissions.get(i).event.options.size(); ++a) {
                            score += (int)MissionTree.lMissions.get(i).event.options.get(a).ai;
                        }
                        int takeID = 0;
                        if (score > 0) {
                            score = Game.oR.nextInt(score);
                            int a2 = 0;
                            final int currentScore = 0;
                            while (a2 < MissionTree.lMissions.get(i).event.options.size()) {
                                if (score <= currentScore + MissionTree.lMissions.get(i).event.options.get(a2).ai) {
                                    takeID = a2;
                                    break;
                                }
                                ++a2;
                            }
                        }
                        Game.getCiv(civID).eventsDataVariables.addVariable(MissionTree.lMissions.get(i).event.id);
                        MissionTree.lMissions.get(i).event.options.get(takeID).executeOutcome(civID);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
