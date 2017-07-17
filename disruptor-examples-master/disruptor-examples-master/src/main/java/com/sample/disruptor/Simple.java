package com.sample.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.ProducerType;

public class Simple {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		ThreadFactory customThreadfactory = new CustomThreadFactoryBuilder()
				.setNamePrefix("DemoPool-Thread").setDaemon(false)
				.setPriority(Thread.MAX_PRIORITY).build();
		ExecutorService exec = Executors.newFixedThreadPool(2,
				customThreadfactory);
		ExecutorService prod_exec = Executors.newFixedThreadPool(2);
		// Preallocate RingBuffer with 1024 ValueEvents
		final EventHandler<ValueEvent> handler = new CustomEventHandler<ValueEvent>();
		DisruptorQueue<ValueEvent> disruptor = new DisruptorQueue<ValueEvent>(
				ValueEvent.EVENT_FACTORY, 8, exec, ProducerType.SINGLE,
				new BlockingWaitStrategy(), handler);

		// Build dependency graph
		RingBuffer<ValueEvent> ringBuffer = disruptor.start();
		Producer<ValueEvent> producer_1 = new Producer<ValueEvent>(ringBuffer,"STAR",0,2,0);
		Producer<ValueEvent> producer_2 = new Producer<ValueEvent>(ringBuffer,"FTL",1,2,0);
		prod_exec.execute(producer_1);
		prod_exec.execute(producer_2);
		
//		prod_exec.
		
	}
}
