package classworkdaysixteen.optionalexample;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

	public static void main(String[] args) {
		// Data
		List<Integer> listOfIntegers = Arrays.asList(15, 16, 18, 1, 2, 3, 4, 56, 7, 8, 6, 5);
		Integer number = 151; // Number to searched in list of numbers

		// search method which returns Optional type which contains index
		Optional<Integer> index = search(listOfIntegers, number);

		if (index.isPresent()) {
			System.out.println(index.get());
		} else {
			System.out.println("Unable to find a number");
		}
		
		Optional<Integer> minValue =min(listOfIntegers);
		if(minValue.isPresent()) {
			System.out.println(minValue.get());
		}
		
		
		Optional<Integer> maxValue =max(listOfIntegers);
		if(maxValue.isPresent()) {
			System.out.println(maxValue.get());
		}
	}

	private static Optional<Integer> search(List<Integer> listOfIntegers, Integer number) {
		Optional<Integer> indexOfNumberFound = Optional.empty();
		for (Integer n : listOfIntegers) {
			if (n == number) {
				indexOfNumberFound = Optional.of(listOfIntegers.indexOf(n));
				break;
			}
		}
		
		return indexOfNumberFound;
		

	}

	private static Optional<Integer> min(List<Integer> listOfIntegers) {
		Integer temp = 0;
		//Converting list into array
		int[] nums = listOfIntegers.stream()
	            .mapToInt(Integer::intValue)//Converting Integer into IntStream
	            .toArray();
		//Bubble Sort
		for (int firstIndex = 0; firstIndex < listOfIntegers.size(); firstIndex++) {
			for (int secondIndex = firstIndex + 1; secondIndex < listOfIntegers.size(); secondIndex++) {
				if(nums[firstIndex] > nums[secondIndex]) {
					temp = nums[firstIndex];
					nums[firstIndex] = nums[secondIndex];
					nums[secondIndex] =temp;
				}

			}

		}
		
		//After sorting in get value from 0 index position    
		Optional<Integer> min = Optional.of(nums[0]);
		return min;
		/**
		Collections.sort(listOfIntegers);
		Optional<Integer> min = Optional.of(listOfIntegers.get(0));
		return min;
		*/

	}
	
	private static Optional<Integer> max(List<Integer> listOfIntegers) {
		//Collections.sort(listOfIntegers.reversed());//Java 21 it will work
		//For Java 17 below code will work
		Collections.sort(listOfIntegers);
		Collections.reverse(listOfIntegers);
		Optional<Integer> max = Optional.of(listOfIntegers.get(0));
		return max;
	}
}