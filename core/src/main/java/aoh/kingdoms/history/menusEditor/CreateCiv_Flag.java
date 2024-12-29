// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusEditor;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.FlagsEditor.Flag_Division;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.ColorPicker;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.button.ButtonMain;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class CreateCiv_Flag extends Menu
{
    public CreateCiv_Flag() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = CFG.BUTTON_HEIGHT;
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        final int menuW = CFG.LEFT_MENU_WIDTH;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        final int textPosX = CFG.PADDING * 4;
        final int buttonH = 44 + CFG.PADDING * 2;
        menuElements.add(new ButtonMain(Game.lang.get("Back"), 1, -1, paddingLeft, buttonY, menuW - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.createCivFlag().setVisible(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonGame("<<", 1, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, buttonH, true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                Game.flagManager.updateDivision(false);
                Game.menuManager.rebuildCreateCivFlag();
                Game.menuManager.addToastGold("ID: [" + Game.flagManager.flagEdit.iDivisionID + "/" + (Game.flagManager.lDivisions.size() - 1) + "]", Images.brush);
            }
        });
        menuElements.add(new ButtonGame("", 1, textPosX, paddingLeft + CFG.BUTTON_WIDTH, buttonY, menuW - paddingLeft * 2 - CFG.BUTTON_WIDTH * 2, buttonH, true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible() && Game.flagManager.activeColorID == 0) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                else {
                    Game.flagManager.activeColorID = 0;
                    Game.menuManager.getColorPicker().setActiveRGBColor(Game.flagManager.flagEdit.lDivisionColors.get(Game.flagManager.activeColorID).r, Game.flagManager.flagEdit.lDivisionColors.get(Game.flagManager.activeColorID).g, Game.flagManager.flagEdit.lDivisionColors.get(Game.flagManager.activeColorID).b);
                    Game.menuManager.getColorPicker().setVisible(true, ColorPicker.PickerAction.CREATE_CIV_DIVISION);
                    Game.menuManager.getColorPicker().setPosX(CreateCiv_Flag.this.getPosX() + CreateCiv_Flag.this.getWidth() + CFG.PADDING * 4);
                    Game.menuManager.getColorPicker().setPosY(this.getPosY());
                }
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                Game.flagManager.drawDivision_FlagFrameSize(oSB, this.getPosX() + this.getWidth() / 2 - 34 + iTranslateX, this.getPosY() + this.getHeight() / 2 - 22 + iTranslateY);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.lang.get("PickColor"), Images.pickerIcon, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonGame(">>", 1, -1, menuW - paddingLeft - CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, buttonH, true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                Game.flagManager.updateDivision(true);
                Game.menuManager.rebuildCreateCivFlag();
                Game.menuManager.addToastGold("ID: [" + Game.flagManager.flagEdit.iDivisionID + "/" + (Game.flagManager.lDivisions.size() - 1) + "]", Images.brush);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 1; i < Game.flagManager.lDivisions.get(Game.flagManager.flagEdit.iDivisionID).iLayers; ++i) {
            menuElements.add(new ButtonGame("", 1, textPosX, paddingLeft, buttonY, menuW - paddingLeft * 2, buttonH, true) {
                int iCurrent;
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.iCurrent = nCurrent;
                }
                
                @Override
                public int getCurrent() {
                    return this.iCurrent;
                }
                
                @Override
                protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                    Game.flagManager.drawDivision_FlagFrameSize(oSB, this.getPosX() + this.getWidth() / 2 - 34 + iTranslateX, this.getPosY() + this.getHeight() / 2 - 22 + iTranslateY, this.iCurrent);
                }
                
                @Override
                public void actionElement() {
                    if (Game.menuManager.getColorPicker().getVisible() && Game.flagManager.activeColorID == this.getCurrent()) {
                        Game.menuManager.getColorPicker().hideColorPicker();
                    }
                    else {
                        Game.flagManager.activeColorID = this.getCurrent();
                        Game.menuManager.getColorPicker().setActiveRGBColor(Game.flagManager.flagEdit.lDivisionColors.get(Game.flagManager.activeColorID).r, Game.flagManager.flagEdit.lDivisionColors.get(Game.flagManager.activeColorID).g, Game.flagManager.flagEdit.lDivisionColors.get(Game.flagManager.activeColorID).b);
                        Game.menuManager.getColorPicker().setVisible(true, ColorPicker.PickerAction.CREATE_CIV_DIVISION);
                        Game.menuManager.getColorPicker().setPosX(CreateCiv_Flag.this.getPosX() + CreateCiv_Flag.this.getWidth() + CFG.PADDING * 4);
                        Game.menuManager.getColorPicker().setPosY(this.getPosY());
                    }
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.lang.get("PickColor"), Images.pickerIcon, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            menuElements.get(menuElements.size() - 1).setCurrent(i);
        }
        for (int i = 0; i < Game.flagManager.flagEdit.lOverlays.size(); ++i) {
            menuElements.add(new ButtonGame("", 1, textPosX, paddingLeft, buttonY, CFG.BUTTON_WIDTH, buttonH, true) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildCreateCivFlag();
                }
                
                @Override
                protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                    ImageManager.getImage(Images.pickerIcon).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.pickerIcon).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pickerIcon).getHeight() / 2 + iTranslateY);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.lang.get("PickColor"), Images.pickerIcon, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonGame("", 1, textPosX, paddingLeft + CFG.BUTTON_WIDTH, buttonY, menuW - paddingLeft * 2 - CFG.BUTTON_WIDTH * 3, buttonH, true) {
                @Override
                public void actionElement() {
                }
                
                @Override
                protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                    Game.flagManager.drawDivision_FlagFrameSize(oSB, this.getPosX() + this.getWidth() / 2 - 34 + iTranslateX, this.getPosY() + this.getHeight() / 2 - 22 + iTranslateY);
                    Game.flagManager.drawOverlay_FlagFrameSize(oSB, this.getPosX() + this.getWidth() / 2 - 34 + iTranslateX, this.getPosY() + this.getHeight() / 2 - 22 + iTranslateY, this.iCurrent);
                }
            });
            menuElements.add(new ButtonGame("UP", 1, textPosX, menuW - paddingLeft - CFG.BUTTON_WIDTH * 2, buttonY, CFG.BUTTON_WIDTH, buttonH, true) {
                int iCurrent;
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.iCurrent = nCurrent;
                }
                
                @Override
                public int getCurrent() {
                    return this.iCurrent;
                }
                
                @Override
                public void actionElement() {
                    Game.flagManager.moveOverlayUp(this.getCurrent());
                    Game.menuManager.rebuildCreateCivFlag();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.lang.get("Up"), Images.upgrade, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(i);
            menuElements.add(new ButtonGame("X", 1, textPosX, menuW - paddingLeft - CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, buttonH, true) {
                int iCurrent;
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.iCurrent = nCurrent;
                }
                
                @Override
                public int getCurrent() {
                    return this.iCurrent;
                }
                
                @Override
                public void actionElement() {
                    Game.flagManager.removeOverlay(this.getCurrent());
                    Game.menuManager.rebuildCreateCivFlag();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.lang.get("Remove"), Images.x, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(i);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        this.initMenu(new MenuTitle(Game.lang.get("CustomizeFlag"), 1.0f, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, CFG.GAME_HEIGHT - titleHeight - menuY * 2, menuElements, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
}
