package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.ImageHandlingJava;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.utils.FileKt.getFolderFiles;

public class ImageHandler {

    private static final int[] checkArray = new int[1];

    private static final Integer transparent = 0;

    private static Integer getX1(Rectangle rect) {
        return rect.x;
    }

    private static void setX1(Rectangle rect, Integer value) {
        rect.width += getX1(rect) - value;
        rect.x = value;
    }

    private static Integer getY1(Rectangle rect) {
        return rect.y;
    }

    private static void setY1(Rectangle rect, Integer value) {
        rect.height += getY1(rect) - value;
        rect.y = value;
    }

    private static Integer getX2(Rectangle rect) {
        return getX1(rect) + rect.width - 1;
    }

    private static void setX2(Rectangle rect, Integer value) {
        rect.width += value - getX2(rect);
    }

    private static Integer getY2(Rectangle rect) {
        return getY1(rect) + rect.height - 1;
    }

    private static void setY2(Rectangle rect, Integer value) {
        rect.height += value - getY2(rect);
    }

    private static void include(Rectangle rect, Integer x, Integer y) {
        if (getX1(rect) > x) {
            setX1(rect, x);
        }
        if (getY1(rect) > y) {
            setY1(rect, y);
        }
        if (getX2(rect) < x) {
            setX2(rect, x);
        }
        if (getY2(rect) < y) {
            setY2(rect, y);
        }
    }

    private static void include(Rectangle rect, Map.Entry<Integer, Integer> pair) {
        include(rect, pair.getKey(), pair.getValue());
    }

    private static Boolean isTransparent(Raster raster, Integer x, Integer y) {
        Boolean isTransparent = raster.getPixel(x, y, checkArray)[0] == transparent;
        checkArray[0] = 0;
        return isTransparent;
    }

    private static Boolean isTransparent(Raster raster, Map.Entry<Integer, Integer> pair) {
        return isTransparent(raster, pair.getKey(), pair.getValue());
    }

    private static Rectangle calculateSpriteRect(
            Raster alphaRaster,
            Integer startX,
            Integer startY
    ) {
        Rectangle rectangle = new Rectangle(startX, startY, 1, 1);
        Queue<Map.Entry<Integer, Integer>> chordsQueue = new ArrayDeque<>();
        List<Map.Entry<Integer, Integer>> watched = new ArrayList<>();
        chordsQueue.offer(new AbstractMap.SimpleEntry<>(startX, startY));

        while (!chordsQueue.isEmpty()) {
            Map.Entry<Integer, Integer> current = chordsQueue.poll();

            if (!isTransparent(alphaRaster, current)) {
                include(rectangle, current);

                int leftBound = 0;
                int rightBound = alphaRaster.getWidth() - 1;
                int topBound = 0;
                int bottomBound = alphaRaster.getHeight() - 1;

                if (current.getKey() > 0) {
                    leftBound = current.getKey() - 1;
                }

                if (current.getKey() < alphaRaster.getWidth() - 1) {
                    rightBound = current.getKey() + 1;
                }

                if (current.getValue() > 0) {
                    topBound = current.getValue() - 1;
                }

                if (current.getValue() < alphaRaster.getHeight() - 1) {
                    bottomBound = current.getValue() + 1;
                }

                for (int y = topBound; y <= bottomBound; y++) {
                    for (int x = leftBound; x <= rightBound; x++) {
                        Map.Entry<Integer, Integer> pair = new AbstractMap.SimpleEntry<>(x, y);
                        if (!watched.contains(pair)) {
                            watched.add(pair);
                            chordsQueue.offer(pair);
                        }
                    }
                }
            }
        }
        return rectangle;
    }

    private static Set<Rectangle> unique(Collection<Rectangle> input) {
        Set<Rectangle> result = new HashSet<>();

        List<Rectangle> leftToCheck = new ArrayList<>(input);

        while (!leftToCheck.isEmpty()) {
            Rectangle current = leftToCheck.remove(0);

            List<Rectangle> toCompare = new ArrayList<>(leftToCheck);

            for (Rectangle currentToCompare : toCompare) {
                if (current.intersects(currentToCompare)) {
                    current = current.union(currentToCompare);

                    leftToCheck.remove(currentToCompare);
                }
            }
            result.add(current);
        }
        return result;
    }

    private static List<Rectangle> findSprites(Raster alphaRaster) {
        List<Rectangle> result = new ArrayList<>();

        int x = 0;
        int y = 0;

        while (y < alphaRaster.getHeight()) {
            while (x < alphaRaster.getWidth()) {
                if (!isTransparent(alphaRaster, x, y)) {
                    boolean alreadyInResults = false;

                    for (Rectangle currentPotential : result) {
                        if (currentPotential.contains(x, y)) {
                            x = getX2(currentPotential);

                            alreadyInResults = true;
                            break;
                        }
                    }

                    if (!alreadyInResults) {
                        Rectangle newResult = calculateSpriteRect(alphaRaster, x, y);

                        result.add(newResult);

                        x = getX2(newResult);
                    }
                }
                x++;
            }
            y++;
            x = 0;
        }

        return result;
    }

    public static List<BufferedImage> calculateSprites(List<File> from) throws IOException {
        List<BufferedImage> result = new ArrayList<>();

        for (File file : from) {
            BufferedImage image = ImageIO.read(file);

            List<Rectangle> spritesRectangles = findSprites(
                    image.getAlphaRaster()
            );

            for (Rectangle spriteRectangle : unique(spritesRectangles)) {
                result.add(
                        image.getSubimage(
                                spriteRectangle.x,
                                spriteRectangle.y,
                                spriteRectangle.width,
                                spriteRectangle.height
                        )
                );
            }
        }

        return result;
    }

    public static List<BufferedImage> calculateSprites(File from) throws IOException {
        return calculateSprites(getFolderFiles(from));
    }

    public static List<BufferedImage> calculateSprites(String from) throws IOException {
        return calculateSprites(new File(from));
    }
}
