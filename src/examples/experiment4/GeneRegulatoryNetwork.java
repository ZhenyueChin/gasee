package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.components.genes.DataGene;
import ga.components.genes.EdgeGene;
import ga.components.genes.Gene;
import ga.components.materials.EdgeMaterial;
import ga.components.materials.Material;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhenyue Qin on 6/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetwork extends EdgeMaterial {

  private SimpleMaterial target;
  private List<EdgeGene> edgeList;
  private double fitness;
  private int maxCycle;

  public GeneRegulatoryNetwork(SimpleMaterial target, List<EdgeGene> edgeList, int maxCycle) {
    super(edgeList, target.getSize());
    this.edgeList = new ArrayList<>(edgeList);
    this.target = target.copy();
    this.maxCycle = maxCycle;
  }

  public List<DataGene> convertSimpleMaterialToDataGeneArray(SimpleMaterial simpleMaterial) {
    DataGene[] dataGenes = new DataGene[simpleMaterial.getSize()];
    for (int i=0; i<simpleMaterial.getSize(); i++) {
      dataGenes[i] = (DataGene) simpleMaterial.getGene(i).copy();
    }
    return Arrays.asList(dataGenes);
  }

  public double getFitness() {
    return fitness;
  }

  public void perturb(double mu) {
    /*
    Each gene (node) has a chance of mu to mutate.
     */
    for (int i=0; i<this.target.getSize(); i++) {
      if (Math.random() <= mu) {
        this.toMutate(i);
      }
    }
  }

  public void toMutate(int i) {
    /*
    Mutates the specified gene at index i according to the rule specified in page 9 of the original paper.
     */
    int nodesSize = this.target.getSize(); // number of nodes in the network
    int regulatorNumber = 0; // number of regulators for this gene.

    for (int j=0; j<this.edges.length; j++) {
      if (this.edges[j][i].getValue() != 0) { // if the mutated note is regulated by j
        regulatorNumber += 1;
      }

      double probabilityToLoseInteraction = (4.0 * regulatorNumber) / (4.0 * regulatorNumber + (nodesSize - regulatorNumber));

      if (Math.random() <= probabilityToLoseInteraction) { // lose an interaction
        List<Integer> interactions = new ArrayList<Integer>();
        for (int edgeIdx=0; edgeIdx<this.edges[0].length; edgeIdx++) {
          if (this.edges[edgeIdx][i].getValue() != 0) {
            interactions.add(edgeIdx);
          }
          if (interactions.size() > 0) {
            int toRemove = this.generateAnInteger(interactions.size());
            this.edges[toRemove][i].setValue(0);
          }
        }
      } else {
        List<Integer> nonInteractions = new ArrayList<Integer>();
        for (int edgeIdx=0; edgeIdx<this.edges[0].length; edgeIdx++) {
          if (this.edges[edgeIdx][i].getValue() == 0) {
            nonInteractions.add(edgeIdx);
          }
          if (nonInteractions.size() > 0) {
            int toAdd = this.generateAnInteger(nonInteractions.size());
            if (this.flipACoin()) {
              this.edges[toAdd][i].setValue(1);
            } else {
              this.edges[toAdd][i].setValue(-1);
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

  public static<T> void printTwoDimensionArray(T[][] anArray) {
    for (int i=0; i<anArray.length; i++) {
      System.out.println(Arrays.toString(anArray[i]));
    }
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

  public GeneRegulatoryNetwork copy() {
    return new GeneRegulatoryNetwork(this.target.copy(), new ArrayList<>(this.edgeList), this.maxCycle);
  }

  public DataGene[] initialseDataGeneArray(DataGene[] dataGenes) {
    DataGene[] temp = dataGenes.clone();
    for (int i=0; i<temp.length; i++) {
      temp[i] = new DataGene();
    }
    return temp;
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

  @Override
  public int getSize() {
    return this.target.getSize();
  }

  @Override
  public Gene getGene(int index) {
    return edges[index/edges.length][index%edges.length];
  }

  @Override
  public String toString() {
    String rtn = "\nA Gene Regulatory Network: \n" + "Target: " + this.target + "\n" + "Network: \n" + this.getEdgeGenesMatrixString();
    return rtn;
  }
}
