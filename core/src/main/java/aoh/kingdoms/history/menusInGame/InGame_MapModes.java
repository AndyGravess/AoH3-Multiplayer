// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Minimap;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.menu_element.button.Button32Padd_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.Button32Padd;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu.Menu;

public class InGame_MapModes extends Menu
{
    public static int maxWidth;
    
    public static void updateMaxWidth(final int imgID) {
        if (ImageManager.getImage(imgID).getWidth() > InGame_MapModes.maxWidth) {
            InGame_MapModes.maxWidth = ImageManager.getImage(imgID).getWidth();
        }
    }
    
    public InGame_MapModes() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int nY = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int nX = CFG.PADDING;
        updateMaxWidth(Images.mapModesCivs);
        updateMaxWidth(Images.population);
        updateMaxWidth(Game_Calendar.IMG_ECONOMY);
        updateMaxWidth(Images.tax);
        updateMaxWidth(Images.mapModesTerrain);
        updateMaxWidth(Images.goldPositive);
        for (int a = 0; a < GameValues.mapModes.MAP_MODES_ORDER.length; ++a) {
            if (GameValues.mapModes.MAP_MODES_ORDER[a] == 0) {
                menuElements.add(new Button32Padd(Images.mapModesCivs, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Political"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.mapModesCivs, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 1) {
                menuElements.add(new Button32Padd(Images.mapModesTerrain, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_TERRAIN);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_TERRAIN;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("TerrainType"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.mapModesTerrain, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 2) {
                menuElements.add(new Button32Padd(Images.mapModesGoods, nX, nY) {
                    @Override
                    public void actionElement() {
                        InGame_MapModes.actionGoods();
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_GOODS;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Resources"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.mapModesGoods, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 3) {
                menuElements.add(new Button32Padd(Images.mapModesWonders, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_WONDERS);
                        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WONDERS) {
                            Game.gameActiveProvince.resetLastActiveProvince();
                            Game.setActiveProvinceID(-1);
                            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                        }
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WONDERS;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Wonders"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.mapModesWonders, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 4) {
                menuElements.add(new Button32Padd(Images.population, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_POPULATION);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_POPULATION;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Population"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("PopulationMapModeDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("PopulationMapModeDesc2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 5) {
                menuElements.add(new Button32Padd(Game_Calendar.IMG_ECONOMY, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_ECONOMY);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_ECONOMY;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Economy"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("EconomyMapModeDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("EconomyMapModeDesc2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 6) {
                menuElements.add(new Button32Padd(Images.tax, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_TAX);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_TAX;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("TaxEfficiency"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.tax, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 7) {
                menuElements.add(new Button32Padd(Images.government, nX, nY) {
                    @Override
                    public void actionElement() {
                        InGame_MapModes.actionGovernment();
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_GOVERNMENT;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Government"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 8) {
                menuElements.add(new Button32Padd(Images.mapModesReligion, nX, nY) {
                    @Override
                    public void actionElement() {
                        InGame_MapModes.actionReligion();
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RELIGION;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Religion"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.mapModesReligion, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 9) {
                menuElements.add(new Button32Padd(Images.revolutionRisk, nX, nY) {
                    @Override
                    public void actionElement() {
                        InGame_MapModes.actionUnrest();
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_UNREST;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Unrest"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.revolutionRisk, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 10) {
                menuElements.add(new Button32Padd(Images.mapModesInfrastructure, nX, nY) {
                    @Override
                    public void actionElement() {
                        InGame_MapModes.actionInfrastructure();
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INFRASTRUCTURE;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Infrastructure"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.mapModesInfrastructure, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 11) {
                menuElements.add(new Button32Padd(Images.fort, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFENSE_LEVEL);
                        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFENSE_LEVEL) {
                            Game.gameActiveProvince.resetLastActiveProvince();
                            Game.setActiveProvinceID(-1);
                            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                        }
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFENSE_LEVEL;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("DefenseLevel"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.fort, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 12) {
                menuElements.add(new Button32Padd(Images.disease, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DISEASES);
                        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DISEASES) {
                            Game.gameActiveProvince.resetLastActiveProvince();
                            Game.setActiveProvinceID(-1);
                            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                        }
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DISEASES;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Diseases"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.disease, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 13) {
                menuElements.add(new Button32Padd(Images.loot, nX, nY) {
                    @Override
                    public void actionElement() {
                        InGame_MapModes.actionLoot();
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_LOOT;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Loot"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loot, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 14) {
                menuElements.add(new Button32Padd(Images.devastation, nX, nY) {
                    @Override
                    public void actionElement() {
                        InGame_MapModes.actionDevastation();
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVASTATION;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Devastation"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.devastation, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 15) {
                menuElements.add(new Button32Padd_Text(Images.war, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_WARS);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WARS;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("CurrentWars"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Wars") + ": ", CFG.getNumberWithSpaces("" + WarManager.iWarsSize), Images.war, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public String getTextToDraw() {
                        if (this.value != WarManager.iWarsSize) {
                            this.value = WarManager.iWarsSize;
                            if (this.value <= 0) {
                                this.setText("");
                            }
                            else {
                                this.setText("" + Math.min(GameValues.mapModes.WAR_BUTTON_WARS_MAX, this.value));
                            }
                        }
                        return super.getTextToDraw();
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 16) {
                menuElements.add(new Button32Padd_Text(Images.alliance, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_ALLIANCES);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_ALLIANCES;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Alliances"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 17) {
                menuElements.add(new Button32Padd_Text(Images.defensivePact, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFENSIVE_PACTS);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFENSIVE_PACTS;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("DefensivePacts"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.defensivePact, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            else if (GameValues.mapModes.MAP_MODES_ORDER[a] == 18) {
                menuElements.add(new Button32Padd_Text(Images.nonAggression, nX, nY) {
                    @Override
                    public void actionElement() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_NON_AGGRESSION_PACTS);
                    }
                    
                    @Override
                    public boolean isActiveView() {
                        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NON_AGGRESSION_PACTS;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("NonAggressionPacts"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nonAggression, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
            }
            nX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (GameValues.mapModes.ENABLE_PROVINCE_INCOME_MAP_MODE) {
            menuElements.add(new Button32Padd(Images.goldPositive, nX, nY) {
                @Override
                public boolean getClickable() {
                    return true;
                }
                
                @Override
                public void actionElement() {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_INCOME);
                }
                
                @Override
                public boolean isActiveView() {
                    return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_INCOME;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MapMode") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("ProvinceIncome"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goldPositive, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ProvinceIncomeMapModeDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ProvinceIncomeMapModeDesc2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            nX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        this.initMenu(null, CFG.GAME_WIDTH - Game.mapBG.minimapOfCivilizations.getWidth() - Minimap.getPadding() * 2, 150, Game.mapBG.minimapOfCivilizations.getWidth() + Minimap.getPadding() * 2 - ImageManager.getImage(Images.arrowUpDown).getWidth() - CFG.PADDING * 4, ImageManager.getImage(Images.arrowUpDown).getHeight() + CFG.PADDING * 4 + Images.boxTitleBORDERWIDTH, menuElements, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public int getMenuPosY() {
        return CFG.GAME_HEIGHT - Game.mapBG.minimapOfCivilizations.getHeight() - Minimap.getPadding() * 2 - this.getHeight() + InGame.iMinimapPosY;
    }
    
    @Override
    public int getPosY() {
        return CFG.GAME_HEIGHT - Game.mapBG.minimapOfCivilizations.getHeight() - Minimap.getPadding() * 2 - this.getHeight() + InGame.iMinimapPosY;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGame();
    }
    
    @Override
    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
    }
    
    public static void actionReligion() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_RELIGION);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RELIGION) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        }
    }
    
    public static void actionGovernment() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_GOVERNMENT);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_GOVERNMENT) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        }
    }
    
    public static void actionGoods() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_GOODS);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_GOODS) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        }
    }
    
    public static void actionUnrest() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_UNREST);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_UNREST) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        }
    }
    
    public static void actionInfrastructure() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INFRASTRUCTURE);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INFRASTRUCTURE) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        }
    }
    
    public static void actionLoot() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_LOOT);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_LOOT) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        }
    }
    
    public static void actionDevastation() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEVASTATION);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVASTATION) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        }
    }
    
    static {
        InGame_MapModes.maxWidth = 0;
    }
}
