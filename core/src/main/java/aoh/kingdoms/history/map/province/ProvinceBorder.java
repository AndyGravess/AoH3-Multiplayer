// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsProvince;
import aoc.kingdoms.lukasz.jakowski.CFG;
import space.earlygrey.shapedrawer.JoinType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import java.util.List;

public class ProvinceBorder
{
    private List<ProvinceBorderLine> provinceBorderLine;
    private Array<Vector2> nPath;
    private int iProvinceBorderLineSize;
    private int iLineWidth;
    public int withProvinceID;
    private boolean civilizationBorder;
    private boolean wastelandBorder;
    public boolean isLocked;
    public static final float PROVINCE_BORDER_INTERVAL = 425.0f;
    public long animationTime;
    private int pathLastPointX;
    private int pathLastPointY;
    public List<Integer> lPointsX;
    public List<Integer> lPointsY;
    public DrawProvinceBorder drawProvinceBorder;
    public static DrawCivBorder drawCivBorder;
    public static DrawCivBorder drawCivBorderWar;
    public static int mapCordsPosY;
    public static float pathProvinceBorderExtraWidth;
    public static float pathProvinceBorderExtraWidth2;
    
    public ProvinceBorder(final int nWithProvinceID, final List<Integer> nPointsX, final List<Integer> nPointsY) {
        this.provinceBorderLine = new ArrayList<ProvinceBorderLine>();
        this.nPath = (Array<Vector2>)new Array();
        this.iLineWidth = 0;
        this.civilizationBorder = false;
        this.wastelandBorder = false;
        this.isLocked = false;
        this.animationTime = 0L;
        this.lPointsX = new ArrayList<Integer>();
        this.lPointsY = new ArrayList<Integer>();
        this.withProvinceID = nWithProvinceID;
        if (nPointsX.size() > 0) {
            this.pathLastPointX = nPointsX.get(nPointsX.size() - 1) * Game.mapBG.iMapScale;
            this.pathLastPointY = nPointsY.get(nPointsY.size() - 1) * Game.mapBG.iMapScale;
        }
        for (int i = 0, iSize = nPointsX.size() - 1; i < iSize; ++i) {
            this.provinceBorderLine.add(new ProvinceBorderLine(nPointsX.get(i) * Game.mapBG.iMapScale, nPointsY.get(i) * Game.mapBG.iMapScale, nPointsX.get(i + 1) * Game.mapBG.iMapScale, nPointsY.get(i + 1) * Game.mapBG.iMapScale));
        }
        for (int i = 0, iSize = nPointsX.size(); i < iSize; ++i) {
            this.lPointsX.add(nPointsX.get(i));
            this.lPointsY.add(nPointsY.get(i));
        }
        this.iProvinceBorderLineSize = this.provinceBorderLine.size();
        for (int i = 0; i < this.iProvinceBorderLineSize; ++i) {
            this.iLineWidth += this.provinceBorderLine.get(i).getWidth();
        }
        for (int i = 0; i < this.iProvinceBorderLineSize; ++i) {
            this.nPath.add((Object)new Vector2((float)this.provinceBorderLine.get(i).getPosX(), (float)(-this.provinceBorderLine.get(i).getPosY())));
        }
        this.nPath.add((Object)new Vector2((float)this.pathLastPointX, (float)(-this.pathLastPointY)));
    }
    
    public final void updateDrawProvinceBorder(final int nProvinceID) {
        try {
            if (this.isLocked) {
                return;
            }
            if (this.wastelandBorder) {
                if (this.civilizationBorder) {
                    if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS) {
                        if (Game.settingsManager.SETTINGS_PROVINCE_BORDER == 1) {
                            this.drawProvinceBorder = new DrawProvinceBorder() {
                                @Override
                                public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                    oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                                    ProvinceBorder.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                                }
                            };
                        }
                        else {
                            this.drawProvinceBorder = new DrawProvinceBorder() {
                                @Override
                                public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                    ProvinceBorder.this.drawStraightBorder_Shape(nTranslateProvincePosX, joinType, lineWidth);
                                }
                            };
                        }
                    }
                    else if (Game.mapScale.getCurrentScale() > SettingsProvince.value.DRAW_BORDERS) {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                ProvinceBorder.this.drawStraightBorder_Shape2(nTranslateProvincePosX, joinType, lineWidth);
                            }
                        };
                    }
                    else {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                            }
                        };
                    }
                }
                else {
                    this.drawProvinceBorder = new DrawProvinceBorder() {
                        @Override
                        public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                            ProvinceBorder.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                        }
                    };
                }
            }
            else if (this.civilizationBorder) {
                if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS) {
                    if (Game.settingsManager.SETTINGS_PROVINCE_BORDER == 1) {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                                ProvinceBorder.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                            }
                        };
                    }
                    else if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER && (DiplomacyManager.isAtWar(Game.player.iCivID, Game.getProvince(nProvinceID).getCivID()) || DiplomacyManager.isAtWar(Game.player.iCivID, Game.getProvince(this.withProvinceID).getCivID()))) {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                ProvinceBorder.this.drawStraightBorder_ShapeWar(nTranslateProvincePosX, joinType, lineWidth);
                            }
                        };
                    }
                    else {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                ProvinceBorder.this.drawStraightBorder_Shape(nTranslateProvincePosX, joinType, lineWidth);
                            }
                        };
                    }
                }
                else if (Game.mapScale.getCurrentScale() > SettingsProvince.value.DRAW_BORDERS) {
                    this.drawProvinceBorder = new DrawProvinceBorder() {
                        @Override
                        public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                            ProvinceBorder.this.drawStraightBorder_Shape2(nTranslateProvincePosX, joinType, lineWidth);
                        }
                    };
                }
                else {
                    this.drawProvinceBorder = new DrawProvinceBorder() {
                        @Override
                        public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                        }
                    };
                }
            }
            else if (Game.getProvince(nProvinceID).getWasteland() >= 0) {
                this.drawProvinceBorder = new DrawProvinceBorder() {
                    @Override
                    public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                    }
                };
            }
            else if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS) {
                this.animationTime = CFG.currentTimeMillis;
                if (Game.getProvince(nProvinceID).getSeaProvince() || Game.getProvince(this.withProvinceID).getSeaProvince()) {
                    if (Game.getProvince(nProvinceID).getSeaProvince() && Game.getProvince(this.withProvinceID).getSeaProvince()) {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                final float tempPerc = (CFG.currentTimeMillis - ProvinceBorder.this.animationTime) / 425.0f;
                                if (tempPerc >= 1.0f) {
                                    oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);
                                    ProvinceBorder.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                                    ProvinceBorder.this.drawProvinceBorder = new DrawProvinceBorder() {
                                        @Override
                                        public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                            oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);
                                            ProvinceBorder.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                                        }
                                    };
                                }
                                else {
                                    oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);
                                    ProvinceBorder.this.drawStraightBorder_Percentage(oSB, nTranslateProvincePosX, tempPerc);
                                }
                            }
                        };
                    }
                    else {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                            }
                        };
                    }
                }
                else {
                    this.drawProvinceBorder = new DrawProvinceBorder() {
                        @Override
                        public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                            oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                            final float tempPerc = (CFG.currentTimeMillis - ProvinceBorder.this.animationTime) / 425.0f;
                            if (tempPerc >= 1.0f) {
                                ProvinceBorder.this.drawDashedBorder_Percentage(oSB, nTranslateProvincePosX, 1.0f);
                                ProvinceBorder.this.drawProvinceBorder = new DrawProvinceBorder() {
                                    @Override
                                    public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                        oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                                        ProvinceBorder.this.drawInnerBorder(oSB, nTranslateProvincePosX);
                                    }
                                };
                            }
                            else {
                                ProvinceBorder.this.drawDashedBorder_Percentage(oSB, nTranslateProvincePosX, tempPerc);
                            }
                        }
                    };
                }
            }
            else {
                this.animationTime = CFG.currentTimeMillis;
                if (Game.getProvince(nProvinceID).getSeaProvince() || Game.getProvince(this.withProvinceID).getSeaProvince()) {
                    if (Game.getProvince(nProvinceID).getSeaProvince() && Game.getProvince(this.withProvinceID).getSeaProvince()) {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                final float tempPerc = (CFG.currentTimeMillis - ProvinceBorder.this.animationTime) / 425.0f;
                                if (tempPerc >= 1.0f) {
                                    ProvinceBorder.this.drawProvinceBorder = new DrawProvinceBorder() {
                                        @Override
                                        public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                        }
                                    };
                                }
                                else {
                                    oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);
                                    ProvinceBorder.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                                }
                            }
                        };
                    }
                    else {
                        this.drawProvinceBorder = new DrawProvinceBorder() {
                            @Override
                            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                            }
                        };
                    }
                }
                else {
                    this.drawProvinceBorder = new DrawProvinceBorder() {
                        @Override
                        public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                            oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                            final float tempPerc = (CFG.currentTimeMillis - ProvinceBorder.this.animationTime) / 425.0f;
                            if (tempPerc >= 1.0f) {
                                ProvinceBorder.this.drawDashedBorder_Percentage(oSB, nTranslateProvincePosX, 0.0f);
                                ProvinceBorder.this.drawProvinceBorder = new DrawProvinceBorder() {
                                    @Override
                                    public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                                    }
                                };
                            }
                            else {
                                ProvinceBorder.this.drawDashedBorder_Percentage(oSB, nTranslateProvincePosX, 1.0f - tempPerc);
                            }
                        }
                    };
                }
            }
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
    }
    
    public final void updateDrawProvinceBorder_ActiveProvince() {
        this.isLocked = true;
        this.drawProvinceBorder = new DrawProvinceBorder() {
            @Override
            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS) {
                    if (ProvinceBorder.this.getIsCivilizationBorder() || ProvinceBorder.this.getIsWastelandBorder()) {
                        ProvinceBorder.this.drawStraightBorder_Shape(nTranslateProvincePosX, joinType, lineWidth);
                        Renderer.oSBBorder.end();
                        Renderer.oSBBorder.begin();
                    }
                    oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, Game.activeProvince_Animation_Data.getBorderAlpha() / 255.0f));
                    ProvinceBorder.this.drawDashedBorder(oSB, ProvinceBorderManager.iLineOffset, nTranslateProvincePosX);
                }
                else if (ProvinceBorder.this.getIsCivilizationBorder() || ProvinceBorder.this.getIsWastelandBorder()) {
                    if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS) {
                        ProvinceBorder.this.drawStraightBorder_Shape(nTranslateProvincePosX, joinType, lineWidth);
                    }
                    else {
                        ProvinceBorder.this.drawStraightBorder_Shape2(nTranslateProvincePosX, joinType, lineWidth);
                    }
                }
            }
        };
    }
    
    public final void updateDrawProvinceBorder_ActiveCivilizationBorder() {
        this.isLocked = true;
        this.drawProvinceBorder = new DrawProvinceBorder() {
            @Override
            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                oSB.setColor(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER);
                ProvinceBorder.this.drawStraightBorder_Shape(nTranslateProvincePosX, joinType, lineWidth, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER2);
            }
        };
    }
    
    public final void updateDrawProvinceBorder_RelationUp() {
        this.isLocked = true;
        this.drawProvinceBorder = new DrawProvinceBorder() {
            @Override
            public void draw(final SpriteBatch oSB, final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
                oSB.setColor(Colors.HOVER_POSITIVE);
                ProvinceBorder.this.drawStraightBorder_Shape(nTranslateProvincePosX, joinType, lineWidth, Colors.HOVER_POSITIVE, new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER2.a));
            }
        };
    }
    
    public static final void updateDrawCivBorder() {
        if (Game.settingsManager.SETTINGS_PROVINCE_BORDER == 0) {
            ProvinceBorder.drawCivBorder = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                }
            };
            ProvinceBorder.drawCivBorderWar = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                }
            };
        }
        else if (Game.settingsManager.SETTINGS_PROVINCE_BORDER == 1) {
            ProvinceBorder.drawCivBorder = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                }
            };
            ProvinceBorder.drawCivBorderWar = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                }
            };
        }
        else if (SettingsProvince.value.ENABLE_DOUBLE_BORDER && Game.settingsManager.DOUBLE_BORDER) {
            ProvinceBorder.drawCivBorder = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                    Renderer.shapeDrawer.path2_Double((Iterable)nPath, lineWidth, joinType, true, offsetX, offsetY, ProvinceDraw.joinType_Shadow, ProvinceBorder.pathProvinceBorderExtraWidth, nColor, nColor2);
                }
            };
            ProvinceBorder.drawCivBorderWar = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                    Renderer.shapeDrawer.path2_Double((Iterable)nPath, lineWidth, joinType, true, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY, ProvinceDraw.joinType_Shadow, ProvinceBorder.pathProvinceBorderExtraWidth, CFG.COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE, CFG.COLOR_PROVINCE_STRAIGHT2_WAR);
                }
            };
        }
        else {
            ProvinceBorder.drawCivBorder = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                    Renderer.shapeDrawer.setColor(nColor);
                    Renderer.shapeDrawer.path2((Iterable)nPath, ProvinceBorder.pathProvinceBorderExtraWidth2, joinType, true, offsetX, offsetY);
                }
            };
            ProvinceBorder.drawCivBorderWar = new DrawCivBorder() {
                @Override
                public void drawCivBorder(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2, final Array<Vector2> nPath, final float offsetX, final float offsetY) {
                    Renderer.shapeDrawer.setColor(CFG.COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE);
                    Renderer.shapeDrawer.path2((Iterable)nPath, ProvinceBorder.pathProvinceBorderExtraWidth2, joinType, true, offsetX, offsetY);
                }
            };
        }
    }
    
    public final void drawStraightBorder_Shape(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor, final Color nColor2) {
        ProvinceBorder.drawCivBorder.drawCivBorder(nTranslateProvincePosX, joinType, lineWidth, nColor, nColor2, this.nPath, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
    }
    
    public final void drawStraightBorder_Shape2(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final Color nColor) {
        Renderer.shapeDrawer.setColor(new Color(nColor.r, nColor.g, nColor.b, nColor.a));
        Renderer.shapeDrawer.path2((Iterable)this.nPath, lineWidth, joinType, true, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
        Renderer.shapeDrawer.setColor(nColor);
        Renderer.shapeDrawer.path((Iterable)this.nPath, ProvinceBorder.pathProvinceBorderExtraWidth2, joinType, true);
    }
    
    public final void drawStraightBorder_Shape(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
        ProvinceBorder.drawCivBorder.drawCivBorder(nTranslateProvincePosX, joinType, lineWidth, CFG.COLOR_PROVINCE_STRAIGHT, CFG.COLOR_PROVINCE_STRAIGHT2, this.nPath, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
    }
    
    public final void drawStraightBorder_ShapeWar(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
        ProvinceBorder.drawCivBorderWar.drawCivBorder(nTranslateProvincePosX, joinType, lineWidth, CFG.COLOR_PROVINCE_STRAIGHT, CFG.COLOR_PROVINCE_STRAIGHT2, this.nPath, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
    }
    
    public final void drawStraightBorder_Shape2(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
        Renderer.shapeDrawer.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
        Renderer.shapeDrawer.path2((Iterable)this.nPath, ProvinceBorder.pathProvinceBorderExtraWidth2, ProvinceDraw.joinType_Shadow, true, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
    }
    
    public final void drawStraightBorder_Shape_Sea(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
        Renderer.shapeDrawer.setColor(new Color(CFG.COLOR_PROVINCE_SEABYSEA.r, CFG.COLOR_PROVINCE_SEABYSEA.g, CFG.COLOR_PROVINCE_SEABYSEA.b, 0.05f));
        Renderer.shapeDrawer.path2((Iterable)this.nPath, lineWidth, joinType, true, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
    }
    
    public final void drawStraightBorder_Shape_Sea(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth, final float fPercentage) {
        Renderer.shapeDrawer.setColor(new Color(CFG.COLOR_PROVINCE_SEABYSEA.r, CFG.COLOR_PROVINCE_SEABYSEA.g, CFG.COLOR_PROVINCE_SEABYSEA.b, 0.05f * fPercentage));
        Renderer.shapeDrawer.path2((Iterable)this.nPath, lineWidth, joinType, true, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
    }
    
    public final void drawStraightBorder_Shape_ActiveProvince(final int nTranslateProvincePosX, final JoinType joinType, final float lineWidth) {
        Renderer.shapeDrawer.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        Renderer.shapeDrawer.path2((Iterable)this.nPath, ProvinceBorder.pathProvinceBorderExtraWidth, ProvinceDraw.joinType_Shadow, true, (float)nTranslateProvincePosX, (float)ProvinceBorder.mapCordsPosY);
    }
    
    public final void drawStraightBorder(final SpriteBatch oSB, final int nTranslateProvincePosX) {
        for (int i = 0; i < this.iProvinceBorderLineSize; ++i) {
            final ProvinceBorderLine borderLine = this.provinceBorderLine.get(i);
            Images.pix.draw2(oSB, nTranslateProvincePosX + borderLine.getPosX(), Game.mapCoords.getPosY() + borderLine.getPosY(), borderLine.getWidth(), 1, borderLine.getAngle());
        }
    }
    
    protected final void drawStraightBorder_Percentage(final SpriteBatch oSB, final int nTranslateProvincePosX, final float fPercent) {
        ProvinceBorderLine borderLine;
        for (int lineWidth = (int)(this.iLineWidth * fPercent), i = 0, currentWidth = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += borderLine.getWidth(), ++i) {
            borderLine = this.provinceBorderLine.get(i);
            Images.pix.draw2(oSB, nTranslateProvincePosX + borderLine.getPosX(), Game.mapCoords.getPosY() + borderLine.getPosY(), borderLine.getWidth(), 1, borderLine.getAngle(), currentWidth);
        }
    }
    
    public final void drawInnerBorder_Shape(final int nTranslateProvincePosX) {
    }
    
    public final void drawInnerBorder(final SpriteBatch oSB, final int nTranslateProvincePosX) {
        this.drawDashedBorder(oSB, 0, nTranslateProvincePosX);
    }
    
    public final void drawDashedBorder(final SpriteBatch oSB, int offsetX, final int nTranslateProvincePosX) {
        for (int i = 0; i < this.iProvinceBorderLineSize; ++i) {
            final ProvinceBorderLine borderLine = this.provinceBorderLine.get(i);
            Images.imgLine_32.draw2(oSB, nTranslateProvincePosX + borderLine.getPosX(), Game.mapCoords.getPosY() + borderLine.getPosY(), borderLine.getWidth(), 1, borderLine.getAngle(), offsetX);
            offsetX += borderLine.getWidth();
        }
    }
    
    protected final void drawDashedBorder_Percentage(final SpriteBatch oSB, final int nTranslateProvincePosX, final float fPercent) {
        ProvinceBorderLine borderLine;
        for (int lineWidth = (int)(this.iLineWidth * fPercent), i = 0, currentWidth = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += borderLine.getWidth(), ++i) {
            borderLine = this.provinceBorderLine.get(i);
            Images.imgLine_32.draw2(oSB, nTranslateProvincePosX + borderLine.getPosX(), Game.mapCoords.getPosY() + borderLine.getPosY(), borderLine.getWidth(), 1, borderLine.getAngle(), currentWidth);
        }
    }
    
    public final int getWithProvinceID() {
        return this.withProvinceID;
    }
    
    public final boolean getIsWastelandBorder() {
        return this.wastelandBorder;
    }
    
    public final boolean getIsCivilizationBorder() {
        return this.civilizationBorder;
    }
    
    public final void setIsCivilizationBorder(final boolean civilizationBorder, final int iProvinceID) {
        this.civilizationBorder = civilizationBorder;
        this.updateDrawProvinceBorder(iProvinceID);
    }
    
    public final void setIsCivilizationBorder_Just(final boolean civilizationBorder, final int iProvinceID) {
        this.civilizationBorder = civilizationBorder;
    }
    
    protected final void setIsWastelandBorder(final boolean wastelandBorder, final int iProvinceID) {
        this.wastelandBorder = wastelandBorder;
        this.updateDrawProvinceBorder(iProvinceID);
    }
    
    static {
        ProvinceBorder.mapCordsPosY = 0;
        ProvinceBorder.pathProvinceBorderExtraWidth = 1.0f;
        ProvinceBorder.pathProvinceBorderExtraWidth2 = 1.0f;
    }
    
    public interface DrawProvinceBorder
    {
        void draw(final SpriteBatch p0, final int p1, final JoinType p2, final float p3);
    }
    
    public interface DrawCivBorder
    {
        void drawCivBorder(final int p0, final JoinType p1, final float p2, final Color p3, final Color p4, final Array<Vector2> p5, final float p6, final float p7);
    }
}
