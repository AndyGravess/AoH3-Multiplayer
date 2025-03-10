// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_AllianceIsLeader extends EventTrigger_Value
{
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        if (Game.getCiv(iCivID).inAllianceSize > 0) {
            for (int i = 0; i < Game.getCiv(iCivID).inAllianceSize; ++i) {
                if (Game.alliancesSpecial.get(Game.getCiv(iCivID).inAlliance.get(i)).iLeaderCivID == iCivID) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String getText() {
        if (Game.getCiv(Game.player.iCivID).inAllianceSize > 0) {
            final int i = 0;
            if (i < Game.getCiv(Game.player.iCivID).inAllianceSize) {
                return Game.lang.get(Game.alliancesSpecial.get(Game.getCiv(Game.player.iCivID).inAlliance.get(i)).Name_Alliance) + ": ";
            }
        }
        return Game.lang.get("Alliance") + ": ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.lang.get("Leader");
    }
    
    @Override
    public String getText3() {
        boolean state = false;
        if (Game.getCiv(Game.player.iCivID).inAllianceSize > 0) {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).inAllianceSize; ++i) {
                if (Game.alliancesSpecial.get(Game.getCiv(Game.player.iCivID).inAlliance.get(i)).iLeaderCivID == Game.player.iCivID) {
                    state = true;
                    break;
                }
            }
        }
        return " [" + Game.lang.get("Currently") + ": " + state + "]";
    }
    
    @Override
    public int getImage() {
        return Images.alliance;
    }
}
