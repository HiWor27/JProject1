public class Main {

  public static void main(String[] args) {
  //setup
    int tableHeight, tableWeight, numberOfGenerations, drawSpeed;
    String initialPopulation;

    //entrada tamaño tabla, n generaciones, velocidad de dibuajdo
    //entrada string para tabla
    //convertir string a array

    //prueba set
    tableWeight = 10;
    tableHeight = 10;
    numberOfGenerations = 50;
    drawSpeed = 1500;
    initialPopulation = "##▇0▇0▇#▇#0▇";
    System.out.println(initialPopulation.length());

    //verificate the string
    /*int stringCounter = 0;
    int maxCounted = 0;
    for (int posOnString = 0; posOnString < initialPopulation.length(); posOnString++) {
      if(initialPopulation.charAt(posOnString) == '#'){
        if(stringCounter >= maxCounted){
          maxCounted = stringCounter;
        }
        stringCounter = 0;
      }
      else stringCounter++;

    }
    System.out.println(maxCounted);
*/

    char [][] gameTable = new char[tableHeight][tableWeight];
    int posOnString = 0;
    char character = ' ';

    for (int j = 0; (j < tableHeight); j++) {

        int counter = 0;
        while((counter < tableWeight) && (posOnString < initialPopulation.length())) {
          character = initialPopulation.charAt(posOnString);
          if(character != '#') gameTable[j][counter] = character;
          posOnString++;
          counter++;
          if(character == '#') break;
        }

    }

    //draw
    //dibujar tabla (libreria master)
    drawMatrix(gameTable);




    //update
    //crear otra tabla para aplicar regla
    char[][] updatedGameTable = new char[tableHeight][tableWeight];
    //recorrer la tabla y verificar alrededores

    for (int i = 0; i < tableHeight; i ++) {
      for (int j = 0; j < tableWeight; j++) {

      }
    }
    //escribir en la nueva tabla
    //reemplazar tabla principal o inicial
    //dibujar

  }
  public static void drawMatrix(char [][] matrix){
    for (int i = 0; i < matrix.length; i ++) {
      for(int j = 0;j < matrix[0].length; j++){
        System.out.print(matrix[i][j]  );
        if(j != matrix[0].length - 1){
          System.out.print("|");
        }
      }
      System.out.println("");
      if(i != matrix.length - 1){
        System.out.print("-+");
        for (int k = 0; k < matrix[0].length - 2; k++){
          System.out.print("-+");
        }
        System.out.println("-");
      }
    }
  }
  public static boolean verificateSetupString(String stringToVerificate, char[][] gameTable){
    int stringCounter = 0;
    int maxOnRowCounted = 0;
    int maxOnColCounted = 0;
    for (int posOnString = 0; posOnString < stringToVerificate.length(); posOnString++) {
      if(stringToVerificate.charAt(posOnString) == '#'){
        if(stringCounter >= maxOnRowCounted){
          maxOnRowCounted = stringCounter;
        }
        stringCounter = 0;
        maxOnColCounted++;
      }
      else stringCounter++;
    }
    return ((gameTable.length < maxOnColCounted) && (gameTable[0].length < maxOnRowCounted));
  }
}