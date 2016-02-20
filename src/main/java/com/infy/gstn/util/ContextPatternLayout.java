package com.infy.gstn.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.PatternLayout;


/**
 * Context pattern layout with %h (hostname) and %u (username) variables.
 *
 * @author l0co@wp.pl
 */
public class ContextPatternLayout extends PatternLayout {

  protected String host;

  protected String getHostname() {
    if (host==null) {
      try {
        InetAddress addr = InetAddress.getLocalHost();
        this.host = addr.getHostName();
      } catch (UnknownHostException e) {
        this.host = "unknown";
      }
   }
   return host;
 }

 @Override
 protected PatternParser createPatternParser(String pattern) {
   return new PatternParser(pattern) {

     @Override
     protected void finalizeConverter(char c) {
       PatternConverter pc = null;

       switch (c) {
         case 'u':
           pc = new PatternConverter() {
             @Override
             protected String convert(LoggingEvent event) {
               return "default";
             }
           };
           break;

         case 'h':
           pc = new PatternConverter() {
             @Override
             protected String convert(LoggingEvent event) {
               return getHostname();
             }
           };
           break;
       }

       if (pc==null)
         super.finalizeConverter(c);
       else
         addConverter(pc);
     }
   };
 }

}
