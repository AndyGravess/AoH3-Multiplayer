// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.setting;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.Gdx;

public class SettingsDesktop
{
    public static boolean fullscreen;
    public static int iWidth;
    public static int iHeight;
    public static boolean vSync;
    
    public static final void readConfig() {
        try {
            if (Gdx.files.local("settings/Config.txt").exists()) {
                final FileHandle file = Gdx.files.local("settings/Config.txt");
                final String tempTags = file.readString();
                final String[] tSplited = tempTags.replace("\n", "").split(";");
                for (int i = 0; i < tSplited.length; ++i) {
                    final String[] tempR = tSplited[i].split("=");
                    try {
                        if (tempR[0].equals("FULLSCREEN")) {
                            SettingsDesktop.fullscreen = Boolean.parseBoolean(tempR[1]);
                        }
                        else if (tempR[0].equals("WIDTH")) {
                            SettingsDesktop.iWidth = Integer.parseInt(tempR[1]);
                        }
                        else if (tempR[0].equals("HEIGHT")) {
                            SettingsDesktop.iHeight = Integer.parseInt(tempR[1]);
                        }
                        else if (tempR[0].equals("VSYNC")) {
                            SettingsDesktop.vSync = Boolean.parseBoolean(tempR[1]);
                        }
                    }
                    catch (final IndexOutOfBoundsException ex) {
                        SettingsDesktop.iWidth = -1;
                        SettingsDesktop.iHeight = -1;
                        SettingsDesktop.fullscreen = true;
                        break;
                    }
                    catch (final IllegalArgumentException ex2) {
                        SettingsDesktop.iWidth = -1;
                        SettingsDesktop.iHeight = -1;
                        SettingsDesktop.fullscreen = true;
                        break;
                    }
                }
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
    }
    
    public static final void saveConfig() {
        final FileHandle fileSave = FileManager.getSaveType("settings/Config.txt");
        fileSave.writeString("FULLSCREEN=" + SettingsDesktop.fullscreen + ";\n", false);
        fileSave.writeString("WIDTH=" + SettingsDesktop.iWidth + ";\n", true);
        fileSave.writeString("HEIGHT=" + SettingsDesktop.iHeight + ";\n", true);
        fileSave.writeString("VSYNC=" + SettingsDesktop.vSync + ";", true);
    }
    
    static {
        SettingsDesktop.fullscreen = true;
        SettingsDesktop.iWidth = -1;
        SettingsDesktop.iHeight = -1;
        SettingsDesktop.vSync = true;
    }
}
