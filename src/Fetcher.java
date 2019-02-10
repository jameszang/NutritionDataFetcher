import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fetcher {

    public static final String API_KEY = "zO657jhxh83tm2KsFBZjvchHVlUlzBusyOxxuHAY";

    public static String NDBNOS[] = {
            "01239", "01187", "01110", "01042", "01116", "09299",
            "11364", "09265", "11049", "09040", "20121", "20453",
            "05067", "10857", "23266", "10984", "09422", "11450",
            "11177", "11969", "11868", "05673", "13849", "15241",
            "01223", "01028", "01270", "43274", "01057", "11248",
            "11311", "45359340", "11100", "09037", "09286", "20038",
            "20120", "20648", "20445", "20068", "10861", "23634",
            "17390", "17111", "16426", "15118", "09039", "11049",
            "11267", "11255", "11115", "20137", "20321", "20029",
            "20134", "05323", "45285752", "01131", "01119", "01236",
            "11439", "11549", "11147", "11080", "11233", "15137",
            "15034", "15171", "23274", "01264", "01023", "01104",
            "01128", "11026", "11118", "11091", "01040", "01102",
            "01077", "01081", "11457", "11745", "20523", "20310",
            "15220", "23353", "10068", "15077", "01092", "01133",
            "01218", "19207", "11938", "15038", "83110", "15085",
            "15072", "09520", "09250", "09205", "09316", "09430",
            "09150", "09213", "09125", "09410", "11540", "11931",
            "11956", "11740", "11965", "11378", "11300", "01224",
            "01140", "01123", "43276", "16108", "16049", "16030",
            "15158", "15012", "05028", "07929", "15230", "16156",
            "16155", "19407", "19412", "28290", "19040", "19059"
    };

    public static void main(String[] args) throws Exception {
        for (String ndbno : NDBNOS) {
            // link to USDA database
            String link = "https://api.nal.usda.gov/ndb/V2/reports" + "?ndbno=" + ndbno + "&api_key=" + API_KEY;

            // establish connection to api endpoint
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoOutput(true);
            con.connect();

            // read request response into StringBuffer
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // close the connection
            con.disconnect();

            // initialize a BufferedWriter
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("data/" + ndbno + ".json")));

            //write contents of StringBuffer to a file
            bwr.write(content.toString());

            //flush the stream
            bwr.flush();

            //close the stream
            bwr.close();
        }
    }
}


