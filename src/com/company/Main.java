package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {270, 260, 250, 240};
    public static int[] heroesDamage = {25, 15, 20, 30};
    public static String[] heroesAttackType = {"Physical", "Magical", "kinetic", "Archer"};
    public static int roundCounter = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()){
            round();
        }
    }
    public static void round(){
        if (bossHealth > 0){
            chageDefenceType();
            bossHits();
        }
        heroesHit();
        printStatistics();
    }
    public static void chageDefenceType(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefenceType);
    }
    public static void printStatistics(){
        System.out.println("_____________________");
        System.out.println("Round: " + roundCounter);
        roundCounter++;
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesDamage.length; i++){
            System.out.println(heroesAttackType[i] + "health; " + heroesHealth[i]);
        }
        System.out.println("_______________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++){
            if (heroesHealth[i] > 0 ){
                if (heroesHealth[i] > 0 && bossHealth > 0){
                    bossHealth = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;

                }
            }
        }
    }
    public static void heroesHit(){
        for (int i = 0; i < heroesHealth.length; i++){
            if (heroesHealth[i] > 0 && bossHealth > 0){
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int randomValue = random.nextInt(10);
                    heroesDamage[i] = heroesDamage[i] - randomValue;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            } else {
                if (bossHealth - heroesDamage[i] < 0){
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }
    public static boolean isGameOver(){
        if (bossHealth <= 0){
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
}
