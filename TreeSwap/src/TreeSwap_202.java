import java.util.HashMap;
import java.util.Map;

public class TreeSwap_202 {

    public TreeSwap_202() {
    }

    public int[] swap(int[] tree) {
        int n = tree.length;
        for (int i = 0; i <= n/2; i++) {
            int si = 2 * (i + 1);
            if (si < n && tree[i] != 0 && tree[si] != 0) {
                int tmp = tree[i];
                tree[i] = tree[si];
                tree[si] = tmp;
            }
        }
        return tree;
    }

    public Map<Integer, Integer> sum(int[] tree) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int i = 0;
        int n = tree.length;
        int l = 0;
        while (i < n) {
            int sum = 0;
            int j = i;
            while (j <  i + Math.pow(2, l) && j < n)
                sum += tree[j++];
            sumMap.put(l++, sum);
            i = j;
        }
        return sumMap;
    }
}
