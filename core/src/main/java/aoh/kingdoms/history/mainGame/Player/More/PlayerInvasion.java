// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.army.ArmyInvasion;
import java.util.List;

public class PlayerInvasion
{
    public List<ArmyInvasion> invasions;
    public int invasionsSize;
    
    public PlayerInvasion() {
        this.invasions = new ArrayList<ArmyInvasion>();
        this.invasionsSize = 0;
    }
    
    public final boolean addInvasion(final int iCivID) {
        if (Game.invasionArmyProvincesSize == 0) {
            return false;
        }
        if (InGame_ProvinceArmy.sActiveKEY == null) {
            return false;
        }
        if (Game.findArmy_FullCheck(iCivID, InGame_ProvinceArmy.sActiveKEY) == null) {
            return false;
        }
        for (int i = 0; i < this.invasionsSize; ++i) {
            if (this.invasions.get(i).armyKey.equals(InGame_ProvinceArmy.sActiveKEY)) {
                this.invasions.get(i).lProvinces = Game.invasionArmyProvinces;
                return false;
            }
        }
        this.invasions.add(new ArmyInvasion(iCivID, InGame_ProvinceArmy.sActiveKEY, Game.invasionArmyProvinces));
        this.invasionsSize = this.invasions.size();
        return true;
    }
    
    public final boolean removeInvasion(final String armyKey) {
        for (int i = 0; i < this.invasionsSize; ++i) {
            if (this.invasions.get(i).armyKey.equals(armyKey)) {
                this.invasions.remove(i);
                this.invasionsSize = this.invasions.size();
                return true;
            }
        }
        return false;
    }
    
    public final boolean haveInvasionPlan(final String armyKey) {
        for (int i = 0; i < this.invasionsSize; ++i) {
            if (this.invasions.get(i).armyKey.equals(armyKey)) {
                return true;
            }
        }
        return false;
    }
    
    public final int getInvasionPlan_NumOfProvinces(final String armyKey) {
        for (int i = 0; i < this.invasionsSize; ++i) {
            if (this.invasions.get(i).armyKey.equals(armyKey)) {
                return this.invasions.get(i).lProvinces.size();
            }
        }
        return 0;
    }
    
    public final boolean moveInvasion(final String armyKey) {
        for (int i = 0; i < this.invasionsSize; ++i) {
            if (this.invasions.get(i).armyKey.equals(armyKey)) {
                if (!this.invasions.get(i).invasionMoveArmy(Game.player.iCivID)) {
                    this.invasions.remove(i);
                    this.invasionsSize = this.invasions.size();
                }
                return true;
            }
        }
        return false;
    }
    
    public final void clearInvasions() {
        this.invasions.clear();
        this.invasionsSize = 0;
    }
}
