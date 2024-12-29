// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusEditor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoh.kingdoms.history.menus.Dialog;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ColorTitle;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.codedisaster.steamworks.SteamUGC;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Steam.SteamManager;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonMain;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Image;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class GameCivs extends Menu
{
    private List<String> lCivsTags;
    private List<Image> lFlags;
    private List<Integer> lLoadedFlags_TagsIDs;
    public static String chosen_AlphabetCharachter;
    public static String sSearch;
    
    public GameCivs() {
        this.lCivsTags = null;
        this.lFlags = new ArrayList<Image>();
        this.lLoadedFlags_TagsIDs = new ArrayList<Integer>();
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = CFG.BUTTON_HEIGHT;
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        final int textPosX = CFG.PADDING * 4;
        this.lCivsTags = new ArrayList<String>();
        menuElements.add(new ButtonMain(null, 1, -1, paddingLeft, buttonYPadding, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.EDITOR);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        String[] tagsSPLITED = null;
        final FileHandle tempFileT = FileManager.loadFile("game/Civilizations.txt");
        final String tempT = tempFileT.readString();
        tagsSPLITED = tempT.split(";");
        final List<String> lTempNames = new ArrayList<String>();
        final List<String> lTempTags = new ArrayList<String>();
        final List<String> tCivsTags = new ArrayList<String>();
        for (int i = 0; i < tagsSPLITED.length; ++i) {
            tCivsTags.add(tagsSPLITED[i]);
        }
        if (CFG.isDesktop()) {
            for (int j = 0; j < SteamManager.modsFoldersSize; ++j) {
                FileHandle[] files;
                if (FileManager.IS_MAC) {
                    files = Gdx.files.external(SteamManager.modsFolders.get(j) + "game/" + "civilizations/").list();
                }
                else {
                    files = Gdx.files.internal(SteamManager.modsFolders.get(j) + "game/" + "civilizations/").list();
                }
                for (final FileHandle file : files) {
                    tCivsTags.add(file.name().replace(".json", ""));
                }
            }
            for (int j = 0; j < SteamManager.itemsInstalledSize; ++j) {
                final FileHandle[] list;
                final FileHandle[] files = list = Gdx.files.absolute(SteamManager.itemsInstalled.get(j).getFolder() + "/" + "game/" + "civilizations/").list();
                for (final FileHandle file : list) {
                    tCivsTags.add(file.name().replace(".json", ""));
                }
            }
        }
        if (GameCivs.sSearch.length() > 0) {
            for (int i = 0, iSize = tCivsTags.size(); i < iSize; ++i) {
                if (Game.lang.getCiv(tCivsTags.get(i)).toLowerCase().indexOf(GameCivs.sSearch.toLowerCase()) >= 0) {
                    lTempNames.add(Game.lang.getCiv(tCivsTags.get(i)));
                    lTempTags.add(tCivsTags.get(i));
                }
            }
        }
        else if (GameCivs.chosen_AlphabetCharachter.length() > 0) {
            for (int i = 0, iSize = tCivsTags.size(); i < iSize; ++i) {
                if (Game.lang.getCiv(tCivsTags.get(i)).charAt(0) == GameCivs.chosen_AlphabetCharachter.charAt(0)) {
                    lTempNames.add(Game.lang.getCiv(tCivsTags.get(i)));
                    lTempTags.add(tCivsTags.get(i));
                }
            }
        }
        else {
            for (int i = 0, iSize = tCivsTags.size(); i < iSize; ++i) {
                lTempNames.add(Game.lang.getCiv(tCivsTags.get(i)));
                lTempTags.add(tCivsTags.get(i));
            }
        }
        try {
            while (lTempNames.size() > 0) {
                int toAddID = 0;
                for (int j = 1; j < lTempNames.size(); ++j) {
                    if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(j))) {
                        toAddID = j;
                    }
                }
                menuElements.add(new ButtonMain(Game.lang.getCiv(lTempTags.get(toAddID)) + " [" + lTempTags.get(toAddID) + "]", 1, CFG.PADDING * 2 + CFG.CIV_FLAG_WIDTH, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2 - CFG.BUTTON_HEIGHT, true) {
                    @Override
                    public void buildElementHover() {
                        try {
                            final String tTag = this.getText().substring(this.getText().indexOf("[") + 1, this.getText().indexOf("]"));
                            final Game.LoadCivilizationData nCivs = Game.loadCivilization(tTag);
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_ColorTitle(new Color(nCivs.iR / 255.0f, nCivs.iG / 255.0f, nCivs.iB / 255.0f, 1.0f), CFG.FONT_BOLD_SMALL, CFG.PADDING));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.getCiv(nCivs.Tag), Colors.HOVER_TITLE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(nCivs.ReligionID).Name, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Group") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text(RulersManager.groups.get(nCivs.GroupID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text("Wiki: ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.getCiv(nCivs.Wiki), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        catch (final IndexOutOfBoundsException ex) {
                            super.buildElementHover();
                        }
                    }
                });
                menuElements.add(new ButtonMain("", 1, CFG.PADDING * 2, paddingLeft + CFG.LEFT_MENU_WIDTH - paddingLeft * 2 - CFG.BUTTON_HEIGHT, buttonY, CFG.BUTTON_HEIGHT, true) {
                    @Override
                    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                        ImageManager.getImage(Images.wiki).draw(oSB, iTranslateX + this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.wiki).getWidth() / 2, iTranslateY + this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.wiki).getHeight() / 2);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                this.lCivsTags.add(lTempTags.get(toAddID));
                lTempNames.remove(toAddID);
                lTempTags.remove(toAddID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.initMenu(new MenuTitle("", 1.0f, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2, menuElements, true);
    }
    
    public final void drawEditorMenuBG(final SpriteBatch oSB, final int iX, final int iY, final int iWidth, final int iHeight, final int iTranslateX, final int iTranslateY) {
        Renderer.drawBoxCorner(oSB, iX + iTranslateX, iY - this.getTitle().getHeight() + iTranslateY, iWidth, iHeight + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawBox_EDGE_TOP_LR(oSB, Images.mainBox, iX + iTranslateX, iY + iTranslateY, iWidth, iHeight + CFG.PADDING, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), iTranslateX, iTranslateY);
        super.beginClip(oSB, iTranslateX, iTranslateY, menuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, menuIsActive);
        try {
            for (int i = 1; i < this.getMenuElementsSize(); i += 2) {
                if (this.getMenuElement(i).getIsInView()) {
                    this.lFlags.get(this.getFlagID((i - 1) / 2)).draw(oSB, this.getPosX() + this.getMenuElement(i).getPosX() + CFG.PADDING + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {}
        catch (final NullPointerException ex2) {}
        super.endClip(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        final FileHandle tempFileT = FileManager.loadFile("game/Civilizations.txt");
        final String tempT = tempFileT.readString();
        final String[] tagsSPLITED = tempT.split(";");
        this.getTitle().setText(Game.lang.get("GameCivilizations") + " [" + this.lCivsTags.size() + "]");
    }
    
    @Override
    public void actionElement(final int nMenuElementID) {
        if (nMenuElementID == 0) {
            super.actionElement(nMenuElementID);
        }
        else if (nMenuElementID % 2 == 1) {
            final String tempCivTag = this.lCivsTags.get((nMenuElementID - 1) / 2);
            GameCivsEdit.nCiv = Game.loadCivilization(tempCivTag);
            GameCivsEdit.goBackTo = View.EDITOR_GAMECIVS;
            Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
        }
        else {
            final String tempCivTag = this.lCivsTags.get((nMenuElementID - 1) / 2);
            try {
                final Game.LoadCivilizationData nCiv = Game.loadCivilization(tempCivTag);
                Dialog.GO_TO_LINK = "https://en.wikipedia.org/wiki/" + nCiv.Wiki;
                Dialog.setDialogType(Dialog.DialogType.GO_TO_LINK);
            }
            catch (final GdxRuntimeException ex) {
                Game.menuManager.addToast_Error(Game.lang.get("NoData"));
            }
        }
    }
    
    @Override
    public void updateMenuElements_IsInView() {
        super.updateMenuElements_IsInView();
        int i;
        for (int tempRandomButton = i = 1; i < this.getMenuElementsSize(); i += 2) {
            final int tempTagID = this.getIsLoaded(this.lCivsTags.get((i - tempRandomButton) / 2));
            if (this.getMenuElement(i).getIsInView()) {
                if (tempTagID < 0) {
                    this.loadFlag((i - tempRandomButton) / 2);
                }
            }
            else if (tempTagID >= 0) {
                this.lFlags.get(tempTagID).getTexture().dispose();
                this.lFlags.set(tempTagID, null);
                this.lFlags.remove(tempTagID);
                this.lLoadedFlags_TagsIDs.remove(tempTagID);
            }
        }
    }
    
    private final int getIsLoaded(final String nCivTag) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (this.lCivsTags.get(this.lLoadedFlags_TagsIDs.get(i)).equals(nCivTag)) {
                return i;
            }
        }
        return -1;
    }
    
    private final int getFlagID(final int nCivTagID) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (this.lLoadedFlags_TagsIDs.get(i) == nCivTagID) {
                return i;
            }
        }
        return 0;
    }
    
    private final void loadFlag(final int nCivTagID) {
        try {
            try {
                try {
                    this.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Nearest));
                }
                catch (final GdxRuntimeException e) {
                    this.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")), Texture.TextureFilter.Nearest));
                }
            }
            catch (final GdxRuntimeException ex) {
                try {
                    this.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Nearest));
                }
                catch (final GdxRuntimeException e2) {
                    this.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")), Texture.TextureFilter.Nearest));
                }
            }
        }
        catch (final GdxRuntimeException e) {
            this.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/ran.png")), Texture.TextureFilter.Nearest));
        }
        this.lLoadedFlags_TagsIDs.add(nCivTagID);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            this.disposeData();
        }
    }
    
    public void disposeData() {
        for (int i = 0; i < this.lFlags.size(); ++i) {
            this.lFlags.get(i).getTexture().dispose();
        }
        this.lFlags.clear();
        this.lLoadedFlags_TagsIDs.clear();
        this.lCivsTags.clear();
    }
    
    static {
        GameCivs.chosen_AlphabetCharachter = "";
        GameCivs.sSearch = "";
    }
}
