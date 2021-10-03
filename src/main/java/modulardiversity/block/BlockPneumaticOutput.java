package modulardiversity.block;

import modulardiversity.tile.TilePneumaticOutput;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPneumaticOutput extends BlockPneumatic {

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TilePneumaticOutput(state.getValue(TIER), 1000);
    }

}
