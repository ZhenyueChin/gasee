package examples.experiment4;

import com.sun.istack.internal.NotNull;
import com.sun.javafx.geom.Edge;
import ga.components.genes.DataGene;
import ga.components.genes.EdgeGene;
import ga.components.materials.EdgeMaterial;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

import java.util.Arrays;

/**
 * Created by Zhenyue Qin on 7/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkFitnessFunction implements FitnessFunction<SimpleMaterial> {

  private final SimpleMaterial target;
  private final int maxCycle;

  public GeneRegulatoryNetworkFitnessFunction(SimpleMaterial target, int maxCycle) {
    this.target = target.copy();
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

  public DataGene[] updateState(DataGene[] currentState, EdgeGene[][] edges) {
    DataGene[] updatedState = new DataGene[currentState.length];
    updatedState = this.initialseDataGeneArray(updatedState);
    for (int i=0; i<currentState.length; i++) {
      double influence = 0;
      for (int j=0; j<currentState.length; j++) {
        influence += edges[i][j].getValue() * currentState[j].getValue();
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
    /*
    DataGene[][] startAttractors = this.generateInitialAttractors(20, 0.15);
    double fitnessValues = 0;
    for (int attractorIndex=0; attractorIndex<startAttractors.length; attractorIndex++) {
      DataGene[] currentAttractor = startAttractors[attractorIndex];
      int currentRound = 0;
      boolean isNotStable;
      do {
        DataGene[] updatedState = this.updateState(currentAttractor, ((EdgeMaterial) phenotype).getEdgeGenes());
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
    ((GeneRegulatoryNetwork) phenotype).setFitness(fitnessValues);
    return fitnessValues;
    */

    int oneCount = 0;
    EdgeGene[][] edgeGenes = ((EdgeMaterial) phenotype).getEdgeGenes();
    for (int i=0; i<edgeGenes.length; i++) {
      for (int j=0; j<edgeGenes[0].length; j++) {
        if (edgeGenes[i][j].getValue() == 1) {
          oneCount += 1;
        }
      }
    }
    return oneCount;
  }

  private DataGene[][] generateDeterministicAttractors() {
    DataGene[][] returnables = new DataGene[11][10];
    int[][] temp = {
      {-1, 1, -1, 1, -1, 1, -1, 1, -1, 1},
      {1, 1, -1, 1, -1, 1, -1, 1, -1, 1},
      {1, -1, -1, 1, -1, 1, -1, 1, -1, 1},
      {1, -1, 1, 1, -1, 1, -1, 1, -1, 1},
      {1, -1, 1, -1, -1, 1, -1, 1, -1, 1},
      {1, -1, 1, -1, 1, 1, -1, 1, -1, 1},
      {1, -1, 1, -1, 1, -1, -1, 1, -1, 1},
      {1, -1, 1, -1, 1, -1, 1, 1, -1, 1},
      {1, -1, 1, -1, 1, -1, 1, -1, -1, 1},
      {1, -1, 1, -1, 1, -1, 1, -1, 1, 1},
      {1, -1, 1, -1, 1, -1, 1, -1, 1, -1}
    };
    for (int i=0; i<11; i++) {
      for (int j=0; j<10; j++) {
        returnables[i][j] = new DataGene(temp[i][j]);
      }
    }
    return returnables;
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
//    return this.generateDeterministicAttractors();
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
