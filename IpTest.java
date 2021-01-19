import java.io.*;
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

        boolean[] myArray = new boolean[1000000000];

        for (int i = 0; i < 5; i++){
            FileInputStream inputStream = new FileInputStream(originalInputFile);
            Scanner sc = new Scanner(inputStream, "UTF-8");


            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                long ip = ipToLong(line);
                if (i == 0 && ip >= 0 && ip < 1000000000) {
                    myArray[(int)(ip)] = true;
                } else if ( i == 1 && ip >= 1000000000 && ip < 2000000000) {
                    myArray[(int)(ip - 1000000000)] = true;
                } else if ( i == 2 && ip >= 2000000000 && ip < 3000000000L) {
                    myArray[(int)(ip - 2000000000L)] = true;
                } else if ( i == 3 && ip >= 3000000000L && ip < 4000000000L) {
                    myArray[(int)(ip - 3000000000L)] = true;
                } else if ( i == 4 && ip >= 4000000000L && ip < 5000000000L) {
                    myArray[(int)(ip - 4000000000L)] = true;
                }
            }

            for (int j = 0; j < 1000000000; j++) {
                if (myArray[j]) {
                    result += 1;
                }
                myArray[j] = false;
            }

            inputStream.close();
            sc.close();
        }

        System.out.println("There is " + String.valueOf(result) + " unique ip addresses in file " + originalInputFile);
    }
}

