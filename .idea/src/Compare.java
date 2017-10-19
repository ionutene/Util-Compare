import java.io.FileInputStream;
import java.io.IOException;

import static com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm.isEqual;

public class Compare {
	public static void main(String[] args) {


		try {
			System.out.println(compareStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static boolean compareStream() throws IOException {
		int i = 0;
		int step = 4096;
		boolean equal = false;

		String file1 = "firstFile";
		String file2 = "2ndFile";
		FileInputStream fi1 = new FileInputStream(file1);
		FileInputStream fi2 = new FileInputStream(file2);

		byte[] fi1Content = new byte[step];
		byte[] fi2Content = new byte[step];

		long size1 = fi1.getChannel().size();
		long size2 = fi2.getChannel().size();

		if (size1 == size2) { //Assumption 1
			while (i * step < file1.length()) {

				fi1.read(fi1Content, 0, step); //Assumption 2
				fi2.read(fi2Content, 0, step); //Assumption 2

				equal = isEqual(fi1Content, fi2Content); //Assumption 2

				if (!equal) { //Assumption 3
					break;
				}

				++i;
			}
		}

		fi1.close();
		fi2.close();
		return equal;
	}



}

