import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 */
public class OrderedLinkedListRQ implements Runqueue {

    /**
     * Constructs empty linked list
     */
	protected Node mHead;
	protected Node mTail;
	protected int mLength;
	protected int index;
	
    public OrderedLinkedListRQ() {
        // Implement me
    	mHead = null;
        mTail = null;
        mLength = 0;
    }  // end of OrderedLinkedList()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	Node newNode = new Node(procLabel, vt);

        
    	//checks if head is empty if so initalises head and tail.
        if (mHead == null) {
            mHead = newNode;
            mTail = newNode;
        }
        else {
        	
        	 Node currNode = mHead;
             for (int i = 0; i < mLength; ++i) {
             	
             	if (vt < currNode.getVt()) 
             		index = i;
             	else if (vt == currNode.getVt()) 
             		index = i+1;
             	else  
             		index = mLength;
             	
                 currNode = currNode.getNext();
             }
             
        	
        	// depending on where index is, we either go forward or backward in
            // list
            if (index <= Math.ceil(mLength / 2)) {
                // go forward from head
                // if index = 0, we should replace mHead with newNode
                if (index == 0) {
                    newNode.setNext(mHead);
                    mHead.setPrev(newNode);
                    mHead = newNode;
                    
                }else {
                    currNode = mHead;
                    for (int i = 0; i < index-1; ++i) {
                        currNode = currNode.getNext();
                    }

                    // nextNode is the one being shift up
                    Node nextNode = currNode.getNext();
                    nextNode.setPrev(newNode);
                    newNode.setNext(nextNode);
                    newNode.setPrev(currNode);
                    currNode.setNext(newNode);
                }
            }
            
            else if (index == mLength-1) {
            	newNode.setNext(mTail);
                mTail.setNext(newNode);
                mTail = newNode;
            }
            
            else {
                // go backward from tail
                currNode = mTail;
                for (int i = mLength-1; i > index; --i) {
                    currNode = currNode.getPrev();
                }

                Node prevNode = currNode.getPrev();
                prevNode.setNext(newNode);
                newNode.setPrev(prevNode);
                newNode.setNext(currNode);
                currNode.setPrev(newNode);
            }
        }

        mLength += 1;
        
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	Node currNode = mHead;
    	if(currNode.getProcLabel() == null) {
    		
    		return "";
    	
    	}else {
    	
    	
    	String dequeued = currNode.getProcLabel();
        // check if value is head node
        if (currNode.getProcLabel() != null) {
            // check if length of 1
            if (mLength == 1) {
                mHead = mTail= null;
            }
            else {
                mHead = currNode.getNext();
                mHead.setPrev(null);
                currNode = null;
            }

            mLength--;
            
        }return dequeued;
    	}   
         
    } // end of dequeue()
    	

    @Override
    public boolean findProcess(String procLabel) {
        // Implement me
    	Node currNode = mHead;
        for (int i = 0; i < mLength; ++i) {
        	if (currNode.getProcLabel().equals(procLabel)) {
        		return true;
        	}
            currNode = currNode.getNext();
        }
        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me

        return false; // placeholder, modify this
    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me
    	Node currNode = mHead;
    	for (int i = 0; i < mLength; i++) {
    	System.out.print(currNode.getProcLabel() + " ");
            currNode = currNode.getNext();
    	}
        
    } // end of printAllProcess()
    private class Node
    {
        //stores values for both vt and procLabel
        private int vt;
        private String procLabel;
        // points to next node. 
        private Node mNext;
        // to previous node. 
        private Node mPrev;

        public Node(String procLabel, int vt) {
        	this.procLabel = procLabel;
            this.vt = vt;
            mNext = null;
            mPrev = null;
        }

        public String getProcLabel() {
            return procLabel;
        }

        public void setProcLabel(String procLabel) {
            this.procLabel = procLabel;
        }
        
        public int getVt() {
            return vt;
        }

        public void setVt(int vt) {
            this.vt = vt;
        }


        public Node getNext() {
            return mNext;
        }


        public Node getPrev() {
            return mPrev;
        }


        public void setNext(Node next) {
            mNext = next;
        }


        public void setPrev(Node prev) {
            mPrev = prev;
        }
    } // end of inner class Node
} // end of class OrderedLinkedListRQ
