// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Government;

import java.util.List;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.AI.Colonization.AI_ColonizationTribal;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Government
{
    public static final void updateGovernment() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_CHANGE_GOVERNMENT; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CHANGE_GOVERNMENT) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateGovernment(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CHANGE_GOVERNMENT;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateGovernment(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CHANGE_GOVERNMENT;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateGovernment(final int civID) {
        if (((Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).CITY_STATE && Game.getCiv(civID).getNumOfProvinces() > Game.aiValues.CHANGE_GOVERNMENT_CITY_STATE_IF_PROVINCES_OVER) || (Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).TRIBAL && Game.getCiv(civID).getNumOfProvinces() > Game.aiValues.CHANGE_GOVERNMENT_TRIBAL_IF_PROVINCES_OVER) || (Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).MUST_BE_CHANGED && Game_Calendar.TURN_ID > Game.aiValues.CHANGE_GOVERNMENT_MUST_BE_CHANGED_IF_TURN_OVER)) && !Game.getCiv(civID).diplomacy.isAtWar()) {
            if (GameValues.colonization.AI_DONT_CHANGE_GOVERNMENT_IF_CAN_COLONIZE && Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).TRIBAL && AI_ColonizationTribal.canColonize(civID)) {
                AI_ColonizationTribal.colonize(civID);
                return;
            }
            final List<Integer> possible = new ArrayList<Integer>();
            for (int i = 0; i < Game.ideologiesManager.getIdeologiesSize(); ++i) {
                if ((Game.ideologiesManager.getIdeology(i).REQUIRED_TECHNOLOGY < 0 || Game.getCiv(civID).getTechResearched(Game.ideologiesManager.getIdeology(i).REQUIRED_TECHNOLOGY)) && !Game.ideologiesManager.getIdeology(i).CITY_STATE && !Game.ideologiesManager.getIdeology(i).TRIBAL && !Game.ideologiesManager.getIdeology(i).REVOLUTIONISTS && !Game.ideologiesManager.getIdeology(i).MUST_BE_CHANGED) {
                    possible.add(i);
                }
            }
            if (!possible.isEmpty()) {
                Game.ideologiesManager.changeGovernmentType(civID, possible.get(Game.oR.nextInt(possible.size())), true);
                possible.clear();
            }
        }
    }
}
