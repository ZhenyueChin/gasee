package ga.components.GRNs;

import com.sun.istack.internal.NotNull;
import ga.components.genes.Gene;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class DataGene<T> extends Gene<T>{

    public DataGene(@NotNull T value) {
        super(value);
    }

    @Override
    public void setValue(@NotNull T value) {
        this.value = value;
    }

    @Override
    public Gene<T> copy() {
        return new DataGene<T>(value);
    }
}
