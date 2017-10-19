package lsg;

import lsg.characters.*;
import lsg.weapons.Claw;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.lang.Character;
import java.util.Scanner;

public class LearningSoulsGame {

    private lsg.characters.Character hero = new Hero();

    private lsg.characters.Character monster = new Monster();

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        LearningSoulsGame learningSoulsGame = new LearningSoulsGame();

        learningSoulsGame.play_v1();

    }

    public void init() {

        hero.setWeapon(new Sword());
        monster.setWeapon(new Claw());

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

        System.out.println("\n--- " + attacked.getName() + " WINS !!! ---");

    }

    public void play_v1() {

        init();
        fight1v1();

    }

    public void refresh() {

        hero.printStats();
        monster.printStats();

    }

}
