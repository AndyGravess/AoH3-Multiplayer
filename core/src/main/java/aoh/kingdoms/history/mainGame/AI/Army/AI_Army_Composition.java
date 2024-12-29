// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.battles.BattleManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_Army_Composition
{
    public int numFirstLine;
    public int numFlank;
    public int numSupport;
    public int numSiege;
    public int numOfRegiments;
    
    public AI_Army_Composition() {
        this.numFirstLine = 0;
        this.numFlank = 0;
        this.numSupport = 0;
        this.numSiege = 0;
        this.numOfRegiments = 0;
    }
    
    public AI_Army_Composition(final AI_Army_Composition armyComposition) {
        this.numFirstLine = 0;
        this.numFlank = 0;
        this.numSupport = 0;
        this.numSiege = 0;
        this.numOfRegiments = 0;
        this.numFirstLine = armyComposition.numFirstLine;
        this.numFlank = armyComposition.numFlank;
        this.numSupport = armyComposition.numSupport;
        this.numSiege = armyComposition.numSiege;
        this.updateNumOfRegiments();
    }
    
    public AI_Army_Composition(final int civID, int numOfRegiments) {
        this.numFirstLine = 0;
        this.numFlank = 0;
        this.numSupport = 0;
        this.numSiege = 0;
        this.numOfRegiments = 0;
        int numNotUsed = 0;
        final Civilization civ = Game.getCiv(civID);
        final int maxBattleWidth = BattleManager.getBattleWidth(civID);
        this.numSupport = Math.min(maxBattleWidth, numOfRegiments / 2);
        numOfRegiments -= this.numSupport;
        this.numFirstLine = (int)Math.ceil(numOfRegiments * civ.aiCiv.armies_FirstLine_Perc);
        this.numFlank = (int)(numOfRegiments * civ.aiCiv.armies_FlankLine_Perc);
        if (civ.unitsBest_FirstLineSize <= 0) {
            numNotUsed += this.numFirstLine;
            this.numFirstLine = 0;
        }
        if (civ.unitsBest_FlankSize <= 0) {
            numNotUsed += this.numFlank;
            this.numFlank = 0;
        }
        if (civ.unitsBest_SupportSize <= 0) {
            numNotUsed += this.numSupport;
            this.numSupport = 0;
        }
        if (numNotUsed > 0) {
            if (civ.unitsBest_SupportSize > 0) {
                if (civ.unitsBest_FirstLineSize > 0) {
                    this.numFirstLine += (int)Math.ceil(numNotUsed / 2.0f);
                    this.numSupport += (int)Math.floor(numNotUsed / 2.0f);
                }
                else if (civ.unitsBest_FlankSize > 0) {
                    this.numFlank += (int)Math.ceil(numNotUsed / 2.0f);
                    this.numSupport += (int)Math.floor(numNotUsed / 2.0f);
                }
            }
            else if (civ.unitsBest_FirstLineSize > 0) {
                this.numFirstLine += numNotUsed;
            }
            else if (civ.unitsBest_FlankSize > 0) {
                this.numFlank += numNotUsed;
            }
        }
        if (civ.unitsBest_SiegeSize > 0 && this.numSupport > civ.aiCiv.armies_SiegeWeaponPerSupportUnits) {
            this.numSiege = Math.min(this.numSupport / civ.aiCiv.armies_SiegeWeaponPerSupportUnits, civ.aiCiv.armies_SiegeWeapon_Max);
            this.numSupport -= this.numSiege;
        }
        this.updateNumOfRegiments();
    }
    
    public void updateNumOfRegiments() {
        this.numOfRegiments = this.numFirstLine + this.numFlank + this.numSupport + this.numSiege;
    }
    
    public void LOG(final String text) {
        CFG.LOG(text + ": " + this.numOfRegiments);
        CFG.LOG("numFirstLine: " + this.numFirstLine);
        CFG.LOG("numFlank: " + this.numFlank);
        CFG.LOG("numSupport: " + this.numSupport);
        CFG.LOG("numSiege: " + this.numSiege);
    }
}
