// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.clouds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class CloudsManager
{
    public List<Image> imageCloud;
    public List<Integer> imageCloudMaxDimension;
    private List<Cloud> lClouds;
    private int iCloudsSize;
    private long lTimeDimOutAlpha;
    private long lTimeDimInAlpha;
    private float fDimAlpha;
    public CloudsSettings cloudsSettings;
    public static int updateInViewID;
    public CloudsInterface cloudsInterface;
    
    public CloudsManager() {
        this.imageCloud = new ArrayList<Image>();
        this.imageCloudMaxDimension = new ArrayList<Integer>();
        this.lClouds = new ArrayList<Cloud>();
        this.iCloudsSize = 0;
        this.lTimeDimOutAlpha = 0L;
        this.lTimeDimInAlpha = 0L;
        this.fDimAlpha = 1.0f;
        this.cloudsSettings = new CloudsSettings();
        this.cloudsInterface = new CloudsInterface() {
            @Override
            public void drawCloudsInterface(final SpriteBatch oSB) {
            }
        };
        this.readSettings();
    }
    
    public boolean loadClouds() {
        return CFG.isDesktop() || GameValues.value.MOBILE_LOAD_CLOUDS;
    }
    
    public final void loadCloudsImages() {
        if (this.loadClouds()) {
            for (int i = 0; i < this.cloudsSettings.numOfCloudImages; ++i) {
                this.imageCloud.add(ImageManager.loadImage("gfx/clouds/" + i + ".png"));
            }
            for (int i = 0; i < this.cloudsSettings.numOfCloudImages; ++i) {
                this.imageCloudMaxDimension.add(Math.max(this.imageCloud.get(i).getWidth(), this.imageCloud.get(i).getHeight()));
            }
            for (int i = 0; i < 25; ++i) {
                this.addCloud();
            }
        }
    }
    
    public final void readSettings() {
        final Json json = new Json();
        final FileHandle file = FileManager.loadFile("gfx/clouds/" + (Game.highTextureSettings ? "Config.json" : "ConfigLow.json"));
        this.cloudsSettings = (CloudsSettings)json.fromJson((Class)CloudsSettings.class, file);
        this.cloudsSettings.moveSpeedY = Math.max(0.1f, this.cloudsSettings.moveSpeedY);
        this.cloudsSettings.randomAlpha = Math.min(1.0f, this.cloudsSettings.randomAlpha);
        if (!this.loadClouds()) {
            this.cloudsSettings.numOfCloudImages = 0;
        }
    }
    
    public final void addCloud() {
        this.addCloud(Game.oR.nextInt(Game.mapBG.getWidth()), Game.oR.nextInt(Game.mapBG.getHeight()));
    }
    
    public final void addCloud(final int nPosX, final int nPosY) {
        if (this.iCloudsSize < this.cloudsSettings.maxNumOfCloudsInTheGame && this.cloudsSettings.numOfCloudImages > 0) {
            final int tempID = Game.oR.nextInt(this.imageCloud.size());
            this.lClouds.add(new Cloud(tempID, nPosX, nPosY, (10 + Game.oR.nextInt(20) + Game.oR.nextInt(25) + Game.oR.nextInt(25) + Game.oR.nextInt(46)) / 100.0f, Game.oR.nextInt(360)));
            this.iCloudsSize = this.lClouds.size();
        }
    }
    
    public final void updateClouds() {
        for (int i = this.lClouds.size() - 1; i >= 0; --i) {
            if (this.lClouds.get(i).removeCloud()) {
                this.lClouds.remove(i);
                this.iCloudsSize = this.lClouds.size();
            }
        }
        this.randomSpawnCloud();
        for (int i = 0; i < this.iCloudsSize; ++i) {
            this.lClouds.get(i).update();
        }
        CloudsManager.updateInViewID = (CloudsManager.updateInViewID + 1) % 8;
        for (int i = CloudsManager.updateInViewID; i < this.iCloudsSize; i += 8) {
            this.lClouds.get(i).updateIsInView();
        }
    }
    
    public final void randomSpawnCloud() {
        if (Game.oR.nextFloat() < this.cloudsSettings.spawnCloudChance) {
            this.addCloud();
        }
    }
    
    public final void updateCloudsInterface() {
        if (!Game.settingsManager.CLOUDS) {
            this.cloudsInterface = new CloudsInterface() {
                @Override
                public void drawCloudsInterface(final SpriteBatch oSB) {
                }
            };
            this.lClouds.clear();
            this.iCloudsSize = 0;
        }
        else {
            this.cloudsInterface = new CloudsInterface() {
                @Override
                public void drawCloudsInterface(final SpriteBatch oSB) {
                    CloudsManager.this.drawClouds(oSB);
                }
            };
        }
    }
    
    public final void drawClouds(final SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() > this.cloudsSettings.drawCloudsMinScale && Game.mapScale.getCurrentScale() < this.cloudsSettings.drawCloudsMaxScale) {
            this.lTimeDimOutAlpha = CFG.currentTimeMillis;
            if (this.fDimAlpha < 1.0f) {
                this.fDimAlpha = Math.max(1.0f * ((CFG.currentTimeMillis - this.lTimeDimInAlpha) / (float)Game.cloudsAnimation.cloudsSettings.spawnAnimationTime), 0.0f);
                this.fDimAlpha = Math.min(this.fDimAlpha, 1.0f);
            }
            this.drawClouds(oSB, this.fDimAlpha);
        }
        else {
            this.fDimAlpha = Math.max(1.0f - 1.0f * ((CFG.currentTimeMillis - this.lTimeDimOutAlpha) / (float)Game.cloudsAnimation.cloudsSettings.spawnAnimationTime), 0.0f);
            this.lTimeDimInAlpha = CFG.currentTimeMillis - (long)(Game.cloudsAnimation.cloudsSettings.spawnAnimationTime * this.fDimAlpha);
            if (this.fDimAlpha > 0.01f) {
                this.drawClouds(oSB, this.fDimAlpha);
            }
        }
        oSB.setColor(Color.WHITE);
    }
    
    public final void drawClouds(final SpriteBatch oSB, float modAlpha) {
        this.updateClouds();
        for (int i = 0; i < this.iCloudsSize; ++i) {
            if (this.lClouds.get(i).isInView) {
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.lClouds.get(i).fAlpha * modAlpha));
                this.imageCloud.get(this.lClouds.get(i).iCloudImageID).draw(oSB, (int)this.lClouds.get(i).iPosX + this.lClouds.get(i).iPosX_SecondSideOfMap + Game.mapCoords.getPosX() + this.lClouds.get(i).iShadowX, (int)this.lClouds.get(i).iPosY + Game.mapCoords.getPosY() + this.lClouds.get(i).iShadowY, this.lClouds.get(i).fScale * 0.75f, (float)this.lClouds.get(i).iRotate);
            }
        }
        if (Game.mapScale.getCurrentScale() < 1.0f) {
            modAlpha *= 1.0f + (1.0f - Game.mapScale.getCurrentScale()) / 2.0f;
        }
        for (int i = 0; i < this.iCloudsSize; ++i) {
            if (this.lClouds.get(i).isInView) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.lClouds.get(i).fAlpha * modAlpha));
                this.imageCloud.get(this.lClouds.get(i).iCloudImageID).draw(oSB, (int)this.lClouds.get(i).iPosX + this.lClouds.get(i).iPosX_SecondSideOfMap + Game.mapCoords.getPosX(), (int)this.lClouds.get(i).iPosY + Game.mapCoords.getPosY(), this.lClouds.get(i).fScale, (float)this.lClouds.get(i).iRotate);
            }
        }
        oSB.setColor(Color.WHITE);
    }
    
    static {
        CloudsManager.updateInViewID = 0;
    }
    
    public static class CloudsSettings
    {
        public int numOfCloudImages;
        public int maxNumOfCloudsInTheGame;
        public float spawnCloudChance;
        public int spawnAnimationTime;
        public float moveSpeedX;
        public float moveSpeedY;
        public int shadowX;
        public int shadowY;
        public float minAlpha;
        public float randomAlpha;
        public float drawCloudsMinScale;
        public float drawCloudsMaxScale;
    }
    
    public interface CloudsInterface
    {
        void drawCloudsInterface(final SpriteBatch p0);
    }
}
