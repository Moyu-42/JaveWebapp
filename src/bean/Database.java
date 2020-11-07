package bean;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.io.*;
import com.alibaba.fastjson.*;
import java.net.URISyntaxException;
import java.sql.*;

public class Database {
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://:/?useUnicode=true&characterEncoding=utf8&useSSL=false&nullCatalogMeansCurrent=true&serverTimezone=UTC";
    private String IPAddress;
    private String PORT;
    private String DatabaseName;
    private String USER;
    private String PASSWORD;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private FileWriter fw = null;

    private void GetSettings() {
        String s = "";
        try {
            String path = "/settings.json";
            File file = new File(".");
            try {
                file = new File(Database.class.getResource(path).toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            FileReader reader = new FileReader(file);
            Reader rr = new InputStreamReader(new FileInputStream(file), "utf-8");
            StringBuffer buf = new StringBuffer();
            int ch = 0;
            while ((ch = rr.read()) != -1) {
                buf.append((char) ch);
            }
            reader.close();
            rr.close();
            s = buf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jo = JSON.parseObject(s);
        IPAddress = jo.getString("IPAddress");
        PORT = jo.getString("PORT");
        USER = jo.getString("USER");
        PASSWORD = jo.getString("PASSWORD");
        DatabaseName = jo.getString("DatabaseName");
        Integer index_IP, index_Port, index_databasename;
        StringBuilder strbu = new StringBuilder(URL);
        index_IP = strbu.indexOf(":/?");
        strbu.insert(index_IP, IPAddress);
        index_Port = strbu.indexOf("/?");
        strbu.insert(index_Port, PORT);
        index_databasename = strbu.indexOf("?");
        strbu.insert(index_databasename, DatabaseName);
        URL = strbu.toString();
    }

    public Database() {
        GetSettings();
        try {
            fw = new FileWriter("./log", false);
            fw.write("");
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(String sql, Object... params) {
        boolean flag = false;
        int result;
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            result = pstmt.executeUpdate();
            flag = result > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Map<String, Object>> query(String sql, Object... params) {
        List<Map<String, Object>> ans = new ArrayList<Map<String, Object>>();
        ResultSet resultset = null;
        ResultSetMetaData rsmd = null;
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            resultset = pstmt.executeQuery();
            rsmd = resultset.getMetaData();

            while (resultset.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String column_label = rsmd.getColumnLabel(i + 1);
                    Object column_object = resultset.getObject(column_label);
                    map.put(column_label, column_object);
                }
                ans.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ans;
    }

    public void getLog(int opt) {
        try {
            fw = new FileWriter("./log", true);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            fw.write(dateFormat.format(date));
            switch (opt) {
                case 1:
                    fw.write("\t\tCreate User table\n");
                    break;
                case 2:
                    fw.write("\t\tUser Insert\n");
                    break;
                case 3:
                    fw.write("\t\tUser Delete\n");
                    break;
                case 4:
                    fw.write("\t\tUser Query\n");
                    break;
                case 5:
                    fw.write("\t\tCreate Person table\n");
                    break;
                case 6:
                    fw.write("\t\tPerson Insert\n");
                    break;
                case 7:
                    fw.write("\t\tPerson Delete\n");
                    break;
                case 8:
                    fw.write("\t\tPerson Query\n");
                    break;
                case 9:
                    fw.write("\t\tDrop User table\n");
                    break;
                case 10:
                    fw.write("\t\tDrop Person table\n");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void close() {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
