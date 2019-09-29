<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>        
        <form id="formGet" name="formGet" action="Servlet">
            <input type="submit" value="Calculadora" />            
            <input type="hidden" id="acao" name="acao" value="calculadora"/>
        </form>
        <form id="formPost" name="formPost" action="Servlet" action="POST">
            <input type="submit" value="Nº de acessos"/>
            <input type="hidden" id="acao" name="acao" value="numAcessos"/>
        </form>
        <form id="formWSGet" name="formWSGet" action="Servlet">
            <input type="submit" value="Testa GET webService"/>
            <input type="hidden" id="acao" name="acao" value="wsGet"/>
        </form>
        <form id="formWSPost" name="formWSGet" action="Servlet">
            <input type="submit" value="Testa POST webService"/>
            <input type="hidden" id="acao" name="acao" value="wsPost"/>
        </form>
    </body>
</html>
