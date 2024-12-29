// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits;

import aoc.kingdoms.lukasz.jakowski.Game;

public class MoveUnits_Player extends MoveUnits
{
    public MoveUnits_Player(final int nCivID, final int iFromProvinceID, final int iToProvinceID, final String key, final int extraArmyY, final boolean inRetreat, final boolean landOnly) {
        super(nCivID, iFromProvinceID, iToProvinceID, key, extraArmyY, inRetreat, landOnly);
    }
    
    public MoveUnits_Player(final int nCivID, final int iFromProvinceID, final int iToProvinceID, final String key, final int extraArmyY, final int iFromProvinceIDExtra) {
        super(nCivID, iFromProvinceID, iToProvinceID, key, extraArmyY, iFromProvinceIDExtra);
    }
    
    @Override
    public boolean getWas(final int nProvinceID) {
        return Game.getProvince(nProvinceID).wasPlayer;
    }
    
    @Override
    public void setWas(final int nProvinceID, final boolean nWas) {
        Game.getProvince(nProvinceID).wasPlayer = nWas;
    }
}
