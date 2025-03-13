import java.util.*;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int priority;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.completionTime = 0;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 5, 10));
        processes.add(new Process(2, 1, 4, 20));
        processes.add(new Process(3, 3, 2, 30));
        processes.add(new Process(4, 4, 1, 40));

        int timeQuantum = 2;
        List<Process> scheduledProcesses = prioritySchedulingPreemptiveTimeQuantum(processes, timeQuantum);

        System.out.println("PID | AT | BT | Priority | CT | TAT | WT");
        for (Process p : scheduledProcesses) {
            System.out.printf("%3d | %2d | %2d | %8d | %2d | %3d | %2d\n",
                    p.pid, p.arrivalTime, p.burstTime, p.priority, p.completionTime, p.turnaroundTime, p.waitingTime);
        }
    }

    public static List<Process> prioritySchedulingPreemptiveTimeQuantum(List<Process> processes, int timeQuantum) {
        int time = 0;
        int completed = 0;
        int n = processes.size();
        List<Process> queue = new ArrayList<>();
        List<String> sequence = new ArrayList<>();

        while (completed < n) {
            for (Process p : processes) {
                if (p.arrivalTime == time && !queue.contains(p) && p.remainingTime > 0) {
                    queue.add(p);
                }
            }
            queue.sort((p1, p2) -> {
                if (p1.priority == p2.priority) {
                    return Integer.compare(p1.arrivalTime, p2.arrivalTime);
                }
                return Integer.compare(p2.priority, p1.priority);
            });

            if (!queue.isEmpty()) {
                Process currentProcess = queue.remove(0);
                sequence.add(time + ":" + currentProcess.pid);

                int executionTime = Math.min(timeQuantum, currentProcess.remainingTime);
                currentProcess.remainingTime -= executionTime;
                time += executionTime;

                for (Process p : processes) {
                    if (timeQuantum > 1 && p.arrivalTime >= time - executionTime && p.arrivalTime <= time
                            && !queue.contains(p) && p.remainingTime > 0) {
                        queue.add(p);
                    }
                }
                queue.sort((p1, p2) -> {
                    if (p1.priority == p2.priority) {
                        return Integer.compare(p1.arrivalTime, p2.arrivalTime);
                    }
                    return Integer.compare(p2.priority, p1.priority);
                });

                if (currentProcess.remainingTime > 0) {
                    queue.add(currentProcess);
                } else {
                    currentProcess.completionTime = time;
                    completed++;
                }
            } else {
                time++;
            }
        }

        for (Process p : processes) {
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
        }

        System.out.println("Sequence: " + String.join(" -> ", sequence));
        return processes;
    }
}
