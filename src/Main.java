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
    initialPopulation = "##10101#1#01";
    System.out.println(initialPopulation.length());

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
    for (int i = 0; i < tableHeight; i ++) {
      for(int j = 0;j < tableWeight; j++){
        System.out.print(gameTable[i][j]  );
        if(j != tableWeight - 1){
          System.out.print("|");
        }
      }
      System.out.println("");
      if(i != tableHeight - 1){
        System.out.print("-+");
        for (int k = 0; k < tableWeight - 2; k++){
          System.out.print("-+");
        }
        System.out.println("-");
      }
    }
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
}