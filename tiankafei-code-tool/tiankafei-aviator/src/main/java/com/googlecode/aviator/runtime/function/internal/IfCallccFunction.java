package com.googlecode.aviator.runtime.function.internal;

import java.util.Map;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.utils.Constants;

/**
 * __if_callcc function
 *
 * @author dennis(killme2008@gmail.com)
 *
 */
public class IfCallccFunction extends AbstractFunction {


  private static final long serialVersionUID = 3511688119189694245L;

  @Override
  public String getName() {
    return "__if_callcc";
  }

  @Override
  public AviatorObject call(final Map<String, Object> env, final AviatorObject arg1,
      final AviatorObject arg2) {
    if (arg1 instanceof ReducerResult && ((ReducerResult) arg1).state != ReducerState.Cont) {
      return arg1;
    } else {
      final Object nextClauseVal = arg2.getValue(env);
      if (nextClauseVal == Constants.REDUCER_EMPTY) {
        return arg1;
      }
      AviatorFunction otherClausesFn = (AviatorFunction) nextClauseVal;
      AviatorObject result = otherClausesFn.call(env);
      // No remaining statements, return the if statement result.
      if (result == Constants.REDUCER_EMPTY) {
        return arg1;
      }
      return result;
    }
  }
}
