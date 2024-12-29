// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Steam;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.codedisaster.steamworks.SteamUGCUpdateHandle;
import com.badlogic.gdx.utils.Json;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamUserStatsCallback;
import com.codedisaster.steamworks.SteamUtilsCallback;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_Load_Workshop;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCHandle;
import com.codedisaster.steamworks.SteamRemoteStorageCallback;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamAPI;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.codedisaster.steamworks.SteamUserStats;
import com.codedisaster.steamworks.SteamUtils;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamRemoteStorage;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamUGC;
import java.util.List;

public class SteamManager
{
    public static final int APP_ID = 2772750;
    public static boolean initSteam;
    public static List<String> modsFolders;
    public static int modsFoldersSize;
    public static List<SteamUGC.ItemInstallInfo> itemsInstalled;
    public static int itemsInstalledSize;
    public static List<String> modsFoldersAll;
    public static List<String> modsFoldersAll_ModName;
    public static List<SteamUGC.ItemInstallInfo> itemsInstalledAll;
    public static List<String> modsTurnedOff;
    public static SteamPublishedFileID createItem_steamPublishedFileID;
    public static SteamRemoteStorage steamRemoteStorage;
    public static SteamUGC steamUGC;
    public static SteamUGCCallback steamUGCCallback;
    public static SteamUtils steamUtils;
    public static SteamUserStats userStats;
    public static boolean DONE;
    
    public static void loadSubscribedItems() {
        try {
            if (SteamManager.initSteam && CFG.isDesktop()) {
                final SteamPublishedFileID[] steamPublishedFileIDS = new SteamPublishedFileID[SteamManager.steamUGC.getNumSubscribedItems()];
                final int numSubscribed = SteamManager.steamUGC.getSubscribedItems(steamPublishedFileIDS);
                if (steamPublishedFileIDS != null) {
                    for (int i = 0; i < steamPublishedFileIDS.length; ++i) {
                        final SteamUGC.ItemInstallInfo itemInstallInfo = new SteamUGC.ItemInstallInfo();
                        SteamManager.steamUGC.getItemInstallInfo(steamPublishedFileIDS[i], itemInstallInfo);
                        SteamManager.itemsInstalled.add(itemInstallInfo);
                    }
                    SteamManager.itemsInstalledSize = SteamManager.itemsInstalled.size();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            FileHandle[] files;
            if (FileManager.IS_MAC) {
                files = Gdx.files.external("mods/").list();
            }
            else {
                files = Gdx.files.local("mods/").list();
            }
            for (final FileHandle file : files) {
                SteamManager.modsFolders.add("mods/" + file.name() + "/");
            }
            SteamManager.modsFoldersSize = SteamManager.modsFolders.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        readModsTurnedOff();
    }
    
    public static final void updateSteam_runCallbacks() {
        if (SteamAPI.isSteamRunning()) {
            SteamAPI.runCallbacks();
        }
    }
    
    public static final void init() {
        if (!SteamManager.initSteam || !CFG.isDesktop()) {
            return;
        }
        if (SteamManager.steamUGC == null) {
            try {
                SteamAPI.loadLibraries();
                if (SteamAPI.init() || SteamAPI.restartAppIfNecessary(2772750)) {}
            }
            catch (final SteamException ex) {
                ex.printStackTrace();
            }
            SteamManager.steamRemoteStorage = new SteamRemoteStorage((SteamRemoteStorageCallback)new SteamRemoteStorageCallback() {
                public void onFileShareResult(final SteamUGCHandle steamUGCHandle, final String s, final SteamResult steamResult) {
                    Gdx.app.log("SteamRemoteStorage", "onFileShareResult");
                }
                
                public void onDownloadUGCResult(final SteamUGCHandle steamUGCHandle, final SteamResult steamResult) {
                    Gdx.app.log("SteamRemoteStorage", "onDownloadUGCResult");
                }
                
                public void onPublishFileResult(final SteamPublishedFileID steamPublishedFileID, final boolean b, final SteamResult steamResult) {
                    Gdx.app.log("SteamRemoteStorage", "onPublishFileResult");
                }
                
                public void onUpdatePublishedFileResult(final SteamPublishedFileID steamPublishedFileID, final boolean b, final SteamResult steamResult) {
                    Gdx.app.log("SteamRemoteStorage", "onUpdatePublishedFileResult");
                }
                
                public void onPublishedFileSubscribed(final SteamPublishedFileID steamPublishedFileID, final int i) {
                    Gdx.app.log("SteamRemoteStorage", "onPublishedFileSubscribed");
                }
                
                public void onPublishedFileUnsubscribed(final SteamPublishedFileID steamPublishedFileID, final int i) {
                    Gdx.app.log("SteamRemoteStorage", "onPublishedFileUnsubscribed");
                }
                
                public void onPublishedFileDeleted(final SteamPublishedFileID steamPublishedFileID, final int i) {
                    Gdx.app.log("SteamRemoteStorage", "onPublishedFileDeleted");
                }
                
                public void onFileWriteAsyncComplete(final SteamResult steamResult) {
                    Gdx.app.log("SteamRemoteStorage", "onFileWriteAsyncComplete");
                }
                
                public void onFileReadAsyncComplete(final SteamAPICall steamAPICall, final SteamResult steamResult, final int i, final int i1) {
                    Gdx.app.log("SteamRemoteStorage", "onFileReadAsyncComplete");
                }
            });
            SteamManager.steamUGCCallback = (SteamUGCCallback)new SteamUGCCallback() {
                public void onUGCQueryCompleted(final SteamUGCQuery steamUGCQuery, final int i, final int i1, final boolean b, final SteamResult steamResult) {
                    Gdx.app.log("onUGCQueryCompleted", "onUGCQueryCompleted");
                }
                
                public void onSubscribeItem(final SteamPublishedFileID steamPublishedFileID, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onSubscribeItem");
                }
                
                public void onUnsubscribeItem(final SteamPublishedFileID steamPublishedFileID, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onUnsubscribeItem");
                }
                
                public void onRequestUGCDetails(final SteamUGCDetails steamUGCDetails, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onRequestUGCDetails");
                }
                
                public void onCreateItem(final SteamPublishedFileID steamPublishedFileID, final boolean b, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onCreateItem");
                    SteamManager.createItem_steamPublishedFileID = steamPublishedFileID;
                    if (steamResult == SteamResult.OK) {
                        Game.menuManager.addToastGold(Game.lang.get("UploadedSuccessfully"), Images.technology2, 60000);
                    }
                    else {
                        Game.menuManager.addToastGold("Create: " + Game.lang.get("Error"), Images.technology2, 60000);
                    }
                    SteamManager.DONE = true;
                }
                
                public void onSubmitItemUpdate(final SteamPublishedFileID steamPublishedFileID, final boolean b, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onSubmitItemUpdate");
                    if (steamResult == SteamResult.OK) {
                        Game.menuManager.addToastGold(Game.lang.get("UploadedSuccessfully"), Images.technology2, 60000);
                    }
                    else {
                        Game.menuManager.addToastGold("Update" + Game.lang.get("Error") + ": " + steamResult.name(), Images.technology2, 60000);
                    }
                    SteamManager.DONE = true;
                    Menu_Load_Workshop.uploaded = true;
                }
                
                public void onDownloadItemResult(final int i, final SteamPublishedFileID steamPublishedFileID, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onDownloadItemResult");
                }
                
                public void onUserFavoriteItemsListChanged(final SteamPublishedFileID steamPublishedFileID, final boolean b, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onUserFavoriteItemsListChanged");
                }
                
                public void onSetUserItemVote(final SteamPublishedFileID steamPublishedFileID, final boolean b, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onSetUserItemVote");
                }
                
                public void onGetUserItemVote(final SteamPublishedFileID steamPublishedFileID, final boolean b, final boolean b1, final boolean b2, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onGetUserItemVote");
                }
                
                public void onStartPlaytimeTracking(final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onStartPlaytimeTracking");
                }
                
                public void onStopPlaytimeTracking(final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onStopPlaytimeTracking");
                }
                
                public void onStopPlaytimeTrackingForAllItems(final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onStopPlaytimeTrackingForAllItems");
                }
                
                public void onDeleteItem(final SteamPublishedFileID steamPublishedFileID, final SteamResult steamResult) {
                    Gdx.app.log("SteamUGC", "onDeleteItem");
                }
            };
            SteamManager.steamUGC = new SteamUGC(SteamManager.steamUGCCallback);
            SteamManager.steamUtils = new SteamUtils((SteamUtilsCallback)new SteamUtilsCallback() {
                public void onSteamShutdown() {
                    Gdx.app.log("SteamUtils", "onSteamShutdown");
                    SteamAPI.shutdown();
                }
            });
            SteamManager.userStats = new SteamUserStats((SteamUserStatsCallback)new SteamUserStatsCallback() {
                public void onUserStatsReceived(final long gameId, final SteamID steamIDUser, final SteamResult result) {
                }
                
                public void onUserStatsStored(final long gameId, final SteamResult result) {
                }
                
                public void onUserStatsUnloaded(final SteamID steamIDUser) {
                }
                
                public void onUserAchievementStored(final long gameId, final boolean isGroupAchievement, final String achievementName, final int curProgress, final int maxProgress) {
                }
                
                public void onLeaderboardFindResult(final SteamLeaderboardHandle leaderboard, final boolean found) {
                }
                
                public void onLeaderboardScoresDownloaded(final SteamLeaderboardHandle leaderboard, final SteamLeaderboardEntriesHandle entries, final int numEntries) {
                }
                
                public void onLeaderboardScoreUploaded(final boolean success, final SteamLeaderboardHandle leaderboard, final int score, final boolean scoreChanged, final int globalRankNew, final int globalRankPrevious) {
                }
                
                public void onNumberOfCurrentPlayersReceived(final boolean success, final int players) {
                }
                
                public void onGlobalStatsReceived(final long gameId, final SteamResult result) {
                }
            });
        }
    }
    
    public static void createItem(final String modFolder) {
        try {
            SteamManager.DONE = false;
            CFG.LOG("mods/" + modFolder + "/id.txt");
            if (FileManager.IS_MAC && Gdx.files.external("mods/" + modFolder + "/id.txt").exists()) {
                final FileHandle fileList = Gdx.files.external("mods/" + modFolder + "/id.txt");
                final String fileContent = fileList.readString();
                SteamManager.createItem_steamPublishedFileID = new SteamPublishedFileID(Long.parseLong(fileContent));
            }
            else if (Gdx.files.internal("mods/" + modFolder + "/id.txt").exists()) {
                final FileHandle fileList = Gdx.files.internal("mods/" + modFolder + "/id.txt");
                final String fileContent = fileList.readString();
                SteamManager.createItem_steamPublishedFileID = new SteamPublishedFileID(Long.parseLong(fileContent));
            }
            else {
                final SteamAPICall steamAPICall = SteamManager.steamUGC.createItem(2772750, SteamRemoteStorage.WorkshopFileType.Community);
                CFG.LOG("createItem", "steamAPICall.isValid: " + steamAPICall.isValid());
                while (!SteamManager.DONE) {
                    SteamAPI.runCallbacks();
                    try {
                        Thread.sleep(250L);
                        continue;
                    }
                    catch (final InterruptedException e) {
                        throw new SteamException((Throwable)e);
                    }
                    break;
                }
                try {
                    final FileHandle fileSave = FileManager.getSaveType("mods/" + modFolder + "/id.txt");
                    fileSave.writeString("" + Long.parseLong(SteamManager.createItem_steamPublishedFileID.toString(), 16), false);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            final Json json = new Json();
            FileHandle file;
            if (FileManager.IS_MAC) {
                file = Gdx.files.external("mods/" + modFolder + "/mod.txt");
            }
            else {
                file = Gdx.files.internal("mods/" + modFolder + "/mod.txt");
            }
            final ModData modData = (ModData)json.fromJson((Class)ModData.class, file);
            final SteamUGCUpdateHandle updateHandle = SteamManager.steamUGC.startItemUpdate(2772750, SteamManager.createItem_steamPublishedFileID);
            final String nPath = new FileHandle("mods/" + modFolder + "/").file().getAbsolutePath();
            Gdx.app.log("nPath", "nPath: " + nPath);
            SteamManager.steamUGC.setItemContent(updateHandle, nPath);
            CFG.LOG("modData.Name: " + modData.Name);
            SteamManager.steamUGC.setItemTitle(updateHandle, modData.Name);
            SteamManager.steamUGC.setItemTags(updateHandle, modData.Tags);
            SteamManager.steamUGC.setItemDescription(updateHandle, modData.Description);
            CFG.LOG(new FileHandle("mods/" + modFolder + "/logo.png").file().getAbsolutePath());
            SteamManager.steamUGC.setItemPreview(updateHandle, new FileHandle("mods/" + modFolder + "/logo.png").file().getAbsolutePath());
            SteamManager.steamUGC.setItemVisibility(updateHandle, SteamRemoteStorage.PublishedFileVisibility.Public);
            SteamManager.steamUGC.submitItemUpdate(updateHandle, modData.ChangeNote);
            final SteamUGC.ItemUpdateInfo updateInfo = new SteamUGC.ItemUpdateInfo();
            SteamManager.steamUGC.getItemUpdateProgress(updateHandle, updateInfo);
            SteamManager.DONE = false;
            while (!SteamManager.DONE) {
                SteamAPI.runCallbacks();
                SteamManager.steamUGC.getItemUpdateProgress(updateHandle, updateInfo);
                Gdx.app.log("Progress", "Progress: " + updateInfo.getBytesProcessed() + "/" + updateInfo.getBytesTotal());
                try {
                    Thread.sleep(250L);
                    continue;
                }
                catch (final InterruptedException e2) {
                    throw new SteamException((Throwable)e2);
                }
                break;
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static void addModsTurnedOff(final String folder) {
        if (folder != null && folder.length() > 0) {
            if (!SteamManager.modsTurnedOff.contains(folder)) {
                SteamManager.modsTurnedOff.add(folder);
            }
            else {
                removeModsTurnedOff(folder);
            }
            saveModsTurnedOff();
        }
    }
    
    public static void removeModsTurnedOff(final String folder) {
        if (folder.length() > 0) {
            for (int i = SteamManager.modsTurnedOff.size() - 1; i >= 0; --i) {
                if (SteamManager.modsTurnedOff.get(i).equals(folder)) {
                    SteamManager.modsTurnedOff.remove(i);
                    saveModsTurnedOff();
                    return;
                }
            }
        }
    }
    
    public static boolean isTurnedOn(final String folder) {
        if (folder != null && folder.length() > 0) {
            for (int i = SteamManager.modsTurnedOff.size() - 1; i >= 0; --i) {
                if (SteamManager.modsTurnedOff.get(i).equals(folder)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void saveModsTurnedOff() {
        final FileHandle fileSave = FileManager.getSaveType("settings/ModsOff.txt");
        if (SteamManager.modsTurnedOff.isEmpty()) {
            if (FileManager.getSaveType("settings/ModsOff.txt").exists()) {
                FileManager.getSaveType("settings/ModsOff.txt").delete();
            }
        }
        else {
            for (int i = 0; i < SteamManager.modsTurnedOff.size(); ++i) {
                fileSave.writeString(SteamManager.modsTurnedOff.get(i) + ";", i != 0);
            }
        }
    }
    
    public static void readModsTurnedOff() {
        for (int j = 0; j < SteamManager.modsFolders.size(); ++j) {
            SteamManager.modsFoldersAll.add(SteamManager.modsFolders.get(j));
        }
        for (int j = 0; j < SteamManager.itemsInstalled.size(); ++j) {
            SteamManager.itemsInstalledAll.add(SteamManager.itemsInstalled.get(j));
            try {
                if (Gdx.files.absolute(SteamManager.itemsInstalled.get(j).getFolder() + "/mod.txt").exists()) {
                    final FileHandle file = Gdx.files.absolute(SteamManager.itemsInstalled.get(j).getFolder() + "/mod.txt");
                    final String tempTags = file.readString();
                    final Pattern pattern = Pattern.compile("Name:\\s*\"(.*?)\"");
                    final Matcher matcher = pattern.matcher(tempTags);
                    if (matcher.find()) {
                        final String name = matcher.group(1);
                        SteamManager.modsFoldersAll_ModName.add(name);
                    }
                    else {
                        SteamManager.modsFoldersAll_ModName.add(SteamManager.itemsInstalled.get(j).getFolder());
                    }
                }
            }
            catch (final Exception ex) {
                SteamManager.modsFoldersAll_ModName.add(SteamManager.itemsInstalled.get(j).getFolder());
            }
        }
        try {
            if (Gdx.files.internal("settings/ModsOff.txt").exists() || (FileManager.IS_MAC && Gdx.files.external("settings/ModsOff.txt").exists())) {
                FileHandle file2;
                if (FileManager.IS_MAC) {
                    file2 = Gdx.files.external("settings/ModsOff.txt");
                }
                else {
                    file2 = Gdx.files.internal("settings/ModsOff.txt");
                }
                final String tempTags2 = file2.readString();
                SteamManager.modsTurnedOff.clear();
                final String[] split = tempTags2.split(";");
                for (int i = 0; i < split.length; ++i) {
                    SteamManager.modsTurnedOff.add(split[i]);
                }
                for (int i = 0; i < SteamManager.modsTurnedOff.size(); ++i) {
                    for (int k = SteamManager.modsFolders.size() - 1; k >= 0; --k) {
                        if (SteamManager.modsTurnedOff.get(i).equals(SteamManager.modsFolders.get(k))) {
                            SteamManager.modsFolders.remove(k);
                            break;
                        }
                    }
                    for (int k = SteamManager.itemsInstalled.size() - 1; k >= 0; --k) {
                        if (SteamManager.modsTurnedOff.get(i).equals(SteamManager.itemsInstalled.get(k).getFolder())) {
                            SteamManager.itemsInstalled.remove(k);
                            break;
                        }
                    }
                }
                SteamManager.modsFoldersSize = SteamManager.modsFolders.size();
                SteamManager.itemsInstalledSize = SteamManager.itemsInstalled.size();
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    static {
        SteamManager.initSteam = true;
        SteamManager.modsFolders = new ArrayList<String>();
        SteamManager.modsFoldersSize = 0;
        SteamManager.itemsInstalled = new ArrayList<SteamUGC.ItemInstallInfo>();
        SteamManager.itemsInstalledSize = 0;
        SteamManager.modsFoldersAll = new ArrayList<String>();
        SteamManager.modsFoldersAll_ModName = new ArrayList<String>();
        SteamManager.itemsInstalledAll = new ArrayList<SteamUGC.ItemInstallInfo>();
        SteamManager.modsTurnedOff = new ArrayList<String>();
        SteamManager.createItem_steamPublishedFileID = null;
        SteamManager.steamRemoteStorage = null;
        SteamManager.steamUGC = null;
        SteamManager.steamUGCCallback = null;
        SteamManager.steamUtils = null;
        SteamManager.userStats = null;
        SteamManager.DONE = false;
    }
    
    public static class ModData
    {
        public String Name;
        public String Description;
        public String[] Tags;
        public String ChangeNote;
    }
}
