// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.battles.BattleManager;
import java.util.ArrayList;
import java.util.List;

public class MercenariesManager
{
    public static List<MercenaryArmy> getMercenaryArmies(final int civID) {
        final List<MercenaryArmy> out = new ArrayList<MercenaryArmy>();
        final int width = BattleManager.getBattleWidth(civID);
        if (width > 3) {
            for (int a = 0; a < GameValues.army.MERCENARIES_LIMIT_TO_CHOOSE_FROM; ++a) {
                final List<BestArmy> bestUnits = new ArrayList<BestArmy>();
                final MercenaryArmy mercenaryArmy = new MercenaryArmy();
                final int limit = (int)Math.max(2.0f, width * ((a + 1.0f) / GameValues.army.MERCENARIES_LIMIT_TO_CHOOSE_FROM));
                int random = 0;
                final List<MercenaryArmy> tempMercenary = new ArrayList<MercenaryArmy>();
                for (int i = 0; i < Game.getCiv(civID).unitsBest.size(); ++i) {
                    if ((ArmyManager.lUnitsTypes.get(Game.getCiv(civID).unitsBest.get(i).unitID).Line == 0 || ArmyManager.lUnitsTypes.get(Game.getCiv(civID).unitsBest.get(i).unitID).Line == 1) && !ArmyManager.lArmy.get(Game.getCiv(civID).unitsBest.get(i).unitID).get(Game.getCiv(civID).unitsBest.get(i).armyID).isSettler) {
                        bestUnits.add(new BestArmy(Game.getCiv(civID).unitsBest.get(i).unitID, Game.getCiv(civID).unitsBest.get(i).armyID));
                    }
                }
                if (bestUnits.size() > 0) {
                    for (int i = 0; i < bestUnits.size(); ++i) {
                        tempMercenary.add(new MercenaryArmy());
                    }
                    for (int i = 0; i < limit; ++i) {
                        random = Game.oR.nextInt(bestUnits.size());
                        tempMercenary.get(random).addArmy(bestUnits.get(random).iUnitID, bestUnits.get(random).iArmyID);
                    }
                    for (int i = 0; i < tempMercenary.size(); ++i) {
                        for (int j = 0; j < tempMercenary.get(i).iUnitID.size(); ++j) {
                            mercenaryArmy.addArmy(tempMercenary.get(i).iUnitID.get(j), tempMercenary.get(i).iArmyID.get(j));
                        }
                    }
                }
                tempMercenary.clear();
                bestUnits.clear();
                for (int i = 0; i < Game.getCiv(civID).unitsBest.size(); ++i) {
                    if (ArmyManager.lUnitsTypes.get(Game.getCiv(civID).unitsBest.get(i).unitID).Line == 2 && !ArmyManager.lArmy.get(Game.getCiv(civID).unitsBest.get(i).unitID).get(Game.getCiv(civID).unitsBest.get(i).armyID).isSettler) {
                        bestUnits.add(new BestArmy(Game.getCiv(civID).unitsBest.get(i).unitID, Game.getCiv(civID).unitsBest.get(i).armyID));
                    }
                }
                if (bestUnits.size() > 0) {
                    for (int i = 0; i < bestUnits.size(); ++i) {
                        tempMercenary.add(new MercenaryArmy());
                    }
                    for (int i = 0; i < limit; ++i) {
                        random = Game.oR.nextInt(bestUnits.size());
                        tempMercenary.get(random).addArmy(bestUnits.get(random).iUnitID, bestUnits.get(random).iArmyID);
                    }
                    for (int i = 0; i < tempMercenary.size(); ++i) {
                        for (int j = 0; j < tempMercenary.get(i).iUnitID.size(); ++j) {
                            mercenaryArmy.addArmy(tempMercenary.get(i).iUnitID.get(j), tempMercenary.get(i).iArmyID.get(j));
                        }
                    }
                }
                tempMercenary.clear();
                bestUnits.clear();
                if (mercenaryArmy.iUnitID.size() > 0) {
                    mercenaryArmy.buildCost(civID);
                    out.add(mercenaryArmy);
                }
            }
        }
        return out;
    }
    
    public static final boolean recruitMercenaries(final int civID, final int provinceID, final MercenaryArmy mercenaryArmy) {
        if (Game.getCiv(civID).fGold < mercenaryArmy.iCost) {
            return false;
        }
        final List<ArmyRegiment> nArmyRegiment = new ArrayList<ArmyRegiment>();
        for (int i = 0, iSize = mercenaryArmy.iUnitID.size(); i < iSize; ++i) {
            nArmyRegiment.add(new ArmyRegiment(mercenaryArmy.iUnitID.get(i), mercenaryArmy.iArmyID.get(i)));
        }
        final ArmyDivision nArmyDivision = new ArmyDivision(civID, provinceID, nArmyRegiment);
        Game.getProvince(provinceID).addArmy(nArmyDivision);
        final Civilization civ = Game.getCiv(civID);
        civ.fGold -= mercenaryArmy.iCost;
        Game.gameThread.addCivUpdateArmyMaintenance(civID);
        return true;
    }
    
    public static class MercenaryArmy
    {
        public List<Integer> iUnitID;
        public List<Integer> iArmyID;
        public int iCost;
        
        public MercenaryArmy() {
            this.iUnitID = new ArrayList<Integer>();
            this.iArmyID = new ArrayList<Integer>();
        }
        
        public void addArmy(final int iUnitID, final int iArmyID) {
            this.iUnitID.add(iUnitID);
            this.iArmyID.add(iArmyID);
        }
        
        public void buildCost(final int civID) {
            for (int i = this.iUnitID.size() - 1; i >= 0; --i) {
                this.iCost += ArmyManager.getRecruitmentCost(civID, -1, this.iUnitID.get(i), this.iArmyID.get(i), ArmyManager.getRecruitmentCost_Regiments(civID) + i);
            }
            this.iCost *= (int)GameValues.army.MERCENARIES_COST_EXTRA_PER_REGIMENT;
        }
    }
    
    public static class BestArmy
    {
        public int iUnitID;
        public int iArmyID;
        
        public BestArmy(final int iUnitID, final int iArmyID) {
            this.iUnitID = iUnitID;
            this.iArmyID = iArmyID;
        }
    }
}
