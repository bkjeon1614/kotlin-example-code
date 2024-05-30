package com.bkjeon.base.core.config.cache

import com.bkjeon.base.core.log.logger
import org.ehcache.event.CacheEvent
import org.ehcache.event.CacheEventListener

class EhCacheLogger: CacheEventListener<Any, Any> {

    private val log = logger()

    override fun onEvent(cacheEvent: CacheEvent<out Any, out Any>) {
        log.info("Key: [${cacheEvent.key}] | EventType: [${cacheEvent.type}] | Old value: [${cacheEvent.oldValue}] | New value: [${cacheEvent.newValue}]")
    }

}