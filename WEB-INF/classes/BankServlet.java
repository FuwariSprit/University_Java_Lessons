// 286120 武田佑樹 課題10  
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BankServlet extends HttpServlet {
    
    // 口座の管理をするオブジェクト
    private SimpleBank bank;
    private int jadge;
    
    public BankServlet(){
        bank = new SimpleBank();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	// 処理の種類の取得
	String cmdType = request.getParameter("command");

	// フォームデータの取得
	String bankName = request.getParameter("name");
	String bankAmount = request.getParameter("amount");
	
	// コンテンツタイプの設定                                              
	response.setContentType("text/html; charset=UTF-8");
	
	// HTML文書の書き出し
	PrintWriter pw = response.getWriter();

	pw.println("<!DOCTYPE html>\n"
		   + "<html>\n"
		   + "<head>\n"
		   + "<title>意識高い銀行</title>\n"
		   + "<link rel='stylesheet' href='css/style.css'>\n"
		   + "</head>\n");
	
	if(cmdType.equals("open")){
	    openBank(bankName, pw);
	}
	if(cmdType.equals("close")){
	    closeBank(bankName, pw);
	}
       	if(cmdType.equals("deposit")){
	    deposit(bankName, bankAmount, pw);
	}
	if(cmdType.equals("withdraw")){
	    withdraw(bankName, bankAmount, pw);
	}
	if(cmdType.equals("balance")){
	    showBalance(bankName, pw);
	}
	
    }
    
    // 口座開設関連処理
    private void openBank(String bankName, PrintWriter pw){
	       
	// 口座開設処理
	jadge = bank.open(bankName);
	
	if(jadge == 0){
	    // 口座開設成功のHTML生成
	    pw.println("<body>\n"
		       + "<h1>当店をご利用いただきありがとうございます。</h1>\n"
		       + "<p>" + bankName + "様の口座を開設しました。</p>\n"
		       + "<form action='index.html'>\n"
		       + "<input type='submit' value='トップメニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	} else {
	    // 口座開設失敗のHTML生成
	    pw.println("<body>\n"
		       + "<h1>口座の開設ができませんでした。</h1>\n"
		       + "<p>" + bankName + "様と同名の口座がすでに存在しています。</p>\n"
		       + "<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	}
    }

    // 口座解約関連処理
    private void closeBank(String bankName, PrintWriter pw){
	
	// 口座解約処理
	jadge = bank.close(bankName);
	
	if(jadge == 0){
	    // 口座開設成功のHTML生成
	    pw.println("<body>\n"
		       + "<h1>今までご利用頂きありがとうございました。</h1>\n"
		       + "<p>" + bankName + "様の口座を解約しました。</p>\n"
		       + "<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	} else {
	    // 口座開設失敗のHTML生成
	    pw.println("<body>\n"
		       + "<h1>口座の解約ができませんでした。</h1>\n");
	    if(jadge == -7){
		pw.println("<p>" + bankName + "様の口座は存在しません。</p>\n");
	    }
	    if(jadge == -1){
		pw.println("<p>" + bankName + "様の口座には残高が残っています。</p>\n");
	    }
	    pw.println("<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	}	
    }

    // 預金関連処理
    private void deposit(String bankName, String bankAmount, PrintWriter pw){
	
	// 預金処理
	jadge = bank.deposit(bankName, bankAmount);
	
	if(jadge == 0){
	    // 預金成功のHTML生成
	    pw.println("<body>\n"
		       + "<h1>本日もご利用頂きありがとうございます。</h1>\n"
		       + "<p>" + bankName + "様の口座に" + bankAmount + "円を預入れました。</p>\n"
		       + "<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	} else {
	    // 預金失敗のHTML生成
	    pw.println("<body>\n"
		       + "<h1>預入れができませんでした。</h1>\n");
	    if(jadge == -7){
		pw.println("<p>" + bankName + "様の口座は存在しません。</p>\n");
	    }
	    if(jadge == -4){
		pw.println("<p>整数以外の値が入力されています。</p>\n");
	    }
	    if(jadge == -3){
		pw.println("<p>0円以下は預入れできません。</p>\n");
	    }
	    pw.println("<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	}	
    }

    // 引き出し関連処理
    private void withdraw(String bankName, String bankAmount, PrintWriter pw){
	
	// 引き出し処理
	jadge = bank.withdraw(bankName, bankAmount);
	
	if(jadge == 0){
	    // 引き出し成功のHTML生成
	    pw.println("<body>\n"
		       + "<h1>本日もご利用頂きありがとうございます。</h1>\n"
		       + "<p>" + bankName + "様の口座から" + bankAmount + "円を引き出しました。</p>\n"
		       + "<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	} else {
	    // 引き出し失敗のHTML生成
	    pw.println("<body>\n"
		       + "<h1>お引き出しできませんでした。</h1>\n");
	    if(jadge == -7){
		pw.println("<p>" + bankName + "様の口座は存在しません。</p>\n");
	    }
	    if(jadge == -4){
		pw.println("<p>整数以外の値が入力されています。</p>\n");
	    }
	    if(jadge == -3){
		pw.println("<p>0円以下は引き出しできません。</p>\n");
	    }
	    if(jadge == -1){
		pw.println("<p>預金残高を超えての引き出しはできません。</p>\n");
	    }
	    pw.println("<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	}	
    }

    // 残高照会関連処理
    private void showBalance(String bankName, PrintWriter pw){
	       
	// 残高照会処理
	jadge = bank.showBalance(bankName);
	
	if(jadge != -7){
	    // 残高照会成功のHTML生成
	    pw.println("<body>\n"
		       + "<h1>当店をご利用いただきありがとうございます。</h1>\n"
		       + "<p>" + bankName + "様の口座の残高は" + jadge + "円です。</p>\n"
		       + "<form action='index.html'>\n"
		       + "<input type='submit' value='トップメニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	} else {
	    // 残高照会失敗のHTML生成
	    pw.println("<body>\n"
		       + "<h1>残高照会ができませんでした。</h1>\n"
		       + "<p>" + bankName + "様の口座は存在しません。</p>\n"
		       + "<form action='index.html'>\n"
		       + "<input type='submit' value='メニューへ戻る'>\n"
		       + "</form>\n"
		       + "</body>\n"
		       + "</html>");
	}
    }
    
}
