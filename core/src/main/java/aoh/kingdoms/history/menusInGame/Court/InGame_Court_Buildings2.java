// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyTree;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding_Special;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_Diplomacy;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.zOther.Point_XY;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_Buildings2 extends Menu
{
    public static Point_XY oBuildingID;
    public static int savePos_X;
    public static int savePos_Y;
    public static int saveMenuPos_X;
    public static int saveMenuPos_Y;
    public static boolean SHOW_BONUSES;
    public static int modeID;
    
    public InGame_Court_Buildings2() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING;
        final int buttonX = Images.boxTitleBORDERWIDTH;
        int buttonY = 0;
        final int tW0 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.8f);
        final int tW2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.2f);
        InGame_Court_Buildings2.oBuildingID = null;
        int tempAdded = 0;
        final int topButtonW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        final Civilization civPlayer = Game.getCiv(Game.player.iCivID);
        int buildingsConstructedAll = 0;
        for (int i = 0; i < civPlayer.getNumOfProvinces(); ++i) {
            buildingsConstructedAll += Game.getProvince(civPlayer.getProvinceID(i)).iBuildingsSize;
        }
        final int titleW = (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 4;
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("All"), Images.boxTitleBORDERWIDTH, buttonY, titleW, CFG.BUTTON_HEIGHT4, InGame_Court_Buildings2.modeID == -1) {
            @Override
            public void actionElement() {
                if (InGame_Court_Buildings2.modeID != -1) {
                    InGame_Court_Buildings2.modeID = -1;
                    Game.menuManager.rebuildInGame_Buildings2SavePos();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.buildings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Administration"), Images.boxTitleBORDERWIDTH + titleW, buttonY, titleW, CFG.BUTTON_HEIGHT4, InGame_Court_Buildings2.modeID == 0) {
            @Override
            public void actionElement() {
                if (InGame_Court_Buildings2.modeID != 0) {
                    InGame_Court_Buildings2.modeID = 0;
                    Game.menuManager.rebuildInGame_Buildings2SavePos();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Military"), Images.boxTitleBORDERWIDTH + titleW * 2, buttonY, titleW, CFG.BUTTON_HEIGHT4, InGame_Court_Buildings2.modeID == 1) {
            @Override
            public void actionElement() {
                if (InGame_Court_Buildings2.modeID != 1) {
                    InGame_Court_Buildings2.modeID = 1;
                    Game.menuManager.rebuildInGame_Buildings2SavePos();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Economy"), Images.boxTitleBORDERWIDTH + titleW * 3, buttonY, titleW, CFG.BUTTON_HEIGHT4, InGame_Court_Buildings2.modeID == 2) {
            @Override
            public void actionElement() {
                if (InGame_Court_Buildings2.modeID != 2) {
                    InGame_Court_Buildings2.modeID = 2;
                    Game.menuManager.rebuildInGame_Buildings2SavePos();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonCurrentSituation("" + CFG.getNumberWithSpaces("" + buildingsConstructedAll), Images.buildings, paddingLeft, buttonY, topButtonW, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingsConstructed") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.buildings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonCurrentSituation(Game.lang.get("Bonuses"), InGame_Court_Buildings2.SHOW_BONUSES ? Images.v : Images.x, paddingLeft + CFG.PADDING + topButtonW, buttonY, topButtonW, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                InGame_Court_Buildings2.SHOW_BONUSES = !InGame_Court_Buildings2.SHOW_BONUSES;
                Game.menuManager.rebuildInGame_Buildings2SavePos();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Bonuses"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.information, CFG.PADDING, 0));
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
        for (int x = (InGame_Court_Buildings2.modeID == -1) ? 0 : InGame_Court_Buildings2.modeID; x < ((InGame_Court_Buildings2.modeID == -1) ? 3 : (InGame_Court_Buildings2.modeID + 1)); ++x) {
            menuElements.add(new Text_Title_v2Center((x == 0) ? Game.lang.get("Administration") : ((x == 1) ? Game.lang.get("Military") : Game.lang.get("Economy")), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            tempAdded = 0;
            for (int j = 0; j < BuildingsManager.buildingsSize; ++j) {
                for (int k = 0; k < BuildingsManager.buildingSize.get(j); ++k) {
                    if (BuildingsManager.buildings.get(j).GroupID == x && !BuildingsManager.buildings.get(j).UniqueCapitalBuilding && civPlayer.isBuildingResearched(j, k) && (BuildingsManager.buildings.get(j).RequiredGovernmentID < 0 || BuildingsManager.buildings.get(j).RequiredGovernmentID == civPlayer.getIdeologyID()) && (BuildingsManager.buildings.get(j).RequiredReligionID < 0 || BuildingsManager.buildings.get(j).RequiredReligionID == civPlayer.getReligionID())) {
                        ++tempAdded;
                        int tNum = 0;
                        int tPossible = 0;
                        for (int l = 0; l < civPlayer.getNumOfProvinces(); ++l) {
                            if (!BuildingsManager.buildings.get(j).SeaAccessRequired || Game.getProvince(civPlayer.getProvinceID(l)).getLevelOfPort() >= 0) {
                                if (Game.getProvince(civPlayer.getProvinceID(l)).buildingBuilt(j, k)) {
                                    ++tNum;
                                }
                                ++tPossible;
                            }
                        }
                        int tempY = buttonY;
                        menuElements.add(new ButtonBuilding_Special(true, j, k, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true, "" + tNum + " / " + tPossible) {
                            @Override
                            public void actionElement() {
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                                InGame_Court_Buildings2.oBuildingID = new Point_XY(this.getValue1(), this.getValue2());
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_BUILDING);
                                Game.menuManager.savePosInGame_Buildings2();
                                Game.menuManager.rebuildInGame_Build();
                                Game.menuManager.setVisibleInGame_Court(true);
                                InGame_Court.lTime = 0L;
                            }
                        });
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                        if (InGame_Court_Buildings2.SHOW_BONUSES) {
                            final List<MenuElement> bonuses = ButtonBuilding.getBuildingBonuses(j, k, paddingLeft + CFG.PADDING, menuWidth);
                            if (!bonuses.isEmpty()) {
                                for (int a = 0; a < bonuses.size(); ++a) {
                                    bonuses.get(a).setPosY(buttonY);
                                    menuElements.add(bonuses.get(a));
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                                }
                                tempY = buttonY - tempY;
                                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - tempY, menuWidth - paddingLeft * 2, tempY));
                                buttonY += CFG.PADDING;
                            }
                        }
                    }
                }
            }
            if (tempAdded == 0) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            }
        }
        if (InGame_Court_Buildings2.modeID == -1 || InGame_Court_Buildings2.modeID == 2) {
            menuElements.add(new Text_Title_v2Center(Game.lang.get("Resources"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            tempAdded = 0;
            for (int m = BuildingsManager.buildingsResourceStartID; m < BuildingsManager.buildingsResourceSize; ++m) {
                for (int j2 = 0; j2 < BuildingsManager.buildingSize.get(m); ++j2) {
                    if (BuildingsManager.buildings.get(m).RequiredResource >= 0 && !BuildingsManager.buildings.get(m).UniqueCapitalBuilding && civPlayer.isBuildingResearched(m, j2) && (BuildingsManager.buildings.get(m).RequiredGovernmentID < 0 || BuildingsManager.buildings.get(m).RequiredGovernmentID == civPlayer.getIdeologyID()) && (BuildingsManager.buildings.get(m).RequiredReligionID < 0 || BuildingsManager.buildings.get(m).RequiredReligionID == civPlayer.getReligionID())) {
                        int tNum2 = 0;
                        int tPossible2 = 0;
                        for (int k2 = 0; k2 < civPlayer.getNumOfProvinces(); ++k2) {
                            if (Game.getProvince(civPlayer.getProvinceID(k2)).buildingBuilt(m, j2)) {
                                ++tNum2;
                            }
                            if (Game.getProvince(civPlayer.getProvinceID(k2)).getResourceID() == BuildingsManager.buildings.get(m).RequiredResource) {
                                ++tPossible2;
                            }
                        }
                        if (tPossible2 > 0) {
                            ++tempAdded;
                            int tempY2 = buttonY;
                            menuElements.add(new ButtonBuilding_Special(true, m, j2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true, "" + tNum2 + " / " + tPossible2) {
                                @Override
                                public void actionElement() {
                                    Game.gameActiveProvince.resetLastActiveProvince();
                                    Game.setActiveProvinceID(-1);
                                    Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                                    InGame_Court_Buildings2.oBuildingID = new Point_XY(this.getValue1(), this.getValue2());
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_BUILDING);
                                    Game.menuManager.savePosInGame_Buildings2();
                                    Game.menuManager.rebuildInGame_Build();
                                    Game.menuManager.setVisibleInGame_Court(true);
                                    InGame_Court.lTime = 0L;
                                }
                            });
                            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                            if (InGame_Court_Buildings2.SHOW_BONUSES) {
                                final List<MenuElement> bonuses2 = ButtonBuilding.getBuildingBonuses(m, j2, paddingLeft + CFG.PADDING, menuWidth);
                                if (!bonuses2.isEmpty()) {
                                    for (int a2 = 0; a2 < bonuses2.size(); ++a2) {
                                        bonuses2.get(a2).setPosY(buttonY);
                                        menuElements.add(bonuses2.get(a2));
                                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                                    }
                                    tempY2 = buttonY - tempY2;
                                    menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - tempY2, menuWidth - paddingLeft * 2, tempY2));
                                    buttonY += CFG.PADDING;
                                }
                            }
                        }
                    }
                }
            }
            if (GameValues.inGame.SHOW_BUILDINGS_NO_PROVINCES_SIDEBAR) {
                if (InGame_Court_Buildings2.modeID == -1) {
                    boolean addedTitleNoProvinces = false;
                    for (int j = BuildingsManager.buildingsResourceStartID; j < BuildingsManager.buildingsResourceSize; ++j) {
                        for (int k = 0; k < BuildingsManager.buildingSize.get(j); ++k) {
                            if (BuildingsManager.buildings.get(j).RequiredResource >= 0 && !BuildingsManager.buildings.get(j).UniqueCapitalBuilding && civPlayer.isBuildingResearched(j, k)) {
                                int tNum = 0;
                                int tPossible = 0;
                                for (int l = 0; l < civPlayer.getNumOfProvinces(); ++l) {
                                    if (Game.getProvince(civPlayer.getProvinceID(l)).buildingBuilt(j, k)) {
                                        ++tNum;
                                    }
                                    if (Game.getProvince(civPlayer.getProvinceID(l)).getResourceID() == BuildingsManager.buildings.get(j).RequiredResource) {
                                        ++tPossible;
                                    }
                                }
                                if (tPossible == 0) {
                                    if (!addedTitleNoProvinces) {
                                        menuElements.add(new Text_Title(Game.lang.get("Provinces") + ": 0", -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
                                            @Override
                                            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                                            }
                                        });
                                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                                        addedTitleNoProvinces = true;
                                    }
                                    ++tempAdded;
                                    int tempY = buttonY;
                                    menuElements.add(new ButtonBuilding_Special(true, j, k, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true, "" + tNum + " / " + tPossible) {
                                        @Override
                                        public void actionElement() {
                                            Game.gameActiveProvince.resetLastActiveProvince();
                                            Game.setActiveProvinceID(-1);
                                            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                                            InGame_Court_Buildings2.oBuildingID = new Point_XY(this.getValue1(), this.getValue2());
                                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_BUILDING);
                                            Game.menuManager.savePosInGame_Buildings2();
                                            Game.menuManager.rebuildInGame_Build();
                                            Game.menuManager.setVisibleInGame_Court(true);
                                            InGame_Court.lTime = 0L;
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                                    if (InGame_Court_Buildings2.SHOW_BONUSES) {
                                        final List<MenuElement> bonuses = ButtonBuilding.getBuildingBonuses(j, k, paddingLeft + CFG.PADDING, menuWidth);
                                        if (!bonuses.isEmpty()) {
                                            for (int a = 0; a < bonuses.size(); ++a) {
                                                bonuses.get(a).setPosY(buttonY);
                                                menuElements.add(bonuses.get(a));
                                                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                                            }
                                            tempY = buttonY - tempY;
                                            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - tempY, menuWidth - paddingLeft * 2, tempY));
                                            buttonY += CFG.PADDING;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (tempAdded == 0) {
                    menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                }
            }
        }
        if (GameValues.inGame.SHOW_TO_BE_RESEARCHED_BUILDINGS_SIDEBAR && InGame_Court_Buildings2.modeID == -1) {
            menuElements.add(new Text_Title(Game.lang.get("ToBeResearched"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            tempAdded = 0;
            for (int x = 0; x < 3; ++x) {
                for (int j = 0; j < BuildingsManager.buildingsSize; ++j) {
                    for (int k = 0; k < BuildingsManager.buildingSize.get(j); ++k) {
                        if (BuildingsManager.buildings.get(j).GroupID == x && !BuildingsManager.buildings.get(j).UniqueCapitalBuilding && !civPlayer.isBuildingResearched(j, k)) {
                            ++tempAdded;
                            int tNum = 0;
                            int tPossible = 0;
                            for (int l = 0; l < civPlayer.getNumOfProvinces(); ++l) {
                                if (Game.getProvince(civPlayer.getProvinceID(l)).buildingBuilt(j, k)) {
                                    ++tNum;
                                }
                                ++tPossible;
                            }
                            menuElements.add(new ButtonBuilding_Special(true, j, k, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, false, "" + tNum + " / " + tPossible) {
                                @Override
                                public void actionElement() {
                                    if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                                        Game.menuManager.setVisibleInGame_TechnologyTree(false);
                                    }
                                    else {
                                        Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                                        InGame_TechnologyTree.centerToTechID = BuildingsManager.buildings.get(this.getValue1()).RequiredTechID[this.getValue2()];
                                        Game.menuManager.rebuildInGame_TechnologyTree(false, true);
                                    }
                                }
                            });
                            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                        }
                    }
                }
            }
            if (tempAdded == 0) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            }
            tempAdded = 0;
            menuElements.add(new Text_Title(Game.lang.get("ToBeResearched") + ": " + Game.lang.get("Resources"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            for (int m = BuildingsManager.buildingsResourceStartID; m < BuildingsManager.buildingsResourceSize; ++m) {
                for (int j2 = 0; j2 < BuildingsManager.buildingSize.get(m); ++j2) {
                    if (BuildingsManager.buildings.get(m).RequiredResource >= 0 && !BuildingsManager.buildings.get(m).UniqueCapitalBuilding && !civPlayer.isBuildingResearched(m, j2)) {
                        ++tempAdded;
                        int tNum2 = 0;
                        int tPossible2 = 0;
                        for (int k2 = 0; k2 < civPlayer.getNumOfProvinces(); ++k2) {
                            if (Game.getProvince(civPlayer.getProvinceID(k2)).buildingBuilt(m, j2)) {
                                ++tNum2;
                            }
                            if (Game.getProvince(civPlayer.getProvinceID(k2)).getResourceID() == BuildingsManager.buildings.get(m).RequiredResource) {
                                ++tPossible2;
                            }
                        }
                        menuElements.add(new ButtonBuilding_Special(true, m, j2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, false, "" + tNum2 + " / " + tPossible2) {
                            @Override
                            public void actionElement() {
                                if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                                    Game.menuManager.setVisibleInGame_TechnologyTree(false);
                                }
                                else {
                                    Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                                    InGame_TechnologyTree.centerToTechID = BuildingsManager.buildings.get(this.getValue1()).RequiredTechID[this.getValue2()];
                                    Game.menuManager.rebuildInGame_TechnologyTree(false, true);
                                }
                            }
                        });
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                    }
                }
            }
            if (tempAdded == 0) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            }
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("Buildings"));
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - InGame_CourtOptions.menuH + iTranslateY, this.getWidth(), this.getHeight() + InGame_CourtOptions.menuH + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civOptionsOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civOptionsOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civOptionsOver).getHeight()));
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
    
    static {
        InGame_Court_Buildings2.oBuildingID = null;
        InGame_Court_Buildings2.savePos_X = 0;
        InGame_Court_Buildings2.savePos_Y = 0;
        InGame_Court_Buildings2.saveMenuPos_X = 0;
        InGame_Court_Buildings2.saveMenuPos_Y = 0;
        InGame_Court_Buildings2.SHOW_BONUSES = true;
        InGame_Court_Buildings2.modeID = -1;
    }
}
