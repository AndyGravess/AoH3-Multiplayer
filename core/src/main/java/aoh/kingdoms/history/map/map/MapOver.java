// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.Iterator;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class MapOver
{
    public List<Overlay> lOver;
    public int iOverSize;
    public List<Image> overTile;
    public List<Image> overMask;
    
    public MapOver() {
        this.iOverSize = 0;
        this.overTile = new ArrayList<Image>();
        this.overMask = new ArrayList<Image>();
    }
    
    public final void loadOverlay(final String sFile) {
        Config data = new Config();
        final Json json = new Json();
        json.setElementType((Class)Config.class, "Overlay", (Class)Overlay.class);
        data = (Config)json.fromJson((Class)Config.class, FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "background/overlays/" + sFile).reader("UTF8"));
        this.lOver = new ArrayList<Overlay>();
        for (final Object obj : data.Overlay) {
            this.lOver.add((Overlay)obj);
        }
        this.iOverSize = this.lOver.size();
    }
    
    public final boolean loadOverlayImages() {
        final int i = MapBG.loadMapBG_FileID++;
        if (i < this.iOverSize) {
            this.overTile.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "background/overlays/" + this.lOver.get(i).Tile), Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat));
            return true;
        }
        return false;
    }
    
    public final boolean loadOverlayImages_2() {
        final int i = MapBG.loadMapBG_FileID++;
        if (i < this.iOverSize) {
            this.overMask.add(new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "background/overlays/" + (Game.highTextureSettings ? "high/" : "low/") + this.lOver.get(i).Mask), Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat));
            this.lOver.get(i).u_maskScale = this.overMask.get(i).getWidth() / (this.overTile.get(i).getWidth() * this.lOver.get(i).Scale);
            this.lOver.get(i).u_maskScaleY = this.overMask.get(i).getHeight() / (this.overTile.get(i).getHeight() * this.lOver.get(i).Scale);
            return true;
        }
        return false;
    }
    
    public void drawMapOverlay(final SpriteBatch oSB, final int nPosX, final int nPosY, final float fAlpha) {
        try {
            oSB.setShader(Renderer.shaderAlpha_Map);
            for (int i = 0; i < this.iOverSize; ++i) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (this.lOver.get(i).Alpha + ((Game.mapScale.getCurrentScale() < 1.0f) ? (this.lOver.get(i).AlphaScaleZoomOut * Game.mapScale.getCurrentScale()) : this.lOver.get(i).AlphaScaleZoomOut) + Game.mapScale.getCurrentScale() * this.lOver.get(i).AlphaScale) * fAlpha));
                Renderer.shaderAlpha_Map.setUniformf("u_maskScale", this.lOver.get(i).u_maskScale);
                Renderer.shaderAlpha_Map.setUniformf("u_maskScaleY", this.lOver.get(i).u_maskScaleY);
                Renderer.shaderAlpha_Map.setUniformf("u_extraColor", this.lOver.get(i).ExtraColor);
                this.overMask.get(i).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                this.overTile.get(i).draw(oSB, nPosX, nPosY, MapBG.iWidthOfMap, MapBG.iHeightOfMap);
                oSB.flush();
            }
            if (Game.mapBG.secondSideOfMap) {
                for (int i = 0; i < this.iOverSize; ++i) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (this.lOver.get(i).Alpha + ((Game.mapScale.getCurrentScale() < 1.0f) ? (this.lOver.get(i).AlphaScaleZoomOut * Game.mapScale.getCurrentScale()) : this.lOver.get(i).AlphaScaleZoomOut) + Game.mapScale.getCurrentScale() * this.lOver.get(i).AlphaScale) * fAlpha));
                    Renderer.shaderAlpha_Map.setUniformf("u_maskScale", this.lOver.get(i).u_maskScale);
                    Renderer.shaderAlpha_Map.setUniformf("u_maskScaleY", this.lOver.get(i).u_maskScaleY);
                    Renderer.shaderAlpha_Map.setUniformf("u_extraColor", this.lOver.get(i).ExtraColor);
                    this.overMask.get(i).getTexture().bind(1);
                    Gdx.gl.glActiveTexture(33984);
                    this.overTile.get(i).draw(oSB, nPosX + Game.mapBG.getWidth(), nPosY, MapBG.iWidthOfMap, MapBG.iHeightOfMap);
                    oSB.flush();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(Color.WHITE);
    }
    
    public void drawMapOverlaySea(final SpriteBatch oSB, final int nPosX, final int nPosY, final float fAlpha) {
        try {
            oSB.setShader(Renderer.shaderAlpha_MapSea);
            for (int i = 0; i < this.iOverSize; ++i) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (this.lOver.get(i).Alpha + ((Game.mapScale.getCurrentScale() < 1.0f) ? (this.lOver.get(i).AlphaScaleZoomOut * Game.mapScale.getCurrentScale()) : this.lOver.get(i).AlphaScaleZoomOut) + Game.mapScale.getCurrentScale() * this.lOver.get(i).AlphaScale) * fAlpha));
                Renderer.shaderAlpha_MapSea.setUniformf("u_maskScale", this.lOver.get(i).u_maskScale);
                Renderer.shaderAlpha_MapSea.setUniformf("u_maskScaleY", this.lOver.get(i).u_maskScaleY);
                Renderer.shaderAlpha_MapSea.setUniformf("u_extraColor", this.lOver.get(i).ExtraColor);
                this.overMask.get(i).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                this.overTile.get(i).draw(oSB, nPosX, nPosY, MapBG.iWidthOfMap, MapBG.iHeightOfMap);
                oSB.flush();
            }
            if (Game.mapBG.secondSideOfMap) {
                for (int i = 0; i < this.iOverSize; ++i) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (this.lOver.get(i).Alpha + ((Game.mapScale.getCurrentScale() < 1.0f) ? (this.lOver.get(i).AlphaScaleZoomOut * Game.mapScale.getCurrentScale()) : this.lOver.get(i).AlphaScaleZoomOut) + Game.mapScale.getCurrentScale() * this.lOver.get(i).AlphaScale) * fAlpha));
                    Renderer.shaderAlpha_MapSea.setUniformf("u_maskScale", this.lOver.get(i).u_maskScale);
                    Renderer.shaderAlpha_MapSea.setUniformf("u_maskScaleY", this.lOver.get(i).u_maskScaleY);
                    Renderer.shaderAlpha_MapSea.setUniformf("u_extraColor", this.lOver.get(i).ExtraColor);
                    this.overMask.get(i).getTexture().bind(1);
                    Gdx.gl.glActiveTexture(33984);
                    this.overTile.get(i).draw(oSB, nPosX + Game.mapBG.getWidth(), nPosY, MapBG.iWidthOfMap, MapBG.iHeightOfMap);
                    oSB.flush();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(Color.WHITE);
    }
    
    public static class Config
    {
        public String Age_of_History;
        public ArrayList Overlay;
    }
    
    public static class Overlay
    {
        public String Tile;
        public String Mask;
        public float Scale;
        public float Alpha;
        public float AlphaScale;
        public float AlphaScaleZoomOut;
        public float ExtraColor;
        public float u_maskScale;
        public float u_maskScaleY;
    }
}
