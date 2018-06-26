package vn.edu.hcmus.ldolphin.data;

import android.graphics.Bitmap;

import java.util.List;

public class Utils {
    public static void prepareData(List<Article> articles) {
        articles.add(new Article("_2324", "Nguyễn Văn B",
                "Yesterday",
                "Hình này ở đâu nhỉ ?"));
        articles.add(new Article("_2324", "Nguyễn Văn C",
                "9:50am June 5",
                "Hình này xấu..."));
        articles.add(new Article("_2324", "Nguyễn Văn D",
                "9:50am June 5",
                "Hình này đẹp"));
        articles.add(new Article("_2324", "Nguyễn Văn E",
                "9:50am June 5",
                "Hình này hơi đẹp"));
        articles.add(new Article("_2324", "Nguyễn Văn F",
                "9:50am June 4",
                " Đẹp vl"));
        articles.add(new Article("_2324", "Nguyễn Văn G",
                "9:40am June 4",
                "Hihihihi"));
        articles.add(new Article("_2324", "Nguyễn Văn H",
                "9:33am June 4",
                "Huh.."));
        articles.add(new Article("_2324", "Nguyễn Văn K",
                "9:50am June 2",
                "Spammer"));
        articles.add(new Article("_2324", "Nguyễn Văn L",
                "9:25am June 2",
                "Des nên ngắn thôi"));
        articles.add(new Article("_2324", "Nguyễn Văn M",
                "9:14am June 2",
                "Chủ yếu xem hình"));
        articles.add(new Article("_2324", "Nguyễn Văn N",
                "5:24am June 2",
                "Okay."));
    }

    public static void prepareDataProfile(List<Article> articles) {
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "Yesterday",
                "Hình này ở đâu nhỉ ?"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:50am June 5",
                "Hình này xấu..."));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:50am June 5",
                "Hình này đẹp"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:50am June 5",
                "Hình này hơi đẹp"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:50am June 4",
                " Đẹp vl"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:40am June 4",
                "Hihihihi"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:33am June 4",
                "Huh.."));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:50am June 2",
                "Spammer"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:25am June 2",
                "Des nên ngắn thôi"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "9:14am June 2",
                "Chủ yếu xem hình"));
        articles.add(new Article("_2324", "Nguyễn Văn A",
                "5:24am June 2",
                "Okay."));

    }

    public static Bitmap getResizedBitmap(Bitmap image, double ratio) {
        long width = image.getWidth();
        long height = image.getHeight();

//        float bitmapRatio = (float)width / (float) height;
//        if (bitmapRatio > 0) {
//            width = maxSize;
//            height = (int) (width / bitmapRatio);
//        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
//        }

        width = Math.round(width * ratio);
        height = Math.round(height * ratio);
        return Bitmap.createScaledBitmap(image, (int) width, (int) height, true);
    }
}
