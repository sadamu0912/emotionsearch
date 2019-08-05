//package com.xjxspace.util;
//
//import org.jumpmind.symmetric.csv.CsvReader;
//import org.jumpmind.symmetric.csv.CsvWriter;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CSVHandler {
//    public String inPath="E:\\departments.csv";
//    public String outPath="D:\\网点大全.csv";
//    public static  ArrayList<String []> list = new ArrayList<String[]>();
//    private void CheckandCreateFile(){
//
//        File file=new File(outPath);
//        try{
//            if(!file.exists()){
//                file.createNewFile();
//                System.out.println("文件不存在，新建成功！");
//            }
//            else{
//                System.out.println("文件存在！");
//            }
//        }catch( Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public static List<Map<String,Object>> readCSV(InputStream is) throws IOException {
//        CsvReader reader = new CsvReader(is,',', Charset.forName("gb2312"));
//        List<Map<String,Object>> list= new ArrayList<>();
//        reader.readHeaders();
//        String[] headers = reader.getHeaders();
//        validateHeader(headers);
//        try{
//            while(reader.readRecord()) {
//                String[] datas = reader.getValues();
//                Map bean = new HashMap<String,Object>();
//                for(int i=0;i<headers.length;i++){
//                    bean.put(headers[i].trim(),datas[i]);
//                }
//                list.add(bean);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            {
//                reader.close();
//            }
//        }
//     return list;
//    }
//
//    private static boolean validateHeader(String[] headers){
//       return headers!=null;
//    }
//    public void WriteCSV()	throws IOException{
//        CSVHandler handler=new CSVHandler();
//        handler.CheckandCreateFile();
//        CsvWriter wr = new CsvWriter(outPath,',', Charset.forName("gb2312"));
//        String[] header = { "Name","Province","City","Address","Tel","Website","Server_content","Jigou_cengji","Type","Parent_level1","Parent_level2","Branch_level" };
//        wr.writeRecord(header);
//        for(int i=0;i<list.size();i++)
//        {
//            String[] Data= list.get(i);
//            wr.writeRecord(Data);
//        }
//        wr.close();
//    }
//}
