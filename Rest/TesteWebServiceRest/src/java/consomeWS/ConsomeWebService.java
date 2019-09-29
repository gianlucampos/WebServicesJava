package consomeWS;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import modelo.Usuario;

/**
 *
 * @author gianlucampos
 */
public class ConsomeWebService {

    public void escolheMetodo(String metodo) throws Exception {
        if (metodo.equals("get")) {
            preparaGet();
        } else if (metodo.equals("post")) {
            sendPost();
        }
    }

    public void preparaGet() throws Exception {
        ConsomeWebService http = new ConsomeWebService();
        String chamadaWS = "http://localhost:8080/TesteWebServiceRest/webresources/db/usuario/get/gianlucampos";
        String json = http.sendGet(chamadaWS);
        Gson g = new Gson();
        Type usuarioType = new TypeToken<Usuario>() {
        }.getType();
        Usuario u = g.fromJson(json, usuarioType);
        System.out.println(u.getLogin());

    }

    private String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + con.getResponseCode());
        StringBuilder response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        System.out.println(response.toString());
        return response.toString();
    }

    private void sendPost() throws Exception {
        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + con.getResponseCode());
        StringBuilder response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        System.out.println(response.toString());
    }

}
