// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import com.badlogic.gdx.Gdx;
import java.util.Random;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import java.util.List;

public class SoundsManager
{
    public static float masterVolume;
    public static float musicVolume;
    public static float soundsVolume;
    public static float ambienceVolume;
    public static float PERC_VOLUME_SELECT_PROVINCE;
    public static float PERC_VOLUME_KEYBOARD;
    public List<String> lTitles;
    public List<String> lTitlesWar;
    public Music currentMusic;
    public int iCurrentMusicID;
    public static boolean isWarMusicPlaying;
    public List<Sound> lSounds;
    public List<Sound> lSoundsRandom;
    public int soundsRandomSize;
    public long SOUNDS_RANDOM_TIME;
    public static int SOUND_CLICK_MAIN;
    public static int SOUND_CLICK_MAIN2;
    public static int SOUND_CLICK2;
    public static int SOUND_CLICK3;
    public static int SOUND_CLICK_PAGE;
    public static int SOUND_CLICK_PAGE_1;
    public static int SOUND_PROVINCE;
    public static int SOUND_BATTLE;
    public static int SOUND_BATTLE2;
    public static int SOUND_NUKE;
    public static int SOUND_NUKE2;
    public static int SOUND_CLICK_WAR;
    public static int SOUND_FORMABLE;
    public static int SOUND_HOVER_0;
    public static int SOUND_HOVER_1;
    public static int SOUND_HOVER_2;
    public static int SOUND_HOVER_3;
    public static int SOUND_HOVER_4;
    public static int SOUND_HOVER_5;
    public static int SOUND_HOVER_6;
    public static int SOUND_HOVER_7;
    public static int SOUND_HOVER_8;
    public static int SOUND_SELECTED_ARMY_0;
    public static int SOUND_SELECTED_ARMY_1;
    public static int SOUND_RECRUIT_ARMY_0;
    public static int SOUND_RECRUIT_ARMY_1;
    public static int SOUND_RECRUIT_CANCEL;
    public static int SOUND_GOLD_0;
    public static int SOUND_GOLD_1;
    public static int SOUND_GOLD_2;
    public static int SOUND_GOLD_3;
    public static int SOUND_GOLD_4;
    public static int TAB_0;
    public static int TAB_1;
    public static int SOUND_WAR_END;
    public static int SOUND_GOLD_LEVEL_0;
    public static int SOUND_GOLD_LEVEL_1;
    public static int SOUND_GOLD_LEVEL_2;
    public static int SOUND_COIN_0;
    public static int SOUND_COIN_1;
    public static int SOUND_COIN_2;
    public static int SOUND_PLAY_NEW_GAME;
    public static int SOUND_CREATE_ARMY;
    public static int SOUND_ECONOMY_0;
    public static int SOUND_ECONOMY_1;
    public static int SOUND_INFRASTRUCTURE;
    public static int SOUND_INFRASTRUCTURE_1;
    public static int SOUND_INCREASE_MANPOWER;
    public static int SOUND_INCREASE_MANPOWER2;
    public static int SOUND_CORES;
    public static int SOUND_GROWTH_RATE;
    public static int SOUND_GROWTH_RATE2;
    public static int SOUND_ADVANTAGE0;
    public static int SOUND_ADVANTAGE1;
    public static int SOUND_ADVANTAGE2;
    public static int SOUND_CLICK_TOP;
    public static int SOUND_LOAN;
    public static int SOUND_LOAN_REPAY;
    public static int MOVE_0;
    public static int MOVE_1;
    public static int MOVE_2;
    public static int MOVE_3;
    public static int MOVE_4;
    public static int MOVE_SEA_0;
    public static int MOVE_SEA_1;
    public static int LEGACY_0;
    public static int LEGACY_1;
    public static int LEGACY_2;
    public static int TECHNOLOGY;
    public static int TECHNOLOGY_CLICK;
    public static int ARMY_CLICK;
    public static int GENERALS_CLICK;
    public static int MAP_MODE0;
    public static int MAP_MODE1;
    public static int PLAY;
    public static int FLAG_CLICK;
    public static int BUDGET_CLICK;
    public static int CIV_OPTIONS_CLICK;
    public static int CIV_OPTIONS_CLICK1;
    public static int BUILD0;
    public static int BUILD1;
    public static int WAR;
    public static int SIEGE;
    public static int DIPLOMACY0;
    public static int DIPLOMACY1;
    public static int DIPLOMACY_CLICK;
    public static int INFO_BOX;
    public static int EVENT;
    public static int EVENT_INFO;
    public long WAR_MUSIC_LAST_TIME_PLAYED;
    public static int iCivOptionsSound;
    public static int iGrowthRate;
    public int iHoverID;
    public float hoverVolume;
    public long lHoverTime;
    public int iSelectArmy;
    public long lRecruitTime;
    public int iRecruitArmy;
    public int tabNum;
    public int iDiplomacyButton;
    public int iEconomy;
    public int iIncreaseManpower;
    
    public static final String getFileExtension() {
        return CFG.isiOS ? "mp3" : "ogg";
    }
    
    public SoundsManager() {
        this.currentMusic = null;
        this.iCurrentMusicID = 0;
        this.lSoundsRandom = new ArrayList<Sound>();
        this.soundsRandomSize = 0;
        this.WAR_MUSIC_LAST_TIME_PLAYED = 0L;
        this.iHoverID = 0;
        this.hoverVolume = 0.5f;
        this.lHoverTime = 0L;
        this.iSelectArmy = 0;
        this.lRecruitTime = 0L;
        this.iRecruitArmy = 0;
        this.tabNum = 0;
        this.iDiplomacyButton = 0;
        this.iEconomy = 0;
        this.iIncreaseManpower = 0;
        this.lTitles = new ArrayList<String>();
        this.lTitlesWar = new ArrayList<String>();
        this.lSounds = new ArrayList<Sound>();
        SoundsManager.SOUND_CLICK_MAIN = this.addSoundSFX("click." + getFileExtension());
        SoundsManager.SOUND_CLICK_MAIN2 = this.addSoundSFX("click." + getFileExtension());
        SoundsManager.SOUND_CLICK2 = this.addSound("click2." + getFileExtension());
        SoundsManager.SOUND_CLICK3 = this.addSound("click3." + getFileExtension());
        SoundsManager.SOUND_CLICK_PAGE = this.addSound("click_page_0." + getFileExtension());
        SoundsManager.SOUND_CLICK_PAGE_1 = this.addSound("click_page_1." + getFileExtension());
        SoundsManager.SOUND_PROVINCE = this.addSoundSFX("clickProvince." + getFileExtension());
        SoundsManager.SOUND_HOVER_0 = this.addSoundSFX("hover0." + getFileExtension());
        SoundsManager.SOUND_HOVER_1 = this.addSoundSFX("hover1." + getFileExtension());
        SoundsManager.SOUND_HOVER_2 = this.addSoundSFX("hover2." + getFileExtension());
        SoundsManager.SOUND_HOVER_3 = this.addSoundSFX("hover3." + getFileExtension());
        SoundsManager.SOUND_HOVER_4 = this.addSoundSFX("hover4." + getFileExtension());
        SoundsManager.SOUND_HOVER_5 = this.addSoundSFX("hover5." + getFileExtension());
        SoundsManager.SOUND_HOVER_6 = this.addSoundSFX("hover6." + getFileExtension());
        SoundsManager.SOUND_HOVER_7 = this.addSoundSFX("hover7." + getFileExtension());
        SoundsManager.SOUND_HOVER_8 = this.addSoundSFX("hover8." + getFileExtension());
        SoundsManager.masterVolume = Game.settingsManager.VOLUME_MASTER;
        this.setSoundsVolume(Game.settingsManager.VOLUME_SOUNDS);
        this.setAmbienceVolume(Game.settingsManager.VOLUME_AMBIENCE);
        this.setMusicVolume(Game.settingsManager.VOLUME_MUSIC);
        this.hoverVolume = Game.settingsManager.VOLUME_HOVER;
    }
    
    public final void loadSounds() {
        SoundsManager.SOUND_BATTLE = this.addSoundSFX("battle." + getFileExtension());
        SoundsManager.SOUND_BATTLE2 = this.addSoundSFX("battle2." + getFileExtension());
        SoundsManager.SOUND_NUKE = this.addSound("nuke." + getFileExtension());
        SoundsManager.PLAY = this.addSoundSFX("play." + getFileExtension());
        SoundsManager.SOUND_SELECTED_ARMY_0 = this.addSoundSFX("selectedArmy0." + getFileExtension());
        SoundsManager.SOUND_SELECTED_ARMY_1 = this.addSoundSFX("selectedArmy1." + getFileExtension());
        SoundsManager.SOUND_RECRUIT_ARMY_0 = this.addSoundSFX("recruitArmy0." + getFileExtension());
        SoundsManager.SOUND_RECRUIT_ARMY_1 = this.addSoundSFX("recruitArmy1." + getFileExtension());
        SoundsManager.SOUND_RECRUIT_CANCEL = this.addSoundSFX("recruitArmyCancel." + getFileExtension());
        SoundsManager.SOUND_CORES = this.addSoundSFX("cores." + getFileExtension());
        SoundsManager.SOUND_GOLD_0 = this.addSoundSFX("gold0." + getFileExtension());
        SoundsManager.SOUND_GOLD_1 = this.addSoundSFX("gold1." + getFileExtension());
        SoundsManager.SOUND_GOLD_2 = this.addSoundSFX("gold2." + getFileExtension());
        SoundsManager.SOUND_GOLD_3 = this.addSoundSFX("gold3." + getFileExtension());
        SoundsManager.SOUND_GOLD_4 = this.addSoundSFX("gold4." + getFileExtension());
        SoundsManager.TAB_0 = this.addSoundSFX("tab0." + getFileExtension());
        SoundsManager.TAB_1 = this.addSoundSFX("tab1." + getFileExtension());
        SoundsManager.SOUND_GOLD_LEVEL_0 = this.addSoundSFX("goldLevel0." + getFileExtension());
        SoundsManager.SOUND_GOLD_LEVEL_1 = this.addSoundSFX("goldLevel1." + getFileExtension());
        SoundsManager.SOUND_GOLD_LEVEL_2 = this.addSoundSFX("goldLevel2." + getFileExtension());
        SoundsManager.SOUND_COIN_0 = this.addSoundSFX("coin0." + getFileExtension());
        SoundsManager.SOUND_COIN_1 = this.addSoundSFX("coin1." + getFileExtension());
        SoundsManager.SOUND_COIN_2 = this.addSoundSFX("coin2." + getFileExtension());
        SoundsManager.SOUND_CREATE_ARMY = this.addSoundSFX("createArmy." + getFileExtension());
        SoundsManager.SOUND_CLICK_WAR = this.addSoundSFX("clickWar." + getFileExtension());
        SoundsManager.SOUND_FORMABLE = this.addSoundSFX("formable." + getFileExtension());
        SoundsManager.SOUND_INFRASTRUCTURE = this.addSoundSFX("infrastructure." + getFileExtension());
        SoundsManager.SOUND_INFRASTRUCTURE_1 = this.addSoundSFX("infrastructure1." + getFileExtension());
        SoundsManager.SOUND_ECONOMY_0 = this.addSoundSFX("economy0." + getFileExtension());
        SoundsManager.SOUND_ECONOMY_1 = this.addSoundSFX("economy1." + getFileExtension());
        SoundsManager.SOUND_INCREASE_MANPOWER = this.addSoundSFX("increaseManpower." + getFileExtension());
        SoundsManager.SOUND_INCREASE_MANPOWER2 = this.addSoundSFX("increaseManpower2." + getFileExtension());
        SoundsManager.SOUND_GROWTH_RATE = this.addSoundSFX("growthRate." + getFileExtension());
        SoundsManager.SOUND_GROWTH_RATE2 = this.addSoundSFX("growthRate2." + getFileExtension());
        SoundsManager.SOUND_ADVANTAGE0 = this.addSoundSFX("advantage0." + getFileExtension());
        SoundsManager.SOUND_ADVANTAGE1 = this.addSoundSFX("advantage1." + getFileExtension());
        SoundsManager.SOUND_ADVANTAGE2 = this.addSoundSFX("advantage2." + getFileExtension());
        SoundsManager.SOUND_CLICK_TOP = this.addSoundSFX("clickTop." + getFileExtension());
        SoundsManager.EVENT = this.addSoundSFX("event." + getFileExtension());
        SoundsManager.EVENT_INFO = this.addSoundSFX("eventInfo2." + getFileExtension());
        SoundsManager.SOUND_LOAN = this.addSoundSFX("loan." + getFileExtension());
        SoundsManager.SOUND_LOAN_REPAY = this.addSoundSFX("loanRepay." + getFileExtension());
        SoundsManager.MOVE_0 = this.addSoundSFX("move0." + getFileExtension());
        SoundsManager.MOVE_1 = this.addSoundSFX("move1." + getFileExtension());
        SoundsManager.MOVE_2 = this.addSoundSFX("move2." + getFileExtension());
        SoundsManager.MOVE_3 = this.addSoundSFX("move3." + getFileExtension());
        SoundsManager.MOVE_4 = this.addSoundSFX("move4." + getFileExtension());
        SoundsManager.MOVE_SEA_0 = this.addSoundSFX("moveSea." + getFileExtension());
        SoundsManager.MOVE_SEA_1 = this.addSoundSFX("moveSea1." + getFileExtension());
        SoundsManager.SOUND_WAR_END = this.addSoundSFX("warEnd." + getFileExtension());
        SoundsManager.SOUND_PLAY_NEW_GAME = this.addSoundSFX("playNewGame." + getFileExtension());
        SoundsManager.LEGACY_0 = this.addSoundSFX("legacy0." + getFileExtension());
        SoundsManager.LEGACY_1 = this.addSoundSFX("legacy1." + getFileExtension());
        SoundsManager.LEGACY_2 = this.addSoundSFX("legacy2." + getFileExtension());
        SoundsManager.TECHNOLOGY = this.addSoundSFX("technology." + getFileExtension());
        SoundsManager.TECHNOLOGY_CLICK = this.addSoundSFX("technologyClick." + getFileExtension());
        SoundsManager.ARMY_CLICK = this.addSoundSFX("armyClick." + getFileExtension());
        SoundsManager.GENERALS_CLICK = this.addSoundSFX("generals." + getFileExtension());
        SoundsManager.MAP_MODE0 = this.addSoundSFX("mapMode0." + getFileExtension());
        SoundsManager.MAP_MODE1 = this.addSoundSFX("mapMode1." + getFileExtension());
        SoundsManager.BUILD0 = this.addSoundSFX("build." + getFileExtension());
        SoundsManager.BUILD1 = this.addSoundSFX("build1." + getFileExtension());
        SoundsManager.DIPLOMACY0 = this.addSoundSFX("diplomacy." + getFileExtension());
        SoundsManager.DIPLOMACY1 = this.addSoundSFX("diplomacy1." + getFileExtension());
        SoundsManager.DIPLOMACY_CLICK = this.addSoundSFX("diplomacyClick." + getFileExtension());
        SoundsManager.WAR = this.addSoundSFX("war." + getFileExtension());
        SoundsManager.SIEGE = this.addSoundSFX("siege." + getFileExtension());
        SoundsManager.FLAG_CLICK = this.addSoundSFX("flagClick." + getFileExtension());
        SoundsManager.BUDGET_CLICK = this.addSoundSFX("budgetClick." + getFileExtension());
        SoundsManager.CIV_OPTIONS_CLICK = this.addSoundSFX("civOptionsClick." + getFileExtension());
        SoundsManager.CIV_OPTIONS_CLICK1 = this.addSoundSFX("civOptionsClick1." + getFileExtension());
        SoundsManager.INFO_BOX = this.addSoundSFX("infoBox." + getFileExtension());
        this.loadSFXRandom();
        this.SOUNDS_RANDOM_TIME = System.currentTimeMillis() + GameValues.inGame.SOUNDS_RANDOM_MIN + Game.oR.nextInt(GameValues.inGame.SOUNDS_RANDOM_RANDOM);
    }
    
    public final void loadSFXRandom() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("audio/random/list.txt");
            final String[] split = tempFileT.readString().split(";");
            for (int i = 0; i < split.length; ++i) {
                this.addSoundSFXRandom(split[i] + "." + getFileExtension());
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        this.soundsRandomSize = this.lSoundsRandom.size();
    }
    
    public final void loadMusic_List() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("audio/music/list.txt");
            final String tempT = tempFileT.readString();
            final String[] tagsSPLITED = tempT.split(";");
            for (int i = 0; i < tagsSPLITED.length; ++i) {
                this.lTitles.add(tagsSPLITED[i]);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.LOG((Throwable)ex);
        }
        try {
            final FileHandle tempFileT = FileManager.loadFile("audio/music/listWar.txt");
            final String tempT = tempFileT.readString();
            final String[] tagsSPLITED = tempT.split(";");
            for (int i = 0; i < tagsSPLITED.length; ++i) {
                this.lTitlesWar.add(tagsSPLITED[i]);
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.LOG((Throwable)ex);
        }
        this.randomizePlayList();
    }
    
    public final void randomizePlayList() {
        final Random oR = new Random();
        final List<String> tempList = new ArrayList<String>();
        for (int i = 0; i < this.lTitles.size(); ++i) {
            tempList.add(this.lTitles.get(i));
        }
        this.lTitles.clear();
        while (tempList.size() > 0) {
            final int tempR = oR.nextInt(tempList.size());
            this.lTitles.add(tempList.get(tempR));
            tempList.remove(tempR);
        }
    }
    
    public final void loadNextMusic() {
        this.disposeCurrentMusic();
        SoundsManager.isWarMusicPlaying = false;
        ++this.iCurrentMusicID;
        if (this.iCurrentMusicID >= this.lTitles.size()) {
            this.iCurrentMusicID = 0;
            this.randomizePlayList();
        }
        try {
            if (FileManager.loadFile("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()).exists()) {
                (this.currentMusic = Gdx.audio.newMusic(FileManager.loadFile("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()))).setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                    public void onCompletion(final Music music) {
                        SoundsManager.this.loadNextMusic();
                    }
                });
            }
            else if (Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()).exists()) {
                (this.currentMusic = Gdx.audio.newMusic(Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()))).setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                    public void onCompletion(final Music music) {
                        SoundsManager.this.loadNextMusic();
                    }
                });
            }
            else {
                this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                    public void onCompletion(final Music music) {
                    }
                });
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void loadNextMusic(final String fileName) {
        try {
            this.disposeCurrentMusic();
            SoundsManager.isWarMusicPlaying = false;
            ++this.iCurrentMusicID;
            if (this.iCurrentMusicID >= this.lTitles.size()) {
                this.iCurrentMusicID = 0;
                this.randomizePlayList();
            }
            try {
                if (FileManager.loadFile("audio/music/" + fileName + this.getFileType()).exists()) {
                    (this.currentMusic = Gdx.audio.newMusic(FileManager.loadFile("audio/music/" + fileName + this.getFileType()))).setLooping(false);
                    this.currentMusic.play();
                    this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                    this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                        public void onCompletion(final Music music) {
                            SoundsManager.this.loadNextMusic();
                        }
                    });
                }
                else if (Gdx.files.local("audio/music/" + fileName + this.getFileType()).exists()) {
                    (this.currentMusic = Gdx.audio.newMusic(Gdx.files.local("audio/music/" + fileName + this.getFileType()))).setLooping(false);
                    this.currentMusic.play();
                    this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                    this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                        public void onCompletion(final Music music) {
                            SoundsManager.this.loadNextMusic();
                        }
                    });
                }
                else {
                    this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                        public void onCompletion(final Music music) {
                        }
                    });
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            this.loadNextMusic();
        }
    }
    
    public final void loadNextMusicWar() {
        if (!SoundsManager.isWarMusicPlaying && CFG.currentTimeMillis - this.WAR_MUSIC_LAST_TIME_PLAYED > GameValues.inGame.WAR_MUSIC_BREAK_BETWEEN_LAST_TIME_PLAYED) {
            this.disposeCurrentMusic();
            SoundsManager.isWarMusicPlaying = true;
            this.WAR_MUSIC_LAST_TIME_PLAYED = CFG.currentTimeMillis;
            try {
                (this.currentMusic = Gdx.audio.newMusic(FileManager.loadFile("audio/music/" + this.lTitlesWar.get(Game.oR.nextInt(this.lTitlesWar.size())) + this.getFileType()))).setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                    public void onCompletion(final Music music) {
                        SoundsManager.this.loadNextMusic();
                    }
                });
            }
            catch (final GdxRuntimeException ex) {
                try {
                    (this.currentMusic = Gdx.audio.newMusic(Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()))).setLooping(false);
                    this.currentMusic.play();
                    this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                    this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                        public void onCompletion(final Music music) {
                            SoundsManager.this.loadNextMusic();
                        }
                    });
                }
                catch (final Exception exr) {
                    CFG.exceptionStack(exr);
                }
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
    }
    
    public final void loadNextMusic(final String sTitle, final int id) {
        this.disposeCurrentMusic();
        this.iCurrentMusicID = id;
        try {
            (this.currentMusic = Gdx.audio.newMusic(FileManager.loadFile("audio/music/" + sTitle + this.getFileType()))).setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
            this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                public void onCompletion(final Music music) {
                    SoundsManager.this.loadNextMusic();
                }
            });
        }
        catch (final GdxRuntimeException ex) {
            try {
                (this.currentMusic = Gdx.audio.newMusic(Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()))).setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                    public void onCompletion(final Music music) {
                        SoundsManager.this.loadNextMusic();
                    }
                });
            }
            catch (final Exception exr) {
                CFG.exceptionStack(exr);
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public final void playStartMusic() {
        try {
            this.disposeCurrentMusic();
            (this.currentMusic = Gdx.audio.newMusic(FileManager.loadFile("audio/music/" + this.lTitles.get(0) + this.getFileType()))).setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
            this.currentMusic.setOnCompletionListener((Music.OnCompletionListener)new Music.OnCompletionListener() {
                public void onCompletion(final Music music) {
                    SoundsManager.this.loadNextMusic();
                }
            });
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void disposeCurrentMusic() {
        if (this.currentMusic != null) {
            this.currentMusic.stop();
            this.currentMusic.dispose();
        }
    }
    
    public String getFileType() {
        return CFG.isIOS() ? ".mp3" : ".ogg";
    }
    
    public final int addSound(final String fileName) {
        try {
            this.lSounds.add(Gdx.audio.newSound(FileManager.loadFile("audio/sounds/" + fileName)));
        }
        catch (final GdxRuntimeException ex) {
            ex.printStackTrace();
            try {
                this.lSounds.add(Gdx.audio.newSound(Gdx.files.local("audio/sounds/" + fileName)));
            }
            catch (final GdxRuntimeException exr) {
                ex.printStackTrace();
            }
        }
        return this.lSounds.size() - 1;
    }
    
    public final int addSoundSFX(final String fileName) {
        try {
            this.lSounds.add(Gdx.audio.newSound(FileManager.loadFile("audio/sfx/" + fileName)));
        }
        catch (final GdxRuntimeException ex) {
            ex.printStackTrace();
            try {
                this.lSounds.add(Gdx.audio.newSound(Gdx.files.local("audio/sfx/" + fileName)));
            }
            catch (final GdxRuntimeException exr) {
                ex.printStackTrace();
            }
        }
        return this.lSounds.size() - 1;
    }
    
    public final int addSoundSFXRandom(final String fileName) {
        try {
            this.lSoundsRandom.add(Gdx.audio.newSound(FileManager.loadFile("audio/random/" + fileName)));
        }
        catch (final GdxRuntimeException ex) {
            ex.printStackTrace();
            try {
                this.lSoundsRandom.add(Gdx.audio.newSound(Gdx.files.local("audio/random/" + fileName)));
            }
            catch (final GdxRuntimeException exr) {
                ex.printStackTrace();
            }
        }
        return this.lSoundsRandom.size() - 1;
    }
    
    public final void playSound(final int id) {
        try {
            if (id >= 0) {
                this.playSound(id, 1.0f);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void playSound(final int id, final float fPercOfVolume) {
        try {
            this.lSounds.get(id).stop();
            this.lSounds.get(id).play(SoundsManager.soundsVolume * SoundsManager.masterVolume * fPercOfVolume);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void stopLegacySound() {
        try {
            this.lSounds.get(SoundsManager.LEGACY_0).stop();
            this.lSounds.get(SoundsManager.LEGACY_1).stop();
            this.lSounds.get(SoundsManager.LEGACY_2).stop();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final String getCurrentMusicTittle() {
        return this.lTitles.get(this.iCurrentMusicID).substring(0, (this.lTitles.get(this.iCurrentMusicID).indexOf("." + getFileExtension()) > 0) ? this.lTitles.get(this.iCurrentMusicID).indexOf("." + getFileExtension()) : this.lTitles.get(this.iCurrentMusicID).length()).replace("_", " ");
    }
    
    public final void setMusicVolume(final float nMusicVolume) {
        SoundsManager.musicVolume = nMusicVolume;
        try {
            if (this.currentMusic != null) {
                this.currentMusic.setVolume(SoundsManager.musicVolume * SoundsManager.masterVolume);
                if (SoundsManager.musicVolume < 0.01f) {
                    this.currentMusic.pause();
                }
                else if (!this.currentMusic.isPlaying()) {
                    this.currentMusic.play();
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    public final float getMusicVolume() {
        return SoundsManager.musicVolume;
    }
    
    public final void setSoundsVolume(final float soundsVolume) {
        SoundsManager.soundsVolume = soundsVolume;
    }
    
    public final void setAmbienceVolume(final float ambienceVolume) {
        SoundsManager.ambienceVolume = ambienceVolume;
    }
    
    public final float getSoundsVolume() {
        return SoundsManager.soundsVolume;
    }
    
    public final float getSoundsVolumeMaster() {
        return SoundsManager.soundsVolume * SoundsManager.masterVolume;
    }
    
    public final void setMasterVolume(final float masterVolume) {
        SoundsManager.masterVolume = masterVolume;
        this.setMusicVolume(this.getMusicVolume());
    }
    
    public final float getMasterVolume() {
        return SoundsManager.masterVolume;
    }
    
    public final void dispose() {
        for (int i = 0; i < this.lSounds.size(); ++i) {
            this.lSounds.get(i).dispose();
        }
        this.currentMusic.dispose();
    }
    
    public static int getClickSound_CivOptions() {
        switch (SoundsManager.iCivOptionsSound++ % 2) {
            case 0: {
                return SoundsManager.CIV_OPTIONS_CLICK;
            }
            default: {
                return SoundsManager.CIV_OPTIONS_CLICK1;
            }
        }
    }
    
    public static int getGrowthRate() {
        switch (SoundsManager.iGrowthRate++ % 2) {
            case 0: {
                return SoundsManager.SOUND_GROWTH_RATE;
            }
            default: {
                return SoundsManager.SOUND_GROWTH_RATE2;
            }
        }
    }
    
    public void playHover() {
        if (CFG.currentTimeMillis - this.lHoverTime > 36L) {
            this.lHoverTime = CFG.currentTimeMillis;
            switch (this.iHoverID++ % 2) {
                case 0: {
                    this.playSound(SoundsManager.SOUND_HOVER_0, this.hoverVolume);
                    break;
                }
                case 1: {
                    this.playSound(SoundsManager.SOUND_HOVER_1, this.hoverVolume);
                    break;
                }
                case 2: {
                    this.playSound(SoundsManager.SOUND_HOVER_2, this.hoverVolume);
                    break;
                }
                case 3: {
                    this.playSound(SoundsManager.SOUND_HOVER_3, this.hoverVolume);
                    break;
                }
                case 4: {
                    this.playSound(SoundsManager.SOUND_HOVER_4, this.hoverVolume);
                    break;
                }
                case 5: {
                    this.playSound(SoundsManager.SOUND_HOVER_5, this.hoverVolume);
                    break;
                }
                case 6: {
                    this.playSound(SoundsManager.SOUND_HOVER_6, this.hoverVolume);
                    break;
                }
                case 7: {
                    this.playSound(SoundsManager.SOUND_HOVER_7, this.hoverVolume);
                    break;
                }
                default: {
                    this.playSound(SoundsManager.SOUND_HOVER_8, this.hoverVolume);
                    break;
                }
            }
        }
    }
    
    public void playRandomSounds() {
        try {
            if (this.SOUNDS_RANDOM_TIME < CFG.currentTimeMillis && this.soundsRandomSize > 0) {
                final int id = Game.oR.nextInt(this.soundsRandomSize);
                this.lSoundsRandom.get(id).stop();
                this.lSoundsRandom.get(id).play(SoundsManager.soundsVolume * SoundsManager.masterVolume * 0.75f);
                this.updateSoundsRandomTime();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void updateSoundsRandomTime() {
        this.SOUNDS_RANDOM_TIME = CFG.currentTimeMillis + GameValues.inGame.SOUNDS_RANDOM_MIN + Game.oR.nextInt(GameValues.inGame.SOUNDS_RANDOM_RANDOM);
    }
    
    public void playSelectedArmy() {
        switch (this.iSelectArmy++ % 2) {
            case 0: {
                this.playSound(SoundsManager.SOUND_SELECTED_ARMY_0);
                break;
            }
            default: {
                this.playSound(SoundsManager.SOUND_SELECTED_ARMY_1);
                break;
            }
        }
    }
    
    public int getSelectedArmy() {
        switch (this.iSelectArmy++ % 2) {
            case 0: {
                return SoundsManager.SOUND_SELECTED_ARMY_0;
            }
            default: {
                return SoundsManager.SOUND_SELECTED_ARMY_1;
            }
        }
    }
    
    public void playRecruitArmy() {
        if (CFG.currentTimeMillis - this.lRecruitTime > 250L) {
            this.lRecruitTime = CFG.currentTimeMillis;
            switch (this.iRecruitArmy++ % 2) {
                case 0: {
                    this.playSound(SoundsManager.SOUND_RECRUIT_ARMY_0);
                    break;
                }
                default: {
                    this.playSound(SoundsManager.SOUND_RECRUIT_ARMY_1);
                    break;
                }
            }
        }
    }
    
    public int getRecruitArmy() {
        switch (this.iRecruitArmy++ % 2) {
            case 0: {
                return SoundsManager.SOUND_RECRUIT_ARMY_0;
            }
            default: {
                return SoundsManager.SOUND_RECRUIT_ARMY_1;
            }
        }
    }
    
    public void playRecruitArmyCancel() {
        this.playSound(SoundsManager.SOUND_RECRUIT_CANCEL);
    }
    
    public void playMove() {
        switch (Game.oR.nextInt(5)) {
            case 0: {
                this.playSound(SoundsManager.MOVE_0);
                break;
            }
            case 1: {
                this.playSound(SoundsManager.MOVE_1);
                break;
            }
            case 2: {
                this.playSound(SoundsManager.MOVE_2);
                break;
            }
            case 3: {
                this.playSound(SoundsManager.MOVE_3);
                break;
            }
            default: {
                this.playSound(SoundsManager.MOVE_4);
                break;
            }
        }
    }
    
    public void playGold() {
        switch (Game.oR.nextInt(5)) {
            case 0: {
                this.playSound(SoundsManager.SOUND_GOLD_0);
                break;
            }
            case 1: {
                this.playSound(SoundsManager.SOUND_GOLD_1);
                break;
            }
            case 2: {
                this.playSound(SoundsManager.SOUND_GOLD_2);
                break;
            }
            case 3: {
                this.playSound(SoundsManager.SOUND_GOLD_3);
                break;
            }
            default: {
                this.playSound(SoundsManager.SOUND_GOLD_4);
                break;
            }
        }
    }
    
    public int getGold() {
        switch (Game.oR.nextInt(5)) {
            case 0: {
                return SoundsManager.SOUND_GOLD_0;
            }
            case 1: {
                return SoundsManager.SOUND_GOLD_1;
            }
            case 2: {
                return SoundsManager.SOUND_GOLD_2;
            }
            case 3: {
                return SoundsManager.SOUND_GOLD_3;
            }
            default: {
                return SoundsManager.SOUND_GOLD_4;
            }
        }
    }
    
    public int getTab() {
        this.tabNum = (this.tabNum + 1) % 2;
        switch (this.tabNum % 2) {
            case 0: {
                return SoundsManager.TAB_0;
            }
            default: {
                return SoundsManager.TAB_1;
            }
        }
    }
    
    public int getBuild() {
        switch (Game.oR.nextInt(2)) {
            case 0: {
                return SoundsManager.BUILD0;
            }
            default: {
                return SoundsManager.BUILD1;
            }
        }
    }
    
    public int getDiplomacy() {
        switch (this.iDiplomacyButton++ % 2) {
            case 0: {
                return SoundsManager.DIPLOMACY0;
            }
            default: {
                return SoundsManager.DIPLOMACY1;
            }
        }
    }
    
    public int getCoin() {
        switch (Game.oR.nextInt(5)) {
            case 0: {
                return SoundsManager.SOUND_COIN_0;
            }
            case 1: {
                return SoundsManager.SOUND_COIN_1;
            }
            default: {
                return SoundsManager.SOUND_COIN_2;
            }
        }
    }
    
    public int getEconomy() {
        switch (this.iEconomy++ % 2) {
            case 0: {
                return SoundsManager.SOUND_ECONOMY_0;
            }
            default: {
                return SoundsManager.SOUND_ECONOMY_1;
            }
        }
    }
    
    public int getInfrastructure() {
        switch (Game.oR.nextInt(2)) {
            case 0: {
                return SoundsManager.SOUND_INFRASTRUCTURE;
            }
            default: {
                return SoundsManager.SOUND_INFRASTRUCTURE_1;
            }
        }
    }
    
    public int getClickMain() {
        switch (Game.oR.nextInt(2)) {
            case 0: {
                return SoundsManager.SOUND_CLICK_MAIN;
            }
            default: {
                return SoundsManager.SOUND_CLICK_MAIN2;
            }
        }
    }
    
    public int getClickIncreaseManpower() {
        switch (this.iIncreaseManpower++ % 2) {
            case 0: {
                return SoundsManager.SOUND_INCREASE_MANPOWER;
            }
            default: {
                return SoundsManager.SOUND_INCREASE_MANPOWER2;
            }
        }
    }
    
    static {
        SoundsManager.masterVolume = 1.0f;
        SoundsManager.musicVolume = 0.4f;
        SoundsManager.soundsVolume = 0.2f;
        SoundsManager.ambienceVolume = 0.2f;
        SoundsManager.PERC_VOLUME_SELECT_PROVINCE = 0.95f;
        SoundsManager.PERC_VOLUME_KEYBOARD = 0.9f;
        SoundsManager.isWarMusicPlaying = false;
        SoundsManager.iCivOptionsSound = 0;
        SoundsManager.iGrowthRate = 0;
    }
}
