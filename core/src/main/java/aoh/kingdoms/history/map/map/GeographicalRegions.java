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

public class GeographicalRegions
{
    public List<GeographicalRegion> lGeographicalRegions;
    public int iGeographicalRegionsSize;
    
    public final void loadMapGeographicalRegions() {
        this.lGeographicalRegions = new ArrayList<GeographicalRegion>();
        try {
            final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "GeographicalRegions.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)GeographicalRegion.class);
            final LoadManager.ConfigJson data = (LoadManager.ConfigJson)json.fromJson((Class)LoadManager.ConfigJson.class, fileContent);
            for (final Object e : data.Data) {
                this.lGeographicalRegions.add((GeographicalRegion)e);
            }
            this.iGeographicalRegionsSize = this.lGeographicalRegions.size();
            for (int i = 0; i < this.iGeographicalRegionsSize; ++i) {
                this.lGeographicalRegions.get(i).sName = Game.lang.get(this.lGeographicalRegions.get(i).sName);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.LOG((Throwable)ex);
        }
    }
    
    public static class GeographicalRegion
    {
        public String sName;
        public int iR;
        public int iG;
        public int iB;
    }
}
