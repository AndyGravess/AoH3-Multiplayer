// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.ResourcesManager;

public class EventOutcome_Resource_PriceChangeGroup extends EventOutcome
{
    public int iGroupID;
    public int changePrice;
    public int timeInMonths;
    public int changePrice_Min;
    public int changePrice_Random;
    public int timeInMonths_Min;
    public int timeInMonths_Random;
    
    public EventOutcome_Resource_PriceChangeGroup(final int iGroupID, final int changePrice_Min, final int changePrice_Random, final int timeInMonths_Min, final int timeInMonths_Random) {
        this.iGroupID = Math.max(0, Math.min(ResourcesManager.iResourcesSize - 1, iGroupID));
        this.changePrice_Min = changePrice_Min;
        this.changePrice_Random = changePrice_Random;
        this.timeInMonths_Min = Math.max(1, timeInMonths_Min);
        this.timeInMonths_Random = Math.max(0, timeInMonths_Random);
        this.changePrice = (changePrice_Min + ((changePrice_Random > 0) ? Game.oR.nextInt(changePrice_Random + 1) : 0)) * ((Game.oR.nextInt(100) < GameValues.events.EVENT_CHANGE_PRICE_INCREASE_CHANCE) ? 1 : -1);
        this.timeInMonths = timeInMonths_Min + ((timeInMonths_Random > 0) ? Game.oR.nextInt(timeInMonths_Random + 1) : 0);
    }
    
    @Override
    public void update() {
        try {
            this.changePrice = (this.changePrice_Min + ((this.changePrice_Random > 0) ? Game.oR.nextInt(this.changePrice_Random + 1) : 0)) * ((Game.oR.nextInt(100) < GameValues.events.EVENT_CHANGE_PRICE_INCREASE_CHANCE) ? 1 : -1);
            this.timeInMonths = this.timeInMonths_Min + ((this.timeInMonths_Random > 0) ? Game.oR.nextInt(this.timeInMonths_Random + 1) : 0);
            int addNotification = 0;
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                if (ResourcesManager.lResources.get(i).GroupID == this.iGroupID && (ResourcesManager.lResources.get(i).RequiredTechID < 0 || Game.getCiv(Game.player.iCivID).getTechResearched(ResourcesManager.lResources.get(i).RequiredTechID))) {
                    ResourcesManager.setPriceChangePerc(i, this.changePrice / 100.0f, Game_Calendar.TURN_ID + 31 * this.timeInMonths);
                    ++addNotification;
                }
            }
            if (addNotification > 0) {
                Game.player.addNotification(new Notification(Notification.Notification_Type.PRICE_CHANGE_GROUP, ResourcesManager.getResourceGroupName(this.iGroupID) + ": " + ((this.changePrice > 0) ? "+" : "") + CFG.getPrecision2((float)this.changePrice, 100) + "%, " + Game.lang.get("Resources") + ": " + addNotification, Images.goods, Game_Calendar.TURN_ID, (this.changePrice > 0) ? Notification.Notification_BG.GREEN : Notification.Notification_BG.RED));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return ResourcesManager.getResourceGroupName(this.iGroupID) + ": " + Game.lang.get("PriceChange") + ": " + ((this.changePrice > 0) ? "+" : "") + CFG.getPrecision2((float)this.changePrice, 100) + "%";
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
        return this.iGroupID;
    }
    
    @Override
    public float getValue2() {
        return (float)this.changePrice;
    }
}
