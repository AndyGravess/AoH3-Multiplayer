// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class PlayerTechQueue
{
    public List<Integer> lTechQueue;
    
    public PlayerTechQueue() {
        this.lTechQueue = new ArrayList<Integer>();
    }
    
    public final void buildTechQueue(final int iTechID) {
        this.lTechQueue.clear();
        this.lTechQueue.add(iTechID);
        this.buildTechQueue1(iTechID);
        this.buildTechQueue2(iTechID);
        final List<Integer> nQueue = new ArrayList<Integer>();
        final List<Integer> nAvailable = new ArrayList<Integer>();
        for (int i = this.lTechQueue.size() - 1; i >= 0; --i) {
            if (Game.getCiv(Game.player.iCivID).getAvailableToResearch(this.lTechQueue.get(i))) {
                nAvailable.add(this.lTechQueue.get(i));
                this.lTechQueue.remove(i);
            }
        }
        while (nAvailable.size() > 0) {
            int bestID = 0;
            for (int j = nAvailable.size() - 1; j > 0; --j) {
                if (TechnologyTree.lTechnology.get(nAvailable.get(j)).getResearchCost() < TechnologyTree.lTechnology.get(nAvailable.get(bestID)).getResearchCost() || TechnologyTree.lTechnology.get(nAvailable.get(j)).TreeColumn < TechnologyTree.lTechnology.get(nAvailable.get(bestID)).TreeColumn) {
                    bestID = j;
                }
            }
            nQueue.add(nAvailable.get(bestID));
            nAvailable.remove(bestID);
        }
        while (this.lTechQueue.size() > 0) {
            int bestID = 0;
            for (int j = this.lTechQueue.size() - 1; j > 0; --j) {
                if (TechnologyTree.lTechnology.get(this.lTechQueue.get(j)).TreeColumn < TechnologyTree.lTechnology.get(this.lTechQueue.get(bestID)).TreeColumn) {
                    bestID = j;
                }
            }
            nQueue.add(this.lTechQueue.get(bestID));
            this.lTechQueue.remove(bestID);
        }
        this.lTechQueue = nQueue;
    }
    
    private final void buildTechQueue1(final int iTechID) {
        if (TechnologyTree.lTechnology.get(iTechID).RequiredTech >= 0 && !Game.getCiv(Game.player.iCivID).getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech)) {
            this.addTechQueue(TechnologyTree.lTechnology.get(iTechID).RequiredTech);
            this.buildTechQueue1(TechnologyTree.lTechnology.get(iTechID).RequiredTech);
            this.buildTechQueue2(TechnologyTree.lTechnology.get(iTechID).RequiredTech);
        }
    }
    
    private final void buildTechQueue2(final int iTechID) {
        if (TechnologyTree.lTechnology.get(iTechID).RequiredTech2 >= 0 && !Game.getCiv(Game.player.iCivID).getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech2)) {
            this.addTechQueue(TechnologyTree.lTechnology.get(iTechID).RequiredTech2);
            this.buildTechQueue1(TechnologyTree.lTechnology.get(iTechID).RequiredTech2);
            this.buildTechQueue2(TechnologyTree.lTechnology.get(iTechID).RequiredTech2);
        }
    }
    
    private final boolean addTechQueue(final int iTechID) {
        if (iTechID < 0) {
            return false;
        }
        for (int i = this.lTechQueue.size() - 1; i >= 0; --i) {
            if (this.lTechQueue.get(i) == iTechID) {
                return false;
            }
        }
        this.lTechQueue.add(iTechID);
        return true;
    }
    
    public final int getTechQueue() {
        final List<Integer> possibleToResearch = new ArrayList<Integer>();
        int bestID = 0;
        for (int i = this.lTechQueue.size() - 1; i >= 0; --i) {
            if (Game.getCiv(Game.player.iCivID).getAvailableToResearch(this.lTechQueue.get(i)) && !Game.getCiv(Game.player.iCivID).getTechResearched(this.lTechQueue.get(i))) {
                possibleToResearch.add(this.lTechQueue.get(i));
            }
        }
        if (possibleToResearch.size() > 0) {
            for (int i = possibleToResearch.size() - 1; i >= 1; --i) {
                if (TechnologyTree.lTechnology.get(possibleToResearch.get(i)).getResearchCost() < TechnologyTree.lTechnology.get(possibleToResearch.get(bestID)).getResearchCost() || TechnologyTree.lTechnology.get(possibleToResearch.get(i)).TreeColumn < TechnologyTree.lTechnology.get(possibleToResearch.get(bestID)).TreeColumn) {
                    bestID = i;
                }
            }
            for (int i = this.lTechQueue.size() - 1; i >= 0; --i) {
                if (this.lTechQueue.get(i) == possibleToResearch.get(bestID)) {
                    this.lTechQueue.remove(i);
                    break;
                }
            }
            return possibleToResearch.get(bestID);
        }
        return -1;
    }
    
    public int getTechIsInQueue(final int iTechID) {
        for (int i = this.lTechQueue.size() - 1; i >= 0; --i) {
            if (this.lTechQueue.get(i) == iTechID) {
                return i;
            }
        }
        return -1;
    }
    
    public void clearTechQueue() {
        this.lTechQueue.clear();
    }
}
