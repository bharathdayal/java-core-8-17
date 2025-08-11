package com.example.java_all;

import com.example.java_all.Java8.ParellelStream;
import com.example.java_all.Java8.SequentialStream;
import com.example.java_all.Java8.StreamAPI;
import com.example.java_all.core.AnimalInter;
import com.example.java_all.core.ArrayListClass.CArrayList;

import com.example.java_all.core.InterviewCode;
import com.example.java_all.core.MapClass.MapClass;
import com.example.java_all.core.StringClass.StringBasics;
import com.example.java_all.core.Thread.*;
import com.example.java_all.core.ThreadLifeCycle.NewState;
import com.example.java_all.core.ThreadLifeCycle.RunnableState;
import com.example.java_all.core.ThreadLifeCycle.TimeWaiting;
import com.example.java_all.core.ThreadLifeCycle.WaitingState;
import com.example.java_all.core.exception.FileReadException;
import com.example.java_all.core.hashcodequals.UserHash;
import com.example.java_all.core.inheritance.InheritanceDog;
import com.example.java_all.core.polymorphismCompiletime.PolyCompileTime;
import com.example.java_all.core.polymorphismRuntime.Animal;
import com.example.java_all.core.polymorphismRuntime.Cat;
import com.example.java_all.core.polymorphismRuntime.Dog;
import com.example.java_all.core.serializable.UserSerialize;
import com.example.java_all.core.service.OrderService;
import com.example.java_all.designpattern.FactoryDesign.PaymentFactory;
import com.example.java_all.designpattern.FactoryDesign.PaymentProcess;
import com.example.java_all.designpattern.SingletonLoggerClass;
import com.example.java_all.designpattern.builderpattern.UserBuilder;
import com.example.java_all.java17.Sealed.*;
import com.example.java_all.java21.SequenceMap;
import com.example.java_all.java21.SequencedCollections;
import com.example.java_all.java21.StringBuilderRepeat;
import com.example.java_all.java21.VirtualThread;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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
			System.out.println("File opened successfully.");
		} catch (IOException e) {
			try {
				throw new FileReadException("Failed to read file: file.txt", e);
			} catch (FileReadException ex) {
				System.err.println("Custom Exception Caught: " + ex.getMessage());
			}
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

		//Interview Code
		InterviewCode interviewCode = new InterviewCode();
		interviewCode.findFirstNonRepChar();
		interviewCode.findSecondNonRepChar();
		interviewCode.findFirstNonRepCharStream();


		// Serializable
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("user.ser"));
		objectOutputStream.writeObject(new UserSerialize("Bharath",40));
		objectOutputStream.close();

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.ser"));
		UserSerialize userSerializes = (UserSerialize) objectInputStream.readObject();
		objectInputStream.close();
		logger.log(userSerializes.toString());

		ObjectMapper objectMapper = new ObjectMapper();
		UserSerialize userSerializesObj =new UserSerialize("Bharath",40);
		String outJson = objectMapper.writeValueAsString(userSerializesObj);
		logger.log(outJson);


		//Builder Pattern
		UserBuilder userBuilder =  UserBuilder.builder().user("Bharath").age(40).phone("33434").email("test@test.com").build();
		String outJson2 = objectMapper.writeValueAsString(userBuilder);
		logger.log(outJson2);


		String chartest = "Count the frequency of numbers";

		Map<Character,Integer> charmap = new HashMap<>();

		String replacetest = chartest.replaceAll(" ","");

		for(char ch:replacetest.toCharArray()) {
			charmap.put(ch,charmap.getOrDefault(ch,0)+1);

		}
		logger.log(charmap.toString());

		for(Map.Entry<Character,Integer> entryMap:charmap.entrySet()) {
			if(entryMap.getValue()==1) {
				logger.log(entryMap.getKey().toString());
				//return;
			}
		}

		String strReverse ="Hello World";
		String reverseString = "";

		for(int i=strReverse.length()-1;i<=0;i++) {
			reverseString+=strReverse.charAt(i);
		}

		logger.log(reverseString);

		String strCount = "aaabbcccddaa";
		Map<Character,Integer> mapCount = new HashMap<>();

		for(char chcount:strCount.toCharArray()){

			mapCount.put(chcount,mapCount.getOrDefault(chcount,0)+1);
		}
		String newcnt = "";

		for(Map.Entry<Character,Integer> entryCnt:mapCount.entrySet()){

			newcnt+=entryCnt.getKey()+""+entryCnt.getValue();
			logger.log(entryCnt.getKey().toString());
			logger.log(entryCnt.getValue().toString());
		}

		logger.log(mapCount.toString());
		logger.log(newcnt);

		AnimalInter ani =()->System.out.println("Test");
		ani.sound();

		HashSet<String> set_char = new HashSet<>();
		set_char.add("Test");

		// No NULL key/values
		//No order
		// Throws  NullPointer Exception for null keys and values
		Map<Integer,String> concurrentHashMap = new ConcurrentHashMap<>();
		concurrentHashMap.put(1,"concurrentHashMap");
		concurrentHashMap.put(2,"Thread safe");
		logger.log("ConcurrentHashMap: " + concurrentHashMap);

		//No Null Key / Multiple Null value
		//Sorted Key order
		// Throws  NullPointer Exception for null keys
		Map<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("Zebra", 1);
		treeMap.put("Banana", 3);
		treeMap.put("Apple", 2);
		treeMap.put("Orange", 4);
		treeMap.put("Manago",  null);
		treeMap.put("Corn",  null);
		treeMap.put("Null",  null);
		logger.log("TreeMap: " + treeMap);

		//Allows 1 Null Key/ Multiple Null values
		// No order
		// No NullPointer Exception for null keys
		// Keys are replace. Check key equals and present.
		Map<String,Integer> hashMapInterview = new HashMap<>();
		hashMapInterview.put("Zebra", 1);
		hashMapInterview.put("Banana", 3);
		hashMapInterview.put("Apple", 2);
		hashMapInterview.put("Orange", 4);
		hashMapInterview.putIfAbsent("Orange", 3);
		hashMapInterview.put("Manago",  null);
		hashMapInterview.put("Corn",  null);
		hashMapInterview.put(null,  5);
		hashMapInterview.put(null,  6);
		logger.log("HashMap: " + hashMapInterview);

		//Allows 1 Null Key/ Multiple Null values
		// Insertion order
		// No NullPointer Exception for null keys
		// Keys are replace. Check key equals and present.
		Map<String,Integer> linkhashMapInterview = new LinkedHashMap<>();
		linkhashMapInterview.put("Zebra", 1);
		linkhashMapInterview.put("Banana", 3);
		linkhashMapInterview.put("Apple", 2);
		linkhashMapInterview.put("Orange", 4);
		linkhashMapInterview.put("Orange", 3);
		linkhashMapInterview.put("Manago",  null);
		linkhashMapInterview.put("Corn",  null);
		linkhashMapInterview.put(null,  5);
		linkhashMapInterview.put(null,  6);
		logger.log("LinkedHashMap: " + linkhashMapInterview);

		Map<Integer, String> syncMap = Collections.synchronizedMap(new HashMap<>());
		syncMap.put(1,"India");

		syncMap.put(2,"AUS");
		syncMap.put(3,"CHINA");
		logger.log("SynchronizedMap: " + syncMap);



		synchronized (syncMap) {
			Iterator<Map.Entry<Integer,String>> iterator = syncMap.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<Integer,String> entryIterator = iterator.next();
				if(entryIterator.getKey().equals(3)) {
					iterator.remove();
				}
				logger.log("Key: " + entryIterator.getKey()+" ,Value:"+entryIterator.getValue() );
			}
		}


		Set<String> setInterview = new HashSet<>();
		setInterview.add("Carrot");
		setInterview.add("Apple");
		setInterview.add("Apple");
		setInterview.add(null);
		setInterview.add(null);
		if (setInterview.remove("Apple")) {
			setInterview.add("Green Apple");
		}
		logger.log("HashSet: " + setInterview);

		Set<String> concurrentSet = ConcurrentHashMap.newKeySet();
		concurrentSet.add("India");
		concurrentSet.add("USA");
		concurrentSet.add("UK");
		logger.log("Concurrent Set: " + concurrentSet);

		// Objects equals -Hashset
		Set<UserHash> hashObj = new HashSet<>();
		UserHash userHash1 = new UserHash("Alice",1);
		UserHash userHash2 = new UserHash("Alice",1);

		hashObj.add(userHash1);
		hashObj.add(userHash2);

		for(UserHash u: hashObj) {
			logger.log(u.name + " - " + u.id);
		}

		//Atomicity
		Atomic atomic = new Atomic();
		atomic.increment();

		//Deadlock
		DeadlockA thread1 = new DeadlockA();
		DeadlockB thread2 = new DeadlockB();

		new Thread(()->thread1.methodA(thread2)).start();
		new Thread(()->thread2.methodB(thread1)).start();

		//Fruits array
		List<String> fruits = Arrays.asList("apple","banana","cherry");

		int target = 3;
		int windowSum = 0;
		//int inputnum[] ={10,1,2,7,6,1,5};
		int[] inputnum ={2,1,5,1,3,2};


		Map<Integer,Integer> mapTest = new HashMap<>();

		for(int n:inputnum){
			mapTest.put(n,mapTest.getOrDefault(n,0)+1);
		}

		for(int i=0;i<inputnum.length;i++) {
			for(int j=i;j<target;j++) {
				mapTest.put(inputnum[i],j);
			}

		}

		System.out.println(mapTest);

		String inter = "AABBBCBB";

		Map<Character,Integer> intermap = new HashMap<>();
		String intersplit = Arrays.toString(inter.split("\\s"));

		for(char ch:inter.toCharArray()){
			intermap.put(ch,intermap.getOrDefault(ch,0)+1);
		}

		System.out.println(intermap);

        //Substring

		int[] sub = {2,1,5,1,3,2};
		int winSum =0;
		int maxSum1=Integer.MIN_VALUE;


		for(int i=0;i<3;i++){
			winSum +=sub[i];
		}

		for(int j=3;j<sub.length;j++) {
			winSum += sub[j]-sub[j-3];
			maxSum1=Math.max(maxSum1,winSum);

		}

		System.out.println(maxSum1);


		RateLimiter limiter = RateLimiter.create(2);

		System.out.println("Waiting 3 seconds to accumulate permits...");
		Thread.sleep(3000);

		for (int i = 0; i < 5; i++) {
			limiter.acquire(); // blocks until a permit is available
			System.out.println(System.currentTimeMillis() + " → " + i);
		}

		//WeakHashMap

		Map<Object,String> weakHashMap = new WeakHashMap<>();

		Object key1 = new String("Key1");
		Object key2 = new String("Key2");

		weakHashMap.put(key1,"value1");
		weakHashMap.put(key2,"value2");

		logger.log("Before GC"+weakHashMap);

		key1=null;

		System.gc();

		try{
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

		logger.log("After GC"+weakHashMap);




	}



}
