import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mvc.xml")
public class ExcelOutTest {
    @Test
    public void testOUt() throws Exception {
        //1.创建Excel文件
        WritableWorkbook workbook = Workbook.createWorkbook(new File("out.xls"));
        //2.创建Sheet(工作簿)
        WritableSheet sheet = workbook.createSheet("第一个工作簿", 0);
        //设置工作簿的单元格宽度
        sheet.setColumnView(0, 50);
        //设置工作簿的单元格高度
        sheet.setRowView(0, 500);
        //合并单元格
        sheet.mergeCells(0, 0, 5, 5);
        //创建单元格
        Label cell = new Label(0, 1, "第一个单元格");
        //4.单元格加入到sheet页中
        sheet.addCell(cell);

        //设置时间格式
        DateFormat df = new DateFormat("yyyy-MM-dd HH:mm:ss");
        WritableCellFormat fo = new WritableCellFormat(df);
        //设置单元格内容水平居中
        fo.setAlignment(Alignment.CENTRE);
        //设置单元格内容垂直居中
        fo.setVerticalAlignment(VerticalAlignment.CENTRE);
        DateTime dt = new DateTime(0, 0, new Date(), fo);
        sheet.addCell(dt);
        //5.将内容写出到文件中
        workbook.write();
        //6.关闭
        workbook.close();

    }

    @Test
    public void testRead() throws Exception {
        //获取Excel文件
        Workbook workbook = Workbook.getWorkbook(new File("out.xls"));
        //获取工作簿
        Sheet sheet = workbook.getSheet(0);
        //获取文件中的行数和列数
        int columns = sheet.getColumns();
        int rows = sheet.getRows();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                System.out.print(sheet.getCell(col, row).getContents() + "\t");
            }
            System.out.println();
        }
    }

}
