// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.List;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Player
{
    public static void update() {
        if (Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_PLAYER_WEAKNESS == 0 && !Game.SPECTATOR_MODE && !Game.getCiv(Game.player.iCivID).diplomacy.isAtWar() && Game.difficultyID >= GameValues.difficulty.NORMAL_ID) {
            if (Game.getCiv(Game.player.iCivID).iRegiments < Game.aiValuesDiplomacy.AI_PLAYER_LOW_ARMY_REGIMENTS_BELOW && Game.getCiv(Game.player.iCivID).iRegiments / (float)Game.getCiv(Game.player.iCivID).iRegimentsLimit < Game.aiValuesDiplomacy.AI_PLAYER_LOW_ARMY_REGIMENTS_BELOW_PERC_OF_MAX) {
                if (Game.player.playerData.lowArmyTurnID == -1) {
                    Game.player.playerData.lowArmyTurnID = Game_Calendar.TURN_ID;
                }
                else if (Game_Calendar.TURN_ID > Game.player.playerData.lowArmyTurnID + Game.aiValuesDiplomacy.AI_PLAYER_LOW_ARMY_REACT_AFTER_X_DAYS) {
                    Game.player.playerData.lowArmyTurnID = -1;
                    reactOnLowArmy(Game.player.iCivID);
                }
            }
            else {
                Game.player.playerData.lowArmyTurnID = -1;
            }
        }
    }
    
    public static void reactOnLowArmy(final int civID) {
        List<Integer> possibleCivs = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(civID).civNeighbors.civsSize; ++i) {
            if (!DiplomacyManager.isAlly(civID, Game.getCiv(civID).civNeighbors.civs.get(i).civID)) {
                possibleCivs.add(Game.getCiv(civID).civNeighbors.civs.get(i).civID);
            }
        }
        if (possibleCivs.isEmpty()) {
            possibleCivs = AI_PrepareForWar.getClosestCivs(civID, Game.aiValuesDiplomacy.AI_PLAYER_LOW_ARMY_REACT_CIVS_LIMIT);
        }
        if (!possibleCivs.isEmpty()) {
            final int regimentsPlayer = (int)Math.max(Game.getCiv(Game.player.iCivID).iRegimentsLimit * Game.aiValuesDiplomacy.AI_PLAYER_LOW_ARMY_REACT_PLAYERS_REGIMENTS_LIMIT_MODIFIER, (float)AI_DeclareWar.declareWar_CheckRegiments_Defender(possibleCivs.get(0), Game.player.iCivID));
            for (int j = possibleCivs.size() - 1; j >= 0; --j) {
                if (regimentsPlayer > AI_DeclareWar.declareWar_CheckRegiments_Attacker(possibleCivs.get(j), Game.player.iCivID) || Game.getCiv(possibleCivs.get(j)).diplomacy.isAtWar()) {
                    possibleCivs.remove(j);
                }
            }
            if (!possibleCivs.isEmpty()) {
                final Civilization civ = Game.getCiv(possibleCivs.get(Game.oR.nextInt(possibleCivs.size())));
                final int onCivID = Game.player.iCivID;
                civ.aiCivDiplomacy.clearPrepareForWar();
                if (civ.diplomacy.getRelation(onCivID) <= GameValues.war.RELATIONS_TO_DECLARE_WAR && AI_DeclareWar.declareWar(civ.getCivID(), onCivID)) {
                    civ.updateMilitaryLevel(Math.max(GameValues.war.AI_AT_WAR_MIN_MILITARY_LEVEL, Game.getCiv(civ.getCivID()).getMilitaryLevel()));
                    possibleCivs.clear();
                    return;
                }
                if (civ.diplomacy.getRelation(onCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR * Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_DECREASE_RELATIONS_MODIFIER) {
                    civ.diplomacy.addDamageRelations(civ.getCivID(), onCivID);
                }
                if (civ.diplomacy.getRelation(onCivID) > Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_SEND_INSULT_IF_RELATIONS_OVER && Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_SEND_INSULT_CHANCE) {
                    DiplomacyManager.sendInsult(civ.getCivID(), onCivID);
                }
                if (civ.aiCivDiplomacy.addPrepareForWar(onCivID)) {
                    Game.gameThread.addAI_SimpleTask(new Game.SimpleTask("update_ReorganizeArmiesAtPeace" + civ.getCivID(), civ.getCivID()) {
                        @Override
                        public void update() {
                            Game.aiManager.update_ReorganizeArmiesAtPeace(this.id);
                        }
                    });
                }
                civ.updateMilitaryLevel(Math.max(GameValues.war.AI_AT_WAR_MIN_MILITARY_LEVEL, Game.getCiv(civ.getCivID()).getMilitaryLevel()));
            }
        }
    }
}
