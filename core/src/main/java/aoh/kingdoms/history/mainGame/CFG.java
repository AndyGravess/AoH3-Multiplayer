// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import java.text.Normalizer;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import com.badlogic.gdx.Application;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.text.DecimalFormat;
import com.badlogic.gdx.graphics.Color;

public class CFG
{
    public static long currentTimeMillis;
    public static final boolean LOGS = true;
    public static boolean debugMode;
    public static final String AGE_OF_HISTORY = "Age of History III";
    public static final String WWW_WIKI = "https://en.wikipedia.org/wiki/";
    public static final String WWW_LUKASZJAKOWSKI = "http://lukaszjakowski.pl";
    public static final String FILE_GAME_LIST = "AoH.json";
    public static final String FILE_GAME_LIST_TXT = "AoH.txt";
    public static final String FILE_MAPS_LIST = "Maps.json";
    public static final String FILE_CONFIG = "Config.json";
    public static final String FILE_CONFIG_LOW = "ConfigLow.json";
    public static final String FILE_TEXTURES_HIGH = "LoadTexturesHigh.txt";
    public static final String FILE_MAP_ICON = "icon.png";
    public static final String FILE_GAME_CFG_FILE = "Age_of_History.txt";
    public static final String FILE_MODS_PATH = "mods/";
    public static final String FILE_MODS_CIVS_PATH = "GameCivs/";
    public static final String FILE_GAME_PATH = "game/";
    public static final String FILE_GAME_CIVILIZATIONS_PATH = "civilizations/";
    public static final String FILE_GAME_CIVILIZATIONS_FILE = "Civilizations.txt";
    public static final String FILE_GAME_CHARACTERS_PATH = "characters/";
    public static final String FILE_GAME_VALUES_PATH = "gameValues/";
    public static final String FILE_AI_VALUES_PATH = "AI/";
    public static final String FILE_AI_VALUES = "AI_Values.json";
    public static final String FILE_AI_VALUES_ARMIES = "AI_Armies.json";
    public static final String FILE_AI_VALUES_BUILD = "AI_Build.json";
    public static final String FILE_AI_VALUES_CONVERT = "AI_ConvertReligion.json";
    public static final String FILE_AI_VALUES_CORES = "AI_Cores.json";
    public static final String FILE_AI_VALUES_DIPLOMACY = "AI_Diplomacy.json";
    public static final String FILE_GAME_VALUES = "GameValues.json";
    public static final String FILE_GAME_VALUES_TEXT = "GameValues_Text.json";
    public static final String FILE_GAME_VALUES_DIPLOMACY = "GV_Diplomacy.json";
    public static final String FILE_GAME_VALUES_HRE = "GV_HRE.json";
    public static final String FILE_GAME_VALUES_BATTLE_TACTICS = "GV_BattleTactics.json";
    public static final String FILE_GAME_VALUES_COURT = "GV_Court.json";
    public static final String FILE_GAME_VALUES_GOLDEN_AGES = "GV_GoldenAges.json";
    public static final String FILE_GAME_VALUES_MANPOWER = "GV_Manpower.json";
    public static final String FILE_GAME_VALUES_GENERALS = "GV_Generals.json";
    public static final String FILE_GAME_VALUES_ADVISORS = "GV_Advisors.json";
    public static final String FILE_GAME_VALUES_ATOMIC = "GV_Atomic.json";
    public static final String FILE_GAME_VALUES_INFRASTRUCTURE = "GV_Infrastructure.json";
    public static final String FILE_GAME_VALUES_PEACE = "GV_Peace.json";
    public static final String FILE_GAME_VALUES_TAX_EFFICIENCY = "GV_TaxEfficiency.json";
    public static final String FILE_GAME_VALUES_SPY = "GV_Spy.json";
    public static final String FILE_GAME_VALUES_BUDGET = "GV_Budget.json";
    public static final String FILE_GAME_VALUES_MILITARY_ACADEMY = "GV_MilitaryAcademy.json";
    public static final String FILE_GAME_VALUES_ECONOMY = "GV_Economy.json";
    public static final String FILE_GAME_VALUES_GROWTH_RATE = "GV_GrowthRate.json";
    public static final String FILE_GAME_VALUES_PRODUCTION = "GV_Production.json";
    public static final String FILE_GAME_VALUES_PROSPERITY = "GV_Prosperity.json";
    public static final String FILE_GAME_VALUES_CAPITAL = "GV_Capital.json";
    public static final String FILE_GAME_VALUES_CIV_RANK = "GV_CivRank.json";
    public static final String FILE_GAME_VALUES_BUILDINGS = "GV_Buildings.json";
    public static final String FILE_GAME_VALUES_BATTLE = "GV_Battle.json";
    public static final String FILE_GAME_VALUES_SIEGE = "GV_Siege.json";
    public static final String FILE_GAME_VALUES_COLONIZATION = "GV_Colonization.json";
    public static final String FILE_GAME_VALUES_SUPREME_COURT = "GV_SupremeCourt.json";
    public static final String FILE_GAME_VALUES_LOAN = "GV_Loan.json";
    public static final String FILE_GAME_VALUES_WAR = "GV_War.json";
    public static final String FILE_GAME_VALUES_VASSAL = "GV_Vassals.json";
    public static final String FILE_GAME_VALUES_ARMY = "GV_Army.json";
    public static final String FILE_GAME_VALUES_CORE = "GV_Core.json";
    public static final String FILE_GAME_VALUES_RELIGION = "GV_Religion.json";
    public static final String FILE_GAME_VALUES_PROVINCE = "GV_Province.json";
    public static final String FILE_GAME_VALUES_RESEARCH = "GV_Research.json";
    public static final String FILE_GAME_VALUES_LAWS = "GV_Laws.json";
    public static final String FILE_GAME_VALUES_PLAGUES = "GV_Plagues.json";
    public static final String FILE_GAME_VALUES_LEGACY = "GV_Legacy.json";
    public static final String FILE_GAME_VALUES_GOVERNMENT = "GV_Government.json";
    public static final String FILE_GAME_VALUES_INFLATION = "GV_Inflation.json";
    public static final String FILE_GAME_VALUES_CLICK_ANIMATION = "GV_ClickAnimation.json";
    public static final String FILE_GAME_VALUES_SHIPS = "GV_Ships.json";
    public static final String FILE_GAME_VALUES_CONSOLE = "GV_Console.json";
    public static final String FILE_GAME_VALUES_RIVALS = "GV_Rivals.json";
    public static final String FILE_GAME_VALUES_AGGRESSIVE_EXPANSION = "GV_AggressiveExpansion.json";
    public static final String FILE_GAME_VALUES_WAR_WEARINESS = "GV_WarWeariness.json";
    public static final String FILE_GAME_VALUES_DIFFICULTY = "GV_Difficulty.json";
    public static final String FILE_GAME_VALUES_CIV_STABILITY = "GV_CivStability.json";
    public static final String FILE_GAME_VALUES_MAP_MODES = "GV_MapModes.json";
    public static final String FILE_GAME_VALUES_ADVANTAGES = "GV_Advantages.json";
    public static final String FILE_GAME_VALUES_SANDBOX = "GV_Sandbox.json";
    public static final String FILE_GAME_VALUES_REBELS = "GV_Rebels.json";
    public static final String FILE_GAME_VALUES_NOTIFICATIONS = "GV_Notifications.json";
    public static final String FILE_GAME_VALUES_GAME_SPEED = "GV_GameSpeed.json";
    public static final String FILE_GAME_VALUES_EVENTS = "GV_Events.json";
    public static final String FILE_GAME_VALUES_FOG_OF_WAR = "GV_FogOfWar.json";
    public static final String FILE_GAME_VALUES_GAME_UPDATE = "GV_GameUpdate.json";
    public static final String FILE_GAME_VALUES_GAME_UPDATE_AI = "GV_GameUpdateAI.json";
    public static final String FILE_GAME_VALUES_PROVINCE_BORDER_WAR = "GV_ProvinceBorder.json";
    public static final String FILE_GAME_VALUES_HOVER = "GV_Hover.json";
    public static final String FILE_GAME_VALUES_LUCKY_CIVS = "GV_LuckyCivs.json";
    public static final String FILE_GAME_VALUES_INGAME = "GV_InGame.json";
    public static final String FILE_GAME_VALUES_ZOOM = "GV_Zoom.json";
    public static final String FILE_GAME_VALUES_INFO = "GV_Info.json";
    public static final String FILE_GAME_VALUES_NEWGAME = "GV_NewGame.json";
    public static final String FILE_GAME_VALUES_MOVE = "GV_Move.json";
    public static final String FILE_GAME_VALUES_LOGS = "GV_Logs.json";
    public static final String FILE_GAME_SETTINGS = "settings/";
    public static final String FILE_GAME_SETTINGS_PROVINCE_BORDER_LOW = "ProvinceBorder_Low.json";
    public static final String FILE_GAME_SETTINGS_PROVINCE_BORDER_MEDIUM = "ProvinceBorder_Medium.json";
    public static final String FILE_GAME_SETTINGS_PROVINCE_BORDER_HIGH = "ProvinceBorder_High.json";
    public static final String FILE_GAME_SETTINGS_PROVINCE_BORDER_VERY_HIGH = "ProvinceBorder_VeryHigh.json";
    public static final String FILE_AGES_LIST = "Ages.json";
    public static final String FILE_TERRAIN_PATH = "terrain/";
    public static final String FILE_TERRAIN_IMAGES_PATH = "terrainImages/";
    public static final String FILE_TERRAIN_LIST = "TerrainTypes.json";
    public static final String FILE_IDEOLOGIES_LIST = "Governments.json";
    public static final String FILE_RELIGIONS_LIST = "Religions.json";
    public static final String FILE_FONTS_PATH = "game/fonts/";
    public static final String FILE_LANGUAGES_LIST = "languages/Languages.txt";
    public static final String FILE_LANGUAGES_PATH = "game/languages/Bundle";
    public static final String FILE_LANGUAGES_CIVS_PATH = "game/languages/civilizations/Bundle";
    public static final String FILE_LANGUAGES_MOD_PATH = "languages/Bundle";
    public static final String FILE_LANGUAGES_LOADING_PATH = "game/languages/loading/Bundle";
    public static final String FILE_GAME_UNITS_PATH = "units/";
    public static final String FILE_GAME_UNITS_LIST = "Units.json";
    public static final String FILE_GAME_UNITS_IMAGES_PATH = "unitsImages/";
    public static final String FILE_GAME_BUILDINGS_PATH = "buildings/";
    public static final String FILE_GAME_BUILDINGS_LIST = "Buildings.json";
    public static final String FILE_GAME_BUILDINGS_RESOURCES_LIST = "BuildingsResources.json";
    public static final String FILE_GAME_BUILDINGS_IMAGES_PATH = "buildingsImages/";
    public static final String FILE_GAME_EVENTS_PATH = "events/";
    public static final String FILE_GAME_EVENTS_SIEGE_PATH = "siege/";
    public static final String FILE_GAME_EVENTS_COMMON_PATH = "common/";
    public static final String FILE_GAME_EVENTS_GLOBAL_PATH = "global/";
    public static final String FILE_GAME_EVENTS_IMAGES_PATH = "images/";
    public static final String FILE_GAME_MISSION_IMAGES_PATH = "imagesMissions/";
    public static final String FILE_GAME_EVENTS_SOUND_PATH = "sound/";
    public static final String FILE_GAME_EVENTS_GENERATE_LIST = "generate_list.txt";
    public static final String FILE_GAME_EVENTS_LIST_COMMON = "list_common.txt";
    public static final String FILE_GAME_EVENTS_LIST_SIEGE = "list_siege.txt";
    public static final String FILE_GAME_EVENTS_LIST_GLOBAL = "list_global.txt";
    public static final String FILE_GAME_DISEASES_PATH = "diseases/";
    public static final String FILE_GAME_DISEASES_IMAGES_PATH = "images/";
    public static final String FILE_GAME_DISEASES_LIST = "Diseases.json";
    public static final String FILE_GAME_RULERS_PATH = "rulers/";
    public static final String FILE_GAME_RULERS_RANDOM_PATH = "rulersRandom/";
    public static final String FILE_GAME_RULERS_LINK_PATH = "link/";
    public static final String FILE_GAME_RULERS_IMAGES_PATH = "rulersImages/";
    public static final String FILE_GAME_RULERS_2_IMAGES_PATH = "rulersImages2/";
    public static final String FILE_GAME_GENERALS_RANDOM_PATH = "randomNames/";
    public static final String FILE_GAME_GENERALS_RANDOM_NAMES_PATH = "names/";
    public static final String FILE_GAME_GENERALS_RANDOM_SURNAMES_PATH = "surnames/";
    public static final String FILE_GAME_WONDERS_PATH = "wonders/";
    public static final String FILE_GAME_WONDERS_LIST = "Wonders.json";
    public static final String FILE_GAME_WONDERS_IMAGES_PATH = "wondersImages/";
    public static final String FILE_GAME_WONDERS_IMAGES_BIG_PATH = "wondersImagesBig/";
    public static final String FILE_GAME_RESOURCES_PATH = "resources/";
    public static final String FILE_GAME_RESOURCES_LIST = "Resources.json";
    public static final String FILE_GAME_RESOURCES_IMAGES_PATH = "resourcesImages/";
    public static final String FILE_GAME_LEGACY_PATH = "legacies/";
    public static final String FILE_GAME_LEGACY_LIST = "Legacies.json";
    public static final String FILE_GAME_LEGACY_GROUPS = "LegaciesGroups.txt";
    public static final String FILE_GAME_LEGACY_IMAGES_PATH = "legaciesImages/";
    public static final String FILE_GAME_ADVANTAGES_PATH = "advantages/";
    public static final String FILE_GAME_ADVANTAGES_LIST = "Advantages.json";
    public static final String FILE_GAME_ADVANTAGES_GROUPS = "AdvantagesGroups.txt";
    public static final String FILE_GAME_ADVANTAGES_IMAGES_PATH = "advantagesImages/";
    public static final String FILE_GAME_LAWS_PATH = "laws/";
    public static final String FILE_GAME_LAWS_LIST = "Laws.json";
    public static final String FILE_GAME_LAWS_IMAGES_PATH = "lawsImages/";
    public static final String FILE_GAME_TECHNOLOGY_PATH = "technologies/";
    public static final String FILE_GAME_TECHNOLOGY_LIST = "Technologies.json";
    public static final String FILE_GAME_TECHNOLOGY_IMAGES_PATH = "technologiesImages/";
    public static final String FILE_GAME_MISSIONS_PATH = "missions/";
    public static final String FILE_GAME_MISSIONS_EVENTS_PATH = "missionsEvents/";
    public static final String FILE_GAME_MISSIONS_LIST = "Missions.json";
    public static final String FILE_GAME_MISSIONS_IMAGES_PATH = "missionsImages/";
    public static final String FILE_GAME_RANDOM_PATH = "random/";
    public static final String FILE_GAME_GROUPS_PATH = "groups/";
    public static final String FILE_GAME_CITIES_PATH = "cities/";
    public static final String FILE_SELECTED_PROVINCES = "selectedProvinces.txt";
    public static final String FILE_MAP_PATH = "map/";
    public static final String FILE_MAP_BACKGROUND_PATH = "background/main/";
    public static final String FILE_MAP_BACKGROUND2_PATH = "background/zoomOut/";
    public static final String FILE_MAP_BACKGROUND_MINIMAP_PATH = "background/minimap/";
    public static final String FILE_MAP_OVERLAYS_PATH = "background/overlays/";
    public static final String FILE_MAP_OVERLAYS_LOW_PATH = "low/";
    public static final String FILE_MAP_OVERLAYS_HIGH_PATH = "high/";
    public static final String FILE_MAP_OVERLAYS_FILE = "Overlays.json";
    public static final String FILE_MAP_OVERLAYS_SEA_FILE = "OverlaysSea.json";
    public static final String FILE_MAP_CONTINENTS = "Continents.json";
    public static final String FILE_MAP_GEO_REGIONS = "GeographicalRegions.json";
    public static final String FILE_MAP_DATA = "data/";
    public static final String FILE_FORMABLE_CIVS_DATA = "formableCivs/";
    public static final String FILE_MAP_SCALES_BG = "scales/";
    public static final String FILE_MAP_AVAILABLE_SCALES = "AvailableScales.txt";
    public static final String FILE_MAP_DEFINED_SCALES = "DefinedScales.json";
    public static final String FILE_MAP_DEFINED_SCALES_LOW = "DefinedScales_Low.json";
    public static final String FILE_MAP_SCENARIOS = "scenarios/";
    public static final String FILE_MAP_SCENARIOS_FILE = "Scenarios.txt";
    public static final String FILE_MAP_SUGGESTED_CIVS_PATH = "suggestedCivilizations/";
    public static final String FILE_MAP_SCENARIO_DETAILS_FILE = "Details.json";
    public static final String FILE_MAP_SCENARIO_DATA_FILE = "Data.json";
    public static final String FILE_MAP_SCENARIO_DATA_LANG_FILE = "Data_";
    public static final String FILE_MAP_SCENARIO_DATA_PROVINCES_FILE = "DataProvinces.json";
    public static final String FILE_MAP_SCENARIO_CORES_FILE = "Cores.json";
    public static final String FILE_MAP_SCENARIO_RELIGIONS_FILE = "Religions.json";
    public static final String FILE_MAP_SCENARIO_CHARACTERS_FILE = "Characters.json";
    public static final String FILE_MAP_SCENARIO_ARMIES_FILE = "Armies.json";
    public static final String FILE_MAP_SCENARIO_BUILDINGS_FILE = "Buildings.json";
    public static final String FILE_MAP_SCENARIO_CIVS_DESCRIPTIONS_PATH = "descriptions/";
    public static final String FILE_MAP_SCENARIO_EVENTS_PATH = "events/";
    public static final String FILE_MAP_SCENARIO_WASTELAND_FILE = "Wasteland.txt";
    public static final String FILE_MAP_SCENARIO_PREVIEW = "preview.png";
    public static final String FILE_MAP_SCENARIO_PREVIEW_MAIN = "previewSpecial.png";
    public static final String FILE_MAP_SCENARIO_DESC_FILE = "Desc.txt";
    public static final String FILE_MAP_SCENARIO_RELATIONS_FILE = "Relations.json";
    public static final String FILE_MAP_SCENARIO_ALLIANCES_FILE = "Alliances.json";
    public static final String FILE_MAP_SCENARIO_DEFENSIVE_FILE = "Defensive.json";
    public static final String FILE_MAP_SCENARIO_TRUCES_FILE = "Truces.json";
    public static final String FILE_MAP_SCENARIO_NON_AGGRESSION_FILE = "NonAggression.json";
    public static final String FILE_MAP_SCENARIO_MILITARY_ACCESS_FILE = "MilitaryAccess.json";
    public static final String FILE_MAP_SCENARIO_GUARANTEE_FILE = "Guarantee.json";
    public static final String FILE_MAP_SCENARIO_ALLIANCES_SPECIAL_FILE = "AlliancesSpecial.json";
    public static final String FILE_MAP_PROVINCES_IMG = "provinces/";
    public static final String FILE_PROVINCE_POINTS = "ProvincePoints/ProvincePoints";
    public static final String FILE_PROVINCE_POINTS_CROP = "ProvincePoints_Cut/ProvincePoints_Cut";
    public static final String FILE_PROVINCE_DETAILS = "ProvinceDetails.json";
    public static final String FILE_PROVINCE_NEIGHBORING = "ProvinceNeighboringProvinces/ProvinceNeighboringProvinces";
    public static final String FILE_PROVINCE_REGIONS = "ProvinceOptimizationRegions.txt";
    public static final String FILE_PROVINCE_NAME_POINTS = "ProvinceNamePoints.json";
    public static final String FILE_MAP_LINES_SEA = "Lines_Sea.txt";
    public static final String FILE_MAP_CITIES = "cities/";
    public static final String FILE_MAP_CITIES_0_JSON = "cities.json";
    public static final String FILE_MAP_CITIES_GENERATE = "build_cities.txt";
    public static final String FILE_SETTINGS_PATH = "settings/";
    public static final String FILE_CONFIG_FILE = "Config.txt";
    public static final String FILE_SETTINGS_FILE = "Settings.txt";
    public static final String FILE_MODS_TURNED_OFF_FILE = "ModsOff.txt";
    public static final String FILE_SAVES_PATH = "saves/";
    public static final String FILE_SAVES_AUTOSAVE = "Autosave";
    public static final String FILE_STATS_PATH = "statistics/";
    public static final String FILE_STATS_LIST_FILE = "AoH.txt";
    public static final String FILE_SAVE_DETAILS = "Details.json";
    public static final String FILE_SAVE_PROVINCES_DATA = "Provinces_Data.json";
    public static final String FILE_SAVE_PROVINCES_DATA2 = "Provinces_Data2.json";
    public static final String FILE_SAVE_PROVINCES_DATA3 = "Provinces_Data3.json";
    public static final String FILE_SAVE_PROVINCES_DATA4 = "Provinces_Data4.json";
    public static final String FILE_SAVE_PROVINCES_DATA5 = "Provinces_Data5.json";
    public static final String FILE_SAVE_PROVINCES_DATA6 = "Provinces_Data6.json";
    public static final String FILE_SAVE_PROVINCES_DATA7 = "Provinces_Data7.json";
    public static final String FILE_SAVE_PROVINCES_DATA8 = "Provinces_Data8.json";
    public static final String FILE_SAVE_PROVINCES_DATA9 = "Provinces_Data9.json";
    public static final String FILE_SAVE_PROVINCES_DATA10 = "Provinces_Data10.json";
    public static final String FILE_SAVE_PROVINCES_DATA_POPULATION = "Provinces_Population.json";
    public static final String FILE_SAVE_PROVINCES_DATA_BUILDINGS_MORE_FILES = "Provinces_Buildings_";
    public static final String FILE_SAVE_PROVINCES_DATA_BUILDINGS_CONSTRUCTION = "Provinces_BuildingsConstruction.json";
    public static final String FILE_SAVE_PROVINCES_DATA_INVEST = "Provinces_InvestEconomy.json";
    public static final String FILE_SAVE_PROVINCES_DATA_INVEST_TAX = "Provinces_InvestTax.json";
    public static final String FILE_SAVE_PROVINCES_DATA_INVEST_MANPOWER = "Provinces_InvestManpower.json";
    public static final String FILE_SAVE_PROVINCES_DATA_INVEST_GROWTH_RATE = "Provinces_InvestGrowth.json";
    public static final String FILE_SAVE_PROVINCES_DATA_INVEST_INFRASTRUCTURE = "Provinces_InvestInfrastructure.json";
    public static final String FILE_SAVE_PROVINCES_DATA_CORES_CONSTRUCTION = "Provinces_CoreCreation.json";
    public static final String FILE_SAVE_PROVINCES_DATA_RELIGION_CONVERSION = "Provinces_Conversion.json";
    public static final String FILE_SAVE_PROVINCES_DATA_WONDER_CONSTRUCTION = "Provinces_WonderConstruction.json";
    public static final String FILE_SAVE_PROVINCES_DATA_ARMIES_MORE_FILES = "Provinces_Armies_";
    public static final String FILE_SAVE_PROVINCES_DATA_PLAGUES = "Provinces_Plagues.json";
    public static final String FILE_SAVE_PLAYER_DATA = "PlayerData.json";
    public static final String FILE_SAVE_PLAYER_STATS = "PlayerStats.json";
    public static final String FILE_SAVE_PLAYER_STATS2 = "PlayerStats2.json";
    public static final String FILE_SAVE_PLAYER_STATS3 = "PlayerStats3.json";
    public static final String FILE_SAVE_CIVS_UNLOCKED_TECHNOLOGIES = "UnlockedTechnologies.json";
    public static final String FILE_SAVE_CIVS_RESEARCH_PROGRESS = "ResearchProgress.json";
    public static final String FILE_SAVE_CIVS_UNLOCKED_ADVANTAGES = "Advantages.json";
    public static final String FILE_SAVE_CIVS_LAWS = "Laws.json";
    public static final String FILE_SAVE_CIVS_RECRUIT_ARMY = "RecruitArmy.json";
    public static final String FILE_SAVE_CIVS_CREATE_NEW_ARMY = "RecruitArmyCreate.json";
    public static final String FILE_SAVE_CIVS_NUKES_PRODUCTION = "NukesProduction.json";
    public static final String FILE_SAVE_CIVS_RULERS = "Rulers.json";
    public static final String FILE_SAVE_CIVS_RULERS_BONUSES = "RulersBonuses.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_ADM = "AdvisorAdministration.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_ADM_BONUSES = "AdvisorAdministrationBonuses.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_ECONOMY = "AdvisorEconomy.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_ECONOMY_BONUSES = "AdvisorEconomyBonuses.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_INNOVATION = "AdvisorInnovation.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_INNOVATION_BONUSES = "AdvisorInnovationBonuses.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_MILITARY = "AdvisorMilitary.json";
    public static final String FILE_SAVE_CIVS_ADVISORS_MILITARY_BONUSES = "AdvisorMilitaryBonuses.json";
    public static final String FILE_SAVE_CIVS_GENERALS_NOT_ASSIGNED = "GeneralsNotAssigned.json";
    public static final String FILE_SAVE_CIVS_EVENTS_VARIABLES = "EventsVariables.json";
    public static final String FILE_SAVE_CIVS_EVENTS_VARIABLES_2 = "EventsVariables2.json";
    public static final String FILE_SAVE_CIVS_DATA = "Civs.json";
    public static final String FILE_SAVE_CIVS_DATA2 = "Civs2.json";
    public static final String FILE_SAVE_CIVS_DATA3 = "Civs3.json";
    public static final String FILE_SAVE_CIVS_DATA4 = "Civs4.json";
    public static final String FILE_SAVE_AI_MERGE = "AI_Merge.json";
    public static final String FILE_SAVE_AI_CREATE_NEW_ARMY = "AI_CreateNewArmy.json";
    public static final String FILE_SAVE_AI_DIPLOMACY = "AI_Diplomacy.json";
    public static final String FILE_SAVE_AI_BUDGET = "AI_Budget.json";
    public static final String FILE_SAVE_CIVS_EVENTS_DATA = "EventsData.json";
    public static final String FILE_SAVE_CIVS_EVENTS_DATA2 = "EventsData2.json";
    public static final String FILE_SAVE_CIVS_EVENTS_DATA3 = "EventsData3.json";
    public static final String FILE_SAVE_CIVS_BONUSES_TEMPORARY = "BonusesTemp.json";
    public static final String FILE_SAVE_CIVS_GOLDEN_AGE = "GoldenAge.json";
    public static final String FILE_SAVE_CIVS_LOANS = "Loans.json";
    public static final String FILE_SAVE_CIVS_LEGACIES = "Legacies.json";
    public static final String FILE_SAVE_CIVS_MOVE_UNITS = "MoveUnits.json";
    public static final String FILE_SAVE_REBELS_DATA = "Rebels.json";
    public static final String FILE_SAVE_REBELS_MOVE_UNITS = "RebelsMoveUnits.json";
    public static final String FILE_SAVE_MAP_BATTLES = "Battles.json";
    public static final String FILE_SAVE_MAP_ACTIVE_PLAGUES = "Plagues.json";
    public static final String FILE_SAVE_CIVS_RELATIONS = "Relations.json";
    public static final String FILE_SAVE_CIVS_RELATIONS_2 = "Relations2.json";
    public static final String FILE_SAVE_CIVS_ALLIANCES = "Alliances.json";
    public static final String FILE_SAVE_CIVS_GUARANTEE = "Guarantee.json";
    public static final String FILE_SAVE_CIVS_DEFENSIVE = "Defensive.json";
    public static final String FILE_SAVE_CIVS_TRUCES = "Trcues.json";
    public static final String FILE_SAVE_CIVS_NON_AGGRESSION = "NonAggression.json";
    public static final String FILE_SAVE_CIVS_MILITARY_ACCESS = "MilitaryAccess.json";
    public static final String FILE_SAVE_CIVS_ALLIANCE_SPECIAL = "AllianceSpecial.json";
    public static final String FILE_SAVE_CIVS_RIVALS = "Rivals.json";
    public static final String FILE_SAVE_CIVS_RELATIONS_IMPROVING = "RelationsImprove.json";
    public static final String FILE_SAVE_CIVS_RELATIONS_DAMAGING = "RelationsDamage.json";
    public static final String FILE_SAVE_CIVS_VASSALS = "Vassals.json";
    public static final String FILE_SAVE_WARS = "Wars.json";
    public static final String FILE_GFX_PATH = "gfx/";
    public static final String FILE_IMAGE_NOT_FOUND = "gfx/imageNotFound.png";
    public static final String FILE_GFX_CLOUDS_PATH = "clouds/";
    public static final String FILE_GFX_RELIGION_PATH = "religion/";
    public static final String FILE_GFX_GOVERNMENT_PATH = "government/";
    public static final String FILE_GFX_MAP_PATH = "map/";
    public static final String FILE_GFX_MAP_NUKE_PATH = "nuke/";
    public static final String FILE_GFX_MAP_SPARKS_PATH = "sparks/";
    public static final String FILE_GFX_MAP_REBELS_PATH = "rebels/";
    public static final String FILE_GFX_MAP_BORDER = "MapBorder.json";
    public static final String FILE_GFX_MAP_OUTSIDE_MAP_IMG_OVER = "OutsideMapImages_Over.json";
    public static final String FILE_GFX_MAP_OUTSIDE_MAP_IMG_BELOW = "OutsideMapImages_Below.json";
    public static final String FILE_EDITOR_FLAGS_PATH = "editorFlags/";
    public static final String FILE_EDITOR_FLAGS_DIVISIONS_PATH = "divisions/";
    public static final String FILE_EDITOR_FLAGS_DIVISIONS_LIST = "divisions.json";
    public static final String FILE_EDITOR_FLAGS_OVERLAYS_PATH = "overlays/";
    public static final String FILE_EDITOR_FLAGS_OVERLAYS_LIST = "overlays.json";
    public static final String FILE_GENERALS_PATH = "generals/";
    public static final String FILE_GENERALS_1_PATH = "generals/";
    public static final String FILE_GENERALS_2_PATH = "generals2/";
    public static final String FILE_GENERALS_3_PATH = "generals3/";
    public static final String FILE_ADVISORS_PATH = "advisors/";
    public static final String FILE_ADVISORS_1_PATH = "advisors/";
    public static final String FILE_ADVISORS_2_PATH = "advisors2/";
    public static final String FILE_CURSOR_PATH = "cursor/";
    public static final String FILE_GFX_CITIES_PATH = "cities/";
    public static final String FILE_GFX_CITIES_2_PATH = "cities2/";
    public static final String FILE_GFX_CITIES_FORT_PATH = "fort/";
    public static final String FILE_GFX_MOVE_PATH = "move/";
    public static final String FILE_ARMY_PATH = "army/";
    public static final String FILE_ARMY_MAP_PATH = "army26/";
    public static final String FILE_ARMY_MAP_PATH_2 = "army32/";
    public static final String FILE_UI_PATH = "ui/";
    public static final String FILE_LOADING_PATH = "loading/";
    public static final String FILE_UI_OTHER_PATH = "other/";
    public static final String FILE_UI_TOP_PATH = "top/";
    public static final String FILE_UI_BUTTONS_PATH = "buttons/";
    public static final String FILE_UI_BOXES_PATH = "boxes/";
    public static final String FILE_UI_MAIN_PATH = "mainMenu/";
    public static final String FILE_UI_MENUS_PATH = "menus/";
    public static final String FILE_UI_INFO_BOX_PATH = "infoBox/";
    public static final String FILE_UI_HRE_PATH = "hre/";
    public static final String FILE_UI_MESSAGE_PATH = "message/";
    public static final String FILE_UI_UNITS_PATH = "units/";
    public static final String FILE_UI_BUILDINGS_PATH = "buildings/";
    public static final String FILE_UI_RULER_PATH = "ruler/";
    public static final String FILE_UI_OVERLAY_PATH = "overlay/";
    public static final String FILE_UI_FLAG_PATH = "flag/";
    public static final String FILE_UI_MINIMAP_PATH = "minimap/";
    public static final String FILE_UI_TUTORIAL_PATH = "tutorial/";
    public static final String FILE_UI_LEVEL_PATH = "level/";
    public static final String FILE_UI_PATTERNS_PATH = "ui/patterns/";
    public static final String FILE_UI_PICKER_PATH = "ui/picker/";
    public static final String FILE_UI_GRADIENT_PATH = "gradients/";
    public static final String FILE_UI_LINES_PATH = "lines/";
    public static final String FILE_UI_ICONS_PATH = "icons/";
    public static final String FILE_UI_FLAGS_PATH = "flags/";
    public static final String FILE_UI_GRAPH_PATH = "graph/";
    public static final String FILE_UI_SCROLL_PATH = "scroll/";
    public static final String FILE_UI_PIECHART_PATH = "piechart/";
    public static final String FILE_MOVE_PATH = "gfx/test_move/";
    public static final String FILE_TILES_PATH = "gfx/tiles/";
    public static final String FILE_TILES_MAIN_PATH = "main/";
    public static final String FILE_TILES_OVERLAY_PATH = "overlay/";
    public static final String FILE_TILES_HOVER_PATH = "hover/";
    public static final String FILE_UNITS_PATH = "gfx/units/";
    public static final String FILE_GAME_FLAGS_PATH = "flags/";
    public static final String FILE_GAME_FLAGSH_PATH = "flagsH/";
    public static final String FILE_GAME_FLAGSXH_PATH = "flagsXH/";
    public static final String CIVILIZATION_FLAG_NOT_FOUND = "ran.png";
    public static final String FILE_MUSIC_PATH = "audio/music/";
    public static final String FILE_SOUNDS_PATH = "audio/sounds/";
    public static final String FILE_SFX_PATH = "audio/sfx/";
    public static final String FILE_SFX_RANDOM_PATH = "audio/random/";
    public static final String FILE_SFX_RANDOM_LIST = "list.txt";
    public static final String FILE_AMBIENCE_PATH = "audio/ambience/";
    public static int GAME_WIDTH;
    public static int GAME_HEIGHT;
    public static int TEXT_HEIGHT;
    public static int TEXT_HEIGHT_SMALL;
    public static int PADDING;
    public static final int RESIZE_PADDING_XY = 6;
    public static int COLOR_WIDTH;
    public static int BUTTON_HEIGHT;
    public static int BUTTON_HEIGHT2;
    public static int BUTTON_HEIGHT3;
    public static int BUTTON_HEIGHT4;
    public static int BUTTON_WIDTH;
    public static int LEFT_MENU_WIDTH;
    public static int LEFT_MENU_WIDTH2;
    public static float GUI_SCALE;
    public static float DENSITY;
    public static boolean XHDPI;
    public static boolean XXHDPI;
    public static boolean isDesktop;
    public static boolean isAndroid;
    public static boolean isiOS;
    public static int FONT_BOLD;
    public static int FONT_REGULAR;
    public static int FONT_BOLD_SMALL;
    public static int FONT_REGULAR_SMALL;
    public static int FONT_ARMY;
    public static int FONT_CITIES;
    public static final float GRAPH_DESC_TEXT_SCALE = 0.7f;
    public static final float GRAPH_DESC_TEXT_SCALE2 = 0.8f;
    public static int CIV_FLAG_WIDTH;
    public static int CIV_FLAG_HEIGHT;
    public static int CIV_COLOR_WIDTH;
    public static Color COLOR_PROVINCE_DASHED;
    public static Color COLOR_PROVINCE_STRAIGHT;
    public static Color COLOR_PROVINCE_STRAIGHT2;
    public static Color COLOR_PROVINCE_STRAIGHT_WAR;
    public static Color COLOR_PROVINCE_STRAIGHT_WAR2;
    public static Color COLOR_PROVINCE_STRAIGHT2_WAR;
    public static Color COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE;
    public static Color COLOR_PROVINCE_SEABYSEA;
    public static Color COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER;
    public static Color COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER2;
    public static boolean brushTool;
    public static boolean actionBrush;
    public static boolean selectMode;
    public static int iCreateScenario_ActiveProvinceID;
    public static int iCreateScenario_AssignProvinces_Civ;
    public static final Color COLOR_ARMY_TEXT;
    public static int[] rotateXMoveUnits;
    public static int[] rotateYMoveUnits;
    public static int[] rotateXMoveUnits_64;
    public static int[] rotateYMoveUnits_64;
    public static boolean reverseDirectionX;
    public static boolean reverseDirectionY;
    public static int UIScale;
    public static final int PROVINCE_OWNER_COLOR_INTERVAL = 725;
    public static char[] suffix;
    public static final String LOGS_FILE = "logs.txt";
    public static boolean append;
    public static int appendNum;
    private static final int KEY_LENGTH = 5;
    private static final int KEY_LENGTH2 = 7;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ALPHABET_LENGTH = 62;
    public static final float PROVINCE_ALPHA_GROWTH_RATE = 0.75f;
    public static final float PROVINCE_ALPHA_GROWTH_RATE_INGAME = 0.6f;
    public static Color[] COLOR_GROWTH_RATE;
    
    public static final String getRescouresPath() {
        if (CFG.XXHDPI) {
            return "interface/XXH/";
        }
        if (CFG.XHDPI) {
            return "interface/XH/";
        }
        return "interface/H/";
    }
    
    public static final String getRescouresPath_Short() {
        if (CFG.XXHDPI) {
            return "XXH/";
        }
        if (CFG.XHDPI) {
            return "XH/";
        }
        return "H/";
    }
    
    public static final String getRescouresPath_Short_H() {
        return "H/";
    }
    
    public static final int getUIScale() {
        if (CFG.XXHDPI) {
            return 2;
        }
        if (CFG.XHDPI) {
            return 1;
        }
        return 0;
    }
    
    public static final int getDarker(final int iColor, final int iMod) {
        return Math.round((float)Math.max(0, iColor - iMod));
    }
    
    public static final Color getDarker(final Color nColor, final int iMod, final float nAlpha) {
        return new Color(Math.max(0.0f, nColor.r * 255.0f - iMod) / 255.0f, Math.max(0.0f, nColor.g * 255.0f - iMod) / 255.0f, Math.max(0.0f, nColor.b * 255.0f - iMod) / 255.0f, nAlpha);
    }
    
    public static final Color getLighter(final Color nColor, final int iMod, final float nAlpha) {
        return new Color(Math.min(255.0f, nColor.r * 255.0f + iMod) / 255.0f, Math.min(255.0f, nColor.g * 255.0f + iMod) / 255.0f, Math.min(255.0f, nColor.b * 255.0f + iMod) / 255.0f, nAlpha);
    }
    
    public static final float getColorStep(final int iOld, final int iNew, final int iColorStepID, final int numOfSteps) {
        return (iOld + ((iOld > iNew) ? ((iNew - iOld) * iColorStepID / (float)numOfSteps) : ((iNew - iOld) * iColorStepID / (float)numOfSteps))) / 255.0f;
    }
    
    public static final float getColorStep(final float iOld, final float iNew, final float iColorStepID, final float numOfSteps) {
        return iOld + ((iOld > iNew) ? ((iNew - iOld) * iColorStepID / numOfSteps) : ((iNew - iOld) * iColorStepID / numOfSteps));
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
    
    public static final Color getColorMixed_2(final Color colorVassal, final Color colorLord) {
        return new Color(colorLord.r * GameValues.vassal.VASSAL_COLOR_LORD_PERC + colorVassal.r * GameValues.vassal.VASSAL_COLOR_VASSAL_PERC, colorLord.g * GameValues.vassal.VASSAL_COLOR_LORD_PERC + colorVassal.g * GameValues.vassal.VASSAL_COLOR_VASSAL_PERC, colorLord.b * GameValues.vassal.VASSAL_COLOR_LORD_PERC + colorVassal.b * GameValues.vassal.VASSAL_COLOR_VASSAL_PERC, colorVassal.a);
    }
    
    public static final String getPercentage(final int nA, final int nB, final int nPrecision) {
        final float nOut = nA / (float)nB * 100.0f;
        if (nOut - Math.floor(nOut) == 0.0) {
            return "" + (int)nOut;
        }
        return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
    }
    
    public static final String getPercentage(final float nPercentage, final int nPrecision) {
        return ("" + nPercentage).substring(0, Math.min(nPrecision, ("" + nPercentage).length()));
    }
    
    public static final String getPrecision2(final float nPercentage, final int nPrecision) {
        String sOut = "" + (int)(nPercentage * nPrecision) / (float)nPrecision;
        try {
            while (sOut.length() > 1 && sOut.indexOf(46) >= 0 && sOut.charAt(sOut.length() - 1) == '0') {
                sOut = sOut.substring(0, sOut.length() - 2);
            }
            if (sOut.indexOf(46) == sOut.length() - 1) {
                return sOut.substring(0, sOut.length() - 2);
            }
        }
        catch (final Exception ex) {}
        return sOut;
    }
    
    public static final String getPrecision2(final double nPercentage, final int nPrecision) {
        String sOut = "" + (int)(nPercentage * nPrecision) / (float)nPrecision;
        try {
            while (sOut.length() > 1 && sOut.indexOf(46) >= 0 && sOut.charAt(sOut.length() - 1) == '0') {
                sOut = sOut.substring(0, sOut.length() - 2);
            }
            if (sOut.indexOf(46) == sOut.length() - 1) {
                return sOut.substring(0, sOut.length() - 2);
            }
        }
        catch (final Exception ex) {}
        return sOut;
    }
    
    public static String getShortNumber(final int iNumber) {
        try {
            final int value = (int)Math.floor(Math.log10(iNumber));
            final int base = value / 3;
            if (value >= 3 && base < CFG.suffix.length) {
                String out;
                for (out = new DecimalFormat("#0.0").format(iNumber / Math.pow(10.0, base * 3)).replace(",", "."); out.length() > 1 && out.indexOf(46) >= 0 && out.charAt(out.length() - 1) == '0'; out = out.substring(0, out.length() - 2)) {}
                return out + CFG.suffix[base];
            }
            return new DecimalFormat("#,##0").format(iNumber);
        }
        catch (final Exception ex) {
            exceptionStack(ex);
            return "" + iNumber;
        }
    }
    
    public static String getShortNumber(final long iNumber) {
        try {
            final int value = (int)Math.floor(Math.log10((double)iNumber));
            final int base = value / 3;
            if (value >= 3 && base < CFG.suffix.length) {
                String out;
                for (out = new DecimalFormat("#0.0").format(iNumber / Math.pow(10.0, base * 3)).replace(",", "."); out.length() > 1 && out.indexOf(46) >= 0 && out.charAt(out.length() - 1) == '0'; out = out.substring(0, out.length() - 2)) {}
                return out + CFG.suffix[base];
            }
            return new DecimalFormat("#,##0").format(iNumber);
        }
        catch (final Exception ex) {
            exceptionStack(ex);
            return "" + iNumber;
        }
    }
    
    public static final String readFile(final String path) {
        try {
            final FileHandle tempFileT = FileManager.loadFile(path);
            return tempFileT.readString();
        }
        catch (final GdxRuntimeException ex) {
            LOG((Throwable)ex);
            LOG("CFG.readFile() - ERROR" + path);
            Game.menuManager.addToast_Error("CFG.readFile() - ERROR" + path);
            return "";
        }
    }
    
    public static void LOG(final String log) {
        LOG("DEFAULT", log);
        if (GameValues.logs.SAVE_LOGS_TO_FILE) {
            final FileHandle file = FileManager.IS_MAC ? Gdx.files.external("logs.txt") : Gdx.files.local("logs.txt");
            file.writeString("\n" + log, CFG.append);
            CFG.append = true;
            if (CFG.appendNum++ > GameValues.logs.SAVE_LOGS_LIMIT) {
                CFG.append = false;
                CFG.appendNum = 0;
            }
        }
    }
    
    public static void LOG(final String log, final String log2) {
        Gdx.app.log(log, log2);
        if (GameValues.logs.SAVE_LOGS_TO_FILE) {
            final FileHandle file = FileManager.IS_MAC ? Gdx.files.external("logs.txt") : Gdx.files.local("logs.txt");
            file.writeString("\n[" + log + "] ", CFG.append);
            file.writeString(log2, CFG.append = true);
            if (CFG.appendNum++ > GameValues.logs.SAVE_LOGS_LIMIT) {
                CFG.append = false;
                CFG.appendNum = 0;
            }
        }
    }
    
    public static void LOG(final Throwable e) {
        e.printStackTrace();
        Game.menuManager.addToast_Error(e.toString());
        if (GameValues.logs.SAVE_LOGS_TO_FILE) {
            final FileHandle file = FileManager.IS_MAC ? Gdx.files.external("logs.txt") : Gdx.files.local("logs.txt");
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            file.writeString(sw.toString(), CFG.append);
            CFG.append = true;
            if (CFG.appendNum++ > GameValues.logs.SAVE_LOGS_LIMIT) {
                CFG.append = false;
                CFG.appendNum = 0;
            }
        }
    }
    
    public static void exceptionStack(final Throwable e) {
        e.printStackTrace();
        if (GameValues.logs.SAVE_LOGS_TO_FILE) {
            final FileHandle file = FileManager.IS_MAC ? Gdx.files.external("logs.txt") : Gdx.files.local("logs.txt");
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            file.writeString(sw.toString(), CFG.append);
            CFG.append = true;
            if (CFG.appendNum++ > GameValues.logs.SAVE_LOGS_LIMIT) {
                CFG.append = false;
                CFG.appendNum = 0;
            }
        }
    }
    
    public static final boolean isAndroid() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }
    
    public static final boolean isIOS() {
        return Gdx.app.getType() == Application.ApplicationType.iOS;
    }
    
    public static final boolean isDesktop() {
        return Gdx.app.getType() == Application.ApplicationType.Desktop;
    }
    
    public static final float changeAnimationPos(final int animationStepID, float animationChangeViewPos, final boolean backAnimation, final int nWidth) {
        switch (animationStepID) {
            case 0:
            case 1:
            case 12: {
                animationChangeViewPos -= nWidth * 2.5f / 100.0f * (backAnimation ? -1 : 1);
                break;
            }
            case 2:
            case 3:
            case 10:
            case 11: {
                animationChangeViewPos -= nWidth * 5.0f / 100.0f * (backAnimation ? -1 : 1);
                break;
            }
            case 4:
            case 5:
            case 8:
            case 9: {
                animationChangeViewPos -= nWidth * 10.0f / 100.0f * (backAnimation ? -1 : 1);
                break;
            }
            case 6:
            case 7: {
                animationChangeViewPos -= nWidth * 15.0f / 100.0f * (backAnimation ? -1 : 1);
                break;
            }
            case 13: {
                animationChangeViewPos = (float)(-nWidth * (backAnimation ? -1 : 1));
                break;
            }
        }
        return animationChangeViewPos;
    }
    
    public static final boolean compareAlphabetic_TwoString(final String a, final String b) {
        for (int i = 0; i < a.length() && i < b.length(); ++i) {
            if (a.charAt(i) < b.charAt(i)) {
                return false;
            }
            if (a.charAt(i) != b.charAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    public static final String extraRandomTag() {
        final StringBuilder output = new StringBuilder(5);
        for (int i = 0; i < 5; ++i) {
            output.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(Game.oR.nextInt(62)));
        }
        return output.toString();
    }
    
    public static String getRandomString() {
        final List<String> roomNames = new ArrayList<String>();
        roomNames.add("Clasros");
        roomNames.add("ShadowLegends");
        roomNames.add("DarkPhantoms");
        roomNames.add("MysticWarriors");
        roomNames.add("InfernoKnights");
        roomNames.add("IronFist");
        roomNames.add("NightRaiders");
        roomNames.add("StarBlazers");
        roomNames.add("LunarWolves");
        roomNames.add("CosmicFalcons");
        roomNames.add("CrimsonRangers");
        roomNames.add("ThunderStrikers");
        roomNames.add("FrostGiants");
        roomNames.add("SolarGuardians");
        roomNames.add("VenomSeekers");
        roomNames.add("VoidStalkers");
        roomNames.add("DragonSlayers");
        roomNames.add("SilentStorm");
        roomNames.add("SteelHawks");
        roomNames.add("BlazingPhoenix");
        roomNames.add("ShadowReapers");
        roomNames.add("GoldenEagles");
        roomNames.add("ElectricPandas");
        roomNames.add("PlasmaWarriors");
        roomNames.add("CyberNinjas");
        roomNames.add("PixelHunters");
        roomNames.add("GalaxyDefenders");
        roomNames.add("FrozenTitans");
        roomNames.add("VortexVikings");
        roomNames.add("TempestRiders");
        roomNames.add("RadiantSnipers");
        roomNames.add("FireWolves");
        roomNames.add("DiamondPanthers");
        roomNames.add("RubyCrusaders");
        roomNames.add("StormWalkers");
        roomNames.add("SkyAssassins");
        roomNames.add("ChaosLords");
        roomNames.add("NebulaFighters");
        roomNames.add("MetalSharks");
        roomNames.add("PhantomRiders");
        roomNames.add("ShadowFury");
        roomNames.add("BlizzardHawks");
        roomNames.add("InfinityBlades");
        roomNames.add("DuskKnights");
        roomNames.add("EclipseHunters");
        roomNames.add("RogueRangers");
        roomNames.add("HorizonRaiders");
        roomNames.add("TidalForces");
        roomNames.add("VenomHawks");
        roomNames.add("BlackCobra");
        roomNames.add("MysticDragons");
        roomNames.add("CrimsonBlades");
        roomNames.add("InfernalKnights");
        roomNames.add("OceanRaiders");
        roomNames.add("FrostKnights");
        roomNames.add("SteelRaiders");
        roomNames.add("LunarPhantoms");
        roomNames.add("SilverLions");
        roomNames.add("SolarRangers");
        roomNames.add("DarkTitans");
        roomNames.add("PlasmaHunters");
        roomNames.add("ShadowSeekers");
        roomNames.add("CrystalEagles");
        roomNames.add("ThunderWolves");
        roomNames.add("IronGuardians");
        roomNames.add("NightStalkers");
        roomNames.add("StormHunters");
        roomNames.add("GalaxyBlasters");
        roomNames.add("PhantomRaiders");
        roomNames.add("SilentFury");
        roomNames.add("CyberDragons");
        roomNames.add("ShadowKnights");
        roomNames.add("LunarRaiders");
        roomNames.add("TempestSeekers");
        roomNames.add("VenomTitans");
        roomNames.add("DiamondWolves");
        roomNames.add("BlazingRiders");
        roomNames.add("VoidPhantoms");
        roomNames.add("FrozenHunters");
        roomNames.add("CrimsonFury");
        roomNames.add("SteelWolves");
        roomNames.add("LunarGuardians");
        roomNames.add("GoldenTitans");
        roomNames.add("InfernalRaiders");
        roomNames.add("OceanKnights");
        roomNames.add("PlasmaRaiders");
        roomNames.add("GalaxyGuardians");
        roomNames.add("ShadowWarriors");
        roomNames.add("MysticRaiders");
        roomNames.add("DarkLions");
        roomNames.add("InfinityRaiders");
        roomNames.add("EclipseBlades");
        roomNames.add("NebulaKnights");
        roomNames.add("SilverFalcons");
        roomNames.add("FrostBlazers");
        roomNames.add("StormWarriors");
        roomNames.add("CyberRaiders");
        roomNames.add("IronDragons");
        roomNames.add("PhantomSeekers");
        roomNames.add("SilentRangers");
        roomNames.add("ShadowHunters");
        roomNames.add("BlazingKnights");
        roomNames.add("FrozenWarriors");
        roomNames.add("DiamondFalcons");
        roomNames.add("PlasmaBlazers");
        roomNames.add("SilverKnights");
        roomNames.add("InfernalHunters");
        roomNames.add("NightTitans");
        roomNames.add("VoidKnights");
        roomNames.add("GalaxyWarriors");
        roomNames.add("GoldenSeekers");
        roomNames.add("VenomRaiders");
        roomNames.add("LunarBlades");
        roomNames.add("CrimsonGuardians");
        roomNames.add("SteelHunters");
        roomNames.add("SolarFalcons");
        roomNames.add("OceanHunters");
        roomNames.add("DarkWarriors");
        roomNames.add("StormSeekers");
        roomNames.add("CyberWarriors");
        roomNames.add("IronHunters");
        roomNames.add("BlizzardBlades");
        roomNames.add("InfinityHunters");
        roomNames.add("DuskRaiders");
        roomNames.add("HorizonBlazers");
        roomNames.add("RogueBlades");
        roomNames.add("RadiantKnights");
        roomNames.add("TidalKnights");
        roomNames.add("SkyFury");
        roomNames.add("NebulaRaiders");
        roomNames.add("ChaosHunters");
        roomNames.add("BlackRaiders");
        roomNames.add("FireTitans");
        roomNames.add("TempestHunters");
        roomNames.add("CrimsonRaiders");
        roomNames.add("SolarWarriors");
        roomNames.add("VenomHunters");
        roomNames.add("CosmicBlades");
        roomNames.add("SilverWarriors");
        roomNames.add("GoldenRaiders");
        roomNames.add("MysticSeekers");
        roomNames.add("IronRaiders");
        roomNames.add("DarkFalcons");
        roomNames.add("OceanBlades");
        roomNames.add("FrozenSeekers");
        roomNames.add("ShadowFalcons");
        roomNames.add("PhantomGuardians");
        roomNames.add("GalaxyHunters");
        final Random random = new Random();
        return roomNames.get(random.nextInt(roomNames.size()));
    }
    
    public static final String extraRandomTag2() {
        final StringBuilder output = new StringBuilder(7);
        for (int i = 0; i < 7; ++i) {
            output.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(Game.oR.nextInt(62)));
        }
        return output.toString();
    }
    
    public static final String extraRandomTag_Old() {
        final Random oR = new Random();
        String output = "";
        for (int i = 0; i < 5; ++i) {
            output += (char)(97 + oR.nextInt(26));
        }
        return output;
    }
    
    public final String getDev() {
        return "\u0141ukasz Jakowski";
    }
    
    public static final Color getGrowthRateColor(final int nGrowthRate, final float nAlpha) {
        switch (nGrowthRate / 10) {
            case 0: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[0], CFG.COLOR_GROWTH_RATE[1], nGrowthRate % 10, 10, nAlpha);
            }
            case 1: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[1], CFG.COLOR_GROWTH_RATE[2], nGrowthRate % 10, 10, nAlpha);
            }
            case 2: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[2], CFG.COLOR_GROWTH_RATE[3], nGrowthRate % 10, 10, nAlpha);
            }
            case 3: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[3], CFG.COLOR_GROWTH_RATE[4], nGrowthRate % 10, 10, nAlpha);
            }
            case 4: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[4], CFG.COLOR_GROWTH_RATE[5], nGrowthRate % 10, 10, nAlpha);
            }
            case 5: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[5], CFG.COLOR_GROWTH_RATE[6], nGrowthRate % 10, 10, nAlpha);
            }
            case 6: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[6], CFG.COLOR_GROWTH_RATE[7], nGrowthRate % 10, 10, nAlpha);
            }
            case 7: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[7], CFG.COLOR_GROWTH_RATE[8], nGrowthRate % 10, 10, nAlpha);
            }
            case 8: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[8], CFG.COLOR_GROWTH_RATE[9], nGrowthRate % 10, 10, nAlpha);
            }
            case 9: {
                return getColorStep(CFG.COLOR_GROWTH_RATE[9], CFG.COLOR_GROWTH_RATE[10], nGrowthRate % 10, 10, nAlpha);
            }
            case 10: {
                return new Color(CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].r, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].g, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].b, nAlpha);
            }
            default: {
                return new Color(CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].r, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].g, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].b, nAlpha);
            }
        }
    }
    
    public static Color getRandomColor() {
        return new Color(Game.oR.nextInt(256) / 255.0f, Game.oR.nextInt(256) / 255.0f, Game.oR.nextInt(256) / 255.0f, 1.0f);
    }
    
    protected static Point_XY getRandomPointToCenterTheMap() {
        final Random oR = new Random();
        return new Point_XY(oR.nextInt(Game.mapBG.getWidth() / Game.mapBG.iMapScale), oR.nextInt(Game.mapBG.getHeight() / Game.mapBG.iMapScale));
    }
    
    public static boolean getMetProvince(final int nProvinceID) {
        return true;
    }
    
    public static final String getNumberWithSpaces(final String nValue) {
        String nOut = "";
        for (int i = nValue.length(); i > 0; i -= 3) {
            nOut = " " + nValue.substring((i - 3 > 0) ? (i - 3) : 0, i) + nOut;
        }
        return (nOut.charAt(0) == ' ') ? nOut.substring(1, nOut.length()) : nOut;
    }
    
    public static final String getNumber_SHORT(final int nValue) {
        if (nValue < 1000) {
            return "" + nValue;
        }
        if (nValue < 1000000) {
            final String outValue = "" + nValue / 1000.0f;
            try {
                Gdx.app.log("AoC", "" + outValue.charAt(outValue.indexOf(".") + 1));
                return "" + ((outValue.charAt(outValue.indexOf(".") + 1) == '0') ? ("" + nValue / 1000 + Game.lang.get("Value_Thousand")) : (outValue.substring(0, outValue.indexOf(".") + 2) + Game.lang.get("Value_Thousand")));
            }
            catch (final IndexOutOfBoundsException ex) {
                return "" + nValue / 1000 + Game.lang.get("Value_Thousand");
            }
        }
        final String outValue = "" + nValue / 1000000.0f;
        try {
            return "" + ((outValue.charAt(outValue.indexOf(".") + 1) == '0') ? ("" + nValue / 1000 + Game.lang.get("Value_Million")) : (outValue.substring(0, outValue.indexOf(".") + 2) + Game.lang.get("Value_Million")));
        }
        catch (final IndexOutOfBoundsException ex) {
            return "" + nValue / 1000 + Game.lang.get("Value_Million");
        }
    }
    
    public static final boolean readLocalFiles() {
        switch (Gdx.app.getType()) {
            case Android:
            case iOS: {
                return true;
            }
            case Desktop: {
                return false;
            }
            default: {
                return false;
            }
        }
    }
    
    public static String checkName(String name) {
        try {
            if (name != null) {
                final StringBuilder newName = new StringBuilder();
                for (int iLength = name.length(), i = 0; i < iLength; ++i) {
                    if (Renderer.charset.indexOf(name.charAt(i)) == -1) {
                        newName.append(Normalizer.normalize("" + name.charAt(i), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));
                    }
                    else {
                        newName.append(name.charAt(i));
                    }
                }
                name = newName.toString();
            }
        }
        catch (final Exception ex) {
            exceptionStack(ex);
        }
        return name;
    }
    
    static {
        CFG.currentTimeMillis = 0L;
        CFG.debugMode = false;
        CFG.GAME_WIDTH = 1;
        CFG.GAME_HEIGHT = 1;
        CFG.TEXT_HEIGHT = 1;
        CFG.TEXT_HEIGHT_SMALL = 1;
        CFG.PADDING = 5;
        CFG.COLOR_WIDTH = 3;
        CFG.BUTTON_HEIGHT = 68;
        CFG.BUTTON_HEIGHT2 = 58;
        CFG.BUTTON_HEIGHT3 = 46;
        CFG.BUTTON_HEIGHT4 = 48;
        CFG.BUTTON_WIDTH = 90;
        CFG.LEFT_MENU_WIDTH = 400;
        CFG.LEFT_MENU_WIDTH2 = 600;
        CFG.GUI_SCALE = 1.0f;
        CFG.DENSITY = 1.0f;
        CFG.XHDPI = false;
        CFG.XXHDPI = false;
        CFG.isDesktop = true;
        CFG.isAndroid = false;
        CFG.isiOS = false;
        CFG.FONT_BOLD = 0;
        CFG.FONT_REGULAR = 1;
        CFG.FONT_BOLD_SMALL = 0;
        CFG.FONT_REGULAR_SMALL = 1;
        CFG.FONT_ARMY = 0;
        CFG.FONT_CITIES = 0;
        CFG.CIV_FLAG_WIDTH = 27;
        CFG.CIV_FLAG_HEIGHT = 18;
        CFG.CIV_COLOR_WIDTH = 3;
        CFG.COLOR_PROVINCE_DASHED = new Color(0.04f, 0.04f, 0.04f, 0.525f);
        CFG.COLOR_PROVINCE_STRAIGHT = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        CFG.COLOR_PROVINCE_STRAIGHT2 = new Color(0.0f, 0.0f, 0.0f, 0.3f);
        CFG.COLOR_PROVINCE_STRAIGHT_WAR = new Color(0.7176471f, 0.35686275f, 0.039215688f, 1.0f);
        CFG.COLOR_PROVINCE_STRAIGHT_WAR2 = new Color(0.54901963f, 0.3019608f, 0.047058824f, 1.0f);
        CFG.COLOR_PROVINCE_STRAIGHT2_WAR = new Color(0.0f, 0.0f, 0.0f, 0.3f);
        CFG.COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        CFG.COLOR_PROVINCE_SEABYSEA = new Color(0.94f, 0.94f, 0.95f, 0.07f);
        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER = new Color(0.8235294f, 0.74509805f, 0.039215688f, 1.0f);
        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER2 = new Color(0.8235294f, 0.74509805f, 0.039215688f, 0.3f);
        CFG.brushTool = false;
        CFG.actionBrush = false;
        CFG.selectMode = true;
        CFG.iCreateScenario_AssignProvinces_Civ = -1;
        COLOR_ARMY_TEXT = new Color(0.88235295f, 0.88235295f, 0.27450982f, 1.0f);
        CFG.rotateXMoveUnits = new int[] { 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 11, 11, 11, 11, 10, 10, 10, 10, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 0, 0, -1, -1, -1, -2, -2, -2, -3, -3, -3, -4, -4, -4, -5, -5, -5, -5, -5, -6, -6, -6, -7, -7, -7, -7, -8, -8, -8, -8, -8, -8, -8, -8, -9, -9, -9, -10, -10, -11, -11, -11, -11, -12, -12, -12, -13, -13, -13, -13, -13, -13, -13, -14, -14, -14, -14, -14, -14, -14, -14, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -14, -14, -14, -14, -14, -14, -13, -13, -13, -13, -13, -13, -13, -11, -11, -11, -11, -11, -11, -11, -11, -10, -10, -10, -10, -9, -9, -9, -9, -8, -8, -8, -8, -7, -7, -7, -7, -7, -7, -6, -6, -6, -5, -5, -5, -5, -5, -4, -4, -4, -3, -3, -2, -2, -1, -1, -1, -1, -1, 0, 0 };
        CFG.rotateYMoveUnits = new int[] { -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -17, -17, -17, -17, -17, -17, -18, -18, -18, -18, -18, -18, -18, -19, -19, -19, -19, -19, -19, -20, -20, -20, -20, -20, -20, -21, -21, -21, -21, -22, -22, -22, -22, -22, -22, -23, -23, -23, -23, -24, -24, -24, -24, -25, -25, -25, -25, -26, -26, -26, -27, -27, -27, -27, -28, -28, -28, -29, -29, -29, -29, -30, -30, -30, -31, -32, -32, -32, -32, -32, -32, -32, -33, -33, -34, -34, -34, -34, -34, -34, -34, -35, -36, -36, -36, -36, -36, -36, -37, -37, -37, -37, -37, -39, -39, -39, -39, -39, -39, -40, -40, -40, -40, -41, -41, -41, -42, -42, -43, -43, -43, -43, -43, -43, -44, -44, -44, -44, -44, -45, -45, -45, -45, -45, -45, -45, -45, -46, -46, -46, -46, -46, -46, -46, -46, -46, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -47, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -48, -47, -47, -47, -47, -47, -47, -47, -47, -46, -46, -46, -46, -46, -46, -46, -46, -45, -45, -45, -45, -45, -45, -45, -44, -44, -43, -43, -43, -43, -42, -42, -42, -41, -41, -41, -41, -41, -41, -41, -40, -40, -40, -40, -40, -40, -40, -40, -39, -39, -39, -39, -37, -37, -37, -37, -36, -36, -36, -36, -35, -35, -35, -34, -34, -34, -34, -34, -33, -33, -33, -33, -32, -32, -32, -32, -31, -31, -30, -30, -30, -30, -29, -29, -29, -28, -28, -28, -27, -27, -27, -26, -26, -26, -26, -25, -25, -25, -25, -25, -25, -24, -24, -24, -24, -24, -24, -23, -23, -23, -23, -23, -23, -23, -23, -22, -22, -20, -20, -20, -20, -20, -20, -20, -20, -19, -19, -19, -19, -19, -19, -19, -19, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -17, -17, -17, -17, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16 };
        CFG.rotateXMoveUnits_64 = new int[] { 0, 0, 0, 1, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 11, 12, 13, 13, 13, 14, 14, 14, 15, 15, 16, 16, 17, 17, 18, 19, 19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 22, 23, 23, 24, 24, 25, 25, 25, 26, 26, 26, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 29, 29, 29, 29, 30, 30, 30, 30, 31, 31, 31, 31, 31, 31, 31, 31, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 31, 31, 31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30, 30, 30, 30, 29, 29, 29, 29, 29, 29, 29, 28, 28, 27, 27, 26, 26, 26, 26, 26, 26, 25, 25, 25, 25, 24, 24, 24, 23, 23, 23, 22, 22, 21, 21, 20, 20, 19, 19, 18, 18, 17, 16, 16, 15, 14, 14, 13, 12, 12, 11, 10, 10, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 4, 4, 3, 3, 2, 2, 1, 1, 0, 0, -1, -1, -2, -3, -3, -4, -5, -5, -6, -7, -7, -8, -9, -9, -9, -10, -10, -11, -12, -12, -13, -13, -14, -14, -15, -15, -15, -15, -16, -16, -16, -16, -17, -18, -18, -19, -20, -21, -21, -22, -22, -23, -23, -24, -25, -25, -25, -26, -26, -26, -26, -27, -27, -27, -28, -28, -28, -28, -28, -29, -29, -29, -29, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -31, -31, -31, -31, -31, -31, -31, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -31, -31, -31, -31, -31, -31, -31, -31, -30, -30, -30, -30, -30, -30, -30, -30, -29, -29, -29, -29, -29, -29, -29, -28, -28, -28, -27, -27, -27, -26, -26, -25, -25, -24, -24, -23, -23, -22, -22, -22, -22, -21, -21, -21, -20, -20, -19, -19, -18, -18, -17, -17, -16, -16, -15, -15, -14, -14, -14, -13, -13, -13, -12, -12, -11, -10, -10, -10, -9, -9, -8, -8, -7, -6, -5, -4, -3, -2, -2, -1, -1, -2, 0, 0 };
        CFG.rotateYMoveUnits_64 = new int[] { -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -34, -34, -34, -34, -34, -34, -36, -36, -36, -36, -36, -36, -36, -38, -38, -38, -38, -38, -38, -40, -40, -40, -40, -40, -40, -42, -42, -42, -42, -44, -44, -44, -44, -44, -44, -46, -46, -46, -46, -48, -48, -48, -48, -50, -50, -50, -50, -52, -52, -52, -54, -54, -54, -54, -56, -56, -56, -58, -58, -58, -58, -60, -60, -60, -62, -64, -64, -64, -64, -64, -64, -64, -66, -66, -68, -68, -68, -68, -68, -68, -68, -70, -72, -72, -72, -72, -72, -72, -74, -74, -74, -74, -74, -78, -78, -78, -78, -78, -78, -80, -80, -80, -80, -82, -82, -82, -84, -84, -86, -86, -86, -86, -86, -86, -88, -88, -88, -88, -88, -90, -90, -90, -90, -90, -90, -90, -90, -92, -92, -92, -92, -92, -92, -92, -92, -92, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -94, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -96, -94, -94, -94, -94, -94, -94, -94, -94, -92, -92, -92, -92, -92, -92, -92, -92, -90, -90, -90, -90, -90, -90, -90, -88, -88, -86, -86, -86, -86, -84, -84, -84, -82, -82, -82, -82, -82, -82, -82, -80, -80, -80, -80, -80, -80, -80, -80, -78, -78, -78, -78, -74, -74, -74, -74, -72, -72, -72, -72, -70, -70, -70, -68, -68, -68, -68, -68, -66, -66, -66, -66, -64, -64, -64, -64, -62, -62, -60, -60, -60, -60, -58, -58, -58, -56, -56, -56, -54, -54, -54, -52, -52, -52, -52, -50, -50, -50, -50, -50, -50, -48, -48, -48, -48, -48, -48, -46, -46, -46, -46, -46, -46, -46, -46, -44, -44, -40, -40, -40, -40, -40, -40, -40, -40, -38, -38, -38, -38, -38, -38, -38, -38, -36, -36, -36, -36, -36, -36, -36, -36, -36, -36, -36, -36, -36, -36, -34, -34, -34, -34, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32 };
        CFG.reverseDirectionX = true;
        CFG.reverseDirectionY = true;
        CFG.UIScale = 0;
        CFG.suffix = new char[] { ' ', 'k', 'M', 'B', 'T', 'P', 'E' };
        CFG.append = false;
        CFG.appendNum = 0;
        CFG.COLOR_GROWTH_RATE = new Color[] { new Color(1.0f, 0.9764706f, 0.64705884f, 0.75f), new Color(0.99607843f, 0.9607843f, 0.0f, 0.75f), new Color(0.99607843f, 0.8901961f, 0.0f, 0.75f), new Color(0.99607843f, 0.7490196f, 0.0f, 0.75f), new Color(0.99607843f, 0.60784316f, 0.0f, 0.75f), new Color(0.99607843f, 0.42352942f, 0.0f, 0.75f), new Color(0.99607843f, 0.23529412f, 0.0f, 0.75f), new Color(0.8627451f, 0.0f, 0.0f, 0.75f), new Color(0.54901963f, 0.0f, 0.0f, 0.75f), new Color(0.39215687f, 0.0f, 0.0f, 0.75f), new Color(0.3137255f, 0.0f, 0.0f, 0.75f) };
    }
}
