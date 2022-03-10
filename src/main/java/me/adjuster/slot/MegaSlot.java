package me.adjuster.slot;

import java.util.List;

public class MegaSlot {
    public List<Float> chances;
    public List<Float> prices;

    public MegaSlot(List<Float> chances, List<Float> prices) {
        this.chances = chances;
        this.prices = prices;
    }

    public float getChance(int lamp) {
        float chance = 1;
        for (int i = 0; i < lamp; i++) {
            chance *= chances.get(i);
        }
        if (lamp < chances.size()) {
            chance *= (1 - chances.get(lamp));
        }
        return chance;
    }


    public float getTotalExpectedValue() {
        float total = 0;
        for (int i = 1; i <= prices.size(); i++) {
            total += getTotalPrice(i) * getChance(i);
        }
        return total;
    }

    public float getTotalPrice(int lamp) {
        float total = 0;
        for (int i = 0; i < lamp; i++) {
            total += prices.get(i);
        }
        return total;
    }
}
