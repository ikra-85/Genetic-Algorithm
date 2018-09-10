package three;

public class TSP {
	public static int maxGenerations = 10000;

	public static void main(String[] args) {
		int numCities = 100;
		City cities[] = new City[numCities];

		for (int cityIndex = 0; cityIndex < numCities; cityIndex++) {
			int xPos = (int) (100 * Math.random());
			int yPos = (int) (100 * Math.random());

			cities[cityIndex] = new City(xPos, yPos);
		}

		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.9, 2, 5);

		Population population = ga.initPopulation(cities.length);

		ga.evalPopulation(population, cities);

		Route startRoute = new Route(population.getFittest(0), cities);
		System.out.println("Start Distance: " + startRoute.getDistance());

		// Keep track of current generation
		int generation = 1;
		// Start evolution loop
		while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {
			// Print fittest individual from population
			Route route = new Route(population.getFittest(0), cities);
			System.out.println("G" + generation + " Best distance: " + route.getDistance());

			population = ga.crossoverPopulation(population);
			population = ga.mutatePopulation(population);
			ga.evalPopulation(population, cities);

			generation++;
		}

		System.out.println("Stopped after " + maxGenerations + " generations.");
		Route route = new Route(population.getFittest(0), cities);
		System.out.println("Best distance: " + route.getDistance());
	}
}