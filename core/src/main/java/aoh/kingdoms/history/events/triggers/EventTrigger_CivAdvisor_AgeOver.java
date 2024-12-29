// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.LanguageManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivAdvisor_AgeOver extends EventTrigger_Value
{
    public int value;
    public int advisorType;
    
    public EventTrigger_CivAdvisor_AgeOver(final int advisorType, final int value) {
        this.advisorType = advisorType;
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final AdvisorManager advisorManager = Game.advisorManager;
        if (AdvisorManager.getAdvisor(iCivID, this.advisorType).sName == null) {
            return false;
        }
        final int currentYear = Game_Calendar.currentYear;
        final AdvisorManager advisorManager2 = Game.advisorManager;
        return currentYear - AdvisorManager.getAdvisor(iCivID, this.advisorType).iYearOfBirth > this.value;
    }
    
    @Override
    public String getText() {
        final StringBuilder sb = new StringBuilder();
        final AdvisorManager advisorManager = Game.advisorManager;
        return sb.append(AdvisorManager.getAdvisorGroupName(this.advisorType)).append(", ").append(Game.lang.get("Age")).append(" > ").toString();
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2((float)this.value, 100);
    }
    
    @Override
    public String getText3() {
        final AdvisorManager advisorManager = Game.advisorManager;
        if (AdvisorManager.getAdvisor(Game.player.iCivID, this.advisorType).sName == null) {
            return " [" + Game.lang.get("Currently") + ": " + Game.lang.get("NoAdvisor") + "]";
        }
        final StringBuilder append = new StringBuilder().append(" [").append(Game.lang.get("Currently")).append(": ");
        final LanguageManager lang = Game.lang;
        final String key = "XYearsOld";
        final int currentYear = Game_Calendar.currentYear;
        final AdvisorManager advisorManager2 = Game.advisorManager;
        return append.append(lang.get(key, currentYear - AdvisorManager.getAdvisor(Game.player.iCivID, this.advisorType).iYearOfBirth)).append("]").toString();
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
