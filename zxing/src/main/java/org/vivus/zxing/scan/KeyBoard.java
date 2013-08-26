package org.vivus.zxing.scan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeyBoard {
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			Scanner sc = new Scanner(System.in);
			list.add(sc.nextLine());
			System.out.println("11");
			i++;
			if (i == 3) {
				break;
			}
		}

		for (String s : list) {
			System.out.println(s);
		}

	}

}
