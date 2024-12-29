// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menus.NewGame.NewGame;
import aoh.kingdoms.history.map.map.MapScale;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menus.LoadSave.Menu_LoadScenario;
import aoh.kingdoms.history.menus.MainMenu;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.map.MapScenarios;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ScenarioPreview;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_PreviewSmall extends Button
{
    public String sText2;
    public int iTextBonusW;
    public int maxIconWidth;
    public int scenarioID;
    public static int previewH;
    public static int previewW;
    
    public Button_PreviewSmall(final String sText, final String sText2, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int scenarioID) {
        Button_PreviewSmall.previewH = CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 3;
        Button_PreviewSmall.previewW = (int)(Game.mapBG.scenarioOver.getWidth() * (Button_PreviewSmall.previewH / (float)Game.mapBG.scenarioOver.getHeight()));
        this.maxIconWidth = Button_PreviewSmall.previewW + CFG.PADDING * 2;
        this.init(sText, CFG.FONT_BOLD, 0, iPosX, iPosY, nWidth, Math.max(Button_PreviewSmall.previewH + CFG.PADDING * 5, nHeight), true, true, false, false);
        this.scenarioID = scenarioID;
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sText2);
        this.iTextBonusW = (int)Renderer.glyphLayout.width;
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - this.maxIconWidth - CFG.PADDING * 2 && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth, 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.maxIconWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.55f)) : 0.4f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setShader(Renderer.shaderAlpha);
        try {
            Game.mapScenarios.lScenarios_Preview.get(this.scenarioID).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            Game.mapBG.scenarioMask.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - Button_PreviewSmall.previewH / 2 + iTranslateY, Button_PreviewSmall.previewW, Button_PreviewSmall.previewH, false, false);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        Game.mapBG.scenarioOver.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - Button_PreviewSmall.previewH / 2 + iTranslateY, Button_PreviewSmall.previewW, Button_PreviewSmall.previewH);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.maxIconWidth + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING - this.iTextHeight + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText2, this.getPosX() + this.maxIconWidth + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + iTranslateY, this.getColorBonus());
    }
    
    public Color getColorBonus() {
        return Colors.HOVER_RIGHT3;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (this.scenarioID == Game.scenarioID) {
            return Colors.HOVER_GOLD;
        }
        return super.getColor(isActive);
    }
    
    public final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_ScenarioPreview(this.scenarioID));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.mapScenarios.details.get(this.scenarioID).Day + " " + Game_Calendar.getMonthName(Game.mapScenarios.details.get(this.scenarioID).Month) + " " + Game.gameAges.getYear(Game.mapScenarios.details.get(this.scenarioID).Year), Images.time, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.gameAges.lAges.get(Game.gameAges.getAgeOfYear(Game.mapScenarios.details.get(this.scenarioID).Year)).Name, Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Civilizations") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.mapScenarios.details.get(this.scenarioID).Civs), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Author") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.mapScenarios.details.get(this.scenarioID).Author, CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public void actionElement() {
        if (Game.scenarioID != this.scenarioID || Game_Calendar.TURN_ID != 1 || Game.reloadScenario || MainMenu.canContinue) {
            Game.scenarioID = this.scenarioID;
            Game.reloadScenario = false;
            Menu_LoadScenario.editorMode = false;
            Menu_LoadScenario.goToMenu = View.CLOUDS_MENU;
            Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SCENARIO);
        }
        else {
            Game.mapScale.definedScale = MapScale.defScales.definedScale_Default;
            Game.mapScale.setCurrentScale(1.0f);
            NewGame.setRandomCiv();
            Game.menuManager.setViewIDWithoutAnimation(View.CLOUDS_MENU);
        }
    }
    
    @Override
    public void setCurrent(final int nCurrent) {
        this.scenarioID = nCurrent;
    }
    
    @Override
    public int getCurrent() {
        return this.scenarioID;
    }
}
