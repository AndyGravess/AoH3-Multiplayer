// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.AtomicNukes;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.Button_NuclearReactor;
import aoh.kingdoms.history.menu_element.button.Button_AtomicBomb;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BuildAtomicBomb extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_BuildAtomicBomb() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING;
        int buttonX = Images.boxTitleBORDERWIDTH;
        int statsY = buttonY;
        menuElements.add(new Button_AtomicBomb(paddingLeft, buttonY));
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 3;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        final int statW = menuWidth - buttonX - paddingLeft;
        final int statH = (Button_NuclearReactor.getButtonHeight() - CFG.PADDING * 2) / 3;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.lang.get("AtomicBombs") + ": " + Game.getCiv(Game.player.iCivID).getNukes(), buttonX, statsY, statW, statH) {
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= Game.getNuclearReactor_MaxLvl(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID), Images.nuke);
                }
                else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 5) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_UpgradeNuclearReactor();
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Nukes.getHoverBuildAtomicBomb();
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getAtomicBombCost(Game.player.iCivID), 100), Images.gold, buttonX, statsY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_RIGHT;
            }
            
            @Override
            public String getTextToDraw() {
                if (this.iCurrent != (int)Game.getAtomicBombCost(Game.player.iCivID)) {
                    this.iCurrent = (int)Game.getAtomicBombCost(Game.player.iCivID);
                    this.sText2 = CFG.getPrecision2(Game.getAtomicBombCost(Game.player.iCivID), 100);
                    Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), this.sText2);
                    this.iTextBonusW = (int)Renderer.glyphLayout.width;
                }
                return super.getTextToDraw();
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProductionTime") + ": ", "" + Game.lang.get("XDays", Game.getAtomicBombProductionTime(Game.player.iCivID)), Images.time, buttonX, statsY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_RIGHT;
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        buttonX = paddingLeft;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.PADDING - CFG.BUTTON_HEIGHT2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.PADDING - CFG.BUTTON_HEIGHT2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.BUTTON_HEIGHT2) / 2, Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
            @Override
            public void actionElement() {
                InGame_BuildAtomicBomb.confirm();
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Nukes.getHoverBuildAtomicBomb();
            }
        });
        menuElements.add(new ButtonGame("+", CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING * 2 + (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.PADDING - CFG.BUTTON_HEIGHT2), buttonY, CFG.BUTTON_HEIGHT2, Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
            @Override
            public void actionElement() {
                InGame_Nukes.buildAtomicBomb();
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Nukes.getHoverBuildAtomicBomb();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("BuildAnAtomicBomb"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_BuildAtomicBomb.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 4, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_BuildAtomicBomb.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_BuildAtomicBomb.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        oSB.setColor(Colors.COLOR_GRADIENT);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_BuildAtomicBomb.lTime = CFG.currentTimeMillis;
    }
    
    public static void confirm() {
        InGame_Nukes.buildAtomicBomb();
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    static {
        InGame_BuildAtomicBomb.lTime = 0L;
    }
}
