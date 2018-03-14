package org.vena.example.extensions.kotlin

/**
 * Calls the specified function [block] with the given [receiver] as its receiver and returns Unit.
 */
inline fun <T> configure(receiver: T, block: T.() -> Unit) = receiver.block()