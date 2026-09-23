/*
 * Decompiled with CFR 0.139.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class KWIC {
    private char[] chars_;
    private int[] line_index_;
    private int[][] circular_shifts_;
    private int[][] alphabetized_;
    private char[] shifts_chars_;
    private int[] shifts_index_;
    private int[] shifts_lines_len_;

    public void input(String string) {
        this.line_index_ = new int[32];
        this.chars_ = new char[32];
        int n = 0;
        int n2 = 0;
        boolean bl = true;
        boolean bl2 = false;
        boolean bl3 = false;
        try {
            int[] arrn;
            FileInputStream fileInputStream = new FileInputStream(string);
            int n3 = ((InputStream)fileInputStream).read();
            while (n3 != -1) {
                switch ((byte)n3) {
                    case 10: {
                        bl = true;
                        break;
                    }
                    case 32: {
                        bl2 = true;
                        break;
                    }
                    case 9: {
                        bl2 = true;
                        break;
                    }
                    case 13: {
                        break;
                    }
                    default: {
                        if (bl) {
                            if (n2 == this.line_index_.length) {
                                arrn = new int[n2];
                                System.arraycopy(this.line_index_, 0, arrn, 0, n2);
                                this.line_index_ = arrn;
                            }
                            this.line_index_[n2] = n;
                            ++n2;
                            bl = false;
                            bl3 = false;
                        }
                        if (bl2) {
                            if (bl3) {
                                if (n == this.chars_.length) {
                                    arrn = new char[n + 1];
                                    System.arraycopy(this.chars_, 0, arrn, 0, n);
                                    this.chars_ = arrn;
                                }
                                this.chars_[n] = 32;
                                ++n;
                            }
                            bl2 = false;
                        }
                        if (n == this.chars_.length) {
                            arrn = new char[n + 1];
                            System.arraycopy(this.chars_, 0, arrn, 0, n);
                            this.chars_ = arrn;
                        }
                        this.chars_[n] = (char)n3;
                        ++n;
                        bl3 = true;
                    }
                }
                n3 = ((InputStream)fileInputStream).read();
            }
            if (n2 != this.line_index_.length) {
                arrn = new int[n2];
                System.arraycopy(this.line_index_, 0, arrn, 0, n2);
                this.line_index_ = arrn;
            }
            if (n != this.chars_.length) {
                arrn = new char[n];
                System.arraycopy(this.chars_, 0, arrn, 0, n);
                this.chars_ = arrn;
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            System.err.println("KWIC Error: Could not open " + string + "file.");
            System.exit(1);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            System.err.println("KWIC Error: Could not read " + string + "file.");
            System.exit(1);
        }
    }

    public void circularShift() {
        this.circular_shifts_ = new int[2][32];
        int n = 0;
        for (int i = 0; i <= this.line_index_.length; ++i) {
            int n2 = 0;
            n2 = i == this.line_index_.length - 1 ? this.chars_.length : this.line_index_[i + 1];
            for (int j = this.line_index_[i]; j < n2; ++j) {
                if (this.chars_[j] != ' ' && j != this.line_index_[i]) continue;
                if (n == this.circular_shifts_[0].length) {
                    int[] arrn = new int[n + 6];
                    System.arraycopy(this.circular_shifts_[0], 0, arrn, 0, n);
                    this.circular_shifts_[0] = arrn;
                    arrn = new int[n + 6];
                    System.arraycopy(this.circular_shifts_[1], 0, arrn, 0, n);
                    this.circular_shifts_[1] = arrn;
                }
                this.circular_shifts_[0][n] = i;
                this.circular_shifts_[1][n] = j == this.line_index_[i] ? j : j + 1;
                ++n;
            }
        }
        if (n != this.circular_shifts_[0].length) {
            int[] arrn = new int[n];
            System.arraycopy(this.circular_shifts_[0], 0, arrn, 0, n);
            this.circular_shifts_[0] = arrn;
            arrn = new int[n];
            System.arraycopy(this.circular_shifts_[1], 0, arrn, 0, n);
            this.circular_shifts_[1] = arrn;
        }
    }

    public void alphabetizing() {
        this.alphabetized_ = new int[2][this.circular_shifts_[0].length];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < this.alphabetized_[0].length; ++i) {
            int n5 = this.circular_shifts_[0][i];
            int n6 = this.circular_shifts_[1][i];
            int n7 = this.line_index_[n5];
            int n8 = 0;
            n8 = n5 == this.line_index_.length - 1 ? this.chars_.length : this.line_index_[n5 + 1];
            char[] arrc = new char[n8 - n7];
            if (n7 != n6) {
                System.arraycopy(this.chars_, n6, arrc, 0, n8 - n6);
                arrc[n8 - n6] = 32;
                System.arraycopy(this.chars_, n7, arrc, n8 - n6 + 1, n6 - n7 - 1);
            } else {
                System.arraycopy(this.chars_, n7, arrc, 0, n8 - n7);
            }
            n2 = 0;
            n3 = n - 1;
            block5 : while (n2 <= n3) {
                n4 = (n2 + n3) / 2;
                int n9 = this.alphabetized_[0][n4];
                int n10 = this.alphabetized_[1][n4];
                int n11 = this.line_index_[n9];
                int n12 = 0;
                n12 = n9 == this.line_index_.length - 1 ? this.chars_.length : this.line_index_[n9 + 1];
                char[] arrc2 = new char[n12 - n11];
                if (n11 != n10) {
                    System.arraycopy(this.chars_, n10, arrc2, 0, n12 - n10);
                    arrc2[n12 - n10] = 32;
                    System.arraycopy(this.chars_, n11, arrc2, n12 - n10 + 1, n10 - n11 - 1);
                } else {
                    System.arraycopy(this.chars_, n11, arrc2, 0, n12 - n11);
                }
                int n13 = arrc.length < arrc2.length ? arrc.length : arrc2.length;
                int n14 = 0;
                for (int j = 0; j < n13; ++j) {
                    if (arrc[j] > arrc2[j]) {
                        n14 = 1;
                        break;
                    }
                    if (arrc[j] >= arrc2[j]) continue;
                    n14 = -1;
                    break;
                }
                if (n14 == 0) {
                    if (arrc.length < arrc2.length) {
                        n14 = -1;
                    } else if (arrc.length > arrc2.length) {
                        n14 = 1;
                    }
                }
                switch (n14) {
                    case 1: {
                        n2 = n4 + 1;
                        continue block5;
                    }
                    case -1: {
                        n3 = n4 - 1;
                        continue block5;
                    }
                }
                n2 = n4;
                n3 = n4 - 1;
            }
            System.arraycopy(this.alphabetized_[0], n2, this.alphabetized_[0], n2 + 1, n - n2);
            System.arraycopy(this.alphabetized_[1], n2, this.alphabetized_[1], n2 + 1, n - n2);
            this.alphabetized_[0][n2] = n5;
            this.alphabetized_[1][n2] = n6;
            ++n;
        }
    }

    public void output() {
        for (int i = 0; i < this.alphabetized_[0].length; ++i) {
            int n;
            int n2 = this.alphabetized_[0][i];
            int n3 = this.alphabetized_[1][i];
            int n4 = this.line_index_[n2];
            int n5 = 0;
            n5 = n2 == this.line_index_.length - 1 ? this.chars_.length : this.line_index_[n2 + 1];
            if (n4 != n3) {
                for (n = n3; n < n5; ++n) {
                    System.out.print(this.chars_[n]);
                }
                System.out.print(' ');
                for (n = n4; n < n3 - 1; ++n) {
                    System.out.print(this.chars_[n]);
                }
            } else {
                for (n = n4; n < n5; ++n) {
                    System.out.print(this.chars_[n]);
                }
            }
            System.out.print('\n');
        }
    }

    public void newCircularShift() {
        String[] arrstring;
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < this.line_index_.length; ++i) {
            if (i == this.line_index_.length - 1) {
                arrayList.add(String.copyValueOf(this.chars_, this.line_index_[i], this.chars_.length - this.line_index_[i]));
                continue;
            }
            arrayList.add(String.copyValueOf(this.chars_, this.line_index_[i], this.line_index_[i + 1] - this.line_index_[i]));
        }
        String string = "";
        int n = 0;
        int n2 = 0;
        for (String string2 : arrayList) {
            for (String string3 : arrstring = string2.split(" ")) {
                ++n;
                ++n2;
            }
        }
        this.shifts_index_ = new int[n];
        this.shifts_lines_len_ = new int[n2];
        n = 0;
        n2 = 0;
        for (String string2 : arrayList) {
            for (String string3 : arrstring = string2.split(" ")) {
                String string4 = "";
                if (string2.indexOf(string3) == 0) {
                    string4 = string4 + string2;
                } else {
                    string4 = string4 + string2.substring(string2.indexOf(string3));
                    string4 = string4 + " " + string2.substring(0, string2.indexOf(string3) - 1);
                }
                this.shifts_index_[n++] = string.length() == 0 ? 0 : string.length();
                string = string + string4;
                this.shifts_lines_len_[n2++] = string4.length();
            }
        }
        this.shifts_chars_ = new char[string.length()];
        System.arraycopy(string.toCharArray(), 0, this.shifts_chars_, 0, string.length());
    }

    public void newAlphabetizing() {
        this.quickSort(this.shifts_index_, 0, this.shifts_index_.length - 1);
    }

    private void swap(int[] arrn, int n, int n2) {
        int n3 = arrn[n];
        arrn[n] = arrn[n2];
        arrn[n2] = n3;
    }

    private void quickSort(int[] arrn, int n, int n2) {
        int n3 = n;
        int n4 = 0;
        if (n < n2) {
            n4 = this.partition(arrn, n, n2);
            this.swap(arrn, n3, n4);
            this.swap(this.shifts_lines_len_, n3, n4);
            this.quickSort(arrn, n3, n4 - 1);
            this.quickSort(arrn, n4 + 1, n2);
        }
    }

    private int partition(int[] arrn, int n, int n2) {
        int n3 = arrn[n];
        int n4 = n + 1;
        int n5 = n2;
        do {
            if (this.shifts_chars_[arrn[n5--]] > this.shifts_chars_[n3]) {
                continue;
            }
            ++n5;
            while (this.shifts_chars_[arrn[n4++]] < this.shifts_chars_[n3]) {
            }
            if (--n4 >= n5) break;
            this.swap(arrn, n4, n5);
            this.swap(this.shifts_lines_len_, n4, n5);
            ++n4;
            --n5;
        } while (true);
        return n5;
    }

    public void filter() {
        String string = "";
        for (int i = 0; i < this.shifts_index_.length; ++i) {
            int n = this.shifts_index_[i];
            if (this.shifts_chars_[n] < '0' || this.shifts_chars_[n] > '9') {
                if (i == this.shifts_index_.length - 1) {
                    string.concat(new String(this.shifts_chars_, n, this.shifts_chars_.length - n));
                    continue;
                }
                int n2 = this.shifts_index_[i + 1];
                String string2 = new String(this.shifts_chars_, n, n2 - n);
                string.concat(string2);
                continue;
            }
            int[] arrn = new int[this.shifts_index_.length - 1];
            if (i == this.shifts_index_.length - 1) {
                System.arraycopy(this.shifts_index_, 0, arrn, 0, this.shifts_index_.length - 1);
                continue;
            }
            System.arraycopy(this.shifts_index_, 0, arrn, 0, i);
            System.arraycopy(this.shifts_index_, i + 1, arrn, i, this.shifts_index_.length - i - 1);
            this.shifts_index_ = arrn;
        }
    }

    public void newOutPut() {
        for (int i = 0; i < this.shifts_index_.length; ++i) {
            System.out.println(String.valueOf(this.shifts_chars_, this.shifts_index_[i], this.shifts_lines_len_[i]));
        }
    }

    public static void main(String[] arrstring) {
        KWIC kWIC = new KWIC();
        if (arrstring.length != 1) {
            System.err.println("KWIC Usage: java KWIC file_name");
            System.exit(1);
        }
        kWIC.input(arrstring[0]);
        kWIC.newCircularShift();
        kWIC.filter();
        kWIC.newAlphabetizing();
        kWIC.newOutPut();
    }
}