// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Space;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonTopDate extends Button
{
    public static int iMaxWidth;
    protected static final Color COLOR_SPEED;
    public static long ANIMATION_TIME;
    public static float ANIMATION_DURATION;
    
    public ButtonTopDate(final String sText, final int iPosY, final int nHeight) {
        this.fontID = CFG.FONT_BOLD;
        this.setText(sText);
        updateMaxWidth();
        this.init(sText, this.fontID, this.iTextPositionX, 0, iPosY, this.getTextWidth() + getPaddingX() * 2 + ImageManager.getImage(Images.pause).getWidth() + CFG.PADDING * 2, nHeight, true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        final int tSpeedWidth = (int)(this.getWidth() * 0.9f - CFG.PADDING * (Game.gameThread.playMaxSpeed - 1)) / Game.gameThread.playMaxSpeed;
        final int tX = (int)(this.getWidth() * 0.9f - tSpeedWidth * Game.gameThread.playMaxSpeed - CFG.PADDING * (Game.gameThread.playMaxSpeed - 1)) / 2;
        int tY = this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY;
        int tH = CFG.PADDING;
        if (ButtonTopDate.ANIMATION_TIME + ButtonTopDate.ANIMATION_DURATION >= CFG.currentTimeMillis) {
            final int extra = Math.max(0, ImageManager.getImage(Images.topStats).getHeight() / 3 - (int)(ImageManager.getImage(Images.topStats).getHeight() / 3 * ((CFG.currentTimeMillis - ButtonTopDate.ANIMATION_TIME) / ButtonTopDate.ANIMATION_DURATION)));
            tY -= extra;
            tH += extra;
            if (CFG.currentTimeMillis - ButtonTopDate.ANIMATION_TIME > ButtonTopDate.ANIMATION_DURATION) {
                ButtonTopDate.ANIMATION_TIME = 0L;
            }
        }
        for (int i = 0; i < Game.gameThread.playSpeed; ++i) {
            draw_Speed(oSB, tX + this.getPosX() + (int)(this.getWidth() * 0.05f) + (tSpeedWidth + CFG.PADDING) * i + iTranslateX, tY, tSpeedWidth, tH);
        }
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.5f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(this.getColor(isActive));
        ImageManager.getImage(Game.gameThread.play ? Images.pause : Images.play).draw(oSB, this.getPosX() + this.getWidth() - getPaddingX() - ImageManager.getImage(Images.pause).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pause).getHeight() / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
        this.setText(Game_Calendar.getCurrentDate());
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getText(), this.getPosX() + this.getWidth() - getPaddingX() - ImageManager.getImage(Images.pause).getWidth() - CFG.PADDING * 2 - this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    protected static final void draw_Speed(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        oSB.setColor(new Color(ButtonTopDate.COLOR_SPEED.r, ButtonTopDate.COLOR_SPEED.g, ButtonTopDate.COLOR_SPEED.b, 0.85f));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(ButtonTopDate.COLOR_SPEED);
        Images.gradientXY.draw(oSB, nPosX, nPosY + nHeight - nHeight / 2, nWidth, nHeight / 2, false, true);
        Images.gradientXY.draw(oSB, nPosX, nPosY, nWidth, nHeight / 2, false, false);
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, nPosX, nPosY, CFG.PADDING, nHeight);
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, nPosX + nWidth - CFG.PADDING, nPosY, CFG.PADDING, nHeight, true, false);
        oSB.setColor(Color.WHITE);
    }
    
    public static int getPaddingX() {
        return CFG.PADDING * 4;
    }
    
    public static int getPaddingY() {
        return CFG.PADDING;
    }
    
    @Override
    public int getWidth() {
        return ButtonTopDate.iMaxWidth + getPaddingX() * 2 + ImageManager.getImage(Images.pause).getWidth() + CFG.PADDING * 2;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }
    
    public static Color getColorDate(final boolean isActive, final boolean isHovered, final float fAlpha) {
        if (isActive) {
            return new Color(Colors.COLOR_TOP_STATS_ACTIVE.r, Colors.COLOR_TOP_STATS_ACTIVE.g, Colors.COLOR_TOP_STATS_ACTIVE.b, fAlpha);
        }
        if (isHovered) {
            return new Color(Colors.COLOR_TOP_STATS_HOVER.r, Colors.COLOR_TOP_STATS_HOVER.g, Colors.COLOR_TOP_STATS_HOVER.b, fAlpha);
        }
        return new Color(Colors.COLOR_TOP_STATS.r, Colors.COLOR_TOP_STATS.g, Colors.COLOR_TOP_STATS.b, fAlpha);
    }
    
    public static final void updateMaxWidth() {
        for (int i = 0; i < 12; ++i) {
            updateMaxWidth(((Game.HOURS_PER_TURN < 24) ? "24:00, " : "") + "31 " + Game_Calendar.getMonthName(i) + " 2345");
        }
    }
    
    public static final void updateMaxWidth(final String sText) {
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), sText);
        ButtonTopDate.iMaxWidth = 0;
        ButtonTopDate.iMaxWidth = Math.max(ButtonTopDate.iMaxWidth, (int)Renderer.glyphLayout.width);
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (Game.gameThread.play) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToPauseTheGame"), Colors.HOVER_GOLD));
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToUnpauseTheGame"), Colors.HOVER_GOLD));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Date") + ": ", "" + Game_Calendar.getCurrentDate(), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game_Calendar.TURN_ID != 1) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PlayingTime") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).Name, Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.gameAges.getAge_TurnDays(Game_Calendar.CURRENT_AGEID) > 1) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TurnsX", 1) + " = ", Game.lang.get("XDays", Game.gameAges.getAge_TurnDays(Game_Calendar.CURRENT_AGEID)), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Regiment") + ", " + Game.lang.get("Manpower") + ": ", "" + CFG.getNumberWithSpaces("" + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (CFG.isDesktop()) {
            nData.add(new MenuElement_HoverElement_Type_Space());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("SPACE", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    static {
        ButtonTopDate.iMaxWidth = 0;
        COLOR_SPEED = new Color(0.27450982f, 0.039215688f, 0.11764706f, 1.0f);
        ButtonTopDate.ANIMATION_TIME = 0L;
        ButtonTopDate.ANIMATION_DURATION = 150.0f;
    }
}
