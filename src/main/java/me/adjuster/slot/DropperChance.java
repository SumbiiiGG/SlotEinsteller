package me.adjuster.slot;

public class DropperChance {
    public float chance;
    public DropperChance(float chance){
        this.chance = chance;
    }
    public void addChance(float amount){
        chance+=amount;
    }
}
