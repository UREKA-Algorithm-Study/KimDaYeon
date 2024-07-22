import java.util.*;

public class pg_주사위고르기 {
    static int n;
    static int[][] dices;
    static List<Integer> com = new ArrayList<>();
    static List<Integer> arrA;
    static List<Integer> arrB;
    static int max = Integer.MIN_VALUE;
    static int[] answer;

    public static void main(String[] args) {
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};
        int[] result = solution(dice);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] solution(int[][] dice) {
        n = dice.length;
        dices = dice;
        answer = new int[n / 2];  // answer 배열 초기화
        combinationA(0, 0);
        return answer;
    }


    // 1. A가 가져갈 수 있는 주사위의 조합 구하기 (재귀 사용)
    private static void combinationA(int d, int s) {
        if (d == n / 2) {
            // 2. 조합을 완성하면 가질 수 있는 눈금의 합을 계산
            int win = calculateCom();

            if (max < win) {
                max = win;
                for (int i = 0; i < com.size(); i++) {
                    answer[i] = com.get(i) + 1;
                }
            }
            return;
        }
        for (int i = s; i < n; i++) {  // 조합 구하는 범위 수정
            com.add(i);
            combinationA(d + 1, i + 1);
            com.remove(com.size() - 1);
        }
    }

    private static int calculateCom() {
        int[][] diceA = new int[n / 2][6];
        int[][] diceB = new int[n / 2][6];
        arrA = new ArrayList<>();
        arrB = new ArrayList<>();

        int a = 0, b = 0;
        // 주사위 배열에 조합 결과 넣기
        for (int i = 0; i < n; i++) {
            // A주사위 조합
            if (com.contains(i)) {
                diceA[a] = dices[i];
                a++;
            }
            // B주사위 조합
            else {
                diceB[b] = dices[i];
                b++;
            }
        }

        // 2. 구해진 조합에 대해서 가질 수 있는 각 주사위 모든 눈금의 합 저장
        arrSum(0, diceA, 0, arrA);
        arrSum(0, diceB, 0, arrB);

        // 3. 정렬 (이분탐색 예정)
        Collections.sort(arrA);
        Collections.sort(arrB);

        int cnt = 0;

        for (int i = 0; i < arrA.size(); i++) {
            // 4. 이분탐색으로 arrB에 arrA보다 작은 숫자가 몇개 있는지 구하여 그 값을 모두 더하면 A가 승리하는 경우이다. (B가 지는 경우 = A가 이기는 경우)
            int num = arrA.get(i);

            int left = 0, right = arrB.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arrB.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            cnt += left;
        }
        return cnt;
    }

    private static void arrSum(int d, int[][] dice, int sum, List<Integer> arr) {
        if (d == n / 2) {
            arr.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            int newSum = sum + dice[d][i];
            arrSum(d + 1, dice, newSum, arr);
        }
    }
}
