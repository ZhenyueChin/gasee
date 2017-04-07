package examples.experiment4;

import com.sun.istack.internal.NotNull;
import com.sun.javafx.geom.Edge;
import ga.components.genes.DataGene;
import ga.components.genes.EdgeGene;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

/**
 * Created by Zhenyue Qin on 7/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkFitnessFunction implements FitnessFunction<SimpleMaterial> {

  private final SimpleMaterial target;
  private final EdgeGene[][] edges;
  private final int maxCycle;

  public GeneRegulatoryNetworkFitnessFunction(SimpleMaterial target, EdgeGene[][] edges, int maxCycle) {
    this.target = target.copy();
    this.edges = edges.clone();
    this.maxCycle = maxCycle;
  }

  public boolean hasNotAttainedAttractor(final DataGene[] currentState, final DataGene[] updatedState) {
    int differenceCounts = 0;
    for (int i=0; i<currentState.length; i++) {
      if (currentState[i].getValue() != updatedState[i].getValue()) {
        differenceCounts += 1;
      }
    }
    return differenceCounts != 0;
  }

  public int checkActivationOrRepression(double influence) {
    if (influence > 0) {
      return 1;
    } else {
      return -1;
    }
  }

  public DataGene[] updateState(DataGene[] currentState) {
    DataGene[] updatedState = new DataGene[currentState.length];
    updatedState = this.initialseDataGeneArray(updatedState);
    for (int i=0; i<currentState.length; i++) {
      double influence = 0;
      for (int j=0; j<currentState.length; j++) {
        influence += this.edges[i][j].getValue() * currentState[j].getValue();
      }
      updatedState[i].setValue(this.checkActivationOrRepression(influence));
    }
    return updatedState;
  }

  public DataGene[] initialseDataGeneArray(DataGene[] dataGenes) {
    DataGene[] temp = dataGenes.clone();
    for (int i=0; i<temp.length; i++) {
      temp[i] = new DataGene();
    }
    return temp;
  }

  @Override
  public double evaluate(@NotNull SimpleMaterial phenotype) {
    DataGene[][] startAttractors = this.generateInitialAttractors(20, 0.15);
    double fitnessValues = 0;
    for (int attractorIndex=0; attractorIndex<startAttractors.length; attractorIndex++) {
      DataGene[] currentAttractor = startAttractors[attractorIndex];
      int currentRound = 0;
      boolean isNotStable;
      do {
        DataGene[] updatedState = this.updateState(currentAttractor);
        isNotStable = this.hasNotAttainedAttractor(currentAttractor, updatedState);
        currentAttractor = updatedState;
        currentRound += 1;
      }
      while (currentRound < this.maxCycle && isNotStable);

      if (currentRound < maxCycle) {
        int hammingDistance = this.getHammingDistance(currentAttractor);
        double thisFitness = Math.pow((1 - (hammingDistance / ((double) this.target.getSize()))), 5);
        fitnessValues += thisFitness;
      } else {
        fitnessValues += 0;
      }
    }
    return fitnessValues;
  }

  private DataGene[][] generateInitialAttractors(int setSize, double p) {
    /*
    Generate a random set of perturbations of the target set for evaluation.
     */
    DataGene[][] returnables = new DataGene[setSize][this.target.getSize()];

    for (int i=0; i<setSize; i++) {
      for (int j=0; j<this.target.getSize(); j++) {
        returnables[i][j] = (DataGene) this.target.getGene(j).copy();
        if (Math.random() < p) {
          returnables[i][j].setRandomValue();
        }
      }
    }
    return returnables;
  }

  public int getHammingDistance(DataGene[] attractor) {
    /*
    TODO: make this cooler!
     */
    int count = 0;
    for (int i=0; i<attractor.length; i++) {
      if (attractor[i].getValue() == this.target.getGene(i).getValue()) {
        count += 1;
      }
    }
    return attractor.length - count;
  }

  @Override
  public void update() {

  }
}
