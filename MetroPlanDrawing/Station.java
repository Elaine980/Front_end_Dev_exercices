
package com.metro;
public class Station {
       private int stationIndex;
       private String stationName;
       private String stationPinyin;
       private int lineIndex;
       private int accNum;
       private double longitude;
       private double latitude;
       private double xStation;
       private double yStation;
       
       public Station(){
              super();
       }
   
       
       
       public Station(int stationIndex, String stationName, int lineIndex, int accNum, double longitude, double latitude) {
              super();
              this.stationIndex = stationIndex;
              this.stationName = stationName;
              this.lineIndex = lineIndex;
              this.accNum = accNum;
              this.longitude = longitude;
              this.latitude = latitude;
       }
       public int getStationIndex() {
              return stationIndex;
       }
       public void setStationIndex(int stationIndex) {
              this.stationIndex = stationIndex;
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
       public double getxStation() {
              return xStation;
       }
       public void setxStation(double xStation) {
              this.xStation = xStation;
       }
       public double getyStation() {
              return yStation;
       }
       public void setyStation(double yStation) {
              this.yStation = yStation;
       }
       
       
       
}