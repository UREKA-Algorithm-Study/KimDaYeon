import java.util.*;

public class Main4 {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 동굴 길이
        int h = sc.nextInt(); // 동굴 높이
        int[] down = new int[n/2];
        int[] up = new int[n/2];
        for (int i = 0; i < n/2; i++) {
            down[i]=sc.nextInt();
            up[i]= sc.nextInt();
        }
        Arrays.sort(down);
        Arrays.sort(up);

        int min=Integer.MAX_VALUE;
        int cnt=0;

        for (int i = 0; i < h; i++) {
            int d = binarySearch(down, i);
//            int u = binarySearch(up, h-i+1);
//
//            if(min > d+u) {
//                min = d+u;
//                cnt=1;
//            } else if (min == d+u) cnt++;
        }
//        System.out.println(min+ " "+cnt);
    }
//    public static int binarySearch(int[] stone, int h) {
//        int start=0;
//        int end=n/2-1;
//
//        while(start<=end) {
//            int mid = (start+end)/2;
//
//            if (stone[mid]>=h) {
//                end=mid-1;
//            } else start=mid+1;
//        }
//        return stone.length-start;
//    }
}