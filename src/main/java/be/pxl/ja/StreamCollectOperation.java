package be.pxl.ja;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCollectOperation {

	public static void main(String[] args) {

		System.out.println(IntStream.of(1, 2, 6).boxed().collect(Collectors.toList()));

		List<String> theBeatles =
				Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr")
				.collect(Collectors.toList());
		System.out.println(theBeatles);
	}

}
