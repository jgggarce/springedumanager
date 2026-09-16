package com.alkemy.springedumanager.model;

/**
 * Distingue si un registro de {@link Evaluacion} corresponde a una practica
 * o a una evaluacion formal, para poder cubrir ambos conceptos mencionados
 * en la consigna con una unica tabla.
 */
public enum TipoEvaluacion {
    PRACTICA,
    EVALUACION
}
