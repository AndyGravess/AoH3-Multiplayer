// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_Resource_PriceChangeRandomDown extends EventOutcome
{
    public int iResourceID;
    public int changePrice;
    public int timeInMonths;
    public int changePrice_Min;
    public int changePrice_Random;
    public int timeInMonths_Min;
    public int timeInMonths_Random;
    
    public EventOutcome_Resource_PriceChangeRandomDown(final int changePrice_Min, final int changePrice_Random, final int timeInMonths_Min, final int timeInMonths_Random) {
        this.changePrice_Min = changePrice_Min;
        this.changePrice_Random = changePrice_Random;
        this.timeInMonths_Min = Math.max(1, timeInMonths_Min);
        this.timeInMonths_Random = Math.max(0, timeInMonths_Random);
        this.changePrice = Math.abs(changePrice_Min + ((changePrice_Random > 0) ? Game.oR.nextInt(changePrice_Random + 1) : 0)) * -1;
        this.timeInMonths = timeInMonths_Min + ((timeInMonths_Random > 0) ? Game.oR.nextInt(timeInMonths_Random + 1) : 0);
    }
    
    @Override
    public void update() {
        try {
            final List<Integer> possibleResources = new ArrayList<Integer>();
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                if (ResourcesManager.lResources.get(i).RequiredTechID < 0 || Game.getCiv(Game.player.iCivID).getTechResearched(ResourcesManager.lResources.get(i).RequiredTechID)) {
                    possibleResources.add(i);
                }
            }
            if (possibleResources.size() > 0) {
                this.iResourceID = possibleResources.get(Game.oR.nextInt(possibleResources.size()));
                possibleResources.clear();
            }
            this.changePrice = Math.abs(this.changePrice_Min + ((this.changePrice_Random > 0) ? Game.oR.nextInt(this.changePrice_Random + 1) : 0)) * -1;
            this.timeInMonths = this.timeInMonths_Min + ((this.timeInMonths_Random > 0) ? Game.oR.nextInt(this.timeInMonths_Random + 1) : 0);
            ResourcesManager.setPriceChangePerc(this.iResourceID, this.changePrice / 100.0f, Game_Calendar.TURN_ID + 31 * this.timeInMonths);
            Game.player.addNotification(new Notification(Notification.Notification_Type.PRICE_CHANGE, ResourcesManager.lResources.get(this.iResourceID).Name + ": " + ((this.changePrice > 0) ? "+" : "") + CFG.getPrecision2((float)this.changePrice, 100) + "%", this.iResourceID, Game_Calendar.TURN_ID, (this.changePrice > 0) ? Notification.Notification_BG.GREEN : Notification.Notification_BG.RED));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return ResourcesManager.lResources.get(this.iResourceID).Name + ": " + Game.lang.get("PriceChange") + ": " + ((this.changePrice > 0) ? "+" : "") + CFG.getPrecision2((float)this.changePrice, 100) + "%";
    }
    
    @Override
    public String getStringRight() {
        return " " + Game.lang.get("APriceModifierIsAppliedUntilX", Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + 31 * this.timeInMonths));
    }
    
    @Override
    public int getImage() {
        return Images.goods;
    }
    
    @Override
    public int getValue1() {
        return this.iResourceID;
    }
    
    @Override
    public float getValue2() {
        return (float)this.changePrice;
    }
}
