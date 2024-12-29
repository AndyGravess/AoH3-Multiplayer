// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc2;
import aoh.kingdoms.history.menu_element.button.Button_Advantage2;
import aoh.kingdoms.history.mainGame.Game;
import java.util.List;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CivilizationAdvantages2 extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iMenuWidth;
    
    public InGame_CivilizationAdvantages2() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int titleHeight = ImageManager.getImage(Images.title928).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop928).getWidth();
        InGame_CivilizationAdvantages2.iMenuWidth = menuWidth - Images.boxTitleBORDERWIDTH * 2;
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title928).getHeight();
        final int buttonYPadding = CFG.PADDING + CFG.PADDING / 2;
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING * 2;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE = false;
        final List<List<MenuElement>> toSort = new ArrayList<List<MenuElement>>();
        final int buttonWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING * 4) / 3;
        menuElements.add(new Button_Advantage2(buttonX, buttonY, 0, 0, Game.lang.get("ArmyRecruitmentCost"), "-10%", Images.gold));
        menuElements.add(new Text_Desc2(Game.lang.get("AdditionalBuildingsInProvince"), buttonX + Button_Advantage2.getButtonHeight() + CFG.PADDING, buttonY, buttonWidth - Button_Advantage2.getButtonWidth() - CFG.PADDING));
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(buttonX - CFG.PADDING, buttonY - CFG.PADDING, buttonWidth + CFG.PADDING * 2, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new Button_Advantage2(buttonX, buttonY, 0, 0, Game.lang.get("ArmyRecruitmentCost"), "-10%", Images.gold));
        menuElements.add(new Text_Desc2(Game.lang.get("AdditionalBuildingsInProvince"), buttonX + Button_Advantage2.getButtonHeight() + CFG.PADDING, buttonY, buttonWidth - Button_Advantage2.getButtonWidth() - CFG.PADDING));
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(buttonX - CFG.PADDING, buttonY - CFG.PADDING, buttonWidth + CFG.PADDING * 2, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new Button_Advantage2(buttonX, buttonY, 0, 0, Game.lang.get("ArmyRecruitmentCost"), "-10%", Images.gold));
        menuElements.add(new Text_Desc2(Game.lang.get("AdditionalBuildingsInProvince"), buttonX + Button_Advantage2.getButtonHeight() + CFG.PADDING, buttonY, buttonWidth - Button_Advantage2.getButtonWidth() - CFG.PADDING));
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(buttonX - CFG.PADDING, buttonY - CFG.PADDING, buttonWidth + CFG.PADDING * 2, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("CivilizationAdvantages"), Game.lang.get("AdvantagePoints") + ": " + Game.getCiv(Game.player.iCivID).getAdvantagePoints(), false, false, Images.title928) {
            @Override
            public long getTime() {
                return InGame_CivilizationAdvantages2.lTime2;
            }
            
            @Override
            public int getFlagCivID() {
                return Game.player.iCivID;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_CivilizationAdvantages2.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_CivilizationAdvantages2.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop928, Images.insideBot928);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_CivilizationAdvantages2.lTime = CFG.currentTimeMillis;
        InGame_CivilizationAdvantages2.lTime2 = CFG.currentTimeMillis;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_CivilizationAdvantages2.lTime = 0L;
        InGame_CivilizationAdvantages2.lTime2 = 0L;
        InGame_CivilizationAdvantages2.iMenuWidth = 0;
    }
}
