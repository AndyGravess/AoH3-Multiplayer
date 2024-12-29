// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.advisors;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class AdvisorSerializer implements Json.Serializer<Advisor>
{
    public void write(final Json json, final Advisor advisor, final Class knownType) {
        json.writeObjectStart();
        if (advisor.TaxEfficiency != 0.0f) {
            json.writeValue("TaxEfficiency", (Object)advisor.TaxEfficiency);
        }
        if (advisor.ProvinceMaintenance != 0.0f) {
            json.writeValue("ProvinceMaintenance", (Object)advisor.ProvinceMaintenance);
        }
        if (advisor.GrowthRate != 0.0f) {
            json.writeValue("GrowthRate", (Object)advisor.GrowthRate);
        }
        if (advisor.ProductionEfficiency != 0.0f) {
            json.writeValue("ProductionEfficiency", (Object)advisor.ProductionEfficiency);
        }
        if (advisor.IncomeProduction != 0.0f) {
            json.writeValue("IncomeProduction", (Object)advisor.IncomeProduction);
        }
        if (advisor.MonthlyLegacy != 0.0f) {
            json.writeValue("MonthlyLegacy", (Object)advisor.MonthlyLegacy);
        }
        if (advisor.MaxManpower != 0.0f) {
            json.writeValue("MaxManpower", (Object)advisor.MaxManpower);
        }
        if (advisor.ArmyMaintenance != 0.0f) {
            json.writeValue("ArmyMaintenance", (Object)advisor.ArmyMaintenance);
        }
        if (advisor.RecruitmentTime != 0.0f) {
            json.writeValue("RecruitmentTime", (Object)advisor.RecruitmentTime);
        }
        if (advisor.RecruitArmyCost != 0.0f) {
            json.writeValue("RecruitArmyCost", (Object)advisor.RecruitArmyCost);
        }
        if (advisor.Research != 0.0f) {
            json.writeValue("Research", (Object)advisor.Research);
        }
        if (advisor.ConstructionCost != 0.0f) {
            json.writeValue("ConstructionCost", (Object)advisor.ConstructionCost);
        }
        if (advisor.AdministrationBuildingsCost != 0.0f) {
            json.writeValue("AdministrationBuildingsCost", (Object)advisor.AdministrationBuildingsCost);
        }
        if (advisor.MilitaryBuildingsCost != 0.0f) {
            json.writeValue("MilitaryBuildingsCost", (Object)advisor.MilitaryBuildingsCost);
        }
        if (advisor.EconomyBuildingsCost != 0.0f) {
            json.writeValue("EconomyBuildingsCost", (Object)advisor.EconomyBuildingsCost);
        }
        if (advisor.ConstructionTime != 0.0f) {
            json.writeValue("ConstructionTime", (Object)advisor.ConstructionTime);
        }
        if (advisor.InvestInEconomyCost != 0.0f) {
            json.writeValue("InvestInEconomyCost", (Object)advisor.InvestInEconomyCost);
        }
        if (advisor.IncreaseManpowerCost != 0.0f) {
            json.writeValue("IncreaseManpowerCost", (Object)advisor.IncreaseManpowerCost);
        }
        if (advisor.IncreaseTaxEfficiencyCost != 0.0f) {
            json.writeValue("IncreaseTaxEfficiencyCost", (Object)advisor.IncreaseTaxEfficiencyCost);
        }
        if (advisor.IncreaseGrowthRateCost != 0.0f) {
            json.writeValue("IncreaseGrowthRateCost", (Object)advisor.IncreaseGrowthRateCost);
        }
        if (advisor.DevelopInfrastructureCost != 0.0f) {
            json.writeValue("DevelopInfrastructureCost", (Object)advisor.DevelopInfrastructureCost);
        }
        if (advisor.GeneralAttack != 0.0f) {
            json.writeValue("GeneralAttack", (Object)advisor.GeneralAttack);
        }
        if (advisor.GeneralDefense != 0.0f) {
            json.writeValue("GeneralDefense", (Object)advisor.GeneralDefense);
        }
        if (advisor.UnitsAttack != 0.0f) {
            json.writeValue("UnitsAttack", (Object)advisor.UnitsAttack);
        }
        if (advisor.UnitsDefense != 0.0f) {
            json.writeValue("UnitsDefense", (Object)advisor.UnitsDefense);
        }
        if (advisor.MaxMorale != 0.0f) {
            json.writeValue("MaxMorale", (Object)advisor.MaxMorale);
        }
        if (advisor.ArmyMovementSpeed != 0.0f) {
            json.writeValue("ArmyMovementSpeed", (Object)advisor.ArmyMovementSpeed);
        }
        if (advisor.SiegeEffectiveness != 0.0f) {
            json.writeValue("SiegeEffectiveness", (Object)advisor.SiegeEffectiveness);
        }
        if (advisor.ImproveRelationsModifier != 0.0f) {
            json.writeValue("ImproveRelationsModifier", (Object)advisor.ImproveRelationsModifier);
        }
        if (advisor.LoanInterest != 0.0f) {
            json.writeValue("LoanInterest", (Object)advisor.LoanInterest);
        }
        if (advisor.CoreCost != 0.0f) {
            json.writeValue("CoreCost", (Object)advisor.CoreCost);
        }
        if (advisor.ReligionCost != 0.0f) {
            json.writeValue("ReligionCost", (Object)advisor.ReligionCost);
        }
        if (advisor.RegimentsLimit != 0) {
            json.writeValue("RegimentsLimit", (Object)advisor.RegimentsLimit);
        }
        json.writeObjectEnd();
    }
    
    public Advisor read(final Json json, final JsonValue jsonData, final Class type) {
        final Advisor advisor = new Advisor();
        advisor.sName = jsonData.getString("sName", (String)null);
        advisor.iYearOfBirth = jsonData.getInt("iYearOfBirth", 0);
        advisor.iMonthOfBirth = jsonData.getInt("iMonthOfBirth", 0);
        advisor.iDayOfBirth = jsonData.getInt("iDayOfBirth", 0);
        advisor.sIMG = jsonData.getString("sIMG", (String)null);
        advisor.imageID = jsonData.getInt("imageID", 0);
        advisor.iLevel = jsonData.getInt("iLevel", 1);
        advisor.TaxEfficiency = jsonData.getFloat("TaxEfficiency", 0.0f);
        advisor.ProvinceMaintenance = jsonData.getFloat("ProvinceMaintenance", 0.0f);
        advisor.GrowthRate = jsonData.getFloat("GrowthRate", 0.0f);
        advisor.ProductionEfficiency = jsonData.getFloat("ProductionEfficiency", 0.0f);
        advisor.IncomeProduction = jsonData.getFloat("IncomeProduction", 0.0f);
        advisor.MonthlyLegacy = jsonData.getFloat("MonthlyLegacy", 0.0f);
        advisor.MaxManpower = jsonData.getFloat("MaxManpower", 0.0f);
        advisor.ArmyMaintenance = jsonData.getFloat("ArmyMaintenance", 0.0f);
        advisor.RecruitmentTime = jsonData.getFloat("RecruitmentTime", 0.0f);
        advisor.RecruitArmyCost = jsonData.getFloat("RecruitArmyCost", 0.0f);
        advisor.Research = jsonData.getFloat("Research", 0.0f);
        advisor.ConstructionCost = jsonData.getFloat("ConstructionCost", 0.0f);
        advisor.AdministrationBuildingsCost = jsonData.getFloat("AdministrationBuildingsCost", 0.0f);
        advisor.MilitaryBuildingsCost = jsonData.getFloat("MilitaryBuildingsCost", 0.0f);
        advisor.EconomyBuildingsCost = jsonData.getFloat("EconomyBuildingsCost", 0.0f);
        advisor.ConstructionTime = jsonData.getFloat("ConstructionTime", 0.0f);
        advisor.InvestInEconomyCost = jsonData.getFloat("InvestInEconomyCost", 0.0f);
        advisor.IncreaseManpowerCost = jsonData.getFloat("IncreaseManpowerCost", 0.0f);
        advisor.IncreaseTaxEfficiencyCost = jsonData.getFloat("IncreaseTaxEfficiencyCost", 0.0f);
        advisor.IncreaseGrowthRateCost = jsonData.getFloat("IncreaseGrowthRateCost", 0.0f);
        advisor.DevelopInfrastructureCost = jsonData.getFloat("DevelopInfrastructureCost", 0.0f);
        advisor.GeneralAttack = jsonData.getFloat("GeneralAttack", 0.0f);
        advisor.GeneralDefense = jsonData.getFloat("GeneralDefense", 0.0f);
        advisor.UnitsAttack = jsonData.getFloat("UnitsAttack", 0.0f);
        advisor.UnitsDefense = jsonData.getFloat("UnitsDefense", 0.0f);
        advisor.MaxMorale = jsonData.getFloat("MaxMorale", 0.0f);
        advisor.ArmyMovementSpeed = jsonData.getFloat("ArmyMovementSpeed", 0.0f);
        advisor.SiegeEffectiveness = jsonData.getFloat("SiegeEffectiveness", 0.0f);
        advisor.ImproveRelationsModifier = jsonData.getFloat("ImproveRelationsModifier", 0.0f);
        advisor.LoanInterest = jsonData.getFloat("LoanInterest", 0.0f);
        advisor.CoreCost = jsonData.getFloat("CoreCost", 0.0f);
        advisor.ReligionCost = jsonData.getFloat("ReligionCost", 0.0f);
        advisor.RegimentsLimit = jsonData.getInt("RegimentsLimit", 0);
        return advisor;
    }
}
