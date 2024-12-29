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

public class CreateCiv extends Menu
{
    public static Game.LoadCivilizationData nCiv;
    private final String sCivTAG = "Civilization TAG";
    private String sCivName;
    public static View goBackTo;
    public static boolean saveFlag;
    
    public CreateCiv() {
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
        if (CreateCiv.nCiv.Name == null) {
            CreateCiv.nCiv.Name = "";
        }
        if (CreateCiv.nCiv.Tag == null) {
            CreateCiv.nCiv.Tag = "" + CFG.extraRandomTag2();
        }
        CreateCiv.nCiv.iR = Game.oR.nextInt(255);
        CreateCiv.nCiv.iG = Game.oR.nextInt(255);
        CreateCiv.nCiv.iB = Game.oR.nextInt(255);
        buttonY += 44 + buttonYPadding;
        menuElements.add(new ButtonMain(CreateCiv.nCiv.Tag, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public String getTextToDraw() {
                return "Civilization TAG: " + CreateCiv.nCiv.Tag + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.CREATE_CIV_EDIT) ? Keyboard.getKeyboardVerticalLine() : "");
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.CREATE_CIV_EDIT, CreateCiv.nCiv.Tag);
            }
            
            @Override
            public int getButtonBG() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.CREATE_CIV_EDIT) ? super.getButtonBG_Active() : super.getButtonBG();
            }
            
            @Override
            public int getButtonBG_Active() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.CREATE_CIV_EDIT) ? super.getButtonBG() : super.getButtonBG_Active();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("CIV_TAG: ", CreateCiv.nCiv.Tag, Images.council, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Must be unique! No spaces and special characters!", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(CreateCiv.nCiv.Name, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public String getTextToDraw() {
                return CreateCiv.this.sCivName + ": " + CreateCiv.nCiv.Name + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.CREATE_CIV_EDIT_NAME) ? Keyboard.getKeyboardVerticalLine() : "");
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.CREATE_CIV_EDIT_NAME, CreateCiv.nCiv.Name);
            }
            
            @Override
            public int getButtonBG() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.CREATE_CIV_EDIT_NAME) ? super.getButtonBG_Active() : super.getButtonBG();
            }
            
            @Override
            public int getButtonBG_Active() {
                final Keyboard keyboard = Game.keyboard;
                return (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.CREATE_CIV_EDIT_NAME) ? super.getButtonBG() : super.getButtonBG_Active();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(Game.lang.get("CustomizeFlag"), 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.createCivFlag().getVisible()) {
                    Game.menuManager.createCivFlag().setVisible(false);
                }
                else {
                    Game.menuManager.createCivReligion().setVisible(false);
                    Game.menuManager.createCivGroup().setVisible(false);
                    Game.menuManager.createCivFlag().setVisible(false);
                    Game.menuManager.rebuildCreateCivFlag();
                    Game.menuManager.createCivFlag().setPosX(CreateCiv.this.getPosX() + CreateCiv.this.getWidth() + CFG.PADDING * 4);
                }
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
                    Game.menuManager.getColorPicker().setActiveRGBColor(CreateCiv.nCiv.iR / 255.0f, CreateCiv.nCiv.iG / 255.0f, CreateCiv.nCiv.iB / 255.0f);
                    Game.menuManager.getColorPicker().setVisible(true, ColorPicker.PickerAction.CREATE_CIV_EDIT);
                    Game.menuManager.getColorPicker().setPosX(CreateCiv.this.getPosX() + CreateCiv.this.getWidth() + CFG.PADDING * 4);
                    Game.menuManager.getColorPicker().setPosY(this.getPosY());
                }
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                oSB.setColor(new Color(CreateCiv.nCiv.iR / 255.0f, CreateCiv.nCiv.iG / 255.0f, CreateCiv.nCiv.iB / 255.0f, 1.0f));
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
                return super.getTextToDraw() + Game.religionManager.getReligion(CreateCiv.nCiv.ReligionID).Name;
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.createCivReligion().getVisible()) {
                    Game.menuManager.createCivReligion().setVisible(false);
                }
                else {
                    Game.menuManager.createCivGroup().setVisible(false);
                    Game.menuManager.createCivFlag().setVisible(false);
                    Game.menuManager.createCivReligion().setVisible(!Game.menuManager.createCivReligion().getVisible());
                    Game.menuManager.createCivReligion().setPosX(CreateCiv.this.getPosX() + CreateCiv.this.getWidth() + CFG.PADDING * 4);
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
                return super.getTextToDraw() + RulersManager.groups.get(CreateCiv.nCiv.GroupID);
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.createCivGroup().getVisible()) {
                    Game.menuManager.createCivGroup().setVisible(false);
                }
                else {
                    Game.menuManager.createCivReligion().setVisible(false);
                    Game.menuManager.createCivFlag().setVisible(false);
                    Game.menuManager.createCivGroup().setVisible(!Game.menuManager.createCivGroup().getVisible());
                    Game.menuManager.createCivGroup().setPosX(CreateCiv.this.getPosX() + CreateCiv.this.getWidth() + CFG.PADDING * 4);
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Save"));
            }
            
            @Override
            public void actionElement() {
                CreateCiv.this.save();
                CreateCiv.saveFlag = true;
            }
            
            @Override
            public boolean getClickable() {
                return CreateCiv.nCiv.Name.length() > 0 && super.getClickable();
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
                Game.menuManager.setViewID(CreateCiv.goBackTo);
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
        Game.flagManager.drawFlag(oSB, this.getPosX() + this.getWidth() / 2 - 34 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY);
    }
    
    @Override
    public void drawTitle(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (CreateCiv.saveFlag) {
            Game.flagManager.saveFlagTexture(oSB);
            Game.menuManager.setViewIDWithoutAnimation(CreateCiv.goBackTo);
        }
        else {
            super.drawTitle(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        }
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("CreateNewCivilization"));
    }
    
    @Override
    public void actionElement(final int nMenuElementID) {
        if (Game.menuManager.getColorPicker().getVisible() && nMenuElementID != 1) {
            Game.menuManager.getColorPicker().hideColorPicker();
        }
        if (Game.menuManager.createCivReligion().getVisible() && nMenuElementID != 2) {
            Game.menuManager.createCivReligion().setVisible(false);
        }
        if (Game.menuManager.createCivGroup().getVisible() && nMenuElementID != 3) {
            Game.menuManager.createCivGroup().setVisible(false);
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
            final FileHandle file = FileManager.getSaveType("mods/GameCivs/game/civilizations/" + CreateCiv.nCiv.Tag + ".json");
            file.writeString(json.prettyPrint((Object)CreateCiv.nCiv), false);
        }
        else {
            final FileHandle file = Gdx.files.local("game/civilizations/" + CreateCiv.nCiv.Tag + ".json");
            file.writeString(json.prettyPrint((Object)CreateCiv.nCiv), false);
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
            if (tempTags.indexOf(CreateCiv.nCiv.Tag) < 0) {
                final FileHandle fileSave = FileManager.getSaveType("game/Civilizations.txt");
                fileSave.writeString(tempTags + CreateCiv.nCiv.Tag + ";", false);
            }
            else {
                final String[] tempTagsSplited = tempTags.split(";");
                boolean tAdd = true;
                for (int i = 0, iSize = tempTagsSplited.length; i < iSize; ++i) {
                    if (tempTagsSplited[i].equals(CreateCiv.nCiv.Tag)) {
                        tAdd = false;
                        break;
                    }
                }
                if (!tAdd) {
                    this.onBackPressed();
                    return;
                }
                final FileHandle fileSave2 = FileManager.getSaveType("game/Civilizations.txt");
                fileSave2.writeString(tempTags + CreateCiv.nCiv.Tag + ";", false);
            }
        }
        catch (final GdxRuntimeException ex) {
            final FileHandle fileSave3 = FileManager.getSaveType("game/Civilizations.txt");
            fileSave3.writeString(CreateCiv.nCiv.Tag + ";", false);
        }
    }
    
    static {
        CreateCiv.nCiv = new Game.LoadCivilizationData();
        CreateCiv.goBackTo = View.EDITOR;
        CreateCiv.saveFlag = false;
    }
}
