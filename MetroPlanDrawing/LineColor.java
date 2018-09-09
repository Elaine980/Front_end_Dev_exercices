
package com.metro;

public class LineColor {

       private int line;
       private int rValue;
       private int gValue;
       private int bValue;

       public LineColor(int line, int rValue, int gValue, int bValue) {
              super();
              this.line = line;
              this.rValue = rValue;
              this.gValue = gValue;
              this.bValue = bValue;
       }

       public int getLine() {
              return line;
       }

       public void setLine(int line) {
              this.line = line;
       }

       public int getrValue() {
              return rValue;
       }

       public void setrValue(int rValue) {
              this.rValue = rValue;
       }

       public int getgValue() {
              return gValue;
       }

       public void setgValue(int gValue) {
              this.gValue = gValue;
       }

       public int getbValue() {
              return bValue;
       }

       public void setbValue(int bValue) {
              this.bValue = bValue;
       }



}
