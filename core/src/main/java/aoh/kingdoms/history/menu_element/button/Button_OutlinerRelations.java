// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Value;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Data;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.pieChart.PieChart;
import com.badlogic.gdx.graphics.Color;

public class Button_OutlinerRelations extends Button_Outliner
{
    public String sText2;
    public int iTextWidth2;
    public int imageID;
    public static Color progressBarBG;
    public Color progressBar;
    public Color colorText;
    public int maxTextWidth2;
    public int pieWidth;
    public PieChart pieChart;
    public float lastValue;
    public int lastTurnID;
    public float fPerc;
    public boolean improvingMode;
    
    public Button_OutlinerRelations(final String sText, final String sText2, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int imageID, final int iCivID, final boolean improvingMode) {
        super(sText, nPosX, nPosY, nWidth, nHeight, iCivID);
        this.iTextWidth2 = 0;
        this.progressBar = new Color(0.29411766f, 0.49019608f, 0.64705884f, 1.0f);
        this.colorText = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.maxTextWidth2 = 0;
        this.pieWidth = 0;
        this.lastValue = -991258.25f;
        this.lastTurnID = 0;
        this.fPerc = 99.9f;
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.imageID = imageID;
        this.improvingMode = improvingMode;
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), "-99");
        this.maxTextWidth2 = (int)Renderer.glyphLayout.width;
        if (improvingMode) {
            this.progressBar = new Color(DiplomacyManager.COLOR_GREEN.r, DiplomacyManager.COLOR_GREEN.g, DiplomacyManager.COLOR_GREEN.b, 0.35f);
        }
        else {
            this.progressBar = new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.35f);
        }
        final PieChart_Data nPieChartData = new PieChart_Data();
        nPieChartData.addPieChartValues(new PieChart_Value(0, 100.0f));
        this.pieWidth = nHeight - CFG.PADDING * 2;
        this.pieChart = new PieChart(nWidth - CFG.PADDING * 2 - this.pieWidth, CFG.PADDING, this.pieWidth, this.pieWidth, nPieChartData, null);
        try {
            this.lastTurnID = -1;
            this.lastValue = Game.getCiv(this.iCurrent).diplomacy.getRelation(Game.player.iCivID);
            this.colorText = DiplomacyManager.getOpinion_Color((int)this.lastValue);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        try {
            if (Game_Calendar.TURN_ID != this.lastTurnID) {
                final float preUpdatePerc = this.fPerc;
                this.fPerc = (this.improvingMode ? Game.getCiv(Game.player.iCivID).diplomacy.getImprovingRelations_Perc(this.iCurrent) : Game.getCiv(Game.player.iCivID).diplomacy.getDamagingRelations_Perc(this.iCurrent));
                if (this.fPerc < 0.0f) {
                    Game.addSimpleTask(new Game.SimpleTask("rebuildRight") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_Right();
                        }
                    });
                }
                if (this.fPerc > preUpdatePerc) {
                    ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(this.iCurrent).getCapitalProvinceID(), this.improvingMode ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE);
                }
                this.lastTurnID = Game_Calendar.TURN_ID;
            }
            if (this.lastValue != Game.getCiv(this.iCurrent).diplomacy.getRelation(Game.player.iCivID)) {
                this.lastValue = Game.getCiv(this.iCurrent).diplomacy.getRelation(Game.player.iCivID);
                this.setText(DiplomacyManager.getOpinion_String((int)this.lastValue));
                this.sText2 = "" + (int)this.lastValue;
                Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sText2);
                this.iTextWidth2 = (int)Renderer.glyphLayout.width;
                this.colorText = DiplomacyManager.getOpinion_Color((int)this.lastValue);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY);
        oSB.setShader(Renderer.shaderAlpha);
        if (this.iCurrent >= 0) {
            Game.getCiv(this.iCurrent).getFlag().getTexture().bind(1);
        }
        else {
            ImageManager.getImage(Images.randomCivilizationFlag).getTexture().bind(1);
        }
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.flagRect2Mask).draw(oSB, this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(this.imageID).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagRect2).draw(oSB, this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(this.imageID).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        this.pieChart.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, isActive, false, this.fPerc, this.progressBar);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + ImageManager.getImage(this.imageID).getWidth() + ImageManager.getImage(Images.flagRect2).getWidth() + CFG.PADDING * 6 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.pieWidth - this.iTextWidth2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(this.iCurrent, Game.player.iCivID, this.improvingMode, !this.improvingMode);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (this.getIsHovered()) {
            return super.getColor(isActive);
        }
        return this.colorText;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.iCurrent;
        }
    }
    
    static {
        Button_OutlinerRelations.progressBarBG = new Color(0.09803922f, 0.09803922f, 0.15686275f, 1.0f);
    }
}
