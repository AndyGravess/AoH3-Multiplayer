// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.SaveLoad;

import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class ExportScenarioAsMod
{
    public static void exportScenario(final String tag) {
        try {
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Details.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Details.json"));
            }
            catch (final Exception ex2) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Data.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Data.json"));
            }
            catch (final Exception ex3) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "DataProvinces.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "DataProvinces.json"));
            }
            catch (final Exception ex4) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "DataProvinces.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "DataProvinces.json"));
            }
            catch (final Exception ex5) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Cores.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Cores.json"));
            }
            catch (final Exception ex6) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Religions.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Religions.json"));
            }
            catch (final Exception ex7) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Characters.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Characters.json"));
            }
            catch (final Exception ex8) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Armies.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Armies.json"));
            }
            catch (final Exception ex9) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Buildings.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Buildings.json"));
            }
            catch (final Exception ex10) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Wasteland.txt");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Wasteland.txt"));
            }
            catch (final Exception ex11) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "preview.png");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "preview.png"));
            }
            catch (final Exception ex12) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "preview.png");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/logo.png"));
            }
            catch (final Exception ex13) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "previewSpecial.png");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "previewSpecial.png"));
            }
            catch (final Exception ex14) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Desc.txt");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Desc.txt"));
            }
            catch (final Exception ex15) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Relations.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Relations.json"));
            }
            catch (final Exception ex16) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Alliances.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Alliances.json"));
            }
            catch (final Exception ex17) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Defensive.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Defensive.json"));
            }
            catch (final Exception ex18) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Truces.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Truces.json"));
            }
            catch (final Exception ex19) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "NonAggression.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "NonAggression.json"));
            }
            catch (final Exception ex20) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "MilitaryAccess.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "MilitaryAccess.json"));
            }
            catch (final Exception ex21) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Guarantee.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "Guarantee.json"));
            }
            catch (final Exception ex22) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "AlliancesSpecial.json");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "AlliancesSpecial.json"));
            }
            catch (final Exception ex23) {}
            try {
                final FileHandle file = FileManager.loadFile("modsExamples/mod.txt");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/mod.txt"));
            }
            catch (final Exception ex24) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "descriptions/");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "descriptions/"));
            }
            catch (final Exception ex25) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "events/");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "events/"));
            }
            catch (final Exception ex26) {}
            try {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "missions/");
                file.copyTo(FileManager.getSaveType("mods/" + tag + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tag + "/" + "missions/"));
            }
            catch (final Exception ex27) {}
        }
        catch (final Exception ex) {
            Game.menuManager.addToast_Error(Game.lang.get("Error"), Images.x);
            CFG.exceptionStack(ex);
        }
    }
}
