// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.NewGame.Scenarios;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.button.Button_Preview;
import aoh.kingdoms.history.map.map.MapScenarios;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class ScenariosList_NewGame extends Menu
{
    private int iXPos;
    public static int iYPos;
    private int iWidth;
    private int iHeight;
    
    public ScenariosList_NewGame() {
        this.iXPos = 0;
        this.iWidth = 480;
        this.iHeight = 480;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingTopBot = CFG.PADDING * 2 + CFG.PADDING / 2;
        final int buttonPaddingY = CFG.PADDING * 4;
        this.iXPos = -CFG.BUTTON_WIDTH;
        this.iWidth = CFG.GAME_WIDTH + CFG.BUTTON_WIDTH * 2;
        this.iHeight = Game.mapBG.scenarioOver.getHeight() + buttonPaddingY * 2;
        ScenariosList_NewGame.iYPos = (int)(0.5f * (CFG.GAME_HEIGHT - this.iHeight));
        final int buttonPadding = CFG.PADDING * 2;
        int buttonX = CFG.BUTTON_WIDTH;
        final int buttonY = CFG.PADDING * 3;
        int activeX = 0;
        for (int i = Game.mapScenarios.SCENARIOS_SIZE - 1; i >= 0; --i) {
            if (!Game.mapScenarios.details.get(i).Campaign) {
                menuElements.add(new Button_Preview(buttonX, buttonY, i) {
                    @Override
                    public void setIsHovered(final boolean isHovered) {
                        if (isHovered) {
                            Scenarios.activeDescID = this.getCurrent();
                        }
                        else {
                            Scenarios.activeDescID = Game.scenarioID;
                        }
                        super.setIsHovered(isHovered);
                    }
                });
                if (i == Game.scenarioID) {
                    activeX = menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth() / 2;
                }
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + buttonPadding;
            }
        }
        if (buttonX < CFG.GAME_WIDTH) {
            activeX = 0;
            int tW = 0;
            for (int j = 0; j < menuElements.size(); ++j) {
                tW += menuElements.get(j).getWidth() + buttonPadding;
            }
            tW -= buttonPadding;
            buttonX = (CFG.GAME_WIDTH - tW) / 2;
            for (int j = 0; j < menuElements.size(); ++j) {
                menuElements.get(j).setPosX(buttonX);
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + buttonPadding;
            }
        }
        this.initMenu(null, 0, CFG.GAME_HEIGHT / 2 - (Button_Preview.getButtonHeight() + buttonY * 2) / 2, CFG.GAME_WIDTH, Button_Preview.getButtonHeight() + buttonY * 2, menuElements, true);
        this.drawScrollPositionAlways = false;
        if (activeX != 0) {
            this.setMenuPosX(-activeX + CFG.GAME_WIDTH / 2);
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, iTranslateX + this.iXPos, iTranslateY + ScenariosList_NewGame.iYPos, this.iWidth, this.iHeight);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_Scenarios();
    }
    
    static {
        ScenariosList_NewGame.iYPos = 0;
    }
}
