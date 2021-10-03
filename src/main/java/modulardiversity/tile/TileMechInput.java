package modulardiversity.tile;

import betterwithmods.api.BWMAPI;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.tile.base.TileEntityMech;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

public class TileMechInput extends TileEntityMech {

    public TileMechInput(int maxLevel) {
        super(maxLevel);
    }

    @Override
    public int getMechanicalOutput(EnumFacing enumFacing) {
        return -1;
    }

    @Override
    public int getMechanicalInput(EnumFacing enumFacing) {
        return BWMAPI.IMPLEMENTATION.getPowerOutput(world, pos.offset(enumFacing), enumFacing.getOpposite());
    }

    public int getCurrentEnergy() {
        return calculateInput();
    }

    @Override
    public boolean consume(RequirementMechanical.ResourceToken token, boolean doConsume) {
        if (getCurrentEnergy() >= token.getRequiredLevel()) {
            token.setRequiredlevelMet();
        }
        return true;
    }

    @Override
    public boolean generate(RequirementMechanical.ResourceToken token, boolean doGenerate) {
        return false;
    }

    @Nullable
    @Override
    public MachineComponent<ICraftingResourceHolder<RequirementMechanical.ResourceToken>> provideComponent() {
        return new MachineComponents.MechanicalHatch(IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMechanical.ResourceToken> getContainerProvider() {
                return TileMechInput.this;
            }
        };
    }
}
