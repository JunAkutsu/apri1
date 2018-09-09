package com.apri.google_api;

import java.io.IOException; 
import java.util.Collections; 
import java.util.Date; 
import java.util.HashSet; 
import java.util.Set; 


import javax.servlet.http.HttpServletRequest; 
 

 import org.apache.commons.logging.Log; 
 import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.stereotype.Controller; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RequestMethod; 
 import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.servlet.view.RedirectView; 
 

 import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl; 
 import com.google.api.client.auth.oauth2.Credential; 
 import com.google.api.client.auth.oauth2.TokenResponse; 
 import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow; 
 import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets; 
 import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details; 
 import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport; 
 import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory; 
 import com.google.api.client.json.jackson2.JacksonFactory; 
 import com.google.api.client.util.DateTime; 

 import com.google.api.services.calendar.CalendarScopes; 
 import com.google.api.services.calendar.model.Event; 
 import com.google.api.services.calendar.model.Events;
 import com.google.api.client.auth.oauth2.Credential; 
 import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp; 
 import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver; 
 import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow; 
 import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets; 
 import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport; 
 import com.google.api.client.http.javanet.NetHttpTransport; 
 import com.google.api.client.json.JsonFactory; 
 import com.google.api.client.json.jackson2.JacksonFactory; 
 import com.google.api.client.util.DateTime; 
 import com.google.api.client.util.store.FileDataStoreFactory; 
 import com.google.api.services.calendar.Calendar; 
 import com.google.api.services.calendar.CalendarScopes; 
 import com.google.api.services.calendar.model.Event; 


 import java.io.IOException; 
 import java.io.InputStream; 
 import java.io.InputStreamReader; 
 import java.security.GeneralSecurityException; 
 import java.util.Collections; 
 import java.util.List; 
 import org.springframework.core.io.Resource;


 @Controller 
 @RequestMapping(value="/google2")
 public class GoogleCalController2 { 
 
		@Autowired
		ApplicationContext context;

	 private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart"; 
     private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance(); 
     private static final String TOKENS_DIRECTORY_PATH = "tokens"; 
 
     private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY); 
     private static final String CREDENTIALS_FILE_PATH = "credentials.json"; 

     /** 
      * Creates an authorized Credential object. 
      * @param HTTP_TRANSPORT The network HTTP Transport. 
      * @return An authorized Credential object. 
      * @throws IOException If the credentials.json file cannot be found. 
      */ 
     private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException { 
         // Load client secrets. 
//         InputStream in = GoogleCalController2.class.getResourceAsStream(CREDENTIALS_FILE_PATH); 
  		Resource resource = context.getResource("classpath:json/client_secret.json"); 
        InputStream in = resource.getInputStream(); 
    	 
         GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in)); 
 

         // Build flow and trigger user authorization request. 
         GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder( 
                 HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES) 
                 .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH))) 
                 .setAccessType("offline") 
                 .build(); 
         return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user"); 
     } 

     
 	@RequestMapping(value = "/main", method = RequestMethod.GET) 
 	public ResponseEntity<String> googleConnectionStatus() throws Exception { 
 			// Build a new authorized API client service. 
 		    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport(); 
 		    Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME) .build(); 

 		    // List the next 10 events from the primary calendar. 
 		    DateTime now = new DateTime(System.currentTimeMillis()); 
 		    Events events = service.events().list("primary").setMaxResults(10).setTimeMin(now).setOrderBy("startTime").setSingleEvents(true).execute();  
 		                   
 		    List<Event> items = events.getItems(); 
 		   String message = events.getItems().toString();  
 		    if (items.isEmpty()) { 
 		          System.out.println("No upcoming events found."); 
 		    }
 		    else { 
 		          System.out.println("Upcoming events"); 
 		          for (Event event : items) { 
 		              DateTime start = event.getStart().getDateTime(); 
 		              if (start == null) { 
 		                   start = event.getStart().getDate(); 
 		              } 
 		              System.out.printf("%s (%s)\n", event.getSummary(), start); 
 		          } 
 		   } 
 		   return new ResponseEntity<>(message, HttpStatus.OK); 
 	} 
 


 } 
