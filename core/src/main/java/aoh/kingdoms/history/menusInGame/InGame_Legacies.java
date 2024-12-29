// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.mainGame.Steam.SteamAchievementsManager;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import java.util.List;
import aoh.kingdoms.history.menu_element.Toast;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonLegacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleLegacy;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.LegacyManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Legacies extends Menu
{
    public static int UNLOCK_LEGACY_ID;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iActiveCivID;
    public static float bgAlpha;
    public static long bgTIME;
    public static long bgTIME_CHANGE;
    
    public InGame_Legacies() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title928).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title928).getHeight();
        int buttonY = 0;
        int buttonX = Images.boxTitleBORDERWIDTH;
        final List<Integer> tMaxLvlLegacies_Group = new ArrayList<Integer>();
        final List<Integer> tCivLvlLegacies_Group = new ArrayList<Integer>();
        for (int i = 0; i < LegacyManager.iLegacyGroupsSize; ++i) {
            tMaxLvlLegacies_Group.add(0);
            tCivLvlLegacies_Group.add(0);
        }
        for (int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
            tMaxLvlLegacies_Group.set(LegacyManager.legacies.get(i).GroupID, tMaxLvlLegacies_Group.get(LegacyManager.legacies.get(i).GroupID) + LegacyManager.legacies.get(i).CostLegacy.length);
        }
        for (int i = 0; i < Game.getCiv(InGame_Legacies.iActiveCivID).getLegaciesSize(); ++i) {
            tCivLvlLegacies_Group.set(LegacyManager.legacies.get(Game.getCiv(InGame_Legacies.iActiveCivID).getCivLegacy(i).id).GroupID, tCivLvlLegacies_Group.get(LegacyManager.legacies.get(Game.getCiv(InGame_Legacies.iActiveCivID).getCivLegacy(i).id).GroupID) + Game.getCiv(InGame_Legacies.iActiveCivID).getCivLegacy(i).lvl + 1);
        }
        menuElements.add(new Text_TitleLegacy(LegacyManager.legacyGroups.get(0), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, "[" + Game.lang.get("Unlocked") + ": " + tCivLvlLegacies_Group.get(0) + "/" + tMaxLvlLegacies_Group.get(0) + "]"));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        int i = 0;
        int currentGroupID = LegacyManager.legacies.get(0).GroupID;
        while (i < LegacyManager.iLegaciesSize) {
            if (LegacyManager.legacies.get(i).GroupID != currentGroupID) {
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
                buttonX = Images.boxTitleBORDERWIDTH;
                currentGroupID = LegacyManager.legacies.get(i).GroupID;
                menuElements.add(new Text_TitleLegacy(LegacyManager.legacyGroups.get(currentGroupID), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, "[" + Game.lang.get("Unlocked") + ": " + tCivLvlLegacies_Group.get(currentGroupID) + "/" + tMaxLvlLegacies_Group.get(currentGroupID) + "]"));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            }
            menuElements.add(new ButtonLegacy(buttonX, buttonY, i, Game.getCiv(InGame_Legacies.iActiveCivID).getLegacyLevel(i)) {
                @Override
                public void actionElement() {
                    if (InGame_Legacies.iActiveCivID == Game.player.iCivID) {
                        if (this.currentLvl + 1 < LegacyManager.legacies.get(this.legacyID).CostLegacy.length) {
                            InGame_Legacies.UNLOCK_LEGACY_ID = this.getCurrent();
                            InGame_Legacies.unlockLegacy();
                        }
                        else {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Max") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(LegacyManager.legacies.get(this.legacyID).Name), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.menuManager.addToast(new Toast(nElements, 0, 6000));
                        }
                    }
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth();
            ++i;
        }
        buttonY = 0;
        i = 0;
        for (int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        if (InGame_Legacies.iActiveCivID == Game.player.iCivID) {
            this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get("CivilizationLegacy"), Game.lang.get("Legacy") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fLegacy, 1), false, false, Images.title928) {
                @Override
                public long getTime() {
                    return InGame_Legacies.lTime;
                }
            }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, tMenuHeight, menuElements, true, true);
        }
        else {
            this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("CivilizationLegacy"), Game.getCiv(InGame_Legacies.iActiveCivID).getCivName(), false, false, Images.title928) {
                @Override
                public long getTime() {
                    return InGame_Legacies.lTime;
                }
                
                @Override
                public int getFlagCivID() {
                    return InGame_Legacies.iActiveCivID;
                }
            }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, tMenuHeight, menuElements, true, true);
        }
        this.drawScrollPositionAlways = false;
        InGame_Legacies.lTime = CFG.currentTimeMillis;
        InGame_Legacies.bgTIME = System.currentTimeMillis();
        InGame_Legacies.bgTIME_CHANGE = System.currentTimeMillis();
        InGame_Legacies.bgAlpha = 0.0f;
    }
    
    public int getInnerTitleHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 6;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Legacies.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_Legacies.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop928, Images.insideBot928);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Legacies.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameLegacies();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
    }
    
    public static final void unlockLegacy() {
        if (Game.getCiv(Game.player.iCivID).unlockLegacy(InGame_Legacies.UNLOCK_LEGACY_ID)) {
            Game.menuManager.rebuildInGame_Legacies();
            Game.menuManager.setOrderOfMenu_InGameLegacies();
            InGame_Legacies.lTime = 0L;
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.soundsManager.stopLegacySound();
            switch (Game.getCiv(Game.player.iCivID).getLegacyLevel(InGame_Legacies.UNLOCK_LEGACY_ID)) {
                case 0: {
                    Game.soundsManager.playSound(SoundsManager.LEGACY_0);
                    break;
                }
                case 1: {
                    Game.soundsManager.playSound(SoundsManager.LEGACY_1);
                    break;
                }
                default: {
                    Game.soundsManager.playSound(SoundsManager.LEGACY_2);
                    break;
                }
            }
            Game.menuManager.rebuildInGameLegacies_Info(Game.lang.get(LegacyManager.legacies.get(InGame_Legacies.UNLOCK_LEGACY_ID).Name), Game.lang.get("Unlocked") + " " + (Game.getCiv(Game.player.iCivID).getLegacyLevel(InGame_Legacies.UNLOCK_LEGACY_ID) + 1) + "/" + LegacyManager.legacies.get(InGame_Legacies.UNLOCK_LEGACY_ID).CostLegacy.length);
            SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.UNLOCK_LEGACY);
            Game.player.currSituation.buildPlayerLegaciesLVL();
            if (Game.menuManager.getVisibleInGame_CivBonuses()) {
                Game.menuManager.rebuildInGame_CivBonuses();
                Game.menuManager.setVisibleInGame_CivBonuses(true);
                InGame_CivBonuses.lTime = 0L;
            }
        }
        else {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy"), "", Images.legacy);
        }
    }
    
    static {
        InGame_Legacies.UNLOCK_LEGACY_ID = 0;
        InGame_Legacies.lTime = 0L;
        InGame_Legacies.iActiveCivID = 0;
        InGame_Legacies.bgAlpha = 0.0f;
    }
}
