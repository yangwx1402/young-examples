package com.am.jlfu.notifier;


import com.am.jlfu.notifier.utils.GenericPropagator;
import org.springframework.stereotype.Component;



/**
 * Propagates the events to the registered listeners.
 * 
 * @author antoinem
 * 
 */
@Component
public class JLFUListenerPropagator extends GenericPropagator<JLFUListener> {

	@Override
	protected Class<JLFUListener> getProxiedClass() {
		return JLFUListener.class;
	}

}
