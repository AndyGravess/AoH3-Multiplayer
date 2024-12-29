// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Goods;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.Empty;
import com.badlogic.gdx.graphics.Color;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Flag;
import aoh.kingdoms.history.map.ResourcesManager;
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

public class InGame_Goods_Provinces extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int RESOURCE_ID;
    
    public InGame_Goods_Provinces() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.title928).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = 0;
        int buttonX = Images.boxTitleBORDERWIDTH;
        final int buttonResH = ImageManager.getImage(Images.flagOverDefault).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 3;
        final int elementWidth = menuWidth - paddingLeft * 2 - CFG.PADDING * 6;
        final int elementWidth2 = menuWidth - paddingLeft * 2;
        InGame_Goods.inGoods = false;
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("Name"), -1, buttonX, buttonY, (int)(elementWidth2 * 0.3f), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("Economy"), -1, buttonX, buttonY, (int)(elementWidth2 * 0.175f), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("ProductionEfficiency"), -1, buttonX, buttonY, (int)(elementWidth2 * 0.175f), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(true, false, Game.lang.get("Production"), -1, buttonX, buttonY, (int)(elementWidth2 * 0.175f), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("MonthlyIncome"), -1, buttonX, buttonY, menuWidth - buttonX - Images.boxTitleBORDERWIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        buttonX = paddingLeft;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        final List<Integer> provinceID = new ArrayList<Integer>();
        final List<Float> production = new ArrayList<Float>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getResourceID() == InGame_Goods_Provinces.RESOURCE_ID) {
                provinceID.add(i);
                production.add(ResourcesManager.getProducedGoods(i));
            }
        }
        int tID = 1;
        while (production.size() > 0) {
            int toAddID = 0;
            for (int j = production.size() - 1; j > 0; --j) {
                if (production.get(toAddID) < production.get(j)) {
                    toAddID = j;
                }
            }
            menuElements.add(new Text_StaticBG_ID_Flag("" + tID++ + ". " + Game.getProvince(provinceID.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, (int)(elementWidth * 0.3f), buttonH, (int)provinceID.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.iActiveProvince == this.getCurrent()) {
                        Game.menuManager.setVisibleInGame_GoodsMarket(false);
                        Game.menuManager.setVisibleInGame_Goods(false);
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
            menuElements.add(new Text_StaticBG_ID(CFG.getPrecision2(Game.getProvince(provinceID.get(toAddID)).getEconomyWithBonuses(), 100), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, (int)(elementWidth * 0.175f), buttonH, (int)provinceID.get(toAddID)) {
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
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(ResourcesManager.getProductionEfficiency(provinceID.get(toAddID)) * 100.0f, 10) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, (int)(elementWidth * 0.175f), buttonH, (int)provinceID.get(toAddID)) {
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
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(production.get(toAddID), 10), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, (int)(elementWidth * 0.175f), buttonH, (int)provinceID.get(toAddID)) {
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
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            final float fGold = Game.getProvince(provinceID.get(toAddID)).fProvinceIncomeProduction;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD_SMALL, -1, buttonX, buttonY, (int)(elementWidth * 0.175f), buttonH, (int)provinceID.get(toAddID)) {
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
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
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
            buttonX = paddingLeft;
            production.remove(toAddID);
            provinceID.remove(toAddID);
        }
        buttonY = 0;
        for (int k = 0, iSize = menuElements.size(); k < iSize; ++k) {
            if (buttonY < menuElements.get(k).getPosY() + menuElements.get(k).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(k).getPosY() + menuElements.get(k).getHeight() + CFG.PADDING * 2;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_DoubleText("", Game.lang.get("Year") + ": " + Game_Calendar.currentYear, false, false, Images.title928) {
            @Override
            public long getTime() {
                return InGame_Goods_Provinces.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        final float fA = 1.0f;
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG.r, Colors.COLOR_GRADIENT_BG.g, Colors.COLOR_GRADIENT_BG.b, 0.2f * fA));
        Images.pix.draw2(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG.r, Colors.COLOR_GRADIENT_BG.g, Colors.COLOR_GRADIENT_BG.b, 0.65f * fA));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        oSB.setColor(Color.WHITE);
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop928, Images.insideBot928);
        oSB.setColor(Colors.COLOR_GRADIENT);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Provinces") + ": " + ResourcesManager.lResources.get(InGame_Goods_Provinces.RESOURCE_ID).Name);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Goods_Provinces.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameGoods();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_Goods(false);
    }
    
    static {
        InGame_Goods_Provinces.lTime = 0L;
        InGame_Goods_Provinces.RESOURCE_ID = 0;
    }
}
