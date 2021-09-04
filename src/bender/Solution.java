package bender;

import java.util.List;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int rows = in.nextInt();
		int columns = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}
		char[][] mapTiles = new char[rows][columns];
		for (int i = 0; i < rows; i++) {
			mapTiles[i] = in.nextLine().toCharArray();
		}
		FuturamaMap futuramaMap = new FuturamaMap(mapTiles);
		Bender bender = new Bender(futuramaMap);
		boolean suicideCabinFound = bender.findSuicideCabin();
		
		if(suicideCabinFound) {
			List<Direction> benderPath = bender.getPath();
			benderPath.forEach(System.out::println);
		}
		else {
			System.out.println("LOOP");
		}
	}

}