// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_ConvertReligion
{
    public static final void convertReligion(final int civID) {
        if (Game.getCiv(civID).convertReligion.provincesSize > 0) {
            try {
                for (int i = 0; i < Game.getCiv(civID).convertReligion.provincesSize; ++i) {
                    buildProvince_AIConvertScore_Default(civID, Game.getCiv(civID).convertReligion.provinces.get(i));
                }
                while (Game.getCiv(civID).fGold > GameValues.religion.DEFAULT_CONVERSION_COST && Game.getCiv(civID).convertReligion.provincesSize > 0) {
                    int bestProvinceID = 0;
                    for (int j = 1; j < Game.getCiv(civID).convertReligion.provincesSize; ++j) {
                        if (Game.getProvince(Game.getCiv(civID).convertReligion.provinces.get(bestProvinceID)).aiScore_CoresReligion < Game.getProvince(Game.getCiv(civID).convertReligion.provinces.get(j)).aiScore_CoresReligion) {
                            bestProvinceID = j;
                        }
                    }
                    if (Game.getProvince(Game.getCiv(civID).convertReligion.provinces.get(bestProvinceID)).aiScore_CoresReligion < 0.0f) {
                        break;
                    }
                    if (!Game.getProvince(Game.getCiv(civID).convertReligion.provinces.get(bestProvinceID)).addReligionConversion()) {
                        Game.getCiv(civID).convertReligion.checkProvince(Game.getCiv(civID).convertReligion.provinces.get(bestProvinceID), civID);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void buildProvince_AIConvertScore_Default(final int civID, final int nProvinceID) {
        if (Game.getProvince(nProvinceID).religionConversion != null) {
            Game.getProvince(nProvinceID).aiScore_CoresReligion = -999999.0f;
        }
        else if (Game.getProvince(nProvinceID).getReligion() == Game.getCiv(civID).getReligionID()) {
            Game.getProvince(nProvinceID).aiScore_CoresReligion = -999999.0f;
            Game.getCiv(civID).convertReligion.removeProvince(nProvinceID);
        }
        else {
            Game.getProvince(nProvinceID).aiScore_CoresReligion = Game.aiValuesConvert.BUILD_SCORE_MIN;
            final Province province = Game.getProvince(nProvinceID);
            province.aiScore_CoresReligion += Game.aiValuesConvert.SCORE_PER_GROWTH_RATE * Game.getProvince(nProvinceID).getGrowthRateWithBonuses();
            final Province province2 = Game.getProvince(nProvinceID);
            province2.aiScore_CoresReligion += Game.aiValuesConvert.SCORE_PER_ECONOMY * Game.getProvince(nProvinceID).getEconomyWithBonuses();
            final Province province3 = Game.getProvince(nProvinceID);
            province3.aiScore_CoresReligion += Game.aiValuesConvert.SCORE_DISTANCE_FROM_CAPITAL * Game.getProvince(nProvinceID).aiDistanceToCapital;
        }
    }
}
