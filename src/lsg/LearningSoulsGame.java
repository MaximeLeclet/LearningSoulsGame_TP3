package lsg;

import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.RingOfDeath;
import lsg.buffs.rings.RingOfSwords;
import lsg.characters.*;
import lsg.weapons.Claw;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.lang.Character;
import java.util.Scanner;

public class LearningSoulsGame {

    private lsg.characters.Hero hero = new Hero();

    private lsg.characters.Monster monster = new Monster();

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        LearningSoulsGame learningSoulsGame = new LearningSoulsGame();

        learningSoulsGame.play_v3();

    }

    public void init() {

        hero.setWeapon(new Sword());
        monster.setWeapon(new Claw());
        hero.setArmorItem(new BlackWitchVeil(), 1);
        hero.setArmorItem(new DragonSlayerLeggings(), 2);
        hero.setArmorItem(new RingedKnightArmor(), 3);
        RingOfDeath ringOfDeath = new RingOfDeath();
        RingOfSwords ringOfSwords = new RingOfSwords();
        hero.setRing(ringOfDeath, 1);
        ringOfDeath.setHero(hero);
        ringOfSwords.setHero(hero);
        hero.setRing(ringOfSwords, 2);
        monster = new Lycanthrope();

    }

    public void fight1v1() {

        int attack;
        int damages;
        lsg.characters.Character attacker = hero;
        lsg.characters.Character attacked = monster;
        lsg.characters.Character transfer;

        while(hero.isAlive() && monster.isAlive()) {

            refresh();

            System.out.print("\nHit enter key for next move > ");
            scanner.nextLine();

            attack = attacker.attack();
            damages = attacked.getHitWith(attack);

            System.out.println("\n" + attacker.getName() + " attacks " + attacked.getName() + " with " + attacker.getWeapon().getName() + " (ATTACK:" + attack + " | DMG : " + damages + ")");

            transfer = attacked;
            attacked = attacker;
            attacker = transfer;

        }

        refresh();

        System.out.println("\n--- " + attacked.getName() + " WINS !!! ---");

    }

    public void play_v1() {

        init();
        fight1v1();

    }

    public void play_v2() {

        init();
        fight1v1();

    }

    public void play_v3() {

        init();
        fight1v1();

    }

    public void refresh() {

        hero.printStats();
        monster.printStats();

    }

}
