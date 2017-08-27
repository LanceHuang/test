package com.lance.test.common.lang;

import org.junit.Test;

import com.lance.common.entity.Color;

public class EnumTest {

	@Test
	public void testEnum() {
		Color color = Enum.valueOf(Color.class, "RED");
		System.out.println(color);
	}
}
