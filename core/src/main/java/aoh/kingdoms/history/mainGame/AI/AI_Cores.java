// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Cores
{
    public static final void createCore(final int civID) {
        if (Game.getCiv(civID).civilizationCores.provincesSize > 0) {
            try {
                for (int i = 0; i < Game.getCiv(civID).civilizationCores.provincesSize; ++i) {
                    buildProvince_AICoresScore_Default(civID, Game.getCiv(civID).civilizationCores.provinces.get(i));
                }
                while (Game.getCiv(civID).fGold > GameValues.core.CORE_CREATION_COST_DEFAULT && Game.getCiv(civID).civilizationCores.provincesSize > 0) {
                    int bestProvinceID = 0;
                    for (int j = 1; j < Game.getCiv(civID).civilizationCores.provincesSize; ++j) {
                        if (Game.getProvince(Game.getCiv(civID).civilizationCores.provinces.get(bestProvinceID)).aiScore_CoresReligion < Game.getProvince(Game.getCiv(civID).civilizationCores.provinces.get(j)).aiScore_CoresReligion) {
                            bestProvinceID = j;
                        }
                    }
                    if (Game.getProvince(Game.getCiv(civID).civilizationCores.provinces.get(bestProvinceID)).aiScore_CoresReligion < 0.0f) {
                        break;
                    }
                    if (!Game.getProvince(Game.getCiv(civID).civilizationCores.provinces.get(bestProvinceID)).addCoreCreation()) {
                        Game.getCiv(civID).civilizationCores.checkProvince(Game.getCiv(civID).civilizationCores.provinces.get(bestProvinceID), civID);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void buildProvince_AICoresScore_Default(final int civID, final int nProvinceID) {
        if (Game.getProvince(nProvinceID).coreCreation != null) {
            Game.getProvince(nProvinceID).aiScore_CoresReligion = -999999.0f;
        }
        else if (Game.getProvince(nProvinceID).haveACore(civID)) {
            Game.getProvince(nProvinceID).aiScore_CoresReligion = -999999.0f;
            Game.getCiv(civID).civilizationCores.removeProvince(nProvinceID);
        }
        else {
            Game.getProvince(nProvinceID).aiScore_CoresReligion = Game.aiValuesCores.BUILD_SCORE_MIN;
            final Province province = Game.getProvince(nProvinceID);
            province.aiScore_CoresReligion += Game.aiValuesCores.SCORE_PER_GROWTH_RATE * Game.getProvince(nProvinceID).getGrowthRateWithBonuses();
            final Province province2 = Game.getProvince(nProvinceID);
            province2.aiScore_CoresReligion += Game.aiValuesCores.SCORE_PER_ECONOMY * Game.getProvince(nProvinceID).getEconomyWithBonuses();
            final Province province3 = Game.getProvince(nProvinceID);
            province3.aiScore_CoresReligion += Game.aiValuesCores.SCORE_DISTANCE_FROM_CAPITAL * Game.getProvince(nProvinceID).aiDistanceToCapital;
        }
    }
}
