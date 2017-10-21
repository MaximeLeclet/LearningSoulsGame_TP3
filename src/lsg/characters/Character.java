package lsg.characters;

import lsg.buffs.BuffItem;
import lsg.helpers.*;
import lsg.weapons.*;

import java.util.Locale;

public abstract class Character {

    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;
    private Dice dice;
    private Weapon weapon;

    public Character() {

        dice = new Dice(101);

    }

    public Character(String name) {
        this();
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    protected void setLife(int life) {
        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    protected void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getStamina() {
        return stamina;
    }

    protected void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    protected void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isAlive() {
        return(this.getLife()>0);
    }

    public int attack() {
        return this.attackWith(weapon);
    }

    private int attackWith(Weapon weapon) {

        weapon.use();

        if(weapon.isBroken() || this.getStamina() <= 0) {
            return 0;
        }
        else {

            int precision = this.dice.roll();

            int degats = weapon.getMinDamage() + (weapon.getMaxDamage() - weapon.getMinDamage()) * precision/100;

            if(this.getStamina() < weapon.getStamCost()) {

                float proportion = ((float)this.getStamina()/(float)weapon.getStamCost());

                degats = Math.round(degats * proportion);

                this.setStamina(0);

            }
            else {
                this.setStamina(this.getStamina()-weapon.getStamCost());
            }

            degats = degats + Math.round(degats * computeBuff()/100.0f);

            return degats;

        }

    }

    public int getHitWith(int value) {

        float protection = this.computeProtection();

        int degats = (protection > 100.0) ? 0 : Math.round(value - (value * (protection/100.f)));

        degats = (this.getLife()-degats < 0) ? this.getLife() : degats;

        this.setLife(this.getLife()-degats);
        return degats;

    }

    public abstract float computeProtection();

    public abstract float computeBuff();

    public void printStats() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String LIFE = String.format("%5d", this.getLife());
        String STAMINA = String.format("%5d",this.getStamina());
        return (String.format(Locale.US,"%-20s %-20s LIFE:%-10s STAMINA:%-10s PROTECTION: %-10s BUFF: %-10s", ("[ " + this.getClass().getSimpleName() + " ]"), this.getName(), LIFE, STAMINA, computeProtection(), computeBuff()) + (this.isAlive() ? "(ALIVE)" : "(DEAD)"));
    }

}
