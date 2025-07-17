package com.example.java_all.java17.Record;

public class EventProcessor {

    public void handleEvent(IEvent event) {
        switch(event) {
            case LoginEvent login -> System.out.printf("User %s logged in at %d%n",
                    login.username(), login.timestamp());

            case LogoutEvent logout ->System.out.printf("User %s logged out at %d%n",
                    logout.username(), logout.timestamp());
        };
    }
}
