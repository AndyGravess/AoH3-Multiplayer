// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Missions;

import aoc.kingdoms.lukasz.events.Event;

public class Mission
{
    public int ID;
    public String Name;
    public String ImageName;
    public int ImageID;
    public String MissionEvent;
    public Event event;
    public int TreeColumn;
    public int TreeRow;
    public int RequiredMission;
    public int RequiredMission2;
    public int AI;
    
    public Mission() {
        this.ImageID = 0;
        this.RequiredMission = -1;
        this.RequiredMission2 = -1;
    }
}
