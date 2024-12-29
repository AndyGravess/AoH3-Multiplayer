// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import aoc.kingdoms.lukasz.jakowski.Stats.Stats;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.menusInGame.InGame_War;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.war.WarCivilization;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.map.war.War;
import java.util.ArrayList;
import java.util.List;

public class PeaceTreaty
{
    public String warKey;
    public int iCivID;
    public int iCivID2;
    public float fScore;
    public float fScoreTotal;
    public float fTotalProvinceWarScore_CivLost;
    public List<Integer> lProvinces;
    public List<Integer> lProvinces_Liberate;
    public List<Integer> lSubjectTransfer;
    public List<Integer> lLiberateCiv;
    public int demandGold;
    public float fAggressiveExpansion;
    public boolean demandWarReparations;
    public boolean demandVassalization;
    public boolean demandGovernmentChange;
    public boolean demandReligionConversion;
    public boolean demandMilitaryAccess;
    public boolean demandHumiliate;
    public boolean demandReturnProvinces;
    
    public PeaceTreaty(final int iCivID, final String nWarKey, final boolean player) {
        this.lProvinces = new ArrayList<Integer>();
        this.lProvinces_Liberate = new ArrayList<Integer>();
        this.lSubjectTransfer = new ArrayList<Integer>();
        this.lLiberateCiv = new ArrayList<Integer>();
        this.demandGold = 0;
        this.fAggressiveExpansion = 0.0f;
        this.demandWarReparations = false;
        this.demandVassalization = false;
        this.demandGovernmentChange = false;
        this.demandReligionConversion = false;
        this.demandMilitaryAccess = false;
        this.demandHumiliate = false;
        this.demandReturnProvinces = false;
        this.warKey = nWarKey;
        this.iCivID = iCivID;
        try {
            this.iCivID2 = (WarManager.lWars.get(this.warKey).isDefender(iCivID) ? WarManager.lWars.get(this.warKey).lAggressors.get(0).iCivID : WarManager.lWars.get(this.warKey).lDefenders.get(0).iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.fTotalProvinceWarScore_CivLost = getTotalProvinceValue(this.iCivID2);
        this.buildScore();
        if (!player) {
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                Game.getProvince(i).aiPeaceCivID = Game.getProvince(i).getCivID();
            }
        }
    }
    
    public void buildScore() {
        float scoreCivID = 0.0f;
        float scoreCivID2 = 0.0f;
        for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
            scoreCivID += Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).fProvinceValue;
        }
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).diplomacy.iVassalsSize; ++i) {
                try {
                    for (int j = 0; j < Game.getCiv(Game.getCiv(this.iCivID).diplomacy.lVassals.get(i).c).getNumOfProvinces(); ++j) {
                        scoreCivID += Game.getProvince(Game.getCiv(Game.getCiv(this.iCivID).diplomacy.lVassals.get(i).c).getProvinceID(j)).fProvinceValue * GameValues.peace.PEACE_SCORE_WINNING_SIDE_VASSALS;
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        for (int i2 = 0; i2 < Game.getCiv(this.iCivID2).getNumOfProvinces(); ++i2) {
            scoreCivID2 += Game.getProvince(Game.getCiv(this.iCivID2).getProvinceID(i2)).fProvinceValue;
        }
        this.fScoreTotal = scoreCivID2;
        this.fScore = Math.max(scoreCivID * GameValues.peace.PEACE_SCORE_WINNING_SIDE, scoreCivID2 * GameValues.peace.PEACE_SCORE_LOSING_SIDE);
        try {
            if (Game.getCiv(this.iCivID2).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.iCivID2).getCapitalProvinceID()).getCivID() == this.iCivID2) {
                this.fScore = Math.max(this.fScore, Game.getProvince(Game.getCiv(this.iCivID2).getCapitalProvinceID()).fProvinceValue + 0.5f);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (WarManager.lWars.get(this.warKey).isCoalition) {
            this.fScore *= GameValues.peace.PEACE_SCORE_COALITION_WAR;
        }
        if (WarManager.lWars.get(this.warKey).conquerVassal) {
            this.fScore *= GameValues.peace.PEACE_SCORE_CONQUER_VASSAL_WAR;
        }
        this.fScore *= Math.max(0.05f, 1.0f + (Game.getCiv(this.iCivID).civBonuses.WarScoreCost + Game.getCiv(this.iCivID).civStability_LostFrom100 * GameValues.civStability.CS_WARSCORE_COST_PER_POINT + GameValues.civRank.CIV_RANK_WAR_SCORE_COST[Game.getCiv(this.iCivID).iCivRankID]) * -1.0f);
    }
    
    public void buildAggressiveExpansion() {
        this.fAggressiveExpansion = 0.0f;
        try {
            for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
                this.fAggressiveExpansion += Math.min(GameValues.aggressiveExpansion.AE_PROVINCE_MAX, Game.getProvince(this.lProvinces.get(i)).fProvinceValue * (GameValues.aggressiveExpansion.AE_PER_PROVINCE_VALUE * this.getAggressiveExpansion_Extra(this.lProvinces.get(i))));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (this.demandVassalization) {
            try {
                for (int i = 0; i < Game.getCiv(this.iCivID2).getNumOfProvinces(); ++i) {
                    if (!Game.getProvince(Game.getCiv(this.iCivID2).getProvinceID(i)).peaceTreatyIsTaken) {
                        this.fAggressiveExpansion += Math.min(GameValues.aggressiveExpansion.AE_PROVINCE_MAX, Game.getProvince(Game.getCiv(this.iCivID2).getProvinceID(i)).fProvinceValue * (GameValues.aggressiveExpansion.AE_PER_PROVINCE_VALUE_VASSALIZATION * this.getAggressiveExpansion_Extra(Game.getCiv(this.iCivID2).getProvinceID(i))));
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        for (int a = 0; a < this.lSubjectTransfer.size(); ++a) {
            try {
                for (int i2 = 0; i2 < Game.getCiv(this.lSubjectTransfer.get(a)).getNumOfProvinces(); ++i2) {
                    this.fAggressiveExpansion += Math.min(GameValues.aggressiveExpansion.AE_PROVINCE_MAX, Game.getProvince(Game.getCiv(this.lSubjectTransfer.get(a)).getProvinceID(i2)).fProvinceValue * (GameValues.aggressiveExpansion.AE_PER_PROVINCE_VALUE_SUBJECT_TRANSFER * this.getAggressiveExpansion_Extra(Game.getCiv(this.lSubjectTransfer.get(a)).getProvinceID(i2))));
                }
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
        if (this.demandHumiliate) {
            this.fAggressiveExpansion += GameValues.peace.PEACE_HUMILIATE_AGGRESSIVE_EXPANSION_GAIN;
        }
        this.fAggressiveExpansion *= Math.max(0.0f, 1.0f + Game.getCiv(this.iCivID).civBonuses.AggressiveExpansion / 100.0f);
    }
    
    public float getAggressiveExpansion_Extra(final int provinceID) {
        return 1.0f + ((Game.getCiv(this.iCivID).getReligionID() == Game.getProvince(provinceID).getReligion()) ? GameValues.aggressiveExpansion.AE_SAME_RELIGION : GameValues.aggressiveExpansion.AE_DIFFERENT_RELIGION) + Game.getDistance_PercOfMax(Game.getCiv(this.iCivID).getCapitalProvinceID(), provinceID) * GameValues.aggressiveExpansion.AE_DISTANCE;
    }
    
    public void takeProvince(final int iProvinceID) {
        if (this.isProvinceTaken(iProvinceID)) {
            this.removeProvince(iProvinceID);
            Game.getProvince(iProvinceID).peaceTreatyIsTaken = false;
        }
        else if (this.addProvince(iProvinceID)) {
            Game.getProvince(iProvinceID).peaceTreatyIsTaken = true;
        }
        this.buildAggressiveExpansion();
    }
    
    public boolean isProvinceTaken(final int iProvinceID) {
        for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
            if (this.lProvinces.get(i) == iProvinceID) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addProvince(final int iProvinceID) {
        if (this.fScore < Game.getProvince(iProvinceID).fProvinceValue) {
            return false;
        }
        for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
            if (this.lProvinces.get(i) == iProvinceID) {
                return true;
            }
        }
        if (this.fScore >= Game.getProvince(iProvinceID).fProvinceValue) {
            this.lProvinces.add(iProvinceID);
            this.fScore -= Game.getProvince(iProvinceID).fProvinceValue;
            return true;
        }
        return false;
    }
    
    public void removeProvince(final int iProvinceID) {
        for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
            if (this.lProvinces.get(i) == iProvinceID) {
                Game.getProvince(iProvinceID).peaceTreatyIsTaken = false;
                this.lProvinces.remove(i);
                this.fScore += Game.getProvince(iProvinceID).fProvinceValue;
                return;
            }
        }
    }
    
    public void resetProvinces() {
        for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
            this.fScore += Game.getProvince(this.lProvinces.get(i)).fProvinceValue;
            Game.getProvince(this.lProvinces.get(i)).peaceTreatyIsTaken = false;
            this.lProvinces.remove(i);
        }
        this.buildAggressiveExpansion();
    }
    
    public final boolean demandGold() {
        if (this.demandGold < GameValues.peace.PEACE_GOLD_MAX && this.fScore >= this.getScore_DemandGold()) {
            ++this.demandGold;
            this.fScore -= this.getScore_DemandGold();
            return true;
        }
        return false;
    }
    
    public final void demandGold_Cancel() {
        if (this.demandGold > 0) {
            --this.demandGold;
            this.fScore += this.getScore_DemandGold();
        }
    }
    
    public final float getScore_DemandGold() {
        return this.fScoreTotal * GameValues.peace.PEACE_GOLD_COST_SCORE;
    }
    
    public float getGold_PerDemand() {
        return this.getGold_PerDemand_One() * this.demandGold;
    }
    
    public float getGold_PerDemand_One() {
        return Game.getLoanValue(this.iCivID2) * GameValues.peace.PEACE_GOLD_MODIFIER;
    }
    
    public final void demandWarReparations() {
        if (this.demandWarReparations) {
            this.demandWarReparations = false;
            this.fScore += this.getScore_DemandWarReparations();
        }
        else if (this.fScore >= this.getScore_DemandWarReparations()) {
            this.demandWarReparations = true;
            this.fScore -= this.getScore_DemandWarReparations();
        }
    }
    
    public final float getScore_DemandWarReparations() {
        return this.fScoreTotal * GameValues.peace.PEACE_WAR_REPARATIONS_COST_SCORE;
    }
    
    public final void demandMilitaryAccess() {
        if (this.demandMilitaryAccess) {
            this.demandMilitaryAccess = false;
            this.fScore += this.getScore_DemandMilitaryAccess();
        }
        else if (this.fScore >= this.getScore_DemandMilitaryAccess()) {
            this.demandMilitaryAccess = true;
            this.fScore -= this.getScore_DemandMilitaryAccess();
        }
    }
    
    public final float getScore_DemandMilitaryAccess() {
        return this.fScoreTotal * GameValues.peace.PEACE_MILITARY_ACCESS_COST_SCORE;
    }
    
    public final void demandHumiliate() {
        if (this.demandHumiliate) {
            this.demandHumiliate = false;
            this.fScore += this.getScore_DemandHumiliate();
        }
        else if (this.fScore >= this.getScore_DemandHumiliate()) {
            this.demandHumiliate = true;
            this.fScore -= this.getScore_DemandHumiliate();
        }
        this.buildAggressiveExpansion();
    }
    
    public final float getScore_DemandHumiliate() {
        return GameValues.peace.PEACE_HUMILIATE_COST_SCORE;
    }
    
    public final void demandVassalization() {
        if (this.demandVassalization) {
            this.demandVassalization = false;
            this.fScore += this.getScore_DemandVassalization();
        }
        else if (this.fScore >= this.getScore_DemandVassalization()) {
            this.demandVassalization = true;
            this.fScore -= this.getScore_DemandVassalization();
        }
        this.buildAggressiveExpansion();
    }
    
    public final float getScore_DemandVassalization() {
        return this.fTotalProvinceWarScore_CivLost * GameValues.peace.PEACE_VASSALIZATION_COST_SCORE;
    }
    
    public final void demandSubjectTransfer(final int civID) {
        if (this.lSubjectTransfer.contains(civID)) {
            for (int i = 0; i < this.lSubjectTransfer.size(); ++i) {
                if (this.lSubjectTransfer.get(i) == civID) {
                    this.lSubjectTransfer.remove(i);
                    break;
                }
            }
            this.fScore += this.getScore_SubjectTransfer(civID);
        }
        else if (this.fScore >= this.getScore_SubjectTransfer(civID)) {
            this.lSubjectTransfer.add(civID);
            this.fScore -= this.getScore_SubjectTransfer(civID);
        }
        this.buildAggressiveExpansion();
    }
    
    public final float getScore_SubjectTransfer(final int civID) {
        return getTotalProvinceValue(civID) * GameValues.peace.PEACE_SUBJECT_TRANSFER_COST_SCORE;
    }
    
    public final void demandLiberateCiv(final int civID, final int liberateCivID) {
        if (this.lLiberateCiv.contains(liberateCivID)) {
            for (int i = 0; i < this.lLiberateCiv.size(); ++i) {
                if (this.lLiberateCiv.get(i) == liberateCivID) {
                    this.lLiberateCiv.remove(i);
                    this.removeProvinces_LiberateCiv(civID, liberateCivID);
                    break;
                }
            }
            this.fScore += this.getScore_LiberateCiv(civID, liberateCivID);
        }
        else if (this.fScore >= this.getScore_LiberateCiv(civID, liberateCivID)) {
            this.lLiberateCiv.add(liberateCivID);
            this.addProvinces_LiberateCiv(civID, liberateCivID);
            this.fScore -= this.getScore_LiberateCiv(civID, liberateCivID);
        }
        this.buildAggressiveExpansion();
    }
    
    public final float getScore_LiberateCiv(final int civID, final int liberateCivID) {
        return getTotalProvinceValue_Liberate(civID, liberateCivID) * GameValues.peace.PEACE_LIBERATE_CIV_COST_SCORE;
    }
    
    public final void addProvinces_LiberateCiv(final int civID, final int liberateCivID) {
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(civID).getProvinceID(i)).haveACore(liberateCivID)) {
                if (!this.lProvinces_Liberate.contains(Game.getCiv(civID).getProvinceID(i))) {
                    this.lProvinces_Liberate.add(Game.getCiv(civID).getProvinceID(i));
                }
            }
        }
    }
    
    public final void removeProvinces_LiberateCiv(final int civID, final int liberateCivID) {
        final int liberateCivSize = this.lLiberateCiv.size();
        for (int i = this.lProvinces_Liberate.size() - 1; i >= 0; --i) {
            boolean remove = true;
            for (int j = 0; j < liberateCivSize; ++j) {
                if (Game.getProvince(this.lProvinces_Liberate.get(i)).haveACore(this.lLiberateCiv.get(j))) {
                    remove = false;
                    break;
                }
            }
            if (remove) {
                this.lProvinces_Liberate.remove(i);
            }
        }
    }
    
    public final void demandReligionConversion() {
        if (this.demandReligionConversion) {
            this.demandReligionConversion = false;
            this.fScore += this.getScore_DemandReligionConversion();
        }
        else if (this.fScore >= this.getScore_DemandReligionConversion()) {
            this.demandReligionConversion = true;
            this.fScore -= this.getScore_DemandReligionConversion();
        }
    }
    
    public final float getScore_DemandReligionConversion() {
        return this.fTotalProvinceWarScore_CivLost * GameValues.peace.PEACE_RELIGION_CONVERSION_COST_SCORE;
    }
    
    public final void demandGovernmentChange() {
        if (this.demandGovernmentChange) {
            this.demandGovernmentChange = false;
            this.fScore += this.getScore_DemandGovernmentChange();
        }
        else if (this.fScore >= this.getScore_DemandGovernmentChange()) {
            this.demandGovernmentChange = true;
            this.fScore -= this.getScore_DemandGovernmentChange();
        }
    }
    
    public final float getScore_DemandGovernmentChange() {
        return this.fTotalProvinceWarScore_CivLost * GameValues.peace.PEACE_GOVERNMENT_CHANGE_COST_SCORE;
    }
    
    public int getPopulation() {
        int out = 0;
        for (int i = this.lProvinces.size() - 1; i >= 0; --i) {
            out += Game.getProvince(this.lProvinces.get(i)).getPopulationTotal();
        }
        return out;
    }
    
    public boolean enforceDemands(final boolean byPlayer) {
        final boolean updateFOG = DiplomacyManager.isAlly(this.iCivID, Game.player.iCivID) || DiplomacyManager.isAlly(this.iCivID2, Game.player.iCivID);
        final Civilization civ = Game.getCiv(this.iCivID);
        final Civilization civ2 = Game.getCiv(this.iCivID2);
        boolean updatePlayersWars = false;
        try {
            updatePlayersWars = (!byPlayer && WarManager.lWars.get(this.warKey).isInThisWar(Game.player.iCivID));
        }
        catch (final Exception ex4) {}
        try {
            if (!byPlayer && !this.lProvinces.isEmpty() && GameValues.peace.AI_ABANDON_DEMAND_PROVINCES_IF_ARE_NOT_CONNECTED && !Game.ideologiesManager.getIdeology(civ2.getIdeologyID()).TRIBAL && Game.oR.nextInt(100) < GameValues.peace.AI_ABANDON_DEMAND_PROVINCES_IF_ARE_NOT_CONNECTED_CHANCE) {
                try {
                    boolean connectionFound = false;
                    boolean canAccessMainSea = false;
                    for (int i2 = this.lProvinces.size() - 1; i2 >= 0; --i2) {
                        final Province province = Game.getProvince(this.lProvinces.get(i2));
                        if (province.accessToMainSea) {
                            canAccessMainSea = true;
                        }
                        for (int j2 = 0; j2 < province.getNeighboringProvincesSize(); ++j2) {
                            if (Game.getProvince(province.getNeighboringProvinces(j2)).getCivID() == this.iCivID) {
                                connectionFound = true;
                                i2 = -1;
                                break;
                            }
                        }
                    }
                    if (!connectionFound) {
                        boolean removeProvinces = true;
                        if (canAccessMainSea) {
                            boolean canCivAccessMainSea = false;
                            for (int i3 = 0; i3 < civ.getNumOfProvinces(); ++i3) {
                                if (Game.getProvince(civ.getProvinceID(i3)).accessToMainSea) {
                                    canCivAccessMainSea = true;
                                    break;
                                }
                            }
                            if (canCivAccessMainSea && Game.oR.nextInt(100) >= GameValues.peace.AI_ABANDON_DEMAND_PROVINCES_HAVE_ONLY_ACCESS_TO_MAIN_SEA_CHANCE) {
                                removeProvinces = false;
                            }
                        }
                        if (removeProvinces) {
                            for (int i4 = this.lProvinces.size() - 1; i4 >= 0; --i4) {
                                Game.getProvince(this.lProvinces.get(i4)).peaceTreatyIsTaken = false;
                                Game.getProvince(this.lProvinces.get(i4)).aiPeaceCivID = Game.getProvince(this.lProvinces.get(i4)).getCivID();
                                this.fScore += Game.getProvince(this.lProvinces.get(i4)).fProvinceValue;
                                this.lProvinces.remove(i4);
                            }
                            if (!this.demandReligionConversion) {
                                this.demandReligionConversion();
                            }
                            if (!this.demandGovernmentChange) {
                                this.demandGovernmentChange();
                            }
                            if (!this.demandVassalization) {
                                this.demandVassalization();
                            }
                            if (!this.demandWarReparations) {
                                this.demandWarReparations();
                            }
                            if (!this.demandHumiliate) {
                                this.demandHumiliate();
                            }
                            this.demandGold();
                            this.demandGold();
                            this.demandGold();
                            this.demandGold();
                            if (!this.demandMilitaryAccess) {
                                this.demandMilitaryAccess();
                            }
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (int i5 = this.lProvinces.size() - 1; i5 >= 0; --i5) {
                final int provID = this.lProvinces.get(i5);
                Game.getProvince(provID).setCivID(this.iCivID);
                if (!Game.getProvince(provID).haveACore(this.iCivID)) {
                    Game.getProvince(provID).setEconomy(Game.getProvince(provID).getEconomy() * GameValues.peace.PEACE_ANNEX_PROVINCE_ECONOMY_CHANGE);
                    Game.getProvince(provID).setTaxEfficiency(Game.getProvince(provID).getTaxEfficiency() * GameValues.peace.PEACE_ANNEX_PROVINCE_TAX_CHANGE);
                    Game.getProvince(provID).setGrowthRate(Game.getProvince(provID).getGrowthRate() * GameValues.peace.PEACE_ANNEX_PROVINCE_GROWTH_RATE_CHANGE);
                    Game.getProvince(provID).setManpower(Game.getProvince(provID).getManpower() * GameValues.peace.PEACE_ANNEX_PROVINCE_MANPOWER_CHANGE);
                }
                Game.getProvince(provID).setRevulutionaryRisk(Math.min(Game.getProvince(provID).getRevulutionaryRisk(), GameValues.peace.PEACE_ANNEX_PROVINCE_MAX_UNREST));
                civ.eventsData3.addConqueredProvinces(1);
                if (civ.getCivID() == Game.player.iCivID) {
                    final Stats civStats = Game.stats.civStats;
                    ++civStats.cp;
                }
            }
            final String listString = this.lProvinces.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
            DesktopLauncher.SMS("PeaceProvince: " + this.iCivID + " " + listString);
            if (!byPlayer && !this.lProvinces.isEmpty() && civ2.getNumOfProvinces() > 2 && GameValues.peace.AI_PEACE_EXTRA_DEMAND_SURROUNDED_PROVINCES) {
                final ArrayList<Integer> extraDemand = new ArrayList<Integer>();
                for (int i6 = 0; i6 < civ2.getNumOfProvinces(); ++i6) {
                    if (civ2.getCapitalProvinceID() != civ2.getProvinceID(i6)) {
                        final Province province2 = Game.getProvince(civ2.getProvinceID(i6));
                        int numOfConnectionWithCivID = 0;
                        for (int j3 = 0; j3 < province2.getNeighboringProvincesSize(); ++j3) {
                            if (Game.getProvince(province2.getNeighboringProvinces(j3)).getCivID() == this.iCivID2) {
                                numOfConnectionWithCivID = 0;
                                break;
                            }
                            if (Game.getProvince(province2.getNeighboringProvinces(j3)).getCivID() == this.iCivID) {
                                ++numOfConnectionWithCivID;
                            }
                        }
                        if (numOfConnectionWithCivID > 0) {
                            extraDemand.add(civ2.getProvinceID(i6));
                        }
                    }
                }
                for (int i6 = extraDemand.size() - 1; i6 >= 0; --i6) {
                    Game.getProvince(extraDemand.get(i6)).setCivID(this.iCivID);
                    this.lProvinces.add(extraDemand.get(i6));
                    civ.eventsData3.addConqueredProvinces(1);
                    if (civ.getCivID() == Game.player.iCivID) {
                        final Stats civStats2 = Game.stats.civStats;
                        ++civStats2.cp;
                    }
                }
            }
            if (Game.FOG_OF_WAR && updateFOG) {
                for (int i7 = this.lProvinces.size() - 1; i7 >= 0; --i7) {
                    Game.player.fog.updateFogOfWar_All(this.lProvinces.get(i7));
                }
            }
            if (this.demandVassalization && civ2.getNumOfProvinces() > 0) {
                final int puppetBefore = civ2.getPuppetOfCivID();
                civ2.setPuppetOfCivID(this.iCivID);
                Game.getCiv(puppetBefore).updateArmyImgID();
            }
            if (this.demandWarReparations && civ2.getNumOfProvinces() > 0) {
                final CivilizationBonuses tBonus = new CivilizationBonuses();
                tBonus.MonthlyIncome = getWarReparationsPerMonth(this.iCivID2);
                tBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.peace.PEACE_WAR_REPARATIONS_TURNS;
                civ.addCivilizationBonus_Temporary(tBonus);
                final CivilizationBonuses civilizationBonuses = tBonus;
                civilizationBonuses.MonthlyIncome *= -1.0f;
                civ2.addCivilizationBonus_Temporary(tBonus);
            }
            if (this.demandGovernmentChange && civ2.getIdeologyID() != civ.getIdeologyID()) {
                Game.addSimpleTask(new Game.SimpleTask("changeGovernmentType" + this.iCivID2 + "_" + civ.getIdeologyID(), this.iCivID2, civ.getIdeologyID()) {
                    @Override
                    public void update() {
                        Game.ideologiesManager.changeGovernmentType(this.id, this.id2, true);
                        final Civilization civ = Game.getCiv(this.id);
                        civ.fGold += GameValues.government.CHANGE_GOVERNMENT_COST;
                        final Civilization civ2 = Game.getCiv(this.id);
                        civ2.fLegacy += GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY;
                    }
                });
            }
            try {
                WarManager.lWars.get(this.warKey).updateCapitalProvinceID();
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
            if (this.demandGold > 0) {
                final float demandedGold = this.getGold_PerDemand();
                final Civilization civilization = civ;
                civilization.fGold += demandedGold;
                final Civilization civilization2 = civ2;
                civilization2.fGold -= demandedGold;
            }
            if (this.demandHumiliate) {
                final Civilization civilization3 = civ;
                civilization3.fLegacy += GameValues.peace.PEACE_HUMILIATE_LEGACY_GAIN;
                final Civilization civilization4 = civ2;
                civilization4.fLegacy -= GameValues.peace.PEACE_HUMILIATE_LEGACY_LOSER;
            }
            if (this.demandReligionConversion && civ2.getReligionID() != civ.getReligionID()) {
                civ2.setReligionID_UpdateBonuses(civ.getReligionID());
                try {
                    if (civ2.getCapitalProvinceID() >= 0 && Game.getProvince(civ2.getCapitalProvinceID()).getCivID() == this.iCivID2 && Game.getProvince(civ2.getCapitalProvinceID()).getReligion() != civ2.getReligionID()) {
                        Game.getProvince(civ2.getCapitalProvinceID()).setReligion(civ2.getReligionID());
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
                try {
                    int toConvert = (int)Math.floor(civ2.getNumOfProvinces() * GameValues.peace.PEACE_RELIGION_CONVERSION_PROVINCES_CONVERT) - 1;
                    if (toConvert > 0) {
                        final ArrayList<Integer> tProvinces = new ArrayList<Integer>();
                        for (int i2 = 0; i2 < civ2.getNumOfProvinces(); ++i2) {
                            tProvinces.add(civ2.getProvinceID(i2));
                        }
                        toConvert = Math.min(toConvert, tProvinces.size());
                        for (int i2 = toConvert - 1; i2 >= 0 && tProvinces.size() > 0; --i2) {
                            final int tR = Game.oR.nextInt(tProvinces.size());
                            if (Game.getProvince(tProvinces.get(tR)).getCivID() == this.iCivID2 && Game.getProvince(tProvinces.get(tR)).getReligion() != civ2.getReligionID()) {
                                Game.getProvince(tProvinces.get(tR)).setReligion(civ2.getReligionID());
                            }
                            tProvinces.remove(tR);
                        }
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        civ.setAggressiveExpansion(civ.getAggressiveExpansion() + Math.min(GameValues.aggressiveExpansion.PEACE_TREATY_AE_FROM_WAR_LIMIT, this.fAggressiveExpansion));
        for (int k = 0; k < this.lSubjectTransfer.size(); ++k) {
            if (Game.getCiv(this.lSubjectTransfer.get(k)).getPuppetOfCivID() == this.iCivID2) {
                Game.getCiv(this.lSubjectTransfer.get(k)).setPuppetOfCivID(this.iCivID);
                if (this.iCivID2 == Game.player.iCivID) {
                    Game.getCiv(this.lSubjectTransfer.get(k)).updateArmyImgID();
                }
            }
        }
        for (int k = 0; k < this.lLiberateCiv.size(); ++k) {
            boolean liberated = false;
            for (int l = civ2.getNumOfProvinces() - 1; l >= 0; --l) {
                if (Game.getProvince(civ2.getProvinceID(l)).haveACore(this.lLiberateCiv.get(k))) {
                    Game.getProvince(civ2.getProvinceID(l)).setCivID(this.lLiberateCiv.get(k));
                    liberated = true;
                }
            }
            if (liberated) {
                if (Game.getCiv(this.lLiberateCiv.get(k)).getNumOfProvinces() > 0) {
                    if (Game.getCiv(this.lLiberateCiv.get(k)).getPuppetOfCivID() != this.lLiberateCiv.get(k)) {
                        Game.getCiv(this.lLiberateCiv.get(k)).setPuppetOfCivID(this.lLiberateCiv.get(k));
                    }
                    if (Game.getProvince(Game.getCiv(this.lLiberateCiv.get(k)).getCapitalProvinceID()).getCivID() == this.lLiberateCiv.get(k)) {
                        Game.getCiv(this.lLiberateCiv.get(k)).setCapitalProvinceID(Game.getCiv(this.lLiberateCiv.get(k)).getCapitalProvinceID());
                    }
                    else if (Game.getCiv(this.lLiberateCiv.get(k)).getCapitalProvinceID() < 0 || Game.getProvince(Game.getCiv(this.lLiberateCiv.get(k)).getCapitalProvinceID()).getCivID() != this.lLiberateCiv.get(k)) {
                        Game.getCiv(this.lLiberateCiv.get(k)).moveCapital_ToLargestProvince();
                    }
                    civ.diplomacy.setRelation(civ.getCivID(), this.lLiberateCiv.get(k), GameValues.peace.PEACE_LIBERATE_CIV_RELATIONS);
                    Game.getCiv(this.lLiberateCiv.get(k)).diplomacy.setRelation(this.lLiberateCiv.get(k), this.iCivID, GameValues.peace.PEACE_LIBERATE_CIV_RELATIONS);
                    final Civilization civilization5 = civ;
                    civilization5.fLegacy += GameValues.peace.PEACE_LIBERATE_CIV_LEGACY;
                    if (Game.getCiv(this.iCivID).getResearchedTechnologies() > Game.getCiv(this.lLiberateCiv.get(k)).getResearchedTechnologies()) {
                        for (int a = 0; a < TechnologyTree.iTechnologySize; ++a) {
                            if (Game.getCiv(this.iCivID).getTechResearched(a)) {
                                Game.getCiv(this.lLiberateCiv.get(k)).addTechnology(a, false);
                            }
                        }
                    }
                    DiplomacyManager.addGuarantee(this.iCivID, this.lLiberateCiv.get(k));
                }
            }
        }
        try {
            WarManager.lWars.get(this.warKey).peaceTreaty();
            DesktopLauncher.SMS("PeaceOK: " + this.warKey);
        }
        catch (final Exception ex3) {
            CFG.LOG("enforceDemands");
            CFG.exceptionStack(ex3);
        }
        if (Game.FOG_OF_WAR && updateFOG) {
            if (this.demandVassalization && civ2.getNumOfProvinces() > 0) {
                for (int i8 = 0; i8 < civ2.getNumOfProvinces(); ++i8) {
                    Game.player.fog.updateFogOfWar_All(civ2.getProvinceID(i8));
                }
            }
            for (int i9 = 0; i9 < this.lSubjectTransfer.size(); ++i9) {
                for (int l = 0; l < Game.getCiv(this.lSubjectTransfer.get(i9)).getNumOfProvinces(); ++l) {
                    Game.player.fog.updateFogOfWar_All(Game.getCiv(this.lSubjectTransfer.get(i9)).getProvinceID(l));
                }
            }
        }
        if (this.demandReturnProvinces) {
            final ArrayList<Integer> returnProvinces = new ArrayList<Integer>();
            for (int i10 = 0; i10 < civ2.getNumOfProvinces(); ++i10) {
                if (civ2.getProvinceID(i10) != civ2.getCapitalProvinceID() && Game.getProvince(civ2.getProvinceID(i10)).getCivID() == this.iCivID2 && Game.getProvince(civ2.getProvinceID(i10)).iCoresSize > 0) {
                    if (Game.getProvince(civ2.getProvinceID(i10)).getCore(0) != this.iCivID2) {
                        returnProvinces.add(civ2.getProvinceID(i10));
                    }
                }
            }
            for (int i10 = returnProvinces.size() - 1; i10 >= 0; --i10) {
                Game.getProvince(returnProvinces.get(i10)).setCivID(Game.getProvince(returnProvinces.get(i10)).getCore(0));
            }
        }
        WarManager.lWars.remove(this.warKey);
        WarManager.iWarsSize = WarManager.lWars.size();
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.iCivID);
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.iCivID2);
        updateRivals(this.iCivID);
        updateRivals(this.iCivID2);
        this.updateSpecialAlliances(this.iCivID);
        this.updateSpecialAlliances(this.iCivID2);
        this.updateVassals(this.iCivID);
        this.updateVassals(this.iCivID2);
        this.moveArmiesToOwnTerritory(this.iCivID);
        this.moveArmiesToOwnTerritory(this.iCivID2);
        Game.getCiv(this.iCivID).diplomacy.updateAlliance_ConqueredProvinces(this.iCivID);
        Game.getCiv(this.iCivID2).diplomacy.updateAlliance_ConqueredProvinces(this.iCivID2);
        if (civ2.getNumOfProvinces() <= 0 && civ2.getPuppetOfCivID() != this.iCivID2) {
            civ2.setPuppetOfCivID(this.iCivID2);
        }
        if (updatePlayersWars) {
            try {
                InGame_Info.iCivID = this.iCivID;
                InGame_Info.iCivID2 = this.iCivID2;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("PeaceTreaty"), Game_Calendar.getCurrentDate());
                InGame_Info.imgID = Images.infoDiplomacy;
                Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                    @Override
                    public void update() {
                        Game.menuManager.rebuildInGame_Wars();
                    }
                });
                if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                    Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                        @Override
                        public void update() {
                            ProvinceBorderManager.updateProvinceBorder();
                        }
                    });
                }
                if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(this.warKey)) {
                    Game.menuManager.setVisibleInGame_War(false);
                }
            }
            catch (final Exception ex3) {
                CFG.exceptionStack(ex3);
            }
        }
        return true;
    }
    
    public final void moveArmiesToOwnTerritory(final int civID) {
        try {
            final Civilization civ = Game.getCiv(civID);
            for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
                final int provinceID = civ.getProvinceID(i);
                for (int j = Game.getProvince(provinceID).getArmySize() - 1; j >= 0; --j) {
                    final ArmyDivision armyDivision = Game.getProvince(provinceID).getArmy(j);
                    if (!MoveUnits.isFriendlyProvince_OrAtWAr(armyDivision.civID, provinceID)) {
                        if (Game.getCiv(armyDivision.civID).getNumOfProvinces() > 0) {
                            int bestProvince = Game.getCiv(armyDivision.civID).getProvinceID(0);
                            float bestDistance = Game.getDistanceFromProvinceToProvince(provinceID, Game.getCiv(armyDivision.civID).getProvinceID(0));
                            for (int b = 1; b < Game.getCiv(armyDivision.civID).getNumOfProvinces(); ++b) {
                                if (bestDistance > Game.getDistanceFromProvinceToProvince(provinceID, Game.getCiv(armyDivision.civID).getProvinceID(b))) {
                                    bestProvince = Game.getCiv(armyDivision.civID).getProvinceID(b);
                                    bestDistance = Game.getDistanceFromProvinceToProvince(provinceID, Game.getCiv(armyDivision.civID).getProvinceID(b));
                                }
                            }
                            armyDivision.inBattle = false;
                            armyDivision.inRetreat = false;
                            Game.getProvince(provinceID).removeArmy(armyDivision.key);
                            Game.getProvince(bestProvince).addArmy(armyDivision);
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateVassals(final int civID) {
        try {
            if (Game.getCiv(civID).getNumOfProvinces() <= 0) {
                for (int i = Game.getCiv(civID).diplomacy.iVassalsSize - 1; i >= 0; --i) {
                    Game.getCiv(Game.getCiv(civID).diplomacy.lVassals.get(i).c).setPuppetOfCivID(Game.getCiv(civID).diplomacy.lVassals.get(i).c);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateSpecialAlliances(final int civID) {
        if (Game.getCiv(civID).getNumOfProvinces() <= 0) {
            for (int i = 0; i < Game.getCiv(civID).inAllianceSize; ++i) {
                if (Game.alliancesSpecial.get(Game.getCiv(civID).inAlliance.get(i)).iLeaderCivID == civID) {
                    Game.alliancesSpecial.get(Game.getCiv(civID).inAlliance.get(i)).elections();
                }
            }
        }
    }
    
    public static void updateRivals(final int civID) {
        if (Game.getCiv(civID).getNumOfProvinces() <= 0) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                Game.getCiv(i).diplomacy.removeRival(civID);
            }
        }
    }
    
    public static float getWarReparationsPerMonth(final int iCivID) {
        return Math.max(0.0f, Game.getCiv(iCivID).fTotalIncomePerMonth * GameValues.peace.PEACE_WAR_REPARATIONS);
    }
    
    public static float getTotalProvinceValue(final int nCivID) {
        float out = 0.0f;
        for (int i = 0; i < Game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            out += Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).fProvinceValue;
        }
        return out;
    }
    
    public static float getTotalProvinceValue_Liberate(final int nCivID, final int liberateCivID) {
        float out = 0.0f;
        for (int i = 0; i < Game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).haveACore(liberateCivID)) {
                out += Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).fProvinceValue;
            }
        }
        return out;
    }
    
    public static List<Integer> getCivsPossibleToLiberate(final int iCivID) {
        final ArrayList<Integer> tCivs = new ArrayList<Integer>();
        try {
            for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                for (int j = 0; j < Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).iCoresSize; ++j) {
                    if (Game.getCiv(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCore(j)).getNumOfProvinces() == 0) {
                        if (!tCivs.contains(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCore(j))) {
                            tCivs.add(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCore(j));
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return tCivs;
    }
    
    public static int getLiberateCivProvinces(final int iCivID, final int liberateCivID) {
        int out = 0;
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).haveACore(liberateCivID)) {
                ++out;
            }
        }
        return out;
    }
    
    public static long getLiberateCivPopulation(final int iCivID, final int liberateCivID) {
        long out = 0L;
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).haveACore(liberateCivID)) {
                out += Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getPopulationTotal();
            }
        }
        return out;
    }
    
    public static int getLiberateCivEconomy(final int iCivID, final int liberateCivID) {
        int out = 0;
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).haveACore(liberateCivID)) {
                out += (int)Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getEconomyWithBonuses();
            }
        }
        return out;
    }
    
    public static void moveAllArmiesToOwnTerritory(final int civID) {
        if (Game.getCiv(civID).getNumOfProvinces() > 0) {
            for (int i = Game.getCiv(civID).iArmyPositionSize - 1; i >= 0; --i) {
                if (!MoveUnits.isFriendlyProvince_OrAtWAr(civID, Game.getCiv(civID).getArmyPosition(i))) {
                    final int id;
                    if ((id = Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getArmyKeyID(Game.getCiv(civID).getArmyPositionKey(i), civID)) >= 0) {
                        if (Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getArmy(id).inMovement) {
                            Game.getCiv(civID).removeMove(Game.getCiv(civID).getArmyPositionKey(i));
                        }
                        int bestID = 0;
                        float bestDistance = Game.getDistanceFromProvinceToProvince(Game.getCiv(civID).getArmyPosition(i), Game.getCiv(civID).getProvinceID(0));
                        for (int j = 1; j < Game.getCiv(civID).getNumOfProvinces(); ++j) {
                            if (!Game.getProvince(Game.getCiv(civID).getProvinceID(j)).isOccupied() && !Game.getProvinceData4(Game.getCiv(civID).getProvinceID(j)).isUnderSiege()) {
                                final float tDistance;
                                if (bestDistance > (tDistance = Game.getDistanceFromProvinceToProvince(Game.getCiv(civID).getArmyPosition(i), Game.getCiv(civID).getProvinceID(j)))) {
                                    bestDistance = tDistance;
                                    bestID = j;
                                }
                            }
                        }
                        final ArmyDivision extraRegroup = Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getArmy(Game.getCiv(civID).getArmyPositionKey(i));
                        if (extraRegroup != null) {
                            extraRegroup.inBattle = false;
                            extraRegroup.inRetreat = false;
                            Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).removeArmy(Game.getCiv(civID).getArmyPositionKey(i));
                            Game.getProvince(Game.getCiv(civID).getProvinceID(bestID)).addArmy(extraRegroup);
                        }
                    }
                }
            }
        }
    }
}
