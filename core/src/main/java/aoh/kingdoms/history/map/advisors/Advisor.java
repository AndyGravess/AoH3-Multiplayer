// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.advisors;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;

public class Advisor
{
    public String sName;
    public int iYearOfBirth;
    public int iMonthOfBirth;
    public int iDayOfBirth;
    public String sIMG;
    public int imageID;
    public int iLevel;
    public float TaxEfficiency;
    public float ProvinceMaintenance;
    public float GrowthRate;
    public float ProductionEfficiency;
    public float IncomeProduction;
    public float MonthlyLegacy;
    public float MaxManpower;
    public float ArmyMaintenance;
    public float RecruitmentTime;
    public float RecruitArmyCost;
    public float Research;
    public float ConstructionCost;
    public float AdministrationBuildingsCost;
    public float MilitaryBuildingsCost;
    public float EconomyBuildingsCost;
    public float ConstructionTime;
    public float InvestInEconomyCost;
    public float IncreaseManpowerCost;
    public float IncreaseTaxEfficiencyCost;
    public float IncreaseGrowthRateCost;
    public float DevelopInfrastructureCost;
    public float GeneralAttack;
    public float GeneralDefense;
    public float UnitsAttack;
    public float UnitsDefense;
    public float MaxMorale;
    public float ArmyMovementSpeed;
    public float SiegeEffectiveness;
    public float ImproveRelationsModifier;
    public float LoanInterest;
    public float CoreCost;
    public float ReligionCost;
    public int RegimentsLimit;
    
    public Advisor() {
        this.sName = null;
        this.iYearOfBirth = 0;
        this.iMonthOfBirth = 0;
        this.iDayOfBirth = 0;
        this.sIMG = null;
        this.imageID = 0;
        this.iLevel = 1;
    }
    
    public Advisor(final String sName, final int imageID, final int iYearOfBirth, final String sIMG) {
        this.sName = null;
        this.iYearOfBirth = 0;
        this.iMonthOfBirth = 0;
        this.iDayOfBirth = 0;
        this.sIMG = null;
        this.imageID = 0;
        this.iLevel = 1;
        this.sName = sName;
        this.imageID = imageID;
        this.iYearOfBirth = iYearOfBirth;
        this.sIMG = sIMG;
        this.iMonthOfBirth = 1 + Game.oR.nextInt(12);
        this.iDayOfBirth = 1 + Game.oR.nextInt(Game_Calendar.getNumOfDaysInMonth(this.iMonthOfBirth));
    }
}
