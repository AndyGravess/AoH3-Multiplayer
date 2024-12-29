// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court.World;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Resource_Small;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Economy;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Infrastructure;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Manpower;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_GrowthRate;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Tax;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_ProvinceIncome;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_Special;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Write;
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
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_WorldSearch extends Menu
{
    public static String sSearch;
    public static int iSortID;
    
    public InGame_Court_WorldSearch() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = CFG.PADDING * 2 + InGame_CourtOptions2.getMenuWidth();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
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
        menuElements.add(new Text_StaticBG_Write(Game.lang.get("Search") + ": ", CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
            @Override
            public String getTextToDraw() {
                return InGame_Court_WorldSearch.sSearch + Keyboard.getKeyboardVerticalLine();
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.SEARCH_COURT_ALL_PROVINCES, InGame_Court_WorldSearch.sSearch);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        final int statsRightW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 6) / 7;
        final int statsRightH = CFG.BUTTON_HEIGHT;
        final int emptyBGH = buttonH + CFG.PADDING + statsRightH;
        final int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) / 4.0f);
        final int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) / 5.0f);
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 0 || InGame_Court_WorldSearch.iSortID == 1, InGame_Court_WorldSearch.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 0) {
                    InGame_Court_WorldSearch.iSortID = 1;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 2 || InGame_Court_WorldSearch.iSortID == 3, InGame_Court_WorldSearch.iSortID == 3, Game.lang.get("Population"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 2) {
                    InGame_Court_WorldSearch.iSortID = 3;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 4 || InGame_Court_WorldSearch.iSortID == 5, InGame_Court_WorldSearch.iSortID == 5, Game.lang.get("Income"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 4) {
                    InGame_Court_WorldSearch.iSortID = 5;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Income"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 6 || InGame_Court_WorldSearch.iSortID == 7, InGame_Court_WorldSearch.iSortID == 7, Game.lang.get("TaxEfficiency"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 6) {
                    InGame_Court_WorldSearch.iSortID = 7;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("TaxEfficiency"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX = Images.boxTitleBORDERWIDTH;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 8 || InGame_Court_WorldSearch.iSortID == 9, InGame_Court_WorldSearch.iSortID == 9, Game.lang.get("GrowthRate"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 8) {
                    InGame_Court_WorldSearch.iSortID = 9;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 8;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("GrowthRate"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 10 || InGame_Court_WorldSearch.iSortID == 11, InGame_Court_WorldSearch.iSortID == 11, Game.lang.get("Manpower"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 10) {
                    InGame_Court_WorldSearch.iSortID = 11;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 10;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Manpower"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 12 || InGame_Court_WorldSearch.iSortID == 13, InGame_Court_WorldSearch.iSortID == 13, Game.lang.get("Infrastructure"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 12) {
                    InGame_Court_WorldSearch.iSortID = 13;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 12;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Infrastructure"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 14 || InGame_Court_WorldSearch.iSortID == 15, InGame_Court_WorldSearch.iSortID == 15, Game.lang.get("Economy"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 14) {
                    InGame_Court_WorldSearch.iSortID = 15;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 14;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldSearch.iSortID == 16 || InGame_Court_WorldSearch.iSortID == 17, InGame_Court_WorldSearch.iSortID == 17, Game.lang.get("Resource"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldSearch.iSortID == 16) {
                    InGame_Court_WorldSearch.iSortID = 17;
                }
                else {
                    InGame_Court_WorldSearch.iSortID = 16;
                }
                Game.menuManager.rebuildInGame_CourtSearch();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
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
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        final List<Integer> tProvinces = new ArrayList<Integer>();
        if (InGame_Court_WorldSearch.sSearch.length() > 0) {
            int num = -1;
            try {
                num = Integer.parseInt(InGame_Court_WorldSearch.sSearch);
            }
            catch (final Exception ex) {}
            if (num >= 0 && num < Game.getProvincesSize()) {
                tProvinces.add(num);
            }
            else {
                final String tSearch = InGame_Court_WorldSearch.sSearch.toLowerCase();
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (Game.getProvince(i).getProvinceName().toLowerCase().indexOf(tSearch) >= 0) {
                        tProvinces.add(i);
                    }
                }
            }
        }
        else {
            for (int j = 0; j < Game.getProvincesSize(); ++j) {
                tProvinces.add(j);
            }
        }
        if (tProvinces.size() > 0) {
            int numOfAdded = 0;
            while (tProvinces.size() > 0 && numOfAdded++ < GameValues.inGame.COURT_PROVINCES_LIMIT) {
                int toAddID = 0;
                if (InGame_Court_WorldSearch.iSortID == 0) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 1) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 2) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() > Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 3) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() < Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 4) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getProvinceIncome() - Game.getProvince(tProvinces.get(o)).fProvinceMaintenance > Game.getProvince(tProvinces.get(toAddID)).getProvinceIncome() - Game.getProvince(tProvinces.get(toAddID)).fProvinceMaintenance) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 5) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getProvinceIncome() - Game.getProvince(tProvinces.get(o)).fProvinceMaintenance < Game.getProvince(tProvinces.get(toAddID)).getProvinceIncome() - Game.getProvince(tProvinces.get(toAddID)).fProvinceMaintenance) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 6) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getTaxEfficiencyWithBonuses() > Game.getProvince(tProvinces.get(toAddID)).getTaxEfficiencyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 7) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getTaxEfficiencyWithBonuses() < Game.getProvince(tProvinces.get(toAddID)).getTaxEfficiencyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 8) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getGrowthRateWithBonuses() > Game.getProvince(tProvinces.get(toAddID)).getGrowthRateWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 9) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getGrowthRateWithBonuses() < Game.getProvince(tProvinces.get(toAddID)).getGrowthRateWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 10) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getManpower() > Game.getProvince(tProvinces.get(toAddID)).getManpower()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 11) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getManpower() < Game.getProvince(tProvinces.get(toAddID)).getManpower()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 12) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getInfrastructure() > Game.getProvince(tProvinces.get(toAddID)).getInfrastructure()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 13) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getInfrastructure() < Game.getProvince(tProvinces.get(toAddID)).getInfrastructure()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 14) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses() > Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 15) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses() < Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 16) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldSearch.iSortID == 17) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()))) {
                            toAddID = o;
                        }
                    }
                }
                final int nProvinceID = tProvinces.get(toAddID);
                menuElements.add(new Text_StaticBG_ID_FlagCiv_Special(Game.getProvince(nProvinceID).getProvinceName(), CFG.FONT_REGULAR, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, nProvinceID, Images.population, CFG.getNumberWithSpaces("" + Game.getProvince(nProvinceID).getPopulationTotal())) {
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
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(this.id).getCivID(), Game.getProvince(this.id).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(this.id).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", CFG.getPrecision2(Game.getProvince(this.id).getProvinceIncome() - Game.getProvince(this.id).fProvinceMaintenance, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Province") + " ID: ", "" + this.id, Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public Color getColor2(final boolean isActive) {
                        return Colors.getColorPopulation(isActive, this.getIsHovered());
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight();
                buttonX = paddingLeft;
                menuElements.add(new TextIcon2_ProvinceIncome(nProvinceID, "" + CFG.getPrecision2(Game.getProvince(nProvinceID).getProvinceIncome() - Game.getProvince(nProvinceID).fProvinceMaintenance, 100), Images.gold, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverProvinceIncome(this.iProvinceID, false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Tax(nProvinceID, "" + CFG.getPrecision2(Game.getProvince(nProvinceID).getTaxEfficiencyWithBonuses(), 10) + "%", Images.tax, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public int getSFX() {
                        return Game.soundsManager.getCoin();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverTaxEfficiency(this.iProvinceID, false);
                    }
                    
                    @Override
                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionTax(this.iProvinceID)) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_WorldSearch.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_WorldSearch.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.HOVER_YELLOW;
                                }
                            });
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_GrowthRate(nProvinceID, "" + CFG.getPrecision2(Game.getProvince(nProvinceID).getGrowthRateWithBonuses(), 10) + "%", Images.populationGrowth, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public int getSFX() {
                        final SoundsManager soundsManager = Game.soundsManager;
                        return SoundsManager.getGrowthRate();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionGrowthRate(this.iProvinceID)) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_WorldSearch.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_WorldSearch.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.COLOR_POPULATION;
                                }
                            });
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverPopulationGrowth(this.iProvinceID, false, true);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Manpower(nProvinceID, "1", Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public int getSFX() {
                        return Game.soundsManager.getClickIncreaseManpower();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionManpower(this.iProvinceID)) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_WorldSearch.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_WorldSearch.this.getMenuPosY(), this.getWidth(), this.getHeight()));
                        }
                    }
                    
                    @Override
                    public int getImageID() {
                        return Game_Calendar.IMG_MANPOWER;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverManpower(this.iProvinceID, false, true);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Infrastructure(nProvinceID, "", Images.infrastructure, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public int getSFX() {
                        return Game.soundsManager.getInfrastructure();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionInfrastructure(this.iProvinceID)) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_WorldSearch.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_WorldSearch.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.HOVER_LEFT;
                                }
                            });
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverInfrastructure(this.iProvinceID, false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Economy(nProvinceID, "" + CFG.getPrecision2(Game.getProvince(nProvinceID).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public int getSFX() {
                        return Game.soundsManager.getEconomy();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.iProvinceID, false);
                    }
                    
                    @Override
                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionEconomy(this.iProvinceID)) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_WorldSearch.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_WorldSearch.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.COLOR_TEXT_ECONOMY;
                                }
                            });
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon_Resource_Small(nProvinceID, ResourcesManager.getResourceName(Game.getProvince(nProvinceID).getResourceID()), Game.getProvince(nProvinceID).getResourceID(), buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverResource(this.iProvinceID, this.resourceID, false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                buttonX = paddingLeft;
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonY - emptyBGH, menuWidth - paddingLeft2 * 2, emptyBGH));
                buttonY += CFG.PADDING;
                tProvinces.remove(toAddID);
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("Search"));
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - InGame_CourtOptions.menuH + iTranslateY, this.getWidth(), this.getHeight() + InGame_CourtOptions.menuH + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Court.lTime = CFG.currentTimeMillis;
        InGame_Court.lTime2 = CFG.currentTimeMillis;
        if (!visible) {
            Game.keyboard.hideKeyboard();
        }
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.keyboard.hideKeyboard();
    }
    
    static {
        InGame_Court_WorldSearch.sSearch = "";
        InGame_Court_WorldSearch.iSortID = 2;
    }
}
