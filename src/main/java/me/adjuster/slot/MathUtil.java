package me.adjuster.slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MathUtil {
    public static int getOnes(String numbers){
        int ones=0;
        for(String s : numbers.split("")){
            if(s.equals("1")){
                ones++;
            }
        }
        return ones;
    }
    public static List<Float> sort(List<Float> unsorted){
        List<Float> sorted = new ArrayList<>();
        while(unsorted.size()>0){
            sorted.add(unsorted.get(minIndex(unsorted)));
            unsorted.remove(unsorted.get(minIndex(unsorted)));
        }
        return sorted;
    }
    public static String toDropper(float chance){
        for(float unstackable=1;unstackable<=8;unstackable++){
            for(float stackable=0;stackable<=8;stackable++){
                if(unstackable+stackable<=9){
                    float thechance = unstackable/(stackable+unstackable);
                  if(thechance == chance){
                      return "Du brauchst "+unstackable+" untackbare Items und "+stackable+" stackbare Items!";
                  }
                }
            }
        }
        return "Nicht möglich!";
    }
    public static int minIndex(List<Float> floats){
        float min = floats.get(0);
        int index = 0;
        for(int i=0;i<floats.size();i++){
            if(floats.get(i)<min){
                min = floats.get(i);
                index = i;
            }
        }
        return index;
    }
    public static List<Float> getPossibleChancesForADropper(){
        List<Float> chances = new ArrayList<>();
        HashMap known = new HashMap();
        for(float unstackable=1;unstackable<=8;unstackable++){
            for(float stackable=0;stackable<=8;stackable++){
                if(unstackable+stackable<=9){
                    float chance = unstackable/(stackable+unstackable);
                    if(!known.containsKey(chance)){
                        known.put(chance,true);
                        chances.add(chance);
                    }
                }
            }
        }
        return sort(chances);
    }
    public static float getDistBetweenNumbers(float a,float b){
        return Math.abs(a-b);
    }
}
