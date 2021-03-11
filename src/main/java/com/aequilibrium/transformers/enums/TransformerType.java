package com.aequilibrium.transformers.enums;

import com.aequilibrium.transformers.exception.InvalidTransformerTypeException;

public enum TransformerType {

    AUTOBOTS("A", "Autobots"),
    DECEPTICONS("D", "Decepticons");

    public final String type;
    public final String value;

    private TransformerType(String type, String value) {
	this.type = type;
	this.value = value;
    }

    public static TransformerType getByType(String type) {
	if (type.equalsIgnoreCase(AUTOBOTS.type)) {
	    return AUTOBOTS;
	} else if (type.equalsIgnoreCase(DECEPTICONS.type)) {
	    return DECEPTICONS;
	}

	throw new InvalidTransformerTypeException(type);
    }
    
    public static TransformerType getByValue(String value) {
	if (value.equalsIgnoreCase(AUTOBOTS.value)) {
	    return AUTOBOTS;
	} else if (value.equalsIgnoreCase(DECEPTICONS.value)) {
	    return DECEPTICONS;
	}

	throw new InvalidTransformerTypeException(value);
    }

}