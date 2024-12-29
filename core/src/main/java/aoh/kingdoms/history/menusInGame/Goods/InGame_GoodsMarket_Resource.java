// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Goods;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Right2;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.map.ResourcesManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_GoodsMarket_Resource extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iActiveCivID;
    public static int iResourceID;
    public static int iSortID;
    
    public InGame_GoodsMarket_Resource() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        final int buttonYPadding = CFG.PADDING;
        int buttonX = paddingLeft;
        int buttonY = 0;
        buttonX = Images.boxTitleBORDERWIDTH;
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.4f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket_Resource.iSortID == 0 || InGame_GoodsMarket_Resource.iSortID == 1, InGame_GoodsMarket_Resource.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket_Resource.iSortID == 0) {
                    InGame_GoodsMarket_Resource.iSortID = 1;
                }
                else {
                    InGame_GoodsMarket_Resource.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_GoodsMarketResource();
                InGame_GoodsMarket_Resource.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket_Resource.iSortID == 2 || InGame_GoodsMarket_Resource.iSortID == 3, InGame_GoodsMarket_Resource.iSortID == 3, Game.lang.get("ProductionEfficiency"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket_Resource.iSortID == 2) {
                    InGame_GoodsMarket_Resource.iSortID = 3;
                }
                else {
                    InGame_GoodsMarket_Resource.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_GoodsMarketResource();
                InGame_GoodsMarket_Resource.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ProductionEfficiency"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket_Resource.iSortID == 4 || InGame_GoodsMarket_Resource.iSortID == 5, InGame_GoodsMarket_Resource.iSortID == 5, Game.lang.get("Production"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket_Resource.iSortID == 4) {
                    InGame_GoodsMarket_Resource.iSortID = 5;
                }
                else {
                    InGame_GoodsMarket_Resource.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_GoodsMarketResource();
                InGame_GoodsMarket_Resource.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_GoodsMarket_Resource.iSortID == 6 || InGame_GoodsMarket_Resource.iSortID == 7, InGame_GoodsMarket_Resource.iSortID == 7, Game.lang.get("Income"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_GoodsMarket_Resource.iSortID == 6) {
                    InGame_GoodsMarket_Resource.iSortID = 7;
                }
                else {
                    InGame_GoodsMarket_Resource.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_GoodsMarketResource();
                InGame_GoodsMarket_Resource.lTime = 0L;
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
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.4f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.2f);
        final List<Integer> tProvinces = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(InGame_GoodsMarket_Resource.iActiveCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(InGame_GoodsMarket_Resource.iActiveCivID).getProvinceID(i)).getResourceID() == InGame_GoodsMarket_Resource.iResourceID) {
                tProvinces.add(Game.getCiv(InGame_GoodsMarket_Resource.iActiveCivID).getProvinceID(i));
            }
        }
        int tID = 1;
        if (tProvinces.size() > 0) {
            while (tProvinces.size() > 0) {
                int toAddID = 0;
                if (InGame_GoodsMarket_Resource.iSortID == 0) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket_Resource.iSortID == 1) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket_Resource.iSortID == 2) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getProductionEfficiency(tProvinces.get(o)) > ResourcesManager.getProductionEfficiency(tProvinces.get(toAddID))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket_Resource.iSortID == 3) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getProductionEfficiency(tProvinces.get(o)) < ResourcesManager.getProductionEfficiency(tProvinces.get(toAddID))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket_Resource.iSortID == 4) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getProducedGoods(tProvinces.get(o)) > ResourcesManager.getProducedGoods(tProvinces.get(toAddID))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket_Resource.iSortID == 5) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getProducedGoods(tProvinces.get(o)) < ResourcesManager.getProducedGoods(tProvinces.get(toAddID))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket_Resource.iSortID == 6) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getMonthlyIncome(tProvinces.get(o)) > ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_GoodsMarket_Resource.iSortID == 7) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getMonthlyIncome(tProvinces.get(o)) < ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID))) {
                            toAddID = o;
                        }
                    }
                }
                buttonX = paddingLeft;
                menuElements.add(new Text_StaticBG_ID("" + tID++ + ". " + Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).isCapital ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.iActiveProvince == this.getCurrent()) {
                            Game.menuManager.setVisibleInGame_GoodsMarket(false);
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
                menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(ResourcesManager.getProductionEfficiency(tProvinces.get(toAddID)) * 100.0f, 10) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.rebuildInGame_GoodsMarket();
                        InGame_GoodsMarket.lTime = 0L;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", this.getText(), Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Empty());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Back"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(ResourcesManager.getProducedGoods(tProvinces.get(toAddID)), 10), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.rebuildInGame_GoodsMarket();
                        InGame_GoodsMarket.lTime = 0L;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProducedGoods") + ": ", this.getText(), Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Empty());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Back"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                final float fGold = ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID));
                menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.rebuildInGame_GoodsMarket();
                        InGame_GoodsMarket.lTime = 0L;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeProduction") + ": ", this.getText(), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Empty());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Back"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                tProvinces.remove(toAddID);
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_GoodsMarket();
                    InGame_GoodsMarket.lTime = 0L;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(ResourcesManager.getResourceName(InGame_GoodsMarket_Resource.iResourceID), Game.getCiv(InGame_GoodsMarket_Resource.iActiveCivID).getCivName(), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_GoodsMarket_Resource.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_GoodsMarket_Resource.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_GoodsMarket_Resource.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_GoodsMarket_Resource.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.goodsOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.goodsOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.goodsOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_GoodsMarket_Resource.lTime = CFG.currentTimeMillis;
        InGame_GoodsMarket_Resource.lTime2 = InGame_GoodsMarket_Resource.lTime;
    }
    
    static {
        InGame_GoodsMarket_Resource.lTime = 0L;
        InGame_GoodsMarket_Resource.lTime2 = 0L;
        InGame_GoodsMarket_Resource.iSortID = 0;
    }
}
