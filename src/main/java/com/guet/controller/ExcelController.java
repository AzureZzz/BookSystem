package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.BookExcelRow;
import com.guet.entity.BookInfoRow;
import com.guet.entity.Borrow;
import com.guet.entity.ReaderRow;
import com.guet.service.*;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("download")
public class ExcelController {
    
    @Autowired
    private IBookService bookService;
    
    @Autowired
    private IBorrowService borrowService;
    
    @Autowired
    private IBookInfoService bookInfoService;
    
    @Autowired
    private IReaderService readerService;
    
    @Autowired
    private IReserveService reserveService;

    @AuthAdmin
    @RequestMapping(value = "borrow",method = RequestMethod.GET)
    public void downloadBorrowExl(HttpServletResponse response, HttpServletRequest request) {
        // 创建工作表
        WritableWorkbook book = null;
        response.reset();
        // 创建工作流
        OutputStream os = null;
        try {
            // 设置弹出对话框
            response.setContentType("application/DOWNLOAD");
            // 设置工作表的标题
            response.setHeader("Content-Disposition", "attachment; filename=download.xls");//设置生成的文件名字
            os = response.getOutputStream();
            // 初始化工作表
            book = Workbook.createWorkbook(os);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            List<Borrow> borrows = borrowService.getExcelBorrows();
            WritableSheet sheet = book.createSheet("借阅记录报表", 0);
            // 表字段名
            sheet.addCell(new jxl.write.Label(0, 0, "图书编号"));
            sheet.addCell(new jxl.write.Label(1, 0, "读者"));
            sheet.addCell(new jxl.write.Label(2, 0, "借阅时间"));
            sheet.addCell(new jxl.write.Label(3, 0, "归还时间"));
            sheet.addCell(new jxl.write.Label(4, 0, "续借次数"));
            sheet.addCell(new jxl.write.Label(5, 0, "借阅状态"));
            // 添加数据
            for (int i = 0; i < borrows.size(); i++) {
                Borrow row = borrows.get(i);
                sheet.addCell(new jxl.write.Label(0, i + 1, row.getBookNo()));
                sheet.addCell(new jxl.write.Label(1, i + 1, row.getUsername()));
                sheet.addCell(new jxl.write.Label(2, i + 1, row.getBorTime().toString()));
                sheet.addCell(new jxl.write.Label(3, i + 1, row.getRetTime().toString()));
                sheet.addCell(new jxl.write.Label(4, i + 1, row.getReNew()+""));
                if(row.getState()==0){
                    sheet.addCell(new jxl.write.Label(5, i + 1, "借阅中"));
                }
                if(row.getState() == 1){
                    sheet.addCell(new jxl.write.Label(5, i + 1, "已归还"));
                }
            }
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AuthAdmin
    @RequestMapping(value = "reader",method = RequestMethod.GET)
    public void downloadReaderExl(HttpServletResponse response, HttpServletRequest request) {
        // 创建工作表
        WritableWorkbook book = null;
        response.reset();
        // 创建工作流
        OutputStream os = null;
        try {
            // 设置弹出对话框
            response.setContentType("application/DOWNLOAD");
            // 设置工作表的标题
            response.setHeader("Content-Disposition", "attachment; filename=download.xls");//设置生成的文件名字
            os = response.getOutputStream();
            // 初始化工作表
            book = Workbook.createWorkbook(os);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            List<ReaderRow> rows = readerService.getExcelReaders();
            WritableSheet sheet = book.createSheet("读者报表", 0);
            // 表字段名
            sheet.addCell(new jxl.write.Label(0, 0, "读者账户名"));
            sheet.addCell(new jxl.write.Label(1, 0, "姓名"));
            sheet.addCell(new jxl.write.Label(2, 0, "类型"));
            sheet.addCell(new jxl.write.Label(3, 0, "邮箱"));
            sheet.addCell(new jxl.write.Label(4, 0, "借阅次数"));
            sheet.addCell(new jxl.write.Label(5, 0, "未还数量"));
            sheet.addCell(new jxl.write.Label(6, 0, "超期数量"));
            // 添加数据
            for (int i = 0; i < rows.size(); i++) {
                ReaderRow row = rows.get(i);
                sheet.addCell(new jxl.write.Label(0, i + 1, row.getUsername()));
                sheet.addCell(new jxl.write.Label(1, i + 1, row.getName()));
                if(row.getType()==0){
                    sheet.addCell(new jxl.write.Label(2, i + 1, "本科生"));
                }
                if(row.getType()==1){
                    sheet.addCell(new jxl.write.Label(2, i + 1, "研究生"));
                }
                if(row.getType()==2){
                    sheet.addCell(new jxl.write.Label(2, i + 1, "老师"));
                }
                sheet.addCell(new jxl.write.Label(3, i + 1, row.getEmail()));
                sheet.addCell(new jxl.write.Label(4, i + 1, row.getBorrowNum()+""));
                sheet.addCell(new jxl.write.Label(5, i + 1, row.getUnReturn()+""));
                sheet.addCell(new jxl.write.Label(6, i + 1, row.getOverNum()+""));
            }
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AuthAdmin
    @RequestMapping(value = "book",method = RequestMethod.GET)
    public void downloadExl(HttpServletResponse response, HttpServletRequest request) {
        // 创建工作表
        WritableWorkbook book = null;
        response.reset();
        // 创建工作流
        OutputStream os = null;
        try {
            // 设置弹出对话框
            response.setContentType("application/DOWNLOAD");
            // 设置工作表的标题
            response.setHeader("Content-Disposition", "attachment; filename=download.xls");//设置生成的文件名字
            os = response.getOutputStream();
            // 初始化工作表
            book = Workbook.createWorkbook(os);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            List<BookExcelRow> rows = bookInfoService.getExcelBooks();
            WritableSheet sheet = book.createSheet("保险单表", 0);
            // 表字段名
            sheet.addCell(new jxl.write.Label(0, 0, "索书号"));
            sheet.addCell(new jxl.write.Label(1, 0, "图书名称"));
            sheet.addCell(new jxl.write.Label(2, 0, "作者"));
            sheet.addCell(new jxl.write.Label(3, 0, "出版社"));
            sheet.addCell(new jxl.write.Label(4, 0, "出版时间"));
            sheet.addCell(new jxl.write.Label(5, 0, "页数"));
            sheet.addCell(new jxl.write.Label(6, 0, "价格"));
            sheet.addCell(new jxl.write.Label(7, 0, "ISBN"));
            sheet.addCell(new jxl.write.Label(8, 0, "馆藏总数"));
            sheet.addCell(new jxl.write.Label(9, 0, "剩余数量"));
            sheet.addCell(new jxl.write.Label(10, 0, "借阅次数"));
            sheet.addCell(new jxl.write.Label(11, 0, "收藏次数"));
            // 添加数据
            for (int i = 0; i < rows.size(); i++) {
                BookExcelRow row = rows.get(i);
                sheet.addCell(new jxl.write.Label(0, i + 1, row.getCallNum()));
                sheet.addCell(new jxl.write.Label(1, i + 1, row.getName()));
                sheet.addCell(new jxl.write.Label(2, i + 1, row.getWriter()));
                sheet.addCell(new jxl.write.Label(3, i + 1, row.getPress()));
                if(row.getPubTime() !=null){
                    sheet.addCell(new jxl.write.Label(4, i + 1, row.getPubTime().toString()));
                }else{
                    sheet.addCell(new jxl.write.Label(4, i + 1, ""));
                }
                sheet.addCell(new jxl.write.Label(5, i + 1, row.getPageNum()+""));
                sheet.addCell(new jxl.write.Label(6, i + 1, row.getPiece()+""));
                sheet.addCell(new jxl.write.Label(7, i + 1, row.getIsbn()));
                sheet.addCell(new jxl.write.Label(8, i + 1, row.getTotal() + ""));
                sheet.addCell(new jxl.write.Label(9, i + 1, row.getRemain() + ""));
                sheet.addCell(new jxl.write.Label(10, i + 1, row.getBorrowNum() + ""));
                sheet.addCell(new jxl.write.Label(11, i + 1, row.getCollectNum() + ""));
            }
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
