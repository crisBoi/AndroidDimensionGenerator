public class SizeConvert {
    public static void main(String[] args) {
        float hdpi, xhdpi, xxhdpi, xxxhdpi, xlhdpi;

        Pixel pixel = new Pixel();
        pixel.setDp(200f);
        pixel.setName("_200");
        pixel.setType(Pixel.Type.densityPixel_dp);

        pixel.setScreenSize(Pixel.ScreenSize.hdpi);
        pixel.print();
        pixel.setScreenSize(Pixel.ScreenSize.mhdpi);
        pixel.print();
        pixel.setScreenSize(Pixel.ScreenSize.xhdpi);
        pixel.print();
        pixel.setScreenSize(Pixel.ScreenSize.xxhdpi);
        pixel.print();
        pixel.setScreenSize(Pixel.ScreenSize.xxxhdpi);
        pixel.print();


        /*//print list
        pixel.setScreenSize(Pixel.ScreenSize.hdpi);
        pixel.setType(Pixel.Type.densityPixel_dp);
        for (float i = 1; i <= 100; i++) {
            pixel.setName("_" + String.format("%.0f", i));
            pixel.setDp(i);
            pixel.print();

        }*/

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
        hdpi(3.0f / 6.0f), mhdpi(4.0f / 6.0f), xhdpi(1f), xxhdpi(8.0f / 6.0f), xxxhdpi(12.0f / 6.0f);
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
