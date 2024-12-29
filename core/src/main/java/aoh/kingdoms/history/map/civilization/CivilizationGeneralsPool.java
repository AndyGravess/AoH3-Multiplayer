// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import aoc.kingdoms.lukasz.jakowski.Stats.Stats;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.button.ButtonArmyGeneral_Assign;
import aoc.kingdoms.lukasz.map.GeneralManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;
import java.util.List;

public class CivilizationGeneralsPool
{
    public List<ArmyGeneral> lGenerals;
    public int generateYear;
    
    public CivilizationGeneralsPool() {
        this.lGenerals = new ArrayList<ArmyGeneral>();
        this.generateYear = -55000;
    }
    
    public final void generateGenerals(final int iCivID) {
        for (int i = this.lGenerals.size(); i < this.getPoolOfGenerals(iCivID); ++i) {
            final int generalIMG = this.getRandomImage(iCivID);
            this.lGenerals.add(new ArmyGeneral(Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID), generalIMG, GameValues.generals.GENERAL_ATTACK_BASE_VALUE + Game.oR.nextInt(GameValues.generals.GENERAL_ATTACK_RANDOM) + Game.oR.nextInt(GameValues.generals.GENERAL_ATTACK_RANDOM2), GameValues.generals.GENERAL_DEFENSE_BASE_VALUE + Game.oR.nextInt(GameValues.generals.GENERAL_DEFENSE_RANDOM) + Game.oR.nextInt(GameValues.generals.GENERAL_DEFENSE_RANDOM2), Game_Calendar.currentYear - GameValues.generals.GENERAL_YEARS_OLD_MIN - Game.oR.nextInt(GameValues.generals.GENERAL_YEARS_OLD_RANDOM), iCivID, null));
        }
    }
    
    public static final ArmyGeneral getGeneral_Random(final int iCivID) {
        final int generalIMG = getRandomImage_Just(iCivID);
        return new ArmyGeneral(Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID), generalIMG, GameValues.generals.GENERAL_ATTACK_BASE_VALUE + Game.oR.nextInt(GameValues.generals.GENERAL_ATTACK_RANDOM) + Game.oR.nextInt(GameValues.generals.GENERAL_ATTACK_RANDOM2), GameValues.generals.GENERAL_DEFENSE_BASE_VALUE + Game.oR.nextInt(GameValues.generals.GENERAL_DEFENSE_RANDOM) + Game.oR.nextInt(GameValues.generals.GENERAL_DEFENSE_RANDOM2), Game_Calendar.currentYear - GameValues.generals.GENERAL_YEARS_OLD_MIN - Game.oR.nextInt(GameValues.generals.GENERAL_YEARS_OLD_RANDOM), iCivID, null);
    }
    
    public final void updatePoolOfGenerals(final int iCivID) {
        if (this.lGenerals.size() == 0) {
            this.generateGenerals(iCivID);
            this.generateYear = Game_Calendar.currentYear;
        }
        else if (this.generateYear + GameValues.generals.GENERALS_REGENERATE_YEARS <= Game_Calendar.currentYear) {
            this.lGenerals.clear();
            this.generateGenerals(iCivID);
            this.generateYear = Game_Calendar.currentYear;
        }
        else if (this.lGenerals.size() < this.getPoolOfGenerals(iCivID)) {
            this.generateGenerals(iCivID);
        }
    }
    
    public int getPoolOfGenerals(final int iCivID) {
        return GameValues.generals.GENERALS_DEFAULT_POOL_SIZE;
    }
    
    public void clearData() {
        this.lGenerals.clear();
        this.generateYear = -55000;
    }
    
    public final boolean recruitGeneralID(final int iCivID, final int id, final boolean assignGeneral) {
        if (id >= this.lGenerals.size()) {
            return false;
        }
        if (Game.getCiv(iCivID).fGold < GeneralManager.getRecruitGoldCost(iCivID)) {
            return false;
        }
        if (Game.getCiv(iCivID).fLegacy < GeneralManager.getRecruitLegacyCost(iCivID)) {
            return false;
        }
        final Civilization civ = Game.getCiv(iCivID);
        civ.fGold -= GeneralManager.getRecruitGoldCost(iCivID);
        final Civilization civ2 = Game.getCiv(iCivID);
        civ2.fLegacy -= GeneralManager.getRecruitLegacyCost(iCivID);
        Game.getCiv(iCivID).addGeneral(this.lGenerals.get(id));
        try {
            if (assignGeneral) {
                ButtonArmyGeneral_Assign.assignGeneral(iCivID, this.lGenerals.get(id).key, false);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.lGenerals.remove(id);
        this.generateGenerals(iCivID);
        if (iCivID == Game.player.iCivID) {
            final PlayerStats playerStats = Game.player.playerStats;
            ++playerStats.recruitedGenerals;
            final Stats civStats = Game.stats.civStats;
            ++civStats.rg;
        }
        return true;
    }
    
    public static int getRandomImage_Just(final int iCivID) {
        return Game.oR.nextInt(Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID));
    }
    
    public int getRandomImage(final int iCivID) {
        final List<Boolean> isUsed = new ArrayList<Boolean>();
        for (int i = 0; i < Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID); ++i) {
            isUsed.add(false);
        }
        for (int i = 0, iSize = this.lGenerals.size(); i < iSize; ++i) {
            isUsed.set(this.lGenerals.get(i).g, true);
        }
        for (int i = 0; i < Game.getCiv(iCivID).iGeneralsSize; ++i) {
            isUsed.set(Game.getCiv(iCivID).getGeneralNotAssigned(i).g, true);
        }
        try {
            for (int i = 0; i < Game.getCiv(iCivID).iArmyPositionSize; ++i) {
                for (int j = 0; j < Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmySize(); ++j) {
                    if (Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmy(j).civID == iCivID && Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmy(j).armyGeneral != null) {
                        isUsed.set(Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmy(j).armyGeneral.g, true);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final List<Integer> out = new ArrayList<Integer>();
        for (int k = 0, iSize2 = Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID); k < iSize2; ++k) {
            if (!isUsed.get(k)) {
                out.add(k);
            }
        }
        if (out.size() != 0) {
            return out.get(Game.oR.nextInt(out.size()));
        }
        for (int k = 0, iSize2 = Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID); k < iSize2; ++k) {
            isUsed.set(k, false);
        }
        for (int k = 0, iSize2 = this.lGenerals.size(); k < iSize2; ++k) {
            isUsed.set(this.lGenerals.get(k).g, true);
        }
        for (int k = 0, iSize2 = Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID); k < iSize2; ++k) {
            if (!isUsed.get(k)) {
                out.add(k);
            }
        }
        if (out.size() == 0) {
            return Game.oR.nextInt(Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID));
        }
        return out.get(Game.oR.nextInt(out.size()));
    }
}
