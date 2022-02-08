package com.company.khang;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class thongke {
     thongke() {
         try {
             String query = "select sanpham,soluong from doanhthusanpham";
             JDBCCategoryDataset dataset = new JDBCCategoryDataset(DBConnectionFactory.getconection(),query);
             JFreeChart barChart=ChartFactory.createBarChart("Biểu Đồ Thống Kê Sản Phẩm ","Sản Phẩm","Số Lượng",dataset,PlotOrientation.VERTICAL,true,true,true);


             ChartPanel doanhsosanpham = new ChartPanel(barChart);
             doanhsosanpham.setPreferredSize(new Dimension(500,500));

             String query2 = "select ngayban,thanhtien from doanhthukhachhang";
             JDBCCategoryDataset dataset2 = new JDBCCategoryDataset(DBConnectionFactory.getconection(),query2);
             JFreeChart lineChart=ChartFactory.createLineChart("Biểu Đồ Thống Kê Doanh Số Khách Hàng ","Ngày","Doanh Số",dataset2,PlotOrientation.VERTICAL,true,true,true);


             ChartPanel doanhsokhachhang = new ChartPanel(lineChart);
             doanhsokhachhang.setPreferredSize(new Dimension(500,500));

             JFrame frame=new JFrame("Biểu Đồ Thống Kê Doanh số");
             frame.setLayout(new FlowLayout());
             frame.add(doanhsosanpham);
             frame.add(doanhsokhachhang);
             frame.setSize(1200, 600);
             frame.setVisible(true);
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
     }
}
