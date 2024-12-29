// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter2;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Mission;
import aoh.kingdoms.history.events.Event;
import aoh.kingdoms.history.events.EventsManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Title;
import aoh.kingdoms.history.menu_element.button.Button_GoldenAge;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Missions extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_Missions() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
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
        menuElements.add(new Text_Title_v2Center(Game.lang.get("GoldenAge"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new Button_GoldenAge(Game.lang.get("GAProsperity"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, Images.goldenGold, Game_Calendar.IMG_ECONOMY, Images.buildings, Game.lang.get("InvestedInEconomy") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).eventsData.getInvestedInEconomy() / 100.0f, 10) + " / " + CFG.getPrecision2(GameValues.goldenAge.GA_PROSPERITY_INVESTED_IN_ECONOMY[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeProsperity(), GameValues.goldenAge.GA_PROSPERITY_INVESTED_IN_ECONOMY.length - 1)], 10), Game.lang.get("EconomyBuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getEconomyBuildingsConstructed(), 10) + " / " + CFG.getPrecision2((float)GameValues.goldenAge.GA_PROSPERITY_ECONOMY_BUILDINGS[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeProsperity(), GameValues.goldenAge.GA_PROSPERITY_INVESTED_IN_ECONOMY.length - 1)], 10), "" + Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeProsperity() + " / " + GameValues.goldenAge.GA_PROSPERITY_INVESTED_IN_ECONOMY.length + "") {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Title(Images.goldenGold, "", Game.lang.get("GoldenAge"), Colors.HOVER_GOLD, Game.lang.get("GAProsperity"), "", Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", "+" + CFG.getPrecision2(GameValues.goldenAge.GA_PROSPERITY_MONTHLY_INCOME[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeProsperity(), GameValues.iGoldenAge_ProsperitySize - 1)], 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeProduction") + ": ", "+" + CFG.getPrecision2(GameValues.goldenAge.GA_PROSPERITY_INCOME_PRODUCTION[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeProsperity(), GameValues.iGoldenAge_ProsperitySize - 1)], 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", "" + Game.lang.get("XDays", GameValues.goldenAge.GA_PROSPERITY_DURATION_DAYS[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeProsperity(), GameValues.iGoldenAge_ProsperitySize - 1)]), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unlocked") + ": ", "" + Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeProsperity() + " / " + GameValues.goldenAge.GA_PROSPERITY_INVESTED_IN_ECONOMY.length, Images.goldenGold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                Game.gameThreadTurns.updateGoldenAge_Prosperity(Game.player.iCivID);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new Button_GoldenAge(Game.lang.get("GAMilitary"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, Images.goldenGreen, Game_Calendar.IMG_MANPOWER, Images.buildings, Game.lang.get("IncreasedManpower") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).eventsData.getIncreasedManpower() / 100.0f, 10) + " / " + CFG.getPrecision2(GameValues.goldenAge.GA_MILITARY_INCREASED_MANPOWER[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary(), GameValues.goldenAge.GA_MILITARY_INCREASED_MANPOWER.length - 1)], 10), Game.lang.get("MilitaryBuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getMilitaryBuildingsConstructed(), 10) + " / " + CFG.getPrecision2((float)GameValues.goldenAge.GA_MILITARY_MILITARY_BUILDINGS[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary(), GameValues.goldenAge.GA_MILITARY_INCREASED_MANPOWER.length - 1)], 10), "" + Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary() + " / " + GameValues.goldenAge.GA_MILITARY_INCREASED_MANPOWER.length + "") {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Title(Images.goldenGreen, "", Game.lang.get("GoldenAge"), Colors.HOVER_GOLD, Game.lang.get("GAMilitary"), "", Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", "+" + GameValues.goldenAge.GA_MILITARY_UNITS_ATTACK[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary(), GameValues.iGoldenAge_MilitarySize - 1)], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", "+" + GameValues.goldenAge.GA_MILITARY_UNITS_DEFENSE[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary(), GameValues.iGoldenAge_MilitarySize - 1)], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "+" + CFG.getPrecision2(100.0f * GameValues.goldenAge.GA_MILITARY_MAX_MANPOWER_PERC[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary(), GameValues.iGoldenAge_MilitarySize - 1)], 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", "" + Game.lang.get("XDays", GameValues.goldenAge.GA_MILITARY_DURATION_DAYS[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary(), GameValues.iGoldenAge_MilitarySize - 1)]), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unlocked") + ": ", "" + Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary() + " / " + GameValues.goldenAge.GA_MILITARY_INCREASED_MANPOWER.length, Images.goldenGreen, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                Game.gameThreadTurns.updateGoldenAge_Military(Game.player.iCivID);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new Button_GoldenAge(Game.lang.get("GAScience"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, Images.goldenBlue, Images.infrastructure, Images.buildings, Game.lang.get("DevelopedInfrastructure") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData.getDevelopedInfrastructure(), 10) + " / " + CFG.getPrecision2(GameValues.goldenAge.GA_SCIENCE_DEVELOPED_INFRASTRUCTURE[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeScience(), GameValues.goldenAge.GA_SCIENCE_DEVELOPED_INFRASTRUCTURE.length - 1)], 10), Game.lang.get("AdministrativeBuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getAdministrativeBuildingsConstructed(), 10) + " / " + CFG.getPrecision2((float)GameValues.goldenAge.GA_SCIENCE_ADMINISTRATIVE_BUILDINGS[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeScience(), GameValues.goldenAge.GA_SCIENCE_DEVELOPED_INFRASTRUCTURE.length - 1)], 10), "" + Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeScience() + " / " + GameValues.goldenAge.GA_SCIENCE_DEVELOPED_INFRASTRUCTURE.length + "") {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Title(Images.goldenBlue, "", Game.lang.get("GoldenAge"), Colors.HOVER_GOLD, Game.lang.get("GAScience"), "", Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", "+" + CFG.getPrecision2(GameValues.goldenAge.GA_SCIENCE_RESEARCH[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeScience(), GameValues.iGoldenAge_ScienceSize - 1)], 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", "+" + CFG.getPrecision2(GameValues.goldenAge.GA_SCIENCE_LEGACY[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeScience(), GameValues.iGoldenAge_ScienceSize - 1)], 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", "" + Game.lang.get("XDays", GameValues.goldenAge.GA_SCIENCE_DURATION_DAYS[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeScience(), GameValues.iGoldenAge_ScienceSize - 1)]), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unlocked") + ": ", "" + Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeScience() + " / " + GameValues.goldenAge.GA_SCIENCE_DEVELOPED_INFRASTRUCTURE.length, Images.goldenBlue, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                Game.gameThreadTurns.updateGoldenAge_Science(Game.player.iCivID);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("AvailableMissions"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
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
                menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.eventsScenario.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 0, i, EventsManager.eventsScenario.get(i).mission_image));
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
                menuElements.add(new Text_StaticBG_Mission("" + Game.lang.get(EventsManager.eventsScenario.get(i).title), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 4, paddingLeft, buttonY, menuWidth - paddingLeft * 2, 0, i, EventsManager.eventsScenario.get(i).mission_image));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                ++tAddedMissions;
            }
        }
        if (tAddedMissions == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
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
        if (tAddedMissions == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter2("", false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Missions.lTime;
            }
            
            @Override
            public int getFlagCivID() {
                return Game.player.iCivID;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Missions.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Missions.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Missions"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Missions.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_Missions.lTime = 0L;
    }
}
