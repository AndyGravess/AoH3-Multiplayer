// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu;

import aoh.kingdoms.history.mainGame.Keyboard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.FBO.FBOProvincesBG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusEditor.CreateCiv;
import aoh.kingdoms.history.menusEditor.GameCivsEdit;
import java.util.List;
import com.badlogic.gdx.graphics.Color;

public class ColorPicker
{
    private int iPosX;
    private int iPosY;
    private boolean visible;
    private int iSVHeight;
    private int iHUEWidth;
    private int iResizeHeight;
    private int fontID;
    private boolean activeHUE;
    private boolean activeSV;
    private boolean activeResize;
    private boolean activeMove;
    private boolean activeClose;
    public static int activeRGB;
    private int iActiveColorID;
    private float fAlpha;
    private int iStartPosX;
    private int iStartPosY;
    private int iStartResizeHeight;
    private Color colorSVPos;
    private int iLastSVPosX;
    private int iLastSVPosY;
    private int iLastHUEPosY;
    private final float RGB_TEXT_SCALE = 0.9f;
    private int iRGBTextWidth;
    private int iRTextWidth;
    private int iGTextWidth;
    private int iBTextWidth;
    public static int ACTIVE_CIV_ID;
    private List<Box> lRGBBoxes;
    private List<Box> lColorsBoxes;
    private List<Color> lColors;
    public ColorPicker_AoC_Action ColorPicker_AoC_Action;
    public static Color hueColor;
    public static Color activeColor;
    private float[] hsv;
    private float hueVal;
    
    public final void updateColorPicker_Action(final PickerAction nAction) {
        switch (nAction) {
            case NONE_ACTION: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                    @Override
                    public void update() {
                    }
                    
                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case GAMECIVS_EDIT: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                    @Override
                    public void update() {
                        GameCivsEdit.nCiv.iR = (int)(ColorPicker.activeColor.r * 255.0f);
                        GameCivsEdit.nCiv.iG = (int)(ColorPicker.activeColor.g * 255.0f);
                        GameCivsEdit.nCiv.iB = (int)(ColorPicker.activeColor.b * 255.0f);
                    }
                    
                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case CREATE_CIV_EDIT: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                    @Override
                    public void update() {
                        CreateCiv.nCiv.iR = (int)(ColorPicker.activeColor.r * 255.0f);
                        CreateCiv.nCiv.iG = (int)(ColorPicker.activeColor.g * 255.0f);
                        CreateCiv.nCiv.iB = (int)(ColorPicker.activeColor.b * 255.0f);
                    }
                    
                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case CREATE_CIV_DIVISION: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                    @Override
                    public void update() {
                        Game.flagManager.flagEdit.lDivisionColors.set(Game.flagManager.activeColorID, new Color(ColorPicker.activeColor.r, ColorPicker.activeColor.g, ColorPicker.activeColor.b, 1.0f));
                    }
                    
                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case CIV_COLOR_NEWGAME: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                    @Override
                    public void update() {
                        Game.getCiv(ColorPicker.ACTIVE_CIV_ID).setR((int)(ColorPicker.activeColor.r * 255.0f));
                        Game.getCiv(ColorPicker.ACTIVE_CIV_ID).setG((int)(ColorPicker.activeColor.g * 255.0f));
                        Game.getCiv(ColorPicker.ACTIVE_CIV_ID).setB((int)(ColorPicker.activeColor.b * 255.0f));
                    }
                    
                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case CIV_COLOR_INGAME: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                    @Override
                    public void update() {
                        Game.getCiv(ColorPicker.ACTIVE_CIV_ID).setR((int)(ColorPicker.activeColor.r * 255.0f));
                        Game.getCiv(ColorPicker.ACTIVE_CIV_ID).setG((int)(ColorPicker.activeColor.g * 255.0f));
                        Game.getCiv(ColorPicker.ACTIVE_CIV_ID).setB((int)(ColorPicker.activeColor.b * 255.0f));
                        FBOProvincesBG.redrawnProvinces();
                    }
                    
                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            default: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                    @Override
                    public void update() {
                    }
                    
                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
        }
    }
    
    public ColorPicker() {
        this.iPosX = 100;
        this.iPosY = 100;
        this.visible = false;
        this.fontID = 0;
        this.activeHUE = false;
        this.activeSV = false;
        this.activeResize = false;
        this.activeMove = false;
        this.activeClose = false;
        this.iActiveColorID = -1;
        this.fAlpha = 1.0f;
        this.colorSVPos = Color.WHITE;
        this.lRGBBoxes = new ArrayList<Box>();
        this.lColorsBoxes = new ArrayList<Box>();
        this.lColors = new ArrayList<Color>();
        this.hsv = new float[] { 0.0f, 1.0f, 1.0f };
        this.hueVal = 1.0f;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(this.fontID), "G 255");
        this.iRGBTextWidth = (int)glyphLayout.width;
        this.updateRGBWidth();
        this.lRGBBoxes.add(new Box(CFG.PADDING, ImageManager.getImage(Images.btn_close).getHeight() + ImageManager.getImage(Images.btn_close).getHeight() / 2, this.iRGBTextWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.lRGBBoxes.add(new Box(CFG.PADDING, ImageManager.getImage(Images.btn_close).getHeight() + ImageManager.getImage(Images.btn_close).getHeight() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 2, this.iRGBTextWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.lRGBBoxes.add(new Box(CFG.PADDING, ImageManager.getImage(Images.btn_close).getHeight() + ImageManager.getImage(Images.btn_close).getHeight() / 2 + CFG.PADDING * 2 + (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 2, this.iRGBTextWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.updateColorPicker_Action(PickerAction.NONE_ACTION);
    }
    
    public final void buildColors() {
        this.lColorsBoxes.add(new Box(0, CFG.PADDING, this.getColorBoxWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.lColors.add(new Color(ColorPicker.activeColor.r, ColorPicker.activeColor.g, ColorPicker.activeColor.b, 1.0f));
        final Random oR = new Random();
        for (int i = this.lColorsBoxes.get(0).getPosX() + this.lColorsBoxes.get(0).getWidth(); i < CFG.GAME_WIDTH; i += this.getColorBoxWidth()) {
            this.lColorsBoxes.add(new Box(i, CFG.PADDING, this.getColorBoxWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            this.lColors.add(new Color(Game.oR.nextInt(256) / 255.0f, Game.oR.nextInt(256) / 255.0f, Game.oR.nextInt(256) / 255.0f, 1.0f));
        }
    }
    
    public final void updateColors() {
        this.lColors.set(0, new Color(ColorPicker.activeColor.r, ColorPicker.activeColor.g, ColorPicker.activeColor.b, 1.0f));
        for (int i = 1; i < this.lColors.size(); ++i) {
            this.lColors.set(i, new Color(Game.oR.nextInt(256) / 255.0f, Game.oR.nextInt(256) / 255.0f, Game.oR.nextInt(256) / 255.0f, 1.0f));
        }
    }
    
    private final int getPickerHue_InnerShadowWidth() {
        return CFG.PADDING * 2;
    }
    
    public final void draw(final SpriteBatch oSB, final int iTranslateX) {
        oSB.setColor(1.0f, 1.0f, 1.0f, this.fAlpha);
        Renderer.drawBoxHover(oSB, this.iPosX - CFG.PADDING * 2 + iTranslateX, this.iPosY - CFG.PADDING * 2, this.getWidth() + CFG.PADDING * 4, this.getHeight() + CFG.PADDING * 4, this.fAlpha);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.pickerHUE).draw(oSB, this.iPosX + this.iSVHeight + CFG.PADDING + iTranslateX, this.iPosY, this.iHUEWidth, this.iSVHeight);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.iPosX + this.iSVHeight + CFG.PADDING + iTranslateX, this.iPosY, this.getPickerHue_InnerShadowWidth(), this.iSVHeight);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth - this.getPickerHue_InnerShadowWidth() + iTranslateX, this.iPosY, this.getPickerHue_InnerShadowWidth(), this.iSVHeight, true, false);
        oSB.setColor(Color.WHITE);
        Images.pix.draw(oSB, this.iPosX + iTranslateX, this.iPosY, this.iSVHeight, this.iSVHeight);
        oSB.setColor(ColorPicker.hueColor);
        ImageManager.getImage(Images.pickerSV).draw(oSB, this.iPosX + iTranslateX, this.iPosY, this.iSVHeight, this.iSVHeight);
        if (this.activeMove) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.iPosX + 1 + iTranslateX, this.iPosY, true, true);
        }
        else {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.825f));
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.iPosX + 1 + iTranslateX, this.iPosY, true, true);
        }
        if (this.activeResize) {
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.iPosX + this.iSVHeight - ImageManager.getImage(Images.pickerEdge).getWidth() + iTranslateX, this.iPosY + this.iSVHeight - ImageManager.getImage(Images.pickerEdge).getHeight());
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.675f));
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.iPosX + this.iSVHeight - ImageManager.getImage(Images.pickerEdge).getWidth() + iTranslateX, this.iPosY + this.iSVHeight - ImageManager.getImage(Images.pickerEdge).getHeight());
            if (!Renderer.clipView_Start(oSB, this.iPosX + iTranslateX, CFG.GAME_HEIGHT - this.iPosY, this.iSVHeight, -this.iSVHeight)) {
                return;
            }
            oSB.setColor(this.colorSVPos);
            ImageManager.getImage(Images.pickerSVPos).draw(oSB, this.iPosX + this.iLastSVPosX - ImageManager.getImage(Images.pickerSVPos).getWidth() / 2 + iTranslateX, this.iPosY + this.iLastSVPosY - ImageManager.getImage(Images.pickerSVPos).getHeight() / 2);
            Renderer.clipView_End(oSB);
            oSB.setColor(0.0f, 0.0f, 0.0f, this.fAlpha);
            Images.pix.draw2(oSB, this.iPosX + this.iSVHeight + CFG.PADDING + CFG.PADDING + iTranslateX, this.iPosY - 1 + this.iLastHUEPosY, this.iHUEWidth - CFG.PADDING * 2 + 1, 1);
        }
        oSB.setColor(Color.BLACK);
        Renderer.drawRect(oSB, this.iPosX + iTranslateX, this.iPosY - 1, this.iSVHeight, this.iSVHeight + 2);
        Renderer.drawRect(oSB, this.iPosX + CFG.PADDING + this.iSVHeight + iTranslateX, this.iPosY - 1, this.iHUEWidth, this.iSVHeight + 2);
        this.drawRGBText(oSB, 0, this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + this.lRGBBoxes.get(0).getPosX() + iTranslateX, this.iPosY + this.lRGBBoxes.get(0).getPosY(), this.lRGBBoxes.get(0).getWidth(), this.lRGBBoxes.get(0).getHeight(), "R", "" + (int)(ColorPicker.activeColor.r * 255.0f), this.iRTextWidth);
        this.drawRGBText(oSB, 1, this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + this.lRGBBoxes.get(1).getPosX() + iTranslateX, this.iPosY + this.lRGBBoxes.get(1).getPosY(), this.lRGBBoxes.get(1).getWidth(), this.lRGBBoxes.get(1).getHeight(), "G", "" + (int)(ColorPicker.activeColor.g * 255.0f), this.iGTextWidth);
        this.drawRGBText(oSB, 2, this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + this.lRGBBoxes.get(2).getPosX() + iTranslateX, this.iPosY + this.lRGBBoxes.get(2).getPosY(), this.lRGBBoxes.get(2).getWidth(), this.lRGBBoxes.get(2).getHeight(), "B", "" + (int)(ColorPicker.activeColor.b * 255.0f), this.iBTextWidth);
        this.drawColorBoxes(oSB, this.iPosX + iTranslateX, this.iPosY + this.iSVHeight);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.iPosX + this.lColorsBoxes.get(0).getPosX() + iTranslateX, this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY(), this.getWidth(), this.lColorsBoxes.get(this.lColorsBoxes.size() - 1).getHeight(), false, true);
        if (this.iActiveColorID >= 0) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.iPosX + this.lColorsBoxes.get(this.iActiveColorID).getPosX() + iTranslateX, this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY(), this.lColorsBoxes.get(this.iActiveColorID).getWidth(), this.lColorsBoxes.get(this.lColorsBoxes.size() - 1).getHeight());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.fAlpha));
        Renderer.drawRect(oSB, this.iPosX + this.lColorsBoxes.get(0).getPosX() + iTranslateX, this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY(), this.getWidth(), this.lColorsBoxes.get(this.lColorsBoxes.size() - 1).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.activeClose ? Images.btnh_close : Images.btn_close).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() + iTranslateX, this.getPosY());
    }
    
    public final void drawRGBText(final SpriteBatch oSB, final int boxID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final String sLeft, final String sRight, final int nRightWidth) {
        oSB.setColor(Colors.COLOR_COLOR_PICKER_RGB_BG);
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, nHeight);
        if (ColorPicker.activeRGB == boxID) {
            oSB.setColor(Colors.COLOR_LOADING_SPLIT_ACTIVE);
        }
        else {
            oSB.setColor(Colors.COLOR_LOADING_SPLIT);
        }
        Renderer.drawRect(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawRect(oSB, nPosX - 1, nPosY - 1, nWidth + 2, nHeight + 2);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, sLeft, nPosX + CFG.PADDING, nPosY + nHeight / 2 - CFG.TEXT_HEIGHT / 2, new Color(0.84f, 0.84f, 0.88f, 1.0f));
        Renderer.fontMain.get(this.fontID).getData().setScale(0.9f);
        Renderer.drawText(oSB, this.fontID, sRight, nPosX + nWidth - CFG.PADDING - (int)(nRightWidth * 0.9f), nPosY + nHeight / 2 - CFG.TEXT_HEIGHT / 2, Color.WHITE);
        Renderer.fontMain.get(this.fontID).getData().setScale(1.0f);
    }
    
    public final void drawColorBoxes(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        for (int i = this.lColorsBoxes.size() - 1; i >= 0; --i) {
            if (this.lColorsBoxes.get(i).getVisible()) {
                oSB.setColor(this.lColors.get(i).r, this.lColors.get(i).g, this.lColors.get(i).b, this.fAlpha);
                Images.pix.draw(oSB, nPosX + this.lColorsBoxes.get(i).getPosX(), nPosY + this.lColorsBoxes.get(i).getPosY(), this.lColorsBoxes.get(i).getWidth(), this.lColorsBoxes.get(i).getHeight());
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.fAlpha));
                ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth(), nPosY + this.lColorsBoxes.get(i).getPosY(), 1, this.lColorsBoxes.get(i).getHeight());
            }
        }
    }
    
    public final void touchUp() {
        if (this.activeResize) {
            this.iLastSVPosX = (int)(this.iLastSVPosX * this.iSVHeight / (float)this.iStartResizeHeight);
            this.iLastSVPosY = (int)(this.iLastSVPosY * this.iSVHeight / (float)this.iStartResizeHeight);
            this.iLastHUEPosY = (int)(this.iLastHUEPosY * this.iSVHeight / (float)this.iStartResizeHeight);
        }
        else if (this.activeClose && this.iLastSVPosX >= this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() && this.iLastSVPosX <= this.getPosX() + this.getWidth() && this.iLastSVPosY >= this.iPosY && this.iLastSVPosY <= this.iPosY + ImageManager.getImage(Images.btn_close).getHeight()) {
            this.setVisible(false, null);
        }
        this.activeSV = false;
        this.activeHUE = false;
        this.activeResize = false;
        this.activeMove = false;
        this.activeClose = false;
        this.iActiveColorID = -1;
        this.fAlpha = 1.0f;
    }
    
    public final void touch(int screenX, int screenY) {
        if (this.activeHUE) {
            if (screenY <= this.iPosY) {
                screenY = this.iPosY + 1;
            }
            else if (screenY > this.iPosY + this.iSVHeight) {
                screenY = this.iPosY + this.iSVHeight;
            }
            if (screenX < this.iPosX + this.iSVHeight + CFG.PADDING) {
                screenX = this.iPosX + this.iSVHeight + CFG.PADDING;
            }
            else if (screenX > this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth) {
                screenX = this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth;
            }
            this.updateHUE(screenY);
            this.updateSV(this.iPosX + this.iLastSVPosX, this.iPosY + this.iLastSVPosY);
        }
        else if (this.activeSV) {
            if (screenY < this.iPosY) {
                screenY = this.iPosY;
            }
            else if (screenY > this.iPosY + this.iSVHeight) {
                screenY = this.iPosY + this.iSVHeight;
            }
            if (screenX < this.iPosX) {
                screenX = this.iPosX;
            }
            else if (screenX > this.iPosX + this.iSVHeight) {
                screenX = this.iPosX + this.iSVHeight;
            }
            this.updateSV(screenX, screenY);
            this.iLastSVPosX = screenX - this.iPosX;
            this.iLastSVPosY = screenY - this.iPosY;
        }
        else {
            if (this.activeResize) {
                this.setSVHeight(screenY - this.iPosY - this.iStartPosY);
                return;
            }
            if (this.activeMove) {
                this.setPosX(screenX - this.iStartPosX);
                this.setPosY(screenY - this.iStartPosY);
                this.fAlpha = 0.75f;
                return;
            }
            if (this.iActiveColorID >= 0) {
                int i = 0;
                while (i < this.lColorsBoxes.size()) {
                    if (this.lColorsBoxes.get(i).getVisible() && screenX >= this.iPosX + this.lColorsBoxes.get(i).getPosX() && screenX <= this.iPosX + this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth()) {
                        this.iActiveColorID = i;
                        if ((int)(ColorPicker.activeColor.r * 255.0f) != (int)(this.lColors.get(i).r * 255.0f) && (int)(ColorPicker.activeColor.r * 255.0f) != (int)(this.lColors.get(i).g * 255.0f) && (int)(ColorPicker.activeColor.r * 255.0f) != (int)(this.lColors.get(i).b * 255.0f)) {
                            this.RGBtoHSV((int)(this.lColors.get(i).r * 255.0f), (int)(this.lColors.get(i).g * 255.0f), (int)(this.lColors.get(i).b * 255.0f));
                            break;
                        }
                        break;
                    }
                    else {
                        ++i;
                    }
                }
            }
            else if (ColorPicker.activeRGB >= 0) {
                this.setActiveRGB_Box(screenX, screenY);
                this.showKeyboard();
            }
            else if (this.activeClose) {
                this.iLastSVPosX = screenX;
                this.iLastSVPosY = screenY;
            }
            else {
                if (screenX >= this.iPosX + this.iSVHeight - this.iResizeHeight && screenX <= this.iPosX + this.iSVHeight && screenY >= this.iPosY + this.iSVHeight - this.iResizeHeight && screenY <= this.iPosY + this.iSVHeight) {
                    this.activeResize = true;
                    this.iStartPosY = screenY - this.iPosY - this.iSVHeight;
                    this.iStartResizeHeight = this.iSVHeight;
                    this.fAlpha = 0.75f;
                    return;
                }
                if (screenX >= this.iPosX && screenX <= this.iPosX + this.iResizeHeight && screenY >= this.iPosY && screenY <= this.iPosY + this.iResizeHeight) {
                    this.activeMove = true;
                    this.iStartPosX = screenX - this.iPosX;
                    this.iStartPosY = screenY - this.iPosY;
                    return;
                }
                if (screenX >= this.iPosX + this.iSVHeight + CFG.PADDING && screenX <= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth && screenY >= this.iPosY && screenY <= this.iPosY + this.iSVHeight) {
                    this.updateHUE(screenY);
                    this.updateSV(this.iPosX + this.iLastSVPosX, this.iPosY + this.iLastSVPosY);
                    this.activeHUE = true;
                    ColorPicker.activeRGB = -1;
                    Game.keyboard.hideKeyboard();
                }
                else if (screenX >= this.iPosX && screenX <= this.iPosX + this.iSVHeight && screenY >= this.iPosY && screenY <= this.iPosY + this.iSVHeight) {
                    this.updateSV(screenX, screenY);
                    this.activeSV = true;
                    this.iLastSVPosX = screenX - this.iPosX;
                    this.iLastSVPosY = screenY - this.iPosY;
                    ColorPicker.activeRGB = -1;
                    Game.keyboard.hideKeyboard();
                }
                else if (screenX >= this.iPosX + this.lColorsBoxes.get(0).getPosX() && screenX <= this.iPosX + this.getWidth() && screenY >= this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY() && screenY <= this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY() + this.lColorsBoxes.get(0).getHeight()) {
                    int i = 0;
                    while (i < this.lColorsBoxes.size()) {
                        if (this.lColorsBoxes.get(i).getVisible() && screenX >= this.iPosX + this.lColorsBoxes.get(i).getPosX() && screenX <= this.iPosX + this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth() && screenY >= this.iPosY + this.iSVHeight + this.lColorsBoxes.get(i).getPosY() && screenY <= this.iPosY + this.iSVHeight + this.lColorsBoxes.get(i).getPosY() + this.lColorsBoxes.get(i).getHeight()) {
                            this.iActiveColorID = i;
                            if ((int)(ColorPicker.activeColor.r * 255.0f) != (int)(this.lColors.get(i).r * 255.0f) && (int)(ColorPicker.activeColor.r * 255.0f) != (int)(this.lColors.get(i).g * 255.0f) && (int)(ColorPicker.activeColor.r * 255.0f) != (int)(this.lColors.get(i).b * 255.0f)) {
                                this.RGBtoHSV((int)(this.lColors.get(i).r * 255.0f), (int)(this.lColors.get(i).g * 255.0f), (int)(this.lColors.get(i).b * 255.0f));
                                break;
                            }
                            break;
                        }
                        else {
                            ++i;
                        }
                    }
                    ColorPicker.activeRGB = -1;
                    Game.keyboard.hideKeyboard();
                }
                else if (screenX >= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(0).getPosX() && screenX <= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(0).getPosX() + this.lRGBBoxes.get(0).getWidth() && screenY >= this.iPosY + this.lRGBBoxes.get(0).getPosY() && screenY <= this.iPosY + this.lRGBBoxes.get(2).getPosY() + this.lRGBBoxes.get(2).getHeight()) {
                    this.setActiveRGB_Box(screenX, screenY);
                    this.showKeyboard();
                }
                else if (screenX >= this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() && screenX <= this.getPosX() + this.getWidth() && screenY >= this.iPosY && screenY <= this.iPosY + ImageManager.getImage(Images.btn_close).getHeight()) {
                    this.activeClose = true;
                }
            }
        }
        this.ColorPicker_AoC_Action.update();
    }
    
    private final void showKeyboard() {
        if (ColorPicker.activeRGB >= 0) {
            if (Keyboard.keyboardMode) {
                Game.keyboard.hideKeyboard();
            }
            Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.COLORPICKER, (ColorPicker.activeRGB == 0) ? ("" + (int)(ColorPicker.activeColor.r * 255.0f)) : ((ColorPicker.activeRGB == 1) ? ("" + (int)(ColorPicker.activeColor.g * 255.0f)) : ("" + (int)(ColorPicker.activeColor.b * 255.0f))));
        }
    }
    
    private final void setActiveRGB_Box(final int screenX, final int screenY) {
        for (int i = 0; i < this.lRGBBoxes.size(); ++i) {
            if (screenX >= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(i).getPosX() && screenX <= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(i).getPosX() + this.lRGBBoxes.get(i).getWidth() && screenY >= this.iPosY + this.lRGBBoxes.get(i).getPosY() && screenY <= this.iPosY + this.lRGBBoxes.get(i).getPosY() + this.lRGBBoxes.get(i).getHeight()) {
                ColorPicker.activeRGB = i;
                return;
            }
        }
        ColorPicker.activeRGB = -1;
    }
    
    private final void updateHUE(final int screenY) {
        final float perc = 1.0f - (screenY - this.iPosY) / (float)this.iSVHeight;
        this.hsv[0] = (this.hueVal = perc * 360.0f);
        this.hsv[1] = (this.hsv[2] = 1.0f);
        this.HSVtoRGB(this.hsv, ColorPicker.hueColor);
        this.updateSV(this.iLastSVPosX, this.iLastSVPosY);
        this.iLastHUEPosY = screenY - this.iPosY;
    }
    
    private final void updateSV(final int screenX, final int screenY) {
        final float sat = (screenX - this.iPosX) / (float)this.iSVHeight;
        final float val = 1.0f - (screenY - this.iPosY) / (float)this.iSVHeight;
        this.hsv[0] = this.hueVal;
        this.hsv[1] = sat;
        this.hsv[2] = val;
        this.updateColorSVPos(screenY - this.iPosY);
        this.HSVtoRGB(this.hsv, ColorPicker.activeColor);
        this.updateRGBWidth();
    }
    
    private final void updateColorSVPos(final int nPosY) {
        if (this.iSVHeight * 0.1f > nPosY) {
            this.colorSVPos = Color.BLACK;
        }
        else {
            this.colorSVPos = Color.WHITE;
        }
    }
    
    public final void setActiveRGBColor(final float R, final float G, final float B) {
        this.setActiveRGBColor((int)(R * 255.0f), (int)(G * 255.0f), (int)(B * 255.0f));
    }
    
    public final void setActiveRGBColor(final int R, final int G, final int B) {
        if (Keyboard.keyboardMode || ColorPicker.activeRGB >= 0) {
            ColorPicker.activeRGB = -1;
            Game.keyboard.hideKeyboard();
        }
        this.RGBtoHSV(R, G, B);
    }
    
    public final void RGBtoHSV(final int R, final int G, final int B) {
        final float x = (float)Math.min(Math.min(R, G), B);
        final float val = (float)Math.max(Math.max(R, G), B);
        if (x == val) {
            this.hsv[0] = 0.0f;
            this.hsv[1] = 0.0f;
        }
        else {
            final float f = (R == (int)x) ? ((float)(G - B)) : ((float)((G == (int)x) ? (B - R) : (R - G)));
            final float i = (R == (int)x) ? 3.0f : ((float)((G == (int)x) ? 5 : 1));
            this.hsv[0] = (i - f / (val - x)) * 60.0f % 360.0f;
            this.hsv[1] = (val - x) / val;
        }
        this.hsv[2] = val / 255.0f;
        this.hueVal = this.hsv[0];
        this.iLastSVPosX = (int)(this.hsv[1] * this.iSVHeight);
        this.iLastSVPosY = (int)(-this.hsv[2] * this.iSVHeight + this.iSVHeight);
        this.iLastHUEPosY = (int)(this.iSVHeight - this.hsv[0] / 360.0f * this.iSVHeight);
        this.updateSV(this.iPosY + this.iLastSVPosX, this.iPosY + this.iLastSVPosY);
        this.updateHUE(this.iPosY + this.iLastHUEPosY);
        ColorPicker.activeColor.r = R / 255.0f;
        ColorPicker.activeColor.g = G / 255.0f;
        ColorPicker.activeColor.b = B / 255.0f;
        this.updateRGBWidth();
    }
    
    private final void HSVtoRGB(final float[] hsv, final Color rgbOut) {
        float h = hsv[0];
        final float s = hsv[1];
        final float v = hsv[2];
        float b;
        float r = 0.0f;
        float g;
        if (s == 0.0f) {
            g = (r = (b = v));
        }
        else {
            h /= 60.0f;
            final int i = (int)h;
            final float f = h - i;
            final float p = v * (1.0f - s);
            final float q = v * (1.0f - s * f);
            final float t = v * (1.0f - s * (1.0f - f));
            switch (i) {
                case 0: {
                    r = v;
                    g = t;
                    b = p;
                    break;
                }
                case 1: {
                    r = q;
                    g = v;
                    b = p;
                    break;
                }
                case 2: {
                    r = p;
                    g = v;
                    b = t;
                    break;
                }
                case 3: {
                    r = p;
                    g = q;
                    b = v;
                    break;
                }
                case 4: {
                    r = t;
                    g = p;
                    b = v;
                    break;
                }
                default: {
                    r = v;
                    g = p;
                    b = q;
                    break;
                }
            }
        }
        rgbOut.r = r;
        rgbOut.g = g;
        rgbOut.b = b;
        rgbOut.a = 1.0f;
    }
    
    public final void updateRGBWidth() {
        this.iRTextWidth = Renderer.getTextWidth("" + (int)(ColorPicker.activeColor.r * 255.0f));
        this.iGTextWidth = Renderer.getTextWidth("" + (int)(ColorPicker.activeColor.g * 255.0f));
        this.iBTextWidth = Renderer.getTextWidth("" + (int)(ColorPicker.activeColor.b * 255.0f));
    }
    
    public final void setPosX(int iPosX) {
        if (iPosX > CFG.GAME_WIDTH - ImageManager.getImage(Images.pickerSV).getHeight() / 2) {
            iPosX = CFG.GAME_WIDTH - ImageManager.getImage(Images.pickerSV).getHeight() / 2;
        }
        else if (iPosX < CFG.PADDING * 2) {
            iPosX = CFG.PADDING * 2;
        }
        this.iPosX = iPosX;
    }
    
    public final int getPosX() {
        return this.iPosX;
    }
    
    public final void setPosY(int iPosY) {
        if (iPosY > CFG.GAME_HEIGHT - ImageManager.getImage(Images.pickerSV).getHeight() / 2) {
            iPosY = CFG.GAME_HEIGHT - ImageManager.getImage(Images.pickerSV).getHeight() / 2;
        }
        else if (iPosY < CFG.PADDING * 2) {
            iPosY = CFG.PADDING * 2;
        }
        this.iPosY = iPosY;
    }
    
    public final int getPosY() {
        return this.iPosY;
    }
    
    public final int getWidth() {
        return this.iSVHeight + this.iHUEWidth + CFG.PADDING + this.iRGBTextWidth + CFG.PADDING * 3;
    }
    
    public final int getHeight() {
        return this.iSVHeight + CFG.TEXT_HEIGHT + CFG.PADDING * 3;
    }
    
    public final Color getActiveColor() {
        return ColorPicker.activeColor;
    }
    
    public final boolean getVisible() {
        return this.visible;
    }
    
    public final void hideColorPicker() {
        this.visible = false;
        this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
            @Override
            public void update() {
            }
            
            @Override
            public void setActiveProvince_Action() {
            }
        };
    }
    
    public final void setVisible(final boolean visible, final PickerAction nAction) {
        if (nAction != null) {
            this.updateColorPicker_Action(nAction);
        }
        else {
            this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action() {
                @Override
                public void update() {
                }
                
                @Override
                public void setActiveProvince_Action() {
                }
            };
        }
        this.visible = visible;
        if (Keyboard.keyboardMode || ColorPicker.activeRGB >= 0) {
            ColorPicker.activeRGB = -1;
            Game.keyboard.hideKeyboard();
        }
    }
    
    public ColorPicker_AoC_Action getColorPickerAction() {
        return this.ColorPicker_AoC_Action;
    }
    
    public final void setHueWidth(final int iHUEWidth) {
        this.iHUEWidth = iHUEWidth;
    }
    
    public final void setSVHeight(int iSVHeight) {
        if (iSVHeight < ImageManager.getImage(Images.pickerSV).getHeight()) {
            iSVHeight = ImageManager.getImage(Images.pickerSV).getHeight();
        }
        else if (this.getPosY() + iSVHeight + CFG.TEXT_HEIGHT + CFG.PADDING * 5 > CFG.GAME_HEIGHT) {
            iSVHeight = CFG.GAME_HEIGHT - this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING * 5);
        }
        this.iSVHeight = iSVHeight;
        for (int i = 1; i < this.lColorsBoxes.size(); ++i) {
            this.lColorsBoxes.get(i).setWidth(this.lColorsBoxes.get(0).getWidth());
            this.lColorsBoxes.get(i).setVisible(true);
        }
        for (int i = this.lColorsBoxes.size() - 1; i > 0; --i) {
            if (this.lColorsBoxes.get(i).getPosX() > this.getWidth()) {
                this.lColorsBoxes.get(i).setVisible(false);
            }
            else {
                this.lColorsBoxes.get(i).setVisible(true);
                if (this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth() > this.getWidth()) {
                    this.lColorsBoxes.get(i).setWidth(this.getWidth() - this.lColorsBoxes.get(i).getPosX());
                }
            }
        }
    }
    
    public final int getColorBoxWidth() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
    }
    
    public final void setResizeHeight(final int iResizeHeight) {
        this.iResizeHeight = iResizeHeight;
    }
    
    static {
        ColorPicker.activeRGB = -1;
        ColorPicker.ACTIVE_CIV_ID = 0;
        ColorPicker.hueColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        ColorPicker.activeColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public enum PickerAction
    {
        NONE_ACTION, 
        GAMECIVS_EDIT, 
        CREATE_CIV_EDIT, 
        CREATE_CIV_DIVISION, 
        CREATE_CIV_OVERLAY, 
        CIV_COLOR_NEWGAME, 
        CIV_COLOR_INGAME;
    }
    
    class Box
    {
        private int iPosX;
        private int iPosY;
        private int iWidth;
        private int iHeight;
        private boolean visible;
        
        public Box(final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
            this.visible = true;
            this.iPosX = iPosX;
            this.iPosY = iPosY;
            this.iWidth = iWidth;
            this.iHeight = iHeight;
        }
        
        public final int getPosX() {
            return this.iPosX;
        }
        
        public final void setPosX(final int iPosX) {
            this.iPosX = iPosX;
        }
        
        public final int getPosY() {
            return this.iPosY;
        }
        
        public final void setPosY(final int iPosY) {
            this.iPosY = iPosY;
        }
        
        public final int getWidth() {
            return this.iWidth;
        }
        
        public final int getHeight() {
            return this.iHeight;
        }
        
        public final void setWidth(final int iWidth) {
            this.iWidth = iWidth;
        }
        
        public final void setHeight(final int iHeight) {
            this.iHeight = iHeight;
        }
        
        public final void setVisible(final boolean visible) {
            this.visible = visible;
        }
        
        public final boolean getVisible() {
            return this.visible;
        }
    }
    
    public interface ColorPicker_AoC_Action
    {
        void update();
        
        void setActiveProvince_Action();
    }
}
