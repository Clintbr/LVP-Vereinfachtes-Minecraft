class Clint implements Clerk {
    final String ID;
    LiveView view;
    final int width, height;
    Font textFont = Font.SANSSERIF;
    double textSize = 10;
    Font.Align textAlign = Font.Align.CENTER;
    Boolean weather = false;

    Clint(LiveView view, int width, int height) {
        this.view = view;
        this.width  = Math.max(1, Math.abs(width));  // width is at least of size 1
        this.height = Math.max(1, Math.abs(height)); // height is at least of size 1
        ID = Clerk.getHashID(this);
        Clerk.load(view, "views/Clint/clint.js");
        Clerk.write(view, "<canvas id='clintCanvas" + ID + "' width='" + this.width + "' height='" + this.height + "' display: block; margin: 50px auto; style='border:1px solid #000; background-image: url(https://thumbs.dreamstime.com/b/twisted-woodland-scenery-resembling-minecraft-landscape-ai-generative-design-background-instagram-facebook-wall-painting-329315903.jpg); background-size: cover; background-repeat: no-repeat; background-position: center;'></canvas>");
        Clerk.script(view, "const clint" + ID + " = new Clint(document.getElementById('clintCanvas" + ID + "'));");
    }

    Clint(LiveView view) { this(view, 500, 500); }
    Clint(int width, int height) { this(Clerk.view(), width, height); }
    Clint() { this(Clerk.view()); }

    Clint color(int red, int green, int blue) {
        Clerk.call(view, "clint" + ID + ".color('rgb(" + (red & 0xFF) + ", " + (green & 0xFF) + ", " + (blue & 0xFF) + ")');");
        return this;
    }

    Clint color(int rgb) {
        color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
        return this;
    }
    
    Clint draw2D(double angle, double distance, double width, double height, double depth, int red, int green, int blue) {
        color(red, green, blue);
        Clerk.call(view,"clint" + ID + ".draw2D(" + angle + "," + distance + "," + width + "," + height + "," + depth + ");");
        return this;
    }
    
    Clint draw3D(int cubeNumber, double angle, double distance, double width, double height, double depth, int red, int green, int blue) {
        colorStroke(weather);
        color(red, green, blue);
        Clerk.call(view,"clint" + ID + ".draw3D(" + cubeNumber + "," + angle + "," + distance + "," + width + "," + height + "," + depth + ");");
        return this;
    }
    
    Clint colorStroke(boolean color) {
        Clerk.call(view, "clint" + ID + ".colorStroke(" + color + ");");
        return this;
    }

    Clint reset() {
        Clerk.call(view, "clint" + ID + ".reset();");
        return this;
    }
}
