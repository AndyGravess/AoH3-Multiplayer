// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonStatsRectIMG_CurrentSituation extends Button
{
    public int iconWidth;
    public int iconHeight;
    public int maxIconWidth;
    protected static long lTimeAnimation;
    protected static int animationState;
    
    public ButtonStatsRectIMG_CurrentSituation(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.maxIconWidth = maxIconWidth;
        final float iconScale = this.getImageScale(Images.currentSituation) * 1.3f;
        this.iconWidth = (int)(ImageManager.getImage(Images.currentSituation).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(Images.currentSituation).getHeight() * iconScale);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, int iTranslateX, int iTranslateY, final boolean isActive) {
        iTranslateX += this.getPosX();
        iTranslateY += this.getPosY();
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.5f : 0.2f));
        Renderer.drawBox(oSB, Images.statsRectBG, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.65f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - CFG.PADDING * 2, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.85f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
        if (this.getClickable() && this.getIsHovered() && ButtonStatsRectIMG_CurrentSituation.animationState >= 0) {
            if (ButtonStatsRectIMG_CurrentSituation.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_CurrentSituation.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + 1, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + this.getHeight() - 2, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_CurrentSituation.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonStatsRectIMG_CurrentSituation.animationState;
                    ButtonStatsRectIMG_CurrentSituation.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_CurrentSituation.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + 1, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + this.getHeight() - 2, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_CurrentSituation.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonStatsRectIMG_CurrentSituation.animationState = 0;
                    ButtonStatsRectIMG_CurrentSituation.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(Images.currentSituation).draw(oSB, this.getPosX() + CFG.PADDING + this.maxIconWidth / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.iconHeight) / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 2 + this.maxIconWidth + (this.getWidth() - (CFG.PADDING * 2 + this.maxIconWidth)) / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    @Override
    public String getTextToDraw() {
        if (this.iCurrent != Game.player.currSituation.currentSituationNum) {
            this.setText("" + Game.player.currSituation.currentSituationNum);
            this.iCurrent = Game.player.currSituation.currentSituationNum;
        }
        return super.getTextToDraw();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CurrentSituation"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.currentSituation, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Messages") + ": ", "" + Game.player.currSituation.currentSituationNum, Images.currentSituation, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.player.currSituation.currentSituationNum > 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.player.currSituation.noActiveResearch) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoActiveResearch"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.maxAmountOfGold) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAmountOfGold") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fGold, 1) + " / " + CFG.getPrecision2((float)Game.getMaxAmountOfGold(Game.player.iCivID), 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.missionCanBeUnlocked) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Missions") + ": " + Game.lang.get("Available"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.missions, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.chooseRivals) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ChooseYourRivals"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rivals, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.availableAdvantage) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvantagePoints") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).getAdvantagePoints(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.advantages, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.newLawAvailable) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Law") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("Unlocked"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.law, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.highInflation) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("HighInflation") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getInflation() * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.inflation, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.nonCoreProvinces) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.core, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.differentReligionProvinces) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.availableCivilizationLegacy) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AvailableCivilizationLegacy"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.noAdvisor > 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.promoteAdvisor > 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PromoteAdvisor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.lackOfGeneral) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LackOfGeneral"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.upgradeCapitalCity) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeCapitalCity"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.upgradeSupremeCourt) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeSupremeCourt"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.stability, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.militaryAcademyCanBeUpgraded) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeMilitaryAcademy"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.militaryAcademyForGeneralsCanBeUpgraded) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeMilitaryAcademyForGenerals"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.upgradeNuclearReactor) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeNuclearReactor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (Game.player.currSituation.wonderCanBeBuilt) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AWonderCanBeBuilt"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.mapModesWonders, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        if (CFG.isDesktop()) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
            nData.add(new MenuElement_HoverElement_Type_Text("T", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.currentSituation, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        ButtonStatsRectIMG_CurrentSituation.lTimeAnimation = CFG.currentTimeMillis;
        ButtonStatsRectIMG_CurrentSituation.animationState = 0;
    }
    
    static {
        ButtonStatsRectIMG_CurrentSituation.lTimeAnimation = 0L;
        ButtonStatsRectIMG_CurrentSituation.animationState = 0;
    }
}
