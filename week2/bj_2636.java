import java.util.*;

public class Main {
    static int[][] arr;
    static int n, m, cnt;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) {
                    cnt++; // 치즈 조각 개수
                }
            }
        }
        bfs();
    }

    private static void bfs() {


        int lastMelted=0;
        int time = 0;

        while(cnt>0) {
            int melted = 0;
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0, 0});
            boolean[][] visited = new boolean[n][m];
            visited[0][0] = true;
            while (!q.isEmpty()) {
                int[] now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        if (arr[nx][ny] == 0) {
                            q.add(new int[]{nx, ny});
                        } else { // 치즈 모서리일때
                            arr[nx][ny] = 0;
                            melted++;
                        }
                    }
                }
            }

            cnt -= melted;
            time++;
            lastMelted=melted;
        }
        System.out.println(time);
        System.out.println(lastMelted);
    }
}