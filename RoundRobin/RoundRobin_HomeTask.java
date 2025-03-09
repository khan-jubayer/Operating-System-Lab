import java.util.ArrayDeque;
import java.util.Queue;

public class RoundRobin_HomeTask {
    public static void main(String[] args) {
        int numProcesses = 4;
        String[] processes = {"p1", "p2", "p3", "p4"};
        int[] arrivalTimes = {0, 1, 2, 4};
        int[] burstTimes = {5, 4, 2, 1};
        int timeQuantum = 2;

        int n = numProcesses;
        int[] remainingBurst = burstTimes.clone();
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        int[] responseTime = new int[n];
        for (int i = 0; i < n; i++) {
            responseTime[i] = -1; // Initialize response time to -1
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int currentTime = 0;
        int index = 0;

        while (index < n || !queue.isEmpty()) {
            // Add processes to the queue that have arrived by the current time
            while (index < n && arrivalTimes[index] <= currentTime) {
                queue.add(index);
                index++;
            }

            if (!queue.isEmpty()) {
                int i = queue.poll();

                // Set response time if it's the first time the process is executed
                if (responseTime[i] == -1) {
                    responseTime[i] = currentTime - arrivalTimes[i];
                }

                // Execute the process for the time quantum or remaining burst time
                int execTime = Math.min(timeQuantum, remainingBurst[i]);
                remainingBurst[i] -= execTime;
                currentTime += execTime;

                // Add newly arrived processes to the queue
                while (index < n && arrivalTimes[index] <= currentTime) {
                    queue.add(index);
                    index++;
                }

                // If the process has remaining burst time, add it back to the queue
                if (remainingBurst[i] > 0) {
                    queue.add(i);
                } else {
                    // Process is completed
                    completionTime[i] = currentTime;
                    turnaroundTime[i] = completionTime[i] - arrivalTimes[i];
                    waitingTime[i] = turnaroundTime[i] - burstTimes[i];
                }
            } else {
                // No process is ready, move to the next arrival time
                currentTime = arrivalTimes[index];
            }
        }

        // Print the results
        System.out.println("Process\tArrivalTime\tBurstTime\tCompletionTime\tTurnaroundTime\tWaitingTime");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i] + "\t\t" + arrivalTimes[i] + "\t\t" + burstTimes[i] + "\t\t" +
                    completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }
    }
}