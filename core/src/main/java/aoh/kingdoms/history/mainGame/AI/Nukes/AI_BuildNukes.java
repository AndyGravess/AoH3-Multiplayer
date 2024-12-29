// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Nukes;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_BuildNukes
{
    public static void updateBuildNukes() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_NUKES; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_NUKES) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    buildNukes(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_NUKES;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    buildNukes(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_NUKES;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void buildNukes(final int civID) {
        if (Game.getCiv(civID).canBuildNuke && Game.getCiv(civID).getNukes() + Game.getCiv(civID).iNukesSize < GameValues.atomic.AI_NUKES_LIMIT) {
            try {
                if (Game.getCiv(civID).fGold > GameValues.atomic.ATOMIC_BOMB_COST) {
                    if (Game.getCiv(civID).getNuclearReactorLevel() < GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
                        while (Game.getCiv(civID).getNuclearReactorLevel() < GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
                            if (!Game.getCiv(civID).upgradeNuclearReactor()) {
                                return;
                            }
                        }
                    }
                    if (Game.getCiv(civID).getNuclearReactorLevel() >= GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
                        for (int a = 0; a < GameValues.atomic.AI_NUKE_PRODUCTION_LIMIT_PER_ACTION; ++a) {
                            if (!Game.getCiv(civID).addNukeProduction()) {
                                return;
                            }
                        }
                    }
                    if (Game.getCiv(civID).getNuclearReactorLevel() < Game.getNuclearReactor_MaxLvl(civID)) {
                        Game.getCiv(civID).upgradeNuclearReactor();
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
}
