// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.plague;

import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class PlagueManager
{
    public static List<Plague> activePlagues;
    public static List<Data_Disease> lPlagues;
    public static int iPlaguesSize;
    public static List<Image> plagueImages;
    public static List<Image> plagueImagesBig;
    
    public static final void runPlagues() {
        try {
            for (int i = PlagueManager.activePlagues.size() - 1; i >= 0; --i) {
                try {
                    PlagueManager.activePlagues.get(i).runDisease();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (int i = PlagueManager.activePlagues.size() - 1; i >= 0; --i) {
                try {
                    final Plague plague = PlagueManager.activePlagues.get(i);
                    final int iDurationTurnsLeft = plague.iDurationTurnsLeft - 1;
                    plague.iDurationTurnsLeft = iDurationTurnsLeft;
                    if (iDurationTurnsLeft < 1 && PlagueManager.activePlagues.get(i).lProvinces_Active.size() == 0) {
                        for (int k = i + 1; k < PlagueManager.activePlagues.size(); ++k) {
                            for (int o = 0; o < PlagueManager.activePlagues.get(k).lProvinces_Active.size(); ++o) {
                                if (Game.getProvince(PlagueManager.activePlagues.get(k).lProvinces_Active.get(o)).provincePlague != null && Game.getProvince(PlagueManager.activePlagues.get(k).lProvinces_Active.get(o)).provincePlague.id == PlagueManager.activePlagues.get(k).getPlagueID_InGame()) {
                                    final ProvincePlague provincePlague = Game.getProvince(PlagueManager.activePlagues.get(k).lProvinces_Active.get(o)).provincePlague;
                                    --provincePlague.id;
                                }
                            }
                            PlagueManager.activePlagues.get(k).setPlagueID_InGame(PlagueManager.activePlagues.get(k).getPlagueID_InGame() - 1);
                        }
                        PlagueManager.activePlagues.remove(i);
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (int i = PlagueManager.activePlagues.size() - 1; i >= 0; --i) {
                PlagueManager.activePlagues.get(i).spreadDisease();
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static final void startDisease() {
        int tRandScore = Game.oR.nextInt(GameValues.plagues.DISEASE_OUTBREAK_RANDOM);
        if (tRandScore < GameValues.plagues.DISEASE_OUTBREAK_RANDOM * Game.gameAges.getAge_DiseaseChance(Game_Calendar.CURRENT_AGEID)) {
            final List<Integer> tempIDsToSpawn = new ArrayList<Integer>();
            int tScoreTotal = 0;
            for (int i = 0; i < PlagueManager.iPlaguesSize; ++i) {
                if (Game_Calendar.currentYear >= PlagueManager.lPlagues.get(i).BeginningYear && Game_Calendar.currentYear <= PlagueManager.lPlagues.get(i).EndYear) {
                    tempIDsToSpawn.add(i);
                    tScoreTotal += (int)(PlagueManager.lPlagues.get(i).OUTBREAK_CHANCE * GameValues.plagues.DISEASE_OUTBREAK_MODIFY);
                }
            }
            if (tempIDsToSpawn.size() > 0) {
                int spawnID = 0;
                if (tScoreTotal > 0) {
                    int j = tempIDsToSpawn.size() - 1;
                    int tCurrentScore = 0;
                    while (j >= 0) {
                        tCurrentScore += (int)(PlagueManager.lPlagues.get(tempIDsToSpawn.get(j)).OUTBREAK_CHANCE * GameValues.plagues.DISEASE_OUTBREAK_MODIFY);
                        tRandScore = Game.oR.nextInt(tScoreTotal);
                        if (tCurrentScore > tRandScore) {
                            spawnID = j;
                            break;
                        }
                        --j;
                    }
                }
                else {
                    spawnID = Game.oR.nextInt(tempIDsToSpawn.size());
                }
                startDisease(tempIDsToSpawn.get(spawnID));
            }
        }
    }
    
    private static final void startDisease(final int nID) {
        try {
            int nOutbreakProvinces = PlagueManager.lPlagues.get(nID).OUTBREAK_PROVINCES;
            if (PlagueManager.lPlagues.get(nID).OUTBREAK_PROVINCES_EXTRA > 0) {
                nOutbreakProvinces += Game.oR.nextInt(PlagueManager.lPlagues.get(nID).OUTBREAK_PROVINCES_EXTRA);
            }
            final List<Integer> lPossibleProvinces = new ArrayList<Integer>();
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                if (Game.getProvince(i).getWasteland() < 0 && !Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(i).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                    lPossibleProvinces.add(i);
                }
            }
            if (lPossibleProvinces.size() > 0) {
                final List<Integer> lSpreadPropositions = new ArrayList<Integer>();
                int nToCheck = 8 + (int)(10.0f * Math.min(PlagueManager.lPlagues.get(nID).DEATH_RATE_MIN, 1.0f));
                while (lPossibleProvinces.size() > 0 && nToCheck-- > 0) {
                    final int tRandID = Game.oR.nextInt(lPossibleProvinces.size());
                    lSpreadPropositions.add(lPossibleProvinces.get(tRandID));
                    lPossibleProvinces.remove(tRandID);
                }
                lPossibleProvinces.clear();
                if (lSpreadPropositions.size() > 0) {
                    final List<Float> lSpreadPropositions_Score = new ArrayList<Float>();
                    int tMaxPopulation = 0;
                    float tMaxEconomy = 0.0f;
                    float tMaxInfrastructure = 0.0f;
                    float tMaxDevastation = 0.0f;
                    for (int j = lSpreadPropositions.size() - 1; j >= 0; --j) {
                        if (Game.getProvince(lSpreadPropositions.get(j)).getPopulationTotal() > tMaxPopulation) {
                            tMaxPopulation = Game.getProvince(lSpreadPropositions.get(j)).getPopulationTotal();
                        }
                        if (Game.getProvince(lSpreadPropositions.get(j)).getEconomyWithBonuses() > tMaxEconomy) {
                            tMaxEconomy = Game.getProvince(lSpreadPropositions.get(j)).getEconomyWithBonuses();
                        }
                        if (Game.getProvince(lSpreadPropositions.get(j)).getInfrastructure() > tMaxInfrastructure) {
                            tMaxInfrastructure = (float)Game.getProvince(lSpreadPropositions.get(j)).getInfrastructure();
                        }
                        if (Game.getProvince(lSpreadPropositions.get(j)).getDevastation() > tMaxDevastation) {
                            tMaxDevastation = Game.getProvince(lSpreadPropositions.get(j)).getDevastation();
                        }
                    }
                    for (int j = lSpreadPropositions.size() - 1; j >= 0; --j) {
                        lSpreadPropositions_Score.add(PlagueManager.lPlagues.get(nID).OUTBREAK_SCORE_POPULATION * Game.getProvince(lSpreadPropositions.get(j)).getPopulationTotal() / tMaxPopulation + PlagueManager.lPlagues.get(nID).OUTBREAK_SCORE_ECONOMY * Game.getProvince(lSpreadPropositions.get(j)).getEconomyWithBonuses() / tMaxEconomy + (PlagueManager.lPlagues.get(nID).OUTBREAK_SCORE_INFRASTRUCTURE - PlagueManager.lPlagues.get(nID).OUTBREAK_SCORE_INFRASTRUCTURE * Game.getProvince(lSpreadPropositions.get(j)).getInfrastructure() / tMaxInfrastructure) + PlagueManager.lPlagues.get(nID).OUTBREAK_SCORE_DEVASTATION * Game.getProvince(lSpreadPropositions.get(j)).getDevastation() / tMaxDevastation);
                    }
                    int tBestID = 0;
                    for (int k = lSpreadPropositions_Score.size() - 1; k > 0; --k) {
                        if (lSpreadPropositions_Score.get(tBestID) < lSpreadPropositions_Score.get(k)) {
                            tBestID = k;
                        }
                    }
                    final int nPlagueID_InGame = PlagueManager.activePlagues.size();
                    PlagueManager.activePlagues.add(new Plague(lSpreadPropositions.get(tBestID), PlagueManager.lPlagues.get(nID).Name, PlagueManager.lPlagues.get(nID).R / 255.0f, PlagueManager.lPlagues.get(nID).G / 255.0f, PlagueManager.lPlagues.get(nID).B / 255.0f, nPlagueID_InGame, PlagueManager.lPlagues.get(nID).DEATH_RATE_MIN + Game.oR.nextInt((int)(PlagueManager.lPlagues.get(nID).DEATH_RATE_EXTRA * 100000.0f + 1.0f)) / 100000.0f, PlagueManager.lPlagues.get(nID).DURATION_TURNS_MIN + ((PlagueManager.lPlagues.get(nID).DURATION_TURNS_EXTRA > 0) ? Game.oR.nextInt(PlagueManager.lPlagues.get(nID).DURATION_TURNS_EXTRA) : 0), PlagueManager.lPlagues.get(nID).EXPANSION_MODIFIER + Game.oR.nextInt((int)(PlagueManager.lPlagues.get(nID).EXPANSION_MODIFIER_EXTRA * 100000.0f + 1.0f)) / 100000.0f, PlagueManager.lPlagues.get(nID).ImageID, PlagueManager.lPlagues.get(nID).DEVASTATION));
                    lSpreadPropositions.clear();
                    lSpreadPropositions_Score.clear();
                    if (--nOutbreakProvinces > 0) {
                        PlagueManager.activePlagues.get(nPlagueID_InGame).spreadDisease(nOutbreakProvinces);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadDiseases() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/diseases/Diseases.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigDiseaseData.class, "Disease", (Class)Data_Disease.class);
            final ConfigDiseaseData data = (ConfigDiseaseData)json.fromJson((Class)ConfigDiseaseData.class, fileContent);
            for (final Object e : data.Disease) {
                PlagueManager.lPlagues.add((Data_Disease)e);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        PlagueManager.iPlaguesSize = PlagueManager.lPlagues.size();
        loadPlagueImages();
    }
    
    public static final void loadPlagueImages() {
        final FileHandle tempFileT = FileManager.loadFile("game/diseases/images/numOfImages.txt");
        for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
            if (FileManager.loadFile("game/diseases/images/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                PlagueManager.plagueImages.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                PlagueManager.plagueImagesBig.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short() + i + "b.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
            else {
                PlagueManager.plagueImages.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                PlagueManager.plagueImagesBig.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short_H() + i + "b.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
        }
    }
    
    static {
        PlagueManager.activePlagues = new ArrayList<Plague>();
        PlagueManager.lPlagues = new ArrayList<Data_Disease>();
        PlagueManager.iPlaguesSize = 0;
        PlagueManager.plagueImages = new ArrayList<Image>();
        PlagueManager.plagueImagesBig = new ArrayList<Image>();
    }
    
    public static class ConfigDiseaseData
    {
        public String Age_of_History;
        public ArrayList Disease;
    }
    
    public static class Data_Disease
    {
        public String Name;
        public int BeginningYear;
        public int EndYear;
        public int ImageID;
        public float OUTBREAK_CHANCE;
        public int OUTBREAK_PROVINCES;
        public int OUTBREAK_PROVINCES_EXTRA;
        public int DURATION_TURNS_MIN;
        public int DURATION_TURNS_EXTRA;
        public float EXPANSION_MODIFIER;
        public float EXPANSION_MODIFIER_EXTRA;
        public float OUTBREAK_SCORE_POPULATION;
        public float OUTBREAK_SCORE_ECONOMY;
        public float OUTBREAK_SCORE_INFRASTRUCTURE;
        public float OUTBREAK_SCORE_DEVASTATION;
        public float DEATH_RATE_MIN;
        public float DEATH_RATE_EXTRA;
        public float DEVASTATION;
        public float R;
        public float G;
        public float B;
    }
}
