package com.lance.test.common.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.lance.test.common.entity.Article;

public class ObjectOutputStreamDemo {

	public static void main(String[] args) {
		String filepath = "D:/note/test/test.txt";
		ObjectOutputStream outputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			// 将对象写入到文件
			fileOutputStream = new FileOutputStream(filepath);
			outputStream = new ObjectOutputStream(fileOutputStream);

			Article article = new Article();
			outputStream.writeObject(article);

			System.out.println("Finished!!!");
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件：" + e);
		} catch (IOException e) {
			System.out.println("对象未序列化：" + e);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					System.err.println("文件关闭失败：" + e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.err.println("流关闭失败：" + e);
				}
			}
		}

	}

}
