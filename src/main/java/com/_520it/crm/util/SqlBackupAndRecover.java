package com._520it.crm.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class SqlBackupAndRecover {

    public static void backup(HttpServletResponse response) {
        try {

            response.setHeader("Content-Disposition", "attachment;fileName=pet.sql");

            Runtime rt = Runtime.getRuntime();

            // 调用 调用mysql的安装目录的命令
            Process child = rt
                    .exec("D:\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -h localhost -uroot -padmin pet");
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流

            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码

            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            // 要用来做导入用的sql目标文件：
            //FileOutputStream fout = new FileOutputStream("D:\\JAVA\\javaApps\\Project\\pet\\src\\main\\webapp\\static\\mysql\\pet.sql");
            ServletOutputStream fout = response.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();

            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void restore(String databaseName) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec("D:\\MySQL\\MySQL Server 5.5\\bin\\mysql.exe -hlocalhost -uroot -p123 --default-character-set=utf8 "
                            + databaseName);
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("E:\\CRM_pet\\pet\\pet\\src\\main\\webapp\\static\\mysql\\pet.sql"), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            // System.out.println(str);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                    "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
