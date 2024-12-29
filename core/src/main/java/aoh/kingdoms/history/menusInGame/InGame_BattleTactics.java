// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Value;
import com.badlogic.gdx.graphics.Color;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation_Value;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BattleTactics extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_BattleTactics() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = CFG.GAME_HEIGHT / 4;
        int buttonY = CFG.PADDING;
        int buttonX = Images.boxTitleBORDERWIDTH;
        for (int i = 0; i < GameValues.battleTactics.BATTLE_TACTICS.length; ++i) {
            buttonX = paddingLeft;
            menuElements.add(new ButtonCurrentSituation_Value(Game.lang.get(GameValues.battleTactics.BATTLE_TACTICS[i]), Images.battle, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.BUTTON_HEIGHT2 * 2 - CFG.PADDING * 2, CFG.BUTTON_HEIGHT, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true, i) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getBattleTacticsID() != this.getCurrent()) {
                        Game.getCiv(Game.player.iCivID).setBattleTacticsID(this.getCurrent());
                        if (Game.menuManager.getVisibleInGame_Court()) {
                            Game.menuManager.rebuildInGame_CourtSavePos();
                            Game.menuManager.setVisibleInGame_Court(true);
                            InGame_Court.lTime = 0L;
                        }
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = Game.player.iCivID;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("BattleTactics"), Game.lang.get(GameValues.battleTactics.BATTLE_TACTICS[Game.getCiv(Game.player.iCivID).getBattleTacticsID()]));
                        InGame_Info.imgID = Images.infoCrown;
                    }
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.battle, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (this.getCurrent() == Game.getCiv(Game.player.iCivID).getBattleTacticsID()) {
                        return Colors.HOVER_GOLD;
                    }
                    return super.getColor(isActive);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_Value(((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[i] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[i], Images.attack, buttonX, buttonY, CFG.BUTTON_HEIGHT2, CFG.BUTTON_HEIGHT, i) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (this.getIsHovered()) {
                        return super.getColor(isActive);
                    }
                    return (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_Value(((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[i] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[i], Images.defense, buttonX, buttonY, CFG.BUTTON_HEIGHT2, CFG.BUTTON_HEIGHT, i) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (this.getIsHovered()) {
                        return super.getColor(isActive);
                    }
                    return (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("BattleTactics"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_BattleTactics.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 4, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_BattleTactics.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_BattleTactics.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_BattleTactics.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_BattleTactics.lTime = 0L;
    }
}
