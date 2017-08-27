package com.lance.test.common.nio;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class FilesDemo {

	public static void main(String[] args) {

		String datapath = "D:\\note\\tree";
		int maxDepth = 2;// 递归深度
		Path path = Paths.get(datapath);

		try {
			Files.walkFileTree(path, EnumSet.of(FileVisitOption.FOLLOW_LINKS),
					maxDepth, new SimpleFileVisitor<Path>() {
						@Override
						public FileVisitResult visitFile(Path file,
								BasicFileAttributes attrs) throws IOException {
							// Files.delete(file);

							System.out.println(file.toString());
							// System.out.println(file.toAbsolutePath().toString());

							return FileVisitResult.CONTINUE;
						}
					});
		} catch (IOException e) {
			System.err.println("遍历文件时出现异常：" + datapath);
		}
	}
}
