
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeMatrix = Integer.parseInt(scanner.nextLine());

        char[][] matrix = getTheMatrix(scanner, sizeMatrix);
        int sumStars = 0;
        int rowStephen = getRowCoordinates('S', matrix);
        int colStephen = getColCoordinates('S', matrix, rowStephen);


        while (sumStars<50 && isInGalaxy(matrix, rowStephen, colStephen)){
            String direction = scanner.nextLine();

            int currentRow = rowStephen;
            int currentCol = colStephen;

            switch (direction){
                case "up":
                    currentRow-=1;
                    break;
                case "down":
                    currentRow+=1;
                    break;
                case "left":
                    currentCol-=1;
                    break;
                case "right":
                    currentCol+=1;
                    break;

            }

            if (isInGalaxy(matrix, currentRow, currentCol)){
                matrix[rowStephen][colStephen] = '-';
                if (Character.isDigit(matrix[currentRow][currentCol])){
                    int star = ((int) matrix[currentRow][currentCol]);
                    sumStars+=(star-48);
                    matrix[currentRow][currentCol]='S';
                } else if (matrix[currentRow][currentCol]=='O'){
                    matrix[currentRow][currentCol] = '-';
                    rowStephen = getRowCoordinates('O',matrix);
                    colStephen = getColCoordinates('O',matrix,rowStephen);
                    matrix[rowStephen][colStephen]= 'S';
                } else if (matrix[currentRow][currentCol]=='-'){
                    matrix[currentRow][currentCol]='S';
                }
                rowStephen = getRowCoordinates('S',matrix);
                colStephen = getColCoordinates('S',matrix,rowStephen);
            } else {
                matrix[rowStephen][colStephen]='-';
                rowStephen =-1;
                colStephen=-1;
                break;
            }

        }
        resultPrinting(matrix, rowStephen, colStephen,sumStars);
        System.out.printf("Star power collected: %d%n", sumStars);
        printMatrix(matrix);

    }

    private static void resultPrinting( char[][]matrix, int row, int col, int sum ) {
        if(isInGalaxy(matrix, row, col)){
            if (50 <= sum){
                System.out.printf("Good news! Stephen succeeded in collecting enough star power!%n");
            }

        } else {
            System.out.printf("Bad news, the spaceship went to the void.%n");
        }

    }

    private static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static int getRowCoordinates( char ch, char[][] matrix) {
            int index = -1;
            for (int row = 0; row < matrix.length ; row++) {
                for (int col = 0; col < matrix[row].length ; col++) {
                    if(matrix[row][col] == ch){
                        index = row;
                        break;
                    }
                }
                if(index!=-1){
                    break;
                }
            }
            return index;
    }

    private static int getColCoordinates( char ch, char[][] matrix, int row) {
        int index = -1;
        for (int col = 0; col < matrix[row].length ; col++) {
            if (matrix[row][col] == ch){
                index = col;
                break;
            }
        }
        return index;
    }


    private static boolean isInGalaxy(char[][] matrix, int row, int col) {
        boolean isInGalaxy;
                if((0<=row && row < matrix.length) && (0<=col && col< matrix.length)){
                    isInGalaxy = true;
                } else {
                    isInGalaxy = false;

        }
        return isInGalaxy;
    }


    private static char[][] getTheMatrix(Scanner scanner, int size) {
        char[][] matrix = new char[size][size];
        for (int i = 0; i < size; i++) {
          matrix [i] = scanner.nextLine().toCharArray();
        }
        return matrix;
    }


}
