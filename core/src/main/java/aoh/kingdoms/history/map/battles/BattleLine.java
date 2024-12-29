// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.battles;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.List;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;

public class BattleLine
{
    public String text;
    public int textW;
    public int armyExtraPosX;
    public int numOfUnits;
    public int numOfUnitsOnBattlefield;
    public int iCasualties;
    public int iRetreated;
    public int inReserve;
    public float fMorale;
    public int diceRollGeneral;
    public int iLastAttackRoundID;
    public int iCivID;
    public ArmyGeneral armyGeneral;
    public List<BattleRegiment> firstLine;
    public List<BattleRegiment> secondLine;
    public List<BattleRegiment> reserveFirstLine;
    public List<BattleRegiment> reserveSecondLine;
    public List<BattleRegiment> defeated;
    public List<Integer> lCivs;
    public List<Integer> lCasualties;
    public List<String> lDefeatedArmyDivisionKeys;
    public List<String> armyDivisionsKeys;
    
    public void updateLoaded_Load() {
        try {
            for (int i = this.firstLine.size() - 1; i >= 0; --i) {
                if (this.firstLine.get(i) != null) {
                    this.firstLine.get(i).l = 1;
                }
            }
            for (int i = this.secondLine.size() - 1; i >= 0; --i) {
                if (this.secondLine.get(i) != null) {
                    this.secondLine.get(i).l = 1;
                }
            }
            for (int i = this.reserveFirstLine.size() - 1; i >= 0; --i) {
                if (this.reserveFirstLine.get(i) != null) {
                    this.reserveFirstLine.get(i).l = 1;
                }
            }
            for (int i = this.reserveSecondLine.size() - 1; i >= 0; --i) {
                if (this.reserveSecondLine.get(i) != null) {
                    this.reserveSecondLine.get(i).l = 1;
                }
            }
            for (int i = this.defeated.size() - 1; i >= 0; --i) {
                if (this.defeated.get(i) != null) {
                    this.defeated.get(i).l = 1;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void updateInBattle_Load(final int provinceID) {
        final List<String> armyKeys = this.getBattleArmiesKeys();
        for (int i = armyKeys.size() - 1; i >= 0; --i) {
            final int armyID = Game.getProvince(provinceID).getArmyKeyID(armyKeys.get(i));
            if (armyID >= 0) {
                Game.getProvince(provinceID).getArmy(armyID).inBattle = true;
            }
        }
    }
    
    public List<String> getBattleArmiesKeys() {
        final List<String> armyKeys = new ArrayList<String>();
        for (int i = this.firstLine.size() - 1; i >= 0; --i) {
            if (this.firstLine.get(i) != null && !armyKeys.contains(this.firstLine.get(i).aK)) {
                armyKeys.add(this.firstLine.get(i).aK);
            }
        }
        for (int i = this.secondLine.size() - 1; i >= 0; --i) {
            if (this.secondLine.get(i) != null && !armyKeys.contains(this.secondLine.get(i).aK)) {
                armyKeys.add(this.secondLine.get(i).aK);
            }
        }
        for (int i = this.reserveFirstLine.size() - 1; i >= 0; --i) {
            if (!armyKeys.contains(this.reserveFirstLine.get(i).aK)) {
                armyKeys.add(this.reserveFirstLine.get(i).aK);
            }
        }
        for (int i = this.reserveSecondLine.size() - 1; i >= 0; --i) {
            if (!armyKeys.contains(this.reserveSecondLine.get(i).aK)) {
                armyKeys.add(this.reserveSecondLine.get(i).aK);
            }
        }
        for (int i = this.defeated.size() - 1; i >= 0; --i) {
            if (!armyKeys.contains(this.defeated.get(i).aK)) {
                armyKeys.add(this.defeated.get(i).aK);
            }
        }
        return armyKeys;
    }
    
    public BattleLine() {
        this.numOfUnits = 0;
        this.numOfUnitsOnBattlefield = 0;
        this.iCasualties = 0;
        this.iRetreated = 0;
        this.inReserve = 0;
        this.fMorale = 0.0f;
        this.diceRollGeneral = 0;
        this.iLastAttackRoundID = 0;
        this.iCivID = 0;
        this.armyGeneral = null;
        this.firstLine = new ArrayList<BattleRegiment>();
        this.secondLine = new ArrayList<BattleRegiment>();
        this.reserveFirstLine = new ArrayList<BattleRegiment>();
        this.reserveSecondLine = new ArrayList<BattleRegiment>();
        this.defeated = new ArrayList<BattleRegiment>();
        this.lCivs = new ArrayList<Integer>();
        this.lCasualties = new ArrayList<Integer>();
        this.lDefeatedArmyDivisionKeys = new ArrayList<String>();
        this.armyDivisionsKeys = new ArrayList<String>();
    }
    
    public BattleLine(final List<ArmyDivision> lineArmy, final int maxWidth, final int sideArmyBegin, final String battleKey) {
        this.numOfUnits = 0;
        this.numOfUnitsOnBattlefield = 0;
        this.iCasualties = 0;
        this.iRetreated = 0;
        this.inReserve = 0;
        this.fMorale = 0.0f;
        this.diceRollGeneral = 0;
        this.iLastAttackRoundID = 0;
        this.iCivID = 0;
        this.armyGeneral = null;
        this.firstLine = new ArrayList<BattleRegiment>();
        this.secondLine = new ArrayList<BattleRegiment>();
        this.reserveFirstLine = new ArrayList<BattleRegiment>();
        this.reserveSecondLine = new ArrayList<BattleRegiment>();
        this.defeated = new ArrayList<BattleRegiment>();
        this.lCivs = new ArrayList<Integer>();
        this.lCasualties = new ArrayList<Integer>();
        this.lDefeatedArmyDivisionKeys = new ArrayList<String>();
        this.armyDivisionsKeys = new ArrayList<String>();
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            this.firstLine.add(null);
            this.secondLine.add(null);
        }
        this.numOfUnits = 0;
        this.numOfUnitsOnBattlefield = 0;
        final List<BattleRegiment> tempLine0 = new ArrayList<BattleRegiment>();
        final List<BattleRegiment> tempLine2 = new ArrayList<BattleRegiment>();
        final List<BattleRegiment> tempLine3 = new ArrayList<BattleRegiment>();
        for (int j = 0, jSize = lineArmy.size(); j < jSize; ++j) {
            this.addCiv(lineArmy.get(j).civID, battleKey);
            for (int k = 0; k < lineArmy.get(j).iArmyRegimentSize; ++k) {
                if (lineArmy.get(j).lArmyRegiment.get(k).num > 0) {
                    this.numOfUnits += lineArmy.get(j).lArmyRegiment.get(k).num;
                    switch (ArmyManager.lUnitsTypes.get(lineArmy.get(j).lArmyRegiment.get(k).uID).Line) {
                        case 0: {
                            tempLine0.add(new BattleRegiment(lineArmy.get(j).civID, lineArmy.get(j).lArmyRegiment.get(k), lineArmy.get(j).key));
                            break;
                        }
                        case 1: {
                            tempLine2.add(new BattleRegiment(lineArmy.get(j).civID, lineArmy.get(j).lArmyRegiment.get(k), lineArmy.get(j).key));
                            break;
                        }
                        default: {
                            tempLine3.add(new BattleRegiment(lineArmy.get(j).civID, lineArmy.get(j).lArmyRegiment.get(k), lineArmy.get(j).key));
                            break;
                        }
                    }
                }
                else {
                    this.defeated.add(new BattleRegiment(lineArmy.get(j).civID, lineArmy.get(j).lArmyRegiment.get(k), lineArmy.get(j).key));
                }
            }
        }
        int firstAdded = 0;
        final int sideArmyWidth = Math.min(maxWidth - (int)(maxWidth * GameValues.battle.BATTLE_SIDES_RATIO), sideArmyBegin);
        while (firstAdded < maxWidth && (tempLine0.size() > 0 || tempLine2.size() > 0)) {
            final boolean sideArmy = firstAdded >= sideArmyWidth;
            if (!sideArmy && tempLine0.size() > 0) {
                this.firstLine.set(BattleManager.FILL_ORDER[firstAdded++], tempLine0.get(0));
                tempLine0.remove(0);
            }
            else if (tempLine2.size() > 0) {
                this.firstLine.set(BattleManager.FILL_ORDER[firstAdded++], tempLine2.get(0));
                tempLine2.remove(0);
            }
            else {
                if (tempLine0.size() <= 0) {
                    continue;
                }
                this.firstLine.set(BattleManager.FILL_ORDER[firstAdded++], tempLine0.get(0));
                tempLine0.remove(0);
            }
        }
        int secondAdded = 0;
        while (secondAdded < maxWidth && tempLine3.size() > 0) {
            if (tempLine3.size() > 0) {
                if (this.firstLine.get(BattleManager.FILL_ORDER[secondAdded]) == null) {
                    this.firstLine.set(BattleManager.FILL_ORDER[secondAdded], tempLine3.get(0));
                }
                else {
                    this.secondLine.set(BattleManager.FILL_ORDER[secondAdded++], tempLine3.get(0));
                }
                tempLine3.remove(0);
            }
        }
        while (tempLine0.size() > 0) {
            this.reserveFirstLine.add(tempLine0.get(0));
            tempLine0.remove(0);
        }
        while (tempLine2.size() > 0) {
            this.reserveFirstLine.add(tempLine2.get(0));
            tempLine2.remove(0);
        }
        while (tempLine3.size() > 0) {
            this.reserveSecondLine.add(tempLine3.get(0));
            tempLine3.remove(0);
        }
        int tRegID = 1;
        for (int l = 0; l < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++l) {
            if (this.firstLine.get(BattleManager.FILL_ORDER[l]) != null) {
                this.firstLine.get(BattleManager.FILL_ORDER[l]).rn = tRegID++;
                this.numOfUnitsOnBattlefield += this.firstLine.get(BattleManager.FILL_ORDER[l]).aR.num;
            }
        }
        for (int l = 0; l < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++l) {
            if (this.secondLine.get(BattleManager.FILL_ORDER[l]) != null) {
                this.secondLine.get(BattleManager.FILL_ORDER[l]).rn = tRegID++;
                this.numOfUnitsOnBattlefield += this.secondLine.get(BattleManager.FILL_ORDER[l]).aR.num;
            }
        }
        this.inReserve = 0;
        for (int l = 0, nReserveSize = this.reserveFirstLine.size(); l < nReserveSize; ++l) {
            this.reserveFirstLine.get(l).rn = tRegID++;
            this.inReserve += this.reserveFirstLine.get(l).aR.num;
        }
        for (int l = 0, nReserveSize = this.reserveSecondLine.size(); l < nReserveSize; ++l) {
            this.reserveSecondLine.get(l).rn = tRegID++;
            this.inReserve += this.reserveSecondLine.get(l).aR.num;
        }
        this.setText("" + CFG.getPrecision2(this.numOfUnits / 1000.0f, 10));
        this.updateAverageMoraleOnBattleField();
    }
    
    public final void joinBattle(final ArmyDivision armyDivision, final int iMaxBattleWidth, final String battleKey) {
        final List<BattleRegiment> tempLine0 = new ArrayList<BattleRegiment>();
        final List<BattleRegiment> tempLine2 = new ArrayList<BattleRegiment>();
        this.addCiv(armyDivision.civID, battleKey);
        armyDivision.inBattle = true;
        if (armyDivision.inMovement) {
            if (armyDivision.civID < 0) {
                Game.revolutionMoveUnits.updateMoveInBattle(armyDivision.key, armyDivision.inBattle);
            }
            else {
                Game.getCiv(armyDivision.civID).updateMoveInBattle(armyDivision.key, armyDivision.inBattle);
            }
        }
        if (this.armyGeneral == null && armyDivision.armyGeneral != null) {
            this.armyGeneral = armyDivision.armyGeneral;
        }
        if (armyDivision.armyGeneral != null) {
            armyDivision.armyGeneral.addCombatExperience(GameValues.generals.COMBAT_EXPERIENCE_JOIN_BATTLE);
        }
        for (int i = 0; i < armyDivision.iArmyRegimentSize; ++i) {
            this.numOfUnits += armyDivision.lArmyRegiment.get(i).num;
            switch (ArmyManager.lUnitsTypes.get(armyDivision.lArmyRegiment.get(i).uID).Line) {
                case 0:
                case 1: {
                    tempLine0.add(new BattleRegiment(armyDivision.civID, armyDivision.lArmyRegiment.get(i), armyDivision.key));
                    break;
                }
                default: {
                    tempLine2.add(new BattleRegiment(armyDivision.civID, armyDivision.lArmyRegiment.get(i), armyDivision.key));
                    break;
                }
            }
        }
        int tRegID = 1;
        while (tempLine0.size() > 0) {
            tempLine0.get(0).rn = tRegID++;
            this.inReserve += tempLine0.get(0).aR.num;
            this.reserveFirstLine.add(tempLine0.get(0));
            tempLine0.remove(0);
        }
        while (tempLine2.size() > 0) {
            tempLine2.get(0).rn = tRegID++;
            this.inReserve += tempLine2.get(0).aR.num;
            this.reserveSecondLine.add(tempLine2.get(0));
            tempLine2.remove(0);
        }
        try {
            for (int j = 0; j < iMaxBattleWidth && this.reserveFirstLine.size() > 0; ++j) {
                if (this.firstLine.get(BattleManager.FILL_ORDER[j]) == null) {
                    this.firstLine.set(BattleManager.FILL_ORDER[j], this.reserveFirstLine.get(0));
                    this.inReserve -= this.reserveFirstLine.get(0).aR.num;
                    this.numOfUnitsOnBattlefield += this.reserveFirstLine.get(0).aR.num;
                    this.reserveFirstLine.remove(0);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int j = 0; j < iMaxBattleWidth && this.reserveSecondLine.size() > 0; ++j) {
                if (this.secondLine.get(BattleManager.FILL_ORDER[j]) == null) {
                    this.secondLine.set(BattleManager.FILL_ORDER[j], this.reserveSecondLine.get(0));
                    this.inReserve -= this.reserveSecondLine.get(0).aR.num;
                    this.numOfUnitsOnBattlefield += this.reserveSecondLine.get(0).aR.num;
                    this.reserveSecondLine.remove(0);
                }
            }
            for (int j = 0; j < iMaxBattleWidth && this.reserveSecondLine.size() > 0; ++j) {
                if (this.firstLine.get(BattleManager.FILL_ORDER[j]) == null) {
                    this.firstLine.set(BattleManager.FILL_ORDER[j], this.reserveSecondLine.get(0));
                    this.inReserve -= this.reserveSecondLine.get(0).aR.num;
                    this.numOfUnitsOnBattlefield += this.reserveSecondLine.get(0).aR.num;
                    this.reserveSecondLine.remove(0);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.setText("" + CFG.getPrecision2(this.numOfUnits / 1000.0f, 10));
    }
    
    public void setText(final String sText) {
        this.text = sText;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontArmy_GlyphLayout, sText);
        final int tX = (int)glyphLayout.width;
        glyphLayout.setText(Renderer.fontArmy_GlyphLayout, (sText.length() > 2) ? sText : "999");
        this.textW = (int)glyphLayout.width;
        this.armyExtraPosX = Math.max(0, this.textW / 2 - tX / 2);
    }
    
    public void addCiv(final int iCivID, final String battleKey) {
        for (int i = 0, iSize = this.lCivs.size(); i < iSize; ++i) {
            if (this.lCivs.get(i) == iCivID) {
                return;
            }
        }
        this.lCivs.add(iCivID);
        Game.getCiv(iCivID).addInBattles(battleKey);
    }
    
    public void updateAllArmiesNotInBattle(final int provinceID) {
        final List<String> armyKeys = this.getBattleArmiesKeys();
        for (int i = armyKeys.size() - 1; i >= 0; --i) {
            try {
                final ArmyDivision armyDivision = Game.getProvince(provinceID).getArmy(armyKeys.get(i));
                if (armyDivision != null) {
                    armyDivision.inBattle = false;
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public void removeCivsInBattle(final String battleKey) {
        for (int i = 0, iSize = this.lCivs.size(); i < iSize; ++i) {
            Game.getCiv(this.lCivs.get(i)).removeInBattles(battleKey);
        }
    }
    
    public final void updateNumOfUnits() {
        this.numOfUnits = 0;
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.firstLine.get(i) != null) {
                this.numOfUnits += this.firstLine.get(i).aR.num;
            }
            if (this.secondLine.get(i) != null) {
                this.numOfUnits += this.secondLine.get(i).aR.num;
            }
        }
        for (int i = this.reserveFirstLine.size() - 1; i >= 0; --i) {
            this.numOfUnits += this.reserveFirstLine.get(i).aR.num;
        }
        for (int i = this.reserveSecondLine.size() - 1; i >= 0; --i) {
            this.numOfUnits += this.reserveSecondLine.get(i).aR.num;
        }
    }
    
    public final void updateBattle_Summary(final int iProvinceID, final boolean haveToRetreat, final int iRoundID, final boolean armyCanBeDestroyed) {
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.firstLine.get(i) != null) {
                this.firstLine.get(i).aR.num += this.firstLine.get(i).re;
                if (this.firstLine.get(i).l > 0) {
                    Game.getProvince(iProvinceID).updateArmy_AfterBattle(this.firstLine.get(i).aK, this.firstLine.get(i).aR.key, this.firstLine.get(i).aR.num, this.firstLine.get(i).aR.mo);
                }
            }
            if (this.secondLine.get(i) != null) {
                this.secondLine.get(i).aR.num += this.secondLine.get(i).re;
                if (this.secondLine.get(i).l > 0) {
                    Game.getProvince(iProvinceID).updateArmy_AfterBattle(this.secondLine.get(i).aK, this.secondLine.get(i).aR.key, this.secondLine.get(i).aR.num, this.secondLine.get(i).aR.mo);
                }
            }
        }
        for (int i = 0, iSize = this.defeated.size(); i < iSize; ++i) {
            this.defeated.get(i).aR.num = this.defeated.get(i).re;
            if (this.defeated.get(i).l > 0) {
                Game.getProvince(iProvinceID).updateArmy_AfterBattle(this.defeated.get(i).aK, this.defeated.get(i).aR.key, this.defeated.get(i).aR.num, this.defeated.get(i).aR.mo);
            }
        }
        try {
            this.armyDivisionsKeys.clear();
            try {
                for (int i = 0, iSize = this.firstLine.size(); i < iSize; ++i) {
                    if (this.firstLine.get(i) != null) {
                        this.addArmyDivisionKey(this.firstLine.get(i).aK);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                for (int i = 0, iSize = this.secondLine.size(); i < iSize; ++i) {
                    if (this.secondLine.get(i) != null) {
                        this.addArmyDivisionKey(this.secondLine.get(i).aK);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            for (int i = 0, iSize = this.reserveFirstLine.size(); i < iSize; ++i) {
                this.addArmyDivisionKey(this.reserveFirstLine.get(i).aK);
            }
            for (int i = 0, iSize = this.reserveSecondLine.size(); i < iSize; ++i) {
                this.addArmyDivisionKey(this.reserveSecondLine.get(i).aK);
            }
            for (int i = 0, iSize = this.defeated.size(); i < iSize; ++i) {
                this.addArmyDivisionKey(this.defeated.get(i).aK);
            }
            for (int i = this.armyDivisionsKeys.size() - 1; i >= 0; --i) {
                Game.getProvince(iProvinceID).updateArmy_BattleSummary(this.armyDivisionsKeys.get(i));
            }
            this.armyDivisionsKeys.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (!haveToRetreat && this.armyGeneral != null) {
            this.armyGeneral.addCombatExperience(Math.min(GameValues.generals.COMBAT_EXPERIENCE_BATTLE_WON_LIMIT, Math.min(GameValues.generals.COMBAT_EXPERIENCE_BATTLE_WON_LIMIT_PER_DAY_OF_BATTLE * (iRoundID + 1), GameValues.generals.COMBAT_EXPERIENCE_BATTLE_WON_EXPERIENCE_PER_DAY_OF_BATTLE * (iRoundID + 1) + GameValues.generals.COMBAT_EXPERIENCE_BATTLE_WON_MIN + Game.oR.nextInt(GameValues.generals.COMBAT_EXPERIENCE_BATTLE_WON_MIN_RANDOM))));
        }
        if (haveToRetreat) {
            int extraArmyY = 0;
            this.buildArmyDivisionsKeys();
            if (armyCanBeDestroyed && iRoundID < GameValues.battle.BATTLE_ARMY_DESTROYED_ROUND_ID) {
                for (int j = 0; j < this.lDefeatedArmyDivisionKeys.size(); ++j) {
                    Game.getProvince(iProvinceID).armyDestroyed(this.lDefeatedArmyDivisionKeys.get(j));
                }
            }
            else {
                for (int j = 0; j < this.lDefeatedArmyDivisionKeys.size(); ++j) {
                    final ArmyDivision armyRetreat = Game.getProvince(iProvinceID).getArmy(this.lDefeatedArmyDivisionKeys.get(j));
                    if (armyRetreat != null && armyRetreat.iArmy / Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * armyRetreat.iArmyRegimentSize < GameValues.battle.BATTLE_ARMY_DESTROYED_IF_MANPOWER_PERC_BELOW) {
                        Game.getProvince(iProvinceID).armyDestroyed(this.lDefeatedArmyDivisionKeys.get(j));
                    }
                    else {
                        final int result = Game.getProvince(iProvinceID).armyRetreat(this.lDefeatedArmyDivisionKeys.get(j), extraArmyY);
                        if (result < 0) {
                            Game.getProvince(iProvinceID).armyDestroyed(this.lDefeatedArmyDivisionKeys.get(j));
                        }
                        else {
                            extraArmyY = result;
                        }
                    }
                }
            }
        }
    }
    
    public final void addArmyDivisionKey(final String key) {
        for (int i = this.armyDivisionsKeys.size() - 1; i >= 0; --i) {
            if (this.armyDivisionsKeys.get(i).equals(key)) {
                return;
            }
        }
        this.armyDivisionsKeys.add(key);
    }
    
    public final void updateDiceRoll() {
        this.diceRollGeneral = 1 + Game.oR.nextInt(GameValues.battle.BATTLE_MAX_DICE_ROLL);
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.firstLine.get(i) != null) {
                this.firstLine.get(i).d = Game.oR.nextInt(GameValues.battle.BATTLE_MAX_DICE_ROLL_REGIMENT);
            }
            if (this.secondLine.get(i) != null) {
                this.secondLine.get(i).d = Game.oR.nextInt(GameValues.battle.BATTLE_MAX_DICE_ROLL_REGIMENT);
            }
        }
    }
    
    public final void updateAttack(final BattleLine nAttacking, final int iRoundID, final int iDefense) {
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.firstLine.get(i) != null) {
                this.firstLine.get(i).cL = 0;
            }
        }
        int generalExtraCasualties = 0;
        if (nAttacking.armyGeneral != null) {
            if (this.armyGeneral != null) {
                generalExtraCasualties = Math.max(0, nAttacking.armyGeneral.getAttack() - this.armyGeneral.getDefense() - iDefense);
            }
            else {
                generalExtraCasualties = Math.max(0, nAttacking.armyGeneral.getAttack() - iDefense);
            }
        }
        for (int j = 0; j < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++j) {
            if (nAttacking.firstLine.get(j) != null) {
                final float fArmyPercLeft = nAttacking.firstLine.get(j).aR.num / (float)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
                if (this.firstLine.get(j) != null && this.firstLine.get(j).aR.num > 0) {
                    final BattleRegiment battleRegiment = this.firstLine.get(j);
                    battleRegiment.cL += this.getAttackCasualties(iRoundID, generalExtraCasualties, fArmyPercLeft, ArmyManager.lArmy.get(nAttacking.firstLine.get(j).aR.uID).get(nAttacking.firstLine.get(j).aR.aID).getAttack(nAttacking.firstLine.get(j).c) / (float)(ArmyManager.lArmy.get(this.firstLine.get(j).aR.uID).get(this.firstLine.get(j).aR.aID).getDefense(this.firstLine.get(j).c) + iDefense), nAttacking.firstLine.get(j).c);
                    this.iLastAttackRoundID = iRoundID;
                }
                else {
                    for (int k = 1; k < ArmyManager.lArmy.get(nAttacking.firstLine.get(j).aR.uID).get(nAttacking.firstLine.get(j).aR.aID).AttackRange + 1; ++k) {
                        if (j + k < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH && this.firstLine.get(j + k) != null && this.firstLine.get(j + k).aR.num > 0) {
                            final BattleRegiment battleRegiment2 = this.firstLine.get(j + k);
                            battleRegiment2.cL += this.getAttackCasualties(iRoundID, generalExtraCasualties, fArmyPercLeft, ArmyManager.lArmy.get(nAttacking.firstLine.get(j).aR.uID).get(nAttacking.firstLine.get(j).aR.aID).getAttack(nAttacking.firstLine.get(j).c) / (float)(ArmyManager.lArmy.get(this.firstLine.get(j + k).aR.uID).get(this.firstLine.get(j + k).aR.aID).getDefense(this.firstLine.get(j + k).c) + iDefense), nAttacking.firstLine.get(j).c);
                            this.iLastAttackRoundID = iRoundID;
                            break;
                        }
                        if (j - k >= 0 && this.firstLine.get(j - k) != null && this.firstLine.get(j - k).aR.num > 0) {
                            final BattleRegiment battleRegiment3 = this.firstLine.get(j - k);
                            battleRegiment3.cL += this.getAttackCasualties(iRoundID, generalExtraCasualties, fArmyPercLeft, ArmyManager.lArmy.get(nAttacking.firstLine.get(j).aR.uID).get(nAttacking.firstLine.get(j).aR.aID).getAttack(nAttacking.firstLine.get(j).c) / (float)(ArmyManager.lArmy.get(this.firstLine.get(j - k).aR.uID).get(this.firstLine.get(j - k).aR.aID).getDefense(this.firstLine.get(j - k).c) + iDefense), nAttacking.firstLine.get(j).c);
                            this.iLastAttackRoundID = iRoundID;
                            break;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++j) {
            if (nAttacking.secondLine.get(j) != null && ArmyManager.lUnitsTypes.get(nAttacking.secondLine.get(j).aR.uID).Line >= 2) {
                final float fArmyPercLeft = nAttacking.secondLine.get(j).aR.num / (float)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
                if (this.firstLine.get(j) != null && this.firstLine.get(j).aR.num > 0) {
                    final BattleRegiment battleRegiment4 = this.firstLine.get(j);
                    battleRegiment4.cL += this.getAttackCasualties(iRoundID, generalExtraCasualties, fArmyPercLeft, ArmyManager.lArmy.get(nAttacking.secondLine.get(j).aR.uID).get(nAttacking.secondLine.get(j).aR.aID).getAttack(nAttacking.secondLine.get(j).c) / (float)(ArmyManager.lArmy.get(this.firstLine.get(j).aR.uID).get(this.firstLine.get(j).aR.aID).getDefense(this.firstLine.get(j).c) + iDefense), nAttacking.secondLine.get(j).c);
                    this.iLastAttackRoundID = iRoundID;
                }
                else {
                    for (int k = 1; k < ArmyManager.lArmy.get(nAttacking.secondLine.get(j).aR.uID).get(nAttacking.secondLine.get(j).aR.aID).AttackRange + 1; ++k) {
                        if (j + k < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH && this.firstLine.get(j + k) != null && this.firstLine.get(j + k).aR.num > 0) {
                            final BattleRegiment battleRegiment5 = this.firstLine.get(j + k);
                            battleRegiment5.cL += this.getAttackCasualties(iRoundID, generalExtraCasualties, fArmyPercLeft, ArmyManager.lArmy.get(nAttacking.secondLine.get(j).aR.uID).get(nAttacking.secondLine.get(j).aR.aID).getAttack(nAttacking.secondLine.get(j).c) / (float)(ArmyManager.lArmy.get(this.firstLine.get(j + k).aR.uID).get(this.firstLine.get(j + k).aR.aID).getDefense(this.firstLine.get(j + k).c) + iDefense), nAttacking.secondLine.get(j).c);
                            this.iLastAttackRoundID = iRoundID;
                            break;
                        }
                        if (j - k >= 0 && this.firstLine.get(j - k) != null && this.firstLine.get(j - k).aR.num > 0) {
                            final BattleRegiment battleRegiment6 = this.firstLine.get(j - k);
                            battleRegiment6.cL += this.getAttackCasualties(iRoundID, generalExtraCasualties, fArmyPercLeft, ArmyManager.lArmy.get(nAttacking.secondLine.get(j).aR.uID).get(nAttacking.secondLine.get(j).aR.aID).getAttack(nAttacking.secondLine.get(j).c) / (float)(ArmyManager.lArmy.get(this.firstLine.get(j - k).aR.uID).get(this.firstLine.get(j - k).aR.aID).getDefense(this.firstLine.get(j - k).c) + iDefense), nAttacking.secondLine.get(j).c);
                            this.iLastAttackRoundID = iRoundID;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private final int getAttackCasualties(final int iRoundID, final int generalExtraCasualties, final float fArmyPercLeft, final float fAttackDefense, final int iCivID) {
        return (int)Math.ceil((GameValues.battle.BATTLE_CASUALTIES + generalExtraCasualties + this.diceRollGeneral) * (fArmyPercLeft * fAttackDefense * (1.0f + iRoundID / 50.0f + Game.getCiv(iCivID).civBonuses.Discipline)));
    }
    
    public final void updateMoraleAndRetreat() {
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.firstLine.get(i) != null && this.firstLine.get(i).cL > 0 && this.firstLine.get(i).aR.num > 0 && this.firstLine.get(i).cL > 0) {
                this.firstLine.get(i).aR.mo = Math.max(0.0f, this.firstLine.get(i).aR.mo - this.firstLine.get(i).cL / (float)this.firstLine.get(i).aR.num * GameValues.battle.BATTLE_MORALE_LOSS_MULTIPLIER);
                if (this.firstLine.get(i).aR.mo < 0.96f) {
                    if (this.firstLine.get(i).aR.mo <= GameValues.battle.BATTLE_FULL_RETREAT_IF_MORALE_BELOW) {
                        this.firstLine.get(i).rL = this.firstLine.get(i).aR.num;
                        final BattleRegiment battleRegiment = this.firstLine.get(i);
                        battleRegiment.re += this.firstLine.get(i).rL;
                        final ArmyRegiment ar = this.firstLine.get(i).aR;
                        ar.num -= this.firstLine.get(i).rL;
                        this.iRetreated += this.firstLine.get(i).rL;
                        this.numOfUnits -= this.firstLine.get(i).rL;
                        this.numOfUnitsOnBattlefield -= this.firstLine.get(i).rL;
                    }
                    else {
                        this.firstLine.get(i).rL = (int)Math.max(0.0f, this.firstLine.get(i).cL * (1.0f - this.firstLine.get(i).aR.mo));
                        if (this.firstLine.get(i).rL > 0) {
                            if (this.firstLine.get(i).rL > this.firstLine.get(i).aR.num) {
                                this.firstLine.get(i).rL = this.firstLine.get(i).aR.num;
                            }
                            final BattleRegiment battleRegiment2 = this.firstLine.get(i);
                            battleRegiment2.re += this.firstLine.get(i).rL;
                            final ArmyRegiment ar2 = this.firstLine.get(i).aR;
                            ar2.num -= this.firstLine.get(i).rL;
                            this.iRetreated += this.firstLine.get(i).rL;
                            this.numOfUnits -= this.firstLine.get(i).rL;
                            this.numOfUnitsOnBattlefield -= this.firstLine.get(i).rL;
                        }
                    }
                }
            }
        }
        this.updateAverageMoraleOnBattleField();
    }
    
    public final void updateAverageMoraleOnBattleField() {
        this.fMorale = 0.0f;
        int tNum = 0;
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.firstLine.get(i) != null) {
                this.fMorale += this.firstLine.get(i).aR.mo;
                ++tNum;
            }
        }
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.secondLine.get(i) != null) {
                this.fMorale += this.secondLine.get(i).aR.mo;
                ++tNum;
            }
        }
        this.fMorale /= tNum;
    }
    
    public final void updateCasualties() {
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            if (this.firstLine.get(i) != null) {
                if (this.firstLine.get(i).cL > this.firstLine.get(i).aR.num) {
                    this.firstLine.get(i).cL = this.firstLine.get(i).aR.num;
                }
                final BattleRegiment battleRegiment = this.firstLine.get(i);
                battleRegiment.ca += this.firstLine.get(i).cL;
                final ArmyRegiment ar = this.firstLine.get(i).aR;
                ar.num -= this.firstLine.get(i).cL;
                this.iCasualties += this.firstLine.get(i).cL;
                this.numOfUnits -= this.firstLine.get(i).cL;
                this.numOfUnitsOnBattlefield -= this.firstLine.get(i).cL;
            }
        }
    }
    
    public final void updateDefeated() {
        try {
            int regroup = 0;
            for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
                if (this.firstLine.get(i) != null && this.firstLine.get(i).aR.num <= 0) {
                    if (this.firstLine.get(i).f == 0) {
                        this.defeated.add(this.firstLine.get(i));
                        this.firstLine.get(i).f = 1;
                    }
                    if (this.reserveFirstLine.size() > 0) {
                        this.firstLine.set(i, this.reserveFirstLine.get(0));
                        this.inReserve -= this.reserveFirstLine.get(0).aR.num;
                        this.numOfUnitsOnBattlefield += this.reserveFirstLine.get(0).aR.num;
                        this.reserveFirstLine.remove(0);
                    }
                    else {
                        boolean updated = false;
                        for (int j = 0; j < this.secondLine.size(); ++j) {
                            if (this.secondLine.get(j) != null && ArmyManager.lUnitsTypes.get(this.secondLine.get(j).aR.uID).Line < 2) {
                                this.firstLine.set(i, this.secondLine.get(j));
                                this.secondLine.set(j, null);
                                updated = true;
                                break;
                            }
                        }
                        if (!updated) {
                            if (this.reserveSecondLine.size() > 0) {
                                this.firstLine.set(i, this.reserveSecondLine.get(0));
                                this.inReserve -= this.reserveSecondLine.get(0).aR.num;
                                this.numOfUnitsOnBattlefield += this.reserveSecondLine.get(0).aR.num;
                                this.reserveSecondLine.remove(0);
                            }
                            else if (this.secondLine.get(i) != null) {
                                this.firstLine.set(i, this.secondLine.get(i));
                                this.secondLine.set(i, null);
                            }
                            else if (i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH / 2) {
                                if (i > 0) {
                                    final BattleRegiment tRegiment = this.firstLine.get(i);
                                    this.firstLine.set(i, this.firstLine.get(i - 1));
                                    this.firstLine.set(i - 1, tRegiment);
                                    ++regroup;
                                }
                            }
                            else if (i + 1 < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH) {
                                final BattleRegiment tRegiment = this.firstLine.get(i);
                                this.firstLine.set(i, this.firstLine.get(i + 1));
                                this.firstLine.set(i + 1, tRegiment);
                                ++regroup;
                            }
                        }
                    }
                }
            }
            if (regroup > 0) {
                for (int k = 0; k < regroup; ++k) {
                    for (int l = 0; l < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++l) {
                        if (this.firstLine.get(l) != null && this.firstLine.get(l).aR.num <= 0) {
                            if (l < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH / 2) {
                                if (l > 0) {
                                    final BattleRegiment tRegiment = this.firstLine.get(l);
                                    this.firstLine.set(l, this.firstLine.get(l - 1));
                                    this.firstLine.set(l - 1, tRegiment);
                                }
                            }
                            else if (l + 1 < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH) {
                                final BattleRegiment tRegiment = this.firstLine.get(l);
                                this.firstLine.set(l, this.firstLine.get(l + 1));
                                this.firstLine.set(l + 1, tRegiment);
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
                if (this.firstLine.get(i) != null) {
                    if (this.firstLine.get(i).f <= 0) {
                        break;
                    }
                    this.firstLine.set(i, null);
                }
            }
            for (int i = GameValues.battle.BATTLE_MAX_BATTLE_WIDTH - 1; i >= 0; --i) {
                if (this.firstLine.get(i) != null) {
                    if (this.firstLine.get(i).f <= 0) {
                        break;
                    }
                    this.firstLine.set(i, null);
                }
            }
            if (regroup > 0) {
                int firstID = -1;
                int lastID = -1;
                int numOfNull = 0;
                for (int m = 0; m < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++m) {
                    if (this.firstLine.get(m) != null) {
                        firstID = m;
                        break;
                    }
                }
                for (int m = GameValues.battle.BATTLE_MAX_BATTLE_WIDTH - 1; m >= 0; --m) {
                    if (this.firstLine.get(m) != null) {
                        lastID = m;
                        break;
                    }
                }
                if (firstID >= 0 && lastID >= 0) {
                    final List<BattleRegiment> tReg = new ArrayList<BattleRegiment>();
                    for (int i2 = firstID; i2 <= lastID; ++i2) {
                        if (this.firstLine.get(i2) == null) {
                            ++numOfNull;
                        }
                        else {
                            tReg.add(this.firstLine.get(i2));
                            this.firstLine.set(i2, null);
                        }
                    }
                    try {
                        for (int i2 = firstID, j2 = 0, pos = (int)Math.max(0.0, GameValues.battle.BATTLE_MAX_BATTLE_WIDTH / 2 - Math.floor((lastID - numOfNull - firstID) / 2.0f)); i2 <= lastID - numOfNull; ++i2, ++j2, ++pos) {
                            this.firstLine.set(pos, tReg.get(j2));
                        }
                    }
                    catch (final Exception ex2) {}
                }
                firstID = -1;
                lastID = -1;
                numOfNull = 0;
                for (int m = 0; m < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++m) {
                    if (this.secondLine.get(m) != null) {
                        firstID = m;
                        break;
                    }
                }
                for (int m = GameValues.battle.BATTLE_MAX_BATTLE_WIDTH - 1; m >= 0; --m) {
                    if (this.secondLine.get(m) != null) {
                        lastID = m;
                        break;
                    }
                }
                if (firstID >= 0 && lastID >= 0) {
                    final List<BattleRegiment> tReg = new ArrayList<BattleRegiment>();
                    for (int i2 = firstID; i2 <= lastID; ++i2) {
                        if (this.secondLine.get(i2) == null) {
                            ++numOfNull;
                        }
                        else {
                            tReg.add(this.secondLine.get(i2));
                            this.secondLine.set(i2, null);
                        }
                    }
                    try {
                        for (int i2 = firstID, j2 = 0, pos = (int)Math.max(0.0, GameValues.battle.BATTLE_MAX_BATTLE_WIDTH / 2 - Math.floor((lastID - numOfNull - firstID) / 2.0f)); i2 <= lastID - numOfNull; ++i2, ++j2, ++pos) {
                            this.secondLine.set(pos, tReg.get(j2));
                        }
                    }
                    catch (final Exception ex3) {}
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildCasualties() {
        this.lCasualties.clear();
        for (int i = this.lCivs.size() - 1; i >= 0; --i) {
            this.lCasualties.add(0);
        }
        for (int i = this.firstLine.size() - 1; i >= 0; --i) {
            if (this.firstLine.get(i) != null) {
                this.addCasualties(this.firstLine.get(i).c, this.firstLine.get(i).ca);
            }
        }
        for (int i = this.secondLine.size() - 1; i >= 0; --i) {
            if (this.secondLine.get(i) != null) {
                this.addCasualties(this.secondLine.get(i).c, this.secondLine.get(i).ca);
            }
        }
        for (int i = this.reserveFirstLine.size() - 1; i >= 0; --i) {
            this.addCasualties(this.reserveFirstLine.get(i).c, this.reserveFirstLine.get(i).ca);
        }
        for (int i = this.reserveSecondLine.size() - 1; i >= 0; --i) {
            this.addCasualties(this.reserveSecondLine.get(i).c, this.reserveSecondLine.get(i).ca);
        }
        for (int i = this.defeated.size() - 1; i >= 0; --i) {
            this.addCasualties(this.defeated.get(i).c, this.defeated.get(i).ca);
        }
    }
    
    public final void addCasualties(final int iCivID, final int iCasualties) {
        if (iCasualties > 0) {
            for (int i = this.lCivs.size() - 1; i >= 0; --i) {
                if (this.lCivs.get(i) == iCivID) {
                    this.lCasualties.set(i, this.lCasualties.get(i) + iCasualties);
                    return;
                }
            }
        }
    }
    
    public final void buildArmyDivisionsKeys() {
        this.lDefeatedArmyDivisionKeys.clear();
        for (int i = this.firstLine.size() - 1; i >= 0; --i) {
            if (this.firstLine.get(i) != null) {
                this.addDefeatedArmyDivisionKey(this.firstLine.get(i).aK);
            }
        }
        for (int i = this.secondLine.size() - 1; i >= 0; --i) {
            if (this.secondLine.get(i) != null) {
                this.addDefeatedArmyDivisionKey(this.secondLine.get(i).aK);
            }
        }
        for (int i = this.reserveFirstLine.size() - 1; i >= 0; --i) {
            this.addDefeatedArmyDivisionKey(this.reserveFirstLine.get(i).aK);
        }
        for (int i = this.reserveSecondLine.size() - 1; i >= 0; --i) {
            this.addDefeatedArmyDivisionKey(this.reserveSecondLine.get(i).aK);
        }
        for (int i = this.defeated.size() - 1; i >= 0; --i) {
            this.addDefeatedArmyDivisionKey(this.defeated.get(i).aK);
        }
    }
    
    public final void addDefeatedArmyDivisionKey(final String key) {
        if (key != null) {
            for (int i = this.lDefeatedArmyDivisionKeys.size() - 1; i >= 0; --i) {
                if (this.lDefeatedArmyDivisionKeys.get(i).equals(key)) {
                    return;
                }
            }
            this.lDefeatedArmyDivisionKeys.add(key);
        }
    }
    
    public boolean isInBattleArmy(final String key) {
        try {
            for (int i = this.firstLine.size() - 1; i >= 0; --i) {
                if (this.firstLine.get(i) != null && this.firstLine.get(i).aK.equals(key)) {
                    return true;
                }
            }
            for (int i = this.secondLine.size() - 1; i >= 0; --i) {
                if (this.secondLine.get(i) != null && this.secondLine.get(i).aK.equals(key)) {
                    return true;
                }
            }
            for (int i = this.reserveFirstLine.size() - 1; i >= 0; --i) {
                if (this.reserveFirstLine.get(i).aK.equals(key)) {
                    return true;
                }
            }
            for (int i = this.reserveSecondLine.size() - 1; i >= 0; --i) {
                if (this.reserveSecondLine.get(i).aK.equals(key)) {
                    return true;
                }
            }
            for (int i = this.defeated.size() - 1; i >= 0; --i) {
                if (this.defeated.get(i).aK.equals(key)) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public boolean isInBattleCiv(final int iCivID) {
        for (int i = this.lCivs.size() - 1; i >= 0; --i) {
            if (this.lCivs.get(i) == iCivID) {
                return true;
            }
        }
        return false;
    }
    
    public int getCivsRegimentsLimit() {
        int out = 0;
        for (int i = this.lCivs.size() - 1; i >= 0; --i) {
            out += Game.getCiv(this.lCivs.get(i)).iRegimentsLimit;
        }
        return out;
    }
    
    public final void updateMoraleEndOfBattle(final float fMorale) {
        for (int i = this.firstLine.size() - 1; i >= 0; --i) {
            if (this.firstLine.get(i) != null) {
                this.firstLine.get(i).aR.mo = Math.max(fMorale, this.firstLine.get(i).aR.mo);
            }
        }
        for (int i = this.secondLine.size() - 1; i >= 0; --i) {
            if (this.secondLine.get(i) != null) {
                this.secondLine.get(i).aR.mo = Math.max(fMorale, this.secondLine.get(i).aR.mo);
            }
        }
        for (int i = this.reserveFirstLine.size() - 1; i >= 0; --i) {
            this.reserveFirstLine.get(i).aR.mo = Math.max(fMorale, this.reserveFirstLine.get(i).aR.mo);
        }
        for (int i = this.reserveSecondLine.size() - 1; i >= 0; --i) {
            this.reserveSecondLine.get(i).aR.mo = Math.max(fMorale, this.reserveSecondLine.get(i).aR.mo);
        }
        for (int i = this.defeated.size() - 1; i >= 0; --i) {
            this.defeated.get(i).aR.mo = Math.max(fMorale, this.defeated.get(i).aR.mo);
        }
    }
}
