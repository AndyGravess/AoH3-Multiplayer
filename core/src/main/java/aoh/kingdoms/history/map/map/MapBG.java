// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import com.badlogic.gdx.graphics.PixmapIO;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_PrintMap;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class MapBG
{
    private List<Image> gameMap;
    public int gameMapSize;
    public int iMapScale;
    public float iMapScaleBG;
    public float iMapExtraScale;
    private int iBackgroundSize_X;
    private int iBackgroundSize_Y;
    public static int iWidthOfMap;
    public static int iHeightOfMap;
    private int iWidthOfMap_Real;
    private int iHeightOfMap_Real;
    private int iWidthOfSingleBG;
    private int iHeightOfSingleBG;
    private int iWidthOfSingleBG_xMapScale;
    private int iHeightOfSingleBG_xMapScale;
    private List<Image> gameMap2;
    public int gameMap2Size;
    private int iBackground2Size_X;
    private int iBackground2Size_Y;
    private int iWidthOfSingleBG2;
    private int iHeightOfSingleBG2;
    private int iWidthOfSingleBGTech;
    private int iHeightOfSingleBGTech;
    private int iWidthOfMapTech;
    public int iHeightOfMapTech;
    public boolean secondSideOfMap;
    public Image minimapOfCivilizations;
    public Image minimapBG;
    public Image minimapOver;
    public boolean requestToDisposeMinimap;
    public int iMinimapHeight;
    public int iMinimapWidth;
    public float fMinimapScaleX;
    public float fMinimapScaleY;
    public Image scenarioBG;
    public Image scenarioMask;
    public Image scenarioOver;
    public Image previewOfCivilizations;
    public int iPreviewHeight;
    public int iPreviewWidth;
    public float fPreviewScaleX;
    public float fPreviewScaleY;
    public static int loadMapBG_FileID;
    private MapShader mapShader;
    private List<OutsideMapImage> outsideMapImagesData;
    private List<Image> outsideMapImages;
    private int outsideMapImagesSize;
    private List<OutsideMapImage> outsideMapImagesData_Below;
    private List<Image> outsideMapImages_Below;
    private int outsideMapImagesSize_Below;
    public MapBorder mapBorder;
    private WorldMap worldMap;
    public boolean drawMapAnimation;
    private int iBGID;
    private long animationTime;
    private boolean inAnimation;
    public static MapBGSea mapBGSea;
    public boolean isOutsideInView;
    public boolean isOutsideInView_Bot;
    public float outsideScale;
    public float outsideScale2;
    public final int ALPHA_MINIMAPS = 220;
    public final float EXTRA_XY = 0.125f;
    public int iMinimapScaled_PosX;
    public int iMinimapScaled_PosY;
    public int iMinimapScaled_Width;
    public int iMinimapScaled_Height;
    public float fMinimapScaled_Scale;
    public boolean minimapBelowZero;
    public int iPreviewScaled_PosX;
    public int iPreviewScaled_PosY;
    public int iPreviewScaled_Width;
    public int iPreviewScaled_Height;
    public float fPreviewScaled_Scale;
    public boolean PreviewBelowZero;
    
    public boolean getHideMenuZoomOut() {
        return Game.mapScale.getCurrentScale() > Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Scale;
    }
    
    public MapBG() {
        this.gameMap = new ArrayList<Image>();
        this.gameMapSize = 0;
        this.iMapScale = 1;
        this.iMapScaleBG = 1.0f;
        this.iMapExtraScale = 1.0f;
        this.iBackgroundSize_X = 1;
        this.iBackgroundSize_Y = 1;
        this.iWidthOfMap_Real = 1;
        this.iHeightOfMap_Real = 1;
        this.iWidthOfSingleBG = 1;
        this.iHeightOfSingleBG = 1;
        this.iWidthOfSingleBG_xMapScale = 1;
        this.iHeightOfSingleBG_xMapScale = 1;
        this.gameMap2 = new ArrayList<Image>();
        this.gameMap2Size = 0;
        this.iBackground2Size_X = 1;
        this.iBackground2Size_Y = 1;
        this.iWidthOfSingleBG2 = 1;
        this.iHeightOfSingleBG2 = 1;
        this.iWidthOfSingleBGTech = 1;
        this.iHeightOfSingleBGTech = 1;
        this.iWidthOfMapTech = 1;
        this.iHeightOfMapTech = 1;
        this.secondSideOfMap = true;
        this.minimapOfCivilizations = null;
        this.minimapBG = null;
        this.minimapOver = null;
        this.requestToDisposeMinimap = false;
        this.scenarioBG = null;
        this.scenarioMask = null;
        this.scenarioOver = null;
        this.previewOfCivilizations = null;
        this.outsideMapImagesData = new ArrayList<OutsideMapImage>();
        this.outsideMapImages = new ArrayList<Image>();
        this.outsideMapImagesSize = 0;
        this.outsideMapImagesData_Below = new ArrayList<OutsideMapImage>();
        this.outsideMapImages_Below = new ArrayList<Image>();
        this.outsideMapImagesSize_Below = 0;
        this.mapBorder = new MapBorder();
        this.worldMap = null;
        this.drawMapAnimation = true;
        this.iBGID = 0;
        this.animationTime = 0L;
        this.inAnimation = false;
        this.isOutsideInView = false;
        this.isOutsideInView_Bot = false;
        this.outsideScale = 1.0f;
        this.outsideScale2 = 1.0f;
        this.iMinimapScaled_PosX = 0;
        this.iMinimapScaled_PosY = 0;
        this.iMinimapScaled_Width = 1;
        this.iMinimapScaled_Height = 1;
        this.fMinimapScaled_Scale = 1.0f;
        this.minimapBelowZero = false;
        this.iPreviewScaled_PosX = 0;
        this.iPreviewScaled_PosY = 0;
        this.iPreviewScaled_Width = 1;
        this.iPreviewScaled_Height = 1;
        this.fPreviewScaled_Scale = 1.0f;
        this.PreviewBelowZero = false;
        this.updateActiveMapBGShader();
        this.updateWorldMap();
    }
    
    public final void loadMapBG_Begin() {
        if (this.gameMapSize > 0) {
            this.dispose();
        }
        this.iBackgroundSize_X = Game.map.getActiveMap_MapData().mapData.BackgroundSize_X;
        this.iBackgroundSize_Y = Game.map.getActiveMap_MapData().mapData.BackgroundSize_Y;
    }
    
    public final boolean loadMapBG() {
        final int j = MapBG.loadMapBG_FileID++;
        if (j < this.iBackgroundSize_Y) {
            for (int i = 0; i < this.iBackgroundSize_X; ++i) {
                this.gameMap.add(new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "background/main/" + j + "_" + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
            }
            return true;
        }
        return false;
    }
    
    public final void loadMapBG_End() {
        this.gameMapSize = this.gameMap.size();
        MapBG.iWidthOfMap = 0;
        for (int i = 0; i < this.iBackgroundSize_X; ++i) {
            MapBG.iWidthOfMap += this.gameMap.get(i).getWidth();
        }
        MapBG.iHeightOfMap = 0;
        for (int i = 0; i < this.iBackgroundSize_Y; ++i) {
            MapBG.iHeightOfMap += this.gameMap.get(i * this.iBackgroundSize_X).getHeight();
        }
        MapBG.iWidthOfMap *= (int)Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundScale;
        MapBG.iHeightOfMap *= (int)Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundScale;
        this.iWidthOfMap_Real = MapBG.iWidthOfMap;
        this.iHeightOfMap_Real = MapBG.iHeightOfMap;
        MapBG.iWidthOfMap *= this.iMapScale;
        MapBG.iHeightOfMap *= this.iMapScale;
        Game.updateMapDistance();
        Game.iMaxDistance = (float)(int)Math.ceil(Math.sqrt(Math.pow(this.getWidth_Real() / (Game.map.isWorldMap(Game.map.getActiveMapID()) ? 2 : 1), 2.0) + Math.pow(this.getHeight_Real(), 2.0)));
        Game.iMaxDistanceManhattan = (float)(this.getWidth_Real() + this.getHeight_Real());
        this.iWidthOfSingleBG = (int)(this.gameMap.get(this.gameMapSize - 1).getWidth() * Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundScale);
        this.iHeightOfSingleBG = (int)(this.gameMap.get(this.gameMapSize - 1).getHeight() * Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundScale);
        this.iWidthOfSingleBG_xMapScale = this.iWidthOfSingleBG * this.iMapScale;
        this.iHeightOfSingleBG_xMapScale = this.iHeightOfSingleBG * this.iMapScale;
        this.iMapScaleBG = this.iMapScale * Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundScale;
        MapScale.MINSCALE = CFG.GAME_HEIGHT / (float)this.getHeight();
        if (CFG.GAME_WIDTH / (float)this.getWidth() > MapScale.MINSCALE) {
            MapScale.MINSCALE = CFG.GAME_WIDTH / (float)this.getWidth();
        }
        if (MapScale.MINSCALE > 1.0f) {
            MapScale.MINSCALE = 1.0f;
        }
        this.iBackground2Size_X = Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Size_X;
        this.iBackground2Size_Y = Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Size_Y;
    }
    
    public final boolean loadMapBG_ZoomOut() {
        if (Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Enable) {
            final int j = MapBG.loadMapBG_FileID++;
            if (j < this.iBackground2Size_Y) {
                for (int i = 0; i < this.iBackground2Size_X; ++i) {
                    this.gameMap2.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "background/zoomOut/" + j + "_" + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                return true;
            }
        }
        return false;
    }
    
    public final void loadMapBG_ZoomOut_End() {
        if (Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Enable) {
            this.gameMap2Size = this.gameMap2.size();
            this.iWidthOfSingleBG2 = MapBG.iWidthOfMap / this.iBackground2Size_X;
            this.iHeightOfSingleBG2 = MapBG.iHeightOfMap / this.iBackground2Size_Y;
            this.iWidthOfSingleBGTech = this.gameMap2.get(0).getWidth();
            this.iHeightOfSingleBGTech = this.gameMap2.get(0).getHeight();
            this.iWidthOfMapTech = 0;
            for (int i = 0; i < this.iBackground2Size_X; ++i) {
                this.iWidthOfMapTech += this.gameMap2.get(i).getWidth();
            }
            this.iHeightOfMapTech = 0;
            for (int i = 0; i < this.iBackground2Size_Y; ++i) {
                this.iHeightOfMapTech += this.gameMap2.get(i * this.iBackground2Size_X).getHeight();
            }
        }
    }
    
    public final void loadMinimap() {
        this.minimapBG = new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "background/minimap/" + CFG.getRescouresPath_Short() + "minimapBG.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        this.minimapOver = new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "background/minimap/" + CFG.getRescouresPath_Short() + "minimapOver.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        this.scenarioBG = new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "background/minimap/" + CFG.getRescouresPath_Short() + "scenarioBG.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        this.scenarioOver = new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "background/minimap/" + CFG.getRescouresPath_Short() + "scenarioOver.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        this.scenarioMask = new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "background/minimap/" + CFG.getRescouresPath_Short() + "scenarioMask.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
    }
    
    public final void updateActiveMapBGShader() {
        try {
            if (Game.menuManager.getInGame() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DISEASES) {
                this.mapShader = new MapShader() {
                    @Override
                    public void drawMap(final SpriteBatch oSB) {
                        Renderer.setBlackWhite(oSB);
                        MapBG.this.drawMapAnimation = false;
                    }
                    
                    @Override
                    public void drawMapEnd(final SpriteBatch oSB) {
                        Renderer.setShaderDefault(oSB);
                        MapBG.this.drawMapAnimation = false;
                    }
                };
            }
            else {
                this.mapShader = new MapShader() {
                    @Override
                    public void drawMap(final SpriteBatch oSB) {
                        MapBG.this.drawMapAnimation = true;
                    }
                    
                    @Override
                    public void drawMapEnd(final SpriteBatch oSB) {
                        MapBG.this.drawMapAnimation = true;
                    }
                };
            }
        }
        catch (final Exception ex) {
            this.mapShader = new MapShader() {
                @Override
                public void drawMap(final SpriteBatch oSB) {
                    MapBG.this.drawMapAnimation = true;
                }
                
                @Override
                public void drawMapEnd(final SpriteBatch oSB) {
                    MapBG.this.drawMapAnimation = true;
                }
            };
        }
    }
    
    protected final void drawMap(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.worldMap.drawMap(oSB, nPosX, nPosY);
    }
    
    protected final void drawMapBorder(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.worldMap.drawMapBorder(oSB, nPosX, nPosY);
    }
    
    public final void loadMapBorder() {
        final Json json = new Json();
        final FileHandle file = FileManager.loadFile("gfx/map/MapBorder.json");
        this.mapBorder = (MapBorder)json.fromJson((Class)MapBorder.class, file);
        this.loadOutsideMapImages();
        this.loadOutsideMapImages_Below();
    }
    
    private final void loadOutsideMapImages() {
        try {
            final FileHandle fileList = FileManager.loadFile("gfx/map/OutsideMapImages_Over.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigJson.class, "Data", (Class)OutsideMapImage.class);
            final ConfigJson data = (ConfigJson)json.fromJson((Class)ConfigJson.class, fileContent);
            for (final Object e : data.Data) {
                final OutsideMapImage tempData = (OutsideMapImage)e;
                this.outsideMapImagesData.add(tempData);
                this.outsideMapImages.add(new Image(ImageManager.loadTexture("gfx/map/" + tempData.imageFileName)));
                final int tID = this.outsideMapImagesData.size() - 1;
                this.outsideMapImagesData.get(tID).fPosX_Percentage = this.getWidth() * this.outsideMapImagesData.get(tID).fPosX_Percentage - (int)(this.outsideMapImages.get(tID).getWidth() * this.outsideMapImagesData.get(tID).fScale) / 2;
                if (this.outsideMapImagesData.get(tID).fPosX_Percentage > this.getWidth()) {
                    this.outsideMapImagesData.get(tID).fPosX_Percentage = (float)(this.getWidth() - this.outsideMapImages.get(tID).getWidth() - CFG.PADDING * 4);
                }
                if (this.outsideMapImagesData.get(tID).fPosY_Percentage >= 1.0f) {
                    this.outsideMapImagesData.get(tID).fPosY_Percentage = (float)(this.mapBorder.OutsideTheMapMaxY_Over - (int)(this.outsideMapImages.get(tID).getHeight() * this.outsideMapImagesData.get(tID).fScale));
                }
                else {
                    this.outsideMapImagesData.get(tID).fPosY_Percentage = this.mapBorder.OutsideTheMapMaxY_Over * this.outsideMapImagesData.get(tID).fPosY_Percentage - (int)(this.outsideMapImages.get(tID).getHeight() * this.outsideMapImagesData.get(tID).fScale) / 2;
                }
            }
            this.outsideMapImagesSize = this.outsideMapImages.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void loadOutsideMapImages_Below() {
        try {
            final FileHandle fileList = FileManager.loadFile("gfx/map/OutsideMapImages_Below.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigJson.class, "Data", (Class)OutsideMapImage.class);
            final ConfigJson data = (ConfigJson)json.fromJson((Class)ConfigJson.class, fileContent);
            for (final Object e : data.Data) {
                final OutsideMapImage tempData = (OutsideMapImage)e;
                this.outsideMapImagesData_Below.add(tempData);
                this.outsideMapImages_Below.add(new Image(ImageManager.loadTexture("gfx/map/" + tempData.imageFileName)));
                final int tID = this.outsideMapImagesData_Below.size() - 1;
                this.outsideMapImagesData_Below.get(tID).fPosX_Percentage = this.getWidth() * this.outsideMapImagesData_Below.get(tID).fPosX_Percentage - (int)(this.outsideMapImages_Below.get(tID).getWidth() * this.outsideMapImagesData_Below.get(tID).fScale) / 2;
                if (this.outsideMapImagesData_Below.get(tID).fPosX_Percentage > this.getWidth()) {
                    this.outsideMapImagesData_Below.get(tID).fPosX_Percentage = (float)(this.getWidth() - this.outsideMapImages_Below.get(tID).getWidth() - CFG.PADDING * 4);
                }
                if (this.outsideMapImagesData_Below.get(tID).fPosY_Percentage <= 0.01f) {
                    this.outsideMapImagesData_Below.get(tID).fPosY_Percentage = 0.0f;
                }
                if (this.outsideMapImagesData_Below.get(tID).fPosY_Percentage >= 1.0f) {
                    this.outsideMapImagesData_Below.get(tID).fPosY_Percentage = (float)(this.mapBorder.OutsideTheMapMaxY_Below - (int)(this.outsideMapImages_Below.get(tID).getHeight() * this.outsideMapImagesData_Below.get(tID).fScale));
                }
                else {
                    this.outsideMapImagesData_Below.get(tID).fPosY_Percentage = this.mapBorder.OutsideTheMapMaxY_Below * this.outsideMapImagesData_Below.get(tID).fPosY_Percentage - (int)(this.outsideMapImages_Below.get(tID).getHeight() * this.outsideMapImagesData_Below.get(tID).fScale) / 2;
                }
                if (this.outsideMapImagesData_Below.get(tID).fPosY_Percentage + this.outsideMapImages_Below.get(tID).getHeight() * this.outsideMapImagesData_Below.get(tID).fScale > this.mapBorder.OutsideTheMapMaxY_Below) {
                    this.outsideMapImagesData_Below.get(tID).fPosY_Percentage = (float)(this.mapBorder.OutsideTheMapMaxY_Below - CFG.PADDING * 2);
                }
                if (this.outsideMapImagesData_Below.get(tID).fPosY_Percentage < 0.0f) {
                    this.outsideMapImagesData_Below.get(tID).fPosY_Percentage = 0.0f;
                }
            }
            this.outsideMapImagesSize_Below = this.outsideMapImages_Below.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateWorldMap() {
        if (this.gameMapSize == 0 || Game.menuManager.getInInitGameMenu() || Game.menuManager.getInInitGame_Menus() || Game.menuManager.getInMainMenu() || Game.menuManager.getInLoadGamesList() || Game.menuManager.getInLoadScenario()) {
            this.worldMap = new WorldMap() {
                @Override
                public void drawMap(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                }
                
                @Override
                public void drawMapBorder(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                }
            };
        }
        else if (Game.map.isWorldMap(Game.map.getActiveMapID())) {
            if (Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Enable) {
                this.worldMap = new WorldMap() {
                    @Override
                    public void drawMap(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                        try {
                            if (MapBG.this.drawMapAnimation) {
                                if (Game.mapScale.getCurrentScale() > Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Scale) {
                                    if (MapBG.this.iBGID == 1) {
                                        MapBG.this.iBGID = 0;
                                        MapBG.this.updateBGAnimationTime();
                                    }
                                    if (MapBG.this.inAnimation) {
                                        if (CFG.currentTimeMillis < MapBG.this.animationTime + Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_AnimationDuration) {
                                            MapBG.this.drawMapBG_Sea(oSB, nPosX, nPosY, 1.0f * (CFG.currentTimeMillis - MapBG.this.animationTime) / Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_AnimationDuration);
                                            MapBG.this.drawMapBG(oSB, nPosX, nPosY, 1.0f);
                                            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - 1.0f * (CFG.currentTimeMillis - MapBG.this.animationTime) / Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_AnimationDuration));
                                            MapBG.this.drawMapBG2(oSB, nPosX, nPosY);
                                            oSB.setColor(Color.WHITE);
                                        }
                                        else {
                                            MapBG.this.drawMapBG_Sea(oSB, nPosX, nPosY, 1.0f);
                                            MapBG.this.inAnimation = false;
                                            MapBG.this.drawMapBG(oSB, nPosX, nPosY, 1.0f);
                                        }
                                    }
                                    else {
                                        MapBG.this.drawMapBG_Sea(oSB, nPosX, nPosY, 1.0f);
                                        MapBG.this.drawMapBG(oSB, nPosX, nPosY, 1.0f);
                                    }
                                }
                                else {
                                    if (MapBG.this.iBGID == 0) {
                                        MapBG.this.iBGID = 1;
                                        MapBG.this.updateBGAnimationTime();
                                    }
                                    if (MapBG.this.inAnimation) {
                                        if (CFG.currentTimeMillis < MapBG.this.animationTime + Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_AnimationDuration) {
                                            MapBG.this.drawMapBG2(oSB, nPosX, nPosY);
                                            MapBG.this.drawMapBG(oSB, nPosX, nPosY, 1.0f - 1.0f * (CFG.currentTimeMillis - MapBG.this.animationTime) / Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_AnimationDuration);
                                            oSB.setColor(Color.WHITE);
                                        }
                                        else {
                                            MapBG.this.inAnimation = false;
                                            MapBG.this.drawMapBG2(oSB, nPosX, nPosY);
                                        }
                                    }
                                    else {
                                        MapBG.this.drawMapBG2(oSB, nPosX, nPosY);
                                    }
                                }
                            }
                            else {
                                MapBG.this.drawMapBG_Sea(oSB, nPosX, nPosY, 1.0f);
                                MapBG.this.drawMapBG(oSB, nPosX, nPosY, 1.0f);
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    
                    @Override
                    public void drawMapBorder(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                        MapBG.this.drawOutsideTheMap_Over(oSB, nPosX, nPosY);
                        MapBG.this.drawOutsideTheMap_Below(oSB, nPosX, nPosY);
                    }
                };
            }
            else {
                this.worldMap = new WorldMap() {
                    @Override
                    public void drawMap(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                        try {
                            MapBG.this.drawMapBG_Sea(oSB, nPosX, nPosY, 1.0f);
                            MapBG.this.drawMapBG(oSB, nPosX, nPosY, 1.0f);
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    
                    @Override
                    public void drawMapBorder(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                        MapBG.this.drawOutsideTheMap_Over(oSB, nPosX, nPosY);
                        MapBG.this.drawOutsideTheMap_Below(oSB, nPosX, nPosY);
                    }
                };
            }
        }
        else {
            this.worldMap = new WorldMap() {
                @Override
                public void drawMap(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                    try {
                        int j = MapBG.this.iBackgroundSize_Y - 1;
                        int currID = MapBG.this.gameMapSize - 1;
                        int tempHeight = MapBG.iHeightOfMap - MapBG.this.iHeightOfSingleBG * MapBG.this.iMapScale;
                        while (j >= 0) {
                            int tempWidth = MapBG.iWidthOfMap;
                            for (int i = MapBG.this.iBackgroundSize_X - 1; i >= 0; --i) {
                                MapBG.this.gameMap.get(currID).draw(oSB, nPosX + tempWidth - MapBG.this.gameMap.get(currID).getWidth() * MapBG.this.iMapScale, nPosY + tempHeight, MapBG.this.iMapScaleBG);
                                tempWidth -= MapBG.this.gameMap.get(currID).getWidth() * MapBG.this.iMapScale;
                                --currID;
                            }
                            tempHeight -= MapBG.this.iHeightOfSingleBG * MapBG.this.iMapScale;
                            --j;
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                
                @Override
                public void drawMapBorder(final SpriteBatch oSB, final int nPosX, final int nPosY) {
                    if (-nPosY + (int)Math.ceil(CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale()) > MapBG.this.getHeight()) {
                        ImageManager.getImage(Images.map_border).draw2(oSB, nPosX + MapBG.this.mapBorder.ShadowY - ImageManager.getImage(Images.map_border).getHeight(), -ImageManager.getImage(Images.map_border).getHeight(), MapBG.this.getHeight() + nPosY, ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, true);
                        ImageManager.getImage(Images.map_border).draw2(oSB, nPosX - MapBG.this.mapBorder.ShadowY + MapBG.this.getWidth(), -ImageManager.getImage(Images.map_border).getHeight(), MapBG.this.getHeight() + nPosY, ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, false);
                    }
                    else {
                        ImageManager.getImage(Images.map_border).draw2(oSB, nPosX + MapBG.this.mapBorder.ShadowY - ImageManager.getImage(Images.map_border).getHeight(), -ImageManager.getImage(Images.map_border).getHeight(), (int)Math.ceil(CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, true);
                        ImageManager.getImage(Images.map_border).draw2(oSB, nPosX - MapBG.this.mapBorder.ShadowY + MapBG.this.getWidth(), -ImageManager.getImage(Images.map_border).getHeight(), (int)Math.ceil(CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, false);
                    }
                    MapBG.this.drawOutsideTheMap_Over(oSB, nPosX, nPosY);
                    MapBG.this.drawOutsideTheMap_Below(oSB, nPosX, nPosY);
                }
            };
        }
    }
    
    private final void updateBGAnimationTime() {
        if (this.inAnimation) {
            this.animationTime = CFG.currentTimeMillis - (Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_AnimationDuration - (CFG.currentTimeMillis - this.animationTime));
        }
        else {
            this.animationTime = CFG.currentTimeMillis;
        }
        this.inAnimation = true;
    }
    
    public void updateMapBGSea() {
        if (CFG.isDesktop() || !GameValues.value.MOBILE_DISABLE_SEA_WAVES) {
            MapBG.mapBGSea = new MapBGSea() {
                @Override
                public void drawMapSea(final SpriteBatch oSB, final int nPosX, final int nPosY, final float fAlpha) {
                    oSB.setColor(new Color(Renderer.BACKGROUND_COLOR.r, Renderer.BACKGROUND_COLOR.g, Renderer.BACKGROUND_COLOR.b, Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.SeaOverAlpha * fAlpha * Math.min(1.0f, Game.mapScale.getCurrentScale())));
                    Game.mapOverSea.drawMapOverlaySea(oSB, nPosX, nPosY, fAlpha);
                    oSB.setColor(Color.WHITE);
                    Renderer.setShaderWater2(oSB);
                    ImageManager.getImage(Images.waves).draw2(oSB, 0, 0, (int)(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale()), (int)(CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale()), -nPosX, -nPosY);
                    Renderer.setShaderDefault(oSB);
                }
            };
        }
        else {
            MapBG.mapBGSea = new MapBGSea() {
                @Override
                public void drawMapSea(final SpriteBatch oSB, final int nPosX, final int nPosY, final float fAlpha) {
                }
            };
        }
    }
    
    private final void drawMapBG_Sea(final SpriteBatch oSB, final int nPosX, final int nPosY, final float fAlpha) {
        MapBG.mapBGSea.drawMapSea(oSB, nPosX, nPosY, fAlpha);
    }
    
    private final void drawMapBG(final SpriteBatch oSB, final int nPosX, final int nPosY, final float fAlpha) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        this.mapShader.drawMap(oSB);
        if (this.secondSideOfMap) {
            int j = this.iBackgroundSize_Y - 1;
            int currID = this.gameMapSize - 1;
            int tempHeight = MapBG.iHeightOfMap - this.iHeightOfSingleBG_xMapScale;
            while (j >= 0) {
                if (Game.inViewY(tempHeight, tempHeight + this.iHeightOfSingleBG_xMapScale)) {
                    int tempWidth = MapBG.iWidthOfMap * 2;
                    for (int i = this.iBackgroundSize_X - 1; i >= 0; --i) {
                        if (Game.inViewX(tempWidth - this.iWidthOfSingleBG_xMapScale, tempWidth) || Game.inViewX2(tempWidth - this.iWidthOfSingleBG_xMapScale, tempWidth)) {
                            this.gameMap.get(currID).draw(oSB, nPosX + tempWidth - this.iWidthOfSingleBG_xMapScale, nPosY + tempHeight, this.iMapScaleBG);
                        }
                        tempWidth -= this.iWidthOfSingleBG_xMapScale;
                        --currID;
                    }
                }
                else {
                    currID -= this.iBackgroundSize_X;
                }
                tempHeight -= this.iHeightOfSingleBG_xMapScale;
                --j;
            }
        }
        int j = this.iBackgroundSize_Y - 1;
        int currID = this.gameMapSize - 1;
        int tempHeight = MapBG.iHeightOfMap - this.iHeightOfSingleBG_xMapScale;
        while (j >= 0) {
            if (Game.inViewY(tempHeight, tempHeight + this.iHeightOfSingleBG_xMapScale)) {
                int tempWidth = MapBG.iWidthOfMap;
                for (int i = this.iBackgroundSize_X - 1; i >= 0; --i) {
                    if (Game.inViewX(tempWidth - this.iWidthOfSingleBG_xMapScale, tempWidth) || Game.inViewX2(tempWidth - this.iWidthOfSingleBG_xMapScale, tempWidth)) {
                        this.gameMap.get(currID).draw(oSB, nPosX + tempWidth - this.iWidthOfSingleBG_xMapScale, nPosY + tempHeight, this.iMapScaleBG);
                    }
                    tempWidth -= this.iWidthOfSingleBG_xMapScale;
                    --currID;
                }
            }
            else {
                currID -= this.iBackgroundSize_X;
            }
            tempHeight -= this.iHeightOfSingleBG_xMapScale;
            --j;
        }
        this.mapShader.drawMapEnd(oSB);
        Game.mapOver.drawMapOverlay(oSB, nPosX, nPosY, fAlpha);
    }
    
    private final void drawMapBG2(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.mapShader.drawMap(oSB);
        if (this.secondSideOfMap) {
            int j = this.iBackground2Size_Y - 1;
            int currID = this.gameMap2Size - 1;
            int tempHeight = MapBG.iHeightOfMap - this.iHeightOfSingleBG2;
            while (j >= 0) {
                int tempWidth = MapBG.iWidthOfMap * 2;
                for (int i = this.iBackground2Size_X - 1; i >= 0; --i) {
                    this.gameMap2.get(currID).draw(oSB, nPosX + tempWidth - this.iWidthOfSingleBG2, nPosY + tempHeight, this.iWidthOfSingleBG2, this.iHeightOfSingleBG2);
                    tempWidth -= this.iWidthOfSingleBG2;
                    --currID;
                }
                tempHeight -= this.iHeightOfSingleBG2;
                --j;
            }
        }
        int j = this.iBackground2Size_Y - 1;
        int currID = this.gameMap2Size - 1;
        int tempHeight = MapBG.iHeightOfMap - this.iHeightOfSingleBG2;
        while (j >= 0) {
            int tempWidth = MapBG.iWidthOfMap;
            for (int i = this.iBackground2Size_X - 1; i >= 0; --i) {
                this.gameMap2.get(currID).draw(oSB, nPosX + tempWidth - this.iWidthOfSingleBG2, nPosY + tempHeight, this.iWidthOfSingleBG2, this.iHeightOfSingleBG2);
                tempWidth -= this.iWidthOfSingleBG2;
                --currID;
            }
            tempHeight -= this.iHeightOfSingleBG2;
            --j;
        }
        this.mapShader.drawMapEnd(oSB);
    }
    
    public final void drawMapBG2_TechTree(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        int j = this.iBackground2Size_Y - 1;
        int currID = this.gameMap2Size - 1;
        int tempHeight = this.iHeightOfMapTech - this.iHeightOfSingleBGTech;
        while (j >= 0) {
            int tempWidth = this.iWidthOfMapTech;
            for (int i = this.iBackground2Size_X - 1; i >= 0; --i) {
                this.gameMap2.get(currID).draw(oSB, nPosX + tempWidth - this.iWidthOfSingleBGTech, nPosY + tempHeight, this.iWidthOfSingleBGTech, this.iHeightOfSingleBGTech);
                tempWidth -= this.iWidthOfSingleBGTech;
                --currID;
            }
            tempHeight -= this.iHeightOfSingleBGTech;
            --j;
        }
    }
    
    private final int drawOutsideTheMap_Shadow() {
        return this.mapBorder.ShadowY * 3;
    }
    
    public void updateOutsideTheMap_Scale() {
        if ((this.isOutsideInView || this.isOutsideInView_Bot) && Math.abs(this.outsideScale2 - Game.mapScale.getCurrentScale()) < 0.245f) {
            return;
        }
        this.outsideScale2 = Game.mapScale.getCurrentScale();
        if (Game.mapScale.getCurrentScale() >= 0.95f) {
            this.outsideScale = 1.0f;
            return;
        }
        this.outsideScale = 1.0f / Game.mapScale.getCurrentScale();
    }
    
    private final void drawOutsideTheMap_Over(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        if (Game.mapCoords.getPosY() < -this.mapBorder.ShadowY) {
            this.isOutsideInView = false;
            return;
        }
        this.updateOutsideTheMap_Scale();
        this.isOutsideInView = true;
        ImageManager.getImage(Images.outsideMap).draw2_Scale(oSB, nPosX, nPosY - this.mapBorder.OutsideTheMapMaxY_Over - ImageManager.getImage(Images.map_border).getHeight() + this.mapBorder.ShadowY, this.getWidth() * 2, this.mapBorder.OutsideTheMapMaxY_Over, this.outsideScale);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY - this.mapBorder.OutsideTheMapMaxY_Over / 2, this.getWidth() * 2, this.mapBorder.OutsideTheMapMaxY_Over / 2, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY - this.mapBorder.OutsideTheMapMaxY_Over - ImageManager.getImage(Images.map_border).getHeight(), this.getWidth() * 2, this.mapBorder.OutsideTheMapMaxY_Over / 8, false, false);
        oSB.setColor(Color.WHITE);
        this.drawOutsideTheMap_OverImages(oSB, nPosX, nPosY);
        if (Game.mapCoords.getSecondSideOfMap()) {
            this.drawOutsideTheMap_OverImages(oSB, nPosX + this.getWidth(), nPosY);
        }
        ImageManager.getImage(Images.map_border).draw2(oSB, 0, nPosY + this.mapBorder.ShadowY - ImageManager.getImage(Images.map_border).getHeight(), (int)Math.ceil(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosX, 0);
    }
    
    private final void drawOutsideTheMap_OverImages(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        for (int i = 0; i < this.outsideMapImagesSize; ++i) {
            this.outsideMapImages.get(i).draw(oSB, nPosX + (int)this.outsideMapImagesData.get(i).fPosX_Percentage, nPosY - this.mapBorder.OutsideTheMapMaxY_Over - ImageManager.getImage(Images.map_border).getHeight() + this.mapBorder.ShadowY + (int)this.outsideMapImagesData.get(i).fPosY_Percentage, this.outsideMapImagesData.get(i).fScale);
        }
    }
    
    private final void drawOutsideTheMap_Below(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        if (-Game.mapCoords.getPosY() + CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale() < this.getHeight() - this.mapBorder.ShadowY) {
            this.isOutsideInView_Bot = false;
            return;
        }
        this.updateOutsideTheMap_Scale();
        this.isOutsideInView_Bot = true;
        ImageManager.getImage(Images.outsideMap).draw2_Scale(oSB, nPosX, nPosY + this.getHeight() + ImageManager.getImage(Images.map_border).getHeight() - this.mapBorder.ShadowY, this.getWidth() * 2, this.mapBorder.OutsideTheMapMaxY_Below, false, true, this.outsideScale);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + this.getHeight() + ImageManager.getImage(Images.map_border).getHeight() - this.mapBorder.ShadowY, this.getWidth() * 2, this.mapBorder.OutsideTheMapMaxY_Below / 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + this.getHeight() + ImageManager.getImage(Images.map_border).getHeight() - this.mapBorder.ShadowY + this.mapBorder.OutsideTheMapMaxY_Below - this.mapBorder.OutsideTheMapMaxY_Below / 8, this.getWidth() * 2, this.mapBorder.OutsideTheMapMaxY_Below / 8, false, true);
        oSB.setColor(Color.WHITE);
        this.drawOutsideTheMap_BelowImages(oSB, nPosX, nPosY);
        if (Game.mapCoords.getSecondSideOfMap()) {
            this.drawOutsideTheMap_BelowImages(oSB, nPosX + this.getWidth(), nPosY);
        }
        ImageManager.getImage(Images.map_border).draw2(oSB, 0, nPosY - this.mapBorder.ShadowY + this.getHeight(), (int)Math.ceil(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosX, 0, 0.0f, false, true);
    }
    
    private final void drawOutsideTheMap_BelowImages(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        for (int i = 0; i < this.outsideMapImagesSize_Below; ++i) {
            this.outsideMapImages_Below.get(i).draw(oSB, nPosX + (int)this.outsideMapImagesData_Below.get(i).fPosX_Percentage, nPosY + this.getHeight() + ImageManager.getImage(Images.map_border).getHeight() - this.mapBorder.ShadowY + (int)this.outsideMapImagesData_Below.get(i).fPosY_Percentage, this.outsideMapImagesData_Below.get(i).fScale);
        }
    }
    
    public int getWidth() {
        return MapBG.iWidthOfMap;
    }
    
    public int getHeight() {
        return MapBG.iHeightOfMap;
    }
    
    public int getWidth_Real() {
        return this.iWidthOfMap_Real;
    }
    
    public int getHeight_Real() {
        return this.iHeightOfMap_Real;
    }
    
    public final void dispose() {
        for (int i = 0; i < this.gameMap.size(); ++i) {
            this.gameMap.get(i).getTexture().dispose();
        }
        this.gameMap.clear();
        this.gameMapSize = 0;
        for (int i = 0; i < this.gameMap2.size(); ++i) {
            this.gameMap2.get(i).getTexture().dispose();
        }
        this.gameMap2.clear();
        this.gameMap2Size = 0;
        MapBG.iWidthOfMap = (MapBG.iHeightOfMap = 0);
        if (this.minimapBG != null) {
            this.minimapBG.dispose();
            this.minimapBG = null;
        }
        if (this.minimapOver != null) {
            this.minimapOver.dispose();
            this.minimapOver = null;
        }
        if (this.scenarioMask != null) {
            this.scenarioMask.dispose();
            this.scenarioMask = null;
        }
        if (this.scenarioBG != null) {
            this.scenarioBG.dispose();
            this.scenarioBG = null;
        }
        if (this.scenarioOver != null) {
            this.scenarioOver.dispose();
            this.scenarioOver = null;
        }
    }
    
    protected final void updateMinimapScaleXY() {
        this.updateMinimapScaleX();
        this.updateMinimapScaleY();
    }
    
    protected final void updateMinimapScaleX() {
        this.fMinimapScaleX = this.getWidth() / (float)this.iMinimapWidth;
    }
    
    protected final void updateMinimapScaleY() {
        this.fMinimapScaleY = this.getHeight() / (float)this.iMinimapHeight;
    }
    
    public final void updateMinimapResolution() {
        this.iMinimapHeight = this.minimapBG.getHeight();
        this.updateMinimapScaleY();
        this.iMinimapWidth = (int)(this.getWidth() / this.fMinimapScaleY);
        this.updateMinimapScaleX();
    }
    
    public final float getMinimapScaled_ScaleX() {
        return this.iMinimapScaled_Width / (float)this.iMinimapWidth;
    }
    
    public final float getMinimapScaled_ScaleY() {
        return this.iMinimapScaled_Height / (float)this.iMinimapHeight;
    }
    
    protected final void updatePreviewScaleX() {
        this.fPreviewScaleX = this.getWidth() / (float)this.iPreviewWidth;
    }
    
    protected final void updatePreviewScaleY() {
        this.fPreviewScaleY = this.getHeight() / (float)this.iPreviewHeight;
    }
    
    public final void updatePreviewResolution() {
        this.iPreviewHeight = this.scenarioBG.getHeight();
        this.updatePreviewScaleY();
        this.iPreviewWidth = (int)(this.getWidth() / this.fPreviewScaleY);
        this.updatePreviewScaleX();
    }
    
    public final float getPreviewScaled_ScaleX() {
        return this.iPreviewScaled_Width / (float)this.iPreviewWidth;
    }
    
    public final float getPreviewScaled_ScaleY() {
        return this.iPreviewScaled_Height / (float)this.iPreviewHeight;
    }
    
    protected final void drawMap(final SpriteBatch oSB, final int nPosX, final int nPosY, float scale) {
        scale *= this.iMapScale;
        int j = this.iBackgroundSize_Y - 1;
        int currID = this.gameMapSize - 1;
        int tempHeight = (int)(MapBG.iHeightOfMap - this.iHeightOfSingleBG * scale);
        while (j >= 0) {
            int tempWidth = MapBG.iWidthOfMap;
            for (int i = this.iBackgroundSize_X - 1; i >= 0; --i) {
                this.gameMap.get(currID).draw(oSB, nPosX + tempWidth - (int)(this.gameMap.get(currID).getWidth() * scale), nPosY + tempHeight, scale);
                tempWidth -= (int)(this.gameMap.get(currID).getWidth() * scale);
                --currID;
            }
            tempHeight -= (int)(this.iHeightOfSingleBG * scale);
            --j;
        }
    }
    
    public final void drawMinimapTexture_Generate(final SpriteBatch oSB) {
        if (this.minimapOfCivilizations == null && !Game.menuManager.getInInitGameMenu() && !Game.menuManager.getInInitGame_Menus() && !Game.menuManager.getInLoadScenario() && this.gameMap.size() > 0) {
            try {
                final int extraScale = 1;
                try {
                    Renderer.clipView_End(oSB);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex) {
                        oSB.begin();
                        oSB.end();
                    }
                    this.minimapBelowZero = false;
                    int tMinX = this.getWidth();
                    int tMaxX = -this.getWidth();
                    int tMinY = this.getHeight();
                    int tMaxY = 0;
                    final int numOfProvinces = 0;
                    tMinX = 0;
                    tMinY = 0;
                    tMaxX = this.getWidth();
                    tMaxY = this.getHeight();
                    int tempExtra = (int)((tMaxX - tMinX) * 0.125f);
                    tMinX -= tempExtra;
                    tMaxX += tempExtra;
                    tempExtra = (int)((tMaxY - tMinY) * 0.125f);
                    tMinY -= tempExtra;
                    tMaxY += tempExtra;
                    if (tMinY < 0) {
                        tMinY = 0;
                    }
                    int tPosX = 0;
                    int tPosY = 0;
                    float tScale = 1.0f;
                    tPosX = tMinX;
                    tPosY = tMinY;
                    int tWidth = tMaxX - tMinX;
                    int tHeight = tMaxY - tMinY;
                    if ((tMaxX - tMinX) / (float)this.getWidth() >= (tMaxY - tMinY) / (float)this.getHeight()) {
                        tHeight = (int)((tMaxX - tMinX) / (float)this.getWidth() * this.getHeight());
                        tPosY = tMinY + (tMaxY - tMinY) / 2 - tHeight / 2;
                        tScale = this.getHeight() / ((tMaxX - tMinX) / (float)this.getWidth() * this.getHeight());
                    }
                    else {
                        tWidth = (int)((tMaxY - tMinY) / (float)this.getHeight() * this.getWidth());
                        tPosX = tMinX + (tMaxX - tMinX) / 2 - tWidth / 2;
                        tScale = this.getWidth() / ((tMaxY - tMinY) / (float)this.getHeight() * this.getWidth());
                    }
                    tPosY = Math.max(0, tPosY);
                    if (tWidth / (float)this.getWidth() >= 0.95f || tHeight / (float)this.getHeight() >= 0.95f || tMinY < 0 || tMaxY >= this.getHeight()) {
                        tPosX = 0;
                        tPosY = 0;
                        tScale = 1.0f;
                        tWidth = this.getWidth();
                        tHeight = this.getHeight();
                    }
                    this.iMinimapScaled_PosX = tPosX;
                    this.iMinimapScaled_PosY = tPosY;
                    this.iMinimapScaled_Width = tWidth;
                    this.iMinimapScaled_Height = tHeight;
                    this.fMinimapScaled_Scale = tScale;
                    Renderer.viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex2) {
                        oSB.end();
                        oSB.begin();
                    }
                    this.minimapBG.draw(oSB, 0, 0, this.iMinimapWidth * extraScale, this.iMinimapHeight * extraScale);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex2) {
                        oSB.begin();
                        oSB.end();
                    }
                    Renderer.viewport.setWorldSize(CFG.GAME_WIDTH * (this.getWidth() / (float)this.iMinimapWidth) / extraScale, CFG.GAME_HEIGHT * (this.getHeight() / (float)this.iMinimapHeight) / extraScale);
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, CFG.GAME_WIDTH * (this.getWidth() / (float)this.iMinimapWidth) / extraScale, -(CFG.GAME_HEIGHT * (this.getHeight() / (float)this.iMinimapHeight)) / extraScale);
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex2) {
                        oSB.end();
                        oSB.begin();
                    }
                    Renderer.clipView_Start(oSB, 0, CFG.GAME_HEIGHT, this.iMinimapWidth * extraScale, -this.iMinimapHeight * extraScale);
                    oSB.setColor(Color.WHITE);
                    oSB.setShader(Renderer.shaderDefaultProvince);
                    ProvinceDraw.drawProvinces_Minimap(oSB, -(int)(tPosX * tScale), -(int)(tPosY * tScale), tScale, 220);
                    if (tPosX + this.getWidth() * tScale > this.getWidth()) {
                        ProvinceDraw.drawProvinces_Minimap(oSB, -(int)(tPosX * tScale) + (int)(this.getWidth() * tScale), -(int)(tPosY * tScale), tScale, 220);
                    }
                    if (tPosX < 0) {
                        ProvinceDraw.drawProvinces_Minimap(oSB, -(int)(tPosX * tScale) - (int)(this.getWidth() * tScale), -(int)(tPosY * tScale), tScale, 220);
                        this.minimapBelowZero = true;
                    }
                    oSB.setShader(Renderer.shaderDefault);
                    Renderer.clipView_End(oSB);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex2) {
                        oSB.begin();
                        oSB.end();
                    }
                }
                finally {
                    Renderer.viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex3) {
                        oSB.end();
                        oSB.begin();
                    }
                    this.minimapOfCivilizations = new Image(new Texture(Pixmap.createFromFrameBuffer(0, CFG.GAME_HEIGHT - this.iMinimapHeight * extraScale, this.iMinimapWidth * extraScale, this.iMinimapHeight * extraScale)));
                    oSB.setColor(Color.BLACK);
                    Images.pix.draw(oSB, 0, 0, this.iMinimapWidth, this.iMinimapHeight);
                    oSB.setColor(Color.WHITE);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex3) {
                        oSB.begin();
                        oSB.end();
                    }
                    Renderer.viewport.setWorldSize(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), -CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex3) {
                        oSB.end();
                        oSB.begin();
                    }
                    oSB.setShader(Renderer.shaderDefault);
                }
            }
            catch (final Exception ex4) {
                CFG.exceptionStack(ex4);
                this.requestToDisposeMinimap = true;
            }
        }
    }
    
    public final void disposeMinimapOfCivilizations() {
        try {
            if (this.minimapOfCivilizations != null) {
                this.requestToDisposeMinimap = true;
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void disposeMinimapOfCivilizations_Real() {
        try {
            if (this.minimapOfCivilizations != null) {
                this.minimapOfCivilizations.getTexture().dispose();
                this.minimapOfCivilizations = null;
                this.requestToDisposeMinimap = false;
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void previewTexture_Generate(final SpriteBatch oSB) {
        if (this.previewOfCivilizations != null) {
            this.previewOfCivilizations.dispose();
            this.previewOfCivilizations = null;
        }
        if (this.previewOfCivilizations == null && this.gameMap.size() > 0) {
            try {
                final int extraScale = 1;
                try {
                    Renderer.clipView_End(oSB);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex) {
                        oSB.begin();
                        oSB.end();
                    }
                    this.minimapBelowZero = false;
                    final int numOfProvinces = 0;
                    int tMinX = 0;
                    int tMinY = 0;
                    int tMaxX = this.getWidth();
                    int tMaxY = this.getHeight();
                    int tempExtra = (int)((tMaxX - tMinX) * 0.125f);
                    tMinX -= tempExtra;
                    tMaxX += tempExtra;
                    tempExtra = (int)((tMaxY - tMinY) * 0.125f);
                    tMinY -= tempExtra;
                    tMaxY += tempExtra;
                    if (tMinY < 0) {
                        tMinY = 0;
                    }
                    int tPosX = 0;
                    int tPosY = 0;
                    float tScale = 1.0f;
                    tPosX = tMinX;
                    tPosY = tMinY;
                    int tWidth = tMaxX - tMinX;
                    int tHeight = tMaxY - tMinY;
                    if ((tMaxX - tMinX) / (float)this.getWidth() >= (tMaxY - tMinY) / (float)this.getHeight()) {
                        tHeight = (int)((tMaxX - tMinX) / (float)this.getWidth() * this.getHeight());
                        tPosY = tMinY + (tMaxY - tMinY) / 2 - tHeight / 2;
                        tScale = this.getHeight() / ((tMaxX - tMinX) / (float)this.getWidth() * this.getHeight());
                    }
                    else {
                        tWidth = (int)((tMaxY - tMinY) / (float)this.getHeight() * this.getWidth());
                        tPosX = tMinX + (tMaxX - tMinX) / 2 - tWidth / 2;
                        tScale = this.getWidth() / ((tMaxY - tMinY) / (float)this.getHeight() * this.getWidth());
                    }
                    tPosY = Math.max(0, tPosY);
                    if (tWidth / (float)this.getWidth() >= 0.95f || tHeight / (float)this.getHeight() >= 0.95f || tMinY < 0 || tMaxY >= this.getHeight()) {
                        tPosX = 0;
                        tPosY = 0;
                        tScale = 1.0f;
                        tWidth = this.getWidth();
                        tHeight = this.getHeight();
                    }
                    this.iPreviewScaled_PosX = tPosX;
                    this.iPreviewScaled_PosY = tPosY;
                    this.iPreviewScaled_Width = tWidth;
                    this.iPreviewScaled_Height = tHeight;
                    this.fPreviewScaled_Scale = tScale;
                    Renderer.viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex2) {
                        oSB.end();
                        oSB.begin();
                    }
                    this.scenarioBG.draw(oSB, 0, 0, this.iPreviewWidth * extraScale, this.iPreviewHeight * extraScale);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex2) {
                        oSB.begin();
                        oSB.end();
                    }
                    Renderer.viewport.setWorldSize(CFG.GAME_WIDTH * (this.getWidth() / (float)this.iPreviewWidth) / extraScale, CFG.GAME_HEIGHT * (this.getHeight() / (float)this.iPreviewHeight) / extraScale);
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, CFG.GAME_WIDTH * (this.getWidth() / (float)this.iPreviewWidth) / extraScale, -(CFG.GAME_HEIGHT * (this.getHeight() / (float)this.iPreviewHeight)) / extraScale);
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex2) {
                        oSB.end();
                        oSB.begin();
                    }
                    Renderer.clipView_Start(oSB, 0, CFG.GAME_HEIGHT, this.iPreviewWidth * extraScale, -this.iPreviewHeight * extraScale);
                    oSB.setColor(Color.WHITE);
                    oSB.setShader(Renderer.shaderDefaultProvince);
                    if (Game.FOG_OF_WAR) {
                        ProvinceDraw.drawProvinces_FogOfWarDiscovery(oSB, -(int)(tPosX * tScale), -(int)(tPosY * tScale), tScale, 220);
                    }
                    else {
                        ProvinceDraw.drawProvinces(oSB, -(int)(tPosX * tScale), -(int)(tPosY * tScale), tScale, 220);
                    }
                    if (tPosX + this.getWidth() * tScale > this.getWidth()) {
                        if (Game.FOG_OF_WAR) {
                            ProvinceDraw.drawProvinces_FogOfWarDiscovery(oSB, -(int)(tPosX * tScale) + (int)(this.getWidth() * tScale), -(int)(tPosY * tScale), tScale, 220);
                        }
                        else {
                            ProvinceDraw.drawProvinces(oSB, -(int)(tPosX * tScale) + (int)(this.getWidth() * tScale), -(int)(tPosY * tScale), tScale, 220);
                        }
                    }
                    if (tPosX < 0) {
                        if (Game.FOG_OF_WAR) {
                            ProvinceDraw.drawProvinces_FogOfWarDiscovery(oSB, -(int)(tPosX * tScale) - (int)(this.getWidth() * tScale), -(int)(tPosY * tScale), tScale, 220);
                        }
                        else {
                            ProvinceDraw.drawProvinces(oSB, -(int)(tPosX * tScale) - (int)(this.getWidth() * tScale), -(int)(tPosY * tScale), tScale, 220);
                        }
                        this.minimapBelowZero = true;
                    }
                    oSB.setShader(Renderer.shaderDefault);
                    Renderer.clipView_End(oSB);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex2) {
                        oSB.begin();
                        oSB.end();
                    }
                }
                finally {
                    Renderer.viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex3) {
                        oSB.end();
                        oSB.begin();
                    }
                    this.previewOfCivilizations = new Image(new Texture(Pixmap.createFromFrameBuffer(0, CFG.GAME_HEIGHT - this.iPreviewHeight * extraScale, this.iPreviewWidth * extraScale, this.iPreviewHeight * extraScale)));
                    try {
                        this.previewOfCivilizations.getTexture().getTextureData().prepare();
                    }
                    catch (final Exception ex5) {}
                    if (FileManager.IS_MAC) {
                        PixmapIO.writePNG(Gdx.files.external("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "preview.png"), EditorMap_PrintMap.flipPixmap(this.previewOfCivilizations.getTexture().getTextureData().consumePixmap()));
                    }
                    else {
                        PixmapIO.writePNG(Gdx.files.local("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "preview.png"), EditorMap_PrintMap.flipPixmap(this.previewOfCivilizations.getTexture().getTextureData().consumePixmap()));
                    }
                    oSB.setColor(Color.BLACK);
                    Images.pix.draw(oSB, 0, 0, this.iPreviewWidth, this.iPreviewHeight);
                    oSB.setColor(Color.WHITE);
                    try {
                        oSB.end();
                    }
                    catch (final Exception ex3) {
                        oSB.begin();
                        oSB.end();
                    }
                    Renderer.viewport.setWorldSize(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
                    Renderer.viewport.apply();
                    Renderer.camera.setToOrtho(true, CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), -CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
                    oSB.setProjectionMatrix(Renderer.camera.combined);
                    try {
                        oSB.begin();
                    }
                    catch (final Exception ex3) {
                        oSB.end();
                        oSB.begin();
                    }
                    oSB.setShader(Renderer.shaderDefault);
                }
            }
            catch (final Exception ex4) {
                CFG.exceptionStack(ex4);
            }
        }
    }
    
    static {
        MapBG.iWidthOfMap = 1;
        MapBG.iHeightOfMap = 1;
        MapBG.loadMapBG_FileID = 0;
        MapBG.mapBGSea = new MapBGSea() {
            @Override
            public void drawMapSea(final SpriteBatch oSB, final int nPosX, final int nPosY, final float fAlpha) {
            }
        };
    }
    
    public static class ConfigJson
    {
        String Age_of_History;
        ArrayList Data;
    }
    
    public static class OutsideMapImage
    {
        String imageFileName;
        float fPosX_Percentage;
        float fPosY_Percentage;
        float fScale;
    }
    
    public static class MapBorder
    {
        public int ShadowY;
        public int OutsideTheMapMaxY_Over;
        public int OutsideTheMapMaxY_Below;
    }
    
    private interface WorldMap
    {
        void drawMap(final SpriteBatch p0, final int p1, final int p2);
        
        void drawMapBorder(final SpriteBatch p0, final int p1, final int p2);
    }
    
    protected interface MapShader
    {
        void drawMap(final SpriteBatch p0);
        
        void drawMapEnd(final SpriteBatch p0);
    }
    
    public interface MapBGSea
    {
        void drawMapSea(final SpriteBatch p0, final int p1, final int p2, final float p3);
    }
}
