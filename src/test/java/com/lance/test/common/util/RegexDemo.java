package com.lance.test.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	public static void main(String[] args) {
		String str = " var num = '112', var num2 = 'fd'";
		String regex = "var num = './{-}'";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		
		System.out.println("Finished!!!");
	}

}
