// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class PlayerData
{
    public List<String> pinnedArmies;
    public List<Integer> pinnedProvinces;
    public PlayerInvasion invasion;
    public PlayerEspionage espionage;
    public List<PlayerActiveEvent> activeEvents;
    public PlayerTechQueue techQueue;
    public boolean wasVassalized;
    public int reorganizeArmyStep;
    public int armyImgID;
    public int lowArmyTurnID;
    
    public PlayerData() {
        this.pinnedArmies = new ArrayList<String>();
        this.pinnedProvinces = new ArrayList<Integer>();
        this.invasion = new PlayerInvasion();
        this.espionage = new PlayerEspionage();
        this.activeEvents = new ArrayList<PlayerActiveEvent>();
        this.techQueue = new PlayerTechQueue();
        this.wasVassalized = false;
        this.reorganizeArmyStep = 0;
        this.armyImgID = 0;
        this.lowArmyTurnID = -1;
    }
    
    public void loadUpdate() {
        Game.player.iPinnedArmiesSize = this.pinnedArmies.size();
        Game.player.iPinnedProvincesSize = this.pinnedProvinces.size();
        Game.player.iActiveEventsSize = this.activeEvents.size();
    }
}
