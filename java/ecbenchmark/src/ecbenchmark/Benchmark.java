/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class Benchmark {

    protected String name, description;
    protected List<Function> functions = new ArrayList<Function>();

    public Benchmark(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Benchmark() {
        this("", "");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    protected void add(Function f) {
        this.functions.add(f);
    }

    public List<Function> getFunctions() {
        return new ArrayList<Function>(this.functions);
    }

    public Function getFunction(int index) {
        return this.functions.get(index);
    }

    public int numberOfFunctions() {
        return this.functions.size();
    }
}
