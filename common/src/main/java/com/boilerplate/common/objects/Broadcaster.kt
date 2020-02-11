package com.boilerplate.common.objects

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object Broadcaster {
    private val emitter = PublishSubject.create<Any>()

    fun send(event: Any) {
        emitter.onNext(event)
    }

    // Listen should return an Observable and not the emitter
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> = emitter.ofType(eventType)
}