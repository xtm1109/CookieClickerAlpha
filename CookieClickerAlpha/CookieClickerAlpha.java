import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.File;
import java.io.IOException;  
import java.math.BigDecimal;

public class CookieClickerAlpha {
  private static final int PRECISION = 7;
  
  private static double round (double unrounded, int roundmode) {
    BigDecimal number = new BigDecimal(unrounded);
    return number.setScale(PRECISION, roundmode).doubleValue();
  }
  
  private static double addtotal (double time1, double time2) {
    BigDecimal t1 = new BigDecimal(time1);
    BigDecimal t2 = new BigDecimal(time2);
    
    return round(t1.add(t2).doubleValue(), BigDecimal.ROUND_HALF_UP);
  }
  
  private static double convert (String num) {
    return Double.parseDouble(num);
  }
  
  public static double time (double cookie, double extra, double win) {
    int farm = 0;
    double totaltime = 0;
    double mintime = win/2;
    double time1 = 0;
    double time2 = 0;
    
    while (totaltime <= mintime) {
      for (int i = 0; i < farm; i++) {
        time1 = time1 + round(cookie/(2+i*extra), BigDecimal.ROUND_HALF_UP);
      }
      
      time2 = round(win/(2+farm*extra), BigDecimal.ROUND_HALF_UP);
      totaltime = addtotal(time1, time2);
      
      if (mintime > totaltime)
        mintime = totaltime;
      
      farm++;
      time1 = 0;
      time2 = 0;
    }
    
    return mintime;
  }
  
  public static void main(String[] args) throws IOException {
    int N;
    File directory = new File (".");
    String path = "";
    
    try {
      path = directory.getCanonicalPath();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    
    BufferedReader in = new BufferedReader(new FileReader(path + "\\B-small-attempt1.in"));
    FileWriter out = new FileWriter(path + "\\B-small-attempt1.out");
    
    int casenum = new Integer(in.readLine());
    String[] line;
    double cookie, extra, win;
    
    for (int i = 1; i <= casenum; i++) {
      line = in.readLine().split("\\s");
      cookie = convert(line[0]);
      extra = convert(line[1]);
      win = convert(line[2]);
      
      out.write("Case #" + i + ": " + CookieClickerAlpha.time(cookie, extra, win) + System.lineSeparator());
    }
    
    in.close();
    out.flush();
    out.close();
  }
}