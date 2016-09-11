package ga.components.chromosome;

import com.sun.istack.internal.NotNull;
import ga.components.materials.DNAStrand;
import ga.operations.dominanceMappings.DominanceMapping;

/**
 * Created by david on 26/08/16.
 */
public class SequentialDiploid extends Diploid<DNAStrand, DNAStrand>{

    public SequentialDiploid(@NotNull final DNAStrand dna1,
                             @NotNull final DNAStrand dna2,
                             @NotNull final DominanceMapping<DNAStrand, DNAStrand> mapping) {
        super(dna1, dna2, mapping);
    }

    @Override
    public SequentialDiploid copy() {
        return new SequentialDiploid(materials.get(0).copy(), materials.get(1).copy(), mapping.copy());
    }

    @Override
    public String toString() {
        return "\nDNA_1: " + materials.get(0).toString() +
                "\nDNA_2: " + materials.get(1).toString() +
                "\nDominance:\n" + mapping.toString() +
                "\nPhenotype: " + getPhenotype().toString();

    }
}
