// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import java.util.ArrayList;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;

public class RivalsManager
{
    public static float getLegacy(final int civID, final int rivalID) {
        return GameValues.rivals.RIVAL_LEGACY_MIN;
    }
    
    public static int getManpower(final int civID, final int rivalID) {
        return (int)(GameValues.rivals.RIVAL_MANPOWER_MIN * Math.max(0.05f, 1.0f - Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(rivalID).getCapitalProvinceID())));
    }
    
    public static final List<Integer> buildRivals(final int civID, final int rivalsLimit) {
        final List<Integer> rivals = new ArrayList<Integer>();
        final List<Float> distance = new ArrayList<Float>();
        for (int i = 1; i < civID; ++i) {
            if (!Game.getCiv(civID).diplomacy.isRival(i)) {
                final float tDistance = buildRivals_IsInDistance_Perc(civID, i);
                if (tDistance >= 0.0f) {
                    rivals.add(i);
                    distance.add(tDistance);
                }
            }
        }
        for (int i = civID + 1; i < Game.getCivsSize(); ++i) {
            if (!Game.getCiv(civID).diplomacy.isRival(i)) {
                final float tDistance = buildRivals_IsInDistance_Perc(civID, i);
                if (tDistance >= 0.0f) {
                    rivals.add(i);
                    distance.add(tDistance);
                }
            }
        }
        if (rivals.size() <= rivalsLimit) {
            return rivals;
        }
        final List<Integer> out = new ArrayList<Integer>();
        for (int j = 0, iSize = distance.size(); j < iSize; ++j) {
            final float tDistance = Math.abs(Game.getCiv(civID).iCivRankScore - Game.getCiv(rivals.get(j)).iCivRankScore);
            distance.set(j, tDistance * GameValues.rivals.RIVALS_SCORE_MIN + tDistance * GameValues.rivals.RIVALS_SCORE_DISTANCE * (distance.get(j) / Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).RIVALS_DISTANCE));
        }
        while (out.size() <= rivalsLimit) {
            int bestID = 0;
            for (int k = 1, iSize2 = rivals.size(); k < iSize2; ++k) {
                if (distance.get(k) < distance.get(bestID)) {
                    bestID = k;
                }
            }
            out.add(rivals.get(bestID));
            rivals.remove(bestID);
            distance.remove(bestID);
        }
        return out;
    }
    
    public static boolean buildRivals_IsInDistance(final int civID, final int rivalID) {
        return Game.getCiv(rivalID).getNumOfProvinces() > 0 && Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(rivalID).getCapitalProvinceID()) <= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).RIVALS_DISTANCE;
    }
    
    public static float buildRivals_IsInDistance_Perc(final int civID, final int rivalID) {
        if (Game.getCiv(rivalID).getNumOfProvinces() <= 0) {
            return -1.0f;
        }
        final float distancePerc = Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(rivalID).getCapitalProvinceID());
        if (distancePerc > Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).RIVALS_DISTANCE) {
            return -1.0f;
        }
        return distancePerc;
    }
    
    public static List<Integer> getRivaledBy(final int civID) {
        final List<Integer> out = new ArrayList<Integer>();
        for (int i = 1; i < civID; ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).diplomacy.isRival(civID)) {
                out.add(i);
            }
        }
        for (int i = civID + 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).diplomacy.isRival(civID)) {
                out.add(i);
            }
        }
        return out;
    }
}
