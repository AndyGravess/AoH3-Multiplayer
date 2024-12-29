// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.province.ProvinceBonuses;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class WondersManager
{
    public static List<Wonders> wonders;
    public static int wondersSize;
    public static List<Image> wonderImages;
    
    public static final void loadWonders() {
        try {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "Wonders.json").exists()) {
                final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "Wonders.json");
                final String fileContent = fileList.readString();
                final Json json = new Json();
                json.setElementType((Class)ConfigWondersData.class, "Wonders", (Class)Wonders.class);
                final ConfigWondersData data = (ConfigWondersData)json.fromJson((Class)ConfigWondersData.class, fileContent);
                final int id = 0;
                for (final Object e : data.Wonders) {
                    WondersManager.wonders.add((Wonders)e);
                }
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        WondersManager.wondersSize = WondersManager.wonders.size();
        for (int i = 0; i < WondersManager.wondersSize; ++i) {
            WondersManager.wonders.get(i).Name = Game.lang.get(WondersManager.wonders.get(i).Name);
        }
        loadWondersImages();
    }
    
    public static final void loadWondersImages() {
        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "wondersImages/" + "numOfImages.txt").exists()) {
            final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "wondersImages/" + "numOfImages.txt");
            for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "wondersImages/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                    WondersManager.wonderImages.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "wondersImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else {
                    WondersManager.wonderImages.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "wonders/" + "wondersImages/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
            }
        }
    }
    
    public static final void initProvinceWonders() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).wonderID = -1;
            Game.getProvince(i).setWonderBuilt(false);
        }
        for (int i = 0; i < WondersManager.wondersSize; ++i) {
            Game.getProvince(WondersManager.wonders.get(i).ProvinceID).wonderID = i;
        }
    }
    
    public static final void updateCivBonuses(final int iCivID, final int iWonderID, final int mod) {
        final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
        civBonuses.MonthlyIncome += WondersManager.wonders.get(iWonderID).MonthlyIncome * mod;
        final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
        civBonuses2.TaxEfficiency += WondersManager.wonders.get(iWonderID).ProductionEfficiency * mod;
        final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
        civBonuses3.ProductionEfficiency += WondersManager.wonders.get(iWonderID).ProductionEfficiency * mod;
        final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
        civBonuses4.ProvinceMaintenance += WondersManager.wonders.get(iWonderID).ProvinceMaintenance * mod;
        final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
        civBonuses5.BuildingsMaintenanceCost += WondersManager.wonders.get(iWonderID).BuildingsMaintenanceCost * mod;
        if (WondersManager.wonders.get(iWonderID).GrowthRate != 0.0f) {
            final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
            civBonuses6.GrowthRate += WondersManager.wonders.get(iWonderID).GrowthRate * mod;
            Game.getCiv(iCivID).updateResearchPerMonth();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        if (WondersManager.wonders.get(iWonderID).MonthlyLegacy != 0.0f) {
            final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
            civBonuses7.MonthlyLegacy += WondersManager.wonders.get(iWonderID).MonthlyLegacy * mod;
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        if (WondersManager.wonders.get(iWonderID).MaxManpower != 0.0f) {
            final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
            civBonuses8.MaxManpower += WondersManager.wonders.get(iWonderID).MaxManpower * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (WondersManager.wonders.get(iWonderID).ManpowerRecoverySpeed != 0.0f) {
            final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
            civBonuses9.ManpowerRecoverySpeed += WondersManager.wonders.get(iWonderID).ManpowerRecoverySpeed * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        if (WondersManager.wonders.get(iWonderID).ArmyMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
            civBonuses10.ArmyMaintenance += WondersManager.wonders.get(iWonderID).ArmyMaintenance * mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }
        final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
        civBonuses11.RecruitmentTime += WondersManager.wonders.get(iWonderID).RecruitmentTime * mod;
        final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
        civBonuses12.RecruitArmyCost += WondersManager.wonders.get(iWonderID).RecruitArmyCost * mod;
        final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
        civBonuses13.RecruitArmyFirstLineCost += WondersManager.wonders.get(iWonderID).RecruitArmyFirstLineCost * mod;
        final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
        civBonuses14.RecruitArmySecondLineCost += WondersManager.wonders.get(iWonderID).RecruitArmySecondLineCost * mod;
        if (WondersManager.wonders.get(iWonderID).ResearchPoints != 0.0f) {
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
        civBonuses15.ResearchPoints += WondersManager.wonders.get(iWonderID).ResearchPoints * mod;
        final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
        civBonuses16.TechnologyCost += WondersManager.wonders.get(iWonderID).TechnologyCost * mod;
        final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
        civBonuses17.ConstructionCost += WondersManager.wonders.get(iWonderID).ConstructionCost * mod;
        final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
        civBonuses18.AdministrationBuildingsCost += WondersManager.wonders.get(iWonderID).AdministrationBuildingsCost * mod;
        final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
        civBonuses19.MilitaryBuildingsCost += WondersManager.wonders.get(iWonderID).MilitaryBuildingsCost * mod;
        final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
        civBonuses20.EconomyBuildingsCost += WondersManager.wonders.get(iWonderID).EconomyBuildingsCost * mod;
        final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
        civBonuses21.ConstructionTime += WondersManager.wonders.get(iWonderID).ConstructionTimeBonus * mod;
        final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
        civBonuses22.DevelopInfrastructureCost += WondersManager.wonders.get(iWonderID).DevelopInfrastructureCost * mod;
        final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
        civBonuses23.IncreaseTaxEfficiencyCost += WondersManager.wonders.get(iWonderID).IncreaseTaxEfficiencyCost * mod;
        final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
        civBonuses24.IncreaseGrowthRateCost += WondersManager.wonders.get(iWonderID).IncreaseGrowthRateCost * mod;
        final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
        civBonuses25.InvestInEconomyCost += WondersManager.wonders.get(iWonderID).InvestInEconomyCost * mod;
        final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
        civBonuses26.IncreaseManpowerCost += WondersManager.wonders.get(iWonderID).IncreaseManpowerCost * mod;
        final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
        civBonuses27.GeneralAttack += WondersManager.wonders.get(iWonderID).GeneralAttack * mod;
        final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
        civBonuses28.GeneralDefense += WondersManager.wonders.get(iWonderID).GeneralDefense * mod;
        final CivilizationBonuses civBonuses29 = Game.getCiv(iCivID).civBonuses;
        civBonuses29.UnitsAttack += WondersManager.wonders.get(iWonderID).UnitsAttack * mod;
        final CivilizationBonuses civBonuses30 = Game.getCiv(iCivID).civBonuses;
        civBonuses30.UnitsDefense += WondersManager.wonders.get(iWonderID).UnitsDefense * mod;
        if (WondersManager.wonders.get(iWonderID).MaxInfrastructure != 0) {
            final CivilizationBonuses civBonuses31 = Game.getCiv(iCivID).civBonuses;
            civBonuses31.MaxInfrastructure += WondersManager.wonders.get(iWonderID).MaxInfrastructure * mod;
            Game.getCiv(iCivID).updateInfrastructureMax();
        }
        final CivilizationBonuses civBonuses32 = Game.getCiv(iCivID).civBonuses;
        civBonuses32.AdvisorMaxLevel += WondersManager.wonders.get(iWonderID).AdvisorMaxLevel * mod;
        final CivilizationBonuses civBonuses33 = Game.getCiv(iCivID).civBonuses;
        civBonuses33.AdvisorPoolSize += WondersManager.wonders.get(iWonderID).AdvisorPoolSize * mod;
        final CivilizationBonuses civBonuses34 = Game.getCiv(iCivID).civBonuses;
        civBonuses34.MaxMorale += WondersManager.wonders.get(iWonderID).MaxMorale * mod;
        final CivilizationBonuses civBonuses35 = Game.getCiv(iCivID).civBonuses;
        civBonuses35.ArmyMovementSpeed += WondersManager.wonders.get(iWonderID).ArmyMovementSpeed * mod;
        final CivilizationBonuses civBonuses36 = Game.getCiv(iCivID).civBonuses;
        civBonuses36.SiegeEffectiveness += WondersManager.wonders.get(iWonderID).SiegeEffectiveness * mod;
        final CivilizationBonuses civBonuses37 = Game.getCiv(iCivID).civBonuses;
        civBonuses37.ImproveRelationsModifier += WondersManager.wonders.get(iWonderID).ImproveRelationsModifier * mod;
        final CivilizationBonuses civBonuses38 = Game.getCiv(iCivID).civBonuses;
        civBonuses38.IncomeFromVassals += WondersManager.wonders.get(iWonderID).IncomeFromVassals * mod;
        if (WondersManager.wonders.get(iWonderID).DiplomacyPoints != 0.0f) {
            final CivilizationBonuses civBonuses39 = Game.getCiv(iCivID).civBonuses;
            civBonuses39.DiplomacyPoints += WondersManager.wonders.get(iWonderID).DiplomacyPoints * mod;
            Game.getCiv(iCivID).updateDiplomacyPerMonth();
        }
        final CivilizationBonuses civBonuses40 = Game.getCiv(iCivID).civBonuses;
        civBonuses40.LoanInterest += WondersManager.wonders.get(iWonderID).LoanInterest * mod;
        final CivilizationBonuses civBonuses41 = Game.getCiv(iCivID).civBonuses;
        civBonuses41.MaxNumberOfLoans += WondersManager.wonders.get(iWonderID).MaxNumberOfLoans * mod;
        final CivilizationBonuses civBonuses42 = Game.getCiv(iCivID).civBonuses;
        civBonuses42.AggressiveExpansion += WondersManager.wonders.get(iWonderID).AggressiveExpansion * mod;
        final CivilizationBonuses civBonuses43 = Game.getCiv(iCivID).civBonuses;
        civBonuses43.RevolutionaryRisk += WondersManager.wonders.get(iWonderID).RevolutionaryRisk * mod;
        final CivilizationBonuses civBonuses44 = Game.getCiv(iCivID).civBonuses;
        civBonuses44.CoreCost += WondersManager.wonders.get(iWonderID).CoreCost * mod;
        final CivilizationBonuses civBonuses45 = Game.getCiv(iCivID).civBonuses;
        civBonuses45.DiseaseDeathRate += WondersManager.wonders.get(iWonderID).DiseaseDeathRate * mod;
        final CivilizationBonuses civBonuses46 = Game.getCiv(iCivID).civBonuses;
        civBonuses46.AllCharactersLifeExpectancy += WondersManager.wonders.get(iWonderID).AllCharactersLifeExpectancy * mod;
        final CivilizationBonuses civBonuses47 = Game.getCiv(iCivID).civBonuses;
        civBonuses47.BattleWidth += WondersManager.wonders.get(iWonderID).BattleWidth * mod;
        final CivilizationBonuses civBonuses48 = Game.getCiv(iCivID).civBonuses;
        civBonuses48.Discipline += WondersManager.wonders.get(iWonderID).Discipline * mod;
        final CivilizationBonuses civBonuses49 = Game.getCiv(iCivID).civBonuses;
        civBonuses49.MaximumAmountOfGold += WondersManager.wonders.get(iWonderID).MaximumAmountOfGold * mod;
        if (WondersManager.wonders.get(iWonderID).RegimentsLimit != 0) {
            final CivilizationBonuses civBonuses50 = Game.getCiv(iCivID).civBonuses;
            civBonuses50.RegimentsLimit += WondersManager.wonders.get(iWonderID).RegimentsLimit * mod;
            Game.getCiv(iCivID).updateRegimentsLimit();
        }
        final CivilizationBonuses civBonuses51 = Game.getCiv(iCivID).civBonuses;
        civBonuses51.ManpowerRecoveryFromADisbandedArmy += WondersManager.wonders.get(iWonderID).ManpowerRecoveryFromADisbandedArmy * mod;
        Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
    }
    
    public static final void updateProvinceBonuses(final int iProvinceID, final int iWonderID, final int mod) {
        final ProvinceBonuses provBonuses = Game.getProvince(iProvinceID).provBonuses;
        provBonuses.LocalTaxEfficiency += WondersManager.wonders.get(iWonderID).LocalTaxEfficiency * mod;
        final ProvinceBonuses provBonuses2 = Game.getProvince(iProvinceID).provBonuses;
        provBonuses2.Economy += WondersManager.wonders.get(iWonderID).Economy * mod;
        final ProvinceBonuses provBonuses3 = Game.getProvince(iProvinceID).provBonuses;
        provBonuses3.DefenseBonus += WondersManager.wonders.get(iWonderID).DefenseBonus * mod;
        final ProvinceBonuses provBonuses4 = Game.getProvince(iProvinceID).provBonuses;
        provBonuses4.FortLevel += WondersManager.wonders.get(iWonderID).FortLevel * mod;
        final ProvinceBonuses provBonuses5 = Game.getProvince(iProvinceID).provBonuses;
        provBonuses5.FortDefense += WondersManager.wonders.get(iWonderID).FortDefense * mod;
    }
    
    public static final float getWonderConstructionCost(final int iProvinceID, final int iWonderID) {
        return Math.max(1.0f, WondersManager.wonders.get(iWonderID).CostGold * (1.0f + Game.getProvince(iProvinceID).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_WONDER_CONSTRUCTION_COST_PER_LVL + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).civBonuses.WonderConstructionCost));
    }
    
    static {
        WondersManager.wonders = new ArrayList<Wonders>();
        WondersManager.wondersSize = 0;
        WondersManager.wonderImages = new ArrayList<Image>();
    }
    
    public static class ConfigWondersData
    {
        public String Age_of_History;
        public ArrayList Wonders;
    }
    
    public static class Wonders
    {
        public String Name;
        public int ImageID;
        public int ProvinceID;
        public int CostGold;
        public float MaintenanceCost;
        public int ConstructionTime;
        public float Legacy;
        public int AI;
        public String Wiki;
        public float MonthlyIncome;
        public float TaxEfficiency;
        public float ProvinceMaintenance;
        public float BuildingsMaintenanceCost;
        public float GrowthRate;
        public float ProductionEfficiency;
        public float MonthlyLegacy;
        public float MaxManpower;
        public float ManpowerRecoverySpeed;
        public float ArmyMaintenance;
        public float RecruitmentTime;
        public float RecruitArmyCost;
        public float RecruitArmyFirstLineCost;
        public float RecruitArmySecondLineCost;
        public float Research;
        public float ResearchPoints;
        public float TechnologyCost;
        public float ConstructionCost;
        public float AdministrationBuildingsCost;
        public float MilitaryBuildingsCost;
        public float EconomyBuildingsCost;
        public float ConstructionTimeBonus;
        public int BuildingSlot;
        public float InvestInEconomyCost;
        public float IncreaseTaxEfficiencyCost;
        public float IncreaseGrowthRateCost;
        public float IncreaseManpowerCost;
        public float DevelopInfrastructureCost;
        public int GeneralAttack;
        public int GeneralDefense;
        public int UnitsAttack;
        public int UnitsDefense;
        public int MaxInfrastructure;
        public int AdvisorMaxLevel;
        public int AdvisorPoolSize;
        public float MaxMorale;
        public float ArmyMovementSpeed;
        public float SiegeEffectiveness;
        public float ImproveRelationsModifier;
        public float IncomeFromVassals;
        public float DiplomacyPoints;
        public float LoanInterest;
        public int MaxNumberOfLoans;
        public float AggressiveExpansion;
        public float RevolutionaryRisk;
        public float CoreCost;
        public float DiseaseDeathRate;
        public int AllCharactersLifeExpectancy;
        public int RegimentsLimit;
        public int BattleWidth;
        public float Discipline;
        public float ManpowerRecoveryFromADisbandedArmy;
        public float MaximumAmountOfGold;
        public float LocalTaxEfficiency;
        public float Economy;
        public int DefenseBonus;
        public int FortLevel;
        public int FortDefense;
    }
}
