package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.util.HybridTank;
import modulardiversity.util.ReservoirTank;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class TileJackHatch extends TileColorableMachineComponent {
    private ReservoirTank tank;
    private IOType ioType = IOType.INPUT;

    public TileJackHatch() {
        this.tank = new ReservoirTank();
        this.tank.setTileEntity(this);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        NBTTagCompound tankTag = new NBTTagCompound();
        tank.writeToNBT(tankTag);
        compound.setTag("tank",tankTag);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        NBTTagCompound tankTag = compound.getCompoundTag("tank");
        tank.readFromNBT(tankTag);
    }
}
