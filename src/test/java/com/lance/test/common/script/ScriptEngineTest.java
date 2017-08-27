package com.lance.test.common.script;

import org.junit.Test;

import javax.script.*;
import java.io.FileReader;

/**
 * @author Lance
 * @date 2016/11/12
 */
public class ScriptEngineTest {

    @Test
    public void test() {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Bindings bind = engine.createBindings();
        bind.put("factor", 1);

        engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
//        Scanner scan = new Scanner()

        int var1 = 10;
        int var2 = 20;

        try {
            engine.eval(new FileReader("e:/module.js"));
            if (engine instanceof Invocable) {
                Invocable in = (Invocable) engine;
                Double result = (Double) in.invokeFunction("formula", var1, var2);
                System.out.println("result: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
