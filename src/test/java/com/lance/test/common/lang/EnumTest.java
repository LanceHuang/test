package com.lance.test.common.lang;

import com.lance.test.common.entity.Color;
import org.junit.Test;


public class EnumTest {

	@Test
	public void testEnum() {
		Color color = Enum.valueOf(Color.class, "RED");
		System.out.println(color);
	}
}
