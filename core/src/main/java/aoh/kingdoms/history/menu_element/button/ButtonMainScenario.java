// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.map.MapScenarios;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ScenarioPreview;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonMainScenario extends ButtonMain
{
    private int iScenarioID;
    private String sDate;
    private String sDesc;
    
    public ButtonMainScenario(final int iScenarioID, final String sText, final String sDate, final String sDesc, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
        this.iScenarioID = iScenarioID;
        this.sDate = sDate;
        this.sDesc = sDesc;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.fontMain.get(this.fontID).getData().setScale(0.9f);
        Renderer.drawText(oSB, this.fontID, this.getText(), this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.9f / 2.0f) + iTranslateY, this.getColor(isActive));
        Renderer.fontMain.get(this.fontID).getData().setScale(0.7f);
        Renderer.drawText(oSB, this.fontID, this.sDate, this.getPosX() + this.getTextPos() + (int)(this.getTextWidth() * 0.9f) + CFG.PADDING * 4 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT + CFG.PADDING + CFG.TEXT_HEIGHT * 0.7f) / 2 + (int)(CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, new Color(0.67f, 0.67f, 0.67f, 1.0f));
        Renderer.drawText(oSB, this.fontID, this.sDesc, this.getPosX() + this.getTextPos() + (int)(this.getTextWidth() * 0.9f) + CFG.PADDING * 4 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT + CFG.PADDING + CFG.TEXT_HEIGHT * 0.7f) / 2 + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT + iTranslateY, new Color(0.48f, 0.48f, 0.48f, 1.0f));
        Renderer.fontMain.get(this.fontID).getData().setScale(1.0f);
        oSB.setColor(new Color(0.15686275f, 0.15686275f, 0.27450982f, 0.275f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getTextPos() + (int)(this.getTextWidth() * 0.9f) + CFG.PADDING * 2, this.getHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getTextPos() + (int)(this.getTextWidth() * 0.9f) + CFG.PADDING * 2 - 1 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public int getCurrent() {
        return this.iScenarioID;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE2;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED2;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT2 : Colors.BUTTON_TEXT_DISABLED;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_ScenarioPreview(this.getCurrent()));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.mapScenarios.details.get(this.getCurrent()).Day + " " + Game_Calendar.getMonthName(Game.mapScenarios.details.get(this.getCurrent()).Month) + " " + Game.mapScenarios.details.get(this.getCurrent()).Year, CFG.FONT_REGULAR_SMALL, Colors.HOVER_TITLE_2));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.gameAges.lAges.get(Game.mapScenarios.details.get(this.getCurrent()).Age).Name, CFG.FONT_REGULAR_SMALL));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Civilizations") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.mapScenarios.details.get(this.getCurrent()).Civs, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Author") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.mapScenarios.details.get(this.getCurrent()).Author, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Tag") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.mapScenarios.lScenarios_TagsList.get(this.getCurrent()), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
