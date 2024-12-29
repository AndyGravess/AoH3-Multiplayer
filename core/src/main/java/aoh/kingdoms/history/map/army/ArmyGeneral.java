// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;

public class ArmyGeneral
{
    public String key;
    public int c;
    public String n;
    public int y;
    public int m;
    public int d;
    public int g;
    public String sI;
    private int at;
    private int df;
    private int e;
    
    public ArmyGeneral() {
        this.n = "";
        this.y = 0;
        this.m = 0;
        this.d = 0;
        this.g = 0;
        this.sI = null;
        this.at = 0;
        this.df = 0;
    }
    
    public ArmyGeneral(final String sName, final int imageID, final int iAttack, final int iDefense, final int iYearOfBirth, final int iCivID, final String sIMG) {
        this.n = "";
        this.y = 0;
        this.m = 0;
        this.d = 0;
        this.g = 0;
        this.sI = null;
        this.at = 0;
        this.df = 0;
        this.n = sName;
        this.g = imageID;
        this.sI = sIMG;
        this.at = iAttack;
        this.df = iDefense;
        this.y = iYearOfBirth;
        this.c = iCivID;
        this.m = 1 + Game.oR.nextInt(12);
        this.d = 1 + Game.oR.nextInt(Game_Calendar.getNumOfDaysInMonth(this.m));
        this.e = GameValues.generals.COMBAT_EXPERIENCE_NEW_GENERAL_MIN + Game.oR.nextInt(GameValues.generals.COMBAT_EXPERIENCE_NEW_GENERAL_RANDOM);
        this.key = CFG.extraRandomTag();
    }
    
    public int getAttack() {
        return this.at + Game.getCiv(this.c).civBonuses.GeneralAttack;
    }
    
    public int getDefense() {
        return this.df + Game.getCiv(this.c).civBonuses.GeneralDefense;
    }
    
    public int getCombatExperience() {
        return this.e;
    }
    
    public void addCombatExperience(final int value) {
        this.e += value;
        if (this.e >= GameValues.generals.COMBAT_EXPERIENCE_MAX) {
            if (Game.oR.nextInt(100) < GameValues.generals.COMBAT_EXPERIENCE_INCREASE_ATTACK_CHANCE && this.at < GameValues.generals.COMBAT_EXPERIENCE_MAX_ATTACK) {
                ++this.at;
            }
            else if (this.df < GameValues.generals.COMBAT_EXPERIENCE_MAX_DEFENSE) {
                ++this.df;
            }
            this.e = Math.min(this.e - GameValues.generals.COMBAT_EXPERIENCE_MAX, GameValues.generals.COMBAT_EXPERIENCE_MAX / 2);
        }
    }
}
