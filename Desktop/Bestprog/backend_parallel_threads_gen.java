import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class VirusGeneProcessor {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

    public static void main(String[] args) {
        // Simulating a database of virus genes
        List<String> virusGenesDatabase = new ArrayList<>();
        virusGenesDatabase.add("Gene1");
        virusGenesDatabase.add("Gene2");
        virusGenesDatabase.add("Gene3");
        virusGenesDatabase.add("Gene4");
        virusGenesDatabase.add("Gene5");
        virusGenesDatabase.add("Gene6");
        virusGenesDatabase.add("Gene7");
        virusGenesDatabase.add("Gene8");
        virusGenesDatabase.add("Gene9");
        virusGenesDatabase.add("Gene10");

        // Simulating a dust sample containing virus genes
        List<String> dustSample = new ArrayList<>();
        dustSample.add("Gene1");
        dustSample.add("Gene5");
        dustSample.add("Gene8");

        processVirusGenes(dustSample, virusGenesDatabase);
    }

    private static void processVirusGenes(List<String> dustSample, List<String> virusGenesDatabase) {
        int numGenes = dustSample.size();
        int genesPerThread = numGenes / NUM_THREADS;

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < NUM_THREADS; i++) {
            final int startIndex = i * genesPerThread;
            final int endIndex = (i == NUM_THREADS - 1) ? numGenes : (startIndex + genesPerThread);

            tasks.add(() -> {
                for (int j = startIndex; j < endIndex; j++) {
                    String gene = dustSample.get(j);
                    searchGeneInDatabase(gene, virusGenesDatabase);
                }
                return null;
            });
        }

        try {
            executor.invokeAll(tasks);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void searchGeneInDatabase(String gene, List<String> virusGenesDatabase) {
        if (virusGenesDatabase.contains(gene)) {
            System.out.println("Gene " + gene + " found in the database.");
            // Perform additional processing or database operations
        } else {
            System.out.println("Gene " + gene + " not found in the database.");
        }
    }
}
