package com.example.java_all;

import com.example.java_all.Java8.SequentialStream;
import com.example.java_all.core.ArrayListClass.CArrayList;

import com.example.java_all.core.ThreadLifeCycle.NewState;
import com.example.java_all.core.ThreadLifeCycle.RunnableState;
import com.example.java_all.core.ThreadLifeCycle.TimeWaiting;
import com.example.java_all.core.ThreadLifeCycle.WaitingState;
import com.example.java_all.core.inheritance.InheritanceDog;
import com.example.java_all.core.polymorphismCompiletime.PolyCompileTime;
import com.example.java_all.core.polymorphismRuntime.Animal;
import com.example.java_all.core.polymorphismRuntime.Cat;
import com.example.java_all.core.polymorphismRuntime.Dog;
import com.example.java_all.designpattern.SingletonLoggerClass;
import com.example.java_all.java17.Record.EventProcessor;
import com.example.java_all.java17.Record.IEvent;
import com.example.java_all.java17.Record.LoginEvent;
import com.example.java_all.java17.Record.LogoutEvent;
import com.example.java_all.java17.Sealed.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaAllApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(JavaAllApplication.class, args);

		SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

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
		EventProcessor event = new EventProcessor();
		IEvent login = new LoginEvent("Jade",System.currentTimeMillis());
		IEvent logout = new LogoutEvent("Mike",System.currentTimeMillis());
		event.handleEvent(login);
		event.handleEvent(logout);

		//Streams
		SequentialStream sequentialStream =new SequentialStream();
		sequentialStream.streamFilter();
		sequentialStream.streamMap();
		sequentialStream.streamFlatmap();
		sequentialStream.streamDistinct();
		sequentialStream.streamReduce();
		sequentialStream.streamReverse();
		sequentialStream.streamEmp();



	}

}
