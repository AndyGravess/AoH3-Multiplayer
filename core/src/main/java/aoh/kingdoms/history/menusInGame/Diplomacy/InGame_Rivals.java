// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.map.RivalsManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Rivals extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int rivalCivID;
    public static boolean backToRivalsList;
    
    public InGame_Rivals() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        final int buttonX = paddingLeft;
        final int rightW = menuWidth - paddingLeft * 2 - ButtonFlag.getButtonWidth() - CFG.PADDING * 2;
        final int rightH = (ButtonFlag.getButtonHeight() - CFG.PADDING) / 2;
        menuElements.add(new ButtonFlag(InGame_Rivals.rivalCivID, buttonX, buttonY, true) {
            @Override
            public int getFlagCivID() {
                return this.iCivID;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, Game.lang.get("Provinces") + ": " + Game.getCiv(this.iCivID).getNumOfProvinces()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Population") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.getCiv(this.iCivID).getPopulationTotal()), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new Text_StaticBG_RulerTitle(Game.getCiv(InGame_Rivals.rivalCivID).getCivName(), buttonX + ButtonFlag.getButtonWidth() + CFG.PADDING * 2, buttonY, rightW, rightH) {
            @Override
            public void actionElement() {
                Game.mapCoords.centerToProvinceID(Game.getCiv(InGame_Rivals.rivalCivID).getCapitalProvinceID());
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Rivals.rivalCivID, Game.lang.get("Provinces") + ": " + Game.getCiv(InGame_Rivals.rivalCivID).getNumOfProvinces()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.getCiv(InGame_Rivals.rivalCivID).getCapitalProvinceID()).getProvinceName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyLegacy") + ": ", "+" + CFG.getPrecision2(RivalsManager.getLegacy(Game.player.iCivID, InGame_Rivals.rivalCivID), 100), Images.legacy, buttonX + ButtonFlag.getButtonWidth() + CFG.PADDING * 2, buttonY + rightH + CFG.PADDING, rightW, rightH, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", "" + this.sText2, Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ButtonFlag.getButtonHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumManpower") + ": ", "+" + CFG.getPrecision2((float)RivalsManager.getManpower(Game.player.iCivID, InGame_Rivals.rivalCivID), 1), Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, rightH, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + this.sText2, Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
                if (InGame_Rivals.backToRivalsList && Game.getCiv(Game.player.iCivID).diplomacy.rivals.size() < GameValues.rivals.RIVALS_LIMIT) {
                    Game.menuManager.rebuildInGame_RivalsList();
                }
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Images.rivals) {
            @Override
            public void actionElement() {
                InGame_Rivals.confirm();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Rival") + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getCiv(InGame_Rivals.rivalCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rivals, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Rivalry"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Rivals.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Rivals.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_Rivals.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Rivals.lTime = CFG.currentTimeMillis;
    }
    
    public static void confirm() {
        if (Game.getCiv(Game.player.iCivID).diplomacy.addRival(Game.player.iCivID, InGame_Rivals.rivalCivID)) {
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = InGame_Rivals.rivalCivID;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("Rivals"), Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Rivals.rivalCivID).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
            if (Game.menuManager.getVisibleInGame_Civ()) {
                InGame_Civ.iRebuildToCivID = InGame_Rivals.rivalCivID;
                Game.menuManager.rebuildInGame_Civ(true);
                InGame_Civ.lTime = 0L;
            }
        }
        else {
            Game.menuManager.addToast_Error(Game.lang.get("Rivals") + ": " + Game.lang.get("Limit") + ": " + GameValues.rivals.RIVALS_LIMIT, Images.rivals);
        }
        Game.menuManager.setVisibleInGame_PopUp(false);
        if (InGame_Rivals.backToRivalsList && Game.getCiv(Game.player.iCivID).diplomacy.rivals.size() < GameValues.rivals.RIVALS_LIMIT) {
            Game.menuManager.rebuildInGame_RivalsList();
        }
    }
    
    static {
        InGame_Rivals.lTime = 0L;
        InGame_Rivals.backToRivalsList = false;
    }
}
