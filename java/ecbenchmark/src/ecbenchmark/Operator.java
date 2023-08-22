/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark;

/**
 *
 * @author jcrada
 */
public abstract class Operator implements Function {

    protected Function innerFunction;

    public Operator() {
    }

    public Operator(Function innerFunction) {
        this.innerFunction = innerFunction;
    }

    public void setInnerFunction(Function f) {
        this.innerFunction = f;
    }

    public Function getInnerFunction() {
        return this.innerFunction;
    }
}
