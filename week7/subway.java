public class Main {
    static int[][] subway;

    public static void main(String[] args) {
        // 테스트 케이스 1
        int[][] subwayInput1 = {{1, 5}, {6, 5}, {6, 1}, {1, 1}};
        int[] a1 = {2, 3};
        int[] b1 = {5, 2};
        System.out.println(solution(subwayInput1, a1, b1)); // return: 2

        // 테스트 케이스 2
        int[][] subwayInput2 = {{-2, 5}, {1, 5}, {1, 2}, {-2, 2}};
        int[] a2 = {-4, 1};
        int[] b2 = {2, 4};
        System.out.println(solution(subwayInput2, a2, b2)); // return: 4

        // 테스트 케이스 3
        int[][] subwayInput3 = {{1, 1}, {4, 1}, {4, -2}, {1, -2}};
        int[] a3 = {2, 3};
        int[] b3 = {-2, 4};
        System.out.println(solution(subwayInput3, a3, b3)); // return: 5

        // 테스트 케이스 4
        int[][] subwayInput4 = {{-2, 2}, {2,2}, {2, -2}, {-2, -2}};
        int[] a4 = {1, 2};
        int[] b4 = {-2, -2};
        System.out.println(solution(subwayInput4, a4, b4)); // return: 0
    }

    public static int solution(int[][] subwayInput, int[] a, int[] b) {
        subway = subwayInput;

        // A, B가 지하철 노선에 방문한 경우 확인
        boolean aOn = onSubway(a);
        boolean bOn = onSubway(b);

        // 2) A, B가 둘 다 노선 위에 있으면 0
        if (aOn && bOn) {
            return 0;
        }

        // 3) A, B 중 한명만 노선에 방문한 경우 방문 안한 친구를 가장 가까운 노선에 도착시킨다.
        // A만 노선 위에 있으면 B의 거리 계산
        else if (aOn) {
            return distanceToSubway(b);
        }
        // B만 노선 위에 있으면 A의 거리 계산
        else if (bOn) {
            return distanceToSubway(a);
        }

        // 1) A, B 두 명 다 노선에 방문하지 않은 경우
        else {
            // A와 B의 거리를 계산
            int aToSubway = distanceToSubway(a);
            int bToSubway = distanceToSubway(b);

            // 지하철을 거치지 않고 두 점 사이의 거리 계산
            int abDistance = Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);

            // 두 점이 지하철 노선 내부에 있을 때
            if (inSubway(a) && inSubway(b)) {
                return Math.min(aToSubway + bToSubway, abDistance);
            }

            // 두 점 사이의 거리가 지하철 노선까지의 거리의 합보다 짧은 경우 작은 값 리턴
            return Math.min(aToSubway + bToSubway, abDistance);
        }
    }

    private static boolean onSubway(int[] p) {
        int x = p[0];
        int y = p[1];
        // 지하철 x 라인 위인 경우
        if (x>=subway[0][0] && x<=subway[1][0] && (y==subway[0][1] || y==subway[2][1])) {
            return true;
        }
        // 지하철 y라인 위인 경우
        if (y>=subway[2][1] && y<=subway[0][1] && (x==subway[0][0] || x==subway[1][0])) {
            return true;
        }
        return false;
    }


    private static boolean inSubway(int[] p) {
        int x = p[0];
        int y = p[1];
        return  (x > subway[0][0] && x < subway[1][0] && y > subway[2][1] && y < subway[0][1]);
    }

    private static int distanceToSubway(int[] p) {
        int x = p[0];
        int y = p[1];
        // 현재 지하철 좌표 저장
        // left: (왼 x좌표), right: (오 x좌표), top: (위 y좌표), bottom: (아래 y좌표)
        int left = subway[0][0], right = subway[1][0];
        int top = subway[0][1], bottom = subway[2][1];

        // 거리 저장 변수
        int dx = 0, dy = 0;

        // x 거리 계산
        if(x<left) {
            dx = left-x;
        } else if(x>right) {
            dx = x-right;
        }
        // y 거리 계산
        if(y>top) {
            dy = y-top;
        } else if (y<bottom) {
            dy = bottom-y;
        }

        // 노선 내부에 있는 경우
        if (dx == 0 && dy == 0) {
            return Math.min(Math.min(x - left, right - x), Math.min(top - y, y - bottom));
        }

        // x 거리 + y 거리
        return dx+dy;
    }
}