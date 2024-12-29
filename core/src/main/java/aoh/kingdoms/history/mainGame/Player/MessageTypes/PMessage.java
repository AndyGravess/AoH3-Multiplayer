// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.MessageTypes;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.jakowski.CFG;

public class PMessage
{
    public String key;
    public int fromCivID;
    public int expiresTurnID;
    public int iValue1;
    public long time;
    
    public PMessage(final int iFromCivID, final int iExpiresTurnID, final int iValue1) {
        this.key = CFG.extraRandomTag();
        this.fromCivID = iFromCivID;
        this.expiresTurnID = iExpiresTurnID;
        this.iValue1 = iValue1;
        this.time = CFG.currentTimeMillis;
    }
    
    public PMessage(final int iFromCivID, final int iExpiresTurnID, final int iValue1, final String KEY) {
        this.key = CFG.extraRandomTag();
        this.fromCivID = iFromCivID;
        this.expiresTurnID = iExpiresTurnID;
        this.iValue1 = iValue1;
        this.time = CFG.currentTimeMillis;
        this.key = KEY;
    }
    
    public void actionClick() {
    }
    
    public void onAccept() {
    }
    
    public void onRefuse() {
    }
    
    public MenuElement_Hover buildElementHover() {
        return null;
    }
    
    public int getImageID() {
        return Images.diplomacy;
    }
}
