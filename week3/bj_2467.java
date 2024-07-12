import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int pl=0;
        int pr=n-1;
        int min=Integer.MAX_VALUE;

        int a=0, b=0;
        while(pl<pr) {
            int sum = arr[pl]+arr[pr];
            // 두 수의 합이 가장 작은 경우 구하기
            if(min>Math.abs(sum)) {
                min=Math.min(min, Math.abs(sum));
                a=pl;
                b=pr;
            }
            if(sum>=0) {
                pr--;
            } else pl++;
        }
        System.out.println(arr[a]+" "+ arr[b]);
    }
}