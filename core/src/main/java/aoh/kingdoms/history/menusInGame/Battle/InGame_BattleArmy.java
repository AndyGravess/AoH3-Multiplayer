// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Battle;

import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.button.ButtonArmyBattle;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.map.battles.BattleRegiment;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BattleArmy extends Menu
{
    public static int mPosX;
    public static int mPosY;
    public static int mWidth;
    private List<BattleStatsRegiments> tRegiments;
    
    public void addRegiment(final int unitTypeID, final int armyID, final int numOfUnits, final int iCivID) {
        for (int i = 0, iSize = this.tRegiments.size(); i < iSize; ++i) {
            if (this.tRegiments.get(i).unitTypeID == unitTypeID && this.tRegiments.get(i).armyID == armyID) {
                final BattleStatsRegiments battleStatsRegiments = this.tRegiments.get(i);
                battleStatsRegiments.numOfUnits += numOfUnits;
                final BattleStatsRegiments battleStatsRegiments2 = this.tRegiments.get(i);
                ++battleStatsRegiments2.numOfRegiments;
                return;
            }
        }
        this.tRegiments.add(new BattleStatsRegiments(unitTypeID, armyID, numOfUnits, iCivID));
    }
    
    public InGame_BattleArmy() {
        this.tRegiments = new ArrayList<BattleStatsRegiments>();
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int buttonX = CFG.PADDING;
        final int buttonY = CFG.PADDING;
        if (InGame_Battle.battleID >= 0) {
            for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.size(); i < iSize; ++i) {
                if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i) != null) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.num, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).c);
                }
            }
            for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.size(); i < iSize; ++i) {
                if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i) != null) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.num, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).c);
                }
            }
            for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.size(); i < iSize; ++i) {
                this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.get(i).aR.num, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.get(i).c);
            }
            for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.size(); i < iSize; ++i) {
                this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.get(i).aR.num, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.get(i).c);
            }
            for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.size(); i < iSize; ++i) {
                this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).aR.num, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).c);
            }
            final List<Integer> tSorted = new ArrayList<Integer>();
            final List<Integer> tempReg = new ArrayList<Integer>();
            for (int j = 0, iSize2 = this.tRegiments.size(); j < iSize2; ++j) {
                tempReg.add(j);
            }
            while (tempReg.size() > 0) {
                int toAdd = 0;
                for (int k = 1, iSize3 = tempReg.size(); k < iSize3; ++k) {
                    if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID > this.tRegiments.get(tempReg.get(k)).unitTypeID) {
                        toAdd = k;
                    }
                    else if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID == this.tRegiments.get(tempReg.get(k)).unitTypeID && this.tRegiments.get(tempReg.get(toAdd)).armyID > this.tRegiments.get(tempReg.get(k)).armyID) {
                        toAdd = k;
                    }
                }
                tSorted.add(tempReg.get(toAdd));
                tempReg.remove(toAdd);
            }
            for (int l = 0; l < 3; ++l) {
                for (int k = 0, iSize3 = this.tRegiments.size(); k < iSize3; ++k) {
                    if (ArmyManager.lUnitsTypes.get(this.tRegiments.get(tSorted.get(k)).unitTypeID).Line == l) {
                        menuElements.add(new ButtonArmyBattle("" + this.tRegiments.get(tSorted.get(k)).numOfUnits, this.tRegiments.get(tSorted.get(k)).numOfRegiments, this.tRegiments.get(tSorted.get(k)).iCivID, buttonX, buttonY, this.tRegiments.get(tSorted.get(k)).unitTypeID, this.tRegiments.get(tSorted.get(k)).armyID, true));
                        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    }
                }
            }
        }
        this.initMenu(null, InGame_BattleArmy.mPosX, InGame_BattleArmy.mPosY - CFG.PADDING, InGame_BattleArmy.mWidth, ImageManager.getImage(Images.unitsFrameBattle).getHeight() + ButtonArmyBattle.getStatsHeight() + 1 + CFG.PADDING, menuElements, false);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameBattle();
    }
    
    @Override
    public int getMenuPosY() {
        return super.getMenuPosY() + InGame_Battle.nTranslateY;
    }
    
    @Override
    public int getPosY() {
        return super.getPosY() + InGame_Battle.nTranslateY;
    }
    
    @Override
    public int getPosX() {
        return super.getPosX() + InGame_Battle.nTranslateX;
    }
    
    @Override
    public int getMenuPosX() {
        return super.getMenuPosX() + InGame_Battle.nTranslateX;
    }
    
    static {
        InGame_BattleArmy.mPosX = 0;
        InGame_BattleArmy.mPosY = 0;
        InGame_BattleArmy.mWidth = 0;
    }
    
    public class BattleStatsRegiments
    {
        public int unitTypeID;
        public int armyID;
        public int numOfUnits;
        public int numOfRegiments;
        public int iCivID;
        
        public BattleStatsRegiments(final int unitTypeID, final int armyID, final int numOfUnits, final int iCivID) {
            this.unitTypeID = unitTypeID;
            this.armyID = armyID;
            this.numOfUnits = numOfUnits;
            this.numOfRegiments = 1;
            this.iCivID = iCivID;
        }
    }
}
