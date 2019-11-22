import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatConversionException;

public class SizeConvert {
    public static  BufferedReader br;
    public static void main(String[] args) {


        Pixel pixel = new Pixel();

         br = new BufferedReader(new InputStreamReader(System.in));

         pixel.setDp(readValue());
         pixel.setName(readName());
         pixel.setScreenSize(getDensity());
         pixel.setType(Pixel.Type.densityPixel_dp);
         pixel.print();



    }


    public static float readValue() {
        float dp = 0.00f;

        try {
            boolean isNumeric = true;
            System.out.println("Please enter the value");


            do {
                try {
                    String sr = br.readLine();
                    dp = Float.parseFloat(sr);
                    sr = String.format("%.2f", dp);
                    isNumeric = false;
                } catch (IllegalFormatConversionException e) {
                    System.out.println("Not a number, please enter a valid number: ");
                } catch (NumberFormatException e) {
                    System.out.println("Not a number, please enter a valid number: ");
                }
            } while (isNumeric);

        } catch (Exception e) {
            System.out.println("IO exception caused");
        }

        return dp;

    }

    public static String readName() {
        String sr = "default_name";
        try {
            boolean isNumeric = true;
            System.out.println("Please enter the name of dimension");


            do {
                sr = br.readLine();
                if (!checkValidName(sr)) {
                    System.out.println("Please enter a valid name.\nNo special character allowed except '_'");
                }
            } while (!checkValidName(sr));

        } catch (IOException e) {
            System.out.println("IO exception caused");
        }

        return sr;

    }

    public static Pixel.ScreenSize getDensity() {
        int choice = -1;


        do {
            clearScreen();
            System.out.println("Please select an option");
            System.out.println("1. hdpi");
            System.out.println("2. mhdpi");
            System.out.println("3. xhdpi");
            System.out.println("4. xxhdpi");
            System.out.println("5. xxxhdpi");

            if (choice == -1) {
                System.out.println("Please enter a valid choice");
            }

            try {
                String sr = br.readLine();
                choice = Integer.parseInt(sr);
                if (choice > 5 || choice == 0) choice = -1;
            } catch (IOException e) {
                e.printStackTrace();
                choice = -1;
                System.out.println("Please enter a valid choice");
            }
        } while (choice == 5);

        switch (choice) {
            case 1: return Pixel.ScreenSize.hdpi;
            case 2: return Pixel.ScreenSize.mhdpi;
            case 3: return Pixel.ScreenSize.xhdpi;
            case 4: return Pixel.ScreenSize.xxhdpi;
            case 5: return Pixel.ScreenSize.xxxhdpi;
        }

        return Pixel.ScreenSize.xxxhdpi;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean checkValidName(String str) {
        boolean isValid = true;

        char[] arr = str.toCharArray();

        for (char c: arr ) {

            isValid = ((c >= 'a') && (c <= 'z')) ||
                    ((c >= 'A') && (c <= 'Z')) ||
                    ((c >= '0') && (c <= '9')) ||
                    c == '_';

            if (!isValid) {
                break;
            }
        }

        return isValid;
    }


}

class Pixel {
    private float dp;
    private String name;
    private Type type;
    private ScreenSize screenSize;

    enum Type {
        text_sp("sp"), densityPixel_dp("dp");
        private String type;

        Type(String type) {
            this.type = type;
        }

    };


    enum ScreenSize {
        hdpi(3.0f / 8.0f), mhdpi(4.0f / 8.0f), xhdpi(6.0f / 8.0f), xxhdpi(1f), xxxhdpi(12.0f / 8.0f);
        private float ratio;

        ScreenSize(float ratio) {
            this.ratio = ratio;
        }

        public float getRatio() {
            return ratio;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ScreenSize getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize) {
        this.screenSize = screenSize;
    }

    public float getDp() {
        return dp;
    }

    public void setDp(float dp) {
        this.dp = dp;
    }


    public void print() {
        System.out.println("<dimen name=\"" + name + "\">" + String.format("%.2f", (screenSize.getRatio() * dp)) + type.type + "</dimen>");
    }


}
