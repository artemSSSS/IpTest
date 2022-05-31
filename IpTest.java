import java.io.*;
import java.util.BitSet;
import java.util.Scanner;

public class IpTest {

	public static long ipToLong(String ipAddress) {
		String[] ipAddressInArray = ipAddress.split("\\.");

		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {

			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);
			result += ip * Math.pow(256, power);

		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Not enough arguments");
			System.exit(-1);
		}

		String originalInputFile = args[0];

		long result = 0;

		BitSet myArray = new BitSet(1000000000);
		BitSet myArray2 = new BitSet(1000000000);
		BitSet myArray3 = new BitSet(1000000000);
		BitSet myArray4 = new BitSet(1000000000);
		BitSet myArray5 = new BitSet(1000000000);

		FileInputStream inputStream = new FileInputStream(originalInputFile);
		Scanner sc = new Scanner(inputStream, "UTF-8");

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			long ip = ipToLong(line);
			if (ip >= 0 && ip < 1000000000) {
				myArray.set((int)(ip), true);
			} else if (ip >= 1000000000 && ip < 2000000000) {
				myArray2.set((int)(ip - 1000000000), true);
			} else if (ip >= 2000000000 && ip < 3000000000L) {
				myArray3.set((int)(ip - 2000000000L), true);
			} else if (ip >= 3000000000L && ip < 4000000000L) {
				myArray4.set((int)(ip - 3000000000L), true);
			} else if (ip >= 4000000000L && ip < 5000000000L) {
				myArray5.set((int)(ip - 4000000000L), true);
			}
		}

		for (int j = 0; j < 1000000000; j++) {
			if (myArray.get(j)) {
				result += 1;
			}
			if (myArray2.get(j)) {
				result += 1;
			}
			if (myArray3.get(j)) {
				result += 1;
			}
			if (myArray4.get(j)) {
				result += 1;
			}
			if (myArray5.get(j)) {
				result += 1;
			}
		}

		inputStream.close();
		sc.close();

		System.out.println("There is " + String.valueOf(result) + " unique ip addresses in file " + originalInputFile);
	}
}
