// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Buildings.InGame_Destroy;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.Toast;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_ActiveBuilding;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_BuildingSlots;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc3;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc2_Special;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding_Special;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_Build extends Menu
{
    public static int iSortID;
    
    public InGame_Court_Build() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING;
        int buttonX = Images.boxTitleBORDERWIDTH;
        int buttonY = buttonYPadding;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        final int tW0 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.8f);
        final int tW2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.2f);
        final int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.35f);
        final int r0W2 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        final int r0W3 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        final int r0W4 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.25f);
        final int r1W = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.35f);
        final int r1W2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.2f);
        final int r1W3 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.2f);
        final int r1W4 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.25f);
        int tNum = 0;
        int tPossible = 0;
        for (int k = 0; k < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++k) {
            try {
                if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(k)).isOccupied()) {
                    if (!BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).SeaAccessRequired || Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(k)).getLevelOfPort() >= 0) {
                        if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(k)).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                            ++tNum;
                        }
                        if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource < 0 || BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource == Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(k)).getResourceID()) {
                            ++tPossible;
                        }
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        menuElements.add(new ButtonBuilding_Special(true, InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY(), paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true, "" + tNum + " / " + tPossible) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Buildings2_Back();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = ButtonBuilding.getHoverBuilding(this.building, this.buildingID, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        if (!Game.lang.get(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).NameDesc[InGame_Court_Buildings2.oBuildingID.getPosY()]).equals(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).NameDesc[InGame_Court_Buildings2.oBuildingID.getPosY()])) {
            menuElements.add(new Text_Desc2_Special(Game.lang.get(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).NameDesc[InGame_Court_Buildings2.oBuildingID.getPosY()]), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Buildings2_Back();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Back"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).ResearchPoints != null) {
            int tempY = buttonY;
            buttonY += CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("CapitalCity") + ": " + Game.getCiv(Game.player.iCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(Game.player.iCivID), Images.capital, paddingLeft + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 4, 0, CFG.FONT_REGULAR_SMALL) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getCapitalLevel() >= Game.getCapital_MaxLvl(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(Game.player.iCivID), Images.capital);
                    }
                    else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 8) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_UpgradeCapital();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_Court_Government.getHoverCapitalCity(true, Game.player.iCivID);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            menuElements.add(new Text_Desc3(Game.lang.get("ResearchPerMonth") + ": +" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fResearchPerMonth, 100) + " / " + CFG.getPrecision2(TechnologyTree.getMaxResearch(Game.player.iCivID), 100), paddingLeft + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 2, CFG.FONT_REGULAR_SMALL) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getCapitalLevel() >= Game.getCapital_MaxLvl(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(Game.player.iCivID), Images.capital);
                    }
                    else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 8) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_UpgradeCapital();
                    }
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (Game.getCiv(Game.player.iCivID).fResearchPerMonth >= TechnologyTree.getMaxResearch(Game.player.iCivID)) {
                        return Colors.HOVER_NEGATIVE;
                    }
                    return super.getColor(isActive);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyChange") + ": ", "+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fResearchPerMonth, 100) + " / " + CFG.getPrecision2(TechnologyTree.getMaxResearch(Game.player.iCivID), 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumResearch") + ": ", CFG.getPrecision2(TechnologyTree.getMaxResearch(Game.player.iCivID), 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("MaximumResearch") + " = " + CFG.getPrecision2(GameValues.research.MAX_RESEARCH_BASE, 100) + " + " + Game.lang.get("Capital") + ", " + Game.lang.get("GrowthRate") + " * " + CFG.getPrecision2(GameValues.research.MAX_RESEARCH_PER_GROWTH_RATE_IN_CAPITAL, 100) + " + " + Game.lang.get("CapitalCity") + ", " + Game.lang.get("Level") + " * " + CFG.getPrecision2(GameValues.capital.CAPITAL_MAX_RESEARCH_PER_LVL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CapitalCity") + ": ", Game.getCiv(Game.player.iCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(Game.player.iCivID), Images.capital, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            tempY = buttonY - tempY;
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - tempY, menuWidth - paddingLeft * 2, tempY));
            buttonY += buttonYPadding;
        }
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Build.iSortID == 0 || InGame_Court_Build.iSortID == 1, InGame_Court_Build.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Build.iSortID == 0) {
                    InGame_Court_Build.iSortID = 1;
                }
                else {
                    InGame_Court_Build.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Build();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Name"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Build.iSortID == 2 || InGame_Court_Build.iSortID == 3, InGame_Court_Build.iSortID == 3, Game.lang.get("Population"), -1, buttonX, buttonY, r0W2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Build.iSortID == 2) {
                    InGame_Court_Build.iSortID = 3;
                }
                else {
                    InGame_Court_Build.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_Build();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Population"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Build.iSortID == 4 || InGame_Court_Build.iSortID == 5, InGame_Court_Build.iSortID == 5, Game.lang.get("BuildingSlots"), -1, buttonX, buttonY, r0W3, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Build.iSortID == 4) {
                    InGame_Court_Build.iSortID = 5;
                }
                else {
                    InGame_Court_Build.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_Build();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BuildingSlots"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Build.iSortID == 6 || InGame_Court_Build.iSortID == 7, InGame_Court_Build.iSortID == 7, Game.lang.get("Cost"), -1, buttonX, buttonY, r0W4, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Build.iSortID == 6) {
                    InGame_Court_Build.iSortID = 7;
                }
                else {
                    InGame_Court_Build.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_Build();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Cost"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final List<Integer> tProvinces = new ArrayList<Integer>();
        try {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && !Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY()) && (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource < 0 || BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource == Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getResourceID())) {
                    if (!BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).SeaAccessRequired || Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getLevelOfPort() >= 0) {
                        tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
                    }
                }
            }
            if (tProvinces.size() > 0) {
                while (tProvinces.size() > 0) {
                    int toAddID = 0;
                    if (InGame_Court_Build.iSortID == 0) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                                toAddID = o;
                            }
                        }
                    }
                    else if (InGame_Court_Build.iSortID == 1) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                                toAddID = o;
                            }
                        }
                    }
                    else if (InGame_Court_Build.iSortID == 2) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() > Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                                toAddID = o;
                            }
                        }
                    }
                    else if (InGame_Court_Build.iSortID == 3) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() < Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                                toAddID = o;
                            }
                        }
                    }
                    else if (InGame_Court_Build.iSortID == 4) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (Game.getProvince(tProvinces.get(o)).iBuildingsLimit > Game.getProvince(tProvinces.get(toAddID)).iBuildingsLimit) {
                                toAddID = o;
                            }
                        }
                    }
                    else if (InGame_Court_Build.iSortID == 5) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (Game.getProvince(tProvinces.get(o)).iBuildingsLimit < Game.getProvince(tProvinces.get(toAddID)).iBuildingsLimit) {
                                toAddID = o;
                            }
                        }
                    }
                    else if (InGame_Court_Build.iSortID == 6) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (Game.getIncreaseTaxEfficiencyCost(tProvinces.get(toAddID)) < Game.getIncreaseTaxEfficiencyCost(tProvinces.get(o))) {
                                toAddID = o;
                            }
                        }
                    }
                    else if (InGame_Court_Build.iSortID == 7) {
                        for (int o = 1; o < tProvinces.size(); ++o) {
                            if (Game.getIncreaseTaxEfficiencyCost(tProvinces.get(toAddID)) > Game.getIncreaseTaxEfficiencyCost(tProvinces.get(o))) {
                                toAddID = o;
                            }
                        }
                    }
                    buttonX = paddingLeft;
                    menuElements.add(new Text_StaticBG_ID(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                        @Override
                        public void actionElement() {
                            if (Game.iActiveProvince == this.getCurrent()) {
                                Game.menuManager.hideCourtCiv();
                                Game.menuManager.setVisibleInGame_Civ(false);
                                Game.setActiveProvinceID(this.getCurrent());
                                Game.menuManager.rebuildInGame_ProvinceInfo(true);
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            }
                            else {
                                Game.mapCoords.centerToProvinceID(this.getCurrent());
                                Game.setActiveProvinceID(this.getCurrent());
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            }
                        }
                        
                        @Override
                        public void buildElementHover() {
                            this.menuElementHover = InGame_ProvinceInfo.getHoverProvince(this.id);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    menuElements.add(new Text_StaticBG_ID("" + CFG.getShortNumber(Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W2, buttonH, (int)tProvinces.get(toAddID)) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getProvince(this.getCurrent()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    menuElements.add(new Text_StaticBG_BuildingSlots("" + Game.getProvince(tProvinces.get(toAddID)).getUsedBuildingsSlots() + " / " + Game.getProvince(tProvinces.get(toAddID)).iBuildingsLimit, CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W3, buttonH, (int)tProvinces.get(toAddID)) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlots") + ": ", "" + Game.getProvince(this.getCurrent()).getUsedBuildingsSlots() + " / " + Game.getProvince(this.getCurrent()).iBuildingsLimit, Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    menuElements.add(new ButtonStatsRectIMG_ActiveBuilding("" + CFG.getPrecision2((float)Game.getBuildingConstructionCost(Game.player.iCivID, tProvinces.get(toAddID), InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY()), 10), Images.gold, buttonX, buttonY, r1W4, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID), InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY()) {
                        @Override
                        public void actionElement() {
                            if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                                if (Game.getProvince(this.getCurrent()).getUsedBuildingsSlots() >= Game.getProvince(this.getCurrent()).iBuildingsLimit) {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": "));
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(this.getCurrent()).getUsedBuildingsSlots() + " / " + Game.getProvince(this.getCurrent()).iBuildingsLimit, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseEconomyInAProvinceToUnlockMoreBuildingSlots"), Colors.HOVER_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    Game.menuManager.addToast(new Toast(nElements, 0, 6000));
                                }
                                else if (Game.getProvince(this.getCurrent()).isOccupied()) {
                                    Game.menuManager.addToast_Error(Game.lang.get("OccupiedProvince"), Images.war);
                                }
                                else if (Game.getProvince(this.getCurrent()).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                    Game.menuManager.addToastGold(Game.lang.get("BuildingConstructed"), Images.build);
                                }
                                else if (Game.getProvince(this.getCurrent()).isUnderConstruction(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                    Game.menuManager.addToastGold(Game.lang.get("UnderConstruction"), Images.build);
                                }
                                else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredReligionID >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredReligionID != Game.getCiv(Game.getProvince(this.getCurrent()).getCivID()).getReligionID()) {
                                    Game.menuManager.addToastGold(Game.lang.get("Religion") + ": " + Game.religionManager.getReligion(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredReligionID).Name, Images.religion);
                                }
                                else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredGovernmentID >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredGovernmentID != Game.getCiv(Game.getProvince(this.getCurrent()).getCivID()).getIdeologyID()) {
                                    Game.menuManager.addToastGold(Game.lang.get("Government") + ": " + Game.ideologiesManager.getIdeology(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredGovernmentID).Name, Images.government);
                                }
                                else if (!Game.getProvince(this.getCurrent()).addBuildingConstruction(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.getBuildingConstructionCost(Game.player.iCivID, this.getCurrent(), InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY()), 100), Images.gold);
                                }
                                else {
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_Build.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_Build.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return Colors.HOVER_GOLD;
                                        }
                                    });
                                }
                            }
                        }
                        
                        @Override
                        public void actionElementPPM() {
                            if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                                Game.getProvince(this.getCurrent()).cancelBuildingConstruction(Game.player.iCivID, InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY());
                            }
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Build") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).Name[InGame_Court_Buildings2.oBuildingID.getPosY()], CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", Game.getProvince(this.id).getProvinceName(), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(this.id).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GrowthRate") + ": ", CFG.getPrecision2(Game.getProvince(this.id).getGrowthRateWithBonuses(), 1) + "%", Images.populationGrowth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    tProvinces.remove(toAddID);
                }
            }
            else {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Built"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        try {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                    tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
                }
            }
            if (tProvinces.size() > 0) {
                while (tProvinces.size() > 0) {
                    int toAddID = 0;
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                    buttonX = paddingLeft;
                    menuElements.add(new Text_StaticBG_ID(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                        @Override
                        public void actionElement() {
                            if (Game.iActiveProvince == this.getCurrent()) {
                                Game.menuManager.hideCourtCiv();
                                Game.menuManager.setVisibleInGame_Civ(false);
                                Game.setActiveProvinceID(this.getCurrent());
                                Game.menuManager.rebuildInGame_ProvinceInfo(true);
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            }
                            else {
                                Game.mapCoords.centerToProvinceID(this.getCurrent());
                                Game.setActiveProvinceID(this.getCurrent());
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            }
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    menuElements.add(new Text_StaticBG_ID("" + CFG.getShortNumber(Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W2, buttonH, (int)tProvinces.get(toAddID)) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getProvince(this.getCurrent()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    menuElements.add(new Text_StaticBG_BuildingSlots("" + Game.getProvince(tProvinces.get(toAddID)).getUsedBuildingsSlots() + " / " + Game.getProvince(tProvinces.get(toAddID)).iBuildingsLimit, CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W3, buttonH, (int)tProvinces.get(toAddID)) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BuildingSlots") + ": ", CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getProvince(this.getCurrent()).getUsedBuildingsSlots() + " / " + Game.getProvince(this.getCurrent()).iBuildingsLimit, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.build, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Destroy"), Images.x, buttonX, buttonY, r1W4, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                        @Override
                        public void actionElement() {
                            if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                                InGame_Destroy.iProvinceID = this.getCurrent();
                                InGame_Destroy.building = InGame_Court_Buildings2.oBuildingID.getPosX();
                                InGame_Destroy.buildingID = InGame_Court_Buildings2.oBuildingID.getPosY();
                                Game.menuManager.rebuildInGame_Destroy();
                            }
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    tProvinces.remove(toAddID);
                }
            }
            else {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        buttonY += CFG.PADDING;
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("ConstructNewBuilding"));
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
        InGame_Court_Build.iSortID = 0;
    }
}
