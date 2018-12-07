package com.example.deivi.pedidosonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deivi.pedidosonline.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    Button btn_login;
    Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById (R.id.login);
        registrarse = findViewById(R.id.registrarse);
        //final TextView email=(TextView)findViewById(R.id.correo);
        //final TextView password=(TextView)findViewById(R.id.password);
        //email.setText( getIntent().getExtras().getString("email"));
        registrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,RegistrarUsuario.class));
                finish();
            }

        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData();
                Toast.makeText(Login.this, "saliendo del sendata", Toast.LENGTH_SHORT).show();
            }

        });

    }
    public void sendData(){
        TextView correo  = findViewById(R.id.correo);
        TextView password  = findViewById(R.id.password);

        AsyncHttpClient login = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("email", correo.getText().toString());
        params.add("password", password.getText().toString());
        login.post(Data.REGISTER_LOGIN, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
                AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                try {


                    int resp = response.getInt("resp");
                    Toast.makeText(Login.this, "respuesta:"+resp, Toast.LENGTH_SHORT).show();
                    if(resp==200){
                        JSONObject json=response.getJSONObject("dato");
                        String token = response.getString("token");
                        String tipo= json.getString("tipo");
                        String nombre=json.getString("nombre");
                        String ci=json.getString("ci");
                        String telefono=json.getString("telefono");
                        String correo=json.getString("email");
                        Data.TOKEN="Data "+token;
                        if(tipo.compareTo("Administrador")==0) {
                            Intent intent =new Intent(Login.this, Admi.class);
                            intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent =new Intent(Login.this, Cliente.class);
                            intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                            startActivity(intent);
                            finish();
                        }



                        Toast.makeText(Login.this, "Login correctamente: "+ token, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this, "error de logueo", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Login.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }


        });
    }
    /*
    public class MaxConnectionsInThreads extends AbstractBasicTest {

  @Test
  public void testMaxConnectionsWithinThreads() throws Exception {

    String[] urls = new String[]{getTargetUrl(), getTargetUrl()};

    AsyncHttpClientConfig config = config()
            .setConnectTimeout(1000)
            .setRequestTimeout(5000)
            .setKeepAlive(true)
            .setMaxConnections(1)
            .setMaxConnectionsPerHost(1)
            .build();

    final CountDownLatch inThreadsLatch = new CountDownLatch(2);
    final AtomicInteger failedCount = new AtomicInteger();

    try (AsyncHttpClient client = asyncHttpClient(config)) {
      for (final String url : urls) {
        Thread t = new Thread() {
          public void run() {
            client.prepareGet(url).execute(new AsyncCompletionHandlerBase() {
              @Override
              public Response onCompleted(Response response) throws Exception {
                Response r = super.onCompleted(response);
                inThreadsLatch.countDown();
                return r;
              }

              @Override
              public void onThrowable(Throwable t) {
                super.onThrowable(t);
                failedCount.incrementAndGet();
                inThreadsLatch.countDown();
              }
            });
          }
        };
        t.start();
      }

      inThreadsLatch.await();

      assertEquals(failedCount.get(), 1, "Max Connections should have been reached when launching from concurrent threads");

      final CountDownLatch notInThreadsLatch = new CountDownLatch(2);
      failedCount.set(0);
      for (final String url : urls) {
        client.prepareGet(url).execute(new AsyncCompletionHandlerBase() {
          @Override
          public Response onCompleted(Response response) throws Exception {
            Response r = super.onCompleted(response);
            notInThreadsLatch.countDown();
            return r;
          }

          @Override
          public void onThrowable(Throwable t) {
            super.onThrowable(t);
            failedCount.incrementAndGet();
            notInThreadsLatch.countDown();
          }
        });
      }

      notInThreadsLatch.await();

      assertEquals(failedCount.get(), 1, "Max Connections should have been reached when launching from main thread");
    }
  }

  @Override
  @BeforeClass
  public void setUpGlobal() throws Exception {
    server = new Server();
    ServerConnector connector = addHttpConnector(server);
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(new ServletHolder(new MockTimeoutHttpServlet()), "/timeout/*");

    server.start();
    port1 = connector.getLocalPort();
  }

  public String getTargetUrl() {
    return "http://localhost:" + port1 + "/timeout/";
  }

  @SuppressWarnings("serial")
  public static class MockTimeoutHttpServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockTimeoutHttpServlet.class);
    private static final String contentType = "text/plain";
    static long DEFAULT_TIMEOUT = 2000;

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
      res.setStatus(200);
      res.addHeader("Content-Type", contentType);
      long sleepTime = DEFAULT_TIMEOUT;
      try {
        sleepTime = Integer.parseInt(req.getParameter("timeout"));

      } catch (NumberFormatException e) {
        sleepTime = DEFAULT_TIMEOUT;
      }

      try {
        LOGGER.debug("=======================================");
        LOGGER.debug("Servlet is sleeping for: " + sleepTime);
        LOGGER.debug("=======================================");
        Thread.sleep(sleepTime);
        LOGGER.debug("=======================================");
        LOGGER.debug("Servlet is awake for");
        LOGGER.debug("=======================================");
      } catch (Exception e) {
        //
      }

      res.setHeader("XXX", "TripleX");

      byte[] retVal = "1".getBytes();
      OutputStream os = res.getOutputStream();

      res.setContentLength(retVal.length);
      os.write(retVal);
      os.close();
    }
  }
    */
}

