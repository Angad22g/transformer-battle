package com.aequilibrium.transformers.exception;

public class InvalidTransformerTypeException extends RuntimeException {

    public InvalidTransformerTypeException(String transformerType) {
	    super("Invalid transformer type: " + transformerType );
    }

}
