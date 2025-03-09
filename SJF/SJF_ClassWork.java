import java.util.Arrays;

public class SJF_ClassWork {
    public static void main(String[] args) {

        int[] arrivalTimes = {2, 5, 1, 0, 4};

        int[] burstTimes = {6, 2, 8, 3, 4};


        sjfNonPreemptive(arrivalTimes, burstTimes);
    }

    public static void sjfNonPreemptive(int[] arrivalTimes, int[] burstTimes) {
        int n = arrivalTimes.length;
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        boolean[] executed = new boolean[n];

        int currentTime = 0;
        int completed = 0;

        while (completed < n) {
            int shortestIndex = -1;
            int shortestBurst = Integer.MAX_VALUE;


            for (int i = 0; i < n; i++) {
                if (!executed[i] && arrivalTimes[i] <= currentTime && burstTimes[i] < shortestBurst) {
                    shortestBurst = burstTimes[i];
                    shortestIndex = i;
                }
            }


            if (shortestIndex == -1) {
                currentTime++;
                continue;
            }


            completionTime[shortestIndex] = currentTime + burstTimes[shortestIndex];
            turnaroundTime[shortestIndex] = completionTime[shortestIndex] - arrivalTimes[shortestIndex];
            waitingTime[shortestIndex] = turnaroundTime[shortestIndex] - burstTimes[shortestIndex];
            executed[shortestIndex] = true;
            currentTime = completionTime[shortestIndex];
            completed++;
        }


        System.out.println("Process ID\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t\t" + arrivalTimes[i] + "\t\t" + burstTimes[i] + "\t\t" +
                               completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }


        double avgTurnaroundTime = Arrays.stream(turnaroundTime).average().orElse(0);
        double avgWaitingTime = Arrays.stream(waitingTime).average().orElse(0);
        System.out.println("\nAverage Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}