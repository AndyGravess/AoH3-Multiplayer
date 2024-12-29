// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import java.util.List;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvincesBG;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Government;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamAchievementsManager;
import com.codedisaster.steamworks.SteamUGC;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class FormableCivManager
{
    public static FormableCiv activeFormableCiv;
    
    public static final void loadActiveFormableCivilization(final String sFile) {
        try {
            final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "data/" + sFile);
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigJson.class, "Data", (Class)FormableCiv.class);
            FormableCivManager.activeFormableCiv = (FormableCiv)json.fromJson((Class)FormableCiv.class, fileContent);
        }
        catch (final Exception ex) {
            CFG.LOG(ex);
        }
    }
    
    public static final FormableCiv getFormableCivilization(final String sFile) {
        try {
            final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "data/" + sFile + ".txt");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigJson.class, "Data", (Class)FormableCiv.class);
            return (FormableCiv)json.fromJson((Class)FormableCiv.class, fileContent);
        }
        catch (final Exception ex) {
            CFG.LOG(ex);
            return null;
        }
    }
    
    public static final void buildFormableCivilizations() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).clearTagsCanForm();
        }
        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "AoH.txt").exists()) {
            final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "AoH.txt");
            final String tempT = tempFileT.readString();
            final String[] tagsSPLITED = tempT.split(";");
            for (int j = 0, iSize = tagsSPLITED.length; j < iSize; ++j) {
                try {
                    loadActiveFormableCivilization(tagsSPLITED[j]);
                    final String formableRealTag = Game.ideologiesManager.getRealTag(FormableCivManager.activeFormableCiv.FormableCivTag);
                    for (int k = 0, jSize = FormableCivManager.activeFormableCiv.ClaimantsTag.size(); k < jSize; ++k) {
                        final String claimantTag = FormableCivManager.activeFormableCiv.ClaimantsTag.get(k);
                        for (int l = 1; l < Game.getCivsSize(); ++l) {
                            if (Game.getCiv(l).getCivTag().equals(claimantTag) || (Game.getCiv(l).realTag.equals(claimantTag) && !Game.getCiv(l).realTag.equals(formableRealTag))) {
                                Game.getCiv(l).addTagsCanForm(FormableCivManager.activeFormableCiv.FormableCivTag);
                            }
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (int j = 0; j < SteamManager.modsFoldersSize; ++j) {
                FileHandle[] files;
                if (FileManager.IS_MAC) {
                    files = Gdx.files.external(SteamManager.modsFolders.get(j) + "map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/").list();
                }
                else {
                    files = Gdx.files.internal(SteamManager.modsFolders.get(j) + "map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/").list();
                }
                for (final FileHandle file : files) {
                    try {
                        final String fileContent = file.readString();
                        final Json json = new Json();
                        json.setElementType((Class)ConfigJson.class, "Data", (Class)FormableCiv.class);
                        FormableCivManager.activeFormableCiv = (FormableCiv)json.fromJson((Class)FormableCiv.class, fileContent);
                        final String formableRealTag2 = Game.ideologiesManager.getRealTag(FormableCivManager.activeFormableCiv.FormableCivTag);
                        for (int m = 0, jSize2 = FormableCivManager.activeFormableCiv.ClaimantsTag.size(); m < jSize2; ++m) {
                            final String claimantTag2 = FormableCivManager.activeFormableCiv.ClaimantsTag.get(m);
                            for (int k2 = 1; k2 < Game.getCivsSize(); ++k2) {
                                if (Game.getCiv(k2).getCivTag().equals(claimantTag2) || (Game.ideologiesManager.getRealTag(Game.getCiv(k2).getCivTag()).equals(claimantTag2) && !Game.ideologiesManager.getRealTag(Game.getCiv(k2).getCivTag()).equals(formableRealTag2))) {
                                    Game.getCiv(k2).addTagsCanForm(FormableCivManager.activeFormableCiv.FormableCivTag);
                                }
                            }
                        }
                    }
                    catch (final Exception ex2) {
                        CFG.exceptionStack(ex2);
                    }
                }
            }
            for (int j = 0; j < SteamManager.itemsInstalledSize; ++j) {
                final FileHandle[] list;
                final FileHandle[] files = list = Gdx.files.absolute(SteamManager.itemsInstalled.get(j).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/").list();
                for (final FileHandle file : list) {
                    try {
                        final String fileContent = file.readString();
                        final Json json = new Json();
                        json.setElementType((Class)ConfigJson.class, "Data", (Class)FormableCiv.class);
                        FormableCivManager.activeFormableCiv = (FormableCiv)json.fromJson((Class)FormableCiv.class, fileContent);
                        final String formableRealTag2 = Game.ideologiesManager.getRealTag(FormableCivManager.activeFormableCiv.FormableCivTag);
                        for (int m = 0, jSize2 = FormableCivManager.activeFormableCiv.ClaimantsTag.size(); m < jSize2; ++m) {
                            final String claimantTag2 = FormableCivManager.activeFormableCiv.ClaimantsTag.get(m);
                            for (int k2 = 1; k2 < Game.getCivsSize(); ++k2) {
                                if (Game.getCiv(k2).getCivTag().equals(claimantTag2) || (Game.getCiv(k2).realTag.equals(claimantTag2) && !Game.getCiv(k2).realTag.equals(formableRealTag2))) {
                                    Game.getCiv(k2).addTagsCanForm(FormableCivManager.activeFormableCiv.FormableCivTag);
                                }
                            }
                        }
                    }
                    catch (final Exception ex2) {
                        CFG.exceptionStack(ex2);
                    }
                }
            }
        }
    }
    
    public static final void updateFormableCivilizations(final int nCivID) {
        try {
            Game.getCiv(nCivID).clearTagsCanForm();
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "AoH.txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "AoH.txt");
                final String tempT = tempFileT.readString();
                final String[] tagsSPLITED = tempT.split(";");
                for (int i = 0, iSize = tagsSPLITED.length; i < iSize; ++i) {
                    try {
                        loadActiveFormableCivilization(tagsSPLITED[i]);
                        final String formableRealTag = Game.ideologiesManager.getRealTag(FormableCivManager.activeFormableCiv.FormableCivTag);
                        for (int j = 0, jSize = FormableCivManager.activeFormableCiv.ClaimantsTag.size(); j < jSize; ++j) {
                            if (Game.getCiv(nCivID).getCivTag().equals(FormableCivManager.activeFormableCiv.ClaimantsTag.get(j)) || (Game.getCiv(nCivID).realTag.equals(FormableCivManager.activeFormableCiv.ClaimantsTag.get(j)) && !Game.getCiv(nCivID).realTag.equals(formableRealTag))) {
                                Game.getCiv(nCivID).addTagsCanForm(FormableCivManager.activeFormableCiv.FormableCivTag);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static boolean formCiv(final int civID, final String nCivTag) {
        if (!canFormACiv(civID, nCivTag)) {
            return false;
        }
        try {
            if (civID == Game.player.iCivID) {
                SteamAchievementsManager.unlockFormable(nCivTag);
            }
        }
        catch (final Exception ex) {}
        final Civilization civ = Game.getCiv(civID);
        civ.fGold -= GameValues.government.FORM_CIV_COST_GOLD;
        final Game.LoadCivilizationData civData = Game.loadCivilization(nCivTag);
        Game.getCiv(civID).updateCivilizationTAG(nCivTag, civData.iR, civData.iG, civData.iB);
        Game.getCiv(civID).clearTagsCanForm();
        Game.addSimpleTask_First(new Game.SimpleTask("loadRuler" + civID, civID) {
            @Override
            public void update() {
                RulersManager.loadRuler(this.id, Game.getCiv(this.id).getCivTag(), false);
            }
        });
        Game.addSimpleTask_First(new Game.SimpleTask("updateFormableCivilizations" + civID, civID) {
            @Override
            public void update() {
                FormableCivManager.updateFormableCivilizations(this.id);
                if (this.id == Game.player.iCivID) {
                    Game.player.loadFormableCivs();
                    InGame_Court_Government.reloadFlags = true;
                }
                FBOProvincesBG.redrawnProvinces();
            }
        });
        return true;
    }
    
    public static boolean canFormACiv(final int civID, final String nCivTag) {
        if (!doesNotExists_FormableCiv(nCivTag)) {
            return false;
        }
        if (Game.getCiv(civID).diplomacy.isAtWar()) {
            return false;
        }
        if (Game.getCiv(civID).getPuppetOfCivID() != civID) {
            return false;
        }
        if (Game.getCiv(civID).fGold < GameValues.government.FORM_CIV_COST_GOLD) {
            return false;
        }
        final FormableCiv formableCiv = getFormableCivilization(nCivTag);
        return formableCiv != null && formableCiv.controlsAllProvinces(civID);
    }
    
    protected static final boolean doesNotExists_FormableCiv(final String nCivTag) {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (nCivTag.equals(Game.getCiv(i).getCivTag())) {
                return false;
            }
        }
        return true;
    }
    
    public static MenuElement_Hover getHover(final int playerTagID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        try {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Form") + ": "));
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear("" + Game.lang.getCiv(Game.getCiv(Game.player.iCivID).sTagsCanForm.get(playerTagID)), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2((float)GameValues.government.FORM_CIV_COST_GOLD, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nData.add(new MenuElement_HoverElement_Type_Image((Game.getCiv(Game.player.iCivID).fGold >= GameValues.government.FORM_CIV_COST_GOLD) ? Images.v : Images.x, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtPeace"), CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).diplomacy.isAtWar() ? Images.x : Images.v, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IsNotAVassal"), CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Image((Game.getCiv(Game.player.iCivID).getPuppetOfCivID() == Game.player.iCivID) ? Images.v : Images.x, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XDoesNotExist", Game.lang.getCiv(Game.getCiv(Game.player.iCivID).sTagsCanForm.get(playerTagID))), CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Image(doesNotExists_FormableCiv(Game.getCiv(Game.player.iCivID).sTagsCanForm.get(playerTagID)) ? Images.v : Images.x, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("OwnsAllProvinces"), CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Image(Game.player.formableCivs.get(playerTagID).controlsAllProvinces(Game.player.iCivID) ? Images.v : Images.x, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return new MenuElement_Hover(nElements);
    }
    
    static {
        FormableCivManager.activeFormableCiv = new FormableCiv();
    }
    
    public static class ConfigJson
    {
        public String Age_of_History;
        public ArrayList Data;
    }
    
    public static class FormableCiv
    {
        public String FormableCivTag;
        public List<String> ClaimantsTag;
        public List<Integer> Provinces;
        public int CapitalProvinceID;
        
        public FormableCiv() {
            this.FormableCivTag = null;
            this.ClaimantsTag = new ArrayList<String>();
            this.Provinces = new ArrayList<Integer>();
            this.CapitalProvinceID = -1;
        }
        
        public final void addProvince(final int id) {
            if (id < 0) {
                return;
            }
            for (int i = 0; i < this.Provinces.size(); ++i) {
                if (this.Provinces.get(i) == id) {
                    return;
                }
            }
            this.Provinces.add(id);
        }
        
        public final void removeProvince(final int id) {
            if (id < 0) {
                return;
            }
            for (int i = 0; i < this.Provinces.size(); ++i) {
                if (this.Provinces.get(i) == id) {
                    this.Provinces.remove(i);
                    return;
                }
            }
        }
        
        public final void addClaimant(final String nTag) {
            if (this.FormableCivTag != null && this.FormableCivTag.equals(nTag)) {
                return;
            }
            for (int i = 0; i < this.ClaimantsTag.size(); ++i) {
                if (this.ClaimantsTag.get(i).equals(nTag)) {
                    return;
                }
            }
            this.ClaimantsTag.add(nTag);
        }
        
        public final void removeClaimant(final String nTag) {
            for (int i = 0; i < this.ClaimantsTag.size(); ++i) {
                if (this.ClaimantsTag.get(i).equals(nTag)) {
                    this.ClaimantsTag.remove(i);
                    return;
                }
            }
        }
        
        public int getControlledProvinces(final int civID) {
            int out = 0;
            for (int i = this.getProvincesSize() - 1; i >= 0; --i) {
                if (Game.getProvince(this.Provinces.get(i)).getCivID() == civID) {
                    ++out;
                }
            }
            return out;
        }
        
        public boolean controlsAllProvinces(final int civID) {
            for (int i = this.getProvincesSize() - 1; i >= 0; --i) {
                if (Game.getProvince(this.Provinces.get(i)).getWasteland() < 0 && Game.getProvince(this.Provinces.get(i)).getCivID() != civID && Game.getCiv(Game.getProvince(this.Provinces.get(i)).getCivID()).getPuppetOfCivID() != civID && Game.getProvince(this.Provinces.get(i)).getCivID() != 0) {
                    return false;
                }
            }
            return true;
        }
        
        public int getClaimantsSize() {
            return this.ClaimantsTag.size();
        }
        
        public int getProvincesSize() {
            return this.Provinces.size();
        }
        
        public int getProvincesSize_WithoutNeutral() {
            int out = 0;
            for (int i = this.Provinces.size() - 1; i >= 0; --i) {
                if (Game.getProvince(this.Provinces.get(i)).getCivID() > 0) {
                    ++out;
                }
            }
            return out;
        }
    }
}
