import java.util.*;

public class Main {
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Node> student = new ArrayList<>();
    static int n;
    static char[][] arr;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String s = sc.next();
                arr[i][j] = s.charAt(0);
                if (arr[i][j] == 'S') {
                    student.add(new Node(i, j));
                }
            }
        }

        if(dfs(0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean dfs(int cnt) {
        if (cnt == 3) { // 장애물 3개 배치 완료
            return isSafe();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 'X') { // 빈 공간일 경우
                    arr[i][j] = 'O'; // 장애물 배치
                    if(dfs(cnt + 1)){
                        return true;
                    } // 다음 장애물 배치 시도
                    arr[i][j] = 'X'; // 장애물 제거(백트래킹)
                }
            }
        }
        return false;
    }

    private static boolean isSafe() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 선생 위치에서
                if (arr[i][j] == 'T') {
                    if (seeStudent(i, j)) { // 학생들을 볼 수 있으면
                        return false; // 안전하지 않음
                    }
                }
            }
        }
        return true; // 모든 학생이 감시되지 않음
    }

    // 선생의 시야를 4가지 방향으로 탐색
    private static boolean seeStudent(int x, int y) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                nx += dx[i];
                ny += dy[i];
                // 범위에서 벗어나거나 장애물을 만나면 해당 방향에서 탐색 종료
                if (nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] == 'O') {
                    break;
                }
                // 학생을 발견함
                if (arr[nx][ny] == 'S') {
                    return true;
                }
            }
        }
        // 학생을 발견하지 못함
        return false;
    }
}