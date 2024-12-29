// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter2;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CourtOptions extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static int menuH;
    public static int iActiveID;
    public static int buildID;
    public static int iGovernmentID;
    public static int iLawID;
    
    public InGame_CourtOptions() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth() - Images.boxTitleBORDERWIDTH * 2;
        final int menuX = Images.boxTitleBORDERWIDTH + InGame_CourtOptions2.getOtherMenuPosX();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        final int buttonX = paddingLeft;
        int buttonY = 0;
        final int buttonW = CFG.BUTTON_HEIGHT2;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT4 : CFG.BUTTON_HEIGHT2;
        menuElements.add(new Empty(0, 0, 1, 1));
        buttonY = menuElements.get(0).getPosY() + menuElements.get(0).getHeight();
        int menuHeight = buttonY + 2;
        menuHeight = 0;
        InGame_CourtOptions.menuH = menuHeight + ImageManager.getImage(Images.title500).getHeight();
        this.initMenu(new MenuTitleIMG_FlagCenter2(Game.lang.get(GameValues.court.COUNCIL_NAME), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_Court.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_Court.lTime2;
            }
            
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
                super.draw(oSB, nPosX - Images.boxTitleBORDERWIDTH, nPosY, nWidth + Images.boxTitleBORDERWIDTH * 2, titleStatus);
            }
            
            @Override
            public void action() {
                super.action();
                Game.menuManager.setOrderOfMenu_InGameCourt();
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        Renderer.drawMenusBoxTopOnly(oSB, this.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH * 2, this.getHeight(), false, Images.insideTop500);
        oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        oSB.setColor(Colors.COLOR_GRADIENT_BG_BLUE);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - 1 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(Colors.COLOR_GRADIENT_BG_BLUE);
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_BOX_FRAME.r, Colors.COLOR_BOX_FRAME.g, Colors.COLOR_BOX_FRAME.b, 0.35f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Colors.COLOR_BOX_FRAME);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_Court(false);
    }
    
    public static void disableAllViews() {
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MOVE_CAPITAL) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        InGame_Court_Buildings2.oBuildingID = null;
    }
    
    @Override
    public void actionElement(final int nMenuElementID) {
        super.actionElement(nMenuElementID);
        if (InGame_Court.iActiveCivID != Game.player.iCivID) {
            InGame_Court.iActiveCivID = Game.player.iCivID;
        }
    }
    
    static {
        InGame_CourtOptions.menuH = 0;
        InGame_CourtOptions.iActiveID = -7777;
        InGame_CourtOptions.buildID = 0;
        InGame_CourtOptions.iGovernmentID = 0;
        InGame_CourtOptions.iLawID = 0;
    }
}
