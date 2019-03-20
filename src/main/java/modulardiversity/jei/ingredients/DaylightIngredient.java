package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

import java.util.ArrayList;

public class DaylightIngredient implements IFakeIngredient {
    long timeMin;
    long timeMax;
    long timeModulo;

    public DaylightIngredient(long timeMin, long timeMax, long timeModulo) {
        this.timeMin = timeMin;
        this.timeMax = timeMax;
        this.timeModulo = timeModulo;
    }

    public String getClockFrame(int time) {
        int num = (int) Math.floor((time - 6000) / 375);
        if (num < 0) {
            num += 64;
        }
        String numString = Integer.toString(num);
        if (num < 10) numString = "0" + numString;
        return numString;
    }

    public static String getFormattedTime(long time) {
        boolean am = true;
        double cfg = time;
        cfg+=6000;
        if (cfg > 23999) cfg -= 24000;
        if (cfg >= 12000) {
            am = false;
            cfg -= 12000;
        }
        cfg = Math.floor(cfg / 10) / 100;
        if (cfg > 12) cfg -= 12;

        int cfgi = (int) Math.floor(cfg);
        int cfgj = (int) Math.floor((cfg - cfgi)*60);

        return Integer.toString(cfgi) + (cfgj < 10 ? ":0" : ":") + Integer.toString(cfgj) + (am ? " a.m." : " p.m.");
    }

    @Override
    public String getDisplayName() {
        return "Time Required: Between " + getFormattedTime(timeMin) + " : " + getFormattedTime(timeMax);
    }

    @Override
    public String getUniqueID() {
        return "daylight";
    }
}
