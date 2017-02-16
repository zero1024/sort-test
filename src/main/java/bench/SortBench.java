package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sort.BubbleSort;
import sort.QuickSort;
import sort.Sort;
import sort.UnbalancedBiTreeSort;
import tree.BiTree;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Arrays.copyOf;
import static java.util.stream.Stream.generate;

/**
 * @author Perekhod Oleg
 */
@State(Scope.Benchmark)
public class SortBench {

    private List<Integer[]> list = generate(
            () -> generate(() -> new Random().nextInt()).limit(10000).toArray(Integer[]::new))
            .limit(50)
            .collect(Collectors.toList());

    @Benchmark
    public void quickSort() {
        Sort<Integer> sort = new QuickSort<>();
        for (Integer[] array : list) {
            sort.sort(copyOf(array, array.length));
        }
    }

    @Benchmark
    public void bubbleSort() {
        Sort<Integer> sort = new BubbleSort<>();
        for (Integer[] array : list) {
            sort.sort(copyOf(array, array.length));
        }
    }

    @Benchmark
    public void unbalancedBiTreeSort() {
        Sort<Integer> sort = new UnbalancedBiTreeSort<>();
        for (Integer[] array : list) {
            sort.sort(copyOf(array, array.length));
        }
    }

    @Benchmark
    public void unbalancedBiTreeSortWithImperativeIterator() {
        Sort<Integer> sort = new UnbalancedBiTreeSort<>(() -> new BiTree<>(true));
        for (Integer[] array : list) {
            sort.sort(copyOf(array, array.length));
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBench.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
