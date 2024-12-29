// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.RomanNumber;
import java.util.Iterator;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions2;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.List;
import aoc.kingdoms.lukasz.textures.Image;

public class RulersManager
{
    public static Image rulerIMG;
    public static Image rulerIMG_DiplomacyLeft;
    public static Image rulerIMG_DiplomacyRight;
    public static List<List<String>> defaultRandomNames;
    public static List<Integer> NUM_OF_RANDOM_RULERS;
    public static List<Integer> nextRulerIMG;
    public static List<Integer> NUM_OF_RANDOM_RULERS_2;
    public static List<Integer> nextRulerIMG_2;
    public static List<String> groups;
    public static int iGroupsSize;
    
    public static void loadRulerIMG(final int iCivID) {
        if (RulersManager.rulerIMG != null) {
            RulersManager.rulerIMG.dispose();
            RulersManager.rulerIMG = null;
        }
        RulersManager.rulerIMG = loadRulerImage(iCivID);
    }
    
    public static void loadRulerIMG_DiplomacyLeft(final int iCivID) {
        if (RulersManager.rulerIMG_DiplomacyLeft != null) {
            RulersManager.rulerIMG_DiplomacyLeft.dispose();
            RulersManager.rulerIMG_DiplomacyLeft = null;
        }
        RulersManager.rulerIMG_DiplomacyLeft = loadRulerImage(iCivID);
    }
    
    public static void loadRulerIMG_DiplomacyRight(final int iCivID) {
        if (RulersManager.rulerIMG_DiplomacyRight != null) {
            RulersManager.rulerIMG_DiplomacyRight.dispose();
            RulersManager.rulerIMG_DiplomacyRight = null;
        }
        RulersManager.rulerIMG_DiplomacyRight = loadRulerImage(iCivID);
    }
    
    public static Image loadRulerImage(final int iCivID) {
        try {
            if (Game.getCiv(iCivID).ruler == null) {
                loadRuler(iCivID, Game.getCiv(iCivID).getCivTag(), false);
            }
            if (Game.getCiv(iCivID).ruler.isRandom) {
                if (Game.getCiv(iCivID).ruler.kingImage) {
                    if (FileManager.loadFile("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Game.getCiv(iCivID).ruler.ImageID + ".png").exists()) {
                        return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Game.getCiv(iCivID).ruler.ImageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                    }
                    return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short_H() + Game.getCiv(iCivID).iGroupID + "/" + Game.getCiv(iCivID).ruler.ImageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
                else {
                    if (FileManager.loadFile("game/rulersRandom/rulersImages2/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Game.getCiv(iCivID).ruler.ImageID + ".png").exists()) {
                        return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages2/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Game.getCiv(iCivID).ruler.ImageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                    }
                    return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages2/" + CFG.getRescouresPath_Short_H() + Game.getCiv(iCivID).iGroupID + "/" + Game.getCiv(iCivID).ruler.ImageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
            }
            else {
                if (FileManager.loadFile("game/rulers/rulersImages/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).ruler.ImageID + ".png").exists()) {
                    return new Image(ImageManager.loadTexture_RGB888("game/rulers/rulersImages/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).ruler.ImageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
                if (FileManager.loadFile("game/rulers/rulersImages/" + CFG.getRescouresPath_Short_H() + Game.getCiv(iCivID).ruler.ImageID + ".png").exists()) {
                    return new Image(ImageManager.loadTexture_RGB888("game/rulers/rulersImages/" + CFG.getRescouresPath_Short_H() + Game.getCiv(iCivID).ruler.ImageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
                try {
                    if (!Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).ENABLE_NON_KINGS_IMG && !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FORCE_NON_KINGS_IMG) {
                        if (FileManager.loadFile("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Math.abs(Game.getCiv(iCivID).ruler.BornYear) % RulersManager.NUM_OF_RANDOM_RULERS.get(Game.getCiv(iCivID).iGroupID) + ".png").exists()) {
                            return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Math.abs(Game.getCiv(iCivID).ruler.BornYear) % RulersManager.NUM_OF_RANDOM_RULERS.get(Game.getCiv(iCivID).iGroupID) + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                        }
                        return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short_H() + Game.getCiv(iCivID).iGroupID + "/" + Math.abs(Game.getCiv(iCivID).ruler.BornYear) % RulersManager.NUM_OF_RANDOM_RULERS.get(Game.getCiv(iCivID).iGroupID) + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                    }
                    else {
                        if (FileManager.loadFile("game/rulersRandom/rulersImages2/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Math.abs(Game.getCiv(iCivID).ruler.BornYear) % RulersManager.NUM_OF_RANDOM_RULERS_2.get(Game.getCiv(iCivID).iGroupID) + ".png").exists()) {
                            return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages2/" + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + Math.abs(Game.getCiv(iCivID).ruler.BornYear) % RulersManager.NUM_OF_RANDOM_RULERS_2.get(Game.getCiv(iCivID).iGroupID) + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                        }
                        return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages2/" + CFG.getRescouresPath_Short_H() + Game.getCiv(iCivID).iGroupID + "/" + Math.abs(Game.getCiv(iCivID).ruler.BornYear) % RulersManager.NUM_OF_RANDOM_RULERS_2.get(Game.getCiv(iCivID).iGroupID) + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                    return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short() + "0/0.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
            }
        }
        catch (final Exception ex) {
            return new Image(ImageManager.loadTexture_RGB888("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short() + "0/0.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        }
    }
    
    public static final void initRandomRulers() {
        int numOfGroups = 0;
        try {
            final FileHandle tempFileT = FileManager.loadFile("game/groups/civsGroups.txt");
            final String[] tText = tempFileT.readString().split(";");
            numOfGroups = tText.length;
            for (int i = 0; i < numOfGroups; ++i) {
                RulersManager.groups.add(tText[i]);
            }
            RulersManager.iGroupsSize = RulersManager.groups.size();
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        for (int j = 0; j < RulersManager.iGroupsSize; ++j) {
            final FileHandle tempFileT2 = FileManager.loadFile("game/rulersRandom/" + RulersManager.groups.get(j) + ".txt");
            final String[] tNames = tempFileT2.readString().split(";");
            final List<String> tLNames = new ArrayList<String>();
            for (int k = 0, jSize = tNames.length; k < jSize; ++k) {
                tLNames.add(tNames[k]);
            }
            RulersManager.defaultRandomNames.add(tLNames);
        }
        try {
            for (int j = 0; j < RulersManager.iGroupsSize; ++j) {
                final FileHandle tempFileT3 = FileManager.loadFile("game/rulersRandom/rulersImages/" + CFG.getRescouresPath_Short() + j + "/numOfImages.txt");
                RulersManager.NUM_OF_RANDOM_RULERS.add(Integer.parseInt(tempFileT3.readString()));
            }
            for (int j = 0, iSize = RulersManager.NUM_OF_RANDOM_RULERS.size(); j < iSize; ++j) {
                RulersManager.nextRulerIMG.add(Game.oR.nextInt(RulersManager.NUM_OF_RANDOM_RULERS.get(j)));
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        try {
            for (int j = 0; j < RulersManager.iGroupsSize; ++j) {
                final FileHandle tempFileT3 = FileManager.loadFile("game/rulersRandom/rulersImages2/" + CFG.getRescouresPath_Short() + j + "/numOfImages.txt");
                RulersManager.NUM_OF_RANDOM_RULERS_2.add(Integer.parseInt(tempFileT3.readString()));
            }
            for (int j = 0, iSize = RulersManager.NUM_OF_RANDOM_RULERS_2.size(); j < iSize; ++j) {
                RulersManager.nextRulerIMG_2.add(Game.oR.nextInt(RulersManager.NUM_OF_RANDOM_RULERS_2.get(j)));
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
    }
    
    public static final void loadRulers(final int startID, final int endID) {
        for (int i = startID; i < endID; ++i) {
            try {
                loadRuler(i, Game.getCiv(i).getCivTag(), false);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final boolean characterDies(final int iCivID, final int iBornYear) {
        final int chanceID = Math.max(0, Math.min(10, (Game_Calendar.currentYear - iBornYear - Game.getCiv(iCivID).civBonuses.AllCharactersLifeExpectancy - Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).CHARACTERS_EXTRA_LIFE_EXPECTANCY) / 10 / Game.gameAges.getAge_TurnDays(Game_Calendar.CURRENT_AGEID)));
        return Game.oR.nextInt(10000) < GameValues.advisors.CHANCE_OF_DEATH[chanceID] + (GameValues.advisors.CHANCE_OF_DEATH[chanceID + 1] - GameValues.advisors.CHANCE_OF_DEATH[chanceID]) * ((Game_Calendar.currentYear - iBornYear) % 10 / 10.0f);
    }
    
    public static final void update_ChanceOfDeathOfRuler(final int iCivID) {
        try {
            if (characterDies(iCivID, Game.getCiv(iCivID).ruler.BornYear)) {
                deathOfRuler(iCivID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void deathOfRuler(final int iCivID) {
        if (iCivID == Game.player.iCivID) {
            Game.player.addNotification(new Notification(Notification.Notification_Type.LEADER_DIED, Game.lang.get("DeathOfALeader") + ": " + Game.getCiv(iCivID).ruler.Name, Images.council, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, Game.player.iCivID));
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("DeathOfALeader"), Game.getCiv(iCivID).ruler.Name);
            InGame_Info.imgID = Images.infoCrown;
            Game.addSimpleTask(new Game.SimpleTask("rebuildCourt") {
                @Override
                public void update() {
                    if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions2.idCourt) {
                        Game.menuManager.rebuildInGame_CourtSavePos();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                }
            });
        }
        for (int i = 0; i < Game.getCiv(iCivID).inAllianceSize; ++i) {
            if (Game.alliancesSpecial.get(Game.getCiv(iCivID).inAlliance.get(i)).iLeaderCivID == iCivID && Game.alliancesSpecial.get(Game.getCiv(iCivID).inAlliance.get(i)).typeOfAlliance == 0) {
                Game.alliancesSpecial.get(Game.getCiv(iCivID).inAlliance.get(i)).elections();
            }
        }
        Game.addSimpleTask(new Game.SimpleTask("deathOfRuler" + iCivID, iCivID) {
            @Override
            public void update() {
                Game.getCiv(this.id).ruler.updateCivBonuses(this.id, -1);
                RulersManager.loadRuler(this.id, Game.getCiv(this.id).getCivTag(), true);
            }
        });
    }
    
    public static final void loadRuler(final int iCivID, final String sCivTAG, final boolean random) {
        try {
            try {
                if (!random) {
                    FileHandle fileList = null;
                    if (FileManager.loadFile("game/rulers/" + sCivTAG + ".json").exists()) {
                        fileList = FileManager.loadFile("game/rulers/" + sCivTAG + ".json");
                    }
                    else if (FileManager.loadFile("game/rulers/" + Game.getCiv(iCivID).realTag + ".json").exists()) {
                        fileList = FileManager.loadFile("game/rulers/" + Game.getCiv(iCivID).realTag + ".json");
                    }
                    else if (FileManager.loadFile("game/rulers/link/" + Game.getCiv(iCivID).realTag + ".txt").exists()) {
                        final FileHandle tempFileT = FileManager.loadFile("game/rulers/link/" + Game.getCiv(iCivID).realTag + ".txt");
                        final String tempFileName = tempFileT.readString();
                        if (FileManager.loadFile("game/rulers/" + tempFileName + ".json").exists()) {
                            fileList = FileManager.loadFile("game/rulers/" + tempFileName + ".json");
                        }
                    }
                    if (fileList != null) {
                        final String fileContent = fileList.readString();
                        final Json json = new Json();
                        json.setElementType((Class)ConfigRulersData.class, "Rulers", (Class)Rulers.class);
                        final ConfigRulersData data = (ConfigRulersData)json.fromJson((Class)ConfigRulersData.class, fileContent);
                        List<Rulers> tempRulers = new ArrayList<Rulers>();
                        int tRulersSize = 0;
                        for (final Object e : data.Rulers) {
                            tempRulers.add((Rulers)e);
                            ++tRulersSize;
                        }
                        if (!tempRulers.isEmpty()) {
                            int bestID = 0;
                            if (tempRulers.get(bestID).ReignYear < Game_Calendar.currentYear) {
                                for (int i = tRulersSize - 1; i > 0; --i) {
                                    if (tempRulers.get(i).ReignYear <= Game_Calendar.currentYear) {
                                        bestID = i;
                                        break;
                                    }
                                }
                            }
                            if (tempRulers.get(bestID).ReignYear <= Game_Calendar.currentYear && tempRulers.get(bestID).BornYear > Game_Calendar.currentYear - 96 && tempRulers.get(bestID).BornYear < Game_Calendar.currentYear) {
                                Game.getCiv(iCivID).ruler = new Ruler(iCivID, tempRulers.get(bestID).Name, "" + tempRulers.get(bestID).ImageID, tempRulers.get(bestID).BornDay, tempRulers.get(bestID).BornMonth, tempRulers.get(bestID).BornYear, tempRulers.get(bestID).ReignYear, false, false);
                                tempRulers.clear();
                                tempRulers = null;
                                return;
                            }
                            tempRulers.clear();
                            tempRulers = null;
                        }
                    }
                }
            }
            catch (final Exception ex) {
                CFG.LOG("ERROR, CIV TAG: " + Game.getCiv(iCivID).getCivTag());
                CFG.exceptionStack(ex);
            }
            final int nMonth = Game.oR.nextInt(12);
            if ((Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).KingsImages || !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).ENABLE_NON_KINGS_IMG) && !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FORCE_NON_KINGS_IMG) {
                Game.getCiv(iCivID).ruler = new Ruler(iCivID, getRulerRandomName(iCivID, sCivTAG), "" + RulersManager.nextRulerIMG.get(Game.getCiv(iCivID).iGroupID), Game.oR.nextInt(Game_Calendar.getNumOfDaysInMonth(nMonth)), nMonth, Game_Calendar.currentYear - GameValues.court.RULER_YEARS_OLD_MIN - Game.oR.nextInt(GameValues.court.RULER_YEARS_OLD_RANDOM), Game_Calendar.currentYear, true, true);
                int nextIMG = RulersManager.nextRulerIMG.get(Game.getCiv(iCivID).iGroupID) + 1;
                if (nextIMG >= RulersManager.NUM_OF_RANDOM_RULERS.get(Game.getCiv(iCivID).iGroupID)) {
                    nextIMG = 0;
                }
                RulersManager.nextRulerIMG.set(Game.getCiv(iCivID).iGroupID, nextIMG);
            }
            else {
                Game.getCiv(iCivID).ruler = new Ruler(iCivID, getRulerRandomName(iCivID, sCivTAG), "" + RulersManager.nextRulerIMG_2.get(Game.getCiv(iCivID).iGroupID), Game.oR.nextInt(Game_Calendar.getNumOfDaysInMonth(nMonth)), nMonth, Game_Calendar.currentYear - GameValues.court.RULER_YEARS_OLD_MIN - Game.oR.nextInt(GameValues.court.RULER_YEARS_OLD_RANDOM), Game_Calendar.currentYear, true, false);
                int nextIMG = RulersManager.nextRulerIMG_2.get(Game.getCiv(iCivID).iGroupID) + 1;
                if (nextIMG >= RulersManager.NUM_OF_RANDOM_RULERS_2.get(Game.getCiv(iCivID).iGroupID)) {
                    nextIMG = 0;
                }
                RulersManager.nextRulerIMG_2.set(Game.getCiv(iCivID).iGroupID, nextIMG);
            }
        }
        catch (final Exception ex) {
            CFG.LOG("ERROR, CIV TAG: " + Game.getCiv(iCivID).getCivTag());
            CFG.exceptionStack(ex);
        }
    }
    
    public static String getRulerRandomName(final int iCivID, String sCivTAG) {
        sCivTAG = Game.getCiv(iCivID).realTag;
        if (Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).RulerRoman) {
            if (FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt").exists()) {
                final FileHandle fileList = FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt");
                final String[] tSplit = fileList.readString().split(";");
                if (tSplit.length > 0) {
                    return tSplit[Game.oR.nextInt(tSplit.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                }
            }
            else if (FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt").exists()) {
                final FileHandle fileList = FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt").exists()) {
                    final FileHandle fileList2 = FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt");
                    final String[] tSplit2 = fileList2.readString().split(";");
                    if (tSplit2.length > 0) {
                        return tSplit2[Game.oR.nextInt(tSplit2.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                    }
                }
            }
            return Game.generalManager.getGeneralRandomName(iCivID) + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(9));
        }
        return Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID);
    }
    
    static {
        RulersManager.rulerIMG = null;
        RulersManager.rulerIMG_DiplomacyLeft = null;
        RulersManager.rulerIMG_DiplomacyRight = null;
        RulersManager.defaultRandomNames = new ArrayList<List<String>>();
        RulersManager.NUM_OF_RANDOM_RULERS = new ArrayList<Integer>();
        RulersManager.nextRulerIMG = new ArrayList<Integer>();
        RulersManager.NUM_OF_RANDOM_RULERS_2 = new ArrayList<Integer>();
        RulersManager.nextRulerIMG_2 = new ArrayList<Integer>();
        RulersManager.groups = new ArrayList<String>();
        RulersManager.iGroupsSize = 0;
    }
    
    public static class ConfigRulersData
    {
        public String Age_of_History;
        public ArrayList Rulers;
    }
    
    public static class Rulers
    {
        public String Name;
        public String ImageID;
        public int BornDay;
        public int BornMonth;
        public int BornYear;
        public int ReignYear;
    }
}
