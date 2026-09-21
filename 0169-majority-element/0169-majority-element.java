class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            // Jab bhi count 0 ho, naye number ko candidate bana lo
            if (count == 0) {
                candidate = num;
            }

            // Agar num candidate ke barabar hai to count badhao, warna ghatao
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}