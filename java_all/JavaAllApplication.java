package com.example.java_all;

import com.example.java_all.Java8.ParellelStream;
import com.example.java_all.Java8.SequentialStream;
import com.example.java_all.Java8.StreamAPI;
import com.example.java_all.core.ArrayListClass.CArrayList;

import com.example.java_all.core.MapClass;
import com.example.java_all.core.StringClass.StringBasics;
import com.example.java_all.core.Thread.CompletableFutureThread;
import com.example.java_all.core.Thread.CompletableThread;
import com.example.java_all.core.Thread.FixedThreadPool;
import com.example.java_all.core.ThreadLifeCycle.NewState;
import com.example.java_all.core.ThreadLifeCycle.RunnableState;
import com.example.java_all.core.ThreadLifeCycle.TimeWaiting;
import com.example.java_all.core.ThreadLifeCycle.WaitingState;
import com.example.java_all.core.inheritance.InheritanceDog;
import com.example.java_all.core.polymorphismCompiletime.PolyCompileTime;
import com.example.java_all.core.polymorphismRuntime.Animal;
import com.example.java_all.core.polymorphismRuntime.Cat;
import com.example.java_all.core.polymorphismRuntime.Dog;
import com.example.java_all.core.service.OrderService;
import com.example.java_all.designpattern.FactoryDesign.PaymentFactory;
import com.example.java_all.designpattern.FactoryDesign.PaymentProcess;
import com.example.java_all.designpattern.SingletonLoggerClass;
import com.example.java_all.java17.Sealed.*;
import com.example.java_all.java21.SequenceMap;
import com.example.java_all.java21.SequencedCollections;
import com.example.java_all.java21.StringBuilderRepeat;
import com.example.java_all.java21.VirtualThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class JavaAllApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(JavaAllApplication.class, args);

		SingletonLoggerClass logger = SingletonLoggerClass.getInstance();
		System.out.println("Thread name: " + Thread.currentThread().getName());

		CArrayList cArrayList=new CArrayList();
		cArrayList.duplicateArray();
		cArrayList.countFrequentElement();
		cArrayList.reverseArray();

		// Runtime Polymorphism - Method Overriding
		Animal animal = new Animal();
		Animal dog = new Dog();
		Animal cat =  new Cat();
		animal.makeSound();
		dog.makeSound();
		cat.makeSound();
		dog.makeFood("Dog",4);
		cat.makeFood("Cat",5);


		// Compile time Polymorphism - Method Overloading
		PolyCompileTime compileTime=new PolyCompileTime();
		compileTime.message("Success");
		compileTime.message(10);

		//Single Inheritance
		InheritanceDog inheritanceDog = new InheritanceDog("Tommy"); // Inherited constructor property
		inheritanceDog.name="Tommy"; // Inherited property
		inheritanceDog.Animaleat(); // Inherited method  - Animal Class
		inheritanceDog.dogBark();  // Overridden method- Dog class

        //Thread Lifecycle - NEW
		NewState t = new NewState();
		t.run();

		//Thread Lifecycle - RUNNABLE
		RunnableState r = new RunnableState();
		r.run();

		//Thread Lifecycle - TIMED_WAITING
		Thread tw = new Thread(new TimeWaiting());
		tw.start();
		Thread.sleep(5000);
		logger.logThread(String.valueOf(tw.getState()));

		//Thread Lifecycle - WAITING
		Thread t1= new Thread(new WaitingState());
		t1.start();
		WaitingState w = new WaitingState();
		w.run();
		logger.logThread(String.valueOf(t1.getState()));



		//Java17- Sealed Class
		PaymentProcessor paymentProcessor =new PaymentProcessor();
		Payment credit=new CreditCardPayment();
		Payment paypal=new PaypalPayment();
		Payment bank=new BankTransferPayment();
		paymentProcessor.handlePayment(credit);
		paymentProcessor.handlePayment(paypal);
		paymentProcessor.handlePayment(bank);

		//Java17- Record Class
		//EventProcessor event = new EventProcessor();
		//IEvent login = new LoginEvent("Jade",System.currentTimeMillis());
		//IEvent logout = new LogoutEvent("Mike",System.currentTimeMillis());
		//event.handleEvent(login);
		//event.handleEvent(logout);

		//Sequential Stream
		SequentialStream sequentialStream =new SequentialStream();
		sequentialStream.streamFilter();
		sequentialStream.streamMap();
		sequentialStream.streamFlatmap();
		sequentialStream.streamDistinct();
		sequentialStream.streamReduce();
		sequentialStream.streamReverse();
		sequentialStream.streamEmp();

		//Parallel Stream
		ParellelStream parellelStream = new ParellelStream();
		parellelStream.parellelStream();

		CompletableThread completableThread  = new CompletableThread();
		completableThread.completableThread();

		FixedThreadPool fixedThreadPool = new FixedThreadPool();
		fixedThreadPool.FixedThread();

		//Java 21 - Sequence Collections
		SequencedCollections sequencedCollections = new SequencedCollections();
		sequencedCollections.sequenceSet();

		SequenceMap sequenceMap = new SequenceMap();
		sequenceMap.sequenceMap();

		//Java 21 - Virtual Threads
		VirtualThread virtualThread = new VirtualThread();
		virtualThread.virtualExecutorService();

		//Java 21 -String Repeat
		StringBuilderRepeat stringBuilderRepeat = new StringBuilderRepeat();
		stringBuilderRepeat.stringRepeat();

		sequentialStream.sortDescStream();
		sequentialStream.streamEvenSum();
		sequentialStream.streamSecond();
		sequentialStream.streamCommon();

		//Java String
		StringBasics stringBasics = new StringBasics();
		stringBasics.strEquals();

		//More Streams
		sequentialStream.removeDuplicate();
		sequentialStream.sortReverseOrder();
		sequentialStream.multiplesofInt(2);
		sequentialStream.filterMutilples(11);
		sequentialStream.filterMax();
		sequentialStream.filterMin();

		sequentialStream.commontwoArray();
		sequentialStream.mergeTwoArray();
		sequentialStream.secondLargest();

		//Service
		OrderService orderService = context.getBean(OrderService.class);
		orderService.placeOrder(500);

		//More Stream API
		StreamAPI stream = new StreamAPI();
		stream.highestLength();
		stream.duplicateSameorder();
		stream.SecondHighestStrLength();
		stream.SecondHighestIntegerLength();

		stream.highestSalaryEmpDept();
		stream.departCount();
		stream.ListEmpByDept();
		stream.filterEven();
		stream.findCharStream();
		stream.findLongString();
		stream.findFrequencyInt();

		stringBasics.equalsCheck();



		//FileReader reader = new FileReader("file.txt"); // Compilation error!
		//Checked Exceptions Compile Time- FileNotFound Exceptions

		try {
			FileReader reader = new FileReader("file.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}

		//UnChecked Exceptions Run Time- NullPointer Exception

		String str = "test";//null
		System.out.println(str.length()); // This will throw NullPointerException

        //Collections -Map
		 MapClass mapClass = new MapClass();
		 mapClass.HashMapBucketIndexInteger();
		 mapClass.HashMapBucketIndexStr();

		Thread thread = new Thread(() -> {
			System.out.println("Thread is running...");
			try {
				Thread.sleep(1000); // TIMED_WAITING
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println("Thread finished");
		});

		 //thread.run();
		 System.out.println("State after Thread Obj creation: " +thread.getState());

		 thread.start();
		 System.out.println("State after start() is called: " +thread.getState());

		 Thread.sleep(100);
		 System.out.println("State during sleep: " +thread.getState());

		 thread.join();

		 System.out.println("State after finish: " +thread.getState());

		 final Object lock = new Object();


		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				1,
				1,
				1,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>()
		);
		executor.submit(() -> {
			System.out.println(thread.getState());
		});

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			return "Hello";
		});

		future.thenApply(result-> result+" World")
				.thenAccept(System.out::println);



        Runnable task = () -> System.out.println("Name :"+Thread.currentThread().getName()+ Thread.currentThread().getState());
		Thread runtask = new Thread(task);
		runtask.start();

		Runnable task2 = () ->System.out.println("Name :"+Thread.currentThread().getName()+ Thread.currentThread().getState());

		ExecutorService runnableService = Executors.newFixedThreadPool(2);
		runnableService.execute(task);
		runnableService.execute(task2);
		runnableService.submit(task);
		runnableService.shutdown();


		Callable<String> call = () -> {
			Thread.sleep(500);
			return "Result from Callable";
		};
		logger.log(call.call().toString());

		CompletableFutureThread completableFutureThread = new CompletableFutureThread();
		completableFutureThread.CompletableFuture();

	    //Factory pattern
		PaymentProcess gPay = PaymentFactory.getPaymentMethod("Gpay");
		PaymentProcess applePay = PaymentFactory.getPaymentMethod("Applepay");

		gPay.processPayment();
		applePay.processPayment();




	}



}
