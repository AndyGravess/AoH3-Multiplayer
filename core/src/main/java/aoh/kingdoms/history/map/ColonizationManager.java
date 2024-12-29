// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game;

public class ColonizationManager
{
    public static boolean establishSettlement(final int iCivID, final int iProvinceID, int numOfSettlers) {
        final Province province = Game.getProvince(iProvinceID);
        if (province.getCivID() != 0) {
            return false;
        }
        numOfSettlers = Math.max(numOfSettlers, getNumberOfSettlers(iCivID, iProvinceID));
        if (numOfSettlers <= 0) {
            return false;
        }
        numOfSettlers = Math.min(numOfSettlers, Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * GameValues.colonization.COLONIZATION_MAX_SETTLERS);
        province.setCivID(iCivID);
        province.setReligion(Game.getCiv(iCivID).getReligionID());
        province.addCore(iCivID);
        province.clearPopulationData();
        province.setPopulationOfCivID(iCivID, numOfSettlers);
        Game.getProvinceData9(iProvinceID).setColonizationStartedTurnID(Game_Calendar.TURN_ID);
        Game.getProvinceData9(iProvinceID).setColonizationGrowthRateExtra((int)(province.getGrowthRate() * GameValues.colonization.COLONIZATION_GROWTH_RATE_EXTRA));
        Game.getCiv(iCivID).addColonizationProvince(iProvinceID);
        for (int i = province.getArmySize() - 1; i >= 0; --i) {
            if (province.getArmy(i).civID == iCivID) {
                province.getArmy(i).removeAllSettlers();
                if (province.getArmy(i).iArmyRegimentSize == 0) {
                    if (province.getArmy(i).armyGeneral != null) {
                        Game.getCiv(province.getArmy(i).civID).addGeneral(province.getArmy(i).armyGeneral);
                    }
                    province.removeArmy(i);
                }
            }
        }
        return true;
    }
    
    public static final int getNumberOfSettlers(final int iCivID, final int iProvinceID) {
        int numOfSettlers = 0;
        final Province province = Game.getProvince(iProvinceID);
        for (int i = province.getArmySize() - 1; i >= 0; --i) {
            if (province.getArmy(i).civID == iCivID) {
                numOfSettlers += province.getArmy(i).getNumberOfSettlers();
            }
        }
        return numOfSettlers;
    }
    
    public static final float getSettlementEstablishmentProgress(final int iProvinceID) {
        return Math.min(1.0f, (Game_Calendar.TURN_ID - Game.getProvinceData9(iProvinceID).getColonizationStartedTurnID()) / (float)GameValues.colonization.COLONIZATION_TIME);
    }
    
    public static boolean establishSettlement_Gold(final int iCivID, final int iProvinceID) {
        final Province province = Game.getProvince(iProvinceID);
        if (province.getCivID() != 0) {
            return false;
        }
        final Civilization civ = Game.getCiv(iCivID);
        civ.fGold -= GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST;
        province.setCivID(iCivID);
        province.setReligion(Game.getCiv(iCivID).getReligionID());
        province.addCore(iCivID);
        province.clearPopulationData();
        province.setPopulationOfCivID(iCivID, GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_SETTLERS + Game.oR.nextInt(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_SETTLERS_RANDOM));
        Game.getProvinceData9(iProvinceID).setColonizationStartedTurnID(Game_Calendar.TURN_ID);
        Game.getProvinceData9(iProvinceID).setColonizationGrowthRateExtra((int)(province.getGrowthRate() * GameValues.colonization.COLONIZATION_GROWTH_RATE_EXTRA));
        Game.getCiv(iCivID).addColonizationProvince(iProvinceID);
        return true;
    }
}
