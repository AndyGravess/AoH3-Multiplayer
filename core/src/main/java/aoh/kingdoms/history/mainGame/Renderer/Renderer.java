// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Renderer;

import java.util.Objects;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import java.util.Collections;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.zOther.XY;
import com.badlogic.gdx.math.Matrix4;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import aoc.kingdoms.lukasz.map.map.MapScale;
import aoc.kingdoms.lukasz.map.map.Map_Data;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.concurrent.CopyOnWriteArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import space.earlygrey.shapedrawer.ShapeDrawer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import aoc.kingdoms.lukasz.menu_element.pieChart.PieChart_Renderer;
import aoc.kingdoms.lukasz.utilities.FPS;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Renderer
{
    public static Viewport viewport;
    public static OrthographicCamera camera;
    public SpriteBatch oSB;
    public static FPS uFPS;
    public static PieChart_Renderer pieChartRenderer;
    public static SpriteBatch oSBBorder;
    public static TextureRegion drawerPix;
    public static ShapeDrawer shapeDrawer;
    public static float shaderTime;
    public static float shaderTime2;
    public static ShaderProgram shaderDefault;
    public static ShaderProgram shaderDefault_FBO;
    public static ShaderProgram shaderWater;
    public static ShaderProgram shaderBlackWhite;
    public static ShaderProgram shaderDefaultProvince;
    public static ShaderProgram shaderAlpha;
    public static ShaderProgram shaderAlpha2;
    public static ShaderProgram shaderAlpha_Pattern;
    public static ShaderProgram shaderAlpha_Map;
    public static ShaderProgram shaderAlpha_MapSea;
    public static ShaderProgram shaderWater2;
    public static ShaderProgram shaderOutline;
    public static ShaderProgram shaderBlur;
    public static ShaderProgram shaderWater3;
    public static boolean drawArmyInProvince;
    private static int numOfScissors;
    public static Rectangle peekBounds;
    public static Color BACKGROUND_COLOR;
    public static boolean updateBackgroundColor;
    public static CopyOnWriteArrayList<Game.SimpleTask> simpleTasksCivNames;
    public static List<SimpleTaskArmyText> simpleTasks_ArmyWidth;
    public static int boxBGExtraY;
    public static int iBoxCornerX;
    public static long BOX_CORNER_TIME;
    public static long BOX_CORNER_TIMER;
    public static GlyphLayout_Game glyphLayout;
    public static String charset;
    public static List<BitmapFont> fontMain;
    public static int fontMainSize;
    public static BitmapFont fontArmy_GlyphLayout;
    public static List<BitmapFont> fontBorder;
    public static int fontBorderSize;
    public static final Vector3 textRotatedVector3;
    public static String sLoadingText;
    public static int iLoadingTextWidth;
    public static long loadingTime;
    public static final int LOADING_CHANGE_TEXT_TIME = 4000;
    
    public static final int getHover_ExtraPosX() {
        return 25;
    }
    
    public static final int getHover_ExtraPosY() {
        return 30;
    }
    
    public Renderer(final int nGameWidth, final int nGameHeight) {
        CFG.GAME_WIDTH = nGameWidth;
        CFG.GAME_HEIGHT = nGameHeight;
        (Renderer.camera = new OrthographicCamera((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT)).setToOrtho(false, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
        Renderer.viewport = (Viewport)new FitViewport((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT, (Camera)Renderer.camera);
        this.oSB = new SpriteBatch();
        Renderer.uFPS = new FPS();
        Renderer.oSBBorder = new SpriteBatch();
        Images.pix = ImageManager.buildPix_IMG();
        Images.pix2 = ImageManager.buildPix();
        Renderer.drawerPix = new TextureRegion(Images.pix.getTexture());
        Renderer.shapeDrawer = new ShapeDrawer((Batch)Renderer.oSBBorder, Renderer.drawerPix);
        this.loadShaders();
    }
    
    private final void loadShaders() {
        final String defaultVertex = FileManager.loadFile("game/shader/default_vertex.glsl").readString();
        Renderer.shaderDefault = new ShaderProgram(defaultVertex, FileManager.loadFile("game/shader/default_fragment.glsl").readString());
        Renderer.shaderBlackWhite = new ShaderProgram(defaultVertex, FileManager.loadFile("game/shader/blackWhite_fragment.glsl").readString());
        Renderer.shaderWater = new ShaderProgram(defaultVertex, FileManager.loadFile("game/shader/water_fragment.glsl").readString());
        final String defaultFragment_Province = "#ifdef GL_ES\n    precision mediump float;\n#endif\n\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\n\nvoid main() {\nvec4 mask = texture2D(u_texture, v_texCoords);\n    gl_FragColor = vec4(v_color.rgb, v_color.a * mask.a);\n}";
        Renderer.shaderDefaultProvince = new ShaderProgram(defaultVertex, defaultFragment_Province);
        final String defaultFragment_FBO = "#ifdef GL_ES\n    precision mediump float;\n#endif\n\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\n\nvoid main() {\n   gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n   gl_FragColor.rgb *= glFragColor.a;\n}";
        Renderer.shaderDefault_FBO = new ShaderProgram(defaultVertex, defaultFragment_FBO);
        final String flagFragment = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform sampler2D u_texture2;\nvoid main()    \n{\n vec4 mask = texture2D(u_texture2, v_texCoords);\n gl_FragColor = vec4(mask.rgb, mask.a * (v_color.a * texture2D(u_texture, v_texCoords).a));\n}";
        final String vertexShader = "attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n";
        Renderer.shaderAlpha = new ShaderProgram(vertexShader, flagFragment);
        final ShaderProgram shaderAlpha = Renderer.shaderAlpha;
        ShaderProgram.pedantic = false;
        Renderer.shaderAlpha.bind();
        Renderer.shaderAlpha.setUniformi("u_texture", 0);
        Renderer.shaderAlpha.setUniformi("u_texture2", 1);
        Renderer.shaderAlpha2 = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/alpha_shader.glsl").readString());
        final ShaderProgram shaderAlpha2 = Renderer.shaderAlpha2;
        ShaderProgram.pedantic = false;
        Renderer.shaderAlpha2.bind();
        Renderer.shaderAlpha2.setUniformi("u_texture", 0);
        Renderer.shaderAlpha2.setUniformi("u_texture2", 1);
        Renderer.shaderAlpha2.setUniformf("u_useMask", 1.0f);
        Renderer.shaderAlpha2.setUniformf("u_maskScale", 20.0f);
        Renderer.shaderAlpha2.setUniformf("u_maskOffset", 0.0f, 0.0f);
        Renderer.shaderAlpha_Pattern = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/alpha_shader_pattern.glsl").readString());
        final ShaderProgram shaderAlpha_Pattern = Renderer.shaderAlpha_Pattern;
        ShaderProgram.pedantic = false;
        Renderer.shaderAlpha_Pattern.bind();
        Renderer.shaderAlpha_Pattern.setUniformi("u_texture", 0);
        Renderer.shaderAlpha_Pattern.setUniformi("u_texture2", 1);
        Renderer.shaderAlpha_Pattern.setUniformf("u_useMask", 1.0f);
        Renderer.shaderAlpha_Pattern.setUniformf("u_maskScale", 20.0f);
        Renderer.shaderAlpha_Pattern.setUniformf("u_maskOffset", 0.0f, 0.0f);
        Renderer.shaderAlpha_Map = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/map_overlay_fragment.glsl").readString());
        final ShaderProgram shaderAlpha_Map = Renderer.shaderAlpha_Map;
        ShaderProgram.pedantic = false;
        Renderer.shaderAlpha_Map.bind();
        Renderer.shaderAlpha_Map.setUniformi("u_texture", 0);
        Renderer.shaderAlpha_Map.setUniformi("u_texture2", 1);
        Renderer.shaderAlpha_Map.setUniformf("u_useMask", 1.0f);
        Renderer.shaderAlpha_Map.setUniformf("u_maskScale", 20.0f);
        Renderer.shaderAlpha_Map.setUniformf("u_maskOffset", 0.0f, 0.0f);
        Renderer.shaderAlpha_MapSea = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/map_overlay_sea_fragment.glsl").readString());
        final ShaderProgram shaderAlpha_MapSea = Renderer.shaderAlpha_MapSea;
        ShaderProgram.pedantic = false;
        Renderer.shaderAlpha_MapSea.bind();
        Renderer.shaderAlpha_MapSea.setUniformi("u_texture", 0);
        Renderer.shaderAlpha_MapSea.setUniformi("u_texture2", 1);
        Renderer.shaderAlpha_MapSea.setUniformf("u_useMask", 1.0f);
        Renderer.shaderAlpha_MapSea.setUniformf("u_maskScale", 20.0f);
        Renderer.shaderAlpha_MapSea.setUniformf("u_maskOffset", 0.0f, 0.0f);
        String testFragment = "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP\n#endif\n\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\n\n\nuniform sampler2D u_texture;\nuniform float time;\nuniform vec2 resolution;\n\n\nconst float PI = 3.1415;\n// \u901f\u5ea6\nconst float speed = 0.025;\nconst float speed_x = 0.05;\nconst float speed_y = 0.05;\n\n// \u6298\u5c04\u89d2\nconst float emboss = 0.3; \t\t// \u51f9\u51f8\u5f3a\u5ea6\nconst float intensity = 2.4;\t// \u5f3a\u5ea6\nconst int steps = 8;  \t\t\t// \u6ce2\u7eb9\u5bc6\u5ea6\nconst float frequency = 4.0;  \t// \u9891\u7387\nconst float angle = 7.0;\n\nconst float delta = 50.0;  \t\t// \u589e\u5e45\uff08\u8d8a\u5c0f\u8d8a\u6fc0\u70c8\uff09\nconst float intence = 200.0;   \t// \u660e\u6697\u5f3a\u5ea6\n\n// \u9ad8\u5149\nconst float reflectionCutOff = 0.012;\nconst float reflectionIntence = 80000.0;\n\nfloat col(vec2 coord)\n{\n    float delta_theta = 2.0 * PI / angle;\n    float col = 0.0;\n    float theta = 0.0;\n    for (int i = 0; i < steps; i++)\n    {\n        vec2 adjc = coord;\n        theta = delta_theta * float(i);\n        adjc.x += cos(theta)*time*speed + time * speed_x;\n        adjc.y -= sin(theta)*time*speed - time * speed_y;\n        col = col + cos((adjc.x * cos(theta) -\n            adjc.y * sin(theta)) * frequency) * intensity;\n    }\n    return cos(col);\n}\n\n\nvoid main()\n{\n    vec2 p = v_texCoords, c1 = p, c2 = p;\n    float cc1 = col(c1);\n\n    c2.x += resolution.x/delta;\n    float dx = emboss*(cc1-col(c2))/delta;\n\n    c2.x = p.x;\n    c2.y += resolution.y/delta;\n    float dy = emboss*(cc1-col(c2))/delta;\n    c1.x = c1.x +dx;\n    c1.y =  c1.y+dy;\n\n    float alpha = 1.0+dot(dx,dy)*intence;\n\n\n    vec4 col = texture2D(u_texture,c1);\n    col *= alpha;\n    gl_FragColor =  col;\n}";
        Renderer.shaderWater2 = new ShaderProgram(defaultVertex, testFragment);
        testFragment = "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP\n#endif\n\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\n\n\nuniform sampler2D u_texture;\nuniform sampler2D u_texture2;\nuniform float time;\nuniform vec2 resolution;\nuniform float u_maskScale;\nuniform float u_maskScaleY;\nuniform float u_useMask;\nuniform vec2 u_maskOffset;\n\n\nconst float PI = 3.1415;\n// \u901f\u5ea6\nconst float speed = 0.03;\nconst float speed_x = 0.06;\nconst float speed_y = 0.06;\n\n// \u6298\u5c04\u89d2\nconst float emboss = 0.3; \t\t// \u51f9\u51f8\u5f3a\u5ea6\nconst float intensity = 2.4;\t// \u5f3a\u5ea6\nconst int steps = 8;  \t\t\t// \u6ce2\u7eb9\u5bc6\u5ea6\nconst float frequency = 4.0;  \t// \u9891\u7387\nconst float angle = 7.0;\n\nconst float delta = 50.0;  \t\t// \u589e\u5e45\uff08\u8d8a\u5c0f\u8d8a\u6fc0\u70c8\uff09\nconst float intence = 200.0;   \t// \u660e\u6697\u5f3a\u5ea6\n\n// \u9ad8\u5149\nconst float reflectionCutOff = 0.012;\nconst float reflectionIntence = 80000.0;\n\nfloat col(vec2 coord)\n{\n    float delta_theta = 2.0 * PI / angle;\n    float col = 0.0;\n    float theta = 0.0;\n    for (int i = 0; i < steps; i++)\n    {\n        vec2 adjc = coord;\n        theta = delta_theta * float(i);\n        adjc.x += cos(theta)*time*speed + time * speed_x;\n        adjc.y -= sin(theta)*time*speed - time * speed_y;\n        col = col + cos((adjc.x * cos(theta) -\n            adjc.y * sin(theta)) * frequency) * intensity;\n    }\n    return cos(col);\n}\n\n\nvoid main()\n{\n    vec2 p = v_texCoords, c1 = p, c2 = p;\n    float cc1 = col(c1);\n\n    c2.x += resolution.x/delta;\n    float dx = emboss*(cc1-col(c2))/delta;\n\n    c2.x = p.x;\n    c2.y += resolution.y/delta;\n    float dy = emboss*(cc1-col(c2))/delta;\n    c1.x = c1.x +dx;\n    c1.y =  c1.y+dy;\n\n    float alpha = 1.0+dot(dx,dy)*intence;\n\n\n    vec4 col = texture2D(u_texture,c1);\n vec2 newCoords = vec2(v_texCoords.x * u_maskScale, v_texCoords.y * u_maskScaleY);\n vec4 mask = vec4(1.0, 1.0, 1.0, 1.0); \n\tmask = texture2D(u_texture2, v_texCoords);\n  gl_FragColor = vec4(col.rgb, mask.a * col.a);\n}";
        (Renderer.shaderWater3 = new ShaderProgram(defaultVertex, testFragment)).bind();
        Renderer.shaderWater3.setUniformi("u_texture", 0);
        Renderer.shaderWater3.setUniformi("u_texture2", 1);
        Renderer.shaderWater3.setUniformf("u_useMask", 1.0f);
        Renderer.shaderWater3.setUniformf("u_maskScale", 20.0f);
        Renderer.shaderWater3.setUniformf("u_maskOffset", 0.0f, 0.0f);
        testFragment = "#ifdef GL_ES\nprecision mediump float;\n#endif\n\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nuniform sampler2D u_texture;\nuniform float outlineSize;\nuniform vec3 outlineColor;\nuniform vec2 textureSize;\n\nconst float PI = 0.01745329252;\n\n\nint getIsStrokeWithAngel(float angel)\n{\n    int stroke = 0;\n    float rad = angel * PI;\n    vec2 unit = 1.0 / textureSize.xy;\n    vec2 offset = vec2(outlineSize * cos(rad) * unit.x, outlineSize * sin(rad) * unit.y);\n    float a = texture2D(u_texture, v_texCoords + offset).a;\n    if (a >= 0.5)\n    {\n        stroke = 1;\n    }\n    return stroke;\n}\n\nvoid main()\n{\n    vec4 myC = texture2D(u_texture, v_texCoords);\n    if (myC.a >= 0.5)\n    {\n        gl_FragColor = v_color * myC;\n        return;\n    }\n\n    int strokeCount = 0;\n    strokeCount += getIsStrokeWithAngel(0.0);\n    strokeCount += getIsStrokeWithAngel(30.0);\n    strokeCount += getIsStrokeWithAngel(60.0);\n    strokeCount += getIsStrokeWithAngel(90.0);\n    strokeCount += getIsStrokeWithAngel(120.0);\n    strokeCount += getIsStrokeWithAngel(150.0);\n    strokeCount += getIsStrokeWithAngel(180.0);\n    strokeCount += getIsStrokeWithAngel(210.0);\n    strokeCount += getIsStrokeWithAngel(240.0);\n    strokeCount += getIsStrokeWithAngel(270.0);\n    strokeCount += getIsStrokeWithAngel(300.0);\n    strokeCount += getIsStrokeWithAngel(330.0);\n\n    if (strokeCount > 0)\n    {\n        myC.rgb = outlineColor;\n        myC.a = 1.0;\n    }\n\n    gl_FragColor = v_color * myC;\n}";
        Renderer.shaderOutline = new ShaderProgram(defaultVertex, testFragment);
        testFragment = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec2 v_texCoords;\nvarying vec4 v_color;\nuniform float widthStep;\nuniform float heightStep;\nuniform float strength;\nuniform sampler2D u_texture;\nconst float blurRadius = 5.0;\nconst float blurPixels = (blurRadius * 2.0 + 1.0) * (blurRadius * 2.0 + 1.0);\n\nvoid main()\n{\n    vec4 v = texture2D(u_texture, v_texCoords);\n    vec3 sumColor = vec3(0.0, 0.0, 0.0);\n    for(float fy = -blurRadius; fy <= blurRadius; ++fy)\n    {\n        for(float fx = -blurRadius; fx <= blurRadius; ++fx)\n        {\n            vec2 coord = vec2(fx * widthStep, fy * heightStep);\n            sumColor += texture2D(u_texture, v_texCoords + coord).rgb;\n        }\n    }\n    gl_FragColor = vec4(mix(v.rgb, sumColor / blurPixels, strength), v.a*v_color.a);\n}";
        Renderer.shaderBlur = new ShaderProgram(defaultVertex, testFragment);
    }
    
    public void render() {
        try {
            this.update();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.clearScreen();
        this.renderer_SetToCurrentScale();
        try {
            this.oSB.setColor(Color.WHITE);
            Game.map.drawMap(this.oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        RendererGame.rendererGame.drawCurrentScale_Provinces(this.oSB);
        this.renderer_resetScale2();
        RendererGame.rendererGame.drawWithoutScale_Provinces(this.oSB);
        this.renderer_SetToCurrentScale2();
        try {
            RendererGame.rendererGame.drawCurrentScale(this.oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.oSB.end();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.renderer_resetScale();
        try {
            RendererGame.rendererGame.drawWithoutScale(this.oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.renderUI();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.oSB.end();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            Renderer.oSBBorder.end();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void renderUI() {
        try {
            Game.menuManager.draw(this.oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (Renderer.numOfScissors > 0) {
            clipView_End(this.oSB);
        }
        this.drawKeyboardText(this.oSB);
        Renderer.uFPS.drawFPS(this.oSB);
    }
    
    public final void drawKeyboardText(final SpriteBatch oSB) {
        if (Keyboard.keyboardMode && (!CFG.isDesktop() || GameValues.info.DESKTOP_KEYBOARD_DRAW_EXTRA_TEXT)) {
            final int textW = getTextWidth(Keyboard.keyboardMessage, CFG.FONT_REGULAR);
            drawBox(oSB, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, textW + CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 4, 1.0f);
            oSB.setColor(Color.WHITE);
            drawText(oSB, CFG.FONT_REGULAR, Keyboard.keyboardMessage, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, Colors.HOVER_GOLD);
        }
    }
    
    public static final void setShaderDefault(final SpriteBatch oSB) {
        oSB.setShader(Renderer.shaderDefault);
    }
    
    public static final void setBlackWhite(final SpriteBatch oSB) {
        oSB.setShader(Renderer.shaderBlackWhite);
    }
    
    public static final void setShaderWater(final SpriteBatch oSB) {
        Renderer.shaderTime += Gdx.graphics.getDeltaTime();
        oSB.setShader(Renderer.shaderWater);
        Renderer.shaderWater.bind();
        Renderer.shaderWater.setUniformf("u_amount", 25.0f);
        Renderer.shaderWater.setUniformf("u_speed", 0.15f);
        Renderer.shaderWater.setUniformf("u_py", 0.15f);
        Renderer.shaderWater.setUniformf("u_px", 0.55f);
        Renderer.shaderWater.setUniformf("u_time", Renderer.shaderTime);
    }
    
    public static final void setShaderWater2(final SpriteBatch oSB) {
        Renderer.shaderTime2 += Gdx.graphics.getDeltaTime();
        oSB.setShader(Renderer.shaderWater2);
        Renderer.shaderWater2.setUniformf("time", Renderer.shaderTime2);
        Renderer.shaderWater2.setUniformf("resolution", new Vector2((float)ImageManager.getImage(Images.waves).getWidth(), (float)ImageManager.getImage(Images.waves).getHeight()));
    }
    
    public static final void setShaderWater3(final SpriteBatch oSB) {
        oSB.setShader(Renderer.shaderWater3);
        Renderer.shaderWater3.setUniformf("time", Renderer.shaderTime2);
        Renderer.shaderWater3.setUniformf("resolution", new Vector2((float)ImageManager.getImage(Images.flagBG).getWidth(), (float)ImageManager.getImage(Images.flagBG).getHeight()));
    }
    
    public static final void setShaderBlur(final SpriteBatch oSB) {
        oSB.setShader(Renderer.shaderBlur);
        Renderer.shaderBlur.setUniformf("widthStep", 0.0f);
        Renderer.shaderBlur.setUniformf("heightStep", 0.0f);
        Renderer.shaderBlur.setUniformf("strength", 1.0f);
    }
    
    public static final void setShaderOutline(final SpriteBatch oSB) {
        if (Game.menuManager.getInNewGame()) {
            oSB.setShader(Renderer.shaderOutline);
            Renderer.shaderOutline.setUniformf("outlineColor", 0.0f, 1.0f, 0.0f);
            Renderer.shaderOutline.setUniformf("outlineSize", 2.0f);
            Renderer.shaderOutline.setUniformf("textureSize", (float)Game.getProvince(0).getProvinceBG().getWidth(), (float)Game.getProvince(0).getProvinceBG().getHeight());
            Game.getProvince(0).getProvinceBG().draw(oSB, 150, 150);
            oSB.setShader(Renderer.shaderDefault);
        }
    }
    
    public static final void drawLineShape(final int iX, final int iY, final int iX2, final int iY2) {
        Renderer.shapeDrawer.line((float)iX, (float)(-iY), (float)iX2, (float)(-iY2));
    }
    
    private final void renderer_SetToCurrentScale() {
        try {
            Renderer.camera.setToOrtho(false, CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), -CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
            Renderer.viewport.setWorldSize(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
            Renderer.viewport.apply();
            this.oSB.setProjectionMatrix(Renderer.camera.combined);
            Renderer.oSBBorder.setProjectionMatrix(Renderer.camera.combined);
            this.oSB.begin();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void renderer_resetScale() {
        try {
            Renderer.camera.setToOrtho(false, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
            Renderer.viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
            Renderer.viewport.apply();
            this.oSB.setProjectionMatrix(Renderer.camera.combined);
            Renderer.oSBBorder.setProjectionMatrix(Renderer.camera.combined);
            this.oSB.begin();
            Renderer.oSBBorder.begin();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void renderer_SetToCurrentScale2() {
        try {
            Renderer.camera.setToOrtho(false, CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), -CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
            Renderer.viewport.setWorldSize(CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
            Renderer.viewport.apply();
            this.oSB.setProjectionMatrix(Renderer.camera.combined);
            Renderer.oSBBorder.setProjectionMatrix(Renderer.camera.combined);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void renderer_resetScale2() {
        try {
            Renderer.camera.setToOrtho(false, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
            Renderer.viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
            Renderer.viewport.apply();
            this.oSB.setProjectionMatrix(Renderer.camera.combined);
            Renderer.oSBBorder.setProjectionMatrix(Renderer.camera.combined);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void clearUnclearedScissors(final SpriteBatch oSB) {
        while (Renderer.numOfScissors > 0) {
            clipView_End(oSB);
        }
    }
    
    public static final void clipViewPeek() {
        try {
            Renderer.peekBounds = ScissorStack.peekScissors();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void clipViewPeek_Add(final SpriteBatch oSB) {
        try {
            clearUnclearedScissors(oSB);
            if (Renderer.peekBounds != null) {
                ScissorStack.pushScissors(Renderer.peekBounds);
                ++Renderer.numOfScissors;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final boolean clipView_Start(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        try {
            final Rectangle clipBounds = new Rectangle((float)nPosX, (float)nPosY, (float)nWidth, (float)nHeight);
            oSB.flush();
            ++Renderer.numOfScissors;
            return ScissorStack.pushScissors(clipBounds);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    public static final void clipView_End(final SpriteBatch oSB) {
        try {
            Renderer.numOfScissors = Math.max(Renderer.numOfScissors - 1, 0);
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (final Exception ex) {}
    }
    
    protected final void clearScreen() {
        this.updateBackgroundColor();
        Gdx.gl.glClearColor(Renderer.BACKGROUND_COLOR.r, Renderer.BACKGROUND_COLOR.g, Renderer.BACKGROUND_COLOR.b, Renderer.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(16640);
        this.oSB.setColor(Color.WHITE);
    }
    
    private final void updateBackgroundColor() {
        if (Renderer.updateBackgroundColor) {
            Renderer.updateBackgroundColor = false;
            if (Game.mapScale.getCurrentScale() == 1.0f) {
                Renderer.BACKGROUND_COLOR = new Color(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[0], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[1], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[2], 1.0f);
            }
            else if (Game.mapScale.getCurrentScale() > 1.0f) {
                final MapScale mapScale = Game.mapScale;
                final float numOfSteps = MapScale.defScales.definedScales[1] - 1.0f;
                final float currentStep = Game.mapScale.getCurrentScale() - 1.0f;
                Renderer.BACKGROUND_COLOR = new Color(CFG.getColorStep(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[0], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor_ZoomIn[0], currentStep, numOfSteps), CFG.getColorStep(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[1], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor_ZoomIn[1], currentStep, numOfSteps), CFG.getColorStep(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[2], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor_ZoomIn[2], currentStep, numOfSteps), 1.0f);
            }
            else {
                final float n = 1.0f;
                final MapScale mapScale2 = Game.mapScale;
                final float numOfSteps = n - MapScale.defScales.definedScales[Game.mapScale.definedScalesLength - 1];
                final float currentStep = 1.0f - Game.mapScale.getCurrentScale();
                Renderer.BACKGROUND_COLOR = new Color(CFG.getColorStep(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[0], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor_ZoomOut[0], currentStep, numOfSteps), CFG.getColorStep(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[1], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor_ZoomOut[1], currentStep, numOfSteps), CFG.getColorStep(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor[2], Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.BackgroundColor_ZoomOut[2], currentStep, numOfSteps), 1.0f);
            }
        }
    }
    
    public void update() {
        CFG.currentTimeMillis = System.currentTimeMillis();
        Game.updateSimpleTask();
        Game.map.update();
        Game.menuManager.update();
        Renderer.uFPS.countFPS();
        Game.ambienceManager.update();
        Game.animationManager.update();
        Keyboard.updateKeyboardVerticalLine();
        try {
            for (int i = Math.min(1, Renderer.simpleTasksCivNames.size() - 1); i >= 0; --i) {
                Renderer.simpleTasksCivNames.get(i).update();
                Renderer.simpleTasksCivNames.remove(i);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i = Math.min(50, Renderer.simpleTasks_ArmyWidth.size() - 1); i >= 0; --i) {
                Renderer.simpleTasks_ArmyWidth.get(i).update();
                Renderer.simpleTasks_ArmyWidth.remove(i);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            SteamManager.updateSteam_runCallbacks();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void addSimpleTaskCivsNames(final Game.SimpleTask newTask) {
        if (Renderer.simpleTasksCivNames.contains(newTask)) {
            return;
        }
        Renderer.simpleTasksCivNames.add(newTask);
    }
    
    public static void addSimpleTask_ArmyWidth(final SimpleTaskArmyText newTask) {
        if (Renderer.simpleTasks_ArmyWidth.contains(newTask)) {
            return;
        }
        Renderer.simpleTasks_ArmyWidth.add(newTask);
    }
    
    public final void drawLine(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nPosX2, final int nPosY2) {
        this.drawLine(oSB, nPosX, nPosY, (int)Math.ceil(Math.sqrt((nPosX2 - nPosX) * (nPosX2 - nPosX) + (nPosY - nPosY2) * (nPosY - nPosY2))), (float)(Math.atan2(nPosY - nPosY2, -nPosX + nPosX2) * 180.0 / 3.141592653589793));
    }
    
    public final void drawLine(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final float fAngle) {
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, Images.pix.getHeight(), fAngle);
    }
    
    public static final void drawRect(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, 1);
        Images.pix.draw(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        Images.pix.draw(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw(oSB, nPosX + nWidth, nPosY, 1, nHeight);
    }
    
    public static final void drawLine(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        oSB.setColor(new Color(Colors.HOVER_LINE1.r, Colors.HOVER_LINE1.g, Colors.HOVER_LINE1.b, 1.0f));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, 1);
        oSB.setColor(new Color(Colors.HOVER_LINE2.r, Colors.HOVER_LINE2.g, Colors.HOVER_LINE2.b, 1.0f));
        Images.pix.draw(oSB, nPosX, nPosY + 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawMenusBox(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final boolean flipX) {
        ImageManager.getImage(Images.insideTop).draw2(oSB, nPosX, nPosY, nWidth, nHeight - ImageManager.getImage(Images.insideBot).getHeight(), flipX, false);
        ImageManager.getImage(Images.insideBot).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(Images.insideBot).getHeight(), nWidth, ImageManager.getImage(Images.insideBot).getHeight(), flipX, false);
    }
    
    public static final void drawMenusBox(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final boolean flipX, final int imageIDTop, final int imageIDBot) {
        ImageManager.getImage(imageIDTop).draw2(oSB, nPosX, nPosY, nWidth, nHeight - ImageManager.getImage(imageIDBot).getHeight(), flipX, false);
        ImageManager.getImage(imageIDBot).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(imageIDBot).getHeight(), nWidth, ImageManager.getImage(imageIDBot).getHeight(), flipX, false);
    }
    
    public static final void drawMenusBoxTopOnly(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final boolean flipX, final int imageIDTop) {
        ImageManager.getImage(imageIDTop).draw2(oSB, nPosX, nPosY, nWidth, nHeight, flipX, false);
    }
    
    public static final void drawBoxCorner(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.85f));
        ImageManager.getImage(Images.boxBGLinePix).draw(oSB, nPosX, nPosY, nWidth, nHeight);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - Renderer.boxBGExtraY, nPosY - Renderer.boxBGExtraY);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY - Renderer.boxBGExtraY, true, false);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - Renderer.boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX - Renderer.boxBGExtraY, nPosY, Renderer.boxBGExtraY, nHeight);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX + nWidth, nPosY, Renderer.boxBGExtraY, nHeight, true, false);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY - Renderer.boxBGExtraY, nWidth, Renderer.boxBGExtraY);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, Renderer.boxBGExtraY, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxCornerAlpha(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        ImageManager.getImage(Images.boxBGLinePix).draw(oSB, nPosX, nPosY, nWidth, nHeight);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - Renderer.boxBGExtraY, nPosY - Renderer.boxBGExtraY);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY - Renderer.boxBGExtraY, true, false);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - Renderer.boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX - Renderer.boxBGExtraY, nPosY, Renderer.boxBGExtraY, nHeight);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX + nWidth, nPosY, Renderer.boxBGExtraY, nHeight, true, false);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY - Renderer.boxBGExtraY, nWidth, Renderer.boxBGExtraY);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, Renderer.boxBGExtraY, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxCornerEmpty(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - Renderer.boxBGExtraY, nPosY - Renderer.boxBGExtraY);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY - Renderer.boxBGExtraY, true, false);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - Renderer.boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX - Renderer.boxBGExtraY, nPosY, Renderer.boxBGExtraY, nHeight);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX + nWidth, nPosY, Renderer.boxBGExtraY, nHeight, true, false);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY - Renderer.boxBGExtraY, nWidth, Renderer.boxBGExtraY);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, Renderer.boxBGExtraY, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxCorner2(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - Renderer.boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        if (Renderer.BOX_CORNER_TIME <= CFG.currentTimeMillis - Renderer.BOX_CORNER_TIMER) {
            Renderer.BOX_CORNER_TIME = CFG.currentTimeMillis;
            ++Renderer.iBoxCornerX;
        }
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, Renderer.boxBGExtraY, -Renderer.iBoxCornerX, 0, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        Images.gradientXY.draw(oSB, nPosX - CFG.PADDING, nPosY, nWidth + CFG.PADDING * 2, nHeight);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        Images.gradientXY.draw(oSB, nPosX - CFG.PADDING, nPosY + (int)Math.ceil(nHeight / 2.0f), nWidth + CFG.PADDING * 2, nHeight / 2);
        Images.gradientXY.draw(oSB, nPosX - CFG.PADDING, nPosY + (int)Math.ceil(nHeight / 2.0f), nWidth + CFG.PADDING * 2, nHeight / 2);
    }
    
    public static final void drawBox(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        drawBox(oSB, Images.boxEdge, nPosX, nPosY, nWidth, nHeight, fAlpha);
    }
    
    public static final void drawBox(final SpriteBatch oSB, final int imageID, final int nPosX, final int nPosY, int nWidth, final int nHeight, final float fAlpha) {
        final int iHCeil = (nHeight + 1) / 2;
        final int iHFloor = nHeight / 2;
        final Image img = ImageManager.getImage(imageID);
        nWidth = Math.max(nWidth, img.getWidth() * 2);
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), iHCeil);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), iHCeil, true);
        img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth - img.getWidth(), iHFloor, false, true);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY + iHCeil, img.getWidth(), iHFloor, true, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBox(final SpriteBatch oSB, final Image img, final int nPosX, final int nPosY, int nWidth, final int nHeight, final float fAlpha) {
        final int iHCeil = (nHeight + 1) / 2;
        final int iHFloor = nHeight / 2;
        nWidth = Math.max(nWidth, img.getWidth() * 2);
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), iHCeil);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), iHCeil, true);
        img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth - img.getWidth(), iHFloor, false, true);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY + iHCeil, img.getWidth(), iHFloor, true, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxProgress(final SpriteBatch oSB, final Image img, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int maxWidth) {
        final int iHCeil = (nHeight + 1) / 2;
        final int iHFloor = nHeight / 2;
        if (img.getWidth() * 2 > nWidth) {
            img.draw2(oSB, nPosX, nPosY, nWidth, iHCeil);
            img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth, iHFloor, false, true);
        }
        else {
            img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), iHCeil);
            img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), iHCeil, true);
            img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth - img.getWidth(), iHFloor, false, true);
            img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY + iHCeil, img.getWidth(), iHFloor, true, true);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxTOP(final SpriteBatch oSB, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxTOP(final SpriteBatch oSB, final Image img, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), nHeight);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), nHeight, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxBOT(final SpriteBatch oSB, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight, false, true);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxBOT(final SpriteBatch oSB, final Image img, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), nHeight, false, true);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), nHeight, true, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBlueBox(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_INFO.r, Colors.COLOR_GRADIENT_BG_INFO.g, Colors.COLOR_GRADIENT_BG_INFO.b, 0.9f));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_INFO.r, Colors.COLOR_GRADIENT_OVER_INFO.g, Colors.COLOR_GRADIENT_OVER_INFO.b, 0.5f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_INFO.r, Colors.COLOR_GRADIENT_OVER_INFO.g, Colors.COLOR_GRADIENT_OVER_INFO.b, 0.25f));
        Images.gradientXY.draw(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_INFO.r, Colors.COLOR_GRADIENT_OVER_INFO.g, Colors.COLOR_GRADIENT_OVER_INFO.b, 1.0f));
        Images.gradientFull.draw(oSB, nPosX, nPosY + 1, nWidth, 1);
        Images.gradientFull.draw(oSB, nPosX, nPosY + nHeight - 2, nWidth, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        Images.gradientFull.draw(oSB, nPosX, nPosY, nWidth, 1);
        Images.gradientFull.draw(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBox2(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        Images.pix.draw2(oSB, nPosX, nPosY, nWidth, 1);
        Images.pix.draw2(oSB, nPosX + nWidth - 1, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxLineFrame(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final Color color) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY, nWidth, CFG.PADDING + CFG.PADDING / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + nHeight - CFG.PADDING - CFG.PADDING / 2, nWidth, CFG.PADDING + CFG.PADDING / 2, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientXY.draw(oSB, nPosX, nPosY, nWidth, CFG.PADDING + CFG.PADDING / 2, false, true);
        Images.gradientXY.draw(oSB, nPosX, nPosY + nHeight - CFG.PADDING - CFG.PADDING / 2, nWidth, CFG.PADDING + CFG.PADDING / 2);
        oSB.setColor(color);
        Images.pix.draw2(oSB, nPosX, nPosY, nWidth, 1);
        Images.pix.draw2(oSB, nPosX + nWidth - 1, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBoxHover(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        drawBoxHover(oSB, Images.boxEdge, nPosX, nPosY, nWidth, nHeight, fAlpha);
    }
    
    public static final void drawBoxHover(final SpriteBatch oSB, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final float fAlpha) {
        final int iHCeil = (int)Math.ceil(nHeight / 2.0f);
        final int iHFloor = (int)Math.floor(nHeight / 2.0f);
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), iHCeil);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), iHCeil, true);
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY + iHCeil, nWidth - ImageManager.getImage(imageID).getWidth(), iHFloor, false, true);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY + iHCeil, ImageManager.getImage(imageID).getWidth(), iHFloor, true, true);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f * fAlpha));
        ImageManager.getImage(Images.noise).draw2(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.HOVER_LINE2.r, Colors.HOVER_LINE2.g, Colors.HOVER_LINE2.b, Colors.HOVER_LINE2.a * fAlpha));
        Images.gradientFull.draw(oSB, nPosX + 1, nPosY + 1, nWidth - 2, 1);
        Images.gradientFull.draw(oSB, nPosX + 1, nPosY + nHeight - 2, nWidth - 2, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawBox_EDGE_TOP_LR(final SpriteBatch oSB, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true);
    }
    
    public static final void drawBox_EDGE_TOP_LR(final SpriteBatch oSB, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final boolean flipY) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight, false, flipY);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true, flipY);
    }
    
    public static final void drawBox_EDGE_LorR(final SpriteBatch oSB, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final boolean flipX, final boolean flipY) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth, nHeight, flipX, flipY);
    }
    
    public static final int getDarker(final int iColor, final int iMod) {
        return Math.round((float)Math.max(0, iColor - iMod));
    }
    
    public static final Color getDarker(final Color nColor, final int iMod, final float nAlpha) {
        return new Color((float)Math.round(Math.max(0.0f, nColor.r * 255.0f - iMod) / 255.0f), (float)Math.round(Math.max(0.0f, nColor.g * 255.0f - iMod) / 255.0f), (float)Math.round(Math.max(0.0f, nColor.b * 255.0f - iMod) / 255.0f), nAlpha);
    }
    
    public static final float getColorStep(final int iOld, final int iNew, final int iColorStepID, final int numOfSteps) {
        return (iOld + ((iOld > iNew) ? ((iNew - iOld) * iColorStepID / (float)numOfSteps) : ((iNew - iOld) * iColorStepID / (float)numOfSteps))) / 255.0f;
    }
    
    public static final Color getColorStep(final Color iOld, final Color iNew, final int iColorStepID, final int numOfSteps, final float fAlpha) {
        return new Color(iOld.r + ((iOld.r > iNew.r) ? ((iNew.r - iOld.r) * iColorStepID / numOfSteps) : ((iNew.r - iOld.r) * iColorStepID / numOfSteps)), iOld.g + ((iOld.g > iNew.g) ? ((iNew.g - iOld.g) * iColorStepID / numOfSteps) : ((iNew.g - iOld.g) * iColorStepID / numOfSteps)), iOld.b + ((iOld.b > iNew.b) ? ((iNew.b - iOld.b) * iColorStepID / numOfSteps) : ((iNew.b - iOld.b) * iColorStepID / numOfSteps)), fAlpha);
    }
    
    public static final Color getColorStep_WithAlpha(final Color iOld, final Color iNew, final int iColorStepID, final int numOfSteps) {
        return new Color(iOld.r + ((iOld.r > iNew.r) ? ((iNew.r - iOld.r) * iColorStepID / numOfSteps) : ((iNew.r - iOld.r) * iColorStepID / numOfSteps)), iOld.g + ((iOld.g > iNew.g) ? ((iNew.g - iOld.g) * iColorStepID / numOfSteps) : ((iNew.g - iOld.g) * iColorStepID / numOfSteps)), iOld.b + ((iOld.b > iNew.b) ? ((iNew.b - iOld.b) * iColorStepID / numOfSteps) : ((iNew.b - iOld.b) * iColorStepID / numOfSteps)), iOld.a + ((iOld.a > iNew.a) ? ((iNew.a - iOld.a) * iColorStepID / numOfSteps) : ((iNew.a - iOld.a) * iColorStepID / numOfSteps)));
    }
    
    public static final Color getColorMixed(final Color iOld, final Color iNew) {
        final float tA = 1.0f - (1.0f - iOld.a) * (1.0f - iNew.a);
        return new Color(iNew.r * iNew.a / tA + iOld.r * iOld.a * (1.0f - iNew.a) / tA, iNew.g * iNew.a / tA + iOld.g * iOld.a * (1.0f - iNew.a) / tA, iNew.b * iNew.a / tA + iOld.b * iOld.a * (1.0f - iNew.a) / tA, iOld.a);
    }
    
    public static final void clearFonts() {
        for (int i = 0; i < Renderer.fontMainSize; ++i) {
            Renderer.fontMain.get(i).dispose();
            Renderer.fontMain.set(i, null);
        }
        Renderer.fontMain.clear();
        Renderer.fontMainSize = 0;
    }
    
    public static final void loadFont(final String sFont, final String charset, int fontSize) {
        FreeTypeFontGenerator generator = null;
        if (fontSize < 0) {
            fontSize = (int)(GameValues.value.DEFAULT_FONT_SIZE * CFG.GUI_SCALE);
        }
        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        }
        catch (final Exception ex) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }
        final FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = charset;
        params.size = Math.max(fontSize, 6);
        params.color = Color.WHITE;
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        Renderer.fontMain.add(generator.generateFont(params));
        Renderer.fontMainSize = Renderer.fontMain.size();
        generator.dispose();
    }
    
    public static final void loadFontArmy_GlyphLayout(final String sFont, final String charset, int fontSize) {
        FreeTypeFontGenerator generator = null;
        if (fontSize < 0) {
            fontSize = (int)(GameValues.value.DEFAULT_FONT_SIZE * CFG.GUI_SCALE);
        }
        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        }
        catch (final Exception ex) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }
        final FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = charset;
        params.size = Math.max(fontSize, 6);
        params.color = Color.WHITE;
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        Renderer.fontArmy_GlyphLayout = generator.generateFont(params);
        generator.dispose();
    }
    
    public static final synchronized void loadFont_UpdateTextHeight() {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(0), "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890");
        CFG.TEXT_HEIGHT = (int)glyphLayout.height;
        ProvinceDrawArmy.updateArmyHeight();
    }
    
    public static final synchronized void loadFont_UpdateTextHeightSmall() {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890");
        CFG.TEXT_HEIGHT_SMALL = (int)glyphLayout.height;
    }
    
    public static final void loadFontBorder(final String sFont, final String charset) {
        FreeTypeFontGenerator generator = null;
        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        }
        catch (final Exception ex) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }
        final FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = charset;
        params.size = Game.settingsManager.FONT_BORDER_SIZE;
        params.color = new Color(Game.settingsManager.civNamesFontColor_R, Game.settingsManager.civNamesFontColor_G, Game.settingsManager.civNamesFontColor_B, Game.settingsManager.civNamesFontColor_A);
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        params.kerning = false;
        params.borderColor = new Color(Game.settingsManager.civNamesFontColorBorder_R, Game.settingsManager.civNamesFontColorBorder_G, Game.settingsManager.civNamesFontColorBorder_B, Game.settingsManager.civNamesFontColorBorder_A);
        params.borderWidth = (float)Game.settingsManager.FONT_BORDER_WIDTH_OF_BORDER;
        Renderer.fontBorder.add(generator.generateFont(params));
        Renderer.fontBorderSize = Renderer.fontBorder.size();
        Renderer.fontBorder.get(0).setFixedWidthGlyphs((CharSequence)charset);
        generator.dispose();
    }
    
    public static final void loadFontBorder2(final String sFont, final String charset) {
    }
    
    public static final void drawText_CacheBegin(final int fontID, final Color color) {
        Renderer.fontMain.get(fontID).getCache().clear();
        Renderer.fontMain.get(fontID).setColor(color);
    }
    
    public static final void drawText_Cache(final int fontID, final String sText, final int nPosX, final int nPosY) {
        try {
            if (sText != null) {
                Renderer.fontMain.get(fontID).getCache().addText((CharSequence)sText, (float)nPosX, (float)(-nPosY));
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final void drawText_CacheEnd(final SpriteBatch oSB, final int fontID) {
        Renderer.fontMain.get(fontID).getCache().draw((Batch)oSB);
        Renderer.fontMain.get(fontID).getCache().clear();
    }
    
    public static final void drawText(final SpriteBatch oSB, final String sText, final int nPosX, final int nPosY, final Color color) {
        drawText(oSB, 0, sText, nPosX, nPosY, color);
    }
    
    public static final void drawText(final SpriteBatch oSB, final int fontID, final String sText, final int nPosX, final int nPosY, final Color color) {
        try {
            if (sText != null) {
                Renderer.fontMain.get(fontID).setColor(color);
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, (float)nPosX, (float)(-nPosY));
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final void drawText(final SpriteBatch oSB, final String sText, final float fontScale, final int nPosX, final int nPosY, final Color color) {
        drawText(oSB, 0, sText, fontScale, nPosX, nPosY, color);
    }
    
    public static final void drawText(final SpriteBatch oSB, final int fontID, final String sText, final float fontScale, final int nPosX, final int nPosY, final Color color) {
        try {
            if (sText != null) {
                setFontScale(fontScale);
                Renderer.fontMain.get(fontID).setColor(color);
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, (float)nPosX, (float)(-nPosY));
                resetFontScale();
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final void drawTextWithShadow(final SpriteBatch oSB, final String sText, final int nPosX, final int nPosY, final Color color) {
        drawTextWithShadow(oSB, 0, sText, nPosX, nPosY, color);
    }
    
    public static final void drawTextWithShadow(final SpriteBatch oSB, final int fontID, final String sText, final int nPosX, final int nPosY, final Color color) {
        try {
            if (sText != null) {
                Renderer.fontMain.get(fontID).setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, (float)(nPosX - 1), (float)(-nPosY - 1));
                Renderer.fontMain.get(fontID).setColor(color);
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, (float)nPosX, (float)(-nPosY));
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final void drawTextWithShadowScale(final SpriteBatch oSB, final int fontID, final String sText, final int nPosX, final int nPosY, final Color color, final float fScale) {
        try {
            if (sText != null) {
                Renderer.fontMain.get(fontID).getData().setScale(fScale);
                Renderer.fontMain.get(fontID).setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, (float)(nPosX - 1), (float)(-nPosY - 1));
                Renderer.fontMain.get(fontID).setColor(color);
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, (float)nPosX, (float)(-nPosY));
                Renderer.fontMain.get(fontID).getData().setScale(1.0f);
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final void drawTextWithShadowRotated(final SpriteBatch oSB, final String sText, final int nPosX, final int nPosY, final Color color, final float rotate) {
        drawTextWithShadowRotated(oSB, 0, sText, nPosX, nPosY, color, rotate);
    }
    
    public static final void drawTextWithShadowRotated(final SpriteBatch oSB, final int fontID, final String sText, final int nPosX, final int nPosY, final Color color, final float rotate) {
        if (sText != null) {
            final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
            try {
                final Matrix4 mx4Font = new Matrix4();
                mx4Font.rotate(Renderer.textRotatedVector3, rotate);
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0f);
                oSB.setTransformMatrix(mx4Font);
                Renderer.fontMain.get(fontID).setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, -1.0f, -1.0f);
                Renderer.fontMain.get(fontID).setColor(color);
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, 0.0f, 0.0f);
            }
            catch (final Exception ex) {}
            finally {
                oSB.setTransformMatrix(oldTransformMatrix);
            }
        }
    }
    
    public static final void drawTextRotated(final SpriteBatch oSB, final String sText, final int nPosX, final int nPosY, final Color color, final float rotate) {
        drawTextRotated(oSB, 0, sText, nPosX, nPosY, color, rotate);
    }
    
    public static final void drawTextRotated(final SpriteBatch oSB, final int fontID, final String sText, final int nPosX, final int nPosY, final Color color, final float rotate) {
        if (sText != null) {
            final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
            try {
                final Matrix4 mx4Font = new Matrix4();
                mx4Font.rotate(new Vector3(0.0f, 0.0f, 1.0f), rotate);
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0f);
                oSB.setTransformMatrix(mx4Font);
                Renderer.fontMain.get(fontID).setColor(color);
                Renderer.fontMain.get(fontID).draw((Batch)oSB, (CharSequence)sText, 0.0f, 0.0f);
            }
            catch (final Exception ex) {}
            finally {
                oSB.setTransformMatrix(oldTransformMatrix);
            }
        }
    }
    
    public static final synchronized void drawTextRotatedBorder(final SpriteBatch oSB, final int nFontID, final String sText, final int nPosX, final int nPosY, final Color color, final float rotate) {
        if (sText != null) {
            final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
            try {
                final Matrix4 mx4Font = new Matrix4();
                mx4Font.rotate(Renderer.textRotatedVector3, rotate);
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0f);
                oSB.setTransformMatrix(mx4Font);
                Renderer.fontBorder.get(nFontID).setColor(color);
                Renderer.fontBorder.get(nFontID).draw((Batch)oSB, (CharSequence)sText, 0.0f, 0.0f);
            }
            catch (final Exception ex) {}
            finally {
                oSB.setTransformMatrix(oldTransformMatrix);
            }
        }
    }
    
    public static final synchronized void drawTextRotatedBorder_2(final SpriteBatch oSB, final int nFontID, final String sText, final int nPosX, final int nPosY, final Color color, final float rotate) {
        try {
            if (sText != null) {
                try {
                    final Matrix4 mx4Font = new Matrix4();
                    mx4Font.rotate(Renderer.textRotatedVector3, rotate);
                    mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0f);
                    oSB.setTransformMatrix(mx4Font);
                    Renderer.fontBorder.get(nFontID).setColor(color);
                    Renderer.fontBorder.get(nFontID).draw((Batch)oSB, (CharSequence)sText, 0.0f, 0.0f);
                }
                catch (final Exception ex) {}
            }
        }
        catch (final Exception ex2) {}
    }
    
    public static final synchronized void drawTextRotatedBorder_2(final SpriteBatch oSB, final int nFontID, final String sText, final int nPosX, final int nPosY, final float rotate) {
        try {
            if (sText != null) {
                try {
                    final Matrix4 mx4Font = new Matrix4();
                    mx4Font.rotate(Renderer.textRotatedVector3, rotate);
                    mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0f);
                    oSB.setTransformMatrix(mx4Font);
                    Renderer.fontBorder.get(nFontID).draw((Batch)oSB, (CharSequence)sText, 0.0f, 0.0f);
                }
                catch (final Exception ex) {}
            }
        }
        catch (final Exception ex2) {}
    }
    
    public static final synchronized void drawTextRotatedBorder(final SpriteBatch oSB, final int nFontID, final String sText, final int nPosX, final int nPosY, final Matrix4 mx4Font) {
        try {
            if (sText != null) {
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0f);
                oSB.setTransformMatrix(mx4Font);
                Renderer.fontBorder.get(nFontID).draw((Batch)oSB, (CharSequence)sText, 0.0f, 0.0f);
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final synchronized void drawTextBorder(final SpriteBatch oSB, final int nFontID, final String sText, final int nPosX, final int nPosY) {
        final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        try {
            if (sText != null) {
                final Matrix4 mx4Font = new Matrix4();
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0f);
                oSB.setTransformMatrix(mx4Font);
                Renderer.fontBorder.get(nFontID).draw((Batch)oSB, (CharSequence)sText, 0.0f, 0.0f);
            }
        }
        catch (final Exception ex) {}
        finally {
            oSB.setTransformMatrix(oldTransformMatrix);
        }
    }
    
    public static final int getTextWidth(final String sText) {
        return getTextWidth(sText, 0);
    }
    
    public static final synchronized int getTextWidth(final String sText, final int fontID) {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(fontID), sText);
        return (int)glyphLayout.width;
    }
    
    public static final int getTextHeight(final String sText) {
        return getTextHeight(sText, 0);
    }
    
    public static final synchronized int getTextHeight(final String sText, final int fontID) {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(fontID), sText);
        return (int)glyphLayout.height;
    }
    
    public static final XY getText_WidthHeight(final String sText) {
        return getText_WidthHeight(sText, 0);
    }
    
    public static final synchronized XY getText_WidthHeight(final String sText, final int fontID) {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(fontID), sText);
        return new XY((int)glyphLayout.width, (int)glyphLayout.height);
    }
    
    public static final XY getText_WidthHeight(final String sText, final float fFontScale) {
        return getText_WidthHeight(sText, 0, fFontScale);
    }
    
    public static final synchronized XY getText_WidthHeight(final String sText, final int fontID, final float fFontScale) {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(fontID), sText);
        return new XY((int)(glyphLayout.width * fFontScale), (int)(glyphLayout.height * fFontScale));
    }
    
    public static final void setFontScale(final float fontScale) {
        for (int i = 0; i < Renderer.fontMainSize; ++i) {
            Renderer.fontMain.get(i).getData().setScale(fontScale);
        }
    }
    
    public static final void resetFontScale() {
        for (int i = 0; i < Renderer.fontMainSize; ++i) {
            Renderer.fontMain.get(i).getData().setScale(1.0f);
        }
    }
    
    public final void resetFontMainScale() {
        this.setFontMainScale(1.0f);
    }
    
    public final void setFontMainScale(final float fontScale) {
        this.setFontMainScale(fontScale, 0);
    }
    
    public final void setFontMainScale(final float fontScale, final int fontID) {
        Renderer.fontMain.get(fontID).getData().setScale(fontScale);
    }
    
    public void resize(final int width, final int height) {
        Renderer.viewport.update(width, height, false);
    }
    
    public void dispose() {
        this.oSB.dispose();
    }
    
    public static final void drawLoading(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final float nProgress) {
        final int nPosX = iTranslateX;
        final int nPosY = iTranslateY;
        final int nHeight = ImageManager.getImage(Images.logo).getHeight() + CFG.BUTTON_HEIGHT * 2;
        if (CFG.currentTimeMillis - 4000L > Renderer.loadingTime) {
            try {
                Renderer.sLoadingText = Game.lang.getLoading("L" + Game.oR.nextInt(Game.lang.iLoading_NumOfTexts)) + "..";
                Renderer.loadingTime = CFG.currentTimeMillis;
                final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), Renderer.sLoadingText);
                Renderer.iLoadingTextWidth = (int)glyphLayout.width;
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11, CFG.GAME_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        Images.gradientXY.draw(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - (Renderer.iLoadingTextWidth + CFG.PADDING * 6) / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11, Renderer.iLoadingTextWidth + CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11 + 1, CFG.GAME_WIDTH, 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11 + CFG.TEXT_HEIGHT + CFG.PADDING * 6 - 2, CFG.GAME_WIDTH, 1);
        drawText(oSB, CFG.FONT_REGULAR, Renderer.sLoadingText, iTranslateX + CFG.GAME_WIDTH / 2 - Renderer.iLoadingTextWidth / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 8, new Color(Colors.COLOR_LOGO.r, Colors.COLOR_LOGO.g, Colors.COLOR_LOGO.b, 0.75f));
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
        ImageManager.getImage(Images.logo).draw(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.logo).getWidth() / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 14 - ImageManager.getImage(Images.logo).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.logo).draw2(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.logo).getWidth() / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 14 - ImageManager.getImage(Images.logo).getHeight(), Math.min(ImageManager.getImage(Images.logo).getWidth(), (int)(ImageManager.getImage(Images.logo).getWidth() * nProgress)), ImageManager.getImage(Images.logo).getHeight());
    }
    
    public static final void drawEditorMenuBG(final SpriteBatch oSB, final int iX, final int iY, final int iWidth, final int iHeight, final int iTranslateX, final int iTranslateY) {
        drawBox_EDGE_TOP_LR(oSB, Images.mainBox, iX + iTranslateX, iY + iTranslateY, iWidth, iHeight, true);
    }
    
    static {
        Renderer.drawArmyInProvince = true;
        Renderer.numOfScissors = 0;
        Renderer.peekBounds = null;
        Renderer.BACKGROUND_COLOR = new Color(0.0509f, 0.1215f, 0.1921f, 1.0f);
        Renderer.updateBackgroundColor = true;
        Renderer.simpleTasksCivNames = new CopyOnWriteArrayList<Game.SimpleTask>();
        Renderer.simpleTasks_ArmyWidth = Collections.synchronizedList(new ArrayList<SimpleTaskArmyText>());
        Renderer.boxBGExtraY = CFG.PADDING;
        Renderer.iBoxCornerX = 0;
        Renderer.BOX_CORNER_TIME = 0L;
        Renderer.BOX_CORNER_TIMER = 56L;
        Renderer.glyphLayout = new GlyphLayout_Game();
        Renderer.charset = "";
        Renderer.fontMain = new ArrayList<BitmapFont>();
        Renderer.fontMainSize = 0;
        Renderer.fontBorder = new ArrayList<BitmapFont>();
        Renderer.fontBorderSize = 0;
        textRotatedVector3 = new Vector3(0.0f, 0.0f, 1.0f);
        Renderer.sLoadingText = "";
        Renderer.iLoadingTextWidth = 0;
        Renderer.loadingTime = 0L;
    }
    
    public static class SimpleTaskArmyText
    {
        public String taskKey;
        public ArmyDivision armyDivision;
        public boolean updateShift;
        
        public SimpleTaskArmyText(final String taskKey, final ArmyDivision armyDivision, final boolean updateShift) {
            this.taskKey = taskKey;
            this.armyDivision = armyDivision;
            this.updateShift = updateShift;
        }
        
        public void update() {
            this.armyDivision.updateArmyWidth_Just(this.updateShift);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof SimpleTaskArmyText)) {
                return false;
            }
            final SimpleTaskArmyText that = (SimpleTaskArmyText)o;
            return Objects.equals(this.taskKey, that.taskKey);
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
    }
}
