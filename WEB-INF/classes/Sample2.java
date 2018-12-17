// 286120 武田佑樹 課題9

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        try{
            // フォームデータの取得
            String carname = request.getParameter("cars");

            // コンテンツタイプの設定
            response.setContentType("text/html; charset=UTF-8");

            // HTML文書の書き出し
            PrintWriter pw = response.getWriter();
            pw.println("<!DOCTYPE html><html>\n"
                       + "<head><title>\n" + carname
                       + "</title></head>\n"
                       + "<body><div style=\"text-align: center;\">\n"
                       + "<h2>\n" + carname
                       + "</h2>\n" + carname
                       + "お買い上げありがとうございました。<br/>\n"
                       + "</div></body>\n"
                       + "</html>\n");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
