import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1); // Base multiplier to simplify division
    }
    
    public void add(int num) {
        if (num == 0) {
            // Reset the prefix list when 0 is encountered
            prefix = new ArrayList<>();
            prefix.add(1);
        } else {
            int lastProduct = prefix.get(prefix.size() - 1);
            prefix.add(lastProduct * num);
        }
    }
    
    public int getProduct(int k) {
        int n = prefix.size();
        // If k >= n, a 0 appeared within the last k elements
        if (k >= n) {
            return 0;
        }
        return prefix.get(n - 1) / prefix.get(n - 1 - k);
    }
}
