// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.rebels;

import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.Player.Notification.Notification;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.map.army.ArmyDivision;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.mainGame.AI.Army.AI_Army_Composition;
import aoh.kingdoms.history.map.army.ArmyRegiment;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyPosition;
import java.util.List;

public class RevolutionManager
{
    public List<ArmyPosition> armyPosition;
    public int iArmyPositionSize;
    public List<OccupiedProvince> occupiedProvinces;
    
    public RevolutionManager() {
        this.armyPosition = new ArrayList<ArmyPosition>();
        this.iArmyPositionSize = 0;
        this.occupiedProvinces = new ArrayList<OccupiedProvince>();
    }
    
    public void clearData() {
        this.occupiedProvinces.clear();
        this.armyPosition.clear();
        this.iArmyPositionSize = 0;
        Game.revolutionMoveUnits.clearData();
    }
    
    public final void update() {
        try {
            this.declareIndependence();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.moveArmies();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.startRevolution();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void startRevolution() {
        List<Integer> possibleProvinces = new ArrayList<Integer>();
        for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_UPRISING_STEPS; i < Game.getCivsSize(); i += GameValues.gameUpdate.GAME_UPDATE_UPRISING_STEPS) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                possibleProvinces.clear();
                for (int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(i).getProvinceID(j)).getRevulutionaryRisk() >= GameValues.rebels.START_UPRISING_MIN_UNREST && !Game.getProvince(Game.getCiv(i).getProvinceID(j)).isOccupied()) {
                        possibleProvinces.add(Game.getCiv(i).getProvinceID(j));
                    }
                }
                if (possibleProvinces.size() > 0) {
                    this.spawnRevolution(i, possibleProvinces);
                }
            }
        }
        possibleProvinces.clear();
        possibleProvinces = null;
    }
    
    public void spawnRevolution(final int civID, final List<Integer> provinces) {
        this.spawnRevolution(civID, provinces.get(Game.oR.nextInt(provinces.size())), (int)(provinces.size() * GameValues.rebels.UPRISING_PROVINCES_PERC));
    }
    
    public void spawnRevolution(final int civID, final int provinceID, final int limitOfProvinces) {
        try {
            try {
                if (Game.getProvince(provinceID).getCivRegionID() >= 0) {
                    for (int regionID = Game.getProvince(provinceID).getCivRegionID(), i = 0; i < Game.getCiv(civID).getCivRegion(regionID).getProvincesSize(); ++i) {
                        if (provinceID != Game.getCiv(civID).getCivRegion(regionID).getProvince(i)) {
                            Game.getProvince(Game.getCiv(civID).getCivRegion(regionID).getProvince(i)).setRevulutionaryRisk(Game.getProvince(Game.getCiv(civID).getCivRegion(regionID).getProvince(i)).getRevulutionaryRisk() - Game.getProvince(Game.getCiv(civID).getCivRegion(regionID).getProvince(i)).getRevulutionaryRisk() * (GameValues.rebels.DECREASE_UNREST_IN_PROVINCES_BY_AFTER_REVOLT * (1.0f - Game.getDistance_PercOfMax(provinceID, Game.getCiv(civID).getCivRegion(regionID).getProvince(i)))));
                        }
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            final List<Integer> spawnProvinces = new ArrayList<Integer>();
            spawnProvinces.add(provinceID);
            if (limitOfProvinces > 1) {
                for (int i = 0; i < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++i) {
                    if (Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getCivID() == civID && !Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).isOccupied()) {
                        spawnProvinces.add(Game.getProvince(provinceID).getNeighboringProvinces(i));
                        if (spawnProvinces.size() >= limitOfProvinces) {
                            break;
                        }
                    }
                }
                if (spawnProvinces.size() < limitOfProvinces) {
                    for (int i = 0; i < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++i) {
                        for (int j = 0; j < Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++j) {
                            if (Game.getProvince(Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j)).getCivID() == civID && !Game.getProvince(Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j)).isOccupied()) {
                                spawnProvinces.add(Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j));
                                if (spawnProvinces.size() >= limitOfProvinces) {
                                    break;
                                }
                            }
                        }
                        if (spawnProvinces.size() >= limitOfProvinces) {
                            break;
                        }
                    }
                }
            }
            for (int i = 0, iSize = spawnProvinces.size(); i < iSize; ++i) {
                Game.getProvince(spawnProvinces.get(i)).setOccupiedByCivID(-civID);
                this.addOccupiedProvince(spawnProvinces.get(i));
                Game.getProvince(spawnProvinces.get(i)).setRevulutionaryRisk(Math.min(Game.getProvince(spawnProvinces.get(i)).getRevulutionaryRisk(), GameValues.rebels.UNREST_AFTER_REVOLUTION_IN_PROVINCE));
            }
            int regimentsToSpawn = (int)Math.max((float)GameValues.rebels.UPRISING_REGIMENTS_MIN, Game.getCiv(civID).iRegimentsLimit * GameValues.rebels.UPRISING_PERC_OF_REGIMENTS_LIMIT);
            for (int k = 0, iSize2 = spawnProvinces.size(); k < iSize2; ++k) {
                final List<ArmyRegiment> rebelsRegiments = new ArrayList<ArmyRegiment>();
                final AI_Army_Composition armyComposition = new AI_Army_Composition(civID, Math.min(regimentsToSpawn, GameValues.rebels.UPRISING_MAX_REGIMENTS_IN_PROVINCE));
                armyComposition.numSupport += armyComposition.numSiege;
                final List<TechnologyTree.Unit> firstLine = Game.getCiv(civID).getUnlockedUnitsFirstLine();
                final List<TechnologyTree.Unit> flank = Game.getCiv(civID).getUnlockedUnitsFlank();
                final List<TechnologyTree.Unit> secondLine = Game.getCiv(civID).getUnlockedUnitsSupport();
                if (flank.size() == 0 && armyComposition.numFlank > 0) {
                    armyComposition.numFirstLine += armyComposition.numFlank;
                }
                if (secondLine.size() == 0 && armyComposition.numSupport > 0) {
                    armyComposition.numFirstLine += armyComposition.numSupport;
                }
                if (firstLine.size() == 0) {
                    firstLine.add(new TechnologyTree.Unit(0, 0));
                }
                int randID = 0;
                for (int l = 0; l < armyComposition.numFirstLine; ++l) {
                    randID = Game.oR.nextInt(firstLine.size());
                    rebelsRegiments.add(new ArmyRegiment(firstLine.get(randID).unitID, firstLine.get(randID).armyID));
                }
                if (flank.size() > 0) {
                    for (int l = 0; l < armyComposition.numFlank; ++l) {
                        randID = Game.oR.nextInt(flank.size());
                        rebelsRegiments.add(new ArmyRegiment(flank.get(randID).unitID, flank.get(randID).armyID));
                    }
                }
                if (secondLine.size() > 0) {
                    for (int l = 0; l < armyComposition.numSupport; ++l) {
                        randID = Game.oR.nextInt(secondLine.size());
                        rebelsRegiments.add(new ArmyRegiment(secondLine.get(randID).unitID, secondLine.get(randID).armyID));
                    }
                }
                regimentsToSpawn -= rebelsRegiments.size();
                Game.getProvince(spawnProvinces.get(k)).addArmy(new ArmyDivision(-civID, spawnProvinces.get(k), rebelsRegiments));
                if (regimentsToSpawn < 0) {
                    break;
                }
            }
            for (int k = 0, iSize2 = spawnProvinces.size(); k < iSize2; ++k) {
                Game.getProvince(spawnProvinces.get(k)).checkForBattle();
            }
            if (civID == Game.player.iCivID) {
                InGame_Info.iCivID = -civID;
                InGame_Info.iCivID2 = civID;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("Revolt"), Game.getProvince(provinceID).getProvinceName());
                InGame_Info.imgID = Images.infoUnrest;
                Game.player.addNotification(new Notification(Notification.Notification_Type.SIEGE, Game.lang.get("SiegeLost") + ": " + Game.getProvince(provinceID).getProvinceName(), Images.siege, Game_Calendar.TURN_ID, Notification.Notification_BG.RED) {
                    @Override
                    public void onAction() {
                        Game.mapCoords.centerToProvinceID(this.id);
                    }
                });
                Game.player.addNotification(new Notification(Notification.Notification_Type.REVOLT, Game.lang.get("Revolt") + ": " + Game.getProvince(provinceID).getProvinceName(), Images.siege, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, provinceID) {
                    @Override
                    public void onAction() {
                        Game.mapCoords.centerToProvinceID(this.id);
                    }
                });
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final int declareIndependence_TurnsLeft(final int provinceID) {
        for (int i = this.occupiedProvinces.size() - 1; i >= 0; --i) {
            if (this.occupiedProvinces.get(i).p == provinceID) {
                return Math.max(1, GameValues.rebels.DECLARE_INDEPENDENCE_AFTER_X_DAYS - (Game_Calendar.TURN_ID - this.occupiedProvinces.get(i).sinceTurnID));
            }
        }
        return 0;
    }
    
    public final void declareIndependence() {
        if (Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_REBELS_INDEPENDENCE_STEPS != 0) {
            return;
        }
        for (int i = this.occupiedProvinces.size() - 1; i >= 0; --i) {
            if (!Game.getProvince(this.occupiedProvinces.get(i).p).isOccupied() || Game.getProvinceData(this.occupiedProvinces.get(i).p).getOccupiedByCivID() > 0) {
                this.occupiedProvinces.remove(i);
            }
            else {
                Game.getProvince(this.occupiedProvinces.get(i).p).aiRebelsIndependenceChecked = false;
            }
        }
        for (int i = this.occupiedProvinces.size() - 1; i >= 0; --i) {
            if (Game_Calendar.TURN_ID - this.occupiedProvinces.get(i).sinceTurnID > GameValues.rebels.DECLARE_INDEPENDENCE_AFTER_X_DAYS && !Game.getProvince(this.occupiedProvinces.get(i).p).aiRebelsIndependenceChecked) {
                boolean cleanOccupiedProvinces = false;
                final List<Integer> provinces = new ArrayList<Integer>();
                final int provID = this.occupiedProvinces.get(i).p;
                Game.getProvince(this.occupiedProvinces.get(i).p).aiRebelsIndependenceChecked = true;
                cleanOccupiedProvinces = (cleanOccupiedProvinces || Game_Calendar.TURN_ID - this.occupiedProvinces.get(i).sinceTurnID > GameValues.rebels.DISBAND_REBELS_ARMIES_AFTER_X_DAYS);
                final int fromCivID = Game.getProvince(this.occupiedProvinces.get(i).p).getCivID();
                provinces.add(provID);
                for (int j = i - 1; j >= 0; --j) {
                    if (i != j && Game.getProvince(this.occupiedProvinces.get(j).p).getCivID() == Game.getProvince(provID).getCivID() && Game_Calendar.TURN_ID - this.occupiedProvinces.get(j).sinceTurnID >= GameValues.rebels.DECLARE_INDEPENDENCE_MIN_OCCUPIED_DAYS) {
                        provinces.add(this.occupiedProvinces.get(j).p);
                        Game.getProvince(this.occupiedProvinces.get(j).p).aiRebelsIndependenceChecked = true;
                        cleanOccupiedProvinces = (cleanOccupiedProvinces || Game_Calendar.TURN_ID - this.occupiedProvinces.get(j).sinceTurnID > GameValues.rebels.DISBAND_REBELS_ARMIES_AFTER_X_DAYS);
                    }
                }
                if (Game.getProvince(provID).getCivRegionID() >= 0) {
                    for (int j = provinces.size() - 1; j >= 0; --j) {
                        if (Game.getProvince(provinces.get(j)).getCivRegionID() != Game.getProvince(provID).getCivRegionID()) {
                            Game.getProvince(provinces.get(j)).aiRebelsIndependenceChecked = false;
                            provinces.remove(j);
                        }
                    }
                }
                for (int j = provinces.size() - 1; j >= 0; --j) {
                    for (int k = 0; k < Game.getProvince(provinces.get(j)).getNeighboringProvincesSize(); ++k) {
                        if (!provinces.contains(Game.getProvince(provinces.get(j)).getNeighboringProvinces(k)) && !Game.getProvince(Game.getProvince(provinces.get(j)).getNeighboringProvinces(k)).isCapital && Game.getProvince(Game.getProvince(provinces.get(j)).getNeighboringProvinces(k)).getCivID() == fromCivID) {
                            boolean canBeAdded = true;
                            for (int z = 0; z < Game.getProvince(Game.getProvince(provinces.get(j)).getNeighboringProvinces(k)).getNeighboringProvincesSize(); ++z) {
                                if (Game.getProvince(Game.getProvince(Game.getProvince(provinces.get(j)).getNeighboringProvinces(k)).getNeighboringProvinces(z)).getCivID() == fromCivID && !provinces.contains(Game.getProvince(Game.getProvince(provinces.get(j)).getNeighboringProvinces(k)).getNeighboringProvinces(z))) {
                                    canBeAdded = false;
                                    break;
                                }
                            }
                            if (canBeAdded) {
                                provinces.add(Game.getProvince(provinces.get(j)).getNeighboringProvinces(k));
                            }
                        }
                    }
                }
                for (int j = provinces.size() - 1; j > 0; --j) {
                    if (Game.getProvince(provinces.get(j)).getNeighboringProvincesSize() > 0) {
                        boolean removeProvince = true;
                        for (int l = 0; l < Game.getProvince(provinces.get(j)).getNeighboringProvincesSize(); ++l) {
                            if (provinces.contains(Game.getProvince(provinces.get(j)).getNeighboringProvinces(l)) || Game.getProvince(Game.getProvince(provinces.get(j)).getNeighboringProvinces(l)).getCivID() == 0) {
                                removeProvince = false;
                                break;
                            }
                        }
                        if (removeProvince) {
                            provinces.remove(j);
                        }
                    }
                }
                final List<Integer> possibleCivs = new ArrayList<Integer>();
                for (int m = provinces.size() - 1; m >= 0; --m) {
                    for (int l = 0; l < Game.getProvince(provinces.get(m)).iCoresSize; ++l) {
                        if (Game.getProvince(provinces.get(m)).getCore(l) > 0 && Game.getCiv(Game.getProvince(provinces.get(m)).getCore(l)).getNumOfProvinces() <= 0 && !possibleCivs.contains(Game.getProvince(provinces.get(m)).getCore(l))) {
                            possibleCivs.add(Game.getProvince(provinces.get(m)).getCore(l));
                        }
                    }
                }
                if (possibleCivs.size() > 0) {
                    final int civID = possibleCivs.get(Game.oR.nextInt(possibleCivs.size()));
                    for (int j2 = provinces.size() - 1; j2 >= 0; --j2) {
                        Game.getProvince(provinces.get(j2)).setRevulutionaryRisk(0.0f);
                        Game.getProvince(provinces.get(j2)).setOccupiedByCivID(0);
                        Game.getProvince(provinces.get(j2)).setCivID(civID);
                        Game.getProvince(provinces.get(j2)).addCore(civID);
                        Game.getProvince(provinces.get(j2)).resetSiegeData();
                        this.removeOccupiedProvince(provinces.get(j2));
                        try {
                            for (int a = Game.getProvince(provinces.get(j2)).getArmySize() - 1; a >= 0; --a) {
                                if (Game.getProvince(provinces.get(j2)).getArmy(a).civID < 0) {
                                    Game.revolutionMoveUnits.removeMove(Game.getProvince(provinces.get(j2)).getArmy(a).key);
                                    this.removeArmyPosition(provinces.get(j2), Game.getProvince(provinces.get(j2)).getArmy(a).key);
                                    Game.getProvince(provinces.get(j2)).removeArmy(Game.getProvince(provinces.get(j2)).getArmy(a).key);
                                    --a;
                                }
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    if (Game.getCiv(civID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(civID).getCapitalProvinceID()).getCivID() == civID) {
                        Game.getProvince(Game.getCiv(civID).getCapitalProvinceID()).setIsCapital(true);
                    }
                    else {
                        Game.getCiv(civID).moveCapital_ToLargestProvince();
                    }
                    Game.getCiv(civID).setPuppetOfCivID(civID);
                    Game.gameThread.addCivUpdateTotalIncomePerMonth(civID);
                    Game.gameThread.addCivUpdateLegacyPerMonth(civID);
                    Game.gameThread.addCivUpdateResearchPerMonth(civID);
                    Game.gameThread.addCivUpdateArmyMaintenance(civID);
                    Game.getCiv(civID).updateManpowerPerMonth();
                    Game.getCiv(civID).updateDiplomacyPerMonth();
                    Game.getCiv(civID).setManpower(Math.max(Game.getCiv(civID).fManpower, Game.getCiv(civID).fManpowerMax));
                    Game.getCiv(civID).fGold = Math.max(Game.getCiv(civID).fGold, (float)(GameValues.rebels.INDEPENDENCE_GOLD + ((GameValues.rebels.INDEPENDENCE_GOLD_RANDOM > 0) ? Game.oR.nextInt(GameValues.rebels.INDEPENDENCE_GOLD_RANDOM) : 0)));
                    Game.getCiv(civID).fLegacy = Math.max(Game.getCiv(civID).fLegacy, (float)(GameValues.rebels.INDEPENDENCE_LEGACY + ((GameValues.rebels.INDEPENDENCE_LEGACY_RANDOM > 0) ? Game.oR.nextInt(GameValues.rebels.INDEPENDENCE_LEGACY_RANDOM) : 0)));
                    Game.getCiv(civID).fDiplomacy = Game.getCiv(civID).fDiplomacyMax;
                    for (int t = 0; t < TechnologyTree.iTechnologySize; ++t) {
                        if (Game.getCiv(fromCivID).getTechResearched(t)) {
                            Game.getCiv(civID).addTechnology(t, false);
                        }
                    }
                    DiplomacyManager.addTruce(fromCivID, civID);
                    this.sentNotification(fromCivID, civID);
                    Game.revolutionMoveUnits.removeAllRebelsArmiesMovingToCiv(civID);
                    Game.getCiv(civID).updateProvinceBorder();
                    possibleCivs.clear();
                    provinces.clear();
                    return;
                }
                final List<String> civTags = new ArrayList<String>();
                for (int j2 = provinces.size() - 1; j2 >= 0; --j2) {
                    final List<String> tTags = Game.loadSuggestedCivs(provinces.get(j2));
                    for (int k2 = tTags.size() - 1; k2 >= 0; --k2) {
                        if (!civTags.contains(tTags.get(k2))) {
                            civTags.add(tTags.get(k2));
                        }
                    }
                }
                for (int j2 = civTags.size() - 1; j2 >= 0; --j2) {
                    for (int k3 = 1; k3 < Game.getCivsSize(); ++k3) {
                        if (Game.getCiv(k3).getCivTag().equals(civTags.get(j2))) {
                            civTags.remove(j2);
                            break;
                        }
                    }
                }
                if (!civTags.isEmpty()) {
                    final String toAdd = civTags.get(Game.oR.nextInt(civTags.size()));
                    if (this.addCiv(toAdd, provinces, fromCivID)) {
                        civTags.clear();
                        provinces.clear();
                        return;
                    }
                }
                if (GameValues.rebels.DECLARE_INDEPENDENCE_ENABLE_DIFFERENT_GOVERNMENT) {
                    final List<Integer> differentGovernment = new ArrayList<Integer>();
                    for (int j3 = 0; j3 < Game.ideologiesManager.getIdeologiesSize(); ++j3) {
                        if (!Game.ideologiesManager.getIdeology(j3).TRIBAL && !Game.ideologiesManager.getIdeology(j3).REVOLUTIONISTS && !Game.ideologiesManager.getIdeology(j3).CITY_STATE && (Game.ideologiesManager.getIdeology(j3).REQUIRED_TECHNOLOGY < 0 || Game.getCiv(fromCivID).getTechResearched(Game.ideologiesManager.getIdeology(j3).REQUIRED_TECHNOLOGY)) && Game.getCiv(fromCivID).getIdeologyID() != j3) {
                            differentGovernment.add(j3);
                        }
                    }
                    if (differentGovernment.isEmpty()) {
                        for (int j3 = 0; j3 < Game.ideologiesManager.getIdeologiesSize(); ++j3) {
                            if (!Game.ideologiesManager.getIdeology(j3).TRIBAL && !Game.ideologiesManager.getIdeology(j3).REVOLUTIONISTS && !Game.ideologiesManager.getIdeology(j3).CITY_STATE && Game.getCiv(fromCivID).getIdeologyID() != j3) {
                                differentGovernment.add(j3);
                            }
                        }
                    }
                    if (!differentGovernment.isEmpty()) {
                        String tag = Game.getCiv(fromCivID).realTag;
                        for (int g = differentGovernment.size() - 1; g >= 0; --g) {
                            final String tTag = tag + ((Game.ideologiesManager.getIdeology(differentGovernment.get(g)).Extra_Tag.length() > 0) ? ("_" + Game.ideologiesManager.getIdeology(differentGovernment.get(g)).Extra_Tag) : "");
                            for (int j4 = 1; j4 < Game.getCivsSize(); ++j4) {
                                if (Game.getCiv(j4).getCivTag().equals(tTag)) {
                                    differentGovernment.remove(g);
                                    break;
                                }
                            }
                        }
                        if (!differentGovernment.isEmpty()) {
                            final int govID = Game.oR.nextInt(differentGovernment.size());
                            tag += ((Game.ideologiesManager.getIdeology(govID).Extra_Tag.length() > 0) ? ("_" + Game.ideologiesManager.getIdeology(govID).Extra_Tag) : "");
                            if (this.addCiv(tag, provinces, fromCivID)) {
                                civTags.clear();
                                provinces.clear();
                                return;
                            }
                        }
                    }
                }
                if (cleanOccupiedProvinces) {
                    try {
                        for (int j2 = provinces.size() - 1; j2 >= 0; --j2) {
                            Game.getProvince(provinces.get(j2)).setRevulutionaryRisk(0.0f);
                            Game.getProvince(provinces.get(j2)).setOccupiedByCivID(0);
                            Game.getProvince(provinces.get(j2)).resetSiegeData();
                            this.removeOccupiedProvince(provinces.get(j2));
                            try {
                                for (int a = Game.getProvince(provinces.get(j2)).getArmySize() - 1; a >= 0; --a) {
                                    if (Game.getProvince(provinces.get(j2)).getArmy(a).civID < 0) {
                                        Game.revolutionMoveUnits.removeMove(Game.getProvince(provinces.get(j2)).getArmy(a).key);
                                        this.removeArmyPosition(provinces.get(j2), Game.getProvince(provinces.get(j2)).getArmy(a).key);
                                        Game.getProvince(provinces.get(j2)).removeArmy(Game.getProvince(provinces.get(j2)).getArmy(a).key);
                                    }
                                }
                            }
                            catch (final Exception ex) {
                                CFG.exceptionStack(ex);
                            }
                        }
                    }
                    catch (final Exception ex2) {}
                    i = Math.min(i, this.occupiedProvinces.size() - 1);
                }
                provinces.clear();
            }
        }
    }
    
    public boolean addCiv(final String toAdd, final List<Integer> provinces, final int fromCivID) {
        Game.addCivilization(toAdd, provinces.get(0), true, true, true, true, true);
        int civID = -1;
        for (int j = Game.getCivsSize() - 1; j > 0; --j) {
            if (Game.getCiv(j).getCivTag().equals(toAdd)) {
                civID = j;
                break;
            }
        }
        if (civID > 0) {
            for (int j = provinces.size() - 1; j >= 0; --j) {
                Game.getProvince(provinces.get(j)).setRevulutionaryRisk(0.0f);
                Game.getProvince(provinces.get(j)).setOccupiedByCivID(0);
                Game.getProvince(provinces.get(j)).resetSiegeData();
                Game.getProvince(provinces.get(j)).setCivID(civID);
                Game.getProvince(provinces.get(j)).addCore(civID);
                Game.getProvince(provinces.get(j)).setReligion(Game.getCiv(civID).getReligionID());
                this.removeOccupiedProvince(provinces.get(j));
                try {
                    for (int a = Game.getProvince(provinces.get(j)).getArmySize() - 1; a >= 0; --a) {
                        if (Game.getProvince(provinces.get(j)).getArmy(a).civID < 0) {
                            Game.revolutionMoveUnits.removeMove(Game.getProvince(provinces.get(j)).getArmy(a).key);
                            this.removeArmyPosition(provinces.get(j), Game.getProvince(provinces.get(j)).getArmy(a).key);
                            Game.getProvince(provinces.get(j)).removeArmy(Game.getProvince(provinces.get(j)).getArmy(a).key);
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            Game.getCiv(civID).moveCapital_ToLargestProvince();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(civID);
            Game.gameThread.addCivUpdateLegacyPerMonth(civID);
            Game.gameThread.addCivUpdateResearchPerMonth(civID);
            Game.gameThread.addCivUpdateArmyMaintenance(civID);
            Game.getCiv(civID).updateManpowerPerMonth();
            Game.getCiv(civID).updateDiplomacyPerMonth();
            Game.getCiv(civID).setManpower(Math.max(Game.getCiv(civID).fManpower, Game.getCiv(civID).fManpowerMax));
            Game.getCiv(civID).fGold = Math.max(Game.getCiv(civID).fGold, (float)(GameValues.rebels.INDEPENDENCE_GOLD + ((GameValues.rebels.INDEPENDENCE_GOLD_RANDOM > 0) ? Game.oR.nextInt(GameValues.rebels.INDEPENDENCE_GOLD_RANDOM) : 0)));
            Game.getCiv(civID).fLegacy = Math.max(Game.getCiv(civID).fLegacy, (float)(GameValues.rebels.INDEPENDENCE_LEGACY + ((GameValues.rebels.INDEPENDENCE_LEGACY_RANDOM > 0) ? Game.oR.nextInt(GameValues.rebels.INDEPENDENCE_LEGACY_RANDOM) : 0)));
            Game.getCiv(civID).fDiplomacy = Game.getCiv(civID).fDiplomacyMax;
            for (int t = 0; t < TechnologyTree.iTechnologySize; ++t) {
                if (Game.getCiv(fromCivID).getTechResearched(t)) {
                    Game.getCiv(civID).addTechnology(t, false);
                }
            }
            DiplomacyManager.addTruce(fromCivID, civID);
            this.sentNotification(fromCivID, civID);
            if (Game.getCiv(civID).updateArmyRegimentSize() <= 0) {
                Game.mapScenarios.buildStartingArmy(civID);
                Game.getCiv(civID).setAdvantagePoints(Math.min(Game.getCiv(civID).getAdvantagePoints(), GameValues.rebels.INDEPENDENCE_MAX_ADVANTAGE_POINTS));
            }
            Game.getCiv(civID).updateProvinceBorder();
            Game.revolutionMoveUnits.removeAllRebelsArmiesMovingToCiv(civID);
            Game.addSimpleTask(new Game.SimpleTask("LOAD_RULER" + civID, civID) {
                @Override
                public void update() {
                    RulersManager.loadRuler(this.id, Game.getCiv(this.id).getCivTag(), true);
                }
            });
            return true;
        }
        return false;
    }
    
    public void sentNotification(final int fromCivID, final int civID) {
        if (fromCivID == Game.player.iCivID && civID < Game.getCivsSize()) {
            InGame_Info.iCivID = fromCivID;
            InGame_Info.iCivID2 = civID;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("Liberation"), Game.getCiv(civID).getCivName());
            InGame_Info.imgID = Images.infoUnrest;
        }
    }
    
    public final void moveArmies() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_REBELS_ARMIES_STEPS; i < this.iArmyPositionSize; i += GameValues.gameUpdate.GAME_UPDATE_REBELS_ARMIES_STEPS) {
                if (!Game.getProvinceData4(this.armyPosition.get(i).provinceID).isUnderSiege()) {
                    final int armyID = Game.getProvince(this.armyPosition.get(i).provinceID).getArmyKeyID(this.armyPosition.get(i).key);
                    if (armyID >= 0) {
                        if (Game.getProvince(this.armyPosition.get(i).provinceID).getArmy(armyID).iArmyRegimentSize < GameValues.siege.SIEGE_REGIMENTS_MIN) {
                            Game.getProvince(this.armyPosition.get(i).provinceID).removeArmy(armyID);
                        }
                        else {
                            if (Game.getProvince(this.armyPosition.get(i).provinceID).getArmy(armyID).fMorale < GameValues.rebels.REBELS_MAX_MORALE) {
                                Game.getProvince(this.armyPosition.get(i).provinceID).getArmy(armyID).updateMorale_Regiments(GameValues.rebels.REBELS_MAX_MORALE, GameValues.rebels.REBELS_MORALE_RECOVERY);
                            }
                            if (!Game.getProvince(this.armyPosition.get(i).provinceID).getArmy(armyID).inBattle) {
                                if (!Game.getProvince(this.armyPosition.get(i).provinceID).getArmy(armyID).inMovement) {
                                    if (Game.getProvince(this.armyPosition.get(i).provinceID).getSeaProvince()) {
                                        Game.getProvince(this.armyPosition.get(i).provinceID).removeArmy(this.armyPosition.get(i).key);
                                    }
                                    else if (Game.getProvince(this.armyPosition.get(i).provinceID).getCivRegionID() >= 0) {
                                        final List<Integer> possibleToMove = new ArrayList<Integer>();
                                        for (int r = 0; r < Game.getCiv(Game.getProvince(this.armyPosition.get(i).provinceID).getCivID()).getCivRegion(Game.getProvince(this.armyPosition.get(i).provinceID).getCivRegionID()).getProvincesSize(); ++r) {
                                            if (!Game.getProvince(Game.getCiv(Game.getProvince(this.armyPosition.get(i).provinceID).getCivID()).getCivRegion(Game.getProvince(this.armyPosition.get(i).provinceID).getCivRegionID()).getProvince(r)).isOccupied() && Game.getCiv(Game.getProvince(this.armyPosition.get(i).provinceID).getCivID()).getCivRegion(Game.getProvince(this.armyPosition.get(i).provinceID).getCivRegionID()).getProvince(r) != this.armyPosition.get(i).provinceID) {
                                                possibleToMove.add(Game.getCiv(Game.getProvince(this.armyPosition.get(i).provinceID).getCivID()).getCivRegion(Game.getProvince(this.armyPosition.get(i).provinceID).getCivRegionID()).getProvince(r));
                                            }
                                        }
                                        if (possibleToMove.size() > 0) {
                                            int bestID = 0;
                                            float bestDistance = Game.getDistanceFromProvinceToProvince(this.armyPosition.get(i).provinceID, possibleToMove.get(bestID));
                                            for (int j = possibleToMove.size() - 1; j > 0; --j) {
                                                float tDistance = Game.getDistanceFromProvinceToProvince(this.armyPosition.get(i).provinceID, possibleToMove.get(j));
                                                if (tDistance < bestDistance) {
                                                    if (Game.revolutionMoveUnits.isArmyAlreadyMoving(this.armyPosition.get(i).provinceID, possibleToMove.get(j))) {
                                                        tDistance *= 10.0f;
                                                    }
                                                    if (tDistance < bestDistance) {
                                                        bestDistance = tDistance;
                                                        bestID = j;
                                                    }
                                                }
                                            }
                                            Game.revolutionMoveUnits.newMove(this.armyPosition.get(i).provinceID, possibleToMove.get(bestID), this.armyPosition.get(i).key, 0, false);
                                            possibleToMove.clear();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        this.armyPosition.remove(i);
                        this.iArmyPositionSize = this.armyPosition.size();
                        --i;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void addOccupiedProvince(final int provinceID) {
        for (int i = this.occupiedProvinces.size() - 1; i >= 0; --i) {
            if (this.occupiedProvinces.get(i).p == provinceID) {
                return;
            }
        }
        this.occupiedProvinces.add(new OccupiedProvince(provinceID, Game_Calendar.TURN_ID));
    }
    
    public void removeOccupiedProvince(final int provinceID) {
        for (int i = this.occupiedProvinces.size() - 1; i >= 0; --i) {
            if (this.occupiedProvinces.get(i).p == provinceID) {
                this.occupiedProvinces.remove(i);
                return;
            }
        }
    }
    
    public List<Integer> getRevolutionaryProvinces(final int civID) {
        final List<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getRevulutionaryRisk() > 0.0f) {
                out.add(Game.getCiv(civID).getProvinceID(i));
            }
        }
        return out;
    }
    
    public List<Integer> getRevolutionaryProvinces_Sorted(final int civID) {
        final List<Integer> list = this.getRevolutionaryProvinces(civID);
        final List<Integer> out = new ArrayList<Integer>();
        while (list.size() > 0) {
            int bestID = 0;
            for (int i = list.size() - 1; i > 0; --i) {
                if (Game.getProvince(list.get(bestID)).getRevulutionaryRisk() < Game.getProvince(list.get(i)).getRevulutionaryRisk()) {
                    bestID = i;
                }
            }
            out.add(list.get(bestID));
            list.remove(bestID);
        }
        return out;
    }
    
    public float getDecreaseRevolutionaryRisk_CostGold(final int nProvinceID) {
        return Math.max(0.0f, GameValues.province.UNREST_DECREASE_COST_GOLD * Game.getProvince(nProvinceID).getRevulutionaryRisk());
    }
    
    public float getDecreaseRevolutionaryRisk_CostLegacy(final int nProvinceID) {
        return Math.max(0.0f, GameValues.province.UNREST_DECREASE_COST_LEGACY * Game.getProvince(nProvinceID).getRevulutionaryRisk());
    }
    
    public boolean decreaseRevolutionaryRisk(final int civID, final int provinceID) {
        if (civID != Game.getProvince(provinceID).getCivID()) {
            return false;
        }
        if (Game.getProvince(provinceID).getRevulutionaryRisk() <= 0.0f) {
            return false;
        }
        if (Game.getCiv(civID).fGold < GameValues.province.UNREST_DECREASE_COST_GOLD) {
            return false;
        }
        if (Game.getCiv(civID).fLegacy < GameValues.province.UNREST_DECREASE_COST_LEGACY) {
            return false;
        }
        float possibleToDecrease = Game.getProvince(provinceID).getRevulutionaryRisk();
        possibleToDecrease = Math.min(possibleToDecrease, Game.getCiv(civID).fGold / GameValues.province.UNREST_DECREASE_COST_GOLD);
        possibleToDecrease = Math.min(possibleToDecrease, Game.getCiv(civID).fLegacy / GameValues.province.UNREST_DECREASE_COST_LEGACY);
        if (possibleToDecrease > 0.0f) {
            Game.getProvince(provinceID).setRevulutionaryRisk(Game.getProvince(provinceID).getRevulutionaryRisk() - possibleToDecrease);
            final Civilization civ = Game.getCiv(civID);
            civ.fGold -= possibleToDecrease * GameValues.province.UNREST_DECREASE_COST_GOLD;
            final Civilization civ2 = Game.getCiv(civID);
            civ2.fLegacy -= possibleToDecrease * GameValues.province.UNREST_DECREASE_COST_LEGACY;
        }
        return true;
    }
    
    public final void addArmyPosition(final int nProvinceID, final String key) {
        try {
            for (int i = this.iArmyPositionSize - 1; i >= 0; --i) {
                if (this.armyPosition.get(i).provinceID == nProvinceID && this.armyPosition.get(i).key.equals(key)) {
                    return;
                }
            }
            this.armyPosition.add(new ArmyPosition(nProvinceID, key));
            this.iArmyPositionSize = this.armyPosition.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeArmyPosition(final int nProvinceID, final String key) {
        try {
            for (int i = 0; i < this.iArmyPositionSize; ++i) {
                if (this.armyPosition.get(i).provinceID == nProvinceID && this.armyPosition.get(i).key.equals(key)) {
                    this.armyPosition.remove(i);
                    this.iArmyPositionSize = this.armyPosition.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void updateArmyPosition(final String key, final int nProvinceID) {
        try {
            for (int i = 0; i < this.iArmyPositionSize; ++i) {
                if (this.armyPosition.get(i).key.equals(key)) {
                    this.armyPosition.get(i).provinceID = nProvinceID;
                    return;
                }
            }
            this.armyPosition.add(new ArmyPosition(nProvinceID, key));
            this.iArmyPositionSize = this.armyPosition.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
