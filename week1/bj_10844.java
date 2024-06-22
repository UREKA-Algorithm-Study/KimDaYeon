package week1;

import java.util.Scanner;

public class bj_10844 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] D = new long[n+1][11]; // [길이:n][끝숫자:h]

        // 숫자가 0으로 시작할 수 없으니까 D[0][1]은 0으로 초기화
        // n=1일 경우 1로 초기화
        for (int i = 1; i <= 9; i++) {
            D[1][i]=1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j==0 || j==9) D[i][j]=D[i-1][1];
                else D[i][j]=D[i-1][j-1]+D[i-1][j+1]%1000000000;
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += D[n][i];
        }
        System.out.println(sum%1000000000);
    }
}
