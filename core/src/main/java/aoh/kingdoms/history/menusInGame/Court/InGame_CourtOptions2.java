// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.IconCourt;
import aoh.kingdoms.history.menu_element.SpaceHorizontal;
import aoh.kingdoms.history.mainGame.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.IconCourt_Notification;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menusInGame.InGame;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CourtOptions2 extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static int menuH;
    public static int buttonW_Draw;
    public static int idProvinces;
    public static int idCourt;
    public static int idExploitEconomy;
    public static int idCores;
    public static int idReligion;
    public static boolean isOptionHovered;
    public static int textMaxWidth;
    public static long TEXT_TIME;
    public static int TEXT_ANIMATION_TIME;
    public static int HEIGHT;
    
    public static final int getMenuWidth() {
        return ImageManager.getImage(Images.leftSideBar).getWidth() - InGame.leftSideBarInnerWidth;
    }
    
    public static final int getOtherMenuPosX() {
        return CFG.PADDING * 2 + getMenuWidth() + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
    }
    
    public static final int getOtherMenuPosX_2() {
        return (Game.settingsManager.enableHideSideMenu ? 0 : getMenuWidth()) + CFG.PADDING * 2 + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
    }
    
    @Override
    public int getPosX() {
        return super.getPosX() + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
    }
    
    public InGame_CourtOptions2() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING;
        final int menuX = 0;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY;
        final int buttonYPadding = CFG.PADDING * 2;
        final int buttonX = 0;
        int buttonY = 0;
        InGame_CourtOptions2.buttonW_Draw = getMenuWidth();
        final int menuWidth;
        final int buttonW = menuWidth = InGame_CourtOptions2.buttonW_Draw + CFG.PADDING * 2;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT2 : CFG.BUTTON_HEIGHT;
        int tID = 0;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Missions"), Images.missions, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                InGame_CourtOptions2.actionMissions();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Missions") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.missions, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.player.currSituation.missionCanBeUnlockedNum > 0) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Available") + ": ", CFG.FONT_REGULAR));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.player.currSituation.missionCanBeUnlockedNum, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.missions, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements, Game.player.currSituation.missionCanBeUnlockedNum == 0);
            }
            
            @Override
            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.missionCanBeUnlockedNum) {
                    this.setNumber(Game.player.currSituation.missionCanBeUnlockedNum);
                }
            }
            
            @Override
            protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                if (Game.player.currSituation.missionCanBeUnlocked) {
                    oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.65f));
                    ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.widthDraw * 3 / 4, this.getHeight());
                    oSB.setColor(Color.WHITE);
                }
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        InGame_CourtOptions.iGovernmentID = tID;
        menuElements.add(new IconCourt(Game.lang.get("Government"), Images.government, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    Game.menuManager.rebuildInGame_Government();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Government"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Capital") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle((Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) ? Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getProvinceName() : Game.lang.get("None"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Level") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(Game.player.iCivID), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        if (GameValues.zoom.SIDEBAR_ZOOM_SCALE_BUTTONS) {
            menuElements.add(new IconCourt(Game.lang.get("Scale") + " +", Images.plus, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
                @Override
                public void actionElement() {
                    Game.mapScale.scrollScale(-1);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Scale"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.plus, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new IconCourt(Game.lang.get("Scale") + " -", Images.minus, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
                @Override
                public void actionElement() {
                    Game.mapScale.scrollScale(1);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Scale"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.minus, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }
        InGame_CourtOptions.iLawID = tID;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Laws"), Images.law, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                InGame_CourtOptions2.actionLaws(InGame_CourtOptions.iLawID);
            }
            
            @Override
            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.newLawAvailableNum) {
                    this.setNumber(Game.player.currSituation.newLawAvailableNum);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Laws"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.law, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                    nData.add(new MenuElement_HoverElement_Type_Text("L", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements, !CFG.isDesktop());
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        InGame_CourtOptions.buildID = tID;
        menuElements.add(new IconCourt(Game.lang.get("Buildings"), Images.build, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                InGame_CourtOptions2.actionBuildings(InGame_CourtOptions.buildID);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Buildings"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.build, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).iBuildingsSize;
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(fAverage / Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.build, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                    nData.add(new MenuElement_HoverElement_Type_Text("B", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("TaxEfficiency"), Images.taxUp, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_IncreaseTaxEfficiency();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY);
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreaseTaxEfficiency"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.taxUp, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getTaxEfficiencyWithBonuses();
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(fAverage / Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.tax, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Economy"), Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_InvestInEconomy.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_InvestInEconomy();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INVEST_IN_ECONOMY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INVEST_IN_ECONOMY);
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("InvestInEconomy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY_UP, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getEconomyWithBonuses();
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(fAverage / Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Infrastructure"), Images.infrastructureUp, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    Game.menuManager.rebuildInGame_DevelopInfrastructure();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE);
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DevelopInfrastructure"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.infrastructureUp, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getInfrastructure();
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(fAverage / Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.infrastructure, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("GrowthRate"), Images.populationUp, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_IncreaseGrowthRate.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_IncreaseGrowthRate();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INCREASE_GROWTH_RATE);
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreasePopulationGrowthRate"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationUp, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getGrowthRateWithBonuses();
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(fAverage / Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.populationGrowth, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getImageID() {
                return Images.populationGrowth;
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Manpower"), Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_IncreaseManpower.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_IncreaseManpower();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_MANPOWER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INCREASE_MANPOWER);
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreaseManpower"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getManpower();
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(fAverage / Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getImageID() {
                return Game_Calendar.IMG_MANPOWER;
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        InGame_CourtOptions2.idCores = tID;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Cores"), Images.core, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                InGame_CourtOptions2.actionCores(this.id);
            }
            
            @Override
            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.nonCoreProvincesNum) {
                    this.setNumber(Game.player.currSituation.nonCoreProvincesNum);
                }
            }
            
            @Override
            protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                if (Game.player.currSituation.nonCoreProvinces) {
                    oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.65f));
                    ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.widthDraw * 3 / 4, this.getHeight());
                    oSB.setColor(Color.WHITE);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CoreConstruction"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.core, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                int tNum = 0;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).haveACore(Game.player.iCivID)) {
                        ++tNum;
                    }
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Cores") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + tNum + " / " + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.core, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        InGame_CourtOptions2.idReligion = tID;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Religion"), Images.religion, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                InGame_CourtOptions2.actionReligion(this.id);
            }
            
            @Override
            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.differentReligionProvincesNum) {
                    this.setNumber(Game.player.currSituation.differentReligionProvincesNum);
                }
            }
            
            @Override
            protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                if (Game.player.currSituation.differentReligionProvinces) {
                    oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.65f));
                    ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.widthDraw * 3 / 4, this.getHeight());
                    oSB.setColor(Color.WHITE);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Religion"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                int tNum = 0;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() == Game.getCiv(Game.player.iCivID).getReligionID()) {
                        ++tNum;
                    }
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + tNum + " / " + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.religion, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Sandbox"), Images.sandbox, buttonX, buttonY, buttonW, buttonH, tID++, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 42) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_Sandbox();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Sandbox"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.sandbox, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
            
            @Override
            public boolean getVisible() {
                return Game.SANDBOX;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, InGame_CourtOptions2.buttonW_Draw) {
            @Override
            public boolean getVisible() {
                return Game.SANDBOX;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        InGame_CourtOptions2.textMaxWidth = 0;
        for (int i = menuElements.size() - 1; i >= 0; --i) {
            if (menuElements.get(i).getTextWidth() > InGame_CourtOptions2.textMaxWidth) {
                InGame_CourtOptions2.textMaxWidth = menuElements.get(i).getTextWidth();
            }
        }
        InGame_CourtOptions2.textMaxWidth += CFG.PADDING * 6;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        this.initMenu(null, menuX, menuY, menuWidth + InGame_CourtOptions2.textMaxWidth + CFG.PADDING * 2, menuHeight, menuElements, !Game.settingsManager.enableHideSideMenu, false);
        this.drawScrollPositionAlways = false;
        this.drawScrollPositionAlways2 = false;
        InGame_CourtOptions2.HEIGHT = 0;
        for (int j = 0; j < this.getMenuElementsSize(); ++j) {
            if (this.getMenuElement(j).getVisible() && this.getMenuElement(j).getPosY() + this.getHeight() > InGame_CourtOptions2.HEIGHT) {
                InGame_CourtOptions2.HEIGHT = this.getMenuElement(j).getPosY() + this.getMenuElement(j).getHeight();
            }
        }
        InGame_CourtOptions2.HEIGHT += this.getPosY() + InGame.leftSideBarPadding;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        InGame_CourtOptions2.isOptionHovered = false;
        int i = 0;
        while (i < this.getMenuElementsSize()) {
            if (this.getMenuElement(i).getIsHovered()) {
                InGame_CourtOptions2.isOptionHovered = true;
                if (!MenuManager.orderOfMenuInGame && !Game.menuManager.getVisibleInGame_TechnologyTree()) {
                    Game.addSimpleTask_First(new Game.SimpleTask("setOrderOfMenu_InGame") {
                        @Override
                        public void update() {
                            Game.menuManager.setOrderOfMenu_InGame();
                        }
                    });
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        if (!InGame_CourtOptions2.isOptionHovered) {
            InGame_CourtOptions2.TEXT_TIME = CFG.currentTimeMillis;
        }
        if (Game.settingsManager.enableHideSideMenu && InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_Court(false);
    }
    
    public static void disableAllViews() {
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MOVE_CAPITAL) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        InGame_Court_Buildings2.oBuildingID = null;
    }
    
    @Override
    public void actionElement(final int nMenuElementID) {
        if (InGame_Court.iActiveCivID != Game.player.iCivID) {
            InGame_Court.iActiveCivID = Game.player.iCivID;
        }
        else if (Game.menuManager.getVisibleInGame_Court() && this.getMenuElement(nMenuElementID).getCurrent() == InGame_CourtOptions.iActiveID) {
            Game.menuManager.setVisibleInGame_Court(false);
            return;
        }
        super.actionElement(nMenuElementID);
    }
    
    public static final void actionCourt(final int id) {
        if (InGame_Court.iActiveCivID != Game.player.iCivID) {
            InGame_Court.iActiveCivID = Game.player.iCivID;
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Court();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }
        else if (id != InGame_CourtOptions.iActiveID || !InGame_Court.inCourt) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Court();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }
    }
    
    public static final void actionProvinces(final int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            InGame_Court_Provinces.sSearch = "";
            Game.menuManager.rebuildInGame_CourtProvinces();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }
    }
    
    public static final void actionBuildings(final int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Buildings2();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Buildings2();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }
    }
    
    public static final void actionLaws(final int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_LawsCourt();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }
    }
    
    public static final void actionCores(final int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Core();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CORE) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_CORE);
            }
        }
    }
    
    public static final void actionReligion(final int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Religion();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CONVERT_RELIGION) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_CONVERT_RELIGION);
            }
        }
    }
    
    public static void actionMissions() {
        if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
            Game.menuManager.setVisibleInGame_TechnologyTree(false);
        }
        else {
            Game.menuManager.rebuildInGame_MissionTree(false, true);
            Game.addSimpleTask(new Game.SimpleTask("setOrderOfMenu_TechnologyTree") {
                @Override
                public void update() {
                    Game.menuManager.setOrderOfMenu_TechnologyTree();
                }
            });
        }
    }
    
    static {
        InGame_CourtOptions2.menuH = 0;
        InGame_CourtOptions2.buttonW_Draw = 0;
        InGame_CourtOptions2.idProvinces = -77;
        InGame_CourtOptions2.idCourt = -9;
        InGame_CourtOptions2.idExploitEconomy = -94;
        InGame_CourtOptions2.idCores = -21;
        InGame_CourtOptions2.idReligion = -24;
        InGame_CourtOptions2.isOptionHovered = false;
        InGame_CourtOptions2.textMaxWidth = 0;
        InGame_CourtOptions2.TEXT_TIME = 0L;
        InGame_CourtOptions2.TEXT_ANIMATION_TIME = 165;
        InGame_CourtOptions2.HEIGHT = 100;
    }
}
