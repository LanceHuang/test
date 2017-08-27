package com.lance.test.common.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {

	public static void main(String[] args) {
		String filename = "D:\\note\\test\\1.txt";

		FileWriter fileWriter = null;
		BufferedWriter bufw = null;
		try {
			fileWriter = new FileWriter(filename, true);
			bufw = new BufferedWriter(fileWriter);

			bufw.write("Hello BufferedWriter！！！");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufw != null) {
					bufw.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Finished!!!");
	}

}
