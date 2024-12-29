// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivAdministrativeAdvisorSkillOver extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_CivAdministrativeAdvisorSkillOver(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).advisorAdministration.sName != null && Game.getCiv(iCivID).advisorAdministration.iLevel > this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get(GameValues.court.ADVISOR_NAME_ADMINISTRATIVE) + ", " + Game.lang.get("AdvisorSkillLevel") + " > ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2((float)this.value, 100);
    }
    
    @Override
    public String getText3() {
        if (Game.getCiv(Game.player.iCivID).advisorAdministration.sName == null) {
            return " [" + Game.lang.get("Currently") + ": " + Game.lang.get("NoAdvisor") + "]";
        }
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).advisorAdministration.iLevel, 1) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.skill;
    }
}
