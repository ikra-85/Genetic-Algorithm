package one;

public class ConvertToOnes {
	public static void main(String[] args) {
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);

		Population population = ga.initPopulation(50);

		ga.evalPopulation(population);

		int generation = 1;

		while (ga.isTerminationConditionMet(population) == false) {
			// Print fittest individual from population
			System.out.println("Best solution: " + population.getFittest(0).toString());

			// Apply crossover
			population = ga.crossoverPopulation(population);

			// Apply mutation
			population = ga.mutatePopulation(population);

			// Evaluate population
			ga.evalPopulation(population);

			// Increment the current generation
			generation++;
		}

		System.out.println("Found solution in " + generation + " generations");
		System.out.println("Best solution: " + population.getFittest(0).toString());
	}
}
