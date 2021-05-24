package com.ibmhq;

public class BrowserNavigate {

  public String execute(String Navigate,String URL) {
	  
	 String strstatus = null;
	  
     try { 
	  switch (Navigate){
		  case "Forward":
			  Browser.Driver.navigate().forward();
			  break;
		  case "Back":
			  Browser.Driver.navigate().back();
			  break;
		  case "Refresh":
			  Browser.Driver.navigate().refresh();
			  break;
		  case "Navigate":
			  Browser.Driver.navigate().to(URL);
			  break;
		  case "Default Content":
			  Browser.Driver.switchTo().defaultContent();
			  break;
	  }
	    
	    if(Browser.Driver != null){
	    	strstatus = "Passed%%The URL: " + URL + " navigation : "+ Navigate +" is successful";
	    }else{
	    	strstatus = "Failed%%The URL: " + URL + " is NOT navigated : "+ Navigate;
	    }
	    
	} catch(Exception e) {
		strstatus = "Failed%%" + e.getMessage();
	}
	return strstatus;
	
  }
  

public String customizedURL(String url) {
		String strStatus = "";
		try {
			System.out.println(url.indexOf("|"));
			String[] urls = url.split("\\|");
			String urltoReplace = urls[0].substring(urls[0].lastIndexOf("/")+1, urls[0].length());
			String urltoReplaceWith = urls[1].substring(urls[1].lastIndexOf("/")+1, urls[1].length());
			System.out.println(urltoReplace);
			System.out.println(urltoReplaceWith);
			String actualUrl = Browser.Driver.getCurrentUrl();
			String newUrl = actualUrl.replaceAll(urltoReplace, urltoReplaceWith);
			Browser.Driver.navigate().to(newUrl); 
			strStatus = "Passed%% Navigated to the URL:"+newUrl+" successfully.";
		}
		catch(Exception e){
			strStatus = "Failed%%Exception in the method LaunchURL:"+e.toString();
		}
		return strStatus;
	} 
}
