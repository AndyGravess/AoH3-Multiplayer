// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.battles.BattleRegiment;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Army;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.mainGame.RomanNumber;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battle;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.MenuElement;

public class ButtonBattleRegiment extends MenuElement
{
    public int imageID;
    public int iCivID;
    public int id;
    public int offsetY;
    public int attackRange;
    public boolean secondLine;
    public boolean defenders;
    
    public ButtonBattleRegiment(final int nCivID, final int imageID, final int iPosX, final int iPosY, final int id, final int offsetY, final int attackRange, final boolean secondLine, final boolean defenders) {
        this.offsetY = 0;
        this.typeOfElement = MenuElement_Type.BUTTON;
        this.iCivID = nCivID;
        this.imageID = imageID;
        this.id = id;
        this.secondLine = secondLine;
        this.defenders = defenders;
        this.attackRange = attackRange * 2 + 1;
        this.offsetY = offsetY;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(ImageManager.getImage(imageID).getWidth() + 2);
        this.setHeight(ImageManager.getImage(imageID).getHeight() + 2);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(Game.getCiv(this.iCivID).getRGB(1.0f));
        Images.pix.draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, ImageManager.getImage(this.imageID).getWidth(), ImageManager.getImage(this.imageID).getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.getIsHovered() ? 0.65f : 0.4f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, ImageManager.getImage(this.imageID).getWidth(), ImageManager.getImage(this.imageID).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY);
        if (this.getIsHovered()) {
            oSB.setColor(Colors.HOVER_GOLD);
            Renderer.drawBox2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            this.drawHover(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public void drawHover(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (this.defenders) {
            final int tHeight = InGame_Battle.getMiddleHeight() - 1 + (this.secondLine ? (ImageManager.getImage(this.imageID).getHeight() + 2) : 0);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + 1 + iTranslateX, this.getPosY() - tHeight + 1 + iTranslateY, 1, tHeight - 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - 1 + iTranslateX, this.getPosY() - tHeight + 1 + iTranslateY, 1, tHeight - 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() - tHeight + iTranslateY, (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange, 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - 1 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() - tHeight - CFG.PADDING - 1 + iTranslateY, 1, CFG.PADDING + 2);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + 1 + (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 - 1 + iTranslateX, this.getPosY() - tHeight - CFG.PADDING - 1 + iTranslateY, 1, CFG.PADDING + 2);
            oSB.setColor(Colors.HOVER_GOLD);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() - tHeight + iTranslateY, 1, tHeight);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() - tHeight - 1 + iTranslateY, (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange, 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() - tHeight - CFG.PADDING - 1 + iTranslateY, 1, CFG.PADDING);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 - 1 + iTranslateX, this.getPosY() - tHeight - CFG.PADDING - 1 + iTranslateY, 1, CFG.PADDING);
        }
        else {
            final int tHeight = InGame_Battle.getMiddleHeight() - 1 + (this.secondLine ? (ImageManager.getImage(this.imageID).getHeight() + 2) : 0);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + 1 + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, 1, tHeight - 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - 1 + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, 1, tHeight - 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() + this.getHeight() + tHeight - 1 + iTranslateY, (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange, 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - 1 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() + this.getHeight() + tHeight - 1 + iTranslateY, 1, CFG.PADDING + 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + 1 + (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 - 1 + iTranslateX, this.getPosY() + this.getHeight() + tHeight - 1 + iTranslateY, 1, CFG.PADDING + 1);
            oSB.setColor(Colors.HOVER_GOLD);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, 1, tHeight);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() + this.getHeight() + tHeight + iTranslateY, (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange - 1, 1);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 + iTranslateX, this.getPosY() + this.getHeight() + tHeight + iTranslateY, 1, CFG.PADDING);
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 + (ImageManager.getImage(this.imageID).getWidth() + 1) * this.attackRange / 2 - 1 + iTranslateX, this.getPosY() + this.getHeight() + tHeight + iTranslateY, 1, CFG.PADDING);
        }
    }
    
    @Override
    public void buildElementHover() {
        final int battleID = Game.battleManager.getBattleID(InGame_Battle.key);
        if (battleID >= 0) {
            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.getBattleRegiment(battleID, this.id).c, RomanNumber.getRoman(this.getBattleRegiment(battleID, this.id).rn) + ". " + Game.lang.get("Regiment")));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Army("" + this.getBattleRegiment(battleID, this.id).aR.num, this.getBattleRegiment(battleID, this.id).aR.uID, this.getBattleRegiment(battleID, this.id).aR.aID, this.getBattleRegiment(battleID, this.id).c));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Casualties") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + this.getBattleRegiment(battleID, this.id).ca, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.skull, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CasualtiesOfThePreviousDay") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + this.getBattleRegiment(battleID, this.id).cL, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.skull, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Retreated") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + this.getBattleRegiment(battleID, this.id).re, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.retreat, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RetreatsOnTheLastDay") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + this.getBattleRegiment(battleID, this.id).rL, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.retreat, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Morale") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(this.getBattleRegiment(battleID, this.id).aR.mo * 100.0f, 1) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.morale, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiceRoll") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + this.getBattleRegiment(battleID, this.id).d, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text(" / " + GameValues.battle.BATTLE_MAX_DICE_ROLL_REGIMENT, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.dice, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AttackRange") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + ArmyManager.lArmy.get(this.getBattleRegiment(battleID, this.id).aR.uID).get(this.getBattleRegiment(battleID, this.id).aR.aID).AttackRange, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SiegeAbility") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ArmyManager.lArmy.get(this.getBattleRegiment(battleID, this.id).aR.uID).get(this.getBattleRegiment(battleID, this.id).aR.aID).SiegeProgress, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.siege, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements) {
                @Override
                public int getMinPosX() {
                    return InGame_Battle.HOVER_POSX;
                }
                
                @Override
                public void draw(final SpriteBatch oSB, final int nPosX, int nPosY) {
                    nPosY = InGame_Battle.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_Battle.HOVER_POSY) / 2 - this.iHeight / 2;
                    super.draw(oSB, nPosX, nPosY);
                }
            };
        }
        else {
            this.menuElementHover = null;
        }
    }
    
    public BattleRegiment getBattleRegiment(final int battleID, final int id) {
        return Game.battleManager.getBattle(battleID).attackingArmy.firstLine.get(id);
    }
    
    @Override
    public int getPosY() {
        return super.getPosY() + this.offsetY;
    }
    
    @Override
    public void setCurrent(final int nCurrent) {
        this.offsetY = nCurrent;
    }
    
    @Override
    public int getCurrent() {
        return this.offsetY;
    }
}
