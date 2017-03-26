package ga.components.GRNs;

import ga.components.genes.Gene;
import ga.components.genes.GeneFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class EvolutionGeneFactory implements GeneFactory<IntegerDataGene[]>{

    private int nodeSize;
    private int interactionSize;
    private double perturbProbability;
    private ArrayList<Interaction> interactions = new ArrayList<Interaction>();
    private ArrayList<IntegerDataGene> integerDataGenes = new ArrayList<IntegerDataGene>();

    public void initialise(int nodeSize, int interactionSize) {
        int maxInteractionSize = nodeSize * (nodeSize - 1) / 2;
        if (nodeSize < 0 || interactionSize > maxInteractionSize) {
            throw new IllegalArgumentException("Illegal network model!");
        }
        this.nodeSize = nodeSize;
        this.interactionSize = interactionSize;
    }

    public EvolutionGeneFactory(int nodeSize, int interactionSize) {
        this.initialise(nodeSize, interactionSize);
        this.perturbProbability = Math.random();
    }

    public EvolutionGeneFactory(int nodeSize, int interactionSize, double perturbProbability) {
        this.initialise(nodeSize, interactionSize);
        this.perturbProbability = perturbProbability;
    }

    public boolean duplicateInteraction(Interaction anInteraction) {
        for (Interaction e : this.interactions) {
            if (e.isEqual(anInteraction)) {
                return true;
            }
        }
        return false;
    }

    public void perturb() {

    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public void generateNodes() {
        for (int i=1; i<=nodeSize; i++) {
            IntegerDataGene aNewIntegerDataGene = new IntegerDataGene(i);
            integerDataGenes.add(aNewIntegerDataGene);
        }
    }

    public void generateInteractions() {
        while (this.interactions.size() <= interactionSize) {
            final int[] endPoints = new Random().ints(1, nodeSize + 1).distinct().limit(2).toArray();
            IntegerDataGene endPointA = this.integerDataGenes.get(endPoints[0]);
            IntegerDataGene endPointB = this.integerDataGenes.get(endPoints[1]);
            Interaction aNewInteraction = new Interaction(endPointA, endPointB, this.getRandomBoolean());
            if (!this.duplicateInteraction(aNewInteraction)) {
                interactions.add(aNewInteraction);
            }
        }
    }

    @Override
    public EvolutionGene generateGene() {
        return null;
    }
}
