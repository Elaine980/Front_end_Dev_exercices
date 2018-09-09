
package com.metro;

public class TopoStation {
       private int stationIndex;
       private int lineIndex;
       private int accNum;
       private int xCoord;
       private int yCoord;
       private int xName;
       private int yName;
       private int xPinyin;
       private int yPinyin;
       private double longitude;
       private double latitude;
       private String stationName;
       private String stationPinyin;

       public TopoStation(){
              super();
       }

       public TopoStation(String name, String pinyin, int index, int acc, int xc, int yc, int xn, int yn){
              super();
              this.stationName = name;
              this.stationPinyin = pinyin;
              this.stationIndex = index;
              this.accNum = acc;
              this.xCoord = xc;
              this.yCoord = yc;
              this.xName = xn;
              this.yName = yn;
       }

       public int getStationIndex() {
              return stationIndex;
       }

       public void setStationIndex(int stationIndex) {
              this.stationIndex = stationIndex;
       }

       public int getLineIndex() {
              return lineIndex;
       }

       public void setLineIndex(int lineIndex) {
              this.lineIndex = lineIndex;
       }

       public int getAccNum() {
              return accNum;
       }

       public void setAccNum(int accNum) {
              this.accNum = accNum;
       }

       public int getxCoord() {
              return xCoord;
       }

       public void setxCoord(int xCoord) {
              this.xCoord = xCoord;
       }

       public int getyCoord() {
              return yCoord;
       }

       public void setyCoord(int yCoord) {
              this.yCoord = yCoord;
       }

       public int getxName() {
              return xName;
       }

       public void setxName(int xName) {
              this.xName = xName;
       }

       public int getyName() {
              return yName;
       }

       public void setyName(int yName) {
              this.yName = yName;
       }

       public int getxPinyin() {
              return xPinyin;
       }

       public void setxPinyin(int xPinyin) {
              this.xPinyin = xPinyin;
       }

       public int getyPinyin() {
              return yPinyin;
       }

       public void setyPinyin(int yPinyin) {
              this.yPinyin = yPinyin;
       }

       public double getLongitude() {
              return longitude;
       }

       public void setLongitude(double longitude) {
              this.longitude = longitude;
       }

       public double getLatitude() {
              return latitude;
       }

       public void setLatitude(double latitude) {
              this.latitude = latitude;
       }

       public String getStationName() {
              return stationName;
       }

       public void setStationName(String stationName) {
              this.stationName = stationName;
       }

       public String getStationPinyin() {
              return stationPinyin;
       }

       public void setStationPinyin(String stationPinyin) {
              this.stationPinyin = stationPinyin;
       }



}
