// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.ArrayList;
import java.util.List;

public class ArmyManager
{
    public static List<Data_UnitTypes> lUnitsTypes;
    public static int iUnitsTypesSize;
    public static ArrayList<ArrayList<Data_Army>> lArmy;
    public static List<Integer> lArmySize;
    public static List<Image> armyImages;
    public static final int FIRST_LINE_MIDDLE = 0;
    public static final int FIRST_LINE_FLANK = 1;
    public static final int SECOND_LINE = 2;
    public static float averageArmyCost;
    
    public static int getRecruitmentCost(final int iCivID, final int iProvinceID, final int iUnitID, final int iArmyID) {
        return getRecruitmentCost(iCivID, iProvinceID, iUnitID, iArmyID, getRecruitmentCost_Regiments(iCivID));
    }
    
    public static int getRecruitmentCost_Regiments(final int iCivID) {
        return Game.getCiv(iCivID).getArmyRegimentSize() + Game.getCiv(iCivID).iArmyRecruitSize_Total;
    }
    
    public static int getRecruitmentCost(final int iCivID, final int iProvinceID, final int iUnitID, final int iArmyID, final int numOfRegiments) {
        return (int)(((numOfRegiments >= Game.getCiv(iCivID).iRegimentsLimit) ? GameValues.army.REGIMENTS_LIMIT_RECRUIT_COST_OVER : 1.0f) * Math.max(1.0f, ArmyManager.lArmy.get(iUnitID).get(iArmyID).Cost * (1.0f + (((iProvinceID >= 0) ? Game.getProvince(iProvinceID).provBonuses.RecruitArmyCostInProvince : 0.0f) + Game.getCiv(iCivID).civBonuses.RecruitArmyCost + ((ArmyManager.lUnitsTypes.get(iUnitID).Line == 2) ? Game.getCiv(iCivID).civBonuses.RecruitArmySecondLineCost : Game.getCiv(iCivID).civBonuses.RecruitArmyFirstLineCost)) / 100.0f)));
    }
    
    public static int getRecruitmentTime(final int iCivID, final int iProvinceID, final int iUnitID, final int iArmyID) {
        return (int)Math.max(1.0f, ArmyManager.lArmy.get(iUnitID).get(iArmyID).RecruitmentTime * (1.0f + ((iProvinceID >= 0) ? (Game.getProvince(iProvinceID).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_RECRUITMENT_TIME_PER_LVL) : 0.0f) + Game.getCiv(iCivID).getWarWeariness() * GameValues.warWeariness.WW_RECRUITMENT_TIME_PER_POINT + Game.getCiv(iCivID).civBonuses.RecruitmentTime / 100.0f));
    }
    
    public static final void loadUnits() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/units/Units.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigUnitsData.class, "Army", (Class)Data_UnitTypes.class);
            ConfigUnitsData data = (ConfigUnitsData)json.fromJson((Class)ConfigUnitsData.class, fileContent);
            for (final Object e : data.Army) {
                ArmyManager.lUnitsTypes.add((Data_UnitTypes)e);
            }
            data = null;
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        ArmyManager.iUnitsTypesSize = ArmyManager.lUnitsTypes.size();
        try {
            loadArmies();
            loadArmyImages();
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static final void loadArmies() {
        for (int i = 0; i < ArmyManager.iUnitsTypesSize; ++i) {
            try {
                ArmyManager.lArmy.add(new ArrayList<Data_Army>());
                final FileHandle fileList = FileManager.loadFile("game/units/" + ArmyManager.lUnitsTypes.get(i).File);
                final String fileContent = fileList.readString();
                final Json json = new Json();
                json.setElementType((Class)ConfigUnitsData.class, "Army", (Class)Data_Army.class);
                ConfigUnitsData data = (ConfigUnitsData)json.fromJson((Class)ConfigUnitsData.class, fileContent);
                for (final Object e : data.Army) {
                    final Data_Army dataArmy = (Data_Army)e;
                    dataArmy.MaintenanceCost *= Game.map.getActiveMap_MapData().mapData.UnitsMaintenanceCost;
                    dataArmy.Cost *= (int)Game.map.getActiveMap_MapData().mapData.UnitsRecruitCost;
                    ArmyManager.lArmy.get(i).add(dataArmy);
                }
                ArmyManager.lArmySize.add(ArmyManager.lArmy.get(i).size());
                data = null;
            }
            catch (final GdxRuntimeException ex) {
                CFG.exceptionStack((Throwable)ex);
            }
        }
        int tempNumOfUnits = 0;
        for (int j = 0; j < ArmyManager.iUnitsTypesSize; ++j) {
            for (int k = 0; k < ArmyManager.lArmySize.get(j); ++k) {
                ArmyManager.lArmy.get(j).get(k).Name = Game.lang.get(ArmyManager.lArmy.get(j).get(k).Name);
                ArmyManager.averageArmyCost += ArmyManager.lArmy.get(j).get(k).Cost;
                ++tempNumOfUnits;
            }
        }
        ArmyManager.averageArmyCost /= tempNumOfUnits;
    }
    
    public static final void loadArmyImages() {
        final FileHandle tempFileT = FileManager.loadFile("game/units/unitsImages/numOfImages.txt");
        for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
            if (FileManager.loadFile("game/units/unitsImages/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                ArmyManager.armyImages.add(new Image(ImageManager.loadTexture_RGB888("game/units/unitsImages/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
            else {
                ArmyManager.armyImages.add(new Image(ImageManager.loadTexture_RGB888("game/units/unitsImages/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
        }
    }
    
    public static boolean armyCanBeUpgraded(final int iCivID, final int unitTypeID, final int armyID) {
        if (armyID + 1 >= ArmyManager.lArmySize.get(unitTypeID)) {
            return false;
        }
        try {
            if (Game.getCiv(iCivID).getTechResearched(ArmyManager.lArmy.get(unitTypeID).get(armyID + 1).RequiredTechID)) {
                return true;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public static int getUpgradeMaxArmyID(final int iCivID, final int unitTypeID, final int armyID) {
        for (int i = ArmyManager.lArmySize.get(unitTypeID) - 1; i >= armyID; --i) {
            if (Game.getCiv(iCivID).getTechResearched(ArmyManager.lArmy.get(unitTypeID).get(i).RequiredTechID)) {
                return i;
            }
        }
        return armyID;
    }
    
    public static final int upgradeAllArmies(final int iCivID) {
        int out = 0;
        for (int i = 0; i < Game.getCiv(iCivID).iArmyPositionSize; ++i) {
            final int nProvinceID = Game.getCiv(iCivID).getArmyPosition(i);
            if (nProvinceID >= 0) {
                for (int j = Game.getProvince(nProvinceID).getArmySize() - 1; j >= 0; --j) {
                    if (Game.getProvince(nProvinceID).getArmy(j).civID == iCivID) {
                        for (int k = Game.getProvince(nProvinceID).getArmy(j).iArmyRegimentSize - 1; k >= 0; --k) {
                            if (armyCanBeUpgraded(iCivID, Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).uID, Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).aID)) {
                                final int maxUpgrade = getUpgradeMaxArmyID(iCivID, Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).uID, Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).aID);
                                if (Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).aID < maxUpgrade) {
                                    Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).aID = maxUpgrade;
                                    final Civilization civ = Game.getCiv(iCivID);
                                    civ.fGold -= getUpgradeRegimentCost(iCivID);
                                    ++out;
                                }
                            }
                        }
                    }
                }
            }
        }
        return out;
    }
    
    public static final void upgradeDivisionArmy(final int iCivID, final int unitTypeID, final int armyID, final int iProvinceID, final int iArmyID) {
        if (iCivID == Game.getProvince(iProvinceID).getArmy(iArmyID).civID) {
            final int maxUpgrade = getUpgradeMaxArmyID(iCivID, unitTypeID, armyID);
            for (int i = Game.getProvince(iProvinceID).getArmy(iArmyID).iArmyRegimentSize - 1; i >= 0; --i) {
                if (Game.getProvince(iProvinceID).getArmy(iArmyID).lArmyRegiment.get(i).uID == unitTypeID && Game.getProvince(iProvinceID).getArmy(iArmyID).lArmyRegiment.get(i).aID < maxUpgrade) {
                    Game.getProvince(iProvinceID).getArmy(iArmyID).lArmyRegiment.get(i).aID = maxUpgrade;
                    final Civilization civ = Game.getCiv(iCivID);
                    civ.fGold -= getUpgradeRegimentCost(iCivID);
                }
            }
        }
    }
    
    public static float getUpgradeRegimentCost(final int iCivID) {
        return GameValues.army.UPGRADE_REGIMENT_COST;
    }
    
    public static int getRegimentsAvailableToUpgrade(final int iCivID) {
        int out = 0;
        for (int i = 0; i < Game.getCiv(iCivID).iArmyPositionSize; ++i) {
            final int nProvinceID = Game.getCiv(iCivID).getArmyPosition(i);
            if (nProvinceID >= 0) {
                for (int j = Game.getProvince(nProvinceID).getArmySize() - 1; j >= 0; --j) {
                    if (Game.getProvince(nProvinceID).getArmy(j).civID == iCivID) {
                        for (int k = Game.getProvince(nProvinceID).getArmy(j).iArmyRegimentSize - 1; k >= 0; --k) {
                            if (armyCanBeUpgraded(iCivID, Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).uID, Game.getProvince(nProvinceID).getArmy(j).lArmyRegiment.get(k).aID)) {
                                ++out;
                            }
                        }
                    }
                }
            }
        }
        return out;
    }
    
    static {
        ArmyManager.lUnitsTypes = new ArrayList<Data_UnitTypes>();
        ArmyManager.lArmy = new ArrayList<ArrayList<Data_Army>>();
        ArmyManager.lArmySize = new ArrayList<Integer>();
        ArmyManager.armyImages = new ArrayList<Image>();
        ArmyManager.averageArmyCost = 0.0f;
    }
    
    public static class ConfigUnitsData
    {
        public String Age_of_History;
        public ArrayList Army;
    }
    
    public static class Data_UnitTypes
    {
        public String File;
        public int ID;
        public int Line;
    }
    
    public static class Data_Army
    {
        public String Name;
        public int ImageID;
        public int UnitLevel;
        private int Attack;
        private int Defense;
        public float MovementSpeed;
        public int AttackRange;
        public float SiegeProgress;
        public int RequiredTechID;
        public int Cost;
        public float MaintenanceCost;
        public int RecruitmentTime;
        public boolean isSettler;
        public boolean SiegeUnit;
        
        public Data_Army() {
            this.isSettler = false;
            this.SiegeUnit = false;
        }
        
        public int getAttack() {
            return this.Attack;
        }
        
        public int getDefense() {
            return this.Defense;
        }
        
        public int getAttack(final int iCivID) {
            return Math.max(1, this.Attack + Game.getCiv(iCivID).civBonuses.UnitsAttack + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(iCivID).getBattleTacticsID()]);
        }
        
        public int getDefense(final int iCivID) {
            return Math.max(1, this.Defense + Game.getCiv(iCivID).civBonuses.UnitsDefense + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(iCivID).getBattleTacticsID()]);
        }
    }
}
