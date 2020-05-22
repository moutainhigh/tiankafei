package cn.tiankafei.base.sort.observer;

import cn.tiankafei.base.sort.SortDecorator;
import cn.tiankafei.base.sort.SortFactory;
import cn.tiankafei.base.sort.merge.MergeSortFactory;

/**
 * 观察者模式：底层使用装饰着模式实现，注释掉责任链模式的实现
 *
 * @ClassName BubbleSortObserver
 * @Author tiankafei
 * @Date 2019/12/1
 * @Version V1.0
 **/
public class MergeSortObserver implements SortObserver {

    @Override
    public void exec(SortDecorator sortDecorator) {
        //工厂方法模式
        SortFactory<Double> doubleSortFactory = new MergeSortFactory();
        SortFactory<Integer> integerSortFactory = new MergeSortFactory();

        sortDecorator.testDoubleMin(doubleSortFactory, doubleMinSortComparator);
        sortDecorator.testDoubleMax(doubleSortFactory, doubleMaxSortComparator);
        sortDecorator.testIntegerMin(integerSortFactory, integerMinSortComparator);
        sortDecorator.testIntegerMax(integerSortFactory, integerMaxSortComparator);
    }
}
