package com.lance.test.common.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main(String[] args) {
		String filename = "D:\\note\\test\\1.txt";

		FileReader fileReader = null;
		BufferedReader bufr = null;
		String buf = null;
		try {
			fileReader = new FileReader(filename);
			bufr = new BufferedReader(fileReader);

			while ((buf = bufr.readLine()) != null) {
				System.out.println(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufr != null) {
					bufr.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
