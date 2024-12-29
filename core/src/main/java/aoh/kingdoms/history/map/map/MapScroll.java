// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;

public class MapScroll
{
    protected static final float SCROLL_SLOW = 0.97f;
    private boolean scrollingTheMap;
    private int iScrollPosX;
    private int iScrollPosY;
    private int iScrollPosX2;
    private int iScrollPosY2;
    private float fScrollNewPosX;
    private float fScrollNewPosY;
    private long moveMapTime;
    private boolean moveMapDirection;
    private int iStepID;
    private int iScrollEvent_PosX;
    private int iScrollEvent_PosY;
    private boolean scrollEvent;
    private ReverseDirection reverseDirectionX;
    private ReverseDirection reverseDirectionY;
    private static final int MAX_SCROLLING_SPEED = 500;
    
    public MapScroll() {
        this.scrollingTheMap = false;
        this.iScrollPosX2 = -1;
        this.iScrollPosY2 = -1;
        this.moveMapTime = 0L;
        this.moveMapDirection = false;
        this.iStepID = 0;
        this.scrollEvent = false;
        this.reverseDirectionX = null;
        this.reverseDirectionY = null;
        this.buildReverseDirectionX();
        this.buildReverseDirectionY();
    }
    
    protected final void buildReverseDirectionX() {
        if (CFG.reverseDirectionX) {
            this.reverseDirectionX = new ReverseDirection() {
                @Override
                public int getNewPos(final int nPosX) {
                    return Game.mapCoords.getNewPosX() + nPosX;
                }
            };
        }
        else {
            this.reverseDirectionX = new ReverseDirection() {
                @Override
                public int getNewPos(final int nPosX) {
                    return Game.mapCoords.getNewPosX() - nPosX;
                }
            };
        }
    }
    
    protected final void buildReverseDirectionY() {
        if (CFG.reverseDirectionY) {
            this.reverseDirectionY = new ReverseDirection() {
                @Override
                public int getNewPos(final int nPosY) {
                    return Game.mapCoords.getNewPosY() + nPosY;
                }
            };
        }
        else {
            this.reverseDirectionY = new ReverseDirection() {
                @Override
                public int getNewPos(final int nPosY) {
                    return Game.mapCoords.getNewPosY() - nPosY;
                }
            };
        }
    }
    
    protected final void update() {
        if (this.scrollEvent) {
            if (this.iStepID < 14) {
                Game.mapCoords.setNewPosX(Game.mapCoords.getPosX() - (int)changeAnimationPos(this.iStepID, this.iScrollEvent_PosX));
                Game.mapCoords.setNewPosY(Game.mapCoords.getPosY() - (int)changeAnimationPos(this.iStepID++, this.iScrollEvent_PosY));
                if (this.iStepID == 14) {
                    this.moveMapTime = CFG.currentTimeMillis;
                    this.scrollEvent = false;
                }
            }
        }
        else if (this.scrollingTheMap && !Game.mapCoords.disableMovingMap) {
            if (Math.abs(this.fScrollNewPosX) > 1.0f || Math.abs(this.fScrollNewPosY) > 1.0f) {
                if (Math.abs(this.fScrollNewPosX) > 1.0f) {
                    Game.mapCoords.setNewPosX(this.reverseDirectionX.getNewPos((int)this.fScrollNewPosX));
                    this.fScrollNewPosX *= 0.97f;
                }
                if (Math.abs(this.fScrollNewPosY) > 1.0f) {
                    Game.mapCoords.setNewPosY(this.reverseDirectionY.getNewPos((int)this.fScrollNewPosY));
                    this.fScrollNewPosY *= 0.97f;
                }
            }
            else {
                this.stopScrollingTheMap();
            }
        }
    }
    
    protected static final float changeAnimationPos(final int animationStepID, final int nWidth) {
        switch (animationStepID) {
            case 0:
            case 1:
            case 12:
            case 13: {
                return nWidth * 2.5f / 100.0f;
            }
            case 2:
            case 3:
            case 10:
            case 11: {
                return nWidth * 5.0f / 100.0f;
            }
            case 4:
            case 5:
            case 8:
            case 9: {
                return nWidth * 10.0f / 100.0f;
            }
            case 6:
            case 7: {
                return nWidth * 15.0f / 100.0f;
            }
            default: {
                return 0.0f;
            }
        }
    }
    
    protected final void startScrollingTheMap() {
        if (!CFG.brushTool && (this.iScrollPosX2 >= 0 || this.iScrollPosY2 >= 0)) {
            if (Math.abs(this.iScrollPosX - this.iScrollPosX2) > (CFG.isDesktop() ? (CFG.PADDING * 2.0f) : 4.0f) * CFG.DENSITY) {
                this.fScrollNewPosX = (this.iScrollPosX - this.iScrollPosX2) * 1.2f * (CFG.reverseDirectionX ? 1 : -1);
                this.scrollingTheMap = true;
            }
            if (Math.abs(this.iScrollPosY - this.iScrollPosY2) > (CFG.isDesktop() ? (CFG.PADDING * 2.0f) : 4.0f) * CFG.DENSITY) {
                this.fScrollNewPosY = (this.iScrollPosY - this.iScrollPosY2) * 1.2f * (CFG.reverseDirectionY ? 1 : -1);
                this.scrollingTheMap = true;
            }
            if (Game.mapScale.getCurrentScale() > 1.0f) {
                this.fScrollNewPosX = Math.max(-500.0f, Math.min(500.0f, this.fScrollNewPosX) / Game.mapScale.getCurrentScale());
                this.fScrollNewPosY = Math.max(-500.0f, Math.min(500.0f, this.fScrollNewPosY) / Game.mapScale.getCurrentScale());
            }
            else {
                this.fScrollNewPosX = Math.max(-500.0f, Math.min(500.0f, this.fScrollNewPosX));
                this.fScrollNewPosY = Math.max(-500.0f, Math.min(500.0f, this.fScrollNewPosY));
            }
        }
        if (this.iScrollPosX != this.iScrollPosX2) {
            this.updateMoveMapDirection(this.iScrollPosX > this.iScrollPosX2);
        }
        this.resetScrollInfo();
    }
    
    public final void stopScrollingTheMap() {
        this.scrollingTheMap = false;
        this.scrollEvent = false;
        this.resetScrollInfo();
    }
    
    protected final void updateMoveMapDirection(final boolean moveMapDirection) {
        this.moveMapDirection = moveMapDirection;
        this.moveMapTime = 0L;
    }
    
    public final void resetScrollInfo() {
        final int n = -1;
        this.iScrollPosY2 = n;
        this.iScrollPosX2 = n;
        this.iScrollPosY = n;
        this.iScrollPosX = n;
    }
    
    protected final void setScrollEvent(final int nProvinceID) {
        this.setScrollEvent_Pos((int)(Game.mapCoords.getPosX() + Game.getProvince(nProvinceID).getCenterX() - CFG.GAME_WIDTH / Game.mapScale.getCurrentScale() / 2.0f), (int)(Game.mapCoords.getPosY() + Game.getProvince(nProvinceID).getCenterY() - CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale() / 2.0f));
    }
    
    protected final void setScrollEvent_ToPosition(final int nPosX, final int nPosY) {
        this.setScrollEvent_Pos((int)(Game.mapCoords.getPosX() + nPosX - CFG.GAME_WIDTH / Game.mapScale.getCurrentScale() / 2.0f), (int)(Game.mapCoords.getPosY() + nPosY - CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale() / 2.0f));
    }
    
    private final void setScrollEvent_Pos(final int nPosX, final int nPosY) {
        if (this.scrollEvent) {
            return;
        }
        this.scrollEvent = true;
        this.iStepID = 0;
        this.iScrollEvent_PosX = nPosX;
        this.iScrollEvent_PosY = nPosY;
        this.moveMapTime = CFG.currentTimeMillis + 208L;
    }
    
    public final void setScrollPos(final int nPosX, final int nPosY) {
        this.iScrollPosX2 = this.iScrollPosX;
        this.iScrollPosY2 = this.iScrollPosY;
        this.iScrollPosX = nPosX;
        this.iScrollPosY = nPosY;
    }
    
    protected boolean getScrollingTheMap() {
        return this.scrollingTheMap;
    }
    
    private interface ReverseDirection
    {
        int getNewPos(final int p0);
    }
}
