package fuzzer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class Fuzzer {
	
	static ArrayList<String> commands;

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, AWTException {
		WebClient webClient = new WebClient();
		webClient.setJavaScriptEnabled(true);
		
		commands = new ArrayList<String>();
		
		String helpString = "Help - gives information about actions for Fuzzer";
		commands.add(helpString);
		
		//for(String arg:args){}
		
		//discoverLinks(webClient);
		//guessLinks(webClient);
		auth(webClient);
		
		webClient.closeAllWindows();

	}
	
	public static List<HtmlAnchor> discoverLinks(WebClient webClient) throws IOException, MalformedURLException{
		
		HtmlPage defaultpage = webClient.getPage("http://localhost:8080/bodgeit");
		
		List<HtmlAnchor> links = defaultpage.getAnchors();
		for(HtmlAnchor link : links){
			System.out.println("Link discovered: " + link.asText() + " @URL=" + link.getHrefAttribute());
		}
		
		System.out.println("--------------------" + "Default Page" + "-------------------------");
		
		for(HtmlAnchor link : links){
			
			if(link.getHrefAttribute().contains("typeid"))
			
			if(link.getHrefAttribute().contains("typeid")){
				HtmlPage subPage = webClient.getPage("http://localhost:8080/bodgeit/" + link.getHrefAttribute());
				List<HtmlAnchor> moreLinks = subPage.getAnchors();
				for(HtmlAnchor linkInSubpage : moreLinks){
					if(linkInSubpage.getHrefAttribute().contains("prodid")){
						System.out.println("Link discovered " + linkInSubpage.asText() +" @URL=" + linkInSubpage.getHrefAttribute());
					}
				}
				System.out.println("--------------------" + link.asText() + "-------------------------");
			}
		}
		
		return links;
	}
	
	public static void guessLinks(WebClient webClient) throws IOException, MalformedURLException{
		//TODO
	}
	
	public static void auth(WebClient webClient) throws FailingHttpStatusCodeException, MalformedURLException, IOException, AWTException{ //assume bodgeit
		
		HtmlPage loginpage = webClient.getPage("http://localhost:8080/bodgeit/login.jsp");
		
		List<HtmlAnchor> loginList = loginpage.getAnchors();
		
		for(HtmlAnchor each : loginList){
			if(each.getHrefAttribute().contains("prodid") || each.getHrefAttribute().contains("typeid")){}
			else{
				System.out.println(each.asText());
			}
		}
		
		List<HtmlForm> forms = loginpage.getForms();
		
		for(HtmlForm form : forms){
			System.out.println(form.asText());
			form.setAttribute("username", "user@example.com");
			form.setAttribute("password", "password");
			
			Robot clicker = new Robot();
			clicker.keyPress(KeyEvent.VK_ENTER);
			
		}
		
		List<HtmlForm> forms2 = loginpage.getForms();
		
		for(HtmlForm form:forms2){
			System.out.println(form.asText());
						
		}	
	}
	
	static void help(){
		System.out.println("Commands: ");
		for(String command:commands){
				System.out.println(command);	 }			
	}
}
