// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.map.province.Province;
import java.util.Iterator;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import java.util.HashMap;
import java.util.Map;

public class AI_MoveNoConnection
{
    public Map<AI_MoveNoConnectionData, Integer> noConnectionMap;
    
    public AI_MoveNoConnection() {
        this.noConnectionMap = new HashMap<AI_MoveNoConnectionData, Integer>();
    }
    
    public final void addProvince(final int fromProvince, final int toProvince) {
        this.noConnectionMap.put(new AI_MoveNoConnectionData(fromProvince, toProvince), Game_Calendar.TURN_ID + Game.aiValuesArmies.AT_WAR_NO_CONNECTION_DAYS);
    }
    
    public void update() {
        final Iterator<Map.Entry<AI_MoveNoConnectionData, Integer>> iterator = this.noConnectionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            if ((int)iterator.next().getValue() < Game_Calendar.TURN_ID) {
                iterator.remove();
            }
        }
    }
    
    public boolean contains(final int fromProvince, final int toProvince) {
        final AI_MoveNoConnectionData keyCheck = new AI_MoveNoConnectionData(fromProvince, toProvince);
        if (!this.noConnectionMap.containsKey(keyCheck)) {
            if (Game.getProvince(fromProvince).getCivID() != Game.getProvince(toProvince).getCivID()) {
                final Iterator<Map.Entry<AI_MoveNoConnectionData, Integer>> iterator = this.noConnectionMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    final Map.Entry<AI_MoveNoConnectionData, Integer> entry = iterator.next();
                    final AI_MoveNoConnectionData key = entry.getKey();
                    if (entry.getValue() < Game_Calendar.TURN_ID) {
                        iterator.remove();
                    }
                    else {
                        if (this.isTheSameRegion(Game.getProvince(key.fromProvince), fromProvince) && this.isTheSameRegion(Game.getProvince(key.toProvince), toProvince)) {
                            return true;
                        }
                        continue;
                    }
                }
            }
            return false;
        }
        final int turnID = this.noConnectionMap.get(keyCheck);
        if (turnID < Game_Calendar.TURN_ID) {
            this.noConnectionMap.remove(keyCheck);
            return false;
        }
        return true;
    }
    
    public boolean isTheSameRegion(final Province provinceA, final int provinceB) {
        return provinceA.getCivRegionID() == Game.getProvince(provinceB).getCivRegionID() && provinceA.getCivRegionID() >= 0;
    }
}
