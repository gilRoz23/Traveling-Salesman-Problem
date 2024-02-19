import java.util.Arrays;

import org.sat4j.tools.SolutionCounter;

public class Assignment2 {
	
	
	
	public static void main(String[] args) {
		boolean[][] flights = {{false, true, false, true, true}, {true, false, true, false, true}, {false, true, false, true, false}, {true, false, true, false, true},{true, true, false, true, false}};
		System.out.println(solve2(flights));
	}

	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/

	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix) {
		boolean isSquare = true;
		if(matrix == null || matrix.length == 0) {
			isSquare = false;
		}
		for(int i =0; isSquare && i < matrix.length; i = i+1) {;
		if (matrix[i] == null) {
			isSquare = false;
		}
		else if(matrix.length != matrix[i].length) {
			isSquare = false;
		}
		}
		return isSquare;
	}

	//task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix) {
		boolean isSymetric = true;
		if (matrix == null || matrix.length == 0) {
			isSymetric = false;
		}
		for(int i = 0; isSymetric & i< matrix.length; i = i+1) {
			if(matrix[i] == null) {
				isSymetric = false;
			}
			for(int j = 0; isSymetric & j < matrix[i].length; j = j+1) {
				//check if matrix[i][j] == matrix[j][i] for each 0 < i,j < n

				if(matrix[i][j] != matrix[j][i]) {
					isSymetric = false;
				}
			}
		}
		return isSymetric;

	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
		boolean isAntiReflexive = true;

		if (matrix == null || matrix.length == 0) {
			isAntiReflexive = false;
		}


		for (int i = 0; isAntiReflexive & i < matrix.length; i = i+1) {

			if (matrix[i] == null || matrix[i].length == 0) {
				isAntiReflexive = false;
			}

			for (int j = 0; isAntiReflexive & j <= i; j = j+1) {
				//making sure when i == j if array[i][j] is false 
				if (i == j & matrix[i][j] != false) {
					isAntiReflexive = false;
				}
			}
		}
		return isAntiReflexive;
	}

	// task 4
	public static boolean isLegalInstance(boolean[][] matrix) {
		boolean isLegal = isSquareMatrix(matrix) && isSymmetricMatrix(matrix) && isAntiReflexiveMatrix(matrix);
		return isLegal;
	}

	// task 5
	public static boolean isPermutation(int[] array) {
		boolean isPermutation = true;

		for (int i = 0; isPermutation & i < array.length; i = i+1) {

			//check if array[i] is in legal range
			if(array[i] < 0 | array[i] >= array.length) {
				isPermutation = false;
			}

			//make sure every number shows up only once
			//the combination of both conditions makes sure that the whole legal range will be in array
			for (int j = i+1; isPermutation & j < array.length; j = j+1) {
				if(array[i] == array[j]) {
					isPermutation = false;
				}
			}
		}
		return isPermutation;		
	}

	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {
		boolean legalStep = true;

		//check if a step between tour[0] to tour[n-1] is legal
		if( flights[tour[0]][tour[tour.length - 1]] == false) {
			legalStep = false;
		}

		//check if a step between tour[i] to tour[i-1o is legal
		for (int i = 1; legalStep & i < tour.length; i = i+1) {

			if( flights[tour[i-1]][tour[i]] == false ) {
				legalStep= false;
			}
		}
		return legalStep;		
	}

	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour) {

		if(tour == null || flights.length != tour.length) {
			throw new IllegalArgumentException("flights array and tour array aren't equal in length");
		}

		if (tour[0] == 0 & isPermutation(tour) & hasLegalSteps(flights, tour))
			return true;
		else
			return false;
	}

	// task 8
	public static boolean evaluate(int[][] cnf, boolean[] assign) {

		boolean isSatisfying = true;

		//check as long as previous clauses were satisfied
		for(int i = 0; isSatisfying & i< cnf.length; i = i+1) {

			isSatisfying = false;


			//check until it has one literal of true, if don't find, clause = false
			for(int j = 0; !isSatisfying & j < cnf[i].length; j = j+1) {

				//if literal is positive and it's suitable boolean is true, or literal is negative and it's suitable boolean is false
				//then it equals true
				if( (cnf[i][j] > 0  &&  assign[cnf[i][j]])  |  (cnf[i][j] < 0  &&  assign[-1*(cnf[i][j])] == false) ) {
					isSatisfying = true;
				}
			}
		}
		return isSatisfying;		
	}

	// task 9
	public static int[][] atLeastOne(int[] lits) {
		int[][]	atLeastOneCnf = {lits};
		return atLeastOneCnf;		
	}

	// task 10
	public static int[][] atMostOne(int[] lits) {
		//we'll make sure that above 1 True literal the cnf won't be satisfied

		//by Bernuli equation we'll make a new array that contain  clauses as the number of possible pairs, and each clause contain a pair
		int[][] atMostCnf = new int[ ( (lits.length) * (lits.length-1) ) / 2 ][2];		
		int cnfIndex = 0;

		//pairs each 2 different literals
		for(int i = 0; i< lits.length; i++) {

			for(int j = i+1; j< lits.length; j++) {

				//to make sure 2 and above true literals will make cnf unsatisfied we'll force at least one of each pair to be False
				atMostCnf[cnfIndex][0] = lits[i] * -1;
				atMostCnf[cnfIndex][1] = lits[j] * -1;
				cnfIndex+=1;
			}
		}
		return atMostCnf;			
	}

	// task 11
	public static int[][] exactlyOne(int[] lits) {
		int[][] atMostCnf = atMostOne(lits);
		int[][] atLeastCnf = atLeastOne(lits);
		int[][] exactlyOneCnf = new int[atLeastCnf.length + atMostCnf.length][];
		exactlyOneCnf[0] = atLeastCnf[0];

		for(int i = 1; i<exactlyOneCnf.length; i++) {
			exactlyOneCnf[i] = atMostCnf[i-1];
		}

		return exactlyOneCnf;
	}

	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/

	// task 12a
	public static int map(int i, int j, int n) {
		int specificPhase = i*n + j + 1;
		return specificPhase;		
	}

	// task 12b
	public static int[] reverseMap(int k, int n) {
		int i = (k-1)/n;
		int j = (k-1)%n;
		int[] currentPhase = {i,j};
		return currentPhase;
	}

	// task 13
	public static int[][] oneCityInEachStep(int n) {

		int[][] oneCityEachStep = new int[ n*( 1 + n*(n-1)/2 )][];  // number of cells = n days * (exactly one city for i day).array length
		// exactly one array.length = at leastOne.length + at mostOne.length
		int lastOneStepIndex = 0;

		//for each day
		for(int i = 0; i<n; i++) {

			//n cities
			int[] lits = new int[n];

			//for each city
			for(int j = 0; j<n; j++) {
				//the specific incident number of current day and current city
				lits[j] = map(i, j, n);
			}

			//cnf which forces only one of incidents be true
			int[][] exactlyOneLits = exactlyOne(lits);

			//insert the specific cnf of day i in to the main cnf
			for(int m = 0; m<exactlyOneLits.length; m++) {
				oneCityEachStep[lastOneStepIndex + m] = exactlyOneLits[m];
			}
			//make sure cnf of next day will be inserted after the last cnf clause 
			lastOneStepIndex+=exactlyOneLits.length;
		}
		return oneCityEachStep;
	}






	// task 14
	public static int[][] eachCityIsVisitedOnce(int n) {

		int[][]  CityVisitedOnceCnf = new int[ n*( 1 + n*(n-1)/2 )][];  // number of cells = n cities * (exactly one day for i city).array length
		// exactly one array.length = at leastOne.length + at mostOne.length
		int lastIndex = 0;

		//for each city
		for(int j = 0; j < n; j++) {

			int[] lits = new int[n];

			//for each day
			for(int i = 0; i< n; i++) {
				lits[i] = map(i, j, n);
			}

			//each city gets one day
			int[][] exactOneLits = exactlyOne(lits);

			for(int m = 0; m< exactOneLits.length; m++) {
				CityVisitedOnceCnf[lastIndex + m] = exactOneLits[m];
			}

			lastIndex+= exactOneLits.length;
		}

		return CityVisitedOnceCnf;
	}

	// task 15
	public static int[][] fixSourceCity(int n) {
		int[][] sourceCity = {{1}};
		return sourceCity;
	}








	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights) {

		int noLinesNum = 0;

		for(int i = 0; i< flights.length; i++) { // each city

			for(int j = 0; j< flights[i].length; j++) { //each second city

				if(i != j & flights[i][j] == false) // if no line between them, count it
					noLinesNum +=1;
			}
		}

		int mainCnfLength = noLinesNum * flights.length; // noLinesNum = number of illegal lines, in the both directions
														 // flight.length = number of days.

		int[][] noIllegalStepsCnf = new int[mainCnfLength][];
		int lastIndex = 0;


		for(int i = 0; i< flights.length; i++) { // i = day number

			for(int j = 0; j< flights.length; j++) { // j = city

				for(int k = 0; k< flights.length; k++) { // k = second city

					if (j!=k & flights[j][k] == false) { // no line between j and k

						// make lits of the two specific phases
						int[] illegalPhasesLitsIJK = {map(i%flights.length, j, flights.length), map((i+1) % (flights.length), k, flights.length)};
						
						// make cnf which forces at most one step to be taken
						int[][] atMostOneForIJK = atMostOne(illegalPhasesLitsIJK);

						// insert the cnf into the main cnf
						for(int m = 0; m< atMostOneForIJK.length; m++) {
							noIllegalStepsCnf[m + lastIndex] = atMostOneForIJK[m];

						}
						lastIndex+= atMostOneForIJK.length;
					}
				}
			}
		}
		return noIllegalStepsCnf;
	}

	// task 17
	public static int[][] encode(boolean[][] flights) {

		int[][] enforceslegalTourCnf = new int[oneCityInEachStep(flights.length).length + eachCityIsVisitedOnce(flights.length).length + fixSourceCity(flights.length).length + noIllegalSteps(flights).length ][];

		int lastIndex = 0;


		for(int i = 0; i < oneCityInEachStep(flights.length).length; i++ ) {
			enforceslegalTourCnf[i] = oneCityInEachStep(flights.length)[i];
		}
		lastIndex+= oneCityInEachStep(flights.length).length;



		for(int i = 0; i< eachCityIsVisitedOnce(flights.length).length; i++) {

			enforceslegalTourCnf[lastIndex+i] = eachCityIsVisitedOnce(flights.length)[i];
		}
		lastIndex+= eachCityIsVisitedOnce(flights.length).length;



		for(int i =0; i<fixSourceCity(flights.length).length; i++) {
			enforceslegalTourCnf[lastIndex+i] = fixSourceCity(flights.length)[i];
		}
		lastIndex+= fixSourceCity(flights.length).length;



		for(int i = 0; i<noIllegalSteps(flights).length; i++) {
			enforceslegalTourCnf[lastIndex+i] = noIllegalSteps(flights)[i];
		}

		return enforceslegalTourCnf;		
	}

	// task 18
	public static int[] decode(boolean[] assignment, int n) {

		if(assignment == null) {
			throw new IllegalArgumentException("assignment is null");
		}

		else if (assignment.length != (n*n)+1) {
			throw new IllegalArgumentException("assaignment is not in suitable length");
		}

		int[] tour = new int[n];
		
		//making sure default array isn't assigns city number 0
		for(int i = 0; i<tour.length; i++) {
			tour[i] = -1;
		}

		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				if(assignment[map(i, j, n)]) {
					tour[i] = j;
				}
			}
		}
		return tour;
	}

	// task19
	public static int[] solve(boolean[][] flights) {

		
		//check legal instance
		if (!isLegalInstance(flights))
			throw new IllegalArgumentException("Instance is Illegal");


		int nVars = (flights.length)*(flights.length);
		SATSolver.init(nVars);


		//making cnf
		int[][] enforcesLegalTourCnf = encode(flights);


		SATSolver.addClauses(enforcesLegalTourCnf);


		boolean[] boleanAssaignment = SATSolver.getSolution();



		if (boleanAssaignment==null)
			throw new IllegalArgumentException("timeout"); 


		else if (boleanAssaignment.length==0)
			return null;		


		else {

			int[] tourAssaignment = decode(boleanAssaignment, flights.length);

			//check legal solution
			if (!isSolution(flights, tourAssaignment))
				throw new IllegalArgumentException("solution is Illegal");

			else {
				return tourAssaignment;
			}


		}

	}

	// task20
	public static boolean solve2(boolean[][] flights) {
		
		//check legal instance
		if (!isLegalInstance(flights))
					throw new IllegalArgumentException("Instance is Illegal");
		
		//finding first solution
		int nVars = (flights.length)*(flights.length);
		SATSolver.init(nVars);
		
		
		//making cnf
		int[][] enforcesLegalTourCnf = encode(flights);	
		
		SATSolver.addClauses(enforcesLegalTourCnf);
		
		
		boolean[] boleanAssaignment1 = SATSolver.getSolution();
		
		int[] solution1;
		
		//didn't find solution in time
		if (boleanAssaignment1==null)
			throw new IllegalArgumentException("timeout");
		
		//unable to be satisfied
		else if (boleanAssaignment1.length==0)
			return false;
		
		else {
			
			solution1 = decode(boleanAssaignment1, flights.length);

			//check legal solution
			if (!isSolution(flights, solution1))
				return false;
			else
				System.out.println(Arrays.toString(solution1));
		}
		
		
		//finding legal second different solution
		
		SATSolver.init(nVars);
		
		SATSolver.addClauses(enforcesLegalTourCnf);
		
		//not second city again
		int[] noSecondCity = {-1 * map(1, solution1[1], flights.length)};
		SATSolver.addClause(noSecondCity);
		
		//not last city to be second
		int[] noLastCity = {-1 * map(1, solution1[solution1.length-1], flights.length)};
		SATSolver.addClause(noLastCity);
		
		
		boolean[] booleanAssaignment2 = SATSolver.getSolution();
		
		int[] solution2;
		
		
		if (booleanAssaignment2==null)
			throw new IllegalArgumentException("timeout");
		
		else if (booleanAssaignment2.length==0)
			return false;
		
		else {

			solution2 = decode(booleanAssaignment2, flights.length);

			//check legal solution
			if (!isSolution(flights, solution2))
				return false;
			
			else {
				System.out.println(Arrays.toString(solution2));
				return true;
			}
		}
				
	}

}
