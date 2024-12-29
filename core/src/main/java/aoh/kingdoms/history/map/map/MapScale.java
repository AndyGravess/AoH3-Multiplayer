// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Touch;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;

public class MapScale
{
    public static float MINSCALE;
    public static final float MAXSCALE = 20.0f;
    public static float STANDARD_SCALE;
    private float currentScale;
    private float newScale;
    private boolean enableScaling;
    public boolean scaleMode;
    private static final short REQUIRED_TIME_TO_RESET_SCALE = 175;
    private float fScaleBeforeReset;
    private float changeCurrentScaleByX;
    private boolean scaleChangeByTouch;
    protected static int animation_TIME_TO_END;
    protected long animation_TIME_STARTED;
    protected float animation_StartingScale;
    private float fScaleAnimation_PercX;
    private float fScaleAnimation_PercY;
    private int iStartScalePosY;
    private int iStartScalePosY2;
    private int iStartScalePosX;
    private int iStartScalePosX2;
    public int definedScale;
    private int definedScaleBeforeReset;
    public int definedScalesLength;
    public static DefinedScales defScales;
    public float startScale;
    public boolean scaleByYAxis;
    private int iStartScaleMapPosX;
    private int iStartScaleMapPosY;
    
    public MapScale() {
        this.currentScale = 1.0f;
        this.newScale = -1.0f;
        this.enableScaling = false;
        this.fScaleBeforeReset = 1.5f;
        this.scaleChangeByTouch = true;
        this.animation_TIME_STARTED = 0L;
        this.animation_StartingScale = 1.0f;
        this.fScaleAnimation_PercX = 1.0f;
        this.fScaleAnimation_PercY = 1.0f;
        this.definedScale = 1;
        this.definedScaleBeforeReset = 1;
        this.definedScalesLength = 1;
        this.startScale = -1.0f;
        this.scaleByYAxis = true;
        this.iStartScaleMapPosX = -1;
        this.iStartScaleMapPosY = -1;
    }
    
    protected final void updateAnimationScale_CenterToXY(final int nPosX, final int nPosY) {
        this.fScaleAnimation_PercX = nPosX / (float)CFG.GAME_WIDTH;
        this.fScaleAnimation_PercY = nPosY / (float)CFG.GAME_HEIGHT;
    }
    
    public final void update() {
        if (this.changeCurrentScaleByX != 0.0f) {
            this.updateScale();
        }
    }
    
    private final void updateScale() {
        final float oldCurrentScale = this.currentScale;
        this.setCurrentScale(this.animation_StartingScale + this.changeCurrentScaleByX * Math.min(CFG.currentTimeMillis - this.animation_TIME_STARTED, MapScale.animation_TIME_TO_END) / MapScale.animation_TIME_TO_END);
        if (CFG.currentTimeMillis - this.animation_TIME_STARTED > MapScale.animation_TIME_TO_END) {
            if ((this.fScaleBeforeReset != MapScale.STANDARD_SCALE && this.scaleChangeByTouch) || (this.currentScale > 0.9925f && this.currentScale < 1.0075f)) {
                this.setCurrentScale(MapScale.STANDARD_SCALE);
            }
            this.resetScaleAnimation();
        }
        Game.mapCoords.setNewPosX((int)(Game.mapCoords.getPosX() - (CFG.GAME_WIDTH / oldCurrentScale - CFG.GAME_WIDTH / this.currentScale) * this.fScaleAnimation_PercX));
        Game.mapCoords.setNewPosY((int)(Game.mapCoords.getPosY() - (CFG.GAME_HEIGHT / oldCurrentScale - CFG.GAME_HEIGHT / this.currentScale) * this.fScaleAnimation_PercY));
    }
    
    private final void resetScaleAnimation() {
        this.changeCurrentScaleByX = 0.0f;
        this.animation_TIME_STARTED = 0L;
    }
    
    public final void initDefinedScales() {
        try {
            final Json json = new Json();
            final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "/" + "data/" + "scales/" + (Game.highTextureSettings ? "DefinedScales.json" : "DefinedScales_Low.json"));
            MapScale.defScales = (DefinedScales)json.fromJson((Class)DefinedScales.class, file);
            this.definedScale = MapScale.defScales.definedScale_Default;
            this.definedScaleBeforeReset = MapScale.defScales.definedScale_Default;
            this.definedScalesLength = MapScale.defScales.definedScale_Default;
            for (int i = 0, iSize = MapScale.defScales.definedScales.length; i < iSize; ++i) {
                if (MapScale.defScales.definedScales[i] < MapScale.MINSCALE) {
                    MapScale.defScales.definedScales[i] = MapScale.MINSCALE;
                }
            }
            if (MapScale.defScales.definedScales[MapScale.defScales.definedScales.length - 1] > MapScale.MINSCALE) {
                MapScale.defScales.definedScales[MapScale.defScales.definedScales.length - 1] = MapScale.MINSCALE;
            }
            this.definedScalesLength = MapScale.defScales.definedScales.length;
        }
        catch (final Exception ex) {
            CFG.LOG("Error loading: DefinedScales");
            CFG.exceptionStack(ex);
        }
    }
    
    private float getDefinedScale() {
        try {
            return MapScale.defScales.definedScales[this.definedScale];
        }
        catch (final IndexOutOfBoundsException ex) {
            return 1.0f;
        }
    }
    
    public final void scrollScale(final int changeScaleByX) {
        if (Game.menuManager.getInMainMenu() || Game.menuManager.getInScenarios_NewGame() || Game.menuManager.getInLoadGamesList() || Game.menuManager.getInGameLegacies()) {
            return;
        }
        if (Game.mapTouchManager.selectMode) {
            return;
        }
        this.definedScale += changeScaleByX;
        if (this.definedScale < 0) {
            this.definedScale = 0;
        }
        else if (this.definedScale >= MapScale.defScales.definedScales.length) {
            this.definedScale = MapScale.defScales.definedScales.length - 1;
        }
        float newMapScale = this.getDefinedScale();
        if (newMapScale >= 0.995f && newMapScale <= 1.005f) {
            newMapScale = MapScale.STANDARD_SCALE;
        }
        if (newMapScale != this.currentScale && newMapScale >= MapScale.MINSCALE - 0.05f) {
            this.resetScaleAnimation();
            this.scaleChangeByTouch = false;
            MapScale.animation_TIME_TO_END = 125;
            this.animation_StartingScale = this.currentScale;
            this.changeCurrentScaleByX = newMapScale - this.currentScale;
            this.fScaleBeforeReset = newMapScale;
            this.animation_TIME_STARTED = CFG.currentTimeMillis;
            this.updateAnimationScale_CenterToXY(Touch.getMousePosX(), Touch.getMousePosY());
        }
    }
    
    public float scrollScaleChange(final boolean zoomIN) {
        final float zoom = zoomIN ? -1.0f : 1.0f;
        if (!zoomIN) {
            return 0.1f * zoom;
        }
        if (this.getCurrentScale() >= 0.75f) {
            return 0.05f * zoom;
        }
        if (this.getCurrentScale() >= 0.5f) {
            return 0.02f * zoom;
        }
        return 0.01f * zoom;
    }
    
    protected final void resetScaleOfMap(final long nActionDownTime) {
        if ((Game.settingsManager.ENABLE_DOUBLE_CLICK_TO_RESET_MAP_SCALE || CFG.isAndroid()) && nActionDownTime > 0L && this.changeCurrentScaleByX == 0.0f && CFG.currentTimeMillis < Game.mapTouchManager.getActionDownTime() + 175L && !Game.mapCoords.getDisableMovingMap()) {
            this.resetScaleAnimation();
            this.scaleChangeByTouch = true;
            this.animation_StartingScale = this.currentScale;
            if (this.currentScale != MapScale.STANDARD_SCALE) {
                this.fScaleBeforeReset = this.currentScale;
                this.changeCurrentScaleByX = MapScale.STANDARD_SCALE - this.currentScale;
                this.definedScale = this.definedScaleBeforeReset;
            }
            else {
                this.changeCurrentScaleByX = this.fScaleBeforeReset - this.currentScale;
                this.fScaleBeforeReset = MapScale.STANDARD_SCALE;
                this.definedScaleBeforeReset = this.definedScale;
                this.definedScale = MapScale.defScales.definedScale_Default;
            }
            this.animation_TIME_STARTED = CFG.currentTimeMillis;
            this.updateAnimationScale_CenterToXY(Touch.getMousePosX(), Touch.getMousePosY());
            MapScale.animation_TIME_TO_END = 100;
            Game.mapTouchManager.updateStartMovePosX = true;
            Game.mapTouchManager.updateStartMovePosY = true;
            Game.mapScroll.resetScrollInfo();
        }
        Game.mapTouchManager.setActionDownTime(nActionDownTime);
    }
    
    public final void startScaleTheMap(final int nX, final int nX2, final int nY, final int nY2) {
        this.scaleMode = true;
        if (Math.max(nX, nX2) - Math.min(nX, nX2) > Math.max(nY, nY2) - Math.min(nY, nY2)) {
            this.scaleByYAxis = false;
            this.iStartScalePosY = nX;
            this.iStartScalePosY2 = nX2;
        }
        else {
            this.scaleByYAxis = true;
            this.iStartScalePosY = nY;
            this.iStartScalePosY2 = nY2;
        }
    }
    
    public final void scaleTheMap(final int nX, final int nX2, final int nY, final int nY2) {
        if (this.scaleByYAxis) {
            this.scaleTheMap(nY, nY2, Math.abs((nX + nX2) / 2.0f), Math.abs((nY + nY2) / 2.0f));
        }
        else {
            this.scaleTheMap(nX, nX2, Math.abs((nX + nX2) / 2.0f), Math.abs((nY + nY2) / 2.0f));
        }
    }
    
    public final void scaleTheMap(final int nY, final int nY2, final float fCenterX, final float fCenterY) {
        if (this.startScale < 0.0f) {
            this.iStartScaleMapPosX = Game.mapCoords.getPosX();
            this.iStartScaleMapPosY = Game.mapCoords.getPosY();
            this.startScale = this.currentScale;
        }
        if (this.iStartScalePosY != nY) {
            this.setNewCurrentScaleByTouch(this.currentScale + ((nY < nY2) ? (this.iStartScalePosY - nY) : (nY - this.iStartScalePosY)) / 150.0f / CFG.GUI_SCALE, fCenterX, fCenterY);
            this.iStartScalePosY = nY;
            Game.mapTouchManager.updateStartMovePosX = true;
            Game.mapTouchManager.updateStartMovePosY = true;
            this.resetScaleAnimation();
        }
        if (this.iStartScalePosY2 != nY2) {
            this.setNewCurrentScaleByTouch(this.currentScale + ((nY > nY2) ? (this.iStartScalePosY2 - nY2) : (nY2 - this.iStartScalePosY2)) / 150.0f / CFG.GUI_SCALE, fCenterX, fCenterY);
            this.iStartScalePosY2 = nY2;
            Game.mapTouchManager.updateStartMovePosX = true;
            Game.mapTouchManager.updateStartMovePosY = true;
            this.resetScaleAnimation();
        }
    }
    
    public final void resetStartScalePosition() {
        final int n = -1;
        this.iStartScalePosY2 = n;
        this.iStartScalePosY = n;
    }
    
    public final void resetScaleInfo() {
        this.resetStartScalePosition();
        this.scaleMode = false;
        this.startScale = -1.0f;
    }
    
    public final void setNewCurrentScaleByTouch(final float nCurrentScale, final float fCenterX, final float fCenterY) {
        if (nCurrentScale > 20.0f) {
            this.newScale = 20.0f;
        }
        else if (nCurrentScale < MapScale.MINSCALE) {
            this.newScale = MapScale.MINSCALE;
        }
        else {
            this.newScale = nCurrentScale;
        }
        this.scaleChangeByTouch = true;
        if (this.newScale > 0.0f) {
            if (this.currentScale != this.newScale) {
                this.fScaleAnimation_PercX = fCenterX / CFG.GAME_WIDTH;
                this.fScaleAnimation_PercY = fCenterY / CFG.GAME_HEIGHT;
                if (this.startScale < this.currentScale) {
                    Game.mapCoords.setNewPosX(this.iStartScaleMapPosX - (int)((CFG.GAME_WIDTH / this.startScale - CFG.GAME_WIDTH / this.newScale) * this.fScaleAnimation_PercX));
                    Game.mapCoords.setNewPosY(this.iStartScaleMapPosY - (int)((CFG.GAME_HEIGHT / this.startScale - CFG.GAME_HEIGHT / this.newScale) * this.fScaleAnimation_PercY));
                }
                else {
                    Game.mapCoords.setNewPosX(this.iStartScaleMapPosX - (int)((CFG.GAME_WIDTH / this.startScale - CFG.GAME_WIDTH / this.newScale) / 2.0f));
                    Game.mapCoords.setNewPosY(this.iStartScaleMapPosY - (int)((CFG.GAME_HEIGHT / this.startScale - CFG.GAME_HEIGHT / this.newScale) / 2.0f));
                }
                this.currentScale = this.newScale;
                this.newScale = 0.0f;
            }
            Game.mapCoords.checkPositionOfMapY();
            Game.mapCoords.checkPositionOfMapX();
            Game.mapCoords.updateSecondSideOfMap();
        }
    }
    
    public float getCurrentScale() {
        return this.currentScale;
    }
    
    public void setCurrentScale(float currentScale) {
        if (20.0f < currentScale) {
            currentScale = 20.0f;
        }
        else if (MapScale.MINSCALE > currentScale) {
            currentScale = MapScale.MINSCALE;
        }
        this.currentScale = currentScale;
        Renderer.updateBackgroundColor = true;
    }
    
    public boolean getEnableScaling() {
        return this.enableScaling;
    }
    
    public void setEnableScaling(final boolean enableScaling) {
        this.enableScaling = enableScaling;
    }
    
    public boolean getScaleMode() {
        return this.scaleMode;
    }
    
    public final int getStartScalePosY() {
        return this.iStartScalePosY;
    }
    
    static {
        MapScale.MINSCALE = 0.01f;
        MapScale.STANDARD_SCALE = 1.0f;
        MapScale.animation_TIME_TO_END = 100;
        MapScale.defScales = new DefinedScales();
    }
    
    public static class DefinedScales
    {
        public int definedScale_Default;
        public float[] definedScales;
    }
}
