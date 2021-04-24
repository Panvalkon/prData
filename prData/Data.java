package prData;


import java.util.Arrays;

public class Data {
	private double[] data;
	private String[] errors;
	private double min;
	private double max;

	public Data(String[] input, double min, double max) {
		this.min = min;
		this.max = max;
		data = new double[input.length];
		errors = new String[input.length];
		this.processData(input);
	}

	public double calcAverage() {
		double average = 0;
		int cnt = 0, i=0;
		while (i < data.length && data[i] <= max) {
			if (data[i] >= min) {
				average += data[i];
				cnt++;
			}
			i++;			
		}
		if (cnt == 0) {
			throw new DataException("There is no data in the given range");
		}
		return average / cnt;
	}
	
	public double calcStandardDeviation() {
		double sDeviation = 0;
			double average = this.calcAverage();
			int cnt = 0, i =0;
			while (i < data.length && data[i] <= max) {
				if (data[i] >= min) {
					sDeviation += Math.pow((data[i]-average),2);
					cnt++;
				}
				i++;			
			}
			return Math.sqrt(sDeviation/cnt);
	}
	
	public void setRange(String range) {
		
		// if not, try with String.split([";"])
		int pos = range.indexOf(";");
		if (pos < 0) {
			throw new DataException("Data error setting range");
		}
		String s = new String();
		try {
			s = range.substring(0, pos);
			this.min = Double.parseDouble(s);
			s = range.substring(pos+1);
			this.max = Double.parseDouble(s);		
		}
		catch (NumberFormatException e) {
			throw new DataException("Data error setting range");
		}
	}
	
	public double[] getData() {
		return this.data;
	}
	
	public String[] getErrors() {
		return this.errors;
	}
	
	@Override
	public String toString() {
		String s = new String();
		s += "Min: " + this.min + ",Max: " + this.max + ",";
		String ss = new String();
		for(int i=0; i<data.length-1; i++) {
			ss += data[i] + ", ";
		}
		ss += data[data.length - 1];
		s += "[" + ss + "],";
		ss = "";
		
		for(int i=0; i<errors.length-1; i++) {
			ss += errors[i] + ", ";
		}
		ss += errors[errors.length - 1];
		s += "[" + ss + "],";
		
		try {
		 	s += "Average: " + this.calcAverage() + ", StandardDeviation: " + this.calcStandardDeviation();
		}
		catch (DataException e) {
			s += "Average: ERROR, StandardDeviation: ERROR";
		}
		return s;
	}
	
	private void processData(String[] input) {
		int datacnt = 0, errcnt = 0;
		for (int i = 0; i < input.length; i++) {
			try {
				double val = Double.parseDouble(input[i]);
				data[datacnt] = val;
				datacnt++;
			} catch (NumberFormatException e) {
				errors[errcnt] = input[i];
				errcnt++;
			}
		}
		data = Arrays.copyOfRange(data, 0, datacnt);
		errors = Arrays.copyOfRange(errors, 0, errcnt);

	}
}
