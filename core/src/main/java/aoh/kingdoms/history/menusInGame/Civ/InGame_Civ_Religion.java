// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical_Data_Type;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.InGame_MapModes;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ_Religion extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iSortID;
    public static int iReligionID;
    
    public InGame_Civ_Religion() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int buttonY = buttonYPadding;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        final int p0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.6f);
        final int p1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.4f);
        final int p0W2 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 3) * 0.6f);
        final int p1W2 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 3) * 0.4f);
        final int religionH = CFG.BUTTON_HEIGHT;
        buttonY = CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        menuElements.add(new ButtonReligion2(InGame_Civ_Religion.iReligionID, buttonX, buttonY, menuWidth - buttonX * 2, religionH) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ();
            }
            
            @Override
            public void actionElementPPM() {
                InGame_MapModes.actionReligion();
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
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.RELIGION_CIVS, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT * 3, true) {};
        menuElements.add(graphVertical);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Religion.iSortID == 0 || InGame_Civ_Religion.iSortID == 1, InGame_Civ_Religion.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, p0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Religion.iSortID == 0) {
                    InGame_Civ_Religion.iSortID = 1;
                }
                else {
                    InGame_Civ_Religion.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Civ_Religion();
                InGame_Civ_Religion.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Religion.iSortID == 2 || InGame_Civ_Religion.iSortID == 3, InGame_Civ_Religion.iSortID == 3, Game.lang.get("Population"), -1, buttonX, buttonY, p1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Religion.iSortID == 2) {
                    InGame_Civ_Religion.iSortID = 3;
                }
                else {
                    InGame_Civ_Religion.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_Civ_Religion();
                InGame_Civ_Religion.lTime = 0L;
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
        final List<Integer> tCivsID = new ArrayList<Integer>();
        final List<Long> tPop = new ArrayList<Long>();
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            tCivsID.add(i);
            tPop.add(0L);
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).getReligion() == InGame_Civ_Religion.iReligionID) {
                tPop.set(Game.getProvince(i).getCivID(), tPop.get(Game.getProvince(i).getCivID()) + Game.getProvince(i).getPopulationTotal());
            }
        }
        for (int i = tCivsID.size() - 1; i >= 0; --i) {
            if (tPop.get(i) <= 0L) {
                tCivsID.remove(i);
                tPop.remove(i);
            }
        }
        if (tCivsID.size() > 0) {
            while (tCivsID.size() > 0) {
                int toAddID = 0;
                if (InGame_Civ_Religion.iSortID == 0) {
                    for (int o = 1; o < tCivsID.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivsID.get(toAddID)).getCivName(), Game.getCiv(tCivsID.get(o)).getCivName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Civ_Religion.iSortID == 1) {
                    for (int o = 1; o < tCivsID.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivsID.get(o)).getCivName(), Game.getCiv(tCivsID.get(toAddID)).getCivName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Civ_Religion.iSortID == 2) {
                    for (int o = 1; o < tCivsID.size(); ++o) {
                        if (tPop.get(toAddID) < tPop.get(o)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Civ_Religion.iSortID == 3) {
                    for (int o = 1; o < tCivsID.size(); ++o) {
                        if (tPop.get(toAddID) > tPop.get(o)) {
                            toAddID = o;
                        }
                    }
                }
                buttonX = Images.boxTitleBORDERWIDTH + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID_FlagCiv(Game.getCiv(tCivsID.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, p0W2, buttonH, (int)tCivsID.get(toAddID)) {
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
                                if (Game.menuManager.getVisibleInGame_ProvinceInfo()) {
                                    Game.menuManager.rebuildInGame_ProvinceInfo(false);
                                }
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.getCurrent(), Game.religionManager.getReligion(Game.getCiv(this.getCurrent()).getReligionID()).Name));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(this.getCurrent()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(CFG.getNumberWithSpaces("" + tPop.get(toAddID)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, p1W2, buttonH) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", this.getText(), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                tCivsID.remove(toAddID);
                tPop.remove(toAddID);
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, Images.boxTitleBORDERWIDTH + CFG.PADDING, buttonY, menuWidth - (Images.boxTitleBORDERWIDTH + CFG.PADDING * 2), CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Game.lang.get("Religion"), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_Civ.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_Civ_Religion.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ_Religion.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ_Religion.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ_Religion.lTime = CFG.currentTimeMillis;
        InGame_Civ_Religion.lTime2 = InGame_Civ_Religion.lTime;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_Civ_Religion.lTime = 0L;
        InGame_Civ_Religion.lTime2 = 0L;
        InGame_Civ_Religion.iSortID = 2;
        InGame_Civ_Religion.iReligionID = -1;
    }
}
