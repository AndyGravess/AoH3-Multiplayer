// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.GameThreads;

import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.concurrent.ConcurrentLinkedDeque;

public class GameThread_Update extends Thread
{
    public boolean running;
    private ConcurrentLinkedDeque<Game.SimpleTask> simpleTasks;
    public int iLastUpdateTurnID;
    public long timeSleep;
    
    public GameThread_Update() {
        this.running = true;
        this.simpleTasks = new ConcurrentLinkedDeque<Game.SimpleTask>();
        this.iLastUpdateTurnID = -1;
    }
    
    public final void updateSimpleTask() {
        try {
            for (int i = this.simpleTasks.size() - 1; i >= 0; --i) {
                try {
                    this.simpleTasks.remove().update();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public final void addSimpleTask(final Game.SimpleTask nSimpleTask) {
        if (this.simpleTasks.contains(nSimpleTask)) {
            return;
        }
        this.simpleTasks.add(nSimpleTask);
    }
    
    public final void addSimpleTask_First(final Game.SimpleTask nSimpleTask) {
        if (this.simpleTasks.contains(nSimpleTask)) {
            return;
        }
        this.simpleTasks.addFirst(nSimpleTask);
    }
    
    public void clearData() {
        this.simpleTasks.clear();
        this.iLastUpdateTurnID = 0;
    }
    
    @Override
    public void run() {
        while (this.running) {
            try {
                try {
                    this.timeSleep = System.currentTimeMillis();
                    Game.updateGame();
                    this.updateSimpleTask();
                    if (Game.menuManager.getInGame() && Game_Calendar.TURN_ID != this.iLastUpdateTurnID) {
                        if (Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_CURRENT_SITUATION_EVERY_X_DAYS == 0) {
                            Game.player.currSituation.updateCurrentSituation();
                        }
                        Game.player.updateNotifications();
                        Game.player.updateMessages();
                        this.iLastUpdateTurnID = Game_Calendar.TURN_ID;
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    Thread.sleep(Math.max(1L, 15L - (System.currentTimeMillis() - this.timeSleep)));
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
}
