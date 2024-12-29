// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import com.codedisaster.steamworks.SteamUGC;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.files.FileHandle;

public class FileManager
{
    public static LoadInterface loadInterface;
    public static boolean IS_MAC;
    
    public static FileHandle getSaveType(final String sFile) {
        return FileManager.loadInterface.getSaveType(sFile);
    }
    
    public static void initLoadInterface() {
        if (CFG.isDesktop()) {
            if (FileManager.IS_MAC) {
                FileManager.loadInterface = new LoadInterface() {
                    @Override
                    public FileHandle loadFile(final String sFile) {
                        for (int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                            if (Gdx.files.external(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return Gdx.files.external(SteamManager.modsFolders.get(i) + sFile);
                            }
                            if (Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile);
                            }
                        }
                        for (int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                            if (Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile).exists()) {
                                return Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile);
                            }
                        }
                        if (Gdx.files.external(sFile).exists()) {
                            return Gdx.files.external(sFile);
                        }
                        return Gdx.files.internal(sFile);
                    }
                    
                    @Override
                    public FileHandle getSaveType(final String sFile) {
                        return Gdx.files.external(sFile);
                    }
                };
            }
            else {
                FileManager.loadInterface = new LoadInterface() {
                    @Override
                    public FileHandle loadFile(final String sFile) {
                        for (int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                            if (Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile);
                            }
                        }
                        for (int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                            if (Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile).exists()) {
                                return Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile);
                            }
                        }
                        return Gdx.files.internal(sFile);
                    }
                    
                    @Override
                    public FileHandle getSaveType(final String sFile) {
                        return Gdx.files.local(sFile);
                    }
                };
            }
        }
        else {
            FileManager.loadInterface = new LoadInterface() {
                @Override
                public FileHandle loadFile(final String sFile) {
                    if (Gdx.files.local(sFile).exists()) {
                        return Gdx.files.local(sFile);
                    }
                    return Gdx.files.internal(sFile);
                }
                
                @Override
                public FileHandle getSaveType(final String sFile) {
                    return Gdx.files.local(sFile);
                }
            };
        }
    }
    
    public static FileHandle loadFile(final String sFile) {
        return FileManager.loadInterface.loadFile(sFile);
    }
    
    static {
        FileManager.IS_MAC = false;
    }
    
    public interface LoadInterface
    {
        FileHandle loadFile(final String p0);
        
        FileHandle getSaveType(final String p0);
    }
}
