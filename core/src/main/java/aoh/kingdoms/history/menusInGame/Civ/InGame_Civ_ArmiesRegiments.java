// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Graph.InGame_GraphPopulation;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Style;
import java.util.List;
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
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Data;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ_ArmiesRegiments extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iSortID;
    
    public InGame_Civ_ArmiesRegiments() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int buttonY = buttonYPadding;
        final int buttonH = CFG.BUTTON_HEIGHT3;
        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
            InGame_Civ.iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
        }
        else if (InGame_Civ.iActiveCivID <= 0) {
            InGame_Civ.iActiveCivID = Game.player.iCivID;
        }
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.3f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        int c0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 4) / 3.0f);
        buttonX = paddingLeft + CFG.PADDING;
        final List<Integer> tCivs = new ArrayList<Integer>();
        final PieChart_Data nPieChartData = new PieChart_Data();
        double maxManpower = 0.0;
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                tCivs.add(i);
                nPieChartData.addPieChartValues(new PieChart_Value(i, (float)(int)Game.getCiv(i).fManpowerMax));
                if (Game.getCiv(i).fManpowerMax > maxManpower) {
                    maxManpower = Game.getCiv(i).fManpowerMax;
                }
            }
        }
        final int pieDim = buttonH * 2 + CFG.PADDING;
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
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("MaximumManpower"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                for (int i = 0; i < this.pieChartData.getPieChartValuesSize() && i < 10; ++i) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.pieChartData.getCivID(i)).getCivName() + ": ", "" + CFG.getPrecision2(this.pieChartData.getPieChartValue(i).getPercentage(), 5) + "%", this.pieChartData.getCivID(i), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 2;
        c0W = menuWidth - paddingLeft - buttonX;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Civilizations") + ": ", "" + tCivs.size(), Images.council, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.battleWidth).getWidth()) {
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
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("MaximumManpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)maxManpower), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.battleWidth).getWidth()) {
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
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", this.sText2, Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_ArmiesRegiments.iSortID == 0 || InGame_Civ_ArmiesRegiments.iSortID == 1, InGame_Civ_ArmiesRegiments.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_ArmiesRegiments.iSortID == 0) {
                    InGame_Civ_ArmiesRegiments.iSortID = 1;
                }
                else {
                    InGame_Civ_ArmiesRegiments.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Civ_Regiments();
                InGame_Civ_ArmiesRegiments.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_ArmiesRegiments.iSortID == 2 || InGame_Civ_ArmiesRegiments.iSortID == 3, InGame_Civ_ArmiesRegiments.iSortID == 3, Game.lang.get("Discipline"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_ArmiesRegiments.iSortID == 2) {
                    InGame_Civ_ArmiesRegiments.iSortID = 3;
                }
                else {
                    InGame_Civ_ArmiesRegiments.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_Civ_Regiments();
                InGame_Civ_ArmiesRegiments.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Discipline"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("DisciplineSignifiesOrganizationAndCoordinationOfTheArmyInBattleIncreasingUnitsAttack"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_ArmiesRegiments.iSortID == 4 || InGame_Civ_ArmiesRegiments.iSortID == 5, InGame_Civ_ArmiesRegiments.iSortID == 5, Game.lang.get("RegimentsLimit"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_ArmiesRegiments.iSortID == 4) {
                    InGame_Civ_ArmiesRegiments.iSortID = 5;
                }
                else {
                    InGame_Civ_ArmiesRegiments.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_Civ_Regiments();
                InGame_Civ_ArmiesRegiments.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_ArmiesRegiments.iSortID == 6 || InGame_Civ_ArmiesRegiments.iSortID == 7, InGame_Civ_ArmiesRegiments.iSortID == 7, Game.lang.get("MaximumManpower"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_ArmiesRegiments.iSortID == 6) {
                    InGame_Civ_ArmiesRegiments.iSortID = 7;
                }
                else {
                    InGame_Civ_ArmiesRegiments.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_Civ_Regiments();
                InGame_Civ_ArmiesRegiments.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumManpower"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.3f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.2f);
        while (!tCivs.isEmpty()) {
            int toAddID = 0;
            if (InGame_Civ_ArmiesRegiments.iSortID == 0) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(toAddID)).getCivName(), Game.getCiv(tCivs.get(o)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_ArmiesRegiments.iSortID == 1) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(o)).getCivName(), Game.getCiv(tCivs.get(toAddID)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_ArmiesRegiments.iSortID == 2) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).civBonuses.Discipline < Game.getCiv(tCivs.get(o)).civBonuses.Discipline) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_ArmiesRegiments.iSortID == 3) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).civBonuses.Discipline > Game.getCiv(tCivs.get(o)).civBonuses.Discipline) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_ArmiesRegiments.iSortID == 4) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iRegimentsLimit < Game.getCiv(tCivs.get(o)).iRegimentsLimit) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_ArmiesRegiments.iSortID == 5) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iRegimentsLimit > Game.getCiv(tCivs.get(o)).iRegimentsLimit) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_ArmiesRegiments.iSortID == 6) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).fManpowerMax < Game.getCiv(tCivs.get(o)).fManpowerMax) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_ArmiesRegiments.iSortID == 7) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).fManpowerMax > Game.getCiv(tCivs.get(o)).fManpowerMax) {
                        toAddID = o;
                    }
                }
            }
            buttonX = paddingLeft;
            menuElements.add(new Text_StaticBG_ID_FlagCiv(Game.getCiv(tCivs.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tCivs.get(toAddID)) {
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
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(Game.getCiv(tCivs.get(toAddID)).civBonuses.Discipline * 100.0f, 100) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W, buttonH, (int)tCivs.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.id).getCivName(), "", this.id, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Discipline") + ": ", this.getText(), Images.discipline, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getNumberWithSpaces("" + Game.getCiv(tCivs.get(toAddID)).iRegimentsLimit), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tCivs.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.id).getCivName(), "", this.id, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RegimentsLimit") + ": ", this.getText(), Images.regimentsLimit, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    InGame_GraphPopulation.activeModeID = 7;
                    Game.menuManager.rebuildInGame_GraphPopulation();
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(tCivs.get(toAddID)).fManpowerMax), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tCivs.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.id).getCivName(), "", this.id, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", this.getText(), Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tCivs.remove(toAddID);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Armies"), false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Civ_ArmiesRegiments.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ_ArmiesRegiments.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ_ArmiesRegiments.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ_ArmiesRegiments.lTime = CFG.currentTimeMillis;
        InGame_Civ_ArmiesRegiments.lTime2 = InGame_Civ_ArmiesRegiments.lTime;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_Civ_ArmiesRegiments.lTime = 0L;
        InGame_Civ_ArmiesRegiments.lTime2 = 0L;
        InGame_Civ_ArmiesRegiments.iSortID = 6;
    }
}
