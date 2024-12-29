// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits.other;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.math.Vector2;

public class MoveUnits_BiggestCities_Siege extends MoveUnits_BiggestCities
{
    public MoveUnits_BiggestCities_Siege(final int nCivID, final int iFromProvinceID, final int iToProvinceID) {
        super(nCivID, iFromProvinceID, iToProvinceID);
    }
    
    @Override
    public void buildMoveUnitsLine(final boolean updateAnimation) {
        this.buildAnimation(updateAnimation);
        this.iPrecision = 15 * this.iRouteSize;
        this.vPoints = new Vector2[this.iPrecision];
        final Vector2[] dataSet = new Vector2[this.iRouteSize + 2];
        for (int i = 0; i < this.iRouteSize; ++i) {
            dataSet[i + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(i)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(i)).iCenterShiftY));
        }
        dataSet[0] = new Vector2((float)(Game.getProvince(this.lRoute.get(0)).iCenterShiftX + this.getShiftPosXY()), (float)(-(Game.getProvince(this.lRoute.get(0)).iCenterShiftY + this.getShiftPosXY())));
        dataSet[this.iRouteSize + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftY));
        final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
        for (int j = 0; j < this.iPrecision; ++j) {
            oCatmull.valueAt((Vector)(this.vPoints[j] = new Vector2()), j / (this.iPrecision - 1.0f));
        }
    }
}
