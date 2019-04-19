package com.marlabs.xml;

import java.io.*;
import org.w3c.dom.*;
import org.apache.xerces.parsers.DOMParser;

public class DOMExample {
	
	static int numberOfCircles = 0;   // total number of circles seen
	   static int x[] = new int[1000];   // X-coordinates of the centers
	   static int y[] = new int[1000];   // Y-coordinates of the centers  
	   static int r[] = new int[1000];   // radius of the circle
	   static String color[] = new String[1000];  // colors of the circles 

	   public static void main(String[] args) {   

	      try{
	         // create a DOMParser
	         DOMParser parser=new DOMParser();
	         parser.parse("src/data.xml");

	         // get the DOM Document object
	         Document doc=parser.getDocument();

	         // get all the circle nodes
	         NodeList nodelist = doc.getElementsByTagName("circle");
	         numberOfCircles =  nodelist.getLength();

	         // retrieve all info about the circles
	         for(int i=0; i<nodelist.getLength(); i++) {

	            // get one circle node
	            Node node = nodelist.item(i);
	  
	            // get the color attribute 
	            NamedNodeMap attrs = node.getAttributes();
	            if(attrs.getLength() > 0)
	               color[i]=(String)attrs.getNamedItem("color").getNodeValue();

	            // get the child nodes of a circle node 
	            NodeList childnodelist = node.getChildNodes();

	            // get the x and y value 
	            for(int j=0; j<childnodelist.getLength(); j++) {
	               Node childnode = childnodelist.item(j);
	               Node textnode = childnode.getFirstChild();//the only text node
	               String childnodename=childnode.getNodeName(); 
	               if(childnodename.equals("x")) 
	                  x[i]= Integer.parseInt(textnode.getNodeValue().trim());
	               else if(childnodename.equals("y")) 
	                  y[i]= Integer.parseInt(textnode.getNodeValue().trim());
	               else if(childnodename.equals("radius")) 
	                  r[i]= Integer.parseInt(textnode.getNodeValue().trim());
	            }

	         }
	         
	         // print the result
	         System.out.println("circles="+numberOfCircles);
	         for(int i=0;i<numberOfCircles;i++) {
	             String line="";
	             line=line+"(x="+x[i]+",y="+y[i]+",r="+r[i]+",color="+color[i]+")";
	             System.out.println(line);
	         }

	      }  catch (Exception e) {e.printStackTrace(System.err);}
	   
	    }

}
