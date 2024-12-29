// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.Battle.InGame_Battle;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.battles.Battle;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.Renderer.SparksAnimation;

public class Button_OutlinerBattle extends Button_Outliner
{
    public SparksAnimation sparksAnimation;
    public String battleKey;
    public int lastTurnID;
    public int civIDLeft;
    public int civIDRight;
    public int leftIconMaxW;
    
    public Button_OutlinerBattle(final String battleKey, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        super("", nPosX, nPosY, nWidth, nHeight, 0);
        this.sparksAnimation = new SparksAnimation();
        this.lastTurnID = -999645;
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.battleKey = battleKey;
        this.leftIconMaxW = Math.max(ImageManager.getImage(Images.relationsUp).getWidth(), ImageManager.getImage(Images.battle).getWidth());
        this.updateBattle();
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        this.updateBattle();
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        this.sparksAnimation.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.battle).draw(oSB, this.getPosX() + CFG.PADDING * 2 + (this.leftIconMaxW - ImageManager.getImage(Images.battle).getWidth()) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.battle).getHeight() / 2 + iTranslateY);
        oSB.setShader(Renderer.shaderAlpha);
        if (this.civIDLeft >= 0) {
            Game.getCiv(this.civIDLeft).getFlag().getTexture().bind(1);
        }
        else {
            ImageManager.getImage(Images.rebelsFlag).getTexture().bind(1);
        }
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.flagRect2Mask).draw(oSB, this.getPosX() + CFG.PADDING * 4 + this.leftIconMaxW + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagRect2).draw(oSB, this.getPosX() + CFG.PADDING * 4 + this.leftIconMaxW + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        oSB.setShader(Renderer.shaderAlpha);
        if (this.civIDRight >= 0) {
            Game.getCiv(this.civIDRight).getFlag().getTexture().bind(1);
        }
        else {
            ImageManager.getImage(Images.rebelsFlag).getTexture().bind(1);
        }
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.flagRect2Mask).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - ImageManager.getImage(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagRect2).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - ImageManager.getImage(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.leftIconMaxW + ImageManager.getImage(Images.flagRect2).getWidth() + CFG.PADDING * 4 + (this.getWidth() - (this.leftIconMaxW + ImageManager.getImage(Images.flagRect2).getWidth() * 2 + CFG.PADDING * 6)) / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        try {
            final Battle battle = Game.battleManager.getBattle(this.battleKey);
            if (battle == null) {
                this.menuElementHover = null;
                return;
            }
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("BattleOf", Game.getProvince(battle.provinceID).getProvinceName()), Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.battle, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Soldiers") + ":", CFG.FONT_REGULAR_SMALL));
            if (battle.attackingArmy.iCivID < 0) {
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, CFG.PADDING, CFG.PADDING));
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Flag(battle.attackingArmy.iCivID, CFG.PADDING, CFG.PADDING));
            }
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + battle.attackingArmy.numOfUnits) + GameValues.battle.RIGHT_BATTLE_TEXT + CFG.getNumberWithSpaces("" + battle.defendingArmy.numOfUnits), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            if (battle.defendingArmy.iCivID < 0) {
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, CFG.PADDING, 0));
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Flag(battle.defendingArmy.iCivID, CFG.PADDING, 0));
            }
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements);
        }
        catch (final Exception ex) {
            this.menuElementHover = null;
        }
    }
    
    public final void updateBattle() {
        try {
            if (this.lastTurnID != Game_Calendar.TURN_ID) {
                this.lastTurnID = Game_Calendar.TURN_ID;
                final Battle battle = Game.battleManager.getBattle(this.battleKey);
                if (battle != null) {
                    this.civIDLeft = battle.attackingArmy.iCivID;
                    this.civIDRight = battle.defendingArmy.iCivID;
                    this.setText(CFG.getNumberWithSpaces("" + battle.attackingArmy.numOfUnits) + " vs " + CFG.getNumberWithSpaces("" + battle.defendingArmy.numOfUnits));
                    this.iCurrent = ((this.civIDLeft == Game.player.iCivID) ? this.civIDRight : this.civIDLeft);
                }
                else {
                    Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Right") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_Right();
                        }
                    });
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.iCurrent;
        }
    }
    
    @Override
    public void actionElement() {
        if (Game.menuManager.getVisibleInGame_Battle() && InGame_Battle.key.equals(this.battleKey)) {
            Game.menuManager.setVisibleInGame_Battle(false);
        }
        else {
            InGame_Battle.key = this.battleKey;
            Game.menuManager.rebuildInGame_Battle();
        }
    }
    
    @Override
    public void actionElementPPM() {
        final Battle battle = Game.battleManager.getBattle(this.battleKey);
        if (battle != null) {
            Game.mapCoords.centerToProvinceID(battle.provinceID);
        }
    }
}
