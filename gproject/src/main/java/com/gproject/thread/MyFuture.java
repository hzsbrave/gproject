package com.gproject.thread;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/2/23.
 */
public class MyFuture {

    //ExecutorService提供办法处理结束--两种 shutdown和提供Future追踪一个或多个异步任务(submit)
   //shutdownNow--尝试停止所有执行的任务和等待的任务，返回所有等待任务
    //shutdown--开始停止已注册执行的任务，不接受新任务，再调用shutdownNow
    //Executor--工厂生成类
    private static ExecutorService pool= Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);
        //ListeningExecutorService service1 = MoreExecutors.listeningDecorator(pool);
        //新建一个任务，需要1秒时间完成
        ListenableFuture<String> planTask = startTask(service, "one");
        //新建一个任务，可以即刻完成
        ListenableFuture<String> planTask1 = startTask(service, "two");
        //当在主线程中调用Future的get()方法以获得结果时， 当前线程就开始阻塞（阻塞等待的时间是对应planTask执行的时间），直到等待planTask中call方法结束返回结果，其他未完成的任务继续异步执行。
        System.out.println(planTask.get(1000, TimeUnit.SECONDS));
        //System.out.println(planTask1.get());
        System.out.println("---------main end----------");
    }

    public static ListenableFuture<String> startTask(ListeningExecutorService service,final String no){

        //注册，返回future
       final ListenableFuture future=service.submit(new Callable() {
            @Override
            public Object call() throws Exception {

                return "finish()";
            }
        });

    //添加一个监听线程，当任务完成时，自动调用该方法
                future.addListener(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            System.out.println("task " + no + " finish,result: " + future.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }, pool);
              return future;
    }



}
