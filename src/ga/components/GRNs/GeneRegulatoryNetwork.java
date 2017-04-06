package ga.components.GRNs;

import com.sun.istack.internal.Interned;
import com.sun.istack.internal.NotNull;
import ga.components.genes.BinaryGene;
import ga.components.genes.Gene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetwork extends Gene<Integer[][]> {

    private int[][] nodes;
    private int[][] edges;
    private double fitness;

    public GeneRegulatoryNetwork(int[] target, int maxCycle, @NotNull Integer[][] connections) {
        super(connections);

    }

    @Override
    public Gene<Integer[][]> copy() {
        return null;
    }

    @Override
    public void setValue(@NotNull Integer[][] value) {

    }

    private void perturb() {
      /*
      Each gene (node) has a chance of mu to mutate.
       */
    }

    private boolean flipACoin() {
      return 0.5 < Math.random();
    }

    private int generateAnInteger(int upBound) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(upBound);
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

  public static void main(String[] args) {
  }

}
