package cn.tiankafei.base.sort.observer;

import cn.tiankafei.base.sort.SortDecorator;
import cn.tiankafei.base.sort.SortFactory;
import cn.tiankafei.base.sort.decorator.ShellSortDecorator;
import cn.tiankafei.base.sort.shell.ShellSortFactory;

/**
 * 观察者模式：底层使用装饰着模式实现，注释掉责任链模式的实现
 *
 * @ClassName BubbleSortObserver
 * @Author tiankafei
 * @Date 2019/12/1
 * @Version V1.0
 **/
public class ShellSortObserver implements SortObserver {

    @Override
    public void exec(SortDecorator sortDecorator) {
        //装饰着模式
        ShellSortDecorator shellSortDecorator = new ShellSortDecorator(sortDecorator);

        //工厂方法模式
        SortFactory<Double> doubleSortFactory = new ShellSortFactory();
        SortFactory<Integer> integerSortFactory = new ShellSortFactory();

        shellSortDecorator.testDoubleMin(doubleSortFactory, doubleMinSortComparator);
        shellSortDecorator.testDoubleMax(doubleSortFactory, doubleMaxSortComparator);
        shellSortDecorator.testIntegerMin(integerSortFactory, integerMinSortComparator);
        shellSortDecorator.testIntegerMax(integerSortFactory, integerMaxSortComparator);

//        StandSortChain standSortChain = new StandSortChain();
//        standSortChain.add(new ShellSortChain());
//        standSortChain.execute(sortDecorator);
    }
}
