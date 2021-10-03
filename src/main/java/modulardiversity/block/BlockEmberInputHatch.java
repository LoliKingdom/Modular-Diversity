package modulardiversity.block;

import modulardiversity.tile.TileEmberInputHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockEmberInputHatch extends BlockEmberHatch {

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEmberInputHatch();
    }

}
