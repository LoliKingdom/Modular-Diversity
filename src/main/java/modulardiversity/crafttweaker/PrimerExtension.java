package modulardiversity.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.crafttweaker.RecipePrimer;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.*;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@ZenRegister
@ZenExpansion("mods.modularmachinery.RecipePrimer")
public class PrimerExtension {
    //----------------------------------------------------------------------------------------------
    // all environmental
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer setPerTick(RecipePrimer primer, boolean perTick) {
        runOnLastRequirement(primer, RequirementEnvironmental.class,"setPerTick", (requirement) -> {
            requirement.setPerTick(perTick);
        });
        return primer;
    }

    //----------------------------------------------------------------------------------------------
    // aura
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addAuraInput(RecipePrimer primer, float visAdded, float fluxAdded) {
        requireAura(primer, IOType.INPUT, visAdded, fluxAdded);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addAuraOutput(RecipePrimer primer, float visAdded, float fluxAdded) {
        requireAura(primer, IOType.OUTPUT, visAdded, fluxAdded);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addVisRequirement(RecipePrimer primer, float visMin, float visMax) {
        runOnLastRequirement(primer,RequirementAura.class,"addVisRequirement", (requirement) -> {
            requirement.visMin = visMin;
            requirement.visMax = visMax;
        });
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addFluxRequirement(RecipePrimer primer, float fluxMin, float fluxMax) {
        runOnLastRequirement(primer,RequirementAura.class,"addFluxRequirement", (requirement) -> {
            requirement.fluxMin = fluxMin;
            requirement.fluxMax = fluxMax;
        });
        return primer;
    }

    private static void requireAura(RecipePrimer primer, IOType io, float vis, float flux) {
        primer.appendComponent(new RequirementAura(io,0,Float.POSITIVE_INFINITY,0,Float.POSITIVE_INFINITY,vis,flux));
    }

    //----------------------------------------------------------------------------------------------
    // mineral
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMineralInput(RecipePrimer primer, String name, int added) {
        requireMineral(primer, IOType.INPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMineralOutput(RecipePrimer primer, String name, int added) {
        requireMineral(primer, IOType.OUTPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addOreRequirement(RecipePrimer primer, int min, int max) {
        runOnLastRequirement(primer,RequirementMineral.class,"addOreRequirement", (requirement) -> {
            requirement.oreMin = min;
            requirement.oreMax = max;
        });
        return primer;
    }

    private static void requireMineral(RecipePrimer primer, IOType io, String name, int added) {
        primer.appendComponent(new RequirementMineral(io,name, 0, Integer.MAX_VALUE, added));
    }

    //----------------------------------------------------------------------------------------------
    // reservoir
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addReservoirInput(RecipePrimer primer, String name, int added) {
        requireReservoir(primer, IOType.INPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addReservoirOutput(RecipePrimer primer, String name, int added) {
        requireReservoir(primer, IOType.OUTPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addReservoirFluidRequirement(RecipePrimer primer, int min, int max) {
        runOnLastRequirement(primer,RequirementReservoir.class,"addReservoirFluidRequirement", (requirement) -> {
            requirement.fluidMin = min;
            requirement.fluidMax = max;
        });
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addReservoirResidualRequirement(RecipePrimer primer, int min, int max) {
        runOnLastRequirement(primer,RequirementReservoir.class,"addReservoirResidualRequirement", (requirement) -> {
            requirement.residualMin = min;
            requirement.residualMax = max;
        });
        return primer;
    }

    private static void requireReservoir(RecipePrimer primer, IOType io, String name, int added) {
        primer.appendComponent(new RequirementReservoir(io,name, 0, Integer.MAX_VALUE,0, Integer.MAX_VALUE, added));
    }

    //----------------------------------------------------------------------------------------------
    // mantle
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMantleRequirement(RecipePrimer primer, float emberMin, float emberMax, float stabilityMin, float stabilityMax) {
        primer.appendComponent(new RequirementEmberWorld(IOType.INPUT,emberMin,emberMax,stabilityMin,stabilityMax));
        return primer;
    }

    //----------------------------------------------------------------------------------------------
    // mana
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addManaInput(RecipePrimer primer, int mana) {
        requireMana(primer, IOType.INPUT, mana);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addManaOutput(RecipePrimer primer, int mana) {
        requireMana(primer, IOType.OUTPUT, mana);
        return primer;
    }

    private static void requireMana(RecipePrimer primer, IOType io, int mana) {
        primer.appendComponent(new RequirementMana(io,mana));
    }

    //----------------------------------------------------------------------------------------------
    // mechanical
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMechanicalInput(RecipePrimer primer, int power) {
        requireMechanical(primer, IOType.INPUT, power, false);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMechanicalCrankInput(RecipePrimer primer, int power) {
        requireMechanical(primer, IOType.INPUT, power, true);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMechanicalOutput(RecipePrimer primer, int power) {
        requireMechanical(primer, IOType.OUTPUT, power, false);
        return primer;
    }

    private static void requireMechanical(RecipePrimer primer, IOType io, int power, boolean crankAllowed) {
        primer.appendComponent(new RequirementMechanical(io,power,crankAllowed));
    }

    //----------------------------------------------------------------------------------------------
    // ember
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addEmberInput(RecipePrimer primer, double ember) {
        requireEmber(primer, IOType.INPUT, ember);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addEmberOutput(RecipePrimer primer, double ember) {
        requireEmber(primer, IOType.OUTPUT, ember);
        return primer;
    }

    private static void requireEmber(RecipePrimer primer, IOType io, double ember) {
        primer.appendComponent(new RequirementEmber(io,ember));
    }

    //----------------------------------------------------------------------------------------------
    // laser
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addLaserInput(RecipePrimer primer, long mMJ) {
        requireLaser(primer, IOType.INPUT, mMJ);
        return primer;
    }

    private static void requireLaser(RecipePrimer primer, IOType io, long mMJ) {
        primer.appendComponent(new RequirementLaser(io,mMJ));
    }

    //----------------------------------------------------------------------------------------------
    // mekanism laser
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMekanismLaserInput(RecipePrimer primer, double power) {
        requireMekanismLaser(primer, IOType.INPUT, power);
        return primer;
    }

    private static void requireMekanismLaser(RecipePrimer primer, IOType io, double power) {
        primer.appendComponent(new RequirementMekLaser(io,power));
    }

    //----------------------------------------------------------------------------------------------
    // hot air
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addHotAirInput(RecipePrimer primer, int temperature, int temperatureMin, int temperatureMax) {
        requireHotAir(primer, IOType.INPUT, temperature, temperatureMin, temperatureMax);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addHotAirOutput(RecipePrimer primer, int temperature, int temperatureMin, int temperatureMax) {
        requireHotAir(primer, IOType.OUTPUT, temperature, temperatureMin, temperatureMax);
        return primer;
    }

    private static void requireHotAir(RecipePrimer primer, IOType io, int temperature, int temperatureMin, int temperatureMax) {
        primer.appendComponent(new RequirementHotAir(io,temperatureMin,temperatureMax,temperature));
    }

    //----------------------------------------------------------------------------------------------
    // mekanism heat
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMekanismHeatInput(RecipePrimer primer, float temperature, float temperatureMin, double temperatureMax) {
        requireMekanismHeat(primer, IOType.INPUT, temperature, temperatureMin, temperatureMax);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMekanismHeatOutput(RecipePrimer primer, float temperature, float temperatureMin, double temperatureMax) {
        requireMekanismHeat(primer, IOType.OUTPUT, temperature, temperatureMin, temperatureMax);
        return primer;
    }

    private static void requireMekanismHeat(RecipePrimer primer, IOType io, double temperature, double temperatureMin, double temperatureMax) {
        primer.appendComponent(new RequirementMekHeat(io,temperature,temperatureMin,temperatureMax));
    }

    //----------------------------------------------------------------------------------------------
    // mystical mechanics
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMysticalMechanicsInput(RecipePrimer primer, double min, double max) {
        primer.appendComponent(new RequirementMysticalMechanics(IOType.INPUT,min,max));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMysticalMechanicsOutput(RecipePrimer primer, double level, int time) {
        primer.appendComponent(new RequirementMysticalMechanics(IOType.OUTPUT,level,time));
        return primer;
    }

    //----------------------------------------------------------------------------------------------
    // time
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addLocalTimeRequirement(RecipePrimer primer, long mod, long min, long max) {
        requireTime(primer, IOType.INPUT, true, mod, min, max);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addGlobalTimeRequirement(RecipePrimer primer, long mod, long min, long max) {
        requireTime(primer, IOType.INPUT, false, mod, min, max);
        return primer;
    }

    private static void requireTime(RecipePrimer primer, IOType io, boolean local, long mod, long min, long max) {
        primer.appendComponent(new RequirementDaylight(io, min, max, mod, local));
    }

    //----------------------------------------------------------------------------------------------
    // weather
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addClearWeatherRequirement(RecipePrimer primer) {
        requireWeather(primer, IOType.INPUT, RequirementWeather.Type.CLEAR);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addRainWeatherRequirement(RecipePrimer primer) {
        requireWeather(primer, IOType.INPUT, RequirementWeather.Type.RAIN);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addSnowWeatherRequirement(RecipePrimer primer) {
        requireWeather(primer, IOType.INPUT, RequirementWeather.Type.SNOW);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addStormWeatherRequirement(RecipePrimer primer) {
        requireWeather(primer, IOType.INPUT, RequirementWeather.Type.STORM);
        return primer;
    }

    private static void requireWeather(RecipePrimer primer, IOType io, RequirementWeather.Type type) {
        primer.appendComponent(new RequirementWeather(io, type));
    }

    //----------------------------------------------------------------------------------------------
    // biome
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addBiomeRequirement(RecipePrimer primer, String[] biomes) {
        requireBiome(primer, IOType.INPUT, biomes);
        return primer;
    }

    private static void requireBiome(RecipePrimer primer, IOType io, String[] biomes) {
        primer.appendComponent(new RequirementBiome(io, biomes));
    }

    //----------------------------------------------------------------------------------------------
    // dimension
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addDimensionRequirement(RecipePrimer primer, int[] dimensions) {
        requireDimension(primer, IOType.INPUT, dimensions);
        return primer;
    }

    private static void requireDimension(RecipePrimer primer, IOType io, int[] dimensions) {
        primer.appendComponent(new RequirementDimension(io, dimensions));
    }

    //----------------------------------------------------------------------------------------------
    // anchor
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addAnchor(RecipePrimer primer, String identifier, int time) {
        requireAnchor(primer, IOType.INPUT, identifier, time);
        return primer;
    }

    private static void requireAnchor(RecipePrimer primer, IOType io, String identifier, int time) {
        primer.appendComponent(new RequirementAnchor(io, identifier, time));
    }

    //----------------------------------------------------------------------------------------------
    // position
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addPositionRequirement(RecipePrimer primer, float x, float y, float z) {
        requirePosition(primer, IOType.INPUT, x, x, y, y, z, z);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addAreaRequirement(RecipePrimer primer, float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
        requirePosition(primer, IOType.INPUT, xMin, xMax, yMin, yMax, zMin, zMax);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addYRequirement(RecipePrimer primer, float yMin, float yMax) {
        requirePosition(primer, IOType.INPUT, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, yMin, yMax, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer setDistance(RecipePrimer primer, float distanceMin, float distanceMax) {
        runOnLastRequirement(primer,RequirementPosition.class,"setDistance", (requirement) -> {
            requirement.distanceMin = distanceMin;
            requirement.distanceMax = distanceMax;
        });
        return primer;
    }

    @ZenMethod
    public static RecipePrimer setAnchor(RecipePrimer primer, AnchorTypeWrapper anchor) {
        runOnLastRequirement(primer,RequirementPosition.class,"setDistance", (requirement) -> {
            requirement.anchor = anchor.getInternal();
        });
        return primer;
    }

    private static void requirePosition(RecipePrimer primer, IOType io, float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
        primer.appendComponent(new RequirementPosition(io,xMin,xMax,yMin,yMax,zMin,zMax,0,0, AnchorType.DEFAULT));
    }

    //----------------------------------------------------------------------------------------------
    // toolbox
    //----------------------------------------------------------------------------------------------
    private static <T extends ComponentRequirement> void runOnLastRequirement(RecipePrimer primer, Class<T> requiredClass, String method, Consumer<T> consumer) {
        ComponentRequirement last = getLastRequirement(primer);
        if(requiredClass.isInstance(last))
            consumer.accept((T) last);
        else
            CraftTweakerAPI.logError("Wrong component to call "+method+". (Expected: "+requiredClass+", Got: "+last.getClass()+")");
    }

    private static ComponentRequirement getLastRequirement(RecipePrimer primer) {
        List<ComponentRequirement> requirementList = primer.getComponents();
        if(requirementList instanceof LinkedList)
            return ((LinkedList<ComponentRequirement>) requirementList).getLast();
        return requirementList.get(requirementList.size()-1);
    }
}
