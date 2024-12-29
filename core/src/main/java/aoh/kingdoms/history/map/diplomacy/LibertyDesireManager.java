// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.diplomacy;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class LibertyDesireManager
{
    public static void updateLibertyDesire() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_VASSAL_LIBERTY_DESIRE; i < Game.getCivsSize(); i += GameValues.gameUpdate.GAME_UPDATE_VASSAL_LIBERTY_DESIRE) {
                if (Game.getCiv(i).diplomacy.iVassalsSize > 0) {
                    if (Game.getCiv(i).getNumOfProvinces() > 0) {
                        for (int a = 0; a < Game.getCiv(i).diplomacy.iVassalsSize; ++a) {
                            final int vassalID = Game.getCiv(i).diplomacy.lVassals.get(a).c;
                            Game.getCiv(i).diplomacy.lVassals.get(a).setLibertyDesire_Change(getUpdateLibertyDesireValue(i, vassalID));
                            if (vassalID != Game.player.iCivID && Game.getCiv(i).diplomacy.lVassals.get(a).lD > GameValues.vassal.AI_DAMAGE_RELATIONS_IF_LIBERTY_DESIRE_OVER) {
                                Game.getCiv(vassalID).diplomacy.addDamageRelations(vassalID, i);
                            }
                        }
                    }
                    else {
                        for (int a = Game.getCiv(i).diplomacy.iVassalsSize - 1; a >= 0; --a) {
                            final int vassalID = Game.getCiv(i).diplomacy.lVassals.get(a).c;
                            Game.getCiv(vassalID).setPuppetOfCivID(vassalID);
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static float getUpdateLibertyDesireValue(final int lordID, final int vassalID) {
        float out = GameValues.vassal.LIBERTY_DESIRE_CHANGE_PER_UPDATE + Game.getCiv(vassalID).diplomacy.getRelation(lordID) * GameValues.vassal.LIBERTY_DESIRE_CHANGE_PER_RELATION;
        if (!Game.getCiv(lordID).diplomacy.getVassal_CanDeclareWar(vassalID)) {
            out += GameValues.vassal.LIBERTY_DESIRE_CANT_DECLARE_WAR;
        }
        if (Game.getCiv(vassalID).iRegimentsLimit > Game.getCiv(lordID).iRegimentsLimit) {
            out += GameValues.vassal.LIBERTY_DESIRE_PER_REGIMENT_MORE_THAN_LORD * (Game.getCiv(vassalID).iRegimentsLimit - Game.getCiv(lordID).iRegimentsLimit);
        }
        else {
            out += GameValues.vassal.LIBERTY_DESIRE_PER_REGIMENT_LESS_THAN_LORD * (Game.getCiv(lordID).iRegimentsLimit - Game.getCiv(vassalID).iRegimentsLimit);
        }
        if (Game.getCiv(vassalID).iCivRankPosition < Game.getCiv(lordID).iCivRankPosition) {
            out += GameValues.vassal.LIBERTY_DESIRE_IF_RANK_POSITION_HIGHER_THAN_LORD;
        }
        else {
            out += GameValues.vassal.LIBERTY_DESIRE_IF_NOT_RANK_POSITION_HIGHER_THAN_LORD;
        }
        return out;
    }
}
