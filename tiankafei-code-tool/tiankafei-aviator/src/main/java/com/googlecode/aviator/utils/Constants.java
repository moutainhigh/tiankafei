package com.googlecode.aviator.utils;

import com.googlecode.aviator.lexer.token.Token;
import com.googlecode.aviator.lexer.token.Variable;
import com.googlecode.aviator.runtime.function.internal.ReducerResult;
import com.googlecode.aviator.runtime.type.AviatorNil;

public class Constants {
  public static final String REDUCER_EMPTY_VAR = "__reducer_empty";
  public static final Variable ReducerEmptyVal = new Variable(REDUCER_EMPTY_VAR, -1);
  public static final Variable IfReturnFn = new Variable("__if_callcc", -1);
  public static final Token<?> ReducerBreakFn = new Variable("__reducer_break", -1);
  public static final Token<?> ReducerContFn = new Variable("__reducer_cont", -1);
  public static final Token<?> ReducerReturnFn = new Variable("__reducer_return", -1);
  public static final Variable ReducerFn = new Variable("__reducer_callcc", -1);
  public static final String PARAMS_META = "params";
  public static final String SCOPE_META = "newLexicalScope";
  public static final String INHERIT_ENV_META = "inheritEnv";
  public static final String INSTANCE_VAR = "__instance__";
  public static final String ENV_VAR = "__env__";
  public static final String FUNC_ARGS_VAR = "__args__";
  public static final String REDUCER_LOOP_VAR = "__reducer_loop";
  public static final String DEFINE_META = "define";
  public static final Variable REDUCER_LOOP = new Variable("__reducer_loop", -1);
  public static final String NEWLINE = "\r\n";
  public static final ReducerResult REDUCER_EMPTY = ReducerResult.withEmpty(AviatorNil.NIL);
  public static final String REQUIRE_FN = "require";
  public static final String LOAD_FN = "load";
  public static final Variable CATCH_HANDLER_VAR = new Variable("__catch_handler", -1);
  public static final Variable SEQ_LIST_VAR = new Variable("seq.list", -1);
  public static final Variable TRY_VAR = new Variable("__try", -1);
  public static final Variable THROW_VAR = new Variable("__throw", -1);
  public static final Variable NEW_VAR = new Variable("__new", -1);
  public static final Variable THROWABLE_VAR = new Variable("Throwable", -1);

  private Constants() {

  }
}
