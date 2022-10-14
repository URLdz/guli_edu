package com.url.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xidazhen
 * @date 2022/9/21 - 23:27
 */
public class TestEasyExcel {

    @Test
    public void write(){
        //设置写入文件夹地址和 excel
        String filename = "C:\\Users\\URL\\Desktop\\file.xlsx";
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
    }

    @Test
    public void read(){
        String filename = "C:\\Users\\URL\\Desktop\\file.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }


    public static List<DemoData> getData(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
