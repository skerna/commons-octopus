package io.skerna.commons.octopus.handlers

/**
 * @author Ronald Cárdenas
 **/
interface CallHandler{

    fun onCallStarted()

    fun onCallSucceeded()

    fun onCallException(throwable: Throwable)
}