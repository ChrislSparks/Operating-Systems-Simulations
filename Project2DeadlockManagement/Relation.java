/* This is the Relation object class
that is required for Project 2.
DO NOT modify this program in ANY way.
Also DO NOT submit this file as part
of your submission for Project 2. */

public class Relation {
	private int[] resource;
	
	public Relation(int resources) {
		resource = new int[resources];
		for (int j = 0; j < resource.length; j++) {
			resource[j] = 0;
		}
	} //End of Constructor
	
	public int getResourceLength() {
		return resource.length;
	} //End of Get Resource Length Method
	
	public int getResource(int resourceIndex) {
		return resource[resourceIndex];
	} //End of Get Resource Method
	
	public void setResource(int resourceIndex, int newValue) {
		resource[resourceIndex] = newValue;
	} //End of Set Resource Method
} //End of Class
