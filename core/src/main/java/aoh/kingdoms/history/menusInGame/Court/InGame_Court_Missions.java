// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Mission;
import aoh.kingdoms.history.events.Event;
import aoh.kingdoms.history.events.EventsManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_Missions extends Menu
{
    public InGame_Court_Missions() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonX = paddingLeft;
        final int buttonYPadding = CFG.PADDING;
        int buttonY = CFG.PADDING;
        final int iCivID = Game.player.iCivID;
        int tAddedMissions = 0;
        menuElements.add(new ButtonCurrentSituation(Game.lang.get(GameValues.court.COUNCIL_NAME), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonCurrentSituation(Game.lang.get("GoldenAge"), Images.goldenGold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                InGame_CourtOptions.disableAllViews();
                Game.menuManager.rebuildInGame_CourtGoldenAges();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("GoldenAge"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goldenGold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Missions") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.lang.get("Missions") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT, Game.player.iCivID) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_MissionTree(false, true);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Missions") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.missions, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Events"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 0; i < EventsManager.iEventsSize; ++i) {
            if (EventsManager.events.get(i).show_in_missions && !Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.events.get(i).id)) {
                menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.events.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 0, i, EventsManager.events.get(i).mission_image));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                ++tAddedMissions;
            }
        }
        for (int i = 0; i < EventsManager.iEventsScenarioSize; ++i) {
            if (EventsManager.eventsScenario.get(i).show_in_missions && !Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.eventsScenario.get(i).id)) {
                menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.eventsScenario.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 3, i, EventsManager.eventsScenario.get(i).mission_image));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                ++tAddedMissions;
            }
        }
        if (tAddedMissions == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        tAddedMissions = 0;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Completed"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 0; i < EventsManager.iEventsSize; ++i) {
            if (EventsManager.events.get(i).show_in_missions && Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.events.get(i).id)) {
                menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.events.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 0, i, EventsManager.events.get(i).mission_image));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                ++tAddedMissions;
            }
        }
        for (int i = 0; i < EventsManager.iEventsScenarioSize; ++i) {
            if (EventsManager.eventsScenario.get(i).show_in_missions && Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.eventsScenario.get(i).id)) {
                menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.eventsScenario.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 3, i, EventsManager.eventsScenario.get(i).mission_image));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                ++tAddedMissions;
            }
        }
        if (tAddedMissions == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (GameValues.events.SHOW_EVENTS_IN_MISSION_MENU) {
            tAddedMissions = 0;
            menuElements.add(new Text_Title_v2Center(Game.lang.get("Events"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            for (int i = 0; i < EventsManager.iEventsSize; ++i) {
                if (!EventsManager.events.get(i).show_in_missions && !Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.events.get(i).id)) {
                    menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.events.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 0, i, EventsManager.events.get(i).mission_image));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    ++tAddedMissions;
                }
            }
            for (int i = 0; i < EventsManager.iEventsScenarioSize; ++i) {
                if (!EventsManager.eventsScenario.get(i).show_in_missions && !Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.eventsScenario.get(i).id)) {
                    menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.eventsScenario.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 3, i, EventsManager.eventsScenario.get(i).mission_image));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    ++tAddedMissions;
                }
            }
            if (tAddedMissions == 0) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("Events"));
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - InGame_CourtOptions.menuH + iTranslateY, this.getWidth(), this.getHeight() + InGame_CourtOptions.menuH + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Court.lTime = CFG.currentTimeMillis;
        InGame_Court.lTime2 = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
}
