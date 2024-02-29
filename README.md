# Traveling Salesman Problem Solver using SAT Reduction

## Description

This Java program solves the Traveling Salesman Problem (TSP) using the Boolean Satisfiability Problem (SAT) reduction. Given a list of cities and flights between them, the program finds a route that starts and ends at the home port, visits each city exactly once, and returns to the home port. The algorithm is based on reducing the TSP to the SAT problem.

## Files

- `Assignment.java`: Contains function templates for the tasks in the assignment.
- `SATSolver.java`: Interface for working with a SAT solver.
- `org.sat4j.core.jar`: SAT solver library.

## Usage

To use the program, download the provided files and follow the instructions in the assignment description.

## Example

For example, given 4 cities {0,1,2,3} and flights {1,3},{2,3},{1,2},{0,3},{0,2}, a valid route would be 0 → 3 → 1 → 2 → 0.

## Representations

### Instance Representation

The TSP instance for n cities is represented by a Boolean matrix `flights` of size n x n, where `flights[i][j]` is true if there is a flight between cities i and j.

### Solution Representation

A solution for the TSP instance with n cities is represented by an array of integers `A` of size n, where `A[i]` represents the city visited at step i of the trip. For example, the route 0 → 3 → 1 → 2 → 0 would be represented as [0, 2, 1, 3].

## Reduction to SAT

The TSP instance is reduced to a set of Boolean variables and constraints, which are then passed to a SAT solver to find a solution. The reduction involves mapping pairs of numbers (i, j) to Boolean variables that represent whether city j is visited at step i of the trip.

## Constraints

The constraints for the SAT problem are constructed to ensure that each city is visited exactly once and that the route forms a cycle returning to the home port.
