// u286120 武田佑樹 課題7(2)

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Date;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet2 extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        
        //日本語(UTF-8コード)を含むHTMLデータを返す設定にする。
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        if(hour>=6 && hour<=12){
            out.println("<p>おはよう</p>");
        } else if(hour<=17){
            out.println("<p>こんにちわ</p>");
        } else {
            out.println("<p>こんばんわ</p>");
        }
        out.println("<p>286120 武田佑樹</p>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }
}
 
