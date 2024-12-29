// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Advisors;

import aoc.kingdoms.lukasz.jakowski.Stats.Stats;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_ManageAdvisors
{
    public static void updateAdvisors() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ADVISORS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ADVISORS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateAdvisors(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ADVISORS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateAdvisors(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ADVISORS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void updateAdvisors(final int civID) {
        final Civilization civ = Game.getCiv(civID);
        if (civ.fGold > GameValues.advisors.RECRUIT_ADVISOR_GOLD_COST && civ.fLegacy > GameValues.advisors.RECRUIT_ADVISOR_LEGACY_COST) {
            final List<Integer> toRecruit = new ArrayList<Integer>();
            if (civ.advisorAdministration.sName == null) {
                toRecruit.add(0);
            }
            if (civ.advisorEconomy.sName == null) {
                toRecruit.add(1);
            }
            if (civ.advisorTechnology.sName == null) {
                toRecruit.add(2);
            }
            if (civ.advisorMilitary.sName == null) {
                toRecruit.add(3);
            }
            while (toRecruit.size() > 0) {
                final int rand = Game.oR.nextInt(toRecruit.size());
                if (!recruitAdvisor(civID, toRecruit.get(rand))) {
                    break;
                }
                toRecruit.remove(rand);
            }
            toRecruit.clear();
            if (civ.fGold > GameValues.advisors.ADVISOR_PROMOTE_COST_PER_LEVEL * 2.0f) {
                if (civ.advisorAdministration.iLevel < AdvisorManager.getAdvisorMaxLevel(civID)) {
                    toRecruit.add(0);
                }
                if (civ.advisorEconomy.iLevel < AdvisorManager.getAdvisorMaxLevel(civID)) {
                    toRecruit.add(1);
                }
                if (civ.advisorTechnology.iLevel < AdvisorManager.getAdvisorMaxLevel(civID)) {
                    toRecruit.add(2);
                }
                if (civ.advisorMilitary.iLevel < AdvisorManager.getAdvisorMaxLevel(civID)) {
                    toRecruit.add(3);
                }
                while (toRecruit.size() > 0) {
                    final int rand = Game.oR.nextInt(toRecruit.size());
                    if (!AdvisorManager.promoteAdvisor(civID, toRecruit.get(rand), false)) {
                        break;
                    }
                    switch (toRecruit.get(rand)) {
                        case 0: {
                            if (civ.advisorAdministration.iLevel >= AdvisorManager.getAdvisorMaxLevel(civID)) {
                                toRecruit.remove(rand);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (civ.advisorEconomy.iLevel >= AdvisorManager.getAdvisorMaxLevel(civID)) {
                                toRecruit.remove(rand);
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (civ.advisorTechnology.iLevel >= AdvisorManager.getAdvisorMaxLevel(civID)) {
                                toRecruit.remove(rand);
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            if (civ.advisorMilitary.iLevel >= AdvisorManager.getAdvisorMaxLevel(civID)) {
                                toRecruit.remove(rand);
                                continue;
                            }
                            continue;
                        }
                    }
                }
            }
            toRecruit.clear();
        }
    }
    
    public static boolean recruitAdvisor(final int iCivID, final int iAdvisorType) {
        if (Game.getCiv(iCivID).fGold < AdvisorManager.getRecruitGoldCost(iCivID)) {
            return false;
        }
        if (Game.getCiv(iCivID).fLegacy < AdvisorManager.getRecruitCostLegacy(iCivID)) {
            return false;
        }
        final Civilization civ = Game.getCiv(iCivID);
        civ.fGold -= AdvisorManager.getRecruitGoldCost(iCivID);
        final Civilization civ2 = Game.getCiv(iCivID);
        civ2.fLegacy -= AdvisorManager.getRecruitCostLegacy(iCivID);
        Game.getCiv(iCivID).eventsData3.addRecruitedAdvisors(1);
        if (iCivID == Game.player.iCivID) {
            final Stats civStats = Game.stats.civStats;
            ++civStats.ra;
        }
        switch (iAdvisorType) {
            case 0: {
                Game.getCiv(iCivID).advisorAdministration = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, iAdvisorType);
                final AdvisorManager advisorManager = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, 1);
                break;
            }
            case 1: {
                Game.getCiv(iCivID).advisorEconomy = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, iAdvisorType);
                final AdvisorManager advisorManager2 = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, 1);
                break;
            }
            case 2: {
                Game.getCiv(iCivID).advisorTechnology = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, iAdvisorType);
                final AdvisorManager advisorManager3 = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, 1);
                break;
            }
            default: {
                Game.getCiv(iCivID).advisorMilitary = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, iAdvisorType);
                final AdvisorManager advisorManager4 = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, 1);
                break;
            }
        }
        return true;
    }
}
