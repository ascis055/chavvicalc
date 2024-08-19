package com.github.ascis055.chavviCalc;

import java.util.*;

/*
 * chavvi calc calculator
 */
public class ChavviCalcApp {

  static float a = 0, b = 0;
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Character command;

    // loop until user quits
    do {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);

      executeCommand(scan, command);
    } while (command != 'q');

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

  // get first character from input line
  // return value:
  //     Character received: character value
  //     Empty line: '_'
  //     Exception (end-of-input or error): 'q'
  private static Character menuGetCommand(Scanner scan) {
    String rawInput;

    try {
        rawInput = scan.nextLine();
    }
    catch (Exception e) {
        System.out.println();
        return 'q';
    }
    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      return rawInput.charAt(0);
    } else
        return '_';
  }

  // get floating point number from input line
  // return value:
  //     Value entered: numeric value
  //     Invalid input: Float.NaN
  // This function ignores end-of-input or error conditions if they
  // happen after a valid input. Next input attempt should process
  // those.
  private static float GetFloat(Scanner scan) {
      float value;

      try {
          value = scan.nextFloat();
      }
      catch (Exception e) {
          System.out.println("ERROR: Invalid numeric value");
          value = Float.NaN;
      }
      try {
          scan.nextLine();
      }
      catch (Exception e) {
          System.out.println();
      }
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
        v = GetFloat(scan);
        if (!Float.isNaN(v)) {
            a = v;
        } else
            success = false;
        break;

      case 'b':
        System.out.print("Enter B: ");
        v = GetFloat(scan);
        if (!Float.isNaN(v)) {
            b = v;
        } else
            success = false;
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
