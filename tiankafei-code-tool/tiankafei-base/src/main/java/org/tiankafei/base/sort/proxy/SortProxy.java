package org.tiankafei.base.sort.proxy;

import org.tiankafei.base.sort.comparator.DoubleSortComparator;
import org.tiankafei.base.sort.comparator.IntegerSortComparator;
import org.tiankafei.base.sort.singleton.SortComparatorSingleton;
import org.tiankafei.base.sort.singleton.SortFactoryStrategy;

/**
 * 代理模式
 *
 * @Author 魏双双
 * @Date 2019/12/2
 * @Version V1.0
 **/
public interface SortProxy {

    /**
     * 策略模式
     */
    DoubleSortComparator doubleMinSortComparator = SortComparatorSingleton.getDoubleMinSortComparator();
    DoubleSortComparator doubleMaxSortComparator = SortComparatorSingleton.getDoubleMaxSortComparator();
    IntegerSortComparator integerMinSortComparator = SortComparatorSingleton.getIntegerMinSortComparator();
    IntegerSortComparator integerMaxSortComparator = SortComparatorSingleton.getIntegerMaxSortComparator();

    SortFactoryStrategy sortFactoryStrategy = SortFactoryStrategy.getInstance();

    /**
     * 执行方法
     *
     * @return
     */
    void execute();

}
