package modulardiversity;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponent;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponentCustomName;
import modulardiversity.block.*;
import modulardiversity.components.*;
import modulardiversity.components.ComponentLaser;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.tile.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class Registry {
    private static ArrayList<Block> BLOCKS = new ArrayList<>();
    private static ArrayList<Item> ITEMS = new ArrayList<>();

    public static void preInit()
    {
        MinecraftForge.EVENT_BUS.register(Registry.class);
        registerBlocks();
        registerTileEntities();
    }

    public static void registerBlocks()
    {
        if(ModularDiversity.EmbersLoaded) {
            BlockEmberInputHatch emberInputHatch = new BlockEmberInputHatch();
            BlockEmberOutputHatch emberOutputHatch = new BlockEmberOutputHatch();

            registerBlock("blockemberinputhatch",emberInputHatch, new ItemBlockMachineComponentCustomName(emberInputHatch));
            registerBlock("blockemberoutputhatch",emberOutputHatch, new ItemBlockMachineComponentCustomName(emberOutputHatch));
        }

        if(ModularDiversity.ImmersivePetroleumLoaded) {
            BlockJackHatch jackHatch = new BlockJackHatch();

            registerBlock("blockjackhatch",jackHatch, new ItemBlockMachineComponent(jackHatch));
        }

        if(ModularDiversity.BuildcraftLoaded) {
            BlockLaserHatch laserHatch = new BlockLaserHatch();

            registerBlock("blocklaserhatch",laserHatch, new ItemBlockMachineComponent(laserHatch));
        }

        /*if(ModularDiversity.PneumaticCraftLoaded) {
            BlockPneumaticInput pneumaticInputHatch = new BlockPneumaticInput();
            BlockPneumaticConsumer pneumaticConsumerHatch = new BlockPneumaticConsumer();
            BlockPneumaticOutput pneumaticOutput = new BlockPneumaticOutput();

            registerBlock("blockpneumaticinputhatch",pneumaticInputHatch, new ItemBlockMachineComponent(pneumaticInputHatch));
            registerBlock("blockpneumaticconsumerhatch",pneumaticConsumerHatch, new ItemBlockMachineComponent(pneumaticConsumerHatch));
            registerBlock("blockpneumaticoutputhatch",pneumaticOutput, new ItemBlockMachineComponent(pneumaticOutput));
        }*/

        if(ModularDiversity.BetterWithModsLoaded) {
            BlockMechInputHatch mechInputHatch = new BlockMechInputHatch();
            BlockMechCrankHatch crankInputHatch = new BlockMechCrankHatch();
            BlockMechOutputHatch mechOutputHatch = new BlockMechOutputHatch(1);
            BlockMechOutputHatch mechSteelOutputHatch = new BlockMechOutputHatch(50);

            registerBlock("blockmechcrankhatch",crankInputHatch, new ItemBlockMachineComponent(crankInputHatch));
            registerBlock("blockmechinputhatch",mechInputHatch, new ItemBlockMachineComponent(mechInputHatch));
            registerBlock("blockmechoutputhatch",mechOutputHatch, new ItemBlockMachineComponent(mechOutputHatch));
            registerBlock("blockmechsteeloutputhatch",mechSteelOutputHatch, new ItemBlockMachineComponent(mechSteelOutputHatch));
        }
        
        if(ModularDiversity.ProdigyTechLoaded) {
        	BlockHotAirInputHatch hotAirInputHatch = new BlockHotAirInputHatch();
        	BlockHotAirOutputHatch hotAirOuputHatch = new BlockHotAirOutputHatch();

            registerBlock("blockhotairinputhatch", hotAirInputHatch, new ItemBlockMachineComponent(hotAirInputHatch));
            registerBlock("blockhotairoutputhatch",hotAirOuputHatch, new ItemBlockMachineComponent(hotAirOuputHatch));
        }

        if (ModularDiversity.MekanismLoaded) {
            BlockMekLaserAcceptor mekLaserAcceptor = new BlockMekLaserAcceptor();
            BlockMekHeatInput mekHeatInput = new BlockMekHeatInput();
            BlockMekHeatOutput mekHeatOutput = new BlockMekHeatOutput();

            registerBlock("blockmeklaseracceptor", mekLaserAcceptor, new ItemBlockMachineComponent(mekLaserAcceptor));
            registerBlock("blockmekheatinput", mekHeatInput, new ItemBlockMachineComponent(mekHeatInput));
            registerBlock("blockmekheatoutput", mekHeatOutput, new ItemBlockMachineComponent(mekHeatOutput));
        }

        if(ModularDiversity.MysticalMechanicsLoaded) {
            BlockMysticalMechanicsInput mysticalMechanicsInput = new BlockMysticalMechanicsInput();
            BlockMysticalMechanicsOutput mysticalMechanicsOutput = new BlockMysticalMechanicsOutput();

            registerBlock("blockmystmechinput", mysticalMechanicsInput, new ItemBlockMachineComponent(mysticalMechanicsInput));
            registerBlock("blockmystmechoutput",mysticalMechanicsOutput, new ItemBlockMachineComponent(mysticalMechanicsOutput));
        }

        BlockBiomeDetector blockBiomeDetector = new BlockBiomeDetector();
        BlockDaylightDetector blockDaylightDetector = new BlockDaylightDetector();
        BlockWeatherDetector blockWeatherDetector = new BlockWeatherDetector();

        registerBlock("blockbiomedetector",blockBiomeDetector, new ItemBlockMachineComponent(blockBiomeDetector));
        registerBlock("blockdaylightdetector", blockDaylightDetector, new ItemBlockMachineComponent(blockDaylightDetector));
        registerBlock("blockweatherdetector", blockWeatherDetector, new ItemBlockMachineComponent(blockWeatherDetector));
    }

    public static void registerBlockModels()
    {
        for (Block block : BLOCKS) {
            ModularDiversity.proxy.registerBlockModel(block);
        }
    }

    public static void registerItemModels()
    {
        for (Item item : ITEMS) {
            ModularDiversity.proxy.registerItemModel(item);
        }
    }

    public static void registerBlock(String id,Block block, ItemBlock itemBlock)
    {
        block.setRegistryName(ModularDiversity.MODID,id);
        block.setUnlocalizedName(id);
        BLOCKS.add(block);
        registerItem(id,itemBlock);
    }

    public static void registerItem(String id,Item item)
    {
        item.setRegistryName(ModularDiversity.MODID,id);
        item.setUnlocalizedName(id);
        ITEMS.add(item);
    }

    public static void registerTileEntities()
    {
        if(ModularDiversity.EmbersLoaded) {
            registerTileEntity(TileEmberInputHatch.class);
            registerTileEntity(TileEmberOutputHatch.class);
        }
        if(ModularDiversity.ImmersivePetroleumLoaded) {
            registerTileEntity(TileJackHatch.class);
        }
        if(ModularDiversity.BetterWithModsLoaded) {
            registerTileEntity(TileMechInput.class);
            registerTileEntity(TileMechInputCrank.class);
            registerTileEntity(TileMechOutput.class);
        }
        if (ModularDiversity.BuildcraftLoaded) {
            registerTileEntity(TileLaserInput.class);
        }
        if(ModularDiversity.ProdigyTechLoaded) {
        	registerTileEntity(TileHotAirInput.class);
        	registerTileEntity(TileHotAirOutput.class);
        }
        /*if(ModularDiversity.PneumaticCraftLoaded) {
            registerTileEntity(TilePneumaticInput.class);
            registerTileEntity(TilePneumaticInputConsume.class);
            registerTileEntity(TilePneumaticOutput.class);
        }*/
        if (ModularDiversity.MekanismLoaded) {
            registerTileEntity(TileEntityMekLaserAcceptor.class);
            registerTileEntity(TileEntityMekHeatInput.class);
            registerTileEntity(TileEntityMekHeatOutput.class);
        }
        if (ModularDiversity.MysticalMechanicsLoaded) {
            registerTileEntity(TileMysticalMechanicsInput.class);
            registerTileEntity(TileMysticalMechanicsOutput.class);
        }
        registerTileEntity(TileEntityBiomeDetector.class);
        registerTileEntity(TileEntityDaylightDetector.class);
        registerTileEntity(TileEntityWeatherDetector.class);
    }

    @SubscribeEvent
    public static void registerComponents(RegistryEvent.Register<ComponentType> event) {
        event.getRegistry().register(new ComponentEmber());
        event.getRegistry().register(new ComponentMechanical());
        event.getRegistry().register(new ComponentEmber());
        event.getRegistry().register(new ComponentMana());
        event.getRegistry().register(new ComponentLaser());
        event.getRegistry().register(new ComponentHotAir());
        event.getRegistry().register(new ComponentMekLaser());
        event.getRegistry().register(new ComponentMekHeat());
        event.getRegistry().register(new ComponentMysticalMechanics());
        event.getRegistry().register(new ComponentBiome());
        event.getRegistry().register(new ComponentDimension());
        event.getRegistry().register(new ComponentDaylight());
        event.getRegistry().register(new ComponentWeather());
        event.getRegistry().register(new ComponentReservoir());
        event.getRegistry().register(new ComponentMineral());
        event.getRegistry().register(new ComponentModifier());
        event.getRegistry().register(new ComponentEmberWorld());
        event.getRegistry().register(new ComponentAura());
        event.getRegistry().register(new ComponentPosition());
        event.getRegistry().register(new ComponentAnchor());
    }

    @SubscribeEvent
    public static void registerRequirements(RegistryEvent.Register<RequirementType<?,?>> event) {
        event.getRegistry().register(new RequirementEmber.Type());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
    }

    private static void registerTileEntity(Class<? extends TileEntity> tile)
    {
        GameRegistry.registerTileEntity(tile,tile.getSimpleName().toLowerCase());
    }
}
