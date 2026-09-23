//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class KWIC {
    private char[] chars_;
    private int[] line_index_;
    private int[][] circular_shifts_;
    private int[][] alphabetized_;
    private char[] shifts_chars_;
    private int[] shifts_index_;
    private int[] shifts_lines_len_;

    public KWIC() {
    }

    public void input(String var1) {
        this.line_index_ = new int[32];
        this.chars_ = new char[32];
        int var2 = 0;
        int var3 = 0;
        boolean var5 = true;
        boolean var6 = false;
        boolean var7 = false;

        try {
            FileInputStream var8 = new FileInputStream(var1);

            int[] var9;
            char[] var12;
            for(int var4 = var8.read(); var4 != -1; var4 = var8.read()) {
                switch((byte)var4) {
                    case 9:
                        var6 = true;
                        break;
                    case 10:
                        var5 = true;
                    case 13:
                        break;
                    case 32:
                        var6 = true;
                        break;
                    default:
                        if (var5) {
                            if (var3 == this.line_index_.length) {
                                var9 = new int[var3];
                                System.arraycopy(this.line_index_, 0, var9, 0, var3);
                                this.line_index_ = var9;
                            }

                            this.line_index_[var3] = var2;
                            ++var3;
                            var5 = false;
                            var7 = false;
                        }

                        if (var6) {
                            if (var7) {
                                if (var2 == this.chars_.length) {
                                    var12 = new char[var2 + 1];
                                    System.arraycopy(this.chars_, 0, var12, 0, var2);
                                    this.chars_ = var12;
                                }

                                this.chars_[var2] = ' ';
                                ++var2;
                            }

                            var6 = false;
                        }

                        if (var2 == this.chars_.length) {
                            var12 = new char[var2 + 1];
                            System.arraycopy(this.chars_, 0, var12, 0, var2);
                            this.chars_ = var12;
                        }

                        this.chars_[var2] = (char)var4;
                        ++var2;
                        var7 = true;
                }
            }

            if (var3 != this.line_index_.length) {
                var9 = new int[var3];
                System.arraycopy(this.line_index_, 0, var9, 0, var3);
                this.line_index_ = var9;
            }

            if (var2 != this.chars_.length) {
                var12 = new char[var2];
                System.arraycopy(this.chars_, 0, var12, 0, var2);
                this.chars_ = var12;
            }
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
            System.err.println("KWIC Error: Could not open " + var1 + "file.");
            System.exit(1);
        } catch (IOException var11) {
            var11.printStackTrace();
            System.err.println("KWIC Error: Could not read " + var1 + "file.");
            System.exit(1);
        }

    }

    public void circularShift() {
        this.circular_shifts_ = new int[2][32];
        int var1 = 0;

        for(int var2 = 0; var2 <= this.line_index_.length; ++var2) {
            boolean var3 = false;
            int var7;
            if (var2 == this.line_index_.length - 1) {
                var7 = this.chars_.length;
            } else {
                var7 = this.line_index_[var2 + 1];
            }

            for(int var4 = this.line_index_[var2]; var4 < var7; ++var4) {
                if (this.chars_[var4] == ' ' || var4 == this.line_index_[var2]) {
                    if (var1 == this.circular_shifts_[0].length) {
                        int[] var5 = new int[var1 + 6];
                        System.arraycopy(this.circular_shifts_[0], 0, var5, 0, var1);
                        this.circular_shifts_[0] = var5;
                        var5 = new int[var1 + 6];
                        System.arraycopy(this.circular_shifts_[1], 0, var5, 0, var1);
                        this.circular_shifts_[1] = var5;
                    }

                    this.circular_shifts_[0][var1] = var2;
                    this.circular_shifts_[1][var1] = var4 == this.line_index_[var2] ? var4 : var4 + 1;
                    ++var1;
                }
            }
        }

        if (var1 != this.circular_shifts_[0].length) {
            int[] var6 = new int[var1];
            System.arraycopy(this.circular_shifts_[0], 0, var6, 0, var1);
            this.circular_shifts_[0] = var6;
            var6 = new int[var1];
            System.arraycopy(this.circular_shifts_[1], 0, var6, 0, var1);
            this.circular_shifts_[1] = var6;
        }

    }

    public void alphabetizing() {
        this.alphabetized_ = new int[2][this.circular_shifts_[0].length];
        int var1 = 0;
        boolean var2 = false;
        boolean var3 = false;
        boolean var4 = false;

        for(int var5 = 0; var5 < this.alphabetized_[0].length; ++var5) {
            int var6 = this.circular_shifts_[0][var5];
            int var7 = this.circular_shifts_[1][var5];
            int var8 = this.line_index_[var6];
            boolean var9 = false;
            int var22;
            if (var6 == this.line_index_.length - 1) {
                var22 = this.chars_.length;
            } else {
                var22 = this.line_index_[var6 + 1];
            }

            char[] var10 = new char[var22 - var8];
            if (var8 != var7) {
                System.arraycopy(this.chars_, var7, var10, 0, var22 - var7);
                var10[var22 - var7] = ' ';
                System.arraycopy(this.chars_, var8, var10, var22 - var7 + 1, var7 - var8 - 1);
            } else {
                System.arraycopy(this.chars_, var8, var10, 0, var22 - var8);
            }

            int var19 = 0;
            int var20 = var1 - 1;

            while(var19 <= var20) {
                int var21 = (var19 + var20) / 2;
                int var11 = this.alphabetized_[0][var21];
                int var12 = this.alphabetized_[1][var21];
                int var13 = this.line_index_[var11];
                boolean var14 = false;
                int var23;
                if (var11 == this.line_index_.length - 1) {
                    var23 = this.chars_.length;
                } else {
                    var23 = this.line_index_[var11 + 1];
                }

                char[] var15 = new char[var23 - var13];
                if (var13 != var12) {
                    System.arraycopy(this.chars_, var12, var15, 0, var23 - var12);
                    var15[var23 - var12] = ' ';
                    System.arraycopy(this.chars_, var13, var15, var23 - var12 + 1, var12 - var13 - 1);
                } else {
                    System.arraycopy(this.chars_, var13, var15, 0, var23 - var13);
                }

                int var16 = var10.length < var15.length ? var10.length : var15.length;
                byte var17 = 0;

                for(int var18 = 0; var18 < var16; ++var18) {
                    if (var10[var18] > var15[var18]) {
                        var17 = 1;
                        break;
                    }

                    if (var10[var18] < var15[var18]) {
                        var17 = -1;
                        break;
                    }
                }

                if (var17 == 0) {
                    if (var10.length < var15.length) {
                        var17 = -1;
                    } else if (var10.length > var15.length) {
                        var17 = 1;
                    }
                }

                switch(var17) {
                    case -1:
                        var20 = var21 - 1;
                        break;
                    case 1:
                        var19 = var21 + 1;
                        break;
                    default:
                        var19 = var21;
                        var20 = var21 - 1;
                }
            }

            System.arraycopy(this.alphabetized_[0], var19, this.alphabetized_[0], var19 + 1, var1 - var19);
            System.arraycopy(this.alphabetized_[1], var19, this.alphabetized_[1], var19 + 1, var1 - var19);
            this.alphabetized_[0][var19] = var6;
            this.alphabetized_[1][var19] = var7;
            ++var1;
        }

    }

    public void output() {
        for(int var1 = 0; var1 < this.alphabetized_[0].length; ++var1) {
            int var2 = this.alphabetized_[0][var1];
            int var3 = this.alphabetized_[1][var1];
            int var4 = this.line_index_[var2];
            boolean var5 = false;
            int var7;
            if (var2 == this.line_index_.length - 1) {
                var7 = this.chars_.length;
            } else {
                var7 = this.line_index_[var2 + 1];
            }

            int var6;
            if (var4 == var3) {
                for(var6 = var4; var6 < var7; ++var6) {
                    System.out.print(this.chars_[var6]);
                }
            } else {
                for(var6 = var3; var6 < var7; ++var6) {
                    System.out.print(this.chars_[var6]);
                }

                System.out.print(' ');

                for(var6 = var4; var6 < var3 - 1; ++var6) {
                    System.out.print(this.chars_[var6]);
                }
            }

            System.out.print('\n');
        }

    }

    public void newCircularShift() {
        ArrayList var1 = new ArrayList();

        for(int var2 = 0; var2 < this.line_index_.length; ++var2) {
            if (var2 == this.line_index_.length - 1) {
                var1.add(String.copyValueOf(this.chars_, this.line_index_[var2], this.chars_.length - this.line_index_[var2]));
            } else {
                var1.add(String.copyValueOf(this.chars_, this.line_index_[var2], this.line_index_[var2 + 1] - this.line_index_[var2]));
            }
        }

        String var13 = "";
        int var3 = 0;
        int var4 = 0;
        Iterator var5 = var1.iterator();

        String var6;
        String[] var7;
        String[] var8;
        int var9;
        int var10;
        while(var5.hasNext()) {
            var6 = (String)var5.next();
            var7 = var6.split(" ");
            var8 = var7;
            var9 = var7.length;

            for(var10 = 0; var10 < var9; ++var10) {
                String var10000 = var8[var10];
                ++var3;
                ++var4;
            }
        }

        this.shifts_index_ = new int[var3];
        this.shifts_lines_len_ = new int[var4];
        var3 = 0;
        var4 = 0;
        var5 = var1.iterator();

        while(var5.hasNext()) {
            var6 = (String)var5.next();
            var7 = var6.split(" ");
            var8 = var7;
            var9 = var7.length;

            for(var10 = 0; var10 < var9; ++var10) {
                String var11 = var8[var10];
                String var12 = "";
                if (var6.indexOf(var11) == 0) {
                    var12 = var12 + var6;
                } else {
                    var12 = var12 + var6.substring(var6.indexOf(var11));
                    var12 = var12 + " " + var6.substring(0, var6.indexOf(var11) - 1);
                }

                if (var13.length() == 0) {
                    this.shifts_index_[var3++] = 0;
                } else {
                    this.shifts_index_[var3++] = var13.length();
                }

                var13 = var13 + var12;
                this.shifts_lines_len_[var4++] = var12.length();
            }
        }

        this.shifts_chars_ = new char[var13.length()];
        System.arraycopy(var13.toCharArray(), 0, this.shifts_chars_, 0, var13.length());
    }

    public void newAlphabetizing() {
        this.quickSort(this.shifts_index_, 0, this.shifts_index_.length - 1);
    }

    private void swap(int[] var1, int var2, int var3) {
        int var4 = var1[var2];
        var1[var2] = var1[var3];
        var1[var3] = var4;
    }

    private void quickSort(int[] var1, int var2, int var3) {
        boolean var5 = false;
        if (var2 < var3) {
            int var6 = this.partition(var1, var2, var3);
            this.swap(var1, var2, var6);
            this.swap(this.shifts_lines_len_, var2, var6);
            this.quickSort(var1, var2, var6 - 1);
            this.quickSort(var1, var6 + 1, var3);
        }

    }

    private int partition(int[] var1, int var2, int var3) {
        int var4 = var1[var2];
        int var5 = var2 + 1;
        int var6 = var3;

        while(true) {
            while(true) {
                if (this.shifts_chars_[var1[var6--]] <= this.shifts_chars_[var4]) {
                    ++var6;

                    while(this.shifts_chars_[var1[var5++]] < this.shifts_chars_[var4]) {
                    }

                    --var5;
                    if (var5 >= var6) {
                        return var6;
                    }

                    this.swap(var1, var5, var6);
                    this.swap(this.shifts_lines_len_, var5, var6);
                    ++var5;
                    --var6;
                }
            }
        }
    }

    public void filter() {
        String var1 = "";

        for(int var2 = 0; var2 < this.shifts_index_.length; ++var2) {
            int var3 = this.shifts_index_[var2];
            if (this.shifts_chars_[var3] >= '0' && this.shifts_chars_[var3] <= '9') {
                int[] var6 = new int[this.shifts_index_.length - 1];
                if (var2 == this.shifts_index_.length - 1) {
                    System.arraycopy(this.shifts_index_, 0, var6, 0, this.shifts_index_.length - 1);
                } else {
                    System.arraycopy(this.shifts_index_, 0, var6, 0, var2);
                    System.arraycopy(this.shifts_index_, var2 + 1, var6, var2, this.shifts_index_.length - var2 - 1);
                    this.shifts_index_ = var6;
                }
            } else if (var2 == this.shifts_index_.length - 1) {
                var1.concat(new String(this.shifts_chars_, var3, this.shifts_chars_.length - var3));
            } else {
                int var4 = this.shifts_index_[var2 + 1];
                String var5 = new String(this.shifts_chars_, var3, var4 - var3);
                var1.concat(var5);
            }
        }

    }

    public void newOutPut() {
        for(int var1 = 0; var1 < this.shifts_index_.length; ++var1) {
            System.out.println(String.valueOf(this.shifts_chars_, this.shifts_index_[var1], this.shifts_lines_len_[var1]));
        }

    }

    public static void main(String[] var0) {
        KWIC var1 = new KWIC();
        if (var0.length != 1) {
            System.err.println("KWIC Usage: java KWIC file_name");
            System.exit(1);
        }

        var1.input(var0[0]);
        var1.newCircularShift();
        var1.filter();
        var1.newAlphabetizing();
        var1.newOutPut();
    }
}
