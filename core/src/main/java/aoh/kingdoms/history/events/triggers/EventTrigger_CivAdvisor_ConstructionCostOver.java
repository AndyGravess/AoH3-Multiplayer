// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivAdvisor_ConstructionCostOver extends EventTrigger_Value
{
    public float value;
    public int advisorType;
    
    public EventTrigger_CivAdvisor_ConstructionCostOver(final int advisorType, final float value) {
        this.advisorType = advisorType;
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final AdvisorManager advisorManager = Game.advisorManager;
        if (AdvisorManager.getAdvisor(iCivID, this.advisorType).sName == null) {
            return false;
        }
        final AdvisorManager advisorManager2 = Game.advisorManager;
        return AdvisorManager.getAdvisor(iCivID, this.advisorType).ConstructionCost * 100.0f < this.value;
    }
    
    @Override
    public String getText() {
        final StringBuilder sb = new StringBuilder();
        final AdvisorManager advisorManager = Game.advisorManager;
        return sb.append(AdvisorManager.getAdvisorGroupName(this.advisorType)).append(", ").append(Game.lang.get("ConstructionCost")).append(" < ").toString();
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public String getText3() {
        final AdvisorManager advisorManager = Game.advisorManager;
        if (AdvisorManager.getAdvisor(Game.player.iCivID, this.advisorType).sName == null) {
            return " [" + Game.lang.get("Currently") + ": " + Game.lang.get("NoAdvisor") + "]";
        }
        final StringBuilder append = new StringBuilder().append(" [").append(Game.lang.get("Currently")).append(": ");
        final AdvisorManager advisorManager2 = Game.advisorManager;
        return append.append(CFG.getPrecision2(AdvisorManager.getAdvisor(Game.player.iCivID, this.advisorType).ConstructionCost * 100.0f, 100)).append("%]").toString();
    }
    
    @Override
    public int getImage() {
        return Images.construction;
    }
}
