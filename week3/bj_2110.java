import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int pl=1; // 최소길이: 맨 앞집의 위치
        int pr=arr[n-1]-arr[0]; // 최대길이: 맨 끝집의 위치-맨 앞집의 위치

        int ans=0;
        while(pl<=pr) {
            int pc=(pl+pr)/2;
            int first = arr[0]; // 첫번째 집은 무조건 설치
            int cnt=1; // 설치 개수
            for (int i = 0; i < n; i++) {
                int d = arr[i]-first; // 현재위치에서 거리 차이
                if (d>=pc) { // 거리 차이가 기준보다 크거나 같을 때 설치 가능
                    cnt++;
                    first=arr[i];
                }
            }
            if (cnt>=c) { // 설치가 더 많이 됐거나 갯수만큼 설치됐으면 범위를 넓혀서 탐색
                pl=pc+1;
                ans=pc; // 최적의 결과 저장
            } else { // 설치가 덜 됐으면 범위를 좁혀서 탐색
                pr=pc-1;
            }
        }
        System.out.println(ans);
    }
}