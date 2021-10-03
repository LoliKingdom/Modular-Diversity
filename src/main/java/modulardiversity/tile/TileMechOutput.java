package modulardiversity.tile;

import betterwithmods.api.BWMAPI;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.tile.base.TileEntityMech;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;

public class TileMechOutput extends TileEntityMech implements ITickable {

    public TileMechOutput(int maxLevel) {
        super(maxLevel);
    }

    public int currentPowerLevel;
    public int keepPowerTicks;

    @Override
    public int getMechanicalOutput(EnumFacing facing) {
        return BWMAPI.IMPLEMENTATION.isAxle(world, pos.offset(facing), facing.getOpposite()) ? currentPowerLevel : -1;
    }

    @Override
    public int getMechanicalInput(EnumFacing enumFacing) {
        return 0;
    }

    public void setCurrentEnergy(int i) {
        currentPowerLevel = i;
        keepPowerTicks = 20;
    }

    @Override
    public void update() {
        keepPowerTicks = Math.max(0,keepPowerTicks-1);
        if(keepPowerTicks <= 0 && currentPowerLevel > 0)
        {
            currentPowerLevel = 0;
        }
    }

    @Override
    public boolean consume(RequirementMechanical.ResourceToken token, boolean doConsume) {
        return false;
    }

    @Override
    public boolean generate(RequirementMechanical.ResourceToken token, boolean doGenerate) {
        token.setRequiredlevelMet();
        if (doGenerate) {
            setCurrentEnergy(token.getRequiredLevel());
        }
        return true;
    }

    @Nullable
    @Override
    public MachineComponent<ICraftingResourceHolder<RequirementMechanical.ResourceToken>> provideComponent() {
        return new MachineComponents.MechanicalHatch(IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMechanical.ResourceToken> getContainerProvider() {
                return TileMechOutput.this;
            }
        };
    }
}
