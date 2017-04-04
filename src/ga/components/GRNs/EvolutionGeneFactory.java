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
public class EvolutionGeneFactory implements GeneFactory<IntegerDataGene[][]>{

    private int nodeSize;
    private int interactionSize;
    private double perturbProbability;
    private IntegerDataGene[][] interactions;
    private IntegerDataGene[] target;
    private IntegerDataGene[] states;

    private void initialise(int nodeSize, int interactionSize) {
        int maxInteractionSize = nodeSize * (nodeSize - 1) / 2;
        if (nodeSize < 0 || interactionSize > maxInteractionSize) {
            throw new IllegalArgumentException("Illegal network model!");
        }
        this.nodeSize = nodeSize;
        this.interactionSize = interactionSize;
        this.interactions = new IntegerDataGene[this.nodeSize+1][this.nodeSize+1];
        for (int i=1; i<=this.nodeSize; i++) {
            for (int j=i; j<=this.nodeSize; j++) {
                this.interactions[i][j].setValue(Variables.noConnection);
            }
        }

        this.generateInteractions();
        this.perturb();
    }

    public IntegerDataGene[] getTarget() {
        return this.target;
    }

    private void initialStates() {
        for (int i=1; i<=this.nodeSize; i++) {
            if (this.getRandomBoolean()) {
                this.states[i].setValue(Variables.active);
            } else {
                this.states[i].setValue(Variables.inactive);
            }
        }
    }

    public EvolutionGeneFactory(int nodeSize, int interactionSize) {
        this.initialise(nodeSize, interactionSize);
        this.perturbProbability = 0.15;
    }

    public EvolutionGeneFactory(int nodeSize, int interactionSize, double perturbProbability) {
        this.initialise(nodeSize, interactionSize);
        this.perturbProbability = perturbProbability;
    }

    private void perturb() {
        this.target = this.states.clone();
        for (int i=1; i<=this.nodeSize; i++) {
            if (Math.random() < this.perturbProbability) {
                this.states[i].setValue(-this.states[i].getValue());
            }
        }
    }

    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    private int getFilledInteractionSize() {
        int sum = 0;
        for (int i=1; i<=nodeSize; i++) {
            for (int j=i; j<=nodeSize; j++) {
                if (this.interactions[i][j].getValue() != Variables.noConnection) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    private void generateInteractions() {
        while (this.getFilledInteractionSize() <= interactionSize) {
            final int[] endPoints = new Random().ints(1, nodeSize + 1).distinct().limit(2).toArray();
            if (this.interactions[endPoints[0]][endPoints[1]].getValue() == Variables.noConnection) {
                if (this.getRandomBoolean()) {
                    this.interactions[endPoints[0]][endPoints[1]].setValue(Variables.activating);
                    this.interactions[endPoints[1]][endPoints[0]].setValue(Variables.activating);
                } else {
                    this.interactions[endPoints[0]][endPoints[1]].setValue(Variables.repressing);
                    this.interactions[endPoints[1]][endPoints[0]].setValue(Variables.repressing);
                }
            }
        }
    }

    @Override
    public EvolutionGene generateGene() {
        return new EvolutionGene(this.interactions, this.target, this.states);
    }
}
