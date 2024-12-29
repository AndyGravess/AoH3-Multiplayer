// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menusInGame.Goods.InGame_GoodsMarket;
import aoh.kingdoms.history.menu_element.button.ButtonResource2;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_Religion;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank_Ranking;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical_Data_Type;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Style;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_WithStatsFlags;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Value;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Data;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ_List extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static List<Integer> civsList;
    public static String civsListTitle;
    public static String civsListTitle2;
    public static int iSortID;
    public static int modeID;
    
    public static final void addCiv(final int civID) {
        for (int i = InGame_Civ_List.civsList.size() - 1; i >= 0; --i) {
            if (InGame_Civ_List.civsList.get(i) == civID) {
                return;
            }
        }
        InGame_Civ_List.civsList.add(civID);
    }
    
    public InGame_Civ_List() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        int buttonH = CFG.BUTTON_HEIGHT3 + CFG.TEXT_HEIGHT + CFG.PADDING * 3;
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.4f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.15f);
        InGame_Civ_Compare.civLeft_Rank = -1;
        InGame_Civ_Compare.civRight_Rank = -1;
        buttonY += buttonH + CFG.PADDING;
        buttonX = paddingLeft + CFG.PADDING;
        final PieChart_Data nPieChartData = new PieChart_Data();
        if (InGame_Civ_List.modeID == 0) {
            for (int i = InGame_Civ_List.civsList.size() - 1; i >= 0; --i) {
                nPieChartData.addPieChartValues(new PieChart_Value(InGame_Civ_List.civsList.get(i), (float)(int)Game.getCiv(InGame_Civ_List.civsList.get(i)).getPopulationTotal()));
            }
        }
        else if (InGame_Civ_List.modeID == 1) {
            for (int i = InGame_Civ_List.civsList.size() - 1; i >= 0; --i) {
                nPieChartData.addPieChartValues(new PieChart_Value(InGame_Civ_List.civsList.get(i), (float)(int)Game.getCiv(InGame_Civ_List.civsList.get(i)).getEconomyTotal()));
            }
        }
        else if (InGame_Civ_List.modeID == 2) {
            for (int i = InGame_Civ_List.civsList.size() - 1; i >= 0; --i) {
                nPieChartData.addPieChartValues(new PieChart_Value(InGame_Civ_List.civsList.get(i), (float)Game.getCiv(InGame_Civ_List.civsList.get(i)).getNumOfProvinces()));
            }
        }
        else if (InGame_Civ_List.modeID == 3) {
            for (int i = InGame_Civ_List.civsList.size() - 1; i >= 0; --i) {
                nPieChartData.addPieChartValues(new PieChart_Value(InGame_Civ_List.civsList.get(i), (float)(int)Game.getCiv(InGame_Civ_List.civsList.get(i)).fManpowerMax));
            }
        }
        else if (InGame_Civ_List.modeID == 4) {
            for (int i = InGame_Civ_List.civsList.size() - 1; i >= 0; --i) {
                nPieChartData.addPieChartValues(new PieChart_Value(InGame_Civ_List.civsList.get(i), (float)Game.getCiv(InGame_Civ_List.civsList.get(i)).iRegimentsLimit));
            }
        }
        else if (InGame_Civ_List.modeID == 5) {
            for (int i = InGame_Civ_List.civsList.size() - 1; i >= 0; --i) {
                nPieChartData.addPieChartValues(new PieChart_Value(InGame_Civ_List.civsList.get(i), (float)(int)Game.getCiv(InGame_Civ_List.civsList.get(i)).iCivRankScore));
            }
        }
        final int pieDim = CFG.BUTTON_HEIGHT3 * 3 + CFG.PADDING * 2;
        menuElements.add(new PieChart_WithStatsFlags(buttonX, buttonY + CFG.PADDING, pieDim - CFG.PADDING * 2, pieDim - CFG.PADDING * 2, nPieChartData, null) {
            @Override
            protected void drawPieChart(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int nWidth_LEFT) {
                oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
                Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight() + CFG.PADDING * 2, 1.0f);
                oSB.setColor(Color.WHITE);
                super.drawPieChart(oSB, iTranslateX, iTranslateY, isActive, scrollableY, nPosX, nPosY, nWidth, nHeight, nWidth_LEFT);
            }
            
            @Override
            protected float animationPerc() {
                return Math.min(1.0f, (CFG.currentTimeMillis - InGame_ProvinceInfo.lTime) / 150.0f);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Civ_List.modeID == 0) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Population"), Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    for (int i = 0; i < this.pieChartData.getPieChartValuesSize() && i < 10; ++i) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.pieChartData.getCivID(i)).getCivName() + ": ", CFG.getNumberWithSpaces("" + CFG.getPrecision2(this.pieChartData.getPieChartValue(i).getValue(), 1)), this.pieChartData.getCivID(i), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                else {
                    if (InGame_Civ_List.modeID == 1) {
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Economy"), Colors.HOVER_GOLD));
                    }
                    else if (InGame_Civ_List.modeID == 2) {
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Provinces"), Colors.HOVER_GOLD));
                    }
                    else if (InGame_Civ_List.modeID == 3) {
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("MaximumManpower"), Colors.HOVER_GOLD));
                    }
                    else if (InGame_Civ_List.modeID == 4) {
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("RegimentsLimit"), Colors.HOVER_GOLD));
                    }
                    else if (InGame_Civ_List.modeID == 5) {
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Prestige"), Colors.HOVER_GOLD));
                    }
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    for (int i = 0; i < this.pieChartData.getPieChartValuesSize() && i < 10; ++i) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.pieChartData.getCivID(i)).getCivName() + ": ", CFG.getNumberWithSpaces("" + CFG.getPrecision2(this.pieChartData.getPieChartValue(i).getValue(), 1)), this.pieChartData.getCivID(i), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                ++InGame_Civ_List.modeID;
                if (InGame_Civ_List.modeID > 5) {
                    InGame_Civ_List.modeID = 0;
                }
                Game.menuManager.rebuildInGame_Civ_List();
                InGame_Civ_List.lTime = 0L;
            }
        });
        final int tButtonW = menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 2;
        menuElements.add(new ButtonStatsRectIMG((InGame_Civ_List.modeID == 0) ? Game.lang.get("Population") : ((InGame_Civ_List.modeID == 1) ? Game.lang.get("Economy") : ((InGame_Civ_List.modeID == 2) ? Game.lang.get("Provinces") : ((InGame_Civ_List.modeID == 3) ? Game.lang.get("MaximumManpower") : ((InGame_Civ_List.modeID == 4) ? Game.lang.get("RegimentsLimit") : Game.lang.get("Prestige"))))), (InGame_Civ_List.modeID == 0) ? Images.population : ((InGame_Civ_List.modeID == 1) ? Game_Calendar.IMG_ECONOMY : ((InGame_Civ_List.modeID == 2) ? Images.provinces : ((InGame_Civ_List.modeID == 3) ? Game_Calendar.IMG_MANPOWER : ((InGame_Civ_List.modeID == 4) ? Images.regimentsLimit : Images.rankGold)))), buttonX - CFG.PADDING, buttonY - CFG.PADDING - buttonH + CFG.BUTTON_HEIGHT3 + CFG.PADDING, tButtonW, CFG.TEXT_HEIGHT + CFG.PADDING * 2, ImageManager.getImage(Images.battleWidth).getWidth()));
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Civilizations") + ": ", "" + InGame_Civ_List.civsList.size(), Images.council, buttonX - CFG.PADDING, buttonY - CFG.PADDING - buttonH, tButtonW, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ();
            }
            
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Civilizations") + ": ", this.sText2, Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 2;
        Graph_Vertical.graphCivs.clear();
        for (int a = 0; a < InGame_Civ_List.civsList.size(); ++a) {
            Graph_Vertical.graphCivs.add(InGame_Civ_List.civsList.get(a));
        }
        final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), buttonX - CFG.PADDING * 2, buttonY - CFG.PADDING - buttonH, menuWidth - paddingLeft - (buttonX - CFG.PADDING * 2), pieDim + CFG.PADDING * 3 + buttonH, true) {};
        menuElements.add(graphVertical);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() - buttonH;
        buttonH = CFG.BUTTON_HEIGHT3;
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_List.iSortID == 0 || InGame_Civ_List.iSortID == 1, InGame_Civ_List.iSortID == 1, Game.lang.get("Ranking"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_List.iSortID == 0) {
                    InGame_Civ_List.iSortID = 1;
                }
                else {
                    InGame_Civ_List.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Civ_List();
                InGame_Civ_List.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_List.iSortID == 2 || InGame_Civ_List.iSortID == 3, InGame_Civ_List.iSortID == 3, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_List.iSortID == 2) {
                    InGame_Civ_List.iSortID = 3;
                }
                else {
                    InGame_Civ_List.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_Civ_List();
                InGame_Civ_List.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_List.iSortID == 4 || InGame_Civ_List.iSortID == 5, InGame_Civ_List.iSortID == 5, Game.lang.get("Population"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_List.iSortID == 4) {
                    InGame_Civ_List.iSortID = 5;
                }
                else {
                    InGame_Civ_List.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_Civ_List();
                InGame_Civ_List.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_List.iSortID == 6 || InGame_Civ_List.iSortID == 7, InGame_Civ_List.iSortID == 7, Game.lang.get("Provinces"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_List.iSortID == 6) {
                    InGame_Civ_List.iSortID = 7;
                }
                else {
                    InGame_Civ_List.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_Civ_List();
                InGame_Civ_List.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_List.iSortID == 8 || InGame_Civ_List.iSortID == 9, InGame_Civ_List.iSortID == 9, Game.lang.get("Goods"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.4f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.15f);
        final List<Integer> tCivs = new ArrayList<Integer>();
        final List<Integer> tPopulation = new ArrayList<Integer>();
        for (int j = 0, iSize = InGame_Civ_List.civsList.size(); j < iSize; ++j) {
            tCivs.add(InGame_Civ_List.civsList.get(j));
            tPopulation.add((int)Game.getCiv(InGame_Civ_List.civsList.get(j)).getPopulationTotal());
        }
        while (tCivs.size() > 0) {
            int toAddID = 0;
            if (InGame_Civ_List.iSortID == 0) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iCivRankPosition > Game.getCiv(tCivs.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_List.iSortID == 1) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iCivRankPosition < Game.getCiv(tCivs.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            if (InGame_Civ_List.iSortID == 2) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(toAddID)).getCivName(), Game.getCiv(tCivs.get(o)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_List.iSortID == 3) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(o)).getCivName(), Game.getCiv(tCivs.get(toAddID)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_List.iSortID == 4) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (tPopulation.get(toAddID) < tPopulation.get(o)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_List.iSortID == 5) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (tPopulation.get(o) < tPopulation.get(toAddID)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_List.iSortID == 6) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).getNumOfProvinces() < Game.getCiv(tCivs.get(o)).getNumOfProvinces()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_List.iSortID == 7) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).getNumOfProvinces() > Game.getCiv(tCivs.get(o)).getNumOfProvinces()) {
                        toAddID = o;
                    }
                }
            }
            buttonX = paddingLeft;
            menuElements.add(new ButtonStatsRectIMG_Rank_Ranking("" + ((Game.getCiv(tCivs.get(toAddID)).iCivRankPosition < 0) ? Game.getCivsSize() : Game.getCiv(tCivs.get(toAddID)).iCivRankPosition), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(tCivs.get(toAddID)), buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.rankGold).getWidth()) {
                int id = 0;
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
                
                @Override
                protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                    super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                    if (this.id == InGame_Civ_Compare.civLeft_Rank) {
                        oSB.setColor(Colors.COLOR_NOTIFICATION_OVER_GREEN);
                        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                        oSB.setColor(Color.WHITE);
                    }
                }
                
                @Override
                public void actionElement() {
                    if (InGame_Civ_Compare.civLeft_Rank > 0) {
                        if (InGame_Civ_Compare.civLeft == this.id) {
                            Game.menuManager.addToast_Error(Game.lang.get("Compare") + ": " + Game.getCiv(InGame_Civ_Compare.civLeft).getCivName() + " == " + Game.getCiv(this.id).getCivName());
                        }
                        else {
                            InGame_Civ_Compare.civRight_Rank = this.id;
                            InGame_Civ_Compare.civLeft = InGame_Civ_Compare.civLeft_Rank;
                            InGame_Civ_Compare.civRight = InGame_Civ_Compare.civRight_Rank;
                            Game.menuManager.rebuildInGame_Civ_Compare();
                        }
                    }
                    else {
                        InGame_Civ_Compare.civLeft_Rank = this.id;
                    }
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CompareCivilizations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.compare, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (InGame_Civ_Compare.civLeft_Rank > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", Game.getCiv(InGame_Civ_Compare.civLeft_Rank).getCivName(), InGame_Civ_Compare.civLeft_Rank, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", "--", 0, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", "--", 0, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", "--", 0, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CivilizationRank") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(tCivs.get(toAddID));
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_FlagCiv_Religion("" + Game.getCiv(tCivs.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tCivs.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                            Game.menuManager.rebuildInGame_Civ();
                        }
                        else {
                            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                            Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        }
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getShortNumber(tPopulation.get(toAddID)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, tCivs.get(toAddID)));
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getNumberWithSpaces("" + Game.getCiv(tCivs.get(toAddID)).getNumOfProvinces()), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, tCivs.get(toAddID)));
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new ButtonResource2(ResourcesManager.getLargestGoodsProducedByCiv(tCivs.get(toAddID)), buttonX, buttonY, r1W, buttonH) {
                int id;
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
                
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_Civ(false);
                    InGame_GoodsMarket.iActiveCivID = this.id;
                    Game.menuManager.rebuildInGame_GoodsMarket();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.lang.get("ProducedGoods")));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    final List<Float> goodsProduced = new ArrayList<Float>();
                    for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                        goodsProduced.add(0.0f);
                    }
                    int bestResourceID = -1;
                    for (int j = 0; j < Game.getCiv(this.id).getNumOfProvinces(); ++j) {
                        if (Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID() >= 0) {
                            goodsProduced.set(Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID(), goodsProduced.get(Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID()) + ResourcesManager.getProducedGoods(Game.getCiv(this.id).getProvinceID(j)));
                            bestResourceID = Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID();
                        }
                    }
                    if (bestResourceID >= 0) {
                        int numAdded = 0;
                        while (numAdded++ < 6) {
                            for (int k = 0; k < ResourcesManager.iResourcesSize; ++k) {
                                if (goodsProduced.get(bestResourceID) < goodsProduced.get(k)) {
                                    bestResourceID = k;
                                }
                            }
                            if (goodsProduced.get(bestResourceID) <= 0.0f) {
                                break;
                            }
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.getResourceName(bestResourceID) + ": ", CFG.getPrecision2(goodsProduced.get(bestResourceID), 10), bestResourceID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            goodsProduced.set(bestResourceID, 0.0f);
                        }
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("None"), "", Images.resourceNone, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    goodsProduced.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(tCivs.get(toAddID));
            buttonX = paddingLeft;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tCivs.remove(toAddID);
            tPopulation.remove(toAddID);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_DoubleText(InGame_Civ_List.civsListTitle, InGame_Civ_List.civsListTitle2, false, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_Civ_List.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ_List.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ_List.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ_List.lTime = CFG.currentTimeMillis;
        InGame_Civ_List.lTime2 = InGame_Civ_List.lTime;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_Civ_List.lTime = 0L;
        InGame_Civ_List.lTime2 = 0L;
        InGame_Civ_List.civsList = new ArrayList<Integer>();
        InGame_Civ_List.civsListTitle = "";
        InGame_Civ_List.civsListTitle2 = "";
        InGame_Civ_List.iSortID = 0;
        InGame_Civ_List.modeID = 0;
    }
}
