// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyTree;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusIdeology;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_Unit;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.LawsManager;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.map.BuildingsManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.map.AdvantagesManager;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import java.util.List;

public class ButtonTechnology extends Button
{
    public int iTechnologyID;
    public int iImageID;
    public int btnIMG;
    public String sCost;
    public int iCostWidth;
    public int iCostHeight;
    public String sQueue;
    public int iQueueWidth;
    public int iQueueHeight;
    public boolean inTechTree;
    public static int techIconWidth;
    public static int techIconHeight;
    public int iCivID;
    public List<Integer> ideologies;
    public List<Integer> advantages;
    
    public ButtonTechnology(final int btnIMG, final int iTechnologyID, final int iPosX, final int iPosY, final boolean inTechTree, final int iPosInQueue) {
        this.sQueue = null;
        this.ideologies = new ArrayList<Integer>();
        this.advantages = new ArrayList<Integer>();
        this.iCivID = Game.player.iCivID;
        this.init(btnIMG, iTechnologyID, iPosX, iPosY, inTechTree, iPosInQueue);
    }
    
    public ButtonTechnology(final int btnIMG, final int iTechnologyID, final int iPosX, final int iPosY, final boolean inTechTree, final int iPosInQueue, final int iCivID) {
        this.sQueue = null;
        this.ideologies = new ArrayList<Integer>();
        this.advantages = new ArrayList<Integer>();
        this.iCivID = iCivID;
        this.init(btnIMG, iTechnologyID, iPosX, iPosY, inTechTree, iPosInQueue);
    }
    
    public void init(final int btnIMG, final int iTechnologyID, final int iPosX, final int iPosY, final boolean inTechTree, final int iPosInQueue) {
        this.iTechnologyID = iTechnologyID;
        this.btnIMG = btnIMG;
        this.iImageID = TechnologyTree.lTechnology.get(iTechnologyID).ImageID;
        this.inTechTree = inTechTree;
        if (iPosInQueue >= 0) {
            this.sQueue = "" + (iPosInQueue + 1);
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sQueue);
            this.iQueueWidth = (int)Renderer.glyphLayout.width;
            this.iQueueHeight = (int)Renderer.glyphLayout.height;
        }
        this.fontID = CFG.FONT_REGULAR;
        this.init(TechnologyTree.lTechnology.get(iTechnologyID).Name, this.fontID, this.iTextPositionX, iPosX, iPosY, TechnologyTree.iTechnologyWidth, TechnologyTree.iTechnologyHeight, true, true, false, false);
        this.sCost = "" + CFG.getPrecision2(TechnologyTree.getResearchCost(iTechnologyID, this.iCivID), 1);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sCost);
        this.iCostWidth = (int)Renderer.glyphLayout.width;
        this.iCostHeight = (int)Renderer.glyphLayout.height;
        final float iconScale = this.getImageScale(Game_Calendar.IMG_TECHNOLOGY);
        ButtonTechnology.techIconWidth = (int)(ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getWidth() * iconScale);
        ButtonTechnology.techIconHeight = (int)(ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getHeight() * iconScale);
        for (int i = 0; i < Game.ideologiesManager.getIdeologiesSize(); ++i) {
            if (Game.ideologiesManager.getIdeology(i).REQUIRED_TECHNOLOGY == iTechnologyID) {
                this.ideologies.add(i);
            }
        }
        for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
            if (AdvantagesManager.advantages.get(i).RequiredTechID == iTechnologyID) {
                this.advantages.add(i);
            }
        }
        if (inTechTree) {
            int tWMax = 0;
            while (this.iTextWidth >= this.getWidth() - CFG.PADDING * 5 - this.iCostWidth - ButtonTechnology.techIconWidth && this.getText().length() > 5 && ++tWMax < 100) {
                this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
            }
        }
        else {
            int tWMax = 0;
            while (this.iTextWidth >= this.getWidth() - CFG.PADDING * 3 && this.getText().length() > 5 && ++tWMax < 100) {
                this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
            }
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        ImageManager.getImage(this.btnIMG).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.btnIMG == Images.techBlue || this.btnIMG == Images.techAvailable) {
            final float fProgress = Game.getCiv(this.iCivID).getResearchProgress(this.iTechnologyID);
            if (fProgress > 0.0f) {
                ImageManager.getImage(Images.techResearched).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, (int)(ImageManager.getImage(Images.techResearched).getWidth() * fProgress), ImageManager.getImage(Images.techResearched).getHeight());
            }
        }
        TechnologyTree.technologyImages.get(this.iImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
            ImageManager.getImage(Images.gradientFull2).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(this.btnIMG).getWidth(), ImageManager.getImage(this.btnIMG).getHeight());
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(this.btnIMG).getWidth(), ImageManager.getImage(this.btnIMG).getHeight());
            oSB.setColor(Color.WHITE);
        }
        if (this.btnIMG == Images.techResearched) {
            oSB.setColor(new Color(Colors.TECH_RESEARCHED.r, Colors.TECH_RESEARCHED.g, Colors.TECH_RESEARCHED.b, 0.8f));
            ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth(), this.titleH());
            ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.techCorner).getWidth(), this.titleH(), true, false);
            oSB.setColor(new Color(Colors.TECH_RESEARCHED.r, Colors.TECH_RESEARCHED.g, Colors.TECH_RESEARCHED.b, 0.25f));
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.35f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
            oSB.setColor(new Color(Colors.TECH_RESEARCHED2.r, Colors.TECH_RESEARCHED2.g, Colors.TECH_RESEARCHED2.b, 1.0f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
            oSB.setColor(new Color(Colors.TECH_RESEARCHED3.r, Colors.TECH_RESEARCHED3.g, Colors.TECH_RESEARCHED3.b, 0.8f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
            oSB.setColor(new Color(Colors.TECH_RESEARCHED.r, Colors.TECH_RESEARCHED.g, Colors.TECH_RESEARCHED.b, 1.0f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), 1);
        }
        else if (this.btnIMG == Images.techGray) {
            oSB.setColor(new Color(Colors.TECH_GRAY.r, Colors.TECH_GRAY.g, Colors.TECH_GRAY.b, 0.8f));
            ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth(), this.titleH());
            ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.techCorner).getWidth(), this.titleH(), true, false);
            oSB.setColor(new Color(Colors.TECH_GRAY.r, Colors.TECH_GRAY.g, Colors.TECH_GRAY.b, 0.25f));
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.35f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
            oSB.setColor(new Color(Colors.TECH_GRAY2.r, Colors.TECH_GRAY2.g, Colors.TECH_GRAY2.b, 1.0f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
            oSB.setColor(new Color(Colors.TECH_GRAY3.r, Colors.TECH_GRAY3.g, Colors.TECH_GRAY3.b, 0.8f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
            oSB.setColor(new Color(Colors.TECH_GRAY.r, Colors.TECH_GRAY.g, Colors.TECH_GRAY.b, 1.0f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), 1);
        }
        else if (this.btnIMG == Images.techBlue) {
            float fProgress = Game.getCiv(this.iCivID).getResearchProgress(this.iTechnologyID);
            if (fProgress >= 0.0f) {
                fProgress = Math.max(fProgress, 0.01f);
                Renderer.clipView_Start(oSB, this.getPosX() + (int)(this.getWidth() * fProgress) + iTranslateX, CFG.GAME_HEIGHT - (this.getPosY() + iTranslateY), (int)(this.getWidth() - this.getWidth() * fProgress), -this.getHeight());
                oSB.setColor(new Color(Colors.TECH_BLUE.r, Colors.TECH_BLUE.g, Colors.TECH_BLUE.b, 0.8f));
                ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth(), this.titleH());
                ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.techCorner).getWidth(), this.titleH(), true, false);
                oSB.setColor(new Color(Colors.TECH_BLUE.r, Colors.TECH_BLUE.g, Colors.TECH_BLUE.b, 0.25f));
                oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.35f));
                Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
                oSB.setColor(new Color(Colors.TECH_BLUE2.r, Colors.TECH_BLUE2.g, Colors.TECH_BLUE2.b, 1.0f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
                oSB.setColor(new Color(Colors.TECH_BLUE3.r, Colors.TECH_BLUE3.g, Colors.TECH_BLUE3.b, 0.8f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
                oSB.setColor(new Color(Colors.TECH_BLUE.r, Colors.TECH_BLUE.g, Colors.TECH_BLUE.b, 1.0f));
                Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), 1);
                Renderer.clipView_End(oSB);
                Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - (this.getPosY() + iTranslateY), (int)(this.getWidth() * fProgress), -this.getHeight());
                oSB.setColor(new Color(Colors.TECH_RESEARCHED.r, Colors.TECH_RESEARCHED.g, Colors.TECH_RESEARCHED.b, 0.8f));
                ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth(), this.titleH());
                ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.techCorner).getWidth(), this.titleH(), true, false);
                oSB.setColor(new Color(Colors.TECH_RESEARCHED.r, Colors.TECH_RESEARCHED.g, Colors.TECH_RESEARCHED.b, 0.25f));
                oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.35f));
                Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
                oSB.setColor(new Color(Colors.TECH_RESEARCHED2.r, Colors.TECH_RESEARCHED2.g, Colors.TECH_RESEARCHED2.b, 1.0f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
                oSB.setColor(new Color(Colors.TECH_RESEARCHED3.r, Colors.TECH_RESEARCHED3.g, Colors.TECH_RESEARCHED3.b, 0.8f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
                oSB.setColor(new Color(Colors.TECH_RESEARCHED.r, Colors.TECH_RESEARCHED.g, Colors.TECH_RESEARCHED.b, 1.0f));
                Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), 1);
                Renderer.clipView_End(oSB);
            }
            else {
                oSB.setColor(new Color(Colors.TECH_BLUE.r, Colors.TECH_BLUE.g, Colors.TECH_BLUE.b, 0.8f));
                ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth(), this.titleH());
                ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.techCorner).getWidth(), this.titleH(), true, false);
                oSB.setColor(new Color(Colors.TECH_BLUE.r, Colors.TECH_BLUE.g, Colors.TECH_BLUE.b, 0.25f));
                oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.35f));
                Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
                oSB.setColor(new Color(Colors.TECH_BLUE2.r, Colors.TECH_BLUE2.g, Colors.TECH_BLUE2.b, 1.0f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
                oSB.setColor(new Color(Colors.TECH_BLUE3.r, Colors.TECH_BLUE3.g, Colors.TECH_BLUE3.b, 0.8f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
                oSB.setColor(new Color(Colors.TECH_BLUE.r, Colors.TECH_BLUE.g, Colors.TECH_BLUE.b, 1.0f));
                Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), 1);
            }
        }
        else {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.8f));
            ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth(), this.titleH());
            ImageManager.getImage(Images.techCorner).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.techCorner).getWidth() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.techCorner).getWidth(), this.titleH(), true, false);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.25f));
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.35f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
            oSB.setColor(Colors.COLOR_GRADIENT);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.titleH());
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 1.0f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 1.0f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.titleH() + iTranslateY, this.getWidth(), 1);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public int titleH() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 3;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        int innerX = CFG.PADDING * 2;
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnlocksNukes && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techNuke).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnlocksAccessToTheSea && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techSea).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).size() > 0) {
            for (int i = 0; i < TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).size(); ++i) {
                oSB.setShader(Renderer.shaderAlpha);
                BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).get(i).building).ImageID[TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).get(i).buildingID]).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.techMask).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techMask).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING + iTranslateY);
                oSB.flush();
                oSB.setShader(Renderer.shaderDefault);
                ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
                if (innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING >= this.getWidth()) {
                    break;
                }
            }
        }
        if (TechnologyTree.lTechUnlocksUnits.get(this.iTechnologyID).size() > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            for (int i = 0; i < TechnologyTree.lTechUnlocksUnits.get(this.iTechnologyID).size(); ++i) {
                oSB.setShader(Renderer.shaderAlpha);
                ArmyManager.armyImages.get(ArmyManager.lArmy.get(TechnologyTree.lTechUnlocksUnits.get(this.iTechnologyID).get(i).unitID).get(TechnologyTree.lTechUnlocksUnits.get(this.iTechnologyID).get(i).armyID).ImageID).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.techMask).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techMask).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING + iTranslateY);
                oSB.flush();
                oSB.setShader(Renderer.shaderDefault);
                ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
                if (innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING >= this.getWidth()) {
                    break;
                }
            }
        }
        if (TechnologyTree.lTechUnlocksLaws.get(this.iTechnologyID).size() > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            for (int i = 0; i < TechnologyTree.lTechUnlocksLaws.get(this.iTechnologyID).size(); ++i) {
                oSB.setShader(Renderer.shaderAlpha);
                LawsManager.lawsImages.get(LawsManager.laws.get(TechnologyTree.lTechUnlocksLaws.get(this.iTechnologyID).get(i).law).ImageID[TechnologyTree.lTechUnlocksLaws.get(this.iTechnologyID).get(i).lawID]).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                oSB.flush();
                oSB.setShader(Renderer.shaderDefault);
                ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
                if (innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING >= this.getWidth()) {
                    break;
                }
            }
        }
        if (this.ideologies.size() > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            for (int i = 0; i < this.ideologies.size(); ++i) {
                if (innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
                    oSB.setShader(Renderer.shaderAlpha);
                    ImageManager.getImage(Images.techEmpty).getTexture().bind(1);
                    Gdx.gl.glActiveTexture(33984);
                    ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                    oSB.flush();
                    oSB.setShader(Renderer.shaderDefault);
                    Game.ideologiesManager.ideologiesImages.get(this.ideologies.get(i)).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + ImageManager.getImage(Images.techOver).getWidth() / 2 - Game.ideologiesManager.ideologiesImages.get(this.ideologies.get(i)).getWidth() / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + ImageManager.getImage(Images.techOver).getHeight() / 2 - Game.ideologiesManager.ideologiesImages.get(this.ideologies.get(i)).getHeight() / 2 + iTranslateY);
                    ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                    innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
                }
            }
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfCapitalCity > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.buildingCapital.get(Math.min(Game.getCiv(this.iCivID).getCapitalLevel(), GameValues.capital.CAPITAL_IMAGES - 1))).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademy > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.armyMilitaryAcademy).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademyForGenerals > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.armyGeneralAcademy).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).BattleWidth > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techBattleWidth).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralAttack > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techGeneral).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralDefense > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techGeneral).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaxMorale > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techMorale).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).Discipline > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techDiscipline).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsAttack > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techAttack).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsDefense > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techDefense).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).Legacy > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.techLegacy).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
            innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
        }
        if (this.advantages.size() > 0 && innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
            for (int i = 0; i < this.advantages.size(); ++i) {
                if (innerX + ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING <= this.getWidth()) {
                    oSB.setShader(Renderer.shaderAlpha);
                    AdvantagesManager.advantagesImages.get(AdvantagesManager.advantages.get(this.advantages.get(i)).ImageID[0]).getTexture().bind(1);
                    Gdx.gl.glActiveTexture(33984);
                    ImageManager.getImage(Images.techMaskSquare).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                    oSB.flush();
                    oSB.setShader(Renderer.shaderDefault);
                    ImageManager.getImage(Images.techOver).draw(oSB, this.getPosX() + innerX - (ImageManager.getImage(Images.techOver).getWidth() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateX, this.getPosY() + this.titleH() + CFG.PADDING - (ImageManager.getImage(Images.techOver).getHeight() - ImageManager.getImage(Images.techMask).getHeight()) / 2 + iTranslateY);
                    innerX += ImageManager.getImage(Images.techMask).getHeight() + CFG.PADDING;
                }
            }
        }
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getText(), this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + iTranslateY, Colors.getColorTopStats2(isActive, this.getIsHovered()));
        if (this.inTechTree && this.btnIMG != Images.techResearched) {
            ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).draw(oSB, this.getPosX() + this.getWidth() - ButtonTechnology.techIconWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + iTranslateY, ButtonTechnology.techIconWidth, ButtonTechnology.techIconHeight);
            Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sCost, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iCostWidth - ButtonTechnology.techIconWidth + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + iTranslateY, Colors.HOVER_RIGHT2);
        }
        if (this.sQueue != null) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getWidth() - this.iQueueWidth * 3 / 4 - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - this.iQueueHeight * 3 / 4 - CFG.PADDING + iTranslateY, this.iQueueWidth + CFG.PADDING * 4, this.iQueueHeight + CFG.PADDING * 2, 1.0f);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.65f));
            Images.gradientFull.draw(oSB, this.getPosX() + this.getWidth() - this.iQueueWidth * 3 / 4 - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - this.iQueueHeight * 3 / 4 - CFG.PADDING + iTranslateY, this.iQueueWidth + CFG.PADDING * 4, this.iQueueHeight + CFG.PADDING * 2);
            Images.gradientXY.draw(oSB, this.getPosX() + this.getWidth() - this.iQueueWidth * 3 / 4 - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - this.iQueueHeight * 3 / 4 - CFG.PADDING + iTranslateY, this.iQueueWidth + CFG.PADDING * 4, this.iQueueHeight + CFG.PADDING * 2);
            oSB.setColor(Color.WHITE);
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sQueue, this.getPosX() + this.getWidth() - this.iQueueWidth * 3 / 4 + iTranslateX, this.getPosY() + this.getHeight() - this.iQueueHeight * 3 / 4 + iTranslateY, Colors.getColorTopStats2(isActive, this.getIsHovered()));
        }
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Title(Game_Calendar.IMG_TECHNOLOGY, "", TechnologyTree.lTechnology.get(this.iTechnologyID).Name, Colors.HOVER_GOLD, Game.lang.get("Unlocks"), "", Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        boolean addRequiredTech = false;
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).RequiredTech >= 0 && !Game.getCiv(this.iCivID).getTechResearched(TechnologyTree.lTechnology.get(this.iTechnologyID).RequiredTech)) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(TechnologyTree.lTechnology.get(TechnologyTree.lTechnology.get(this.iTechnologyID).RequiredTech).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            addRequiredTech = true;
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).RequiredTech2 >= 0 && !Game.getCiv(this.iCivID).getTechResearched(TechnologyTree.lTechnology.get(this.iTechnologyID).RequiredTech2)) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(TechnologyTree.lTechnology.get(TechnologyTree.lTechnology.get(this.iTechnologyID).RequiredTech2).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            addRequiredTech = true;
        }
        if (addRequiredTech) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnlocksNukes) {
            nData.add(new MenuElement_HoverElement_Type_Button(Game.lang.get("NuclearWeapons"), Images.nuke, CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnlocksAccessToTheSea) {
            nData.add(new MenuElement_HoverElement_Type_Button(Game.lang.get("AllowsYourArmyGoToTheSea"), Images.ship, CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnlocksColonization) {
            nData.add(new MenuElement_HoverElement_Type_Button(Game.lang.get("Colonization"), Images.population, CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        for (int i = 0; i < LawsManager.iLawsSize; ++i) {
            for (int j = 0; j < LawsManager.laws.get(i).RequiredTechID.length; ++j) {
                if (LawsManager.laws.get(i).RequiredTechID[j] == this.iTechnologyID) {
                    nData.add(new MenuElement_HoverElement_Type_Button(Game.lang.get(LawsManager.laws.get(i).Law[j]), Images.law, CFG.FONT_REGULAR_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
            }
        }
        if (TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).size() > 0) {
            for (int i = 0; i < TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).size(); ++i) {
                nData.add(new MenuElement_HoverElement_Type_Button(BuildingsManager.buildings.get(TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).get(i).building).Name[TechnologyTree.lTechUnlocksBuildings.get(this.iTechnologyID).get(i).buildingID], Images.build, CFG.FONT_REGULAR_SMALL));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        for (int i = 0; i < ArmyManager.iUnitsTypesSize; ++i) {
            for (int j = 0; j < ArmyManager.lArmySize.get(i); ++j) {
                if (ArmyManager.lArmy.get(i).get(j).RequiredTechID == this.iTechnologyID) {
                    nData.add(new MenuElement_HoverElement_Type_Button_Unit(ArmyManager.lArmy.get(i).get(j).Name, Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, "" + ArmyManager.lArmy.get(i).get(j).getAttack(this.iCivID), "" + ArmyManager.lArmy.get(i).get(j).getDefense(this.iCivID)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
            }
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).BattleWidth != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BattleWidth") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).BattleWidth > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).BattleWidth, Images.battleWidth, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsAttack > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsAttack, Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsDefense > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).UnitsDefense, Images.defense, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralAttack > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralAttack, Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralDefense > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).GeneralDefense, Images.defense, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaxMorale != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaxMorale") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).MaxMorale > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).MaxMorale, Images.morale, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).Discipline != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Discipline") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).Discipline > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).Discipline, Images.discipline, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfCapitalCity != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumLevelOfCapitalCity") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfCapitalCity > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfCapitalCity, Images.capital, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademy != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumLevelOfTheMilitaryAcademy") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademy > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademy, Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademyForGenerals != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademyForGenerals > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).MaximumLevelOfTheMilitaryAcademyForGenerals, Images.general, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).Legacy != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LegacyPoints") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).Legacy > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).Legacy, Images.legacy, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (TechnologyTree.lTechnology.get(this.iTechnologyID).Gold != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", ((TechnologyTree.lTechnology.get(this.iTechnologyID).Gold > 0) ? "+" : "") + TechnologyTree.lTechnology.get(this.iTechnologyID).Gold, Images.gold, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        for (int i = 0; i < Game.ideologiesManager.getIdeologiesSize(); ++i) {
            if (Game.ideologiesManager.getIdeology(i).REQUIRED_TECHNOLOGY == this.iTechnologyID) {
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusIdeology(Game.lang.get("Government") + ": ", Game.ideologiesManager.getIdeology(i).Name, i, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        for (int i = 0; i < this.advantages.size(); ++i) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(AdvantagesManager.getAdvantageName(this.advantages.get(i)), "", Images.advantages, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (CFG.debugMode) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("ID: ", "" + this.iTechnologyID, Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.iCivID == Game.player.iCivID && !Game.getCiv(this.iCivID).getTechResearched(this.iTechnologyID)) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ClickToStartResearching"), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void actionElement() {
        if (this.btnIMG == Images.techAvailable) {
            Game.player.playerData.techQueue.lTechQueue.clear();
            Game.getCiv(Game.player.iCivID).setActiveTechResearch(this.iTechnologyID);
            if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                Game.menuManager.rebuildInGame_TechnologyTree(true, false);
                InGame_TechnologyTree.lTime = 0L;
            }
            if (Game.menuManager.getVisibleInGame_TechnologyChoose() && InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE) {
                Game.menuManager.rebuildInGame_TechnologyChoose(true, false);
                InGame_TechnologyChoose.lTime = 0L;
            }
            Game.player.currSituation.updateCurrentSituation();
        }
        else if (this.btnIMG == Images.techBlue) {
            Game.player.playerData.techQueue.lTechQueue.clear();
            if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                Game.menuManager.rebuildInGame_TechnologyTree(true, false);
                InGame_TechnologyTree.lTime = 0L;
            }
            if (Game.menuManager.getVisibleInGame_TechnologyChoose() && InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE) {
                Game.menuManager.rebuildInGame_TechnologyChoose(true, false);
                InGame_TechnologyChoose.lTime = 0L;
            }
        }
        else if (this.btnIMG == Images.techGray) {
            Game.player.playerData.techQueue.buildTechQueue(this.iTechnologyID);
            final int nextTech = Game.player.playerData.techQueue.getTechQueue();
            if (nextTech >= 0) {
                Game.getCiv(Game.player.iCivID).setActiveTechResearch(nextTech);
            }
            if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                Game.menuManager.rebuildInGame_TechnologyTree(true, false);
                InGame_TechnologyTree.lTime = 0L;
            }
            if (Game.menuManager.getVisibleInGame_TechnologyChoose() && InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE) {
                Game.menuManager.rebuildInGame_TechnologyChoose(true, false);
                InGame_TechnologyChoose.lTime = 0L;
            }
            Game.player.currSituation.updateCurrentSituation();
        }
    }
    
    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK_TOP;
    }
    
    @Override
    public int getCurrent() {
        return this.iTechnologyID;
    }
}
