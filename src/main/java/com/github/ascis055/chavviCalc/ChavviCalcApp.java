package com.github.ascis055.chavviCalc;

import java.util.*;

/*
 * chavvi calc calculator
 */
public class ChavviCalcApp {

  static float a = 0, b = 0;
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Character command = '_';

    // loop until user quits
    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);

      executeCommand(scan, command);
    }

    scan.close();
  }

  //
  // menu functions
  //
  private static void printMenuLine() {
    System.out.println(
      "----------------------------------------------------------"
    );
  }

  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t%s\n", command, desc);
  }

  // prints the menu
  public static void printMenu() {
    printMenuLine();
    System.out.println("ChavviCalc");
    printMenuLine();
    System.out.printf("A = %.3f\tB = %.3f\n", a, b);
    printMenuLine();

    printMenuCommand('a', "Enter a value for A");
    printMenuCommand('b', "Enter a value for B");
    printMenuCommand('+', "Add");
    printMenuCommand('-', "Subtract");
    printMenuCommand('*', "Multiply");
    printMenuCommand('/', "Divide");
    printMenuCommand('c', "Clear");
    printMenuCommand('q', "Quit");

    printMenuLine();
  }

  // get first character from input
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';

    String rawInput = scan.nextLine();

    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      command = rawInput.charAt(0);
    }

    return command;
  }
  // get floating point number from input line
  private static float GetFloat(Scanner scan) {
      float value;

      value = scan.nextFloat();
      scan.nextLine();
      return value;
  }

  // calculator functions
  private static Boolean executeCommand(Scanner scan, Character command) {
    Boolean success = true;
    float v;

    switch (command) {
      case 'q':
        System.out.println("Thank you for using Chavvi Calc");
        break;

      case 'a':
        System.out.print("Enter A: ");
        a = GetFloat(scan);
        break;

      case 'b':
        System.out.print("Enter B: ");
        b = GetFloat(scan);
        break;

      case 'c':
        a = 0;
        b = 0;
        break;

      case '+':
        a = a + b;
        break;

      case '-':
        a = a - b;
        break;

      case '*':
        a = a * b;
        break;

      case '/':
        v = a / b;
        if (!Float.isNaN(v) && !Float.isInfinite(v)) {
             a = v;
        } else {
             System.out.println("ERROR: invalid division");
            success = false;
        }
        break;

      default:
        System.out.println("ERROR: Unknown commmand");
        success = false;
    }

    return success;
  }
}
