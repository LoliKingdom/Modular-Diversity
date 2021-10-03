package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementAir;
import modulardiversity.components.requirements.RequirementMysticalMechanics;
import modulardiversity.tile.base.TileEntityPneumaticBase;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nullable;

public class TilePneumaticOutput extends TileEntityPneumaticBase {
    public TilePneumaticOutput(int tier, int volume) {
        super(tier, volume);
    }

    @Nullable
    @Override
    public MachineComponent<ICraftingResourceHolder<RequirementAir.ResourceToken>> provideComponent() {
        return new MachineComponents.AirHatch(IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementAir.ResourceToken> getContainerProvider() {
                return null;
            }
        };
    }
}
