// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.civilization.CivilizationGeneralsPool;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import java.util.ArrayList;
import java.util.List;
import aoc.kingdoms.lukasz.textures.Image;

public class GeneralManager
{
    public Image noGeneral;
    public List<Integer> generalsImagesSize;
    private ArrayList<List<String>> generalNames;
    private ArrayList<List<String>> generalSurnames;
    
    public GeneralManager() {
        this.generalsImagesSize = new ArrayList<Integer>();
        this.generalNames = new ArrayList<List<String>>();
        this.generalSurnames = new ArrayList<List<String>>();
    }
    
    public static int getRecruitGoldCost(final int iCivID) {
        return (int)(GameValues.generals.RECRUIT_GENERAL_GOLD_COST * Math.max(0.0f, 1.0f + Game.getCiv(iCivID).civBonuses.GeneralCost + GameValues.civRank.CIV_RANK_GENERAL_COST[Game.getCiv(iCivID).iCivRankID]));
    }
    
    public static int getRecruitLegacyCost(final int iCivID) {
        return (int)(GameValues.generals.RECRUIT_GENERAL_LEGACY_COST * Math.max(0.0f, 1.0f + Game.getCiv(iCivID).civBonuses.GeneralCost + GameValues.civRank.CIV_RANK_GENERAL_COST[Game.getCiv(iCivID).iCivRankID]));
    }
    
    public static boolean recruitGeneral_AI(final int iCivID, final int provinceID, final int armyID) {
        try {
            if (Game.getCiv(iCivID).fGold < getRecruitGoldCost(iCivID)) {
                return false;
            }
            if (Game.getCiv(iCivID).fLegacy < getRecruitLegacyCost(iCivID)) {
                return false;
            }
            if (Game.getProvince(provinceID).getArmy(armyID).civID != iCivID) {
                return false;
            }
            if (Game.getProvince(provinceID).getArmy(armyID).armyGeneral != null) {
                return false;
            }
            Game.getProvince(provinceID).getArmy(armyID).setArmyGeneral(CivilizationGeneralsPool.getGeneral_Random(iCivID));
            final Civilization civ = Game.getCiv(iCivID);
            civ.fGold -= getRecruitGoldCost(iCivID);
            final Civilization civ2 = Game.getCiv(iCivID);
            civ2.fLegacy -= getRecruitLegacyCost(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return true;
    }
    
    public final void loadGenerals() {
        this.noGeneral = new Image(ImageManager.loadTexture_RGB888("game/generals/generals/" + CFG.getRescouresPath_Short() + "noGeneral.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        for (int i = 0; i < RulersManager.iGroupsSize; ++i) {
            int numOfGenerals = 0;
            try {
                final FileHandle tempFileT = FileManager.loadFile("game/generals/generals/" + CFG.getRescouresPath_Short() + i + "/numOfGenerals.txt");
                numOfGenerals = Integer.parseInt(tempFileT.readString());
            }
            catch (final GdxRuntimeException ex) {
                CFG.exceptionStack((Throwable)ex);
            }
            this.generalsImagesSize.add(numOfGenerals);
        }
        for (int i = 0; i < RulersManager.iGroupsSize; ++i) {
            final FileHandle tempFileT2 = FileManager.loadFile("game/randomNames/names/" + RulersManager.groups.get(i) + ".txt");
            String[] tNames = tempFileT2.readString().split(";");
            final List<String> tLNames = new ArrayList<String>();
            for (int j = 0, jSize = tNames.length; j < jSize; ++j) {
                tLNames.add(tNames[j]);
            }
            this.generalNames.add(tLNames);
            tNames = null;
        }
        for (int i = 0; i < RulersManager.iGroupsSize; ++i) {
            final FileHandle tempFileT2 = FileManager.loadFile("game/randomNames/surnames/" + RulersManager.groups.get(i) + ".txt");
            String[] tNames = tempFileT2.readString().split(";");
            final List<String> tLNames = new ArrayList<String>();
            for (int j = 0, jSize = tNames.length; j < jSize; ++j) {
                tLNames.add(tNames[j]);
            }
            this.generalSurnames.add(tLNames);
            tNames = null;
        }
    }
    
    public static String getGeneralsImgPath() {
        if (Game_Calendar.currentYear >= GameValues.generals.MODERN_WORLD_GENERALS_SINCE_YEAR) {
            return "generals3/";
        }
        if (!Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).ENABLE_NON_KINGS_IMG && !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FORCE_NON_KINGS_IMG) {
            return "generals/";
        }
        return "generals2/";
    }
    
    public static String getGeneralsImgPath(final int iCivID) {
        if (Game_Calendar.currentYear >= GameValues.generals.MODERN_WORLD_GENERALS_SINCE_YEAR) {
            return "generals3/";
        }
        if ((Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).KingsImages || !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).ENABLE_NON_KINGS_IMG) && !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FORCE_NON_KINGS_IMG) {
            return "generals/";
        }
        return "generals2/";
    }
    
    public String getGeneralRandomName(final int iCivID) {
        String sCivTAG = Game.getCiv(iCivID).realTag;
        if (FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt").exists()) {
            final FileHandle fileList = FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt");
            final String[] tSplit = fileList.readString().split(";");
            if (tSplit.length > 0) {
                return tSplit[Game.oR.nextInt(tSplit.length)];
            }
        }
        else if (FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt").exists()) {
            final FileHandle fileList = FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt");
            sCivTAG = fileList.readString();
            if (FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt").exists()) {
                final FileHandle fileList2 = FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt");
                final String[] tSplit2 = fileList2.readString().split(";");
                if (tSplit2.length > 0) {
                    return tSplit2[Game.oR.nextInt(tSplit2.length)];
                }
            }
        }
        try {
            return this.generalNames.get(Game.getCiv(iCivID).iGroupID).get(Game.oR.nextInt(this.generalNames.get(Game.getCiv(iCivID).iGroupID).size()));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return this.generalNames.get(0).get(Game.oR.nextInt(this.generalNames.get(0).size()));
        }
    }
    
    public String getGeneralRandomSurname(final int iCivID) {
        String sCivTAG = Game.getCiv(iCivID).realTag;
        if (FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt").exists()) {
            final FileHandle fileList = FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt");
            final String[] tSplit = fileList.readString().split(";");
            if (tSplit.length > 0) {
                return tSplit[Game.oR.nextInt(tSplit.length)];
            }
            return "";
        }
        else {
            if (FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt").exists()) {
                final FileHandle fileList = FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt").exists()) {
                    final FileHandle fileList2 = FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt");
                    final String[] tSplit2 = fileList2.readString().split(";");
                    if (tSplit2.length > 0) {
                        return tSplit2[Game.oR.nextInt(tSplit2.length)];
                    }
                }
            }
            try {
                return this.generalSurnames.get(Game.getCiv(iCivID).iGroupID).get(Game.oR.nextInt(this.generalSurnames.get(Game.getCiv(iCivID).iGroupID).size()));
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
                return this.generalSurnames.get(0).get(Game.oR.nextInt(this.generalSurnames.get(0).size()));
            }
        }
    }
}
