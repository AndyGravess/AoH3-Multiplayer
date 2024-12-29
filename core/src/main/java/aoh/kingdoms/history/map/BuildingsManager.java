// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class BuildingsManager
{
    public static final int GROUP_CAPITAL = 3;
    public static List<Buildings> buildings;
    public static List<Integer> buildingSize;
    public static int buildingsSize;
    public static int buildingsResourceStartID;
    public static int buildingsResourceSize;
    public static List<Image> buildingImages;
    
    public static final void loadBuildings() {
        int id = 0;
        try {
            final FileHandle fileList = FileManager.loadFile("game/buildings/Buildings.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigBuildingsData.class, "Buildings", (Class)Buildings.class);
            ConfigBuildingsData data = (ConfigBuildingsData)json.fromJson((Class)ConfigBuildingsData.class, fileContent);
            for (final Object e : data.Buildings) {
                final Buildings dataBuilding = (Buildings)e;
                if (dataBuilding.CostGold != null) {
                    for (int a = dataBuilding.CostGold.length - 1; a >= 0; --a) {
                        dataBuilding.CostGold[a] = (float)(int)(dataBuilding.CostGold[a] * Game.map.getActiveMap_MapData().mapData.BuildingsCost);
                    }
                }
                if (dataBuilding.MaintenanceCost != null) {
                    for (int a = dataBuilding.MaintenanceCost.length - 1; a >= 0; --a) {
                        dataBuilding.MaintenanceCost[a] = (float)(int)(dataBuilding.MaintenanceCost[a] * Game.map.getActiveMap_MapData().mapData.BuildingsMaintenanceCost);
                    }
                }
                if (dataBuilding.ConstructionTime != null) {
                    for (int a = dataBuilding.ConstructionTime.length - 1; a >= 0; --a) {
                        dataBuilding.ConstructionTime[a] *= (int)Game.map.getActiveMap_MapData().mapData.BuildingsConstructionTime;
                    }
                }
                BuildingsManager.buildings.add(dataBuilding);
                BuildingsManager.buildingSize.add(BuildingsManager.buildings.get(id).Name.length);
                ++id;
            }
            data = null;
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        BuildingsManager.buildingsSize = BuildingsManager.buildings.size();
        BuildingsManager.buildingsResourceStartID = BuildingsManager.buildingsSize;
        try {
            final FileHandle fileList = FileManager.loadFile("game/buildings/BuildingsResources.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigBuildingsData.class, "Buildings", (Class)Buildings.class);
            ConfigBuildingsData data = (ConfigBuildingsData)json.fromJson((Class)ConfigBuildingsData.class, fileContent);
            for (final Object e : data.Buildings) {
                BuildingsManager.buildings.add((Buildings)e);
                BuildingsManager.buildingSize.add(BuildingsManager.buildings.get(id).Name.length);
                ++id;
            }
            data = null;
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        BuildingsManager.buildingsResourceSize = BuildingsManager.buildings.size();
        for (int i = 0; i < BuildingsManager.buildingsResourceSize; ++i) {
            if (BuildingsManager.buildings.get(i).NameDesc == null) {
                BuildingsManager.buildings.get(i).NameDesc = new String[BuildingsManager.buildings.get(i).Name.length];
                for (int j = 0; j < BuildingsManager.buildings.get(i).Name.length; ++j) {
                    BuildingsManager.buildings.get(i).NameDesc[j] = BuildingsManager.buildings.get(i).Name[j] + "Desc";
                }
            }
        }
        try {
            for (int i = 0; i < BuildingsManager.buildingsResourceSize; ++i) {
                for (int j = 0; j < BuildingsManager.buildingSize.get(i); ++j) {
                    BuildingsManager.buildings.get(i).Name[j] = Game.lang.get(BuildingsManager.buildings.get(i).Name[j]);
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        loadBuildingsImages();
    }
    
    public static final void loadBuildingsImages() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("game/buildings/buildingsImages/numOfImages.txt");
            for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
                if (FileManager.loadFile("game/buildings/buildingsImages/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                    BuildingsManager.buildingImages.add(new Image(ImageManager.loadTexture_RGB888("game/buildings/buildingsImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else {
                    BuildingsManager.buildingImages.add(new Image(ImageManager.loadTexture_RGB888("game/buildings/buildingsImages/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        BuildingsManager.buildings = new ArrayList<Buildings>();
        BuildingsManager.buildingSize = new ArrayList<Integer>();
        BuildingsManager.buildingsSize = 0;
        BuildingsManager.buildingsResourceStartID = 0;
        BuildingsManager.buildingsResourceSize = 0;
        BuildingsManager.buildingImages = new ArrayList<Image>();
    }
    
    public static class ConfigBuildingsData
    {
        public String Age_of_History;
        public ArrayList Buildings;
    }
    
    public static class Buildings
    {
        public String[] Name;
        public String[] NameDesc;
        public int[] ImageID;
        public int GroupID;
        public float[] CostGold;
        public float[] MaintenanceCost;
        public int[] ConstructionTime;
        public int[] RequiredTechID;
        public int RequiredGovernmentID;
        public int RequiredReligionID;
        public float[] TaxEfficiency;
        public float[] MonthlyLegacy;
        public int[] DefenseBonus;
        public int[] FortLevel;
        public int[] FortDefense;
        public int[] MaximumManpower;
        public float[] LocalManpower;
        public int[] ConstructionCost;
        public int[] ConstructionTimeBonus;
        public float[] RecruitArmyCostInProvince;
        public float[] LocalGrowthRate;
        public float[] InvestInEconomyCost;
        public float[] IncreaseManpowerCost;
        public float[] IncreaseTaxEfficiencyCost;
        public float[] DevelopInfrastructureCost;
        public float[] IncreaseGrowthRateCost;
        public float[] ProvinceMaintenance;
        public float[] MonthlyIncome;
        public float[] LocalTaxEfficiency;
        public float[] DiseaseDeathRate;
        public float[] CasualtiesNuclearAttacks;
        public float[] Economy;
        public float[] ResearchPoints;
        public float[] ProductionEfficiency;
        public float[] IncomeProduction;
        public int[] MaxInfrastructure;
        public int[] BuildingSlots;
        public float[] ArmyMovementSpeed;
        public int RequiredResource;
        public boolean UniqueCapitalBuilding;
        public boolean SeaAccessRequired;
        public boolean ShowUpgrades;
        public int[] AI;
        
        public Buildings() {
            this.RequiredGovernmentID = -1;
            this.RequiredReligionID = -1;
            this.RequiredResource = -1;
            this.UniqueCapitalBuilding = false;
            this.SeaAccessRequired = false;
            this.ShowUpgrades = true;
        }
    }
}
