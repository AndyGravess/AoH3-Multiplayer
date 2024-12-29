// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.InGame_Generals;
import aoh.kingdoms.history.map.province.ProvinceTouchExtraAction;
import aoh.kingdoms.history.mainGame.Game;

public class ButtonArmyGeneral2_Armies extends ButtonArmyGeneral2
{
    public String key;
    public int iProvinceID;
    public int iCivID;
    
    public ButtonArmyGeneral2_Armies(final String sName, final int iCivID, final int iAttack, final int iDefense, final int iPosX, final int iPosY, final int imageID, final int iDay, final int iMonth, final int iYear, final String key, final int nCivID, final int iProvinceID, final String sIMG, final int combatExperience) {
        super(sName, iCivID, iAttack, iDefense, iPosX, iPosY, imageID, iDay, iMonth, iYear, sIMG, combatExperience);
        this.key = key;
        this.iProvinceID = iProvinceID;
        this.iCivID = nCivID;
    }
    
    @Override
    public void actionElement() {
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
            if (!Game.getProvince(this.iProvinceID).getDrawProvince()) {
                Game.mapCoords.centerToProvinceID(this.iProvinceID);
            }
            Game.clearActiveArmy();
            final Game.HoveredArmy nHA = new Game.HoveredArmy();
            nHA.key = Game.getProvince(this.iProvinceID).getArmy(nArmyID).key;
            nHA.iCivID = Game.getProvince(this.iProvinceID).getArmy(nArmyID).civID;
            nHA.iProvinceID = this.iProvinceID;
            nHA.iArmyID = nArmyID;
            Game.addActiveArmy(nHA);
            ProvinceTouchExtraAction.actionUp_SetActiveArmy();
            if (Game.menuManager.getVisibleInGame_Generals()) {
                if (InGame_Generals.assignProvinceID >= 0 && InGame_Generals.assignProvinceID != this.iProvinceID && !InGame_Generals.assignArmyKey.equals(this.key)) {
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
                InGame_Generals.backButton = false;
                Game.menuManager.rebuildInGame_Generals();
                Game.menuManager.setVisibleInGame_Generals(true);
            }
        }
    }
}
