
package com.metro;
public class ODLine {
       private int line;
       private String Onames;
       private String Dnames;
       private int accBegin;
       private int accEnd;
       private double xBegin;
       private double yBegin;
       private double xEnd;
       private double yEnd;
       private int rVal;
       private int gVal;
       private int bVal;
       
       
       public ODLine(int line, int accBegin, int accEnd) {
              super();
              this.line = line;
              this.accBegin = accBegin;
              this.accEnd = accEnd;
       }
       
       public int getLine() {
              return line;
       }
       public void setLine(int line) {
              this.line = line;
       }
       public String getOnames() {
              return Onames;
       }
       public void setOnames(String onames) {
              Onames = onames;
       }
       public String getDnames() {
              return Dnames;
       }
       public void setDnames(String dnames) {
              Dnames = dnames;
       }
       public int getAccBegin() {
              return accBegin;
       }
       public void setAccBegin(int accBegin) {
              this.accBegin = accBegin;
       }
       public int getAccEnd() {
              return accEnd;
       }
       public void setAccEnd(int accEnd) {
              this.accEnd = accEnd;
       }
       public double getxBegin() {
              return xBegin;
       }
       public void setxBegin(double xBegin) {
              this.xBegin = xBegin;
       }
       public double getyBegin() {
              return yBegin;
       }
       public void setyBegin(double yBegin) {
              this.yBegin = yBegin;
       }
       public double getxEnd() {
              return xEnd;
       }
       public void setxEnd(double xEnd) {
              this.xEnd = xEnd;
       }
       public double getyEnd() {
              return yEnd;
       }
       public void setyEnd(double yEnd) {
              this.yEnd = yEnd;
       }
       public int getrVal() {
              return rVal;
       }
       public void setrVal(int rVal) {
              this.rVal = rVal;
       }
       public int getgVal() {
              return gVal;
       }
       public void setgVal(int gVal) {
              this.gVal = gVal;
       }
       public int getbVal() {
              return bVal;
       }
       public void setbVal(int bVal) {
              this.bVal = bVal;
       }
       
       
}