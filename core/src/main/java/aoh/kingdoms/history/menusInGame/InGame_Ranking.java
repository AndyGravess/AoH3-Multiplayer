// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Population;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Economy;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_EconomyRanking;
import aoh.kingdoms.history.menusInGame.Graph.InGame_GraphPopulation;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_Religion;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Compare;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank_Ranking;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Style;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Write;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Ranking extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iSortID;
    public static String sSearch;
    
    public InGame_Ranking() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title600).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = CFG.PADDING;
        int buttonX = Images.boxTitleBORDERWIDTH;
        int r0W0 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.15f);
        int r0W2 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.4f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.15f);
        final int c0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 4) / 3.0f);
        int buttonH = CFG.BUTTON_HEIGHT3;
        final int searchButtonW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        menuElements.add(new Text_StaticBG_Write(Game.lang.get("Search") + ": ", CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, searchButtonW, buttonH) {
            @Override
            public String getTextToDraw() {
                return InGame_Ranking.sSearch + Keyboard.getKeyboardVerticalLine();
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.SEARCH_RANKING, InGame_Ranking.sSearch);
            }
        });
        int civsInGame = 0;
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                ++civsInGame;
            }
        }
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Civilizations") + ": ", "" + civsInGame, Images.council, paddingLeft + searchButtonW + CFG.PADDING, buttonY, searchButtonW, buttonH, ImageManager.getImage(Images.battleWidth).getWidth()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Ranking.iSortID == 0 || InGame_Ranking.iSortID == 1, InGame_Ranking.iSortID == 1, Game.lang.get("Ranking"), -1, buttonX, buttonY, r0W0, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Ranking.iSortID == 0) {
                    InGame_Ranking.iSortID = 1;
                }
                else {
                    InGame_Ranking.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                InGame_Ranking.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Ranking.iSortID == 2 || InGame_Ranking.iSortID == 3, InGame_Ranking.iSortID == 3, Game.lang.get("Name"), -1, buttonX, buttonY, r0W2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Ranking.iSortID == 2) {
                    InGame_Ranking.iSortID = 3;
                }
                else {
                    InGame_Ranking.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                InGame_Ranking.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Ranking.iSortID == 4 || InGame_Ranking.iSortID == 5, InGame_Ranking.iSortID == 5, Game.lang.get("Prestige"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Ranking.iSortID == 4) {
                    InGame_Ranking.iSortID = 5;
                }
                else {
                    InGame_Ranking.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                InGame_Ranking.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Prestige"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Ranking.iSortID == 6 || InGame_Ranking.iSortID == 7, InGame_Ranking.iSortID == 7, Game.lang.get("Economy"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Ranking.iSortID == 6) {
                    InGame_Ranking.iSortID = 7;
                }
                else {
                    InGame_Ranking.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                InGame_Ranking.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Ranking.iSortID == 8 || InGame_Ranking.iSortID == 9, InGame_Ranking.iSortID == 9, Game.lang.get("Population"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Ranking.iSortID == 8) {
                    InGame_Ranking.iSortID = 9;
                }
                else {
                    InGame_Ranking.iSortID = 8;
                }
                Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                InGame_Ranking.lTime = 0L;
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
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        r0W0 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.15f);
        r0W2 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.4f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.15f);
        buttonH = (CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2);
        final List<Integer> tCivs = new ArrayList<Integer>();
        final List<Float> tEconomy = new ArrayList<Float>();
        final List<Long> tPopulationTotal = new ArrayList<Long>();
        if (InGame_Ranking.sSearch.length() > 0) {
            final String tempSearch = InGame_Ranking.sSearch.toLowerCase();
            for (int j = 1; j < Game.getCivsSize(); ++j) {
                if (Game.getCiv(j).getNumOfProvinces() > 0 && Game.getCiv(j).iCivRankPosition >= 0 && Game.getCiv(j).getCivName().toLowerCase().indexOf(tempSearch) != -1) {
                    tCivs.add(j);
                    tEconomy.add(Game.getCiv(j).getEconomyTotal());
                    tPopulationTotal.add(Game.getCiv(j).getPopulationTotal());
                }
            }
        }
        else {
            for (int k = 1; k < Game.getCivsSize(); ++k) {
                if (Game.getCiv(k).getNumOfProvinces() > 0 && Game.getCiv(k).iCivRankPosition >= 0) {
                    tCivs.add(k);
                    tEconomy.add(Game.getCiv(k).getEconomyTotal());
                    tPopulationTotal.add(Game.getCiv(k).getPopulationTotal());
                }
            }
        }
        while (tCivs.size() > 0) {
            int toAddID = 0;
            if (InGame_Ranking.iSortID == 0) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iCivRankPosition > Game.getCiv(tCivs.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 1) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iCivRankPosition < Game.getCiv(tCivs.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 2) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(toAddID)).getCivName(), Game.getCiv(tCivs.get(o)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 3) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(o)).getCivName(), Game.getCiv(tCivs.get(toAddID)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 4) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iCivRankScore < Game.getCiv(tCivs.get(o)).iCivRankScore) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 5) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (Game.getCiv(tCivs.get(toAddID)).iCivRankScore > Game.getCiv(tCivs.get(o)).iCivRankScore) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 6) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (tEconomy.get(toAddID) < tEconomy.get(o)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 7) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (tEconomy.get(toAddID) > tEconomy.get(o)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 8) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (tPopulationTotal.get(toAddID) < tPopulationTotal.get(o)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Ranking.iSortID == 9) {
                for (int o = 1; o < tCivs.size(); ++o) {
                    if (tPopulationTotal.get(toAddID) > tPopulationTotal.get(o)) {
                        toAddID = o;
                    }
                }
            }
            buttonX = paddingLeft;
            menuElements.add(new ButtonStatsRectIMG_Rank_Ranking("" + Game.getCiv(tCivs.get(toAddID)).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(tCivs.get(toAddID)), buttonX, buttonY, r0W0, buttonH, ImageManager.getImage(Images.rankGold).getWidth()) {
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
            menuElements.add(new Text_StaticBG_ID_FlagCiv_Religion("" + Game.getCiv(tCivs.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W2, buttonH, (int)tCivs.get(toAddID)) {
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
            menuElements.add(new Text_StaticBG_ID_Special("" + CFG.getPrecision2(Game.getCiv(tCivs.get(toAddID)).iCivRankScore, 1), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tCivs.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
                
                @Override
                public void actionElement() {
                    InGame_GraphPopulation.activeModeID = 6;
                    Game.menuManager.rebuildInGame_GraphPopulation();
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_EconomyRanking("" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(tEconomy.get(toAddID), 1)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tCivs.get(toAddID)) {
                @Override
                public void actionElement() {
                    InGame_Civ.iActiveCivID = this.id;
                    InGame_Civ_Economy.goBackToRank = true;
                    Game.menuManager.rebuildInGame_Civ_Economy();
                }
                
                @Override
                public void actionElementPPM() {
                    InGame_GraphPopulation.activeModeID = 1;
                    Game.menuManager.rebuildInGame_GraphPopulation();
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getShortNumber(tPopulationTotal.get(toAddID)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tCivs.get(toAddID)) {
                @Override
                public void actionElement() {
                    InGame_Civ.iActiveCivID = this.id;
                    InGame_Civ_Population.goBackToRank = true;
                    Game.menuManager.rebuildInGame_Civ_Population();
                }
                
                @Override
                public void actionElementPPM() {
                    InGame_GraphPopulation.activeModeID = 0;
                    Game.menuManager.rebuildInGame_GraphPopulation();
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.COLOR_POPULATION;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(this.id).getCivName() + ": " + Game.lang.get("Population"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.population, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Ranking") + ": ", "#" + InGame_Civ.getRankPopulation(this.id) + " / " + InGame_Civ.getRankCivsWithProvinces(this.id), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(this.id), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ", " + Game.lang.get("Average") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCiv(this.id).getAverageGrowthRate(), 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tCivs.remove(toAddID);
            tEconomy.remove(toAddID);
            tPopulationTotal.remove(toAddID);
        }
        buttonY = 0;
        for (int k = 0, iSize = menuElements.size(); k < iSize; ++k) {
            if (buttonY < menuElements.get(k).getPosY() + menuElements.get(k).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(k).getPosY() + menuElements.get(k).getHeight() + CFG.PADDING * 2;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("CivilizationRanking"), false, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_Ranking.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Ranking.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_Ranking.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.goodsOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.goodsOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.goodsOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Ranking.lTime = CFG.currentTimeMillis;
        InGame_Ranking.lTime2 = InGame_Ranking.lTime;
    }
    
    static {
        InGame_Ranking.lTime = 0L;
        InGame_Ranking.lTime2 = 0L;
        InGame_Ranking.iSortID = 0;
        InGame_Ranking.sSearch = "";
    }
}
