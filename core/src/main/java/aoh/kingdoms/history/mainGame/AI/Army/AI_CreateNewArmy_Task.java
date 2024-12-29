// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class AI_CreateNewArmy_Task
{
    public String key;
    public List<AI_CreateNewArmy> cre;
    public int rL;
    public int t;
    
    public AI_CreateNewArmy_Task() {
        this.cre = new ArrayList<AI_CreateNewArmy>();
        this.rL = 0;
        this.t = 0;
    }
    
    public AI_CreateNewArmy_Task(final String key, final List<AI_CreateNewArmy> nCreateNewArmy, final int expiresTurnID) {
        this.cre = new ArrayList<AI_CreateNewArmy>();
        this.rL = 0;
        this.t = 0;
        this.key = key;
        this.cre = nCreateNewArmy;
        this.t = expiresTurnID;
        for (int i = this.cre.size() - 1; i >= 0; --i) {
            this.rL += this.cre.get(i).n;
        }
    }
    
    public boolean recruitArmy(final int civID) {
        final List<Integer> possibleProvinces = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            if (!Game.getProvince(Game.getCiv(civID).getProvinceID(i)).isOccupied()) {
                possibleProvinces.add(Game.getCiv(civID).getProvinceID(i));
            }
        }
        final int possibleProvincesSize = possibleProvinces.size();
        if (possibleProvinces.size() > 0) {
            int iSize;
            while ((iSize = this.cre.size()) > 0) {
                final int randomID = Game.oR.nextInt(iSize);
                if (!Game.getCiv(civID).recruitArmy(new ArmyRecruit(possibleProvinces.get(Game.oR.nextInt(possibleProvincesSize)), this.cre.get(randomID).u, this.cre.get(randomID).a, this.key))) {
                    break;
                }
                final AI_CreateNewArmy a\u0131_CreateNewArmy = this.cre.get(randomID);
                --a\u0131_CreateNewArmy.n;
                --this.rL;
                if (this.cre.get(randomID).n > 0) {
                    continue;
                }
                this.cre.remove(randomID);
            }
        }
        possibleProvinces.clear();
        return this.cre.size() <= 0;
    }
}
