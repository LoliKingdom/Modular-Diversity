package modulardiversity.block;

import modulardiversity.tile.TileEmberOutputHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockEmberOutputHatch extends BlockEmberHatch {

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEmberOutputHatch();
    }

}
