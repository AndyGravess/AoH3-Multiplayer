// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.war;

import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.map.PeaceTreaty;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Stats.Stats;
import aoh.kingdoms.history.mainGame.Player.More.PlayerStats;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import java.util.ArrayList;
import java.util.List;

public class War
{
    public String key;
    public int iWarTurnID;
    public int lastFight_TurnID;
    public float warScore;
    public float warScoreFromBattles;
    public float warScoreFromOccupiedProvinces;
    public float tickingWarScore;
    public List<WarCivilization> lAggressors;
    public List<WarCivilization> lDefenders;
    public boolean conquerVassal;
    public boolean isCoalition;
    
    public War() {
        this.iWarTurnID = 0;
        this.lastFight_TurnID = 0;
        this.warScore = 0.0f;
        this.warScoreFromBattles = 0.0f;
        this.warScoreFromOccupiedProvinces = 0.0f;
        this.tickingWarScore = 0.0f;
        this.lAggressors = new ArrayList<WarCivilization>();
        this.lDefenders = new ArrayList<WarCivilization>();
        this.conquerVassal = false;
        this.isCoalition = false;
    }
    
    public War(final int nAggressor, final int nDefender, final String tKey, final boolean conquerVassal, final boolean isCoalition) {
        this.iWarTurnID = 0;
        this.lastFight_TurnID = 0;
        this.warScore = 0.0f;
        this.warScoreFromBattles = 0.0f;
        this.warScoreFromOccupiedProvinces = 0.0f;
        this.tickingWarScore = 0.0f;
        this.lAggressors = new ArrayList<WarCivilization>();
        this.lDefenders = new ArrayList<WarCivilization>();
        this.conquerVassal = false;
        this.isCoalition = false;
        this.key = tKey;
        this.iWarTurnID = Game_Calendar.TURN_ID;
        this.lastFight_TurnID = Game_Calendar.TURN_ID;
        this.warScore = 0.0f;
        this.conquerVassal = conquerVassal;
        this.isCoalition = isCoalition;
        this.addAggressor(nAggressor);
        this.addDefender(nDefender);
    }
    
    public final void updateWars_TickingWarScore() {
        float tTickingWarScore = (this.warScoreFromOccupiedProvinces + this.warScoreFromBattles) * GameValues.war.TICKING_WAR_SCORE_EACH_MONTH;
        if (tTickingWarScore > 0.0f) {
            tTickingWarScore *= 1.0f + Game.getCiv(this.lDefenders.get(0).iCivID).getWarWeariness() / 100.0f;
        }
        else {
            tTickingWarScore *= 1.0f + Game.getCiv(this.lAggressors.get(0).iCivID).getWarWeariness() / 100.0f;
        }
        if (tTickingWarScore > 0.0f) {
            if (this.tickingWarScore + tTickingWarScore > GameValues.war.TICKING_WAR_SCORE_LIMIT) {
                tTickingWarScore = Math.max(0.0f, GameValues.war.TICKING_WAR_SCORE_LIMIT - this.tickingWarScore);
            }
        }
        else if (this.tickingWarScore + tTickingWarScore < -GameValues.war.TICKING_WAR_SCORE_LIMIT) {
            tTickingWarScore = -Math.max(0.0f, GameValues.war.TICKING_WAR_SCORE_LIMIT + this.tickingWarScore);
        }
        this.warScore += tTickingWarScore;
        this.tickingWarScore += tTickingWarScore;
        if (this.warScore > 0.0f && this.tickingWarScore < 0.0f) {
            this.warScore -= this.tickingWarScore;
            this.tickingWarScore = 0.0f;
        }
        else if (this.warScore < 0.0f && this.tickingWarScore > 0.0f) {
            this.warScore += this.tickingWarScore;
            this.tickingWarScore = 0.0f;
        }
    }
    
    public final void updateWars_AllProvincesOccupied() {
        boolean allOccupied = true;
        final Civilization civDef = Game.getCiv(this.lDefenders.get(0).iCivID);
        for (int i = 0; i < civDef.getNumOfProvinces(); ++i) {
            if (Game.getProvinceData(civDef.getProvinceID(i)).getOccupiedByCivID() == 0) {
                allOccupied = false;
                break;
            }
        }
        if (allOccupied) {
            this.tickingWarScore += GameValues.war.TICKING_WAR_SCORE_IF_ALL_PROVINCES_OCCUPIED;
            this.warScore += GameValues.war.TICKING_WAR_SCORE_IF_ALL_PROVINCES_OCCUPIED;
            return;
        }
        allOccupied = true;
        final Civilization civAgr = Game.getCiv(this.lAggressors.get(0).iCivID);
        for (int j = 0; j < civAgr.getNumOfProvinces(); ++j) {
            if (Game.getProvinceData(civAgr.getProvinceID(j)).getOccupiedByCivID() == 0) {
                allOccupied = false;
                break;
            }
        }
        if (allOccupied) {
            this.tickingWarScore -= GameValues.war.TICKING_WAR_SCORE_IF_ALL_PROVINCES_OCCUPIED;
            this.warScore -= GameValues.war.TICKING_WAR_SCORE_IF_ALL_PROVINCES_OCCUPIED;
        }
    }
    
    public final void addWarScore(final float nWarScore, final int civA, final int civB) {
        this.warScore += this.addWarScore_ValueToAdd(nWarScore, civA, civB);
    }
    
    public final void addWarScore_Just(final float nWarScore) {
        this.warScore += nWarScore;
    }
    
    public final float addWarScore_ValueToAdd(final float nWarScore, final int civA, final int civB) {
        if ((civA == this.lAggressors.get(0).iCivID && civB == this.lDefenders.get(0).iCivID) || (civB == this.lAggressors.get(0).iCivID && civA == this.lDefenders.get(0).iCivID)) {
            return nWarScore;
        }
        return nWarScore * GameValues.war.WAR_SCORE_ALLIES;
    }
    
    public final float addWarScore_ValueToAdd_Province(final float nWarScore, final int civA, final int civB, final int provinceID) {
        if ((civA == this.lAggressors.get(0).iCivID && civB == this.lDefenders.get(0).iCivID) || (civB == this.lAggressors.get(0).iCivID && civA == this.lDefenders.get(0).iCivID) || Game.getProvince(provinceID).getCivID() == this.lDefenders.get(0).iCivID || Game.getProvince(provinceID).getCivID() == this.lAggressors.get(0).iCivID) {
            return nWarScore;
        }
        return nWarScore * GameValues.war.WAR_SCORE_ALLIES;
    }
    
    public final void loadSave_AddInWar() {
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            Game.getCiv(this.lDefenders.get(i).iCivID).diplomacy.addWar(this.key, this.lDefenders.get(i).iCivID);
        }
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            Game.getCiv(this.lAggressors.get(i).iCivID).diplomacy.addWar(this.key, this.lAggressors.get(i).iCivID);
        }
    }
    
    public final void addAggressor(final int nCivID) {
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            if (this.lDefenders.get(i).iCivID == nCivID) {
                return;
            }
        }
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            if (this.lAggressors.get(i).iCivID == nCivID) {
                return;
            }
        }
        this.lAggressors.add(new WarCivilization(nCivID));
        Game.getCiv(nCivID).diplomacy.addWar(this.key, nCivID);
        if (nCivID == Game.player.iCivID) {
            final PlayerStats playerStats = Game.player.playerStats;
            ++playerStats.numOfWars;
            final Stats civStats = Game.stats.civStats;
            ++civStats.nw;
        }
        Game.getCiv(nCivID).eventsData3.addNumOfWars(1);
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            if (!DiplomacyManager.isAtWar(nCivID, this.lDefenders.get(i).iCivID)) {
                DiplomacyManager.declareWar_UpdateRelation(this.lDefenders.get(i).iCivID, nCivID);
            }
        }
        if (this.lAggressors.size() > 1 && Game.getCiv(nCivID).getPuppetOfCivID() == this.lAggressors.get(0).iCivID && !DiplomacyManager.isAlly_AllianceCheck(Game.getCiv(nCivID).getPuppetOfCivID(), this.lAggressors.get(0).iCivID)) {
            Game.getCiv(this.lAggressors.get(0).iCivID).diplomacy.setLibertyDesire_Change(nCivID, GameValues.vassal.LIBERTY_DESIRE_LORD_CALL_TO_WAR);
        }
    }
    
    public final void removeAggressor(final int nCivID) {
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            if (this.lAggressors.get(i).iCivID == nCivID) {
                this.lAggressors.remove(i);
                return;
            }
        }
    }
    
    public final void addDefender(final int nCivID) {
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            if (this.lDefenders.get(i).iCivID == nCivID) {
                return;
            }
        }
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            if (this.lAggressors.get(i).iCivID == nCivID) {
                return;
            }
        }
        this.lDefenders.add(new WarCivilization(nCivID));
        Game.getCiv(nCivID).diplomacy.addWar(this.key, nCivID);
        if (nCivID == Game.player.iCivID) {
            final PlayerStats playerStats = Game.player.playerStats;
            ++playerStats.numOfWars;
            final Stats civStats = Game.stats.civStats;
            ++civStats.nw;
        }
        Game.getCiv(nCivID).eventsData3.addNumOfWars(1);
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            if (!DiplomacyManager.isAtWar(nCivID, this.lAggressors.get(i).iCivID)) {
                DiplomacyManager.declareWar_UpdateRelation(this.lAggressors.get(i).iCivID, nCivID);
            }
        }
        if (this.lDefenders.size() > 1 && Game.getCiv(nCivID).getPuppetOfCivID() == this.lDefenders.get(0).iCivID && !DiplomacyManager.isAlly_AllianceCheck(Game.getCiv(nCivID).getPuppetOfCivID(), this.lDefenders.get(0).iCivID)) {
            Game.getCiv(this.lDefenders.get(0).iCivID).diplomacy.setLibertyDesire_Change(nCivID, GameValues.vassal.LIBERTY_DESIRE_LORD_CALL_TO_WAR);
        }
    }
    
    public final void removeDefender(final int nCivID) {
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            if (this.lDefenders.get(i).iCivID == nCivID) {
                this.lDefenders.remove(i);
                return;
            }
        }
    }
    
    public final void removeCiv(final int nCivID) {
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            if (this.lDefenders.get(i).iCivID == nCivID) {
                this.lDefenders.remove(i);
                return;
            }
        }
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            if (this.lAggressors.get(i).iCivID == nCivID) {
                this.lAggressors.remove(i);
                return;
            }
        }
    }
    
    public final boolean isAggressor(final int iCivID) {
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            if (this.lAggressors.get(i).iCivID == iCivID) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean isDefender(final int iCivID) {
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            if (this.lDefenders.get(i).iCivID == iCivID) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean areInThisWar(final int iCivA, final int iCivB) {
        return (this.isAggressor(iCivA) && this.isDefender(iCivB)) || (this.isAggressor(iCivB) && this.isDefender(iCivA));
    }
    
    public final boolean isInThisWar(final int iCivA) {
        return this.isAggressor(iCivA) || this.isDefender(iCivA);
    }
    
    public final void peaceTreaty() {
        final boolean updatePlayer = this.isInThisWar(Game.player.iCivID);
        Game.getCiv(this.lDefenders.get(0).iCivID).diplomacy.addTruce(this.lAggressors.get(0).iCivID, Game_Calendar.TURN_ID + GameValues.peace.PEACE_WAR_DAYS);
        Game.getCiv(this.lAggressors.get(0).iCivID).diplomacy.addTruce(this.lDefenders.get(0).iCivID, Game_Calendar.TURN_ID + GameValues.peace.PEACE_WAR_DAYS);
        for (int i = this.lAggressors.size() - 1; i > 0; --i) {
            Game.getCiv(this.lDefenders.get(0).iCivID).diplomacy.addTruce(this.lAggressors.get(i).iCivID, Game_Calendar.TURN_ID + GameValues.peace.PEACE_WAR_DAYS);
            Game.getCiv(this.lAggressors.get(i).iCivID).diplomacy.addTruce(this.lDefenders.get(0).iCivID, Game_Calendar.TURN_ID + GameValues.peace.PEACE_WAR_DAYS);
        }
        for (int i = this.lDefenders.size() - 1; i > 0; --i) {
            Game.getCiv(this.lAggressors.get(0).iCivID).diplomacy.addTruce(this.lDefenders.get(i).iCivID, Game_Calendar.TURN_ID + GameValues.peace.PEACE_WAR_DAYS);
            Game.getCiv(this.lDefenders.get(i).iCivID).diplomacy.addTruce(this.lAggressors.get(0).iCivID, Game_Calendar.TURN_ID + GameValues.peace.PEACE_WAR_DAYS);
        }
        Game.battleManager.stopAllBattles_PeaceTreaty(this.key);
        try {
            for (int i = this.lAggressors.size() - 1; i >= 0; --i) {
                final Civilization civ = Game.getCiv(this.lAggressors.get(i).iCivID);
                if (civ.getCivID() != Game.player.iCivID) {
                    for (int j = civ.getMoveUnitsSize() - 1; j >= 0; --j) {
                        final int toCivID = Game.getProvince(civ.getMoveUnits(j).getToProvinceLastID()).getCivID();
                        for (int k = this.lDefenders.size() - 1; k >= 0; --k) {
                            if (this.lDefenders.get(k).iCivID == toCivID) {
                                civ.removeMove(j);
                                break;
                            }
                        }
                    }
                }
            }
            for (int i = this.lDefenders.size() - 1; i >= 0; --i) {
                final Civilization civ = Game.getCiv(this.lDefenders.get(i).iCivID);
                if (civ.getCivID() != Game.player.iCivID) {
                    for (int j = civ.getMoveUnitsSize() - 1; j >= 0; --j) {
                        final int toCivID = Game.getProvince(civ.getMoveUnits(j).getToProvinceLastID()).getCivID();
                        for (int k = this.lAggressors.size() - 1; k >= 0; --k) {
                            if (this.lAggressors.get(k).iCivID == toCivID) {
                                civ.removeMove(j);
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        for (int i = this.lAggressors.size() - 1; i >= 0; --i) {
            for (int l = this.lDefenders.size() - 1; l >= 0; --l) {
                try {
                    DiplomacyManager.declareWar_UpdateRelation_Peace(this.lAggressors.get(i).iCivID, this.lDefenders.get(l).iCivID, this.key);
                    this.retakeOccupiedProvinces(this.lAggressors.get(i).iCivID, this.lDefenders.get(l).iCivID);
                    this.retakeOccupiedProvinces(this.lDefenders.get(l).iCivID, this.lAggressors.get(i).iCivID);
                    if (this.lAggressors.get(i).iCivID == Game.player.iCivID) {
                        Game.getCiv(this.lDefenders.get(l).iCivID).updateArmyImgID();
                    }
                    else if (this.lDefenders.get(l).iCivID == Game.player.iCivID) {
                        Game.getCiv(this.lAggressors.get(i).iCivID).updateArmyImgID();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        for (int i = this.lAggressors.size() - 1; i >= 0; --i) {
            if (Game.getCiv(this.lAggressors.get(i).iCivID).getNumOfProvinces() <= 0) {
                Game.getCiv(this.lAggressors.get(i).iCivID).removeAllArmies();
            }
            else {
                PeaceTreaty.moveAllArmiesToOwnTerritory(this.lAggressors.get(i).iCivID);
                if (this.lAggressors.get(i).iCivID != Game.player.iCivID && !Game.getCiv(this.lAggressors.get(i).iCivID).diplomacy.isAtWar()) {
                    Game.gameThread.addAI_SimpleTask(new Game.SimpleTask("update_ReorganizeArmiesAtPeace" + this.lAggressors.get(i).iCivID, this.lAggressors.get(i).iCivID) {
                        @Override
                        public void update() {
                            Game.aiManager.update_ReorganizeArmiesAtPeace(this.id);
                        }
                    });
                }
            }
        }
        for (int i = this.lDefenders.size() - 1; i >= 0; --i) {
            if (Game.getCiv(this.lDefenders.get(i).iCivID).getNumOfProvinces() <= 0) {
                Game.getCiv(this.lDefenders.get(i).iCivID).removeAllArmies();
            }
            else {
                PeaceTreaty.moveAllArmiesToOwnTerritory(this.lDefenders.get(i).iCivID);
            }
            if (this.lDefenders.get(i).iCivID != Game.player.iCivID && !Game.getCiv(this.lDefenders.get(i).iCivID).diplomacy.isAtWar()) {
                Game.gameThread.addAI_SimpleTask(new Game.SimpleTask("update_ReorganizeArmiesAtPeace" + this.lDefenders.get(i).iCivID, this.lDefenders.get(i).iCivID) {
                    @Override
                    public void update() {
                        Game.aiManager.update_ReorganizeArmiesAtPeace(this.id);
                    }
                });
            }
        }
        this.lAggressors.clear();
        this.lDefenders.clear();
        if (updatePlayer && GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
            Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                @Override
                public void update() {
                    ProvinceBorderManager.updateProvinceBorder();
                }
            });
        }
    }
    
    public final void retakeOccupiedProvinces(final int iCivA, final int iCivB) {
        for (int i = 0; i < Game.getCiv(iCivA).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(iCivA).getProvinceID(i)).isOccupied() && Game.getProvinceData(Game.getCiv(iCivA).getProvinceID(i)).getOccupiedByCivID() == iCivB) {
                Game.getProvince(Game.getCiv(iCivA).getProvinceID(i)).retakeOccupiedProvince_Peace();
            }
        }
    }
    
    public final void addCasualties(final int iCivID, final int iCasualties) {
        this.lastFight_TurnID = Game_Calendar.TURN_ID;
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            if (this.lDefenders.get(i).iCivID == iCivID) {
                final WarCivilization warCivilization = this.lDefenders.get(i);
                warCivilization.iCasualties += iCasualties;
                return;
            }
        }
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            if (this.lAggressors.get(i).iCivID == iCivID) {
                final WarCivilization warCivilization2 = this.lAggressors.get(i);
                warCivilization2.iCasualties += iCasualties;
                return;
            }
        }
    }
    
    public int getWarScore_Side(final int iCivID) {
        if (this.isAggressor(iCivID)) {
            return -1;
        }
        return 1;
    }
    
    public final int getCasualties_Aggressors() {
        int out = 0;
        for (int i = 0; i < this.lAggressors.size(); ++i) {
            out += this.lAggressors.get(i).iCasualties;
        }
        return out;
    }
    
    public final int getCasualties_Defenders() {
        int out = 0;
        for (int i = 0; i < this.lDefenders.size(); ++i) {
            out += this.lDefenders.get(i).iCasualties;
        }
        return out;
    }
    
    public boolean isWarLeader(final int civID) {
        return this.lAggressors.get(0).iCivID == civID || this.lDefenders.get(0).iCivID == civID;
    }
    
    public final void updateCapitalProvinceID() {
        try {
            for (int i = this.lDefenders.size() - 1; i >= 0; --i) {
                if (Game.getCiv(this.lDefenders.get(i).iCivID).getCapitalProvinceID() < 0) {
                    Game.getCiv(this.lDefenders.get(i).iCivID).moveCapital_ToLargestProvince();
                }
                else if (Game.getProvince(Game.getCiv(this.lDefenders.get(i).iCivID).getCapitalProvinceID()).getCivID() != this.lDefenders.get(i).iCivID) {
                    Game.getCiv(this.lDefenders.get(i).iCivID).moveCapital_ToLargestProvince();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i = this.lAggressors.size() - 1; i >= 0; --i) {
                if (Game.getCiv(this.lAggressors.get(i).iCivID).getCapitalProvinceID() < 0) {
                    Game.getCiv(this.lAggressors.get(i).iCivID).moveCapital_ToLargestProvince();
                }
                else if (Game.getProvince(Game.getCiv(this.lAggressors.get(i).iCivID).getCapitalProvinceID()).getCivID() != this.lAggressors.get(i).iCivID) {
                    Game.getCiv(this.lAggressors.get(i).iCivID).moveCapital_ToLargestProvince();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
