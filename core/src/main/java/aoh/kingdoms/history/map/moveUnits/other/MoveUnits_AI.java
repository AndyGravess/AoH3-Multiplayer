// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits.other;

import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class MoveUnits_AI
{
    public List<Integer> lRoute;
    public int iRouteSize;
    
    public MoveUnits_AI(final int nCivID, final int iFromProvinceID, final int iToProvinceID) {
        this.lRoute = new ArrayList<Integer>();
        this.iRouteSize = 0;
        this.buildRoute(nCivID, iFromProvinceID, iToProvinceID);
    }
    
    protected boolean buildRoute(final int nCivID, final int fromProvinceID, final int toProvinceID) {
        this.lRoute.clear();
        if (fromProvinceID < 0 || toProvinceID < 0 || Game.getProvince(toProvinceID).getWasteland() >= 0) {
            return false;
        }
        final List<Integer> was = new ArrayList<Integer>();
        was.add(fromProvinceID);
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).wasAI = false;
        }
        Game.getProvince(fromProvinceID).wasAI = true;
        final List<Integer> in = new ArrayList<Integer>();
        final List<List<Integer>> inPath = new ArrayList<List<Integer>>();
        for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringProvincesSize(); ++j) {
            if (this.canBeUsedInPath(nCivID, Game.getProvince(fromProvinceID).getNeighboringProvinces(j))) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID()).wasAI = true;
            }
        }
        if (!Game.getProvince(fromProvinceID).getSeaProvince()) {
            for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); ++j) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID()).wasAI = true;
            }
        }
        this.buildPath(nCivID, was, in, inPath, fromProvinceID, toProvinceID, true);
        return true;
    }
    
    public boolean isFriendlyProvince(final int nCivID, final int toProvinceID) {
        return Game.getProvince(toProvinceID).getCivID() == nCivID;
    }
    
    public boolean canBeUsedInPath(final int nCivID, final int nProvinceID) {
        return !Game.getCiv(Game.getProvince(nProvinceID).getCivID()).diplomacy.isRival(nCivID) && Game.getProvince(nProvinceID).getWasteland() < 0;
    }
    
    protected boolean buildPath(final int nCivID, final List<Integer> was, final List<Integer> in, final List<List<Integer>> inPath, final int from, final int lookingFor, final boolean forDirection) {
        final List<Integer> nIN = new ArrayList<Integer>();
        final List<List<Integer>> nINPath = new ArrayList<List<Integer>>();
        for (int i = 0; i < in.size(); ++i) {
            if (Game.getProvince(in.get(i)).getProvinceID() == lookingFor) {
                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                this.clearWas(was);
                return true;
            }
        }
        if (forDirection) {
            for (int i = 0; i < in.size(); ++i) {
                for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringProvincesSize(); ++j) {
                    if (this.canBeUsedInPath(nCivID, Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()) && !Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasAI) {
                        if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                            this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                            this.clearWas(was);
                            return true;
                        }
                        nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        final List<Integer> tPL = new ArrayList<Integer>();
                        for (int u = 0; u < inPath.get(i).size(); ++u) {
                            tPL.add(inPath.get(i).get(u));
                        }
                        tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasAI = true;
                        was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                    }
                }
                if (!Game.getProvince(in.get(i)).getSeaProvince()) {
                    for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                        if (!Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasAI) {
                            if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                                this.clearWas(was);
                                return true;
                            }
                            nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            final List<Integer> tPL = new ArrayList<Integer>();
                            for (int u = 0; u < inPath.get(i).size(); ++u) {
                                tPL.add(inPath.get(i).get(u));
                            }
                            tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            nINPath.add(tPL);
                            Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasAI = true;
                            was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < in.size(); ++i) {
                for (int j = Game.getProvince(in.get(i)).getNeighboringProvincesSize() - 1; j >= 0; --j) {
                    if (this.canBeUsedInPath(nCivID, Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()) && !Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasAI) {
                        if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                            this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                            this.clearWas(was);
                            return true;
                        }
                        nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        final List<Integer> tPL = new ArrayList<Integer>();
                        for (int u = 0; u < inPath.get(i).size(); ++u) {
                            tPL.add(inPath.get(i).get(u));
                        }
                        tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasAI = true;
                        was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                    }
                }
                if (!Game.getProvince(in.get(i)).getSeaProvince()) {
                    for (int j = Game.getProvince(in.get(i)).getNeighboringSeaProvincesSize() - 1; j >= 0; --j) {
                        if (!Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasAI) {
                            if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                                this.clearWas(was);
                                return true;
                            }
                            nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            final List<Integer> tPL = new ArrayList<Integer>();
                            for (int u = 0; u < inPath.get(i).size(); ++u) {
                                tPL.add(inPath.get(i).get(u));
                            }
                            tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            nINPath.add(tPL);
                            Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasAI = true;
                            was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                        }
                    }
                }
            }
        }
        try {
            return this.buildPath(nCivID, was, nIN, nINPath, from, lookingFor, !forDirection);
        }
        catch (final StackOverflowError ex) {
            this.clearWas(was);
            return false;
        }
    }
    
    protected final void clearWas(final List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            Game.getProvince(was.get(i)).wasAI = false;
        }
    }
    
    protected final void setPath(final int p1, final int p2, final List<Integer> lPath, final int toProvinceID, final int fromProvinceID) {
        this.lRoute.add(fromProvinceID);
        for (int i = 0; i < lPath.size(); ++i) {
            this.lRoute.add(lPath.get(i));
        }
        if (toProvinceID != this.lRoute.get(this.lRoute.size() - 1)) {
            this.lRoute.add(toProvinceID);
        }
        this.iRouteSize = this.lRoute.size();
    }
    
    public int getFromProvinceID() {
        return this.lRoute.get(0);
    }
    
    public int getToProvinceID() {
        return this.lRoute.get(1);
    }
    
    public int getToProvinceLastID() {
        return this.lRoute.get(this.iRouteSize - 1);
    }
}
