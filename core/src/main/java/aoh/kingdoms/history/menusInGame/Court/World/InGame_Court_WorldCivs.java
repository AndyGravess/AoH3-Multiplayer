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
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.menu_element.button.ButtonIdeology2;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialCiv;
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

public class InGame_Court_WorldCivs extends Menu
{
    public static String sSearch;
    public static int iSortID;
    
    public InGame_Court_WorldCivs() {
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
                return InGame_Court_WorldCivs.sSearch + Keyboard.getKeyboardVerticalLine();
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.SEARCH_COURT_ALL_CIVS, InGame_Court_WorldCivs.sSearch);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        final int statsRightW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 6) / 7;
        final int statsRightH = CFG.BUTTON_HEIGHT;
        final int emptyBGH = buttonH + CFG.PADDING + statsRightH;
        final int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) / 5.0f);
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldCivs.iSortID == 0 || InGame_Court_WorldCivs.iSortID == 1, InGame_Court_WorldCivs.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldCivs.iSortID == 0) {
                    InGame_Court_WorldCivs.iSortID = 1;
                }
                else {
                    InGame_Court_WorldCivs.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_CourtSearchCivs();
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldCivs.iSortID == 2 || InGame_Court_WorldCivs.iSortID == 3, InGame_Court_WorldCivs.iSortID == 3, Game.lang.get("Population"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldCivs.iSortID == 2) {
                    InGame_Court_WorldCivs.iSortID = 3;
                }
                else {
                    InGame_Court_WorldCivs.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_CourtSearchCivs();
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldCivs.iSortID == 4 || InGame_Court_WorldCivs.iSortID == 5, InGame_Court_WorldCivs.iSortID == 5, Game.lang.get("Provinces"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldCivs.iSortID == 4) {
                    InGame_Court_WorldCivs.iSortID = 5;
                }
                else {
                    InGame_Court_WorldCivs.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_CourtSearchCivs();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldCivs.iSortID == 6 || InGame_Court_WorldCivs.iSortID == 7, InGame_Court_WorldCivs.iSortID == 7, Game.lang.get("Economy"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldCivs.iSortID == 6) {
                    InGame_Court_WorldCivs.iSortID = 7;
                }
                else {
                    InGame_Court_WorldCivs.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_CourtSearchCivs();
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_WorldCivs.iSortID == 8 || InGame_Court_WorldCivs.iSortID == 9, InGame_Court_WorldCivs.iSortID == 9, Game.lang.get("Technologies"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_WorldCivs.iSortID == 8) {
                    InGame_Court_WorldCivs.iSortID = 9;
                }
                else {
                    InGame_Court_WorldCivs.iSortID = 8;
                }
                Game.menuManager.rebuildInGame_CourtSearchCivs();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Technologies"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        final List<Integer> tCivs = new ArrayList<Integer>();
        final List<Long> tPop = new ArrayList<Long>();
        final List<Float> tEco = new ArrayList<Float>();
        final List<Integer> tTech = new ArrayList<Integer>();
        if (InGame_Court_WorldCivs.sSearch.length() > 0) {
            int num = -1;
            try {
                num = Integer.parseInt(InGame_Court_WorldCivs.sSearch);
            }
            catch (final Exception ex) {}
            if (num >= 0 && num < Game.getCivsSize()) {
                tCivs.add(num);
                tPop.add(Game.getCiv(num).getPopulationTotal());
                tEco.add(Game.getCiv(num).getEconomyTotal());
                tTech.add(Game.getCiv(num).getResearchedTechnologies());
            }
            else {
                final String tSearch = InGame_Court_WorldCivs.sSearch.toLowerCase();
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (Game.getCiv(i).getCivName().toLowerCase().indexOf(tSearch) >= 0) {
                        tCivs.add(i);
                        tPop.add(Game.getCiv(i).getPopulationTotal());
                        tEco.add(Game.getCiv(i).getEconomyTotal());
                        tTech.add(Game.getCiv(i).getResearchedTechnologies());
                    }
                }
            }
        }
        else {
            for (int j = 1; j < Game.getCivsSize(); ++j) {
                tCivs.add(j);
                tPop.add(Game.getCiv(j).getPopulationTotal());
                tEco.add(Game.getCiv(j).getEconomyTotal());
                tTech.add(Game.getCiv(j).getResearchedTechnologies());
            }
        }
        if (tCivs.size() > 0) {
            int numOfAdded = 0;
            final int limit = GameValues.inGame.COURT_PROVINCES_LIMIT * 2;
            while (tCivs.size() > 0 && numOfAdded++ < limit) {
                int toAddID = 0;
                if (InGame_Court_WorldCivs.iSortID == 0) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(toAddID)).getCivName(), Game.getCiv(tCivs.get(o)).getCivName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 1) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(o)).getCivName(), Game.getCiv(tCivs.get(toAddID)).getCivName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 2) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (tPop.get(o) > tPop.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 3) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (tPop.get(o) < tPop.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 4) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (Game.getCiv(tCivs.get(o)).getNumOfProvinces() > Game.getCiv(tCivs.get(toAddID)).getNumOfProvinces()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 5) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (Game.getCiv(tCivs.get(o)).getNumOfProvinces() < Game.getCiv(tCivs.get(toAddID)).getNumOfProvinces()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 6) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (tEco.get(o) > tEco.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 7) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (tEco.get(o) < tEco.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 8) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (tTech.get(o) > tTech.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_WorldCivs.iSortID == 9) {
                    for (int o = 1; o < tCivs.size(); ++o) {
                        if (tTech.get(o) < tTech.get(toAddID)) {
                            toAddID = o;
                        }
                    }
                }
                final int nCivID = tCivs.get(toAddID);
                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialCiv(Game.getCiv(nCivID).getCivName(), CFG.FONT_REGULAR, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, nCivID, Images.population, CFG.getNumberWithSpaces("" + tPop.get(toAddID))) {
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                Game.menuManager.hideCourtCiv();
                                Game.menuManager.setVisibleInGame_Civ(false);
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
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getCiv(this.id).getEconomyTotal(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Civilization") + " ID: ", "" + this.id, Images.world, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
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
                menuElements.add(new ButtonIdeology2(Game.getCiv(nCivID).getIdeologyID(), buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH) {});
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonReligion2(Game.getCiv(nCivID).getReligionID(), buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH) {});
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + Game.getCiv(nCivID).getNumOfProvinces()), Images.provinces, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", this.getText(), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2("" + CFG.getPrecision2(tEco.get(toAddID), 1), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", this.getText(), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2("" + tTech.get(toAddID), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, statsRightW, statsRightH) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnlockedTechnologies") + ": ", this.getText(), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                buttonX = paddingLeft;
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonY - emptyBGH, menuWidth - paddingLeft2 * 2, emptyBGH));
                buttonY += CFG.PADDING;
                tCivs.remove(toAddID);
                tPop.remove(toAddID);
                tEco.remove(toAddID);
                tTech.remove(toAddID);
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
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("Civilizations"));
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
        InGame_Court_WorldCivs.sSearch = "";
        InGame_Court_WorldCivs.iSortID = 2;
    }
}
