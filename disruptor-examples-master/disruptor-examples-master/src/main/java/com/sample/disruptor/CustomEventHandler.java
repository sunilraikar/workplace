package com.sample.disruptor;

import com.lmax.disruptor.EventHandler;

public class CustomEventHandler<T> implements EventHandler<T> {

	public void onEvent(T event, long sequence, boolean endOfBatch)
			throws Exception {
    	process(event, sequence);
    	Thread.sleep(5000);
	}

	public void process(T event,long sequence){
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Consumer --> Sequence: "+sequence +" ValueEvent: " +  ((ValueEvent) event).getValue()+" Producer :"+((ValueEvent) event).getProducerIdentifier());
		System.out.println("----------------------------------------------------------------------------------------");
//		System.out.println("ValueEvent: " +  ((ValueEvent) event).getValue());
	}
}
