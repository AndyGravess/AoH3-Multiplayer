// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.war;

import aoh.kingdoms.history.map.province.Province;
import aoh.kingdoms.history.map.moveUnits.MoveUnits;
import java.util.List;
import aoh.kingdoms.history.menusInGame.InGame_War;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.AI.AI_PeaceTreaty;
import aoh.kingdoms.history.mainGame.GameValues;
import java.util.Iterator;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import java.util.concurrent.ConcurrentHashMap;

public class WarManager
{
    public static ConcurrentHashMap<String, War> lWars;
    public static int iWarsSize;
    public static int UPDATE_WARS_CHECK_PROVINCES;
    
    public static final String addWar(final int nAggressor, final int nDefender, final boolean conquerVassal, final boolean isCoalition) {
        if (nAggressor == 0 || nDefender == 0) {
            return null;
        }
        if (Game.getCiv(nAggressor).getNumOfProvinces() == 0 || Game.getCiv(nDefender).getNumOfProvinces() == 0) {
            return null;
        }
        try {
            for (final War nWar : WarManager.lWars.values()) {
                if (nWar.areInThisWar(nAggressor, nDefender)) {
                    return null;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final String tKey = CFG.extraRandomTag();
        WarManager.lWars.put(tKey, new War(nAggressor, nDefender, tKey, conquerVassal, isCoalition));
        WarManager.iWarsSize = WarManager.lWars.size();
        Game.getCiv(nAggressor).diplomacy.addWar(tKey, nAggressor);
        Game.getCiv(nDefender).diplomacy.addWar(tKey, nDefender);
        return tKey;
    }
    
    public static String getWarKey(final int iCivA, final int iCivB) {
        try {
            for (final War nWar : WarManager.lWars.values()) {
                if (nWar.areInThisWar(iCivA, iCivB)) {
                    return nWar.key;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    public static final void updateWars_Peace() {
        try {
            for (final War nWar : WarManager.lWars.values()) {
                if (Math.abs(nWar.warScore) >= GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE) {
                    final int tCivID = nWar.lAggressors.get(0).iCivID;
                    final int tCivID2 = nWar.lDefenders.get(0).iCivID;
                    if (nWar.warScore > 0.0f) {
                        AI_PeaceTreaty.peaceTreaty(nWar.key, tCivID, tCivID2);
                    }
                    else {
                        AI_PeaceTreaty.peaceTreaty(nWar.key, tCivID2, tCivID);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateWars_WhitePeace() {
        try {
            final List<String> toRemove = new ArrayList<String>();
            for (final War nWar : WarManager.lWars.values()) {
                try {
                    if ((nWar.lastFight_TurnID + GameValues.war.WAR_AUTO_WHITE_PEACE_IF_NOTHING_HAPPENS_IN_WAR_DAYS >= Game_Calendar.TURN_ID || Math.abs(nWar.warScore) >= GameValues.war.WAR_AUTO_WHITE_PEACE_IF_WARSCORE_BELOW) && (nWar.iWarTurnID + GameValues.war.WAR_AUTO_WHITE_PEACE_AFTER_X_DAYS_OF_WAR >= Game_Calendar.TURN_ID || Math.abs(nWar.warScore) >= GameValues.war.WAR_AUTO_WHITE_PEACE_AFTER_X_DAYS_OF_WAR_IF_WARSCORE_BELOW) && Game.getCiv(nWar.lAggressors.get(0).iCivID).getNumOfProvinces() > 0 && Game.getCiv(nWar.lDefenders.get(0).iCivID).getNumOfProvinces() > 0) {
                        continue;
                    }
                    final int tCivID = nWar.lAggressors.get(0).iCivID;
                    final int tCivID2 = nWar.lDefenders.get(0).iCivID;
                    if (DiplomacyManager.whitePeace(nWar.key) && (tCivID == Game.player.iCivID || tCivID2 == Game.player.iCivID)) {
                        InGame_Info.iCivID = tCivID;
                        InGame_Info.iCivID2 = tCivID2;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("WhitePeace"), Game_Calendar.getCurrentDate());
                        InGame_Info.imgID = Images.infoDiplomacy;
                        Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_Wars();
                            }
                        });
                        if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(nWar.key)) {
                            Game.menuManager.setVisibleInGame_War(false);
                        }
                    }
                    toRemove.add(nWar.key);
                }
                catch (final Exception ex2) {}
            }
            for (int i = toRemove.size() - 1; i >= 0; --i) {
                WarManager.lWars.remove(toRemove.get(i));
            }
            toRemove.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateWars_TickingWarScore() {
        try {
            if (WarManager.UPDATE_WARS_CHECK_PROVINCES++ > GameValues.gameUpdate.GAME_UPDATE_WAR_ALL_PROVINCES_OCCUPIED_MONTHS) {
                WarManager.UPDATE_WARS_CHECK_PROVINCES = 0;
                for (final War nWar : WarManager.lWars.values()) {
                    nWar.updateWars_AllProvincesOccupied();
                    nWar.updateWars_TickingWarScore();
                }
            }
            else {
                for (final War nWar : WarManager.lWars.values()) {
                    nWar.updateWars_TickingWarScore();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final int retreatToProvinceID(final int nCivID, final int fromProvinceID) {
        if (fromProvinceID < 0) {
            return -1;
        }
        final List<Integer> was = new ArrayList<Integer>();
        was.add(fromProvinceID);
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).wasRetreat = false;
        }
        Game.getProvince(fromProvinceID).wasRetreat = true;
        final List<Integer> in = new ArrayList<Integer>();
        final List<List<Integer>> inPath = new ArrayList<List<Integer>>();
        for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringProvincesSize(); ++j) {
            if (MoveUnits.canBeUsedInPath(nCivID, Game.getProvince(fromProvinceID).getNeighboringProvinces(j), true, fromProvinceID)) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID()).wasRetreat = true;
            }
        }
        if (!Game.getProvince(fromProvinceID).getSeaProvince()) {
            for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); ++j) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID()).wasRetreat = true;
            }
        }
        return buildPath(nCivID, was, in, inPath, fromProvinceID, nCivID);
    }
    
    protected static int buildPath(final int nCivID, final List<Integer> was, final List<Integer> in, final List<List<Integer>> inPath, final int from, final int lookingForCivID) {
        final List<Integer> nIN = new ArrayList<Integer>();
        final List<List<Integer>> nINPath = new ArrayList<List<Integer>>();
        for (int i = 0; i < in.size(); ++i) {
            final Province province = Game.getProvince(in.get(i));
            if ((province.getCivID() == lookingForCivID || Game.getCiv(province.getCivID()).getPuppetOfCivID() == lookingForCivID || province.getCivID() == Game.getCiv(lookingForCivID).getPuppetOfCivID()) && !province.isOccupied()) {
                clearWas(was);
                return in.get(i);
            }
        }
        for (int i = 0; i < in.size(); ++i) {
            final Province province = Game.getProvince(in.get(i));
            for (int j = 0; j < province.getNeighboringProvincesSize(); ++j) {
                if (!Game.getProvince(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID()).wasRetreat && MoveUnits.canBeUsedInPath(nCivID, Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID(), false, from)) {
                    if ((Game.getProvince(province.getNeighboringProvinces(j)).getCivID() == lookingForCivID || Game.getCiv(Game.getProvince(province.getNeighboringProvinces(j)).getCivID()).getPuppetOfCivID() == lookingForCivID || Game.getProvince(province.getNeighboringProvinces(j)).getCivID() == Game.getCiv(lookingForCivID).getPuppetOfCivID()) && !Game.getProvince(province.getNeighboringProvinces(j)).isOccupied()) {
                        clearWas(was);
                        return province.getNeighboringProvinces(j);
                    }
                    nIN.add(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID());
                    final List<Integer> tPL = new ArrayList<Integer>();
                    for (int u = 0; u < inPath.get(i).size(); ++u) {
                        tPL.add(inPath.get(i).get(u));
                    }
                    tPL.add(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID());
                    nINPath.add(tPL);
                    Game.getProvince(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID()).wasRetreat = true;
                    was.add(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID());
                }
            }
            if (!province.getSeaProvince()) {
                for (int j = 0; j < province.getNeighboringSeaProvincesSize(); ++j) {
                    if (!Game.getProvince(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID()).wasRetreat) {
                        if (Game.getProvince(province.getNeighboringSeaProvinces(j)).getCivID() == lookingForCivID && !Game.getProvince(province.getNeighboringSeaProvinces(j)).isOccupied()) {
                            return province.getNeighboringSeaProvinces(j);
                        }
                        nIN.add(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID());
                        final List<Integer> tPL = new ArrayList<Integer>();
                        for (int u = 0; u < inPath.get(i).size(); ++u) {
                            tPL.add(inPath.get(i).get(u));
                        }
                        tPL.add(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        Game.getProvince(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID()).wasRetreat = true;
                        was.add(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID());
                    }
                }
            }
        }
        try {
            return buildPath(nCivID, was, nIN, nINPath, from, lookingForCivID);
        }
        catch (final StackOverflowError ex) {
            clearWas(was);
            return -1;
        }
    }
    
    protected static final void clearWas(final List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            Game.getProvince(was.get(i)).wasRetreat = false;
        }
    }
    
    public static final void buildWars_Load() {
        try {
            for (final War nWar : WarManager.lWars.values()) {
                for (int i = 0; i < nWar.lAggressors.size(); ++i) {
                    Game.getCiv(nWar.lAggressors.get(i).iCivID).diplomacy.addWar(nWar.key, nWar.lAggressors.get(i).iCivID);
                }
                for (int i = 0; i < nWar.lDefenders.size(); ++i) {
                    Game.getCiv(nWar.lDefenders.get(i).iCivID).diplomacy.addWar(nWar.key, nWar.lDefenders.get(i).iCivID);
                }
                for (int i = 0; i < nWar.lAggressors.size(); ++i) {
                    for (int j = 0; j < nWar.lDefenders.size(); ++j) {
                        Game.getCiv(nWar.lAggressors.get(i).iCivID).diplomacy.addAtWar(nWar.lDefenders.get(j).iCivID);
                        Game.getCiv(nWar.lDefenders.get(j).iCivID).diplomacy.addAtWar(nWar.lAggressors.get(i).iCivID);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static String getWarName(final String key) {
        try {
            final int warNameID = key.charAt(0) % GameValues.war.WAR_NAMES.length;
            return Game.lang.get(GameValues.war.WAR_NAMES[warNameID]);
        }
        catch (final Exception ex) {
            return Game.lang.get("WarOverview");
        }
    }
    
    public static final void clearData() {
        WarManager.lWars.clear();
        WarManager.iWarsSize = 0;
    }
    
    static {
        WarManager.lWars = new ConcurrentHashMap<String, War>();
        WarManager.iWarsSize = 0;
        WarManager.UPDATE_WARS_CHECK_PROVINCES = 0;
    }
}
