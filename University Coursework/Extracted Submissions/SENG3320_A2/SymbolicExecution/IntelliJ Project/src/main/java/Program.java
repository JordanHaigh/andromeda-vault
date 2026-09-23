public class Program {
    // The Median program
    static int median(int x, int y, int z){
        int median = 0;
        if(x >= y && x <= z){ // y<=x<=z
            median = x;
        } else if(x >= z && x <= y){ // z<=x<=y
            median = x;
        } else if(y >=x && y < z){ // x<=y<=z // a bug here (y < z)
            z = y; // a bug here
        } else if(y >= z && y <= x){ // z<=y<=x
            median = y;
        } else { // x<=z<=y or y<=z<=x
            median = z;
        }
        return median;
    }
}