import java.util.Scanner;
public class FCFS_HomeTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        int[] arrival_time = new int[n];
        int[] burst_time = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.printf("Enter arrival time for process P%d: ", i);
            arrival_time[i] = scanner.nextInt();
            System.out.printf("Enter burst time for process P%d: ", i);
            burst_time[i] = scanner.nextInt();
        }
        
        // Sort processes based on arrival time (FCFS)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrival_time[j] > arrival_time[j + 1]) {
                    int temp = arrival_time[j];
                    arrival_time[j] = arrival_time[j + 1];
                    arrival_time[j + 1] = temp;

                    temp = burst_time[j];
                    burst_time[j] = burst_time[j + 1];
                    burst_time[j + 1] = temp;
                }
            }
        }

        int[] turnaround_time = new int[n];
        int[] waiting_time = new int[n];
        int[] complete_time = new int[n];

        complete_time[0] = arrival_time[0] + burst_time[0];
        for (int i = 1; i < n; i++) {
            complete_time[i] = Math.max(arrival_time[i], complete_time[i - 1]) + burst_time[i];
        }

        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");

        int totalTAT = 0;
        int totalWT = 0;

        for (int i = 0; i < n; i++) {
            turnaround_time[i] = complete_time[i] - arrival_time[i];
            waiting_time[i] = turnaround_time[i] - burst_time[i];

            System.out.printf("P%d\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", i, arrival_time[i], burst_time[i], complete_time[i], turnaround_time[i], waiting_time[i]);

            totalTAT += turnaround_time[i];
            totalWT += waiting_time[i];
        }

        double avgTAT = (double) totalTAT / n;
        double avgWT = (double) totalWT / n;

        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTAT);
        System.out.printf("Average Waiting Time: %.2f\n", avgWT);

        scanner.close();
    }
}