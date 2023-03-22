package com.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getGreeting")
public class InternationalGreetingResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting getGreeting(Data data) {
        switch (data.getLanguage()) {
            case "Portuguese":
                return new Greeting("Saudações do Serverless Workflow, " + data.getName() + "!");
            case "Spanish":
                return new Greeting("Saludos desde Serverless Workflow, " + data.getName() + "!");
            default:
                return new Greeting("Greetings from Serverless Workflow, " + data.getName() + "!");
        }
    }
}