// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonUnit_03;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_ListOfUnits extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    
    public InGame_ListOfUnits() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title698).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop698).getWidth() - Images.boxTitleBORDERWIDTH * 2;
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title698).getHeight();
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        InGame_ListOfBuildings.IN_BUILDINGS = false;
        InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE = false;
        int numOfUnits = 0;
        final int buttonWidth = ImageManager.getImage(Images.insideTop).getWidth() - paddingLeft * 2 - CFG.BUTTON_HEIGHT;
        for (int i = 0; i < ArmyManager.lArmy.size(); ++i) {
            for (int j = 0; j < ArmyManager.lArmy.get(i).size(); ++j) {
                menuElements.add(new ButtonUnit_03(i, j, buttonX, buttonY, buttonWidth, true));
                buttonX += buttonWidth + CFG.PADDING;
                ++numOfUnits;
            }
            buttonX = paddingLeft;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        int maxWidth = 0;
        for (int k = menuElements.size() - 1; k >= 0; --k) {
            maxWidth = Math.max(maxWidth, menuElements.get(k).getPosX() + menuElements.get(k).getWidth() + CFG.PADDING);
        }
        menuElements.add(new Empty(0, 0, maxWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get("ListOfUnits"), "" + numOfUnits, false, false, Images.title698) {
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, int nWidth, final Status titleStatus) {
                nWidth += Images.boxTitleBORDERWIDTH * 2;
                super.draw(oSB, nPosX - Images.boxTitleBORDERWIDTH, nPosY, nWidth, titleStatus);
            }
            
            @Override
            public long getTime() {
                return InGame_ListOfUnits.lTime2;
            }
        }, menuX + Images.boxTitleBORDERWIDTH, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.scrollExtraPosX = Images.boxTitleBORDERWIDTH;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_ListOfUnits.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_ListOfUnits.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH * 2, this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH * 2, this.getHeight() + CFG.PADDING, false, Images.insideTop698, Images.insideBot698);
        ImageManager.getImage(Images.outlinerOver).draw2(oSB, this.getPosX() + (this.getWidth() + Images.boxTitleBORDERWIDTH) / 2 - ImageManager.getImage(Images.outlinerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.outlinerOver).getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.outlinerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_ListOfUnits.lTime = CFG.currentTimeMillis;
        InGame_ListOfUnits.lTime2 = CFG.currentTimeMillis;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_ListOfUnits.lTime = 0L;
        InGame_ListOfUnits.lTime2 = 0L;
    }
}
