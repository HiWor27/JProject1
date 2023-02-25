import java.util.Scanner;
import java.util.Random;
import java.lang.Thread;

public class Main {

  public static void main(String[] args) {

    System.out.println("<<<<<<<<<<<WELCOME TO GAME OF LIFE>>>>>>>>>>>");
    int numberOfGenerations, drawSpeed;

    char[][] gameTable = getSetupMatrix();

    numberOfGenerations = getUserIntInput("Insert the number of generations");
    drawSpeed = getUserIntInput("Insert the speed of drawing");
    while(true){

        drawMatrix(gameTable);
        gameTable=getNewGeneration(gameTable);



      }
    }

  }
  public static char[][] getNewGeneration(char[][] oldMatrix){
    char[][] newMatrix = new char[oldMatrix.length][oldMatrix[0].length];


    for (int i = 1; i < newMatrix.length - 1; i ++) {
      for (int j = 1; j < newMatrix[0].length - 1; j++) {
        //detectar vecinos
        int cellsAround = 0;
        if (oldMatrix[i - 1][j - 1] == '■') cellsAround++;
        if (oldMatrix[i][j - 1] == '■') cellsAround++;
        if (oldMatrix[i + 1][j - 1] == '■') cellsAround++;

        if (oldMatrix[i - 1][j] == '■') cellsAround++;
        if (oldMatrix[i + 1][j] == '■') cellsAround++;

        if (oldMatrix[i - 1][j + 1] == '■') cellsAround++;
        if (oldMatrix[i][j + 1] == '■') cellsAround++;
        if (oldMatrix[i + 1][j + 1] == '■') cellsAround++;



        if (oldMatrix[i][j] == '■') {
          if ((cellsAround == 2) || (cellsAround == 3))
            newMatrix[i][j] = '■';
          else newMatrix[i][j] = ' ';

        }

        else if (oldMatrix[i][j] == ' ') {
          if (cellsAround == 3)
            newMatrix[i][j] = '■';
        }
//#0101#001

      }
    }
    return newMatrix;
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