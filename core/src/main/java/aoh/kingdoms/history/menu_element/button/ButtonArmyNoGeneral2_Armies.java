// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.InGame_Generals;
import aoh.kingdoms.history.mainGame.Game;

public class ButtonArmyNoGeneral2_Armies extends ButtonArmyNoGeneral2
{
    public String key;
    public int iProvinceID;
    public int iCivID;
    
    public ButtonArmyNoGeneral2_Armies(final String sName, final int iCivID, final int iPosX, final int iPosY, final String key, final int nCivID, final int iProvinceID) {
        super(sName, iCivID, iPosX, iPosY, true);
        this.key = key;
        this.iProvinceID = iProvinceID;
        this.iCivID = nCivID;
    }
    
    @Override
    public void actionElement() {
        if (this.iProvinceID >= 0) {
            int nArmyID = Game.getProvince(this.iProvinceID).getArmyKeyID(this.key);
            if (nArmyID < 0) {
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    final int outID = Game.getProvince(i).getArmyKeyID(this.key, this.iCivID);
                    if (outID >= 0) {
                        this.iProvinceID = i;
                        nArmyID = outID;
                        break;
                    }
                }
            }
            if (nArmyID >= 0) {
                final Game.HoveredArmy nHA = new Game.HoveredArmy();
                nHA.key = Game.getProvince(this.iProvinceID).getArmy(nArmyID).key;
                nHA.iCivID = Game.getProvince(this.iProvinceID).getArmy(nArmyID).civID;
                nHA.iProvinceID = this.iProvinceID;
                nHA.iArmyID = nArmyID;
                if (Game.menuManager.getVisibleInGame_Generals()) {
                    if (InGame_Generals.assignProvinceID != this.iProvinceID && !InGame_Generals.assignArmyKey.equals(this.key)) {
                        final int tDivID = Game.getProvince(this.iProvinceID).getArmyKeyID(this.key);
                        if (tDivID >= 0) {
                            InGame_Generals.assignProvinceID = this.iProvinceID;
                            InGame_Generals.assignArmyKey = this.key;
                        }
                        else {
                            InGame_Generals.assignProvinceID = -1;
                            Game.menuManager.addToast_Error(Game.lang.get("ArmyNotFound"));
                        }
                        Game.menuManager.setOrderInGame_Generals();
                    }
                    else {
                        Game.menuManager.setVisibleInGame_Generals(false);
                    }
                }
                else {
                    InGame_Generals.assignProvinceID = this.iProvinceID;
                    InGame_Generals.assignArmyKey = this.key;
                    final int armyID = Game.getProvince(this.iProvinceID).getArmyKeyID(this.key);
                    if (Game.getCiv(Game.player.iCivID).getGeneralsNotAssignedSize() == 0 && Game.getProvince(this.iProvinceID).getArmy(armyID).civID == Game.player.iCivID) {
                        if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                            Game.menuManager.setVisibleInGame_GeneralRecruit(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_GeneralRecruit();
                        }
                    }
                    else {
                        InGame_Generals.backButton = false;
                        Game.menuManager.rebuildInGame_Generals();
                        Game.menuManager.setVisibleInGame_Generals(true);
                    }
                }
            }
        }
    }
}
