package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.block.BlockEmberHatch;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import teamroots.embers.api.capabilities.EmbersCapabilities;
import teamroots.embers.power.DefaultEmberCapability;

public abstract class TileEntityEmber extends TileColorableMachineComponent implements MachineComponentTile, ICraftingResourceHolder<RequirementEmber.ResourceToken> {

    public final DefaultEmberCapability capability = new DefaultEmberCapability();

    @Override
    public void setWorld(World worldIn) {
        super.setWorld(worldIn);
        if (this.capability.getEmberCapacity() != 0.0D && this.pos != null) {
            this.capability.setEmberCapacity(this.world.getBlockState(this.pos).getValue(BlockEmberHatch.BUS_TYPE).get());
        }
    }

    @Override
    public void setPos(BlockPos posIn) {
        super.setPos(posIn);
        if (this.capability.getEmberCapacity() != 0.0D && this.world != null) {
            this.capability.setEmberCapacity(this.world.getBlockState(this.pos).getValue(BlockEmberHatch.BUS_TYPE).get());
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        capability.readFromNBT(compound.getCompoundTag("ember"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        NBTTagCompound emberTag = new NBTTagCompound();
        capability.writeToNBT(emberTag);
        compound.setTag("ember", emberTag);
    }

    @Override
    public boolean consume(RequirementEmber.ResourceToken token, boolean doConsume) {
        double emberConsumed = capability.removeAmount(token.getEmber(), false);
        token.setEmber(token.getEmber() - emberConsumed);
        if (doConsume) {
            capability.removeAmount(emberConsumed, true);
        }
        return emberConsumed > 0;
    }

    @Override
    public boolean generate(RequirementEmber.ResourceToken token, boolean doGenerate) {
        double emberAdded = capability.addAmount(token.getEmber(), false);
        token.setEmber(token.getEmber() - emberAdded);
        if (doGenerate) {
            capability.addAmount(emberAdded, true);
        }
        return emberAdded > 0;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == EmbersCapabilities.EMBER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == EmbersCapabilities.EMBER_CAPABILITY ? (T) this.capability : super.getCapability(capability, facing);
    }

    @Override
    public String getInputProblem(RequirementEmber.ResourceToken token) {
        return "craftcheck.ember.input";
    }

    @Override
    public String getOutputProblem(RequirementEmber.ResourceToken token) {
        return "craftcheck.ember.output";
    }
}
