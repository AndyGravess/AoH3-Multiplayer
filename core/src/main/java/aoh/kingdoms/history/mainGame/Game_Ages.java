// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class Game_Ages
{
    public List<Data_Ages> lAges;
    public int iAgesSize;
    public String sBC;
    
    public Game_Ages() {
        this.lAges = new ArrayList<Data_Ages>();
    }
    
    public final void loadAges() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/Ages.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigAgesData.class, "Age", (Class)Data_Ages.class);
            ConfigAgesData data = new ConfigAgesData();
            data = (ConfigAgesData)json.fromJson((Class)ConfigAgesData.class, fileContent);
            for (final Object e : data.Age) {
                this.lAges.add((Data_Ages)e);
            }
            data = null;
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        this.sBC = Game.lang.get("BeforeChrist");
        this.iAgesSize = this.lAges.size();
        for (int i = 0; i < this.iAgesSize; ++i) {
            this.lAges.get(i).Name = Game.lang.get(this.lAges.get(i).Name);
        }
    }
    
    protected final void updateLanguage() {
        this.loadAges();
    }
    
    public final String getYear(final int nYear) {
        return (nYear < 0) ? ("" + -nYear + " " + this.getBC()) : ("" + nYear);
    }
    
    public final int getAgeOfYear(final int nYear) {
        for (int i = 0; i < this.lAges.size() - 1; ++i) {
            if (this.lAges.get(i).AGE_BeginningYear <= nYear && this.lAges.get(i).AGE_EndYear >= nYear) {
                return i;
            }
        }
        return this.lAges.size() - 1;
    }
    
    public final int getAge_TurnDays(final int nAgeID) {
        return (int)(this.lAges.get(nAgeID).GAME_DAYS_PER_TURN * Game_Calendar.GAME_SPEED);
    }
    
    public final float getAge_DiseaseChance(final int nAgeID) {
        return this.lAges.get(nAgeID).DISEASE_CHANCE;
    }
    
    protected final String getBC() {
        return this.sBC;
    }
    
    protected final int getAgesSize() {
        return this.iAgesSize;
    }
    
    public static String getVassal() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "Vassal";
        }
        return "Puppet";
    }
    
    public static String getVassals() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "Vassals";
        }
        return "PuppetStates";
    }
    
    public static String getManageVassals() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "ManageVassals";
        }
        return "ManageSubjects";
    }
    
    public static String getLord() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "Lord";
        }
        return "ControllingCivilization";
    }
    
    public static String getPlayAsAReleasedVassal() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "PlayAsAReleasedVassal";
        }
        return "PlayAsReleasedCivilization";
    }
    
    public static String getLiberateAVassal() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "LiberateAVassal";
        }
        return "LiberateCivilization";
    }
    
    public static String getReleaseAVassal() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "ReleaseAVassal";
        }
        return "ReleasePuppetState";
    }
    
    public static String getDemandVassalization() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).VASSALS) {
            return "DemandVassalization";
        }
        return "DemandPuppetState";
    }
    
    public static class ConfigAgesData
    {
        public String Age_of_History;
        public ArrayList Age;
    }
    
    public static class Data_Ages
    {
        public String Name;
        public int AGE_BeginningYear;
        public int AGE_EndYear;
        public boolean ENABLE_NON_KINGS_IMG;
        public boolean FORCE_NON_KINGS_IMG;
        public int REGIMENT_SIZE;
        public int REGIMENTS_LIMIT_BASE_VALUE;
        public int POPULATION_GROWTH_PER_MONTH;
        public int[] FLAG_GROUP;
        public boolean FLAG_BELOW;
        public int OVER_IMAGE_ID;
        public int SHIP_GROUP;
        public int GAME_DAYS_PER_TURN;
        public boolean BATTLE_SOUND_2;
        public float DISEASE_CHANCE;
        public float RIVALS_DISTANCE;
        public boolean NEW_GAME_ADVANTAGES;
        public int CHARACTERS_EXTRA_LIFE_EXPECTANCY;
        public boolean VASSALS;
        public boolean UQ_UI;
        public int AI_CONQUER_OWN_VASSALS_IF_OVER;
    }
}
