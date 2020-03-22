package com.googlecode.aviator.script;

import com.googlecode.aviator.Expression;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * @author libinsong1204@gmail.com
 */
public class CompiledAviatorScript extends CompiledScript {

    private final AviatorScriptEngine engine;
    private final Expression expression;


    CompiledAviatorScript(AviatorScriptEngine engine, Expression expression) {
        this.engine = engine;
        this.expression = expression;
    }


    @Override
    @SuppressWarnings("unchecked")
    public Object eval(ScriptContext context) throws ScriptException {
        try {
            Map<String, Object> map = new HashMap<String, Object>(100);
            for (Iterator it = context.getScopes().iterator(); it.hasNext(); ) {
                int scope = ((Integer) it.next()).intValue();
                Bindings bindings = context.getBindings(scope);
                Set keys = bindings.keySet();

                for (Object key : keys) {
                    map.put((String) key, bindings.get(key));
                }
            }
            return this.expression.execute(map);
        } catch (Exception e) {
            throw new ScriptException(e);
        }
    }


    @Override
    public ScriptEngine getEngine() {
        return this.engine;
    }

}
