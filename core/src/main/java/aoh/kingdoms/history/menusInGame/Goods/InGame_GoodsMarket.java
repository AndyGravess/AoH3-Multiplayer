// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Goods;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Resource2;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_LargestProducer;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticGoods_ProgressBar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticGoods;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Style;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
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
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_GoodsMarket extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iActiveCivID;
    public static int iSortID;
    
    public InGame_GoodsMarket() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        int maxGoodsH = 0;
        int maxGoodsW = 0;
        for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
            if (ResourcesManager.resourceImages.get(i).getHeight() > maxGoodsH) {
                maxGoodsH = ResourcesManager.resourceImages.get(i).getHeight();
            }
            if (ResourcesManager.resourceImages.get(i).getWidth() > maxGoodsW) {
                maxGoodsW = ResourcesManager.resourceImages.get(i).getWidth();
            }
        }
        final int maxIconW = ImageManager.getImage(Images.goods).getWidth();
        final int statW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        final List<Integer> lResources = new ArrayList<Integer>();
        final List<Float> lProduction = new ArrayList<Float>();
        final List<Float> lIncome = new ArrayList<Float>();
        final List<Float> lProductionEfficiency = new ArrayList<Float>();
        final List<Integer> lProvinces = new ArrayList<Integer>();
        for (int j = 0; j < Game.getCiv(InGame_GoodsMarket.iActiveCivID).getNumOfProvinces(); ++j) {
            if (Game.getProvince(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)).getResourceID() >= 0) {
                boolean tAdded = false;
                for (int k = lResources.size() - 1; k >= 0; --k) {
                    if (lResources.get(k) == Game.getProvince(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)).getResourceID()) {
                        tAdded = true;
                        lProduction.set(k, lProduction.get(k) + ResourcesManager.getProducedGoods(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)));
                        lIncome.set(k, lIncome.get(k) + ResourcesManager.getMonthlyIncome(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)));
                        lProductionEfficiency.set(k, lProductionEfficiency.get(k) + ResourcesManager.getProductionEfficiency(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)));
                        lProvinces.set(k, lProvinces.get(k) + 1);
                        break;
                    }
                }
                if (!tAdded) {
                    lResources.add(Game.getProvince(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)).getResourceID());
                    lProduction.add(ResourcesManager.getProducedGoods(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)));
                    lIncome.add(ResourcesManager.getMonthlyIncome(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)));
                    lProductionEfficiency.add(ResourcesManager.getProductionEfficiency(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(j)));
                    lProvinces.add(1);
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
        for (int l = lResources.size() - 1; l >= 0; --l) {
            if (lProduction.get(l) > 0.001f) {
                nPieChartData.addPieChartValues(new PieChart_Value(lResources.get(l), lProduction.get(l)));
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
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getCivName() + ": ", Colors.HOVER_LEFT));
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
                Game.menuManager.rebuildInGame_Goods();
                Game.menuManager.setVisibleInGame_Goods(true);
                InGame_Goods.lTime = 0L;
            }
        });
        int nY = buttonY;
        final int nX = menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 2;
        final int nW = menuWidth - paddingLeft - nX;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("LargestGoodsProducers"), Images.goods, nX, nY, nW, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.goods).getWidth(), 0, true) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Goods();
                Game.menuManager.setVisibleInGame_Goods(true);
                InGame_Goods.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Production0"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Production1"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Production2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        nY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("UniqueResources") + ": ", "" + Game.getCiv(InGame_GoodsMarket.iActiveCivID).iUniqueResources, Images.goods, nX, nY, nW, CFG.BUTTON_HEIGHT3, maxIconW + CFG.PADDING * 2) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        nY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Legacy") + ": ", ((Game.getLegacyPerUniqueResources(InGame_GoodsMarket.iActiveCivID) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getLegacyPerUniqueResources(InGame_GoodsMarket.iActiveCivID), 100), Images.legacy, nX, nY, nW, CFG.BUTTON_HEIGHT3, maxIconW + CFG.PADDING * 2) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Legacy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(Game.getLegacyPerUniqueResources(InGame_GoodsMarket.iActiveCivID), 100), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(" = " + CFG.getPrecision2(GameValues.legacy.LEGACY_PER_UNIQUE_RESOURCE, 100) + " * " + Game.lang.get("UniqueResources") + " * min(" + CFG.getPrecision2(GameValues.legacy.LEGACY_PER_UNIQUE_RESOURCE_PER_TECHS_MAX, 100) + ", " + Game.lang.get("UnlockedTechnologies") + " * " + CFG.getPrecision2(GameValues.legacy.LEGACY_PER_UNIQUE_RESOURCE_PER_TECHS, 100) + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UniqueResources") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(InGame_GoodsMarket.iActiveCivID).iUniqueResources, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnlockedTechnologies") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(InGame_GoodsMarket.iActiveCivID).getResearchedTechnologies(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        maxGoodsH += CFG.PADDING * 2;
        maxGoodsW += CFG.PADDING * 5;
        buttonX = Images.boxTitleBORDERWIDTH;
        final int r0W = maxGoodsW + CFG.PADDING * 2;
        final int r1W = (menuWidth - Images.boxTitleBORDERWIDTH * 2 - r0W) / 4;
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket.iSortID == 0 || InGame_GoodsMarket.iSortID == 1, InGame_GoodsMarket.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket.iSortID == 0) {
                    InGame_GoodsMarket.iSortID = 1;
                }
                else {
                    InGame_GoodsMarket.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_GoodsMarket();
                InGame_GoodsMarket.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket.iSortID == 2 || InGame_GoodsMarket.iSortID == 3, InGame_GoodsMarket.iSortID == 3, Game.lang.get("Provinces"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket.iSortID == 2) {
                    InGame_GoodsMarket.iSortID = 3;
                }
                else {
                    InGame_GoodsMarket.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_GoodsMarket();
                InGame_GoodsMarket.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket.iSortID == 4 || InGame_GoodsMarket.iSortID == 5, InGame_GoodsMarket.iSortID == 5, Game.lang.get("Production"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket.iSortID == 4) {
                    InGame_GoodsMarket.iSortID = 5;
                }
                else {
                    InGame_GoodsMarket.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_GoodsMarket();
                InGame_GoodsMarket.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Production"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket.iSortID == 6 || InGame_GoodsMarket.iSortID == 7, InGame_GoodsMarket.iSortID == 7, Game.lang.get("Income"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket.iSortID == 6) {
                    InGame_GoodsMarket.iSortID = 7;
                }
                else {
                    InGame_GoodsMarket.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_GoodsMarket();
                InGame_GoodsMarket.lTime = 0L;
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
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket.iSortID == 8 || InGame_GoodsMarket.iSortID == 9, InGame_GoodsMarket.iSortID == 9, Game.lang.get("ShareInWorldProduction"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket.iSortID == 8) {
                    InGame_GoodsMarket.iSortID = 9;
                }
                else {
                    InGame_GoodsMarket.iSortID = 8;
                }
                Game.menuManager.rebuildInGame_GoodsMarket();
                InGame_GoodsMarket.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ShareInWorldProduction"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (lResources.size() == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
                @Override
                public void actionElement() {
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                        InGame_Goods.iCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                    }
                    else {
                        InGame_Goods.iCivID = Game.player.iCivID;
                    }
                    InGame_Goods.sortBy = 0;
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers") + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        else {
            final List<Integer> production = new ArrayList<Integer>();
            for (int m = 0; m < Game.getCivsSize(); ++m) {
                production.add(0);
            }
            final List<Float> lMarketShare = new ArrayList<Float>();
            for (int i2 = 0, iSize = lResources.size(); i2 < iSize; ++i2) {
                lMarketShare.add(lProduction.get(i2) * 100.0f / ResourcesManager.worldResourcesProduced.get(lResources.get(i2)) * 100.0f);
                if (!Game.getCiv(InGame_GoodsMarket.iActiveCivID).getTechResearched(ResourcesManager.lResources.get(i2).RequiredTechID)) {
                    lProductionEfficiency.set(i2, 0.0f);
                }
                else {
                    lProductionEfficiency.set(i2, lProductionEfficiency.get(i2) / lProvinces.get(i2));
                }
            }
            final int textW = (menuWidth - paddingLeft * 2 - maxGoodsW - CFG.PADDING) / 4;
            while (lResources.size() > 0) {
                int toAddID = 0;
                if (InGame_GoodsMarket.iSortID == 0) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(lResources.get(toAddID)), ResourcesManager.getResourceName(lResources.get(o)))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 1) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(lResources.get(o)), ResourcesManager.getResourceName(lResources.get(toAddID)))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 2) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (lProvinces.get(o) > lProvinces.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 3) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (lProvinces.get(o) < lProvinces.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 4) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (lProduction.get(o) > lProduction.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 5) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (lProduction.get(o) < lProduction.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 6) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (lIncome.get(o) > lIncome.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 7) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (lIncome.get(o) < lIncome.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 8) {
                    for (int o = 1; o < lResources.size(); ++o) {
                        if (lMarketShare.get(o) > lMarketShare.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket.iSortID == 9) {
                    for (int o = 1; o > lResources.size(); ++o) {
                        if (lMarketShare.get(o) < lMarketShare.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                buttonX = paddingLeft + maxGoodsW + CFG.PADDING;
                if (lProduction.get(toAddID) > 0.0f) {
                    menuElements.add(new Text_StaticGoods("" + CFG.getNumberWithSpaces("" + lProvinces.get(toAddID)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, textW, maxGoodsH, (int)lResources.get(toAddID)) {
                        @Override
                        public void actionElement() {
                            InGame_GoodsMarket_Resource.iResourceID = this.getCurrent();
                            InGame_GoodsMarket_Resource.iActiveCivID = InGame_GoodsMarket.iActiveCivID;
                            Game.menuManager.rebuildInGame_GoodsMarketResource();
                            InGame_GoodsMarket_Resource.lTime = 0L;
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Resource") + ": ", ResourcesManager.getResourceName(this.getCurrent()), this.getCurrent(), CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", this.getText(), Images.provinces, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getCivName() + ": "));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(ResourcesManager.getResourceName(this.getCurrent()), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth();
                    menuElements.add(new Text_StaticGoods("" + ((lProduction.get(toAddID) >= 1000.0f) ? CFG.getNumberWithSpaces("" + CFG.getPrecision2(lProduction.get(toAddID), 1)) : CFG.getPrecision2(lProduction.get(toAddID), 10)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, textW, maxGoodsH, (int)lResources.get(toAddID)) {
                        @Override
                        public void actionElement() {
                            InGame_Goods_Provinces.RESOURCE_ID = this.getCurrent();
                            InGame_Goods_Provinces.lTime = 0L;
                            InGame_GoodsMarket.lTime = 0L;
                            Game.menuManager.rebuildInGame_Goods_Provinces();
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Resource") + ": ", ResourcesManager.getResourceName(this.getCurrent()), this.getCurrent(), CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            int numOfProvinces = 0;
                            for (int i = 0; i < Game.getCiv(InGame_GoodsMarket.iActiveCivID).getNumOfProvinces(); ++i) {
                                if (Game.getProvince(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(i)).getResourceID() == this.getCurrent()) {
                                    ++numOfProvinces;
                                }
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + numOfProvinces, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Provinces") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(ResourcesManager.getResourceName(this.getCurrent()), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth();
                    menuElements.add(new Text_StaticGoods("" + CFG.getPrecision2(lIncome.get(toAddID), (lIncome.get(toAddID) < 1.0f) ? 100 : 10), CFG.FONT_BOLD_SMALL, -1, buttonX, buttonY, textW, maxGoodsH, (int)lResources.get(toAddID)) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Resource") + ": ", ResourcesManager.getResourceName(this.getCurrent()), this.getCurrent(), CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            int numOfProvinces = 0;
                            for (int i = 0; i < Game.getCiv(InGame_GoodsMarket.iActiveCivID).getNumOfProvinces(); ++i) {
                                if (Game.getProvince(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getProvinceID(i)).getResourceID() == this.getCurrent()) {
                                    ++numOfProvinces;
                                }
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + numOfProvinces, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
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
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth();
                    menuElements.add(new Text_StaticGoods_ProgressBar("" + CFG.getPrecision2(Math.min(100.0f, lMarketShare.get(toAddID)), (lMarketShare.get(toAddID) < 1.0f) ? 100 : 10) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, textW, maxGoodsH, Math.min(1.0f, lMarketShare.get(toAddID) / 100.0f), (int)lResources.get(toAddID), true) {
                        @Override
                        public void actionElement() {
                            InGame_Goods_LargestProducers.RESOURCE_ID = this.getCurrent();
                            InGame_Goods_LargestProducers.lTime = 0L;
                            InGame_GoodsMarket.lTime = 0L;
                            Game.menuManager.rebuildInGame_Goods_LargestProducers();
                        }
                        
                        @Override
                        public void drawBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                        }
                        
                        @Override
                        public void buildElementHover() {
                            this.menuElementHover = Text_StaticBG_LargestProducer.getHoverLargestProducers(this.getCurrent(), false);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth();
                    for (int i3 = 0; i3 < Game.getCivsSize(); ++i3) {
                        production.set(i3, 0);
                    }
                    for (int i3 = 0; i3 < Game.getProvincesSize(); ++i3) {
                        if (Game.getProvince(i3).getResourceID() == lResources.get(toAddID) && !Game.getProvince(i3).getSeaProvince() && Game.getProvince(i3).getCivID() > 0) {
                            production.set(Game.getProvince(i3).getCivID(), production.get(Game.getProvince(i3).getCivID()) + (int)(ResourcesManager.getProducedGoods(i3) * 100.0f));
                        }
                    }
                    int civProducerPosition = 1;
                    for (int i4 = 0; i4 < Game.getCivsSize(); ++i4) {
                        if (production.get(InGame_GoodsMarket.iActiveCivID) < production.get(i4)) {
                            ++civProducerPosition;
                        }
                    }
                    menuElements.add(new TextIcon_Resource2("#" + civProducerPosition, (int)lResources.get(toAddID), paddingLeft, buttonY, maxGoodsW, maxGoodsH, menuWidth - paddingLeft * 2) {
                        boolean numberOne = false;
                        
                        @Override
                        public void actionElement() {
                            InGame_GoodsMarket_Resource.iResourceID = this.getCurrent();
                            InGame_GoodsMarket_Resource.iActiveCivID = InGame_GoodsMarket.iActiveCivID;
                            Game.menuManager.rebuildInGame_GoodsMarketResource();
                            InGame_GoodsMarket_Resource.lTime = 0L;
                        }
                        
                        @Override
                        public void setCurrent(final int nCurrent) {
                            this.numberOne = true;
                        }
                        
                        @Override
                        protected Color getColor(final boolean isActive) {
                            if (this.numberOne) {
                                return Colors.HOVER_GOLD;
                            }
                            return super.getColor(isActive);
                        }
                    });
                    if (civProducerPosition == 1) {
                        menuElements.get(menuElements.size() - 1).setCurrent(1);
                    }
                    buttonY += maxGoodsH + CFG.PADDING;
                }
                lResources.remove(toAddID);
                lIncome.remove(toAddID);
                lProduction.remove(toAddID);
                lMarketShare.remove(toAddID);
                lProductionEfficiency.remove(toAddID);
                lProvinces.remove(toAddID);
            }
            production.clear();
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("ProducedGoods"), Game.getCiv(InGame_GoodsMarket.iActiveCivID).getCivName(), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_GoodsMarket.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_GoodsMarket.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_GoodsMarket.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_GoodsMarket.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.goodsOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.goodsOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.goodsOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_GoodsMarket.lTime = CFG.currentTimeMillis;
        InGame_GoodsMarket.lTime2 = InGame_GoodsMarket.lTime;
    }
    
    static {
        InGame_GoodsMarket.lTime = 0L;
        InGame_GoodsMarket.lTime2 = 0L;
        InGame_GoodsMarket.iSortID = 4;
    }
}
