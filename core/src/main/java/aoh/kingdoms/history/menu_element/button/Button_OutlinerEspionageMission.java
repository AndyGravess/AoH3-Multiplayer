// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Value;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Data;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.pieChart.PieChart;
import com.badlogic.gdx.graphics.Color;

public class Button_OutlinerEspionageMission extends Button_Outliner
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
    public int lastTurnID;
    public float fPerc;
    public int espionageStartedTurnID;
    public int espionageEndTurnID;
    public boolean inRightMenu;
    
    public Button_OutlinerEspionageMission(final String sText, final String sText2, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int imageID, final int iCivID, final int espionageStartedTurnID, final int espionageEndTurnID, final boolean inRightMenu) {
        super(sText, nPosX, nPosY, nWidth, nHeight, iCivID);
        this.iTextWidth2 = 0;
        this.progressBar = new Color(0.29411766f, 0.49019608f, 0.64705884f, 1.0f);
        this.colorText = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.maxTextWidth2 = 0;
        this.pieWidth = 0;
        this.lastTurnID = 0;
        this.fPerc = 0.0f;
        this.inRightMenu = inRightMenu;
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.espionageStartedTurnID = espionageStartedTurnID;
        this.espionageEndTurnID = espionageEndTurnID;
        this.imageID = imageID;
        this.fPerc = (Game_Calendar.TURN_ID - espionageStartedTurnID) / (float)(espionageEndTurnID - espionageStartedTurnID) * 100.0f;
        this.sText2 = "" + (int)Math.min(100.0f, this.fPerc) + "%";
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), "99%");
        this.maxTextWidth2 = (int)Renderer.glyphLayout.width;
        this.progressBar = new Color(DiplomacyManager.COLOR_SPY.r, DiplomacyManager.COLOR_SPY.g, DiplomacyManager.COLOR_SPY.b, 0.35f);
        final PieChart_Data nPieChartData = new PieChart_Data();
        nPieChartData.addPieChartValues(new PieChart_Value(0, 100.0f));
        this.pieWidth = nHeight - CFG.PADDING * 2;
        this.pieChart = new PieChart(nWidth - CFG.PADDING * 2 - this.pieWidth, CFG.PADDING, this.pieWidth, this.pieWidth, nPieChartData, null);
        try {
            this.lastTurnID = Game_Calendar.TURN_ID;
            this.colorText = DiplomacyManager.COLOR_NEUTRAL;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        try {
            if (Game_Calendar.TURN_ID != this.lastTurnID) {
                this.fPerc = (Game_Calendar.TURN_ID - this.espionageStartedTurnID) / (float)(this.espionageEndTurnID - this.espionageStartedTurnID) * 100.0f;
                if (this.inRightMenu && this.fPerc >= 100.0f) {
                    Game.addSimpleTask(new Game.SimpleTask("rebuildRight", this.iCurrent) {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_Right();
                            InGame_Info.iCivID = Game.player.iCivID;
                            InGame_Info.iCivID2 = this.id;
                            Game.menuManager.rebuildInGame_Info(Game.lang.get("EspionageMission") + ": " + Game.lang.get("Completed"), Game.getCiv(this.id).getCivName());
                            InGame_Info.imgID = Images.infoDiplomacy;
                        }
                    });
                    Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_CourtSavePos") {
                        @Override
                        public void update() {
                            if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions2.idCourt) {
                                Game.menuManager.rebuildInGame_CourtSavePos();
                                Game.menuManager.setVisibleInGame_Court(true);
                                InGame_Court.lTime = 0L;
                            }
                        }
                    });
                }
                this.sText2 = "" + (int)Math.min(100.0f, this.fPerc) + "%";
                Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sText2);
                this.iTextWidth2 = (int)Renderer.glyphLayout.width;
                this.lastTurnID = Game_Calendar.TURN_ID;
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
        this.pieChart.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, isActive, false, Math.max(0.0f, 100.0f - Math.max(1.0f, this.fPerc)), this.progressBar);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + ImageManager.getImage(this.imageID).getWidth() + ImageManager.getImage(Images.flagRect2).getWidth() + CFG.PADDING * 6 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.pieWidth - this.iTextWidth2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, DiplomacyManager.COLOR_SPY);
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCurrent, Game.lang.get("EspionageMission")));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EspionageProgress") + ": ", this.sText2, Images.time, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
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
    
    @Override
    public int getCurrent() {
        return this.iCurrent;
    }
    
    static {
        Button_OutlinerEspionageMission.progressBarBG = new Color(0.09803922f, 0.09803922f, 0.15686275f, 1.0f);
    }
}
