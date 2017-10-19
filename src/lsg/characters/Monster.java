package lsg.characters;

public class Monster extends Character {

    private static int INSTANCES_COUNT = 0;
    private float skinThickness = 20;

    public Monster() {
        super();
        INSTANCES_COUNT++;
        this.setName("Monster_" + INSTANCES_COUNT);
        this.setMaxLife(10);
        this.setLife(this.getMaxLife());
        this.setMaxStamina(10);
        this.setStamina(this.getMaxStamina());
    }

    public Monster(String name) {
        this();
        this.setName(name);
    }

    public float getSkinThickness() {
        return skinThickness;
    }

    private void setSkinThickness(float skinThickness) {
        this.skinThickness = skinThickness;
    }

    @Override
    public float computeProtection() {
        return skinThickness;
    }
}
