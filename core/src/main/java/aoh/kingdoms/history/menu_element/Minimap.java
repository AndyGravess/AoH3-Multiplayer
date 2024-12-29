// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import aoh.kingdoms.history.mainGame.Touch;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.math.Rectangle;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.Color;

public class Minimap extends MenuElement
{
    private int iWindowPosX;
    private int iWindowPosY;
    private int iWidnowHeight;
    public static int minimapBoxBorder;
    public final Color boxColor;
    
    public Minimap(final int nPosX, final int nPosY) {
        this.boxColor = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        this.typeOfElement = MenuElement_Type.MINIMAP;
        this.setPosX(nPosX);
        this.setPosY(nPosY);
        this.setWidth(Game.mapBG.iMinimapWidth);
        this.setHeight(Game.mapBG.iMinimapHeight);
    }
    
    @Override
    public final void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        try {
            Game.mapBG.minimapOfCivilizations.draw(oSB, this.getPosX() + getPadding() + iTranslateX, this.getPosY() + getPadding() + iTranslateY, false, true);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
            if (Game.mapBG.fMinimapScaled_Scale != 1.0f) {
                final Rectangle clipBounds = new Rectangle((float)(this.getPosX() + iTranslateX), (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY), (float)this.getWidth(), (float)(-this.getHeight()));
                oSB.flush();
                ScissorStack.pushScissors(clipBounds);
                this.iWindowPosX = (int)(-(Game.mapCoords.getPosX() + Game.mapBG.iMinimapScaled_PosX) / Game.mapBG.getMinimapScaled_ScaleX() - ((Game.mapBG.minimapBelowZero && -Game.mapCoords.getPosX() > Game.mapBG.getWidth() - Game.mapBG.getWidth() / Game.mapBG.fMinimapScaled_Scale) ? (Game.mapBG.getWidth() / Game.mapBG.getMinimapScaled_ScaleX()) : 0.0f));
                this.iWindowPosY = (int)(-(Game.mapCoords.getPosY() + Game.mapBG.iMinimapScaled_PosY) / Game.mapBG.getMinimapScaled_ScaleY());
                this.iWidnowHeight = (int)((this.iWindowPosY + CFG.GAME_HEIGHT / Game.mapBG.getMinimapScaled_ScaleY() / Game.mapScale.getCurrentScale() > Game.mapBG.minimapOfCivilizations.getHeight()) ? ((float)(Game.mapBG.minimapOfCivilizations.getHeight() - this.iWindowPosY)) : (CFG.GAME_HEIGHT / Game.mapBG.getMinimapScaled_ScaleY() / Game.mapScale.getCurrentScale()));
                Renderer.drawRect(oSB, this.getPosX() + this.iWindowPosX - 1 + iTranslateX + getPadding(), 1 + this.getPosY() + this.iWindowPosY - 1 + iTranslateY + getPadding(), 2 + (int)((CFG.GAME_WIDTH / Game.mapBG.getMinimapScaled_ScaleX() / Game.mapScale.getCurrentScale() + this.iWindowPosX > Game.mapBG.minimapOfCivilizations.getWidth()) ? ((float)(Game.mapBG.minimapOfCivilizations.getWidth() - this.iWindowPosX)) : (CFG.GAME_WIDTH / Game.mapBG.getMinimapScaled_ScaleX() / Game.mapScale.getCurrentScale())), this.iWidnowHeight + 2);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
                Renderer.drawRect(oSB, this.getPosX() + this.iWindowPosX + iTranslateX + getPadding(), 1 + this.getPosY() + this.iWindowPosY + iTranslateY + getPadding(), (int)((CFG.GAME_WIDTH / Game.mapBG.getMinimapScaled_ScaleX() / Game.mapScale.getCurrentScale() + this.iWindowPosX > Game.mapBG.minimapOfCivilizations.getWidth()) ? ((float)(Game.mapBG.minimapOfCivilizations.getWidth() - this.iWindowPosX)) : (CFG.GAME_WIDTH / Game.mapBG.getMinimapScaled_ScaleX() / Game.mapScale.getCurrentScale())), this.iWidnowHeight);
                try {
                    oSB.flush();
                    ScissorStack.popScissors();
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
            else {
                this.iWindowPosX = (int)(-Game.mapCoords.getPosX() / Game.mapBG.fMinimapScaleX);
                this.iWindowPosY = (int)((-Game.mapCoords.getPosY() / Game.mapBG.fMinimapScaleY < 0.0f) ? 0.0f : (-Game.mapCoords.getPosY() / Game.mapBG.fMinimapScaleY));
                this.iWidnowHeight = (int)((this.iWindowPosY + CFG.GAME_HEIGHT / Game.mapBG.fMinimapScaleY / Game.mapScale.getCurrentScale() > Game.mapBG.minimapOfCivilizations.getHeight()) ? ((float)(Game.mapBG.minimapOfCivilizations.getHeight() - this.iWindowPosY)) : (CFG.GAME_HEIGHT / Game.mapBG.fMinimapScaleY / Game.mapScale.getCurrentScale()));
                Renderer.drawRect(oSB, this.getPosX() + this.iWindowPosX - 1 + iTranslateX + getPadding(), 1 + this.getPosY() + this.iWindowPosY - 1 + iTranslateY + getPadding(), 2 + (int)((CFG.GAME_WIDTH / Game.mapBG.fMinimapScaleX / Game.mapScale.getCurrentScale() + this.iWindowPosX > Game.mapBG.minimapOfCivilizations.getWidth()) ? ((float)(Game.mapBG.minimapOfCivilizations.getWidth() - this.iWindowPosX)) : (CFG.GAME_WIDTH / Game.mapBG.fMinimapScaleX / Game.mapScale.getCurrentScale())), this.iWidnowHeight + 2);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
                Renderer.drawRect(oSB, this.getPosX() + this.iWindowPosX + iTranslateX + getPadding(), 1 + this.getPosY() + this.iWindowPosY + iTranslateY + getPadding(), (int)((CFG.GAME_WIDTH / Game.mapBG.fMinimapScaleX / Game.mapScale.getCurrentScale() + this.iWindowPosX > Game.mapBG.minimapOfCivilizations.getWidth()) ? ((float)(Game.mapBG.minimapOfCivilizations.getWidth() - this.iWindowPosX)) : (CFG.GAME_WIDTH / Game.mapBG.fMinimapScaleX / Game.mapScale.getCurrentScale())), this.iWidnowHeight);
                if (Game.mapCoords.getSecondSideOfMap()) {
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
                    Renderer.drawRect(oSB, this.getPosX() + iTranslateX + getPadding(), this.getPosY() + 1 + this.iWindowPosY + iTranslateY + getPadding(), (int)Math.abs(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale() / Game.mapBG.fMinimapScaleX - (Game.mapBG.getWidth() + Game.mapCoords.getPosX()) / Game.mapBG.fMinimapScaleX), this.iWidnowHeight);
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
                    Renderer.drawRect(oSB, this.getPosX() + iTranslateX + getPadding(), this.getPosY() + 1 + this.iWindowPosY + iTranslateY + getPadding(), (int)Math.abs(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale() / Game.mapBG.fMinimapScaleX - (Game.mapBG.getWidth() + Game.mapCoords.getPosX()) / Game.mapBG.fMinimapScaleX), this.iWidnowHeight);
                }
            }
            oSB.setColor(Color.WHITE);
            Game.mapBG.minimapOver.draw(oSB, this.getPosX() + getPadding() + iTranslateX, this.getPosY() + getPadding() + iTranslateY);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static int getPadding() {
        return 0;
    }
    
    @Override
    public int getWidth() {
        return Game.mapBG.iMinimapWidth + getPadding() * 2;
    }
    
    @Override
    public int getHeight() {
        return Game.mapBG.iMinimapHeight + getPadding() * 2;
    }
    
    @Override
    public void actionElement() {
        super.actionElement();
        Game.mapCoords.centerToMinimapClick(Touch.getMousePosX() - this.getPosX() - getPadding(), Touch.getMousePosY() - this.getPosY() - getPadding());
    }
    
    static {
        Minimap.minimapBoxBorder = 0;
    }
}
