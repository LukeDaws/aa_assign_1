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
     * 
     */
	
	
	String[] proclabel_array = {null};
	int[] vt_array = {};
	
	//I'm not sure if I need these here just yet but I'm gonna keep the variables there.
	int vt_array_length = vt_array.length;
	int proclabel_array_length = proclabel_array.length;
	int[] new_vt_array = {};	
	int position = -1;
	String[] new_proclabel_array = {null};
	
	
    public OrderedArrayRQ() {
        // Implement Me
    	
    	//creates an array of 1
    	
    	proclabel_array = new String[1];
    	vt_array = new int[1];
		

    }  // end of OrderedArrayRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me

    	
    	//will check if a the array is empty and if so place the input straight into the array.
    	if (vt_array_length == 0) {		
    			 
			proclabel_array[0] = procLabel;
			vt_array[0] = vt;
			System.out.println(proclabel_array .length);
			System.out.println(vt_array.length);
			System.out.println(proclabel_array[0]);
			System.out.println(vt_array[0]);	
			return;
			//if the array already already has a variables then it will search through it.		
		} else {
				
						
			 
			String proclabel_array_input = procLabel;
			
			//this creates a new array that will contain the original array and the new input 
			int vt_array_input = vt;
			new_vt_array = new int[vt_array_length + 1];
			new_proclabel_array = new String[vt_array_length + 1];
			
			
			//this searches through the array to find the a position where the VT is greater than the position before it and less than the one after it
			for(int i = 0; i < vt_array_length - 1; i++){
				
				if (vt_array[i] < vt_array_input || vt_array[i]> vt_array_input )
				position = i;
			}
			//this will copy the VT variable of the old array and place them in the correct position with respect to the new input.
			for(int i = 0; i < vt_array_length +1; i++ ) {
				if (i < position)
					new_vt_array[i] = vt_array[i];
				else if (i == position)
					new_vt_array[i] = vt_array_input;
				else
					new_vt_array[i] = vt_array[i -1];
			}
			//This will place the proc labels for both the old and new variables in their respective positions
			for(int i = 0; i < vt_array_length +1; i++ ) {
				if (i < position)
					new_proclabel_array[i] = proclabel_array[i];
				else if (i == position)
					new_proclabel_array[i] = proclabel_array_input;
				else
					new_proclabel_array[i] = proclabel_array[i -1];
			}
			
			// this should then replace the the old array with the new array that has the new input.
			vt_array = new_vt_array;
			proclabel_array = new_proclabel_array;
			vt_array_length = vt_array.length;
			position = -1;
			
			}
		}
    	
    	
     // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	//if there is nothing left in the array this should return an empty string
    	if(proclabel_array == null) {
    		
    		return "";
    	
    	}else {
    	
    	//this should create a new array smaller than the original
    	new_proclabel_array = new String[vt_array_length - 1];
		new_vt_array = new int[vt_array_length - 1];
		
		//this takes the first in the queue so it can be output later
		String output = proclabel_array[0];
			
		//this should loop through and copy from the old array and place it in the new array.
		for(int i = 1; i < vt_array_length +1; i++) {
			new_proclabel_array[i-1] = proclabel_array[i];
			new_vt_array[i-1] = vt_array[i];
		}
		
		//This will replace the old array with the new array
		vt_array = new_vt_array;
		proclabel_array = new_proclabel_array;
		vt_array_length = vt_array.length;
		
		//this should output the dequeued process label
        return output; 
    } // end of dequeue()
   }

    @Override
    public boolean findProcess(String procLabel){
        // Implement me
    	
    	//this will search through the label array
    	for (int i = 0; i < vt_array_length + 1;i++) {
    		//if there is a match it will return true
    		if (proclabel_array[i] = procLabel) {
    		
    			return true;	
    		
    		//no match should return false
    		}else {
    		return false; 
        
    }
  } // end of findProcess()  
 }

    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
    	//this will search through and try and find the the process
    	for (int i = 0; i < vt_array_length + 1;i++) {
      	//if it finds it then creates a new smaller array and crates a variable of the index position
    		if (proclabel_array[i] = procLabel) {
    			new_proclabel_array = new String[vt_array_length - 1];
    			new_vt_array = new int[vt_array_length - 1];
    			position = i;
    			
    		}
    	}
    		//if position is not null will add variables from old array to new skipping supplied index
    	if (position != -1){
    	for (int i = 0, k = 0; i < vt_array_length +1; i++);
    		int i;
    		int k;
			if (i == position) {
    			continue;
    		}
    		
			new_proclabel_array[k++] = proclabel_array[i];
			new_vt_array[k++] = vt_array[i];
    		
    		
    	}else{   	
        	//will return false if position is null
        return false;
        		}
    	vt_array = new_vt_array;
		proclabel_array = new_proclabel_array;
		vt_array_length = vt_array.length;
		position = -1;
    	
    	return true;
    	}
     // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me
    	//this will search through and try and find the the process
    	for (int i = 0; i < vt_array_length + 1;i++) {
      	//if it finds it then creates a new smaller array and crates a variable of the index position
    		if (proclabel_array[i] = procLabel) {
    			new_proclabel_array = new String[vt_array_length - 1];
    			new_vt_array = new int[vt_array_length - 1];
    			position = i;
    			
    		}
    	}
    	if (position != -1){
    		//this should add all the preceding elements in the array
    		int sum;
			for ( int i = 0; i < position; i++) {
    			sum += vt_array[i];
    		}
    		return sum;
    	}else {
    	
        return -1;
    	}
    }// end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me
    	//this will search through and try and find the the process
    	for (int i = 0; i < vt_array_length + 1;i++) {
      	//if it finds it then creates a new smaller array and crates a variable of the index position
    		if (proclabel_array[i] = procLabel) {
    			new_proclabel_array = new String[vt_array_length - 1];
    			new_vt_array = new int[vt_array_length - 1];
    			position = i;
    			
    		}
    	}
    	if (position != -1){
    		//this should add all the preceding elements in the array
    		int sum;
			for ( int i = position +1; i < vt_array_length; i++) {
    			sum += vt_array[i];
    		}
    		return sum;
    	}else {
    	
        return -1;
    	}
       
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me
    	if(proclabel_array == null) {
    		
    		return;
    	
    	}else {
    		for(int i = 0; i < vt_array_length; i++) {
    			return;
    			
    		}
    } // end of printAllProcesses()

   }
}// end of class OrderedArrayRQ
