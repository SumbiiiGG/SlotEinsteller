package me.adjuster.calculator;

import me.adjuster.slot.MathUtil;
import me.adjuster.slot.Slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlotCalculator {
    public static List<Float> dropperPossible = MathUtil.getPossibleChancesForADropper();

    public int getBestOneNumber(Slot slot, float target) {
        int index = 0;
        float best = 2100000000f;
        for (int i = 0; i < dropperPossible.size(); i++) {
            List<Float> droppers = new ArrayList<>();
            for (int j = 0; j < slot.prices.size(); j++) {
                droppers.add(dropperPossible.get(i));
            }
            if (MathUtil.getDistBetweenNumbers(target, slot.getExpectedProfit(droppers)) < MathUtil.getDistBetweenNumbers(best, target)) {
                index = i;
                best = slot.getExpectedProfit(droppers);
            }
        }
        return index;
    }

    public Float[] getClosedOption(Slot slot, float target) {

        Float[] base = new Float[slot.prices.size()];
        boolean getCloser = true;
        int bestOne = getBestOneNumber(slot, target);
        for (int i = 0; i < base.length; i++) {

            base[i] = dropperPossible.get(bestOne);
        }
        base[0] = 1f;
        while (getCloser) {
            getCloser = false;
            for (int i = 1; i < base.length; i++) {
                float expectedProfit = slot.getExpectedProfit(Arrays.asList(base));
                float inc = slot.getExpectedProfit(increased(base, i));
                float dec = slot.getExpectedProfit(decreased(base, i));
                if (MathUtil.getDistBetweenNumbers(target, expectedProfit) > MathUtil.getDistBetweenNumbers(target, inc)) {

                    if (increase(base[i]) > 0) {
                        base[i] = increase(base[i]);
                        getCloser = true;
                    }
                } else if (MathUtil.getDistBetweenNumbers(target, expectedProfit) > MathUtil.getDistBetweenNumbers(target, dec)) {

                    if (decrease(base[i]) > 0) {
                        base[i] = decrease(base[i]);
                        getCloser = true;
                    }
                }
            }
            if (!getCloser) {
                break;
            }
        }
        return base;
    }

    public List<Float> increased(Float[] thebase, int index) {
        Float[] inced = Arrays.copyOf(thebase, thebase.length);
        inced[index] = increase(inced[index]);
        return Arrays.asList(inced);
    }

    public List<Float> decreased(Float[] thebase, int index) {
        Float[] deced = Arrays.copyOf(thebase, thebase.length);
        deced[index] = decrease(deced[index]);
        return Arrays.asList(deced);
    }

    public float increase(float before) {
        int index = dropperPossible.indexOf(before);
        if (index + 1 >= dropperPossible.size()) {
            return 0;
        }
        return dropperPossible.get(index + 1);
    }

    public float decrease(float before) {
        int index = dropperPossible.indexOf(before);
        if (index - 1 < 0) {
            return 0;
        }
        return dropperPossible.get(index - 1);
    }
}
