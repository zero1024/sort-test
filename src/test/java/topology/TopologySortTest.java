package topology;

import org.junit.Test;
import topology.TopologySort.Node;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * @author Perekhod Oleg
 */
public class TopologySortTest {


    private static class TestNode implements Node {

        private String name;
        private List<String> dependencyList;

        private TestNode(String name, List<String> dependencyList) {
            this.name = name;
            this.dependencyList = dependencyList;
        }

        @Override
        public List<String> getDependencyList() {
            return dependencyList;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    @Test
    public void good() throws Exception {
        Node node1 = new TestNode("1", asList("2", "3"));
        Node node2 = new TestNode("2", asList("3", "4"));
        Node node3 = new TestNode("3", emptyList());
        Node node4 = new TestNode("4", emptyList());

        TopologySort<Node> sort = new TopologySort<>();

        Node[] array = {node1, node2, node3, node4};
        sort.sort(array);
        assert Arrays.equals(array, new Node[]{node3, node4, node2, node1});
    }

    @Test(expected = IllegalStateException.class)
    public void bad() throws Exception {
        Node node1 = new TestNode("1", asList("2", "3"));
        Node node2 = new TestNode("2", asList("3", "4"));
        Node node3 = new TestNode("3", emptyList());
        Node node4 = new TestNode("4", singletonList("1"));

        TopologySort<Node> sort = new TopologySort<>();
        Node[] array = {node1, node2, node3, node4};
        sort.sort(array);


    }

}
