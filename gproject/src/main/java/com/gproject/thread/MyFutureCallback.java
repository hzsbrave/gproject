package com.gproject.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/*
 * google guava 使用事例
 * 
 * 1.利用Executors创建一个线程池pool
 * 2.使用Guava包的MoreExecutors根据线程池pool创建异步任务
 * 3.创建多个任务
 */

public class MyFutureCallback {
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);
        //ListeningExecutorService service1 = MoreExecutors.listeningDecorator(pool);
        //新建一个任务，需要1秒时间完成
        ListenableFuture<String> planTask = planTask(service, "one", 1000);
        //新建一个任务，可以即刻完成
        ListenableFuture<String> planTask1 = planTask(service, "two", 2000);
        //当在主线程中调用Future的get()方法以获得结果时， 当前线程就开始阻塞（阻塞等待的时间是对应planTask执行的时间），直到等待planTask中call方法结束返回结果，其他未完成的任务继续异步执行。
        System.out.println(planTask.get(1000, TimeUnit.SECONDS));
        //System.out.println(planTask1.get());
        System.out.println("---------main end----------");
    }

    public static ListenableFuture<String> planTask(ListeningExecutorService service, final String no,
                                                    final int sleepTime) {
        /*submit中使用runnable或callable的情况
         * 在并发编程时，一般使用runnable，然后扔给线程池完事，这种情况下不需要线程的结果。 所以run的返回值是void类型。
		 * 
		 * 如果是一个多线程协作程序，使用多线程来计算。
		 * 但后者需要前者的结果，就需要用callable接口了。
		 * callable用法和runnable一样，只不过调用的是call方法，该方法有一个泛型返回值类型，你可以任意指定。
		 * 
		 * 线程是属于异步计算模型，所以你不可能直接从别的线程中得到函数返回值。
		 * 这时候，Future就出场了。Futrue可以监视目标线程调用call的情况，当你调用Future的get()方法以获得结果时，
		 * 当前线程就开始阻塞，直接call方法结束返回结果。
		 */
        final ListenableFuture<String> listenableFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(sleepTime);
                return no + "... finished!";
            }
        });
        //添加一个监听线程，当任务完成时，自动调用该方法
        listenableFuture.addListener(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("task " + no + " finish,result: " + listenableFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, pool);
        System.out.println("--------plan Task --------" + no);
        return listenableFuture;
    }

}