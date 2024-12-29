// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.army.ArmyRecruit;

public class Button_QueueUnit extends Button
{
    public ArmyRecruit armyRecruit;
    
    public Button_QueueUnit(final int nPosX, final int nPosY, final ArmyRecruit armyRecruit) {
        this.init(null, CFG.FONT_BOLD_SMALL, -1, nPosX, nPosY, ImageManager.getImage(Images.unitsFrameMap).getWidth(), ImageManager.getImage(Images.unitsFrameMap).getHeight(), true, true, false, false);
        this.armyRecruit = armyRecruit;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        final Image progressBarFrame = ImageManager.getImage(Images.progressBarFrame);
        final Image progressBarFrameMask = ImageManager.getImage(Images.progressBarFrameMask);
        final Image unitsFrameMap = ImageManager.getImage(Images.unitsFrameMap);
        oSB.setShader(Renderer.shaderAlpha);
        ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).ImageID).getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        unitsFrameMap.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        final int tCenterX = (progressBarFrame.getWidth() - progressBarFrameMask.getWidth()) / 2;
        final int tCenterY = (progressBarFrame.getHeight() - progressBarFrameMask.getHeight()) / 2;
        oSB.setColor(new Color(ProvinceDraw.progressBarBG));
        progressBarFrameMask.draw(oSB, this.getPosX() + iTranslateX + tCenterX, this.getPosY() + iTranslateY + unitsFrameMap.getHeight() - progressBarFrame.getHeight() + tCenterY);
        oSB.setColor(ProvinceDraw.progressBar);
        progressBarFrameMask.draw(oSB, this.getPosX() + iTranslateX + tCenterX, this.getPosY() + iTranslateY + unitsFrameMap.getHeight() - progressBarFrame.getHeight() + tCenterY, (int)Math.max(1.0f, progressBarFrameMask.getWidth() * ((ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).RecruitmentTime - this.armyRecruit.timeLeft) / (float)ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).RecruitmentTime)), progressBarFrameMask.getHeight());
        oSB.setColor(Color.WHITE);
        progressBarFrame.draw(oSB, this.getPosX() + iTranslateX + unitsFrameMap.getWidth() / 2 - progressBarFrame.getWidth() / 2, this.getPosY() + iTranslateY + unitsFrameMap.getHeight() - progressBarFrame.getHeight());
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    public void actionElement() {
        Game.mapCoords.centerToProvinceID(this.armyRecruit.provinceID);
    }
    
    @Override
    public void actionElementPPM() {
        Game.getCiv(Game.player.iCivID).cancelRecruitArmy(this.armyRecruit.provinceID);
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Attack") + ": ", "" + ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).getAttack(Game.player.iCivID), Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Defense") + ": ", "" + ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).getDefense(Game.player.iCivID), Images.defense, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MovementSpeed") + ": ", "" + ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).MovementSpeed, Images.movementSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AttackRange") + ": ", "" + ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).AttackRange, Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SiegeAbility") + ": ", "" + CFG.getPrecision2(ArmyManager.lArmy.get(this.armyRecruit.unitID).get(this.armyRecruit.armyID).SiegeProgress, 100), Images.siege, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattlePosition") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ((ArmyManager.lUnitsTypes.get(this.armyRecruit.unitID).Line == 0 || ArmyManager.lUnitsTypes.get(this.armyRecruit.unitID).Line == 1) ? Game.lang.get("FirstLine") : ((ArmyManager.lUnitsTypes.get(this.armyRecruit.unitID).Line == 2) ? Game.lang.get("Support") : Game.lang.get("ThirdLine"))), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(this.armyRecruit.provinceID).getProvinceName(), Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(this.armyRecruit.provinceID).getCivID(), CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
