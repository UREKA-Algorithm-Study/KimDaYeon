package week1;

import java.util.*;

/*
끝자리에 집중해서 앞자리에 어떤 수가 올 수 있나?
        D[n][h]: 길이가 n인 숫자에서
        끝값이 h로 종료되는 계단 수를 만들 수 있는 경우의 수

        1) h가 0일 경우
        D[n][0]=D[n-1][1]

        2) h가 1~8일 경우
        D[n][h]=D[n-1][h-1]+D[n-1][h+1]

        3) h가 9일 경우
        D[n][9]=D[n-1][8]

        1~3) 모든 값을 더해주고 % 연산 정답!!!

*/

public class bj_2193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] D = new int[n+1][2];
        D[1][0]=0;
        D[1][1]=1;
        for (int i = 2; i <= n; i++) {
            D[i][0] = D[i-1][0]+D[i-1][1];
            D[i][1] = D[i-1][0];
        }
        System.out.println(D[n][0]+D[n][1]);
    }
}
