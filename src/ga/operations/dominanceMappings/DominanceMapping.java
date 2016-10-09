package ga.operations.dominanceMappings;

import com.sun.istack.internal.NotNull;
import ga.components.materials.GeneticMaterial;
import ga.others.Copyable;

import java.util.List;

/**
 * This interface abstracts the genotype-to-phenotype mapping.
 *
 * @author Siu Kei Muk (David)
 * @since 27/08/16.
 */
public interface DominanceMapping<M extends GeneticMaterial, P extends GeneticMaterial> extends Copyable<DominanceMapping<M, P>> {
    /**
     * Performs the genotype-to-phenotype mapping.
     *
     * @param materials genotype of an individual
     * @return phenotype of an individual
     */
    P map(@NotNull final List<M> materials);
}
