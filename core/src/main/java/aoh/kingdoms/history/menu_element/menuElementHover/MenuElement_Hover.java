// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;
import java.util.List;
import aoh.kingdoms.history.menu.Hover;

public class MenuElement_Hover implements Hover
{
    public static final float TEXT_SCALE = 0.9f;
    private List<MenuElement_HoverElement> lElements;
    private int iElementsSize;
    public static int DRAW_EXTRA_TIME;
    public static long lTimeDrawExtra;
    public boolean haveDrawExtraElement;
    public long INIT_TIME;
    public static long ANIMATION_TIME;
    public static float ANIMATION_ALPHA;
    public static float ANIMATION_PADDING;
    public static int ANIMATION_INTERVAL;
    public int iFontID;
    public int iHeight;
    public int iHeight2;
    public int iHeightDrawExtra;
    private int iWidth;
    private int iMaxWidth;
    private int iWidthOver;
    private int iScrollPosX;
    private boolean backAnimation;
    private long lTime;
    public static Color colorGradient;
    public static Color colorGradientLoading;
    
    public static final void resetAnimation() {
        MenuElement_Hover.ANIMATION_TIME = CFG.currentTimeMillis;
        MenuElement_Hover.ANIMATION_ALPHA = 0.0225f;
        MenuElement_Hover.ANIMATION_PADDING = (float)CFG.PADDING;
    }
    
    public MenuElement_Hover(final List<MenuElement_HoverElement> nElements) {
        this.iElementsSize = 0;
        this.haveDrawExtraElement = false;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iHeight2 = 0;
        this.iHeightDrawExtra = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.lTime = 0L;
        this.init(nElements, 0, false);
    }
    
    public MenuElement_Hover(final List<MenuElement_HoverElement> nElements, final boolean titleBGLast) {
        this.iElementsSize = 0;
        this.haveDrawExtraElement = false;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iHeight2 = 0;
        this.iHeightDrawExtra = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.lTime = 0L;
        this.init(nElements, 0, titleBGLast);
    }
    
    public MenuElement_Hover(final List<MenuElement_HoverElement> nElements, final int iFontID) {
        this.iElementsSize = 0;
        this.haveDrawExtraElement = false;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iHeight2 = 0;
        this.iHeightDrawExtra = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.lTime = 0L;
        this.init(nElements, iFontID, false);
    }
    
    private final void init(final List<MenuElement_HoverElement> nElements, final int iFontID, final boolean titleBGLast) {
        this.lElements = nElements;
        this.iElementsSize = this.lElements.size();
        this.iWidth = 0;
        this.iFontID = iFontID;
        for (int i = 0; i < this.iElementsSize; ++i) {
            if (this.lElements.get(i).getWidth() > CFG.GAME_WIDTH - CFG.PADDING * 2 && this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2 > this.iWidthOver) {
                this.iWidthOver = this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2;
            }
            if (!this.lElements.get(i).drawElement) {
                this.haveDrawExtraElement = true;
            }
        }
        if (this.iWidthOver > 0) {
            this.iScrollPosX = this.iWidthOver + CFG.PADDING * 10;
            this.lTime = CFG.currentTimeMillis;
        }
        for (int i = 0; i < this.iElementsSize; ++i) {
            if (this.lElements.get(i).getWidth() > this.iWidth) {
                this.iWidth = this.lElements.get(i).getWidth();
                this.iMaxWidth = this.lElements.get(i).getWidth();
            }
        }
        this.iWidth += getDrawExtraXPos() * 2;
        this.iHeight2 = 0;
        this.iHeightDrawExtra = CFG.PADDING * (this.iElementsSize - 1) + CFG.PADDING * 6;
        int numElements = 0;
        for (int j = 0; j < this.iElementsSize; ++j) {
            this.iHeightDrawExtra += this.lElements.get(j).getHeight();
            if (this.lElements.get(j).drawElement) {
                this.iHeight2 += this.lElements.get(j).getHeight();
                ++numElements;
            }
        }
        if (titleBGLast) {
            this.iHeight2 -= CFG.PADDING * 2;
            this.iHeightDrawExtra -= CFG.PADDING * 2;
        }
        this.iHeight2 += CFG.PADDING * numElements + CFG.PADDING * 5;
        this.iHeight = this.iHeight2;
        MenuElement_Hover.lTimeDrawExtra = CFG.currentTimeMillis;
        this.INIT_TIME = CFG.currentTimeMillis;
    }
    
    public final void updateHeight() {
        this.iHeight = ((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra >= MenuElement_Hover.DRAW_EXTRA_TIME) ? this.iHeightDrawExtra : this.iHeight2);
    }
    
    public boolean initHide() {
        return this.INIT_TIME + GameValues.notifications.HOVER_HIDE_TIME > CFG.currentTimeMillis;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int nPosX, int nPosY) {
        if (this.initHide() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
            return;
        }
        this.updateHeight();
        nPosX = Math.max(this.getMinPosX(), nPosX);
        nPosX += (int)MenuElement_Hover.ANIMATION_PADDING;
        if (nPosX + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        }
        else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    @Override
    public final void drawAlwaysOver(final SpriteBatch oSB, int nPosX, int nPosY) {
        if (this.initHide()) {
            return;
        }
        this.updateHeight();
        nPosX += (int)MenuElement_Hover.ANIMATION_PADDING;
        nPosX += CFG.PADDING;
        nPosY = nPosY - this.iHeight - CFG.PADDING;
        if (nPosX + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        }
        else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    @Override
    public final void drawAlwaysOver_Mobile(final SpriteBatch oSB, int nPosX, int nPosY) {
        this.updateHeight();
        nPosX += (int)MenuElement_Hover.ANIMATION_PADDING;
        nPosX -= this.iWidth / 4;
        nPosY = nPosY - this.iHeight - CFG.PADDING * 4;
        if (nPosX < CFG.PADDING) {
            nPosX = CFG.PADDING;
        }
        if (nPosX + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        }
        else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    @Override
    public final void drawAlwaysBelow(final SpriteBatch oSB, int nPosX, int nPosY) {
        this.updateHeight();
        nPosX += (int)MenuElement_Hover.ANIMATION_PADDING;
        nPosX += CFG.PADDING;
        nPosY += CFG.PADDING;
        if (nPosX + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        }
        else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    protected final int getScrollPosX() {
        if (this.iWidthOver > 0) {
            if (this.backAnimation) {
                if (this.lTime + 1500L < CFG.currentTimeMillis && this.iScrollPosX-- < -CFG.PADDING) {
                    this.backAnimation = !this.backAnimation;
                    this.lTime = CFG.currentTimeMillis;
                }
            }
            else if (this.lTime + 1000L < CFG.currentTimeMillis && this.iScrollPosX++ > this.iWidthOver + CFG.PADDING * 10) {
                this.backAnimation = !this.backAnimation;
                this.lTime = CFG.currentTimeMillis;
            }
            return this.iScrollPosX;
        }
        return 0;
    }
    
    @Override
    public final void drawProvinceInfo(final SpriteBatch oSB, int nPosX, final int nPosY) {
        if (this.initHide()) {
            return;
        }
        nPosX += (int)MenuElement_Hover.ANIMATION_PADDING;
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    public static final int getDrawExtraXPos() {
        return CFG.PADDING * 3;
    }
    
    @Override
    public final void draw_Hover(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        final int tempScrollX = this.getScrollPosX();
        final boolean drawExtra = CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra >= MenuElement_Hover.DRAW_EXTRA_TIME;
        if (drawExtra) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.975f * MenuElement_Hover.ANIMATION_ALPHA));
            Renderer.drawBoxHover(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeightDrawExtra, MenuElement_Hover.ANIMATION_ALPHA);
            oSB.setColor(new Color(MenuElement_Hover.colorGradient.r, MenuElement_Hover.colorGradient.g, MenuElement_Hover.colorGradient.b, MenuElement_Hover.colorGradient.a * MenuElement_Hover.ANIMATION_ALPHA));
            Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra / 2, this.iWidth, this.iHeightDrawExtra - this.iHeightDrawExtra / 2 - 2, false, false);
            if (this.haveDrawExtraElement) {
                oSB.setColor(new Color(MenuElement_Hover.colorGradientLoading.r, MenuElement_Hover.colorGradientLoading.g, MenuElement_Hover.colorGradientLoading.b, MenuElement_Hover.colorGradientLoading.a * MenuElement_Hover.ANIMATION_ALPHA));
                ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra - CFG.PADDING, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra - CFG.PADDING * 2, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING * 2, false, false);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f * MenuElement_Hover.ANIMATION_ALPHA));
                Images.gradientFull.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra - 1, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), 1, false, false);
            }
            oSB.setColor(Color.WHITE);
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.975f * MenuElement_Hover.ANIMATION_ALPHA));
            Renderer.drawBoxHover(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight, MenuElement_Hover.ANIMATION_ALPHA);
            oSB.setColor(new Color(MenuElement_Hover.colorGradient.r, MenuElement_Hover.colorGradient.g, MenuElement_Hover.colorGradient.b, MenuElement_Hover.colorGradient.a * MenuElement_Hover.ANIMATION_ALPHA));
            Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight / 2, this.iWidth, this.iHeight - this.iHeight / 2 - 2, false, false);
            if (this.haveDrawExtraElement) {
                oSB.setColor(new Color(MenuElement_Hover.colorGradientLoading.r, MenuElement_Hover.colorGradientLoading.g, MenuElement_Hover.colorGradientLoading.b, MenuElement_Hover.colorGradientLoading.a * MenuElement_Hover.ANIMATION_ALPHA));
                ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - CFG.PADDING, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - CFG.PADDING * 2, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING * 2, false, false);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f * MenuElement_Hover.ANIMATION_ALPHA));
                Images.gradientFull.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 1, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), 1, false, false);
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, MenuElement_Hover.ANIMATION_ALPHA));
        this.draw_HoverWithoutAnimation(oSB, nPosX, nPosY, tempScrollX, MenuElement_Hover.ANIMATION_ALPHA, drawExtra);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public final void draw_HoverWithoutAnimation(final SpriteBatch oSB, final int nPosX, int nPosY) {
        final int tempScrollX = this.getScrollPosX();
        final boolean drawExtra = CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra >= MenuElement_Hover.DRAW_EXTRA_TIME;
        if (drawExtra) {
            if (nPosY + this.iHeightDrawExtra > CFG.GAME_HEIGHT - CFG.PADDING * 2) {
                nPosY = CFG.GAME_HEIGHT - CFG.PADDING * 2 - this.iHeightDrawExtra;
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.975f));
            Renderer.drawBoxHover(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeightDrawExtra, 1.0f);
            oSB.setColor(MenuElement_Hover.colorGradient);
            Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra / 2, this.iWidth, this.iHeightDrawExtra - this.iHeightDrawExtra / 2 - 2, false, false);
            if (this.haveDrawExtraElement) {
                oSB.setColor(new Color(MenuElement_Hover.colorGradientLoading.r, MenuElement_Hover.colorGradientLoading.g, MenuElement_Hover.colorGradientLoading.b, MenuElement_Hover.colorGradientLoading.a));
                ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra - CFG.PADDING, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra - CFG.PADDING * 2, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING * 2, false, false);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
                Images.gradientFull.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeightDrawExtra - 1, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), 1, false, false);
            }
            oSB.setColor(Color.WHITE);
        }
        else {
            if (nPosY + this.iHeight > CFG.GAME_HEIGHT - CFG.PADDING * 2) {
                nPosY = CFG.GAME_HEIGHT - CFG.PADDING * 2 - this.iHeight;
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.975f));
            Renderer.drawBoxHover(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight, 1.0f);
            oSB.setColor(MenuElement_Hover.colorGradient);
            Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight / 2, this.iWidth, this.iHeight - this.iHeight / 2 - 2, false, false);
            if (this.haveDrawExtraElement) {
                oSB.setColor(new Color(MenuElement_Hover.colorGradientLoading.r, MenuElement_Hover.colorGradientLoading.g, MenuElement_Hover.colorGradientLoading.b, MenuElement_Hover.colorGradientLoading.a));
                ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - CFG.PADDING, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - CFG.PADDING * 2, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), CFG.PADDING * 2, false, false);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
                Images.gradientFull.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 1, (int)(this.iWidth * Math.min((CFG.currentTimeMillis - MenuElement_Hover.lTimeDrawExtra) / (float)MenuElement_Hover.DRAW_EXTRA_TIME, 1.0f)), 1, false, false);
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        this.draw_HoverWithoutAnimation(oSB, nPosX, nPosY, tempScrollX, 1.0f, drawExtra);
        oSB.setColor(Color.WHITE);
    }
    
    public final void draw_HoverWithoutAnimation(final SpriteBatch oSB, final int nPosX, final int nPosY, final int tempScrollX, final float fAlpha, final boolean drawExtra) {
        int i = 0;
        int tempPosY = 0;
        while (i < this.iElementsSize) {
            if (drawExtra || this.lElements.get(i).drawElement) {
                this.lElements.get(i).draw(oSB, nPosX + tempScrollX + getDrawExtraXPos(), nPosY + CFG.PADDING * 2 + tempPosY, fAlpha, this.iMaxWidth);
                tempPosY += this.lElements.get(i).getHeight() + CFG.PADDING;
            }
            ++i;
        }
    }
    
    public int getMinPosX() {
        return 0;
    }
    
    static {
        MenuElement_Hover.DRAW_EXTRA_TIME = 2500;
        MenuElement_Hover.lTimeDrawExtra = 0L;
        MenuElement_Hover.ANIMATION_INTERVAL = 2450;
        MenuElement_Hover.colorGradient = new Color(0.078431375f, 0.078431375f, 0.1254902f, 0.45f);
        MenuElement_Hover.colorGradientLoading = new Color(0.13725491f, 0.19607843f, 0.25490198f, 1.0f);
    }
}
