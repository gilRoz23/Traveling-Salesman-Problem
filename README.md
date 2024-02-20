# Flight Tour Solver

This Java program provides solutions for various tasks related to solving flight tour problems using SAT solvers. The program includes functions for checking the legality of matrices, generating CNF encodings for different constraints, and solving the flight tour problem.

## Usage

To use this program, simply run the `main` method in the `Assignment2` class. This will demonstrate the usage of the various functions implemented in the class.

## Functionality

### Part A - Tasks 1-11

- Task 1: Check if a given matrix is a square matrix.
- Task 2: Check if a given matrix is symmetric.
- Task 3: Check if a given matrix is anti-reflexive.
- Task 4: Check if a given matrix is a legal instance (square, symmetric, and anti-reflexive).
- Task 5: Check if a given array is a permutation (contains each element exactly once).
- Task 6: Check if a given tour has legal steps (following the flight connections).
- Task 7: Check if a given tour is a valid solution.
- Task 8: Evaluate a given CNF (conjunctive normal form) formula.
- Task 9: Generate CNF for the "at least one" constraint.
- Task 10: Generate CNF for the "at most one" constraint.
- Task 11: Generate CNF for the "exactly one" constraint.

### Part B - Tasks 12-20

- Task 12a: Map a pair of indices (i, j) to a single index k.
- Task 12b: Reverse mapping from a single index k to a pair of indices (i, j).
- Task 13: Generate CNF for ensuring that one city is visited each day.
- Task 14: Generate CNF for ensuring that each city is visited exactly once.
- Task 15: Generate CNF for fixing the source city.
- Task 16: Generate CNF for ensuring no illegal steps (no flights where no connection exists).
- Task 17: Encode all constraints into a single CNF.
- Task 18: Decode a given assignment to a tour.
- Task 19: Solve the flight tour problem and return a valid tour.
- Task 20: Solve the flight tour problem and print two different valid tours.

This program provides a comprehensive solution for solving flight tour problems using SAT solvers, enabling efficient and accurate solutions to complex routing problems.
