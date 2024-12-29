// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court.ChangeGovernmentReligion;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter2;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonIdeology_Court;
import aoh.kingdoms.history.menu_element.button.Button_Capital;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.Button_CapitalMove;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_ChangeIdeology2 extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int toIdeologyID;
    
    public InGame_ChangeIdeology2() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = 0;
        int buttonX = paddingLeft;
        final int statH = (Button_CapitalMove.getButtonHeight() - CFG.PADDING * 2) / 3;
        menuElements.add(new Text_Title_v2Center(Game.ideologiesManager.getIdeology(InGame_ChangeIdeology2.toIdeologyID).Name, -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int buttonBGY = buttonY - CFG.PADDING;
        buttonX += Button_Capital.getButtonHeight() + CFG.PADDING;
        final List<MenuElement> nElementsBonuses = Game.ideologiesManager.getMenuElements(InGame_ChangeIdeology2.toIdeologyID, buttonX, buttonY, menuWidth - paddingLeft - buttonX, statH);
        for (int i = 0; i < nElementsBonuses.size(); ++i) {
            if (i > 2) {
                nElementsBonuses.get(i).setPosX(paddingLeft);
                nElementsBonuses.get(i).setWidth(menuWidth - paddingLeft * 2);
            }
            menuElements.add(nElementsBonuses.get(i));
        }
        menuElements.add(new ButtonIdeology_Court(InGame_ChangeIdeology2.toIdeologyID, paddingLeft, buttonY, Button_Capital.getButtonHeight(), Button_Capital.getButtonHeight()) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = Game.ideologiesManager.getHoverIdeology(this.getCurrent(), false, false);
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
        final int iconWidth = (int)Math.ceil(ImageManager.getImage(Images.gold).getWidth() * 1.5f);
        final int costW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), Images.gold, paddingLeft, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, iconWidth));
        menuElements.add(new TextBonus(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), Images.legacy, paddingLeft + costW + CFG.PADDING, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, iconWidth));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                InGame_ChangeIdeology2.confirm();
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = Game.ideologiesManager.getHoverIdeology(InGame_ChangeIdeology2.toIdeologyID, false, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
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
                return InGame_ChangeIdeology2.lTime;
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
        if (InGame_ChangeIdeology2.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_ChangeIdeology2.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_ChangeIdeology2.lTime = CFG.currentTimeMillis;
    }
    
    public static void confirm() {
        if (Game.getCiv(Game.player.iCivID).getIdeologyID() == InGame_ChangeIdeology2.toIdeologyID) {
            Game.menuManager.addToast_Error(Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).Name + " == " + Game.ideologiesManager.getIdeology(InGame_ChangeIdeology2.toIdeologyID).Name);
        }
        else if (Game.getCiv(Game.player.iCivID).fGold < GameValues.government.CHANGE_GOVERNMENT_COST) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), Images.gold);
        }
        else if (Game.getCiv(Game.player.iCivID).fLegacy < GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), Images.legacy);
        }
        else if (Game.ideologiesManager.getIdeology(InGame_ChangeIdeology2.toIdeologyID).REQUIRED_TECHNOLOGY >= 0 && !Game.getCiv(Game.player.iCivID).getTechResearched(Game.ideologiesManager.getIdeology(InGame_ChangeIdeology2.toIdeologyID).REQUIRED_TECHNOLOGY)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("RequiredTechnology") + ": ", TechnologyTree.lTechnology.get(Game.ideologiesManager.getIdeology(InGame_ChangeIdeology2.toIdeologyID).REQUIRED_TECHNOLOGY).Name, Game_Calendar.IMG_TECHNOLOGY);
        }
        else if (Game.ideologiesManager.changeGovernmentType(Game.player.iCivID, InGame_ChangeIdeology2.toIdeologyID, false)) {
            if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions.iGovernmentID) {
                Game.menuManager.rebuildInGame_Government();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("Government"), Game.ideologiesManager.getIdeology(InGame_ChangeIdeology2.toIdeologyID).Name);
            InGame_Info.imgID = Images.infoCrown;
            Game.menuManager.setVisibleInGame_PopUp(false);
        }
    }
    
    static {
        InGame_ChangeIdeology2.lTime = 0L;
        InGame_ChangeIdeology2.toIdeologyID = 0;
    }
}
