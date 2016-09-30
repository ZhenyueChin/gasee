package ga.operations.selectors;

import com.sun.istack.internal.NotNull;
import ga.collections.Individual;
import ga.components.chromosome.Chromosome;

import java.util.List;

/**
 * This class implements a simple selector that uses proportionate selection scheme.
 *
 * @author Siu Kei Muk (David)
 * @since 11/09/16
 */
public class SimpleProportionateSelector<T extends Chromosome> extends BaseSelector<T> {

    public SimpleProportionateSelector() {
        super(new ProportionateScheme());
    }

    @Override
    public void setSelectionData(@NotNull final List<Individual<T>> individuals) {
        final int size = individuals.size();
        this.individuals = individuals;
        fitnessValues.clear();
        double sum = individuals.stream().mapToDouble(Individual::getFitness).sum();
        for (int i = 0; i < size; i++) {
            fitnessValues.add(individuals.get(i).getFitness() / sum);
        }
    }
}