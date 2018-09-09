
package com.metro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class MetroServlet
 */
@WebServlet("/MetroServlet")
public class MetroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static double station_radius = 3;
    public static double plan_height = 2000;
    public static double plan_width = 2000;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetroServlet() {
        super();
    }

    public static double[] millierConvert(double lat, double lon) 
    { 
         double L = 6381372 * Math.PI * 2;//地球周长 
         double W=L;// 平面展开后，x轴等于周长 
         double H=L/2;// y轴约等于周长一半 
         double mill=2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间 
         double x = lon * Math.PI / 180;// 将经度从度数转换为弧度 
         double y = lat * Math.PI / 180;// 将纬度从度数转换为弧度 
         y=1.25 * Math.log( Math.tan( 0.25 * Math.PI + 0.4 * y ) );// 米勒投影的转换
         // 弧度转为实际距离 
         x = ( W / 2 ) + ( W / (2 * Math.PI) ) * x; 
         y = ( H / 2 ) - ( H / ( 2 * mill ) ) * y; 
         double[] result = new double[2]; 
         result[0]=x; 
         result[1]=y; 
         return result; 
    }

    public boolean contains(List<Station> ss, Station s){
        for(Station m: ss){
            if(m.getStationIndex() == s.getStationIndex())
                return true;
        }
        return false;
    }

    public void ttranslates(ODLine l){
       double x1 = l.getxBegin();
       double y1 = l.getyBegin();
       double x2 = l.getxEnd();
       double y2 = l.getyEnd();

       if(x1 == x2){
              x1 -= station_radius;
              x2 -= station_radius;

       }else if(y1 == y2){
              y1 -= station_radius;
              y2 -= station_radius;

       }else{
              double k = (y1 - y2)/(x1 - x2);
              double alpha = Math.atan(-1 / k);
              x1 -= station_radius * Math.cos(alpha);
              y1 -= station_radius * Math.sin(alpha);
              x2 -= station_radius * Math.cos(alpha);
              y2 -= station_radius * Math.sin(alpha);
       }

       l.setxBegin(x1);
       l.setyBegin(y1);
       l.setxEnd(x2);
       l.setyEnd(y2);
    }
    

    public void translates(ODLine l){
        double x1 = l.getxBegin();
        double y1 = l.getyBegin();
        double x2 = l.getxEnd();
        double y2 = l.getyEnd();

        if(x1 == x2){
            x1 += station_radius;
            x2 += station_radius;

        }else if(y1 == y2){
            y1 += station_radius;
            y2 += station_radius;

        }else{
            double k = (y1 - y2)/(x1 - x2);
            double alpha = Math.atan(-1 / k);
            x1 += station_radius * Math.cos(alpha);
            y1 += station_radius * Math.sin(alpha);
            x2 += station_radius * Math.cos(alpha);
            y2 += station_radius * Math.sin(alpha);
        }

        l.setxBegin(x1);
        l.setyBegin(y1);
        l.setxEnd(x2);
        l.setyEnd(y2);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        Station s = new Station();
        List<Station> stations = new ArrayList<Station>();
        List<ODLine> lines = new ArrayList<ODLine>();
        List<LineColor> colors = new ArrayList<LineColor>();

        Hashtable<Integer,Integer> sCodes = new Hashtable<Integer,Integer>();

        try{
           Class.forName("com.mysql.jdbc.Driver");
           String strUrl = "jdbc:mysql://localhost:3306/metro";
           Connection conn = (Connection) DriverManager.getConnection(strUrl, "root", "12241083");
           Statement st= (Statement) conn.createStatement();

           ResultSet rs1=st.executeQuery("select * from stationinfo_v25");
           while(rs1!=null && rs1.next())
           {   
               //column 1 is the column stationindex
               s = new Station(rs1.getInt(1),rs1.getString("stationname"),rs1.getInt("lineindex"),rs1.getInt("ACCnum"),rs1.getDouble("lon"),rs1.getDouble("lat"));
               if(!contains(stations,s)){
                   stations.add(s);
               }

           }

           ResultSet rs2=st.executeQuery("select * from interdist2016");
           while(rs2!=null && rs2.next())
           {
               lines.add(new ODLine(rs2.getInt("LINE_INDEX"),rs2.getInt("O_STATION_ACCCODE"),rs2.getInt("D_STATION_ACCCODE")));   
           }

           ResultSet rs3=st.executeQuery("select * from linecolor");
           while(rs3!=null && rs3.next())
           {
               colors.add(new LineColor(rs3.getInt("line"),rs3.getInt("R"),rs3.getInt("G"),rs3.getInt("B")));
           }

           ResultSet rs4=st.executeQuery("select * from stationcode");
           while(rs4!=null && rs4.next())
           {
               //Key: ACCnum code; Value: Station index code
               // column 1 is the column "STATION_CODE"
               sCodes.put(rs4.getInt("ACCCODE"), rs4.getInt(1));
               //System.out.println(sCodes.get(rs4.getInt("ACCCODE")));
           }

         }
         catch (Exception e)
         {
               response.getWriter().print("错误"+e);
               e.printStackTrace();
         }

        //经纬度坐标转化为屏幕坐标
        double minLat = stations.get(0).getLatitude();
        double maxLat = stations.get(0).getLatitude();
        double minLon = stations.get(0).getLongitude();
        double maxLon = stations.get(0).getLongitude();
        for(int i=0; i<stations.size(); i++){
           
            if(stations.get(i).getLatitude() < minLat)   minLat = stations.get(i).getLatitude();
            if(stations.get(i).getLatitude() > maxLat)   maxLat = stations.get(i).getLatitude();
            if(stations.get(i).getLongitude() < minLon)   minLon = stations.get(i).getLongitude();
            if(stations.get(i).getLongitude() > maxLon)   maxLon = stations.get(i).getLongitude();
        }

        double scaleX = (maxLon - minLon) * 3600 / (0.9 * plan_width);
        double scaleY = (maxLat - minLat) * 3600 / (0.9 * plan_height);
        //经纬度左边转化为屏幕画布坐标
        double[] xStations = new double[stations.size()];
        double[] yStations = new double[stations.size()];
        //横纵坐标都平移10个像素点使站点不被画在坐标轴上
        //经纬度坐标拉伸比例为3:4     
        for(int i=0; i<stations.size(); i++){
            xStations[i] = 10 + (stations.get(i).getLongitude() - minLon) * 3600 / scaleX;
            yStations[i] = (10 + (maxLat - stations.get(i).getLatitude()) * 3600 / scaleY) * 3/4;
            stations.get(i).setxStation(xStations[i]);
            stations.get(i).setyStation(yStations[i]);

        }

        //设置起止点坐标
        for(int i=0; i<lines.size(); i++){
            for(int j=0; j<stations.size(); j++){
                if(sCodes.get(lines.get(i).getAccBegin()) == stations.get(j).getStationIndex()){
                    lines.get(i).setxBegin(xStations[j]);
                    lines.get(i).setyBegin(yStations[j]);

                }
                if(sCodes.get(lines.get(i).getAccEnd()) == stations.get(j).getStationIndex()){
                    lines.get(i).setxEnd(xStations[j]);
                    lines.get(i).setyEnd(yStations[j]);
                                    }
                }
        }

        //检查双向站点
        for(int i=0; i<lines.size(); i++){
            for(int j=i+1; j<lines.size(); j++){
                if(lines.get(j).getAccBegin() == lines.get(i).getAccEnd() && lines.get(j).getAccEnd() == lines.get(i).getAccBegin()){
                      ttranslates(lines.get(i));
                      translates(lines.get(j));
                      continue;
                }
            }
        }

        //设置颜色
        for(int i=0; i<lines.size(); i++){
            for(int j=0; j<colors.size(); j++){
                if(lines.get(i).getLine() == colors.get(j).getLine()){

                    lines.get(i).setrVal(colors.get(j).getrValue());
                    lines.get(i).setgVal(colors.get(j).getgValue());
                    lines.get(i).setbVal(colors.get(j).getbValue());
                }
            }
        }

        JSONArray json0 = JSONArray.fromObject(stations);
        JSONArray json1 = JSONArray.fromObject(lines);

         List<JSONArray> json = new ArrayList<JSONArray>();
         json.add(json0);
         json.add(json1);
         out.println(json);
         out.flush();

    }

}

