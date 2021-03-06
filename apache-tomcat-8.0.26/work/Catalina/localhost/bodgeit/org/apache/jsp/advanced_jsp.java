/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.26
 * Generated at: 2015-09-23 15:47:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.UUID;
import com.thebodgeitstore.util.AES;

public final class advanced_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {


	private Connection conn = null;

	public void jspInit() {
		try {
			// Get hold of the JDBC driver
			Class.forName("org.hsqldb.jdbcDriver" );
			// Establish a connection to an in memory db
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:SQL", "sa", "");
		} catch (SQLException e) {
			getServletContext().log("Db error: " + e);
		} catch (Exception e) {
			getServletContext().log("System error: " + e);
		}
	}
	
	public void jspDestroy() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			getServletContext().log("Db error: " + e);
		} catch (Exception e) {
			getServletContext().log("System error: " + e);
		}
	}
        
        public static String implode(String[] ary, String delim) {
            String out = "";
            for(int i=0; i<ary.length; i++) {
                if(i!=0) { out += delim; }
                out += ary[i];
            }
            return out;
        }
        

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.thebodgeitstore.util.AES");
    _jspx_imports_classes.add("java.util.UUID");
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

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/header.jsp", out, false);
      out.write('\n');

    String query = null;
    String key = "";
    String[] params = {};
    if (request.getMethod().equals("POST")){
        AES enc = new AES();
        try {
            key = session.getAttribute("key").toString();
        } catch (Exception e){
            key = UUID.randomUUID().toString().substring(0, 16);
        }
        if ("true".equals(request.getParameter("debug")))
            out.println("<!--".concat(key).concat("-->"));
        
        enc.setCrtKey(key);
        String eQuery = (String) request.getParameter("q");
        query = enc.decryptCrt(eQuery);
        query = query.replaceAll("[^\\p{ASCII}]", "");
        params = query.split("\\|");
        query = "\n\t<div class='search'>".concat(implode(params, "</div>\n\t<div class='search'>")).concat("</div>\n");
    } else { 
        key = UUID.randomUUID().toString().substring(0, 16);
        session.setAttribute("key", key);
    }

      out.write("\n");
      out.write("<SCRIPT>\n");
      out.write("    loadfile('./js/encryption.js');\n");
      out.write("    \n");
      out.write("    var key = \"");
      out.print( key );
      out.write("\";\n");
      out.write("    \n");
      out.write("    function validateForm(form){\n");
      out.write("        var query = document.getElementById('query');\n");
      out.write("        var q = document.getElementById('q');\n");
      out.write("        var val = encryptForm(key, form);\n");
      out.write("        if(val){\n");
      out.write("            q.value = val;\n");
      out.write("            query.submit();\n");
      out.write("        }   \n");
      out.write("        return false;\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    function encryptForm(key, form){\n");
      out.write("        var params = form_to_params(form).replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/\"/g, '&quot;').replace(/'/g, '&#39');\n");
      out.write("        if(params.length > 0)\n");
      out.write("            return Aes.Ctr.encrypt(params, key, 128);\n");
      out.write("        return false;\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("</SCRIPT>\n");
      out.write("    \n");
      out.write("<h3>Search</h3>\n");
      out.write("<font size=\"-1\">\n");


if (request.getMethod().equals("POST") && query != null){
          
        

      out.write("\n");
      out.write("<b>You searched for:</b> ");
      out.print( query );
      out.write("<br/><br/>\n");
    
        if (query.replaceAll("\\s", "").toLowerCase().indexOf("<script>alert(\"h@ckeda3s\")</script>") > -1) {
                conn.createStatement().execute("UPDATE Score SET status = 1 WHERE task = 'AES_XSS'");
        }

        Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
                String sql = "SELECT PRODUCT, DESC, TYPE, TYPEID, PRICE " +
                             "FROM PRODUCTS AS a JOIN PRODUCTTYPES AS b " +
                             "ON a.TYPEID = b.TYPEID " +
                             "WHERE PRODUCT LIKE '%{PRODUCT}%' AND " + 
                             "DESC LIKE '%{DESCRIPTION}%' AND PRICE LIKE '%{PRICE}%' " +
                             "AND TYPE LIKE '%{TYPE}%'";
               
                for(int x = 0; x < params.length; x++){
                    String[] parm = params[x].split(":");
                    if(parm.length == 2){
                        String find = "\\{".concat(parm[0].toUpperCase()).concat("\\}");
                        sql = sql.replaceAll(find, parm[1]);
                    }
                    
                }
                sql = sql.replaceAll("%\\{[^\\}]+\\}", "");
                if ("true".equals(request.getParameter("debug")))
                    out.println(sql);
		rs = stmt.executeQuery(sql);
              
                int count = 0;
                String output = "";
                while (rs.next()) {
                    //A random table was created an inserted into table list.
                    //Since there are many SQL statements that could return a list
                    //of tables, this validates without requiring a specific input.
                    String tableName = "f0ecfb32e56d3845f140e5c81a81363ce61d9d50";
                    if( rs.getString("PRODUCT").toLowerCase().indexOf(tableName) > -1 || 
                        rs.getString("DESC").toLowerCase().indexOf(tableName) > -1 || 
                        rs.getString("TYPE").toLowerCase().indexOf(tableName) > -1 || 
                        rs.getString("PRICE").toLowerCase().indexOf(tableName) > -1      )
                            stmt.execute("UPDATE Score SET status = 1 WHERE task = 'AES_SQLI'");
                        
                    output = output.concat("<TR><TD>" + rs.getString("PRODUCT") + 
                                  "</TD><TD>" + rs.getString("DESC") + 
                                  "</TD><TD>" + rs.getString("TYPE") + 
                                  "</TD><TD>" + rs.getString("PRICE") + "</TD></TR>\n");
                    count++;
                }
                if(count > 0){

      out.write("\n");
      out.write("<TABLE border=\"1\">\n");
      out.write("<TR><TD>Product</TD><TD>Description</TD><TD>Type</TD><TD>Price</TD></TR>\n");
      out.print( output );
      out.write("\n");
      out.write("</TABLE>                    \n");
              
                } else {   
                    out.println("<div><b>No Results Found</b></div>");
                }
        } catch (Exception e) {
		if ("true".equals(request.getParameter("debug"))) {
			stmt.execute("UPDATE Score SET status = 1 WHERE task = 'HIDDEN_DEBUG'");
			out.println("DEBUG System error: " + e + "<br/><br/>");
		} else {
			out.println("System error.");
		}
	} finally {
		if (rs != null) {
			rs.close();
		}
		stmt.close();
	}

} else {

      out.write("\n");
      out.write("<form id=\"advanced\" name=\"advanced\" method=\"POST\" onsubmit=\"return validateForm(this);false;\">\n");
      out.write("<table>\n");
      out.write("<tr><td>Product:</td><td><input id='product' type='text' name='product' /></td></td>\n");
      out.write("<tr><td>Description:</td><td><input id='desc' type='text' name='description' /></td></td>\n");
      out.write("<tr><td>Type:</td><td><input id='type' type='text' name='type' /></td></td>\n");
      out.write("<tr><td>Price:</td><td><input id='price' type='text' name='price' /></td></td>\n");
      out.write("<tr><td></td><td><input type='submit' value='Search'/></td></td>\n");
      out.write("</table>\n");
      out.write("</form>\n");
      out.write("<form id=\"query\" name=\"advanced\" method=\"POST\">\n");
      out.write("    <input id='q' type=\"hidden\" name=\"q\" value=\"\" />\n");
      out.write("</form>\n");
  
}

      out.write("\n");
      out.write("</font>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jsp", out, false);
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
