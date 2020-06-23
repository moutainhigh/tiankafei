package cn.tiankafei.poi.model.impl;

import cn.tiankafei.poi.model.ICol;
import cn.tiankafei.poi.model.ICols;
import lombok.Data;

import java.util.List;

/**
 * @author tiankafei
 * @since 1.0
 **/
@Data
public class ColsVo implements ICols {

    private Integer length;

    private List<ICol> colList;

}
