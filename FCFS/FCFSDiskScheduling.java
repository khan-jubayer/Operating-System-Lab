import java.util.ArrayList;
import java.util.List;

public class FCFSDiskScheduling {

    public static int[] fcfsDiskScheduling(int[] requests, int head) {
        int totalSeekTime = 0;
        List<Integer> sequence = new ArrayList<>();
        sequence.add(head);

        for (int request : requests) {
            int seekDistance = Math.abs(request - head);
            totalSeekTime += seekDistance;
            head = request;
            sequence.add(head);
        }

        int[] result = new int[2];
        result[0] = totalSeekTime;
        result[1] = sequence.size();
        return result;
    }

    public static void main(String[] args) {
        int[] requests = {60, 100, 30, 150, 20};
        int initialHead = 50;

        int[] result = fcfsDiskScheduling(requests, initialHead);
        int seekTime = result[0];
        int sequenceSize = result[1];

        System.out.println("Total Seek Time: " + seekTime);
        System.out.print("Head Movement Sequence: ");
        for (int i = 0; i < sequenceSize; i++) {
            System.out.print(result[1 + i]);
            if (i < sequenceSize - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
