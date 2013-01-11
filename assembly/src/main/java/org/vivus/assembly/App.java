package org.vivus.assembly;

public class App {
	public static void call(Test t) {
		Test t2 = new Test();
		t2.setName("cba");
		t.setName("abc");
		t = t2;
	}

	public static void main(String[] arg) {
		Test obj = new Test();
		call(obj);
		System.out.println("obj" + obj.getName());
	}

	static class Test {
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
