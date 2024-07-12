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
        int cnt=0;
        for (int i = 0; i < n; i++) {
            int pl = 0;
            int pr = n-1;

            while(pl<pr) {
                if(pl==i) {
                    pl++;
                    continue;
                }
                if(pr==i) {
                    pr--;
                    continue;
                }
                int sum=arr[pl]+arr[pr];
                if(sum==arr[i]) {
                    cnt++;
                    break;
                } else if(sum<arr[i]) pl++;
                else pr--;
            }
        }
        System.out.println(cnt);
    }
}