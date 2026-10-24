class Solution {
    // Changed name here from countDaysSpentTogether to countDaysTogether
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // 1. Convert all date strings into day numbers of the year (1 to 365)
        int startAlice = dateToDayOfYear(arriveAlice);
        int endAlice = dateToDayOfYear(leaveAlice);
        int startBob = dateToDayOfYear(arriveBob);
        int endBob = dateToDayOfYear(leaveBob);

        // 2. Find the date they are both in town (the overlap range)
        int latestArrival = Math.max(startAlice, startBob);
        int earliestDeparture = Math.min(endAlice, endBob);

        // 3. Calculate the number of overlapping days
        int overlap = earliestDeparture - latestArrival + 1;

        // If overlap is negative, it means their dates never crossed, so return 0
        if (overlap < 0) {
            return 0;
        }
        
        return overlap;
    }

    // Helper method to turn a "MM-DD" string into a single day number (1-365)
    private int dateToDayOfYear(String dateStr) {
        String[] parts = dateStr.split("-");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);

        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int totalDays = 0;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }

        totalDays += day;
        return totalDays;
    }
}
