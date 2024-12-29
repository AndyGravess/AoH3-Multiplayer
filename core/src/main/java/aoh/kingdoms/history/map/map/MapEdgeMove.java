// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;

public class MapEdgeMove
{
    public int MAP_MOVE_KEYBOARD;
    public boolean MAP_MOVE_LEFT;
    public boolean MAP_MOVE_RIGHT;
    public boolean MAP_MOVE_TOP;
    public boolean MAP_MOVE_BOT;
    private static int PADDING_EDGE_MOVE;
    private static int MAX_MOVE_SPEED;
    public static final int DEFAULT_SCROLL_MAP = 30;
    public float iScroll_MAP;
    public float fScroll_XorY_Perc;
    public long lScrollTime_MAP;
    private long lScrollResetTime;
    private static int SCROLL_RESET_TIME;
    public static final int DEFAULT_SCROLL;
    private int iScroll;
    private long lScrollTime;
    private int iMousePosX;
    private int iMousePosY;
    private int extraTime;
    private final int NEXT_SCROLL_TIME = 10;
    
    public MapEdgeMove() {
        this.MAP_MOVE_KEYBOARD = 0;
        this.MAP_MOVE_LEFT = false;
        this.MAP_MOVE_RIGHT = false;
        this.MAP_MOVE_TOP = false;
        this.MAP_MOVE_BOT = false;
        this.iScroll_MAP = 30.0f;
        this.fScroll_XorY_Perc = 0.0f;
        this.lScrollTime_MAP = 0L;
        this.lScrollResetTime = 0L;
        this.iScroll = MapEdgeMove.DEFAULT_SCROLL;
        this.lScrollTime = 0L;
        this.iMousePosX = 100;
        this.iMousePosY = 100;
        this.extraTime = 125;
    }
    
    public final void MouseMoved_EdgeMove(final int screenX, final int screenY) {
        if (CFG.isDesktop && this.MAP_MOVE_KEYBOARD == 0) {
            this.iMousePosX = screenX;
            this.iMousePosY = screenY;
            if (Game.settingsManager.ENABLE_EDGE_SCROLL) {
                if (screenX < MapEdgeMove.PADDING_EDGE_MOVE) {
                    if (!this.MAP_MOVE_LEFT) {
                        this.MAP_MOVE_LEFT = true;
                        this.MAP_MOVE_RIGHT = false;
                        this.resetScrollMap();
                        Game.mapScroll.stopScrollingTheMap();
                        this.MAP_MOVE_KEYBOARD = 0;
                    }
                }
                else {
                    this.MAP_MOVE_LEFT = false;
                }
                if (screenX > CFG.GAME_WIDTH - MapEdgeMove.PADDING_EDGE_MOVE) {
                    if (!this.MAP_MOVE_RIGHT) {
                        this.MAP_MOVE_RIGHT = true;
                        this.MAP_MOVE_LEFT = false;
                        this.resetScrollMap();
                        Game.mapScroll.stopScrollingTheMap();
                        this.MAP_MOVE_KEYBOARD = 0;
                    }
                }
                else {
                    this.MAP_MOVE_RIGHT = false;
                }
                if (screenY < MapEdgeMove.PADDING_EDGE_MOVE) {
                    if (!this.MAP_MOVE_TOP) {
                        this.MAP_MOVE_TOP = true;
                        this.MAP_MOVE_BOT = false;
                        this.resetScrollMap();
                        Game.mapScroll.stopScrollingTheMap();
                        this.MAP_MOVE_KEYBOARD = 0;
                    }
                }
                else {
                    this.MAP_MOVE_TOP = false;
                }
                if (screenY > CFG.GAME_HEIGHT - MapEdgeMove.PADDING_EDGE_MOVE) {
                    if (!this.MAP_MOVE_BOT) {
                        this.MAP_MOVE_BOT = true;
                        this.MAP_MOVE_TOP = false;
                        this.resetScrollMap();
                        Game.mapScroll.stopScrollingTheMap();
                        this.MAP_MOVE_KEYBOARD = 0;
                    }
                }
                else {
                    this.MAP_MOVE_BOT = false;
                }
            }
        }
    }
    
    private final void resetScrollMap() {
        if (this.lScrollResetTime + MapEdgeMove.SCROLL_RESET_TIME < CFG.currentTimeMillis) {
            this.lScrollTime_MAP = CFG.currentTimeMillis + this.extraTime;
            this.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
        }
    }
    
    public final void updateMoveMap() {
        try {
            if (this.MAP_MOVE_KEYBOARD > 0) {
                if (this.MAP_MOVE_LEFT) {
                    this.updateScroll_MapX();
                    Game.mapCoords.setNewPosX(Game.mapCoords.getPosX() + (int)this.iScroll_MAP);
                }
                else if (this.MAP_MOVE_RIGHT) {
                    this.updateScroll_MapX();
                    Game.mapCoords.setNewPosX(Game.mapCoords.getPosX() - (int)this.iScroll_MAP);
                }
                if (this.MAP_MOVE_TOP) {
                    this.updateScroll_MapY();
                    Game.mapCoords.setNewPosY(Game.mapCoords.getPosY() + (int)this.iScroll_MAP);
                }
                else if (this.MAP_MOVE_BOT) {
                    this.updateScroll_MapY();
                    Game.mapCoords.setNewPosY(Game.mapCoords.getPosY() - (int)this.iScroll_MAP);
                }
            }
            else if (this.MAP_MOVE_LEFT) {
                this.updateScroll_MapX();
                Game.mapCoords.setNewPosX(Game.mapCoords.getPosX() + (int)this.iScroll_MAP);
                Game.mapCoords.setNewPosY((int)(Game.mapCoords.getPosY() - (int)this.iScroll_MAP * this.fScroll_XorY_Perc));
            }
            else if (this.MAP_MOVE_RIGHT) {
                this.updateScroll_MapX();
                Game.mapCoords.setNewPosX(Game.mapCoords.getPosX() - (int)this.iScroll_MAP);
                Game.mapCoords.setNewPosY((int)(Game.mapCoords.getPosY() - (int)this.iScroll_MAP * this.fScroll_XorY_Perc));
            }
            else if (this.MAP_MOVE_TOP) {
                this.updateScroll_MapY();
                Game.mapCoords.setNewPosY(Game.mapCoords.getPosY() + (int)this.iScroll_MAP);
                Game.mapCoords.setNewPosX((int)(Game.mapCoords.getPosX() - (int)this.iScroll_MAP * this.fScroll_XorY_Perc));
            }
            else if (this.MAP_MOVE_BOT) {
                this.updateScroll_MapY();
                Game.mapCoords.setNewPosY(Game.mapCoords.getPosY() - (int)this.iScroll_MAP);
                Game.mapCoords.setNewPosX((int)(Game.mapCoords.getPosX() - (int)this.iScroll_MAP * this.fScroll_XorY_Perc));
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.LOG(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.LOG(ex2);
        }
        catch (final ArithmeticException ex3) {
            CFG.LOG(ex3);
        }
    }
    
    private final void updateScroll_MapX() {
        if (this.iMousePosY > CFG.GAME_HEIGHT / 2) {
            this.fScroll_XorY_Perc = (this.iMousePosY - CFG.GAME_HEIGHT / 2.0f) / (CFG.GAME_HEIGHT / 2.0f);
        }
        else {
            this.fScroll_XorY_Perc = -(1.0f - this.iMousePosY / (CFG.GAME_HEIGHT / 2.0f));
        }
        this.lScrollResetTime = CFG.currentTimeMillis;
        if (this.lScrollTime_MAP + 10L < CFG.currentTimeMillis) {
            this.lScrollTime_MAP = CFG.currentTimeMillis;
            if (Game.mapScale.getCurrentScale() < 1.0f) {
                this.iScroll_MAP += this.iScroll_MAP * (0.13f + (1.0f - Game.mapScale.getCurrentScale()));
            }
            else {
                this.iScroll_MAP += this.iScroll_MAP * (0.13f / Game.mapScale.getCurrentScale());
            }
            if (this.iScroll_MAP > this.getMaxMoveSpeed()) {
                this.iScroll_MAP = (float)this.getMaxMoveSpeed();
            }
        }
    }
    
    private final void updateScroll_MapY() {
        if (this.iMousePosX > CFG.GAME_WIDTH / 2) {
            this.fScroll_XorY_Perc = (this.iMousePosX - CFG.GAME_WIDTH / 2.0f) / (CFG.GAME_WIDTH / 2.0f);
        }
        else {
            this.fScroll_XorY_Perc = -(1.0f - this.iMousePosX / (CFG.GAME_WIDTH / 2.0f));
        }
        this.lScrollResetTime = CFG.currentTimeMillis;
        if (this.lScrollTime_MAP + 10L < CFG.currentTimeMillis) {
            this.lScrollTime_MAP = CFG.currentTimeMillis;
            this.iScroll_MAP += this.iScroll_MAP * (0.2f + ((Game.mapScale.getCurrentScale() < 1.0f) ? (1.0f - Game.mapScale.getCurrentScale()) : 0.0f));
            if (this.iScroll_MAP > this.getMaxMoveSpeed()) {
                this.iScroll_MAP = (float)this.getMaxMoveSpeed();
            }
        }
    }
    
    private final int getMaxMoveSpeed() {
        if (Game.mapScale.getCurrentScale() < 1.0f) {
            return (int)(MapEdgeMove.MAX_MOVE_SPEED * (1.0f + (1.0f - Game.mapScale.getCurrentScale()) * 2.0f));
        }
        final float n = (float)MapEdgeMove.MAX_MOVE_SPEED;
        final float n2 = 1.0f;
        final float n3 = 0.75f;
        final float currentScale = Game.mapScale.getCurrentScale();
        final MapScale mapScale = Game.mapScale;
        return (int)(n * (n2 - n3 * (currentScale / MapScale.defScales.definedScales[0])));
    }
    
    static {
        MapEdgeMove.PADDING_EDGE_MOVE = 10;
        MapEdgeMove.MAX_MOVE_SPEED = 45;
        MapEdgeMove.SCROLL_RESET_TIME = 350;
        DEFAULT_SCROLL = MapEdgeMove.PADDING_EDGE_MOVE;
    }
}
