import java.util.Scanner;
import java.util.Random;

public class Main {

  public static void main(String[] args) {

    System.out.println("<<<<<<<<<<<WELCOME TO GAME OF LIFE>>>>>>>>>>>");
    int numberOfGenerations, drawSpeed;

    char[][] gameTable = getSetupMatrix();

    numberOfGenerations = getUserIntInput("Insert the number of generations");
    drawSpeed = getUserIntInput("Insert the speed of drawing");

    drawMatrix(gameTable);
  }

  public static char[][] getSetupMatrix() {
    int tableHeight, tableWeight;
    String initialPopulation;
    Scanner scanner = new Scanner(System.in);
    scanner.useDelimiter("\\n");

    tableHeight = getUserIntInput("Insert the heigth of the table");
    tableWeight = getUserIntInput("Insert the weigth of the table");

    char[][] gameTable = createMatrix(tableHeight, tableWeight);

    do {
      System.out.println("Insert the initial population of cells");
      initialPopulation = scanner.next();
      if (initialPopulation.equals("rnd")) {
        break;
      }
    } while (!(verificateSetupString(initialPopulation, gameTable)));

    if (initialPopulation.equals("rnd")) {
      return convertToRandomTable(gameTable);
    } else {
      return convertStringToMatrix(gameTable, initialPopulation);
    }
  }

  public static int getUserIntInput(String message) {
    Scanner scanner = new Scanner(System.in);
    scanner.useDelimiter("\\n");
    int input;

    do {
      System.out.println(message);
      input = scanner.nextInt();
    } while (!verificateUserInput(input));
    return input;
  }

  public static char[][] createMatrix(int tableHeight, int tableWeight) {
    char[][] matrix = new char[tableHeight][tableWeight];
    for (int row = 0; row < tableHeight; row++) {
      for (int col = 0; col < tableWeight; col++) {
        matrix[row][col] = ' ';
      }
    }
    return matrix;
  }

  public static char[][] convertToRandomTable(char[][] matrix) {
    char[][] randomMatrix = matrix;
    Random random = new Random();
    for (int row = 0; row < randomMatrix.length; row++) {
      for (int col = 0; col < randomMatrix[0].length; col++) {
        if (random.nextBoolean()) {
          randomMatrix[row][col] = '■';
        }
      }
    }
    return randomMatrix;

  }

  public static void drawMatrix(char[][] matrix) {
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        System.out.print(matrix[row][col]);
        if (col != matrix[0].length - 1) {
          System.out.print("|");
        }
      }
      System.out.println("");
      if (row != matrix.length - 1) {
        System.out.print("-+");
        for (int k = 0; k < matrix[0].length - 2; k++) {
          System.out.print("-+");
        }
        System.out.println("-");
      }
    }
  }

  public static char[][] convertStringToMatrix(char[][] matrix, String stringToConvert) {
    char[][] table = matrix;
    int posOnString = 0;
    char character = ' ';

    for (int row = 0; (row < table.length); row++) {

      int counter = 0;
      while ((counter < table[0].length) && (posOnString < stringToConvert.length())) {
        character = stringToConvert.charAt(posOnString);
        if (character != '#') {
          if (character == '1') {
            table[row][counter] = '■';
          } else {
            table[row][counter] = ' ';
          }
        }
        posOnString++;
        counter++;
        if (character == '#') {
          break;
        }
      }

    }
    return table;
  }

  public static boolean verificateSetupString(String stringToVerificate, char[][] gameTable) {
    int stringCounter = 0;
    int maxOnRowCounted = 0;
    int nOfRows = 0;
    for (int posOnString = 0; posOnString < stringToVerificate.length(); posOnString++) {
      if (stringToVerificate.charAt(posOnString) == '#') {
        if (stringCounter >= maxOnRowCounted) {
          maxOnRowCounted = stringCounter;
        }
        stringCounter = 0;
        nOfRows++;
      } else {
        stringCounter++;
      }
    }
    if ((gameTable.length > nOfRows) && (gameTable[0].length >= maxOnRowCounted)) {
      return true;
    } else {
      System.out.println("Error was founded, repeat the initial setup");
      return false;
    }
  }

  public static boolean verificateUserInput(int input) {
    if (input <= 0) {
      System.out.println("Number not admited, try again");
      return false;
    } else {
      return true;
    }
  }
}