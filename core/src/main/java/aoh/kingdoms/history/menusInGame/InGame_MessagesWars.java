// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.MessageWar;
import aoh.kingdoms.history.map.war.WarCivilization;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Renderer.SparksAnimation;
import aoh.kingdoms.history.menu.Menu;

public class InGame_MessagesWars extends Menu
{
    public static SparksAnimation sparksAnimation;
    
    public InGame_MessagesWars() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int iWarsWidth = Math.min(4, Math.max(Game.getCiv(Game.player.iCivID).diplomacy.iWarsSize, 1));
        final int menuPadding = CFG.PADDING * 3;
        int menuWidth = ImageManager.getImage(Images.warBG).getWidth() * iWarsWidth + CFG.PADDING * (iWarsWidth - 1);
        final int menuX = CFG.GAME_WIDTH - (Game.mapBG.iMinimapWidth + Images.boxTitleBORDERWIDTH) - menuWidth - menuPadding;
        menuWidth += ((Game.getCiv(Game.player.iCivID).diplomacy.iWarsSize > 4) ? menuPadding : 0);
        int buttonX = 0;
        final int buttonY = CFG.PADDING / 2;
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iWarsSize; ++i) {
            try {
                if (WarManager.lWars.get(Game.getCiv(Game.player.iCivID).diplomacy.lWars.get(i)).isAggressor(Game.player.iCivID)) {
                    menuElements.add(new MessageWar(buttonX, buttonY, true, WarManager.lWars.get(Game.getCiv(Game.player.iCivID).diplomacy.lWars.get(i)).lDefenders.get(0).iCivID, Game.getCiv(Game.player.iCivID).diplomacy.lWars.get(i)));
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                else if (WarManager.lWars.get(Game.getCiv(Game.player.iCivID).diplomacy.lWars.get(i)).isDefender(Game.player.iCivID)) {
                    menuElements.add(new MessageWar(buttonX, buttonY, true, WarManager.lWars.get(Game.getCiv(Game.player.iCivID).diplomacy.lWars.get(i)).lAggressors.get(0).iCivID, Game.getCiv(Game.player.iCivID).diplomacy.lWars.get(i)));
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        this.initMenu(null, menuX, CFG.GAME_HEIGHT - menuPadding - ImageManager.getImage(Images.warBG).getHeight(), menuWidth, ImageManager.getImage(Images.warBG).getHeight() + CFG.PADDING * 4, menuElements, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (this.getMenuElementsSize() > 0) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
            InGame_MessagesWars.sparksAnimation.draw2(oSB, CFG.GAME_WIDTH - (Game.mapBG.iMinimapWidth + Images.boxTitleBORDERWIDTH) - Images.sparkWidth * 3 / 4 + iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight * 3 / 4 + iTranslateY, Images.sparkWidth * 3 / 4, Images.sparkHeight * 3 / 4);
            oSB.setColor(Color.WHITE);
        }
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
    }
    
    static {
        InGame_MessagesWars.sparksAnimation = new SparksAnimation();
    }
}
