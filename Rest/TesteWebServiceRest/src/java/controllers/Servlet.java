package controllers;

import consomeWS.ConsomeWebService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 *
 * @author gianlucampos
 */
@WebServlet(name = "Servlet", urlPatterns = "/Servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (null != acao) switch (acao) {
            case "calculadora":
                calculadora(req, resp);
                break;
            case "numAcessos":
                numAcessos(req, resp);
                break;
            case "wsGet":
                chamaWebService("get"); 
                break;
            case "wsPost":
                chamaWebService("post");
                break;
        }
        System.out.println(acao);
    }

    private void calculadora(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("Você está no servlet");
        String operacao = req.getParameter("oper");
        String elem1 = req.getParameter("elem1");
        String elem2 = req.getParameter("elem2");
        String resultado = null;
        if ("soma".equals(operacao)) {
            Double result = (Double.parseDouble(elem1) + Double.parseDouble(elem2));
            resultado = result.toString();
        }
        res.getWriter().println("Operação: " + req.getParameter("oper"));
        res.getWriter().println("Resultado: " + resultado);
    }

    private void numAcessos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        int vezesAcesso = 1;
        for (Cookie temp : req.getCookies()) {
            if ("vezesAcesso".equals(temp.getName())) {
                vezesAcesso += Integer.parseInt(temp.getValue());
            }
        }
        Cookie cook = new Cookie("vezesAcesso", String.valueOf(vezesAcesso));
        cook.setMaxAge(10);
        resp.addCookie(cook);
        out.println(String.format("<h1>Acesso [%d] - Data [%s]</h1>", vezesAcesso, new Date()));
    }

    private void chamaWebService(String metodo) {
        ConsomeWebService webServiceGet = new ConsomeWebService();
        try {
        webServiceGet.escolheMetodo(metodo);
        } catch (Exception e) {
            System.out.println("Não deu Jão " + e.getMessage());
        }
    }

}
