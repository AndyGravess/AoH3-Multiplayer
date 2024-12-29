// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.LawsManager;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.map.allianceHRE.HREManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import java.util.List;

public class Button_HRE_Reform extends Button
{
    public int imageID;
    public List<String> sLines;
    public int iLineSize;
    public int reformID;
    
    public Button_HRE_Reform(final String sText2, final String sDesc, final int iPosX, final int iPosY, final int nWidth, final int imageID, final int reformID) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.init(sText2, CFG.FONT_REGULAR, 0, iPosX, iPosY, nWidth, getButtonHeight(), true, true, false, false);
        this.reformID = reformID;
        this.imageID = imageID;
        final String[] words = sDesc.split(" ");
        int textPosX = 0;
        final int maxW = this.getInnerWidth() - CFG.PADDING * 4;
        String currentLine = "";
        int tWidth = 0;
        int tHeight = 0;
        for (int i = 0, iSize = words.length; i < iSize; ++i) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), words[i] + " ");
            tWidth = (int)Renderer.glyphLayout.width;
            textPosX += tWidth;
            if (textPosX < maxW) {
                currentLine = currentLine + words[i] + " ";
            }
            else {
                this.sLines.add(currentLine);
                currentLine = words[i] + " ";
                textPosX = tWidth;
            }
        }
        if (currentLine.length() > 0) {
            this.sLines.add(currentLine);
        }
        if (this.sLines.size() > 0 && this.sLines.get(0).length() > 0) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sLines.get(0));
            tHeight = (int)Renderer.glyphLayout.height;
        }
        this.iLineSize = this.sLines.size();
        this.setHeight(Math.max(getButtonHeight(), getButtonHeight() / 2 + CFG.PADDING * 3 + tHeight * this.sLines.size() + (this.sLines.size() - 1) * CFG.PADDING * 2));
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG.r, Colors.COLOR_NOTIFICATION_BG.g, Colors.COLOR_NOTIFICATION_BG.b, (this.getIsHovered() || isActive) ? 0.5f : 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 1.0f : 0.75f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, getButtonHeight(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.3f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG.r, Colors.COLOR_NOTIFICATION_BG.g, Colors.COLOR_NOTIFICATION_BG.b, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2, 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.85f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.5f : 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, (getButtonHeight() - CFG.PADDING * 3) / 2, 1.0f);
        oSB.setColor(Color.WHITE);
        this.drawImage(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    protected void drawImage(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setShader(Renderer.shaderAlpha);
        HREManager.reformsIMG.get(this.reformID).getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.lawMask).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.lawOver).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 3 + iTranslateX, this.getPosY() + getButtonHeight() / 4 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        for (int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sLines.get(i), this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + getButtonHeight() / 2 + CFG.PADDING + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, Colors.getColorButtonHover(isActive, this.getIsHovered()));
        }
    }
    
    public static int getButtonHeight() {
        return HREManager.reformsIMG.get(0).getHeight() + getPaddingIMG() * 2;
    }
    
    public static int getPaddingIMG() {
        return CFG.PADDING + CFG.PADDING / 2;
    }
    
    public int getInnerPosX() {
        return getPaddingIMG() * 2 + LawsManager.lawsImages.get(this.imageID).getWidth();
    }
    
    public int getInnerWidth() {
        return this.getWidth() - this.getInnerPosX();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover2(isActive, this.getIsHovered());
    }
    
    @Override
    public int getCurrent() {
        return this.reformID;
    }
}
