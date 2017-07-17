package com.sample.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * WARNING: This is a mutable object which will be recycled by the RingBuffer. You must take a copy of data it holds
 * before the framework recycles it.
 */
public final class ValueEvent {
    private String value;
    private String producerIdentifier;


	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getProducerIdentifier() {
    	return producerIdentifier;
    }
    
    public void setProducerIdentifier(String producerIdentifier) {
    	this.producerIdentifier = producerIdentifier;
    }

    public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
        public ValueEvent newInstance() {
            return new ValueEvent();
        }
    };
}
