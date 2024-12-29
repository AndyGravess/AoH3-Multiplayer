// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Colonization;

import aoc.kingdoms.lukasz.map.province.Province;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class AI_Colonization_NeighCiv
{
    public static void updateColonize() {
        try {
            if (!AI_Colonization.provincesAll.isEmpty() && Game_Calendar.TURN_ID >= GameValues.colonization.AI_COLONIZE_NEIGH_CIV_MIN_TURN_ID) {
                for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_COLONIZE_NEIGH_CIV; i < AI_Colonization.provincesAll.size(); i += GameValues.gameUpdateAI.GAME_UPDATE_AI_COLONIZE_NEIGH_CIV) {
                    if (Game.getProvince(AI_Colonization.provincesAll.get(i)).getCivID() > 0 || Game.getProvince(AI_Colonization.provincesAll.get(i)).getSeaProvince() || Game.getProvince(AI_Colonization.provincesAll.get(i)).getWasteland() >= 0) {
                        AI_Colonization.provincesAll.remove(i);
                    }
                    else {
                        final List<Integer> possibleCivs = new ArrayList<Integer>();
                        final Province province = Game.getProvince(AI_Colonization.provincesAll.get(i));
                        for (int j = 0; j < province.getNeighboringProvincesSize(); ++j) {
                            if (Game.getProvince(province.getNeighboringProvinces(j)).getCivID() > 0 && Game.getCiv(Game.getProvince(province.getNeighboringProvinces(j)).getCivID()).canColonize && !possibleCivs.contains(Game.getProvince(province.getNeighboringProvinces(j)).getCivID())) {
                                possibleCivs.add(Game.getProvince(province.getNeighboringProvinces(j)).getCivID());
                            }
                        }
                        if (possibleCivs.isEmpty()) {
                            for (int j = 0; j < province.getNeighboringSeaProvincesSize(); ++j) {
                                final Province seaProvince = Game.getProvince(province.getNeighboringSeaProvinces(j));
                                for (int k = 0; k < seaProvince.getNeighboringProvincesSize(); ++k) {
                                    if (Game.getProvince(seaProvince.getNeighboringProvinces(k)).getCivID() > 0 && Game.getCiv(Game.getProvince(seaProvince.getNeighboringProvinces(k)).getCivID()).canColonize && !possibleCivs.contains(Game.getProvince(seaProvince.getNeighboringProvinces(k)).getCivID())) {
                                        possibleCivs.add(Game.getProvince(seaProvince.getNeighboringProvinces(k)).getCivID());
                                    }
                                }
                            }
                        }
                        while (!possibleCivs.isEmpty()) {
                            final int id = Game.oR.nextInt(possibleCivs.size());
                            if (AI_Colonization.aiColonize(possibleCivs.get(id), AI_Colonization.provincesAll.get(i))) {
                                break;
                            }
                            possibleCivs.remove(id);
                        }
                        possibleCivs.clear();
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
