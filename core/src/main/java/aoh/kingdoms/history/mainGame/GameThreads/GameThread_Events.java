// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.GameThreads;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;

public class GameThread_Events extends Thread
{
    public boolean running;
    public int iLastUpdateTurnID;
    public long timeSleep;
    public static int THREAD_TURN_ID;
    
    public GameThread_Events() {
        this.running = true;
        this.iLastUpdateTurnID = 0;
    }
    
    @Override
    public void run() {
        while (this.running) {
            try {
                try {
                    this.timeSleep = System.currentTimeMillis();
                    if (Game.menuManager.getInGame() && Game_Calendar.TURN_ID != this.iLastUpdateTurnID) {
                        EventsManager.runEvents(++GameThread_Events.THREAD_TURN_ID);
                        EventsManager.runEvents_Global(GameThread_Events.THREAD_TURN_ID);
                        Game.player.updateEvents();
                        this.iLastUpdateTurnID = Game_Calendar.TURN_ID;
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    Thread.sleep(Math.max(1L, 10L - (System.currentTimeMillis() - this.timeSleep)));
                }
                catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    static {
        GameThread_Events.THREAD_TURN_ID = 0;
    }
}
