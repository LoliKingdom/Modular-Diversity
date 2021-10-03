package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementAir;
import modulardiversity.tile.base.TileEntityPneumaticBase;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nullable;

public class TilePneumaticInput extends TileEntityPneumaticBase {
    public TilePneumaticInput(int tier, int volume) {
        super(IOType.INPUT, tier, volume);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.AirHatch(IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementAir.ResourceToken> getContainerProvider() {
                return null;
            }
        };
    }
}
