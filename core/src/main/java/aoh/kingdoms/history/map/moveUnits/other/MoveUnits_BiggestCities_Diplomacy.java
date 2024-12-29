// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits.other;

import space.earlygrey.shapedrawer.JoinType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.math.Vector2;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.Color;

public class MoveUnits_BiggestCities_Diplomacy extends MoveUnits_BiggestCities
{
    public float widthPercentage;
    
    public MoveUnits_BiggestCities_Diplomacy(final int nCivID, final int iFromProvinceID, final int iToProvinceID, final Color nLineColor) {
        super(nCivID, iFromProvinceID, iToProvinceID);
        this.widthPercentage = 1.0f;
        this.ColorLine = nLineColor;
        this.ColorLine2 = nLineColor;
    }
    
    @Override
    public void buildAnimation(final boolean updateAnimation) {
        if (updateAnimation) {
            this.lMovingTime = CFG.currentTimeMillis;
            this.fMovingPercentage = 0.01f;
            this.littleAnimationMainLine = new MoveUnits.LittleAnimation() {
                @Override
                public void update() {
                    final MoveUnits_BiggestCities_Diplomacy this$0 = MoveUnits_BiggestCities_Diplomacy.this;
                    this$0.fMovingPercentage += (CFG.currentTimeMillis - MoveUnits_BiggestCities_Diplomacy.this.lMovingTime) / GameValues.inGame.DIPLOMACY_LINES_ANIMATION_DURATION;
                    MoveUnits_BiggestCities_Diplomacy.this.lMovingTime = CFG.currentTimeMillis;
                    if (MoveUnits_BiggestCities_Diplomacy.this.fMovingPercentage >= 1.0f) {
                        MoveUnits_BiggestCities_Diplomacy.this.fMovingPercentage = 1.0f;
                        MoveUnits_BiggestCities_Diplomacy.this.lMovingTime = CFG.currentTimeMillis;
                        MoveUnits_BiggestCities_Diplomacy.this.littleAnimationMainLine = new MoveUnits.LittleAnimation() {
                            @Override
                            public void update() {
                                final MoveUnits_BiggestCities_Diplomacy this$0 = MoveUnits_BiggestCities_Diplomacy.this;
                                this$0.widthPercentage -= (CFG.currentTimeMillis - MoveUnits_BiggestCities_Diplomacy.this.lMovingTime) / GameValues.inGame.DIPLOMACY_LINES_ANIMATION_DURATION;
                                MoveUnits_BiggestCities_Diplomacy.this.lMovingTime = CFG.currentTimeMillis;
                            }
                        };
                    }
                }
            };
        }
    }
    
    @Override
    public boolean draw2(final SpriteBatch oSB, final float nScale) {
        try {
            if (this.iRouteSize > 0) {
                final Array<Vector2> nPath = (Array<Vector2>)new Array();
                if (Game.getProvince(this.lRoute.get(0)).getDrawProvince()) {
                    for (int j = 0; j < (int)((this.iPrecision - 2) * this.fMovingPercentage); ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.getProvince(this.lRoute.get(0)).getTranslateProvincePosX()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                else {
                    for (int j = 0; j < this.iPrecision; ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.mapCoords.getPosX()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                if (this.fMovingPercentage < 0.99f) {
                    Renderer.shapeDrawer.setColor(new Color(this.ColorLine.r, this.ColorLine.g, this.ColorLine.b, 0.55f));
                }
                else {
                    Renderer.shapeDrawer.setColor(new Color(this.ColorLine2.r, this.ColorLine2.g, this.ColorLine2.b, 0.55f));
                }
                Renderer.shapeDrawer.path((Iterable)nPath, 2.5f * (0.25f + 0.75f * this.fMovingPercentage) * this.widthPercentage * ((Game.mapScale.getCurrentScale() < 1.0f) ? Game.mapScale.getCurrentScale() : 1.0f), JoinType.SMOOTH, true);
                if (this.fMovingPercentage > 0.99f && Game.getProvince(this.lRoute.get(this.lRoute.size() - 1)).getDrawProvince()) {
                    Renderer.shapeDrawer.setColor(new Color(this.ColorLine2.r, this.ColorLine2.g, this.ColorLine2.b, 0.45f * this.widthPercentage));
                    Renderer.shapeDrawer.filledCircle((this.vPoints[this.vPoints.length - 1].x + Game.getProvince(this.lRoute.get(this.lRoute.size() - 1)).getTranslateProvincePosX()) * nScale, (this.vPoints[this.vPoints.length - 1].y - Game.mapCoords.getPosY()) * nScale, 12.0f * nScale * this.fMovingPercentage * this.widthPercentage);
                    Renderer.shapeDrawer.setColor(new Color(this.ColorLine2.r, this.ColorLine2.g, this.ColorLine2.b, 0.6f * this.widthPercentage));
                    Renderer.shapeDrawer.circle((this.vPoints[this.vPoints.length - 1].x + Game.getProvince(this.lRoute.get(this.lRoute.size() - 1)).getTranslateProvincePosX()) * nScale, (this.vPoints[this.vPoints.length - 1].y - Game.mapCoords.getPosY()) * nScale, 16.0f * nScale * this.fMovingPercentage * this.widthPercentage, 2.0f * nScale);
                }
                if (this.widthPercentage < 0.05f) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return true;
        }
        return false;
    }
    
    @Override
    public int getShiftPosXY() {
        return 0;
    }
}
