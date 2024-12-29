// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Stats;

import java.util.ArrayList;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;

public class StatsManager
{
    public Stats civStats;
    
    public StatsManager() {
        this.civStats = new Stats("neu");
    }
    
    public void loadStats(final String tag, final boolean increaseGames) {
        try {
            if (FileManager.loadFile("statistics/" + tag + ".json").exists()) {
                try {
                    final FileHandle fileList = FileManager.loadFile("statistics/" + tag + ".json");
                    final Json json = new Json();
                    this.civStats = (Stats)json.fromJson((Class)Stats.class, fileList);
                    if (increaseGames) {
                        final Stats civStats = this.civStats;
                        ++civStats.ga;
                    }
                    return;
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.civStats = new Stats(tag);
    }
    
    public void saveStats() {
        if (Game.player.iCivID == 0 || Game.SPECTATOR_MODE || this.civStats.tg.equals("neu")) {
            return;
        }
        this.civStats.li = Math.max(this.civStats.li, (int)Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth);
        this.civStats.lp = Math.max(this.civStats.lp, Game.getCiv(Game.player.iCivID).getNumOfProvinces());
        final Json json = SaveManager.getJson();
        json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)Stats.class);
        final FileHandle file = FileManager.getSaveType("statistics/" + this.civStats.tg + ".json");
        file.writeString(json.toJson((Object)this.civStats), false);
        this.saveStatsList();
    }
    
    private final void saveStatsList() {
        String tList = "";
        if (FileManager.loadFile("statistics/AoH.txt").exists()) {
            final FileHandle file2 = FileManager.loadFile("statistics/AoH.txt");
            final String tempTags = tList = file2.readString();
            final String[] tSplited = tempTags.split(";");
            boolean add = true;
            for (int i = 0, iSize = tSplited.length; i < iSize; ++i) {
                if (tSplited[i].equals(this.civStats.tg)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                tList = tList + this.civStats.tg + ";";
            }
        }
        else {
            tList = tList + this.civStats.tg + ";";
        }
        final FileHandle fileSave = FileManager.getSaveType("statistics/AoH.txt");
        fileSave.writeString(tList, false);
    }
    
    public final List<Stats> loadAllStats() {
        final List<Stats> out = new ArrayList<Stats>();
        if (FileManager.loadFile("statistics/AoH.txt").exists()) {
            final FileHandle file2 = FileManager.loadFile("statistics/AoH.txt");
            final String tempTags = file2.readString();
            final String[] tSplited = tempTags.split(";");
            for (int i = 0, iSize = tSplited.length; i < iSize; ++i) {
                if (FileManager.loadFile("statistics/" + tSplited[i] + ".json").exists()) {
                    try {
                        final FileHandle fileList = FileManager.loadFile("statistics/" + tSplited[i] + ".json");
                        final Json json = new Json();
                        final Stats loadedStats = (Stats)json.fromJson((Class)Stats.class, fileList);
                        if (loadedStats != null) {
                            out.add(loadedStats);
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        return out;
    }
}
