// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.terrain;

import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.textures.ImageManager;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.SaveLoad.LoadManager;
import com.badlogic.gdx.utils.Json;
import aoh.kingdoms.history.mainGame.FileManager;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Image;
import java.util.List;

public class TerrainManager
{
    public List<Terrain> terrains;
    public List<List<Image>> terrainImages;
    public static int terrainSmallWidth;
    public static int terrainSmallHeight;
    
    public TerrainManager() {
        this.terrains = new ArrayList<Terrain>();
        this.terrainImages = new ArrayList<List<Image>>();
    }
    
    public final void loadTerrains() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/terrain/TerrainTypes.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)Terrain.class);
            LoadManager.ConfigJson data = (LoadManager.ConfigJson)json.fromJson((Class)LoadManager.ConfigJson.class, fileContent);
            for (final Object e : data.Data) {
                this.terrains.add((Terrain)e);
                try {
                    this.terrains.get(this.terrains.size() - 1).Name = Game.lang.get(this.terrains.get(this.terrains.size() - 1).Name);
                }
                catch (final Exception ex2) {}
            }
            for (int i = 0, iSize = this.terrains.size(); i < iSize; ++i) {
                this.terrains.get(i).Color[0] /= 255.0f;
                this.terrains.get(i).Color[1] /= 255.0f;
                this.terrains.get(i).Color[2] /= 255.0f;
            }
            data = null;
            this.loadTerrainImages();
        }
        catch (final GdxRuntimeException ex) {
            CFG.LOG((Throwable)ex);
        }
    }
    
    private final void loadTerrainImages() {
        for (int i = 0, iSize = this.terrains.size(); i < iSize; ++i) {
            final List<Image> nImages = new ArrayList<Image>();
            for (int a = 0; a < this.terrains.get(i).ImageFile.length; ++a) {
                try {
                    if (FileManager.loadFile("game/terrain/terrainImages/" + CFG.getRescouresPath_Short() + this.terrains.get(i).ImageFile[a]).exists()) {
                        nImages.add(new Image(ImageManager.loadTexture_RGB888("game/terrain/terrainImages/" + CFG.getRescouresPath_Short() + this.terrains.get(i).ImageFile[a])));
                    }
                    else {
                        nImages.add(new Image(ImageManager.loadTexture_RGB888("game/terrain/terrainImages/" + CFG.getRescouresPath_Short_H() + this.terrains.get(i).ImageFile[a])));
                    }
                }
                catch (final GdxRuntimeException ex) {
                    nImages.add(new Image(ImageManager.loadTexture_RGB888("game/terrain/terrainImages/" + CFG.getRescouresPath_Short() + "notFound.png")));
                }
            }
            this.terrainImages.add(nImages);
        }
        final float iconScale = ImageManager.getImage(Images.terrainSmall).getHeight() / (float)this.terrainImages.get(0).get(0).getHeight();
        TerrainManager.terrainSmallWidth = (int)(this.terrainImages.get(0).get(0).getWidth() * iconScale);
        TerrainManager.terrainSmallHeight = (int)(this.terrainImages.get(0).get(0).getHeight() * iconScale);
    }
    
    public int getBattleTerrain(final int terrainID) {
        switch (this.terrains.get(terrainID).BattleOver) {
            case 0: {
                return Images.battleOver;
            }
            case 1: {
                return Images.battleOver1;
            }
            case 3: {
                return Images.battleOver3;
            }
            default: {
                return Images.battleOver2;
            }
        }
    }
    
    static {
        TerrainManager.terrainSmallWidth = 0;
        TerrainManager.terrainSmallHeight = 0;
    }
}
