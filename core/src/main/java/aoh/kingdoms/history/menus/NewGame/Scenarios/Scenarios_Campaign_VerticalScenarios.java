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
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.Button_Preview;
import aoh.kingdoms.history.map.map.MapScenarios;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class Scenarios_Campaign_VerticalScenarios extends Menu
{
    private int iXPos;
    private int iYPos;
    private int iWidth;
    private int iHeight;
    
    public Scenarios_Campaign_VerticalScenarios() {
        this.iXPos = 0;
        this.iYPos = 0;
        this.iWidth = 480;
        this.iHeight = 480;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingTopBot = CFG.PADDING * 2 + CFG.PADDING / 2;
        final int buttonPaddingY = CFG.PADDING * 4;
        this.iYPos = -CFG.BUTTON_HEIGHT;
        this.iWidth = Game.mapBG.scenarioOver.getWidth() + paddingTopBot * 2;
        this.iHeight = CFG.GAME_HEIGHT + CFG.BUTTON_HEIGHT * 2;
        this.iXPos = CFG.GAME_WIDTH / 2 - this.iWidth / 2;
        this.iYPos = (int)(0.5f * (CFG.GAME_HEIGHT - this.iHeight));
        final int buttonPadding = CFG.PADDING * 2;
        final int buttonX = paddingTopBot;
        int buttonY = CFG.BUTTON_HEIGHT / 2;
        int activeX = 0;
        for (int i = Game.mapScenarios.SCENARIOS_SIZE - 1; i >= 0; --i) {
            if (Game.mapScenarios.details.get(i).Campaign) {
                menuElements.add(new Button_Preview(buttonX, buttonY, i));
                if (i == Game.scenarioID) {
                    activeX = menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth() / 2;
                }
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonPadding;
            }
        }
        if (buttonY < CFG.GAME_HEIGHT) {
            activeX = 0;
            int tW = 0;
            for (int j = 0; j < menuElements.size(); ++j) {
                tW += menuElements.get(j).getHeight() + buttonPadding;
            }
            tW -= buttonPadding;
            buttonY = (CFG.GAME_HEIGHT - tW) / 2;
            for (int j = 0; j < menuElements.size(); ++j) {
                menuElements.get(j).setPosY(buttonY);
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonPadding;
            }
        }
        menuElements.add(new Empty(0, 0, this.iWidth - 1, buttonY + CFG.BUTTON_HEIGHT));
        this.initMenu(null, this.iXPos, 0, this.iWidth, CFG.GAME_HEIGHT, menuElements, true);
        this.drawScrollPositionAlways2 = false;
        if (activeX != 0) {
            this.setMenuPosY(-activeX + CFG.GAME_HEIGHT / 2);
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, iTranslateX + this.iXPos, iTranslateY + this.iYPos, this.iWidth, this.iHeight);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_Scenarios();
    }
}
