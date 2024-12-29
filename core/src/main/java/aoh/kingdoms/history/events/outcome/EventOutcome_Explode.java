// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import java.util.List;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_Explode extends EventOutcome
{
    public String civTAG;
    
    public EventOutcome_Explode(final String civTAG) {
        this.civTAG = civTAG;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int civID = Game.getCivID(this.civTAG);
            if (civID > 0) {
                final Civilization civ = Game.getCiv(civID);
                if (civ.getNumOfProvinces() > 1) {
                    final List<ExplodeProvince> explode = new ArrayList<ExplodeProvince>();
                    for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
                        if (civ.getProvinceID(i) != civ.getCapitalProvinceID()) {
                            int bestID = -1;
                            for (int j = 0; j < Game.getProvince(civ.getProvinceID(i)).iCoresSize; ++j) {
                                if (Game.getProvince(civ.getProvinceID(i)).getCore(j) != civID && Game.getCiv(Game.getProvince(civ.getProvinceID(i)).getCore(j)).getNumOfProvinces() == 0) {
                                    bestID = Math.max(bestID, Game.getProvince(civ.getProvinceID(i)).getCore(j));
                                }
                            }
                            if (bestID > 0) {
                                explode.add(new ExplodeProvince(bestID, civ.getProvinceID(i)));
                            }
                        }
                    }
                    for (int i = explode.size() - 1; i >= 0; --i) {
                        Game.getProvince(explode.get(i).provinceID).setCivID(explode.get(i).civID);
                    }
                    final List<Integer> civs = new ArrayList<Integer>();
                    for (int k = explode.size() - 1; k >= 0; --k) {
                        if (!civs.contains(explode.get(k).civID)) {
                            civs.add(explode.get(k).civID);
                        }
                    }
                    for (int k = 0; k < civs.size(); ++k) {
                        if (Game.getCiv(civs.get(k)).getPuppetOfCivID() != civs.get(k)) {
                            Game.getCiv(civs.get(k)).setPuppetOfCivID(civs.get(k));
                        }
                        if (Game.getProvince(Game.getCiv(civs.get(k)).getCapitalProvinceID()).getCivID() == civs.get(k)) {
                            Game.getCiv(civs.get(k)).setCapitalProvinceID(Game.getCiv(civs.get(k)).getCapitalProvinceID());
                        }
                        else if (Game.getCiv(civs.get(k)).getCapitalProvinceID() < 0 || Game.getProvince(Game.getCiv(civs.get(k)).getCapitalProvinceID()).getCivID() != civs.get(k)) {
                            Game.getCiv(civs.get(k)).moveCapital_ToLargestProvince();
                        }
                        Game.getCiv(civs.get(k)).fGold = Math.max(Game.getCiv(civs.get(k)).fGold, (float)GameValues.events.EVENT_EXPLODE_MIN_GOLD);
                        Game.getCiv(civs.get(k)).fLegacy = Math.max(Game.getCiv(civs.get(k)).fLegacy, (float)GameValues.events.EVENT_EXPLODE_MIN_LEGACY);
                        Game.getCiv(civs.get(k)).fManpower = Math.max(Game.getCiv(civs.get(k)).fManpower, GameValues.events.EVENT_EXPLODE_MIN_MANPOWER);
                        if (civ.getResearchedTechnologies() > Game.getCiv(civs.get(k)).getResearchedTechnologies()) {
                            for (int a = 0; a < TechnologyTree.iTechnologySize; ++a) {
                                if (civ.getTechResearched(a)) {
                                    Game.getCiv(civs.get(k)).addTechnology(a, false);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("RevolutionaryMovements");
    }
    
    @Override
    public String getStringRight() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.revolutionRisk;
    }
    
    public class ExplodeProvince
    {
        public int civID;
        public int provinceID;
        
        public ExplodeProvince(final int civID, final int provinceID) {
            this.civID = civID;
            this.provinceID = provinceID;
        }
    }
}
