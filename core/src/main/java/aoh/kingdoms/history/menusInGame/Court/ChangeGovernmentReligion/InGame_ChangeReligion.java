// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court.ChangeGovernmentReligion;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter2;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.Button_Capital;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.Button_CapitalMove;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_ChangeReligion extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_ChangeReligion() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING;
        int buttonX = paddingLeft;
        final int statH = (Button_CapitalMove.getButtonHeight() - CFG.PADDING * 2) / 3;
        final int iconWidth = (int)Math.ceil(ImageManager.getImage(Images.gold).getWidth() * 1.5f);
        final int costW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        menuElements.add(new ButtonGame(Game.lang.get("Close"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.religion.CHANGE_RELIGION_COST, 10), Images.gold, paddingLeft, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, iconWidth));
        menuElements.add(new TextBonus(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.religion.CHANGE_RELIGION_COST_LEGACY, 10), Images.legacy, paddingLeft + costW + CFG.PADDING, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, iconWidth));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        for (int o = 0; o < Game.religionManager.getReligionsSize(); ++o) {
            buttonX = paddingLeft;
            buttonX += Button_Capital.getButtonHeight() + CFG.PADDING;
            buttonY += CFG.PADDING;
            final int buttonBGY = buttonY - CFG.PADDING;
            final List<MenuElement> nElementsBonuses = Game.religionManager.getMenuElements(o, buttonX, buttonY, menuWidth - paddingLeft - buttonX, statH);
            if (nElementsBonuses.isEmpty()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, buttonX, buttonY, menuWidth - paddingLeft - buttonX, Button_Capital.getButtonHeight() * 3 / 5));
            }
            else if (nElementsBonuses.size() == 1) {
                nElementsBonuses.get(0).setHeight(Button_Capital.getButtonHeight() * 3 / 5);
                menuElements.add(nElementsBonuses.get(0));
            }
            else {
                for (int i = 0; i < nElementsBonuses.size(); ++i) {
                    if (i > 2) {
                        nElementsBonuses.get(i).setPosX(paddingLeft);
                        nElementsBonuses.get(i).setWidth(menuWidth - paddingLeft * 2);
                    }
                    menuElements.add(nElementsBonuses.get(i));
                }
            }
            menuElements.add(new ButtonReligion2(o, paddingLeft, buttonY, Button_Capital.getButtonHeight(), Button_Capital.getButtonHeight() * 3 / 5) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getReligionID() == this.getCurrent()) {
                        Game.menuManager.addToast_Error(Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name + " == " + Game.religionManager.getReligion(this.getCurrent()).Name);
                    }
                    else if (Game.getCiv(Game.player.iCivID).fGold < GameValues.religion.CHANGE_RELIGION_COST) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(GameValues.religion.CHANGE_RELIGION_COST, 10), Images.gold);
                    }
                    else if (Game.getCiv(Game.player.iCivID).fLegacy < GameValues.religion.CHANGE_RELIGION_COST_LEGACY) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(GameValues.religion.CHANGE_RELIGION_COST_LEGACY, 10), Images.legacy);
                    }
                    else {
                        InGame_ChangeReligion2.toReligionID = this.getCurrent();
                        Game.menuManager.rebuildInGame_ChangeReligion2();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = Game.religionManager.getHoverReligion(this.religionID, Game.player.iCivID);
                }
            });
            buttonY = 0;
            for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
                buttonY = Math.max(buttonY, menuElements.get(i).getPosY() + menuElements.get(i).getHeight());
            }
            buttonY += CFG.PADDING;
            buttonX = paddingLeft;
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonBGY, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, buttonY - buttonBGY));
            buttonY += CFG.PADDING;
        }
        buttonY = 0;
        for (int j = 0, iSize2 = menuElements.size(); j < iSize2; ++j) {
            if (buttonY < menuElements.get(j).getPosY() + menuElements.get(j).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(j).getPosY() + menuElements.get(j).getHeight() + CFG.PADDING * 2;
            }
        }
        menuY = CFG.GAME_HEIGHT / 8;
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter2(Game.getCiv(Game.player.iCivID).getCivName() + ": " + Game.lang.get("Religion"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_ChangeReligion.lTime;
            }
            
            @Override
            public int getFlagCivID() {
                return Game.player.iCivID;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_ChangeReligion.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_ChangeReligion.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_ChangeReligion.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_ChangeReligion.lTime = 0L;
    }
}
