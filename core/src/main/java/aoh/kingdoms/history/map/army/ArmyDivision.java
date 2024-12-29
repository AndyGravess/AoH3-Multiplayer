// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_Army_Composition;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy;
import java.util.ArrayList;
import java.util.List;

public class ArmyDivision
{
    public String key;
    public List<ArmyRegiment> lArmyRegiment;
    public int iArmyRegimentSize;
    public int civID;
    public int provinceID;
    public ArmyGeneral armyGeneral;
    public boolean inRetreat;
    public int iArmyOfProvinceID;
    public float fMaintenanceCost;
    private float fAverageSpeed;
    public float fMorale;
    public int fMoraleDraw;
    public boolean inMovement;
    public boolean inBattle;
    public boolean addedToBattle;
    public int iArmy;
    public String sArmy;
    public int iShiftX;
    public int iShiftY;
    public int iShiftX_Scaled;
    public int iShiftY_Scaled;
    public int iArmyWidth;
    public int iArmyExtraPosX;
    public boolean updateMorale;
    public byte iStack;
    
    public ArmyDivision() {
        this.lArmyRegiment = new ArrayList<ArmyRegiment>();
        this.iArmyRegimentSize = 0;
        this.armyGeneral = null;
        this.inRetreat = false;
        this.fMaintenanceCost = 0.0f;
        this.fAverageSpeed = 1.0f;
        this.fMorale = 0.0f;
        this.fMoraleDraw = 0;
        this.inMovement = false;
        this.inBattle = false;
        this.addedToBattle = false;
        this.iArmy = 0;
        this.updateMorale = false;
        this.iStack = 0;
    }
    
    public ArmyDivision(final int nCivID, final List<InGame_RecruitArmy_NewArmy.CreateNewArmy> tArmyRegiment) {
        this.lArmyRegiment = new ArrayList<ArmyRegiment>();
        this.iArmyRegimentSize = 0;
        this.armyGeneral = null;
        this.inRetreat = false;
        this.fMaintenanceCost = 0.0f;
        this.fAverageSpeed = 1.0f;
        this.fMorale = 0.0f;
        this.fMoraleDraw = 0;
        this.inMovement = false;
        this.inBattle = false;
        this.addedToBattle = false;
        this.iArmy = 0;
        this.updateMorale = false;
        this.iStack = 0;
        this.civID = nCivID;
        final ArrayList<ArmyRegiment> nArmyRegiment = new ArrayList<ArmyRegiment>();
        for (int iSize = tArmyRegiment.size(), i = 0; i < iSize; ++i) {
            for (int j = 0; j < tArmyRegiment.get(i).numOfRegiments; ++j) {
                nArmyRegiment.add(new ArmyRegiment(tArmyRegiment.get(i).iUnitID, tArmyRegiment.get(i).iArmyID));
            }
        }
        this.sortArmyRegiment(nArmyRegiment);
        this.setArmyGeneral(null);
    }
    
    public ArmyDivision(final int nCivID, final int nProvinceID, final List<ArmyRegiment> nArmyRegiment) {
        this.lArmyRegiment = new ArrayList<ArmyRegiment>();
        this.iArmyRegimentSize = 0;
        this.armyGeneral = null;
        this.inRetreat = false;
        this.fMaintenanceCost = 0.0f;
        this.fAverageSpeed = 1.0f;
        this.fMorale = 0.0f;
        this.fMoraleDraw = 0;
        this.inMovement = false;
        this.inBattle = false;
        this.addedToBattle = false;
        this.iArmy = 0;
        this.updateMorale = false;
        this.iStack = 0;
        this.civID = nCivID;
        this.provinceID = nProvinceID;
        this.iArmyOfProvinceID = nProvinceID;
        this.key = CFG.extraRandomTag();
        this.sortArmyRegiment(nArmyRegiment);
        this.updateArmy(true);
        this.updateMorale();
        this.setArmyGeneral(null);
    }
    
    public ArmyDivision(final int nCivID, final int nProvinceID, final List<ArmyRegiment> nArmyRegiment, final String key) {
        this.lArmyRegiment = new ArrayList<ArmyRegiment>();
        this.iArmyRegimentSize = 0;
        this.armyGeneral = null;
        this.inRetreat = false;
        this.fMaintenanceCost = 0.0f;
        this.fAverageSpeed = 1.0f;
        this.fMorale = 0.0f;
        this.fMoraleDraw = 0;
        this.inMovement = false;
        this.inBattle = false;
        this.addedToBattle = false;
        this.iArmy = 0;
        this.updateMorale = false;
        this.iStack = 0;
        this.civID = nCivID;
        this.provinceID = nProvinceID;
        this.iArmyOfProvinceID = nProvinceID;
        this.key = key;
        this.sortArmyRegiment(nArmyRegiment);
        this.updateArmy(true);
        this.updateMorale();
        this.setArmyGeneral(null);
    }
    
    public ArmyDivision(final int nCivID, final int nProvinceID, final int i, final String NewKey) {
        this.lArmyRegiment = new ArrayList<ArmyRegiment>();
        this.iArmyRegimentSize = 0;
        this.armyGeneral = null;
        this.inRetreat = false;
        this.fMaintenanceCost = 0.0f;
        this.fAverageSpeed = 1.0f;
        this.fMorale = 0.0f;
        this.fMoraleDraw = 0;
        this.inMovement = false;
        this.inBattle = false;
        this.addedToBattle = false;
        this.iArmy = 0;
        this.updateMorale = false;
        this.iStack = 0;
        this.civID = nCivID;
        this.provinceID = nProvinceID;
        this.iArmyOfProvinceID = nProvinceID;
        this.key = NewKey;
        this.sortArmyRegiment(i);
        this.updateArmy(true);
        this.updateMorale();
        this.setArmyGeneral(null);
    }
    
    public ArmyDivision(final int nCivID, final int nProvinceID, final List<ArmyRegiment> nArmyRegiment, final ArmyGeneral armyGeneral) {
        this.lArmyRegiment = new ArrayList<ArmyRegiment>();
        this.iArmyRegimentSize = 0;
        this.armyGeneral = null;
        this.inRetreat = false;
        this.fMaintenanceCost = 0.0f;
        this.fAverageSpeed = 1.0f;
        this.fMorale = 0.0f;
        this.fMoraleDraw = 0;
        this.inMovement = false;
        this.inBattle = false;
        this.addedToBattle = false;
        this.iArmy = 0;
        this.updateMorale = false;
        this.iStack = 0;
        this.civID = nCivID;
        this.provinceID = nProvinceID;
        this.iArmyOfProvinceID = nProvinceID;
        this.key = CFG.extraRandomTag();
        this.sortArmyRegiment(nArmyRegiment);
        this.updateArmy(true);
        this.updateMorale();
        this.setArmyGeneral(armyGeneral);
    }
    
    public ArmyDivision(final SaveGameManager.Save_Provinces_ArmyDivision armyDivision) {
        this.lArmyRegiment = new ArrayList<ArmyRegiment>();
        this.iArmyRegimentSize = 0;
        this.armyGeneral = null;
        this.inRetreat = false;
        this.fMaintenanceCost = 0.0f;
        this.fAverageSpeed = 1.0f;
        this.fMorale = 0.0f;
        this.fMoraleDraw = 0;
        this.inMovement = false;
        this.inBattle = false;
        this.addedToBattle = false;
        this.iArmy = 0;
        this.updateMorale = false;
        this.iStack = 0;
        this.civID = armyDivision.c;
        this.provinceID = armyDivision.p;
        this.iArmyOfProvinceID = armyDivision.p;
        this.key = armyDivision.k;
        this.inRetreat = armyDivision.t;
        this.armyGeneral = armyDivision.g;
        final ArrayList<ArmyRegiment> nArmyRegiment = new ArrayList<ArmyRegiment>();
        for (int jSize = armyDivision.r.size(), j = 0; j < jSize; ++j) {
            nArmyRegiment.add(new ArmyRegiment(armyDivision.r.get(j)));
        }
        this.sortArmyRegiment(nArmyRegiment);
        this.updateArmy(true);
        this.updateMorale();
    }
    
    public final void sortArmyRegiment(final List<ArmyRegiment> nArmyRegiment) {
        this.lArmyRegiment.clear();
        while (nArmyRegiment.size() > 0) {
            int addID = 0;
            for (int iSize = nArmyRegiment.size(), i = 1; i < iSize; ++i) {
                if (ArmyManager.lUnitsTypes.get(nArmyRegiment.get(i).uID).Line < ArmyManager.lUnitsTypes.get(nArmyRegiment.get(addID).uID).Line) {
                    addID = i;
                }
                else if (ArmyManager.lUnitsTypes.get(nArmyRegiment.get(i).uID).Line == ArmyManager.lUnitsTypes.get(nArmyRegiment.get(addID).uID).Line) {
                    if (nArmyRegiment.get(i).uID < nArmyRegiment.get(addID).uID) {
                        addID = i;
                    }
                    else if (nArmyRegiment.get(i).uID == nArmyRegiment.get(addID).uID) {
                        if (nArmyRegiment.get(i).aID < nArmyRegiment.get(addID).aID) {
                            addID = i;
                        }
                    }
                }
            }
            this.lArmyRegiment.add(nArmyRegiment.get(addID));
            nArmyRegiment.remove(addID);
        }
        this.iArmyRegimentSize = this.lArmyRegiment.size();
    }
    
    public final void sortArmyRegiment(final int numberOfUnits) {
        this.lArmyRegiment.clear();
        for (int i = 0; i < numberOfUnits; ++i) {
            final ArmyRegiment newRegiment = new ArmyRegiment();
            newRegiment.uID = 1;
            newRegiment.aID = Game.getProvince(this.provinceID).getArmyKeyID(this.key);
            newRegiment.num = 1000;
            this.lArmyRegiment.add(newRegiment);
        }
        this.iArmyRegimentSize = this.lArmyRegiment.size();
    }
    
    public final void removeRegiment(final int i) {
        this.lArmyRegiment.remove(i);
        this.iArmyRegimentSize = this.lArmyRegiment.size();
        this.updateArmy(false);
    }
    
    public final boolean removeRegiment(final String key, final boolean bManpowerRecoveryFromADisbandedArmy, final int disbandCivID) {
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            if (this.lArmyRegiment.get(i).key.equals(key)) {
                if (bManpowerRecoveryFromADisbandedArmy) {
                    Game.getCiv(disbandCivID).setManpower(Game.getCiv(disbandCivID).fManpower + (int)Math.max(0.0f, this.lArmyRegiment.get(i).num * Game.getManpowerRecoveryFromADisbandedArmy(disbandCivID)));
                }
                this.lArmyRegiment.remove(i);
                this.iArmyRegimentSize = this.lArmyRegiment.size();
                this.updateArmy(false);
                this.updateMorale();
                return true;
            }
        }
        return false;
    }
    
    public final void addRegiment(final ArmyRegiment nArmyRegiment) {
        final ArrayList<ArmyRegiment> tempArmyRegiment = new ArrayList<ArmyRegiment>();
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            tempArmyRegiment.add(this.lArmyRegiment.get(i));
        }
        tempArmyRegiment.add(nArmyRegiment);
        this.sortArmyRegiment(tempArmyRegiment);
        this.updateArmy(false);
        this.updateMorale();
    }
    
    public final void updateRegiment(final List<ArmyRegiment> nArmyRegiment) {
        final ArrayList<ArmyRegiment> tempArmyRegiment = new ArrayList<ArmyRegiment>();
        for (int iSize = nArmyRegiment.size(), i = 0; i < iSize; ++i) {
            tempArmyRegiment.add(nArmyRegiment.get(i));
        }
        this.sortArmyRegiment(tempArmyRegiment);
        this.updateArmy(true);
        this.updateMorale();
        this.updateSpeed();
    }
    
    public final void updateMaintenanceCost() {
        this.fMaintenanceCost = 0.0f;
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            this.fMaintenanceCost += ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).MaintenanceCost;
        }
        Game.gameThread.addCivUpdateArmyMaintenance(this.civID);
    }
    
    public final void updateMorale_Regiments(final float maxMorale, final float moraleRecovery) {
        this.updateMorale = false;
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            if (this.lArmyRegiment.get(i).mo != maxMorale) {
                this.lArmyRegiment.get(i).mo = Math.min(maxMorale, this.lArmyRegiment.get(i).mo + moraleRecovery);
                this.updateMorale = true;
            }
        }
        if (this.updateMorale) {
            this.updateMorale();
        }
    }
    
    public final void updateMorale() {
        this.fMorale = 0.0f;
        for (int i = this.iArmyRegimentSize - 1; i >= 0; --i) {
            this.fMorale += this.lArmyRegiment.get(i).mo;
        }
        this.fMorale /= this.iArmyRegimentSize;
        this.fMoraleDraw = Math.max(0, Math.min(GameValues.inGame.ARMY_LEFT_IMAGES, (int)(this.fMorale * 10.0f)));
    }
    
    public final float updateReinforce(final int maxReinforce, final float maxRegimentSize) {
        float reinforcedManpower = 0.0f;
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            final int neededReinforce = Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE - this.lArmyRegiment.get(i).num;
            if (neededReinforce > 0) {
                final int actualReinforce = Math.min(neededReinforce, maxReinforce);
                reinforcedManpower += this.getReinforceCost(actualReinforce, this.lArmyRegiment.get(i).uID, this.lArmyRegiment.get(i).aID, maxRegimentSize);
                final ArmyRegiment armyRegiment = this.lArmyRegiment.get(i);
                armyRegiment.num += actualReinforce;
            }
        }
        this.updateArmy(false);
        return reinforcedManpower;
    }
    
    public float getReinforceCost(final int nReinforce, final int uID, final int aID, final float maxRegimentSize) {
        return ArmyManager.lArmy.get(uID).get(aID).Cost * (nReinforce / maxRegimentSize) * GameValues.army.REINFORCE_ARMY_COST_MODIFIER;
    }
    
    public final void updateSpeed() {
        this.fAverageSpeed = 0.0f;
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            this.fAverageSpeed += ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).MovementSpeed;
        }
        this.fAverageSpeed /= this.iArmyRegimentSize;
    }
    
    public final void updateArmy(final boolean updateShift) {
        this.iArmy = 0;
        this.fAverageSpeed = 0.0f;
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            this.iArmy += this.lArmyRegiment.get(i).num;
            this.fAverageSpeed += ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).MovementSpeed;
        }
        this.fAverageSpeed /= this.iArmyRegimentSize;
        this.iStack = 0;
        this.updateMaintenanceCost();
        Renderer.addSimpleTask_ArmyWidth(new Renderer.SimpleTaskArmyText(this.key, this, updateShift));
    }
    
    public final void updateArmyWidth_Just(final boolean updateShift) {
        try {
            this.sArmy = ((!Game.FOG_OF_WAR || !GameValues.fog.HIDE_ARMIES || this.civID == Game.player.iCivID || DiplomacyManager.isAlly(this.civID, Game.player.iCivID) || DiplomacyManager.isAlly(Game.getProvince(this.provinceID).getCivID(), Game.player.iCivID)) ? ((this.iArmy < 1000) ? ("" + CFG.getPrecision2(this.iArmy / 1000.0f, 10)) : ("" + this.iArmy / 1000)) : GameValues.fog.TEXT_UNKNOWN_ARMIES);
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            if (!glyphLayout.setText(Renderer.fontArmy_GlyphLayout, this.sArmy)) {
                final byte by = this.iStack;
                this.iStack = (byte)(by + 1);
                if (by < 9) {
                    this.updateArmyWidth_Just(updateShift);
                    return;
                }
            }
            final int tX = (int)glyphLayout.width;
            if (!glyphLayout.setText(Renderer.fontArmy_GlyphLayout, (this.sArmy.length() > 2 && !this.sArmy.contains(".")) ? this.sArmy : "999")) {
                final byte by2 = this.iStack;
                this.iStack = (byte)(by2 + 1);
                if (by2 < 9) {
                    this.updateArmyWidth_Just(updateShift);
                    return;
                }
            }
            this.iArmyWidth = (int)glyphLayout.width;
            this.iArmyExtraPosX = this.iArmyWidth / 2 - tX / 2;
            if (updateShift) {
                this.iShiftX = this.defaultShiftX();
                this.iShiftY = this.defaultShiftY();
                this.iShiftX_Scaled = 0;
                this.iShiftY_Scaled = 0;
                Game.getProvince(this.provinceID).updateArmyPosY();
            }
        }
        catch (final Exception ex) {
            final byte by = this.iStack;
            this.iStack = (byte)(by + 1);
            if (by < 9) {
                this.updateArmyWidth_Just(updateShift);
                return;
            }
            CFG.exceptionStack(ex);
            this.iArmyWidth = CFG.PADDING;
        }
    }
    
    public final int defaultShiftX() {
        return -ProvinceDrawArmy.getArmyWidth(this.iArmyWidth) / 2;
    }
    
    public final int defaultShiftY() {
        return -ProvinceDrawArmy.getArmyHeight() / 2;
    }
    
    public final float getArmyMovementSpeed() {
        return Math.max(0.1f, this.fAverageSpeed * (1.0f + Game.getCiv(this.civID).getWarWeariness() * GameValues.warWeariness.WW_ARMY_MOVEMENT_SPEED_PER_POINT + Game.getCiv(this.civID).civBonuses.ArmyMovementSpeed / 100.0f));
    }
    
    public final float getCivilizationRanking_ArmyScore() {
        float out = 0.0f;
        try {
            for (int iSize = this.lArmyRegiment.size(), i = 0; i < iSize; ++i) {
                out += GameValues.civRank.CIV_SCORE_MAX_PER_REGIMENT * (ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).getAttack() + ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).getDefense()) / CivilizationRanking.bestArmyAttackDefense;
            }
        }
        catch (final Exception ex) {}
        return out;
    }
    
    public final float getSiegeProgressPerDay() {
        float out = 0.0f;
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            out += ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).SiegeProgress * this.lArmyRegiment.get(i).num / Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
        }
        return out * (1.0f + Game.getCiv(this.civID).civBonuses.SiegeEffectiveness + Game.getCiv(this.civID).getWarWeariness() * GameValues.warWeariness.WW_SIEGE_EFFECTIVENESS_PER_POINT);
    }
    
    public final void setInMovement(final boolean inMovement) {
        if (!(this.inMovement = inMovement)) {
            this.inRetreat = false;
        }
        else {
            this.inBattle = false;
        }
    }
    
    public final int getNumberOfSettlers() {
        int out = 0;
        for (int i = this.iArmyRegimentSize - 1; i >= 0; --i) {
            if (ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).isSettler) {
                out += this.lArmyRegiment.get(i).num;
            }
        }
        return out;
    }
    
    public final void removeAllSettlers() {
        int tRemoved = 0;
        for (int i = this.iArmyRegimentSize - 1; i >= 0; --i) {
            if (ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).isSettler) {
                this.lArmyRegiment.remove(i);
                if (++tRemoved >= GameValues.colonization.COLONIZATION_MAX_SETTLERS) {
                    break;
                }
            }
        }
        this.iArmyRegimentSize = this.lArmyRegiment.size();
        this.updateArmy(false);
    }
    
    public final void removeRegiment_ScenarioEditor(final int unitTypeID, final int armyID) {
        for (int i = this.iArmyRegimentSize - 1; i >= 0; --i) {
            if (this.lArmyRegiment.get(i).uID == unitTypeID && this.lArmyRegiment.get(i).aID == armyID) {
                this.lArmyRegiment.remove(i);
                this.iArmyRegimentSize = this.lArmyRegiment.size();
                this.updateArmy(false);
                return;
            }
        }
    }
    
    public final void setArmyGeneral(final ArmyGeneral general) {
        this.armyGeneral = general;
        if (general == null) {
            Game.getCiv(this.civID).armiesWithoutGenerals.addArmyKey(this.key);
        }
        else {
            Game.getCiv(this.civID).armiesWithoutGenerals.removeArmyKey(this.key);
        }
    }
    
    public AI_Army_Composition getArmyComposition() {
        final AI_Army_Composition out = new AI_Army_Composition();
        for (int i = 0; i < this.iArmyRegimentSize; ++i) {
            if (ArmyManager.lUnitsTypes.get(this.lArmyRegiment.get(i).uID).Line == 0) {
                final AI_Army_Composition a\u0131_Army_Composition = out;
                ++a\u0131_Army_Composition.numFirstLine;
            }
            else if (ArmyManager.lUnitsTypes.get(this.lArmyRegiment.get(i).uID).Line == 1) {
                final AI_Army_Composition a\u0131_Army_Composition2 = out;
                ++a\u0131_Army_Composition2.numFlank;
            }
            else if (ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).SiegeUnit) {
                final AI_Army_Composition a\u0131_Army_Composition3 = out;
                ++a\u0131_Army_Composition3.numSiege;
            }
            else if (!ArmyManager.lArmy.get(this.lArmyRegiment.get(i).uID).get(this.lArmyRegiment.get(i).aID).isSettler) {
                final AI_Army_Composition a\u0131_Army_Composition4 = out;
                ++a\u0131_Army_Composition4.numSupport;
            }
        }
        out.numOfRegiments = out.numFirstLine + out.numFlank + out.numSupport + out.numSiege;
        return out;
    }
    
    public final float getPercOfTotalUnits() {
        return this.iArmy / (float)(Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * this.iArmyRegimentSize);
    }
}
