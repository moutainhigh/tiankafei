package cn.tiankafei.base.sort.proxy;

import cn.tiankafei.base.sort.SortDecorator;
import cn.tiankafei.base.sort.SortProxy;
import cn.tiankafei.base.sort.chain.BubbleSortChain;
import cn.tiankafei.base.sort.chain.StandSortChain;
import cn.tiankafei.base.sort.decorator.StandSortDecorator;
import lombok.extern.slf4j.Slf4j;

/**
 * 代理模式：底层使用责任链模式
 *
 * @Author 魏双双
 * @Date 2019/12/2
 * @Version V1.0
 **/
@Slf4j
public class BubbleSortProxy implements SortProxy {

    @Override
    public Boolean execute() {
        long currentTime = System.currentTimeMillis();
        SortDecorator sortDecorator = new StandSortDecorator();
        StandSortChain standSortChain = new StandSortChain();
        standSortChain.add(new BubbleSortChain());
        standSortChain.execute(sortDecorator);
        log.info("执行完成用时 -> " + (System.currentTimeMillis() - currentTime) + "ms");
        return Boolean.TRUE;
    }
}
