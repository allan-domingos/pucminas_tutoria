<%  
String relyingParty = request.getParameter("relyingParty");

if (relyingParty.equals("sgm") || relyingParty.equals("rzK09sDWcAQoQwkVFfRKXIBNhooa") ) {
    RequestDispatcher dispatcher = request.getRequestDispatcher("login_sgm.jsp");
    dispatcher.forward(request, response);
} else if (relyingParty.equals("msc")) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("sgm/login_msc.jsp");
        dispatcher.forward(request, response);    
} else {
    RequestDispatcher dispatcher = request.getRequestDispatcher("default_login.jsp");
    dispatcher.forward(request, response);
} 
%>