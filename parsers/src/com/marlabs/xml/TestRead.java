package com.marlabs.xml;

import java.util.List;

public class TestRead {
  public static void main(String args[]) {
    StaXParser read = new StaXParser();
    List<Item> readConfig = read.readConfig("src/config.xml");
    for (Item item : readConfig) {
      System.out.println(item);
    }
  }
}