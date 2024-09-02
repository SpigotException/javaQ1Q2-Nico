package _test.backtracking;


class BacktrackingMagischesQuadrat{

	private static final int size = 4;

	private int magic_sum;

	private int[][] quadrat;

	// INFO: Optimised Solution
	private boolean[] usedNumbers;
	private int[] rowSum;
	private int[] colSum;
	private int diag1Sum;
	private int diag2Sum;

	BacktrackingMagischesQuadrat(){
		quadrat = new int[size][size];
		usedNumbers = new boolean[size * size + 1];
		rowSum = new int[size];
		colSum = new int[size];
		diag1Sum = 0;
		diag2Sum = 0;
		initialisiere();
	}

	public void initialisiere(){
		for ( int y=0 ; y < size ; y++ ) {
			for ( int x=0 ; x < size ; x++ ) {
				quadrat[x][y] = 0;
			} 
		}
		int sum = 0;
		for (int i = 0; i <= size * size; i++){
			sum += i;
		}
		this.magic_sum = sum / size;
	}

	public void findeLoesung(){
		findeLoesung(0);
		System.out.println("*** Loesung: ***");
		this.ausgeben();
	}
	
	public boolean findeLoesung(int pStelle){
		// Ausgeben lässt es deutlich länger dauern
		//ausgeben();
		//TODO abbruchbedingung
		if (pStelle == size * size){
			return magisch();
		}
		// x: Spaltennummer (0..2); 
		// y: Zeilennummer(0..2)
		int x = pStelle % size;
		int y = pStelle / size;
		//TODO rekursiver aufruf

		// Go Through options
		for (int i = 1; i<= size * size; i++){
			// INFO: Optimised Solution
			if (!usedNumbers[i]) {
				quadrat[x][y] = i;
				usedNumbers[i] = true;
				rowSum[y] += i;
				colSum[x] += i;
				if (x == y) diag1Sum += i;
				if (x + y == size - 1) diag2Sum += i;

				if (isValid(x, y) && findeLoesung(pStelle + 1)) {
					return true;
				}

				// Backtrack
				usedNumbers[i] = false;
				rowSum[y] -= i;
				colSum[x] -= i;
				if (x == y) diag1Sum -= i;
				if (x + y == size - 1) diag2Sum -= i;
				quadrat[x][y] = 0;
			}


			/*
			INFO: MY Solution
			// set current square to that option
			this.quadrat[x][y] = i;
			if (esGibtDoppelte()) {
				this.quadrat[x][y] = 0;
				continue;
			}
			// if it found a solution that way, Yay !
			if (findeLoesung(pStelle + 1)) {
				return true;
			}
			// else, undo the current move, and try the next
			if (i == 9){
				this.quadrat[x][y] = 0;
			}
			*/
		}
		return false;
	}
	
	public boolean esGibtDoppelte(){
		boolean[] dabei = new boolean[size*size+1];
		for ( int y=0 ; y < size ; y++ ) {
			for ( int x=0 ; x < size ; x++ ) {
				int index = quadrat[x][y];
				if(index != 0 && dabei[index]){
					return true;
				}
				dabei[index] = true;
			} 
		}
		return false;
	}

	/* INFO: Old version
	public boolean magisch(){
		// auf Nullen testen
		for(int x=0; x<size; x++){
			for(int y=0; y<size; y++){
				if(quadrat[x][y] == 0){
					return false;
				}
			}
		}
		if(esGibtDoppelte()){
			return false;
		}
		// Summen testen
		int s=0, t=0;
		// 1. Diagonale
		for ( int x=0 ; x < size ; x++ ) s+=quadrat[x][x];
		//2. Diagonale
		for ( int x=0 ; x < size ; x++ ) t+=quadrat[size-x-1][x];
		if (t != s) return false;
		// Zeilen
		for ( int y=0 ; y < size ; y++ ) {
			int k=0;
			for ( int x=0 ; x < size ; x++ ) {
				k += quadrat[x][y];
			}
			if (k != s) return false;
		}
		//Spalten
		for ( int x=0 ; x < size ; x++ ) {
			int k=0;
			for ( int y=0 ; y < size ; y++ ) {
				k += quadrat[x][y];
			}
			if (k != s) return false;
		}
		System.out.println("*** It's magic!!! ***");
		return true;
	}
	 */

	private boolean magisch() {
		for (int i = 0; i < size; i++) {
			if (rowSum[i] != magic_sum || colSum[i] != magic_sum) {
				return false;
			}
		}
		return diag1Sum == magic_sum && diag2Sum == magic_sum;
	}

	public void ausgeben(){
		for ( int y=0 ; y < size ; y++ ) {
			String str = "";
			for ( int x=0 ; x < size ; x++ ) {
				if (x < size - 1) {
					str = str + quadrat[x][y] + "|";
				}
				else {
					str = str + quadrat[x][y];
				}
				//if (x < size - 1) System.out.print(quadrat[x][y] + "|");
				//else System.out.print(quadrat[x][y]);
			} 
			System.out.print(str + "\n");
			if (y != size - 1) System.out.println("-".repeat(str.length()));

		}
		System.out.println();
	}

	private boolean isValid(int x, int y) {
		return rowSum[y] <= magic_sum && colSum[x] <= magic_sum
				&& (x != y || diag1Sum <= magic_sum)
				&& (x + y != size - 1 || diag2Sum <= magic_sum);
	}

	
	public static void main(String[] args) {
		BacktrackingMagischesQuadrat mq = new BacktrackingMagischesQuadrat();
		long startTime = System.nanoTime();
		mq.findeLoesung();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
		System.out.println("Duration to execute: " + duration + "ms");
	}
}