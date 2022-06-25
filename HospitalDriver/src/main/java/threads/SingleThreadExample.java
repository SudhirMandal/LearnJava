package threads;

public class SingleThreadExample {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			longTask();
		});
		t.start();
		System.out.println("doing other work in main thread");

	}

	public static void longTask() {
		long t = System.currentTimeMillis();
		long end = t + 3000;

		while (true) {
			System.out.println(end + "," + System.currentTimeMillis());
			if ((System.currentTimeMillis() >= end)) {
				System.out.println("Finishing long task");
				break;
			} else {
				System.out.println("long task");
			}
		}
	}

}
