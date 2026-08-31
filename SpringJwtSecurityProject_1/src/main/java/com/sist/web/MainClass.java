package com.sist.web;

import java.util.Arrays;

public class MainClass {
	public static void main(String[] args) {
		String username = "admin";
		byte[] bytes = username.getBytes();
		System.out.println(Arrays.toString(bytes)); // [97, 100, 109, 105, 110]
 	}
}
