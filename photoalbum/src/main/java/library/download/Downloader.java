package library.download;

import library.data.ResponseInfo;

/**
 * Created by Administrator on 2016/11/2.
 */

public interface Downloader {
    ResponseInfo downloadImgByUrl(String urlStr);
}
