// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class AI_MergeTask
{
    public int iProvinceID;
    public List<String> keys;
    
    public AI_MergeTask() {
        this.keys = new ArrayList<String>();
    }
    
    public AI_MergeTask(final int iProvinceID, final String armyKey) {
        this.keys = new ArrayList<String>();
        this.iProvinceID = iProvinceID;
        this.keys.add(armyKey);
    }
    
    public void addKey(final String key) {
        if (!this.keys.contains(key)) {
            this.keys.add(key);
        }
    }
    
    public void removeKey(final String key) {
        for (int i = this.keys.size() - 1; i > 0; --i) {
            if (this.keys.get(i).equals(key)) {
                this.keys.remove(i);
                return;
            }
        }
    }
    
    public void checkArmiesExists(final int civID) {
        for (int i = this.keys.size() - 1; i >= 0; --i) {
            if (!Game.getCiv(civID).armyExists(this.keys.get(i))) {
                this.keys.remove(i);
            }
            else if (Game.getProvince(this.iProvinceID).getArmyKeyID(this.keys.get(i), civID) < 0 && !Game.getCiv(civID).isArmyMovingToProvince(this.keys.get(i), this.iProvinceID)) {
                this.keys.remove(i);
            }
        }
    }
}
