package test.java;

public class SeriesAddition {

	/**
	 * 1+4+9+....+6115729=?
	 * @param args
	 */
	
	public long seriesNumberAddition() {
		long result=0;
		long incremental = 0;
		for(int i=1; i <= 2473; i++) {
			incremental = (long) Math.pow(i, 2);
			result += incremental;
		}		
		return result;		
	}
	public static void main(String[] args) {
		SeriesAddition sa = new SeriesAddition();
		
		log("1+4+9+....+6115729 = " + sa.seriesNumberAddition());
	}
	private static void log(Object aObject){
		System.out.println(String.valueOf(aObject));
	}
}
