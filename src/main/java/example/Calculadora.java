/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 * @author esteban
 */
public class Calculadora implements Serializable{
    
    private static final long serialVersionUID = 7526471155622776147L;

    /**
     * Contiene los números de la pila
     * @serial 
     */
    private Stack<Double> numbers = new Stack<>();

    public Stack<Double> getNumbers() {
	return numbers;
    }

    public void setNumbers(Stack<Double> numbers) {
	this.numbers = numbers;
    }

    public Calculadora() {
	numbers = new Stack<>();
    }

    /**
     * Retorna la pila como un string
     *
     * @return
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (Double n : numbers) {
	    sb.append(n.toString());
	    sb.append(" ");
	}
	return sb.toString();
    }

    /**
     * Retorna la pila actual
     *
     * @return
     */
    public Stack<Double> getStack() {
	return numbers;
    }

    /**
     * Retorna el largo de la pila.
     *
     * @return
     */
    public int getStackSize() {
	return numbers.size();
    }

    /**
     * Empuja un número al tope de la pila
     *
     * @param n
     */
    public boolean PushNumber(Double n) {
	numbers.push(n);
	return true;
    }

    /**
     * Hace una suma de los dos elementos en el tope de la pila.
     */
    public boolean OpSum() {
	if (getStackSize() >= 2) {
	    Double a, b;
	    a = numbers.pop();
	    b = numbers.pop();
	    numbers.push(a + b);
	    return true;
	}
	return false;
    }

    /**
     * Hace una resta de los dos elementos en el tope de la pila.
     */
    public boolean OpSub() {
	if (getStackSize() >= 2) {
	    Double a, b;
	    a = numbers.pop();
	    b = numbers.pop();
	    numbers.push(b - a);
	    return true;
	}
	return false;
    }

    /**
     * Hace una multiplicación de los dos elementos en el tope de la pila.
     */
    public boolean OpMul() {
	if (getStackSize() >= 2) {
	    Double a, b;
	    a = numbers.pop();
	    b = numbers.pop();
	    numbers.push(b * a);
	    return true;
	}
	return false;
    }

    /**
     * Hace una división de los dos elementos en el tope de la pila.
     */
    public boolean OpDiv() {
	if (getStackSize() >= 2) {
	    Double a, b;
	    a = numbers.pop();
	    b = numbers.pop();
	    numbers.push(b / a);
	    return true;
	}
	return false;
    }

    /**
     * Retorna el residuo de la división.
     */
    public boolean OpRem() {
	if (getStackSize() >= 2) {
	    Double a, b;
	    a = numbers.pop();
	    b = numbers.pop();
	    numbers.push(b % a);
	    return true;
	}
	return false;
    }

    /**
     * Devuelve la raiz cuadrada del elemento en el tope de la pila.
     */
    public boolean OpSqrt() {
	if (getStackSize() >= 1) {
	    Double a, b;
	    a = numbers.pop();
	    numbers.push(Math.sqrt(a));
	    return true;
	}
	return false;
    }
}
