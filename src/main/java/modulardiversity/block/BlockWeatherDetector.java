package modulardiversity.block;

import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import hellfirepvp.modularmachinery.common.block.BlockVariants;
import modulardiversity.tile.TileEntityWeatherDetector;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

// TODO - make this class a lot less shitty.
public class BlockWeatherDetector extends BlockMachineComponent implements BlockVariants {

    public static final PropertyInteger WEATHER_TYPE = PropertyInteger.create("weather", 0, 2);

    public BlockWeatherDetector() {
        super(Material.IRON);
        setHardness(2F);
        setResistance(10F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(CommonProxy.creativeTabModularMachinery);
    }

    //Debug Code
    //When the player right clicks sends a message to chat with the biome
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        /*if (!worldIn.isRemote) {
            int weather = (worldIn.isRaining()) ? (worldIn.isThundering()) ? 2 : 1 : 0;
            playerIn.sendStatusMessage(new TextComponentString("Current Weather: " + Weather.getFormattedWeather(weather)), false);
        }*/
        return true;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(WEATHER_TYPE, (worldIn.isRaining()) ? (worldIn.isThundering()) ? 2 : 1 : 0);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWeatherDetector();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(WEATHER_TYPE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(WEATHER_TYPE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, WEATHER_TYPE);
    }

    @Override
    public Iterable<IBlockState> getValidStates() {
        return blockState.getValidStates();
    }

    @Override
    public String getBlockStateName(IBlockState state) {
        return "weather=" + state.getValue(WEATHER_TYPE);
    }
}
