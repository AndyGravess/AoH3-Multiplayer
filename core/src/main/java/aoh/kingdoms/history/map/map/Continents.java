// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class Continents
{
    public static final int OCEAN_ID = 0;
    public static final int OCEAN_ID_LOW_QUALITY = -4;
    public List<Continent> lContinents;
    public int iContinentsSize;
    
    public final void loadMapContinents() {
        this.lContinents = new ArrayList<Continent>();
        try {
            final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Continents.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)Continent.class);
            final LoadManager.ConfigJson data = (LoadManager.ConfigJson)json.fromJson((Class)LoadManager.ConfigJson.class, fileContent);
            for (final Object e : data.Data) {
                this.lContinents.add((Continent)e);
            }
            this.iContinentsSize = this.lContinents.size();
            for (int i = 0; i < this.iContinentsSize; ++i) {
                this.lContinents.get(i).sName = Game.lang.get(this.lContinents.get(i).sName);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.LOG((Throwable)ex);
        }
    }
    
    public static class Continent
    {
        public String sName;
        public int iR;
        public int iG;
        public int iB;
        public boolean prioritizeColonization;
        
        public Continent() {
            this.prioritizeColonization = false;
        }
    }
}
