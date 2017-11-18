import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * This is the executor class of the ADS project Btree.
 * 
 * @author devanshu
 * 
 */
public class treesearch {

	public static void main(String args[]) {
		InputStreamReader reader = null;
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;
		OutputStreamWriter writer = null;
		try {

			long start = System.currentTimeMillis();
			reader = new InputStreamReader(new FileInputStream(args[0]));
			bufReader = new BufferedReader(reader);

			writer = new OutputStreamWriter(new FileOutputStream(
					"output_file.txt"));
			bufWriter = new BufferedWriter(writer);

			String input = bufReader.readLine();
			Btree btree = Btree.initialize(Integer.parseInt(input));
			
			while (!CommonUtil.isNull((input = bufReader.readLine()))) {

				if (input.startsWith("Insert")) {
					insert(input, btree);
				} else if (input.startsWith("Search")) {
					search(bufWriter, input, btree);
				} else {
					throw new OperationNotSupportedException("Invalid Input "
							+ input);
				}
			}

			long time = System.currentTimeMillis() - start;
			System.out.println("Done " + time + "ms");

		} catch (IOException e) {
			System.out.println("The program terminated with error: "
					+ e.getMessage());

		} catch (InvalidOrderException e) {
			System.out.println("InvalidOrderException : " + e.getMessage());

		} catch (OperationNotSupportedException e) {
			System.out.println("OperationNotSupportedException : "
					+ e.getMessage());

		} catch (Exception e) {
			System.out.println("The program terminated with error: "
					+ e.getMessage());

		} finally {
			try {
				reader.close();
				bufReader.close();
				bufWriter.close();
				writer.close();
			} catch (IOException e) {
				System.out.println("The program terminated with error: "
						+ e.getCause());
			}

		}
	}

	private static void search(BufferedWriter bufWriter, String input,
			Btree btree) throws Exception, IOException {
		input = input.split("Search")[1];
		String[] inputPair = input.split(",");
		String output = null;
		if (inputPair.length == 2) {
			output = rangeSearch(btree, inputPair);
		} else if (inputPair.length == 1) {
			output = keySearch(btree, inputPair);
		} else {
			throw new OperationNotSupportedException("Operation not supported");
		}

		output = output.substring(1, output.length() - 1);
		if (CommonUtil.isNull(output) || output.trim().equals("")) {
			output = "Null";
		}
		bufWriter.write(output);
		bufWriter.newLine();
	}

	private static String keySearch(Btree btree, String[] inputPair) {
		String output;
		double key = Double.parseDouble(inputPair[0].substring(1,
				(inputPair[0].length() - 1)));
		output = btree.search(key).toString();
		return output;
	}

	private static String rangeSearch(Btree btree, String[] inputPair) {
		String output;
		double key1 = Double.parseDouble(inputPair[0].substring(1));

		double key2 = Double.parseDouble(inputPair[1].substring(0,
				inputPair[1].length() - 1));

		output = btree.search(key1, key2).toString();
		return output;
	}

	private static void insert(String input, Btree btree) {
		input = input.split("Insert")[1];
		String[] inputPair = input.split(",");
		double key = Double.parseDouble(inputPair[0].substring(1));
		String value = inputPair[1].substring(0, inputPair[1].length() - 1);
		btree.insert(key, value);
	}
}
