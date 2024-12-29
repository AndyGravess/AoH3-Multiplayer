// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.MessageButton;
import aoh.kingdoms.history.mainGame.Player.MessageTypes.PMessage;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Messages extends Menu
{
    public InGame_Messages() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int buttonX = 0;
        final int buttonY = CFG.PADDING / 2;
        try {
            for (int i = Game.player.iMessagesSize - 1; i >= 0; --i) {
                menuElements.add(new MessageButton(Game.player.messages.get(i).key, Game.player.messages.get(i).fromCivID, Game.player.messages.get(i).getImageID(), Game.player.messages.get(i).expiresTurnID, Game.player.messages.get(i).time, buttonX, buttonY, true) {
                    @Override
                    public void actionElement() {
                        super.actionElement();
                        final MenuManager menuManager = Game.menuManager;
                        MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Messages.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Messages.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                            @Override
                            public Color getColor() {
                                return Colors.HOVER_GOLD;
                            }
                        });
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.initMenu(null, InGame.rankPosXW, ImageManager.getImage(Images.topStats).getHeight(), CFG.GAME_WIDTH - InGame.rankPosXW, ImageManager.getImage(Images.messageBG).getHeight() + CFG.PADDING * 3, menuElements, true);
    }
    
    @Override
    public void actionElement(final int nMenuElementID) {
        super.actionElement(nMenuElementID);
        ProvinceDraw.drawProvincesCiv_HoveredFlagID = 0;
    }
}
