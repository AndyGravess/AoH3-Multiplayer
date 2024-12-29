// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import aoc.kingdoms.lukasz.jakowski.Stats.Stats;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import java.util.List;

public class CivilizationAdvisorsPool
{
    public List<Advisor> lAdvisors;
    public int generateYear;
    public int iAdvisorType;
    
    public CivilizationAdvisorsPool(final int iAdvisorType) {
        this.lAdvisors = new ArrayList<Advisor>();
        this.generateYear = -55000;
        this.iAdvisorType = iAdvisorType;
    }
    
    public final void generateAdvisors(final int iCivID, final String sIMG) {
        for (int i = this.lAdvisors.size(); i < this.getPoolOfAdvisors(iCivID); ++i) {
            int advIMG;
            if (this.iAdvisorType == 3) {
                advIMG = Game.advisorManager.getRandomGeneralImage(iCivID);
            }
            else {
                advIMG = Game.advisorManager.getRandomImage(iCivID, this.iAdvisorType);
            }
            this.lAdvisors.add(new Advisor(Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID), advIMG, Game_Calendar.currentYear - GameValues.advisors.ADVISOR_YEARS_OLD_MIN - Game.oR.nextInt(Math.max(1, GameValues.advisors.ADVISOR_YEARS_OLD_RANDOM)), sIMG));
            final int advID = this.lAdvisors.size() - 1;
            this.lAdvisors.set(advID, buildAdvisorBonuses(this.lAdvisors.get(advID), this.iAdvisorType));
        }
    }
    
    public static final Advisor generateAdvisor_Random(final int iCivID, final int iAdvisorType) {
        int advIMG;
        if (iAdvisorType == 3) {
            advIMG = Game.advisorManager.getRandomGeneralImage(iCivID);
        }
        else {
            advIMG = Game.advisorManager.getRandomImage(iCivID, iAdvisorType);
        }
        return buildAdvisorBonuses(new Advisor(Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID), advIMG, Game_Calendar.currentYear - GameValues.advisors.ADVISOR_YEARS_OLD_MIN - Game.oR.nextInt(Math.max(1, GameValues.advisors.ADVISOR_YEARS_OLD_RANDOM)), null), iAdvisorType);
    }
    
    public static final Advisor buildAdvisorBonuses(final Advisor advisor, final int iAdvisorType) {
        int random = 0;
        if (iAdvisorType == 0) {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 4;
            switch (random) {
                case 0: {
                    advisor.TaxEfficiency = GameValues.advisors.ADVISOR_TAX_EFFICIENCY_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_TAX_EFFICIENCY_RANDOM * 100.0f)) / 100.0f;
                    break;
                }
                case 1: {
                    advisor.ProvinceMaintenance = -(GameValues.advisors.ADVISOR_PROVINCE_MAINTENANCE_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_PROVINCE_MAINTENANCE_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
                case 2: {
                    advisor.AdministrationBuildingsCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_RANDOM * 10000.0f)) / 10000.0f);
                    break;
                }
                case 3: {
                    advisor.IncreaseGrowthRateCost = -(GameValues.advisors.ADVISOR_INCREASE_GROWTH_RATE_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCREASE_GROWTH_RATE_COST_RANDOM * 10000.0f)) / 10000.0f);
                    break;
                }
                default: {
                    advisor.GrowthRate = GameValues.advisors.ADVISOR_GROWTH_RATE_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_GROWTH_RATE_RANDOM * 100.0f)) / 100.0f;
                    break;
                }
            }
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 3;
            switch (random) {
                case 0: {
                    advisor.ConstructionTime = -(GameValues.advisors.ADVISOR_CONSTRUCTION_TIME_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_TIME_RANDOM * 10000.0f)) / 10000.0f);
                    break;
                }
                case 1: {
                    advisor.IncreaseManpowerCost = -(GameValues.advisors.ADVISOR_INCREASE_MANPOWER_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCREASE_MANPOWER_COST_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
                default: {
                    advisor.RecruitmentTime = -(GameValues.advisors.ADVISOR_RECRUITMENT_TIME_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RECRUITMENT_TIME_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
            }
        }
        else if (iAdvisorType == 1) {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(3500)) % 100;
            if (random > 97) {
                advisor.ConstructionCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_COST_RANDOM * 100.0f)) / 100.0f);
            }
            else {
                random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 5;
                switch (random) {
                    case 0: {
                        advisor.EconomyBuildingsCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_RANDOM * 10000.0f)) / 10000.0f);
                        break;
                    }
                    case 1: {
                        advisor.InvestInEconomyCost = -(GameValues.advisors.ADVISOR_INVEST_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INVEST_COST_RANDOM * 10000.0f)) / 10000.0f);
                        break;
                    }
                    case 2: {
                        advisor.IncreaseTaxEfficiencyCost = -(GameValues.advisors.ADVISOR_INCREASE_TAX_EFFICIENCY_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCREASE_TAX_EFFICIENCY_COST_RANDOM * 10000.0f)) / 10000.0f);
                        break;
                    }
                    case 3: {
                        advisor.DevelopInfrastructureCost = -(GameValues.advisors.ADVISOR_DEVELOP_INFRASTRUCTURE_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_DEVELOP_INFRASTRUCTURE_COST_RANDOM * 10000.0f)) / 10000.0f);
                        break;
                    }
                    default: {
                        advisor.ProductionEfficiency = GameValues.advisors.ADVISOR_PRODUCTION_EFFICIENCY_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_PRODUCTION_EFFICIENCY_RANDOM * 100.0f)) / 100.0f;
                        break;
                    }
                }
            }
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 4;
            switch (random) {
                case 0: {
                    advisor.LoanInterest = -(GameValues.advisors.ADVISOR_LOAN_INTEREST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_LOAN_INTEREST_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
                case 1: {
                    advisor.CoreCost = -(GameValues.advisors.ADVISOR_CORE_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CORE_COST_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
                case 2: {
                    advisor.IncomeProduction = GameValues.advisors.ADVISOR_INCOME_PRODUCTION_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCOME_PRODUCTION_RANDOM * 100.0f)) / 100.0f;
                    break;
                }
                default: {
                    advisor.ReligionCost = -(GameValues.advisors.ADVISOR_CONVERT_RELIGION_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONVERT_RELIGION_COST_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
            }
        }
        else if (iAdvisorType == 2) {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 3;
            switch (random) {
                case 0: {
                    advisor.Research = GameValues.advisors.ADVISOR_RESEARCH_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RESEARCH_RANDOM * 100.0f)) / 100.0f;
                    break;
                }
                case 1: {
                    advisor.MonthlyLegacy = GameValues.advisors.ADVISOR_LEGACY_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_LEGACY_RANDOM * 100.0f)) / 100.0f;
                    break;
                }
                default: {
                    advisor.Research = GameValues.advisors.ADVISOR_RESEARCH_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RESEARCH_RANDOM2 * 100.0f)) / 100.0f;
                    break;
                }
            }
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 4;
            switch (random) {
                case 0: {
                    advisor.UnitsAttack = (float)(int)(GameValues.advisors.ADVISOR_UNITS_ATTACK_MIN + ((GameValues.advisors.ADVISOR_UNITS_ATTACK_RANDOM > 0.0f) ? (Game.oR.nextInt((int)GameValues.advisors.ADVISOR_UNITS_ATTACK_RANDOM * 100) / 100.0f) : 0.0f));
                    break;
                }
                case 1: {
                    advisor.UnitsDefense = (float)(int)(GameValues.advisors.ADVISOR_UNITS_DEFENSE_MIN + ((GameValues.advisors.ADVISOR_UNITS_DEFENSE_RANDOM > 0.0f) ? (Game.oR.nextInt((int)GameValues.advisors.ADVISOR_UNITS_DEFENSE_RANDOM * 100) / 100.0f) : 0.0f));
                    break;
                }
                case 2: {
                    advisor.RegimentsLimit = (int)(GameValues.advisors.ADVISOR_REGIMENTS_LIMIT_MIN + ((GameValues.advisors.ADVISOR_REGIMENTS_LIMIT_RANDOM > 0.0f) ? (Game.oR.nextInt((int)GameValues.advisors.ADVISOR_REGIMENTS_LIMIT_RANDOM * 100) / 100.0f) : 0.0f));
                    break;
                }
                default: {
                    advisor.ImproveRelationsModifier = GameValues.advisors.ADVISOR_IMPROVE_RELATIONS_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_IMPROVE_RELATIONS_RANDOM * 100.0f)) / 100.0f;
                    break;
                }
            }
        }
        else {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 5;
            switch (random) {
                case 0: {
                    advisor.GeneralAttack = (float)(int)(GameValues.advisors.ADVISOR_GENERAL_ATTACK_MIN + ((GameValues.advisors.ADVISOR_GENERAL_ATTACK_RANDOM > 0.0f) ? (Game.oR.nextInt((int)GameValues.advisors.ADVISOR_GENERAL_ATTACK_RANDOM * 100) / 100.0f) : 0.0f));
                    break;
                }
                case 1: {
                    advisor.GeneralDefense = (float)(int)(GameValues.advisors.ADVISOR_GENERAL_DEFENSE_MIN + ((GameValues.advisors.ADVISOR_GENERAL_DEFENSE_RANDOM > 0.0f) ? (Game.oR.nextInt((int)GameValues.advisors.ADVISOR_GENERAL_DEFENSE_RANDOM * 100) / 100.0f) : 0.0f));
                    break;
                }
                case 2: {
                    advisor.RecruitArmyCost = -(GameValues.advisors.ADVISOR_RECRUIT_ARMY_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RECRUIT_ARMY_COST_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
                case 3: {
                    advisor.MilitaryBuildingsCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_RANDOM * 10000.0f)) / 10000.0f);
                    break;
                }
                default: {
                    advisor.ArmyMaintenance = -(GameValues.advisors.ADVISOR_ARMY_MAINTENANCE_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_ARMY_MAINTENANCE_RANDOM * 100.0f)) / 100.0f);
                    break;
                }
            }
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 3;
            switch (random) {
                case 0: {
                    advisor.MaxManpower = GameValues.advisors.ADVISOR_MAX_MANPOWER_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_MAX_MANPOWER_RANDOM + 1.0f));
                    break;
                }
                case 1: {
                    advisor.ArmyMovementSpeed = GameValues.advisors.ADVISOR_ARMY_MOVEMENT_SPEED_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_ARMY_MOVEMENT_SPEED_RANDOM * 100.0f)) / 100.0f;
                    break;
                }
                default: {
                    advisor.SiegeEffectiveness = GameValues.advisors.ADVISOR_SIEGE_EFFECTIVENESS_MIN + Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_SIEGE_EFFECTIVENESS_RANDOM * 10000.0f)) / 10000.0f;
                    break;
                }
            }
        }
        return advisor;
    }
    
    public final void updatePoolOfAdvisors(final int iCivID) {
        if (this.lAdvisors.size() == 0) {
            this.generateAdvisors(iCivID, null);
            this.generateYear = Game_Calendar.currentYear;
        }
        else if (this.generateYear + GameValues.advisors.RECRUIT_ADVISOR_REGENERATE_YEARS <= Game_Calendar.currentYear) {
            this.lAdvisors.clear();
            this.generateAdvisors(iCivID, null);
            this.generateYear = Game_Calendar.currentYear;
        }
        else if (this.lAdvisors.size() < this.getPoolOfAdvisors(iCivID)) {
            this.generateAdvisors(iCivID, null);
        }
    }
    
    public final boolean recruitAdvisorID(final int iCivID, final int id) {
        if (id >= this.lAdvisors.size()) {
            return false;
        }
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
        switch (this.iAdvisorType) {
            case 0: {
                if (Game.getCiv(iCivID).advisorAdministration.sName != null) {
                    final AdvisorManager advisorManager = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, -1);
                }
                Game.getCiv(iCivID).advisorAdministration = this.lAdvisors.get(id);
                final AdvisorManager advisorManager2 = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, 1);
                break;
            }
            case 1: {
                if (Game.getCiv(iCivID).advisorEconomy.sName != null) {
                    final AdvisorManager advisorManager3 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, -1);
                }
                Game.getCiv(iCivID).advisorEconomy = this.lAdvisors.get(id);
                final AdvisorManager advisorManager4 = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, 1);
                break;
            }
            case 2: {
                if (Game.getCiv(iCivID).advisorTechnology.sName != null) {
                    final AdvisorManager advisorManager5 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, -1);
                }
                Game.getCiv(iCivID).advisorTechnology = this.lAdvisors.get(id);
                final AdvisorManager advisorManager6 = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, 1);
                break;
            }
            default: {
                if (Game.getCiv(iCivID).advisorMilitary.sName != null) {
                    final AdvisorManager advisorManager7 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, -1);
                }
                Game.getCiv(iCivID).advisorMilitary = this.lAdvisors.get(id);
                final AdvisorManager advisorManager8 = Game.advisorManager;
                AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, 1);
                break;
            }
        }
        this.lAdvisors.remove(id);
        this.generateAdvisors(iCivID, null);
        return true;
    }
    
    public int getPoolOfAdvisors(final int iCivID) {
        return GameValues.advisors.RECRUIT_ADVISOR_DEFAULT_POOL + Game.getCiv(iCivID).civBonuses.AdvisorPoolSize;
    }
    
    public void clearData() {
        this.lAdvisors.clear();
        this.generateYear = -55000;
    }
}
