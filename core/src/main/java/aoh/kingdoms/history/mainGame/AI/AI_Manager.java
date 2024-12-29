// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_ReorganizeArmies;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_MoveAtPeace;
import aoc.kingdoms.lukasz.jakowski.AI.Nukes.AI_Nuke;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_MoveAtWar;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_Generals;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_RecruitArmy;
import aoc.kingdoms.lukasz.jakowski.AI.Build.AI_Build;
import aoc.kingdoms.lukasz.jakowski.AI.Invest.AI_DevelopInfrastructure;
import aoc.kingdoms.lukasz.jakowski.AI.Invest.AI_GrowthRate;
import aoc.kingdoms.lukasz.jakowski.AI.Invest.AI_IncreaseManpower;
import aoc.kingdoms.lukasz.jakowski.AI.Invest.AI_IncreaseTaxEfficiency;
import aoc.kingdoms.lukasz.jakowski.AI.Invest.AI_InvestInEconomy;
import aoc.kingdoms.lukasz.jakowski.AI.Build.AI_BuildResearch;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerData;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Manager
{
    public static final float SCORE_NOT_POSSIBLE = -999999.0f;
    public static AI_Budget aiBudget;
    
    public final void updateAll() {
    }
    
    public final void update() {
    }
    
    public final void updateBuildInvest() {
        int i;
        for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_INVEST; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_INVEST) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_BuildInvest(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        if (i == Game.player.iCivID) {
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_INVEST;
        }
        while (i < Game.getCivsSize()) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_BuildInvest(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_INVEST;
        }
    }
    
    public final void updateCoresReligion() {
        int i;
        for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_CORES_RELIGION; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CORES_RELIGION) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_CoresReligion(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        if (i == Game.player.iCivID) {
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CORES_RELIGION;
        }
        while (i < Game.getCivsSize()) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_CoresReligion(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_CORES_RELIGION;
        }
    }
    
    public final void updateRecruitArmy() {
        int i;
        for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ARMY_AT_PEACE; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ARMY_AT_PEACE) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_RecruitArmy(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        if (i == Game.player.iCivID) {
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ARMY_AT_PEACE;
        }
        while (i < Game.getCivsSize()) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_RecruitArmy(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ARMY_AT_PEACE;
        }
    }
    
    public final void updateRecruitGenerals() {
        int i;
        for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_GENERALS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_GENERALS) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_RecruitGenerals(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        if (i == Game.player.iCivID) {
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_GENERALS;
        }
        while (i < Game.getCivsSize()) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_RecruitGenerals(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_GENERALS;
        }
    }
    
    public final void updateReorganizeArmiesAtPeace() {
        int i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_REORGANIZE_ARMIES_AT_PEACE;
        if (i == 1) {
            final PlayerData playerData = Game.player.playerData;
            ++playerData.reorganizeArmyStep;
            if (Game.player.playerData.reorganizeArmyStep > 10) {
                Game.player.playerData.reorganizeArmyStep = 0;
            }
        }
        while (i < Game.player.iCivID) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_ReorganizeArmiesAtPeace_Regiments(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            else {
                try {
                    if (Game.getCiv(i).iArmyPositionSize > 0) {
                        Game.getCiv(i).removeAllArmies();
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_REORGANIZE_ARMIES_AT_PEACE;
        }
        if (i == Game.player.iCivID) {
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_REORGANIZE_ARMIES_AT_PEACE;
        }
        while (i < Game.getCivsSize()) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_ReorganizeArmiesAtPeace_Regiments(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            else {
                try {
                    if (Game.getCiv(i).iArmyPositionSize > 0) {
                        Game.getCiv(i).removeAllArmies();
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_REORGANIZE_ARMIES_AT_PEACE;
        }
    }
    
    public final void updateMoveArmies() {
        int i;
        for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_ARMIES; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_ARMIES) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_MoveUnits(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        if (i == Game.player.iCivID) {
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_ARMIES;
        }
        while (i < Game.getCivsSize()) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                try {
                    this.update_MoveUnits(i);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MOVE_ARMIES;
        }
    }
    
    public final void update_BuildInvest(final int civID) {
        if (Game.getCiv(civID).fGold >= GameValues.gameUpdateAI.GAME_UPDATE_AI_BUILD_INVEST_IF_GOLD_OVER) {
            if (!Game.aiValuesBuild.BUILD_INVEST_AT_WAR && Game.getCiv(civID).diplomacy.isAtWar()) {
                return;
            }
            final int rand = Game.oR.nextInt(100);
            if (Game.aiValuesBuild.BUILD_PRIORITIZE_RESEARCH && Game.getCiv(civID).fResearchPerMonth < TechnologyTree.getMaxResearch(civID) * Game.aiValuesBuild.BUILD_PRIORITIZE_RESEARCH_PERC) {
                AI_BuildResearch.buildResearchBuilding(civID, Game.aiValuesBuild.BUILD_PRIORITIZE_RESEARCH_BUILD_LIMIT + ((Game.aiValuesBuild.BUILD_PRIORITIZE_RESEARCH_BUILD_LIMIT_RANDOM > 0) ? Game.oR.nextInt(Game.aiValuesBuild.BUILD_PRIORITIZE_RESEARCH_BUILD_LIMIT_RANDOM) : 0));
            }
            if (Game.getCiv(civID).fTotalIncomePerMonth < Game.aiValuesBuild.BUILD_PRIORITIZE_ECONOMY_IF_INCOME_BELOW) {
                AI_InvestInEconomy.investInEconomy(civID, (float)Game.aiValuesBuild.INVEST_IN_ECONOMY_MIN_LEFT_GOLD);
            }
            if (rand < Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_INVEST_IN_ECONOMY) {
                AI_InvestInEconomy.investInEconomy(civID, (float)Game.aiValuesBuild.INVEST_IN_ECONOMY_MIN_LEFT_GOLD);
            }
            else if (rand < Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_INCREASE_TAX_EFFICIENCY) {
                AI_IncreaseTaxEfficiency.increaseTaxEfficiency(civID, (float)Game.aiValuesBuild.INCREASE_TAX_EFFICIENCY_MIN_LEFT_GOLD);
            }
            else if (rand < Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_INCREASE_MANPOWER) {
                AI_IncreaseManpower.increaseManpower(civID, (float)Game.aiValuesBuild.INCREASE_MANPOWER_MIN_LEFT_GOLD);
            }
            else if (rand < Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_INCREASE_GROWTH_RATE) {
                AI_GrowthRate.increaseGrowthRate(civID, (float)Game.aiValuesBuild.INCREASE_GROWTH_RATE_MIN_LEFT_GOLD);
            }
            else if (rand < Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_DEVELOP_INFRASTRUCTURE) {
                AI_DevelopInfrastructure.developInfrastructure(civID, (float)Game.aiValuesBuild.DEVELOP_INFRASTRUCTURE_MIN_LEFT_GOLD);
            }
            else if (rand < Game.ideologiesManager.getIdeology(Game.getCiv(civID).getIdeologyID()).AI_BUILD_BUILDING) {
                AI_Build.build(civID);
            }
        }
    }
    
    public final void update_RecruitArmy(final int civID) {
        if (Game.getCiv(civID).fGold >= GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_ARMY_IF_GOLD_OVER) {
            Game.getCiv(civID).aiCivCreateNewArmy.runCreateNewArmy_Task(civID);
            AI_RecruitArmy.recruitArmy(civID);
        }
        else {
            Game.getCiv(civID).aiCivCreateNewArmy.runCreateNewArmy_Expired(civID);
        }
    }
    
    public final void update_RecruitGenerals(final int civID) {
        if (Game.getCiv(civID).fGold >= GameValues.gameUpdateAI.GAME_UPDATE_AI_RECRUIT_GENERALS_IF_GOLD_OVER) {
            AI_Generals.recruitGenerals(civID);
        }
    }
    
    public final void update_CoresReligion(final int civID) {
        if (Game.getCiv(civID).fGold >= GameValues.gameUpdateAI.GAME_UPDATE_AI_CORES_RELIGION_IF_GOLD_OVER) {
            if (Game.oR.nextInt(100) < 50) {
                AI_ConvertReligion.convertReligion(civID);
                AI_Cores.createCore(civID);
            }
            else {
                AI_Cores.createCore(civID);
                AI_ConvertReligion.convertReligion(civID);
            }
        }
    }
    
    public final void update_MoveUnits(final int civID) {
        if (Game.getCiv(civID).diplomacy.isAtWar()) {
            AI_MoveAtWar.moveAtWar(civID);
            AI_Nuke.useNukes(civID);
            this.update_RecruitArmy(civID);
            this.update_RecruitGenerals(civID);
        }
        else {
            AI_MoveAtPeace.moveAtPeace(civID);
        }
    }
    
    public final void update_ReorganizeArmiesAtPeace(final int civID) {
        if (!Game.getCiv(civID).diplomacy.isAtWar()) {
            AI_ReorganizeArmies.reorganizeArmies_AtPeace(civID);
        }
    }
    
    public final void update_ReorganizeArmiesAtPeace_Regiments(final int civID) {
        if (!Game.getCiv(civID).diplomacy.isAtWar()) {
            if (Game.getCiv(civID).iRegiments > 2500) {
                if (Game.player.playerData.reorganizeArmyStep == 0) {
                    AI_ReorganizeArmies.reorganizeArmies_AtPeace(civID);
                }
            }
            else if (Game.getCiv(civID).iRegiments > 1000) {
                if (Game.player.playerData.reorganizeArmyStep % 5 == 0) {
                    AI_ReorganizeArmies.reorganizeArmies_AtPeace(civID);
                }
            }
            else if (Game.getCiv(civID).iRegiments > 500) {
                if (Game.player.playerData.reorganizeArmyStep % 2 == 0) {
                    AI_ReorganizeArmies.reorganizeArmies_AtPeace(civID);
                }
            }
            else {
                AI_ReorganizeArmies.reorganizeArmies_AtPeace(civID);
            }
        }
    }
    
    public final int getRandomProvince(final int iCivID) {
        return Game.getCiv(iCivID).getProvinceID(Game.oR.nextInt(Game.getCiv(iCivID).getNumOfProvinces()));
    }
    
    static {
        AI_Manager.aiBudget = new AI_Budget();
    }
}
