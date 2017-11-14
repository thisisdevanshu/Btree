

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class Executor {

	public static void main(String args[]) {

		try {

			Long start = System.currentTimeMillis();
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream("/home/himanshu/input.txt"));
			@SuppressWarnings("resource")
			BufferedReader bufReader = new BufferedReader(reader);

			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream("/home/himanshu/output_file.txt"));
			@SuppressWarnings("resource")
			BufferedWriter bufWriter = new BufferedWriter(writer);

			String input;
			boolean firstLine = true;
			Btree btree = null;

			while (!CommonUtil.isNull((input = bufReader.readLine()))) {

				String output = null;
				if (firstLine) {
					btree = Btree.initialize(Integer.parseInt(input));
					firstLine = false;
					
				} else if (input.startsWith("Insert")) {
					input = input.split("Insert")[1];
					String[] inputPair = input.split(",");
					double key = Double.parseDouble(inputPair[0].substring(1));
					String value = inputPair[1].substring(0,
							inputPair[1].length() - 1);
					btree.insert(key, value);
					
				} else if (input.startsWith("Search")) {

					input = input.split("Search")[1];
					String[] inputPair = input.split(",");
					if (inputPair.length == 2) {

						double key1 = Double.parseDouble(inputPair[0]
								.substring(1));

						double key2 = Double.parseDouble(inputPair[1]
								.substring(0, inputPair[1].length() - 1));

						output = btree.search(key1, key2).toString();

					} else if (inputPair.length == 1) {
						double key = Double.parseDouble(inputPair[0].substring(
								1, (inputPair[0].length() - 1)));
						output = btree.search(key).toString();
						
					} else {
						throw new Exception("Operation not supported");
					}

					if (CommonUtil.isNull(output) || output.equals("")) {
						output = "Null";
					} else {
						output = output.substring(1, output.length() - 1);
					}
					bufWriter.write(output);
					bufWriter.newLine();
					
				} else if (input.trim().equals("")) {
					continue;
					
				} else {
					
					throw new Exception("Invalid Input " + input);
				}
			}

			reader.close();
			bufWriter.close();

			Long time = System.currentTimeMillis() - start;
			System.out.println("Done " + time + "ms");
			
		} catch (Exception e) {
			System.out.println("The program terminated with error: "
					+ e.getMessage());
		}
	}
}
