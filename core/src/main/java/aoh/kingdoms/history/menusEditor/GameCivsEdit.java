// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusEditor;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.FileManager;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Json;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.map.RulersManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.ColorPicker;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menu_element.button.ButtonMain;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu.Menu;

public class GameCivsEdit extends Menu
{
    public static Game.LoadCivilizationData nCiv;
    private final String sCivTAG = "Civilization TAG";
    private String sCivName;
    public static View goBackTo;
    
    public GameCivsEdit() {
        this.sCivName = "";
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = CFG.BUTTON_HEIGHT;
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        final int textPosX = CFG.PADDING * 4;
        this.sCivName = Game.lang.get("Name");
        if (GameCivsEdit.nCiv.Name == null) {
            GameCivsEdit.nCiv.Name = "";
        }
        menuElements.add(new ButtonMain(GameCivsEdit.nCiv.Tag, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public String getTextToDraw() {
                return "Civilization TAG: " + GameCivsEdit.nCiv.Tag + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT) ? Keyboard.getKeyboardVerticalLine() : "");
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.GAMECIVS_EDIT, GameCivsEdit.nCiv.Tag);
            }
            
            @Override
            public int getButtonBG() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT) ? super.getButtonBG_Active() : super.getButtonBG();
            }
            
            @Override
            public int getButtonBG_Active() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT) ? super.getButtonBG() : super.getButtonBG_Active();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("CIV_TAG: ", GameCivsEdit.nCiv.Tag, Images.council, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("How to add flag:", "", Images.council, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Go to:", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("mods/GameCivs/gfx/flagsXH/", CFG.FONT_REGULAR, Colors.COLOR_BOX_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Add new Flag with created TAG of Civilization", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("CIV_TAG.png", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Example: ", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(GameCivsEdit.nCiv.Tag + ".png", CFG.FONT_REGULAR, Colors.COLOR_BOX_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Flag image dimensions: 154 px width x 100 px height", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(GameCivsEdit.nCiv.Name, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public String getTextToDraw() {
                return GameCivsEdit.this.sCivName + ": " + GameCivsEdit.nCiv.Name + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT_NAME) ? Keyboard.getKeyboardVerticalLine() : "");
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.GAMECIVS_EDIT_NAME, GameCivsEdit.nCiv.Name);
            }
            
            @Override
            public int getButtonBG() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT_NAME) ? super.getButtonBG_Active() : super.getButtonBG();
            }
            
            @Override
            public int getButtonBG_Active() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT_NAME) ? super.getButtonBG() : super.getButtonBG_Active();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("CivilizationColor"));
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                else {
                    Game.menuManager.getColorPicker().setActiveRGBColor(GameCivsEdit.nCiv.iR / 255.0f, GameCivsEdit.nCiv.iG / 255.0f, GameCivsEdit.nCiv.iB / 255.0f);
                    Game.menuManager.getColorPicker().setVisible(true, ColorPicker.PickerAction.GAMECIVS_EDIT);
                    Game.menuManager.getColorPicker().setPosX(GameCivsEdit.this.getPosX() + GameCivsEdit.this.getWidth() + CFG.PADDING * 4);
                    Game.menuManager.getColorPicker().setPosY(GameCivsEdit.this.getPosY());
                }
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                oSB.setColor(new Color(GameCivsEdit.nCiv.iR / 255.0f, GameCivsEdit.nCiv.iG / 255.0f, GameCivsEdit.nCiv.iB / 255.0f, 1.0f));
                Images.pix.draw(oSB, iTranslateX + this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2, iTranslateY + this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Religion") + ": ");
            }
            
            @Override
            public String getTextToDraw() {
                return super.getTextToDraw() + Game.religionManager.getReligion(GameCivsEdit.nCiv.ReligionID).Name;
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.editorGameCivsEditReligion().getVisible()) {
                    Game.menuManager.editorGameCivsEditReligion().setVisible(false);
                }
                else {
                    Game.menuManager.editorGameCivsEditGroup().setVisible(false);
                    Game.menuManager.editorGameCivsEditReligion().setVisible(!Game.menuManager.editorGameCivsEditReligion().getVisible());
                    Game.menuManager.editorGameCivsEditReligion().setPosX(GameCivsEdit.this.getPosX() + GameCivsEdit.this.getWidth() + CFG.PADDING * 4);
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Group") + ": ");
            }
            
            @Override
            public String getTextToDraw() {
                return super.getTextToDraw() + RulersManager.groups.get(GameCivsEdit.nCiv.GroupID);
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.editorGameCivsEditGroup().getVisible()) {
                    Game.menuManager.editorGameCivsEditGroup().setVisible(false);
                }
                else {
                    Game.menuManager.editorGameCivsEditReligion().setVisible(false);
                    Game.menuManager.editorGameCivsEditGroup().setVisible(!Game.menuManager.editorGameCivsEditGroup().getVisible());
                    Game.menuManager.editorGameCivsEditGroup().setPosX(GameCivsEdit.this.getPosX() + GameCivsEdit.this.getWidth() + CFG.PADDING * 4);
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(GameCivsEdit.nCiv.Wiki, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public String getTextToDraw() {
                return "Wiki: " + GameCivsEdit.nCiv.Wiki + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT_WIKI) ? Keyboard.getKeyboardVerticalLine() : "");
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.GAMECIVS_EDIT_WIKI, GameCivsEdit.nCiv.Wiki);
            }
            
            @Override
            public int getButtonBG() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT_WIKI) ? super.getButtonBG_Active() : super.getButtonBG();
            }
            
            @Override
            public int getButtonBG_Active() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.GAMECIVS_EDIT_WIKI) ? super.getButtonBG() : super.getButtonBG_Active();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Optional"), "", Images.wiki, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding + CFG.BUTTON_HEIGHT;
        menuElements.add(new ButtonMain(null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Save"));
            }
            
            @Override
            public void actionElement() {
                GameCivsEdit.this.save();
                Game.menuManager.setViewID(GameCivsEdit.goBackTo);
            }
            
            @Override
            public boolean getClickable() {
                return GameCivsEdit.nCiv.Tag.length() > 0 && super.getClickable();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(GameCivsEdit.goBackTo);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        this.initMenu(new MenuTitle("", 1.0f, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2, menuElements, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        if (GameCivsEdit.nCiv.Tag.length() > 0) {
            this.getTitle().setText(Game.lang.get("Edit") + ": " + Game.lang.getCiv(GameCivsEdit.nCiv.Tag));
        }
        else {
            this.getTitle().setText(Game.lang.get("AddCivilization"));
        }
    }
    
    @Override
    public void actionElement(final int nMenuElementID) {
        if (Game.menuManager.getColorPicker().getVisible() && nMenuElementID != 1) {
            Game.menuManager.getColorPicker().hideColorPicker();
        }
        if (Game.menuManager.editorGameCivsEditReligion().getVisible() && nMenuElementID != 2) {
            Game.menuManager.editorGameCivsEditReligion().setVisible(false);
        }
        if (Game.menuManager.editorGameCivsEditGroup().getVisible() && nMenuElementID != 3) {
            Game.menuManager.editorGameCivsEditGroup().setVisible(false);
        }
        super.actionElement(nMenuElementID);
    }
    
    public final void save() {
        final Json json = new Json();
        json.setTypeName((String)null);
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        json.setOutputType(JsonWriter.OutputType.json);
        if (CFG.isDesktop()) {
            final FileHandle file = FileManager.getSaveType("mods/GameCivs/game/civilizations/" + GameCivsEdit.nCiv.Tag + ".json");
            file.writeString(json.prettyPrint((Object)GameCivsEdit.nCiv), false);
        }
        else {
            final FileHandle file = Gdx.files.local("game/civilizations/" + GameCivsEdit.nCiv.Tag + ".json");
            file.writeString(json.prettyPrint((Object)GameCivsEdit.nCiv), false);
        }
        this.saveCivsList();
    }
    
    private final void saveCivsList() {
        try {
            FileHandle file;
            if (FileManager.IS_MAC && Gdx.files.external("game/Civilizations.txt").exists()) {
                file = Gdx.files.external("game/Civilizations.txt");
            }
            else {
                file = Gdx.files.internal("game/Civilizations.txt");
            }
            final String tempTags = file.readString();
            if (tempTags.indexOf(GameCivsEdit.nCiv.Tag) < 0) {
                final FileHandle fileSave = FileManager.getSaveType("game/Civilizations.txt");
                fileSave.writeString(tempTags + GameCivsEdit.nCiv.Tag + ";", false);
            }
            else {
                final String[] tempTagsSplited = tempTags.split(";");
                boolean tAdd = true;
                for (int i = 0, iSize = tempTagsSplited.length; i < iSize; ++i) {
                    if (tempTagsSplited[i].equals(GameCivsEdit.nCiv.Tag)) {
                        tAdd = false;
                        break;
                    }
                }
                if (!tAdd) {
                    this.onBackPressed();
                    return;
                }
                final FileHandle fileSave2 = FileManager.getSaveType("game/Civilizations.txt");
                fileSave2.writeString(tempTags + GameCivsEdit.nCiv.Tag + ";", false);
            }
        }
        catch (final GdxRuntimeException ex) {
            final FileHandle fileSave3 = FileManager.getSaveType("game/Civilizations.txt");
            fileSave3.writeString(GameCivsEdit.nCiv.Tag + ";", false);
        }
    }
    
    static {
        GameCivsEdit.nCiv = new Game.LoadCivilizationData();
    }
}
