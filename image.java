public class image {
    int[] pixels;
    public image(int[] p){
        pixels = p;
    }
    public int[] getPixels(){
        return pixels;
    }
    public int getPixel(int i){
        return pixels[i];
    }
}
