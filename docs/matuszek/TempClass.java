package hammurabi.docs.matuszek;               // package declaration
import hammurabi.docs.Hammurabi;

import java.util.InputMismatchException;
import java.util.Random;         // imports go here
import java.util.Scanner;

public class TempClass {
    static int totalDeaths = 0, percentDied = 0,acres=1000, year = 0, population = 95, stores = 2800, immigrants = 5, deaths,
            harvest = 3000, yeild = 3, /*acres = harvest / yeild*/ /*eaten = harvest - stores*/eaten=0, landPrice=19, fullPeople, temp;
    static boolean plague = false;
    final static String FINK = "DUE TO THIS EXTREME MISMANAGEMENT YOU HAVE NOT ONLY\n" +
            "BEEN IMPEACHED AND THROWN OUT OF OFFICE BUT YOU HAVE\n" +
            "ALSO BEEN DECLARED PERSONA NON GRATA!!\n";
    // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { // required in every Java program
        new TempClass().playGame();

    }

    public void playGame() {


        for (int i = 1; i <= 10; i++) {
            uprising(population,deaths);
            plagueDeaths(population);

            printSummary();
            immigrants(population,acres,stores);
            askHowManyAcresToBuy(landPrice,stores);
            askHowManyAcresToSell(acres);
            askHowMuchGrainToFeedPeople(stores);
            askHowManyAcresToPlant(acres,population,stores);
            //if seed mod 2 != 1
            grainEatenByRats(stores);
            harvest(acres);
            starvationDeaths(population,deaths);
            newCostOfLand();
        }



    }


        int askHowManyAcresToBuy(int price, int bushels){
            do {
                System.out.print("HOW MANY ACRES DO YOU WISH TO BUY?  ");
                temp = scanner.nextInt();
                if (temp < 0)
                    epicFail(true);
                if (temp * landPrice > stores)
                    System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                            stores + " BUSHELS OF GRAIN. NOW THEN,");
            } while (temp * landPrice > stores);
            acres += temp;
            stores -= temp * landPrice;
            return temp;
        }
        int askHowManyAcresToSell(int acress){

            do {
                System.out.print("HOW MANY ACRES DO YOU WISH TO SELL?  ");

                temp = scanner.nextInt();
                if (temp < 0)
                    epicFail(true);
                if (temp > acres)
                    System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acres + " ACRES. NOW THEN,");
            } while (temp > acres);
            stores += temp * landPrice;
            acres -= temp;
            return temp;
        }

        int askHowMuchGrainToFeedPeople(int bushels) {
            do {
                System.out.print("\nHOW MANY BUSHELS DO YOU WISH TO FEED YOUR PEOPLE?  ");
                temp = scanner.nextInt();
                if (temp < 0)
                    epicFail(true);
                if (temp > stores)
                    System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                            stores + " BUSHELS OF GRAIN. NOW THEN,");
            } while (temp > stores);
            fullPeople = temp / 20;
            stores -= temp;
            return fullPeople;
        }
        int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {

            do {
                System.out.print("\nHOW MANY ACRES DO YOU WISH TO PLANT WITH SEED?  ");
                temp = scanner.nextInt();
                if (temp < 0)
                    epicFail(true);
                if (temp > acres)
                    System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acres + " ACRES. NOW THEN,");
                if (temp / 2 > stores)
                    System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                            stores + " BUSHELS OF GRAIN. NOW THEN,");
                if (temp > population * 10)
                    System.out.println("BUT YOU HAVE ONLY" + population + "PEOPLE TO TEND THE FIELDS. NOW THEN,");
            } while (temp > acres || temp / 2 > stores || temp > population * 10);
            stores -= temp / 2;

            return temp;
        }
    public static void epicFail(boolean x) {
        String reason = "";
        if(x){
            reason = "HAMURABI:  I CANNOT DO WHAT YOU WISH.\n" +
                    "GET YOURSELF ANOTHER STEWARD!!!!!";
        }
        else{
            reason = "YOU STARVED " + deaths + " PEOPLE IN ONE YEAR!!!\n" +
                    FINK;
        }
        System.out.println(reason);
        System.exit(0);
    }
    public static String printSummary() {

        String answer = "\nHammurabi:  I BEG TO REPORT TO YOU,\n" +
                "IN YEAR " + year + ", " + deaths + " PEOPLE STARVED, " + immigrants + " CAME TO THE CITY.\n";

        if (plague) {
            population = population / 2;
            answer += "A HORRIBLE PLAGUE STRUCK!  HALF THE PEOPLE DIED.\n";
        }
        population+=immigrants;
        answer += "POPULATION IS NOW " + population + ".\n" +
                "THE CITY NOW OWNS " + acres + " ACRES.\n" +
                "YOU HARVESTED " + yeild + " BUSHELS PER ACRE.\n" +
                "RATS ATE " + eaten + " BUSHELS.\n" +
                "YOU NOW HAVE " + stores + " BUSHELS IN STORE\n\n" +
                "LAND IS TRADING AT " + landPrice + " BUSHELS PER ACRE.";
        System.out.println(answer);
        year++;

        return answer;

    }
    public int plagueDeaths(int i) {
        int random= (rand.nextInt(99) + 1) ;
        if(random<=15){
            plague=true;
        }
        else{
            plague=false;
        }

        return deaths;
    }

    public int starvationDeaths(int populations, int bushelsFedToPeople) {
        if (population > fullPeople) {
            deaths = population - fullPeople;

            percentDied = ((year - 1) * percentDied + deaths * 100 / population) / year;
            population = fullPeople;
            totalDeaths += deaths;
        }

        return deaths;
    }

    public boolean uprising(int population, int howManyPeopleStarved) {
        if (deaths > .45 * population) {
            epicFail(false);
        }

        return false;
    }

    public int immigrants(int population, int acresOwned, int grainInStorage) {

        if(deaths<1) {
            immigrants = (20 * acres + stores) / (100 * population) + 1;
        }
        return immigrants;
    }

    public int harvest(int acres) {

        yeild= (rand.nextInt(6) + 1);
        return 0;
    }

    public int grainEatenByRats(int i) {

        if(rand.nextInt(100) <= 40){
            eaten = (int) (i*(rand.nextDouble()/5+.1));
            stores-=eaten;
        }
        return eaten;
    }

    public int newCostOfLand() {
        int random;
        int max=23;
        int min=17;
        random= rand.nextInt(max - min + 1) + min;
        landPrice=random;

        return landPrice;
    }





        // declare local variables here: grain, population, etc.
        // statements go after the declations
    }



    //other methods go here

