package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.tile.base.TileEntityEmber;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nullable;

public class TileEmberInputHatch extends TileEntityEmber {

    @Nullable
    @Override
    public MachineComponent<ICraftingResourceHolder<RequirementEmber.ResourceToken>> provideComponent() {
        return new MachineComponents.EmberHatch(IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementEmber.ResourceToken> getContainerProvider() {
                return TileEmberInputHatch.this;
            }
        };
    }
}
