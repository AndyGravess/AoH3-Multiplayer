// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import com.badlogic.gdx.audio.Music;
import java.util.List;

public class AmbienceManager
{
    public List<Music> lSounds;
    public long VOLUME_TIME;
    public long VOLUME_TIME_DOWN;
    public static List<Ambience> ambiences;
    public static int lastPosX;
    public static int lastPosY;
    public static int lastProvinceID;
    public static int activeNew;
    public static int activeOld;
    
    public AmbienceManager() {
        this.VOLUME_TIME = 2000L;
        this.VOLUME_TIME_DOWN = 1500L;
        this.lSounds = new ArrayList<Music>();
    }
    
    public boolean loadAmbience() {
        return CFG.isDesktop() || GameValues.value.MOBILE_LOAD_AMBIENCE;
    }
    
    public final void loadSounds() {
        if (this.loadAmbience()) {
            final FileHandle tempFileT = FileManager.loadFile("audio/ambience/AoH.txt");
            final String tempT = tempFileT.readString();
            final String[] tList = tempT.split(";");
            for (int i = 0; i < tList.length; ++i) {
                AmbienceManager.ambiences.add(new Ambience(this.addSoundSFX(tList[i] + "." + getFileExtension())));
            }
        }
    }
    
    public final void update() {
        if (!this.loadAmbience()) {
            return;
        }
        try {
            if (Game.mapScale.getCurrentScale() > GameValues.inGame.AMBIENCE_SCALE && (AmbienceManager.lastPosX != Game.mapCoords.getPosX() || AmbienceManager.lastPosY != Game.mapCoords.getPosY())) {
                AmbienceManager.lastPosX = Game.mapCoords.getPosX();
                AmbienceManager.lastPosY = Game.mapCoords.getPosY();
                if (AmbienceManager.lastProvinceID < 0 || !Game.setProvinceID_IsMouseOverAProvinceID((int)(-Game.mapCoords.getPosX() + CFG.GAME_WIDTH / 2 / Game.mapScale.getCurrentScale()), (int)(-Game.mapCoords.getPosY() + CFG.GAME_HEIGHT / 2 / Game.mapScale.getCurrentScale()), AmbienceManager.lastProvinceID)) {
                    final int nProvinceID = Game.setProvinceID_Point((int)(-Game.mapCoords.getPosX() + CFG.GAME_WIDTH / 2 / Game.mapScale.getCurrentScale()), (int)(-Game.mapCoords.getPosY() + CFG.GAME_HEIGHT / 2 / Game.mapScale.getCurrentScale()));
                    if (nProvinceID != AmbienceManager.lastProvinceID && nProvinceID >= 0) {
                        if (AmbienceManager.activeNew != Game.terrainManager.terrains.get(Game.getProvince(nProvinceID).getTerrainID()).Ambience) {
                            if (AmbienceManager.activeOld < 0 && AmbienceManager.activeNew >= 0) {
                                AmbienceManager.activeOld = AmbienceManager.activeNew;
                            }
                            else if (AmbienceManager.activeNew >= 0) {
                                AmbienceManager.ambiences.get(AmbienceManager.activeNew).isPlaying = false;
                                AmbienceManager.ambiences.get(AmbienceManager.activeNew).isPlaying = false;
                                AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateVolumeUP = false;
                                this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id).pause();
                            }
                            AmbienceManager.activeNew = Game.terrainManager.terrains.get(Game.getProvince(nProvinceID).getTerrainID()).Ambience;
                        }
                        AmbienceManager.lastProvinceID = nProvinceID;
                        if (AmbienceManager.activeNew == AmbienceManager.activeOld) {
                            AmbienceManager.activeOld = -1;
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (AmbienceManager.activeOld >= 0 && AmbienceManager.ambiences.get(AmbienceManager.activeOld).isPlaying) {
                if (AmbienceManager.ambiences.get(AmbienceManager.activeOld).updateTime) {
                    AmbienceManager.ambiences.get(AmbienceManager.activeOld).updateTime = false;
                    AmbienceManager.ambiences.get(AmbienceManager.activeOld).updateVolumeUP = true;
                    AmbienceManager.ambiences.get(AmbienceManager.activeOld).lTime = CFG.currentTimeMillis;
                }
                final Music music = this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeOld).id);
                final SoundsManager soundsManager = Game.soundsManager;
                final float ambienceVolume = SoundsManager.ambienceVolume;
                final SoundsManager soundsManager2 = Game.soundsManager;
                music.setVolume(ambienceVolume * SoundsManager.masterVolume * Math.max(0.0f, 1.0f - (CFG.currentTimeMillis - AmbienceManager.ambiences.get(AmbienceManager.activeOld).lTime) / (float)this.VOLUME_TIME_DOWN));
                if (CFG.currentTimeMillis - AmbienceManager.ambiences.get(AmbienceManager.activeOld).lTime > this.VOLUME_TIME) {
                    AmbienceManager.ambiences.get(AmbienceManager.activeOld).isPlaying = false;
                    AmbienceManager.ambiences.get(AmbienceManager.activeOld).isPlaying = false;
                    AmbienceManager.ambiences.get(AmbienceManager.activeOld).updateVolumeUP = false;
                    this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeOld).id).pause();
                    AmbienceManager.activeOld = -1;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (AmbienceManager.activeNew >= 0) {
                if (Game.mapScale.getCurrentScale() > GameValues.inGame.AMBIENCE_SCALE) {
                    if (!AmbienceManager.ambiences.get(AmbienceManager.activeNew).startedPlaying) {
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).startedPlaying = true;
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateVolumeUP = true;
                        this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id).setLooping(true);
                        this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id).setVolume(0.0f);
                        this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id).play();
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).lTime = CFG.currentTimeMillis;
                    }
                    else if (!AmbienceManager.ambiences.get(AmbienceManager.activeNew).isPlaying) {
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).isPlaying = true;
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateVolumeUP = true;
                        this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id).play();
                        this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id).setVolume(0.0f);
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).lTime = CFG.currentTimeMillis;
                    }
                    else if (AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateVolumeUP) {
                        if (CFG.currentTimeMillis - AmbienceManager.ambiences.get(AmbienceManager.activeNew).lTime > this.VOLUME_TIME) {
                            AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateVolumeUP = false;
                        }
                        final Music music2 = this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id);
                        final SoundsManager soundsManager3 = Game.soundsManager;
                        final float ambienceVolume2 = SoundsManager.ambienceVolume;
                        final SoundsManager soundsManager4 = Game.soundsManager;
                        music2.setVolume(ambienceVolume2 * SoundsManager.masterVolume * Math.min(1.0f, (CFG.currentTimeMillis - AmbienceManager.ambiences.get(AmbienceManager.activeNew).lTime) / (float)this.VOLUME_TIME));
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateTime = true;
                    }
                }
                else if (AmbienceManager.ambiences.get(AmbienceManager.activeNew).isPlaying) {
                    if (AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateTime) {
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateTime = false;
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateVolumeUP = true;
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).lTime = CFG.currentTimeMillis;
                    }
                    final Music music3 = this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id);
                    final SoundsManager soundsManager5 = Game.soundsManager;
                    final float ambienceVolume3 = SoundsManager.ambienceVolume;
                    final SoundsManager soundsManager6 = Game.soundsManager;
                    music3.setVolume(ambienceVolume3 * SoundsManager.masterVolume * Math.max(0.0f, 1.0f - (CFG.currentTimeMillis - AmbienceManager.ambiences.get(AmbienceManager.activeNew).lTime) / (float)this.VOLUME_TIME_DOWN));
                    if (CFG.currentTimeMillis - AmbienceManager.ambiences.get(AmbienceManager.activeNew).lTime > this.VOLUME_TIME) {
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).isPlaying = false;
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).isPlaying = false;
                        AmbienceManager.ambiences.get(AmbienceManager.activeNew).updateVolumeUP = false;
                        this.lSounds.get(AmbienceManager.ambiences.get(AmbienceManager.activeNew).id).pause();
                        AmbienceManager.activeNew = -1;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final int addSoundSFX(final String fileName) {
        try {
            this.lSounds.add(Gdx.audio.newMusic(FileManager.loadFile("audio/ambience/" + fileName)));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return this.lSounds.size() - 1;
    }
    
    public static final String getFileExtension() {
        return CFG.isiOS ? "mp3" : "ogg";
    }
    
    static {
        AmbienceManager.ambiences = new ArrayList<Ambience>();
        AmbienceManager.activeNew = -1;
        AmbienceManager.activeOld = -1;
    }
    
    public class Ambience
    {
        public int id;
        public long idSoundPlaying;
        public boolean startedPlaying;
        public boolean isPlaying;
        public boolean updateVolumeUP;
        public boolean updateTime;
        public long lTime;
        
        public Ambience(final int id) {
            this.id = 0;
            this.idSoundPlaying = 0L;
            this.startedPlaying = false;
            this.isPlaying = false;
            this.updateVolumeUP = true;
            this.updateTime = false;
            this.lTime = 0L;
            this.id = id;
        }
        
        public final void update() {
        }
    }
}
