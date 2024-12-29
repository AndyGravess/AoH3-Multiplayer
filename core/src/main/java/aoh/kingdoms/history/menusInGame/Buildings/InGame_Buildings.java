// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Buildings;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlue;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Buildings extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    private long lTime;
    public static int iProvinceID;
    
    public InGame_Buildings() {
        this.lTime = 0L;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 4;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = 0;
        final int textPosX = CFG.PADDING * 4;
        menuElements.add(new Text_Title(Game.lang.get("BuildingsConstructed"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("BuildingsConstructed"));
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonBuilding(true, 6, 0, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        final boolean addUniqueCapitalBuildings = true;
        if (addUniqueCapitalBuildings) {
            menuElements.add(new Text_TitleBlue(Game.lang.get("UniqueCapitalBuildings"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
                @Override
                public void updateLanguage() {
                    this.setText(Game.lang.get("UniqueCapitalBuildings"));
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        boolean addOnce = true;
        for (int i = 0; i < BuildingsManager.buildingsSize; ++i) {
            for (int j = 0; j < BuildingsManager.buildingSize.get(i); ++j) {
                if (addOnce && !BuildingsManager.buildings.get(i).UniqueCapitalBuilding) {
                    if (addUniqueCapitalBuildings) {
                        menuElements.add(new Text_TitleBlue(Game.lang.get("Buildings"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
                            @Override
                            public void updateLanguage() {
                                this.setText(Game.lang.get("Buildings"));
                            }
                        });
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                    }
                    addOnce = false;
                }
                menuElements.add(new ButtonBuilding(false, i, j, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("ConstructNewBuilding"), Game.getProvince(InGame_Buildings.iProvinceID).getProvinceName(), false, false, Images.title1Red) {
            @Override
            public int getFlagCivID() {
                return Game.getProvince(InGame_Buildings.iProvinceID).getCivID();
            }
            
            @Override
            public long getTime() {
                return InGame_Buildings.this.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (this.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - this.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.recruitArmyOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.recruitArmyOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_Buildings.iProvinceID = 0;
    }
}
