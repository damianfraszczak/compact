/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;
// Represents native methods.

public class NativeMethods {
    public static native String resizeImageFromImageSource(String url, int width, int height, DefaultCallback callback)
        /*-{
            var result = "";
            var img = new Image();

            img.src = url;
            img.onload = function () {
                var canvas = document.createElement('canvas'), ctx = canvas
                    .getContext('2d');
                canvas.width = width;
                canvas.height = height;
                ctx.drawImage(img, 0, 0, width, height);
                callback.@pl.edu.wat.wcy.cop.app.client.utils.DefaultCallback::onSuccess(Ljava/lang/String;)(canvas.toDataURL());
            };
            return result;
        }-*/;

    public static native void putToClipboard(String text) /*-{

        var textArea = $doc.createElement("textarea");
        textArea.style.position = 'fixed';
        textArea.style.top = 0;
        textArea.style.left = 0;
        textArea.style.width = '2em';
        textArea.style.height = '2em';
        textArea.style.padding = 0;
        textArea.style.border = 'none';
        textArea.style.outline = 'none';
        textArea.style.boxShadow = 'none';
        textArea.style.background = 'transparent';
        textArea.value = text;
        $doc.body.appendChild(textArea);
        textArea.select();
        try {
            $doc.execCommand('copy');
        } catch (err) {
        }
        $doc.body.removeChild(textArea);

    }-*/;

    public interface UploadedFileReadListener {
        void onUploadedFileRead(String text);
    }
}
