// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import com.codedisaster.steamworks.SteamUGC;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.files.FileHandle;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.utils.I18NBundle;

public class LanguageManager
{
    public static boolean translationsKeysMode;
    private I18NBundle bundle;
    private I18NBundle bundleCivs;
    private I18NBundle bundleLoading;
    public int iLoading_NumOfTexts;
    private KeyOutput keyOutput;
    public List<I18NBundle> modsBundles;
    public int modsBundlesSize;
    
    public LanguageManager(String nTag) {
        this.iLoading_NumOfTexts = 0;
        this.modsBundles = new ArrayList<I18NBundle>();
        this.modsBundlesSize = 0;
        if (nTag == null) {
            nTag = "";
        }
        final FileHandle fileHandle = FileManager.loadFile("game/languages/Bundle");
        final Locale locale = new Locale(nTag);
        this.bundle = I18NBundle.createBundle(fileHandle, locale);
        this.initCivsBundle(nTag);
        this.initLoadingBundle(nTag);
        this.updateKeyOutput();
    }
    
    public final void initCivsBundle(final String nTag) {
        final FileHandle fileHandleCivs = FileManager.loadFile("game/languages/civilizations/Bundle");
        if (nTag != null && nTag.length() > 0) {
            if (FileManager.loadFile("game/languages/civilizations/Bundle_" + nTag + ".properties").exists()) {
                final Locale localeCivs = new Locale(nTag);
                this.bundleCivs = I18NBundle.createBundle(fileHandleCivs, localeCivs);
            }
            else {
                final Locale localeCivs = new Locale("");
                this.bundleCivs = I18NBundle.createBundle(fileHandleCivs, localeCivs);
            }
        }
        else {
            final Locale localeCivs = new Locale(nTag);
            this.bundleCivs = I18NBundle.createBundle(fileHandleCivs, localeCivs);
        }
    }
    
    public final void initLoadingBundle(final String nTag) {
        final FileHandle fileHandleLoading = FileManager.loadFile("game/languages/loading/Bundle");
        final Locale localeLoading = new Locale(nTag);
        this.bundleLoading = I18NBundle.createBundle(fileHandleLoading, localeLoading);
        try {
            this.iLoading_NumOfTexts = Integer.parseInt(this.getLoading("NumOfTexts"));
        }
        catch (final Exception ex) {
            this.iLoading_NumOfTexts = 0;
        }
    }
    
    public void loadModsLanguages(final String nTag) {
        try {
            this.modsBundles.clear();
            for (int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                if (Gdx.files.external(SteamManager.modsFolders.get(i) + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    final FileHandle fileHandleCivs = Gdx.files.external(SteamManager.modsFolders.get(i) + "languages/Bundle");
                    final Locale localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
                else if (Gdx.files.external(SteamManager.modsFolders.get(i) + "languages/Bundle" + ".properties").exists()) {
                    final FileHandle fileHandleCivs = Gdx.files.external(SteamManager.modsFolders.get(i) + "languages/Bundle");
                    final Locale localeCivs = new Locale("");
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
                else if (Gdx.files.internal(SteamManager.modsFolders.get(i) + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    final FileHandle fileHandleCivs = Gdx.files.internal(SteamManager.modsFolders.get(i) + "languages/Bundle");
                    final Locale localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
                else if (Gdx.files.internal(SteamManager.modsFolders.get(i) + "languages/Bundle" + ".properties").exists()) {
                    final FileHandle fileHandleCivs = Gdx.files.internal(SteamManager.modsFolders.get(i) + "languages/Bundle");
                    final Locale localeCivs = new Locale("");
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
            }
            for (int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                if (Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    final FileHandle fileHandleCivs = Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + "languages/Bundle");
                    final Locale localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
                else if (Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + "languages/Bundle" + ".properties").exists()) {
                    final FileHandle fileHandleCivs = Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + "languages/Bundle");
                    final Locale localeCivs = new Locale("");
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.modsBundlesSize = this.modsBundles.size();
    }
    
    public final void updateKeyOutput() {
        if (LanguageManager.translationsKeysMode) {
            this.keyOutput = new KeyOutput() {
                @Override
                public String get(final String key) {
                    return "[" + key + "]";
                }
                
                @Override
                public String get(final String key, final int iValue) {
                    return "[" + key + "]";
                }
                
                @Override
                public String get(final String key, final String sValue) {
                    return "[" + key + "]";
                }
                
                @Override
                public String get(final String key, final String sValue, final String sValue2) {
                    return "[" + key + "]";
                }
            };
        }
        else {
            this.keyOutput = new KeyOutput() {
                @Override
                public String get(final String key) {
                    try {
                        int i = 0;
                        while (i < LanguageManager.this.modsBundlesSize) {
                            try {
                                return LanguageManager.this.modsBundles.get(i).get(key);
                            }
                            catch (final Exception ex) {
                                ++i;
                                continue;
                            }
                            break;
                        }
                        return LanguageManager.this.bundle.get(key);
                    }
                    catch (final Exception ex2) {
                        return key;
                    }
                }
                
                @Override
                public String get(final String key, final int iValue) {
                    try {
                        int i = 0;
                        while (i < LanguageManager.this.modsBundlesSize) {
                            try {
                                return LanguageManager.this.modsBundles.get(i).format(key, new Object[] { iValue });
                            }
                            catch (final Exception ex) {
                                ++i;
                                continue;
                            }
                            break;
                        }
                        return LanguageManager.this.bundle.format(key, new Object[] { iValue });
                    }
                    catch (final Exception ex2) {
                        return key;
                    }
                }
                
                @Override
                public String get(final String key, final String sValue) {
                    try {
                        int i = 0;
                        while (i < LanguageManager.this.modsBundlesSize) {
                            try {
                                return LanguageManager.this.modsBundles.get(i).format(key, new Object[] { sValue });
                            }
                            catch (final Exception ex) {
                                ++i;
                                continue;
                            }
                            break;
                        }
                        return LanguageManager.this.bundle.format(key, new Object[] { sValue });
                    }
                    catch (final Exception ex2) {
                        return key;
                    }
                }
                
                @Override
                public String get(final String key, final String sValue, final String sValue2) {
                    try {
                        int i = 0;
                        while (i < LanguageManager.this.modsBundlesSize) {
                            try {
                                return LanguageManager.this.modsBundles.get(i).format(key, new Object[] { sValue, sValue2 });
                            }
                            catch (final Exception ex) {
                                ++i;
                                continue;
                            }
                            break;
                        }
                        return LanguageManager.this.bundle.format(key, new Object[] { sValue, sValue2 });
                    }
                    catch (final Exception ex2) {
                        return key;
                    }
                }
            };
        }
    }
    
    public String get(final String key) {
        return this.keyOutput.get(key);
    }
    
    public String get(final String key, final int nValue) {
        return this.keyOutput.get(key, nValue);
    }
    
    public String get(final String key, final String nValue) {
        return this.keyOutput.get(key, nValue);
    }
    
    public String get(final String key, final String nValue, final String nValue2) {
        return this.keyOutput.get(key, nValue, nValue2);
    }
    
    public String getCiv(final String key) {
        try {
            return this.bundleCivs.get(key);
        }
        catch (final Exception ex) {
            if (key != null && key.indexOf(95) > 0) {
                try {
                    int i = 0;
                    while (i < this.modsBundlesSize) {
                        try {
                            return this.modsBundles.get(i).get(key.substring(0, key.indexOf(95)));
                        }
                        catch (final Exception exr) {
                            ++i;
                            continue;
                        }
                        break;
                    }
                    return this.bundleCivs.get(key.substring(0, key.indexOf(95)));
                }
                catch (final Exception ex2) {}
            }
            try {
                final Game.LoadCivilizationData tCiv = Game.loadCivilization(key);
                if (tCiv.Name != null && tCiv.Name.length() > 0) {
                    return tCiv.Name;
                }
            }
            catch (final Exception ex3) {}
            return key;
        }
    }
    
    public String getLoading(final String key) {
        try {
            return this.bundleLoading.get(key);
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    public final void dispose() {
        this.bundle = null;
        this.bundleCivs = null;
        this.bundleLoading = null;
        this.iLoading_NumOfTexts = 0;
    }
    
    static {
        LanguageManager.translationsKeysMode = false;
    }
    
    interface KeyOutput
    {
        String get(final String p0);
        
        String get(final String p0, final int p1);
        
        String get(final String p0, final String p1);
        
        String get(final String p0, final String p1, final String p2);
    }
}
