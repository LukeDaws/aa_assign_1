/**
 * Runqueue Processs Generator
 *
 *
 * @author Kaelob Smythe
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;


public class RunqueueGenerator
{

    /**
     * Print help/usage message.
     */
    public static void usage() {
        System.err.println("Usage: <implementation> [number of processes]");
        System.err.println("<implementation> = <array | linkedlist | tree>");
        System.err.println("[number of processes] is a number between 1-5000");
        System.exit(1);
    } // end of usage()

    /**
     * Main method.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        //
        // Parse command line arguments.
        //

        // if no arguments, we print out help message and exit
        if (args.length <= 0) {
            usage();
        }

        // Implementation type
        String implementationType = args[0];
        int runqueueSize = Integer.parseInt(args[1]);

        // determine which implementation to test
        Runqueue queue = null;
        switch(implementationType) {
            case "array":
                queue = new OrderedArrayRQ();
                break;
            case "linkedlist":
                queue  = new OrderedLinkedListRQ();
                break;
            case "tree":
                queue = new BinarySearchTreeRQ();
                break;
            default:
                System.err.println("Unknown implmementation type.");
                usage();
        }

        ArrayList<Long> enqueueTimeList = new ArrayList<Long>();
        ArrayList<Long> preceedingProcessTimeList = new ArrayList<Long>();
        ArrayList<Long> dequeueTimeList = new ArrayList<Long>();


        for(int j = 1; j <= 10; j++)
        {
            long enqueueStartTime = 0;
            long dequeueStartTime = 0;
            // enqueue
            long calculateProceedingProcessesStartTime = 0;
            for (int i = 1; i <= runqueueSize; i++)
            {
                enqueueStartTime = System.nanoTime();
                Random r = new Random();
                int low = 10;
                int high = 100;
                int result = r.nextInt(high-low) + low;
                queue.enqueue("P"+Integer.toString(i), result);
            }
            long enqueueEndTime = System.nanoTime();
            long enqueueDuration = (enqueueEndTime - enqueueStartTime);
            enqueueTimeList.add(enqueueDuration);
            // test the Calculate total vruntime of proceeding processes
            calculateProceedingProcessesStartTime = System.nanoTime();
            queue.precedingProcessTime("P"+String.valueOf(runqueueSize));
            long calculatePreceedingProcessEndTime = System.nanoTime();
            long calculatePreceedingProcessDuration = (calculatePreceedingProcessEndTime - calculateProceedingProcessesStartTime);
            preceedingProcessTimeList.add(calculatePreceedingProcessDuration);
            // dequeue
            for (int i = 1; i <= runqueueSize; i++)
            {
                dequeueStartTime = System.nanoTime();
                queue.dequeue();
            }
            long dequeueEndTime = System.nanoTime();
            long dequeueDuration = (dequeueEndTime - dequeueStartTime);
            dequeueTimeList.add(dequeueDuration);
        }

        //System.out.print();
        Iterator queueListIterator = enqueueTimeList.iterator();
        while ( queueListIterator.hasNext()) {
            System.out.print( queueListIterator.next()+",");
        }
        System.out.print("\n");
        Iterator proceedingIterator = preceedingProcessTimeList.iterator();
        //System.out.print();
        while ( proceedingIterator.hasNext() ) {
            System.out.print( proceedingIterator.next()+",");
        }
        System.out.print("\n");
        Iterator dequeueIterator = dequeueTimeList.iterator();
        //System.out.print();
        while ( dequeueIterator.hasNext() ) {
            System.out.print( dequeueIterator.next()+",");
        }
    }
}