package examples.experiment4;

import ga.collections.Population;
import ga.collections.Statistics;
import ga.components.chromosomes.SimpleHaploid;
import ga.frame.Frame;
import ga.frame.SimpleFrame;
import ga.frame.SimpleState;
import ga.frame.State;
import ga.operations.fitnessFunctions.FitnessFunction;
import ga.operations.initializers.BinarySimpleHaploidInitializer;
import ga.operations.initializers.Initializer;
import ga.operations.mutators.Mutator;
import ga.operations.postOperators.PostOperator;
import ga.operations.postOperators.SimpleFillingOperatorForNormalizable;
import ga.operations.priorOperators.PriorOperator;
import ga.operations.reproducers.Reproducer;
import ga.operations.selectionOperators.selectionSchemes.ProportionalScheme;
import ga.operations.selectionOperators.selectors.Selector;
import ga.operations.selectionOperators.selectors.SimpleProportionalSelector;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp5Main {

    private static final int target = 119;  // target is the result that we want to get in the end
    private static final int size = 200; // size is the size of population.
    private static final int maxGen = 2000;
    private static final int numberElites = 20;
    private static final double mutationRate = 0.05;
    private static final double crossoverRate = 0.8;
    private static final double epsilon = 0.5;
    private static final double maxFit = 32;
    private static final String outfile = "Exp5.out";

    public static void main(String[] args) {
        FitnessFunction fitnessFunction = new Exp5FitnessFunction(target);
        Initializer initializer = new BinarySimpleHaploidInitializer(size, 32);
        Population<SimpleHaploid> population = initializer.initialize();
        Mutator mutator = new Exp5Mutator(mutationRate);
        Selector<SimpleHaploid> selector = new SimpleProportionalSelector<>();
        PriorOperator<SimpleHaploid> priorOperator = new Exp5PriorOperator(numberElites, selector);
        PostOperator<SimpleHaploid> postOperator = new SimpleFillingOperatorForNormalizable<>(new ProportionalScheme());
        Reproducer<SimpleHaploid> reproducer = new Exp5Reproducer();
        Statistics<SimpleHaploid> statistics = new Exp5Statistics();

        State<SimpleHaploid> state = new SimpleState<>(population, fitnessFunction, mutator, reproducer, selector, 2, crossoverRate);
        Frame<SimpleHaploid> frame = new SimpleFrame<>(state, postOperator, statistics);
        frame.setPriorOperator(priorOperator);
        statistics.print(0);
        for (int i = 0; i < maxGen; i++) {
            frame.evolve();
            statistics.print(i);
            if (statistics.getOptimum(i) > maxFit - epsilon)
                break;
        }

        statistics.save(outfile);
        System.out.println(Integer.toBinaryString(target));
    }

}
