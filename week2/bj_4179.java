import java.util.*;

public class Main {
    static char[][] arr;
    static int[][] ji, fire;
    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new char[n][m];
        ji = new int[n][m];
        fire = new int[n][m];

        Queue<int[]> jihoonQueue = new LinkedList<>();
        Queue<int[]> fireQueue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j);
                ji[i][j] = -1;
                fire[i][j] = -1;
                if (arr[i][j] == 'J') {
                    ji[i][j] = 0;
                    jihoonQueue.add(new int[]{i, j});
                }
                if (arr[i][j] == 'F') {
                    fire[i][j] = 0;
                    fireQueue.add(new int[]{i, j});
                }
            }
        }

        bfsFire(fireQueue);
        bfsJihoon(jihoonQueue);
    }

    // 불에 대한 bfs
    private static void bfsFire(Queue<int[]> q) {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == '.' && fire[nx][ny] == -1) {
                    q.add(new int[]{nx, ny});
                    fire[nx][ny] = fire[now[0]][now[1]] + 1;
                }
            }
        }
    }

    // 지훈에 대한 bfs
    public static void bfsJihoon(Queue<int[]> q) {
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                // 범위를 벗어났다는 건 탈출에 성공했다는 것
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    System.out.println(ji[now[0]][now[1]] + 1);
                    return;
                }
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == '.' && ji[nx][ny] == -1) {
                    // 불이 특정 공간에 도달하는 시간보다 지훈이가 도달하는 시간이 더 작아야 한다
                    if (fire[nx][ny] == -1 || ji[now[0]][now[1]] + 1 < fire[nx][ny]) {
                        q.add(new int[]{nx, ny});
                        ji[nx][ny] = ji[now[0]][now[1]] + 1;
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}