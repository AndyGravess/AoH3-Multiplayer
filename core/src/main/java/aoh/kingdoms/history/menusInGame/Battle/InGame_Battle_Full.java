// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Battle;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleTechTree;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegimentDefeated;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegimentEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegiment;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.map.battles.BattleRegiment;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Battle_Full extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static String key;
    public static int iProvinceID;
    public static int battleID;
    public static int TURN_ID;
    public static String sDay;
    public static int iDayWidth;
    
    public InGame_Battle_Full() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int paddingTop = CFG.PADDING * 4;
        final int titleHeight = ImageManager.getImage(Images.titleTechTree).getHeight();
        final int menuWidth = CFG.GAME_WIDTH;
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title928).getHeight();
        int buttonY = paddingTop;
        int buttonX = paddingLeft;
        buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
        for (int i = 0; i < Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.secondLine.size(); ++i) {
            if (Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.secondLine.get(i) != null) {
                menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.secondLine.get(i).c, this.getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.secondLine.get(i).aR.uID).Line), buttonX, buttonY, i, 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.secondLine.get(i).aR.uID).get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.secondLine.get(i).aR.aID).AttackRange, true, false) {
                    @Override
                    public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                        return Game.battleManager.getBattle(battleID).attackingArmy.secondLine.get(id);
                    }
                });
            }
            else {
                menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {});
            }
            buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
        }
        buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
        buttonY += ImageManager.getImage(Images.battleArmy0).getHeight() + 2;
        for (int i = 0; i < Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.size(); ++i) {
            if (Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i) != null) {
                if (Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i).aR.num > 0) {
                    menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i).c, this.getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i).aR.uID).Line), buttonX, buttonY, i, 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i).aR.uID).get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i).aR.aID).AttackRange, false, false) {});
                }
                else {
                    menuElements.add(new ButtonBattleRegimentDefeated(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i).c, this.getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).attackingArmy.firstLine.get(i).aR.uID).Line), buttonX, buttonY, i, 0) {});
                }
            }
            else {
                menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy0, buttonX, buttonY));
            }
            buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
        }
        buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
        buttonY += getMiddleHeight() + ImageManager.getImage(Images.battleArmy0).getHeight() + 1;
        for (int i = 0; i < Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.size(); ++i) {
            if (Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i) != null) {
                if (Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i).aR.num > 0) {
                    menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i).c, this.getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i).aR.uID).Line), buttonX, buttonY, i, 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i).aR.uID).get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i).aR.aID).AttackRange, false, true) {
                        @Override
                        public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                            return Game.battleManager.getBattle(battleID).defendingArmy.firstLine.get(id);
                        }
                    });
                }
                else {
                    menuElements.add(new ButtonBattleRegimentDefeated(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i).c, this.getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.firstLine.get(i).aR.uID).Line), buttonX, buttonY, i, 0) {
                        @Override
                        public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                            return Game.battleManager.getBattle(battleID).defendingArmy.firstLine.get(id);
                        }
                    });
                }
            }
            else {
                menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy0, buttonX, buttonY));
            }
            buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
        }
        buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
        buttonY += ImageManager.getImage(Images.battleArmy0).getHeight() + 2;
        for (int i = 0; i < Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.secondLine.size(); ++i) {
            if (Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.secondLine.get(i) != null) {
                menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.secondLine.get(i).c, this.getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.secondLine.get(i).aR.uID).Line), buttonX, buttonY, i, 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.secondLine.get(i).aR.uID).get(Game.battleManager.getBattle(InGame_Battle_Full.battleID).defendingArmy.secondLine.get(i).aR.aID).AttackRange, true, true) {
                    @Override
                    public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                        return Game.battleManager.getBattle(battleID).defendingArmy.secondLine.get(id);
                    }
                });
            }
            else {
                menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {});
            }
            buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3;
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        int tMaxX = 0;
        for (int j = 0, iSize2 = menuElements.size(); j < iSize2; ++j) {
            if (menuElements.get(j).getPosX() + menuElements.get(j).getWidth() > tMaxX) {
                tMaxX = menuElements.get(j).getPosX() + menuElements.get(j).getWidth();
            }
        }
        tMaxX += 100;
        menuElements.add(new Empty(0, 0, tMaxX, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleTechTree(Game.lang.get("Battle"), titleHeight, false, false) {
            @Override
            public long getTime() {
                return InGame_Battle_Full.lTime;
            }
        }, 0, CFG.GAME_HEIGHT / 2 - tMenuHeight / 2, menuWidth, tMenuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Battle_Full.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_Battle_Full.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        ImageManager.getImage(Images.insideTechTree).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, true);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Battle_Full.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_BattleFull();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_BattleFull(false);
    }
    
    public int getImageRegimentID(final int iLine) {
        switch (iLine) {
            case 1: {
                return Images.battleArmy1;
            }
            case 2: {
                return Images.battleArmy2;
            }
            default: {
                return Images.battleArmy0;
            }
        }
    }
    
    public static int getMiddleHeight() {
        return ImageManager.getImage(Images.battleArmy0).getHeight();
    }
    
    static {
        InGame_Battle_Full.lTime = 0L;
        InGame_Battle_Full.key = "";
        InGame_Battle_Full.iProvinceID = 0;
        InGame_Battle_Full.battleID = -1;
        InGame_Battle_Full.TURN_ID = 0;
        InGame_Battle_Full.sDay = "";
    }
}
