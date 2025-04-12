import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Scan_Disk_Scheduling {
    public static void main(String[] args) {
        List<Integer> reqSeq = new ArrayList<>(Arrays.asList(0, 14, 41, 53, 65, 67, 98, 122, 124, 183, 199));
        List<Integer> output = new ArrayList<>();
        int head = 53;
        int seekOp = 0;

        // Print inputs
        System.out.print("Request Sequence: ");
        for (int n : reqSeq) {
            System.out.print(n + ", ");
        }
        System.out.println("\nHead is at: " + head);

        // Sort the sequence
        Collections.sort(reqSeq);

        // Find head index
        int headIndex = reqSeq.indexOf(head);

        // Head to cylinder (right direction)
        for (int seq : reqSeq) {
            if (seq >= head) {
                output.add(seq);
            }
        }

        // Update seek operations
        seekOp += (output.get(output.size() - 1) - head);
        int lastCylinder = output.get(output.size() - 1);

        // Sort in descending order for left direction
        Collections.sort(reqSeq, Collections.reverseOrder());

        // Cylinder to starting (left direction)
        for (int seq : reqSeq) {
            if (seq < head && seq != 0) {
                output.add(seq);
            }
        }

        // Update seek operations
        seekOp += (lastCylinder - output.get(output.size() - 1));

        // Print results
        System.out.println("\nOutput:");
        System.out.println("Total Seek Operations for SCAN Disk Schedule: " + seekOp);
        System.out.print("Sequence Order: ");
        for (int n : output) {
            System.out.print(n + " ");
        }
    }
}
