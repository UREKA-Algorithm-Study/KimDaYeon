import java.util.*;
public class Main {
    public static int n;
    public static int[][] arr;
    public static boolean[] selected;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        selected = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j]=sc.nextInt();
            }
        }
        // 조합으로 모든 팀 탐색
        combination(0, 0);
        System.out.println(ans);
    }

    private static void combination(int cnt, int start) {
        if(cnt==n/2) {
            // 조합된 팀으로 능력치 계산
            calculate();
            return;
        }
        for (int i = start; i < n; i++) {
            selected[i]=true;
            combination(cnt+1, i+1);
            selected[i]=false;
        }
    }

    private static void calculate() {
        int startTeam=0, linkTeam=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(selected[i] && selected[j]) {
                    startTeam+=arr[i][j];
                } else if (!selected[i]&&!selected[j]) {
                    linkTeam+=arr[i][j];
                }
            }
        }
        ans=Math.min(Math.abs(startTeam-linkTeam), ans);
    }
}
