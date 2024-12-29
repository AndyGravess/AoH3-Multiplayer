// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;

public class Ruler
{
    public String Name;
    public String ImageID;
    public boolean kingImage;
    public int BornDay;
    public int BornMonth;
    public int BornYear;
    public boolean isRandom;
    public CivilizationBonuses rulerBonuses;
    
    public Ruler(final int iCivID, final String Name, final String ImageID, final int BornDay, final int BornMonth, final int BornYear, final int ReignYear, final boolean isRandom, final boolean kingImage) {
        this.isRandom = false;
        this.Name = Name;
        this.ImageID = "" + ImageID;
        this.kingImage = kingImage;
        this.BornDay = Math.max(1, BornDay);
        this.BornMonth = BornMonth;
        this.BornYear = BornYear;
        this.isRandom = isRandom;
        this.initRulerBonuses(iCivID);
    }
    
    public Ruler(final SaveGameManager.Save_Civ_Ruler saveRuler) {
        this.isRandom = false;
        this.Name = saveRuler.n;
        this.ImageID = "" + saveRuler.a;
        this.kingImage = saveRuler.k;
        this.BornDay = saveRuler.d;
        this.BornMonth = saveRuler.m;
        this.BornYear = saveRuler.y;
        this.isRandom = saveRuler.r;
    }
    
    public final void initRulerBonuses_Load(final int iCivID, final CivilizationBonuses bonuses) {
        this.rulerBonuses = bonuses;
        this.updateCivBonuses(iCivID, 1);
    }
    
    private final void initRulerBonuses(final int iCivID) {
        this.rulerBonuses = new CivilizationBonuses();
        int iRandom = Game.oR.nextInt(11);
        switch (iRandom) {
            case 0: {
                this.rulerBonuses.MonthlyIncome = GameValues.court.RULER_MONTHLY_INCOME_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_MONTHLY_INCOME_RANDOM * 100.0f)) / 100.0f;
                if (this.rulerBonuses.MonthlyIncome == 0.0f) {
                    this.rulerBonuses.MonthlyIncome = 0.01f;
                    break;
                }
                break;
            }
            case 1: {
                this.rulerBonuses.TaxEfficiency = GameValues.court.RULER_TAX_EFFICIENCY_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_TAX_EFFICIENCY_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            case 2: {
                this.rulerBonuses.ProductionEfficiency = GameValues.court.RULER_PRODUCTION_EFFICIENCY_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_PRODUCTION_EFFICIENCY_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            case 3: {
                this.rulerBonuses.IncreaseGrowthRateCost = -GameValues.court.RULER_INCREASE_GROWTH_RATE_COST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_INCREASE_GROWTH_RATE_COST_RANDOM * 10000.0f)) / 10000.0f;
                break;
            }
            case 4: {
                this.rulerBonuses.ConstructionCost = -GameValues.court.RULER_CONSTRUCTION_COST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_CONSTRUCTION_COST_RANDOM * 10000.0f)) / 10000.0f;
                break;
            }
            case 5: {
                this.rulerBonuses.InvestInEconomyCost = -GameValues.court.RULER_INVEST_COST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_INVEST_COST_RANDOM * 10000.0f)) / 10000.0f;
                break;
            }
            case 6: {
                this.rulerBonuses.IncreaseTaxEfficiencyCost = -GameValues.court.RULER_INCREASE_TAX_EFFICIENCY_COST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_INCREASE_TAX_EFFICIENCY_COST_RANDOM * 10000.0f)) / 10000.0f;
                break;
            }
            case 7: {
                this.rulerBonuses.DevelopInfrastructureCost = -GameValues.court.RULER_DEVELOP_INFRASTRUCTURE_COST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_DEVELOP_INFRASTRUCTURE_COST_RANDOM * 10000.0f)) / 10000.0f;
                break;
            }
            case 8: {
                this.rulerBonuses.Devastation = GameValues.court.RULER_DEVASTATION_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_DEVASTATION_RANDOM * 10000.0f)) / 10000.0f;
                break;
            }
            case 9: {
                this.rulerBonuses.IncreaseManpowerCost = -GameValues.court.RULER_INCREASE_MANPOWER_COST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_INCREASE_MANPOWER_COST_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            default: {
                this.rulerBonuses.ProvinceMaintenance = -GameValues.court.RULER_PROVINCE_MAINTENANCE_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_PROVINCE_MAINTENANCE_RANDOM * 100.0f)) / 100.0f;
                break;
            }
        }
        iRandom = Game.oR.nextInt(7);
        switch (iRandom) {
            case 0: {
                this.rulerBonuses.MaxManpower = GameValues.court.RULER_MAX_MANPOWER_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_MAX_MANPOWER_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            case 1: {
                this.rulerBonuses.RecruitmentTime = -GameValues.court.RULER_RECRUITMENT_TIME_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_RECRUITMENT_TIME_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            case 2: {
                this.rulerBonuses.ResearchPoints = GameValues.court.RULER_RESEARCH_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_RESEARCH_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            case 3: {
                this.rulerBonuses.GeneralCost = -GameValues.court.RULER_GENERAL_COST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_GENERAL_COST_RANDOM * 10000.0f)) / 10000.0f;
                break;
            }
            case 4: {
                this.rulerBonuses.ImproveRelationsModifier = GameValues.court.RULER_IMPROVE_RELATIONS_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_IMPROVE_RELATIONS_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            case 5: {
                this.rulerBonuses.LoanInterest = -GameValues.court.RULER_LOAN_INTEREST_MIN - Game.oR.nextInt((int)(GameValues.court.RULER_LOAN_INTEREST_RANDOM * 100.0f)) / 100.0f;
                break;
            }
            default: {
                this.rulerBonuses.MonthlyLegacy = GameValues.court.RULER_MONTHLY_LEGACY_MIN + Game.oR.nextInt((int)(GameValues.court.RULER_MONTHLY_LEGACY_RANDOM * 100.0f)) / 100.0f;
                break;
            }
        }
        if (Game.oR.nextInt(100) < GameValues.court.RULER_THIRD_BONUS_CHANCE) {
            iRandom = Game.oR.nextInt(4);
            switch (iRandom) {
                case 0: {
                    this.rulerBonuses.UnitsAttack = GameValues.court.RULER_UNITS_ATTACK_MIN + Game.oR.nextInt(GameValues.court.RULER_UNITS_ATTACK_RANDOM);
                    break;
                }
                case 1: {
                    this.rulerBonuses.UnitsDefense = GameValues.court.RULER_UNITS_DEFENSE_MIN + Game.oR.nextInt(GameValues.court.RULER_UNITS_DEFENSE_RANDOM);
                    break;
                }
                case 2: {
                    this.rulerBonuses.GeneralAttack = GameValues.court.RULER_GENERALS_ATTACK_MIN + Game.oR.nextInt(GameValues.court.RULER_GENERALS_ATTACK_RANDOM);
                    break;
                }
                default: {
                    this.rulerBonuses.GeneralDefense = GameValues.court.RULER_GENERALS_DEFENSE_MIN + Game.oR.nextInt(GameValues.court.RULER_GENERALS_DEFENSE_RANDOM);
                    break;
                }
            }
        }
        this.updateCivBonuses(iCivID, 1);
    }
    
    public final void updateCivBonuses(final int iCivID, final int mod) {
        Game.getCiv(iCivID).updateCivilizationBonuses_Temporary(this.rulerBonuses, (float)mod);
    }
}
