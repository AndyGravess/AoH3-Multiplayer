// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.allianceHRE;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class Alliance
{
    public String Name_Alliance;
    public String Name_Leader;
    public String Name_FirstTier;
    public String Name_Rest;
    public String FlagTag;
    public int iLeaderCivID;
    public List<Integer> firstTier;
    public List<Integer> secondTier;
    public int typeOfAlliance;
    public int iReformsPassed;
    
    public Alliance() {
        this.Name_Alliance = "";
        this.Name_Leader = "";
        this.Name_FirstTier = "";
        this.Name_Rest = "";
        this.FlagTag = "";
        this.firstTier = new ArrayList<Integer>();
        this.secondTier = new ArrayList<Integer>();
        this.typeOfAlliance = 0;
        this.iReformsPassed = 0;
    }
    
    public String getTypeOfAlliance_Name() {
        if (this.typeOfAlliance == 0) {
            return Game.lang.get("HolyRomanEmpire");
        }
        return Game.lang.get("Defensive");
    }
    
    public void incTypeOfAlliance() {
        ++this.typeOfAlliance;
        if (this.typeOfAlliance > 1) {
            this.typeOfAlliance = 0;
        }
    }
    
    public void elections() {
        int bestCivID = -1;
        float bestScore = -100000.0f;
        if (this.electionsCheck(this.iLeaderCivID, bestScore)) {
            bestCivID = this.iLeaderCivID;
            bestScore = Game.getCiv(this.iLeaderCivID).iCivRankScore;
        }
        for (int i = this.firstTier.size() - 1; i >= 0; --i) {
            if (this.electionsCheck(this.firstTier.get(i), bestScore)) {
                bestCivID = this.firstTier.get(i);
                bestScore = Game.getCiv(this.firstTier.get(i)).iCivRankScore;
            }
        }
        for (int i = this.secondTier.size() - 1; i >= 0; --i) {
            if (this.electionsCheck(this.secondTier.get(i), bestScore)) {
                bestCivID = this.secondTier.get(i);
                bestScore = Game.getCiv(this.secondTier.get(i)).iCivRankScore;
            }
        }
        final int oldLeader = this.iLeaderCivID;
        this.iLeaderCivID = bestCivID;
        if (oldLeader != this.iLeaderCivID) {
            if (!this.firstTier.contains(oldLeader) && !this.secondTier.contains(oldLeader)) {
                if (Game.getCiv(oldLeader).getNumOfProvinces() > 0) {
                    this.firstTier.add(oldLeader);
                }
                else {
                    this.secondTier.add(oldLeader);
                }
            }
            Game.gameThread.addCivUpdateTotalIncomePerMonth(oldLeader);
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.iLeaderCivID);
            Game.gameThreadTurns.addCivUpdateMaxManpower(oldLeader);
            Game.gameThreadTurns.addCivUpdateMaxManpower(this.iLeaderCivID);
            Game.getCiv(oldLeader).updateRegimentsLimit();
            Game.getCiv(this.iLeaderCivID).updateRegimentsLimit();
        }
        if (this.isInAlliance(Game.player.iCivID)) {
            Game.player.addNotification(new Notification(Notification.Notification_Type.DEFAULT, Game.lang.get("ResultOfElections") + ": " + Game.getCiv(this.iLeaderCivID).getCivName(), Images.alliance, Game_Calendar.TURN_ID, Notification.Notification_BG.NEUTRAL_BG, Game.getCiv(this.iLeaderCivID).getCapitalProvinceID()) {
                @Override
                public void onAction() {
                    Game.mapCoords.centerToProvinceID(this.id);
                }
            });
        }
    }
    
    public boolean electionsCheck(final int civID, final float score) {
        return civID > 0 && Game.getCiv(civID).getNumOfProvinces() > 0 && Game.getCiv(civID).iCivRankScore > score;
    }
    
    public void setLeader(final int iCivID) {
        this.iLeaderCivID = iCivID;
    }
    
    public void addFirstTier(final int nCivID) {
        for (int i = 0; i < this.firstTier.size(); ++i) {
            if (this.firstTier.get(i) == nCivID) {
                return;
            }
        }
        this.firstTier.add(nCivID);
        this.removeSecondTier(nCivID);
    }
    
    public void removeFirstTier(final int nCivID) {
        for (int i = 0; i < this.firstTier.size(); ++i) {
            if (this.firstTier.get(i) == nCivID) {
                this.firstTier.remove(i);
                return;
            }
        }
    }
    
    public void addSecondTier(final int nCivID) {
        for (int i = 0; i < this.secondTier.size(); ++i) {
            if (this.secondTier.get(i) == nCivID) {
                return;
            }
        }
        this.secondTier.add(nCivID);
        this.removeFirstTier(nCivID);
    }
    
    public void removeSecondTier(final int nCivID) {
        for (int i = 0; i < this.secondTier.size(); ++i) {
            if (this.secondTier.get(i) == nCivID) {
                this.secondTier.remove(i);
                return;
            }
        }
    }
    
    public void removeCiv(final int civID) {
        this.removeFirstTier(civID);
        this.removeSecondTier(civID);
        if (this.iLeaderCivID == civID) {
            this.iLeaderCivID = -1;
            this.elections();
        }
    }
    
    public boolean isInAlliance(final int iCivID) {
        if (this.iLeaderCivID == iCivID) {
            return true;
        }
        for (int i = 0; i < this.firstTier.size(); ++i) {
            if (this.firstTier.get(i) == iCivID) {
                return true;
            }
        }
        for (int i = 0; i < this.secondTier.size(); ++i) {
            if (this.secondTier.get(i) == iCivID) {
                return true;
            }
        }
        return false;
    }
    
    public final int getPopulation() {
        int out = 0;
        for (int i = 0; i < this.firstTier.size(); ++i) {
            out += (int)Game.getCiv(this.firstTier.get(i)).getPopulationTotal();
        }
        for (int i = 0; i < this.secondTier.size(); ++i) {
            out += (int)Game.getCiv(this.secondTier.get(i)).getPopulationTotal();
        }
        if (!this.firstTier.contains(this.iLeaderCivID) && !this.secondTier.contains(this.iLeaderCivID)) {
            out += (int)Game.getCiv(this.iLeaderCivID).getPopulationTotal();
        }
        return out;
    }
    
    public final int getEconomy() {
        int out = 0;
        for (int i = 0; i < this.firstTier.size(); ++i) {
            out += (int)Game.getCiv(this.firstTier.get(i)).getEconomyTotal();
        }
        for (int i = 0; i < this.secondTier.size(); ++i) {
            out += (int)Game.getCiv(this.secondTier.get(i)).getEconomyTotal();
        }
        if (!this.firstTier.contains(this.iLeaderCivID) && !this.secondTier.contains(this.iLeaderCivID)) {
            out += (int)Game.getCiv(this.iLeaderCivID).getEconomyTotal();
        }
        return out;
    }
    
    public final int getProvinces() {
        int out = 0;
        for (int i = 0; i < this.firstTier.size(); ++i) {
            out += Game.getCiv(this.firstTier.get(i)).getNumOfProvinces();
        }
        for (int i = 0; i < this.secondTier.size(); ++i) {
            out += Game.getCiv(this.secondTier.get(i)).getNumOfProvinces();
        }
        if (!this.firstTier.contains(this.iLeaderCivID) && !this.secondTier.contains(this.iLeaderCivID)) {
            out += Game.getCiv(this.iLeaderCivID).getNumOfProvinces();
        }
        return out;
    }
    
    public final int getNumOfCivilizations() {
        int out = 0;
        for (int i = 0; i < this.firstTier.size(); ++i) {
            if (Game.getCiv(this.firstTier.get(i)).getNumOfProvinces() > 0) {
                ++out;
            }
        }
        for (int i = 0; i < this.secondTier.size(); ++i) {
            if (Game.getCiv(this.secondTier.get(i)).getNumOfProvinces() > 0) {
                ++out;
            }
        }
        if (!this.firstTier.contains(this.iLeaderCivID) && !this.secondTier.contains(this.iLeaderCivID) && Game.getCiv(this.iLeaderCivID).getNumOfProvinces() > 0) {
            ++out;
        }
        return out;
    }
    
    public final int getNumOfCivilizations_FirstTier() {
        int out = 0;
        for (int i = 0; i < this.firstTier.size(); ++i) {
            if (Game.getCiv(this.firstTier.get(i)).getNumOfProvinces() > 0) {
                ++out;
            }
        }
        return out;
    }
    
    public final int getNumOfCivilizations_SecondTier() {
        int out = 0;
        for (int i = 0; i < this.secondTier.size(); ++i) {
            if (Game.getCiv(this.secondTier.get(i)).getNumOfProvinces() > 0) {
                ++out;
            }
        }
        return out;
    }
    
    public void updateAfterRemoveOfCiv(final int nCivID) {
        for (int i = 0; i < this.secondTier.size(); ++i) {
            if (this.secondTier.get(i) > nCivID) {
                this.secondTier.set(i, this.secondTier.get(i) - 1);
            }
        }
        for (int i = 0; i < this.firstTier.size(); ++i) {
            if (this.firstTier.get(i) > nCivID) {
                this.firstTier.set(i, this.firstTier.get(i) - 1);
            }
        }
        if (this.iLeaderCivID > nCivID) {
            --this.iLeaderCivID;
        }
    }
}
