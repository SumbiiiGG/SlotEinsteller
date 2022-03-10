package me.adjuster.slot;

import java.util.ArrayList;
import java.util.List;

public class Slot {
    //Im sinne von prices was man gewinnt nicht was man zahlt!
    public List<Float> prices;

    public Slot(List<Float> prices) {
        this.prices = prices;
    }

    public List<String> getAllCombinations() {
        List<String> combinations = new ArrayList<>();
        int highest = (int) (Math.pow(2, prices.size()));
        for (int i = 0; i < highest; i++) {
            combinations.add(Integer.toBinaryString(i));
        }
        return combinations;
    }

    public float getChanceForCombination(String combination, List<Float> droppers) {
        float chance = 1;
        String[] numbers = combination.split("");
        for (int i = 0; i < droppers.size(); i++) {
            if (i < numbers.length) {
                if (numbers[numbers.length - i - 1].equals("1")) {
                    chance *= droppers.get(i);
                    continue;
                }
                chance *= (1 - droppers.get(i));
                continue;
            }
            chance *= (1 - droppers.get(i));
        }
        return chance;
    }

    public List<Float> getChancesExact(List<Float> droppers, List<String> combinations) {
        List<DropperChance> chances = new ArrayList<>();
        for (int i = 0; i <= droppers.size(); i++) {
            chances.add(new DropperChance(0));
        }
        for (String combination : combinations) {
            float chance = getChanceForCombination(combination, droppers);
            chances.get(MathUtil.getOnes(combination)).addChance(chance);
        }
        List<Float> exactChances = new ArrayList<>();
        for (DropperChance dc : chances) {
            exactChances.add(dc.chance);
        }
        return exactChances;
    }

    public float getExpectedProfit(List<Float> droppers) {
        List<String> combinations = getAllCombinations();
        List<Float> exact = getChancesExact(droppers, combinations);
        float expected = 0;
        for (int i = 0; i < prices.size(); i++) {
            float price = 0;
            for (int j = i; j >= 0; j--) {
                price += prices.get(j);
            }
            expected += price * exact.get(i + 1);
        }
        return expected;
    }
}
