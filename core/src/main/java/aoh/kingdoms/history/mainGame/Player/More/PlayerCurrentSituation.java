// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.map.LegacyManager;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.map.LawsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class PlayerCurrentSituation
{
    public int currentSituationNum;
    public boolean noActiveResearch;
    public boolean lackOfGeneral;
    public boolean availableCivilizationLegacy;
    public boolean availableAdvantage;
    public boolean wonderCanBeBuilt;
    public boolean newLawAvailable;
    public int newLawAvailableNum;
    public int noAdvisor;
    public int promoteAdvisor;
    public boolean upgradeCapitalCity;
    public boolean militaryAcademyCanBeUpgraded;
    public boolean militaryAcademyForGeneralsCanBeUpgraded;
    public boolean upgradeSupremeCourt;
    public boolean upgradeNuclearReactor;
    public boolean nonCoreProvinces;
    public int nonCoreProvincesNum;
    public boolean differentReligionProvinces;
    public int differentReligionProvincesNum;
    public boolean maxAmountOfGold;
    public boolean highInflation;
    public boolean chooseRivals;
    public boolean allMissionsUnlocked;
    public boolean missionCanBeUnlocked;
    public int missionCanBeUnlockedNum;
    public List<Integer> playerLegaciesLVL;
    
    public PlayerCurrentSituation() {
        this.currentSituationNum = 0;
        this.noActiveResearch = false;
        this.lackOfGeneral = false;
        this.availableCivilizationLegacy = false;
        this.availableAdvantage = false;
        this.wonderCanBeBuilt = false;
        this.newLawAvailable = false;
        this.newLawAvailableNum = 0;
        this.noAdvisor = 0;
        this.promoteAdvisor = 0;
        this.upgradeCapitalCity = false;
        this.militaryAcademyCanBeUpgraded = false;
        this.militaryAcademyForGeneralsCanBeUpgraded = false;
        this.upgradeSupremeCourt = false;
        this.upgradeNuclearReactor = false;
        this.nonCoreProvinces = false;
        this.nonCoreProvincesNum = 0;
        this.differentReligionProvinces = false;
        this.differentReligionProvincesNum = 0;
        this.maxAmountOfGold = false;
        this.highInflation = false;
        this.chooseRivals = false;
        this.allMissionsUnlocked = false;
        this.missionCanBeUnlocked = false;
        this.missionCanBeUnlockedNum = 0;
        this.playerLegaciesLVL = new ArrayList<Integer>();
    }
    
    public final void updateCurrentSituation() {
        this.updateCurrentSituation(Game.player.iCivID);
    }
    
    public final void updateCurrentSituation(final int iCivID) {
        try {
            this.currentSituationNum = 0;
            if (Game.getCiv(Game.player.iCivID).getActiveTechResearch() < 0) {
                this.noActiveResearch = true;
                ++this.currentSituationNum;
            }
            else {
                this.noActiveResearch = false;
            }
            if (Game.getCiv(Game.player.iCivID).getAdvantagePoints() > 0) {
                this.availableAdvantage = true;
                ++this.currentSituationNum;
            }
            else {
                this.availableAdvantage = false;
            }
            this.updateLackOfGeneral(iCivID);
            this.updateAvailableCivilizationLegacy(iCivID);
            this.updateWonderCanBeBuild(iCivID);
            this.updateMilitaryAcademyCanBeUpgraded(iCivID);
            this.updateMilitaryAcademyForGeneralsCanBeUpgraded(iCivID);
            this.updateNonCoreProvinces(iCivID);
            this.updateDifferentReligion(iCivID);
            this.updateNoAdvisor(iCivID);
            this.updateNewLaw(iCivID);
            this.updateChooseRivals(iCivID);
            this.highInflation = (Game.getCiv(iCivID).getInflation() >= GameValues.inflation.INFLATION_CURRENT_SITUATION_INFO);
            if (this.highInflation) {
                ++this.currentSituationNum;
            }
            this.maxAmountOfGold = (Game.getCiv(Game.player.iCivID).fGold >= Game.getMaxAmountOfGold(iCivID));
            if (this.maxAmountOfGold) {
                ++this.currentSituationNum;
            }
            this.upgradeSupremeCourt = false;
            if (Game.getCiv(iCivID).getCorruption() > 0.005f && Game.getCiv(iCivID).getSupremeCourtLevel() < Game.getSupremeCourt_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold > Game.getSupremeCourt_Cost(iCivID)) {
                this.upgradeSupremeCourt = true;
                ++this.currentSituationNum;
            }
            this.upgradeNuclearReactor = false;
            if (Game.getCiv(iCivID).canBuildNuke && Game.getCiv(iCivID).getNuclearReactorLevel() < Game.getNuclearReactor_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold > Game.getNuclearReactor_Cost(iCivID)) {
                this.upgradeNuclearReactor = true;
                ++this.currentSituationNum;
            }
            this.upgradeCapitalCity = false;
            if (Game.getCiv(iCivID).getCapitalLevel() < Game.getCapital_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold > Game.getCapital_Cost(iCivID)) {
                this.upgradeCapitalCity = true;
                ++this.currentSituationNum;
            }
            this.updateMissionsCanBeUnlocked(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateNewLaw(final int iCivID) {
        this.newLawAvailable = false;
        this.newLawAvailableNum = 0;
        for (int i = 0; i < LawsManager.iLawsSize; ++i) {
            int j = LawsManager.laws.get(i).RequiredTechID.length - 1;
            while (j > 0) {
                if (Game.getCiv(iCivID).getTechResearched(LawsManager.laws.get(i).RequiredTechID[j]) && (LawsManager.laws.get(i).RequiredGovernmentID == null || LawsManager.laws.get(i).RequiredGovernmentID[j] < 0 || LawsManager.laws.get(i).RequiredGovernmentID[j] == Game.getCiv(iCivID).getIdeologyID())) {
                    if (Game.getCiv(iCivID).laws.get(i) < j) {
                        if (!this.newLawAvailable) {
                            ++this.currentSituationNum;
                        }
                        this.newLawAvailable = true;
                        ++this.newLawAvailableNum;
                        break;
                    }
                    break;
                }
                else {
                    --j;
                }
            }
        }
    }
    
    public final void updateChooseRivals(final int iCivID) {
        this.chooseRivals = false;
        if (Game.getCiv(iCivID).diplomacy.rivals.size() < GameValues.rivals.RIVALS_LIMIT) {
            this.chooseRivals = true;
            ++this.currentSituationNum;
        }
    }
    
    private final void updateNoAdvisor(final int iCivID) {
        try {
            this.noAdvisor = 0;
            this.promoteAdvisor = 0;
            final int maxLevel = AdvisorManager.getAdvisorMaxLevel(iCivID);
            if (Game.getCiv(iCivID).advisorAdministration.sName == null) {
                ++this.noAdvisor;
            }
            else if (Game.getCiv(iCivID).advisorAdministration.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }
            if (Game.getCiv(iCivID).advisorEconomy.sName == null) {
                ++this.noAdvisor;
            }
            else if (Game.getCiv(iCivID).advisorEconomy.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }
            if (Game.getCiv(iCivID).advisorTechnology.sName == null) {
                ++this.noAdvisor;
            }
            else if (Game.getCiv(iCivID).advisorTechnology.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }
            if (Game.getCiv(iCivID).advisorMilitary.sName == null) {
                ++this.noAdvisor;
            }
            else if (Game.getCiv(iCivID).advisorMilitary.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }
            if (this.noAdvisor > 0) {
                ++this.currentSituationNum;
            }
            if (this.promoteAdvisor > 0) {
                ++this.currentSituationNum;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void updateLackOfGeneral(final int iCivID) {
        try {
            this.lackOfGeneral = false;
            for (int i = 0; i < Game.getCiv(iCivID).iArmyPositionSize; ++i) {
                for (int j = 0; j < Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmySize(); ++j) {
                    if (Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmy(j).civID == iCivID && Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmy(j).armyGeneral == null) {
                        this.lackOfGeneral = true;
                        ++this.currentSituationNum;
                        return;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void updateAvailableCivilizationLegacy(final int iCivID) {
        try {
            this.availableCivilizationLegacy = false;
            for (int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
                if (this.playerLegaciesLVL.get(i) >= 0 && Game.getCiv(iCivID).fLegacy >= LegacyManager.legacies.get(i).CostLegacy[this.playerLegaciesLVL.get(i)]) {
                    this.availableCivilizationLegacy = true;
                    ++this.currentSituationNum;
                    break;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildPlayerLegaciesLVL() {
        this.buildPlayerLegaciesLVL(Game.player.iCivID);
    }
    
    public final void buildPlayerLegaciesLVL(final int iCivID) {
        try {
            this.playerLegaciesLVL.clear();
            for (int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
                int tLevel = Game.getCiv(iCivID).getLegacyLevel(i) + 1;
                if (tLevel >= LegacyManager.legacies.get(i).CostLegacy.length) {
                    tLevel = -1;
                }
                this.playerLegaciesLVL.add(tLevel);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateWonderCanBeBuild(final int iCivID) {
        try {
            this.wonderCanBeBuilt = false;
            for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).wonderID >= 0 && !Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getWonderBuilt() && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).wonderConstruction == null && Game.getCiv(iCivID).fGold >= WondersManager.getWonderConstructionCost(Game.getCiv(iCivID).getProvinceID(i), Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).wonderID)) {
                    this.wonderCanBeBuilt = true;
                    ++this.currentSituationNum;
                    break;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateMilitaryAcademyCanBeUpgraded(final int iCivID) {
        try {
            this.militaryAcademyCanBeUpgraded = false;
            if (Game.getCiv(iCivID).getMilitaryAcademyLevel() < Game.getMilitaryAcademy_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold >= Game.getMilitaryAcademy_Cost(iCivID)) {
                this.militaryAcademyCanBeUpgraded = true;
                ++this.currentSituationNum;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateMilitaryAcademyForGeneralsCanBeUpgraded(final int iCivID) {
        try {
            this.militaryAcademyForGeneralsCanBeUpgraded = false;
            if (Game.getCiv(iCivID).getMilitaryAcademyForGeneralsLevel() < Game.getMilitaryAcademyForGenerals_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold >= Game.getMilitaryAcademyForGenerals_Cost(iCivID)) {
                this.militaryAcademyForGeneralsCanBeUpgraded = true;
                ++this.currentSituationNum;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateNonCoreProvinces(final int iCivID) {
        try {
            this.nonCoreProvinces = false;
            this.nonCoreProvincesNum = 0;
            for (int i = Game.getCiv(iCivID).getNumOfProvinces() - 1; i >= 0; --i) {
                if (!Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).isOccupied() && !Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).haveACore(iCivID) && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).coreCreation == null) {
                    if (!this.nonCoreProvinces) {
                        ++this.currentSituationNum;
                    }
                    this.nonCoreProvinces = true;
                    ++this.nonCoreProvincesNum;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateDifferentReligion(final int iCivID) {
        try {
            this.differentReligionProvinces = false;
            this.differentReligionProvincesNum = 0;
            for (int i = Game.getCiv(iCivID).getNumOfProvinces() - 1; i >= 0; --i) {
                if (!Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getReligion() != Game.getCiv(iCivID).getReligionID() && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).religionConversion == null) {
                    if (!this.differentReligionProvinces) {
                        ++this.currentSituationNum;
                    }
                    this.differentReligionProvinces = true;
                    ++this.differentReligionProvincesNum;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateMissionsCanBeUnlocked(final int iCivID) {
        try {
            if (!this.allMissionsUnlocked && Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_CURRENT_SITUATION_MISSION_TREE_EVERY_X_DAYS == 0) {
                this.missionCanBeUnlocked = false;
                this.missionCanBeUnlockedNum = 0;
                if (Game.getCiv(iCivID).iMissionsSize > 0) {
                    for (int i = 0; i < Game.getCiv(iCivID).iMissionsSize; ++i) {
                        if (MissionTree.canRunMission_Civ(iCivID, i)) {
                            this.missionCanBeUnlocked = true;
                            ++this.missionCanBeUnlockedNum;
                        }
                    }
                    if (Game.getCiv(iCivID).iMissionsSize == this.missionCanBeUnlockedNum) {
                        this.allMissionsUnlocked = true;
                    }
                }
                else {
                    for (int i = 0; i < MissionTree.iMissionsSize; ++i) {
                        if (MissionTree.canRunMission(iCivID, i)) {
                            this.missionCanBeUnlocked = true;
                            ++this.missionCanBeUnlockedNum;
                        }
                    }
                    if (MissionTree.iMissionsSize == this.missionCanBeUnlockedNum) {
                        this.allMissionsUnlocked = true;
                    }
                }
            }
            if (this.missionCanBeUnlocked) {
                ++this.currentSituationNum;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
