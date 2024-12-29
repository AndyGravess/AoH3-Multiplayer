// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Missions;

import aoc.kingdoms.lukasz.events.EventOption;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class MissionTree
{
    public static final int MISSION_TYPE_ID = 999;
    public static final int MISSION_TYPE_ID_CIV = 1000;
    public static List<Mission> lMissions;
    public static int iMissionsSize;
    public static List<Image> missionImages;
    public static List<String> missionImagesNames;
    public static List<Image> missionImagesCivs;
    public static List<String> missionImagesNamesCivs;
    public static int iMissionWidth;
    public static int iMissionHeight;
    
    public static final void loadMissions() {
        try {
            final FileHandle fileList = FileManager.loadFile("game/missions/Missions.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigMissionsData.class, "Mission", (Class)Mission.class);
            ConfigMissionsData data = (ConfigMissionsData)json.fromJson((Class)ConfigMissionsData.class, fileContent);
            for (final Object e : data.Mission) {
                try {
                    final Mission mission = (Mission)e;
                    mission.Name = Game.lang.get(mission.Name);
                    final FileHandle tempFileEvent = FileManager.loadFile("game/missions/missionsEvents/" + mission.MissionEvent);
                    mission.event = EventsManager.loadEvent(999, tempFileEvent.readString().split("\\r?\\n"));
                    MissionTree.lMissions.add(mission);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            data = null;
        }
        catch (final GdxRuntimeException ex2) {
            CFG.exceptionStack((Throwable)ex2);
        }
        MissionTree.iMissionsSize = MissionTree.lMissions.size();
        loadMissionsImages();
    }
    
    public static final void loadMissions_Civs() {
        disposeMissionImagesCivs();
        final List<Integer> loadCivsImages = new ArrayList<Integer>();
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (!FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + Game.getCiv(i).getCivTag() + ".json").exists()) {
                    if (!FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + Game.getCiv(i).realTag + ".json").exists()) {
                        continue;
                    }
                }
                try {
                    Game.getCiv(i).lMissions.clear();
                    Game.getCiv(i).iMissionsSize = 0;
                    FileHandle fileList;
                    if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + Game.getCiv(i).getCivTag() + ".json").exists()) {
                        fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + Game.getCiv(i).getCivTag() + ".json");
                    }
                    else {
                        fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + Game.getCiv(i).realTag + ".json");
                    }
                    final String fileContent = fileList.readString();
                    final Json json = new Json();
                    json.setElementType((Class)ConfigMissionsData.class, "Mission", (Class)Mission.class);
                    ConfigMissionsData data = (ConfigMissionsData)json.fromJson((Class)ConfigMissionsData.class, fileContent);
                    for (final Object e : data.Mission) {
                        try {
                            final Mission mission = (Mission)e;
                            mission.Name = Game.lang.get(mission.Name);
                            FileHandle tempFileEvent;
                            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + "missionsEvents/" + mission.MissionEvent).exists()) {
                                tempFileEvent = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + "missionsEvents/" + mission.MissionEvent);
                            }
                            else {
                                tempFileEvent = FileManager.loadFile("game/missions/missionsEvents/" + mission.MissionEvent);
                            }
                            mission.event = EventsManager.loadEvent(999, tempFileEvent.readString().split("\\r?\\n"));
                            Game.getCiv(i).lMissions.add(mission);
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    data = null;
                }
                catch (final GdxRuntimeException ex2) {
                    CFG.exceptionStack((Throwable)ex2);
                }
                loadCivsImages.add(i);
                Game.getCiv(i).iMissionsSize = Game.getCiv(i).lMissions.size();
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        for (int i = loadCivsImages.size() - 1; i >= 0; --i) {
            loadMissionsImagesCiv(loadCivsImages.get(i));
        }
        loadCivsImages.clear();
    }
    
    public static final void loadMissionsImages() {
        for (int i = 0; i < MissionTree.iMissionsSize; ++i) {
            boolean addImage = true;
            for (int j = MissionTree.missionImagesNames.size() - 1; j >= 0; --j) {
                if (MissionTree.missionImagesNames.get(j).equals(MissionTree.lMissions.get(i).ImageName)) {
                    MissionTree.lMissions.get(i).ImageID = j;
                    addImage = false;
                    break;
                }
            }
            if (addImage) {
                if (FileManager.loadFile("game/missions/missionsImages/" + CFG.getRescouresPath_Short() + MissionTree.lMissions.get(i).ImageName).exists()) {
                    MissionTree.missionImages.add(new Image(ImageManager.loadTexture("game/missions/missionsImages/" + CFG.getRescouresPath_Short() + MissionTree.lMissions.get(i).ImageName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else {
                    MissionTree.missionImages.add(new Image(ImageManager.loadTexture("game/missions/missionsImages/" + CFG.getRescouresPath_Short_H() + MissionTree.lMissions.get(i).ImageName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                MissionTree.missionImagesNames.add(MissionTree.lMissions.get(i).ImageName);
                MissionTree.lMissions.get(i).ImageID = MissionTree.missionImages.size() - 1;
            }
        }
        MissionTree.iMissionWidth = ImageManager.getImage(Images.missionMask).getWidth();
        MissionTree.iMissionHeight = ImageManager.getImage(Images.missionMask).getHeight();
    }
    
    public static final void loadMissionsImagesCiv(final int civID) {
        for (int i = 0; i < Game.getCiv(civID).iMissionsSize; ++i) {
            boolean addImage = true;
            for (int j = MissionTree.missionImagesNamesCivs.size() - 1; j >= 0; --j) {
                if (MissionTree.missionImagesNamesCivs.get(j).equals(Game.getCiv(civID).lMissions.get(i).ImageName)) {
                    Game.getCiv(civID).lMissions.get(i).ImageID = j;
                    addImage = false;
                    break;
                }
            }
            if (addImage) {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + "missionsImages/" + CFG.getRescouresPath_Short() + Game.getCiv(civID).lMissions.get(i).ImageName).exists()) {
                    MissionTree.missionImagesCivs.add(new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + "missionsImages/" + CFG.getRescouresPath_Short() + Game.getCiv(civID).lMissions.get(i).ImageName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + "missionsImages/" + CFG.getRescouresPath_Short_H() + Game.getCiv(civID).lMissions.get(i).ImageName).exists()) {
                    MissionTree.missionImagesCivs.add(new Image(ImageManager.loadTexture("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/" + "missions/" + "missionsImages/" + CFG.getRescouresPath_Short_H() + Game.getCiv(civID).lMissions.get(i).ImageName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else if (FileManager.loadFile("game/missions/missionsImages/" + CFG.getRescouresPath_Short() + Game.getCiv(civID).lMissions.get(i).ImageName).exists()) {
                    MissionTree.missionImagesCivs.add(new Image(ImageManager.loadTexture("game/missions/missionsImages/" + CFG.getRescouresPath_Short() + Game.getCiv(civID).lMissions.get(i).ImageName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else {
                    MissionTree.missionImagesCivs.add(new Image(ImageManager.loadTexture("game/missions/missionsImages/" + CFG.getRescouresPath_Short_H() + Game.getCiv(civID).lMissions.get(i).ImageName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                MissionTree.missionImagesNamesCivs.add(Game.getCiv(civID).lMissions.get(i).ImageName);
                Game.getCiv(civID).lMissions.get(i).ImageID = MissionTree.missionImagesCivs.size() - 1;
            }
        }
    }
    
    public static void disposeMissionImagesCivs() {
        for (int i = MissionTree.missionImagesCivs.size() - 1; i >= 0; --i) {
            MissionTree.missionImagesCivs.get(i).getTexture().dispose();
        }
        MissionTree.missionImagesNamesCivs.clear();
        MissionTree.missionImagesCivs.clear();
    }
    
    public static final boolean haveUnlockedMission(final int civID, final int missionID) {
        return Game.getCiv(civID).eventsDataVariables.hasVariable(MissionTree.lMissions.get(missionID).event.id);
    }
    
    public static final boolean haveUnlockedMission_Civ(final int civID, final int missionID) {
        return Game.getCiv(civID).eventsDataVariables.hasVariable(Game.getCiv(civID).lMissions.get(missionID).event.id);
    }
    
    public static final boolean canRunMission_PreviousMissions(final int civID, final int missionID) {
        return (MissionTree.lMissions.get(missionID).RequiredMission < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(MissionTree.lMissions.get(MissionTree.lMissions.get(missionID).RequiredMission).event.id)) && (MissionTree.lMissions.get(missionID).RequiredMission2 < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(MissionTree.lMissions.get(MissionTree.lMissions.get(missionID).RequiredMission2).event.id));
    }
    
    public static final boolean canRunMission_PreviousMissions_Civ(final int civID, final int missionID) {
        return (Game.getCiv(civID).lMissions.get(missionID).RequiredMission < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(Game.getCiv(civID).lMissions.get(Game.getCiv(civID).lMissions.get(missionID).RequiredMission).event.id)) && (Game.getCiv(civID).lMissions.get(missionID).RequiredMission2 < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(Game.getCiv(civID).lMissions.get(Game.getCiv(civID).lMissions.get(missionID).RequiredMission2).event.id));
    }
    
    public static final boolean canRunMission(final int civID, final int missionID) {
        return (MissionTree.lMissions.get(missionID).RequiredMission < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(MissionTree.lMissions.get(MissionTree.lMissions.get(missionID).RequiredMission).event.id)) && (MissionTree.lMissions.get(missionID).RequiredMission2 < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(MissionTree.lMissions.get(MissionTree.lMissions.get(missionID).RequiredMission2).event.id)) && !Game.getCiv(civID).eventsDataVariables.hasVariable(MissionTree.lMissions.get(missionID).event.id) && MissionTree.lMissions.get(missionID).event.runTriggers(civID);
    }
    
    public static final boolean canRunMission_Civ(final int civID, final int missionID) {
        return (Game.getCiv(civID).lMissions.get(missionID).RequiredMission < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(Game.getCiv(civID).lMissions.get(Game.getCiv(civID).lMissions.get(missionID).RequiredMission).event.id)) && (Game.getCiv(civID).lMissions.get(missionID).RequiredMission2 < 0 || Game.getCiv(civID).eventsDataVariables.hasVariable(Game.getCiv(civID).lMissions.get(Game.getCiv(civID).lMissions.get(missionID).RequiredMission2).event.id)) && !Game.getCiv(civID).eventsDataVariables.hasVariable(Game.getCiv(civID).lMissions.get(missionID).event.id) && Game.getCiv(civID).lMissions.get(missionID).event.runTriggers(civID);
    }
    
    public static final void runMission(final int civID, final int missionID) {
        Game.player.addActiveEvent(999, missionID, 0);
        Game.addSimpleTask(new Game.SimpleTask("" + MissionTree.lMissions.get(missionID).event.id + 999, missionID) {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_Right();
                Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                Game.menuManager.rebuildInGame_Event(MissionTree.lMissions.get(this.id).event, 999, this.id);
                Game.soundsManager.playSound(SoundsManager.EVENT);
            }
        });
    }
    
    public static final void runMission_Civ(final int civID, final int missionID) {
        Game.player.addActiveEvent(1000, missionID, 0);
        Game.addSimpleTask(new Game.SimpleTask("" + Game.getCiv(Game.player.iCivID).lMissions.get(missionID).event.id + 1000, missionID) {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_Right();
                Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                Game.menuManager.rebuildInGame_Event(Game.getCiv(Game.player.iCivID).lMissions.get(this.id).event, 1000, this.id);
                Game.soundsManager.playSound(SoundsManager.EVENT);
            }
        });
    }
    
    public static final void takeMissionDecision(final int iCivID, final int missionID, final int optionID) {
        try {
            Game.getCiv(iCivID).eventsDataVariables.addVariable(MissionTree.lMissions.get(missionID).event.id);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            MissionTree.lMissions.get(missionID).event.options.get(optionID).executeOutcome(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void takeMissionDecision_Civ(final int iCivID, final int missionID, final int optionID) {
        try {
            Game.getCiv(iCivID).eventsDataVariables.addVariable(Game.getCiv(iCivID).lMissions.get(missionID).event.id);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            Game.getCiv(iCivID).lMissions.get(missionID).event.options.get(optionID).executeOutcome(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        MissionTree.lMissions = new ArrayList<Mission>();
        MissionTree.iMissionsSize = 0;
        MissionTree.missionImages = new ArrayList<Image>();
        MissionTree.missionImagesNames = new ArrayList<String>();
        MissionTree.missionImagesCivs = new ArrayList<Image>();
        MissionTree.missionImagesNamesCivs = new ArrayList<String>();
    }
    
    public static class ConfigMissionsData
    {
        public String Age_of_History;
        public ArrayList Mission;
    }
}
