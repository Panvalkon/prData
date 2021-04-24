import java.util.Arrays;

import prData.Data;
import prData.DataException;

public class MainData {
	public static void main(String[] args) {
		
		try {
			double min = 0, max = 0;
			String[] input = new String[0];
			if(args.length < 3) {
				throw new RuntimeException("Error, there are not enough values");
			}
			int N = 0;
			try {
				min = Double.parseDouble(args[N++]);
				max = Double.parseDouble(args[N++]);
			}
			catch (NumberFormatException e) {
				throw new DataException("Error, when converting a value to a real number (" + e.getMessage() + ")");
			}
			input = Arrays.copyOfRange(args, N, args.length);
			Data data = new Data(input, min, max);
			System.out.println(data.toString());
			data.setRange("0;4");
			System.out.println(data.toString());			
			data.setRange("15 25");
			System.out.println(data.toString());
		}
		catch (DataException e) {
			System.out.println(e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}
