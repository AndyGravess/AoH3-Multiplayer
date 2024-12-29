// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Graph;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical_Data_Type;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonIcon;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_GraphPopulation extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int activeModeID;
    
    public InGame_GraphPopulation() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.title928).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        final int buttonX = Images.boxTitleBORDERWIDTH;
        final int menuHeight = Math.min(CFG.GAME_HEIGHT - menuY * 2, menuWidth);
        InGame_Goods.inGoodsView = false;
        final int statsH = ImageManager.getImage(Images.provinces).getHeight() + CFG.PADDING * 4;
        final int typeW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 5) / 6;
        int typeX = paddingLeft;
        menuElements.add(new ButtonIcon("", Images.population, typeX, buttonY, typeW, statsH) {
            @Override
            public void actionElement() {
                InGame_GraphPopulation.activeModeID = 0;
                Game.menuManager.rebuildInGame_GraphPopulation();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Population"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public boolean isActive() {
                return InGame_GraphPopulation.activeModeID == 0;
            }
        });
        typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonIcon("", Game_Calendar.IMG_ECONOMY, typeX, buttonY, typeW, statsH) {
            @Override
            public void actionElement() {
                InGame_GraphPopulation.activeModeID = 1;
                Game.menuManager.rebuildInGame_GraphPopulation();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Economy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public boolean isActive() {
                return InGame_GraphPopulation.activeModeID == 1;
            }
        });
        typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonIcon("", Images.buildings, typeX, buttonY, typeW, statsH) {
            @Override
            public void actionElement() {
                InGame_GraphPopulation.activeModeID = 2;
                Game.menuManager.rebuildInGame_GraphPopulation();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConstructedBuildings"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.buildings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public boolean isActive() {
                return InGame_GraphPopulation.activeModeID == 2;
            }
        });
        typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonIcon("", Images.infrastructure, typeX, buttonY, typeW, statsH) {
            @Override
            public void actionElement() {
                InGame_GraphPopulation.activeModeID = 3;
                Game.menuManager.rebuildInGame_GraphPopulation();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Infrastructure"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.infrastructure, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public boolean isActive() {
                return InGame_GraphPopulation.activeModeID == 3;
            }
        });
        typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonIcon("", Images.provinces, typeX, buttonY, typeW, statsH) {
            @Override
            public void actionElement() {
                InGame_GraphPopulation.activeModeID = 4;
                Game.menuManager.rebuildInGame_GraphPopulation();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Provinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public boolean isActive() {
                return InGame_GraphPopulation.activeModeID == 4;
            }
        });
        typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonIcon("", Game_Calendar.IMG_TECHNOLOGY, typeX, buttonY, typeW, statsH) {
            @Override
            public void actionElement() {
                InGame_GraphPopulation.activeModeID = 5;
                Game.menuManager.rebuildInGame_GraphPopulation();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UnlockedTechnologies"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public boolean isActive() {
                return InGame_GraphPopulation.activeModeID == 5;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        if (InGame_GraphPopulation.activeModeID == 0) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_POPULATION, Game.lang.get("Civilizations"), Game.lang.get("Population"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        else if (InGame_GraphPopulation.activeModeID == 1) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_ECONOMY, Game.lang.get("Civilizations"), Game.lang.get("Economy"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        else if (InGame_GraphPopulation.activeModeID == 2) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_CONSTRUCTED_BUILDINGS, Game.lang.get("Civilizations"), Game.lang.get("ConstructedBuildings"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        else if (InGame_GraphPopulation.activeModeID == 3) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_INFRASTRUCTURE, Game.lang.get("Civilizations"), Game.lang.get("Population"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        else if (InGame_GraphPopulation.activeModeID == 4) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT, Game.lang.get("Civilizations"), Game.lang.get("Provinces"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        else if (InGame_GraphPopulation.activeModeID == 5) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        else if (InGame_GraphPopulation.activeModeID == 6) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_PRESTIGE, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        else if (InGame_GraphPopulation.activeModeID == 7) {
            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_REGIMENTS_LIMIT, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, menuHeight - buttonY - buttonYPadding, true) {};
            menuElements.add(graphVertical);
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_DoubleText((InGame_GraphPopulation.activeModeID == 0) ? Game.lang.get("Population") : ((InGame_GraphPopulation.activeModeID == 1) ? Game.lang.get("Economy") : ((InGame_GraphPopulation.activeModeID == 2) ? Game.lang.get("ConstructedBuildings") : ((InGame_GraphPopulation.activeModeID == 3) ? Game.lang.get("Infrastructure") : ((InGame_GraphPopulation.activeModeID == 4) ? Game.lang.get("Provinces") : ((InGame_GraphPopulation.activeModeID == 5) ? Game.lang.get("UnlockedTechnologies") : ((InGame_GraphPopulation.activeModeID == 6) ? Game.lang.get("Prestige") : Game.lang.get("RegimentsLimit"))))))), Game.lang.get("Year") + ": " + Game_Calendar.currentYear, false, false, Images.title928) {
            @Override
            public long getTime() {
                return InGame_GraphPopulation.lTime;
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
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_GraphPopulation.lTime = CFG.currentTimeMillis;
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
        InGame_GraphPopulation.lTime = 0L;
        InGame_GraphPopulation.activeModeID = 0;
    }
}
