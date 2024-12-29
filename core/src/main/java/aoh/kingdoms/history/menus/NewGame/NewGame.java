// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.NewGame;

import aoh.kingdoms.history.menu_element.button.ButtonTopDate;
import aoh.kingdoms.history.mainGame.AI.Advantages.AI_Advantages;
import aoh.kingdoms.history.map.LuckyCivsManager;
import aoh.kingdoms.history.mainGame.AI.Colonization.AI_Colonization;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Government;
import aoh.kingdoms.history.map.province.ProvinceDrawArmy;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonTopOutliner;
import aoh.kingdoms.history.menusInGame.InGame;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.mainGame.Player.MessageTypes.PMessage;
import aoh.kingdoms.history.mainGame.Player.MessageTypes.PMessage_ChooseRivals;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.GameThreads.GameThread_Events;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.button.ButtonPlay;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class NewGame extends Menu
{
    public static final int ANIMATION_TIME = 500;
    public static long lTime;
    public static boolean settingsMenu;
    
    public NewGame() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new ButtonPlay(Game.lang.get("PLAY"), 1, -1, CFG.GAME_WIDTH - ImageManager.getImage(Images.buttonPlay).getWidth(), CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), false) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                NewGame.clearPlayerData();
                GameThread_Events.THREAD_TURN_ID = Game_Calendar.TURN_ID;
                if (Game.SPECTATOR_MODE) {
                    Game.player.iCivID = 0;
                }
                Game.player.playerStats.initStartingData();
                Game.stats.loadStats(Game.getCiv(Game.player.iCivID).getCivTag(), true);
                NewGame.initNewGame();
                Game.player.addMessage(new PMessage_ChooseRivals(Game.player.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                Game.menuManager.setViewID(View.IN_GAME);
                Game.menuManager.rebuildInGame_Messages();
                Game.menuManager.rebuildInGame_Wars();
                Game.menuManager.setOrderOfMenu_InGame();
                if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                }
                ProvinceDraw.updateDrawExtraDetails();
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_PLAY_NEW_GAME;
            }
        });
        menuElements.add(new ButtonPlay(Game.lang.get("Back"), 1, -1, 0, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                Game.setActiveProvinceID(-1);
                ProvinceBorderManager.clearProvinceBorder();
                Game.menuManager.setViewID(View.MAINMENU);
            }
        });
        menuElements.add(new ButtonTopOutliner(CFG.BUTTON_WIDTH + InGame.topRightPadding, 0, CFG.BUTTON_WIDTH, InGame.topStatsHeight, Images.encyclopedia) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                if (!NewGame.settingsMenu && Game.menuManager.getVisible_NewGame_Settings()) {
                    Game.menuManager.setVisible_NewGame_Settings(false);
                }
                else {
                    NewGame.settingsMenu = false;
                    Game.menuManager.rebuild_NewGame_Encyclopedia();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Encyclopedia"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.encyclopedia, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                oSB.setColor(this.getColor(isActive));
                ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 + InGame.outlinerExtraClassic - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new ButtonTopOutliner(0, 0, CFG.BUTTON_WIDTH, InGame.topStatsHeight, Images.settings) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                if (NewGame.settingsMenu && Game.menuManager.getVisible_NewGame_Settings()) {
                    Game.menuManager.setVisible_NewGame_Settings(false);
                }
                else {
                    NewGame.settingsMenu = true;
                    Game.menuManager.rebuild_NewGame_Settings();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Settings"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.settings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                oSB.setColor(this.getColor(isActive));
                ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 + InGame.outlinerExtraClassic - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        });
        NewGame.lTime = CFG.currentTimeMillis;
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT / 2);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.topStatsClassic).draw2(oSB, iTranslateX, iTranslateY, this.getMenuElement(this.getMenuElementsSize() - 2).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 2).getWidth() + InGame.topStatsPadding, ImageManager.getImage(Images.topStatsClassic).getHeight(), true, false);
        ImageManager.getImage(Images.topStatsClassic).draw2(oSB, iTranslateX, iTranslateY, this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + InGame.topStatsPadding, ImageManager.getImage(Images.topStatsClassic).getHeight(), true, false);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    public static final void setRandomCiv() {
        final List<Integer> tCivs = new ArrayList<Integer>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                tCivs.add(i);
            }
        }
        if (tCivs.size() > 0) {
            Game.player.iCivID = tCivs.get(Game.oR.nextInt(tCivs.size()));
            try {
                if (Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) {
                    Game.mapCoords.centerToProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                    ProvinceBorderManager.updateDrawProvinceBorder_SelectCiv_ByProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void initNewGame() {
        Game.setActiveProvinceID(-1);
        Game.gameThread.play = false;
        ProvinceBorderManager.clearProvinceBorder();
        Game.initDifficulty();
        Game.player.currSituation.buildPlayerLegaciesLVL();
        Game.player.currSituation.updateCurrentSituation();
        ProvinceDrawArmy.updateArmyImgID();
        Game.menuManager.rebuildInGame();
        hideMenus();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateArmyRegimentSize();
        }
        Game.player.loadFormableCivs();
        InGame_Court_Government.reloadFlags = true;
        if (Game.SPECTATOR_MODE) {
            Game.FOG_OF_WAR = false;
        }
        AI_Colonization.initData();
        Game.player.fog.buildFogOfWar(Game.player.iCivID);
        InGame_Court_Government.reloadFlags = true;
        Game.mapCoords.centerToProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
        Game.soundsManager.updateSoundsRandomTime();
        Game.addSimpleTask(new Game.SimpleTask("takeAdvantages_AI") {
            @Override
            public void update() {
                NewGame.takeAdvantages_AI();
            }
        });
        Game.addSimpleTask(new Game.SimpleTask("buildLuckyCivs") {
            @Override
            public void update() {
                LuckyCivsManager.buildLuckyCivs();
            }
        });
        if (GameValues.fog.HIDE_ARMIES) {
            updateArmiesText();
        }
    }
    
    public static final void updateArmiesText() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            for (int j = 0; j < Game.getProvince(i).getArmySize(); ++j) {
                Game.getProvince(i).getArmy(j).updateArmyWidth_Just(false);
            }
        }
    }
    
    public static final void takeAdvantages_AI() {
        for (int i = 1; i < Game.player.iCivID; ++i) {
            AI_Advantages.takeAdvantages(i);
        }
        for (int i = Game.player.iCivID + 1; i < Game.getCivsSize(); ++i) {
            AI_Advantages.takeAdvantages(i);
        }
    }
    
    public static final void clearPlayerData() {
        Game.player.civilizationGeneralsPool.clearData();
        Game.player.civAdvisorsPool_Administration.clearData();
        Game.player.civAdvisorsPool_Economy.clearData();
        Game.player.civAdvisorsPool_Technology.clearData();
        Game.player.civAdvisorsPool_Military.clearData();
        Game.player.clearEvents();
        Game.player.playerData.invasion.clearInvasions();
        Game.player.clearMessages();
        Game.player.clearNotifications();
        Game.player.clearBattleReport();
        Game.player.playerData.espionage.clearEspionageMissions();
        Game.player.clearPinnedArmy();
        Game.player.clearFormableCivs();
        Game.player.playerData.techQueue.clearTechQueue();
        Game.player.allowAIMove = false;
    }
    
    public static final void hideMenus() {
        try {
            if (Game.menuManager.getVisibleInGame_Console()) {
                Game.menuManager.setVisibleInGame_Console(false);
            }
            if (Game.menuManager.getVisibleInGame_SaveGame()) {
                Game.menuManager.setVisibleInGame_SaveGame(false);
            }
            if (Game.menuManager.getVisibleInGame_Escape()) {
                Game.menuManager.setVisibleInGame_Escape(false);
            }
            if (Game.menuManager.getVisibleInGame_PopUp()) {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
            if (Game.menuManager.getVisibleInGame_Right()) {
                Game.menuManager.rebuildInGame_Right();
            }
            if (Game.menuManager.getVisibleInGame_Notifications()) {
                Game.menuManager.rebuildInGame_Notifications();
            }
        }
        catch (final Exception ex) {}
        try {
            ButtonTopDate.updateMaxWidth();
        }
        catch (final Exception ex2) {}
        try {
            if (Game.menuManager.getVisibleInGame_Civ()) {
                Game.menuManager.setVisibleInGame_Civ(false);
            }
        }
        catch (final Exception ex3) {}
        try {
            if (Game.menuManager.getVisibleInGame_Court()) {
                Game.menuManager.setVisibleInGame_Court(false);
            }
        }
        catch (final Exception ex4) {}
    }
    
    static {
        NewGame.lTime = 0L;
        NewGame.settingsMenu = false;
    }
}
