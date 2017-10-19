package lsg.characters;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.RingedKnightArmor;

public class Hero extends Character {

    private static int MAX_ARMOR_PIECES = 3;

    private ArmorItem[] armor;

    public Hero() {
        super();
        this.setName("Gregooninator");
        this.setMaxLife(100);
        this.setLife(this.getMaxLife());
        this.setMaxStamina(50);
        this.setStamina(this.getMaxStamina());
        this.armor = new ArmorItem[MAX_ARMOR_PIECES];
    }

    public Hero(String name) {
        this();
        this.setName(name);
    }

    public void setArmorItem(ArmorItem armorItem, int slot) {

        if(slot > 0 && slot <= MAX_ARMOR_PIECES) {
            armor[slot-1] = armorItem;
        }

    }

    public float getTotalArmor() {

        float totalArmor = 0;

        for(int i = 0; i < MAX_ARMOR_PIECES; i++) {

            if(armor[i] != null) {

                totalArmor += armor[i].getArmorValue();

            }

        }

        return totalArmor;

    }

    public String armorToString() {

        String result = String.format("ARMOR ", 1);

        for(int i = 0; i < MAX_ARMOR_PIECES; i++) {

            result = result.concat(String.format(" %2d:%-30s" , i+1, ((armor[i] != null) ? armor[i].toString() : "empty")));

        }

        result = result.concat("TOTAL:" + getTotalArmor());

        return(result);

    }

    public ArmorItem[] getArmorItems() {

        int nbArmoItems = 0;

        for(int i = 0; i < MAX_ARMOR_PIECES; i++) {

            if(armor[i] != null) {

                nbArmoItems++;

            }

        }

        ArmorItem[] armorItems = new ArmorItem[nbArmoItems];

        for(int i = 0, j = 0; i < MAX_ARMOR_PIECES; i++) {

            if(armor[i] != null) {

                armorItems[j] = armor[i];
                j++;

            }

        }

        return armorItems;

    }

    @Override
    public float computeProtection() {
        return getTotalArmor();
    }

    public static void main(String[] args) {

        Hero hero = new Hero();

        hero.setArmorItem(new BlackWitchVeil(), 1);
        hero.setArmorItem(new RingedKnightArmor(), 3);

        System.out.println(hero.armorToString());

    }

}
