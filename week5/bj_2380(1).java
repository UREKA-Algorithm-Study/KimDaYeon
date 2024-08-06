import java.util.Scanner;

public class Main {
    static int[][] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // 가로, 세로, 3x3 확인과 함께 백트래킹을 사용하여 스도쿠 풀이
        if (sudoku()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static boolean sudoku() {
        int[] zero = findZero();
        if(zero==null) {
            return true; // 0이 없으면 스도쿠가 완성된 경우이다.
        }
        int row = zero[0];
        int col = zero[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                arr[row][col]=num; // 가능한 숫자를 채운다
                // 다음 빈칸으로 이동
                if(sudoku()) {
                    return true;
                }
                // 불가능한 숫자면 다시 돌아와 다른 숫자를 시도
                arr[row][col]=0; // 백트래킹
            }
        }
        return false;
    }

    private static int[] findZero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == 0) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    private static boolean isValid(int row, int col, int num) {
        // 가로 확인
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == num) {
                return false;
            }
        }

        // 세로 확인
        for (int i = 0; i < 9; i++) {
            if (arr[i][col] == num) {
                return false;
            }
        }

        // 3x3 확인
        int startRow = row - row % 3; // 서브그리드의 시작행
        int startCol = col - col % 3; // 서브그리드의 시작열
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
