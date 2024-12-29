// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Resource;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menusInGame.InGame_Ranking;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Style;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_WithStatsResources;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Value;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Data;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ_Economy extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iSortID;
    public static boolean goBackToRank;
    
    public InGame_Civ_Economy() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int buttonY = buttonYPadding;
        final int buttonH = CFG.BUTTON_HEIGHT3;
        if (InGame_Civ_Economy.goBackToRank) {
            if (InGame_Civ.iActiveCivID <= 0) {
                InGame_Civ.iActiveCivID = Game.player.iCivID;
            }
        }
        else if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
            InGame_Civ.iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
        }
        else if (InGame_Civ.iActiveCivID <= 0) {
            InGame_Civ.iActiveCivID = Game.player.iCivID;
        }
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        final int c0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 4) / 3.0f);
        final List<Integer> lResources = new ArrayList<Integer>();
        final List<Float> lProduction = new ArrayList<Float>();
        for (int i = 0; i < Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(i)).getResourceID() >= 0) {
                boolean tAdded = false;
                for (int k = lResources.size() - 1; k >= 0; --k) {
                    if (lResources.get(k) == Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(i)).getResourceID()) {
                        tAdded = true;
                        lProduction.set(k, lProduction.get(k) + ResourcesManager.getProducedGoods(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(i)));
                        break;
                    }
                }
                if (!tAdded) {
                    lResources.add(Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(i)).getResourceID());
                    lProduction.add(ResourcesManager.getProducedGoods(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(i)));
                }
            }
        }
        final PieChart_Data nPieChartData = new PieChart_Data() {
            @Override
            public float getPieChartValue_ColorR(final int i) {
                return ResourcesManager.lResources.get(this.getPieChartValue(i).getDataID()).Color[0];
            }
            
            @Override
            public float getPieChartValue_ColorG(final int i) {
                return ResourcesManager.lResources.get(this.getPieChartValue(i).getDataID()).Color[1];
            }
            
            @Override
            public float getPieChartValue_ColorB(final int i) {
                return ResourcesManager.lResources.get(this.getPieChartValue(i).getDataID()).Color[2];
            }
        };
        for (int j = lResources.size() - 1; j >= 0; --j) {
            if (lProduction.get(j) > 0.001f) {
                nPieChartData.addPieChartValues(new PieChart_Value(lResources.get(j), lProduction.get(j)));
            }
        }
        final int pieDim = CFG.BUTTON_HEIGHT3 * 3 + CFG.PADDING * 2;
        menuElements.add(new PieChart_WithStatsResources(buttonX, buttonY + CFG.PADDING, pieDim - CFG.PADDING * 2, pieDim - CFG.PADDING * 2, nPieChartData, null) {
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
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": ", Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("ProducedGoods"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                for (int i = 0; i < this.pieChartData.getPieChartValuesSize() && i < 10; ++i) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.getResourceName(this.pieChartData.getCivID(i)) + ": ", CFG.getPrecision2(this.pieChartData.getPieChartValue(i).getPercentage(), 10) + "%", this.pieChartData.getCivID(i), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
            }
        });
        int nY = buttonY;
        final int nX = menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 2;
        final int nW = menuWidth - paddingLeft - nX;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3;
        float fGold = Game.getCiv(InGame_Civ.iActiveCivID).getEconomyTotal();
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Economy") + ": ", "" + CFG.getPrecision2(fGold, (fGold < 10.0f) ? 100 : ((fGold < 100.0f) ? 10 : 1)), Game_Calendar.IMG_ECONOMY, nX, nY, nW, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Game_Calendar.IMG_ECONOMY).getWidth() + CFG.PADDING * 2) {
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.goBackToRank) {
                    InGame_Ranking.sSearch = "";
                    Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                }
                else {
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
            
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_ECONOMY;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Civ_Economy.goBackToRank) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rank, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getEconomyTotal(), 100), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        nY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(InGame_Civ.iActiveCivID).getTotalIncomeFromEconomy();
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + CFG.getPrecision2(fGold, (fGold < 10.0f) ? 100 : ((fGold < 100.0f) ? 10 : 1)), Images.gold, nX, nY, nW, buttonH, ImageManager.getImage(Images.gold).getWidth(), 0) {
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.goBackToRank) {
                    InGame_Ranking.sSearch = "";
                    Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                }
                else {
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Civ_Economy.goBackToRank) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rank, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncomeEconomy") + ": ", CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getTotalIncomeFromEconomy(), 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        nY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(InGame_Civ.iActiveCivID).getTotalIncomeFromProduction();
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + CFG.getPrecision2(fGold, (fGold < 10.0f) ? 100 : ((fGold < 100.0f) ? 10 : 1)), Images.goods, nX, nY, nW, buttonH, ImageManager.getImage(Images.goods).getWidth(), 0) {
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.goBackToRank) {
                    InGame_Ranking.sSearch = "";
                    Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                }
                else {
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Civ_Economy.goBackToRank) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rank, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeProduction") + ": ", CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getTotalIncomeFromProduction(), 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Economy.iSortID == 0 || InGame_Civ_Economy.iSortID == 1, InGame_Civ_Economy.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.iSortID == 0) {
                    InGame_Civ_Economy.iSortID = 1;
                }
                else {
                    InGame_Civ_Economy.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Civ_Economy();
                InGame_Civ_Economy.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Economy.iSortID == 2 || InGame_Civ_Economy.iSortID == 3, InGame_Civ_Economy.iSortID == 3, Game.lang.get("Economy"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.iSortID == 2) {
                    InGame_Civ_Economy.iSortID = 3;
                }
                else {
                    InGame_Civ_Economy.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_Civ_Economy();
                InGame_Civ_Economy.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Economy"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Economy.iSortID == 4 || InGame_Civ_Economy.iSortID == 5, InGame_Civ_Economy.iSortID == 5, Game.lang.get("Income"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.iSortID == 4) {
                    InGame_Civ_Economy.iSortID = 5;
                }
                else {
                    InGame_Civ_Economy.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_Civ_Economy();
                InGame_Civ_Economy.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MonthlyIncomeEconomy"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Economy.iSortID == 6 || InGame_Civ_Economy.iSortID == 7, InGame_Civ_Economy.iSortID == 7, Game.lang.get("Resource"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.iSortID == 6) {
                    InGame_Civ_Economy.iSortID = 7;
                }
                else {
                    InGame_Civ_Economy.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_Civ_Economy();
                InGame_Civ_Economy.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Resource"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Economy.iSortID == 8 || InGame_Civ_Economy.iSortID == 9, InGame_Civ_Economy.iSortID == 9, Game.lang.get("IncomeProduction"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Economy.iSortID == 8) {
                    InGame_Civ_Economy.iSortID = 9;
                }
                else {
                    InGame_Civ_Economy.iSortID = 8;
                }
                Game.menuManager.rebuildInGame_Civ_Economy();
                InGame_Civ_Economy.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("IncomeProduction"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.2f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.2f);
        final List<Integer> tProvinces = new ArrayList<Integer>();
        for (int l = 0; l < Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(); ++l) {
            if (!Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(l)).isOccupied()) {
                tProvinces.add(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(l));
            }
        }
        while (tProvinces.size() > 0) {
            int toAddID = 0;
            if (InGame_Civ_Economy.iSortID == 0) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 1) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 2) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses() < Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 3) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses() > Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 4) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getIncomeFromEconomy(tProvinces.get(toAddID)) < Game.getIncomeFromEconomy(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 5) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getIncomeFromEconomy(tProvinces.get(toAddID)) > Game.getIncomeFromEconomy(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 6) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 7) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 8) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID)) < ResourcesManager.getMonthlyIncome(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Economy.iSortID == 9) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID)) > ResourcesManager.getMonthlyIncome(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            buttonX = paddingLeft;
            menuElements.add(new Text_StaticBG_ID(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
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
            fGold = Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses();
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(fGold, (fGold < 10.0f) ? 100 : ((fGold < 100.0f) ? 10 : 1)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.getCurrent(), false, false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            fGold = Game.getIncomeFromEconomy(tProvinces.get(toAddID));
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(fGold, (fGold < 10.0f) ? 100 : ((fGold < 100.0f) ? 10 : 1)), CFG.FONT_BOLD_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.getCurrent(), false, false);
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (isActive) {
                        return Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
                    }
                    if (this.getIsHovered()) {
                        return Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
                    }
                    return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            if (Game.getProvince(tProvinces.get(toAddID)).getResourceID() >= 0) {
                menuElements.add(new ButtonStatsRectIMG_Resource(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()), Game.getProvince(tProvinces.get(toAddID)).getResourceID(), buttonX, buttonY, r0W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverResource(this.getCurrent(), this.getValue1(), false);
                    }
                });
            }
            else {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W, buttonH));
            }
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            fGold = ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID));
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(fGold, (fGold < 10.0f) ? 100 : ((fGold < 100.0f) ? 10 : 1)), CFG.FONT_BOLD_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverResource(this.getCurrent(), Game.getProvince(this.getCurrent()).getResourceID(), false);
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (isActive) {
                        return Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
                    }
                    if (this.getIsHovered()) {
                        return Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
                    }
                    return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tProvinces.remove(toAddID);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Game.lang.get("Economy"), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_Civ.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_Civ_Economy.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ_Economy.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ_Economy.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ_Economy.lTime = CFG.currentTimeMillis;
        InGame_Civ_Economy.lTime2 = InGame_Civ_Economy.lTime;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_Civ_Economy.lTime = 0L;
        InGame_Civ_Economy.lTime2 = 0L;
        InGame_Civ_Economy.iSortID = 2;
        InGame_Civ_Economy.goBackToRank = false;
    }
}
