package com.moqi.b.b06.b0610;

import java.util.ArrayList;
import java.util.List;

/**
 * SingleThreadRendere
 * <p/>
 * Rendering page elements sequentially
 * 串行地渲染页面元素
 *
 * @author Brian Goetz and Tim Peierls
 */
public abstract class SingleThreadRenderer {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<ImageData>();
        for (ImageInfo imageInfo : scanForImageInfo(source))
            imageData.add(imageInfo.downloadImage());
        for (ImageData data : imageData)
            renderImage(data);
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }
}
