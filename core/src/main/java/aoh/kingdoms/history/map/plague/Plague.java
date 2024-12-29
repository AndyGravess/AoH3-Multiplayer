// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.plague;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData10;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class Plague
{
    public String sName;
    public int iPlagueID_InGame;
    public List<Integer> lProvinces;
    public int iProvincesSize;
    public List<Integer> lProvinces_Active;
    public float fDeathRate;
    public int iDurationTurnsLeft;
    public float fDevastation;
    public int iDeaths;
    public int iImageID;
    public int iDurationTurnsLeft_BEGINNING;
    public float fR;
    public float fG;
    public float fB;
    public float EXPANSION_MODIFIER;
    public float EXPANSION_SCORE;
    public boolean nS;
    
    public Plague() {
        this.iPlagueID_InGame = 0;
        this.lProvinces = new ArrayList<Integer>();
        this.iProvincesSize = 0;
        this.lProvinces_Active = new ArrayList<Integer>();
        this.fDeathRate = 0.0f;
        this.iDurationTurnsLeft = 0;
        this.fDevastation = 0.0f;
        this.iDeaths = 0;
        this.iImageID = 0;
        this.iDurationTurnsLeft_BEGINNING = 0;
        this.nS = false;
    }
    
    public Plague(final int outbreakProvince, final String sName, final float fR, final float fG, final float fB, final int nPlagueID_InGame, final float fDeathRate, final int iDurationTurnsLeft, final float EXPANSION_MODIFIER, final int iImageID, final float fDevastation) {
        this.iPlagueID_InGame = 0;
        this.lProvinces = new ArrayList<Integer>();
        this.iProvincesSize = 0;
        this.lProvinces_Active = new ArrayList<Integer>();
        this.fDeathRate = 0.0f;
        this.iDurationTurnsLeft = 0;
        this.fDevastation = 0.0f;
        this.iDeaths = 0;
        this.iImageID = 0;
        this.iDurationTurnsLeft_BEGINNING = 0;
        this.nS = false;
        this.sName = Game.lang.get(sName);
        this.iPlagueID_InGame = nPlagueID_InGame;
        this.fR = fR;
        this.fG = fG;
        this.fB = fB;
        this.iImageID = iImageID;
        this.fDevastation = fDevastation;
        this.fDeathRate = fDeathRate;
        this.iDurationTurnsLeft = iDurationTurnsLeft;
        this.iDurationTurnsLeft_BEGINNING = iDurationTurnsLeft;
        this.EXPANSION_MODIFIER = EXPANSION_MODIFIER;
        this.addProvince(outbreakProvince);
    }
    
    protected final void runDisease() {
        for (int i = this.lProvinces_Active.size() - 1; i >= 0; --i) {
            if (Game.getProvince(this.lProvinces_Active.get(i)).provincePlague != null && Game.getProvince(this.lProvinces_Active.get(i)).provincePlague.id == this.getPlagueID_InGame()) {
                int nPopBefore = Game.getProvince(this.lProvinces_Active.get(i)).getPopulationTotal();
                final int nDeaths = (int)Math.ceil(nPopBefore * (this.fDeathRate * (0.225f + 0.325f * this.getDurationPercLEFT() + 0.55f * Game.oR.nextInt(100) / 100.0f)) * (1.0f + Math.max(GameValues.plagues.DISEASE_MAX_DEATH_RATE_REDUCTION, Game.getProvince(this.lProvinces_Active.get(i)).provBonuses.DiseaseDeathRate + Game.getCiv(Game.getProvince(this.lProvinces_Active.get(i)).getCivID()).civBonuses.DiseaseDeathRate + Game.getProvince(this.lProvinces_Active.get(i)).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_DISEASE_DEATH_RATE_PER_LVL)));
                if (nDeaths > 0) {
                    for (int k = Game.getProvince(this.lProvinces_Active.get(i)).getPopulationSize() - 1; k >= 0; --k) {
                        Game.getProvince(this.lProvinces_Active.get(i)).setPopulationOfCivID(Game.getProvince(this.lProvinces_Active.get(i)).getPopulationCivID(k), (int)(Game.getProvince(this.lProvinces_Active.get(i)).getPopulationID(k) - Math.floor(nDeaths * (Game.getProvince(this.lProvinces_Active.get(i)).getPopulationID(k) / (float)nPopBefore))));
                    }
                    nPopBefore -= Game.getProvince(this.lProvinces_Active.get(i)).getPopulationTotal();
                    final ProvincePlague provincePlague = Game.getProvince(this.lProvinces_Active.get(i)).provincePlague;
                    provincePlague.deaths += nPopBefore;
                    final ProvinceData10 provinceData10 = Game.getProvinceData10(this.lProvinces_Active.get(i));
                    provinceData10.d += nPopBefore;
                    this.iDeaths += nPopBefore;
                    Game.getProvince(this.lProvinces_Active.get(i)).setDevastation(Game.getProvince(this.lProvinces_Active.get(i)).getDevastation() + this.fDevastation * (0.75f + Game.oR.nextInt(75) / 100.0f));
                }
                final ProvincePlague provincePlague2 = Game.getProvince(this.lProvinces_Active.get(i)).provincePlague;
                provincePlague2.turnsLeft -= 0.875f - 0.065f * (Game.getProvince(this.lProvinces_Active.get(i)).getGrowthRateWithBonuses() / 100.0f) + Game.oR.nextInt(GameValues.plagues.DISEASE_DAYS_LEFT_RANDOM) / 1000.0f;
                if (Game.getProvince(this.lProvinces_Active.get(i)).provincePlague.turnsLeft <= 0.0f) {
                    Game.getProvinceData10(this.lProvinces_Active.get(i)).t = Game_Calendar.TURN_ID;
                    Game.getProvince(this.lProvinces_Active.get(i)).provincePlague = null;
                    this.lProvinces_Active.remove(i);
                }
            }
        }
        this.fDeathRate *= GameValues.plagues.DISEASE_DEATH_RATE_CHANGE_PER_DAY - Game.oR.nextInt(GameValues.plagues.DISEASE_DEATH_RATE_CHANGE_PER_DAY_RANDOM) / 10000.0f;
    }
    
    protected final void spreadDisease() {
        if (this.iDurationTurnsLeft > 0 && this.lProvinces_Active.size() > 0) {
            if (this.lProvinces.size() / (float)Game.getProvincesSize() > 0.35f) {
                return;
            }
            this.EXPANSION_SCORE += this.lProvinces_Active.size() * 0.425f * this.EXPANSION_MODIFIER * (0.1f + 0.9f * this.getDurationPercLEFT());
            this.EXPANSION_MODIFIER *= 0.925f - Game.oR.nextInt(17850) / 100000.0f;
            if (this.EXPANSION_SCORE >= 1.0f) {
                final int nRand = Game.oR.nextInt((int)this.EXPANSION_SCORE);
                if (nRand > 0) {
                    this.EXPANSION_SCORE -= nRand;
                    this.spreadDisease(nRand);
                }
            }
        }
    }
    
    protected final void spreadDisease(int nNumOfProvinces) {
        try {
            nNumOfProvinces = (int)Math.min((float)nNumOfProvinces, Math.max(Game.getProvincesSize() * 0.01425f, 16.0f));
            final List<Integer> tPossibleSpreadProvinces = new ArrayList<Integer>();
            final List<Integer> tPossibleSpreadProvinces_Scores = new ArrayList<Integer>();
            for (int i = 0; i < this.lProvinces_Active.size(); ++i) {
                if (Game.getProvince(this.lProvinces_Active.get(i)).getSeaProvince()) {
                    for (int k = 0; k < Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                        if (Game.getProvince(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                            tPossibleSpreadProvinces.add(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                        }
                    }
                }
                else {
                    for (int k = 0; k < Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                        if (Game.getProvince(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).getWasteland() < 0 && Game.getProvince(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                            tPossibleSpreadProvinces.add(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                        }
                    }
                    if (Game.getProvince(this.lProvinces_Active.get(i)).getLevelOfPort() > 0 || Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize() < 2) {
                        for (int k = 0; k < Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvincesSize(); ++k) {
                            if (Game.getProvince(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).getWasteland() < 0 && Game.getProvince(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                                tPossibleSpreadProvinces.add(Game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k));
                            }
                        }
                    }
                }
            }
            if (tPossibleSpreadProvinces.size() > 0) {
                int tTotalScore = 0;
                for (int j = tPossibleSpreadProvinces.size() - 1; j >= 0; --j) {
                    final int tempScore = this.getSpreadScore(tPossibleSpreadProvinces.get(j)) * 3 + 1;
                    tPossibleSpreadProvinces_Scores.add(tempScore);
                    tTotalScore += tempScore;
                }
                if (tTotalScore > 0) {
                    while (tPossibleSpreadProvinces_Scores.size() > 0 && nNumOfProvinces > 0) {
                        final int tRandScore = Game.oR.nextInt(tTotalScore);
                        int l = 0;
                        int tCurrentScore = 0;
                        while (l < tPossibleSpreadProvinces_Scores.size()) {
                            tCurrentScore += tPossibleSpreadProvinces_Scores.get(l);
                            if (tCurrentScore > tRandScore) {
                                this.addProvince(tPossibleSpreadProvinces.get(l));
                                tTotalScore -= tPossibleSpreadProvinces_Scores.get(l);
                                tPossibleSpreadProvinces_Scores.remove(l);
                                tPossibleSpreadProvinces.remove(l);
                                --nNumOfProvinces;
                                break;
                            }
                            ++l;
                        }
                    }
                    if (nNumOfProvinces > 0) {
                        this.spreadDisease(nNumOfProvinces);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    protected final int getSpreadScore(final int nProvinceID) {
        int tempScore = 0;
        for (int k = 0; k < Game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++k) {
            if (Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(k)).provincePlague == null) {
                tempScore += (Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(k)).getSeaProvince() ? 1 : 2);
            }
        }
        for (int k = 0; k < Game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++k) {
            if (Game.getProvince(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(k)).provincePlague == null) {
                tempScore += (Game.getProvince(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(k)).getSeaProvince() ? 1 : 2);
            }
        }
        return tempScore;
    }
    
    protected final void addProvince(final int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvinces.get(i) == nProvinceID) {
                return;
            }
        }
        Game.getProvinceData10(nProvinceID).t = Game_Calendar.TURN_ID;
        if (Game.getProvince(nProvinceID).provincePlague != null) {
            return;
        }
        Game.getProvince(nProvinceID).provincePlague = new ProvincePlague(this.iPlagueID_InGame, Game_Calendar.TURN_ID, this.iDurationTurnsLeft * (0.625f + Game.oR.nextInt(6000) / 10000.0f), 0);
        if (!this.nS && Game.getProvince(nProvinceID).getCivID() == Game.player.iCivID && Game.player.iCivID > 0) {
            this.nS = true;
            if (Game.oR.nextInt(100) < GameValues.plagues.SEND_NOTIFICATION_CHANCE) {
                Game.player.addNotification(new Notification(Notification.Notification_Type.DISEASE, this.sName + ": " + Game.lang.get("Disease"), Images.disease, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, this.sName, nProvinceID));
            }
        }
        final ProvinceData10 provinceData10 = Game.getProvinceData10(nProvinceID);
        ++provinceData10.n;
        this.lProvinces.add(nProvinceID);
        this.lProvinces_Active.add(nProvinceID);
        this.iProvincesSize = this.lProvinces.size();
    }
    
    protected final String getPlagueName() {
        try {
            return this.sName;
        }
        catch (final Exception ex) {
            return Game.lang.get("Plague");
        }
    }
    
    protected final void setPlagueID_InGame(final int iPlagueID_InGame) {
        this.iPlagueID_InGame = iPlagueID_InGame;
    }
    
    protected final int getPlagueID_InGame() {
        return this.iPlagueID_InGame;
    }
    
    protected final float getDurationPercLEFT() {
        return this.iDurationTurnsLeft / (float)this.iDurationTurnsLeft_BEGINNING;
    }
    
    protected final float getDurationPercLEFT(final int nNumOfTurns) {
        return nNumOfTurns / (float)this.iDurationTurnsLeft_BEGINNING;
    }
    
    protected final int getOutbreakProvinceID() {
        try {
            return this.lProvinces.get(0);
        }
        catch (final IndexOutOfBoundsException ex) {
            return -1;
        }
    }
    
    protected final int getDeaths() {
        return this.iDeaths;
    }
    
    protected final int getNumOfProvinces_Total() {
        return this.lProvinces.size();
    }
    
    protected final int getNumOfProvinces_Active() {
        return this.lProvinces_Active.size();
    }
}
