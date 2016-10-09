package ga.operations.selectors;

import com.sun.istack.internal.NotNull;
import ga.collections.Individual;
import ga.components.chromosome.Chromosome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class provides a simple framework for implementation of selector.
 * The given reference of list of individuals is stored for efficient retrieval purpose.
 * Any subclass of this class must implement a way that facilitates execution according to particular selection scheme.
 *
 * @author Siu Kei Muk (David)
 * @since 3/09/16.
 */
public abstract class BaseSelector<C extends Chromosome> implements Selector<C> {

    protected List<Individual<C>> individuals;
    protected List<Double> fitnessValues;
    protected SelectionScheme scheme;

    public BaseSelector(@NotNull final SelectionScheme scheme) {
        this.scheme = scheme;
        fitnessValues = new ArrayList<>();
    }

    @Override
    public List<C> select(final int numOfMates) {
        Set<Integer> set = new HashSet<>(numOfMates);
        while (set.size() < numOfMates) {
            set.add(scheme.select(fitnessValues));
        }

        List<C> parents = new ArrayList<>(numOfMates);

        for (int i : set) {
            parents.add(individuals.get(i).getChromosome());
        }
        return parents;
    }
}
