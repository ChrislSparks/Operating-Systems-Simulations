/* This is the Block object class
that is required for Project 3.
DO NOT modify this program in ANY way.
Also DO NOT submit this file as part
of your submission for Project 3. */

public class Block {
	private int data;
	private int nextIndex;
	
	public Block() {
		data = 0;
		nextIndex = -1;
	} //End of Constructor
	
	public int getData() {
		return data;
	} //End of Get Data Method
	
	public void setData(int data) {
		this.data = data;
	} //End of Set Data Method
	
	public int getNextIndex() {
		return nextIndex;
	} //End of Get Next Index Method
	
	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	} //End of Set Next Index Method
} //End of Class
