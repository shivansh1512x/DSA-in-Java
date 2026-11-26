class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean aliceTurn = true;

        while (n >= remove) {
            n -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }

        // If it is Alice's turn when the loop ends, she cannot move and loses.
        return !aliceTurn;
    }
}
