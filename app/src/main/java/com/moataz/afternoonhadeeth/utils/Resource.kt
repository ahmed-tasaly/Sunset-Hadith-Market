package com.moataz.afternoonhadeeth.utils

class Resource {

    private var status: Status? = null
    private var data: Any? = null
    private var message: String? = null

    private fun resource(status: Status, data: Any?, message: String?) {
        this.status = status
        this.data = data
        this.message = message
    }

    fun success(data: Any?) {
        return resource(Status.SUCCESS, data, null)
    }

    fun error(msg: String): Any {
        return resource(Status.ERROR, null, msg)
    }

    fun loading(): Any {
        return resource(Status.LOADING, null, null)
    }
}