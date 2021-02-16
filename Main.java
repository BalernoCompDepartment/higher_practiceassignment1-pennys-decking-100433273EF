import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;


class Deck {
    String name;
    double length;
    double width;
    double price;
}


class Main {
  public static void main(String[] args) {
    
    Deck [] Decks = getInfo();
    int userChoice = 0;
//this should be a function 
    while (userChoice != 4) {
      userChoice = getValidChoice();
//to get the choice from the user
//something like .... int getValidChoice() { ...... }
     if (userChoice == 1) {
       cheapestDeck(Decks);
       userChoice = 0;
     } else if (userChoice == 2) {
       certainLength2(Decks);
       userChoice = 0;
     } else if (userChoice == 3) {
       int noOfDecks = greaterArea(Decks);
       System.out.println(noOfDecks + " decks are bigger than the specified area.");
       userChoice = 0;
     }
   }
  } 

public static int getValidChoice() {

  int tempChoice = 0;

  while(!(tempChoice == 1 || tempChoice == 2 || tempChoice == 3 || tempChoice == 4 )) {
        
    tempChoice = Keyboard.getInt("Enter 1 to find the cheapest garden deck \nEnter 2 to display the names of garden decks over a certain length \nEnter 3 to display the number of garden decks that are available over a certain area \nEnter 4 to quit");

    if (!(tempChoice == 1 || tempChoice == 2 || tempChoice == 3 || tempChoice == 4 )) {
      System.out.println("Invalid input");
    }
  }

  return tempChoice;
 
}

public static Deck[] getInfo() {

  int noInFile = 0;
  Scanner fileScanner = null;
  String fileName = "Decks.csv";


  try { 
    fileScanner = new Scanner (new BufferedReader (new FileReader (fileName)));
    fileScanner.useDelimiter("[\\r\\n,]+");
    while (fileScanner.hasNext()) {
      noInFile = noInFile + 1;
      fileScanner.next();
      }
    } catch (FileNotFoundException error) {
        System.out.println("File not found, fix the code and try again!");
    } finally {
        if(fileScanner != null) {
          fileScanner.close();
        }
    }
  

  int numberInRecords= noInFile/4;
  int index = 0;
  Deck [] tempDecks = new Deck [numberInRecords];

  try { 
    fileScanner = new Scanner (new BufferedReader (new FileReader (fileName)));
    fileScanner.useDelimiter("[\\r\\n,]+");
    while (fileScanner.hasNext()) {
      tempDecks[index] = new Deck();
      tempDecks[index].name = fileScanner.next();
      tempDecks[index].length = fileScanner.nextDouble();
      tempDecks[index].width = fileScanner.nextDouble();
      tempDecks[index].price = fileScanner.nextDouble();
      index = index + 1;
      }
    } catch (FileNotFoundException error) {
        System.out.println("File not found, fix the code and try again!");
    } finally {
        if(fileScanner != null) {
          fileScanner.close();
        }
  }
  return tempDecks;
} 

public static void cheapestDeck(Deck tempDecks[]) {

  double cheapest = tempDecks[0].price;
  int cheapestPos = 0;

  for (int index = 1; index < tempDecks.length; index ++) {
    if (tempDecks[index].price < cheapest) {
      cheapest = tempDecks[index].price;
      cheapestPos = index;
    }
  }
  System.out.println(tempDecks[cheapestPos].name + " is the cheapest deck at £" + tempDecks[cheapestPos].price);
}

//public static String[] certainLength(Deck tempDecks[]) {

 //int count = 0;
  //String[] desDecks;
  //double userLength = Keyboard.getReal("Please enter the desired minimum length of the decks.");
 
 // for (int index = 0; index < tempDecks.length; index ++) {
  //  if (tempDecks[index].length >= userLength) {
  //    desDecks[count] = tempDecks[index].name;
  //    count = count + 1;
  //  }
 // }
 // return desDecks;
//}

public static void certainLength2(Deck tempDecks[]) {

  int count = 0;
  double userLength = Keyboard.getReal("Please enter the desired minimum length of the decks.");
  System.out.println("The decks over " + userLength + "m long are:");

  for (int index = 0; index < tempDecks.length; index ++) {
    if (tempDecks[index].length >= userLength) {
      System.out.println(tempDecks[index].name);
    }
  }
}

public static int greaterArea(Deck tempDecks[]) {
  
  double userArea = Keyboard.getReal("Please enter the desired minimum area of the decks.");
  int count = 0;

  for (int index = 0; index < tempDecks.length; index ++) {
    if ((tempDecks[index].length * tempDecks[index].width) > userArea) {
      count = count + 1;
    }
  }
  return count;
}
}