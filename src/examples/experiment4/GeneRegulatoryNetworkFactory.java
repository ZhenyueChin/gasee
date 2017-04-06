package examples.experiment4;

import ga.components.GRNs.DirectedEdge;
import ga.components.GRNs.GRN;
import ga.components.genes.DataGene;
import ga.components.genes.GeneFactory;
import ga.components.materials.SimpleMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkFactory{

    private SimpleMaterial target;
    private int networkSize;
    private int edgeSize;
    private int maxCycle;

    public GeneRegulatoryNetworkFactory(int[] target, int maxCycle, int edgeSize) {
        this.target = this.convertIntArrayToDataGenes(target);
        this.networkSize = target.length;
        this.edgeSize = edgeSize;
        this.maxCycle = maxCycle;
    }

    public SimpleMaterial convertIntArrayToDataGenes(int[] numbers) {
      List<DataGene> dataGenes = new ArrayList<DataGene>();
      for (int i=0; i<numbers.length; i++) {
        dataGenes.add(new DataGene());
      }
      return new SimpleMaterial(dataGenes);
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

    public GeneRegulatoryNetwork generateGeneRegulatoryNetwork() {
        int[][] connections = this.initialiseEdges(this.networkSize, this.networkSize);
        return new GeneRegulatoryNetwork(this.target.copy(), connections, this.maxCycle);
    }
}
