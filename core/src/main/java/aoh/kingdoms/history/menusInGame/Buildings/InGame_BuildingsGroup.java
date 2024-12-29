// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Buildings;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlue;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BuildingsGroup extends Menu
{
    public static int menuX;
    public static int menuY;
    public static int menuHeight;
    public static int mTranslateX;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iProvinceID;
    public static boolean isCapital;
    public int imgTitle;
    public int imgTop;
    public int imgBot;
    
    public InGame_BuildingsGroup() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        int buttonWidth;
        if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).isCapital && Game.getCiv(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID()).getCapitalProvinceID() == InGame_BuildingsGroup.iProvinceID) {
            this.imgTitle = Images.title928;
            this.imgTop = Images.insideTop928;
            this.imgBot = Images.insideBot928;
            InGame_BuildingsGroup.isCapital = true;
            buttonWidth = (ImageManager.getImage(this.imgTitle).getWidth() - Images.boxTitleBORDERWIDTH * 2) / 4;
        }
        else {
            this.imgTitle = Images.title698;
            this.imgTop = Images.insideTop698;
            this.imgBot = Images.insideBot698;
            InGame_BuildingsGroup.isCapital = false;
            buttonWidth = (ImageManager.getImage(this.imgTitle).getWidth() - Images.boxTitleBORDERWIDTH * 2) / 3;
        }
        final int menuWidth = ImageManager.getImage(this.imgTitle).getWidth();
        final int tMenuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        InGame_BuildingsGroup.menuX = tMenuX + Images.boxTitleBORDERWIDTH;
        final int tMenuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        InGame_BuildingsGroup.menuY = tMenuY + this.getInnerTitleHeight();
        int buttonY = 0;
        menuElements.add(new Text_TitleBlue(Game.lang.get("Administration"), -1, Images.boxTitleBORDERWIDTH, 0, buttonWidth, this.getInnerTitleHeight()) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats3(isActive, this.getIsHovered());
            }
            
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        menuElements.add(new Text_TitleBlue(Game.lang.get("Military"), -1, Images.boxTitleBORDERWIDTH + buttonWidth, 0, buttonWidth, this.getInnerTitleHeight()) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats3(isActive, this.getIsHovered());
            }
            
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        menuElements.add(new Text_TitleBlue(Game.lang.get("Economy"), -1, Images.boxTitleBORDERWIDTH + buttonWidth * 2, 0, buttonWidth, this.getInnerTitleHeight()) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats3(isActive, this.getIsHovered());
            }
            
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        if (InGame_BuildingsGroup.isCapital) {
            menuElements.add(new Text_TitleBlue(Game.lang.get("Capital"), -1, Images.boxTitleBORDERWIDTH + buttonWidth * 3, 0, buttonWidth, this.getInnerTitleHeight()) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorTopStats3(isActive, this.getIsHovered());
                }
                
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UniqueCapitalBuildings"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        buttonY += (ImageManager.getImage(Images.buildingsFrame).getHeight() + CFG.PADDING * 4) * 6;
        int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - tMenuY - (CFG.GAME_HEIGHT - InGame_ProvinceInfo.menuPosY) - CFG.PADDING * 3);
        if (!CFG.isDesktop()) {
            tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - tMenuY - CFG.PADDING * 2);
        }
        InGame_BuildingsGroup.menuHeight = tMenuHeight - this.getInnerTitleHeight();
        menuElements.add(new Empty(0, 0, menuWidth, Math.min(tMenuY - 1, Math.max(buttonY, tMenuHeight))));
        this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get("ConstructNewBuilding"), Game.getProvince(InGame_BuildingsGroup.iProvinceID).getProvinceName(), false, false, this.imgTitle) {
            @Override
            public long getTime() {
                return InGame_BuildingsGroup.lTime;
            }
            
            @Override
            public void onHovered() {
                Game.menuManager.setOrderOfMenu_InGameBuildings();
            }
            
            @Override
            public void action() {
                Game.menuManager.setOrderOfMenu_InGameBuildings();
            }
        }, tMenuX, tMenuY, menuWidth, tMenuHeight, menuElements, false, true);
    }
    
    public int getInnerTitleHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 6;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_BuildingsGroup.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_BuildingsGroup.lTime) / 60.0f));
        }
        InGame_BuildingsGroup.mTranslateX = iTranslateX;
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, this.imgTop, this.imgBot);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_BuildingsGroup.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameBuildings();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_Buildings(false, false);
    }
    
    static {
        InGame_BuildingsGroup.menuX = 0;
        InGame_BuildingsGroup.menuY = 0;
        InGame_BuildingsGroup.menuHeight = 0;
        InGame_BuildingsGroup.mTranslateX = 0;
        InGame_BuildingsGroup.lTime = 0L;
        InGame_BuildingsGroup.iProvinceID = 0;
        InGame_BuildingsGroup.isCapital = false;
    }
}
