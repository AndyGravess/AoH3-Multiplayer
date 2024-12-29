// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class Button_LoadGame_MainMenu extends Button
{
    public String sText2;
    public int iTextWidth2;
    public int iTextHeight2;
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public static final int EXTRA_Y = 3;
    private static final Color colorLine;
    
    public Button_LoadGame_MainMenu(final String sText, final String sText2, final int iPosX, final int iPosY, final int nWidth) {
        this.init(sText, CFG.FONT_REGULAR, 0, iPosX, iPosY, nWidth, getButtonHeight(), true, true, false, false);
        this.sText2 = sText2;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText2);
        this.iTextWidth2 = (int)glyphLayout.width;
        this.iTextHeight2 = (int)glyphLayout.height;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if ((isActive || this.getIsHovered()) && this.getClickable()) {
            Renderer.drawBox(oSB, this.getButtonBG_Active(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(ButtonGame2.colorGradientHover);
        }
        else {
            Renderer.drawBox(oSB, this.getButtonBG(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(ButtonGame2.colorGradient);
        }
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 + iTranslateY, this.getWidth(), this.getHeight() - 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 3 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BG_RED.r, Colors.COLOR_GRADIENT_OVER_BG_RED.g, Colors.COLOR_GRADIENT_OVER_BG_RED.b, 0.4f));
        }
        else {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.4f));
        }
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 3 + CFG.PADDING + iTranslateY, this.getInnerWidth() - CFG.PADDING * 3, (getButtonHeight() - CFG.PADDING * 3) / 2, 1.0f);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && Button_LoadGame_MainMenu.animationState >= 0) {
            if (Button_LoadGame_MainMenu.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_LoadGame_MainMenu.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (Button_LoadGame_MainMenu.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++Button_LoadGame_MainMenu.animationState;
                    Button_LoadGame_MainMenu.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_LoadGame_MainMenu.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (Button_LoadGame_MainMenu.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    Button_LoadGame_MainMenu.animationState = 0;
                    Button_LoadGame_MainMenu.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        this.drawImage(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    public int getButtonBG() {
        return Images.buttonGame;
    }
    
    public int getButtonBG_Active() {
        return Images.buttonGameH;
    }
    
    protected static final Color getColorLine() {
        return Button_LoadGame_MainMenu.colorLine;
    }
    
    protected void drawImage(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (MainMenu.flag != null) {
            oSB.setShader(Renderer.shaderAlpha);
            try {
                MainMenu.flag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.flagMaskDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX + (ImageManager.getImage(Images.flagOverDefault).getWidth() - ImageManager.getImage(Images.flagMaskDefault).getWidth()) / 2, this.getPosY() + getPaddingIMG() + iTranslateY + (ImageManager.getImage(Images.flagOverDefault).getHeight() - ImageManager.getImage(Images.flagMaskDefault).getHeight()) / 2, ImageManager.getImage(Images.flagMaskDefault).getWidth(), ImageManager.getImage(Images.flagMaskDefault).getHeight());
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.flagOverDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 3 + iTranslateX, this.getPosY() + 3 + getButtonHeight() / 4 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.sText2, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 3 + iTranslateX, this.getPosY() + getButtonHeight() / 2 + getButtonHeight() / 4 - this.iTextHeight2 / 2 - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.flagOverDefault).getHeight() + getPaddingIMG() * 2;
    }
    
    public static int getPaddingIMG() {
        return CFG.PADDING * 2;
    }
    
    public int getInnerPosX() {
        return getPaddingIMG() * 2 + ImageManager.getImage(Images.flagOverDefault).getWidth();
    }
    
    public int getInnerWidth() {
        return this.getWidth() - this.getInnerPosX();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover2(isActive, this.getIsHovered());
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        Button_LoadGame_MainMenu.lTimeAnimation = CFG.currentTimeMillis;
        Button_LoadGame_MainMenu.animationState = 0;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LoadGame"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.save, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.sText, "", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(this.sText2, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    static {
        Button_LoadGame_MainMenu.lTimeAnimation = 0L;
        Button_LoadGame_MainMenu.animationState = 0;
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
    }
}
