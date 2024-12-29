// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.NewGame;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.button.ButtonFlag2_CivName;
import aoh.kingdoms.history.menu_element.button.ButtonFlag2_CivName_Random;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class NewGameFlags extends Menu
{
    public NewGameFlags() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int buttonX = CFG.PADDING * 2;
        final int buttonY = CFG.PADDING * 2;
        final List<Integer> tCivs = new ArrayList<Integer>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getCapitalProvinceID() >= 0) {
                tCivs.add(i);
            }
        }
        menuElements.add(new ButtonFlag2_CivName_Random(buttonX, buttonY, true));
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING + CFG.PADDING / 2;
        while (!tCivs.isEmpty()) {
            int bestID = 0;
            for (int j = 1, iSize = tCivs.size(); j < iSize; ++j) {
                if (Game.getCiv(tCivs.get(bestID)).iCivRankScore < Game.getCiv(tCivs.get(j)).iCivRankScore) {
                    bestID = j;
                }
            }
            menuElements.add(new ButtonFlag2_CivName((int)tCivs.get(bestID), buttonX, buttonY, true) {
                @Override
                public void actionElement() {
                    Game.setActiveProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0 && Game.player.iCivID != Game.getProvince(Game.iActiveProvince).getCivID()) {
                        Game.player.iCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.mapCoords.centerToProvinceID(Game.iActiveProvince);
                        Game.menuManager.rebuildNewGameCiv();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = null;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING + CFG.PADDING / 2;
            tCivs.remove(bestID);
            if (menuElements.size() >= GameValues.newGame.NEW_GAME_FLAGS_LIMIT) {
                break;
            }
        }
        final int menuH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2 + buttonY * 2;
        final int buttonPlayW = ImageManager.getImage(Images.buttonPlay).getWidth() + CFG.PADDING;
        this.initMenu(null, buttonPlayW, CFG.GAME_HEIGHT - menuH, CFG.GAME_WIDTH - buttonPlayW * 2, menuH, menuElements, true);
        this.drawScrollPositionAlways = false;
        this.drawScrollPositionAlways2 = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.65f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, this.getWidth(), this.getHeight() / 2);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
}
