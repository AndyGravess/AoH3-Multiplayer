// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.technology;

import aoh.kingdoms.history.mainGame.GameValues;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.utils.Json;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.map.LawsManager;
import aoh.kingdoms.history.map.BuildingsManager;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Image;
import java.util.List;

public class TechnologyTree
{
    public static List<Image> technologyImages;
    public static List<Technology> lTechnology;
    public static int iTechnologySize;
    public static List<List<Building>> lTechUnlocksBuildings;
    public static List<List<LawTech>> lTechUnlocksLaws;
    public static List<List<Unit>> lTechUnlocksUnits;
    public static final int PADDING_X = 100;
    public static final int PADDING_Y = 15;
    public static int iTechnologyWidth;
    public static int iTechnologyHeight;
    public static float MapResearchCost;
    
    public static void buildTechUnlocks() {
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            TechnologyTree.lTechUnlocksBuildings.add(new ArrayList<Building>());
        }
        for (int i = 0; i < BuildingsManager.buildingsResourceSize; ++i) {
            if (BuildingsManager.buildings.get(i).RequiredTechID != null) {
                for (int j = 0, jSize = BuildingsManager.buildings.get(i).RequiredTechID.length; j < jSize; ++j) {
                    if (BuildingsManager.buildings.get(i).RequiredTechID[j] >= 0) {
                        TechnologyTree.lTechUnlocksBuildings.get(BuildingsManager.buildings.get(i).RequiredTechID[j]).add(new Building(i, j));
                    }
                }
            }
        }
    }
    
    public static void buildTechUnlocks_Laws() {
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            TechnologyTree.lTechUnlocksLaws.add(new ArrayList<LawTech>());
        }
        for (int i = 0; i < LawsManager.iLawsSize; ++i) {
            for (int j = 0; j < LawsManager.laws.get(i).RequiredTechID.length; ++j) {
                if (LawsManager.laws.get(i).RequiredTechID[j] >= 0) {
                    TechnologyTree.lTechUnlocksLaws.get(LawsManager.laws.get(i).RequiredTechID[j]).add(new LawTech(i, j));
                }
            }
        }
    }
    
    public static void buildTechUnlocks_Units() {
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            TechnologyTree.lTechUnlocksUnits.add(new ArrayList<Unit>());
        }
        for (int i = 0; i < ArmyManager.iUnitsTypesSize; ++i) {
            for (int j = 0; j < ArmyManager.lArmySize.get(i); ++j) {
                if (ArmyManager.lArmy.get(i).get(j).RequiredTechID >= 0) {
                    TechnologyTree.lTechUnlocksUnits.get(ArmyManager.lArmy.get(i).get(j).RequiredTechID).add(new Unit(i, j));
                }
            }
        }
    }
    
    public static float getResearchCost(final int iTechID, final int iCivID) {
        return Math.max(1.0f, TechnologyTree.lTechnology.get(iTechID).getResearchCost() * (1.0f + Game.getCiv(iCivID).civBonuses.TechnologyCost / 100.0f));
    }
    
    public static int getTechBG(final int iTechID, final int iCivID) {
        if (Game.getCiv(iCivID).getActiveTechResearch() == iTechID) {
            return Images.techBlue;
        }
        if (Game.getCiv(iCivID).getTechResearched(iTechID)) {
            return Images.techResearched;
        }
        if (TechnologyTree.lTechnology.get(iTechID).RequiredTech >= 0 && !Game.getCiv(iCivID).getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech)) {
            return Images.techGray;
        }
        if (TechnologyTree.lTechnology.get(iTechID).RequiredTech2 >= 0 && !Game.getCiv(iCivID).getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech2)) {
            return Images.techGray;
        }
        return Images.techAvailable;
    }
    
    public static final void loadTechnology() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/technologies/Technologies.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigTechnologyData.class, "Technology", (Class)Technology.class);
            ConfigTechnologyData data = (ConfigTechnologyData)json.fromJson((Class)ConfigTechnologyData.class, fileContent);
            for (final Object e : data.Technology) {
                TechnologyTree.lTechnology.add((Technology)e);
            }
            data = null;
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        TechnologyTree.iTechnologySize = TechnologyTree.lTechnology.size();
        loadTechnologyImages();
    }
    
    public static final void buildTechnologiesNames() {
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            if (TechnologyTree.lTechnology.get(i).MaintainTechnologyName) {
                TechnologyTree.lTechnology.get(i).Name = Game.lang.get(TechnologyTree.lTechnology.get(i).Name);
            }
            else if (TechnologyTree.lTechUnlocksLaws.get(i).size() > 0) {
                TechnologyTree.lTechnology.get(i).Name = Game.lang.get(LawsManager.laws.get(TechnologyTree.lTechUnlocksLaws.get(i).get(0).law).Law[TechnologyTree.lTechUnlocksLaws.get(i).get(0).lawID]);
            }
            else if (TechnologyTree.lTechUnlocksUnits.get(i).size() > 0) {
                TechnologyTree.lTechnology.get(i).Name = ArmyManager.lArmy.get(TechnologyTree.lTechUnlocksUnits.get(i).get(0).unitID).get(TechnologyTree.lTechUnlocksUnits.get(i).get(0).armyID).Name;
            }
            else if (TechnologyTree.lTechUnlocksBuildings.get(i).size() > 0) {
                TechnologyTree.lTechnology.get(i).Name = BuildingsManager.buildings.get(TechnologyTree.lTechUnlocksBuildings.get(i).get(0).building).Name[TechnologyTree.lTechUnlocksBuildings.get(i).get(0).buildingID];
            }
            else {
                TechnologyTree.lTechnology.get(i).Name = Game.lang.get(TechnologyTree.lTechnology.get(i).Name);
            }
        }
    }
    
    public static final void loadTechnologyImages() {
        final FileHandle tempFileT = FileManager.loadFile("game/technologies/technologiesImages/numOfImages.txt");
        for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
            TechnologyTree.technologyImages.add(new Image(ImageManager.loadTexture("game/technologies/technologiesImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
        }
        TechnologyTree.iTechnologyWidth = ImageManager.getImage(Images.techBlue).getWidth();
        TechnologyTree.iTechnologyHeight = ImageManager.getImage(Images.techBlue).getHeight();
    }
    
    public static final float getMaxResearch(final int civID) {
        return GameValues.research.MAX_RESEARCH_BASE + ((Game.getCiv(civID).getCapitalProvinceID() >= 0) ? Game.getProvince(Game.getCiv(civID).getCapitalProvinceID()).getGrowthRateWithBonuses() : 1.0f) * GameValues.research.MAX_RESEARCH_PER_GROWTH_RATE_IN_CAPITAL + Game.getCiv(civID).getCapitalLevel() * GameValues.capital.CAPITAL_MAX_RESEARCH_PER_LVL;
    }
    
    static {
        TechnologyTree.technologyImages = new ArrayList<Image>();
        TechnologyTree.lTechnology = new ArrayList<Technology>();
        TechnologyTree.iTechnologySize = 0;
        TechnologyTree.lTechUnlocksBuildings = new ArrayList<List<Building>>();
        TechnologyTree.lTechUnlocksLaws = new ArrayList<List<LawTech>>();
        TechnologyTree.lTechUnlocksUnits = new ArrayList<List<Unit>>();
        TechnologyTree.MapResearchCost = 1.0f;
    }
    
    public static class Building
    {
        public int building;
        public int buildingID;
        
        public Building(final int building, final int buildingID) {
            this.building = building;
            this.buildingID = buildingID;
        }
    }
    
    public static class LawTech
    {
        public int law;
        public int lawID;
        
        public LawTech(final int law, final int lawID) {
            this.law = law;
            this.lawID = lawID;
        }
    }
    
    public static class Unit
    {
        public int unitID;
        public int armyID;
        
        public Unit(final int unitID, final int armyID) {
            this.unitID = unitID;
            this.armyID = armyID;
        }
    }
    
    public static class ConfigTechnologyData
    {
        public String Age_of_History;
        public ArrayList Technology;
    }
    
    public static class Technology
    {
        public int ID;
        public String Name;
        public int ImageID;
        public boolean MaintainTechnologyName;
        public int TreeColumn;
        public int TreeRow;
        public int RequiredTech;
        public int RequiredTech2;
        public int ResearchCost;
        public boolean UnlocksNukes;
        public boolean UnlocksAccessToTheSea;
        public boolean UnlocksColonization;
        public int BattleWidth;
        public int UnitsAttack;
        public int UnitsDefense;
        public int GeneralAttack;
        public int GeneralDefense;
        public int MaxMorale;
        public int Discipline;
        public int Legacy;
        public int Gold;
        public int MaximumLevelOfCapitalCity;
        public int MaximumLevelOfTheMilitaryAcademy;
        public int MaximumLevelOfTheMilitaryAcademyForGenerals;
        public boolean Repeatable;
        public int AI;
        
        public Technology() {
            this.MaintainTechnologyName = false;
            this.UnlocksNukes = false;
            this.UnlocksAccessToTheSea = false;
            this.UnlocksColonization = false;
            this.BattleWidth = 0;
            this.UnitsAttack = 0;
            this.UnitsDefense = 0;
            this.GeneralAttack = 0;
            this.GeneralDefense = 0;
            this.MaxMorale = 0;
            this.Discipline = 0;
            this.Legacy = 0;
            this.Gold = 0;
            this.MaximumLevelOfCapitalCity = 0;
            this.MaximumLevelOfTheMilitaryAcademy = 0;
            this.MaximumLevelOfTheMilitaryAcademyForGenerals = 0;
        }
        
        public int getResearchCost() {
            return (int)(this.ResearchCost * TechnologyTree.MapResearchCost);
        }
    }
}
