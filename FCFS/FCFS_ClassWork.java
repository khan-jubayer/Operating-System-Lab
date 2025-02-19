public class FCFS_ClassWork {
    public static void main(String[] args) {
        
        int[] arrival_time = {0, 1, 2, 3};

        for (int i = 0; i < arrival_time.length - 1; i++) {
            for (int j = 0; j < arrival_time.length - i - 1; j++) {
                if (arrival_time[j] > arrival_time[j + 1]) {
                    int temp = arrival_time[j];
                    arrival_time[j] = arrival_time[j + 1];
                    arrival_time[j + 1] = temp;
                }
            }
        }

        int[] burst_time = {4, 3, 1, 2};
        int[] turnaround_time = new int[4];
        int[] waiting_time = new int[4];
        int[] complete_time = new int[4];
        
        
        complete_time[0] = arrival_time[0] + burst_time[0]; 
        for (int i = 1; i < 4; i++) {
            complete_time[i] = Math.max(arrival_time[i], complete_time[i - 1]) + burst_time[i];
        }

        
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");

        int totalTAT = 0;
        int totalWT = 0;
        
        for (int i = 0; i < 4; i++) {
            turnaround_time[i] = complete_time[i] - arrival_time[i];
            waiting_time[i] = turnaround_time[i] - burst_time[i];

           
            System.out.printf("P%d\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", i, arrival_time[i], burst_time[i], complete_time[i], turnaround_time[i], waiting_time[i]);

            
            totalTAT += turnaround_time[i];
            totalWT += waiting_time[i];
        }

    }
        
}
