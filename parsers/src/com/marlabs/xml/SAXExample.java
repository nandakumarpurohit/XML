package com.marlabs.xml;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.xerces.parsers.SAXParser;

public class SAXExample extends DefaultHandler {
	
	static int numberOfCircles = 0;   // total number of circles seen
	   static int x[] = new int[1000];   // X-coordinates of the centers
	   static int y[] = new int[1000];   // Y-coordinates of the centers
	   static int r[] = new int[1000];   // radius of the circle
	   static String color[] = new String[1000];  // colors of the circles

	   static int flagX=0;    //to remember what element has occurred
	   static int flagY=0;    //to remember what element has occurred
	   static int flagR=0;    //to remember what element has occurred

	   // main method 
	   public static void main(String[] args) {   
	      try{
	    	  SAXExample SAXHandler = new SAXExample (); // an instance of this class
	         SAXParser parser=new SAXParser();          // create a SAXParser object 
	         parser.setContentHandler(SAXHandler);      // register with the ContentHandler 
	         parser.parse("src/data.xml");
	      }  catch (Exception e) {e.printStackTrace(System.err);}  // catch exeptions
	   }

	   // override the startElement() method
	   public void startElement(String uri, String localName, 
	                       String rawName, Attributes attributes) {
	         if(rawName.equals("circle"))                      // if a circle element is seen
	            color[numberOfCircles]=attributes.getValue("color");  // get the color attribute 
	         
	         else if(rawName.equals("x"))      // if a x element is seen set the flag as 1 
	            flagX=1;
	         else if(rawName.equals("y"))      // if a y element is seen set the flag as 2
	            flagY=1;
	         else if(rawName.equals("radius")) // if a radius element is seen set the flag as 3 
	            flagR=1;
	   }

	   // override the endElement() method
	   public void endElement(String uri, String localName, String rawName) {
	         // in this example we do not need to do anything else here
	         if(rawName.equals("circle"))                       // if a circle element is ended 
	            numberOfCircles +=  1;                          // increment the counter 
	   }

	   // override the characters() method
	   public void characters(char characters[], int start, int length) {
	         String characterData = 
	             (new String(characters,start,length)).trim(); // get the text
	         
	         if(flagX==1) {        // indicate this text is for <x> element 
	             x[numberOfCircles] = Integer.parseInt(characterData);
	             flagX=0;
	         }
	         else if(flagY==1) {  // indicate this text is for <y> element 
	             y[numberOfCircles] = Integer.parseInt(characterData);
	             flagY=0;
	         }
	         else if(flagR==1) {  // indicate this text is for <radius> element 
	             r[numberOfCircles] = Integer.parseInt(characterData);
	             flagR=0;
	         }
	   }

	   // override the endDocument() method
	   public void endDocument() {
	         // when the end of document is seen, just print the circle info 
	         System.out.println("circles="+numberOfCircles);
	         for(int i=0;i<numberOfCircles;i++) {
	             String line="";
	             line=line+"(x="+x[i]+",y="+y[i]+",r="+r[i]+",color="+color[i]+")";
	             System.out.println(line);
	         }
	   }

}
