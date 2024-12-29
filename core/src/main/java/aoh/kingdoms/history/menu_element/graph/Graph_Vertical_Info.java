// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.graph;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import java.util.List;

public class Graph_Vertical_Info
{
    private List<String> lTexts;
    private int iTextsSize;
    private List<Integer> lTextWidths;
    private List<Color> lColors;
    private boolean isMoveable;
    private boolean moveRight;
    private int iTextWidth;
    private int iTextPosX;
    private long lTime;
    private List<Integer> lSortedIDs;
    
    protected Graph_Vertical_Info(final List<String> nTexts, final List<Color> nColors, final int iWidth, final boolean nSortText) {
        this.lTexts = null;
        this.iTextsSize = 0;
        this.lTextWidths = null;
        this.lColors = null;
        this.isMoveable = false;
        this.moveRight = false;
        this.iTextWidth = 0;
        this.iTextPosX = 0;
        this.lTime = 0L;
        this.lSortedIDs = null;
        this.iTextsSize = nTexts.size();
        this.lTexts = new ArrayList<String>();
        this.lColors = new ArrayList<Color>();
        this.lSortedIDs = new ArrayList<Integer>();
        final List<Boolean> tempAdded = new ArrayList<Boolean>();
        for (int i = 0; i < this.iTextsSize; ++i) {
            this.lSortedIDs.add(i);
            tempAdded.add(false);
        }
        if (nSortText) {
            while (nTexts.size() != this.lTexts.size()) {
                int nMinID = 0;
                for (int j = 0; j < this.iTextsSize; ++j) {
                    if (!tempAdded.get(j)) {
                        nMinID = j;
                        break;
                    }
                }
                for (int j = nMinID + 1; j < this.iTextsSize; ++j) {
                    if (!tempAdded.get(j) && CFG.compareAlphabetic_TwoString(nTexts.get(nMinID), nTexts.get(j))) {
                        nMinID = j;
                    }
                }
                this.lTexts.add(nTexts.get(nMinID));
                this.lColors.add(nColors.get(nMinID));
                tempAdded.set(nMinID, true);
                this.lSortedIDs.set(nMinID, this.lTexts.size() - 1);
            }
        }
        else {
            this.lTexts = nTexts;
            this.lColors = nColors;
        }
        this.lTextWidths = new ArrayList<Integer>();
        Renderer.fontMain.get(0).getData().setScale(0.7f);
        for (int i = 0; i < this.iTextsSize; ++i) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(0), this.lTexts.get(i));
            this.iTextWidth += (int)Renderer.glyphLayout.width;
            this.lTextWidths.add((int)Renderer.glyphLayout.width);
        }
        Renderer.fontMain.get(0).getData().setScale(1.0f);
        this.iTextWidth += CFG.PADDING * this.iTextsSize + CFG.PADDING * (this.iTextsSize - 1) + (int)(CFG.TEXT_HEIGHT * 0.7f * this.iTextsSize);
        this.updateMoveable(iWidth);
    }
    
    protected final void updateMoveable(final int iWidth) {
        if (this.iTextWidth > iWidth) {
            this.isMoveable = true;
            this.resetisMoveable();
        }
        else {
            this.resetisMoveable();
            this.isMoveable = false;
            this.iTextPosX = iWidth / 2 - this.iTextWidth / 2;
        }
    }
    
    protected final void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        if (this.isMoveable) {
            Renderer.clipView_Start(oSB, nPosX, CFG.GAME_HEIGHT - nPosY, nWidth, -(int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING);
            if (this.lTime < CFG.currentTimeMillis - 45L) {
                this.lTime = CFG.currentTimeMillis;
                if (this.moveRight) {
                    --this.iTextPosX;
                    if (-this.iTextPosX + nWidth >= this.iTextWidth + CFG.PADDING) {
                        this.moveRight = !this.moveRight;
                    }
                }
                else {
                    ++this.iTextPosX;
                    if (this.iTextPosX >= 0) {
                        this.moveRight = !this.moveRight;
                    }
                }
            }
        }
        int i = 0;
        int tempOffsetX = 0;
        while (i < this.iTextsSize) {
            oSB.setColor((Color)this.lColors.get(i));
            Images.pix.draw(oSB, nPosX + tempOffsetX + this.iTextPosX, nPosY, (int)(CFG.TEXT_HEIGHT * 0.7f), (int)(CFG.TEXT_HEIGHT * 0.7f));
            oSB.setColor(new Color(this.lColors.get(i).r, this.lColors.get(i).g, this.lColors.get(i).b, 0.7f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + tempOffsetX + this.iTextPosX, nPosY, (int)(CFG.TEXT_HEIGHT * 0.7f), (int)(CFG.TEXT_HEIGHT * 0.7f));
            tempOffsetX += (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING;
            Renderer.drawTextWithShadow(oSB, this.lTexts.get(i), nPosX + tempOffsetX + this.iTextPosX, nPosY, new Color(this.lColors.get(i).r, this.lColors.get(i).g, this.lColors.get(i).b, 0.7f));
            tempOffsetX += this.lTextWidths.get(i) + CFG.PADDING;
            ++i;
        }
        if (this.isMoveable) {
            Renderer.clipView_End(oSB);
        }
    }
    
    protected final void resetisMoveable() {
        this.iTextPosX = 0;
        this.moveRight = true;
    }
    
    protected final int getTextSize() {
        return this.iTextsSize;
    }
    
    protected final String getText(final int i) {
        return this.lTexts.get(i);
    }
    
    protected final List<Integer> getSorted() {
        return this.lSortedIDs;
    }
    
    protected final int getSortedID(final int i) {
        return this.lSortedIDs.get(i);
    }
    
    protected final List<Color> getColors() {
        return this.lColors;
    }
}
