
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
 * Servlet implementation class TopoServlet
 */
@WebServlet("/TopoServlet")
public class TopoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static double station_radius = 3;
    public static double plan_height = 1920;
    public static double plan_width = 1080;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public boolean contains(List<Station> ss, Station s){
        for(Station m: ss){
            if(m.getStationIndex() == s.getStationIndex())
                return true;
        }
        return false;
    }

    public void firstTranslatedLine(ODLine l){
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

    public void secdTranslatedLine(ODLine l){
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
        List<TopoStation> topostations = new ArrayList<TopoStation>();
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

           ResultSet rs5=st.executeQuery("select * from topo_stationinfo");
           while(rs5!=null && rs5.next()){
               topostations.add(new TopoStation(rs5.getString("stationname"),rs5.getString("stationpinyin"),rs5.getInt("stationindex"),rs5.getInt("ACCnum"),rs5.getInt("coordinatex"),rs5.getInt("coordinatey"),rs5.getInt("stationnamex"),rs5.getInt("stationnamey")));
           }

         }
         catch (Exception e)
         {
               response.getWriter().print("错误"+e);
               e.printStackTrace();
         }

        //获取拓扑图站点坐标
        for(int i=0; i<stations.size(); i++){
            for(int j=0; j<topostations.size(); j++){
                if(stations.get(i).getAccNum() == topostations.get(j).getAccNum()){
                    stations.get(i).setxStation(topostations.get(j).getxCoord());
                    stations.get(i).setyStation(topostations.get(j).getyCoord());
                    stations.get(i).setStationPinyin(topostations.get(j).getStationPinyin());
                    //System.out.println(stations.get(i).getxStation());
                }
            }
        }

        //设置画线起止点坐标
        for(int i=0; i<lines.size(); i++){
            for(int j=0; j<stations.size(); j++){
                if(sCodes.get(lines.get(i).getAccBegin()) == stations.get(j).getStationIndex()){
                    lines.get(i).setxBegin(stations.get(j).getxStation());
                    lines.get(i).setyBegin(stations.get(j).getyStation());
                    //System.out.println(lines.get(i).getxBegin());

                }
                if(sCodes.get(lines.get(i).getAccEnd()) == stations.get(j).getStationIndex()){
                    lines.get(i).setxEnd(stations.get(j).getxStation());
                    lines.get(i).setyEnd(stations.get(j).getyStation());
                                    }
                }
        }

        //检查双向站点
        for(int i=0; i<lines.size(); i++){
            for(int j=i+1; j<lines.size(); j++){
                if(lines.get(j).getAccBegin() == lines.get(i).getAccEnd() && lines.get(j).getAccEnd() == lines.get(i).getAccBegin()){
                    firstTranslatedLine(lines.get(i));
                    secdTranslatedLine(lines.get(j));
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
         out.println(json);//返给ajax
         out.flush();

    }
}

