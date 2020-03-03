package stExperiment1;

public class Bill {
	private int[] billSequence= {50,20,10,5,5,1,1,1};
	
	public boolean accumulated(int x) {
		for(int i=0;i<billSequence.length;i++) {
			if(x >= billSequence[i]) {
				x-=billSequence[i];
				if(x==0) {
					return true;
				}
			}
		}
		return false;
	}
}
