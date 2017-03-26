package ga.components.GRNs;

import com.sun.istack.internal.NotNull;
import ga.components.genes.Gene;

/**
 * Created by Chinyuer on 26/03/2017.
 */
public class DataGene<T> extends Gene<T>{

    public DataGene(@NotNull T value) {
        super(value);
    }

    @Override
    public void setValue(@NotNull T value) {

    }

    @Override
    public Gene<T> copy() {
        return null;
    }
}
