// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_VassalLiberty
{
    public static final void update() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_VASSAL_PROCLAIM_INDEPENDENCE; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_VASSAL_PROCLAIM_INDEPENDENCE) {
                if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getPuppetOfCivID() != i) {
                    update(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_VASSAL_PROCLAIM_INDEPENDENCE;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getPuppetOfCivID() != i) {
                    update(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_VASSAL_PROCLAIM_INDEPENDENCE;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void update(final int civID) {
        if (Game.getCiv(Game.getCiv(civID).getPuppetOfCivID()).getNumOfProvinces() <= 0) {
            Game.getCiv(civID).setPuppetOfCivID(civID);
            return;
        }
        if (Game.getCiv(Game.getCiv(civID).getPuppetOfCivID()).diplomacy.getVassal_LibertyDesire(civID) > GameValues.vassal.DECLARE_INDEPENDENCE_MIN_LIBERTY_DESIRE) {
            if (Game.getCiv(civID).getPuppetOfCivID() == Game.player.iCivID && Game.difficultyID <= GameValues.difficulty.NORMAL_ID) {
                Game.getCiv(Game.getCiv(civID).getPuppetOfCivID()).diplomacy.setLibertyDesire_Change(civID, -10000.0f);
                return;
            }
            if (Game.getCiv(civID).aiCivDiplomacy.addPrepareForWar(Game.getCiv(civID).getPuppetOfCivID())) {
                Game.gameThread.addAI_SimpleTask(new Game.SimpleTask("update_ReorganizeArmiesAtPeace" + civID, civID) {
                    @Override
                    public void update() {
                        Game.aiManager.update_ReorganizeArmiesAtPeace(this.id);
                    }
                });
            }
            Game.getCiv(civID).updateMilitaryLevel(Math.max(GameValues.war.AI_AT_WAR_MIN_MILITARY_LEVEL, Game.getCiv(civID).getMilitaryLevel()));
            CFG.LOG("Liberty: " + Game.getCiv(civID).getCivName() + " -> " + Game.getCiv(Game.getCiv(civID).getPuppetOfCivID()).diplomacy.getVassal_LibertyDesire(civID) + "%");
        }
    }
}
