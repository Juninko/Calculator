package com.kong.test;

import java.util.Stack;

public class Calculator {
    private double currentValue;
    private Stack<Double> undoStack;
    private Stack<Double> redoStack;

    public Calculator() {
        currentValue = 0;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public double add(double num) {
        undoStack.push(currentValue);
        currentValue += num;
        redoStack.clear();
        return currentValue;
    }

    public double subtract(double num) {
        undoStack.push(currentValue);
        currentValue -= num;
        redoStack.clear();
        return currentValue;
    }

    public double multiply(double num) {
        undoStack.push(currentValue);
        currentValue *= num;
        redoStack.clear();
        return currentValue;
    }

    public double divide(double num) {
        if (num == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        undoStack.push(currentValue);
        currentValue /= num;
        redoStack.clear();
        return currentValue;
    }

    public double undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentValue);
            return currentValue = undoStack.pop();
        }
        return currentValue;
    }

    public double redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentValue);
            return currentValue = redoStack.pop();
        }
        return currentValue;
    }


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.add(5);
        System.out.println("After adding 5: " + calculator.currentValue);
        calculator.multiply(3);
        System.out.println("After multiplying by 3: " + calculator.currentValue);
        calculator.undo();
        System.out.println("After undo: " + calculator.currentValue);
        calculator.redo();
        System.out.println("After redo: " + calculator.currentValue);
    }
}