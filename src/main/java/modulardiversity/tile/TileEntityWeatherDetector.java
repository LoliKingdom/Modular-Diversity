package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static modulardiversity.block.BlockWeatherDetector.WEATHER_TYPE;

public class TileEntityWeatherDetector extends TileColorableMachineComponent implements ITickable {

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setBoolean("input", true);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void update() {
        int weather = (world.isRaining()) ? (world.isThundering()) ? 2 : 1 : 0;
        world.setBlockState(pos, world.getBlockState(pos).getBlock().getDefaultState().withProperty(WEATHER_TYPE, weather));
    }
}
