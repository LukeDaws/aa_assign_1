import java.io.PrintWriter;
import java.lang.String;


/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class OrderedArrayRQ implements Runqueue {

    /**
     * Constructs empty queue
     */
	
	protected String proc_array[];
	protected int vt_array[];
	protected int position;
	protected int sum;
	
	
    public OrderedArrayRQ() {
        // Implement Me
    	
    	proc_array = null;
    	vt_array = null;
    	
    }  // end of OrderedArrayRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	if (proc_array == null) {
            // allocate array of size 1
    		proc_array = new String[1];
            proc_array[0] = procLabel;
            vt_array = new int [1];
            vt_array[0] = vt;
            
            
            
            
        }else {
            
        	//creates a larger array 
            String new_proc_array[] = new String[proc_array.length + 1];
            int new_vt_array[] = new int[vt_array.length + 1];
            
            
            
			
            //finds where the vt time should be placed
            for(int i = 0; i < vt_array.length; i++){
				
            	if (vt < vt_array[i])
            		position = i;
            	else if (vt == vt_array[i])
            		position = i+1;
            	else 
            		position = vt_array.length;
			}
            
          //this will copy the VT variable of the old array and place them in the correct position with respect to the new input.
            for (int i = 0; i < position; i++) {
            	new_vt_array[i] = vt_array[i];
        	}
            for (int j = vt_array.length; j > position; j--) {
            	new_vt_array[j] = vt_array[j-1];
        	}
            new_vt_array[position] = vt;
			//This will place the proc labels for both the old and new variables in their respective positions
            for (int i = 0; i < position; i++) {
            	new_proc_array[i] = proc_array[i];
        	}
            for (int j = proc_array.length; j > position; j--) {
            	new_proc_array[j] = proc_array[j-1];
        	}
            new_proc_array[position] = procLabel;
            
           // update reference of array to point to newArray
            proc_array = new_proc_array;
            vt_array = new_vt_array;
             
            
        }
    	
    	
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	if(proc_array == null) {
    		
    		return "";
    	
    	}else{
    		
    		
    		//create a new array smaller than the original
    		String new_proc_array[] = new String[proc_array.length - 1];
            int new_vt_array[] = new int[vt_array.length - 1];
    		
          //Takes the first in the queue so it can be output later
            String output = proc_array[0];
            
          //copies old array minus first element in the array  
            for(int i = 1; i < vt_array.length; i++) {
    			new_proc_array[i-1] = proc_array[i];
    			new_vt_array[i-1] = vt_array[i];
    			    			
    		}
            
            proc_array = new_proc_array;
            vt_array = new_vt_array;
                    
            
          //this should output the dequeued process label
            return output;
    	}
    		
    		
        
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel){
        // Implement me
    if (proc_array != null) {
    	for (int i = 0; i < proc_array.length; ++i) {
    		//if there is a match it will return true
    		if (proc_array[i].equals(procLabel)) {
    		
    			return true;	
    		
    		//no match should return false
    		}
    	}
    }
    		return false; 
            }
    	
      // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
    	//this will search through and try and find the the process
    	if (proc_array != null) {
    		
    	for (int i = 0; i < vt_array.length; i++) {
      	//if it finds it then create a new smaller array and crates a variable of the index position
    		if (proc_array[i].equals(procLabel)) {
    			position = i;
    			
    			String new_proc_array[] = new String[proc_array.length - 1];
    	        int new_vt_array[] = new int[vt_array.length - 1];
    	        
    	    	for (int j = 0; j <= i; j++) {
    	         	    	    		
    	    		new_vt_array[j] = vt_array[j];
    	    		new_proc_array[j] = proc_array[j];
    	    		}
    	    	
    	    	for (int j = position +1; j < proc_array.length; j++) {
            		     	
	    	   		new_vt_array[j-1] = vt_array[j];
    	    		new_proc_array[j-1] = proc_array[j];
    	    		}
    	    	
    	    	proc_array = new_proc_array;
                vt_array = new_vt_array;
    	    	
                return true;
    		
    		}
    			

    	    	
    		}
    		
    		
    	} 
    	
    	
        return false;
    	// placeholder, modify this
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me
    	if (proc_array != null) {
    		int sum = 0;
    		//this will search through and try and find the the process
        	for (int i = 0; i < proc_array.length; ++i) {
          	//if it finds it then creates a new smaller array and crates a variable of the index position
        		if (proc_array[i].equals(procLabel)) {
        			position = i ;
        			//this should add all the preceding elements in the array
        			for (int j = 0; j < position; j++) {
        				
        				sum += vt_array[j];
            		}
            		return sum;
        		}  		
        	  		
        	}
    	}
        return -1; // placeholder, modify this
    }// end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me
    		if (proc_array != null) {
    		int sum = 0;
    		//this will search through and try and find the the process
        	for (int i = 0; i < proc_array.length; ++i) {
          	//if it finds it then creates a new smaller array and crates a variable of the index position
        		if (proc_array[i].equals(procLabel)) {
        			position = i ;
        			
        			for (int j = proc_array.length-1; j > position; j--) {
        				
        				sum += vt_array[j];
            		}
            		return sum;
        		}  		
        	
        	
    		}
    		
    		}
        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me
    	if (proc_array != null) {
            for (int i = 0; i < proc_array.length; i++) {
                System.out.print(proc_array[i] + " ");
            }
        }

        System.out.println("");
    } // end of printAllProcesses()

} // end of class OrderedArrayRQ
