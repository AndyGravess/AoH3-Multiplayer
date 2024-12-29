// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding_Special;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_ListOfBuildings extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static boolean IN_BUILDINGS;
    
    public InGame_ListOfBuildings() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        final int buttonX = paddingLeft;
        int buttonY = 0;
        final int buttonYPadding = CFG.PADDING;
        InGame_ListOfBuildings.IN_BUILDINGS = true;
        InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE = false;
        int numOfBuildings = 0;
        for (int x = 0; x < 4; ++x) {
            menuElements.add(new Text_Title_v2Center((x == 0) ? Game.lang.get("Administration") : ((x == 1) ? Game.lang.get("Military") : ((x == 2) ? Game.lang.get("Economy") : Game.lang.get("UniqueCapitalBuildings"))), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            for (int i = 0; i < BuildingsManager.buildingsSize; ++i) {
                for (int j = 0; j < BuildingsManager.buildingSize.get(i); ++j) {
                    if (BuildingsManager.buildings.get(i).GroupID == x) {
                        menuElements.add(new ButtonBuilding_Special(true, i, j, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true, ""));
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                        ++numOfBuildings;
                    }
                }
            }
        }
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Resources"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int k = BuildingsManager.buildingsResourceStartID; k < BuildingsManager.buildingsResourceSize; ++k) {
            for (int l = 0; l < BuildingsManager.buildingSize.get(k); ++l) {
                if (BuildingsManager.buildings.get(k).RequiredResource >= 0 && !BuildingsManager.buildings.get(k).UniqueCapitalBuilding) {
                    menuElements.add(new ButtonBuilding_Special(true, k, l, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true, ""));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                    ++numOfBuildings;
                }
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get("ListOfBuildings"), "" + numOfBuildings, false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_ListOfBuildings.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_ListOfBuildings.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_ListOfBuildings.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.rulerOver).getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_ListOfBuildings.lTime = CFG.currentTimeMillis;
        InGame_ListOfBuildings.lTime2 = CFG.currentTimeMillis;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_ListOfBuildings.lTime = 0L;
        InGame_ListOfBuildings.lTime2 = 0L;
        InGame_ListOfBuildings.IN_BUILDINGS = false;
    }
}
