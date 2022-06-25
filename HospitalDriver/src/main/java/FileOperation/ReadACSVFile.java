package FileOperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadACSVFile {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> timeConsumedWithParalelStreamSerial());
		Thread t2 = new Thread(() -> {
			try {
				timeConsumedWithParalelStream();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		t1.setPriority(10);
		t2.setPriority(1);
		t2.start();
//		t2.join();
//		timeConsumedWithParalelStreamSerial();
//		timeConsumedWithParalelStream();
	}

	private static void timeConsumedWithParalelStream() throws InterruptedException {

		long start = System.currentTimeMillis() / 1000L;
		String fileName = "C:\\Users\\SHUDHIR\\Downloads\\Test.csv";
		try {
			Path path = Paths.get(fileName);
			Files.lines(path).parallel().forEach(System.out::println);
			System.out.println(Files.lines(path).skip(1).count());
			System.out.println(Thread.currentThread().getName()+ "parallel");
			Files.lines(path).parallel().map(str -> str.split(",")[1]).skip(1).distinct().forEach(System.out::println);
			Files.lines(path).parallel().map(str -> str.split(",")[1]).skip(1).distinct().count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread.currentThread().sleep(10000L);
		long end = System.currentTimeMillis() / 1000L;
		System.out.println("time consumed parallel: " + (end - start));
	}

	private static void timeConsumedWithParalelStreamSerial() {
		

		long start = System.currentTimeMillis() / 1000L;
		String fileName = "C:\\Users\\SHUDHIR\\Downloads\\Test.csv";
		try {
			Path path = Paths.get(fileName);
			Files.lines(path).forEach(System.out::println);
			System.out.println(Files.lines(path).skip(1).count());
			System.out.println(Thread.currentThread().getName()+"serial");
			Files.lines(path).map(str -> str.split(",")[1]).skip(1).distinct().forEach(System.out::println);
			Files.lines(path).map(str -> str.split(",")[1]).skip(1).distinct().count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis() / 1000L;
		System.out.println("time consumed serial: " + (end - start));
	}
}
