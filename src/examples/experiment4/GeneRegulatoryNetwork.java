package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.components.genes.DataGene;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhenyue Qin on 6/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetwork implements FitnessFunction<SimpleMaterial> {

  private SimpleMaterial target;
  private int[][] nodes;
  private int[][] edges;
  private double fitness;
  private int maxCycle;

  public GeneRegulatoryNetwork(int[] target) {
    this.target = this.convertIntArrayToDataGenes(target);
  }

  public SimpleMaterial convertIntArrayToDataGenes(int[] numbers) {
    List<DataGene> dataGenes = new ArrayList<DataGene>();
    for (int i=0; i<numbers.length; i++) {
      dataGenes.add(new DataGene());
    }
    return new SimpleMaterial(dataGenes);
  }

  private void perturb(double mu) {
    /*
    Each gene (node) has a chance of mu to mutate.
     */
    for (int i=0; i<nodes[0].length; i++) {
      if (Math.random() <= mu) {
        this.toMutate(i);
      }
    }
  }

  private void toMutate(int i) {
    /*
    Mutates the specified gene at index i according to the rule specified in page 9 of the original paper.
     */
    int nodesSize = this.nodes[0].length; // number of nodes in the network
    int regulatorNumber = 0; // number of regulators for this gene.

    for (int j=0; j<this.edges.length; j++) {
      if (this.edges[j][i] != 0) { // if the mutated note is regulated by j
        regulatorNumber += 1;
      }

      double probabilityToLoseInteraction = (4.0 * regulatorNumber) / (4.0 * regulatorNumber + (nodesSize - regulatorNumber));

      if (Math.random() <= probabilityToLoseInteraction) { // lose an interaction
        List<Integer> interactions = new ArrayList<Integer>();
        for (int edgeIdx=0; edgeIdx<this.edges[0].length; edgeIdx++) {
          if (this.edges[edgeIdx][i] != 0) {
            interactions.add(edgeIdx);
          }
          if (interactions.size() > 0) {
            int toRemove = this.generateAnInteger(interactions.size());
            this.edges[toRemove][i] = 0;
          }
        }
      } else {
        List<Integer> nonInteractions = new ArrayList<Integer>();
        for (int edgeIdx=0; edgeIdx<this.edges[0].length; edgeIdx++) {
          if (this.edges[edgeIdx][i] == 0) {
            nonInteractions.add(edgeIdx);
          }
          if (nonInteractions.size() > 0) {
            int toAdd = this.generateAnInteger(nonInteractions.size());
            if (this.flipACoin()) {
              this.edges[toAdd][i] = 1;
            } else {
              this.edges[toAdd][i] = -1;
            }
          }
        }
      }
    }
  }

  private int generateAnInteger(int upBound) {
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(upBound);
  }

  private boolean flipACoin() {
    return 0.5 < Math.random();
  }

  private DataGene[][] generateInitialAttractors(int setSize, double p) {
    /*
    Generate a random set of perturbations of the target set for evaluation.
     */
    DataGene[][] returnables = new DataGene[setSize][this.target.getSize()];

    for (int i=0; i<setSize; i++) {
      for (int j=0; j<this.target.getSize(); j++) {
        returnables[i][j] = (DataGene) this.target.getGene(j);
        if (Math.random() < p) {
          returnables[i][j].setRandomValue();
        }
      }
    }
    return returnables;
  }

  @Override
  public double evaluate(@NotNull SimpleMaterial phenotype) {
    DataGene[][] startAttractors = this.generateInitialAttractors(20, 0.15);
    double[] fitnessValues = new double[startAttractors.length];
    for (int attractorIndex=0; attractorIndex<startAttractors.length; attractorIndex++) {

    }
    return 0;
  }

  @Override
  public void update() {

  }

  public DataGene[] updateState(DataGene[] currentState) {
    DataGene[] updatedState = new DataGene[currentState.length];
    for (int i=0; i<currentState.length; i++) {
      double influence = 0;
      for (int j=0; j<currentState.length; j++) {
        influence += this.edges[i][j] * currentState[j].getValue();
      }
      updatedState[i].setValue(this.checkActivationOrRepression(influence));
    }
    return updatedState;
  }

  public int checkActivationOrRepression(double influence) {
    if (influence > 0) {
      return 1;
    } else {
      return 0;
    }
  }

}
