package tree;

/**
 * 1. узел либо красный либо черный
 * 2. корень и конечные узлы - черные
 * 3. у красного узла родительский узел - черный
 * 4. все пути от любого узла до листьев содержать одинаковое кол-во черных узлов
 * 5. черный узел может иметь черного родителя
 *
 * @author Perekhod Oleg
 */
public class RedWhiteBiTree<T extends Comparable> extends UnbalancedBiTree<T> {
}
