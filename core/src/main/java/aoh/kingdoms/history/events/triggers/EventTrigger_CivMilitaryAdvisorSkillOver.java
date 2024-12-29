// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivMilitaryAdvisorSkillOver extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_CivMilitaryAdvisorSkillOver(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).advisorMilitary.sName != null && Game.getCiv(iCivID).advisorMilitary.iLevel > this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get(GameValues.court.ADVISOR_NAME_MILITARY) + ", " + Game.lang.get("AdvisorSkillLevel") + " > ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2((float)this.value, 100);
    }
    
    @Override
    public String getText3() {
        if (Game.getCiv(Game.player.iCivID).advisorMilitary.sName == null) {
            return " [" + Game.lang.get("Currently") + ": " + Game.lang.get("NoAdvisor") + "]";
        }
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).advisorMilitary.iLevel, 1) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.skill;
    }
}
