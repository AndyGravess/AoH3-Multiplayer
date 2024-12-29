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
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menu_element.button.ButtonIdeology_Court;
import aoh.kingdoms.history.menu_element.button.Button_Capital;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc2;
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

public class InGame_ChangeIdeology extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_ChangeIdeology() {
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
        menuElements.add(new Text_Desc2(Game.lang.get("EachGovernmentUnlocksSpecialBonusesThatBoostSpecificAreas"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), Images.gold, paddingLeft, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, iconWidth));
        menuElements.add(new TextBonus(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), Images.legacy, paddingLeft + costW + CFG.PADDING, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, iconWidth));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        for (int o = 0; o < Game.ideologiesManager.getIdeologiesSize(); ++o) {
            menuElements.add(new Text_Title_v2Center(Game.ideologiesManager.getIdeology(o).Name, -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            final int buttonBGY = buttonY - CFG.PADDING;
            buttonX += Button_Capital.getButtonHeight() + CFG.PADDING;
            final List<MenuElement> nElementsBonuses = Game.ideologiesManager.getMenuElements(o, buttonX, buttonY, menuWidth - paddingLeft - buttonX, statH);
            for (int i = 0; i < nElementsBonuses.size(); ++i) {
                if (i > 2) {
                    nElementsBonuses.get(i).setPosX(paddingLeft);
                    nElementsBonuses.get(i).setWidth(menuWidth - paddingLeft * 2);
                }
                menuElements.add(nElementsBonuses.get(i));
            }
            menuElements.add(new ButtonIdeology_Court(o, paddingLeft, buttonY, Button_Capital.getButtonHeight(), Button_Capital.getButtonHeight()) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = Game.ideologiesManager.getHoverIdeology(this.getCurrent(), false, true);
                }
                
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getIdeologyID() == this.getCurrent()) {
                        Game.menuManager.addToast_Error(Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).Name + " == " + Game.ideologiesManager.getIdeology(this.getCurrent()).Name);
                    }
                    else if (Game.getCiv(Game.player.iCivID).fGold < GameValues.government.CHANGE_GOVERNMENT_COST) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), Images.gold);
                    }
                    else if (Game.getCiv(Game.player.iCivID).fLegacy < GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), Images.legacy);
                    }
                    else if (Game.ideologiesManager.getIdeology(this.getCurrent()).REQUIRED_TECHNOLOGY >= 0 && !Game.getCiv(Game.player.iCivID).getTechResearched(Game.ideologiesManager.getIdeology(this.getCurrent()).REQUIRED_TECHNOLOGY)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("RequiredTechnology") + ": ", TechnologyTree.lTechnology.get(Game.ideologiesManager.getIdeology(this.getCurrent()).REQUIRED_TECHNOLOGY).Name, Game_Calendar.IMG_TECHNOLOGY);
                    }
                    else {
                        InGame_ChangeIdeology2.toIdeologyID = this.getCurrent();
                        Game.menuManager.rebuildInGame_ChangeIdeology2();
                    }
                }
            });
            buttonY = 0;
            for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
                buttonY = Math.max(buttonY, menuElements.get(i).getPosY() + menuElements.get(i).getHeight());
            }
            buttonY += CFG.PADDING;
            buttonX = paddingLeft;
            if (Game.ideologiesManager.getIdeology(o).REQUIRED_TECHNOLOGY >= 0 && !Game.getCiv(Game.player.iCivID).getTechResearched(Game.ideologiesManager.getIdeology(o).REQUIRED_TECHNOLOGY)) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("RequiredTechnology") + ": ", TechnologyTree.lTechnology.get(Game.ideologiesManager.getIdeology(o).REQUIRED_TECHNOLOGY).Name, Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, statH, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2) {
                    @Override
                    public Color getColorBonus() {
                        return Colors.HOVER_NEGATIVE;
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
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
        this.initMenu(new MenuTitleIMG_FlagCenter2(Game.lang.get("ChangeTypeOfGovernment"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_ChangeIdeology.lTime;
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
        if (InGame_ChangeIdeology.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_ChangeIdeology.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_ChangeIdeology.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_ChangeIdeology.lTime = 0L;
    }
}
