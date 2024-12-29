// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.List;

public class RegionManager
{
    public List<Region> lRegions;
    public int iRegionsSize;
    
    public RegionManager() {
        this.lRegions = new ArrayList<Region>();
        this.iRegionsSize = 0;
    }
    
    public final void loadRegions() {
        this.lRegions.clear();
        this.iRegionsSize = 0;
        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "ProvinceOptimizationRegions.txt").exists()) {
            final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "ProvinceOptimizationRegions.txt");
            final String text = file.readString();
            final String[] sAllRegions = text.split("\n");
            final List<Boolean> tempAdded = new ArrayList<Boolean>();
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                tempAdded.add(false);
            }
            for (int i = 0; i < sAllRegions.length; ++i) {
                final String[] sRegion = sAllRegions[i].split(";");
                final Region newRegion = new Region();
                for (int j = 0; j < sRegion.length; ++j) {
                    newRegion.addProvince(Integer.parseInt(sRegion[j]));
                }
                for (int j = 0; j < sRegion.length; ++j) {
                    tempAdded.set(newRegion.getProvince(j), true);
                }
                this.lRegions.add(newRegion);
                this.lRegions.get(i).buildRegionBounds();
            }
            final Region tempRegionOfProvincesWithoutIDs = new Region();
            for (int k = 0; k < Game.getProvincesSize(); ++k) {
                if (!tempAdded.get(k)) {
                    tempRegionOfProvincesWithoutIDs.addProvince(k);
                }
            }
            if (tempRegionOfProvincesWithoutIDs.getProvincesSize2() > 0) {
                this.lRegions.add(tempRegionOfProvincesWithoutIDs);
                this.lRegions.get(this.lRegions.size() - 1).buildRegionBounds();
            }
            this.iRegionsSize = this.lRegions.size();
        }
        else {
            final Region newRegion2 = new Region();
            for (int l = 0; l < Game.getProvincesSize(); ++l) {
                newRegion2.addProvince(l);
            }
            this.lRegions.add(newRegion2);
            this.lRegions.get(0).buildRegionBounds();
            this.iRegionsSize = this.lRegions.size();
        }
    }
    
    public final int getRegionID(final int nProvinceID) {
        for (int i = 0; i < this.iRegionsSize; ++i) {
            for (int j = 0; j < this.lRegions.get(i).getProvincesSize(); ++j) {
                if (this.lRegions.get(i).getProvince(j) == nProvinceID) {
                    return i;
                }
            }
        }
        Gdx.app.log("AoC", "REGION ERROR: " + nProvinceID);
        return 0;
    }
    
    public final void updateRegionsSize() {
        this.iRegionsSize = this.lRegions.size();
    }
    
    public final void disposeRegions() {
        this.lRegions.clear();
        this.iRegionsSize = 0;
    }
}
