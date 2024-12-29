// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.battles;

import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_BattleStart;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class BattleManager
{
    public static final int BUTTON_PADDING = 1;
    public static int[] FILL_ORDER;
    public List<Battle> lBattle;
    public int iBattleSize;
    public static int BATTLE_IMG_ID;
    private static long ANIMATION_TIME;
    private static final long ANIMATION_TIMER = 110L;
    private static int imgStepID;
    private static boolean reversedAnimation;
    
    public BattleManager() {
        this.lBattle = Collections.synchronizedList(new ArrayList<Battle>());
        this.iBattleSize = 0;
        BattleManager.FILL_ORDER = new int[GameValues.battle.BATTLE_MAX_BATTLE_WIDTH];
        int i = 0;
        int j = 0;
        while (i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH) {
            BattleManager.FILL_ORDER[i] = GameValues.battle.BATTLE_MAX_BATTLE_WIDTH / 2 + j++;
            i += 2;
        }
        i = 1;
        j = 1;
        while (i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH) {
            BattleManager.FILL_ORDER[i] = GameValues.battle.BATTLE_MAX_BATTLE_WIDTH / 2 - j++;
            i += 2;
        }
    }
    
    public final void addBattle(final Battle nBattle) {
        try {
            this.lBattle.add(nBattle);
            this.iBattleSize = this.lBattle.size();
            final int rand = Game.oR.nextInt(Math.max(1, Game.aiValuesArmies.BATTLE_START_MIN_REGIMENTS_RANDOM));
            final int numOfUnits = Math.min(Game.battleManager.getBattle(this.iBattleSize - 1).defendingArmy.numOfUnits, Game.battleManager.getBattle(this.iBattleSize - 1).attackingArmy.numOfUnits);
            if (numOfUnits <= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * (rand + Game.aiValuesArmies.BATTLE_START_MIN_REGIMENTS)) {
                AI_BattleStart.battleStarted_1(this.iBattleSize - 1, nBattle.provinceID);
            }
            else if (numOfUnits <= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * (rand + Game.aiValuesArmies.BATTLE_START_MIN_REGIMENTS_2)) {
                AI_BattleStart.battleStarted_2(this.iBattleSize - 1, nBattle.provinceID);
            }
            else if (Game.oR.nextInt(100) < Game.aiValuesArmies.BATTLE_START_MOVE_ARMIES_RANDOM_CHANCE) {
                AI_BattleStart.battleStarted(this.iBattleSize - 1, nBattle.provinceID, 2 + Game.oR.nextInt(5));
            }
            else if (numOfUnits <= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * (rand + Game.aiValuesArmies.BATTLE_START_MIN_REGIMENTS_3)) {
                AI_BattleStart.battleStarted(this.iBattleSize - 1, nBattle.provinceID, 3);
            }
            else if (numOfUnits <= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * (rand + Game.aiValuesArmies.BATTLE_START_MIN_REGIMENTS_4)) {
                AI_BattleStart.battleStarted(this.iBattleSize - 1, nBattle.provinceID, 4);
            }
            else if (numOfUnits <= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * (rand + Game.aiValuesArmies.BATTLE_START_MIN_REGIMENTS_5)) {
                AI_BattleStart.battleStarted(this.iBattleSize - 1, nBattle.provinceID, 5);
            }
            else {
                AI_BattleStart.battleStarted(this.iBattleSize - 1, nBattle.provinceID, 6);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeBattle(final int id) {
        try {
            this.lBattle.get(id).defendingArmy.updateAllArmiesNotInBattle(this.lBattle.get(id).provinceID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.lBattle.get(id).attackingArmy.updateAllArmiesNotInBattle(this.lBattle.get(id).provinceID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.lBattle.get(id).defendingArmy.removeCivsInBattle(this.lBattle.get(id).key);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.lBattle.get(id).attackingArmy.removeCivsInBattle(this.lBattle.get(id).key);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.lBattle.remove(id);
            this.iBattleSize = this.lBattle.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void joinBattle(final int iProvinceID, final ArmyDivision nArmyDivision, final int iAgainstCivID) {
        try {
            for (int i = 0; i < this.iBattleSize; ++i) {
                if (this.lBattle.get(i).provinceID == iProvinceID) {
                    if (this.lBattle.get(i).defendingArmy.isInBattleCiv(iAgainstCivID)) {
                        this.lBattle.get(i).attackingArmy.joinBattle(nArmyDivision, this.lBattle.get(i).iMaxBattleWidth, this.lBattle.get(i).key);
                    }
                    else if (this.lBattle.get(i).attackingArmy.isInBattleCiv(iAgainstCivID)) {
                        this.lBattle.get(i).defendingArmy.joinBattle(nArmyDivision, this.lBattle.get(i).iMaxBattleWidth, this.lBattle.get(i).key);
                    }
                    else if (this.lBattle.get(i).defendingArmy.isInBattleCiv(nArmyDivision.civID)) {
                        this.lBattle.get(i).defendingArmy.joinBattle(nArmyDivision, this.lBattle.get(i).iMaxBattleWidth, this.lBattle.get(i).key);
                    }
                    else if (this.lBattle.get(i).attackingArmy.isInBattleCiv(nArmyDivision.civID)) {
                        this.lBattle.get(i).attackingArmy.joinBattle(nArmyDivision, this.lBattle.get(i).iMaxBattleWidth, this.lBattle.get(i).key);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public int getBattleID(final String key) {
        for (int i = 0; i < this.iBattleSize; ++i) {
            if (this.lBattle.get(i).key.equals(key)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getBattleID(final int nProvinceID, final String key) {
        for (int i = 0; i < this.iBattleSize; ++i) {
            if (this.lBattle.get(i).provinceID == nProvinceID && this.lBattle.get(i).key.equals(key)) {
                return i;
            }
        }
        return -1;
    }
    
    public Battle getBattle(final int i) {
        return this.lBattle.get(i);
    }
    
    public Battle getBattle(final String key) {
        for (int i = 0; i < this.iBattleSize; ++i) {
            if (this.lBattle.get(i).key.equals(key)) {
                return this.lBattle.get(i);
            }
        }
        return null;
    }
    
    public int getBattleSize() {
        return this.iBattleSize;
    }
    
    public void draw(final SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
            this.drawBattles(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
        else if (Game.mapTouchManager.selectMode) {
            this.drawBattles(oSB, 1.0f);
        }
        else if (ProvinceDrawArmy.drawHideAnimation && Game.mapScale.getCurrentScale() >= ProvinceDrawArmy.DRAW_ARMY_MIN_SCALE_ANIMATION) {
            this.drawBattles(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
    }
    
    private final void drawBattles(final SpriteBatch oSB, final float fAlpha) {
        this.updateBattleIcon();
        try {
            for (int i = 0; i < this.iBattleSize; ++i) {
                Game.getProvince(this.getBattle(i).provinceID).iBattlesInProvince = 0;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (Game.FOG_OF_WAR) {
                for (int i = 0; i < this.iBattleSize; ++i) {
                    if (Game.getProvince(this.getBattle(i).provinceID).getFogDrawArmy()) {
                        this.getBattle(i).drawBattle_Just(oSB, fAlpha);
                    }
                }
            }
            else {
                for (int i = 0; i < this.iBattleSize; ++i) {
                    this.getBattle(i).drawBattle_Just(oSB, fAlpha);
                }
            }
        }
        catch (final Exception ex2) {}
    }
    
    private final void updateBattleIcon() {
        if (CFG.currentTimeMillis - 110L > BattleManager.ANIMATION_TIME) {
            BattleManager.ANIMATION_TIME = CFG.currentTimeMillis;
            if (BattleManager.reversedAnimation) {
                --BattleManager.imgStepID;
                if (BattleManager.imgStepID <= 0) {
                    BattleManager.imgStepID = 0;
                    BattleManager.reversedAnimation = false;
                }
            }
            else {
                ++BattleManager.imgStepID;
                if (BattleManager.imgStepID >= 7) {
                    BattleManager.imgStepID = 7;
                    BattleManager.reversedAnimation = true;
                }
            }
            switch (BattleManager.imgStepID % 8) {
                case 0: {
                    BattleManager.BATTLE_IMG_ID = Images.battleIcon5;
                    break;
                }
                case 1: {
                    BattleManager.BATTLE_IMG_ID = Images.battleIcon4;
                    break;
                }
                case 2: {
                    BattleManager.BATTLE_IMG_ID = Images.battleIcon3;
                    break;
                }
                case 3: {
                    BattleManager.BATTLE_IMG_ID = Images.battleIcon2;
                    break;
                }
                case 4: {
                    BattleManager.BATTLE_IMG_ID = Images.battleIcon1;
                    break;
                }
                default: {
                    BattleManager.BATTLE_IMG_ID = Images.battleIcon0;
                    break;
                }
            }
        }
    }
    
    public static int getBattleWidth(final int iCivID) {
        return Math.min(GameValues.battle.BATTLE_MAX_BATTLE_WIDTH, GameValues.battle.BATTLE_MIN_BATTLE_WIDTH + Game.getCiv(iCivID).civBonuses.BattleWidth);
    }
    
    public final void getJakowskiLine() {
    }
    
    public boolean isBattleInProvince(final int iProvinceID) {
        try {
            for (int i = this.iBattleSize - 1; i >= 0; --i) {
                if (this.getBattle(i).provinceID == iProvinceID) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public boolean isArmyInBattle(final int iProvinceID, final String key) {
        try {
            for (int i = this.iBattleSize - 1; i >= 0; --i) {
                if (this.getBattle(i).provinceID == iProvinceID && this.getBattle(i).isInBattle(key)) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public String getArmyBattleKey(final int iProvinceID, final String armyKey) {
        try {
            for (int i = this.iBattleSize - 1; i >= 0; --i) {
                if (this.getBattle(i).provinceID == iProvinceID && this.getBattle(i).isInBattle(armyKey)) {
                    return this.getBattle(i).key;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return "";
    }
    
    public void stopAllBattles_PeaceTreaty(final String warKey) {
        try {
            for (int i = this.iBattleSize - 1; i >= 0; --i) {
                try {
                    if (WarManager.getWarKey(this.lBattle.get(i).attackingArmy.lCivs.get(0), this.lBattle.get(i).defendingArmy.lCivs.get(0)).equals(warKey)) {
                        this.lBattle.get(i).updateBattle_Summary(false);
                        this.lBattle.remove(i);
                        this.iBattleSize = this.lBattle.size();
                    }
                }
                catch (final Exception ex2) {}
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void clearData() {
        this.lBattle.clear();
        this.iBattleSize = 0;
    }
    
    static {
        BattleManager.BATTLE_IMG_ID = 0;
        BattleManager.ANIMATION_TIME = 0L;
        BattleManager.imgStepID = 0;
        BattleManager.reversedAnimation = false;
    }
}
