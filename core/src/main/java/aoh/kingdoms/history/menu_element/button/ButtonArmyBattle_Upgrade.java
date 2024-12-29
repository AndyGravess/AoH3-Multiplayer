// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menusInGame.InGame_Armies;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonArmyBattle_Upgrade extends ButtonArmyBattle
{
    public String key;
    
    public ButtonArmyBattle_Upgrade(final String nArmy, final int numOfRegiments, final int iCivID, final int iPosX, final int iPosY, final int niUnitTypeID, final int nArmyID, final boolean inBattleView, final String key) {
        super(nArmy, numOfRegiments, iCivID, iPosX, iPosY, niUnitTypeID, nArmyID, inBattleView);
        this.key = key;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        if (this.getIsHovered()) {
            oSB.setColor(Color.WHITE);
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        }
        ImageManager.getImage(Images.upgrade).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.upgrade).getWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.unitsFrameBattle).getHeight() / 2 - ImageManager.getImage(Images.upgrade).getHeight() / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public void actionElement() {
        final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(Game.player.iCivID, this.key);
        if (nArmyPos != null) {
            if (Game.getCiv(Game.player.iCivID).fGold < ArmyManager.getUpgradeRegimentCost(Game.player.iCivID)) {
                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), Images.gold);
            }
            else {
                ArmyManager.upgradeDivisionArmy(Game.player.iCivID, this.iUnitTypeID, this.iArmyID, nArmyPos.iProvinceID, nArmyPos.iID);
                Game.menuManager.addToastGold_Short(Game.lang.get("RegimentsUpgraded"), Images.upgrade);
                Game.menuManager.rebuildInGame_Armies(false, true);
                Game.menuManager.setVisibleInGame_Armies(true);
                InGame_Armies.lTime = 0L;
            }
        }
        else {
            Game.menuManager.addToast_Error(Game.lang.get("ArmyNotFound"));
        }
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Attack") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getAttack(this.iCivID), Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Defense") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getDefense(this.iCivID), Images.defense, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MovementSpeed") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).MovementSpeed, Images.movementSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AttackRange") + ": ", "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).AttackRange, Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SiegeAbility") + ": ", "" + CFG.getPrecision2(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).SiegeProgress, 100), Images.siege, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattlePosition") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ((ArmyManager.lUnitsTypes.get(this.iUnitTypeID).Line == 0 || ArmyManager.lUnitsTypes.get(this.iUnitTypeID).Line == 1) ? Game.lang.get("FirstLine") : ((ArmyManager.lUnitsTypes.get(this.iUnitTypeID).Line == 2) ? Game.lang.get("Support") : Game.lang.get("ThirdLine"))), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Manpower") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + this.getText(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Text("/" + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * this.numOfRegiments, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        final int nArmyID = ArmyManager.getUpgradeMaxArmyID(Game.player.iCivID, this.iUnitTypeID, this.iArmyID);
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Upgrade") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(ArmyManager.lArmy.get(this.iUnitTypeID).get(nArmyID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.upgrade, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Attack") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ArmyManager.lArmy.get(this.iUnitTypeID).get(nArmyID).getAttack(this.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.attack, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Defense") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ArmyManager.lArmy.get(this.iUnitTypeID).get(nArmyID).getDefense(this.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.defense, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NumberOfRegiments") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)this.numOfRegiments, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID) * this.numOfRegiments, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CostPerRegiment") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public int getSFX() {
        return Game.soundsManager.getRecruitArmy();
    }
}
