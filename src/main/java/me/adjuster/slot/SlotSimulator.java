package me.adjuster.slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotSimulator {
    //Diese classe dient dazu zu simulieren ob meine ergbenisse in etwa stimmen
    public List<Float> chances;
    public List<Float> prices;
    public SlotSimulator(List<Float> chances,List<Float> prices){
        this.chances = chances;
        this.prices=prices;
    }
    public void simulate(int iterations){
        List<DropperChance> result = new ArrayList<>();
        List<Float> combinePrices = new ArrayList<>();
        for(int i=0;i<prices.size();i++){
            float price = 0;
            for(int j=i;j>=0;j--){
                price+=prices.get(j);
            }
            combinePrices.add(price);
        }
        for(int j=0;j<=chances.size();j++){
           result.add(new DropperChance(0));
        }
        Random random = new Random();
        for(int i=0;i<iterations;i++){
            int counter = 0;
            for(int j=0;j<chances.size();j++){
                if(random.nextFloat() <= chances.get(j)){
                    counter++;
                }
            }
            result.get(counter).addChance(1);
        }
        float total = 0;
        for(int i=1;i<result.size();i++){
            System.out.println(i +" "+result.get(i).chance +" Win is:"+combinePrices.get(i-1)+" Total win is:"+result.get(i).chance * combinePrices.get(i-1));
            total += (result.get(i).chance * combinePrices.get(i-1));
        }
        System.out.println("The Total Amount Made is "+total);
        System.out.println("Avaradge = "+(total/iterations));
    }
}
