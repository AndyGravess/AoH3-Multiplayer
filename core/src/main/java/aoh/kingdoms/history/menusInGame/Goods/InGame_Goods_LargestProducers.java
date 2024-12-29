// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Goods;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.button.ButtonFlag2;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_LargestProducer;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Resource;
import aoh.kingdoms.history.map.ResourcesManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical_Data_Type;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Goods_LargestProducers extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int RESOURCE_ID;
    
    public InGame_Goods_LargestProducers() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.title928).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        int buttonX = Images.boxTitleBORDERWIDTH;
        final int buttonResW = (int)(CFG.BUTTON_WIDTH * 1.25f);
        final int buttonResH = ImageManager.getImage(Images.flagOverDefault).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 3;
        final int elementWidth = menuWidth - paddingLeft * 2 - buttonResW - ImageManager.getImage(Images.flagOverDefault).getWidth() - CFG.PADDING * 6;
        final int elementWidth2 = menuWidth - paddingLeft * 2 - buttonResW - ImageManager.getImage(Images.flagOverDefault).getWidth();
        InGame_Goods.inGoods = false;
        final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.RESOURCE_PRODUCTION, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (int)(CFG.BUTTON_HEIGHT * 3.0f), true) {};
        menuElements.add(graphVertical);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("Name"), -1, buttonX, buttonY, buttonResW + CFG.PADDING * 3, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("Provinces"), -1, buttonX, buttonY, (int)(elementWidth2 * 0.25f), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("Economy"), -1, buttonX, buttonY, (int)(elementWidth2 * 0.2f), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": " + Game.lang.get("Economy"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(false, false, Game.lang.get("ProductionEfficiency"), -1, buttonX, buttonY, (int)(elementWidth2 * 0.2f), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": " + Game.lang.get("ProductionEfficiency"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(true, false, Game.lang.get("LargestProducer"), -1, buttonX, buttonY, menuWidth - buttonX - Images.boxTitleBORDERWIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        buttonX = paddingLeft;
        final List<Integer> productionCivID = new ArrayList<Integer>();
        final List<Integer> production = new ArrayList<Integer>();
        final List<Integer> productionProvinces = new ArrayList<Integer>();
        final List<Float> productionEconomy = new ArrayList<Float>();
        final List<Float> productionProductionEfficiency = new ArrayList<Float>();
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            production.add(0);
            productionCivID.add(i);
            productionProvinces.add(0);
            productionEconomy.add(0.0f);
            productionProductionEfficiency.add(0.0f);
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getResourceID() == InGame_Goods_LargestProducers.RESOURCE_ID && !Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                production.set(Game.getProvince(i).getCivID(), production.get(Game.getProvince(i).getCivID()) + (int)(ResourcesManager.getProducedGoods(i) * 100.0f));
                productionProvinces.set(Game.getProvince(i).getCivID(), productionProvinces.get(Game.getProvince(i).getCivID()) + 1);
                productionEconomy.set(Game.getProvince(i).getCivID(), productionEconomy.get(Game.getProvince(i).getCivID()) + Game.getProvince(i).getEconomyWithBonuses());
                productionProductionEfficiency.set(Game.getProvince(i).getCivID(), productionProductionEfficiency.get(Game.getProvince(i).getCivID()) + ResourcesManager.getProductionEfficiency(i));
            }
        }
        for (int i = production.size() - 1; i >= 0; --i) {
            if (production.get(i) == 0) {
                production.remove(i);
                productionCivID.remove(i);
                productionProvinces.remove(i);
                productionEconomy.remove(i);
                productionProductionEfficiency.remove(i);
            }
        }
        int nPosition = 1;
        while (production.size() > 0) {
            int toAddID = 0;
            for (int j = production.size() - 1; j > 0; --j) {
                if (production.get(toAddID) < production.get(j)) {
                    toAddID = j;
                }
            }
            menuElements.add(new TextIcon_Resource("#" + nPosition++, InGame_Goods_LargestProducers.RESOURCE_ID, buttonX, buttonY, buttonResW, buttonResH) {
                @Override
                public void actionElement() {
                    InGame_Goods_Provinces.RESOURCE_ID = this.getCurrent();
                    InGame_Goods_Provinces.lTime = 0L;
                    InGame_Goods_LargestProducers.lTime = 0L;
                    Game.menuManager.rebuildInGame_Goods_Provinces();
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG("" + productionProvinces.get(toAddID), CFG.FONT_REGULAR, -1, buttonX, buttonY, (int)(elementWidth * 0.25f), buttonResH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
                
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG("" + CFG.getPrecision2(productionEconomy.get(toAddID) / productionProvinces.get(toAddID), 10), CFG.FONT_REGULAR, -1, buttonX, buttonY, (int)(elementWidth * 0.2f), buttonResH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
                
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG("" + CFG.getPrecision2(productionProductionEfficiency.get(toAddID) * 100.0f / productionProvinces.get(toAddID), 10) + "%", CFG.FONT_REGULAR, -1, buttonX, buttonY, (int)(elementWidth * 0.2f), buttonResH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
                
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_LargestProducer("" + CFG.getPrecision2(production.get(toAddID) / 100.0f, 10) + " / " + CFG.getPrecision2(ResourcesManager.worldResourcesProduced.get(InGame_Goods_LargestProducers.RESOURCE_ID) / 100.0f, 10), InGame_Goods_LargestProducers.RESOURCE_ID, CFG.FONT_REGULAR, -1, buttonX, buttonY, (int)(elementWidth * 0.25f), buttonResH) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_LargestProducer("" + CFG.getPrecision2((float)Math.min(100.0, Math.floor(production.get(toAddID) / (float)ResourcesManager.worldResourcesProduced.get(InGame_Goods_LargestProducers.RESOURCE_ID) * 100.0f)), 10) + "%", InGame_Goods_LargestProducers.RESOURCE_ID, CFG.FONT_BOLD, -1, buttonX, buttonY, (int)(elementWidth * 0.1f), buttonResH) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new ButtonFlag2((int)productionCivID.get(toAddID), buttonX, buttonY, true) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0) {
                        if (!Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getDrawProvince()) {
                            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                        }
                        Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.setVisibleInGame_Goods(false);
                        Game.menuManager.rebuildInGame_Civ();
                    }
                }
            });
            menuElements.add(new Text_StaticBG(Game.getCiv(productionCivID.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY + ImageManager.getImage(Images.flagOverDefault).getHeight() + CFG.PADDING, ImageManager.getImage(Images.flagOverDefault).getWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            buttonY += buttonResH + buttonYPadding;
            buttonX = paddingLeft;
            production.remove(toAddID);
            productionCivID.remove(toAddID);
            productionProvinces.remove(toAddID);
            productionEconomy.remove(toAddID);
            productionProductionEfficiency.remove(toAddID);
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
                return InGame_Goods_LargestProducers.lTime2;
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
        this.getTitle().setText(Game.lang.get("LargestGoodsProducers") + ": " + ResourcesManager.lResources.get(InGame_Goods_LargestProducers.RESOURCE_ID).Name);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Goods_LargestProducers.lTime = CFG.currentTimeMillis;
        InGame_Goods_LargestProducers.lTime2 = InGame_Goods_LargestProducers.lTime;
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
        InGame_Goods_LargestProducers.lTime = 0L;
        InGame_Goods_LargestProducers.lTime2 = 0L;
        InGame_Goods_LargestProducers.RESOURCE_ID = 0;
    }
}
