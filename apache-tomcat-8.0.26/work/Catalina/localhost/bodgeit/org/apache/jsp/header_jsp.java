/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.26
 * Generated at: 2015-09-23 01:30:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.math.*;
import java.servlet.*;
import java.servlet.http.*;
import java.sql.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("java.servlet.http");
    _jspx_imports_packages.add("java.math");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>The BodgeIt Store</title>\n");
      out.write("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<script type=\"text/javascript\" src=\"./js/util.js\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");

	String username = (String) session.getAttribute("username");
	String usertype = (String) session.getAttribute("usertype");

      out.write("\n");
      out.write("<center>\n");
      out.write("<table width=\"80%\" class=\"border\">\n");
      out.write("<tr BGCOLOR=#C3D9FF>\n");
      out.write("<td align=\"center\" colspan=\"6\">\n");
      out.write("<H1>The BodgeIt Store</H1>\n");
      out.write("<table width=\"100%\" class=\\\"noborder\\\">\n");
      out.write("<tr BGCOLOR=#C3D9FF>\n");
      out.write("<td align=\"center\" width=\"30%\">&nbsp;</td>\n");
      out.write("<td align=\"center\" width=\"40%\">We bodge it, so you dont have to!</td>\n");
      out.write("<td align=\"center\" width=\"30%\" style=\"text-align: right\" >\n");

	if (username != null) {
		out.println("User: <a href=\"password.jsp\">" + username + "</a>");
	} else {
		out.println("Guest user");
	}

      out.write("\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE><a href=\"home.jsp\">Home</a></td>\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE><a href=\"about.jsp\">About Us</a></td>\n");

	if (usertype != null && usertype.equals("ADMIN")) {

      out.write("\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE><a href=\"contact.jsp\">Comments</a></td>\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE><a href=\"admin.jsp\">Admin</a></td>\n");

	} else {

      out.write("\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE><a href=\"contact.jsp\">Contact Us</a></td>\n");
      out.write("<!-- td align=\"center\" width=\"16%\"><a href=\"admin.jsp\">Admin</a></td-->\n");

	}

      out.write("\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE>\n");

	if (usertype == null) {

      out.write("\n");
      out.write("\t\t<a href=\"login.jsp\">Login</a>\n");

	} else {

      out.write("\n");
      out.write("\t\t<a href=\"logout.jsp\">Logout</a>\n");

	}

      out.write("\n");
      out.write("</td>\n");

	if (usertype == null || ! usertype.equals("ADMIN")) {

      out.write("\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE><a href=\"basket.jsp\">Your Basket</a></td>\n");

	}

      out.write("\n");
      out.write("<td align=\"center\" width=\"16%\" BGCOLOR=#EEEEEE><a href=\"search.jsp\">Search</a></td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td align=\"center\" colspan=\"6\">\n");
      out.write("<table width=\"100%\" class=\"border\">\n");
      out.write("<tr>\n");
      out.write("<td align=\"left\" valign=\"top\" width=\"25%\">\n");

	Connection c = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		// Get hold of the JDBC driver
		Class.forName("org.hsqldb.jdbcDriver" );
		// Establish a connection to an in memory db
		c = DriverManager.getConnection("jdbc:hsqldb:mem:SQL", "sa", "");
		stmt = c.prepareStatement("SELECT * FROM ProductTypes ORDER BY type");
		rs = stmt.executeQuery();
		while (rs.next()) {
			String type = rs.getString("type");
			out.println("<a href=\"product.jsp?typeid=" + rs.getInt("typeid") + "\">" + type + "</a><br/>");
		}
	} catch (SQLException e) {
		if ("true".equals(request.getParameter("debug"))) {
			c.createStatement().execute("UPDATE Score SET status = 1 WHERE task = 'HIDDEN_DEBUG'");
			out.println("DEBUG System error: " + e + "<br/><br/>");
		} else {
			out.println("System error.");
		}
	} finally {
		stmt.close();
		rs.close();
		c.close();
	}

      out.write("\n");
      out.write("<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\n");
      out.write("</td>\n");
      out.write("<td valign=\"top\" width=\"70%\">\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}