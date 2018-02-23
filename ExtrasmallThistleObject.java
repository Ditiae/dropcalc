//Made by Hilarious
//Drop Rate Calculator for Imagine-PS
//Version 3.13
import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.*;
class Main {
 public static void main(String[] args) {
  String dropChanceS = "";
  double dropChanceD = 0;
  double dropChanceYo = 0;
  int dropRateB = 0, dropRateD = 0;
  double dropPotential = 0.0;
  DecimalFormat df = new DecimalFormat("#.##");
  Scanner scan = new Scanner(System.in);
  try {
   System.out.println("Enter your current drop rate bonus including double drop rate (Ex: 200% would be 200): ");
   dropRateB = scan.nextInt();
   System.out.println("Enter current drop rate denominator (Ex: 1/100 would be 100): ");
   dropRateD = scan.nextInt();
   System.out.println("Enter current drop potential (Ex: 2% would be 2): ");
   dropPotential = scan.nextDouble();
  } catch (InputMismatchException e) {
   System.out.println("Ya dun goofed! Follow the examples and input correct format.");
   main(null);
  }
  if (dropRateD < 100 && dropPotential != 0.0) {
   dropChanceS = "Your current drop rate is: " + convertDecimalToFraction((1 / (dropRateD / dropPotential)));
   dropChanceYo = (dropRateD / dropPotential);
   dropChanceD = dropRateD / (1 + ((double) dropRateB / 100));
  } else if (dropRateD < 100 && dropPotential == 0.0) {
   dropChanceS = "Your current drop rate is: 1/" + (int) dropRateD;
   dropChanceYo = dropRateD;
   dropChanceD = dropRateD / (1 + ((double) dropRateB / 100));
  } else if (dropRateD >= 100 && dropPotential != 0.0) {
   if (dropPotential == 1) {
    dropChanceS = "Your current drop rate is: 1/100";
    dropChanceD = dropRateD / (1 + ((double) dropRateB / 100));
   } else {
    dropChanceS = "Your current drop rate is: " + convertDecimalToFraction((1 / (100 / dropPotential)));
    dropChanceYo = (100 / dropPotential);
    dropChanceD = dropRateD / (1 + ((double) dropRateB / 100));
   }
  } else if (dropRateD >= 100 && dropPotential == 0.0) {
   dropChanceS = "Your current drop rate is: 1/" + (int) dropRateD;
   dropChanceYo = (int) dropRateD;
   dropChanceD = dropRateD / (1 + ((double) dropRateB / 100));
  }
  if (dropChanceYo > dropChanceD || dropChanceYo == 0) {
   System.out.println("Your current drop rate is: 1/" + (int) dropChanceD);
   double chance = 1 / dropChanceD;
   for (int i = 10; i <= 100; i += 10) {
    double chanceP = 100 - (Math.pow(1 - chance, i) * 100);
    System.out.println("You have a " + df.format(chanceP) + "% chance to get this drop in the next " + i + " kills.");
   }
  } else {
   System.out.println(dropChanceS);
   double chance = 1 / dropChanceYo;
   for (int i = 10; i <= 100; i += 10) {
    double chanceP = 100 - (Math.pow(1 - chance, i) * 100);
    System.out.println("You have a " + df.format(chanceP) + "% chance to get this drop in the next " + i + " kills.");
   }
  }
  main(null);
 }

 static private String convertDecimalToFraction(double x) {
  if (x < 0) {
   return "-" + convertDecimalToFraction(-x);
  }
  double tolerance = 1.0E-6;
  double h1 = 1, h2 = 0, k1 = 0, k2 = 1, b = x;
  do {
   double a = Math.floor(b), aux = h1;
   h1 = a * h1 + h2;
   h2 = aux;
   aux = k1;
   k1 = a * k1 + k2;
   k2 = aux;
   b = 1 / (b - a);
  } while (Math.abs(x - h1 / k1) > x * tolerance);
  return 1 + "/" + Math.round((int) Math.ceil(k1 / h1));
 }
}