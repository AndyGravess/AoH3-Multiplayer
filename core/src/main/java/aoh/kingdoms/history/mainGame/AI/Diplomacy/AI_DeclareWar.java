// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Diplomacy;

import java.util.List;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_DeclareWar
{
    public static final void declareWar() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_DECLARE_WAR; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DECLARE_WAR) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    declareWar(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DECLARE_WAR;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    declareWar(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_DECLARE_WAR;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void declareWar(final int civID) {
        if (Game.getCiv(civID).fGold < Game.aiValuesDiplomacy.AI_DECLARE_WAR_ONLY_IF_GOLD_OVER) {
            Game.getCiv(civID).aiCivDiplomacy.clearPrepareForWar();
            return;
        }
        if (Game.getCiv(civID).diplomacy.isAtWar() && !Game.aiValuesDiplomacy.AI_CAN_DECLARE_WAR_IF_AT_WAR) {
            return;
        }
        if (!Game.getCiv(civID).aiCivDiplomacy.w.isEmpty()) {
            for (int i = Game.getCiv(civID).aiCivDiplomacy.w.size() - 1; i >= 0; --i) {
                if (Game.getCiv(Game.getCiv(civID).aiCivDiplomacy.w.get(i).c).getNumOfProvinces() <= 0 || AI_PrepareForWar.isCivAlly(civID, Game.getCiv(civID).aiCivDiplomacy.w.get(i).c)) {
                    Game.getCiv(civID).aiCivDiplomacy.w.remove(i);
                }
            }
        }
        if (Game.getCiv(civID).civStability_LostFrom100 > Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_ONLY_IF_CIV_STABILITY_IS_BELOW_100) {
            return;
        }
        if (Game.getCiv(civID).getAggressiveExpansion() > Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_ONLY_IF_AE_IS_BELOW) {
            return;
        }
        if (Game.getCiv(civID).fManpower / Game.getCiv(civID).fManpowerMax < Game.aiValuesDiplomacy.AI_DECLARE_WAR_ONLY_IF_MANPOWER_PERC_OVER) {
            return;
        }
        if (!Game.getCiv(civID).aiCivDiplomacy.w.isEmpty()) {
            for (int i = 0; i < Math.min(Game.aiValuesDiplomacy.AI_DECLARE_WARS_LIMIT_PER_UPDATE, Game.getCiv(civID).aiCivDiplomacy.w.size()) && !Game.getCiv(civID).aiCivDiplomacy.w.isEmpty(); ++i) {
                if (Game.getCiv(Game.getCiv(civID).aiCivDiplomacy.w.get(i).c).getNumOfProvinces() == 0) {
                    Game.getCiv(civID).aiCivDiplomacy.w.remove(i);
                    AI_PrepareForWar.prepareForWar(civID, Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_RANDOM_MAX / 2);
                }
                else if (Game_Calendar.TURN_ID >= Game.getCiv(civID).aiCivDiplomacy.w.get(i).t) {
                    if (Game.getCiv(civID).diplomacy.getRelation(Game.getCiv(civID).aiCivDiplomacy.w.get(i).c) <= GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                        if (!declareWar(civID, Game.getCiv(civID).aiCivDiplomacy.w.get(i).c)) {
                            Game.getCiv(civID).aiCivDiplomacy.w.remove(i--);
                            AI_PrepareForWar.prepareForWar(civID, Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_RANDOM_MAX / 2);
                        }
                        else {
                            Game.getCiv(civID).aiCivDiplomacy.w.remove(i--);
                        }
                    }
                    else {
                        Game.getCiv(civID).diplomacy.addDamageRelations(civID, Game.getCiv(civID).aiCivDiplomacy.w.get(i).c);
                        if (Game.oR.nextInt(100) < Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_SEND_INSULT_CHANCE) {
                            DiplomacyManager.sendInsult(civID, Game.getCiv(civID).aiCivDiplomacy.w.get(i).c);
                        }
                    }
                }
            }
        }
    }
    
    public static final boolean checkRegiments_ForWar(final int civID, final int onCivID) {
        final List<Integer> alliesCiv = DiplomacyManager.declareWar_AlliesAttacker(civID, onCivID);
        final List<Integer> alliesDefenders = DiplomacyManager.declareWar_AlliesDefender(onCivID, civID);
        int regimentsA = 0;
        int regimentsB = 0;
        try {
            for (int i = alliesCiv.size() - 1; i >= 0; --i) {
                regimentsA += Game.getCiv(alliesCiv.get(i)).getArmyRegimentSize();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i = alliesDefenders.size() - 1; i >= 0; --i) {
                regimentsB += Game.getCiv(alliesDefenders.get(i)).getArmyRegimentSize();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        regimentsA *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER;
        regimentsB *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER_DEFENDERS;
        alliesCiv.clear();
        alliesDefenders.clear();
        regimentsA += Game.getCiv(civID).getArmyRegimentSize();
        return regimentsA >= (regimentsB += (int)(Math.max(Game.getCiv(onCivID).iRegimentsLimit * Game.aiValuesDiplomacy.AI_DECLARE_WAR_DEFENDER_REGIMENTS_LIMIT_MODIFIER, (float)Game.getCiv(onCivID).getArmyRegimentSize()) * Game.aiValuesDiplomacy.AI_DECLARE_WAR_DEFENDER_REGIMENTS_MODIFIER));
    }
    
    public static final boolean declareWar(final int civID, final int onCivID) {
        final List<Integer> alliesCiv = DiplomacyManager.declareWar_AlliesAttacker(civID, onCivID);
        final List<Integer> alliesDefenders = DiplomacyManager.declareWar_AlliesDefender(onCivID, civID);
        int regimentsA = 0;
        int regimentsB = 0;
        try {
            for (int i = alliesCiv.size() - 1; i >= 0; --i) {
                if (alliesCiv.get(i) != civID) {
                    if (alliesCiv.get(i) != onCivID) {
                        regimentsA += Game.getCiv(alliesCiv.get(i)).getArmyRegimentSize();
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i = alliesDefenders.size() - 1; i >= 0; --i) {
                if (alliesDefenders.get(i) != civID) {
                    if (alliesDefenders.get(i) != onCivID) {
                        regimentsB += Game.getCiv(alliesDefenders.get(i)).getArmyRegimentSize();
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        regimentsA *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER;
        regimentsB *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER_DEFENDERS;
        regimentsA += Game.getCiv(civID).getArmyRegimentSize();
        if (regimentsA >= (regimentsB += (int)(Game.getCiv(onCivID).getArmyRegimentSize() * Game.aiValuesDiplomacy.AI_DECLARE_WAR_DEFENDER_REGIMENTS_MODIFIER))) {
            return DiplomacyManager.declareWar(civID, onCivID, false, alliesCiv);
        }
        alliesCiv.clear();
        alliesDefenders.clear();
        return false;
    }
    
    public static final boolean declareWar_CheckRegiments(final int civID, final int onCivID) {
        try {
            final List<Integer> alliesCiv = DiplomacyManager.declareWar_AlliesAttacker(civID, onCivID);
            final List<Integer> alliesDefenders = DiplomacyManager.declareWar_AlliesDefender(onCivID, civID);
            int regimentsA = 0;
            int regimentsB = 0;
            try {
                for (int i = alliesCiv.size() - 1; i >= 0; --i) {
                    regimentsA += Game.getCiv(alliesCiv.get(i)).getArmyRegimentSize();
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                for (int i = alliesDefenders.size() - 1; i >= 0; --i) {
                    regimentsB += Game.getCiv(alliesDefenders.get(i)).getArmyRegimentSize();
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            regimentsA *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER;
            regimentsB *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER_DEFENDERS;
            regimentsA += Game.getCiv(civID).getArmyRegimentSize();
            if (regimentsA >= (regimentsB += (int)(Game.getCiv(onCivID).getArmyRegimentSize() * Game.aiValuesDiplomacy.AI_DECLARE_WAR_DEFENDER_REGIMENTS_MODIFIER))) {
                return DiplomacyManager.declareWar(civID, onCivID, false, alliesCiv);
            }
            alliesCiv.clear();
            alliesDefenders.clear();
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }
    
    public static final int declareWar_CheckRegiments_Attacker(final int civID, final int onCivID) {
        try {
            final List<Integer> alliesCiv = DiplomacyManager.declareWar_AlliesAttacker(civID, onCivID);
            int regimentsA = 0;
            try {
                for (int i = alliesCiv.size() - 1; i >= 0; --i) {
                    regimentsA += Game.getCiv(alliesCiv.get(i)).getArmyRegimentSize();
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            regimentsA *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER;
            return regimentsA += Game.getCiv(civID).getArmyRegimentSize();
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
            return 0;
        }
    }
    
    public static final int declareWar_CheckRegiments_Defender(final int civID, final int onCivID) {
        try {
            final List<Integer> alliesDefenders = DiplomacyManager.declareWar_AlliesDefender(onCivID, civID);
            int regimentsB = 0;
            try {
                for (int i = alliesDefenders.size() - 1; i >= 0; --i) {
                    regimentsB += Game.getCiv(alliesDefenders.get(i)).getArmyRegimentSize();
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            regimentsB *= (int)Game.aiValuesDiplomacy.AI_DECLARE_WAR_ALLIES_REGIMENTS_MODIFIER_DEFENDERS;
            return regimentsB += (int)(Game.getCiv(onCivID).getArmyRegimentSize() * Game.aiValuesDiplomacy.AI_DECLARE_WAR_DEFENDER_REGIMENTS_MODIFIER);
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
            return 0;
        }
    }
}
