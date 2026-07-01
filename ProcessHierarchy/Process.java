/* This is the Process object class
that is required for Project 1.
DO NOT modify this program in ANY way.
Also DO NOT submit this file as part
of your submission for Project 1. */

public class Process {
	private int parentIndex;
	private int firstChildIndex;
	private int youngerSiblingIndex;
	
	public Process() {
		parentIndex = -1;
		firstChildIndex = -1;
		youngerSiblingIndex = -1;
	} //End of Constructor
	
	public int getParentIndex() {
		return parentIndex;
	} //End of Get Parent Index Method
	
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	} //End of Set Parent Index Method
	
	public int getFirstChildIndex() {
		return firstChildIndex;
	} //End of Get First Child Index Method
	
	public void setFirstChildIndex(int firstChildIndex) {
		this.firstChildIndex = firstChildIndex;
	} //End of Set First Child Index Method
	
	public int getYoungerSiblingIndex() {
		return youngerSiblingIndex;
	} //End of Get Younger Sibling Index Method
	
	public void setYoungerSiblingIndex(int youngerSiblingIndex) {
		this.youngerSiblingIndex = youngerSiblingIndex;
	} //End of Set Younger Sibling Index Method
} //End of Class
