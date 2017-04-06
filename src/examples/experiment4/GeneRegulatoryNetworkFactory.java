package examples.experiment4;

import ga.components.GRNs.DirectedEdge;
import ga.components.GRNs.GRN;
import ga.components.genes.GeneFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkFactory implements GeneFactory<Integer[][]>{

    private int[] target;
    private int networkSize;
    private int edgeSize;
    private int maxCycle;
    private int[][] nodes;

    public GeneRegulatoryNetworkFactory(int[] target, int maxCycle, int edgeSize) {
        this.target = target;
        this.networkSize = target.length;
        this.edgeSize = edgeSize;
        this.maxCycle = maxCycle;
        this.nodes = this.matrixCreate(maxCycle, this.target);
    }

    private int[][] initialiseEdges(int m, int n) {
        List<DirectedEdge> candidates = new ArrayList<DirectedEdge>();
        for (int i=0; i<networkSize; i++) {
            for (int j=0; j<networkSize; j++) {
                candidates.add(new DirectedEdge(i, j));
            }
        }
        final int[] edgeIndices = new Random().ints(0, networkSize * networkSize).distinct().limit(edgeSize).toArray();
        List<DirectedEdge> edges = new ArrayList<DirectedEdge>();

        for (int edgeIndex : edgeIndices) {
            edges.add(candidates.get(edgeIndex));
        }

        int[][] connections = new int[m][n];

        for (DirectedEdge edge : edges) {
            if (this.flipACoin()) {
              connections[edge.getLeft()][edge.getRight()] = 1;
            } else {
              connections[edge.getLeft()][edge.getRight()] = -1;
            }
        }
        return connections;
    }

    private boolean flipACoin() {
        return 0.5 < Math.random();
    }

    private int[][] matrixCreate(int rows, int[] firstRow) {
        /*
        Initialise a 2D zero integer matrix
         */
        int[][] matrix = new int[rows][firstRow.length];
        matrix[0] = firstRow;
        return matrix;
    }

    @Override
    public GRN generateGene() {
        return null;
    }
}
