// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu;

import aoh.kingdoms.history.map.map.MapModeManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menusInGame.Right.InGame_Right;
import com.badlogic.gdx.graphics.Color;

public class Colors
{
    public static Color COLOR_TITLE;
    public static Color COLOR_GRADIENT_TITLE_BLUE;
    public static Color COLOR_BOX_HOVER;
    public static Color COLOR_BOX_ACTIVE;
    public static Color COLOR_STATS_RECT_BG;
    public static Color COLOR_STATS_RECT_BG_RED;
    public static Color COLOR_STATS_RECT_BG_RED_OVER;
    public static Color COLOR_GRADIENT_OVER_BG_RED;
    public static Color COLOR_GRADIENT_OVER_BG_RED_OVER;
    public static Color COLOR_GRAY;
    public static Color COLOR_GRAY2;
    public static Color COLOR_GRADIENT_OVER_BLUE;
    public static Color COLOR_GRADIENT_OVER_BLUE2;
    public static Color COLOR_GRADIENT_OVER_BLUE3;
    public static Color COLOR_GRADIENT_BG_BLUE;
    public static Color COLOR_GRADIENT_OVER_GREEN;
    public static Color COLOR_GRADIENT_BG_GREEN;
    public static Color COLOR_GRADIENT_OVER_INFO;
    public static Color COLOR_GRADIENT_BG_INFO;
    public static Color COLOR_GRADIENT_OVER;
    public static Color COLOR_GRADIENT_BG;
    public static Color COLOR_UNKNOWN;
    public static Color COLOR_BOX_FRAME;
    public static Color COLOR_BOX_FRAME2;
    public static Color COLOR_BOX_GOLD;
    public static Color COLOR_LOGO;
    public static Color COLOR_TEXT_TITLE;
    public static Color COLOR_TEXT_DESC;
    public static Color COLOR_TEXT_DESC_RIGHT;
    public static Color COLOR_TEXT_GOLD;
    public static Color COLOR_TEXT_RESEARCH;
    public static Color COLOR_TEXT_POSITIVE;
    public static Color COLOR_TEXT_NEGATIVE;
    public static Color BUTTON_BUILDING_COLORS_STATS;
    public static Color COLOR_NOTIFICATION_BG;
    public static Color COLOR_NOTIFICATION_OVER;
    public static Color COLOR_NOTIFICATION_BG_GREEN;
    public static Color COLOR_NOTIFICATION_OVER_GREEN;
    public static Color COLOR_NOTIFICATION_BG_RED;
    public static Color COLOR_NOTIFICATION_OVER_RED;
    public static Color HOVER_TITLE;
    public static Color HOVER_TITLE_2;
    public static Color HOVER_IMPORTANT;
    public static Color HOVER_LEFT;
    public static Color HOVER_LEFT2;
    public static Color HOVER_RIGHT;
    public static Color HOVER_RIGHT2;
    public static Color HOVER_RIGHT3;
    public static Color HOVER_GOLD;
    public static Color HOVER_YELLOW;
    public static Color HOVER_POSITIVE;
    public static Color HOVER_NEGATIVE;
    public static Color HOVER_NEUTRAL;
    public static Color HOVER_ATTACK;
    public static Color HOVER_DEFENSE;
    public static Color HOVER_LINE1;
    public static Color HOVER_LINE2;
    public static Color COLOR_TOP_STATS2;
    public static Color COLOR_TOP_STATS3;
    public static Color COLOR_TOP_STATS4;
    public static Color COLOR_TOP_STATS5;
    public static Color COLOR_TOP_STATS;
    public static Color COLOR_TOP_STATS_HOVER;
    public static Color COLOR_TOP_STATS_ACTIVE;
    public static Color COLOR_TOP_STATS_RIGHT;
    public static Color COLOR_TOP_STATS_RIGHT_HOVER;
    public static Color COLOR_TOP_STATS_RIGHT_ACTIVE;
    public static Color COLOR_TEXT_MODIFIER_POSITIVE;
    public static Color COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
    public static Color COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
    public static Color COLOR_TEXT_MODIFIER_NEUTRAL;
    public static Color COLOR_TEXT_MODIFIER_NEUTRAL_HOVER;
    public static Color COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE;
    public static Color COLOR_TEXT_MODIFIER_NEGATIVE;
    public static Color COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
    public static Color COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
    public static Color COLOR_INFO_BOX;
    public static Color COLOR_INFO_BOX2;
    public static Color COLOR_INFO_BOX2_BOT;
    public static Color COLOR_INGAME_GOLD;
    public static Color COLOR_INGAME_GOLD_HOVER;
    public static Color COLOR_INGAME_GOLD_ACTIVE;
    public static Color COLOR_POPULATION;
    public static Color COLOR_POPULATION_HOVER;
    public static Color COLOR_POPULATION_ACTIVE;
    public static Color COLOR_TEXT_ECONOMY;
    public static Color COLOR_TEXT_ECONOMY_HOVER;
    public static Color COLOR_TEXT_ECONOMY_ACTIVE;
    public static Color COLOR_GRADIENT;
    public static Color COLOR_GRADIENT_HOVER;
    public static Color TEXT_TOP_BOT;
    public static Color TEXT_CURRENT_SITUATION;
    public static Color TEXT_CURRENT_SITUATION_HOVER;
    public static Color TEXT_CURRENT_SITUATION_ACTIVE;
    public static Color BUTTON_TEXT;
    public static Color BUTTON_TEXT_BRIGHT;
    public static Color BUTTON_TEXT_BRIGHT2;
    public static Color BUTTON_TEXT_ACTIVE;
    public static Color BUTTON_TEXT_HOVERED;
    public static Color BUTTON_TEXT_DISABLED;
    public static Color BUTTON_TEXT_DESC_SIMPLE;
    public static Color BUTTON_TEXT2;
    public static Color BUTTON_TEXT_ACTIVE2;
    public static Color BUTTON_TEXT_HOVERED2;
    public static Color COLOR_TEXT_PIE_CHART_STATS;
    public static Color COLOR_SLIDER_LEFT_BG;
    public static Color COLOR_SLIDER_RIGHT_BG;
    public static Color COLOR_COLOR_PICKER_RGB_BG;
    public static Color COLOR_LOADING_SPLIT_ACTIVE;
    public static Color COLOR_LOADING_SPLIT;
    public static Color TECH_RESEARCHED;
    public static Color TECH_RESEARCHED2;
    public static Color TECH_RESEARCHED3;
    public static Color TECH_BLUE;
    public static Color TECH_BLUE2;
    public static Color TECH_BLUE3;
    public static Color TECH_GRAY;
    public static Color TECH_GRAY2;
    public static Color TECH_GRAY3;
    public static float PROVINCE_ALPHA_POPULATION;
    public static Color[] COLOR_POPULATION_VIEW;
    public static Color[] COLOR_ECONOMY;
    public static Color[] COLOR_PROVINCE_INCOME;
    public static Color[] COLOR_PROVINCE_INFRASTRUCTURE;
    public static Color[] COLOR_PROVINCE_PROVINCE_VALUE;
    public static Color[] COLOR_PROVINCE_RED;
    public static Color[] COLOR_PROVINCE_MANPOWER;
    public static Color[] COLOR_PROVINCE_DEVASTATION;
    public static Color[] COLOR_PROVINCE_TAX;
    public static Color[] COLOR_PROVINCE_ECONOMY;
    public static Color COLOR_PROVINCE_DEFENSE;
    public static Color[] COLOR_PROVINCE_FORT;
    public static Color[] COLOR_PROVINCE_LOOT;
    
    public static Color getColorButtonHover(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (isHovered) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return Colors.BUTTON_TEXT_BRIGHT;
    }
    
    public static Color getColorButton(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (isHovered) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return Colors.BUTTON_TEXT;
    }
    
    public static Color getColorButtonHover2(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (isHovered) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return Colors.BUTTON_TEXT_BRIGHT2;
    }
    
    public static Color getColorTopStats(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        return Colors.COLOR_TOP_STATS;
    }
    
    public static Color getColorTopStats_Outliner(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        if (InGame_Right.outlinerInView) {
            return Colors.COLOR_TOP_STATS;
        }
        return new Color(Colors.COLOR_TOP_STATS.r, Colors.COLOR_TOP_STATS.g, Colors.COLOR_TOP_STATS.b, 0.25f);
    }
    
    public static Color getColorTopStatsRight(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_RIGHT_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS_RIGHT_HOVER;
        }
        return Colors.COLOR_TOP_STATS_RIGHT;
    }
    
    public static Color getColorTopStatsHover(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS;
        }
        return Colors.COLOR_TOP_STATS_HOVER;
    }
    
    public static Color getColorTopStats2(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        return Colors.COLOR_TOP_STATS2;
    }
    
    public static Color getColorTopStats3(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        return Colors.COLOR_TOP_STATS3;
    }
    
    public static Color getColorTopStats4(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        return Colors.COLOR_TOP_STATS4;
    }
    
    public static Color getColorTopStats5(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        return Colors.BUTTON_TEXT;
    }
    
    public static Color getColorNeutral(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TEXT_MODIFIER_NEUTRAL_HOVER;
        }
        return Colors.COLOR_TEXT_MODIFIER_NEUTRAL;
    }
    
    public static Color getColorPositive(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
        }
        return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
    }
    
    public static Color getColorPopulation(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_POPULATION_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_POPULATION_HOVER;
        }
        return Colors.COLOR_POPULATION;
    }
    
    public static Color getColorEconomy(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TEXT_ECONOMY_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TEXT_ECONOMY_HOVER;
        }
        return Colors.COLOR_TEXT_ECONOMY;
    }
    
    public static Color getColorNegative(final boolean isActive, final boolean isHovered) {
        if (isActive) {
            return Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
        }
        if (isHovered) {
            return Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
        }
        return Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
    }
    
    public static Color getPopulationColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_POPULATION_VIEW[3], Colors.COLOR_POPULATION_VIEW[8], Math.min(nData, 99) % 100, 100, nAlpha);
    }
    
    public static Color getWhiteBlackColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Color.WHITE, Color.BLUE, Math.min(nData, 99) % 100, 100, nAlpha);
    }
    
    public static Color getEconomyColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_ECONOMY[2], Colors.COLOR_ECONOMY[6], Math.min(nData, 99) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceIncomeColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_INCOME[0], Colors.COLOR_PROVINCE_INCOME[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceInfrastructureColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_INFRASTRUCTURE[0], Colors.COLOR_PROVINCE_INFRASTRUCTURE[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceProvinceValueColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_PROVINCE_VALUE[0], Colors.COLOR_PROVINCE_PROVINCE_VALUE[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceRedColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_RED[0], Colors.COLOR_PROVINCE_RED[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceManpowerColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_MANPOWER[0], Colors.COLOR_PROVINCE_MANPOWER[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceTaxColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_TAX[0], Colors.COLOR_PROVINCE_TAX[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceEconomyColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_ECONOMY[0], Colors.COLOR_PROVINCE_ECONOMY[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceFortColor(final int nData, final float nAlpha) {
        return CFG.getColorStep(Colors.COLOR_PROVINCE_FORT[0], Colors.COLOR_PROVINCE_FORT[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceDevastationColor(final int nData, final float nAlpha) {
        if (nData == 0) {
            return new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, nAlpha);
        }
        return CFG.getColorStep(Colors.COLOR_PROVINCE_DEVASTATION[0], Colors.COLOR_PROVINCE_DEVASTATION[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    public static Color getProvinceLootColor(final int nData, final float nAlpha) {
        if (nData == 0) {
            return new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, nAlpha);
        }
        return CFG.getColorStep(Colors.COLOR_PROVINCE_LOOT[0], Colors.COLOR_PROVINCE_LOOT[1], Math.min(99, nData) % 100, 100, nAlpha);
    }
    
    static {
        Colors.COLOR_TITLE = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        Colors.COLOR_GRADIENT_TITLE_BLUE = new Color(0.105882354f, 0.16078432f, 0.2901961f, 0.775f);
        Colors.COLOR_BOX_HOVER = new Color(0.3529412f, 0.27450982f, 0.33333334f, 1.0f);
        Colors.COLOR_BOX_ACTIVE = new Color(0.5686275f, 0.47058824f, 0.5294118f, 1.0f);
        Colors.COLOR_STATS_RECT_BG = new Color(0.078431375f, 0.039215688f, 0.09803922f, 1.0f);
        Colors.COLOR_STATS_RECT_BG_RED = new Color(0.13725491f, 0.078431375f, 0.09803922f, 1.0f);
        Colors.COLOR_STATS_RECT_BG_RED_OVER = new Color(0.09803922f, 0.05882353f, 0.078431375f, 1.0f);
        Colors.COLOR_GRADIENT_OVER_BG_RED = new Color(0.14117648f, 0.07450981f, 0.09411765f, 1.0f);
        Colors.COLOR_GRADIENT_OVER_BG_RED_OVER = new Color(0.21960784f, 0.09411765f, 0.10980392f, 1.0f);
        Colors.COLOR_GRAY = new Color(0.13725491f, 0.14117648f, 0.16470589f, 1.0f);
        Colors.COLOR_GRAY2 = new Color(0.09803922f, 0.11764706f, 0.18039216f, 1.0f);
        Colors.COLOR_GRADIENT_OVER_BLUE = new Color(0.078431375f, 0.13725491f, 0.1764706f, 0.75f);
        Colors.COLOR_GRADIENT_OVER_BLUE2 = new Color(0.039215688f, 0.09803922f, 0.13725491f, 0.75f);
        Colors.COLOR_GRADIENT_OVER_BLUE3 = new Color(0.11764706f, 0.19607843f, 0.29411766f, 0.75f);
        Colors.COLOR_GRADIENT_BG_BLUE = new Color(0.039215688f, 0.05882353f, 0.11764706f, 0.75f);
        Colors.COLOR_GRADIENT_OVER_GREEN = new Color(0.13725491f, 0.21568628f, 0.15686275f, 0.75f);
        Colors.COLOR_GRADIENT_BG_GREEN = new Color(0.078431375f, 0.11764706f, 0.09803922f, 0.75f);
        Colors.COLOR_GRADIENT_OVER_INFO = new Color(0.078431375f, 0.13725491f, 0.1764706f, 0.75f);
        Colors.COLOR_GRADIENT_BG_INFO = new Color(0.039215688f, 0.05882353f, 0.11764706f, 0.75f);
        Colors.COLOR_GRADIENT_OVER = new Color(0.16862746f, 0.08627451f, 0.10980392f, 0.75f);
        Colors.COLOR_GRADIENT_BG = new Color(0.09019608f, 0.047058824f, 0.05882353f, 0.75f);
        Colors.COLOR_UNKNOWN = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        Colors.COLOR_BOX_FRAME = new Color(0.50980395f, 0.42352942f, 0.23529412f, 1.0f);
        Colors.COLOR_BOX_FRAME2 = new Color(0.77254903f, 0.6117647f, 0.2627451f, 1.0f);
        Colors.COLOR_BOX_GOLD = new Color(0.5686275f, 0.49019608f, 0.2901961f, 1.0f);
        Colors.COLOR_LOGO = new Color(0.69803923f, 0.54901963f, 0.23529412f, 1.0f);
        Colors.COLOR_TEXT_TITLE = new Color(0.9607843f, 0.9607843f, 0.88235295f, 1.0f);
        Colors.COLOR_TEXT_DESC = new Color(0.7647059f, 0.7647059f, 0.7254902f, 1.0f);
        Colors.COLOR_TEXT_DESC_RIGHT = new Color(0.64705884f, 0.64705884f, 0.60784316f, 1.0f);
        Colors.COLOR_TEXT_GOLD = new Color(0.7647059f, 0.5686275f, 0.1764706f, 1.0f);
        Colors.COLOR_TEXT_RESEARCH = new Color(0.4f, 0.6f, 0.8f, 1.0f);
        Colors.COLOR_TEXT_POSITIVE = new Color(0.27450982f, 0.50980395f, 0.23529412f, 1.0f);
        Colors.COLOR_TEXT_NEGATIVE = new Color(0.9607843f, 0.29411766f, 0.29411766f, 1.0f);
        Colors.BUTTON_BUILDING_COLORS_STATS = new Color(0.039215688f, 0.05882353f, 0.09803922f, 0.6f);
        Colors.COLOR_NOTIFICATION_BG = new Color(0.039215688f, 0.05882353f, 0.11764706f, 0.75f);
        Colors.COLOR_NOTIFICATION_OVER = new Color(0.13725491f, 0.15686275f, 0.21568628f, 0.75f);
        Colors.COLOR_NOTIFICATION_BG_GREEN = new Color(0.078431375f, 0.11764706f, 0.09803922f, 0.75f);
        Colors.COLOR_NOTIFICATION_OVER_GREEN = new Color(0.13725491f, 0.21568628f, 0.15686275f, 0.75f);
        Colors.COLOR_NOTIFICATION_BG_RED = new Color(0.14117648f, 0.07450981f, 0.09411765f, 1.0f);
        Colors.COLOR_NOTIFICATION_OVER_RED = new Color(0.27450982f, 0.10980392f, 0.10980392f, 0.75f);
        Colors.HOVER_TITLE = new Color(0.9764706f, 0.99215686f, 1.0f, 1.0f);
        Colors.HOVER_TITLE_2 = new Color(0.972549f, 0.92941177f, 0.8980392f, 1.0f);
        Colors.HOVER_IMPORTANT = new Color(0.98039216f, 0.98039216f, 0.84313726f, 1.0f);
        Colors.HOVER_LEFT = new Color(0.92156863f, 0.92156863f, 0.88235295f, 1.0f);
        Colors.HOVER_LEFT2 = new Color(0.7647059f, 0.7647059f, 0.7647059f, 1.0f);
        Colors.HOVER_RIGHT = new Color(0.6862745f, 0.6862745f, 0.6862745f, 1.0f);
        Colors.HOVER_RIGHT2 = new Color(0.5294118f, 0.5294118f, 0.5294118f, 1.0f);
        Colors.HOVER_RIGHT3 = new Color(0.33333334f, 0.33333334f, 0.33333334f, 1.0f);
        Colors.HOVER_GOLD = new Color(0.8039216f, 0.6666667f, 0.27450982f, 1.0f);
        Colors.HOVER_YELLOW = new Color(0.8627451f, 0.9019608f, 0.54901963f, 1.0f);
        Colors.HOVER_POSITIVE = new Color(0.27450982f, 0.50980395f, 0.23529412f, 1.0f);
        Colors.HOVER_NEGATIVE = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        Colors.HOVER_NEUTRAL = new Color(0.8235294f, 0.8235294f, 0.78431374f, 1.0f);
        Colors.HOVER_ATTACK = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        Colors.HOVER_DEFENSE = new Color(0.05882353f, 0.49019608f, 0.6666667f, 1.0f);
        Colors.HOVER_LINE1 = new Color(0.06666667f, 0.07450981f, 0.09019608f, 1.0f);
        Colors.HOVER_LINE2 = new Color(0.16862746f, 0.1764706f, 0.1882353f, 1.0f);
        Colors.COLOR_TOP_STATS2 = new Color(0.92156863f, 0.9411765f, 0.8627451f, 1.0f);
        Colors.COLOR_TOP_STATS3 = new Color(0.8627451f, 0.84313726f, 0.74509805f, 1.0f);
        Colors.COLOR_TOP_STATS4 = new Color(0.9019608f, 0.8627451f, 0.7254902f, 1.0f);
        Colors.COLOR_TOP_STATS5 = new Color(0.78431374f, 0.7647059f, 0.6666667f, 1.0f);
        Colors.COLOR_TOP_STATS = new Color(0.98039216f, 0.99607843f, 0.9411765f, 1.0f);
        Colors.COLOR_TOP_STATS_HOVER = new Color(0.84313726f, 0.84313726f, 0.7647059f, 1.0f);
        Colors.COLOR_TOP_STATS_ACTIVE = new Color(0.7254902f, 0.7254902f, 0.627451f, 1.0f);
        Colors.COLOR_TOP_STATS_RIGHT = new Color(0.627451f, 0.50980395f, 0.29411766f, 1.0f);
        Colors.COLOR_TOP_STATS_RIGHT_HOVER = new Color(0.84313726f, 0.84313726f, 0.7647059f, 1.0f);
        Colors.COLOR_TOP_STATS_RIGHT_ACTIVE = new Color(0.7254902f, 0.7254902f, 0.627451f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_POSITIVE = new Color(0.27450982f, 0.50980395f, 0.23529412f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER = new Color(0.27450982f, 0.47058824f, 0.19607843f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE = new Color(0.23529412f, 0.43137255f, 0.19607843f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_NEUTRAL = new Color(0.8235294f, 0.8235294f, 0.78431374f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_NEUTRAL_HOVER = new Color(0.8627451f, 0.8627451f, 0.78431374f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE = new Color(0.9019608f, 0.9019608f, 0.8627451f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_NEGATIVE = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE = new Color(0.6509804f, 0.14117648f, 0.09411765f, 1.0f);
        Colors.COLOR_INFO_BOX = new Color(0.13725491f, 0.15686275f, 0.1764706f, 1.0f);
        Colors.COLOR_INFO_BOX2 = new Color(0.8509804f, 0.7607843f, 0.59607846f, 1.0f);
        Colors.COLOR_INFO_BOX2_BOT = new Color(0.47058824f, 0.4117647f, 0.37254903f, 1.0f);
        Colors.COLOR_INGAME_GOLD = new Color(0.87058824f, 0.85882354f, 0.12941177f, 1.0f);
        Colors.COLOR_INGAME_GOLD_HOVER = new Color(0.75686276f, 0.75686276f, 0.0f, 1.0f);
        Colors.COLOR_INGAME_GOLD_ACTIVE = new Color(0.6901961f, 0.6901961f, 0.0f, 1.0f);
        Colors.COLOR_POPULATION = new Color(0.392f, 0.533f, 0.251f, 1.0f);
        Colors.COLOR_POPULATION_HOVER = new Color(0.595f, 0.743f, 0.427f, 1.0f);
        Colors.COLOR_POPULATION_ACTIVE = new Color(0.4f, 0.51f, 0.3f, 1.0f);
        Colors.COLOR_TEXT_ECONOMY = new Color(0.776f, 0.518f, 0.227f, 1.0f);
        Colors.COLOR_TEXT_ECONOMY_HOVER = new Color(0.708f, 0.448f, 0.173f, 1.0f);
        Colors.COLOR_TEXT_ECONOMY_ACTIVE = new Color(0.552f, 0.36f, 0.141f, 1.0f);
        Colors.COLOR_GRADIENT = new Color(0.09803922f, 0.15686275f, 0.23529412f, 1.0f);
        Colors.COLOR_GRADIENT_HOVER = new Color(0.19607843f, 0.13725491f, 0.11764706f, 1.0f);
        Colors.TEXT_TOP_BOT = new Color(0.3529412f, 0.3529412f, 0.3529412f, 1.0f);
        Colors.TEXT_CURRENT_SITUATION = new Color(0.1254902f, 0.1254902f, 0.1254902f, 1.0f);
        Colors.TEXT_CURRENT_SITUATION_HOVER = new Color(0.1764706f, 0.1764706f, 0.1764706f, 1.0f);
        Colors.TEXT_CURRENT_SITUATION_ACTIVE = new Color(0.23529412f, 0.23529412f, 0.23529412f, 1.0f);
        Colors.BUTTON_TEXT = new Color(0.58f, 0.55f, 0.55f, 1.0f);
        Colors.BUTTON_TEXT_BRIGHT = new Color(0.675f, 0.65f, 0.65f, 1.0f);
        Colors.BUTTON_TEXT_BRIGHT2 = new Color(0.8f, 0.75f, 0.75f, 1.0f);
        Colors.BUTTON_TEXT_ACTIVE = new Color(0.98f, 0.98f, 0.843f, 1.0f);
        Colors.BUTTON_TEXT_HOVERED = new Color(0.705f, 0.705f, 0.647f, 1.0f);
        Colors.BUTTON_TEXT_DISABLED = new Color(0.49f, 0.49f, 0.49f, 0.5f);
        Colors.BUTTON_TEXT_DESC_SIMPLE = new Color(0.5686275f, 0.54901963f, 0.5294118f, 1.0f);
        Colors.BUTTON_TEXT2 = new Color(0.9f, 0.9f, 0.865f, 1.0f);
        Colors.BUTTON_TEXT_ACTIVE2 = new Color(0.92f, 0.92f, 0.85f, 1.0f);
        Colors.BUTTON_TEXT_HOVERED2 = new Color(0.75f, 0.75f, 0.7f, 1.0f);
        Colors.COLOR_TEXT_PIE_CHART_STATS = new Color(0.8f, 0.8f, 0.8f, 1.0f);
        Colors.COLOR_SLIDER_LEFT_BG = new Color(0.11764706f, 0.13725491f, 0.23529412f, 1.0f);
        Colors.COLOR_SLIDER_RIGHT_BG = new Color(0.98039216f, 0.98039216f, 0.98039216f, 1.0f);
        Colors.COLOR_COLOR_PICKER_RGB_BG = new Color(0.047058824f, 0.0627451f, 0.078431375f, 0.55f);
        Colors.COLOR_LOADING_SPLIT_ACTIVE = new Color(0.96862745f, 0.76862746f, 0.41960785f, 0.65f);
        Colors.COLOR_LOADING_SPLIT = new Color(0.77254903f, 0.6117647f, 0.2627451f, 0.35f);
        Colors.TECH_RESEARCHED = new Color(0.50980395f, 0.39215687f, 0.13725491f, 1.0f);
        Colors.TECH_RESEARCHED2 = new Color(0.78431374f, 0.627451f, 0.2627451f, 1.0f);
        Colors.TECH_RESEARCHED3 = new Color(0.6627451f, 0.5137255f, 0.1882353f, 1.0f);
        Colors.TECH_BLUE = new Color(0.09411765f, 0.16862746f, 0.23529412f, 1.0f);
        Colors.TECH_BLUE2 = new Color(0.078431375f, 0.3137255f, 0.49019608f, 1.0f);
        Colors.TECH_BLUE3 = new Color(0.105882354f, 0.22352941f, 0.30980393f, 1.0f);
        Colors.TECH_GRAY = new Color(0.13725491f, 0.13725491f, 0.13725491f, 1.0f);
        Colors.TECH_GRAY2 = new Color(0.28235295f, 0.28235295f, 0.28235295f, 1.0f);
        Colors.TECH_GRAY3 = new Color(0.21568628f, 0.21568628f, 0.21568628f, 1.0f);
        Colors.PROVINCE_ALPHA_POPULATION = 0.5f;
        Colors.COLOR_POPULATION_VIEW = new Color[] { new Color(0.8627451f, 0.93333334f, 0.78039217f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.8f, 0.92941177f, 0.7372549f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.6901961f, 0.89411765f, 0.59607846f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.6117647f, 0.8666667f, 0.49019608f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.5647059f, 0.87058824f, 0.3137255f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.41568628f, 0.7921569f, 0.23529412f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.37254903f, 0.7294118f, 0.19607843f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.30588236f, 0.6039216f, 0.16078432f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.2509804f, 0.49019608f, 0.13333334f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.20392157f, 0.4f, 0.10980392f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.14509805f, 0.28627452f, 0.078431375f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_ECONOMY = new Color[] { new Color(1.0f, 0.92156863f, 0.8f, Colors.PROVINCE_ALPHA_POPULATION), new Color(1.0f, 0.83137256f, 0.65882355f, Colors.PROVINCE_ALPHA_POPULATION), new Color(1.0f, 0.77254903f, 0.56078434f, Colors.PROVINCE_ALPHA_POPULATION), new Color(1.0f, 0.7294118f, 0.47843137f, Colors.PROVINCE_ALPHA_POPULATION), new Color(1.0f, 0.63529414f, 0.3254902f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.96862745f, 0.54509807f, 0.19215687f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.9411765f, 0.4627451f, 0.019607844f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.88235295f, 0.3882353f, 0.0627451f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.7921569f, 0.24313726f, 0.02745098f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.7137255f, 0.09803922f, 0.015686275f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.654902f, 0.08627451f, 0.011764706f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_INCOME = new Color[] { new Color(0.9882353f, 0.93333334f, 0.49411765f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.77254903f, 0.5176471f, 0.14509805f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_INFRASTRUCTURE = new Color[] { new Color(0.8509804f, 0.7372549f, 0.5686275f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.42745098f, 0.40392157f, 0.14117648f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_PROVINCE_VALUE = new Color[] { new Color(0.8509804f, 0.7372549f, 0.5686275f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.23137255f, 0.3254902f, 0.19607843f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_RED = new Color[] { new Color(0.81960785f, 0.7137255f, 0.6862745f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.7058824f, 0.1764706f, 0.11764706f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_MANPOWER = new Color[] { new Color(0.7058824f, 0.74509805f, 0.78431374f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.11764706f, 0.15686275f, 0.27450982f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_DEVASTATION = new Color[] { new Color(1.0f, 0.9019608f, 0.4509804f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.9411765f, 0.23529412f, 0.039215688f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_TAX = new Color[] { new Color(0.91764706f, 0.8f, 0.3764706f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.6862745f, 0.5254902f, 0.19215687f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_ECONOMY = new Color[] { new Color(1.0f, 0.83137256f, 0.65882355f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.7921569f, 0.24313726f, 0.02745098f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_DEFENSE = new Color(0.28235295f, 0.36078432f, 0.36078432f, Colors.PROVINCE_ALPHA_POPULATION);
        Colors.COLOR_PROVINCE_FORT = new Color[] { new Color(0.8235294f, 0.8235294f, 0.78431374f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.28235295f, 0.36078432f, 0.36078432f, Colors.PROVINCE_ALPHA_POPULATION) };
        Colors.COLOR_PROVINCE_LOOT = new Color[] { new Color(0.972549f, 0.96862745f, 0.7647059f, Colors.PROVINCE_ALPHA_POPULATION), new Color(0.92156863f, 0.41960785f, 0.08235294f, Colors.PROVINCE_ALPHA_POPULATION) };
    }
}
