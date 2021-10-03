package modulardiversity.block.prop;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EmberHatchSize implements IStringSerializable {

    TINY(100.0D),
    SMALL(800.0D),
    COPPER(4000.0D),
    REINFORCED(8000.0D),
    BIG(16000.0D),
    HUGE(32000.0D),
    REACTOR(64000.0D),
    CRYSTAL(1440000.0D);

    public static final EmberHatchSize[] VALUES = values();

    private final double size;

    EmberHatchSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}